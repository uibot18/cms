package com.cms.task.config.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.task.config.bean.TaskConfigEscalationChildDO;
import com.mysql.jdbc.Statement;

public class TaskConfigEscalationChildDAO {

	private static final String SELECT="select   task_config_escalation_id, task_config_id, department, designation, emp_id, ticket_duration, ticket_duration_uom, bool_delete_status, created_user, created_date, update_user, update_date from task_config_escalation_child ";
	private static final String INSERT="insert into task_config_escalation_child( task_config_escalation_id, task_config_id, department, designation, emp_id, ticket_duration, ticket_duration_uom, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() ) ";
	private static final String UPDATE="update  task_config_escalation_child set  task_config_id=?, department=?, designation=?, emp_id=?, ticket_duration=?, ticket_duration_uom=?, bool_delete_status=?,  update_user=?, update_date=NOW() WHERE task_config_escalation_id=? ";
	private static final String DELETE_BY_TASK_CONGIG_ID="delete from  task_config_escalation_child where task_config_id=?";
	
	public static int insert(Connection preCon, TaskConfigEscalationChildDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getTaskConfigEscalationId() );
			stmt.setInt(i++, dto.getTaskConfigId() );
			stmt.setString(i++, dto.getDepartment() );
			stmt.setString(i++, dto.getDesignation() );
			stmt.setString(i++, dto.getEmpId() );
			stmt.setInt(i++, dto.getTicketDuration() );
			stmt.setString(i++, dto.getTicketDurationUom() );
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
	public static boolean insert(Connection preCon, List<TaskConfigEscalationChildDO> dtos, int tsakConfigId) {
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT);
			for (TaskConfigEscalationChildDO dto : dtos) {
				int i=1;
				stmt.setInt(i++, dto.getTaskConfigEscalationId() );
				stmt.setInt(i++, tsakConfigId );
				stmt.setString(i++, dto.getDepartment() );
				stmt.setString(i++, dto.getDesignation() );
				stmt.setString(i++, dto.getEmpId() );
				stmt.setInt(i++, dto.getTicketDuration() );
				stmt.setString(i++, dto.getTicketDurationUom() );
				stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
				stmt.setString(i++, dto.getCreatedUser() );
				stmt.setString(i++, dto.getUpdateUser() );
				System.out.println("stmt : "+stmt.toString());
				stmt.addBatch();
			}
			
			stmt.executeBatch();
			return true;
//			rs=stmt.getGeneratedKeys();
//			if(rs.next()) { insertId=rs.getInt(1); }
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
		return false;
	}

	public static boolean update(Connection preCon, TaskConfigEscalationChildDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setInt(i++,dto.getTaskConfigId());
			stmt.setString(i++,dto.getDepartment());
			stmt.setString(i++,dto.getDesignation());
			stmt.setString(i++,dto.getEmpId());
			stmt.setInt(i++,dto.getTicketDuration());
			stmt.setString(i++,dto.getTicketDurationUom());
			stmt.setBoolean(i++,dto.getBoolDeleteStatus());
			stmt.setString(i++,dto.getCreatedUser());
			stmt.setInt(i++,dto.getTaskConfigEscalationId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}
	
	public static boolean delete(Connection preCon, int taskConfigId) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(DELETE_BY_TASK_CONGIG_ID);
			stmt.setInt(i++, taskConfigId);
			
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static List<TaskConfigEscalationChildDO> getTaskConfigEscalationChild(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<TaskConfigEscalationChildDO> dtoList =getTaskConfigEscalationChild(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskConfigEscalationChildDO>(); }
		return dtoList;
	}
	
	public static List<TaskConfigEscalationChildDO> getTaskConfigEscalationChildByTaskConfigId(Connection preCon, int taskConfigId, boolean needChild) {
		String query=SELECT + " WHERE task_config_id="+taskConfigId;
		List<TaskConfigEscalationChildDO> dtoList =getTaskConfigEscalationChild(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskConfigEscalationChildDO>(); }
		return dtoList;
	}

	public static TaskConfigEscalationChildDO getTaskConfigEscalationChildByTaskConfigEscalationId(Connection preCon, int taskConfigEscalationId, boolean needChild) {
		String query=SELECT+" WHERE task_config_escalation_id="+taskConfigEscalationId;
		List<TaskConfigEscalationChildDO> dtoList =getTaskConfigEscalationChild(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskConfigEscalationChildDO>(); }
		return dtoList.size()>0?dtoList.get(0): new TaskConfigEscalationChildDO();
	}

	private  static List<TaskConfigEscalationChildDO> getTaskConfigEscalationChild(Connection preCon, String query, boolean needChild) {
		List<TaskConfigEscalationChildDO> dtos=new ArrayList<TaskConfigEscalationChildDO>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=(Statement) con.createStatement();
			rs=stmt.executeQuery( query );
			while(rs.next()) { dtos.add(constructDTO( con, rs, needChild) );	}
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null ); }
		return dtos;
	}

	private static TaskConfigEscalationChildDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		TaskConfigEscalationChildDO dto=new TaskConfigEscalationChildDO();
		try {
			int i=1;
			dto.setTaskConfigEscalationId(rs.getInt(i++));
			dto.setTaskConfigId(rs.getInt(i++));
			dto.setDepartment(rs.getString(i++));
			dto.setDesignation(rs.getString(i++));
			dto.setEmpId(rs.getString(i++));
			dto.setTicketDuration(rs.getInt(i++));
			dto.setTicketDurationUom(rs.getString(i++));
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