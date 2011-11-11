package mapeamento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import util.Util;

import enumerador.EnumArquivoNomes;

import mapeamento.xml.AtributoMptXml;
import mapeamento.xml.EntidadeMptXml;
import mapeamento.xml.MapeamentoXml;

/**
 * Responsável por gerar e atualizar o conteúdo do arquivo 'GrdEnumEntidadeMpt.java', em 
 * que 'Entidade' é o nome da entidade a que se refere.
 * 
 * @author - Fradico e Bregaida
 */
public class GrdEnumEntidadeMpt
{

	private static final String	RETORNO							= "\r\n";
	private static final String	TAB								= "\t";

	private static final String	REGEX_PADRAO_ENUM_ENTIDADE_MPT	= ";(.)*\\r\\n(.)*/[*] FIM DAS DECLARACOES [*]/";
	private static final String	TOKEN_ENUM_ENTIDADE_MPT			= "/* FIM DAS DECLARACOES */";

	private static final String	ENUM_ENTIDADE_MPT_NOME			= "ENUM_NOME";

	private MapeamentoXml		mpt								= null;
	private String				caminhoArquivo					= "";

	public GrdEnumEntidadeMpt(MapeamentoXml mpt, String caminhoArquivo) throws FileNotFoundException
	{
		this.mpt = mpt;
		this.caminhoArquivo = caminhoArquivo;
	}

	private void criarArquivoEConteudo(File arquivo, EntidadeMptXml entidade) throws IOException
	{
		arquivo.createNewFile();

		StringBuffer className = new StringBuffer();
		StringBuffer interfaceName = new StringBuffer();

		inserirBuffer(className, "Enum", entidade.getNome().substring(0, entidade.getNome().length() - 1), "Mpt");
		inserirBuffer(interfaceName, "Enum", entidade.getNome().substring(0, entidade.getNome().length() - 1), "MptI");

		File arquivoTemplate = new File(Util.caminhoArquivo(EnumArquivoNomes.ENUM_ENTIDADE_MPT_TEMPLATE.getNome()));
		Reader reader = new BufferedReader(new FileReader(arquivoTemplate));
		char[] conteudoChars = new char[(int) arquivoTemplate.length()];
		reader.read(conteudoChars);
		String conteudoTemplate = String.valueOf(conteudoChars);

		conteudoTemplate = conteudoTemplate.replaceAll(ENUM_ENTIDADE_MPT_NOME, className.toString());

		BufferedWriter out = new BufferedWriter(new FileWriter(arquivo));
		out.write(conteudoTemplate);
		out.flush();
		out.close();
		
	}

	/**
	 * Gera e atualiza o conteúdo para o arquivo 'EnumEntidadeMPT.java', em que 'Entidade' é 
	 * o nome da entidade a que se refere.
	 * @return
	 * @throws IOException
	 */
	public String gerar() throws IOException
	{
		File arquivo = null;
		StringBuffer caminhoArquivoBuffer;

		BufferedReader reader;
		char[] conteudoChars;
		String conteudo = "";

		StringBuffer atributoBuffer = new StringBuffer();
		CharSequence seq;

		for (EntidadeMptXml entidade : mpt.getContent())
		{
			caminhoArquivoBuffer = new StringBuffer(caminhoArquivo);
			inserirBuffer(caminhoArquivoBuffer, "Enum", entidade.getNome().substring(0, entidade.getNome().length() - 1), "Mpt.java");
			arquivo = new File(caminhoArquivoBuffer.toString());

			if (!arquivo.exists())
			{
				criarArquivoEConteudo(arquivo, entidade);
			}

			reader = new BufferedReader(new FileReader(arquivo));
			conteudoChars = new char[(int) arquivo.length()];
			reader.read(conteudoChars);
			conteudo = String.valueOf(conteudoChars);

			atributoBuffer = new StringBuffer();

			for (AtributoMptXml atributo : entidade.getContent())
			{
				seq = new String(atributo.getInformacao());
				if (!conteudo.contains(seq))
				{
					inserirBuffer(atributoBuffer, 
							formataMaiusculoEUnderscore(atributo.getNome()), "(\"", 
														atributo.getNome(), "\", new EnumTipoInfoAtributo[] {EnumTipoInfoAtributo.", 
														atributo.getInformacao(), "}),", RETORNO, TAB);
				}
			}
		}

		String[] conteudoPartes;

		StringBuffer arquivoRemapeado = new StringBuffer();

		if (atributoBuffer.length() > 0)
		{
			inserirBuffer(atributoBuffer, ";", RETORNO, TAB, TOKEN_ENUM_ENTIDADE_MPT);
			conteudoPartes = conteudo.split(REGEX_PADRAO_ENUM_ENTIDADE_MPT);
			inserirBuffer(arquivoRemapeado, conteudoPartes[0], atributoBuffer.toString(), conteudoPartes[1]);

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

	/**
	 * Formata a string de formato padrão java (ex: umaStringExemplo) para formato padrão tipo constante (ex: UMA_STRING_EXEMPLO). 
	 * @param strOriginal - a string original
	 * @return String - a string formatada
	 */
	private String formataMaiusculoEUnderscore(String strOriginal)
	{
		List<Character> resultadoList = new ArrayList<Character>();
		char[] strOriginalChars = strOriginal.toCharArray();
		
		/* Substitui letras maiúsculas por "_", transforma as restantes em maiúsculas e converte para array de char */
		char[] maiusculaUnderscoreChars = strOriginal.replaceAll("[A-Z]", "_").toUpperCase().toCharArray();
		
		/* Re-introduz as letras maiúsculas da string original */
		for (int i = 0; i < maiusculaUnderscoreChars.length; i++)
		{
			resultadoList.add(maiusculaUnderscoreChars[i]);
			if(maiusculaUnderscoreChars[i] == '_')
			{
				resultadoList.add(String.valueOf(strOriginalChars[i]).toUpperCase().toCharArray()[0]);
			}
		}

		
		/* Descarta o primeiro caracter caso seja "_" */
		if(resultadoList.get(0) == '_')
		{
			resultadoList.remove(0);
		}
		
		/* Verifica se contém a substring final "_E", se sim, descarta-a */
		if(resultadoList.get(resultadoList.size()-2) == '_'  &&  resultadoList.get(resultadoList.size()-1) == 'E')
		{
			resultadoList.remove(resultadoList.size()-1);
			resultadoList.remove(resultadoList.size()-1);
		}
		
		char[] resultado = new char[resultadoList.size()];
		
		/* Inicia a iteração a partir da segunda posição, ou seja, ignora o primeiro caracter "_" */
		for (int i = 0; i < resultadoList.size() ; i++)
		{
			resultado[i] = resultadoList.get(i);
		}
		
		return String.valueOf(resultado);
	}
}
