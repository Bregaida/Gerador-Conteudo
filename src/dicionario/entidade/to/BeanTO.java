/**
 * 
 */
package dicionario.entidade.to;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Bregaida e Fradico
 * 
 *         TO para representar os parametros que serao preenchidos no XML do Spring
 * 
 */
public class BeanTO
{
	private String				id;
	private String				clazz;
	private List<PropertyTO>	properties = new ArrayList<PropertyTO>();

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getClazz()
	{
		return clazz;
	}

	public void setClazz(String clazz)
	{
		this.clazz = clazz;
	}
	
	public void add(PropertyTO property)
	{
		this.properties.add(property);
	}
	
	public List<PropertyTO> getContent()
	{
		return this.properties;
	}
}
