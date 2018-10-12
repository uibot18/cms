package com.cms.task.config.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.task.config.bean.TaskConfigMasterDO;

public class TaskConfigMasterDAO {

	private static final String SELECT="select   task_config_id, process_id, task_config_name, exe_order, department, designation, emp_id, config_type, daily_every_day, bool_daily_every_week_day, weekly_every_week, weekly_week_day, monthly_every_month, monthly_every_month_day, monthly_every_week, monthly_every_week_weekday, yearly_every_year, yearly_every_week, yearly_every_week_week, yearly_every_month, holiday_ids, end_after_no_of_rec, bool_no_end_date, start_time, duration, duration_type, bool_delete_status, created_user, created_date, update_user, update_date, bool_monthly_day_specific, bool_yearly_year_specific from task_config_master ";
	private static final String INSERT="insert into task_config_master( task_config_id, process_id, task_config_name, exe_order, department, designation, emp_id, config_type, daily_every_day, bool_daily_every_week_day, weekly_every_week, weekly_week_day, monthly_every_month, monthly_every_month_day, monthly_every_week, monthly_every_week_weekday, yearly_every_year, yearly_every_week, yearly_every_week_week, yearly_every_month, holiday_ids, end_after_no_of_rec, bool_no_end_date, start_time, duration, duration_type, bool_delete_status, created_user, created_date, bool_monthly_day_specific, bool_yearly_year_specific )  values(  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, ? ) ";
	private static final String UPDATE="update  task_config_master set  process_id=?, task_config_name=?, exe_order=?, department=?, designation=?, emp_id=?, config_type=?, daily_every_day=?, bool_daily_every_week_day=?, weekly_every_week=?, weekly_week_day=?, monthly_every_month=?, monthly_every_month_day=?, monthly_every_week=?, monthly_every_week_weekday=?, yearly_every_year=?, yearly_every_week=?, yearly_every_week_week=?, yearly_every_month=?, holiday_ids=?, end_after_no_of_rec=?, bool_no_end_date=?, start_time=?, duration=?, duration_type=?,  update_user=?, update_date=NOW(), bool_monthly_day_specific=?, bool_yearly_year_specific=? WHERE task_config_id=? ";

	public static int insert(Connection preCon, TaskConfigMasterDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():con;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getTaskConfigId() );
			stmt.setInt(i++, dto.getProcessId() );
			stmt.setString(i++, dto.getTaskConfigName() );
			stmt.setInt(i++, dto.getExeOrder() );
			stmt.setString(i++, dto.getDepartment() );
			stmt.setString(i++, dto.getDesignation() );
			stmt.setString(i++, dto.getEmpId() );
			stmt.setString(i++, dto.getConfigType() );
			stmt.setInt(i++, dto.getDailyEveryDay() );
			stmt.setBoolean(i++, dto.isBoolDailyEveryWeekDay() );
			stmt.setInt(i++, dto.getWeeklyEveryWeek() );
			stmt.setString(i++, dto.getWeeklyWeekDay() );
			stmt.setInt(i++, dto.getMonthlyEveryMonth() );
			stmt.setInt(i++, dto.getMonthlyEveryMonthDay() );
			stmt.setString(i++, dto.getMonthlyEveryWeek() );
			stmt.setString(i++, dto.getMonthlyEveryWeekWeekday() );
			stmt.setInt(i++, dto.getYearlyEveryYear() );
			stmt.setString(i++, dto.getYearlyEveryWeek() );
			stmt.setString(i++, dto.getYearlyEveryWeekWeek() );
			stmt.setInt(i++, dto.getYearlyEveryMonth() );
			stmt.setString(i++, dto.getHolidayIds() );
			stmt.setInt(i++, dto.getEndAfterNoOfRec() );
			stmt.setBoolean(i++, dto.isBoolNoEndDate() );
			stmt.setString(i++, dto.getStartTime() );
			stmt.setInt(i++, dto.getDuration() );
			stmt.setString(i++, dto.getDurationType() );
			stmt.setBoolean(i++, dto.isBoolDeleteStatus() );
			stmt.setString(i++, dto.getCreatedUser() );
			stmt.setBoolean(i++, dto.isBoolMonthlyDaySpecfic() );
			stmt.setBoolean(i++, dto.isBoolYearlyYearSpecific() );
			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) { insertId=rs.getInt(1); }
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
		return insertId;
	}

	public static boolean update(Connection preCon, TaskConfigMasterDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setInt(i++,dto.getProcessId());
			stmt.setString(i++,dto.getTaskConfigName());
			stmt.setInt(i++,dto.getExeOrder());
			stmt.setString(i++,dto.getDepartment());
			stmt.setString(i++,dto.getDesignation());
			stmt.setString(i++,dto.getEmpId());
			stmt.setString(i++,dto.getConfigType());
			stmt.setInt(i++,dto.getDailyEveryDay());
			stmt.setBoolean(i++,dto.isBoolDailyEveryWeekDay());
			stmt.setInt(i++,dto.getWeeklyEveryWeek());
			stmt.setString(i++,dto.getWeeklyWeekDay());
			stmt.setInt(i++,dto.getMonthlyEveryMonth());
			stmt.setInt(i++,dto.getMonthlyEveryMonthDay());
			stmt.setString(i++,dto.getMonthlyEveryWeek());
			stmt.setString(i++,dto.getMonthlyEveryWeekWeekday());
			stmt.setInt(i++,dto.getYearlyEveryYear());
			stmt.setString(i++,dto.getYearlyEveryWeek());
			stmt.setString(i++,dto.getYearlyEveryWeekWeek());
			stmt.setInt(i++,dto.getYearlyEveryMonth());
			stmt.setString(i++,dto.getHolidayIds());
			stmt.setInt(i++,dto.getEndAfterNoOfRec());
			stmt.setBoolean(i++,dto.isBoolNoEndDate());
			stmt.setString(i++,dto.getStartTime());
			stmt.setInt(i++,dto.getDuration());
			stmt.setString(i++,dto.getDurationType());
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setBoolean(i++, dto.isBoolMonthlyDaySpecfic() );
			stmt.setBoolean(i++, dto.isBoolYearlyYearSpecific() );
			stmt.setInt(i++,dto.getTaskConfigId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static List<TaskConfigMasterDO> getTaskConfigMaster(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<TaskConfigMasterDO> dtoList =getTaskConfigMaster(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskConfigMasterDO>(); }
		return dtoList;
	}

	public static TaskConfigMasterDO getTaskConfigMasterByTaskConfigId(Connection preCon, int taskConfigId, boolean needChild) {
		String query=SELECT+" WHERE task_config_id="+taskConfigId;
		List<TaskConfigMasterDO> dtoList =getTaskConfigMaster(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<TaskConfigMasterDO>(); }
		return dtoList.size()>0?dtoList.get(0): new TaskConfigMasterDO();
	}

	private  static List<TaskConfigMasterDO> getTaskConfigMaster(Connection preCon, String query, boolean needChild) {
		List<TaskConfigMasterDO> dtos=new ArrayList<TaskConfigMasterDO>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():con;
			stmt=con.createStatement();
			rs=stmt.executeQuery( query );
			while(rs.next()) { dtos.add(constructDTO( con, rs, needChild) );	}
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null ); }
		return dtos;
	}

	private static TaskConfigMasterDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		TaskConfigMasterDO dto=new TaskConfigMasterDO();
		try {
			int i=1;
			dto.setTaskConfigId(rs.getInt(i++));
			dto.setProcessId(rs.getInt(i++));
			dto.setTaskConfigName(rs.getString(i++));
			dto.setExeOrder(rs.getInt(i++));
			dto.setDepartment(rs.getString(i++));
			dto.setDesignation(rs.getString(i++));
			dto.setEmpId(rs.getString(i++));
			dto.setConfigType(rs.getString(i++));
			dto.setDailyEveryDay(rs.getInt(i++));
			dto.setBoolDailyEveryWeekDay(rs.getBoolean(i++));
			dto.setWeeklyEveryWeek(rs.getInt(i++));
			dto.setWeeklyWeekDay(rs.getString(i++));
			dto.setMonthlyEveryMonth(rs.getInt(i++));
			dto.setMonthlyEveryMonthDay(rs.getInt(i++));
			dto.setMonthlyEveryWeek(rs.getString(i++));
			dto.setMonthlyEveryWeekWeekday(rs.getString(i++));
			dto.setYearlyEveryYear(rs.getInt(i++));
			dto.setYearlyEveryWeek(rs.getString(i++));
			dto.setYearlyEveryWeekWeek(rs.getString(i++));
			dto.setYearlyEveryMonth(rs.getInt(i++));
			dto.setHolidayIds(rs.getString(i++));
			dto.setEndAfterNoOfRec(rs.getInt(i++));
			dto.setBoolNoEndDate(rs.getBoolean(i++));
			dto.setStartTime(rs.getString(i++));
			dto.setDuration(rs.getInt(i++));
			dto.setDurationType(rs.getString(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate(rs.getString(i++));
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate(rs.getString(i++));
			dto.setBoolMonthlyDaySpecfic( rs.getBoolean(i++) );
			dto.setBoolYearlyYearSpecific(rs.getBoolean(i++) );
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	} 


}