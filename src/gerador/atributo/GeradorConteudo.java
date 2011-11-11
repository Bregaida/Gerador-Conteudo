package gerador.atributo;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

import util.ReflectionUtil;
import enumerador.EnumMapeamento;
import enumerador.EnumTipoInfoAtributo;
import enumerador.mapeamento.entidade.EnumEntidadeMptI;
import exception.MapeamentoException;
import gerador.DicionarioCarregador;

/**
 * Classe responsavel pela instanciacao e geracao de conteudo teste (aleatorio) para entidades e outros tipos.
 * 
 * @author Bregaida e Fradico
 * 
 */
public class GeradorConteudo
{
	private static final int	COLECAO_TAMANHO	= 10;
	/**
	 * O mapa guarda listas de valores de atributos. Uma lista para cada tipo de informacao (EnumTipoInfoAtributo).
	 */
	private Map<EnumTipoInfoAtributo, List<?>>				tipoInfoEConteudoMap					= new HashMap<EnumTipoInfoAtributo, List<?>>();

	public GeradorConteudo() throws FileNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, MapeamentoException
	{
		DicionarioCarregador carregador = new DicionarioCarregador();
		tipoInfoEConteudoMap = carregador.carregarConteudoParaAtributos();
		
		EnumMapeamento.validate();
	}

	/**
	 * Retorna uma instancia populada (com conteudo aleatorio) da classe parametrizada.
	 * 
	 * @param entityToPopulate
	 *            - classe do objeto a ser populado
	 * @param entidadesJaPopuladas
	 *            - set que gerencia a criacao de objetos em objetos (para evitar recursao infinita)
	 * @return Object - instância da classe parametrizada
	 * 
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public Object obterInstanciaPopulada(Class entityToPopulate, Set entidadesJaPopuladas) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, FileNotFoundException, SecurityException, NoSuchFieldException
	{
		Object instancia = null;
		EnumTipoInfoAtributo tipoInfo = null;
		int nivelDeRecursao = 0;
		
		/* Verifica se alguma entidade do mesmo tipo ja foi populada para evitar recursao infinita.
		 * Por exemplo, objeto do tipo EmpresaE sera populado apenas uma unica vez durante todas as recursoes. */
		if(entidadesJaPopuladas.contains(entityToPopulate))
		{
			return instancia; 
		}

		/* Percorre todos os atributos (fields) da instância (Entidade, Pk) */
		for (Field field : ReflectionUtil.findAllFields(entityToPopulate))
		{
			if ("serialVersionUID".equals(field.getName())) 
				continue;
			
					/* COLECAO: instancia e popula */
			else if (ReflectionUtil.implementsOf(field.getType(), Collection.class) || ReflectionUtil.instanceOf(field.getType(), Collection.class))
			{
				Collection itemColl = new ArrayList();

				for (int i = 0; i < COLECAO_TAMANHO; i++)
				{
					itemColl.add(obterInstanciaPopulada((Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0], entidadesJaPopuladas));
				}
				
				PropertyUtils.setProperty(instancia, field.getName(), itemColl);
			}
			/* ENUM: gera um valor de acordo com o enum */
			else if (ReflectionUtil.instanceOf(field.getType(), Enum.class))
			{
				Object valor = obterValorAleatorioEnum(field);
				PropertyUtils.setProperty(instancia, field.getName(), valor);
			}
			/* PRIMITIVOS: gera algum valor de acordo com o tipo */
			else
			{
				tipoInfo = getTipoInfo(entityToPopulate, field);
				if (tipoInfo != null)
				{
					Object valor = this.obterConteudoAleatorioParaAtributo(tipoInfo);
					PropertyUtils.setProperty(instancia, field.getName(), valor);
				}
			}
		}

		return instancia;
	}

	/**
	 * Retorna um valor aleatorio para o enum.
	 * 
	 * @param enumField
	 *            - atributo do tipo Enum
	 * @return Object - um dos possíveis valores do Enum
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private Object obterValorAleatorioEnum(Field enumField) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		Class clz = Class.forName(enumField.getType().getCanonicalName());
		Method mth = clz.getDeclaredMethod("values");
		Object[] enums = (Object[]) mth.invoke(clz);

		Random random = new Random();
		int pos = random.nextInt(enums.length);

		return enums[pos];
	}

	/**
	 * Retorna o tipo de informacao que o atributo (field) representa.
	 * 
	 * @param clazz
	 *            - classe mapeada
	 * @param field
	 *            - atributo mapeado da classe
	 * @return EnumTipoInfoAtributo - tipo de informação
	 */
	private EnumTipoInfoAtributo getTipoInfo(Class<?> clazz, Field field)
	{
		String fieldName = field.getName();
		EnumEntidadeMptI[] enumAtributosEntidade = getEnumAtributosEntidade(clazz);
		if (enumAtributosEntidade != null)
		{
			for (EnumEntidadeMptI enumTest : enumAtributosEntidade)
			{
				if (enumTest != null && enumTest.getNomeAtributo().equals(fieldName)) { return enumTest.getTipoAtributoMapeado()[0]; }
			}
		}
		return null;
	}

	/**
	 * Retorna o enum referente a classe mapeada.
	 * 
	 * @param clazz
	 *            - classe mapeada
	 * @return EnumEntidadeMptI[] - enum referente à classe mapeada
	 */
	private EnumEntidadeMptI[] getEnumAtributosEntidade(Class<?> clazz)
	{
		for (EnumMapeamento enumTest : EnumMapeamento.values())
		{
			if (enumTest != null && enumTest.getClassEntidade().getCanonicalName().equals(clazz.getCanonicalName())) { return enumTest.getEnumAtributosEntidade(); }
		}
		return null;
	}
	
	/**
	 * Método para obter o conteúdo do XML de forma aleatoria passando, como parametro, o Enum contendo o mapeamento do Atributo e seu respectivo tipo.
	 * 
	 * @param enumTipoInfo
	 * @return Object
	 */
	private Object obterConteudoAleatorioParaAtributo(EnumTipoInfoAtributo enumTipoInfo)
	{
		List<?> conteudoList = tipoInfoEConteudoMap.get(enumTipoInfo);
		if (conteudoList != null && !conteudoList.isEmpty())
		{
			Collections.shuffle(conteudoList);
			Random random = new Random();
			int pos = random.nextInt(conteudoList.size());
			return conteudoList.get(pos);
		}
		return null;
	}

}
