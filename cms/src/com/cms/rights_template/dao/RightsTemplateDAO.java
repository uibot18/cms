package com.cms.rights_template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.rights_template.bean.RightsTemplateDO;

public class RightsTemplateDAO {

	private static final String SELECT="select   rights_template_id, rights_template_name, menu_ids, rights_ids, bool_delete_status, created_user, created_date, update_user, update_date from rights_template ";
	private static final String INSERT="insert into rights_template( rights_template_id, rights_template_name, menu_ids, rights_ids, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() ) ";
	private static final String UPDATE="update  rights_template set  rights_template_name=?, menu_ids=?, rights_ids=?, bool_delete_status=?,  update_user=?, update_date=NOW() WHERE rights_template_id=? ";
	private static final String DELETE_UPDATE="update  rights_template set  bool_delete_status=?, update_user=?, update_date=NOW() WHERE rights_template_id=? ";
	public static int insert(Connection preCon, RightsTemplateDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getRightsTemplateId() );
			stmt.setString(i++, dto.getRightsTemplateName() );
			stmt.setString(i++, dto.getMenuIds() );
			stmt.setString(i++, dto.getRightsIds() );
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

	public static boolean update(Connection preCon, RightsTemplateDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setString(i++,dto.getRightsTemplateName());
			stmt.setString(i++,dto.getMenuIds());
			stmt.setString(i++,dto.getRightsIds());
			stmt.setBoolean(i++,dto.getBoolDeleteStatus());
			/*stmt.setString(i++,dto.getCreatedUser());
stmt.setString(i++,dto.getCreatedDate());*/
			stmt.setString(i++,dto.getUpdateUser());
			/*stmt.setString(i++,dto.getUpdateDate());*/
			stmt.setInt(i++,dto.getRightsTemplateId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static List<RightsTemplateDO> getRightsTemplate(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<RightsTemplateDO> dtoList =getRightsTemplate(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<RightsTemplateDO>(); }
		return dtoList;
	}

	public static RightsTemplateDO getRightsTemplateByRightsTemplateId(Connection preCon, int rightsTemplateId, boolean needChild) {
		String query=SELECT+" WHERE rights_template_id="+rightsTemplateId;
		List<RightsTemplateDO> dtoList =getRightsTemplate(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<RightsTemplateDO>(); }
		return dtoList.size()>0?dtoList.get(0): new RightsTemplateDO();
	}

	private  static List<RightsTemplateDO> getRightsTemplate(Connection preCon, String query, boolean needChild) {
		List<RightsTemplateDO> dtos=new ArrayList<RightsTemplateDO>();
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

	private static RightsTemplateDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		RightsTemplateDO dto=new RightsTemplateDO();
		try {
			int i=1;
			dto.setRightsTemplateId(rs.getInt(i++));
			dto.setRightsTemplateName(rs.getString(i++));
			dto.setMenuIds(rs.getString(i++));
			dto.setRightsIds(rs.getString(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate(rs.getString(i++));
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate(rs.getString(i++));
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	} 


	public static String duplicatecheck(Connection preCon, RightsTemplateDO rightsTemplateDO){
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		String query="select   rights_template_id, rights_template_name from rights_template where 0=0 and rights_template_name ='"+rightsTemplateDO.getRightsTemplateName()+"' and bool_delete_status=0 AND rights_template_id not in("+rightsTemplateDO.getRightsTemplateId()+")"; 
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			if(rs.next()) {
				return " Rights Template Name \""+rightsTemplateDO.getRightsTemplateName()+"\" already exisits.";
			}

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( stmt, preCon==null?con:null, rs  ); }
		return "";
	}

	public static boolean deleteupdate(Connection preCon, RightsTemplateDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(DELETE_UPDATE);
			stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setInt(i++,dto.getRightsTemplateId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static Map<String, String> getRightsNameMap(Connection preCon, String rightsIds) {
		Map<String, String> rightsMap=new HashMap<String, String>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		String query="select rights_template_id, rights_template_name from rights_template where bool_delete_status=0 "; 
		if(!rightsIds.isEmpty()) {
			 query +=" AND rights_template_id in( "+rightsIds+" )";
		}
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()) {
				rightsMap.put(""+rs.getInt(1), ""+rs.getString(2) );
			}

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( stmt, preCon==null?con:null, rs  ); }
		return rightsMap;
	}
}