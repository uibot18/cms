package com.cms.menu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.menu.handler.MenuCreationController;
import com.cms.menu.handler.MenuSearchHandler;

public class menuController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public menuController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );

		if(action.equalsIgnoreCase("search")) { 
			MenuSearchHandler.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/menuMasterSearch.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("add")) {
			request.getRequestDispatcher("WEB-INF/jsp/menuMasterAddUpdate.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("save")) {
			MenuCreationController.doMenuSave( request, response );
			request.getRequestDispatcher("WEB-INF/jsp/menuMasterAddUpdate.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("edit")) {
			MenuCreationController.doMenuEdit( request, response );
			request.getRequestDispatcher("WEB-INF/jsp/menuMasterAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("delete")) {
			MenuCreationController.doMenuDelete( request, response );
		}


	}

}
