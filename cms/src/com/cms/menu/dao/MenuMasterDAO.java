package com.cms.menu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.application.util.AppUtil;
import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.menu.bean.MenuMasterDO;

public class MenuMasterDAO {

private static final String SELECT="select   menu_id, menu_name, menu_action, bool_delete_status, created_user, created_date, update_user, update_date from menu_master ";
private static final String INSERT="insert into menu_master( menu_id, menu_name, menu_action, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, NOW(), ?, NOW()) ";
private static final String UPDATE="update  menu_master set  menu_name=?, menu_action=?, bool_delete_status=?,  update_user=?, update_date=NOW() WHERE menu_id=? ";
private static final String DELETE_UPDATE="update  menu_master set  bool_delete_status=?, update_user=?, update_date=NOW() WHERE menu_id=? ";
public static int insert(Connection preCon, MenuMasterDO dto) {
int insertId=0;
Connection con=null;
PreparedStatement stmt=null;
ResultSet rs=null;
try {
con=preCon==null?DBConnection.getConnection():preCon;
stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
int i=1;
stmt.setInt(i++, dto.getMenuId() );
stmt.setString(i++, dto.getMenuName() );
stmt.setString(i++, dto.getMenuAction() );
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

public static boolean update(Connection preCon, MenuMasterDO dto) {
Connection con=null;
PreparedStatement stmt=null;
int i=1;
try {
con=preCon==null?DBConnection.getConnection():preCon;
stmt=con.prepareStatement(UPDATE);
stmt.setString(i++,dto.getMenuName());
stmt.setString(i++,dto.getMenuAction());
stmt.setBoolean(i++,dto.getBoolDeleteStatus());
stmt.setString(i++,dto.getUpdateUser());
stmt.setInt(i++,dto.getMenuId());
int rowAffect=stmt.executeUpdate();
if(rowAffect!=0) { return true; }
} catch (Exception e) { e.printStackTrace(); } 
finally { DBUtil.close( stmt, preCon==null?con:null  ); }
return false;
}

public static List<MenuMasterDO> getMenuMaster(Connection preCon, boolean needChild) {
String query=SELECT +" where bool_delete_status=0 ";
List<MenuMasterDO> dtoList =getMenuMaster(preCon, query, needChild);
if( dtoList==null ) { dtoList=new ArrayList<MenuMasterDO>(); }
return dtoList;
}

public static MenuMasterDO getMenuMasterByMenuId(Connection preCon, int menuId, boolean needChild) {
String query=SELECT+" WHERE menu_id="+menuId;
List<MenuMasterDO> dtoList =getMenuMaster(preCon, query, needChild);
if( dtoList==null ) { dtoList=new ArrayList<MenuMasterDO>(); }
return dtoList.size()>0?dtoList.get(0): new MenuMasterDO();
}

private  static List<MenuMasterDO> getMenuMaster(Connection preCon, String query, boolean needChild) {
List<MenuMasterDO> dtos=new ArrayList<MenuMasterDO>();
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

public static boolean deleteupdate(Connection preCon, MenuMasterDO dto) {
	Connection con=null;
	PreparedStatement stmt=null;
	int i=1;
	try {
		con=preCon==null?DBConnection.getConnection():preCon;
		stmt=con.prepareStatement(DELETE_UPDATE);
		stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
		stmt.setString(i++,dto.getUpdateUser());
		stmt.setInt(i++,dto.getMenuId());
		int rowAffect=stmt.executeUpdate();
		if(rowAffect!=0) { return true; }
	} catch (Exception e) { e.printStackTrace(); } 
	finally { DBUtil.close( stmt, preCon==null?con:null  ); }
	return false;
}
private static MenuMasterDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
MenuMasterDO dto=new MenuMasterDO();
try {
int i=1;
dto.setMenuId(rs.getInt(i++));
dto.setMenuName(rs.getString(i++));
dto.setMenuAction(rs.getString(i++));
dto.setBoolDeleteStatus(rs.getBoolean(i++));
dto.setCreatedUser(rs.getString(i++));
dto.setCreatedDate(rs.getString(i++));
dto.setUpdateUser(rs.getString(i++));
dto.setUpdateDate(rs.getString(i++));
} catch (SQLException e) { e.printStackTrace(); }
finally { }
return dto;
} 


public static Map<String, String> loadParentMenuMap(Connection preCon, String menuIds){
	Map<String, String> parentMap=new HashMap<String, String>();
	menuIds=AppUtil.getNullToEmpty(menuIds,"0");
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;

	String query="select   menu_id, menu_name FROM menu_master where 0=0 and bool_delete_status=0 ";
	if(!menuIds.equals("0")) { query+=" AND menu_id in("+menuIds+")"; }
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

public static String duplicatecheck(Connection preCon, MenuMasterDO menuDO){
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;

	String query="select   menu_id, menu_name FROM menu_master where 0=0 and menu_name ='"+menuDO.getMenuName()+"' and bool_delete_status=0	 AND menu_id not in("+menuDO.getMenuId()+")"; 
	try {
		con=preCon==null?DBConnection.getConnection():preCon;
		stmt=con.createStatement();
		rs=stmt.executeQuery(query);
		if(rs.next()) {
			return " Menu Name \""+menuDO.getMenuName()+"\" already exisits.";
		}

	} catch (Exception e) { e.printStackTrace(); }
	finally { DBUtil.close( stmt, preCon==null?con:null, rs  ); }
	return "";
}

public static Set<String> getMenuIdSet(Connection preCon) {
	Set<String> menuMasterSet=new HashSet<String>();
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;

	String query="select menu_id, menu_name FROM menu_master where 0=0 and bool_delete_status=0 ";
	try {
		con=preCon==null?DBConnection.getConnection():preCon;
		stmt=con.createStatement();
		rs=stmt.executeQuery(query);
		while(rs.next()) {
			menuMasterSet.add( ""+rs.getInt(1) );
		}

	} catch (Exception e) { e.printStackTrace(); }
	finally { DBUtil.close( stmt, preCon==null?con:null, rs  ); }
	return menuMasterSet;
}
}