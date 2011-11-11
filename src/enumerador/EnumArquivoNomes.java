package enumerador;

public enum EnumArquivoNomes
{
	
	CABECALHO_SPRING_XML("..\\Gerador-Teste\\src\\cabecalhoSpringXml"),
	ENUM_TIPO_INFO_ATRIBUTO	( "..\\Gerador-Teste\\src\\enumerador\\EnumTipoInfoAtributo.java"),
	ENUM_MAPEAMENTO			( "..\\Gerador-Teste\\src\\enumerador\\EnumMapeamento.java"),
	ENUM_ENTIDADE_MPT ("..\\Gerador-Teste\\src\\enumerador\\mapeamento\\entidade\\"),
	ENUM_ENTIDADE_MPT_TEMPLATE("..\\Gerador-Teste\\src\\EnumEntidadeMptTemplate"),
	ATRIBUTOS_DICIONARIO	("..\\Gerador-Teste\\src\\dicionario\\atributo\\AtributosDicionarioXML.java"),
	MAPEAMENTO_XML("..\\Gerador-Teste\\src\\mapeamento.xml"),
	//TODO: nao conseguimos fazer o classloader pegar esse diretorio
	DICIONARIO_ATRIBUTOS("..\\Gerador-Teste\\src\\dicionarioAtributos.xml");
	
	private String nome;
	
	private EnumArquivoNomes(String nome)
	{
		this.nome = nome;
	}
	
	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
}
