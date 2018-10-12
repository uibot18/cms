package com.cms.service.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.service.handler.ServiceCreationController;
import com.cms.service.handler.ServiceSearchController;
public class ServiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServiceController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );
		
		if(action.equalsIgnoreCase("search")){
			ServiceSearchController.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/serviceMasterSearch.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("add")){
			ServiceCreationController.doAdd(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/serviceMasterAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("save")){
			ServiceCreationController.doSave(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/serviceMasterAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("edit")){
			ServiceCreationController.doEdit(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/serviceMasterAddUpdate.jsp").forward(request, response);
		}
	}

}
