package enumerador.mapeamento.entidade;

import enumerador.EnumTipoInfoAtributo;


public enum EnumFuncionarioMpt implements EnumEntidadeMptI
{
	NOME("razaoSocial", new EnumTipoInfoAtributo[] {EnumTipoInfoAtributo.NOME_PESSOA}),
	EMAIL("email", new EnumTipoInfoAtributo[] {EnumTipoInfoAtributo.EMAIL}),
	ID("pk", new EnumTipoInfoAtributo[] {EnumTipoInfoAtributo.CODIGO_ID}),
	;
	/* FIM DAS DECLARACOES */
	
	private String nomeAtributo;
	private EnumTipoInfoAtributo[] tipoAtributoMapeado;
	
	private EnumFuncionarioMpt(String nomeAtributo, EnumTipoInfoAtributo[] tipoAtributoMapeado)
	{
		this.nomeAtributo = nomeAtributo;
		this.tipoAtributoMapeado = tipoAtributoMapeado;
	}

	public String getNomeAtributo()
	{
		return nomeAtributo;
	}

	public EnumTipoInfoAtributo[] getTipoAtributoMapeado()
	{
		return tipoAtributoMapeado;
	}

}