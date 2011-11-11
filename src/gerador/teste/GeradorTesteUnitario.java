/**
 * 
 */
package gerador.teste;

import static org.junit.Assert.assertNotNull;
import entidade.EmpresaE;
import entidade.EstadoE;
import gerador.GeradorMapeamento;
import gerador.atributo.GeradorConteudo;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class GeradorTesteUnitario
{
	
	GeradorConteudo gerador = null;
	GeradorMapeamento mapeamento = null;
	Set<?> entidades = new HashSet();
	
	@Test
	public void deveriaGerarDadosEmpresa() throws Exception {
		mapeamento = new GeradorMapeamento();
		mapeamento.gerar();
		gerador = new GeradorConteudo();
		assertNotNull((EmpresaE) gerador.obterInstanciaPopulada(EmpresaE.class, entidades));
	}

	@Test
	public void deveriaGerarDadosEstado() throws Exception {
		gerador = new GeradorConteudo();
		assertNotNull((EstadoE) gerador.obterInstanciaPopulada(EstadoE.class, entidades));
	}

}
