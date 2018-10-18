package com.cms.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.employee.bean.AdminEmployeeQualificationDetailsDO;

public class AdminEmployeeQualificationDetailsDAO {
	private static final String SELECT="select   emp_qual_id, emp_id, course_name, major, grade_per, year_of_passing, school_col_univ, bool_delete_status, created_user, created_date, update_user, update_date from admin_employee_qualification_details ";
	private static final String INSERT="insert into admin_employee_qualification_details( emp_qual_id, emp_id, course_name, major, grade_per, year_of_passing, school_col_univ, bool_delete_status, created_user, created_date, update_user, update_date)  values( ?,?,?,?,?,?,?,?,?,?,?,? ) ";
	private static final String UPDATE="update  admin_employee_qualification_details set  emp_id=?, course_name=?, major=?, grade_per=?, year_of_passing=?, school_col_univ=?, bool_delete_status=?, created_user=?, created_date=?, update_user=?, update_date=? WHERE emp_qual_id=? ";

	public static int insert(Connection preCon, AdminEmployeeQualificationDetailsDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getEmpQualId() );
			stmt.setInt(i++, dto.getEmpId() );
			stmt.setString(i++, dto.getCourseName() );
			stmt.setString(i++, dto.getMajor() );
			stmt.setString(i++, dto.getGradePer() );
			stmt.setString(i++, dto.getYearOfPassing() );
			stmt.setString(i++, dto.getSchoolColUniv() );
			stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
			stmt.setString(i++, dto.getCreatedUser() );
			stmt.setString(i++, dto.getCreatedDate() );
			stmt.setString(i++, dto.getUpdateUser() );
			stmt.setString(i++, dto.getUpdateDate() );
			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) { insertId=rs.getInt(1); }
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
		return insertId;
	}

	public static boolean update(Connection preCon, AdminEmployeeQualificationDetailsDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setInt(i++,dto.getEmpId());
			stmt.setString(i++,dto.getCourseName());
			stmt.setString(i++,dto.getMajor());
			stmt.setString(i++,dto.getGradePer());
			stmt.setString(i++,dto.getYearOfPassing());
			stmt.setString(i++,dto.getSchoolColUniv());
			stmt.setBoolean(i++,dto.getBoolDeleteStatus());
			stmt.setString(i++,dto.getCreatedUser());
			stmt.setString(i++,dto.getCreatedDate());
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setString(i++,dto.getUpdateDate());
			stmt.setInt(i++,dto.getEmpQualId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static List<AdminEmployeeQualificationDetailsDO> getAdminEmployeeQualificationDetails(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<AdminEmployeeQualificationDetailsDO> dtoList =getAdminEmployeeQualificationDetails(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<AdminEmployeeQualificationDetailsDO>(); }
		return dtoList;
	}

	public static AdminEmployeeQualificationDetailsDO getAdminEmployeeQualificationDetailsByEmpQualId(Connection preCon, int empQualId, boolean needChild) {
		String query=SELECT+" WHERE emp_qual_id="+empQualId;
		List<AdminEmployeeQualificationDetailsDO> dtoList =getAdminEmployeeQualificationDetails(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<AdminEmployeeQualificationDetailsDO>(); }
		return dtoList.size()>0?dtoList.get(0): new AdminEmployeeQualificationDetailsDO();
	}

	private  static List<AdminEmployeeQualificationDetailsDO> getAdminEmployeeQualificationDetails(Connection preCon, String query, boolean needChild) {
		List<AdminEmployeeQualificationDetailsDO> dtos=new ArrayList<AdminEmployeeQualificationDetailsDO>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			rs=stmt.executeQuery( query );
			while(rs.next()) { dtos.add(constructDTO( con, rs, needChild) );	}
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null ); }
		return dtos;
	}
	private static AdminEmployeeQualificationDetailsDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		AdminEmployeeQualificationDetailsDO dto=new AdminEmployeeQualificationDetailsDO();
		try {
			int i=1;
			dto.setEmpQualId(rs.getInt(i++));
			dto.setEmpId(rs.getInt(i++));
			dto.setCourseName(rs.getString(i++));
			dto.setMajor(rs.getString(i++));
			dto.setGradePer(rs.getString(i++));
			dto.setYearOfPassing(rs.getString(i++));
			dto.setSchoolColUniv(rs.getString(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate(rs.getString(i++));
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate(rs.getString(i++));
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	} 


}