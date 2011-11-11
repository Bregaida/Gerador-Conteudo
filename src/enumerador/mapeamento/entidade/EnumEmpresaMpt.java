package enumerador.mapeamento.entidade;

import enumerador.EnumTipoInfoAtributo;


public enum EnumEmpresaMpt implements EnumEntidadeMptI
{
	RAZAO_SOCIAL("razaoSocial", new EnumTipoInfoAtributo[] {EnumTipoInfoAtributo.NOME_EMPRESA}),
	DESCRICAO("descricao", new EnumTipoInfoAtributo[] {EnumTipoInfoAtributo.DESCRICAO}),
	DATA_PROCESSAMENTO_PAGAMENTO("dataProcessamentoPagamento", new EnumTipoInfoAtributo[] {EnumTipoInfoAtributo.DATA}),
	DATA_PROCESSAMENTO_ADIANTAMENTO("dataProcessamentoAdiantamento", new EnumTipoInfoAtributo[] {EnumTipoInfoAtributo.DATA}),
	STATUS("status", new EnumTipoInfoAtributo[] {EnumTipoInfoAtributo.BOOLEANO}),	
	PK("pk", new EnumTipoInfoAtributo[] {EnumTipoInfoAtributo.CODIGO_ID}),
	;
	/* FIM DAS DECLARACOES */
	
	private String nomeAtributo;
	private EnumTipoInfoAtributo[] tipoAtributoMapeado;
	
	private EnumEmpresaMpt(String nomeAtributo, EnumTipoInfoAtributo[] tipoAtributoMapeado)
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