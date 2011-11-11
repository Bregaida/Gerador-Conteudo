/**
 * 
 */
package exception;

/**
 *  @author Bregaida e Fradico
 * 
 * Classe Pai para excecoes
 */
public class GeradorException extends Exception
{
	private static final long	serialVersionUID	= 1L;
	private String				msg;

	public GeradorException(String msg)
	{
		super(msg);
		this.msg = msg;
	}

	public String getMessage()
	{
		return msg;
	}
}
