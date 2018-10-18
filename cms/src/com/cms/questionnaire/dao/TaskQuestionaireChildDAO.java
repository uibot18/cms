package com.cms.questionnaire.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.questionnaire.bean.TaskQuestionaireChildDO;

public class TaskQuestionaireChildDAO {

	private static final String SELECT="select   questionaire_child_id, questionaire_id, quest_option, bool_delete_status, created_user, created_date, update_user, update_date from task_questionaire_child ";
	private static final String INSERT="insert into task_questionaire_child( questionaire_child_id, questionaire_id, quest_option, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, NOW(), ?, NOW() ) ";
	private static final String UPDATE="update  task_questionaire_child set  questionaire_id=?, quest_option=?, bool_delete_status=?, update_user=?, update_date=NOW() WHERE questionaire_child_id=? ";
	private static final String DELETE_BY_QUESTIONAIRE_ID="delete from task_questionaire_child where questionaire_id=? ";
	
	public static int insert(Connection preCon, TaskQuestionaireChildDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getQuestionaireChildId() );
			stmt.setInt(i++, dto.getQuestionaireId() );
			stmt.setString(i++, dto.getOption() );
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
	public static boolean insert(Connection preCon, List<TaskQuestionaireChildDO> dtos, int questionaireId) {
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			for (TaskQuestionaireChildDO dto : dtos) {
				int i=1;
				stmt.setInt(i++, dto.getQuestionaireChildId() );
				stmt.setInt(i++, questionaireId );
				stmt.setString(i++, dto.getOption() );
				stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
				stmt.setString(i++, dto.getCreatedUser() );
				stmt.setString(i++, dto.getUpdateUser() );
				System.out.println("insert: stmt: "+stmt.toString());
				stmt.addBatch();
			}
			
			stmt.executeBatch();
			return true;
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
		return false;
	} 

	public static boolean update(Connection preCon, TaskQuestionaireChildDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setInt(i++,dto.getQuestionaireId());
			stmt.setString(i++,dto.getOption());
			stmt.setBoolean(i++,dto.getBoolDeleteStatus());
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setInt(i++,dto.getQuestionaireChildId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static List<TaskQuestionaireChildDO> getTaskQuestionaireChild(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<TaskQuestionaireChildDO> dtoList =getTaskQuestionaireChild(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskQuestionaireChildDO>(); }
		return dtoList;
	}
	public static List<TaskQuestionaireChildDO> getTaskQuestionaireChild(Connection preCon, int questionaireId, boolean needChild) {
		String query=SELECT+" WHERE questionaire_id="+questionaireId;
		List<TaskQuestionaireChildDO> dtoList =getTaskQuestionaireChild(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskQuestionaireChildDO>(); }
		return dtoList;
	}

	public static TaskQuestionaireChildDO getTaskQuestionaireChildByQuestionaireChildId(Connection preCon, int questionaireChildId, boolean needChild) {
		String query=SELECT+" WHERE questionaire_child_id="+questionaireChildId;
		List<TaskQuestionaireChildDO> dtoList =getTaskQuestionaireChild(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskQuestionaireChildDO>(); }
		return dtoList.size()>0?dtoList.get(0): new TaskQuestionaireChildDO();
	}

	private  static List<TaskQuestionaireChildDO> getTaskQuestionaireChild(Connection preCon, String query, boolean needChild) {
		List<TaskQuestionaireChildDO> dtos=new ArrayList<TaskQuestionaireChildDO>();
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

	private static TaskQuestionaireChildDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		TaskQuestionaireChildDO dto=new TaskQuestionaireChildDO();
		try {
			int i=1;
			dto.setQuestionaireChildId(rs.getInt(i++));
			dto.setQuestionaireId(rs.getInt(i++));
			dto.setOption(rs.getString(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate(rs.getString(i++));
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate(rs.getString(i++));
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	}
	public static boolean delete(Connection preCon, int questionnaireId) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(DELETE_BY_QUESTIONAIRE_ID);
			stmt.setInt(i++, questionnaireId);
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	


}