package com.cms.task.dao;

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
import com.cms.task.bean.TaskMasterDO;

public class TaskMasterDAO {

	private static final String SELECT="select   task_id, process_child_id, task_particulars, task_config_id, process_id, exe_order, assigned_to, task_date, task_date_from, task_date_to, task_status, pause_time_start, pause_time_end, pause_count, task_description, ref_type, ref_id, bool_delete_status, created_user, created_date, update_user, update_date from task_master ";
	private static final String INSERT="insert into task_master( task_id, process_child_id, task_particulars, task_config_id, process_id, exe_order, assigned_to, task_date, task_date_from, task_date_to, task_status, pause_time_start, pause_time_end, pause_count, task_description, ref_type, ref_id, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() ) ";
	private static final String UPDATE="update  task_master set  process_child_id=?, task_particulars=?, task_config_id=?, process_id=?, exe_order=?, assigned_to=?, task_date=?, task_date_from=?, task_date_to=?, task_status=?, pause_time_start=?, pause_time_end=?, pause_count=?, task_description=?, ref_type=?, ref_id=?, bool_delete_status=?, update_user=?, update_date=NOW() WHERE task_id=? ";
	private static final String UPDATE_MINIMAL="update  task_master set task_particulars=?, assigned_to=?, task_date=?, task_date_from=?, task_date_to=?, update_user=?, update_date=NOW() WHERE task_id=? ";
	public static int insert(Connection preCon, TaskMasterDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getTaskId() );
			stmt.setInt(i++, dto.getProcessChildId() );
			stmt.setString(i++, dto.getTaskParticulars() );
			stmt.setInt(i++, dto.getTaskConfigId() );
			stmt.setInt(i++, dto.getProcessId() );
			stmt.setInt(i++, dto.getExeOrder() );
			stmt.setInt(i++, dto.getAssignedTo() );
			stmt.setString(i++, dto.getTaskDate() );
			stmt.setString(i++, dto.getTaskDateFrom() );
			stmt.setString(i++, dto.getTaskDateTo() );
			stmt.setString(i++, dto.getTaskStatus() );
			stmt.setString(i++, dto.getPauseTimeStart() );
			stmt.setString(i++, dto.getPauseTimeEnd() );
			stmt.setInt(i++, dto.getPauseCount() );
			stmt.setString(i++, dto.getTaskDescription() );
			stmt.setString(i++, dto.getRefType() );
			stmt.setInt(i++, dto.getRefId() );
			stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
			stmt.setString(i++, dto.getCreatedUser() );
			stmt.setString(i++, dto.getCreatedDate() );
			stmt.setString(i++, dto.getUpdateUser() );
			stmt.setString(i++, dto.getUpdateDate() );
			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) { insertId=rs.getInt(1); }
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
		return insertId;
	}

	public static boolean insert(Connection preCon, List<TaskMasterDO> taskList, int processChildId) {
		
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			
			for (TaskMasterDO dto : taskList) {
				stmt.setInt(i++, dto.getTaskId() );
				stmt.setInt(i++, dto.getProcessChildId() );
				stmt.setString(i++, dto.getTaskParticulars() );
				stmt.setInt(i++, dto.getTaskConfigId() );
				stmt.setInt(i++, dto.getProcessId() );
				stmt.setInt(i++, dto.getExeOrder() );
				stmt.setInt(i++, dto.getAssignedTo() );
				stmt.setString(i++, dto.getTaskDate() );
				stmt.setString(i++, dto.getTaskDateFrom() );
				stmt.setString(i++, dto.getTaskDateTo() );
				stmt.setString(i++, dto.getTaskStatus() );
				stmt.setString(i++, dto.getPauseTimeStart() );
				stmt.setString(i++, dto.getPauseTimeEnd() );
				stmt.setInt(i++, dto.getPauseCount() );
				stmt.setString(i++, dto.getTaskDescription() );
				stmt.setString(i++, dto.getRefType() );
				stmt.setInt(i++, dto.getRefId() );
				stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
				stmt.setString(i++, dto.getCreatedUser() );
				stmt.setString(i++, dto.getCreatedDate() );
				stmt.setString(i++, dto.getUpdateUser() );
				stmt.setString(i++, dto.getUpdateDate() );
				stmt.addBatch();
			}
			stmt.executeBatch();
			return true;
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
		return false;
	} 


	public static boolean update(Connection preCon, TaskMasterDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setInt(i++,dto.getProcessChildId());
			stmt.setString(i++,dto.getTaskParticulars());
			stmt.setInt(i++,dto.getTaskConfigId());
			stmt.setInt(i++,dto.getProcessId());
			stmt.setInt(i++,dto.getExeOrder());
			stmt.setInt(i++,dto.getAssignedTo());
			stmt.setString(i++,dto.getTaskDate());
			stmt.setString(i++,dto.getTaskDateFrom());
			stmt.setString(i++,dto.getTaskDateTo());
			stmt.setString(i++,dto.getTaskStatus());
			stmt.setString(i++,dto.getPauseTimeStart());
			stmt.setString(i++,dto.getPauseTimeEnd());
			stmt.setInt(i++,dto.getPauseCount());
			stmt.setString(i++,dto.getTaskDescription());
			stmt.setString(i++,dto.getRefType());
			stmt.setInt(i++,dto.getRefId());
			stmt.setBoolean(i++,dto.getBoolDeleteStatus());
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setInt(i++,dto.getTaskId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}
//	task_particulars=?, assigned_to=?, task_date=?, task_date_from=?, task_date_to=?, task_description=?, update_user=?, update_date=NOW() WHERE task_id=?
	public static boolean updateMinimal(Connection preCon, TaskMasterDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE_MINIMAL);
			stmt.setString(i++, dto.getTaskParticulars());
			stmt.setInt(i++, dto.getAssignedTo());
			stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getTaskDate()+" 00:00:00", true, true) );
			stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getTaskDateFrom()+" 00:00:00", true, true) );
			stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getTaskDateTo()+" 00:00:00", true, true) );
			stmt.setString(i++, dto.getUpdateUser());
			stmt.setInt(i++, dto.getTaskId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static List<TaskMasterDO> getTaskMaster(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<TaskMasterDO> dtoList =getTaskMaster(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskMasterDO>(); }
		return dtoList;
	}
	
	public static List<TaskMasterDO> getTaskMasterByProcessChildId(Connection preCon, int processChildId, boolean needChild) {
		String query=SELECT+" WHERE process_child_id="+processChildId;
		List<TaskMasterDO> dtoList =getTaskMaster(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskMasterDO>(); }
		return dtoList;
	}

	public static TaskMasterDO getTaskMasterByTaskId(Connection preCon, int taskId, boolean needChild) {
		String query=SELECT+" WHERE task_id="+taskId;
		List<TaskMasterDO> dtoList =getTaskMaster(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskMasterDO>(); }
		return dtoList.size()>0?dtoList.get(0): new TaskMasterDO();
	}

	private  static List<TaskMasterDO> getTaskMaster(Connection preCon, String query, boolean needChild) {
		List<TaskMasterDO> dtos=new ArrayList<TaskMasterDO>();
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

	private static TaskMasterDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		TaskMasterDO dto=new TaskMasterDO();
		try {
			int i=1;
			dto.setTaskId(rs.getInt(i++));
			dto.setProcessChildId(rs.getInt(i++));
			dto.setTaskParticulars(rs.getString(i++));
			dto.setTaskConfigId(rs.getInt(i++));
			dto.setProcessId(rs.getInt(i++));
			dto.setExeOrder(rs.getInt(i++));
			dto.setAssignedTo(rs.getInt(i++));
			dto.setTaskDate(AppDateUtil.convertToAppDate(rs.getString(i++), false, true));
			dto.setTaskDateFrom(AppDateUtil.convertToAppDate(rs.getString(i++), false, true));
			dto.setTaskDateTo(AppDateUtil.convertToAppDate(rs.getString(i++), false, true));
			dto.setTaskStatus(rs.getString(i++));
			dto.setPauseTimeStart(rs.getString(i++));
			dto.setPauseTimeEnd(rs.getString(i++));
			dto.setPauseCount(rs.getInt(i++));
			dto.setTaskDescription(rs.getString(i++));
			dto.setRefType(rs.getString(i++));
			dto.setRefId(rs.getInt(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate(AppDateUtil.convertToAppDate(rs.getString(i++), true, true));
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	}



}