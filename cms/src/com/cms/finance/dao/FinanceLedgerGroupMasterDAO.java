package com.cms.finance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.application.util.AppDateUtil;
import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.finance.bean.FinanceLedgerGroupMasterDO;

public class FinanceLedgerGroupMasterDAO {

	private final static String SELECT="select group_id, parent_group_id, group_name, bool_delete_status, created_user, created_date, update_user, update_date " + 
			"from finance_ledger_group_master ";
	private final static String INSERT="insert into finance_ledger_group_master(group_id, parent_group_id, group_name, bool_delete_status, created_user, created_date, update_user, update_date) " + 
			"values(?, ?, ?, ?, ?, NOW(), ?, NOW() ) ";


	public static ArrayList<FinanceLedgerGroupMasterDO> gatFinanceLedgerGroupMasterList(Connection preCon, boolean needChild) {
		ArrayList<FinanceLedgerGroupMasterDO> dtos=new ArrayList<FinanceLedgerGroupMasterDO>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			rs=stmt.executeQuery( SELECT );
			while(rs.next()) { dtos.add(constructDTO( rs, needChild) );	}

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null  ); }
		return dtos;

	}

	
	public static int insert(Connection preCon, FinanceLedgerGroupMasterDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getGroupId() );
			stmt.setInt(i++, dto.getParentGroupId() );
			stmt.setString(i++, dto.getGroupName() );
			stmt.setBoolean(i++, dto.isBoolDeleteStatus() );
			stmt.setString(i++, dto.getCreatedUser() );
			stmt.setString(i++, dto.getUpdateUser() );
			
			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) { insertId=rs.getInt(1); }
			
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null  ); }
		return insertId;
		
	}
	private static FinanceLedgerGroupMasterDO constructDTO( ResultSet rs, boolean needChild ) {

		FinanceLedgerGroupMasterDO dto=new FinanceLedgerGroupMasterDO();
		try {
			int i=1;
			dto.setGroupId( rs.getInt( i++) );
			dto.setParentGroupId( rs.getInt( i++) );
			dto.setGroupName( rs.getString( i++) );
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) ); 
			
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	}

}
