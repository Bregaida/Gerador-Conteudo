package dicionario.atributo;
import java.util.ArrayList;
import java.util.List;



/**
 *  @author Bregaida e Fradico
 *  
 * Classe contendo a lista de atributos do dicionário, é chamada pelo DicionarioDTO
 * 
 */
public class ListaAtributosDicionarioXml
{
	private List<AtributosDicionarioXml> atributos = new ArrayList<AtributosDicionarioXml>();

	public void add(AtributosDicionarioXml atributos)
	{
		this.atributos.add(atributos);
	}
	
	public List<AtributosDicionarioXml> getContent()
	{
		return this.atributos;
	}
	
}
