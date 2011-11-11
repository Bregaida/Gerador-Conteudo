package mapeamento.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe representante do arquivo XML 'mapeamento.xml'.
 * 
 * @author Fradico e Bregaida 
 */
public class MapeamentoXml
{
	private List<EntidadeMptXml> entidades = new ArrayList<EntidadeMptXml>();
	
	public void add(EntidadeMptXml entidade)
	{
		this.entidades.add(entidade);
	}
	
	public List<EntidadeMptXml> getContent()
	{
		return this.entidades;
	}
}
