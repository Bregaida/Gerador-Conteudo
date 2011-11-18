package br.com.gerador.test;

import entidade.Funcionario;
import entidade.GeradorConteudoConsistente;

public class GeradorConsistenteTeste {
	private GeradorConteudoConsistente gerador;

	public GeradorConsistenteTeste() {
		try {
			gerador = new GeradorConteudoConsistente();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void testarFuncionario() {
		for (int i = 0; i < 10; i++) {
			Funcionario funcionario = (Funcionario) gerador
					.obterEntidadeConsistente(Funcionario.class);

			System.out.println(funcionario.getEmail());
			System.out.println(funcionario.getNome());
			System.out.println(funcionario.getId());
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		GeradorConsistenteTeste gerador = new GeradorConsistenteTeste();

		gerador.testarFuncionario();
	}

}
