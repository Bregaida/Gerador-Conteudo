/**
 * 
 */
package anotacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import enumerador.EnumTipoInfoAtributo;

/**
 * @author Bregaida e Fradico
 *
 * Anotation para o dicinario contendo o Array de Enum para Tipos do Atributo
 * 
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DicionarioAnotacao
{
	EnumTipoInfoAtributo[] tipoInfo();
}
