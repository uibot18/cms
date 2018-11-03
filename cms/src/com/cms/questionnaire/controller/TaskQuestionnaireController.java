package com.cms.questionnaire.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.questionnaire.handler.TaskQuestionnaireCreationController;
import com.cms.questionnaire.handler.TaskQuestionnaireSearchController;
public class TaskQuestionnaireController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TaskQuestionnaireController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );
		
		if(action.equalsIgnoreCase("search")){
			TaskQuestionnaireSearchController.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/taskQuestionaireMasterSearch.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("add")){
			TaskQuestionnaireCreationController.doAdd(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/taskQuestionaireMasterAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("save")){
			TaskQuestionnaireCreationController.doSave(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/taskQuestionaireMasterAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("delete")){
			TaskQuestionnaireCreationController.doDelete(request, response);
		}
	}

}
