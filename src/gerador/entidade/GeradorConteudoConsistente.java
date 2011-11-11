package gerador.entidade;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import exception.MapeamentoException;
import gerador.DicionarioCarregador;

public class GeradorConteudoConsistente
{

	private Map<Class, List>	entidadesConsistentesEConteudoMap	= new HashMap<Class, List>();

	public GeradorConteudoConsistente() throws FileNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, MapeamentoException
	{
		DicionarioCarregador carregador = new DicionarioCarregador();
		entidadesConsistentesEConteudoMap = carregador.carregarConteudoParaEntidadesConsistentes();
	}

	public Object obterEntidadeConsistente(Class klass)
	{
		return obterEntidadeConsistenteAleatorio(klass);
	}

	/**
	 * Método que retorna uma entidade consistente aleatoriamente dada o seu tipo de classe.
	 * 
	 * @param klass
	 * @return Object - a entidade com seu conteúdo preenchido/carregado.
	 */
	private Object obterEntidadeConsistenteAleatorio(Class klass)
	{
		List<?> entidadesList = entidadesConsistentesEConteudoMap.get(klass);

		if (entidadesList != null && !entidadesList.isEmpty())
		{
			Collections.shuffle(entidadesList);
			Random random = new Random();
			int pos = random.nextInt(entidadesList.size());
			return entidadesList.get(pos);
		}

		return null;
	}

}
