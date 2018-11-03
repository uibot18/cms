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
import com.cms.questionnaire.bean.TaskQuestionaireDetailsDO;

public class TaskQuestionaireDetailsDAO {

	private static final String SELECT="select   questionaire_id, task_config_id, questionaire_name, answer_type, description, bool_delete_status, created_user, created_date, update_user, update_date from task_questionaire_details ";
	private static final String INSERT="insert into task_questionaire_details( questionaire_id, task_config_id, questionaire_name, answer_type, description, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() ) ";
	private static final String UPDATE="update  task_questionaire_details set  task_config_id=?, questionaire_name=?, answer_type=?, description=?, bool_delete_status=?, update_user=?, update_date=NOW() WHERE questionaire_id=? ";
	private static final String DELETE_UPDATE="update  task_questionaire_details set  bool_delete_status=?, update_user=?, update_date=NOW() WHERE task_config_id=? ";
	public static int insert(Connection preCon, TaskQuestionaireDetailsDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getQuestionaireId() );
			stmt.setInt(i++, dto.getTaskConfigId() );
			stmt.setString(i++, dto.getQuestionaireName() );
			stmt.setString(i++, dto.getAnswerType() );
			stmt.setString(i++, dto.getDescription() );
			stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
			stmt.setString(i++, dto.getCreatedUser() );
			stmt.setString(i++, dto.getUpdateUser() );
			System.out.println("Insert: stmt: "+stmt.toString());
			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) { 
				insertId=rs.getInt(1); 
				System.out.println("insertId: "+insertId+", Size: "+dto.getQuestionaryChildList().size());
				if(insertId!=0) {
					if( dto.getQuestionaryChildList().size()>0) {
						if(TaskQuestionaireChildDAO.insert(con, dto.getQuestionaryChildList(), insertId)==false) { System.out.println("insert: Error-1"); return 0; }
					}
				}
			}else { System.out.println("Failed to insert QuestionaireDetail.. ");}
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
		return insertId;
	}

	public static boolean update(Connection preCon, TaskQuestionaireDetailsDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setInt(i++,dto.getTaskConfigId());
			stmt.setString(i++,dto.getQuestionaireName());
			stmt.setString(i++,dto.getAnswerType());
			stmt.setString(i++,dto.getDescription());
			stmt.setBoolean(i++,dto.getBoolDeleteStatus());
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setInt(i++,dto.getQuestionaireId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) {
				TaskQuestionaireChildDAO.delete(con, dto.getQuestionaireId() );
				if(dto.getQuestionaryChildList().size()>0) {
					if(TaskQuestionaireChildDAO.insert(con, dto.getQuestionaryChildList(), dto.getQuestionaireId())==false) { System.out.println("update: Error 2"); return false; }
				}
				return true; 
			}else { System.out.println("update: Error-1");}
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}
	public static boolean deleteupdate(Connection preCon, TaskQuestionaireDetailsDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(DELETE_UPDATE);
			stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setInt(i++,dto.getTaskConfigId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}
	public static List<TaskQuestionaireDetailsDO> getTaskQuestionaireDetails(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<TaskQuestionaireDetailsDO> dtoList =getTaskQuestionaireDetails(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskQuestionaireDetailsDO>(); }
		return dtoList;
	}

	public static List<TaskQuestionaireDetailsDO> getTaskQuestionaireDetails(Connection preCon, int taskConfigId, boolean needChild) {
		String query=SELECT+" WHERE task_config_id="+taskConfigId;
		List<TaskQuestionaireDetailsDO> dtoList =getTaskQuestionaireDetails(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskQuestionaireDetailsDO>(); }
		return dtoList;
	}

	public static TaskQuestionaireDetailsDO getTaskQuestionaireDetailsByQuestionaireId(Connection preCon, int questionaireId, boolean needChild) {
		String query=SELECT+" WHERE questionaire_id="+questionaireId;
		List<TaskQuestionaireDetailsDO> dtoList =getTaskQuestionaireDetails(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskQuestionaireDetailsDO>(); }
		return dtoList.size()>0?dtoList.get(0): new TaskQuestionaireDetailsDO();
	}

	private  static List<TaskQuestionaireDetailsDO> getTaskQuestionaireDetails(Connection preCon, String query, boolean needChild) {
		List<TaskQuestionaireDetailsDO> dtos=new ArrayList<TaskQuestionaireDetailsDO>();
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

	private static TaskQuestionaireDetailsDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		TaskQuestionaireDetailsDO dto=new TaskQuestionaireDetailsDO();
		try {
			int i=1;
			dto.setQuestionaireId(rs.getInt(i++));
			dto.setTaskConfigId(rs.getInt(i++));
			dto.setQuestionaireName(rs.getString(i++));
			dto.setAnswerType(rs.getString(i++));
			dto.setDescription(rs.getString(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate(rs.getString(i++));
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate(rs.getString(i++));
			if(needChild) { dto.setQuestionaryChildList( TaskQuestionaireChildDAO.getTaskQuestionaireChild(null, dto.getQuestionaireId(), needChild) ); }
			
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	}

	public static boolean save(Connection preCon, List<TaskQuestionaireDetailsDO> taskQuestionaireList) {

		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			con.setAutoCommit(false);

			for (TaskQuestionaireDetailsDO questDO : taskQuestionaireList) {
				if(questDO.getQuestionaireId()==0) {
					System.out.println("Insert..!");
					if(insert(con, questDO)==0) { System.out.println("save: Error 1"); con.rollback(); return false; }
				}else {
					System.out.println("Update..!");
					if(update(con, questDO)==false) { System.out.println("save: Error 2"); con.rollback(); return false; }
				}
			}
			con.commit();
			return true;

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null ); }
		return false;
	} 


}