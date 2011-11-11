package gerador.teste;

import entidade.EmpresaE;
import entidade.EstadoE;
import gerador.entidade.GeradorConteudoConsistente;

public class GeradorConsistenteTeste
{
	private GeradorConteudoConsistente gerador;
	
	public GeradorConsistenteTeste()
	{
		try
		{
			gerador = new GeradorConteudoConsistente();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void testarEmpresa()
	
	{
		for (int i = 0; i < 10; i++)
		{
			EmpresaE empresa = (EmpresaE) gerador.obterEntidadeConsistente(EmpresaE.class);
			
			System.out.println(empresa.getRazaoSocial());
			System.out.println(empresa.getDescricao());
			System.out.println(empresa.getPk());
			System.out.println("");
		}
	}
	
	private void testarEstado()
	{
		for (int i = 0; i < 27; i++)
		{
			EstadoE item = (EstadoE) gerador.obterEntidadeConsistente(EstadoE.class);
			
			System.out.println(item.getNome());
			
			System.out.println(item.getPk());
			System.out.println("");
		}
	}
	
	public static void main(String[] args)
	{
		GeradorConsistenteTeste gerador = new GeradorConsistenteTeste();
		
		gerador.testarEstado();
		gerador.testarEmpresa();
	}

}
