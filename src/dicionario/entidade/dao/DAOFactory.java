///**
// * 
// */
//package dicionario.entidade.dao;
//
//
//import java.io.Serializable;
//
//import org.apache.log4j.Logger;
//
//import br.com.meuProjeto.core.dao.base.DAOBase;
//import br.com.meuProjeto.core.dao.exception.DBException;
//import br.com.meuProjeto.core.entity.enumerator.EnumExceptionMap;
//
//public class DAOFactory implements Serializable
//{
//	/**
//	 * 
//	 */
//	private static final long	serialVersionUID	= 1L;
//	
//	private static Logger logger = Logger.getLogger(DAOFactory.class);
//	
//	public static <DAO extends DAOBase> DAO getInstance(Class<DAO> clazz) throws DBException
//	{
//		return getInstance(clazz, true);
//	}
//	
//	public static <DAO extends DAOBase> DAO getInstance(Class<DAO> clazz, boolean lazyLoadActive) throws DBException
//	{
//		logger.debug("start");
//		DAO daoBase = null;
//		
//		try
//		{
//			if (clazz.getCanonicalName().contains("pojo"))
//			{
//				daoBase = DAOPojoFactory.getInstance(clazz);
//			}
//			else
//			{
//				daoBase = clazz.newInstance();
//			}
//			
//			/* atribuí o lazyLoadActive no DAO, dependendo da solicitação realizada na factory */
//			daoBase.setLazyActive(lazyLoadActive);
//		}
//		catch (InstantiationException e) 
//		{
//			logger.error("error: " + e.getMessage(), e);
//			throw new DBException(EnumExceptionMap.DAO_FACTORY_NEW_INSTANCE.getKey());
//		}
//		catch (IllegalAccessException e)
//		{
//			logger.error("error: " + e.getMessage(), e);
//			throw new DBException(EnumExceptionMap.DAO_FACTORY_NEW_INSTANCE.getKey());
//		}
//		catch (Exception e) 
//		{
//			logger.error("error: " + e.getMessage(), e);
//			throw new DBException(EnumExceptionMap.DAO_FACTORY_NEW_INSTANCE.getKey());
//		}
//		
//		logger.debug("done");
//		return daoBase;
//	}
//}
