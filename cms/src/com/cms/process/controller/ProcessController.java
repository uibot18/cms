package com.cms.process.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.process.handler.ProcessCreationController;
import com.cms.process.handler.ProcessSearchController;
public class ProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProcessController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );
		
		if(action.equalsIgnoreCase("search")){
			ProcessSearchController.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/processMasterSearch.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("add")){
			ProcessCreationController.doAdd(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/processMasterAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("save")){
			ProcessCreationController.doSave(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/processMasterAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("edit")){
			ProcessCreationController.doEdit(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/processMasterAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("delete")){
			ProcessCreationController.doDelete(request, response);
		}
	}

}
