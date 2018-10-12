package com.cms.user.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.user.login.bean.AdminLoginMasterDO;

public class AdminLoginMasterDAO {

	private static final String SELECT="select   login_key, login_id, login_pwd, ref_type, ref_id, bool_login_active_status, bool_delete_status, created_user, created_date, update_user, update_date from admin_login_master ";
	private static final String INSERT="insert into admin_login_master( login_key, login_id, login_pwd, ref_type, ref_id, bool_login_active_status, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
	private static final String UPDATE="update  admin_login_master set  login_id=?, login_pwd=?, ref_type=?, ref_id=?, bool_login_active_status=?, bool_delete_status=?, created_user=?, created_date=?, update_user=?, update_date=? WHERE login_key=? ";

	public static int insert(Connection preCon, AdminLoginMasterDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():con;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getLoginKey() );
			stmt.setString(i++, dto.getLoginId() );
			stmt.setString(i++, dto.getLoginPwd() );
			stmt.setString(i++, dto.getRefType() );
			stmt.setInt(i++, dto.getRefId() );
			stmt.setBoolean(i++, dto.getBoolLoginActiveStatus() );
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

	public static boolean update(Connection preCon, AdminLoginMasterDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setString(i++,dto.getLoginId());
			stmt.setString(i++,dto.getLoginPwd());
			stmt.setString(i++,dto.getRefType());
			stmt.setInt(i++,dto.getRefId());
			stmt.setBoolean(i++,dto.getBoolLoginActiveStatus());
			stmt.setBoolean(i++,dto.getBoolDeleteStatus());
			stmt.setString(i++,dto.getCreatedUser());
			stmt.setString(i++,dto.getCreatedDate());
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setString(i++,dto.getUpdateDate());
			stmt.setInt(i++,dto.getLoginKey());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static List<AdminLoginMasterDO> getAdminLoginMaster(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<AdminLoginMasterDO> dtoList =getAdminLoginMaster(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<AdminLoginMasterDO>(); }
		return dtoList;
	}

	public static AdminLoginMasterDO getAdminLoginMasterByLoginKey(Connection preCon, int loginKey, boolean needChild) {
		String query=SELECT+" WHERE login_key="+loginKey;
		List<AdminLoginMasterDO> dtoList =getAdminLoginMaster(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<AdminLoginMasterDO>(); }
		return dtoList.size()>0?dtoList.get(0): new AdminLoginMasterDO();
	}

	private  static List<AdminLoginMasterDO> getAdminLoginMaster(Connection preCon, String query, boolean needChild) {
		List<AdminLoginMasterDO> dtos=new ArrayList<AdminLoginMasterDO>();
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

	private static AdminLoginMasterDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		AdminLoginMasterDO dto=new AdminLoginMasterDO();
		try {
			int i=1;
			dto.setLoginKey(rs.getInt(i++));
			dto.setLoginId(rs.getString(i++));
			dto.setLoginPwd(rs.getString(i++));
			dto.setRefType(rs.getString(i++));
			dto.setRefId(rs.getInt(i++));
			dto.setBoolLoginActiveStatus(rs.getBoolean(i++));
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