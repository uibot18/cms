package com.cms.task.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AjaxModel;
import com.application.util.AjaxUtil;
import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.questionnaire.bean.TaskQuestionaireChildDO;
import com.cms.questionnaire.bean.TaskQuestionaireDetailsDO;
import com.cms.questionnaire.dao.TaskQuestionaireDetailsDAO;
import com.cms.task.bean.TaskMasterDO;
import com.cms.task.bean.TaskMasterQuestionaireDO;
import com.cms.task.dao.TaskMasterDAO;
import com.cms.task.dao.TaskMasterQuestionaireDAO;
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

	public static void doQuestionaire(HttpServletRequest request, HttpServletResponse response) {
		int taskId=AppUtil.getNullToInteger( request.getParameter("taskId") );

		TaskMasterDO taskMstDO=TaskMasterDAO.getTaskMasterByTaskId(null, taskId, false);
		List<TaskQuestionaireDetailsDO> configQuestionnaireList = null;
		if(taskMstDO != null) {
			configQuestionnaireList = TaskQuestionaireDetailsDAO.getTaskQuestionaireDetails(null, taskMstDO.getTaskConfigId(), true);
		}
		if(configQuestionnaireList==null) { configQuestionnaireList=new ArrayList<TaskQuestionaireDetailsDO>(); }

		List<TaskMasterQuestionaireDO> taskMstQuestionnaireList = TaskMasterQuestionaireDAO.getTaskMasterQuestionaire(null, taskId, true);
		if(taskMstQuestionnaireList==null) { taskMstQuestionnaireList = new ArrayList<TaskMasterQuestionaireDO>(); }

		request.setAttribute("configQuestionnaireList", configQuestionnaireList);
		request.setAttribute("taskMstQuestionnaireList", taskMstQuestionnaireList);
		request.setAttribute("taskId", ""+taskId);

	}


	public static String constructQuetionaireData(HttpServletRequest request, List<TaskQuestionaireDetailsDO> configQuestionnaireList, List<TaskMasterQuestionaireDO> taskMstQuestionnaireList ) {

		StringBuffer questionaireData = new StringBuffer();
		Map<String, TaskMasterQuestionaireDO> taskMstQuestMap=new HashMap<String, TaskMasterQuestionaireDO>();
		if(taskMstQuestionnaireList != null) {
			for(TaskMasterQuestionaireDO taskQuestDO: taskMstQuestionnaireList) {
				taskMstQuestMap.put(""+taskQuestDO.getQuestionaireId(), taskQuestDO);
			}
		}

		if(configQuestionnaireList!=null) {
			int sno=1;
			for(TaskQuestionaireDetailsDO questDetDO : configQuestionnaireList) {

				TaskMasterQuestionaireDO taskMstQuestDO = taskMstQuestMap.get( ""+questDetDO.getQuestionaireId());
				questionaireData.append( questionaireRow(request, sno, questDetDO, taskMstQuestDO) );
				sno++;
			}
		}
		return questionaireData.toString();
	}

	private static String questionaireRow(HttpServletRequest request, int sno, TaskQuestionaireDetailsDO questDetDO, TaskMasterQuestionaireDO taskMstQuestDO) {

		if(questDetDO == null) { questDetDO = new TaskQuestionaireDetailsDO(); }
		if(taskMstQuestDO==null) { taskMstQuestDO=new TaskMasterQuestionaireDO(); }

		StringBuffer questRow = new StringBuffer();

		questRow.append("<div style='padding: 5px;'>");
		questRow.append("<input type='hidden' name='taskQuestId_"+sno+"' id='taskQuestId_"+sno+"' value='"+taskMstQuestDO.getTaskQuestId()+"' >");
		questRow.append("<div class='row'>");
		questRow.append("<span>"+AppUtil.getNullToEmpty( questDetDO.getQuestionaireName() )+"</span>");
		questRow.append("<input type='hidden' name='questionnaireId_"+sno+"' id='questionnaireId_"+sno+"' value='"+questDetDO.getQuestionaireId()+"'> ");
		questRow.append("<input type='hidden' name='answerType_"+sno+"' id='answerType_"+sno+"' value='"+questDetDO.getAnswerType()+"' >");
		questRow.append("</div>");
		questRow.append("<div class='row' style='padding: 10px;'>");
		String answerType = AppUtil.getNullToEmpty( questDetDO.getAnswerType() );
		
		if(answerType.equalsIgnoreCase( "single" )) { 
			questRow.append(formSingleOption(sno, questDetDO, taskMstQuestDO) );
		}
		else if(answerType.equalsIgnoreCase( "multiselect" )) {
			questRow.append(formMultipleOption(sno, questDetDO, taskMstQuestDO) );
		}
		else if(answerType.equalsIgnoreCase( "user_text" )) { 
			questRow.append("<div class='form-group'>");
			questRow.append("<textarea  name='option_"+sno+"' id='option_"+sno+"' class='form-control' rows='3' cols='100' placeholder='Text Here...' required='required'>"+AppUtil.getNullToEmpty( taskMstQuestDO.getOptions() )+"</textarea>");
			questRow.append("</div>");
		}
		questRow.append("</div>");
		questRow.append("</div>");

		return questRow.toString();
	}

	private static String formSingleOption(int sno, TaskQuestionaireDetailsDO questDetDO, TaskMasterQuestionaireDO taskMstQuestDO) {

		StringBuffer optionBuf = new StringBuffer();

		for (TaskQuestionaireChildDO optionDO : questDetDO.getQuestionaryChildList()) {

			String option = AppUtil.getNullToEmpty(optionDO.getOption());
			String chk="";
			if(taskMstQuestDO!=null && taskMstQuestDO.getOptions().equalsIgnoreCase(option)) {
				chk="checked='checked'";
			}

			optionBuf.append("<div class='form-check form-check-inline'>");
			optionBuf.append("<input "+chk+" type='radio' name='option_"+sno+"' id='option_"+sno+"'  class='form-check-input'value='"+option+"' required='required'>");
			optionBuf.append("<label class='form-check-label' for='option_"+sno+"'>"+option+"</label>");
			optionBuf.append("</div>");
		}

		return optionBuf.toString();
	}
	private static String formMultipleOption(int sno, TaskQuestionaireDetailsDO questDetDO, TaskMasterQuestionaireDO taskMstQuestDO) {

		StringBuffer optionBuf = new StringBuffer();

		for (TaskQuestionaireChildDO optionDO : questDetDO.getQuestionaryChildList()) {

			String option = AppUtil.getNullToEmpty(optionDO.getOption());

			Set<String> optionSet = null;
			if(taskMstQuestDO!=null) {
				optionSet = AppUtil.convertStrArrayToSet(taskMstQuestDO.getOptions().split(",") );
			}
			if(optionSet == null) { optionSet = new HashSet<String>(); }

			String chk="";
			if(taskMstQuestDO!=null && optionSet.contains(option)) {
				chk="checked='checked'";
			}

			optionBuf.append("<div class='form-check form-check-inline'>");
			optionBuf.append("<input "+chk+" type='checkbox' name='option_"+sno+"' id='option_"+sno+"' class='form-check-input'  value='"+option+"' required='required' data-req-msg='hhhh'>");
			optionBuf.append("<label class='form-check-label' for='option_"+sno+"'>"+option+"</label>");
			optionBuf.append("</div>");
		}

		return optionBuf.toString();
	}

	public static void doQquestionnaireSave(HttpServletRequest request, HttpServletResponse response) {
		int taskId=AppUtil.getNullToInteger( request.getParameter("taskId") );
		List<TaskMasterQuestionaireDO> taskMstQuestionnaireList = null;
		try {

			taskMstQuestionnaireList = construcQuestionnaireList(request, response);
			if(TaskMasterQuestionaireDAO.saveTaskQuestionnaire(null, taskMstQuestionnaireList)) {
				taskMstQuestionnaireList = TaskMasterQuestionaireDAO.getTaskMasterQuestionaire(null, taskId, true);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Questionnaire successfully saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Questionnaire..!");
			}
						
		} catch (Exception e) { e.printStackTrace(); }
		
		TaskMasterDO taskMstDO = TaskMasterDAO.getTaskMasterByTaskId(null, taskId, false);
		if(taskMstDO == null) { taskMstDO = new TaskMasterDO(); }
				
		if(taskMstQuestionnaireList==null) { taskMstQuestionnaireList = new ArrayList<TaskMasterQuestionaireDO>(); }
		
		request.setAttribute("configQuestionnaireList", TaskQuestionaireDetailsDAO.getTaskQuestionaireDetails(null, taskMstDO.getTaskConfigId(), true) );
		request.setAttribute("taskMstQuestionnaireList", taskMstQuestionnaireList );
		request.setAttribute("taskId", ""+taskId);
		
	}

	private static List<TaskMasterQuestionaireDO> construcQuestionnaireList(HttpServletRequest request, HttpServletResponse response) {
		
		LoginDetail loginDetail = LoginUtil.getLoginDetail(request);
		
		List<TaskMasterQuestionaireDO> questionnaireList = new ArrayList<TaskMasterQuestionaireDO>();
		
		int taskId=AppUtil.getNullToInteger( request.getParameter("taskId") );
		int rowCount=AppUtil.getNullToInteger( request.getParameter("rowCount") );
		for (int i=1; i<=rowCount; i++) {
			
			TaskMasterQuestionaireDO questionnaireDO = new TaskMasterQuestionaireDO();
			questionnaireDO.setTaskQuestId( AppUtil.getNullToInteger( request.getParameter("taskQuestId_"+i) ) );
			questionnaireDO.setQuestionaireId( AppUtil.getNullToInteger( request.getParameter("questionnaireId_"+i) ) );
			questionnaireDO.setTaskId( taskId );
			
			String answerType=AppUtil.getNullToEmpty( request.getParameter("answerType_"+i) );
			String options;
			if(answerType.equalsIgnoreCase("multiselect")) {
				String[] optionsArr = request.getParameterValues("option_"+i);
				options = AppUtil.convertArrayToString(optionsArr, ",");
			}else {
				options = AppUtil.getNullToEmpty( request.getParameter("option_"+i) );
			}
			questionnaireDO.setOptions(options);
			questionnaireDO.setCreatedUser( loginDetail.getLoginId() );
			questionnaireDO.setUpdateUser( loginDetail.getLoginId() );
			questionnaireList.add(questionnaireDO);
		}
		return questionnaireList;
	}

	public static void doTaskAction(HttpServletRequest request, HttpServletResponse response) {
		
		LoginDetail loginDetail = LoginUtil.getLoginDetail(request);
		
		String type=AppUtil.getNullToEmpty( request.getParameter("type") );
		int taskId = AppUtil.getNullToInteger( request.getParameter("taskId") );
		
		AjaxModel model = new AjaxModel();
		
		if(TaskMasterDAO.updateTaskAction(null, taskId, loginDetail.getLoginId(), type)) {
			model.setErrorExists(false);
			model.setMessage("Task "+type+"ed");
		}else {
			model.setMessage("Failed to "+type+" Task");
			model.setErrorExists(true);
		}
		AjaxUtil.sendResponse(request, response, model);
		
	}

}
