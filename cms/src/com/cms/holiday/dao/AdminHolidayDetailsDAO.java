package com.cms.holiday.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.application.util.AppDateUtil;
import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.holiday.bean.AdminHolidayDetailsDO;

public class AdminHolidayDetailsDAO {

	private static final String SELECT="select   holiday_id, holiday_type_id, holiday_name, holiday_sub_type, holiday, bool_delete_status, created_user, created_date, update_user, update_date from admin_holiday_details ";
	private static final String INSERT="insert into admin_holiday_details( holiday_id, holiday_type_id, holiday_name, holiday_sub_type, holiday, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() ) ";
	private static final String UPDATE="update  admin_holiday_details set  holiday_type_id=?, holiday_name=?, holiday_sub_type=?, holiday=?,  update_user=?, update_date=NOW() WHERE holiday_id=? ";

	public static int insert(Connection preCon, AdminHolidayDetailsDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():con;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getHolidayId() );
			stmt.setInt(i++, dto.getHolidayTypeId() );
			stmt.setString(i++, dto.getHolidayName() );
			stmt.setString(i++, dto.getHolidaySubType() );
			stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getHoliday(), false, true)  );
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

	public static boolean update(Connection preCon, AdminHolidayDetailsDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setInt(i++,dto.getHolidayTypeId());
			stmt.setString(i++,dto.getHolidayName());
			stmt.setString(i++,dto.getHolidaySubType());
			stmt.setString(i++,AppDateUtil.convertToDBDate(dto.getHoliday(), false, true) );
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setInt(i++,dto.getHolidayId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static List<AdminHolidayDetailsDO> getAdminHolidayDetails(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<AdminHolidayDetailsDO> dtoList =getAdminHolidayDetails(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<AdminHolidayDetailsDO>(); }
		return dtoList;
	}

	public static AdminHolidayDetailsDO getAdminHolidayDetailsByHolidayId(Connection preCon, int holidayId, boolean needChild) {
		String query=SELECT+" WHERE holiday_id="+holidayId;
		List<AdminHolidayDetailsDO> dtoList =getAdminHolidayDetails(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<AdminHolidayDetailsDO>(); }
		return dtoList.size()>0?dtoList.get(0): new AdminHolidayDetailsDO();
	}

	private  static List<AdminHolidayDetailsDO> getAdminHolidayDetails(Connection preCon, String query, boolean needChild) {
		List<AdminHolidayDetailsDO> dtos=new ArrayList<AdminHolidayDetailsDO>();
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

	private static AdminHolidayDetailsDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		AdminHolidayDetailsDO dto=new AdminHolidayDetailsDO();
		try {
			int i=1;
			dto.setHolidayId(rs.getInt(i++));
			dto.setHolidayTypeId(rs.getInt(i++));
			dto.setHolidayName(rs.getString(i++));
			dto.setHolidaySubType(rs.getString(i++));
			dto.setHoliday(AppDateUtil.convertToAppDate(rs.getString(i++), false, true) );
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate(AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate(AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	} 


}