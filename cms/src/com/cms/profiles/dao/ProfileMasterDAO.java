package com.cms.profiles.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.profiles.bean.ProfileMasterDO;

public class ProfileMasterDAO {

private static final String SELECT="select   profile_id, profile_name, profile_description, profile_rights, created_by, created_on, updated_by, updated_on from profile_master ";
private static final String INSERT="insert into profile_master( profile_id, profile_name, profile_description, profile_rights, created_by, created_on, updated_by, updated_on)  values(  ?, ?, ?, ?, ?, ?, ?, ? ) ";
private static final String UPDATE="update  profile_master set  profile_name=?, profile_description=?, profile_rights=?, created_by=?, created_on=?, updated_by=?, updated_on=? WHERE profile_id=? ";

public static int insert(Connection preCon, ProfileMasterDO dto) {
int insertId=0;
Connection con=null;
PreparedStatement stmt=null;
ResultSet rs=null;
try {
con=preCon==null?DBConnection.getConnection():preCon;
stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
int i=1;
stmt.setInt(i++, dto.getProfileId() );
stmt.setString(i++, dto.getProfileName() );
stmt.setString(i++, dto.getProfileDescription() );
stmt.setString(i++, dto.getProfileRights() );
stmt.setString(i++, dto.getCreatedBy() );
stmt.setString(i++, dto.getCreatedOn() );
stmt.setString(i++, dto.getUpdatedBy() );
stmt.setString(i++, dto.getUpdatedOn() );
stmt.execute();
rs=stmt.getGeneratedKeys();
if(rs.next()) { insertId=rs.getInt(1); }
} catch (Exception e) { e.printStackTrace(); }
finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
return insertId;
}

public static boolean update(Connection preCon, ProfileMasterDO dto) {
Connection con=null;
PreparedStatement stmt=null;
int i=1;
try {
con=preCon==null?DBConnection.getConnection():preCon;
stmt=con.prepareStatement(UPDATE);
stmt.setString(i++, dto.getProfileName());
stmt.setString(i++, dto.getProfileDescription());
stmt.setString(i++, dto.getProfileRights());
stmt.setString(i++, dto.getCreatedBy());
stmt.setString(i++, dto.getCreatedOn());
stmt.setString(i++, dto.getUpdatedBy());
stmt.setString(i++, dto.getUpdatedOn());
stmt.setInt(i++,dto.getProfileId());
int rowAffect=stmt.executeUpdate();
if(rowAffect!=0) { return true; }
} catch (Exception e) { e.printStackTrace(); } 
finally { DBUtil.close( stmt, preCon==null?con:null  ); }
return false;
}

public static List<ProfileMasterDO> getProfileMaster(Connection preCon, boolean needChild) {
String query=SELECT;
List<ProfileMasterDO> dtoList =getProfileMaster(preCon, query, needChild);
if( dtoList==null ) { dtoList=new ArrayList(); }
return dtoList;
}

public static ProfileMasterDO getProfileMasterByProfileId(Connection preCon, int profileId, boolean needChild) {
String query=SELECT+" WHERE profile_id="+profileId;
List<ProfileMasterDO> dtoList =getProfileMaster(preCon, query, needChild);
if( dtoList==null ) { dtoList=new ArrayList<ProfileMasterDO>(); }
return dtoList.size()>0?dtoList.get(0): new ProfileMasterDO();
}

private  static List<ProfileMasterDO> getProfileMaster(Connection preCon, String query, boolean needChild) {
List<ProfileMasterDO> dtos=new ArrayList<ProfileMasterDO>();
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

private static ProfileMasterDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
ProfileMasterDO dto=new ProfileMasterDO();
try {
int i=1;
dto.setProfileId(rs.getInt(i++));
dto.setProfileName(rs.getString(i++));
dto.setProfileDescription(rs.getString(i++));
dto.setProfileRights(rs.getString(i++));
dto.setCreatedBy(rs.getString(i++));
dto.setCreatedOn(rs.getString(i++));
dto.setUpdatedBy(rs.getString(i++));
dto.setUpdatedOn(rs.getString(i++));
} catch (SQLException e) { e.printStackTrace(); }
finally { }
return dto;
} 


}