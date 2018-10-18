package com.cms.common.master.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.common.master.bean.CommonGroupMasterDO;

public class CommonGroupMasterDAO {

	private static final String SELECT="select   cmn_group_id, parent_group_id, cmn_group_name, no_of_levels, level_no, bool_delete_status, created_user, created_date, update_user, update_date from common_group_master ";
	private static final String INSERT="insert into common_group_master( cmn_group_id, parent_group_id, cmn_group_name, no_of_levels, level_no, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW()) ";
	private static final String UPDATE="update  common_group_master set  parent_group_id=?, cmn_group_name=?, no_of_levels=?, level_no=?, update_user=?, update_date=NOW() WHERE cmn_group_id=? ";

	public static int insert(Connection preCon, CommonGroupMasterDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getCmnGroupId() );
			stmt.setInt(i++, dto.getParentGroupId() );
			stmt.setString(i++, dto.getCmnGroupName() );
			stmt.setInt(i++, dto.getNoOfLevels() );
			stmt.setInt(i++, dto.getLevelNo() );
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

	public static boolean update(Connection preCon, CommonGroupMasterDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setInt(i++,dto.getParentGroupId());
			stmt.setString(i++,dto.getCmnGroupName());
			stmt.setInt(i++,dto.getNoOfLevels());
			stmt.setInt(i++,dto.getLevelNo());
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setInt(i++,dto.getCmnGroupId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static List<CommonGroupMasterDO> getCommonGroupMaster(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<CommonGroupMasterDO> dtoList =getCommonGroupMaster(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<CommonGroupMasterDO>(); }
		return dtoList;
	}

	public static CommonGroupMasterDO getCommonGroupMasterByCmnGroupId(Connection preCon, int cmnGroupId, boolean needChild) {
		String query=SELECT+" WHERE cmn_group_id="+cmnGroupId;
		List<CommonGroupMasterDO> dtoList =getCommonGroupMaster(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<CommonGroupMasterDO>(); }
		return dtoList.size()>0?dtoList.get(0): new CommonGroupMasterDO();
	}

	private  static List<CommonGroupMasterDO> getCommonGroupMaster(Connection preCon, String query, boolean needChild) {
		List<CommonGroupMasterDO> dtos=new ArrayList<CommonGroupMasterDO>();
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

	private static CommonGroupMasterDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		CommonGroupMasterDO dto=new CommonGroupMasterDO();
		try {
			int i=1;
			dto.setCmnGroupId(rs.getInt(i++));
			dto.setParentGroupId(rs.getInt(i++));
			dto.setCmnGroupName(rs.getString(i++));
			dto.setNoOfLevels(rs.getInt(i++));
			dto.setLevelNo(rs.getInt(i++));
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