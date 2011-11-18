/**
 * 
 */
package br.com.gerador.test;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import atributo.GeradorConteudo;
import atributo.GeradorMapeamento;
import entidade.Funcionario;

public class GeradorTesteUnitario
{
	
	GeradorConteudo gerador = null;
	GeradorMapeamento mapeamento = null;
	Set<?> entidades = new HashSet();
	
	@Test
	public void deveriaGerarDadosFuncionario() throws Exception {
		mapeamento = new GeradorMapeamento();
		mapeamento.gerar();
		gerador = new GeradorConteudo();
		assertNotNull((Funcionario) gerador.obterInstanciaPopulada(Funcionario.class, entidades));
	}

}
