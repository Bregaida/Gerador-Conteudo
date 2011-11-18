/**
 * 
 */
package dicionario.entidade.modelo;

import entidade.Funcionario;

/**
 * @author Bregaida e Fradico
 * 
 *         Esta classe contém as Entidades que serão geradas no XML do Spring
 *         para serem populadas quando uma nova entidade entrar no sistema ela
 *         deverá ser colocada aqui.
 */
public class GrupoEntidadeE {

	private Funcionario empresa;

	public Funcionario getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Funcionario empresa) {
		this.empresa = empresa;
	}

}
