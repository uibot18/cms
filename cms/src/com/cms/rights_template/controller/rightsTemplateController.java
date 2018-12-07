package com.cms.rights_template.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.rights_template.handler.RightsTemplateCreationController;
import com.cms.rights_template.handler.RightsTemplateSearchHandler;

public class rightsTemplateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public rightsTemplateController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );

		if(action.equalsIgnoreCase("search")) { 
			RightsTemplateSearchHandler.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/rightsTemplateSearch.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("add")) {
			request.getRequestDispatcher("WEB-INF/jsp/rightsTemplateAddUpdate.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("save")) {
			RightsTemplateCreationController.doRightsTemplateSave( request, response );
			request.getRequestDispatcher("WEB-INF/jsp/rightsTemplateAddUpdate.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("edit")) {
			RightsTemplateCreationController.doRightsTemplateEdit( request, response );
			request.getRequestDispatcher("WEB-INF/jsp/rightsTemplateAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("delete")) {
			RightsTemplateCreationController.doRightsTemplateDelete( request, response );
		}


	}

}
