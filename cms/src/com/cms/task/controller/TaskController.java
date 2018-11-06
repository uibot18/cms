package com.cms.task.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.task.handler.TaskCreationHandler;
import com.cms.task.handler.TaskSearchHandler;
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TaskController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );
		
		if(action.equalsIgnoreCase("search")){
			TaskSearchHandler.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/task/taskSearch.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("add")){
			TaskCreationHandler.doAdd(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/task/taskAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("loadProcessRow")){
			TaskCreationHandler.doLoadProcessRow(request, response);
		}
		else if(action.equalsIgnoreCase("processSave")){
			TaskCreationHandler.doProcessSave(request, response);
		}
		else if(action.equalsIgnoreCase("edit")){
			TaskCreationHandler.doEdit(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/task/taskAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("loadCustomerTask")){
			TaskCreationHandler.generateCustomerTaskTable(request, response);
		}
		
		else {
			System.out.println("service not available..");
		}
	}

}
