package com.cms.navigation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.application.util.AppUtil;
import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.navigation.bean.MenuNavigationDO;

public class MenuNavigationDAO {

	private static final String SELECT="select   navigation_id, navigation_name, parent_navigation_id, bool_is_menu, menu_id, bool_delete_status, created_user, created_date, update_user, update_date from menu_navigation ";
	private static final String INSERT="insert into menu_navigation( navigation_id, navigation_name, parent_navigation_id, bool_is_menu, menu_id, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() ) ";
	private static final String UPDATE="update  menu_navigation set  navigation_name=?, parent_navigation_id=?, bool_is_menu=?, menu_id=?, bool_delete_status=?,  update_user=?, update_date=NOW() WHERE navigation_id=? ";
	private static final String DELETE_UPDATE="update  menu_navigation set  bool_delete_status=?, update_user=?, update_date=NOW() WHERE navigation_id=? ";

	public static int insert(Connection preCon, MenuNavigationDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getNavigationId() );
			stmt.setString(i++, dto.getNavigationName() );
			stmt.setInt(i++, dto.getParentNavigationId() );
			stmt.setBoolean(i++, dto.getBoolIsMenu() );
			stmt.setInt(i++, dto.getMenuId() );
			stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
			stmt.setString(i++, dto.getCreatedUser() );
			/*stmt.setString(i++, dto.getCreatedDate() );*/
			stmt.setString(i++, dto.getUpdateUser() );
			/*stmt.setString(i++, dto.getUpdateDate() );*/
			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) { insertId=rs.getInt(1); }
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
		return insertId;
	}

	public static boolean update(Connection preCon, MenuNavigationDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setString(i++,dto.getNavigationName());
			stmt.setInt(i++,dto.getParentNavigationId());
			stmt.setBoolean(i++,dto.getBoolIsMenu());
			stmt.setInt(i++,dto.getMenuId());
			stmt.setBoolean(i++,dto.getBoolDeleteStatus());
			/*stmt.setString(i++,dto.getCreatedUser());
stmt.setString(i++,dto.getCreatedDate());*/
			stmt.setString(i++,dto.getUpdateUser());
			/*stmt.setString(i++,dto.getUpdateDate());*/
			stmt.setInt(i++,dto.getNavigationId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static List<MenuNavigationDO> getMenuNavigation(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<MenuNavigationDO> dtoList =getMenuNavigation(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<MenuNavigationDO>(); }
		return dtoList;
	}

	public static MenuNavigationDO getMenuNavigationByNavigationId(Connection preCon, int navigationId, boolean needChild) {
		String query=SELECT+" WHERE navigation_id="+navigationId;
		List<MenuNavigationDO> dtoList =getMenuNavigation(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<MenuNavigationDO>(); }
		return dtoList.size()>0?dtoList.get(0): new MenuNavigationDO();
	}

	private  static List<MenuNavigationDO> getMenuNavigation(Connection preCon, String query, boolean needChild) {
		List<MenuNavigationDO> dtos=new ArrayList<MenuNavigationDO>();
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

	private static MenuNavigationDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		MenuNavigationDO dto=new MenuNavigationDO();
		try {
			int i=1;
			dto.setNavigationId(rs.getInt(i++));
			dto.setNavigationName(rs.getString(i++));
			dto.setParentNavigationId(rs.getInt(i++));
			dto.setBoolIsMenu(rs.getBoolean(i++));
			dto.setMenuId(rs.getInt(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate(rs.getString(i++));
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate(rs.getString(i++));
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	} 

	public static boolean deleteupdate(Connection preCon, MenuNavigationDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(DELETE_UPDATE);
			stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setInt(i++,dto.getNavigationId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static Map<String, String> loadParentnavigationMap(Connection preCon, String navIds){
		Map<String, String> parentMap=new HashMap<String, String>();
		navIds=AppUtil.getNullToEmpty(navIds,"0");
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		String query="select   navigation_id , navigation_name  FROM menu_navigation where 0=0 and bool_is_menu =0 ";
		query+=" AND navigation_id not  in("+navIds+")"; System.out.println(query);
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()) {
				parentMap.put(""+rs.getInt(1), ""+rs.getString(2));
			}

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( stmt, preCon==null?con:null, rs  ); }
		return parentMap;
	}

	public static String duplicatecheck(Connection preCon, MenuNavigationDO menunavDO){
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		String query="select   navigation_id, navigation_name FROM menu_navigation where 0=0 and navigation_name ='"+menunavDO.getNavigationName()+"' and bool_delete_status=0	 AND navigation_id not in("+menunavDO.getNavigationId()+")"; 
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			if(rs.next()) {
				return " Navigation Name \""+menunavDO.getNavigationName()+"\" already exisits.";
			}

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( stmt, preCon==null?con:null, rs  ); }
		return "";
	}
	
}