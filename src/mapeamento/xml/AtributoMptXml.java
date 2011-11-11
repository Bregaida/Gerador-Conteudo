package mapeamento.xml;

/**
 * Classe representante da tag 'atributo' do arquivo XML 'mapeamento.xml'.
 * 
 * @author Fradico e Bregaida 
 */
public class AtributoMptXml
{
	private String nome = "";
	private String alias = "";
	private String informacao = "";
	private String tipo = "";
	
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public String getInformacao()
	{
		return informacao;
	}
	public void setInformacao(String informacao)
	{
		this.informacao = informacao;
	}
	public String getTipo()
	{
		return tipo;
	}
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}
	public String getAlias()
	{
		return alias;
	}
	public void setAlias(String alias)
	{
		this.alias = alias;
	}
	
}
