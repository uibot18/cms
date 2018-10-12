package com.cms.user.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cms.common.db.connection.DBConnection;
import com.cms.user.login.bean.LoginMasterBean;

public class LoginMasterDAO {

	private static final String INSERT="insert into login_master(login_id, password, email,  is_active, status) values(?,?,?,?,?)";
	private static final String SELECT="select id, login_id, password, email, is_active, status from login_master";
	
	
	public static int insert(LoginMasterBean dto) throws SQLException {
	Connection con=null;
	PreparedStatement ps = null;
	int id=0;
	ResultSet rs = null;
		try {
			con=DBConnection.getConnection();
			ps=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getLoginId());
			ps.setString(2, dto.getPassword());
			ps.setString(3, dto.getEmail());
			ps.setBoolean(4, dto.isActive());
			ps.setString(5, dto.getStatus());
			ps.executeUpdate();
			rs=ps.getGeneratedKeys();
			if(rs.next()){ id=rs.getInt(1); System.out.println("id: "+id); }
		} catch (Exception e) { e.printStackTrace(); }
		finally { ps.close(); rs.close(); con.close(); }
		return id;
	}
	
	public static LoginMasterBean getLoginMasterByLoginid(String loginId)  {
		Connection con=null;
		PreparedStatement ps = null;
		LoginMasterBean loginMasterBean=null;
		ResultSet rs = null;
			try {
				con=DBConnection.getConnection();
				ps=con.prepareStatement(SELECT+" where login_id=?");
				ps.setString(1, loginId);
				rs=ps.executeQuery();
				if(rs.next()) {
					loginMasterBean=constructBean(rs);
				}
			} catch (Exception e) { e.printStackTrace(); }
			finally { /*ps.close(); rs.close(); con.close();*/ }
			return loginMasterBean==null?new LoginMasterBean():loginMasterBean;
		}
	public static ArrayList<LoginMasterBean> getLoginMasterList(String subQry)  {
		
		ArrayList<LoginMasterBean> loginList=new ArrayList<LoginMasterBean>();
		Connection con=null;
		Statement stmt = null;
		
		ResultSet rs = null;
			try {
				con=DBConnection.getConnection();
				stmt=con.createStatement();
				rs=stmt.executeQuery(SELECT + subQry);
				while(rs.next()) {
					loginList.add(constructBean(rs));
				}
			} catch (Exception e) { e.printStackTrace(); }
			finally { /*ps.close(); rs.close(); con.close();*/ }
			return loginList;
		}
	private static LoginMasterBean constructBean( ResultSet rs) throws SQLException{
		LoginMasterBean loginMasterBean=new LoginMasterBean();
		loginMasterBean.setId(rs.getInt(1));
		loginMasterBean.setLoginId(rs.getString(2));
		loginMasterBean.setPassword(rs.getString(3));
		loginMasterBean.setEmail(rs.getString(4));
		loginMasterBean.setActive(rs.getBoolean(5));
		loginMasterBean.setStatus(rs.getString(6));
		return loginMasterBean;
	}
}
