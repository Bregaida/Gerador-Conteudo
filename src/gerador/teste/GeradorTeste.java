package gerador.teste;

import entidade.EstadoE;
import gerador.atributo.GeradorConteudo;

import java.util.HashSet;
import java.util.Set;

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
				EstadoE estado;
				estado = (EstadoE) gerador.obterInstanciaPopulada(EstadoE.class, entidades);

				System.out.println("Nome Estado: " + estado.getNome());
				System.out.println("PK Estado: " + estado.getNome());
				System.out.println();
			}

			// for(int i = 0; i < 10; i++)
			// {
			// Set<?> entidades = new HashSet();
			// EmpresaE empresa;
			// empresa = (EmpresaE) gerador.obterInstanciaPopulada(EmpresaE.class, entidades);
			//				
			// System.out.println("Nome Empresa: " + empresa.getRazaoSocial());
			// System.out.println("Descricao: " + empresa.getDescricao());
			// System.out.println("Codigo Empresa: " + empresa.getPk());
			// System.out.println();
			// }

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
