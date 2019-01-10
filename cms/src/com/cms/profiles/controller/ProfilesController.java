package com.cms.profiles.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.master.handler.CommonMasterSearchHandler;
import com.cms.profiles.handler.ProfilesMasterSearchHandler;

public class ProfilesController extends HttpServlet{

	
	 public ProfilesController() {
	        super();
	    }
	    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );		
		if(action.equalsIgnoreCase("search")) {
			ProfilesMasterSearchHandler.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/profile_search.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("add")) {
			request.getRequestDispatcher("WEB-INF/jsp/profileAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("addPermissions")) {
			request.getRequestDispatcher("WEB-INF/jsp/profilepermissions.jsp").forward(request, response);
		}
		
		
		
		
	}
}
