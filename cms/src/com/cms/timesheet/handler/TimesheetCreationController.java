package com.cms.timesheet.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AjaxModel;
import com.application.util.AjaxUtil;
import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.cms_package.handler.PackageCreationController;
import com.cms.common.master.CmnGroupName;
import com.cms.common.master.bean.CommonMasterDO;
import com.cms.common.master.dao.CommonMasterDAO;
import com.cms.process.handler.ProcessCreationController;
import com.cms.timesheet.bean.TaskTimeSheetChildDO;
import com.cms.timesheet.bean.TaskTimeSheetMasterDO;

public class TimesheetCreationController {

	public static void doAdd(HttpServletRequest request, HttpServletResponse response) {
		CommonMasterDO cmnMstDO =new CommonMasterDO();
		cmnMstDO.setCmnGroupId( CmnGroupName.SERVICE.getGroupId() );
		cmnMstDO.setParentId(0);
		cmnMstDO.setLevelNo(1);
		request.setAttribute("serviceDO", cmnMstDO);
	}
	
	public static void doEdit(HttpServletRequest request, HttpServletResponse response) {

		int serviceId=AppUtil.getNullToInteger( request.getParameter("serviceId")  );
		CommonMasterDO cmnMstDO =CommonMasterDAO.getCommonMasterByCmnMasterId(null, serviceId, false);
		request.setAttribute("serviceDO", cmnMstDO);
	}

	public static void doSave(HttpServletRequest request, HttpServletResponse response) {

		CommonMasterDO serviceDO=constructDO(request, response);

		if(serviceDO.getCmnMasterId()==0) {
			//			insert
			int serviceId =CommonMasterDAO.insert(null, serviceDO);
			if(serviceId!=0) {
				System.out.println("Service Inderted...Id:"+serviceId);
				serviceDO=CommonMasterDAO.getCommonMasterByCmnMasterId(null, serviceId, false);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Service Successfully Saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Service..!");
			}
		}else {
			//			update
			if(CommonMasterDAO.update(null, serviceDO)) {
				System.out.println("Service Updated...Id:");
				serviceDO=CommonMasterDAO.getCommonMasterByCmnMasterId(null, serviceDO.getCmnMasterId(), false);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Service Successfully Saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Service..!");
			}
		}
		request.setAttribute("serviceDO", serviceDO);

	}

	private static CommonMasterDO constructDO(HttpServletRequest request, HttpServletResponse response) {

		String loginId="Admin";
		CommonMasterDO cmnMstDO=new CommonMasterDO();

		cmnMstDO.setCmnMasterId( AppUtil.getNullToInteger( request.getParameter("serviceId") ) );
		cmnMstDO.setCmnGroupId( AppUtil.getNullToInteger( request.getParameter("groupId") ) );
		cmnMstDO.setParentId( AppUtil.getNullToInteger( request.getParameter("parentId") ) );
		cmnMstDO.setCmnMasterName( AppUtil.getNullToEmpty( request.getParameter("serviceName") ) );
		cmnMstDO.setLevelNo( AppUtil.getNullToInteger( request.getParameter("levelNo") )  );
		cmnMstDO.setCreatedUser(loginId);
		cmnMstDO.setUpdateUser(loginId);

		return cmnMstDO;
	}

	
	public static String generateTimeSheetTable(HttpServletRequest request, String formName, TaskTimeSheetMasterDO timeSheetMstDO ) {
		
		StringBuffer timeSheetTable = new StringBuffer();
		
		List<TaskTimeSheetChildDO> timeSheetChildList = timeSheetMstDO.getTimeSheetChildList();
		
		if(timeSheetChildList != null && !timeSheetChildList.isEmpty()) {
			int sno = 1;
			for (TaskTimeSheetChildDO timeSheetChildDO : timeSheetChildList) {
				timeSheetTable.append( generateTimeSheetRow(request, formName, sno, timeSheetMstDO, timeSheetChildDO) );
				sno++;	
			}
		}else {
			timeSheetTable.append( generateTimeSheetRow(request, formName, 1, timeSheetMstDO, new TaskTimeSheetChildDO()) );
		}
		return timeSheetTable.toString();
	}
	
	private static String generateTimeSheetRow(HttpServletRequest request, String formName, int sno, TaskTimeSheetMasterDO timeSheetMstDO, TaskTimeSheetChildDO timeSheetChildDO) {
		
		StringBuffer timeSheetRow = new StringBuffer();
		timeSheetRow.append("<tr id='row_"+sno+"'>");
		timeSheetRow.append("<td>"+sno+"</td>");
		timeSheetRow.append("<td><div class='form-group'>");
		timeSheetRow.append("<input type='text' id='wef_"+sno+"' class='form-control input-sm date_picker wef' placeholder='Start Time' name='wef_"+sno+"' value='"+timeSheetChildDO.getStartTime()+"' required='required'>");
		timeSheetRow.append("</div></td>");

		timeSheetRow.append("<td><div class='form-group'>");
		timeSheetRow.append("<input type='text' id='endsOn_"+sno+"' class='form-control input-sm date_picker endsOn' placeholder='End Time' name='endsOn_"+sno+"' value='"+timeSheetChildDO.getEndTime()+"' required='required'>");
		timeSheetRow.append("</div></td>	");
		
		timeSheetRow.append("<td><div class='form-group'>");
		timeSheetRow.append("<select id='"+formName+"_packageName_"+sno+"' class='form-control input-sm select2 packageName' placeholder='Package Name' name='packageName_"+sno+"'>");
		timeSheetRow.append("<option>-- please Select --</option>");
		timeSheetRow.append("<option>Lunch Break</option>");
		timeSheetRow.append("<option>Task</option>");
		/*timeSheetRow.append("<option>-- please Select --</option>"+PackageCreationController.packageOption("", ""));*/
		timeSheetRow.append("</select>");
		timeSheetRow.append("</div></td>");

		timeSheetRow.append("<td><div class='form-group'>");
		timeSheetRow.append("<select id='"+formName+"_processName_"+sno+"' class='form-control input-sm select2 processName' placeholder='Process Name' name='processName_"+sno+"'>");
		timeSheetRow.append("<option>-- please Select --</option>");
		/*timeSheetRow.append("<option>-- please Select --</option>"+ProcessCreationController.processOption("", ""));*/
		timeSheetRow.append("</select>");
		timeSheetRow.append("</div></td>");
		
		timeSheetRow.append("<td><div class='form-group'>");
		timeSheetRow.append("<textarea  name='option_"+sno+"' id='option_"+sno+"' class='form-control' rows='1' cols='50' placeholder='Comments..' required='required'>"+AppUtil.getNullToEmpty( timeSheetChildDO.getComments() )+"</textarea>");
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
			model.setData( generateTimeSheetRow(request, formName, sno, null, new TaskTimeSheetChildDO()) );
			AjaxUtil.sendResponse(request, response, model);
			
		} catch (Exception e) { e.printStackTrace(); }
		
	}
}
