package com.cms.finance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.application.util.AppDateUtil;
import com.application.util.AppUtil;
import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.finance.bean.FinanceLedgerMasterDO;

public class FinanceLedgerMasterDAO {

	private final static String SELECT="select ledger_id,company_id,group_id,ledger_name,ref_type,ref_id,bool_delete_status,created_user,created_date,update_user,update_date " + 
			"from finance_ledger_master ";
	private final static String INSERT="insert into finance_ledger_master( ledger_id, company_id, group_id, ledger_name,ref_type,ref_id,bool_delete_status,created_user,created_date,update_user,update_date ) " + 
			"values(?,?,?,?,?,?,?,?,NOW(),?,NOW() )";
	private final static String UPDATE=" update  finance_ledger_master set ledger_name=?, update_user=?, update_date=NOW() where ledger_id=?";


	public static ArrayList<FinanceLedgerMasterDO> getFinanceLedgerMasterList(Connection preCon, boolean needChild) {
		String query=SELECT;
		return getFinanceLedgerMasters(preCon, query, needChild);
	}
	
	public static ArrayList<FinanceLedgerMasterDO> getFinanceLedgerMasterListBysubQry(Connection preCon, String subQry, boolean needChild) {
		String query=SELECT + "WHERE 0=0 "+AppUtil.getNullToEmpty( subQry );
		return getFinanceLedgerMasters(preCon, query, needChild);
	}
	
	public static FinanceLedgerMasterDO getFinanceLedgerMasterBysubQry(Connection preCon, String subQry, boolean needChild) {
		String query=SELECT + "WHERE 0=0 "+AppUtil.getNullToEmpty( subQry );
		ArrayList<FinanceLedgerMasterDO> masterList =getFinanceLedgerMasters(preCon, query, needChild);
		return masterList.size()>0?masterList.get(0):new FinanceLedgerMasterDO();
	}
	public static FinanceLedgerMasterDO getFinanceLedgerMasterByLedgerId(Connection preCon, int ledgerId, boolean needChild) {
		String query=SELECT + "WHERE ledger_id= "+ledgerId;
		ArrayList<FinanceLedgerMasterDO> masterList =getFinanceLedgerMasters(preCon, query, needChild);
		return masterList.size()>0?masterList.get(0):new FinanceLedgerMasterDO();
	}
	
	public static  ArrayList<FinanceLedgerMasterDO> getFinanceLedgerMasters(Connection preCon, String query, boolean needChild){
		ArrayList<FinanceLedgerMasterDO> dtos=new ArrayList<FinanceLedgerMasterDO>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			rs=stmt.executeQuery( query );
			while(rs.next()) { dtos.add(constructDTO( rs, needChild) );	}

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null  ); }
		return dtos;
	}

	
	public static int insert(Connection preCon, FinanceLedgerMasterDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getLedgerId() );
			stmt.setInt(i++, dto.getCompanyId() );
			stmt.setInt(i++, dto.getGroupId() );
			stmt.setString(i++, dto.getLedgerName() );
			stmt.setString(i++, dto.getRef_type() );
			stmt.setInt(i++, dto.getRef_id() );
			stmt.setBoolean(i++, dto.isBoolDeleteStatus() );
			stmt.setString(i++, dto.getCreatedUser() );
			stmt.setString(i++, dto.getUpdateUser() );
			System.out.println("Ledger => insert:"+stmt.toString() );
			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) { insertId=rs.getInt(1); }
			
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null  ); }
		return insertId;
		
	}
	private static FinanceLedgerMasterDO constructDTO( ResultSet rs, boolean needChild ) {

		FinanceLedgerMasterDO dto=new FinanceLedgerMasterDO();
		try {
			int i=1;
			dto.setLedgerId(rs.getInt(i++));
			dto.setCompanyId(rs.getInt(i++));
			dto.setGroupId(rs.getInt(i++));
			dto.setLedgerName(rs.getString(i++));
			dto.setRef_type(rs.getString(i++));
			dto.setRef_id(rs.getInt(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) ); 
			
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	}

	public static boolean update(Connection preCon, FinanceLedgerMasterDO ledgerMasterDO) {

		Connection con=null;
		PreparedStatement stmt=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;

			stmt=con.prepareStatement(UPDATE);
			stmt.setString(1,ledgerMasterDO.getLedgerName() );
			stmt.setString(2,ledgerMasterDO.getUpdateUser());
			stmt.setInt(3,ledgerMasterDO.getLedgerId());
			System.out.println("Ledger Update: "+stmt.toString());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

}
