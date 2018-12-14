package com.cms.rights.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.rights.bean.RightsMasterDO;

public class RightsMasterDAO {

private static final String SELECT="select   rights_master_id, rights_id, rights_group_name, rights_name, bool_delete_status, created_user, created_date, update_user, update_date from rights_master ";
private static final String INSERT="insert into rights_master( rights_master_id, rights_id, rights_group_name, rights_name, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() ) ";
private static final String UPDATE="update  rights_master set  rights_id=?, rights_group_name=?, rights_name=?, bool_delete_status=?,  update_user=?, update_date=NOW() WHERE rights_master_id=? ";
private static final String DELETE_UPDATE="update  rights_master set  bool_delete_status=?, update_user=?, update_date=NOW() WHERE rights_master_id=? ";
public static int insert(Connection preCon, RightsMasterDO dto) {
int insertId=0;
Connection con=null;
PreparedStatement stmt=null;
ResultSet rs=null;
try {
con=preCon==null?DBConnection.getConnection():preCon;
stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
int i=1;
stmt.setInt(i++, dto.getRightsMasterId() );
stmt.setInt(i++, dto.getRightsId() );
stmt.setString(i++, dto.getRightsGroupName() );
stmt.setString(i++, dto.getRightsName() );
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

public static boolean update(Connection preCon, RightsMasterDO dto) {
Connection con=null;
PreparedStatement stmt=null;
int i=1;
try {
con=preCon==null?DBConnection.getConnection():preCon;
stmt=con.prepareStatement(UPDATE);
stmt.setInt(i++,dto.getRightsId());
stmt.setString(i++,dto.getRightsGroupName());
stmt.setString(i++,dto.getRightsName());
stmt.setBoolean(i++,dto.getBoolDeleteStatus());


stmt.setString(i++,dto.getUpdateUser());

stmt.setInt(i++,dto.getRightsMasterId());
int rowAffect=stmt.executeUpdate();
if(rowAffect!=0) { return true; }
} catch (Exception e) { e.printStackTrace(); } 
finally { DBUtil.close( stmt, preCon==null?con:null  ); }
return false;
}

public static List<RightsMasterDO> getRightsMaster(Connection preCon, boolean needChild) {
String query=SELECT +" where bool_delete_status=0 ";
List<RightsMasterDO> dtoList =getRightsMaster(preCon, query, needChild);
if( dtoList==null ) { dtoList=new ArrayList<RightsMasterDO>(); }
return dtoList;
}

public static RightsMasterDO getRightsMasterByRightsMasterId(Connection preCon, int rightsMasterId, boolean needChild) {
String query=SELECT+" WHERE rights_master_id="+rightsMasterId;
List<RightsMasterDO> dtoList =getRightsMaster(preCon, query, needChild);
if( dtoList==null ) { dtoList=new ArrayList<RightsMasterDO>(); }
return dtoList.size()>0?dtoList.get(0): new RightsMasterDO();
}

private  static List<RightsMasterDO> getRightsMaster(Connection preCon, String query, boolean needChild) {
List<RightsMasterDO> dtos=new ArrayList<RightsMasterDO>();
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

private static RightsMasterDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
RightsMasterDO dto=new RightsMasterDO();
try {
int i=1;
dto.setRightsMasterId(rs.getInt(i++));
dto.setRightsId(rs.getInt(i++));
dto.setRightsGroupName(rs.getString(i++));
dto.setRightsName(rs.getString(i++));
dto.setBoolDeleteStatus(rs.getBoolean(i++));
dto.setCreatedUser(rs.getString(i++));
dto.setCreatedDate(rs.getString(i++));
dto.setUpdateUser(rs.getString(i++));
dto.setUpdateDate(rs.getString(i++));
} catch (SQLException e) { e.printStackTrace(); }
finally { }
return dto;
} 

public static String duplicatecheck(Connection preCon, RightsMasterDO rightsDO){
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;

	String query="select   rights_master_id, rights_id, rights_group_name, rights_name FROM rights_master where 0=0 and rights_id ='"+rightsDO.getRightsId()+"' and bool_delete_status=0 AND rights_master_id not in("+rightsDO.getRightsMasterId()+")"; 
	try {
		con=preCon==null?DBConnection.getConnection():preCon;
		stmt=con.createStatement();
		rs=stmt.executeQuery(query);
		if(rs.next()) {
			return " Rights Id \""+rightsDO.getRightsId()+"\" already exisits.";
		}

	} catch (Exception e) { e.printStackTrace(); }
	finally { DBUtil.close( stmt, preCon==null?con:null, rs  ); }
	return "";
}

public static boolean deleteupdate(Connection preCon, RightsMasterDO dto) {
	Connection con=null;
	PreparedStatement stmt=null;
	int i=1;
	try {
		con=preCon==null?DBConnection.getConnection():preCon;
		stmt=con.prepareStatement(DELETE_UPDATE);
		stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
		stmt.setString(i++,dto.getUpdateUser());
		stmt.setInt(i++,dto.getRightsMasterId());
		int rowAffect=stmt.executeUpdate();
		if(rowAffect!=0) { return true; }
	} catch (Exception e) { e.printStackTrace(); } 
	finally { DBUtil.close( stmt, preCon==null?con:null  ); }
	return false;
}

public static Set<String> getAllRightsIdSet(Connection preCon) {
	Set<String> rightsIdSet=new HashSet<String>();
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;

	String query="select rights_master FROM rights_master where 0=0 and bool_delete_status=0 ";
	try {
		con=preCon==null?DBConnection.getConnection():preCon;
		stmt=con.createStatement();
		rs=stmt.executeQuery(query);
		while(rs.next()) {
			rightsIdSet.add( ""+rs.getInt(1) );
		}

	} catch (Exception e) { e.printStackTrace(); }
	finally { DBUtil.close( stmt, preCon==null?con:null, rs  ); }
	return rightsIdSet;
}
}