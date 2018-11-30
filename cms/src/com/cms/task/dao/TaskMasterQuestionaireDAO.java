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
import com.cms.task.bean.TaskMasterQuestionaireDO;

public class TaskMasterQuestionaireDAO {

	private static final String SELECT="select   task_quest_id, task_id, questionaire_id, options, description, bool_delete_status, created_user, created_date, update_user, update_date from task_master_questionaire ";
	private static final String INSERT="insert into task_master_questionaire( task_quest_id, task_id, questionaire_id, options, description, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, ?, now(), ?, now() ) ";
	private static final String UPDATE="update  task_master_questionaire set  task_id=?, questionaire_id=?, options=?, description=?, bool_delete_status=?, update_user=?, update_date=NOW() WHERE task_quest_id=? ";

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
			stmt.setString(i++, dto.getUpdateUser() );
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
			stmt.setString(i++,dto.getUpdateUser());
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
	
	public static List<TaskMasterQuestionaireDO> getTaskMasterQuestionaire(Connection preCon, int taskId, boolean needChild) {
		String query=SELECT+" WHERE task_id="+taskId;
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
			dto.setCreatedDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	}

	public static boolean saveTaskQuestionnaire(Connection preCon, List<TaskMasterQuestionaireDO> questionnaireList) {
		System.out.println("questionnaireList=>size : "+questionnaireList.size());
		Connection con=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			con.setAutoCommit(false);
			
			for (TaskMasterQuestionaireDO taskMasterQuestionaireDO : questionnaireList) {
				if(taskMasterQuestionaireDO.getTaskQuestId()==0 ) {
					if(insert(null, taskMasterQuestionaireDO)==0) {
						con.rollback(); return false;
					}
				}else {
					if(update(null, taskMasterQuestionaireDO)==false) {
						con.rollback(); return false;
					}
				}
			}
			con.commit();
			return true;
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( preCon==null?con:null ); }
		return false;
	} 


}