package mapeamento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mapeamento.xml.EntidadeMptXml;
import mapeamento.xml.MapeamentoXml;

/**
 * Responsável por gerar e atualizar o conteúdo do arquivo 'EnumMapeamento.java'
 * 
 * @author - Fradico e Bregaida
 */
public class GrdEnumMapeamento
{
	private static final String	RETORNO									= "\r\n";
	private static final String	TAB										= "\t";

	private static final String	REGEX_PADRAO_ENUM_MAPEAMENTO_ENTIDADE	= "/[*] FIM ENTIDADE [*]/";
	private static final String	REGEX_PADRAO_ENUM_MAPEAMENTO_PK			= ";(.)*\\r\\n(.)*/[*] FIM PK [*]/";
	private static final String	REGEX_PADRAO_ENUM_MAPEAMENTO_IMPORT		= "/[*] FIM IMPORT [*]/";
	private static final String	TOKEN_ENUM_MAPEAMENTO_ENTIDADE			= "/* FIM ENTIDADE */";
	private static final String	TOKEN_ENUM_MAPEAMENTO_PK				= "/* FIM PK */";
	private static final String	TOKEN_ENUM_MAPEAMENTO_IMPORT			= "/* FIM IMPORT */";

	private MapeamentoXml		mpt										= null;
	private String				caminhoArquivo							= "";

	public GrdEnumMapeamento(MapeamentoXml mpt, String caminhoArquivo) throws FileNotFoundException
	{
		this.mpt = mpt;
		this.caminhoArquivo = caminhoArquivo;
	}

	/**
	 * Gera e atualiza o conteúdo para o arquivo 'EnumMapeamento.java'.  
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

		StringBuffer entidadeBuffer = new StringBuffer();
		StringBuffer pkBuffer = new StringBuffer();
		StringBuffer importBuffer = new StringBuffer();
		CharSequence sequence;
		String entidadeEnum, pkEnum;

		for (EntidadeMptXml entidade : mpt.getContent())
		{
			sequence = new String(entidade.getNome() + ".class");
			if (!conteudo.contains(sequence))
			{
				entidadeEnum = formataMaiusculoEUnderscore(entidade.getNome());
				inserirBuffer(entidadeBuffer, entidadeEnum, "(", entidade.getNome(), ".class, ", "Enum", entidade.getNome().substring(0, entidade.getNome().length() - 1), "Mpt.values()),", RETORNO, TAB);

				inserirBuffer(importBuffer, "import ", entidade.getEntidadePacote(), ".", entidade.getNome(), ";", RETORNO);

				inserirBuffer(importBuffer, "import ", entidade.getEntidadeMptPacote(), ".", "Enum", entidade.getNome().substring(0, entidade.getNome().length() - 1), "Mpt", ";", RETORNO);
			}
			if(entidade.getPk()!=null){
				sequence = new String(entidade.getPk() + ".class");
				if (!conteudo.contains(sequence))
				{
					pkEnum = formataMaiusculoEUnderscore(entidade.getPk());
					inserirBuffer(pkBuffer, pkEnum, "(", entidade.getPk(), ".class, ", "Enum", entidade.getPk(), "Mpt.values()),", RETORNO, TAB);
	
					inserirBuffer(importBuffer, "import ", entidade.getPkPacote(), ".", "Enum", entidade.getPk(), ";", RETORNO);
	
					inserirBuffer(importBuffer, "import ", entidade.getPkMptPacote(), ".", "Enum", entidade.getPk(), "Mpt", ";", RETORNO);
				}
			}
		}

		String[] conteudoPartes;

		StringBuffer arquivoRemapeado = new StringBuffer();

		if (importBuffer.length() > 0)
		{
			inserirBuffer(importBuffer, TOKEN_ENUM_MAPEAMENTO_IMPORT);
			conteudoPartes = conteudo.split(REGEX_PADRAO_ENUM_MAPEAMENTO_IMPORT);
			arquivoRemapeado = new StringBuffer();
			inserirBuffer(arquivoRemapeado, conteudoPartes[0], importBuffer.toString(), conteudoPartes[1]);
		}

		if (entidadeBuffer.length() > 0)
		{
			inserirBuffer(entidadeBuffer, TOKEN_ENUM_MAPEAMENTO_ENTIDADE);
			conteudoPartes = arquivoRemapeado.toString().split(REGEX_PADRAO_ENUM_MAPEAMENTO_ENTIDADE);
			arquivoRemapeado = new StringBuffer();
			inserirBuffer(arquivoRemapeado, conteudoPartes[0], entidadeBuffer.toString(), conteudoPartes[1]);
		}

		if (pkBuffer.length() > 0)
		{
			inserirBuffer(pkBuffer, ";", RETORNO, TAB, TOKEN_ENUM_MAPEAMENTO_PK);
			conteudoPartes = arquivoRemapeado.toString().split(REGEX_PADRAO_ENUM_MAPEAMENTO_PK);
			arquivoRemapeado = new StringBuffer();
			inserirBuffer(arquivoRemapeado, conteudoPartes[0], pkBuffer.toString(), conteudoPartes[1]);
		}

		if (importBuffer.length() > 0 || entidadeBuffer.length() > 0 || pkBuffer.length() > 0)
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
