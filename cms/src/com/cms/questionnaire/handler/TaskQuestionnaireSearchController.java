package com.cms.questionnaire.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.master.CmnGroupName;
import com.cms.common.search.SearchEnum;
import com.cms.common.search.util.SearchUtil;

public class TaskQuestionnaireSearchController {

	public static void doSearch( HttpServletRequest request, HttpServletResponse response ) {

		Map<String, String> requestMap =constructRequstMap( request );

		String query=constructQuery( requestMap, request, response  );
		SearchUtil.generateSearhResult(request, response, query);

		request.setAttribute(SearchEnum.REQUEST_MAP.getKeyName(), requestMap);
	}

	private static String constructQuery(Map<String, String> requestMap, HttpServletRequest request, HttpServletResponse response) {

		String taskConfigId=AppUtil.getNullToEmpty( requestMap.get("taskConfigId") );
		String questionnaireName=AppUtil.getNullToEmpty( requestMap.get("questionnaireName") );

		String query="SELECT a.task_config_id, b.task_config_name FROM task_questionaire_details a, task_config_master b " + 
				"WHERE a.task_config_id=b.task_config_id "; 

		if( !taskConfigId.isEmpty() && !taskConfigId.equals("0") ) { query+=" AND a.task_config_id in("+taskConfigId+") "; }
		if( !questionnaireName.isEmpty()  ) { query+=" AND b.task_config_name like '"+questionnaireName+"' "; }
		query+="GROUP BY a.task_config_id ";
		return query;
	}

	private static Map<String, String>  constructRequstMap(HttpServletRequest request) {

		Map<String, String> requestMap=new HashMap<String, String>();

		String taskConfigId=AppUtil.getNullToEmpty( request.getParameter("taskConfigId") );
		String questionnaireName=AppUtil.getNullToEmpty( request.getParameter("questionnaireName") );

		requestMap.put("taskConfigId", taskConfigId);
		requestMap.put("questionnaireName", questionnaireName);

		return requestMap;
	}

}
