//package dicionario.entidade.dao;
//
///**
// * 
// */
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//
//import dicionario.entidade.modelo.GrupoEntidadeE;
//import entidade.EmpresaE;
//import entidade.EstadoE;
//
///**
// * @author Bregaida e Fradico
// * 
// */
//public class GrupoEntidadeDAO
//{
//
//	private Logger	logger	= Logger.getLogger(GrupoEntidadeDAO.class);
//
//	public List<GrupoEntidadeE> listarEntidades(Class klass, ConnectionImpl conn) throws DBException, SQLException
//	{
//		if (EmpresaE.class.equals(klass)) return listarEmpresas(conn);
//
//		else if (EstadoE.class.equals(klass)) return listarEstados(conn);
//
//		return null;
//	}
//
//	/**
//	 * Listar Estados
//	 */
//	public List<GrupoEntidadeE> listarEstados(ConnectionImpl conn) throws DBException, SQLException
//	{
//		logger.debug("start");
//
//		StringBuilder sql = new StringBuilder();
//		sql.append(" SELECT TB1.CD_EST_SIG, TB1.NM_EST ");
//		sql.append(" FROM EST TB1 ");
//		sql.append(" ORDER BY TB1.NM_EST ");
//
//		PrepareStatementImpl pstmt = conn.prepareStatement(sql.toString());
//		ResultSetImpl rs = pstmt.executeQuery();
//		List<GrupoEntidadeE> lista = new ArrayList<GrupoEntidadeE>();
//		GrupoEntidadeE entidade = null;
//		while (rs.next())
//		{
//
//			entidade = new GrupoEntidadeE();
//			/*
//			 * Estados
//			 */
//			EstadoE estado = EntityFactory.getInstance().buildInstance(EstadoE.class, false);
//			estado.setPk(rs.getString("CD_EST_SIG"));
//			estado.setNome(rs.getString("NM_EST"));
//
//			// Popula a Entidade Genérica
//			entidade.setEstado(estado);
//
//			// Popula a lista
//			lista.add(entidade);
//		}
//		rs.close();
//		pstmt.close();
//		logger.debug("done");
//		return lista;
//	}
//
//	/**
//	 * Lista as primeiras 50 Filiais
//	 */
//	public List<GrupoEntidadeE> listarFiliais(ConnectionImpl conn) throws DBException, SQLException
//	{
//		logger.debug("start");
//
//		StringBuilder sql = new StringBuilder();
//		sql.append(" SELECT  TB1.CD_EMPGCB, TB1.CD_FIL, TB1.CD_VRSFIL, TB1.DT_FIL_ING, TB1.NM_FIL_APE, TB1.CD_MUN, ");
//		sql.append(" 		 TB1.CD_EST_SIG, TB1.CD_GERRGA, TB1.ST_FIL_SHO, TB1.DS_FIL_SHO, ");
//		sql.append(" 		 TB2.CD_EMPGCB, TB2.NM_EMPGCB_RZS, TB2.NM_EMPGCB_GUR, ");
//		sql.append(" 	   	 TB2.DT_EMPGCB_PRC_PGT, TB2.DT_EMPGCB_PRC_ADT, TB2.ST_EMPGCB_ATO, ");
//		sql.append(" 	   	 TB3.NM_MUN, TB4.NM_EST ");
//		sql.append("  FROM    FIL TB1 ");
//		sql.append("  JOIN    EMP_GCB TB2 ");
//		sql.append("    ON    TB1.CD_EMPGCB = TB2.CD_EMPGCB ");
//		sql.append("  JOIN    MUN TB3 ");
//		sql.append("    ON 	 TB1.CD_MUN = TB3.CD_MUN ");
//		sql.append("  JOIN    EST TB4 ");
//		sql.append("    ON    TB1.CD_EST_SIG = TB4.CD_EST_SIG ");
//		sql.append(" FETCH FIRST 500 ROWS ONLY ");
//
//		PrepareStatementImpl pstmt = conn.prepareStatement(sql.toString());
//		ResultSetImpl rs = pstmt.executeQuery();
//		List<GrupoEntidadeE> lista = new ArrayList<GrupoEntidadeE>();
//		GrupoEntidadeE entidade = null;
//		while (rs.next())
//		{
//
//			entidade = new GrupoEntidadeE();
//			FilialE filial = EntityFactory.getInstance().buildInstance(FilialE.class, false);
//
//			/*
//			 * Empresa
//			 */
//			EmpresaE empresa = EntityFactory.getInstance().buildInstance(EmpresaE.class, false);
//			empresa.setPk(rs.getLong("CD_EMPGCB"));
//			empresa.setRazaoSocial(rs.getString("NM_EMPGCB_RZS"));
//			empresa.setDescricao(rs.getString("NM_EMPGCB_GUR"));
//			empresa.setDataProcessamentoPagamento(rs.getDate("DT_EMPGCB_PRC_PGT"));
//			empresa.setDataProcessamentoAdiantamento(rs.getDate("DT_EMPGCB_PRC_ADT"));
//			empresa.setStatus(rs.getBooleanSN("ST_EMPGCB_ATO"));
//
//			/*
//			 * Estado
//			 */
//			EstadoE estado = EntityFactory.getInstance().buildInstance(EstadoE.class, false);
//			estado.setPk(rs.getString("CD_EST_SIG"));
//			estado.setNome(rs.getString("NM_EST"));
//
//			/*
//			 * Município
//			 */
//			MunicipioE municipio = EntityFactory.getInstance().buildInstance(MunicipioE.class, false);
//			municipio.setPk(rs.getLong("CD_MUN"));
//			municipio.setNome(rs.getString("NM_MUN"));
//			municipio.setEstado(estado);
//
//			/*
//			 * Filial
//			 */
//			FilialPK itemFilialPk = EntityFactory.getInstance().buildInstancePk(FilialPK.class, false);
//			itemFilialPk.setEmpresa(empresa);
//			itemFilialPk.setCodigo(rs.getLong("CD_FIL"));
//			filial.setPk(itemFilialPk);
//			filial.setVersao(rs.getInt("CD_VRSFIL"));
//			filial.setDescricao(rs.getString("NM_FIL_APE"));
//			filial.setDataInauguracao(rs.getDate("DT_FIL_ING"));
//			filial.setMunicipio(municipio);
//
//			if (rs.getString("ST_FIL_SHO") != null)
//			{
//				filial.setShopping(EnumFilialShopping.getInstance(rs.getString("ST_FIL_SHO")));
//			}
//
//			filial.setShoppingDescricao(rs.getString("DS_FIL_SHO"));
//
//			// Popula a Entidade Genérica
//			entidade.setEmpresa(empresa);
//			entidade.setMunicipio(municipio);
//			entidade.setEstado(estado);
//			entidade.setFilial(filial);
//
//			// Popula a lista
//			lista.add(entidade);
//		}
//		rs.close();
//		pstmt.close();
//		logger.debug("done");
//		return lista;
//	}
//
//	/**
//	 * Lista as primeiras 50 Empresas
//	 */
//	public List<GrupoEntidadeE> listarEmpresas(ConnectionImpl conn) throws DBException, SQLException
//	{
//		logger.debug("start");
//
//		StringBuilder sql = new StringBuilder();
//		sql.append(" SELECT TB1.CD_EMPGCB, TB1.NM_EMPGCB_RZS, TB1.NM_EMPGCB_GUR, ");
//		sql.append("    	TB1.DT_EMPGCB_PRC_PGT, TB1.DT_EMPGCB_PRC_ADT, TB1.ST_EMPGCB_ATO ");
//		sql.append(" FROM EMP_GCB TB1 ");
//		sql.append(" ORDER BY TB1.CD_EMPGCB ");
//		sql.append(" FETCH FIRST 50 ROWS ONLY ");
//
//		PrepareStatementImpl pstmt = conn.prepareStatement(sql.toString());
//		ResultSetImpl rs = pstmt.executeQuery();
//		List<GrupoEntidadeE> lista = new ArrayList<GrupoEntidadeE>();
//		GrupoEntidadeE entidade = null;
//		while (rs.next())
//		{
//
//			entidade = new GrupoEntidadeE();
//			/*
//			 * Empresa
//			 */
//			EmpresaE empresa = EntityFactory.getInstance().buildInstance(EmpresaE.class, false);
//			empresa.setPk(rs.getLong("CD_EMPGCB"));
//			empresa.setRazaoSocial(rs.getString("NM_EMPGCB_RZS"));
//			empresa.setDescricao(rs.getString("NM_EMPGCB_GUR"));
//			empresa.setDataProcessamentoPagamento(rs.getDate("DT_EMPGCB_PRC_PGT"));
//			empresa.setDataProcessamentoAdiantamento(rs.getDate("DT_EMPGCB_PRC_ADT"));
//			empresa.setStatus(rs.getBooleanSN("ST_EMPGCB_ATO"));
//
//			// Popula a Entidade Genérica
//			entidade.setEmpresa(empresa);
//
//			// Popula a lista
//			lista.add(entidade);
//		}
//		rs.close();
//		pstmt.close();
//		logger.debug("done");
//		return lista;
//	}
//
//	/**
//	 * Lista as primeiras 50 Datas de Histórico de Pagamento
//	 */
//	public List<GrupoEntidadeE> listarHistoricosDatasPagamento(ConnectionImpl conn) throws DBException, SQLException
//	{
//		logger.debug("start");
//
//		StringBuilder sql = new StringBuilder();
//		sql.append(" SELECT    HDP.CD_EMPGCB, HDP.DT_HDPGT_CMT, HDP.ST_HDPGT_PGT, HDP.DT_HDPGT_PGT, HDP.DT_HDPGT_PGT_EVP, ");
//		sql.append(" 		 HDP.DT_HDPGT_FEC, HDP.DT_HDPGT_DIA_FEC, HDP.DT_HDPGT_VFC_FEC, HDP.ST_HDPGT_FEC, HDP.DT_HDPGT_ITG_CNT, ");
//		sql.append(" 		 TB1.CD_EMPGCB, TB1.NM_EMPGCB_RZS, TB1.NM_EMPGCB_GUR, ");
//		sql.append(" 	   	 TB1.DT_EMPGCB_PRC_PGT, TB1.DT_EMPGCB_PRC_ADT, TB1.ST_EMPGCB_ATO ");
//		sql.append("  FROM    HTR_DAT_PGT HDP ");
//		sql.append("  JOIN	 EMP_GCB TB1 ");
//		sql.append("  	ON 	 TB1.CD_EMPGCB = HDP.CD_EMPGCB ");
//		sql.append(" FETCH FIRST 50 ROWS ONLY ");
//
//		PrepareStatementImpl pstmt = conn.prepareStatement(sql.toString());
//		ResultSetImpl rs = pstmt.executeQuery();
//		List<GrupoEntidadeE> lista = new ArrayList<GrupoEntidadeE>();
//		GrupoEntidadeE entidade = null;
//		while (rs.next())
//		{
//
//			entidade = new GrupoEntidadeE();
//			DataPagamentoHistoricoE dataPagamentoHistorico = EntityFactory.getInstance().buildInstance(DataPagamentoHistoricoE.class, false);
//
//			/*
//			 * Empresa
//			 */
//			EmpresaE empresa = EntityFactory.getInstance().buildInstance(EmpresaE.class, false);
//			empresa.setPk(rs.getLong("CD_EMPGCB"));
//			empresa.setRazaoSocial(rs.getString("NM_EMPGCB_RZS"));
//			empresa.setDescricao(rs.getString("NM_EMPGCB_GUR"));
//			empresa.setDataProcessamentoPagamento(rs.getDate("DT_EMPGCB_PRC_PGT"));
//			empresa.setDataProcessamentoAdiantamento(rs.getDate("DT_EMPGCB_PRC_ADT"));
//			empresa.setStatus(rs.getBooleanSN("ST_EMPGCB_ATO"));
//
//			/*
//			 * Data Pagamento
//			 */
//			dataPagamentoHistorico.setPk(EntityFactory.getInstance().buildInstancePk(DataPagamentoHistoricoPk.class, false));
//			dataPagamentoHistorico.getPk().setEmpresa(empresa);
//			dataPagamentoHistorico.getPk().setDataCompetencia(rs.getDate("DT_HDPGT_CMT"));
//			dataPagamentoHistorico.getPk().setTipo(EnumPagamentoTipo.getInstance(rs.getString("ST_HDPGT_PGT")));
//
//			String fechada = rs.getString("ST_HDPGT_FEC");
//			dataPagamentoHistorico.setFolhaFechada(fechada == null ? false : (fechada.equals("1")));
//
//			dataPagamentoHistorico.setDataVerificacaoFechamento(rs.getDate("DT_HDPGT_VFC_FEC"));
//			dataPagamentoHistorico.setDataPagamentoEnvelope(rs.getDate("DT_HDPGT_PGT_EVP"));
//			dataPagamentoHistorico.setDataPagamento(rs.getDate("DT_HDPGT_PGT"));
//			dataPagamentoHistorico.setDataIntegracaoContabil(rs.getDate("DT_HDPGT_ITG_CNT"));
//			dataPagamentoHistorico.setDataFechamento(rs.getDate("DT_HDPGT_FEC"));
//			dataPagamentoHistorico.setDataLimiteFechamentoCalculo(rs.getDate("DT_HDPGT_DIA_FEC"));
//
//			// Popula a Entidade Genérica
//			entidade.setEmpresa(empresa);
//			entidade.setDataPagamentoHistorico(dataPagamentoHistorico);
//
//			// Popula a lista
//			lista.add(entidade);
//		}
//		rs.close();
//		pstmt.close();
//		logger.debug("done");
//		return lista;
//	}
//
//	/**
//	 * Lista os primeiros 50 Departamentos
//	 */
//	public List<GrupoEntidadeE> listarDepartamentos(ConnectionImpl conn) throws DBException, SQLException
//	{
//		logger.debug("start");
//
//		StringBuilder sql = new StringBuilder();
//		sql.append(" SELECT TB1.CD_TIPDEP, TB1.NM_TIPDEP, TB1.ST_TIPDEP_LOJ, TB1.CD_TIPDEP_RSP ");
//		sql.append(" FROM TIP_DEP TB1 ");
//		sql.append(" ORDER BY TB1.CD_TIPDEP, TB1.NM_TIPDEP ");
//		sql.append(" FETCH FIRST 50 ROWS ONLY ");
//
//		PrepareStatementImpl pstmt = conn.prepareStatement(sql.toString());
//		ResultSetImpl rs = pstmt.executeQuery();
//		List<GrupoEntidadeE> lista = new ArrayList<GrupoEntidadeE>();
//		GrupoEntidadeE entidade = null;
//		while (rs.next())
//		{
//
//			entidade = new GrupoEntidadeE();
//			/*
//			 * Departamento
//			 */
//			DepartamentoE departamento = EntityFactory.getInstance().buildInstance(DepartamentoE.class, false);
//			departamento.setPk(rs.getLong("CD_TIPDEP"));
//			departamento.setDescricao(rs.getString("NM_TIPDEP"));
//			departamento.setLoja(rs.getBooleanSN("ST_TIPDEP_LOJ"));
//
//			if (rs.getInt("CD_TIPDEP_RSP") != null)
//			{
//				departamento.setResponsavel(EntityFactory.getInstance().buildInstance(DepartamentoE.class, false));
//				departamento.getResponsavel().setPk(rs.getLong("CD_TIPDEP_RSP"));
//			}
//
//			// Popula a Entidade Genérica
//			entidade.setDepartamento(departamento);
//
//			// Popula a lista
//			lista.add(entidade);
//		}
//		rs.close();
//		pstmt.close();
//		logger.debug("done");
//		return lista;
//	}
//
//	/**
//	 * Lista as primeiras 50 Filiais X Departamentos
//	 */
//	public List<GrupoEntidadeE> listarFilialDepartamento(ConnectionImpl conn) throws DBException, SQLException
//	{
//		logger.debug("start");
//
//		StringBuilder sql = new StringBuilder();
//		sql.append(" SELECT  FTD.CD_EMPGCB, FTD.CD_FIL, FTD.CD_TIPDEP, FTD.DS_FTDEP, FTD.ST_FTDEP_RSP, ");
//		sql.append(" 		 GER.CD_GERRGA AS FTD_CD_GERRGA, GER.NM_GERRGA AS FTD_NM_GERRGA, ");
//		sql.append(" 		 DEP.CD_TIPDEP, DEP.NM_TIPDEP, DEP.ST_TIPDEP_LOJ, DEP.CD_TIPDEP_RSP, ");
//		sql.append(" 		 TB1.CD_VRSFIL, TB1.DT_FIL_ING, TB1.NM_FIL_APE, TB1.CD_MUN, ");
//		sql.append(" 		 TB1.CD_EST_SIG, TB1.CD_GERRGA, TB1.ST_FIL_SHO, TB1.DS_FIL_SHO, ");
//		sql.append(" 		 TB2.NM_EMPGCB_RZS, TB2.NM_EMPGCB_GUR, ");
//		sql.append("		 TB3.NM_MUN, TB4.NM_EST ");
//		sql.append(" FROM    FIL_TIP_DEP FTD ");
//		sql.append("  LEFT JOIN    GER_RGA GER ");
//		sql.append("    ON    GER.CD_GERRGA = FTD.CD_GERRGA ");
//		sql.append("  JOIN 	 TIP_DEP DEP ");
//		sql.append("    ON    DEP.CD_TIPDEP = FTD.CD_TIPDEP ");
//		sql.append("  JOIN    FIL TB1 ");
//		sql.append("    ON    TB1.CD_EMPGCB = FTD.CD_EMPGCB ");
//		sql.append("   AND    TB1.CD_FIL = FTD.CD_FIL ");
//		sql.append("  JOIN    EMP_GCB TB2 ");
//		sql.append("    ON    TB2.CD_EMPGCB = FTD.CD_EMPGCB ");
//		sql.append("  JOIN    MUN TB3 ");
//		sql.append("    ON    TB3.CD_MUN = TB1.CD_MUN ");
//		sql.append("  JOIN    EST TB4 ");
//		sql.append("    ON    TB4.CD_EST_SIG = TB1.CD_EST_SIG ");
//		sql.append(" FETCH FIRST 50 ROWS ONLY ");
//
//		PrepareStatementImpl pstmt = conn.prepareStatement(sql.toString());
//		ResultSetImpl rs = pstmt.executeQuery();
//		List<GrupoEntidadeE> lista = new ArrayList<GrupoEntidadeE>();
//		GrupoEntidadeE entidade = null;
//		if (rs.next())
//		{
//
//			entidade = new GrupoEntidadeE();
//
//			/*
//			 * >> EMPRESA
//			 */
//			EmpresaE empresa = EntityFactory.getInstance().buildInstance(EmpresaE.class, false);
//			empresa.setPk(rs.getLong("CD_EMPGCB"));
//			empresa.setRazaoSocial(rs.getString("NM_EMPGCB_RZS"));
//			empresa.setDescricao(rs.getString("NM_EMPGCB_GUR"));
//
//			/*
//			 * >> Estado
//			 */
//			EstadoE estado = EntityFactory.getInstance().buildInstance(EstadoE.class, false);
//			estado.setPk(rs.getString("CD_EST_SIG"));
//			estado.setNome(rs.getString("NM_EST"));
//
//			/*
//			 * >> Municipio
//			 */
//			MunicipioE municipio = EntityFactory.getInstance().buildInstance(MunicipioE.class, false);
//			municipio.setPk(rs.getLong("CD_MUN"));
//			municipio.setNome(rs.getString("NM_MUN"));
//			municipio.setEstado(estado);
//
//			/*
//			 * >> FILIAL
//			 */
//			FilialE filial = EntityFactory.getInstance().buildInstance(FilialE.class, false);
//			FilialPK filialPk = EntityFactory.getInstance().buildInstancePk(FilialPK.class, false);
//			filialPk.setEmpresa(empresa);
//			filialPk.setCodigo(rs.getLong("CD_FIL"));
//			filial.setPk(filialPk);
//			filial.setVersao(rs.getInt("CD_VRSFIL"));
//			filial.setDescricao(rs.getString("NM_FIL_APE"));
//			filial.setDataInauguracao(rs.getDate("DT_FIL_ING"));
//			filial.setShoppingDescricao(rs.getString("DS_FIL_SHO"));
//			filial.setMunicipio(municipio);
//
//			if (rs.getString("ST_FIL_SHO") != null)
//			{
//				filial.setShopping(EnumFilialShopping.getInstance(rs.getString("ST_FIL_SHO")));
//			}
//
//			/*
//			 * >> DEPARTAMENTO
//			 */
//			DepartamentoE departamento = EntityFactory.getInstance().buildInstance(DepartamentoE.class, false);
//			departamento.setPk(rs.getLong("CD_TIPDEP"));
//			departamento.setDescricao(rs.getString("NM_TIPDEP"));
//			departamento.setLoja(rs.getBooleanSN("ST_TIPDEP_LOJ"));
//
//			if (rs.getInt("CD_TIPDEP_RSP") != null)
//			{
//				departamento.setResponsavel(EntityFactory.getInstance().buildInstance(DepartamentoE.class, false));
//				departamento.getResponsavel().setPk(rs.getLong("CD_TIPDEP_RSP"));
//			}
//
//			/*
//			 * >> FILIAL X DEPARTAMENTO
//			 */
//
//			FilialDepartamentoPK filialDepartamentoPk = EntityFactory.getInstance().buildInstancePk(FilialDepartamentoPK.class, false);
//			filialDepartamentoPk.setFilial(filial);
//			filialDepartamentoPk.setDepartamento(departamento);
//
//			FilialDepartamentoE filialDepartamento = EntityFactory.getInstance().buildInstance(FilialDepartamentoE.class, false);
//			filialDepartamento.setDescricao(rs.getString("DS_FTDEP"));
//			filialDepartamento.setResponsavel(rs.getBooleanSN("ST_FTDEP_RSP"));
//			filialDepartamento.setPk(filialDepartamentoPk);
//
//			if (rs.getInt("FTD_CD_GERRGA") != null)
//			{
//				filialDepartamento.setGerenteRegional(EntityFactory.getInstance().buildInstance(GerenteRegionalE.class, false));
//				filialDepartamento.getGerenteRegional().setPk(rs.getLong("FTD_CD_GERRGA"));
//				filialDepartamento.getGerenteRegional().setDescricao(rs.getString("FTD_NM_GERRGA"));
//			}
//
//			// Popula a Entidade Genérica
//			entidade.setEmpresa(empresa);
//			entidade.setEstado(estado);
//			entidade.setMunicipio(municipio);
//			entidade.setFilial(filial);
//			entidade.setDepartamento(departamento);
//			entidade.setFilialDepartamento(filialDepartamento);
//
//			// Popula a lista
//			lista.add(entidade);
//		}
//		rs.close();
//		pstmt.close();
//		logger.debug("done");
//		return lista;
//	}
//
//	/**
//	 * Lista os primeiros 50 Funcionários
//	 */
//	public List<GrupoEntidadeE> listarFuncionarios(ConnectionImpl conn) throws DBException, SQLException
//	{
//		logger.debug("start");
//
//		StringBuilder sql = new StringBuilder();
//		sql.append(" SELECT  F.CD_EMPGCB, F.CD_FUN, F.NM_FUN, F.CD_FIL, F.CD_TIPDEP, ");
//		sql.append(" 		 F.ST_FUN_SXO, F.CD_FUN_CIC, F.CD_FUN_RGE ");
//		sql.append(" FROM    FUN AS F ");
//		sql.append(" FETCH FIRST 50 ROWS ONLY ");
//
//		PrepareStatementImpl pstmt = conn.prepareStatement(sql.toString());
//		ResultSetImpl rs = pstmt.executeQuery();
//		List<GrupoEntidadeE> lista = new ArrayList<GrupoEntidadeE>();
//		GrupoEntidadeE entidade = null;
//		while (rs.next())
//		{
//
//			entidade = new GrupoEntidadeE();
//
//			/*
//			 * >> EMPRESA
//			 */
//			EmpresaE empresa = EntityFactory.getInstance().buildInstance(EmpresaE.class, false);
//			empresa.setPk(rs.getLong("CD_EMPGCB"));
//
//			/*
//			 * >> FuncionarioPk
//			 */
//			FuncionarioPK funcionarioPk = EntityFactory.getInstance().buildInstancePk(FuncionarioPK.class, false);
//			funcionarioPk.setEmpresa(empresa);
//			funcionarioPk.setCodigo(rs.getLong("CD_FUN"));
//
//			/*
//			 * >> Filial
//			 */
//			FilialPK filialPk = EntityFactory.getInstance().buildInstancePk(FilialPK.class, false);
//			filialPk.setEmpresa(empresa);
//			filialPk.setCodigo(rs.getLong("CD_FIL"));
//			FilialE filial = EntityFactory.getInstance().buildInstance(FilialE.class, false);
//			filial.setPk(filialPk);
//
//			/*
//			 * >> Departamento
//			 */
//			DepartamentoE departamento = EntityFactory.getInstance().buildInstance(DepartamentoE.class, false);
//			departamento.setPk(rs.getLong("CD_TIPDEP"));
//
//			/*
//			 * >> Filial x Departamento
//			 */
//			FilialDepartamentoPK filialDepartamentoPk = EntityFactory.getInstance().buildInstancePk(FilialDepartamentoPK.class, false);
//			filialDepartamentoPk.setFilial(filial);
//			filialDepartamentoPk.setDepartamento(departamento);
//			FilialDepartamentoE filialDepartamento = EntityFactory.getInstance().buildInstance(FilialDepartamentoE.class, false);
//			filialDepartamento.setPk(filialDepartamentoPk);
//
//			/*
//			 * >> Funcionário
//			 */
//			FuncionarioE funcionario = EntityFactory.getInstance().buildInstance(FuncionarioE.class, false);
//			funcionario.setPk(funcionarioPk);
//			funcionario.setFilialDepartamento(filialDepartamento);
//			if (rs.getBigDecimal("CD_FUN_CIC") != null)
//			{
//				funcionario.setCpf(StringUtils.leftPad(rs.getBigDecimal("CD_FUN_CIC").abs().toString(), 11, "0"));
//			}
//			funcionario.setRg(rs.getString("CD_FUN_RGE"));
//			funcionario.setNome(rs.getString("NM_FUN"));
//			if (rs.getString("ST_FUN_SXO") != null)
//			{
//				funcionario.setSexo(EnumFuncionarioSexo.getInstance(rs.getString("ST_FUN_SXO")));
//			}
//
//			// Popula a Entidade Genérica
//			entidade.setEmpresa(empresa);
//			entidade.setFilial(filial);
//			entidade.setDepartamento(departamento);
//			entidade.setFilialDepartamento(filialDepartamento);
//			entidade.setFuncionario(funcionario);
//
//			// Popula a lista
//			lista.add(entidade);
//		}
//		rs.close();
//		pstmt.close();
//		logger.debug("done");
//		return lista;
//	}
//
//	/**
//	 * Listar Municipios
//	 */
//	public List<GrupoEntidadeE> listarMunicipios(ConnectionImpl conn) throws DBException, SQLException
//	{
//		logger.debug("start");
//
//		StringBuilder sql = new StringBuilder();
//		sql.append(" SELECT TB1.CD_MUN, TB1.CD_EST_SIG, TB1.NM_MUN, TB2.NM_EST ");
//		sql.append(" FROM MUN TB1 ");
//		sql.append(" JOIN EST TB2 ");
//		sql.append(" 	ON TB1.CD_EST_SIG = TB2.CD_EST_SIG ");
//		sql.append(" FETCH FIRST 50 ROWS ONLY ");
//
//		PrepareStatementImpl pstmt = conn.prepareStatement(sql.toString());
//		ResultSetImpl rs = pstmt.executeQuery();
//		List<GrupoEntidadeE> lista = new ArrayList<GrupoEntidadeE>();
//		GrupoEntidadeE entidade = null;
//		while (rs.next())
//		{
//
//			entidade = new GrupoEntidadeE();
//
//			/*
//			 * Municipio
//			 */
//			MunicipioE municipio = EntityFactory.getInstance().buildInstance(MunicipioE.class, false);
//			municipio.setPk(rs.getLong("CD_MUN"));
//			municipio.setNome(rs.getString("NM_MUN"));
//
//			/*
//			 * Estado
//			 */
//			EstadoE estado = EntityFactory.getInstance().buildInstance(EstadoE.class, false);
//			estado.setPreLoad(true);
//			estado.setPk(rs.getString("CD_EST_SIG"));
//			estado.setNome(rs.getString("NM_EST"));
//			municipio.setEstado(estado);
//
//			// Popula a Entidade Genérica
//			entidade.setMunicipio(municipio);
//			entidade.setEstado(estado);
//
//			// Popula a lista
//			lista.add(entidade);
//		}
//		rs.close();
//		pstmt.close();
//		logger.debug("done");
//		return lista;
//	}
//
//	/**
//	 * Listar Notificações
//	 */
//	public List<GrupoEntidadeE> listarNotificacoes(ConnectionImpl conn) throws DBException, SQLException
//	{
//		logger.debug("start");
//
//		StringBuilder sql = new StringBuilder();
//		sql.append(" SELECT	CD_SISMSG, CD_EMPGCB_FUN_DIG, CD_FUN_DIG, ST_SISMSG_ORI, ");
//		sql.append(" 		TS_SISMSG_INI, TS_SISMSG_FIN, DT_SISMSG_REG, DS_SISMSG, ST_SISMSG, ST_SISMSG_TIP ");
//		sql.append(" FROM	SIS_MSG ");
//		sql.append(" FETCH FIRST 50 ROWS ONLY ");
//
//		PrepareStatementImpl pstmt = conn.prepareStatement(sql.toString());
//		ResultSetImpl rs = pstmt.executeQuery();
//		List<GrupoEntidadeE> lista = new ArrayList<GrupoEntidadeE>();
//		GrupoEntidadeE entidade = null;
//		while (rs.next())
//		{
//
//			entidade = new GrupoEntidadeE();
//
//			/*
//			 * Notificações
//			 */
//			NotificacaoE notificacao = EntityFactory.getInstance().buildInstance(NotificacaoE.class, false);
//			notificacao.setPk(rs.getLong("CD_SISMSG"));
//			notificacao.setVigenciaInicial(rs.getTimestamp("TS_SISMSG_INI"));
//			notificacao.setVigenciaFinal(rs.getTimestamp("TS_SISMSG_FIN"));
//			notificacao.setDataRegistro(rs.getDate("DT_SISMSG_REG"));
//			notificacao.setMensagem(rs.getString("DS_SISMSG"));
//			if (rs.getString("ST_SISMSG_ORI") != null)
//			{
//				notificacao.setModulo(EnumSystemModule.getInstance(rs.getString("ST_SISMSG_ORI")));
//			}
//			if (rs.getString("ST_SISMSG") != null)
//			{
//				notificacao.setStatus(EnumNotificacaoStatus.getInstance(rs.getString("ST_SISMSG")));
//			}
//
//			if (rs.getString("ST_SISMSG_TIP") != null)
//			{
//				notificacao.setTipo(EnumNotificacaoTipo.getInstance(rs.getString("ST_SISMSG_TIP")));
//			}
//
//			/*
//			 * Usuário
//			 */
//			UsuarioE usuario = new UsuarioE();
//			usuario.setEmpresaId(rs.getLong("CD_EMPGCB_FUN_DIG"));
//			usuario.setFuncionarioId(rs.getLong("CD_FUN_DIG"));
//			notificacao.setUsuario(usuario);
//
//			// Popula a Entidade Genérica
//			entidade.setNotificacao(notificacao);
//			entidade.setUsuario(usuario);
//
//			// Popula a lista
//			lista.add(entidade);
//		}
//		rs.close();
//		pstmt.close();
//		logger.debug("done");
//		return lista;
//	}
//}
