package br.com.gerador.test;
//package gerador.teste;
//
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//
//import java.lang.reflect.Field;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Test;
//
//import dicionario.entidade.dao.GrupoEntidadeDAO;
//import dicionario.entidade.modelo.GrupoEntidadeE;
//
//import br.com.meuProjeto.core.dao.conn.ConnectionImpl;
//import br.com.meuProjeto.core.dao.conn.DBConnection;
//import br.com.meuProjeto.core.entity.enumerator.EnumConfigAmbiente;
//
///**
// * @author m01457110
// * 
// */
//public class DAOTesteUnitario
//{
//
//	private GrupoEntidadeDAO	grupoEntidadeDAO	= new GrupoEntidadeDAO();
//
//	@Test
//	public void deveriaListarTodosEstados() throws Exception
//	{
//		ConnectionImpl conn = DBConnection.getConnection(EnumConfigAmbiente.CONTABILIDADE);
//
//		try
//		{
//			Collection<GrupoEntidadeE> itemColl = grupoEntidadeDAO.listarEstados(conn);
//			assertTrue(itemColl.size() <= 28);
//		}
//		catch (Exception e)
//		{
//			fail(e.getMessage());
//		}
//	}
//	
//	@Test
//	public void deveriaListarNoMaximoCinquentaEmpresas() throws Exception
//	{
//		ConnectionImpl conn = DBConnection.getConnection(EnumConfigAmbiente.CONTABILIDADE);
//
//		try
//		{
//			Map map = new HashMap<String, String>();
//			Collection<GrupoEntidadeE> itemColl = grupoEntidadeDAO.listarEmpresas(conn);
//			
//			for (GrupoEntidadeE grupoEntidadeE : itemColl)
//			{
//				for (Field field : grupoEntidadeE.getEmpresa().getClass().getDeclaredFields())
//				{
//					map.put(field.getName(), field.get(grupoEntidadeE.getEmpresa()));
//				}
//			}
//			assertTrue(itemColl.size() <= 50);
//		}
//		catch (Exception e)
//		{
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void deveriaListarNoMaximoCinquentaDepartamentos() throws Exception
//	{
//		ConnectionImpl conn = DBConnection.getConnection(EnumConfigAmbiente.CONTABILIDADE);
//
//		try
//		{
//			Collection<GrupoEntidadeE> itemColl = grupoEntidadeDAO.listarDepartamentos(conn);
//			assertTrue(itemColl.size() <= 50);
//		}
//		catch (Exception e)
//		{
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void deveriaListarNoMaximoCinquentaFiliais() throws Exception
//	{
//		ConnectionImpl conn = DBConnection.getConnection(EnumConfigAmbiente.CONTABILIDADE);
//
//		try
//		{
//			Collection<GrupoEntidadeE> itemColl = grupoEntidadeDAO.listarFiliais(conn);
//			assertTrue(itemColl.size() <= 50);
//		}
//		catch (Exception e)
//		{
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void deveriaListarNoMaximoCinquentaFilialDepartamento() throws Exception
//	{
//		ConnectionImpl conn = DBConnection.getConnection(EnumConfigAmbiente.CONTABILIDADE);
//
//		try
//		{
//			Collection<GrupoEntidadeE> itemColl = grupoEntidadeDAO.listarFilialDepartamento(conn);
//			assertTrue(itemColl.size() <= 50);
//		}
//		catch (Exception e)
//		{
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void deveriaListarNoMaximoCinquentaFuncionarios() throws Exception
//	{
//		ConnectionImpl conn = DBConnection.getConnection(EnumConfigAmbiente.CONTABILIDADE);
//
//		try
//		{
//			Collection<GrupoEntidadeE> itemColl = grupoEntidadeDAO.listarFuncionarios(conn);
//			assertTrue(itemColl.size() <= 50);
//		}
//		catch (Exception e)
//		{
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void deveriaListarNoMaximoCinquentaHistoricosDatasPagamento() throws Exception
//	{
//		ConnectionImpl conn = DBConnection.getConnection(EnumConfigAmbiente.CONTABILIDADE);
//
//		try
//		{
//			Collection<GrupoEntidadeE> itemColl = grupoEntidadeDAO.listarHistoricosDatasPagamento(conn);
//			assertTrue(itemColl.size() <= 50);
//		}
//		catch (Exception e)
//		{
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void deveriaListarNoMaximoCinquentaMunicipios() throws Exception
//	{
//		ConnectionImpl conn = DBConnection.getConnection(EnumConfigAmbiente.CONTABILIDADE);
//
//		try
//		{
//			Collection<GrupoEntidadeE> itemColl = grupoEntidadeDAO.listarMunicipios(conn);
//			assertTrue(itemColl.size() <= 50);
//		}
//		catch (Exception e)
//		{
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void deveriaListarNoMaximoCinquentaNotificacoes() throws Exception
//	{
//		ConnectionImpl conn = DBConnection.getConnection(EnumConfigAmbiente.CONTABILIDADE);
//
//		try
//		{
//			Collection<GrupoEntidadeE> itemColl = grupoEntidadeDAO.listarNotificacoes(conn);
//			assertTrue(itemColl.size() <= 50);
//		}
//		catch (Exception e)
//		{
//			fail(e.getMessage());
//		}
//	}
//}
