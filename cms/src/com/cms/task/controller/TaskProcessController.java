package com.cms.task.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.task.handler.TaskProcessCreationHandler;
import com.cms.task.handler.TaskProcessSearchHandler;
public class TaskProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TaskProcessController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );
		
		if(action.equalsIgnoreCase("search")){
			TaskProcessSearchHandler.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/task/taskProcessSearch.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("add")){
			TaskProcessCreationHandler.doAdd(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/task/taskProcessAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("loadProcessRow")){
			TaskProcessCreationHandler.doLoadProcessRow(request, response);
		}
		else if(action.equalsIgnoreCase("processSave")){
			TaskProcessCreationHandler.doProcessSave(request, response);
		}
		else if(action.equalsIgnoreCase("edit")){
			TaskProcessCreationHandler.doEdit(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/task/taskProcessAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("loadCustomerTask")){
			TaskProcessCreationHandler.generateCustomerTaskTable(request, response);
		}
		else if(action.equalsIgnoreCase("approveTaskProcess")){
			TaskProcessCreationHandler.doTaskProcessApproval(request, response);
			TaskProcessSearchHandler.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/task/taskProcessSearch.jsp").forward(request, response);
		}
		
		else {
			System.out.println("service not available..");
		}
	}

}
