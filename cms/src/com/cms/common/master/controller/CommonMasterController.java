package com.cms.common.master.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.master.handler.CommonMasterSearchHandler;

public class CommonMasterController extends HttpServlet{

	 public CommonMasterController() {
	        super();
	    }
	    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );
		
		
	}
}
