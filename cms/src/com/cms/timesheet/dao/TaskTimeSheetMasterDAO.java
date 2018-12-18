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
import com.cms.timesheet.bean.TaskTimeSheetMasterDO;

public class TaskTimeSheetMasterDAO {

	private static final String SELECT="select   time_sheet_id, assigned_to, shift_id, from_date, to_date, bool_delete_status, status, approved_by, approved_on, created_user, created_date, update_user, update_date from task_time_sheet_master ";
	private static final String INSERT="insert into task_time_sheet_master( time_sheet_id, assigned_to, shift_id, from_date, to_date, bool_delete_status, status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() ) ";
	private static final String UPDATE="update  task_time_sheet_master set  assigned_to=?, shift_id=?, from_date=?, to_date=?, bool_delete_status=?, status=?, update_user=?, update_date=? WHERE time_sheet_id=? ";

	public static int insert(Connection preCon, TaskTimeSheetMasterDO dto) {
		int timeSheetId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			con.setAutoCommit( false );
			//time_sheet_id, assigned_to, shift_id, from_date, to_date, bool_delete_status, status,  created_user, created_date, update_user, update_date
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getTimeSheetId() );
			stmt.setString(i++, dto.getAssignedTo() );
			stmt.setInt(i++, dto.getShiftId() );
			stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getFromDate(), true, true) );
			stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getToDate(), true, true) );
			stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
			stmt.setString(i++, dto.getStatus() );
			stmt.setString(i++, dto.getCreatedUser() );
			stmt.setString(i++, dto.getUpdateUser() );
			System.out.println("Insert: "+stmt.toString());
			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if( rs.next() ) { 
				timeSheetId=rs.getInt(1); 
				if(timeSheetId!=0) {
					if( !TaskTimeSheetChildDAO.insert(con, dto.getTimeSheetChildList(), timeSheetId) ) { System.out.println("Failed to Save TimeSheet Child"); con.rollback(); return 0; }
				}else { System.out.println("Failed to Save TimeSheet2"); con.rollback(); return 0; }
			}else { System.out.println("Failed to Save TimeSheet1"); con.rollback(); return 0; }
			con.commit();
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
		return timeSheetId;
	}

	public static boolean update(Connection preCon, TaskTimeSheetMasterDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			con.setAutoCommit( false );
			stmt=con.prepareStatement(UPDATE);
			stmt.setString(i++, dto.getAssignedTo());
			stmt.setInt(i++, dto.getShiftId());
			stmt.setString(i++, dto.getFromDate());
			stmt.setString(i++, dto.getToDate());
			stmt.setBoolean(i++, dto.getBoolDeleteStatus());
			stmt.setString(i++, dto.getStatus());
			stmt.setString(i++, dto.getUpdateUser());
			stmt.setString(i++, dto.getUpdateDate());
			stmt.setInt(i++, dto.getTimeSheetId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { 
				TaskTimeSheetChildDAO.delete(con, dto.getTimeSheetId() );
				if( !TaskTimeSheetChildDAO.insert(preCon, dto.getTimeSheetChildList(), dto.getTimeSheetId()) ) { System.out.println("Failed to Save TimeSheet Child"); con.rollback(); return false; }
			}else {System.out.println("Failed to Save TimeSheet"); con.rollback(); return false;}
			con.commit();
			return true;
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static List<TaskTimeSheetMasterDO> getTaskTimeSheetMaster(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<TaskTimeSheetMasterDO> dtoList =getTaskTimeSheetMaster(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskTimeSheetMasterDO>(); }
		return dtoList;
	}

	public static TaskTimeSheetMasterDO getTaskTimeSheetMasterByTimeSheetId(Connection preCon, int timeSheetId, boolean needChild) {
		String query=SELECT+" WHERE time_sheet_id="+timeSheetId;
		List<TaskTimeSheetMasterDO> dtoList =getTaskTimeSheetMaster(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskTimeSheetMasterDO>(); }
		return dtoList.size()>0?dtoList.get(0): new TaskTimeSheetMasterDO();
	}

	private  static List<TaskTimeSheetMasterDO> getTaskTimeSheetMaster(Connection preCon, String query, boolean needChild) {
		List<TaskTimeSheetMasterDO> dtos=new ArrayList<TaskTimeSheetMasterDO>();
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

	private static TaskTimeSheetMasterDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		TaskTimeSheetMasterDO dto=new TaskTimeSheetMasterDO();
		try {
			int i=1;
			dto.setTimeSheetId(rs.getInt(i++));
			dto.setAssignedTo(rs.getString(i++));
			dto.setShiftId(rs.getInt(i++));
			dto.setFromDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setToDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setStatus(rs.getString(i++));
			dto.setApprovedBy(rs.getString(i++));
			dto.setApprovedOn( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate(AppDateUtil.convertToAppDate(rs.getString(i++), true, true));
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate(AppDateUtil.convertToAppDate(rs.getString(i++), true, true));
			if(needChild) {
				dto.setTimeSheetChildList( TaskTimeSheetChildDAO.getTaskTimeSheetChildsByTimeSheetId(null, dto.getTimeSheetId(), needChild));
			}
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	} 
	
	public static boolean updateApproval(Connection preCon, String timeSheetIds, String approvedBy, String status) {
		Connection con=null;
		Statement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			int rowAffect = stmt.executeUpdate("update  task_time_sheet_master set status='"+status+"', approved_by='"+approvedBy+"', approved_on=NOW(), update_user='"+approvedBy+"', update_date=NOW() WHERE time_sheet_id in("+timeSheetIds+") ");
			if(rowAffect!=0) { 
				return true;
			}
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}
	


}