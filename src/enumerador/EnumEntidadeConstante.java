package enumerador;

import entidade.EmpresaE;
import entidade.EstadoE;

public enum EnumEntidadeConstante
{
	//se usar o caminho/path, usar classloader. Senao, usar apenas o nome do arquivo.
	EMPRESA(EmpresaE.class, "empresas.xml"),
	ESTADO(EstadoE.class, "estados.xml");
	
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
