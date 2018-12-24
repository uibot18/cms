package com.cms.task.config.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AjaxModel;
import com.application.util.AjaxUtil;
import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.employee.handler.EmployeeCreationHandler;
import com.cms.task.config.bean.TaskConfigEscalationChildDO;
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
				taskConfigDO=TaskConfigMasterDAO.getTaskConfigMasterByTaskConfigId(null, taskConfigId, true);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Task Configuration Successfully Saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Task Configuration..!");
			}
		}else {
			//			update
			if(TaskConfigMasterDAO.update(null, taskConfigDO)) {
				taskConfigDO=TaskConfigMasterDAO.getTaskConfigMasterByTaskConfigId(null, taskConfigDO.getTaskConfigId(), true);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Task Configuration Successfully Saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Task Configuration..!");
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
		taskConfigDO.setTicketDuration( AppUtil.getNullToInteger( request.getParameter("ticketDuration") ) );
		taskConfigDO.setTicketDurationUom( AppUtil.getNullToEmpty( request.getParameter("ticketDurationUom"),"na" ) );

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
		else if(configType.equalsIgnoreCase("event")) {
			taskConfigDO.setTaskExeUnit( AppUtil.getNullToInteger( request.getParameter("taskExeUnit") ) );
			taskConfigDO.setTaskExeUnitUom(AppUtil.getNullToEmpty( request.getParameter("taskExeUnitUom"), "na"));
			String refTaskConfigType=AppUtil.getNullToEmpty( request.getParameter("refTaskConfigType"), "na");
			taskConfigDO.setRefTaskConfigType( refTaskConfigType );
			if(refTaskConfigType.equals("after")) {
				taskConfigDO.setRefTaskConfigId( AppUtil.getNullToInteger( request.getParameter("refTaskConfigId1") ) );
			}else if(refTaskConfigType.equals("before")) {
				taskConfigDO.setRefTaskConfigId( AppUtil.getNullToInteger( request.getParameter("refTaskConfigId2") ) );
			}
		}

		boolean noEndDate= Boolean.parseBoolean( AppUtil.getNullToEmpty( request.getParameter("noEndDate")) );
		taskConfigDO.setBoolNoEndDate(noEndDate);
		if(noEndDate==false) {
			taskConfigDO.setEndAfterNoOfRec( AppUtil.getNullToInteger( request.getParameter("endAfterNoOfRec") ) );
		}

		taskConfigDO.setStartTime( AppUtil.getNullToEmpty( request.getParameter("startTime"), "00:00:00") );
		taskConfigDO.setDuration( AppUtil.getNullToInteger( request.getParameter("duration") ) );
		taskConfigDO.setDurationType( AppUtil.getNullToEmpty( request.getParameter("durationType")) );

		taskConfigDO.setCreatedUser(loginId);
		taskConfigDO.setUpdateUser(loginId);

		List<TaskConfigEscalationChildDO> escChildList=new ArrayList<TaskConfigEscalationChildDO>();
		int escRowCount=AppUtil.getNullToInteger( request.getParameter("esc_rowCount") );
		for (int i = 1; i <= escRowCount; i++) {
			TaskConfigEscalationChildDO escChildDO=new TaskConfigEscalationChildDO();
			escChildDO.setDepartment( AppUtil.getNullToEmpty( request.getParameter("esc_department_"+i)  ) );
			escChildDO.setDesignation( AppUtil.getNullToEmpty( request.getParameter("esc_designation_"+i)  ) );
			escChildDO.setEmpId( AppUtil.getNullToEmpty( request.getParameter("esc_empId_"+i)  )  );
			escChildDO.setTicketDuration( AppUtil.getNullToInteger( request.getParameter("esc_ticketDuration_"+i) ) );
			escChildDO.setTicketDurationUom( AppUtil.getNullToEmpty( request.getParameter("esc_ticketDurationUom_"+i),"na" ) );
			escChildDO.setCreatedUser(loginId);
			escChildDO.setUpdateUser(loginId);
			escChildList.add(escChildDO);
		}
		taskConfigDO.setEscalationChildList(escChildList);

		return taskConfigDO;
	}

	public static String taskOption( String selTaskIds, String excludeTaskIds ) {
		excludeTaskIds=AppUtil.getNullToEmpty(excludeTaskIds);
		String subQry="";
		if(excludeTaskIds.isEmpty()==false) { subQry+=" AND task_config_id NOT IN("+excludeTaskIds+") "; }

		return AppUtil.formOption( TaskConfigMasterDAO.getTaskNameMapBySubQry( null, subQry ), selTaskIds);
	}

	public static void doDelete(HttpServletRequest request, HttpServletResponse response) {
		String loginId="Admin";
		int taskConfigId=AppUtil.getNullToInteger( request.getParameter("taskConfigId")  );
		TaskConfigMasterDO taskConfigDO=new TaskConfigMasterDO();

		taskConfigDO.setBoolDeleteStatus(true);
		taskConfigDO.setUpdateUser(loginId);
		taskConfigDO.setTaskConfigId(taskConfigId);

		AjaxModel model=new AjaxModel();
		if(TaskConfigMasterDAO.deleteupdate(null, taskConfigDO)) {
			model.setMessage(" Deleted Successfully");
		}else {
			model.setMessage(" Unable to Delete");model.setErrorExists(true);
		}
		AjaxUtil.sendResponse(request, response, model);
	}


	public static  String generateTaskEscChildTable(HttpServletRequest request, String formName, TaskConfigMasterDO taskConfigDO) {
		StringBuffer escTable = new StringBuffer();
		List<TaskConfigEscalationChildDO> escalationChildDOs = taskConfigDO.getEscalationChildList();

		if(escalationChildDOs != null && !escalationChildDOs.isEmpty()) {
			int sno=1;
			for (TaskConfigEscalationChildDO escChildDO : escalationChildDOs) {
				escTable.append( generateTaskEscChildRow(request, formName, taskConfigDO, escChildDO, sno ) );
				sno++;
			}

		}else {

		}
		return escTable.toString();
	}

	private static String generateTaskEscChildRow(HttpServletRequest request, String formName, TaskConfigMasterDO taskConfigDO, TaskConfigEscalationChildDO escChildDO, int sno) {

		Map<String, String> durationMap = new HashMap<String, String>();
		durationMap.put("na", "-Please Select-");
		durationMap.put("day", "Day");
		durationMap.put("hour", "Hour");
		durationMap.put("minute", "Minute");
		
		StringBuffer escChildRow = new StringBuffer();

		escChildRow.append("<div class='row esc_row esc_row_style'>");

		escChildRow.append("<div class='col-md-4'>");
		escChildRow.append("<div class='form-group'>");
		escChildRow.append("<label for='esc_department_"+sno+"'>Department</label>");
		escChildRow.append("<div class='position-relative'>");
		escChildRow.append("<select id='esc_department_"+sno+"' class='form-control esc_department select2' placeholder='Department' name='esc_department_"+sno+"' >");
		escChildRow.append("<option value=''>-Please Select-</option>" );
		escChildRow.append(EmployeeCreationHandler.formDepartmentOption(""+escChildDO.getDepartment() ) );
		escChildRow.append("</select></div></div></div>");
		
		escChildRow.append("<div class='col-md-4'>");
		escChildRow.append("<div class='form-group'>");
		escChildRow.append("<label for=''>Designation</label>");
		escChildRow.append("<div class='position-relative'>");
		escChildRow.append("<select id='esc_designation_"+sno+"' class='form-control esc_designation select2' placeholder='Designation' name='esc_designation_"+sno+"' >");
		escChildRow.append("<option value=''>-Please Select-</option>");
		escChildRow.append(EmployeeCreationHandler.formDesignationOption("", ""+escChildDO.getDesignation() ));
		escChildRow.append("</select></div></div></div>");
		
		escChildRow.append("<div class='col-md-4'>");
		escChildRow.append("<div class='form-group'>");
		escChildRow.append("<label for='esc_empId_"+sno+"'>Employee</label>");
		escChildRow.append("<div class='position-relative'>");
		escChildRow.append("<select id='esc_empId_"+sno+"' class='form-control esc_empId select2' placeholder='Employee' name='esc_empId_"+sno+"' >");
		escChildRow.append("<option value=''>-Please Select-</option>");
		escChildRow.append( EmployeeCreationHandler.formEmployeeOption(""+escChildDO.getEmpId() ) );
		escChildRow.append("</select></div></div></div>");
		
		escChildRow.append("<div class='col-md-2'>");
		escChildRow.append("<div class='form-group'>");
		escChildRow.append("<label for='esc_ticketDuration_"+sno+"'>Ticket Duration</label>");
		escChildRow.append("<div class='position-relative '>");
		escChildRow.append("<input type='text' id='esc_ticketDuration_"+sno+"' class='form-control esc_ticketDuration' placeholder='Ticket Duration' name='esc_ticketDuration_"+sno+"' value='"+escChildDO.getTicketDuration()+"'>");
		escChildRow.append("</div></div></div>");
		
		escChildRow.append("<div class='col-md-4'>");
		escChildRow.append("<div class='form-group'>");
		escChildRow.append("<label for='esc_ticketDurationUom_"+sno+"'>&nbsp;</label>");
		escChildRow.append("<div class='position-relative '>");
		escChildRow.append("<select id='esc_ticketDurationUom_"+sno+"' class='form-control esc_ticketDurationUom select2' placeholder='UOM' name='esc_ticketDurationUom_"+sno+"' >");
		escChildRow.append( AppUtil.formOption(durationMap, escChildDO.getTicketDurationUom()) );
		escChildRow.append("</select></div></div></div>");
		
		escChildRow.append("</div>");
		return escChildRow.toString();
	}

	public static void doLoadEscRow(HttpServletRequest request, HttpServletResponse response) {
		AjaxModel model = new AjaxModel();
		int sno=AppUtil.getNullToInteger( request.getParameter("sno") );
		String formName = AppUtil.getNullToEmpty( request.getParameter("formName") );
		model.setData( generateTaskEscChildRow(request, formName, null, new TaskConfigEscalationChildDO(), sno) );
		AjaxUtil.sendResponse(request, response, model);
		
	}



}
