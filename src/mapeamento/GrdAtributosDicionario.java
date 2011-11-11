/**
 * 
 */
package mapeamento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import mapeamento.xml.AtributoMptXml;
import mapeamento.xml.EntidadeMptXml;
import mapeamento.xml.MapeamentoXml;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 	@author Bregaida e Fradico
 *
 *	Classe para gerar os atributos dentro da classe AtributosDicionario.java
 *
 */
public class GrdAtributosDicionario
{
	/* Tokens e expressões regulares para validar o fim de onde deve-se atribuir as novas linhas que serão geradas,
	 * tais anotações na classe DEVEM estar lá, então verifique se ninguém as apagou na classe AtributosDicionario.java */
	private static final String	TOKEN_PADRAO_ATRIBUTOS_DICIONARIO	= "/* FIM ATRIBUTOS */";
	private static final String	TOKEN_GETTERS_AND_SETTERS			= "/* FIM GETTERS AND SETTERS */";
	private static final String	REGEX_PADRAO_ATRIBUTOS				= "/[*] FIM ATRIBUTOS [*]/";
	private static final String	REGEX_PADRAO_GETTERS_AND_SETTERS	= "/[*] FIM GETTERS AND SETTERS [*]/";

	/*Constantes para formatação da geração*/
	private static final String	RETORNO								= "\r\n";
	private static final String	TAB									= "\t";

	private MapeamentoXml		mpt									= null;
	private String				caminhoArquivo						= "";

	/**
	 * Construtor que inicializa o caminho da classe, do XML e instancia a leitura do XML do XStream
	 * @throws FileNotFoundException
	 */
	public GrdAtributosDicionario(MapeamentoXml mpt, String caminhoArquivo) throws FileNotFoundException
	{
		this.mpt = mpt;
		this.caminhoArquivo = caminhoArquivo;
	}

	/**
	 * Método que tem como função gerar a estrutura da declaração dos atributos com a anotação do tipo e seus respectivos getters and setters.
	 * @return String - conteúdo gerado
	 * @throws IOException
	 */
	public String gerar() throws IOException
	{
		File arquivo = new File(caminhoArquivo);
		BufferedReader reader = new BufferedReader(new FileReader(arquivo));
		char[] conteudoChars = new char[(int) arquivo.length()];
		reader.read(conteudoChars);
		String conteudo = String.valueOf(conteudoChars);

		String[] conteudoPartes;

		StringBuffer arquivoRemapeado = new StringBuffer();

		StringBuffer atributosBuffer = new StringBuffer();
		StringBuffer gerarGetSetBuffer = new StringBuffer();

		for (EntidadeMptXml entidade : mpt.getContent())
		{
			for (AtributoMptXml atributo : entidade.getContent())
			{
				CharSequence sequencia = new String(atributo.getAlias());
				if (!conteudo.contains(sequencia))
				{
					inserirBuffer(atributosBuffer, "@DicionarioAnotacao(tipoInfo=EnumTipoInfoAtributo.", atributo.getInformacao(), ") ", RETORNO, TAB, "private ", atributo.getTipo(), " ", atributo.getAlias(), ";", RETORNO, TAB);

					// get
					inserirBuffer(gerarGetSetBuffer, "public ", atributo.getTipo(), " get", StringUtils.capitalize(atributo.getAlias()), "(){", RETORNO, TAB, TAB, "return  ", atributo.getAlias(), ";", RETORNO, TAB, "}", RETORNO, TAB);

					// set
					inserirBuffer(gerarGetSetBuffer, "public void ", "set", StringUtils.capitalize(atributo.getAlias()) , "(", atributo.getTipo(), " ", atributo.getAlias(), "){", RETORNO, TAB, TAB, "this.", atributo.getAlias(), " = ", atributo.getAlias(), ";", RETORNO, TAB, "}", RETORNO, TAB);

				}

			}
		}

		if (atributosBuffer.length() > 0)
		{
			inserirBuffer(atributosBuffer, TOKEN_PADRAO_ATRIBUTOS_DICIONARIO);
			conteudoPartes = conteudo.toString().split(REGEX_PADRAO_ATRIBUTOS);
			arquivoRemapeado = new StringBuffer();
			inserirBuffer(arquivoRemapeado, conteudoPartes[0], atributosBuffer.toString(), conteudoPartes[1]);
		}

		if (gerarGetSetBuffer.length() > 0)
		{
			inserirBuffer(gerarGetSetBuffer, RETORNO, TAB, TOKEN_GETTERS_AND_SETTERS);
			conteudoPartes = arquivoRemapeado.toString().split(REGEX_PADRAO_GETTERS_AND_SETTERS);
			arquivoRemapeado = new StringBuffer();
			inserirBuffer(arquivoRemapeado, conteudoPartes[0], gerarGetSetBuffer.toString(), conteudoPartes[1]);
		}

		if (atributosBuffer.length() > 0 || gerarGetSetBuffer.length() > 0)
		{
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
	 * Método para inserção das Strings a serem geradas na classe dentro de um buffer
	 * @param buffer
	 * @param args
	 */
	private void inserirBuffer(StringBuffer buffer, String... args)
	{
		for (String arg : args)
		{
			buffer.append(arg);
		}
	}

}
