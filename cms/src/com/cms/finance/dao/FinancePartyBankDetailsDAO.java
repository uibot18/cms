package com.cms.finance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.application.util.AppDateUtil;
import com.application.util.AppUtil;
import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.finance.bean.FinancePartyBankDetailsDO;

public class FinancePartyBankDetailsDAO {

	private final static String SELECT="select branch_id, bank_id, area, city, state, ifsc_code, bool_delete_status, created_user, created_date, update_user, update_date " + 
			"from finance_party_bank_branch_details";
	private final static String INSERT="insert into finance_party_bank_branch_details(branch_id, bank_id, area, city, state, ifsc_code, bool_delete_status, created_user, created_date, update_user, update_date) " + 
			"values(?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW()) ";


	public static ArrayList<FinancePartyBankDetailsDO> getFinancePartyBankDetailsList(Connection preCon, boolean needChild) {
		String query=SELECT;
		return getFinancePartyBankDetails(preCon, query, needChild);
	}
	
	public static ArrayList<FinancePartyBankDetailsDO> getFinancePartyBankDetailsListBySubQry(Connection preCon, String subQry, boolean needChild) {
		String query=SELECT + "WHERE 0=0 "+AppUtil.getNullToEmpty( subQry );
		return getFinancePartyBankDetails(preCon, query, needChild);
	}
	
	public static FinancePartyBankDetailsDO getFinancePartyBankDetailsBySubQry(Connection preCon, String subQry, boolean needChild) {
		String query=SELECT + "WHERE 0=0 "+AppUtil.getNullToEmpty( subQry );
		ArrayList<FinancePartyBankDetailsDO> masterList =getFinancePartyBankDetails(preCon, query, needChild);
		return masterList.size()>0?masterList.get(0):new FinancePartyBankDetailsDO();
	}
	
	private static  ArrayList<FinancePartyBankDetailsDO> getFinancePartyBankDetails(Connection preCon, String query, boolean needChild){
		ArrayList<FinancePartyBankDetailsDO> dtos=new ArrayList<FinancePartyBankDetailsDO>();
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

	
	public static int insert(Connection preCon, FinancePartyBankDetailsDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getBankId());
			stmt.setString(i++, dto.getBankName() );
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
	private static FinancePartyBankDetailsDO constructDTO( ResultSet rs, boolean needChild ) {

		FinancePartyBankDetailsDO dto=new FinancePartyBankDetailsDO();
		try {
			int i=1;
			dto.setBankId( rs.getInt(i++));
			dto.setBankName( rs.getString(i++) );
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) ); 
			
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	}

	public static boolean update(Connection preCon, FinancePartyBankDetailsDO dto) {

		Connection con=null;
		PreparedStatement stmt=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;

//			stmt=con.prepareStatement(UPDATE);
//			stmt.setString(1,dto.getLedgerName() );
//			stmt.setString(2,dto.getUpdateUser());
//			stmt.setInt(3,dto.getLedgerId());
			System.out.println("Ledger Update: "+stmt.toString());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}
	
	public static Map<String, String> loadBankMap( Connection preCon ){
		Map<String, String> map=new HashMap<String, String>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		String query=" SELECT bank_id, bank_name FROM finance_party_bank_details ";
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			rs=stmt.executeQuery( query );
			while(rs.next()) { 
				map.put(""+rs.getInt(1), ""+rs.getString(2) );
			}

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null  ); }
		return map;
	}

}
