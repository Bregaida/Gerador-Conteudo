package dicionario.atributo;
import java.util.Date;

import anotacao.DicionarioAnotacao;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import enumerador.EnumTipoInfoAtributo;

/**
 *  @author Bregaida e Fradico
 *  
 * Classe contendo todos os tipos de informação para ser utilizado pelo mapeamento.
 * 
 */

public class AtributosDicionarioXml
{
	@XStreamAsAttribute
	@DicionarioAnotacao(tipoInfo=EnumTipoInfoAtributo.NOME_PESSOA)
	private String	nomePessoa;
	
	@DicionarioAnotacao(tipoInfo=EnumTipoInfoAtributo.NOME_EMPRESA)
	private String	nomeEmpresa;
	
	@DicionarioAnotacao(tipoInfo=EnumTipoInfoAtributo.DESCRICAO)
	private String	descricao;
	
	@DicionarioAnotacao(tipoInfo=EnumTipoInfoAtributo.DATA)
	private Date	data;
	
	@DicionarioAnotacao(tipoInfo=EnumTipoInfoAtributo.BOOLEANO)
	private Boolean	booleano;
	
	@DicionarioAnotacao(tipoInfo=EnumTipoInfoAtributo.CPF)
	private String	cpf;
	
	@DicionarioAnotacao(tipoInfo=EnumTipoInfoAtributo.RG)
	private String	rg;
	
	@DicionarioAnotacao(tipoInfo=EnumTipoInfoAtributo.VERSAO)
	private Integer	versao;

	@DicionarioAnotacao(tipoInfo=EnumTipoInfoAtributo.CODIGO_ID)
	private Long	codigo;

	@DicionarioAnotacao(tipoInfo=EnumTipoInfoAtributo.NOME_SHOPPING)
	private String	nomeShopping;
	
	@DicionarioAnotacao(tipoInfo=EnumTipoInfoAtributo.TIPO_ATIVIDADE)
	private String	tipoAtividade;

	@DicionarioAnotacao(tipoInfo=EnumTipoInfoAtributo.NOME_MUNICIPIO)
	private String	nomeMunicipio;
	@DicionarioAnotacao(tipoInfo=EnumTipoInfoAtributo.NOME_ESTADO) 
	private String nomeEstado;
	/* FIM ATRIBUTOS */
	
	
	public String getNomeMunicipio()
	{
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio)
	{
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getTipoAtividade()
	{
		return tipoAtividade;
	}

	public void setTipoAtividade(String tipoAtividade)
	{
		this.tipoAtividade = tipoAtividade;
	}

	public String getNomeShopping()
	{
		return nomeShopping;
	}

	public void setNomeShopping(String nomeShopping)
	{
		this.nomeShopping = nomeShopping;
	}

	public Long getCodigo()
	{
		return codigo;
	}

	public void setCodigo(Long codigo)
	{
		this.codigo = codigo;
	}

	public Integer getVersao()
	{
		return versao;
	}

	public void setVersao(Integer versao)
	{
		this.versao = versao;
	}

	public String getNomePessoa()
	{
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa)
	{
		this.nomePessoa = nomePessoa;
	}

	public String getNomeEmpresa()
	{
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa)
	{
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public Date getData()
	{
		return data;
	}

	public void setData(Date data)
	{
		this.data = data;
	}

	public Boolean getBooleano()
	{
		return booleano;
	}

	public void setBooleano(Boolean booleano)
	{
		this.booleano = booleano;
	}

	public String getCpf()
	{
		return cpf;
	}

	public void setCpf(String cpf)
	{
		this.cpf = cpf;
	}

	public String getRg()
	{
		return rg;
	}

	public void setRg(String rg)
	{
		this.rg = rg;
	}
	
	public String getNomeEstado(){
		return  nomeEstado;
	}
	public void setNomeEstado(String nomeEstado){
		this.nomeEstado = nomeEstado;
	}
	
	/* FIM GETTERS AND SETTERS */

}
