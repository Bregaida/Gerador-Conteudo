package br.com.gerador.test;

import java.util.HashSet;
import java.util.Set;

import atributo.GeradorConteudo;
import entidade.Funcionario;

public class GeradorTeste
{
	public static void main(String[] args)
	{
		try
		{
			GeradorConteudo gerador = new GeradorConteudo();
			for (int i = 0; i < 10; i++)
			{
				Set<?> entidades = new HashSet();
				Funcionario funcionario;
				funcionario = (Funcionario) gerador.obterInstanciaPopulada(Funcionario.class, entidades);

				System.out.println("ID Funcionario: " + funcionario.getId());
				System.out.println("Nome Funcionario: " + funcionario.getNome());
				System.out.println("PK Funcionario: " + funcionario.getId());
				System.out.println();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
