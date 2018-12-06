package com.cms.navigation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.navigation.handler.NavigationCreationController;
import com.cms.navigation.handler.NavigationSearchHandler;

public class navigationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public navigationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );
System.out.println(action);
		if(action.equalsIgnoreCase("search")) { 
			NavigationSearchHandler.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/navigationMasterSearch.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("add")) {
			request.getRequestDispatcher("WEB-INF/jsp/navigationMasterAddUpdate.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("save")) {
			NavigationCreationController.doNavigationSave( request, response );
			request.getRequestDispatcher("WEB-INF/jsp/navigationMasterAddUpdate.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("edit")) {
			NavigationCreationController.doNavigationEdit( request, response );
			request.getRequestDispatcher("WEB-INF/jsp/navigationMasterAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("delete")) {
			NavigationCreationController.doNavigationDelete( request, response );
		}


	}

}
