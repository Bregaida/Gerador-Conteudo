package enumerador;

public enum EnumArquivoNomes
{
	
	CABECALHO_SPRING_XML("..\\Gerador-Conteudo\\src\\cabecalhoSpringXml"),
	ENUM_TIPO_INFO_ATRIBUTO	( "..\\Gerador-Conteudo\\src\\enumerador\\EnumTipoInfoAtributo.java"),
	ENUM_MAPEAMENTO			( "..\\Gerador-Conteudo\\src\\enumerador\\EnumMapeamento.java"),
	ENUM_ENTIDADE_MPT ("..\\Gerador-Conteudo\\src\\enumerador\\mapeamento\\entidade\\"),
	ENUM_ENTIDADE_MPT_TEMPLATE("..\\Gerador-Conteudo\\src\\EnumEntidadeMptTemplate"),
	ATRIBUTOS_DICIONARIO	("..\\Gerador-Conteudo\\src\\dicionario\\atributo\\AtributosDicionarioXML.java"),
	MAPEAMENTO_XML("..\\Gerador-Conteudo\\src\\mapeamento.xml"),
	//TODO: nao conseguimos fazer o classloader pegar esse diretorio
	DICIONARIO_ATRIBUTOS("..\\Gerador-Conteudo\\src\\dicionarioAtributos.xml");
	
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
