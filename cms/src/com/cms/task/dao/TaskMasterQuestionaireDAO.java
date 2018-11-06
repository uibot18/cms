package com.cms.task.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.task.bean.TaskMasterQuestionaireDO;

public class TaskMasterQuestionaireDAO {

	private static final String SELECT="select   task_quest_id, task_id, questionaire_id, options, description, bool_delete_status, created_user, created_date, update_user, update_date from task_master_questionaire ";
	private static final String INSERT="insert into task_master_questionaire( task_quest_id, task_id, questionaire_id, options, description, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
	private static final String UPDATE="update  task_master_questionaire set  task_id=?, questionaire_id=?, options=?, description=?, bool_delete_status=?, created_user=?, created_date=?, update_user=?, update_date=? WHERE task_quest_id=? ";

	public static int insert(Connection preCon, TaskMasterQuestionaireDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getTaskQuestId() );
			stmt.setInt(i++, dto.getTaskId() );
			stmt.setInt(i++, dto.getQuestionaireId() );
			stmt.setString(i++, dto.getOptions() );
			stmt.setString(i++, dto.getDescription() );
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

	public static boolean update(Connection preCon, TaskMasterQuestionaireDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setInt(i++,dto.getTaskId());
			stmt.setInt(i++,dto.getQuestionaireId());
			stmt.setString(i++,dto.getOptions());
			stmt.setString(i++,dto.getDescription());
			stmt.setBoolean(i++,dto.getBoolDeleteStatus());
			stmt.setString(i++,dto.getCreatedUser());
			stmt.setString(i++,dto.getCreatedDate());
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setString(i++,dto.getUpdateDate());
			stmt.setInt(i++,dto.getTaskQuestId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static List<TaskMasterQuestionaireDO> getTaskMasterQuestionaire(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<TaskMasterQuestionaireDO> dtoList =getTaskMasterQuestionaire(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskMasterQuestionaireDO>(); }
		return dtoList;
	}

	public static TaskMasterQuestionaireDO getTaskMasterQuestionaireByTaskQuestId(Connection preCon, int taskQuestId, boolean needChild) {
		String query=SELECT+" WHERE task_quest_id="+taskQuestId;
		List<TaskMasterQuestionaireDO> dtoList =getTaskMasterQuestionaire(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskMasterQuestionaireDO>(); }
		return dtoList.size()>0?dtoList.get(0): new TaskMasterQuestionaireDO();
	}

	private  static List<TaskMasterQuestionaireDO> getTaskMasterQuestionaire(Connection preCon, String query, boolean needChild) {
		List<TaskMasterQuestionaireDO> dtos=new ArrayList<TaskMasterQuestionaireDO>();
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

	private static TaskMasterQuestionaireDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		TaskMasterQuestionaireDO dto=new TaskMasterQuestionaireDO();
		try {
			int i=1;
			dto.setTaskQuestId(rs.getInt(i++));
			dto.setTaskId(rs.getInt(i++));
			dto.setQuestionaireId(rs.getInt(i++));
			dto.setOptions(rs.getString(i++));
			dto.setDescription(rs.getString(i++));
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