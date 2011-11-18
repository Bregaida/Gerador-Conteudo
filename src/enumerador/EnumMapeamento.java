package enumerador;

import java.lang.reflect.Field;

import util.ReflectionUtil;
import entidade.Funcionario;
import enumerador.mapeamento.entidade.EnumEntidadeMptI;
import enumerador.mapeamento.entidade.EnumFuncionarioMpt;
import exception.MapeamentoException;


/**
 * @author Bregaida e Fradico
 *
 * Enum que contém o mapeamento dos Enum das entidades criadas para seus atributos serem populados
 */
public enum EnumMapeamento
{

	/*Entidades do Projeto Corporativo*/
	FUNCIONARIO(Funcionario.class, EnumFuncionarioMpt.values()),
	;
	/* FIM ENTIDADE */
	
	private Class<?> classEntidade;
	
	private EnumEntidadeMptI[] enumAtributosEntidade;
	
	private EnumMapeamento(Class<?> classEntidade, EnumEntidadeMptI[] enumAtributosEntidade)
	{
		this.classEntidade = classEntidade;
		this.enumAtributosEntidade = enumAtributosEntidade;
	}

	public Class<?> getClassEntidade()
	{
		return classEntidade;
	}

	public EnumEntidadeMptI[] getEnumAtributosEntidade()
	{
		return enumAtributosEntidade;
	}
	
	/**
	 * Método Validate faz a validacao dos campos com o nome do atributo para verificar se o mapeamento esta correto
	 * 
	 * @throws MapeamentoException
	 */
	public static void validate() throws MapeamentoException
	{
		for (EnumMapeamento enumTest : EnumMapeamento.values())
		{
			for (EnumEntidadeMptI enumAtributoTest : enumTest.getEnumAtributosEntidade())
			{
				boolean found = false;
				for (Field field : ReflectionUtil.findAllFields(enumTest.getClassEntidade()))
				{
					if (field.getName().equals(enumAtributoTest.getNomeAtributo()))
					{
						found = true;
						break;
					}
					
				}
				if (!found) {
					throw new MapeamentoException();
				}
			}
		}
	}
}
