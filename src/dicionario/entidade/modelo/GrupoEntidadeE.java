/**
 * 
 */
package dicionario.entidade.modelo;

import entidade.EmpresaE;
import entidade.EstadoE;

/**
 * @author Bregaida e Fradico
 * 
 * Esta classe contém as Entidades que serão geradas no XML do Spring para serem populadas
 * quando uma nova entidade entrar no sistema ela deverá ser colocada aqui.
 */
public class GrupoEntidadeE {

	private EmpresaE empresa;
	private EstadoE estado;
	
	public EmpresaE getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaE empresa) {
		this.empresa = empresa;
	}

	public EstadoE getEstado() {
		return estado;
	}

	public void setEstado(EstadoE estado) {
		this.estado = estado;
	}

}
