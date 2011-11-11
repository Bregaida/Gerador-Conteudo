package dicionario.entidade.to;

import java.util.ArrayList;
import java.util.List;


public class RootTO
{
	List<BeanTO> beanList = new ArrayList<BeanTO>();
	
	public void add(BeanTO bean)
	{
		this.beanList.add(bean);
	}
	
	public List<BeanTO> getContent()
	{
		return this.beanList;
	}
}
