package com.cms.user.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.application.util.AppDateUtil;
import com.application.util.AppUtil;
import com.application.util.CMSMailer;
import com.application.util.EmailPayload;
import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.user.login.UserType;
import com.cms.user.login.bean.AdminLoginMasterDO;

public class AdminLoginMasterDAO {

	private static final String SELECT="select login_key, login_id, login_pwd, ref_type, ref_id, bool_login_active_status, bool_delete_status, rights_template_id, created_user, created_date, update_user, update_date from admin_login_master ";
	private static final String INSERT="insert into admin_login_master( login_key, login_id, login_pwd, ref_type, ref_id, bool_login_active_status, bool_delete_status, rights_template_id, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() ) ";
	private static final String UPDATE="update admin_login_master set  login_id=?, login_pwd=?, ref_type=?, ref_id=?, bool_login_active_status=?, bool_delete_status=?, rights_template_id=?, update_user=?, update_date=NOW() WHERE login_key=? ";

	public static int insert(Connection preCon, AdminLoginMasterDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getLoginKey() );
			stmt.setString(i++, dto.getLoginId() );
			stmt.setString(i++, dto.getLoginPwd() );
			stmt.setString(i++, dto.getRefType() );
			stmt.setInt(i++, dto.getRefId() );
			stmt.setBoolean(i++, dto.getBoolLoginActiveStatus() );
			stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
			stmt.setInt(i++, dto.getRightsTemplateId() );
			stmt.setString(i++, dto.getCreatedUser() );
			stmt.setString(i++, dto.getUpdateUser() );
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
			stmt.setString(i++, dto.getLoginId());
			stmt.setString(i++, dto.getLoginPwd());
			stmt.setString(i++, dto.getRefType());
			stmt.setInt(i++, dto.getRefId());
			stmt.setBoolean(i++, dto.getBoolLoginActiveStatus());
			stmt.setBoolean(i++, dto.getBoolDeleteStatus());
			stmt.setInt(i++, dto.getRightsTemplateId());
			stmt.setString(i++, dto.getUpdateUser());
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
	public static AdminLoginMasterDO getAdminLoginMasterByLoginId(Connection preCon, String loginId, boolean needChild) {
		String query=SELECT+" WHERE login_id='"+loginId+"'";
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
			con=preCon==null?DBConnection.getConnection():preCon;
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
			dto.setRightsTemplateId(rs.getInt(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate(AppDateUtil.convertToAppDate(rs.getString(i++), true, true));
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate(AppDateUtil.convertToAppDate(rs.getString(i++), true, true));
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	} 

	public static boolean createLogin(Connection preCon, String refType, int refId, String createUser) {
		boolean retVal = false;
		Connection con = null;
		String loginId="";
		String password="cms@123";
		try {
			AdminLoginMasterDO loginMasterDO = new AdminLoginMasterDO();
			loginMasterDO.setLoginPwd( password );
			if(refType.equalsIgnoreCase(UserType.EMPLOYEE.getType())) {
				loginMasterDO.setRefType( UserType.EMPLOYEE.getType() );
				loginId="CMSE"+AppUtil.fillDigit(refId, 4, '0');
			}else if(refType.equalsIgnoreCase(UserType.CUSTOMER.getType())) {
				loginMasterDO.setRefType( UserType.CUSTOMER.getType() );
				loginId="CMSC"+AppUtil.fillDigit(refId, 4, '0');
			}else {
				
			}
			loginMasterDO.setRefId( refId );
			loginMasterDO.setLoginId( loginId );
			loginMasterDO.setCreatedDate(createUser); 
			loginMasterDO.setUpdateUser(createUser);
			loginMasterDO.setBoolLoginActiveStatus( true );
			loginMasterDO.setBoolDeleteStatus( true );
			
			con = preCon==null? DBConnection.getConnection():preCon;
			int loginKey=insert(con, loginMasterDO);
			if(loginKey!=0) { 
				retVal = true; 
			}
		} catch (Exception e) { e.printStackTrace(); }
		finally { 
			DBUtil.close(  preCon==null?con:null  ); 
			if(retVal) {
				EmailPayload payload = new EmailPayload();
				payload.setTo("vijay.cool35@gmail.com");
				payload.setSubject("Reg: User Registration");
				payload.setMessage("Dear User,\n\n\t\t Your Account successfully created..!\n\n"
						+ "User Name: "+loginId+"\n password: "+password+"\n\n\n"
						+ "regards\nTeam\nUI-BOT");
				CMSMailer.send(payload);
			}
		}
		return retVal;
	}

	public static boolean updateRights(Connection preCon, int templateId, String updateUser, String refType, int refId) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement("update admin_login_master set rights_template_id=?, update_user=?, update_date=NOW() where ref_type=? and ref_id=? ");
			stmt.setInt(i++, templateId);
			stmt.setString(i++, updateUser);
			stmt.setString(i++, refType);
			stmt.setInt(i++, refId);
			
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

}