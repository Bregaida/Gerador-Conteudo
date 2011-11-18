package enumerador;

import entidade.Funcionario;

public enum EnumEntidadeConstante
{
	//se usar o caminho/path, usar classloader. Senao, usar apenas o nome do arquivo.
	FUNCIONARIO(Funcionario.class, "empresas.xml");
	
	private Class klass;
	private String nomeArqXml;
	private String nomeArqXmlCompleto;
	private static final String path = "\\dicionario-xml\\";
	private static final String caminhoCompleto="..\\dicionario-xml\\";
	
	private EnumEntidadeConstante(Class klass, String nomeArqXml)
	{
		this.klass = klass;
		this.nomeArqXml = path+nomeArqXml;
		this.nomeArqXmlCompleto = caminhoCompleto;
	}
	
	public Class getEntidadeClass()
	{
		return klass;
	}
	
	public String getNomeArqXml()
	{
		return nomeArqXml;
	}

	public String getNomeArqXmlCompleto()
	{
		return nomeArqXmlCompleto;
	}
	
}
