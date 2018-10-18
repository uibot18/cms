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
import com.cms.finance.bean.FinanceCompanyMasterDO;

public class FinanceCompanyMasterDAO {

	private final static String SELECT="select company_id,company_name,add1,add2,add3,city,state,pincode,phone1,phone2,fax,email1,email2,website,bool_delete_status,created_user,created_date,update_user,update_date " + 
			" from finance_company_master";
	private final static String INSERT="insert into finance_company_master(company_id,company_name,add1,add2,add3,city,state,pincode,phone1,phone2,fax,email1,email2,website,bool_delete_status,created_user,created_date,update_user,update_date) " + 
			" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?,NOW());";


	public static ArrayList<FinanceCompanyMasterDO> getFinanceCompanyMasterList(Connection preCon, boolean needChild) {
		ArrayList<FinanceCompanyMasterDO> dtos=new ArrayList<FinanceCompanyMasterDO>();
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

	
	public static int insert(Connection preCon, FinanceCompanyMasterDO dto) {
		
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getCompanyId() );
			stmt.setString( i++, dto.getCompanyName());
			stmt.setString( i++, dto.getAdd1());
			stmt.setString( i++, dto.getAdd2());
			stmt.setString( i++, dto.getAdd3());
			stmt.setString( i++, dto.getCity());
			stmt.setString( i++, dto.getState());
			stmt.setString( i++, dto.getPinCode());
			stmt.setString( i++, dto.getPhone1());
			stmt.setString( i++, dto.getPhone2());
			stmt.setString( i++, dto.getFax());
			stmt.setString( i++, dto.getEmail1());
			stmt.setString( i++, dto.getEmail2());
			stmt.setString( i++, dto.getWebSite());
			stmt.setBoolean( i++, dto.isBoolDeleteStatus());
			stmt.setString( i++, dto.getCreatedUser());
			stmt.setString( i++, dto.getUpdateUser());

			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) { insertId=rs.getInt(1); }
			
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null  ); }
		return insertId;
		
	}
	private static FinanceCompanyMasterDO constructDTO( ResultSet rs, boolean needChild ) {

		FinanceCompanyMasterDO dto=new FinanceCompanyMasterDO();
		try {
			int i=1;
			dto.setCompanyId(rs.getInt(i++));
			dto.setCompanyName(rs.getString(i++));
			dto.setAdd1(rs.getString(i++));
			dto.setAdd2(rs.getString(i++));
			dto.setAdd3(rs.getString(i++));
			dto.setCity(rs.getString(i++));
			dto.setState(rs.getString(i++));
			dto.setPinCode(rs.getString(i++));
			dto.setPhone1(rs.getString(i++));
			dto.setPhone2(rs.getString(i++));
			dto.setFax(rs.getString(i++));
			dto.setEmail1(rs.getString(i++));
			dto.setEmail2(rs.getString(i++));
			dto.setWebSite(rs.getString(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) ); 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally { }
		return dto;
	}

}
