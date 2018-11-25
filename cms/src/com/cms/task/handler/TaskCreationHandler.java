package com.cms.task.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.task.bean.TaskMasterDO;
import com.cms.task.dao.TaskMasterDAO;
import com.cms.user.login.LoginDetail;
import com.cms.user.login.util.LoginUtil;

public class TaskCreationHandler {

	public static void doAdd(HttpServletRequest request, HttpServletResponse response) {
		
	}

	public static void doEdit(HttpServletRequest request, HttpServletResponse response) {

		int taskId=AppUtil.getNullToInteger( request.getParameter("taskId")  );
		TaskMasterDO taskDO=TaskMasterDAO.getTaskMasterByTaskId(null, taskId, true);
		request.setAttribute("taskDO", taskDO);
	}

	public static void doProcessSave(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			TaskMasterDO taskDO=constructDO(request, response);

			if(taskDO.getTaskId()==0) {
				//			insert
				int taskId =TaskMasterDAO.insert(null, taskDO);
				if(taskId!=0) {
					taskDO=TaskMasterDAO.getTaskMasterByTaskId(null, taskId, true);
					request.setAttribute(PageAlertType.SUCCESS.getType(), "Task Successfully Saved..!");
				}else {
					request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Task..!");
				}
			}else {
				//			update
				if(TaskMasterDAO.updateMinimal(null, taskDO)) {
					taskDO=TaskMasterDAO.getTaskMasterByTaskId(null, taskDO.getTaskId(), true);
					request.setAttribute(PageAlertType.SUCCESS.getType(), "Task Successfully Saved..!");
				}else {
					request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Task..!");
				}
			}
			request.setAttribute("taskDO", taskDO);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static TaskMasterDO constructDO(HttpServletRequest request, HttpServletResponse response) {

		LoginDetail loginDetail = LoginUtil.getLoginDetail(request);
		String loginId=loginDetail.getLoginId();

		TaskMasterDO tasMstDO=new TaskMasterDO();
		tasMstDO.setTaskId( AppUtil.getNullToInteger( request.getParameter("taskId")) );
		tasMstDO.setTaskConfigId( AppUtil.getNullToInteger( request.getParameter("taskConfigId")) );
		tasMstDO.setTaskDate(AppUtil.getNullToEmpty( request.getParameter("taskDate") ) );
		tasMstDO.setTaskDateFrom(AppUtil.getNullToEmpty( request.getParameter("taskDateFrom") ) );
		tasMstDO.setTaskDateTo(AppUtil.getNullToEmpty( request.getParameter("taskDateTo") ) );
		tasMstDO.setAssignedTo(AppUtil.getNullToInteger( request.getParameter("taskAssignedTo")));
		tasMstDO.setCreatedUser(loginId);
		tasMstDO.setUpdateUser(loginId);
		return tasMstDO;
	}

	public static void doDisplay(HttpServletRequest request, HttpServletResponse response) {
		int taskId=AppUtil.getNullToInteger( request.getParameter("taskId")  );
		TaskMasterDO taskDO=TaskMasterDAO.getTaskMasterByTaskId(null, taskId, true);
		request.setAttribute("taskDO", taskDO);
	}



}
