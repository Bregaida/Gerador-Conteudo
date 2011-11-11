/**
 * 
 */
package util;

/**
 * @author Bregaida e Fradico
 * 
 *         Classe com m�todos comuns para o projeto
 */
public class Util
{

	public static String caminhoArquivo(String nomeArquivo)
	{
		ClassLoader classLoader = Util.class.getClassLoader();
		return classLoader.getResource(nomeArquivo).getFile();
	}

}
