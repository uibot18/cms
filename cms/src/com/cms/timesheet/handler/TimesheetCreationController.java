package com.cms.timesheet.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AjaxModel;
import com.application.util.AjaxUtil;
import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.common.master.CmnGroupName;
import com.cms.common.master.bean.CommonMasterDO;
import com.cms.common.master.dao.CommonMasterDAO;
import com.cms.timesheet.bean.TaskTimeSheetChildDO;
import com.cms.timesheet.bean.TaskTimeSheetMasterDO;
import com.cms.timesheet.dao.TaskTimeSheetMasterDAO;
import com.cms.user.login.LoginDetail;
import com.cms.user.login.util.LoginUtil;

public class TimesheetCreationController {

	public static void doAdd(HttpServletRequest request, HttpServletResponse response) {
		TaskTimeSheetMasterDO timeSheetMstDO = new TaskTimeSheetMasterDO();
		timeSheetMstDO.setShiftId( 1 );

		request.setAttribute("timeSheetMstDO", timeSheetMstDO);
	}

	public static void doEdit(HttpServletRequest request, HttpServletResponse response) {

		int timeSheetId = AppUtil.getNullToInteger( request.getParameter("timeSheetId")  );
		TaskTimeSheetMasterDO timeSheetMstDO=TaskTimeSheetMasterDAO.getTaskTimeSheetMasterByTimeSheetId(null, timeSheetId, true);
		request.setAttribute("timeSheetMstDO", timeSheetMstDO);
	}

	public static void doSave(HttpServletRequest request, HttpServletResponse response) {

		TaskTimeSheetMasterDO timeSheetMstDO = constructDO(request, response);

		if(timeSheetMstDO.getTimeSheetId()==0) {
			int timeSheetId =TaskTimeSheetMasterDAO.insert(null, timeSheetMstDO);
			if(timeSheetId!=0) {
				System.out.println("Service Inderted...Id:"+timeSheetId);
				timeSheetMstDO=TaskTimeSheetMasterDAO.getTaskTimeSheetMasterByTimeSheetId(null, timeSheetId, true);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "TimeSheet Succesfully Saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save TimeSheet..!");
			}
		}else {
			if(TaskTimeSheetMasterDAO.update(null, timeSheetMstDO)) {
				System.out.println("Service Updated...Id:");
				timeSheetMstDO=TaskTimeSheetMasterDAO.getTaskTimeSheetMasterByTimeSheetId(null, timeSheetMstDO.getTimeSheetId(), true);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "TimeSheet Successfully Saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save TimeSheet..!");
			}
		}
		request.setAttribute("timeSheetMstDO", timeSheetMstDO);
	}

	private static TaskTimeSheetMasterDO constructDO(HttpServletRequest request, HttpServletResponse response) {

		LoginDetail loginDetail = LoginUtil.getLoginDetail(request);
		String loginId = loginDetail.getLoginId();

		TaskTimeSheetMasterDO timeSheetMstDO = new TaskTimeSheetMasterDO();

		timeSheetMstDO.setAssignedTo( AppUtil.getNullToEmpty( loginId ) );
		timeSheetMstDO.setShiftId( AppUtil.getNullToInteger( request.getParameter("shiftId") ) );
		timeSheetMstDO.setStatus( AppUtil.getNullToEmpty( request.getParameter("status"), "pending" ) );

		TreeSet<String> timeset=new TreeSet<String>();

		String[] snoArr = request.getParameterValues( "sno" );
		Set<String> snoSet = AppUtil.convertStrArrayToSet(snoArr);
		List<TaskTimeSheetChildDO> timeSheetChildList = new ArrayList<TaskTimeSheetChildDO>();
		for (String sno : snoSet) {
			TaskTimeSheetChildDO childDO = new TaskTimeSheetChildDO();
			childDO.setTimeSheetChildId( AppUtil.getNullToInteger( request.getParameter("timeSheetChildId_"+sno) ) );
			childDO.setStartTime( AppUtil.getNullToEmpty(request.getParameter("startTime_"+sno), "10/10/1000 00:00:00") );
			childDO.setEndTime( AppUtil.getNullToEmpty(request.getParameter("endTime_"+sno), "10/10/1000 00:00:00") );
			childDO.setRefType( AppUtil.getNullToInteger( request.getParameter("refType_"+sno) ));
			childDO.setParticularsId( AppUtil.getNullToInteger( request.getParameter("particularsId_"+sno) ) );
			childDO.setComments( AppUtil.getNullToEmpty(request.getParameter("comments_"+sno)) );
			childDO.setCreatedUser(loginId);
			childDO.setUpdateUser(loginId);

			timeSheetChildList.add( childDO );
			timeset.add(childDO.getStartTime() ); timeset.add(childDO.getEndTime() );
		}

		String fromDate="01/10/1000 00:00:00";
		String toDate="01/10/1000 00:00:00";

		if(timeset.size() > 0) {
			fromDate = timeset.first();
			toDate = timeset.last();
		}

		timeSheetMstDO.setFromDate( fromDate );
		timeSheetMstDO.setToDate( toDate );
		timeSheetMstDO.setTimeSheetChildList(timeSheetChildList);
		timeSheetMstDO.setCreatedUser(loginId);
		timeSheetMstDO.setUpdateUser(loginId);

		return timeSheetMstDO;
	}


	public static String generateTimeSheetTable(HttpServletRequest request, String formName, TaskTimeSheetMasterDO timeSheetMstDO ) {

		StringBuffer timeSheetTable = new StringBuffer();
		List<TaskTimeSheetChildDO> timeSheetChildList = timeSheetMstDO.getTimeSheetChildList();
		Map<String, String> timeSheetProcessMap = CommonMasterDAO.getCommonDataByGroupId(null, ""+CmnGroupName.TIME_SHEET_PROCESS.getGroupId() );
		if(timeSheetProcessMap==null) { timeSheetProcessMap = new HashMap<>(); }
		if(timeSheetChildList != null && !timeSheetChildList.isEmpty()) {

			int sno = 1;
			for (TaskTimeSheetChildDO timeSheetChildDO : timeSheetChildList) {
				timeSheetTable.append( generateTimeSheetRow(request, formName, sno, timeSheetMstDO, timeSheetChildDO, timeSheetProcessMap) );
				sno++;	
			}
		}else {
			timeSheetTable.append( generateTimeSheetRow(request, formName, 1, timeSheetMstDO, new TaskTimeSheetChildDO(), timeSheetProcessMap) );
		}
		return timeSheetTable.toString();
	}

	private static String generateTimeSheetRow(HttpServletRequest request, String formName, int sno, TaskTimeSheetMasterDO timeSheetMstDO, TaskTimeSheetChildDO timeSheetChildDO, Map<String, String> timeSheetProcessMap) {

		StringBuffer timeSheetRow = new StringBuffer();
		timeSheetRow.append("<tr id='row_"+sno+"'>");

		timeSheetRow.append("<td>"+sno+"");
		timeSheetRow.append("<input type='hidden' name='sno' value='"+sno+"'/>");
		timeSheetRow.append("<input type='hidden' name='timeSheetChildId_"+sno+"' value='"+timeSheetChildDO.getTimeSheetChildId()+"'/>");
		timeSheetRow.append("</td>");

		timeSheetRow.append("<td><div class='form-group'>");
		timeSheetRow.append("<input type='text' id='startTime_"+sno+"' class='form-control input-sm date_picker startTime' placeholder='Start Time' name='startTime_"+sno+"' value='"+timeSheetChildDO.getStartTime()+"' required='required'>");
		timeSheetRow.append("</div></td>");

		timeSheetRow.append("<td><div class='form-group'>");
		timeSheetRow.append("<input type='text' id='endTime_"+sno+"' class='form-control input-sm date_picker endTime' placeholder='End Time' name='endTime_"+sno+"' value='"+timeSheetChildDO.getEndTime()+"' required='required'>");
		timeSheetRow.append("</div></td>	");

		timeSheetRow.append("<td><div class='form-group'>");
		timeSheetRow.append("<select id='"+formName+"_refType_"+sno+"' class='form-control input-sm select2 refType' placeholder='Package Name' name='refType_"+sno+"' required='required'>");
		timeSheetRow.append("<option>-- please Select --</option>"+AppUtil.formOption(timeSheetProcessMap, ""+timeSheetChildDO.getRefType()));
		timeSheetRow.append("</select>");
		timeSheetRow.append("</div></td>");

		Map<String, String> timeSheetProcessChildMap = CommonMasterDAO.getCommonDetMapBySubQry( null, " AND cmn_group_id="+CmnGroupName.TIME_SHEET_PROCESS_CHILD.getGroupId()+" AND parent_id="+timeSheetChildDO.getRefType() );
		if(timeSheetProcessChildMap==null) { timeSheetProcessChildMap = new HashMap<String, String>(); }

		timeSheetRow.append("<td><div class='form-group'>");
		timeSheetRow.append("<select id='"+formName+"_particularsId_"+sno+"' class='form-control input-sm select2 particularsId' placeholder='Process Name' name='particularsId_"+sno+"' required='required'>");
		timeSheetRow.append("<option>-- please Select --</option>"+AppUtil.formOption(timeSheetProcessChildMap, ""+timeSheetChildDO.getParticularsId() ));
		timeSheetRow.append("</select>");
		timeSheetRow.append("</div></td>");

		timeSheetRow.append("<td><div class='form-group'>");
		timeSheetRow.append("<textarea  name='comments_"+sno+"' id='comments_"+sno+"' class='form-control' rows='1' cols='50' placeholder='Comments..' required='required'>"+AppUtil.getNullToEmpty( timeSheetChildDO.getComments() )+"</textarea>");
		timeSheetRow.append("</div></td>");
		timeSheetRow.append("<td><span style='cursor:pointer;' id='del_row_"+sno+"' class='del_row'>Delete</span></td>");
		timeSheetRow.append("</tr>");
		return timeSheetRow.toString();
	}

	public static void doDelete(HttpServletRequest request, HttpServletResponse response) {
		String loginId="Admin";
		int serviceId=AppUtil.getNullToInteger( request.getParameter("serviceId")  );
		CommonMasterDO cmnMstDO =new CommonMasterDO();
		cmnMstDO.setBoolDeleteStatus(true);
		cmnMstDO.setUpdateUser(loginId);
		cmnMstDO.setCmnMasterId(serviceId);

		AjaxModel model=new AjaxModel();
		if(CommonMasterDAO.deleteupdate(null, cmnMstDO)) {
			model.setMessage(" Service Deleted Successfully");
		}else {
			model.setMessage(" Unable to Delete Service");model.setErrorExists(true);
		}
		AjaxUtil.sendResponse(request, response, model);

	}

	public static void doLoadTimeSheetRow(HttpServletRequest request, HttpServletResponse response) {

		try {
			int sno = AppUtil.getNullToInteger( request.getParameter("sno") );
			String formName = AppUtil.getNullToEmpty( request.getParameter("formName") );
			AjaxModel model = new AjaxModel();

			Map<String, String> timeSheetProcessMap = CommonMasterDAO.getCommonDataByGroupId(null, ""+CmnGroupName.TIME_SHEET_PROCESS.getGroupId() );
			if(timeSheetProcessMap == null) { timeSheetProcessMap = new HashMap<String, String>(); }

			model.setData( generateTimeSheetRow(request, formName, sno, null, new TaskTimeSheetChildDO(), timeSheetProcessMap) );
			AjaxUtil.sendResponse(request, response, model);

		} catch (Exception e) { e.printStackTrace(); }

	}

	public static void doLoadParticulars(HttpServletRequest request, HttpServletResponse response) {
		AjaxModel model = new AjaxModel();
		try {
			int refTypeId = AppUtil.getNullToInteger( request.getParameter("refTypeId") );
			Map<String, String> timeSheetProcessChildMap = CommonMasterDAO.getCommonDetMapBySubQry( null, " AND cmn_group_id="+CmnGroupName.TIME_SHEET_PROCESS_CHILD.getGroupId()+" AND parent_id="+refTypeId );
			if(timeSheetProcessChildMap==null) { timeSheetProcessChildMap = new HashMap<String, String>(); }
			model.setData( AppUtil.formOption(timeSheetProcessChildMap, ""));
		} catch (Exception e) { 
			e.printStackTrace(); 
			model.setErrorExists(true);
			model.setMessage("Failed to load Particulars");
		}

		AjaxUtil.sendResponse(request, response, model);
	}
}
