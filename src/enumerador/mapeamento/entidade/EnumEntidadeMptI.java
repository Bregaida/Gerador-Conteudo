package enumerador.mapeamento.entidade;

import enumerador.EnumTipoInfoAtributo;


/**
 * @author Bregaida e Fradico
 *
 * Interface contendo o nome do atributo e o tipo do atributo Mapeado mapeado é implementada pelo Enum da Entidade mapeada
 */
public interface EnumEntidadeMptI
{
	public String getNomeAtributo();

	public EnumTipoInfoAtributo[] getTipoAtributoMapeado();
	
}
