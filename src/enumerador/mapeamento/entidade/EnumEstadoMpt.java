package enumerador.mapeamento.entidade;

import enumerador.EnumTipoInfoAtributo;


public enum EnumEstadoMpt implements EnumEntidadeMptI
{
	NOME("nome", new EnumTipoInfoAtributo[] {EnumTipoInfoAtributo.NOME_ESTADO}),
	;
	/* FIM DAS DECLARACOES */
	
	private String nomeAtributo;
	private EnumTipoInfoAtributo[] tipoAtributoMapeado;
	
	private EnumEstadoMpt(String nomeAtributo, EnumTipoInfoAtributo[] tipoAtributoMapeado)
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