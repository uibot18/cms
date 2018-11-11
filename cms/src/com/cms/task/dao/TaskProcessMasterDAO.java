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
import com.cms.task.bean.TaskProcessChildDO;
import com.cms.task.bean.TaskProcessMasterDO;

public class TaskProcessMasterDAO {

	private static final String SELECT="select   process_master_id, task_type, sales_id, process_master_status, bool_delete_status, created_user, created_date, update_user, update_date from task_process_master ";
	private static final String INSERT="insert into task_process_master( process_master_id, task_type, sales_id, process_master_status, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() ) ";
	private static final String UPDATE="update  task_process_master set  task_type=?, sales_id=?, process_master_status=?, bool_delete_status=?, update_user=?, update_date=NOW() WHERE process_master_id=? ";

	public static int insert(Connection preCon, TaskProcessMasterDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getProcessMasterId() );
			stmt.setString(i++, dto.getTaskType() );
			stmt.setInt(i++, dto.getSalesId() );
			stmt.setString(i++, dto.getProcessMasterStatus() );
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

	public static int processInsert(Connection preCon, TaskProcessMasterDO taskProMstDO) {
		int processMasterId=0;
		Connection con=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			con.setAutoCommit(false);

			processMasterId =insert(con, taskProMstDO);
			if(processMasterId!=0) {
				List<TaskProcessChildDO> childList=taskProMstDO.getTaskProcessChildList();
				if(childList.size()>0) {
					if(!TaskProcessChildDAO.insert(con, childList, processMasterId)) {
						System.out.println("TaskProcessChild Insertion Failed"); con.rollback(); return 0;
					}
				}

			}else { System.out.println("Process Master Insertion Failed"); con.rollback(); return 0; }
			con.commit();
			generateTask(null, processMasterId);
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( preCon==null?con:null); }
		return processMasterId;
	}

	public static boolean update(Connection preCon, TaskProcessMasterDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setString(i++,dto.getTaskType());
			stmt.setInt(i++,dto.getSalesId());
			stmt.setString(i++,dto.getProcessMasterStatus());
			stmt.setBoolean(i++,dto.getBoolDeleteStatus());
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setInt(i++,dto.getProcessMasterId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static boolean processUpdate(Connection preCon, TaskProcessMasterDO taskProMstDO) {
		Connection con=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			con.setAutoCommit(false);
			System.out.println("Test====1");
			if(update(con, taskProMstDO)) {
				System.out.println("Test====2");
				TaskProcessChildDAO.delete(con, taskProMstDO.getProcessMasterId());
				if(!TaskProcessChildDAO.insert(con, taskProMstDO.getTaskProcessChildList(), taskProMstDO.getProcessMasterId() )) { System.out.println("Unable to Update"); con.rollback(); return false; }
			}else { System.out.println("Unable to Update"); con.rollback(); return false; }
			con.commit();
			generateTask(null, taskProMstDO.getProcessMasterId());
			return true;
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( preCon==null?con:null  ); }
		return false;
	}

	public static List<TaskProcessMasterDO> getTaskProcessMaster(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<TaskProcessMasterDO> dtoList =getTaskProcessMaster(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskProcessMasterDO>(); }
		return dtoList;
	}

	public static TaskProcessMasterDO getTaskProcessMasterByProcessMasterId(Connection preCon, int processMasterId, boolean needChild) {
		String query=SELECT+" WHERE process_master_id="+processMasterId;
		List<TaskProcessMasterDO> dtoList =getTaskProcessMaster(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskProcessMasterDO>(); }
		return dtoList.size()>0?dtoList.get(0): new TaskProcessMasterDO();
	}

	private  static List<TaskProcessMasterDO> getTaskProcessMaster(Connection preCon, String query, boolean needChild) {
		List<TaskProcessMasterDO> dtos=new ArrayList<TaskProcessMasterDO>();
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

	private static TaskProcessMasterDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		TaskProcessMasterDO dto=new TaskProcessMasterDO();
		try {
			int i=1;
			dto.setProcessMasterId(rs.getInt(i++));
			dto.setTaskType(rs.getString(i++));
			dto.setSalesId(rs.getInt(i++));
			dto.setProcessMasterStatus(rs.getString(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			if(needChild) { dto.setTaskProcessChildList( TaskProcessChildDAO.getTaskProcessChildList(con, dto.getProcessMasterId(), needChild) ); }
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	}

	public static boolean generateTask(Connection preCon, int taskProcessMasterId ) {
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement("CALL task_config_det_by_id("+taskProcessMasterId+",0,0,0,0,0,1,@a);");
			int row=stmt.executeUpdate();

			return true;

		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null, rs  ); }
		return false;
	}

}