package com.cms.task.config.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.master.CmnGroupName;
import com.cms.common.master.dao.CommonMasterDAO;
import com.cms.task.config.bean.TaskConfigMasterDO;
import com.cms.task.config.dao.TaskConfigMasterDAO;

public class TaskConfigCreationController {

	public static void doAdd(HttpServletRequest request, HttpServletResponse response) {
		TaskConfigMasterDO taskConfigDO=new TaskConfigMasterDO();
		request.setAttribute("taskConfigDO", taskConfigDO);
	}

	public static void doEdit(HttpServletRequest request, HttpServletResponse response) {

		int taskConfigId=AppUtil.getNullToInteger( request.getParameter("taskConfigId")  );
		TaskConfigMasterDO taskConfigDO=TaskConfigMasterDAO.getTaskConfigMasterByTaskConfigId(null, taskConfigId, true);
		request.setAttribute("taskConfigDO", taskConfigDO);
	}

	public static void doSave(HttpServletRequest request, HttpServletResponse response) {

		TaskConfigMasterDO taskConfigDO=constructDO(request, response);

		System.out.println("taskConfigDO: "+taskConfigDO);

		if(taskConfigDO.getTaskConfigId()==0) {
			//			insert
			int taskConfigId =TaskConfigMasterDAO.insert(null, taskConfigDO);
			if(taskConfigId!=0) {
				System.out.println("Service Inderted...Id:"+taskConfigId);
				taskConfigDO=TaskConfigMasterDAO.getTaskConfigMasterByTaskConfigId(null, taskConfigId, true);
			}else {
				System.out.println("Failed to indert Task Config..!");
			}
		}else {
			//			update
			if(TaskConfigMasterDAO.update(null, taskConfigDO)) {
				taskConfigDO=TaskConfigMasterDAO.getTaskConfigMasterByTaskConfigId(null, taskConfigDO.getTaskConfigId(), true);
			}else {
				System.out.println("Failed to Update Task Config..");
			}
		}
		request.setAttribute("taskConfigDO", taskConfigDO);

	}

	private static TaskConfigMasterDO constructDO(HttpServletRequest request, HttpServletResponse response) {

		String loginId="Admin";
		TaskConfigMasterDO taskConfigDO=new TaskConfigMasterDO();

		taskConfigDO.setTaskConfigId( AppUtil.getNullToInteger( request.getParameter("taskConfigId") ) );
		taskConfigDO.setProcessId( AppUtil.getNullToInteger( request.getParameter("processName") )  );
		taskConfigDO.setTaskConfigName( AppUtil.getNullToEmpty( request.getParameter("taskConfigName")  ) );
		taskConfigDO.setExeOrder(AppUtil.getNullToInteger( request.getParameter("executionOrder") ));
		taskConfigDO.setDepartment( AppUtil.getNullToEmpty( request.getParameter("department")  ) );
		taskConfigDO.setDesignation( AppUtil.getNullToEmpty( request.getParameter("designation")  ) );
		taskConfigDO.setEmpId( AppUtil.getNullToEmpty( request.getParameter("empId")  )  );

		String configType = AppUtil.getNullToEmpty( request.getParameter("configType")  ) ;
		taskConfigDO.setConfigType( configType  );
		/*daily','weekly','monthly','yearly','holidays','na'
		 */		
		if(configType.equalsIgnoreCase("daily")) {
			boolean dailyEveryWeekDay=Boolean.parseBoolean( AppUtil.getNullToEmpty( request.getParameter("dailyEveryWeekDay"),"false" ) );
			taskConfigDO.setBoolDailyEveryWeekDay(dailyEveryWeekDay);
			if(dailyEveryWeekDay==false) {
				taskConfigDO.setDailyEveryDay( AppUtil.getNullToInteger( request.getParameter("dailyEveryDay") ) );
			}
		}
		else if(configType.equalsIgnoreCase("weekly")) {
			taskConfigDO.setWeeklyEveryWeek( AppUtil.getNullToInteger( request.getParameter("weeklyEveryWeek") ) );
			taskConfigDO.setWeeklyWeekDay( AppUtil.convertArrayToString( request.getParameterValues("weeklyWeekDay") , ",") );
		}
		else if(configType.equalsIgnoreCase("monthly")) {
			boolean monthlyIsDaySpecifc= Boolean.parseBoolean( AppUtil.getNullToEmpty( request.getParameter("monthlyDaySpecfic")) );
			if(monthlyIsDaySpecifc==false) {
				taskConfigDO.setMonthlyEveryMonthDay( AppUtil.getNullToInteger( request.getParameter("monthlyEveryMonthDay") )  );
				taskConfigDO.setMonthlyEveryMonth( AppUtil.getNullToInteger( request.getParameter("monthlyEveryMonth1") )  );
			}else {
				taskConfigDO.setMonthlyEveryWeek( AppUtil.getNullToEmpty( request.getParameter("monthlyEveryWeek") ) );
				taskConfigDO.setMonthlyEveryWeekWeekday( AppUtil.getNullToEmpty( request.getParameter("monthlyEveryWeekWeekday") )  );
				taskConfigDO.setMonthlyEveryMonth( AppUtil.getNullToInteger( request.getParameter("monthlyEveryMonth2") )  );
			}
			taskConfigDO.setBoolMonthlyDaySpecfic( monthlyIsDaySpecifc );


		}
		else if(configType.equalsIgnoreCase("yearly")) {
			boolean yearlyYearSpecific= Boolean.parseBoolean( AppUtil.getNullToEmpty( request.getParameter("yearlyYearSpecific")) );
			taskConfigDO.setBoolYearlyYearSpecific(yearlyYearSpecific);
			if(yearlyYearSpecific) {
				taskConfigDO.setYearlyEveryYear( AppUtil.getNullToInteger( request.getParameter("yearlyEveryYear") )  );
			}else {
				taskConfigDO.setYearlyEveryWeek(  AppUtil.getNullToEmpty( request.getParameter("yearlyEveryWeek") )  );
				taskConfigDO.setYearlyEveryWeekWeek(  AppUtil.getNullToEmpty( request.getParameter("yearlyEveryWeekWeek") )  );
				taskConfigDO.setYearlyEveryMonth( AppUtil.getNullToInteger( request.getParameter("yearlyEveryMonth") )  );
			}

		}
		else if(configType.equalsIgnoreCase("holidays")) {
			taskConfigDO.setHolidayIds( AppUtil.convertArrayToString(request.getParameterValues("holidayIds"), ",") );
		}
		else if(configType.equalsIgnoreCase("na")) {

			boolean noEndDate= Boolean.parseBoolean( AppUtil.getNullToEmpty( request.getParameter("noEndDate")) );
			taskConfigDO.setBoolNoEndDate(noEndDate);
			if(noEndDate==false) {
				taskConfigDO.setEndAfterNoOfRec( AppUtil.getNullToInteger( request.getParameter("endAfterNoOfRec") ) );
			}
		}

		String startType=AppUtil.getNullToEmpty( request.getParameter("startType") ); 
		System.out.println("startType: "+startType);
		if(startType.equals("time")) {
			taskConfigDO.setStartTime( AppUtil.getNullToEmpty( request.getParameter("startTime"), "00:00:00") );
		}else {
			taskConfigDO.setDuration( AppUtil.getNullToInteger( request.getParameter("duration") ) );
			taskConfigDO.setDurationType( AppUtil.getNullToEmpty( request.getParameter("durationType")) );
		}

		taskConfigDO.setCreatedUser(loginId);
		taskConfigDO.setUpdateUser(loginId);

		return taskConfigDO;
	}

	public static String serviceOption( String parentIds, String selServiceIds ) {
		String subQry=" AND cmn_group_id="+CmnGroupName.SERVICE.getGroupId() +" AND parent_id=0 and bool_delete_status=0 ";
		if(!parentIds.isEmpty() && !parentIds.isEmpty() ) { subQry=" AND parent_id in("+parentIds+")"; }
		Map<String, String> map=CommonMasterDAO.getCommonDetMapBySubQry(null, subQry);

		return AppUtil.formOption(map, selServiceIds);
	}

}
