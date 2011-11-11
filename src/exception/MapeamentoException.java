/**
 * 
 */
package exception;

/**
 * @author Bregaida e Fradico
 * 
 * Classe de Excecao para erro de mapeamento
 */
public class MapeamentoException extends GeradorException
{

	private static final long	serialVersionUID	= 1L;

	public MapeamentoException()
	{
		super("Erro no mapeamento, por favor validar se o mesmo está correto no EnumTest chamado.");
	}

}
