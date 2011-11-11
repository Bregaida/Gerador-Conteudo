/**
 * 
 */
package entidade;

/**
 * @author bregaida
 * 
 *         Classe de teste para representar uma Entidade Empresa
 */
public class EmpresaE
{
	private Long	pk;
	private String	razaoSocial;
	private String	descricao;

	public Long getPk()
	{
		return pk;
	}

	public void setPk(Long pk)
	{
		this.pk = pk;
	}

	public String getRazaoSocial()
	{
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial)
	{
		this.razaoSocial = razaoSocial;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

}
