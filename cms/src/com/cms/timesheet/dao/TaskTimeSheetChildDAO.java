package com.cms.timesheet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.application.util.AppDateUtil;
import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.timesheet.bean.TaskTimeSheetChildDO;

public class TaskTimeSheetChildDAO {

	private static final String SELECT="select   time_sheet_child_id, time_sheet_id, start_time, end_time, ref_type, particulars_id, comments, bool_delete_status, created_user, created_date, update_user, update_date from task_time_sheet_child ";
	private static final String INSERT="insert into task_time_sheet_child( time_sheet_child_id, time_sheet_id, start_time, end_time, ref_type, particulars_id, comments, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() ) ";
	private static final String UPDATE="update  task_time_sheet_child set  time_sheet_id=?, start_time=?, end_time=?, ref_type=?, particulars_id=?, comments=?, bool_delete_status=?, update_user=?, update_date=NOW() WHERE time_sheet_child_id=? ";
	private static final String DELETE_BY_TIME_SHEET_ID="delete from task_time_sheet_child WHERE  time_sheet_id=? ";

	public static int insert(Connection preCon, TaskTimeSheetChildDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getTimeSheetChildId() );
			stmt.setInt(i++, dto.getTimeSheetId() );
			stmt.setString(i++, dto.getStartTime() );
			stmt.setString(i++, dto.getEndTime() );
			stmt.setInt(i++, dto.getRefType() );
			stmt.setInt(i++, dto.getParticularsId() );
			stmt.setString(i++, dto.getComments() );
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

	public static boolean insert(Connection preCon, List<TaskTimeSheetChildDO> dtos, int timeSheetId) {
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			for (TaskTimeSheetChildDO dto : dtos) {
				int i=1;
				stmt.setInt(i++, dto.getTimeSheetChildId() );
				stmt.setInt(i++, timeSheetId );
				stmt.setString(i++, AppDateUtil.convertToDBDate( dto.getStartTime(), true, true ) );
				stmt.setString(i++, AppDateUtil.convertToDBDate( dto.getEndTime(), true, true ) );
				stmt.setInt(i++, dto.getRefType() );
				stmt.setInt(i++, dto.getParticularsId() );
				stmt.setString(i++, dto.getComments() );
				stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
				stmt.setString(i++, dto.getCreatedUser() );
				stmt.setString(i++, dto.getUpdateUser() );
				stmt.addBatch();
			}
			stmt.executeBatch();
			return true;
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
		return false;
	} 

	public static boolean update(Connection preCon, TaskTimeSheetChildDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setInt(i++,dto.getTimeSheetId());
			stmt.setString(i++,dto.getStartTime());
			stmt.setString(i++,dto.getEndTime());
			stmt.setInt(i++,dto.getRefType());
			stmt.setInt(i++,dto.getParticularsId());
			stmt.setString(i++,dto.getComments());
			stmt.setBoolean(i++,dto.getBoolDeleteStatus());
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setInt(i++,dto.getTimeSheetChildId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static List<TaskTimeSheetChildDO> getTaskTimeSheetChild(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<TaskTimeSheetChildDO> dtoList =getTaskTimeSheetChild(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskTimeSheetChildDO>(); }
		return dtoList;
	}
	public static List<TaskTimeSheetChildDO> getTaskTimeSheetChildsByTimeSheetId(Connection preCon, int timeSheetId,  boolean needChild) {
		String query=SELECT +" WHERE time_sheet_id="+timeSheetId;
		List<TaskTimeSheetChildDO> dtoList =getTaskTimeSheetChild(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskTimeSheetChildDO>(); }
		return dtoList;
	}

	public static TaskTimeSheetChildDO getTaskTimeSheetChildByTimeSheetChildId(Connection preCon, int timeSheetChildId, boolean needChild) {
		String query=SELECT+" WHERE time_sheet_child_id="+timeSheetChildId;
		List<TaskTimeSheetChildDO> dtoList =getTaskTimeSheetChild(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskTimeSheetChildDO>(); }
		return dtoList.size()>0?dtoList.get(0): new TaskTimeSheetChildDO();
	}

	private  static List<TaskTimeSheetChildDO> getTaskTimeSheetChild(Connection preCon, String query, boolean needChild) {
		List<TaskTimeSheetChildDO> dtos=new ArrayList<TaskTimeSheetChildDO>();
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

	private static TaskTimeSheetChildDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		TaskTimeSheetChildDO dto=new TaskTimeSheetChildDO();
		try {
			int i=1;
			dto.setTimeSheetChildId(rs.getInt(i++));
			dto.setTimeSheetId(rs.getInt(i++));
			dto.setStartTime(rs.getString(i++));
			dto.setEndTime(rs.getString(i++));
			dto.setRefType(rs.getInt(i++));
			dto.setParticularsId(rs.getInt(i++));
			dto.setComments(rs.getString(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate(AppDateUtil.convertToAppDate(rs.getString(i++), true, true));
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate(AppDateUtil.convertToAppDate(rs.getString(i++), true, true));

		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	}

	public static boolean delete(Connection preCon, int timeSheetId) {
		Connection con=null;
		PreparedStatement stmt=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(DELETE_BY_TIME_SHEET_ID);
			stmt.setInt(1, timeSheetId);

			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }

		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

}