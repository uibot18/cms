package com.cms.common.master.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.application.util.AppDateUtil;
import com.application.util.AppUtil;
import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.common.master.bean.CommonMasterDO;

public class CommonMasterDAO {

	private static final String SELECT="select   cmn_master_id, cmn_group_id, parent_id, cmn_master_name, level_no, bool_delete_status, created_user, created_date, update_user, update_date from common_master ";
	private static final String INSERT="insert into common_master( cmn_master_id, cmn_group_id, parent_id, cmn_master_name, level_no, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW()) ";
	private static final String UPDATE="update  common_master set  cmn_group_id=?, parent_id=?, cmn_master_name=?, level_no=?,  update_user=?, update_date=NOW() WHERE cmn_master_id=? ";
	private static final String DELETE_UPDATE="update  common_master set  bool_delete_status=?,  update_user=?, update_date=NOW() WHERE cmn_master_id=? ";
	public static int insert(Connection preCon, CommonMasterDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getCmnMasterId() );
			stmt.setInt(i++, dto.getCmnGroupId() );
			stmt.setInt(i++, dto.getParentId() );
			stmt.setString(i++, dto.getCmnMasterName() );
			stmt.setInt(i++, dto.getLevelNo() );
			stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
			stmt.setString(i++, dto.getCreatedUser() );
			stmt.setString(i++, dto.getUpdateUser() );
			System.out.println(""+stmt.toString());
			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) { insertId=rs.getInt(1); }
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
		return insertId;
	}

	public static boolean update(Connection preCon, CommonMasterDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setInt(i++,dto.getCmnGroupId());
			stmt.setInt(i++,dto.getParentId());
			stmt.setString(i++,dto.getCmnMasterName());
			stmt.setInt(i++,dto.getLevelNo());
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setInt(i++,dto.getCmnMasterId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static boolean deleteupdate(Connection preCon, CommonMasterDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(DELETE_UPDATE);
			stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setInt(i++,dto.getCmnMasterId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}
	
	public static List<CommonMasterDO> getCommonMaster(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<CommonMasterDO> dtoList =getCommonMaster(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<CommonMasterDO>(); }
		return dtoList;
	}

	public static List<CommonMasterDO> getCommonMasterBySubqry(Connection preCon, String subqry, boolean needChild) {
		String query=SELECT +" where 0=0 "+subqry;
		System.out.println("query: "+query);
		List<CommonMasterDO> dtoList =getCommonMaster(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<CommonMasterDO>(); }
		return dtoList;
	}
	public static CommonMasterDO getCommonMasterByCmnMasterId(Connection preCon, int cmnMasterId, boolean needChild) {
		String query=SELECT+" WHERE cmn_master_id="+cmnMasterId;
		List<CommonMasterDO> dtoList =getCommonMaster(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<CommonMasterDO>(); }
		return dtoList.size()>0?dtoList.get(0): new CommonMasterDO();
	}

	private  static List<CommonMasterDO> getCommonMaster(Connection preCon, String query, boolean needChild) {
		List<CommonMasterDO> dtos=new ArrayList<CommonMasterDO>();
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

	private static CommonMasterDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		CommonMasterDO dto=new CommonMasterDO();
		try {
			int i=1;
			dto.setCmnMasterId(rs.getInt(i++));
			dto.setCmnGroupId(rs.getInt(i++));
			dto.setParentId(rs.getInt(i++));
			dto.setCmnMasterName(rs.getString(i++));
			dto.setLevelNo(rs.getInt(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate(AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate(AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	} 

	public static Map<String, String> getCommonDetMapBySubQry(Connection preCon, String subQry  ){
		Map<String, String> commonMap=new HashMap<String, String>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		String query="SELECT cmn_master_id, cmn_master_name FROM common_master WHERE 0=0 "+AppUtil.getNullToEmpty(subQry);

		System.out.println("query: "+query);
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			rs=stmt.executeQuery( query );
			while(rs.next()) { 
				commonMap.put(""+rs.getInt(1), ""+rs.getString(2));
			}

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, con  ); }
		return commonMap;

	}
	
	public static Map<String, String> getCommonDataByGroupId(Connection preCon, String commonGroupIds){
		return getCommonDetMapBySubQry(preCon, " AND cmn_group_id in("+AppUtil.getNullToEmpty(commonGroupIds, "0")+")");
	}
	
	public static Map<String, String> getCommonDataByParentId(Connection preCon, String commonGroupIds){
		return getCommonDetMapBySubQry(preCon, " AND parent_id in("+AppUtil.getNullToEmpty(commonGroupIds, "0")+")");
	}

}