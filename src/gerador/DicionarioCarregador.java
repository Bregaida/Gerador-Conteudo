package gerador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import anotacao.DicionarioAnotacao;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import dicionario.atributo.AtributosDicionarioXml;
import dicionario.atributo.ListaAtributosDicionarioXml;
import enumerador.EnumArquivoNomes;
import enumerador.EnumEntidadeConstante;
import enumerador.EnumTipoInfoAtributo;

public class DicionarioCarregador
{
	
	public DicionarioCarregador() throws FileNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		
	}
	
	/**
	 * Método para carregar conteudo para atributos com os dados do XML.
	 * 
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws FileNotFoundException
	 */
	public Map<EnumTipoInfoAtributo, List<?>> carregarConteudoParaAtributos() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, FileNotFoundException
	{
		/* O mapa guarda listas de valores de atributos. Uma lista para cada tipo de informacao (EnumTipoInfoAtributo). */
		Map<EnumTipoInfoAtributo, List<?>>	tipoInfoEConteudoMap				= new HashMap<EnumTipoInfoAtributo, List<?>>();
		
		File fileInput = new File(EnumArquivoNomes.DICIONARIO_ATRIBUTOS.getNome());
		BufferedReader readerXML = new BufferedReader(new FileReader(fileInput));
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("dicionario", ListaAtributosDicionarioXml.class);
		xstream.alias("tipoAtributosDicionario", AtributosDicionarioXml.class);
		ListaAtributosDicionarioXml dicAtributos = (ListaAtributosDicionarioXml) xstream.fromXML(readerXML);

		for (EnumTipoInfoAtributo enumTipoInfo : EnumTipoInfoAtributo.values())
		{
			List listaDeValoresParaCadaTipoInformacao = new ArrayList();
			for (AtributosDicionarioXml atributosEConteudo : dicAtributos.getContent())
			{
				for (Field campoDicionario : atributosEConteudo.getClass().getDeclaredFields())
				{
					DicionarioAnotacao campoDicionarioAnnotation = campoDicionario.getAnnotation(DicionarioAnotacao.class);
					if (enumTipoInfo.equals(campoDicionarioAnnotation.tipoInfo()[0]))
					{
						Object valor = PropertyUtils.getProperty(atributosEConteudo, campoDicionario.getName());
						listaDeValoresParaCadaTipoInformacao.add(valor);
					}
				}
			}
			tipoInfoEConteudoMap.put(enumTipoInfo, listaDeValoresParaCadaTipoInformacao);
		}
		
		return tipoInfoEConteudoMap;
	}

	private Map<Class, List> carregarConteudoParaEntidade(EnumEntidadeConstante enumEntidade, Map<Class, List> entidadesConsistentesEConteudoMap)
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext(enumEntidade.getNomeArqXml());
		List list = new ArrayList(ctx.getBeansOfType(enumEntidade.getEntidadeClass()).values());
		if(list == null)
		{
			list = new ArrayList();
		}
		entidadesConsistentesEConteudoMap.put(enumEntidade.getEntidadeClass(), list);
		
		return entidadesConsistentesEConteudoMap;
	}
	
	/**
	 * Método para carregar o conteúdo para entidades, de forma consistente, do arquivo xml.
	 */
	public Map<Class, List> carregarConteudoParaEntidadesConsistentes()
	{
		Map<Class, List> entidadesConsistentesEConteudoMap = new HashMap<Class, List>();
		
		entidadesConsistentesEConteudoMap = carregarConteudoParaEntidade(EnumEntidadeConstante.ESTADO, entidadesConsistentesEConteudoMap);

		entidadesConsistentesEConteudoMap = carregarConteudoParaEntidade(EnumEntidadeConstante.EMPRESA, entidadesConsistentesEConteudoMap);
		
		return entidadesConsistentesEConteudoMap;
	}
}
