package com.cms.task.config.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.task.config.handler.TaskConfigCreationController;
import com.cms.task.config.handler.TaskConfigSearchController;
public class TaskConfigController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TaskConfigController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );
		
		if(action.equalsIgnoreCase("search")){
			TaskConfigSearchController.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/taskConfigSearch.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("add")){
			TaskConfigCreationController.doAdd(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/taskConfigAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("save")){
			TaskConfigCreationController.doSave(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/taskConfigAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("edit")){
			TaskConfigCreationController.doEdit(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/taskConfigAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("view")){
			TaskConfigCreationController.doEdit(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/taskConfigView.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("delete")){
			TaskConfigCreationController.doDelete(request, response);
		}
		else if(action.equalsIgnoreCase("loadEscRow")){
			TaskConfigCreationController.doLoadEscRow(request, response);
		}
	}

}
