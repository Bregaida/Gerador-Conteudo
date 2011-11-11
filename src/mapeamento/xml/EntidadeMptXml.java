package mapeamento.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe representante da tag 'entidade' do arquivo XML 'mapeamento.xml'.
 * 
 * @author Fradico e Bregaida 
 */
public class EntidadeMptXml
{
	private String nome = "";
	private String pk = "";
	
	private String entidadePacote = "";
	private String entidadeMptPacote = "";
	private String pkPacote = "";
	private String pkMptPacote = "";

	private List<AtributoMptXml> atributos = new ArrayList<AtributoMptXml>();
	
	public String getNome()
	{
		return nome;
	}
	
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getPk()
	{
		return pk;
	}

	public void setPk(String pk)
	{
		this.pk = pk;
	}

	public String getEntidadePacote()
	{
		return entidadePacote;
	}

	public void setEntidadePacote(String entidadePacote)
	{
		this.entidadePacote = entidadePacote;
	}

	public String getEntidadeMptPacote()
	{
		return entidadeMptPacote;
	}

	public void setEntidadeMptPacote(String entidadeMptPacote)
	{
		this.entidadeMptPacote = entidadeMptPacote;
	}

	public String getPkPacote()
	{
		return pkPacote;
	}

	public void setPkPacote(String pkPacote)
	{
		this.pkPacote = pkPacote;
	}

	public String getPkMptPacote()
	{
		return pkMptPacote;
	}

	public void setPkMptPacote(String pkMptPacote)
	{
		this.pkMptPacote = pkMptPacote;
	}

	public void add(AtributoMptXml atributo)
	{
		this.atributos.add(atributo);
	}
	
	public List<AtributoMptXml> getContent()
	{
		return this.atributos;
	}
}
