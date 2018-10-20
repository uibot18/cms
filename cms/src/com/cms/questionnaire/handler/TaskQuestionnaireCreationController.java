package com.cms.questionnaire.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.common.master.CmnGroupName;
import com.cms.common.master.bean.CommonMasterDO;
import com.cms.common.master.dao.CommonMasterDAO;
import com.cms.questionnaire.bean.TaskQuestionaireChildDO;
import com.cms.questionnaire.bean.TaskQuestionaireDetailsDO;
import com.cms.questionnaire.dao.TaskQuestionaireDetailsDAO;
import com.cms.user.login.LoginDetail;
import com.cms.user.login.util.LoginUtil;

public class TaskQuestionnaireCreationController {

	public static void doAdd(HttpServletRequest request, HttpServletResponse response) {

		int taskConfigId=AppUtil.getNullToInteger( request.getParameter("taskConfigId")  );

		List<TaskQuestionaireDetailsDO> taskQuestionaireList=TaskQuestionaireDetailsDAO.getTaskQuestionaireDetails(null, taskConfigId, true);
		if(taskQuestionaireList==null) { taskQuestionaireList=new ArrayList<TaskQuestionaireDetailsDO>(); }

		request.setAttribute("taskConfigId", ""+taskConfigId);
		request.setAttribute("taskQuestionaireList", taskQuestionaireList);
	}

	public static void doSave(HttpServletRequest request, HttpServletResponse response) {

		int taskConfigId=AppUtil.getNullToInteger( request.getParameter("taskConfigId") ); 
		List<TaskQuestionaireDetailsDO> taskQuestionaireList=constructDO(request, response);

		if(TaskQuestionaireDetailsDAO.save( null,  taskQuestionaireList) ) {
			System.out.println("Questionary Saves..!");
			taskQuestionaireList=TaskQuestionaireDetailsDAO.getTaskQuestionaireDetails(null, taskConfigId, true);
			request.setAttribute(PageAlertType.SUCCESS.getType(), "Questionnaire Successfully Saved..!");
		}else {
			request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Questionnaire..!");
		}

		request.setAttribute("taskConfigId", ""+taskConfigId);
		request.setAttribute("taskQuestionaireList", taskQuestionaireList);

	}

	private static List<TaskQuestionaireDetailsDO> constructDO(HttpServletRequest request, HttpServletResponse response) {

		List<TaskQuestionaireDetailsDO> taskQuestionaireList=new ArrayList<TaskQuestionaireDetailsDO>();
		LoginDetail loginDetail =LoginUtil.getLoginDetail(request);
		String loginId=loginDetail.getLoginId();

		int taskConfigId=AppUtil.getNullToInteger( request.getParameter("taskConfigId") ); 

		int questionnaire_rowCount=AppUtil.getNullToInteger( request.getParameter("questionnaire_rowCount") );
		for (int i = 1; i <= questionnaire_rowCount; i++) {

			TaskQuestionaireDetailsDO qustDetDO=new TaskQuestionaireDetailsDO();
			qustDetDO.setQuestionaireId( AppUtil.getNullToInteger( request.getParameter("questionnaireId_"+i) ) );
			qustDetDO.setTaskConfigId( taskConfigId );
			qustDetDO.setQuestionaireName( AppUtil.getNullToEmpty( request.getParameter("questionnaireName_"+i) ) );
			String answerType=AppUtil.getNullToEmpty( request.getParameter("answerType_"+i), "na");
			qustDetDO.setAnswerType( answerType );
			qustDetDO.setDescription( AppUtil.getNullToEmpty( request.getParameter("description_"+i) ) );
			qustDetDO.setBoolDeleteStatus( false );
			qustDetDO.setCreatedUser( loginId );
			qustDetDO.setUpdateUser( loginId );
			System.out.println("answerType:###### "+answerType);

			List<TaskQuestionaireChildDO> questChildList=new ArrayList<TaskQuestionaireChildDO>();

			if(answerType.equals("single") || answerType.equals("multiselect")) {
				int optionRowCount=AppUtil.getNullToInteger( request.getParameter("optionRowCount_"+i) );
				for (int j = 1; j <= optionRowCount; j++) {

					TaskQuestionaireChildDO questChildDO=new TaskQuestionaireChildDO();
					questChildDO.setQuestionaireId( qustDetDO.getQuestionaireId() );
					questChildDO.setOption( AppUtil.getNullToEmpty( request.getParameter("taskOption_"+i+"_"+j) ) );
					questChildDO.setBoolDeleteStatus( false );
					questChildDO.setCreatedUser( loginId );
					questChildDO.setUpdateUser( loginId );

					questChildList.add( questChildDO );
				}
			}
			qustDetDO.setQuestionaryChildList(questChildList);
			taskQuestionaireList.add( qustDetDO );
		}

		return taskQuestionaireList;
	}

	public static String serviceOption( String parentIds, String selServiceIds ) {
		String subQry=" AND cmn_group_id="+CmnGroupName.SERVICE.getGroupId() +" AND parent_id=0 AND bool_delete_status=0 ";
		if(!parentIds.isEmpty() && !parentIds.isEmpty() ) { subQry=" AND parent_id in("+parentIds+")"; }
		Map<String, String> map=CommonMasterDAO.getCommonDetMapBySubQry(null, subQry);

		return AppUtil.formOption(map, selServiceIds);
	}

}
