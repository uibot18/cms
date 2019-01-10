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
import com.cms.employee.bean.UserMasterDO;

public class UserMasterDAO {

private static final String SELECT="select   emp_id, first_name, last_name, email, gender, marital_status, blood_group, mobile, date_of_birth, department, role, profile, reporting_manager, subordinates, pan_card, bank_detials, epf_no, street, city, state, zipcode, country from user_master ";
private static final String INSERT="insert into user_master( emp_id, first_name, last_name, email, gender, marital_status, blood_group, mobile, date_of_birth, department, role, profile, reporting_manager, subordinates, pan_card, bank_detials, epf_no, street, city, state, zipcode, country)  values(  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
private static final String UPDATE="update  user_master set  first_name=?, last_name=?, email=?, gender=?, marital_status=?, blood_group=?, mobile=?, date_of_birth=?, department=?, role=?, profile=?, reporting_manager=?, subordinates=?, pan_card=?, bank_detials=?, epf_no=?, street=?, city=?, state=?, zipcode=?, country=? WHERE emp_id=? ";

public static int insert(Connection preCon, UserMasterDO dto) {
int insertId=0;
Connection con=null;
PreparedStatement stmt=null;
ResultSet rs=null;
try {
con=preCon==null?DBConnection.getConnection():preCon;
stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
int i=1;
stmt.setInt(i++, dto.getEmpId() );
stmt.setString(i++, dto.getFirstName() );
stmt.setString(i++, dto.getLastName() );
stmt.setString(i++, dto.getEmail() );
stmt.setString(i++, dto.getGender() );
stmt.setString(i++, dto.getMaritalStatus() );
stmt.setInt(i++, dto.getBloodGroup() );
stmt.setString(i++, dto.getMobile() );
stmt.setString(i++, dto.getDateOfBirth() );
stmt.setInt(i++, dto.getDepartment() );
stmt.setInt(i++, dto.getRole() );
stmt.setInt(i++, dto.getProfile() );
stmt.setInt(i++, dto.getReportingManager() );
stmt.setString(i++, dto.getSubordinates() );
stmt.setString(i++, dto.getPanCard() );
stmt.setString(i++, dto.getBankDetials() );
stmt.setString(i++, dto.getEpfNo() );
stmt.setString(i++, dto.getStreet() );
stmt.setString(i++, dto.getCity() );
stmt.setInt(i++, dto.getState() );
stmt.setString(i++, dto.getZipcode() );
stmt.setInt(i++, dto.getCountry() );
stmt.execute();
rs=stmt.getGeneratedKeys();
if(rs.next()) { insertId=rs.getInt(1); }
} catch (Exception e) { e.printStackTrace(); }
finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
return insertId;
}

public static boolean update(Connection preCon, UserMasterDO dto) {
Connection con=null;
PreparedStatement stmt=null;
int i=1;
try {
con=preCon==null?DBConnection.getConnection():preCon;
stmt=con.prepareStatement(UPDATE);
stmt.setString(i++, dto.getFirstName());
stmt.setString(i++, dto.getLastName());
stmt.setString(i++, dto.getEmail());
stmt.setString(i++, dto.getGender());
stmt.setString(i++, dto.getMaritalStatus());
stmt.setInt(i++, dto.getBloodGroup());
stmt.setString(i++, dto.getMobile());
stmt.setString(i++, dto.getDateOfBirth());
stmt.setInt(i++, dto.getDepartment());
stmt.setInt(i++, dto.getRole());
stmt.setInt(i++, dto.getProfile());
stmt.setInt(i++, dto.getReportingManager());
stmt.setString(i++, dto.getSubordinates());
stmt.setString(i++, dto.getPanCard());
stmt.setString(i++, dto.getBankDetials());
stmt.setString(i++, dto.getEpfNo());
stmt.setString(i++, dto.getStreet());
stmt.setString(i++, dto.getCity());
stmt.setInt(i++, dto.getState());
stmt.setString(i++, dto.getZipcode());
stmt.setInt(i++, dto.getCountry());
stmt.setInt(i++,dto.getEmpId());
int rowAffect=stmt.executeUpdate();
if(rowAffect!=0) { return true; }
} catch (Exception e) { e.printStackTrace(); } 
finally { DBUtil.close( stmt, preCon==null?con:null  ); }
return false;
}

public static List<UserMasterDO> getUserMaster(Connection preCon, boolean needChild) {
String query=SELECT;
List<UserMasterDO> dtoList =getUserMaster(preCon, query, needChild);
if( dtoList==null ) { dtoList=new ArrayList<UserMasterDO>(); }
return dtoList;
}

public static UserMasterDO getUserMasterByEmpId(Connection preCon, int empId, boolean needChild) {
String query=SELECT+" WHERE emp_id="+empId;
List<UserMasterDO> dtoList =getUserMaster(preCon, query, needChild);
if( dtoList==null ) { dtoList=new ArrayList<UserMasterDO>(); }
return dtoList.size()>0?dtoList.get(0): new UserMasterDO();
}

private  static List<UserMasterDO> getUserMaster(Connection preCon, String query, boolean needChild) {
List<UserMasterDO> dtos=new ArrayList<UserMasterDO>();
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

private static UserMasterDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
UserMasterDO dto=new UserMasterDO();
try {
int i=1;
dto.setEmpId(rs.getInt(i++));
dto.setFirstName(rs.getString(i++));
dto.setLastName(rs.getString(i++));
dto.setEmail(rs.getString(i++));
dto.setGender(rs.getString(i++));
dto.setMaritalStatus(rs.getString(i++));
dto.setBloodGroup(rs.getInt(i++));
dto.setMobile(rs.getString(i++));
dto.setDateOfBirth(rs.getString(i++));
dto.setDepartment(rs.getInt(i++));
dto.setRole(rs.getInt(i++));
dto.setProfile(rs.getInt(i++));
dto.setReportingManager(rs.getInt(i++));
dto.setSubordinates(rs.getString(i++));
dto.setPanCard(rs.getString(i++));
dto.setBankDetials(rs.getString(i++));
dto.setEpfNo(rs.getString(i++));
dto.setStreet(rs.getString(i++));
dto.setCity(rs.getString(i++));
dto.setState(rs.getInt(i++));
dto.setZipcode(rs.getString(i++));
dto.setCountry(rs.getInt(i++));
} catch (SQLException e) { e.printStackTrace(); }
finally { }
return dto;
} 


}