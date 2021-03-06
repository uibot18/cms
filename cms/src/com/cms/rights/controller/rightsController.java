package com.cms.rights.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.rights.handler.RightsCreationController;
import com.cms.rights.handler.RightsSearchHandler;

public class rightsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public rightsController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );

		if(action.equalsIgnoreCase("search")) { 
			RightsSearchHandler.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/rightsMasterSearch.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("add")) {
			request.getRequestDispatcher("WEB-INF/jsp/rightsMasterAddUpdate.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("save")) {
			RightsCreationController.doRightsSave( request, response );
			request.getRequestDispatcher("WEB-INF/jsp/rightsMasterAddUpdate.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("edit")) {
			RightsCreationController.doRightsEdit( request, response );
			request.getRequestDispatcher("WEB-INF/jsp/rightsMasterAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("delete")) {
			RightsCreationController.doRightsDelete( request, response );
		}


	}

}
