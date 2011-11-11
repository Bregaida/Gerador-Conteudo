///**
// * 
// */
//package dicionario.entidade.dao;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//
//import dicionario.entidade.modelo.GrupoEntidadeE;
//
///**
// * @author Bregaida e Fradico
// * 
// */
//public class GrupoEntidadeDAOImpl extends DAOBase<GrupoEntidadeE>
//{
//
//	private static final long	serialVersionUID	= 1L;
//	private Logger				logger				= Logger.getLogger(GrupoEntidadeDAOImpl.class);
//	private GrupoEntidadeDAO	entidadeDAO			= new GrupoEntidadeDAO();
//
//	protected GrupoEntidadeDAOImpl()
//	{
//		super(GrupoEntidadeDAOImpl.class, EnumConfigAmbiente.RECURSOS_HUMANOS_ALTA);
//	}
//
//	@Override
//	protected GrupoEntidadeE buildEntity(ResultSetImpl rs) throws DBException, SQLException
//	{
//		return null;
//	}
//
//	public List<GrupoEntidadeE> listarEstados(final GrupoEntidadeE filter) throws DBException, SQLException
//	{
//		final List<GrupoEntidadeE> itemColl = executar(new DAOSearchI<GrupoEntidadeE>()
//		{
//			public List<GrupoEntidadeE> execute(ConnectionImpl conn) throws SQLException, DBException
//			{
//				logger.debug("start");
//				List<GrupoEntidadeE> lista = entidadeDAO.listarEstados(conn);
//
//				logger.debug("done");
//				return lista;
//			}
//		});
//
//		return itemColl;
//	}
//
//	public List<GrupoEntidadeE> listarDepartamentos(final GrupoEntidadeE filter) throws DBException, SQLException
//	{
//		final List<GrupoEntidadeE> itemColl = executar(new DAOSearchI<GrupoEntidadeE>()
//		{
//			public List<GrupoEntidadeE> execute(ConnectionImpl conn) throws SQLException, DBException
//			{
//				logger.debug("start");
//				List<GrupoEntidadeE> lista = entidadeDAO.listarDepartamentos(conn);
//
//				logger.debug("done");
//				return lista;
//			}
//		});
//
//		return itemColl;
//	}
//
//	public List<GrupoEntidadeE> listarEmpresas(final GrupoEntidadeE filter) throws DBException, SQLException
//	{
//		final List<GrupoEntidadeE> itemColl = executar(new DAOSearchI<GrupoEntidadeE>()
//		{
//			public List<GrupoEntidadeE> execute(ConnectionImpl conn) throws SQLException, DBException
//			{
//				logger.debug("start");
//				List<GrupoEntidadeE> lista = entidadeDAO.listarEmpresas(conn);
//
//				logger.debug("done");
//				return lista;
//			}
//		});
//
//		return itemColl;
//	}
//
//	public List<GrupoEntidadeE> listarFiliais(final GrupoEntidadeE filter) throws DBException, SQLException
//	{
//		final List<GrupoEntidadeE> itemColl = executar(new DAOSearchI<GrupoEntidadeE>()
//		{
//			public List<GrupoEntidadeE> execute(ConnectionImpl conn) throws SQLException, DBException
//			{
//				logger.debug("start");
//				List<GrupoEntidadeE> lista = entidadeDAO.listarFiliais(conn);
//
//				logger.debug("done");
//				return lista;
//			}
//		});
//
//		return itemColl;
//	}
//
//	public List<GrupoEntidadeE> listarFilialDepartamento(final GrupoEntidadeE filter) throws DBException, SQLException
//	{
//		final List<GrupoEntidadeE> itemColl = executar(new DAOSearchI<GrupoEntidadeE>()
//		{
//			public List<GrupoEntidadeE> execute(ConnectionImpl conn) throws SQLException, DBException
//			{
//				logger.debug("start");
//				List<GrupoEntidadeE> lista = entidadeDAO.listarFilialDepartamento(conn);
//
//				logger.debug("done");
//				return lista;
//			}
//		});
//
//		return itemColl;
//	}
//
//	public List<GrupoEntidadeE> listarFuncionarios(final GrupoEntidadeE filter) throws DBException, SQLException
//	{
//		final List<GrupoEntidadeE> itemColl = executar(new DAOSearchI<GrupoEntidadeE>()
//		{
//			public List<GrupoEntidadeE> execute(ConnectionImpl conn) throws SQLException, DBException
//			{
//				logger.debug("start");
//				List<GrupoEntidadeE> lista = entidadeDAO.listarFuncionarios(conn);
//
//				logger.debug("done");
//				return lista;
//			}
//		});
//
//		return itemColl;
//	}
//
//	public List<GrupoEntidadeE> listarHistoricosDatasPagamento(final GrupoEntidadeE filter) throws DBException, SQLException
//	{
//		final List<GrupoEntidadeE> itemColl = executar(new DAOSearchI<GrupoEntidadeE>()
//		{
//			public List<GrupoEntidadeE> execute(ConnectionImpl conn) throws SQLException, DBException
//			{
//				logger.debug("start");
//				List<GrupoEntidadeE> lista = entidadeDAO.listarHistoricosDatasPagamento(conn);
//
//				logger.debug("done");
//				return lista;
//			}
//		});
//
//		return itemColl;
//	}
//
//	public List<GrupoEntidadeE> listarMunicipios(final GrupoEntidadeE filter) throws DBException, SQLException
//	{
//		final List<GrupoEntidadeE> itemColl = executar(new DAOSearchI<GrupoEntidadeE>()
//		{
//			public List<GrupoEntidadeE> execute(ConnectionImpl conn) throws SQLException, DBException
//			{
//				logger.debug("start");
//				List<GrupoEntidadeE> lista = entidadeDAO.listarMunicipios(conn);
//
//				logger.debug("done");
//				return lista;
//			}
//		});
//
//		return itemColl;
//	}
//
//	public List<GrupoEntidadeE> listarNotificacoes(final GrupoEntidadeE filter) throws DBException, SQLException
//	{
//		final List<GrupoEntidadeE> itemColl = executar(new DAOSearchI<GrupoEntidadeE>()
//		{
//			public List<GrupoEntidadeE> execute(ConnectionImpl conn) throws SQLException, DBException
//			{
//				logger.debug("start");
//				List<GrupoEntidadeE> lista = entidadeDAO.listarNotificacoes(conn);
//
//				logger.debug("done");
//				return lista;
//			}
//		});
//
//		return itemColl;
//	}
//}
