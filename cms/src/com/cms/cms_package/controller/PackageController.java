package com.cms.cms_package.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.cms_package.handler.PackageCreationController;
import com.cms.cms_package.handler.PackageSearchController;
import com.cms.service.handler.ServiceCreationController;
import com.cms.service.handler.ServiceSearchController;
public class PackageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PackageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );
		
		if(action.equalsIgnoreCase("search")){
			PackageSearchController.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/packageMasterSearch.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("add")){
			PackageCreationController.doAdd(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/packageMasterAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("save")){
			PackageCreationController.doSave(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/packageMasterAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("edit")){
			PackageCreationController.doEdit(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/packageMasterAddUpdate.jsp").forward(request, response);
		}
	}

}
