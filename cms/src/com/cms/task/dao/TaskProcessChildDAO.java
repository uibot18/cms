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
import com.cms.task.bean.TaskProcessChildDO;

public class TaskProcessChildDAO {

	private static final String SELECT="select   process_child_id, process_master_id, service_id, package_id, process_id, process_starts_on, process_ends_on, bool_override, bool_delete_status, created_user, created_date, update_user, update_date from task_process_child ";
	private static final String INSERT="insert into task_process_child( process_child_id, process_master_id, service_id, package_id, process_id, process_starts_on, process_ends_on, bool_override, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() ) ";
	private static final String UPDATE="update  task_process_child set  process_master_id=?, service_id=?, package_id=?, process_id=?, process_starts_on=?, process_ends_on=?, bool_override=?, bool_delete_status=?, update_user=?, update_date=NOW() WHERE process_child_id=? ";
	private static final String DELETE_BY_PROCESS_MASTER="delete from task_process_child WHERE process_master_id=?";
	public static int insert(Connection preCon, TaskProcessChildDO dto, int processMasterId) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getProcessChildId() );
			stmt.setInt(i++, processMasterId );
			stmt.setInt(i++, dto.getServiceId() );
			stmt.setInt(i++, dto.getPackageId() );
			stmt.setInt(i++, dto.getProcessId() );
			stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getProcessStartsOn(), false, true) );
			stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getProcessEndsOn(), false, true) );
			stmt.setBoolean(i++, dto.isBoolOverride() );
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
	
	public static boolean insert(Connection preCon, List<TaskProcessChildDO> childList, int processMasterId) {
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			for (TaskProcessChildDO dto : childList) {
				int processChildId=insert(con, dto, processMasterId);
				if(processChildId!=0) {
					/*List<TaskMasterDO> taskList=dto.getTaskList();
					if(taskList.size()>0) {
						if(!TaskMasterDAO.insert(con, taskList, processChildId)) {
							System.out.println("Task insertion failed.."); return false; 
						}
					}*/
				}else { System.out.println("Process Child insertion failed.."); return false; }
			}
			return true;
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
		return false;
	} 

	public static boolean update(Connection preCon, TaskProcessChildDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setInt(i++,dto.getProcessMasterId());
			stmt.setInt(i++,dto.getServiceId());
			stmt.setInt(i++,dto.getPackageId());
			stmt.setInt(i++,dto.getProcessId());
			stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getProcessStartsOn(), false, true) );
			stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getProcessEndsOn(), false, true) );
			stmt.setBoolean(i++,dto.isBoolOverride());
			stmt.setBoolean(i++,dto.getBoolDeleteStatus());
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setInt(i++,dto.getProcessChildId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}
	
	public static boolean update(Connection con, List<TaskProcessChildDO> childList, int processMasterId) {
		
		if(childList!=null && childList.size()>0) {
			for (TaskProcessChildDO dto : childList) {
				if(dto.getProcessChildId()==0) {
					if(insert(con, dto, processMasterId)==0) { System.out.println("failed to inser"); return false; }
				}else {
					if(!update(con, dto)) { System.out.println("failed to inser"); return false; }
				}
			}
			return true;
		}else {
			return false;
		}
	}
	
	public static List<TaskProcessChildDO> getTaskProcessChild(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<TaskProcessChildDO> dtoList =getTaskProcessChild(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskProcessChildDO>(); }
		return dtoList;
	}
	
	public static List<TaskProcessChildDO> getTaskProcessChildList(Connection preCon, int processMasterId,  boolean needChild) {
		String query=SELECT +" WHERE process_master_id="+processMasterId;
		List<TaskProcessChildDO> dtoList =getTaskProcessChild(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskProcessChildDO>(); }
		return dtoList;
	}

	public static TaskProcessChildDO getTaskProcessChildByProcessChildId(Connection preCon, int processChildId, boolean needChild) {
		String query=SELECT+" WHERE process_child_id="+processChildId;
		List<TaskProcessChildDO> dtoList =getTaskProcessChild(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskProcessChildDO>(); }
		return dtoList.size()>0?dtoList.get(0): new TaskProcessChildDO();
	}

	private  static List<TaskProcessChildDO> getTaskProcessChild(Connection preCon, String query, boolean needChild) {
		List<TaskProcessChildDO> dtos=new ArrayList<TaskProcessChildDO>();
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

	private static TaskProcessChildDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		TaskProcessChildDO dto=new TaskProcessChildDO();
		try {
			int i=1;
			dto.setProcessChildId(rs.getInt(i++));
			dto.setProcessMasterId(rs.getInt(i++));
			dto.setServiceId(rs.getInt(i++));
			dto.setPackageId(rs.getInt(i++));
			dto.setProcessId(rs.getInt(i++));
			dto.setProcessStartsOn( AppDateUtil.convertToAppDate(rs.getString(i++), false, true) );
			dto.setProcessEndsOn( AppDateUtil.convertToAppDate(rs.getString(i++), false, true) );
			dto.setBoolOverride(rs.getBoolean(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	}

	public static boolean delete(Connection preCon, int processMasterId) {
		Connection con=null;
		PreparedStatement stmt=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(DELETE_BY_PROCESS_MASTER);
			
			stmt.setInt(1, processMasterId);
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	

	


}