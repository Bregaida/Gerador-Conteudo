/**
 * 
 */
package util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Bregaida e Fradico
 * 
 *         Classe com métodos comuns para o projeto
 */
public class Util
{

	public static String caminhoArquivo(String nomeArquivo)
	{
		File file = null;
		file = new File(nomeArquivo);
		
		String path = file.getPath();
		return path;
//		ClassLoader classLoader = Util.class.getClassLoader();
//		return classLoader.getResource(nomeArquivo).getFile();
	}

}
