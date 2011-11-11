/**
 * 
 */
package dicionario.entidade.to;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bregaida e Fradico
 *  
 *         TO para representar os beans que serão preenchidos no XML do Spring
 * 
 */
public class PropertyTO
{
	private String	name;
	private String	value;
	private String	ref;
	private List<RefTO> refList;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getRef()
	{
		return ref;
	}

	public void setRef(String ref)
	{
		this.ref = ref;
	}
	
	/*
	public void destruirRefList()
	{
		refList = null;
	}
	*/

	public void add(RefTO ref)
	{
		if(refList == null)
		{
			refList = new ArrayList<RefTO>();
		}
		this.refList.add(ref);
	}
	
	public List<RefTO> getContent()
	{
		return this.refList;
	}
	
}
