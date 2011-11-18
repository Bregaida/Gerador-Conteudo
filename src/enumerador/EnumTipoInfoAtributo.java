package enumerador;
import java.util.ArrayList;
import java.util.Date;


/**
 *  @author Bregaida e Fradico
 *  
 * Mapeamento dos Tipos de Informacao para serem atribuidos com os atributos
 *
 */
public enum EnumTipoInfoAtributo
{
	/* INICIO DAS DECLARACOES */
	NOME_PESSOA(1, String.class),
	NOME_EMPRESA(2, String.class),
	DESCRICAO(3, String.class),
	DATA(4, Date.class),
	BOOLEANO(5, Boolean.class),
	CPF(6, String.class),
	RG(7, String.class),
	ENUM(8, Enum.class),
	VERSAO(11, Integer.class),
	COLECAO(12, ArrayList.class),
	CODIGO_ID(13, Long.class),
	NOME_SHOPPING(14, String.class),
	TIPO_ATIVIDADE(15, String.class),
	NOME_MUNICIPIO(16, String.class),
	NOME_ESTADO(17, String.class),
	EMAIL(18, String.class),
	;
	/* FIM DAS DECLARACOES */
	
	private EnumTipoInfoAtributo(int codigo, Class type)
	{
		setCodigo(codigo);
		setType(type);
	}
	
	private int codigo;
	private Class type;
	
	public int getCodigo()
	{
		return codigo;
	}
	public void setCodigo(int codigo)
	{
		this.codigo = codigo;
	}
	public Class getType()
	{
		return type;
	}
	public void setType(Class type)
	{
		this.type = type;
	}
}
