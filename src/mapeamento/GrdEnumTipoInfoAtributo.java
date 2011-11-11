package mapeamento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import util.Util;

import mapeamento.xml.AtributoMptXml;
import mapeamento.xml.EntidadeMptXml;
import mapeamento.xml.MapeamentoXml;

/**
 * Responsável por gerar e atualizar o conteúdo do arquivo 'EnumTipoInfoAtributo.java'.
 * 
 * @author - Fradico e Bregaida
 */
public class GrdEnumTipoInfoAtributo
{
	private static final String	RETORNO									= "\r\n";
	private static final String	TAB										= "\t";

	private static final String	REGEX_PADRAO_ENUM_TIPO_INFO_ATRIBUTO	= ";(.)*\\r\\n(.)*/[*] FIM DAS DECLARACOES [*]/";
	private static final String	TOKEN_ENUM_TIPO_INFO_ATRIBUTO			= "/* FIM DAS DECLARACOES */";

	private MapeamentoXml		mpt										= null;
	private String				caminhoArquivo							= "";

	public GrdEnumTipoInfoAtributo(MapeamentoXml mpt, String caminhoArquivo) throws FileNotFoundException
	{
		this.mpt = mpt;
		this.caminhoArquivo = caminhoArquivo;
	}

	/**
	 * Gera e atualiza o conteúdo para o arquivo 'EnumTipoInfoAtributo.java'.
	 * @return
	 * @throws IOException
	 */
	public String gerar() throws IOException
	{
		File arquivo = new File(caminhoArquivo);

		BufferedReader reader = new BufferedReader(new FileReader(arquivo));
		char[] conteudoChars = new char[(int) arquivo.length()];
		reader.read(conteudoChars);
		String conteudo = String.valueOf(conteudoChars);

		String[] conteudoPartes = conteudo.split(REGEX_PADRAO_ENUM_TIPO_INFO_ATRIBUTO);

		StringBuffer arquivoRemapeado = new StringBuffer();
		arquivoRemapeado.append(conteudoPartes[0]);

		StringBuffer atributosBuffer = new StringBuffer();
		CharSequence seq;

		for (EntidadeMptXml entidade : mpt.getContent())
		{
			for (AtributoMptXml atributo : entidade.getContent())
			{
				seq = new String(atributo.getInformacao());
				if (!conteudo.contains(seq))
				{
					inserirBuffer(atributosBuffer, atributo.getInformacao(), "(0, ", atributo.getTipo(), ".class),", RETORNO, TAB);
				}
			}
		}

		if (atributosBuffer.length() > 0)
		{
			inserirBuffer(atributosBuffer, ";", RETORNO, TAB, TOKEN_ENUM_TIPO_INFO_ATRIBUTO);

			inserirBuffer(arquivoRemapeado, atributosBuffer.toString(), conteudoPartes[1]);

			String novoConteudo = arquivoRemapeado.toString();

			BufferedWriter out = new BufferedWriter(new FileWriter(arquivo));
			out.write(novoConteudo);
			out.flush();
			out.close();
			return novoConteudo;
		}

		return conteudo;
	}

	/**
	 * Concatena strings em um buffer.
	 * @param buffer - buffer de string
	 * @param args - strings a serem concatenadas
	 */
	private void inserirBuffer(StringBuffer buffer, String... args)
	{
		for (String arg : args)
		{
			buffer.append(arg);
		}
	}
}
