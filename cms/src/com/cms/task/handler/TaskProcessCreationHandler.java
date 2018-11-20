package com.cms.task.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AjaxModel;
import com.application.util.AjaxUtil;
import com.application.util.AppDateUtil;
import com.application.util.AppUtil;
import com.application.util.CommonData;
import com.application.util.PageAlertType;
import com.cms.booking.bean.SalesCustomerPackageDetailsDO;
import com.cms.booking.dao.SalesCustomerPackageDetailsDAO;
import com.cms.cms_package.handler.PackageCreationController;
import com.cms.common.master.bean.CommonMasterDO;
import com.cms.employee.dao.AdmEmployeeMasterDAO;
import com.cms.process.handler.ProcessCreationController;
import com.cms.service.handler.ServiceCreationController;
import com.cms.task.bean.TaskMasterDO;
import com.cms.task.bean.TaskProcessChildDO;
import com.cms.task.bean.TaskProcessMasterDO;
import com.cms.task.config.bean.TaskConfigMasterDO;
import com.cms.task.config.dao.TaskConfigMasterDAO;
import com.cms.task.dao.TaskProcessMasterDAO;
import com.cms.user.login.LoginDetail;
import com.cms.user.login.util.LoginUtil;

public class TaskProcessCreationHandler {

	public static void doAdd(HttpServletRequest request, HttpServletResponse response) {
		TaskProcessMasterDO taskProMstDO=new TaskProcessMasterDO();
		String taskType = AppUtil.getNullToEmpty( request.getParameter("taskType"), TaskType.GeneralTask.getType());
		taskProMstDO.setTaskType(taskType);
		request.setAttribute("taskProMstDO", taskProMstDO);
	}

	public static void doEdit(HttpServletRequest request, HttpServletResponse response) {

		int processMasterId=AppUtil.getNullToInteger( request.getParameter("processMasterId")  );
		TaskProcessMasterDO taskProMstDO=TaskProcessMasterDAO.getTaskProcessMasterByProcessMasterId(null, processMasterId, true);
		request.setAttribute("taskProMstDO", taskProMstDO);
	}

	public static void doProcessSave(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String URI="WEB-INF/jsp/task/taskPreview.jsp"; 
			TaskProcessMasterDO taskProMstDO=constructDO(request, response);

			System.out.println("taskConfigDO: "+taskProMstDO);

			if(taskProMstDO.getProcessMasterId()==0) {
				//			insert
				int processMasterId =TaskProcessMasterDAO.processInsert(null, taskProMstDO);
				if(processMasterId!=0) {
					taskProMstDO=TaskProcessMasterDAO.getTaskProcessMasterByProcessMasterId(null, processMasterId, true);
					request.setAttribute(PageAlertType.SUCCESS.getType(), "Task Detail Successfully Saved..!");
				}else {
					request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Task Detail..!");
					URI="WEB-INF/jsp/task/taskAddUpdate.jsp"; 
				}
			}else {
				//			update
				if(TaskProcessMasterDAO.processUpdate(null, taskProMstDO)) {
					taskProMstDO=TaskProcessMasterDAO.getTaskProcessMasterByProcessMasterId(null, taskProMstDO.getProcessMasterId(), true);
					request.setAttribute(PageAlertType.SUCCESS.getType(), "Task Detail Successfully Saved..!");
				}else {
					request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Task Detail..!");
					URI="WEB-INF/jsp/task/taskAddUpdate.jsp"; 
				}
			}
			request.setAttribute("taskProMstDO", taskProMstDO);
			request.getRequestDispatcher(URI).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static TaskProcessMasterDO constructDO(HttpServletRequest request, HttpServletResponse response) {

		LoginDetail loginDetail = LoginUtil.getLoginDetail(request);
		String loginId=loginDetail.getLoginId();

		TaskProcessMasterDO taskProMstDO=new TaskProcessMasterDO();
		taskProMstDO.setProcessMasterId( AppUtil.getNullToInteger( request.getParameter("processMasterId")) );
		taskProMstDO.setTaskType( AppUtil.getNullToEmpty( request.getParameter("taskType"), "general") );
		taskProMstDO.setSalesId( AppUtil.getNullToInteger( request.getParameter("saleId")) );
		taskProMstDO.setProcessMasterStatus( AppUtil.getNullToEmpty( request.getParameter("processMasterStatus"), "pending") );

		String[] rowArr =request.getParameterValues("childRows");
		List<TaskProcessChildDO> taskProcessChildList=new ArrayList<TaskProcessChildDO>();

		for (int i = 0; i < rowArr.length; i++) {
			String sno=rowArr[i];
			TaskProcessChildDO childDO=new TaskProcessChildDO();
			int processChildId = AppUtil.getNullToInteger( request.getParameter("processChildId_"+sno) );
			childDO.setProcessChildId( processChildId );
			childDO.setServiceId( AppUtil.getNullToInteger( request.getParameter("serviceName_"+sno) ) );
			childDO.setPackageId( AppUtil.getNullToInteger( request.getParameter("packageName_"+sno)));
			int processId=AppUtil.getNullToInteger( request.getParameter("processName_"+sno) );
			childDO.setProcessId( processId );
			childDO.setProcessStartsOn(AppUtil.getNullToEmpty(request.getParameter("wef_"+sno)) );
			childDO.setProcessEndsOn( AppUtil.getNullToEmpty(request.getParameter("endsOn_"+sno)) );
			childDO.setBoolOverride( Boolean.parseBoolean( AppUtil.getNullToEmpty(request.getParameter("overide_"+sno), "false")));
			childDO.setCreatedUser(loginId);
			childDO.setUpdateUser(loginId);
			taskProcessChildList.add( childDO );

			/*List<TaskConfigMasterDO> taskConfigList=TaskConfigMasterDAO.getTaskConfigMasterByProcessId(null, processId, false);
			if(taskConfigList==null) { taskConfigList=new ArrayList<TaskConfigMasterDO>(); }
			childDO.setTaskList( convertToTaskMstList(taskConfigList, childDO.getProcessStartsOn(), childDO.getProcessEndsOn()) );*/
		}

		taskProMstDO.setTaskProcessChildList(taskProcessChildList);
		taskProMstDO.setCreatedUser(loginId);
		taskProMstDO.setUpdateUser(loginId);
		return taskProMstDO;
	}

	private static List<TaskMasterDO> convertToTaskMstList(List<TaskConfigMasterDO> taskConfigList, String startDate, String endDate) {

		List<TaskMasterDO> taskList=new ArrayList<TaskMasterDO>(); 

		for (TaskConfigMasterDO taskConfigDO : taskConfigList) {
			taskList.addAll( getTaskList(taskConfigDO, startDate, endDate) );
		}
		return taskList;
	}

	private static List<TaskMasterDO> getTaskList(TaskConfigMasterDO taskConfigDO, String startDate, String endDate) {

		String taskConfigType=taskConfigDO.getRefTaskConfigType();
		TaskMasterDO taskDO=new TaskMasterDO();
		taskDO.set( taskConfigDO );

		List<TaskMasterDO> taskList = new ArrayList<TaskMasterDO>();
		//'daily','weekly','monthly','yearly','holidays','na','event'
		if(taskConfigType.equalsIgnoreCase("daily")) {
			List<String> dateList=null;
			if(taskConfigDO.isBoolDailyEveryWeekDay()) {
				dateList=AppDateUtil.getWeekDaysDateList(startDate, endDate);
			}else {
				taskConfigDO.getDailyEveryDay();
				dateList=AppDateUtil.getDateList(startDate, endDate, taskConfigDO.getDailyEveryDay());
			}
			if(dateList!=null) {
				for (String date : dateList) {
					TaskMasterDO taskDONew=new TaskMasterDO();
					taskDONew.set(taskDO, date, date);
					taskList.add(taskDO);
				}
			}

		}else if(taskConfigType.equalsIgnoreCase("weekly")) {
			List<Integer> dayNoList=new ArrayList<Integer>(); 
			int weekStep=taskConfigDO.getWeeklyEveryWeek();
			String[] dayArr =AppUtil.getNullToEmpty(taskConfigDO.getWeeklyWeekDay()).split(",");
			for (String dayStr : dayArr) {
				dayNoList.add(AppDateUtil.getDayNo(dayStr));
			}

			List<String> dateList=AppDateUtil.getDateList(startDate, endDate, weekStep*7, dayNoList);
			if(dateList!=null) {
				for (String date : dateList) {
					TaskMasterDO taskDONew=new TaskMasterDO();
					taskDONew.set(taskDO, date, date);
					taskList.add(taskDO);
				}
			}

		}else if(taskConfigType.equalsIgnoreCase("monthly")) {
			if(taskConfigDO.isBoolMonthlyDaySpecfic()) {

			}else {

			}

		}else if(taskConfigType.equalsIgnoreCase("yearly")) {

		}else if(taskConfigType.equalsIgnoreCase("holidays")) {

		}else if(taskConfigType.equalsIgnoreCase("event")) {

		}

		return taskList;
	}

	public static String generateChildTable(HttpServletRequest request, String formName, TaskProcessMasterDO processMstDO ) {
		StringBuffer mastTable=new StringBuffer();
		List<TaskProcessChildDO> childList=processMstDO.getTaskProcessChildList();
		if(childList==null) { childList=new ArrayList<TaskProcessChildDO>(); }
		int sno=1;
		for (TaskProcessChildDO childDO : childList) {
			mastTable.append( generateChildRow(request, formName, processMstDO, childDO, sno, processMstDO.getTaskType()) );
			sno++;
		}
		return mastTable.toString();
	}


	private static String generateChildRow(HttpServletRequest request, String formName, TaskProcessMasterDO processMstDO, TaskProcessChildDO childDO, int sno, String taskType ) {
		if(childDO==null) { childDO=new TaskProcessChildDO(); }

		StringBuffer row=new StringBuffer();
		if(taskType.equalsIgnoreCase(TaskType.GeneralTask.getType())) {
			row.append("<tr id='row_"+sno+"'>");

			row.append("<td>");
			row.append("<span class='sno'>"+sno+"</span><input name='childRows' type='hidden' value='"+sno+"'>");
			row.append("<input type='hidden' name='processChildId_"+sno+"' value='"+childDO.getProcessChildId()+"'></td>");
			row.append("</td>");

			row.append("<td><div class='form-group'>");
			row.append("<select id='"+formName+"_serviceName_"+sno+"' class='form-control input-sm select2 serviceName' placeholder='Service Name' name='serviceName_"+sno+"'>");
			row.append("<option>-- please Select --</option>"+ServiceCreationController.serviceOption("", ""+childDO.getServiceId()));
			row.append("</select>");
			row.append("</div></td>");

			row.append("<td><div class='form-group'>");
			row.append("<select id='"+formName+"_packageName_"+sno+"' class='form-control input-sm select2 packageName' placeholder='Package Name' name='packageName_"+sno+"'>");
			row.append("<option>-- please Select --</option>"+PackageCreationController.packageOption(""+childDO.getServiceId(), ""+childDO.getPackageId()));
			row.append("</select>");
			row.append("</div></td>");

			row.append("<td><div class='form-group'>");
			row.append("<select id='"+formName+"_processName_"+sno+"' class='form-control input-sm select2 processName' placeholder='Process Name' name='processName_"+sno+"'>");
			row.append("<option>-- please Select --</option>"+ProcessCreationController.processOption(""+childDO.getPackageId(), ""+childDO.getProcessId()));
			row.append("</select>");
			row.append("</div></td>");

			row.append("<td><div class='form-group'>");
			row.append("<input type='text' id='wef_"+sno+"' class='form-control input-sm date_picker wef' placeholder='W.E.F' name='wef_"+sno+"' value='"+childDO.getProcessEndsOn()+"' required='required'>");
			row.append("</div></td>");

			row.append("<td><div class='form-group'>");
			row.append("<input type='text' id='endsOn_"+sno+"' class='form-control input-sm date_picker endsOn' placeholder='Booking Date' name='endsOn_"+sno+"' value='"+childDO.getProcessEndsOn()+"' required='required'>");
			row.append("</div></td>	");

			row.append("<td><div class='form-check'>");
			row.append("<input type='checkbox' name='overide_"+sno+"' class='form-check-input overide' value='true' id='overide_"+sno+"' "+(childDO.isBoolOverride()?"checked":"")+">");
			row.append("<label class='form-check-label' for='overide_"+sno+"'>Overide</label>");
			row.append("</div></td>");

			row.append("<td><span style='cursor:pointer;' id='del_row_"+sno+"' class='del_row'>Delete</span></td>");

			row.append("</tr>");
		}else {

			row.append("<tr id='row_"+sno+"'>");

			row.append("<td>");
			row.append("<span class='sno'>"+sno+"</span><input name='childRows' type='hidden' value='"+sno+"'>");
			row.append("<input type='hidden' name='processChildId_"+sno+"' value='"+childDO.getProcessChildId()+"'></td>");
			row.append("</td>");

			row.append("<td><div class='form-group'>");
			row.append("<input type='hidden' id='"+formName+"_serviceName_"+sno+"' class='serviceName' value='"+childDO.getServiceId()+"' name='serviceName_"+sno+"'>");
			row.append("<label>"+AppUtil.getCommonMasterValueById(childDO.getServiceId())+"</label>");
			row.append("</div></td>");

			row.append("<td><div class='form-group'>");
			row.append("<input type='hidden' id='"+formName+"_packageName_"+sno+"' class='packageName' value='"+childDO.getPackageId()+"' name='packageName_"+sno+"'>");
			row.append("<label>"+AppUtil.getCommonMasterValueById(childDO.getPackageId())+"</label>");
			row.append("</div></td>");

			row.append("<td><div class='form-group'>");
			row.append("<select id='"+formName+"_processName_"+sno+"' class='form-control input-sm select2 processName' placeholder='Process Name' name='processName_"+sno+"'>");
			row.append("<option>-- please Select --</option>"+ProcessCreationController.processOption(""+childDO.getPackageId(), ""+childDO.getProcessId()));
			row.append("</select>");
			row.append("</div></td>");

			row.append("<td><div class='form-group'>");
			row.append("<input type='text' id='wef_"+sno+"' class='form-control input-sm date_picker wef' placeholder='W.E.F' name='wef_"+sno+"' value='"+childDO.getProcessEndsOn()+"' required='required'>");
			row.append("</div></td>");

			row.append("<td><div class='form-group'>");
			row.append("<input type='text' id='endsOn_"+sno+"' class='form-control input-sm date_picker endsOn' placeholder='Booking Date' name='endsOn_"+sno+"' value='"+childDO.getProcessEndsOn()+"' required='required'>");
			row.append("</div></td>	");

			row.append("<td><div class='form-check'>");
			row.append("<input type='checkbox' name='overide_"+sno+"' class='form-check-input overide' value='true' id='overide_"+sno+"' "+(childDO.isBoolOverride()?"checked":"")+">");
			row.append("<label class='form-check-label' for='overide_"+sno+"'>Overide</label>");
			row.append("</div></td>");

			row.append("<td><span style='cursor:pointer;' id='del_row_"+sno+"' class='del_row'>Delete</span></td>");

			row.append("</tr>");

		}
		return row.toString();
	}
	public static void doLoadProcessRow(HttpServletRequest request, HttpServletResponse response) {
		int sno=AppUtil.getNullToInteger(request.getParameter("sno"));
		String tasktype=AppUtil.getNullToEmpty( request.getParameter("taskType"));
		String formName=AppUtil.getNullToEmpty( request.getParameter("formName"));
		AjaxModel model=new AjaxModel();
		model.setData( generateChildRow(request, formName, null, new TaskProcessChildDO(), sno, tasktype ) );
		AjaxUtil.sendResponse(request, response, model);
	}

	public static String generateTaskPreviewTableDisp(HttpServletRequest request, TaskProcessMasterDO taskProMstDO ) {

		Map<String, String> empMap=AdmEmployeeMasterDAO.loadAllEmpNameMap(null, "");
		if(empMap==null) { empMap=new HashMap<String, String>(); }
		
		String subQuery=" AND task_config_id IN( " + 
				"SELECT c.task_config_id FROM task_process_master a, task_process_child b,  task_master c " + 
				"WHERE a.process_master_id=b.process_master_id AND b.process_child_id=c.process_child_id " + 
				"AND a.bool_delete_status=0 AND b.bool_delete_status=0 AND c.bool_delete_status=0 " + 
				")";
		Map<String, String> taskConfigNameMap=TaskConfigMasterDAO.getTaskNameMapBySubQry(null, subQuery);
		if( taskConfigNameMap==null ) { taskConfigNameMap=new HashMap<String, String>(); }
		
		StringBuffer table=new StringBuffer();

		List<TaskProcessChildDO> processChildList = taskProMstDO.getTaskProcessChildList();
		if(processChildList!=null) {
			int sno=1;
			for(TaskProcessChildDO childDO : processChildList) {
				generateTaskPreviewRowDisp(request, taskProMstDO, childDO, sno, empMap, taskConfigNameMap);
				sno++;
			}
		}
		return table.toString();
	}

	private static String generateTaskPreviewRowDisp(HttpServletRequest request, TaskProcessMasterDO taskProMstDO, TaskProcessChildDO taskProcChildDO, int sno, Map<String, String> empMap, Map<String, String> taskConfigNameMap) {
		StringBuffer row=new StringBuffer();
		
		row.append("<tr>");
		row.append("<td>"+sno+"</td>");
		row.append("<td colspan='4'><span>"+AppUtil.getCommonMasterValueById( taskProcChildDO.getProcessId() )+"</span></td>");
		row.append("</tr>");
		row.append("<tr>");
		row.append("<td colspan='5'>");
		row.append("<table><tbody>");
		List<TaskMasterDO> taskList=taskProcChildDO.getTaskList();
		if(taskList!=null) {
			int childRow=1;
			for(TaskMasterDO taskDO : taskList) {
				row.append( generateTaskPreviewDetDiap(request, taskProMstDO, taskProcChildDO, taskDO, sno, childRow, empMap, taskConfigNameMap) );
				childRow++;
			}
		}
		row.append("</tbody></table>");
		row.append("</td>");
		row.append("</tr>");

		return row.toString();
	}

	private static Object generateTaskPreviewDetDiap(HttpServletRequest request, TaskProcessMasterDO taskProMstDO, TaskProcessChildDO taskProcChildDO, TaskMasterDO taskDO, int sNo1, int sNo2, Map<String, String> empMap, Map<String, String> taskConfigNameMap) {
		StringBuffer childRow=new StringBuffer();

		childRow.append("<tr>");
		childRow.append("<td>"+sNo1+"."+sNo2+"</td>");
		childRow.append("<td><label>"+taskDO.getTaskDateFrom()+"</label></td>");
		childRow.append("<td><label>"+taskDO.getTaskDateTo()+"</label></td>");
		childRow.append("<td><label>"+AppUtil.getNullToEmpty( taskConfigNameMap.get(""+taskDO.getTaskConfigId()) )+"</label></td>");
		childRow.append("<td><label>"+AppUtil.getNullToEmpty( empMap.get(""+taskDO.getAssignedTo()) )+"</label></td>");
		childRow.append("</td>");
		childRow.append("</tr>");

		return childRow.toString();
	}

	public static void generateCustomerTaskTable(HttpServletRequest request, HttpServletResponse response) {
		AjaxModel model=new AjaxModel();

		int customerId = AppUtil.getNullToInteger( request.getParameter("customerId") );
		int salesId = AppUtil.getNullToInteger( request.getParameter("salesId") );
		String formName = AppUtil.getNullToEmpty( request.getParameter("formName") );

		String subQry=" AND sales_id IN( " + 
				"	SELECT sale_id FROM sales_customer_booking_form " + 
				"	WHERE bool_delete_status=0 AND customer_id="+customerId
				+ " AND sale_id=" + salesId+")";

		List<SalesCustomerPackageDetailsDO> customerPackageList = SalesCustomerPackageDetailsDAO.getSalesCustomerPackageDetailsBySubQry(null, subQry, false);
		if(customerPackageList!=null && customerPackageList.size()>0) {

			List<TaskProcessChildDO> processChildList=new ArrayList<TaskProcessChildDO>();

			for (SalesCustomerPackageDetailsDO custPackDO : customerPackageList) {
				TaskProcessChildDO processChildDO=new TaskProcessChildDO();

				CommonMasterDO cmnDO=CommonData.commonMasterData.get(""+custPackDO.getPackageId() );
				if(cmnDO!=null) {
					processChildDO.setServiceId( cmnDO.getParentId() );
					processChildDO.setPackageId( cmnDO.getCmnMasterId() );
					processChildDO.setProcessStartsOn( custPackDO.getProcessStartsFrom() );
					processChildDO.setProcessEndsOn( custPackDO.getProcessEndsOn() );

					processChildList.add( processChildDO );
				}
			}

			TaskProcessMasterDO processDO=new TaskProcessMasterDO();
			processDO.setSalesId(salesId);
			processDO.setTaskType( TaskType.Customer.getType() );
			processDO.setTaskProcessChildList(processChildList);

			model.setData( generateChildTable(request, formName, processDO) );

		}else {
			model.setErrorExists(true);
			model.setMessage("Booking Not found..!");
		}

		AjaxUtil.sendResponse(request, response, model);
	}

	public static void doTaskProcessApproval(HttpServletRequest request, HttpServletResponse response) {
		
		String[] processIdArr = request.getParameterValues("processIds");
		String processIds = AppUtil.getNullToEmpty(AppUtil.convertArrayToString(processIdArr, ","));
		if(!processIds.isEmpty()) {
			boolean flag_updated=TaskProcessMasterDAO.updateApproval(null, processIds );
			request.setAttribute(PageAlertType.SUCCESS.getType(), "Task Detail Successfully Saved..!");
		}else {
			request.setAttribute(PageAlertType.ERROR.getType(), "Task Detail Successfully Saved..!");
		}
		
		
		
	}

}
