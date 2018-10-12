package com.cms.holiday.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.application.util.AppDateUtil;
import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.holiday.bean.AdminHolidayTypeDO;

public class AdminHolidayTypeDAO {
	
	private static final String SELECT="select   holiday_type_id, holiday_type, bool_delete_status, created_user, created_date, update_user, update_date from admin_holiday_type ";
	private static final String INSERT="insert into admin_holiday_type( holiday_type_id, holiday_type, bool_delete_status, created_user, created_date, update_user, update_date)  values( ?, ?, ?, ?, NOW(), ?, now() ) ";
	private static final String UPDATE="update  admin_holiday_type set  holiday_type=?, update_user=?, update_date=NOW() WHERE holiday_type_id=? ";

	public static int insert(Connection preCon, AdminHolidayTypeDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():con;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getHolidayTypeId() );
			stmt.setString(i++, dto.getHolidayType() );
			stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
			stmt.setString(i++, dto.getCreatedUser() );
			stmt.setString(i++, dto.getUpdateUser() );
			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) { insertId=rs.getInt(1); }
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
		return insertId;
	}

	public static boolean update(Connection preCon, AdminHolidayTypeDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setString(i++,dto.getHolidayType());
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setInt(i++,dto.getHolidayTypeId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static List<AdminHolidayTypeDO> getAdminHolidayType(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<AdminHolidayTypeDO> dtoList =getAdminHolidayType(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<AdminHolidayTypeDO>(); }
		return dtoList;
	}

	public static AdminHolidayTypeDO getAdminHolidayTypeByHolidayTypeId(Connection preCon, int holidayTypeId, boolean needChild) {
		String query=SELECT+" WHERE holiday_type_id="+holidayTypeId;
		List<AdminHolidayTypeDO> dtoList =getAdminHolidayType(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<AdminHolidayTypeDO>(); }
		return dtoList.size()>0?dtoList.get(0): new AdminHolidayTypeDO();
	}

	private  static List<AdminHolidayTypeDO> getAdminHolidayType(Connection preCon, String query, boolean needChild) {
		List<AdminHolidayTypeDO> dtos=new ArrayList<AdminHolidayTypeDO>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():con;
			stmt=con.createStatement();
			rs=stmt.executeQuery( query );
			while(rs.next()) { dtos.add(constructDTO( con, rs, needChild) );	}
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null ); }
		return dtos;
	}
	private static AdminHolidayTypeDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		AdminHolidayTypeDO dto=new AdminHolidayTypeDO();
		try {
			int i=1;
			dto.setHolidayTypeId(rs.getInt(i++));
			dto.setHolidayType(rs.getString(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate(AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate(AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	}

	public static Map<String, String> getHolidayTypeMap(Connection preCon) {
		Map<String, String> typeMap=new HashMap<String, String>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		String query=" SELECT holiday_type_id, holiday_type FROM admin_holiday_type WHERE bool_delete_status=0 ";
		
		try {
			con=preCon==null?DBConnection.getConnection():con;
			stmt=con.createStatement();
			rs=stmt.executeQuery( query );
			while(rs.next()) { 
				typeMap.put(""+rs.getInt(1), ""+rs.getString(2));
			}
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null ); }
		return typeMap;
	} 


}