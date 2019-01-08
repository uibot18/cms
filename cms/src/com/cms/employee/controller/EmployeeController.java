package com.cms.employee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.employee.handler.EmployeeCreationHandler;
import com.cms.employee.handler.EmployeeSearchHandler;

public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );
		
		if(action.equalsIgnoreCase("search")) {
			EmployeeSearchHandler.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/employeeSearch.jsp").forward(request, response);
		}
		if(action.equalsIgnoreCase("search_new")) {
			EmployeeSearchHandler.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/employeeSearch_new.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("add_new")) {
			request.getRequestDispatcher("WEB-INF/jsp/employeeAddUpdate_new.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("add")) {
			request.getRequestDispatcher("WEB-INF/jsp/employeeAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("save")) {
			EmployeeCreationHandler.saveEmployee( request, response );
			request.getRequestDispatcher("WEB-INF/jsp/employeeAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("save_new")) {
			EmployeeCreationHandler.savenewEmployee( request, response );
			request.getRequestDispatcher("WEB-INF/jsp/employeeAddUpdate_new.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("edit")) {
			EmployeeCreationHandler.doEmployeeEdit(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/employeeAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("view")) {
			EmployeeCreationHandler.doEmployeeEdit(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/employeeView.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("loadBranch")) {
			EmployeeCreationHandler.loadBranch( request, response );
		}
		else if(action.equalsIgnoreCase("delete")) {
			EmployeeCreationHandler.doEmployeeDelete(request, response);
		}
		else if(action.equalsIgnoreCase("employeeRightsSearch")) {
			EmployeeSearchHandler.doEmployeeRightsSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/employeeRightsSearch.jsp").forward(request, response);
		}		
		else if(action.equalsIgnoreCase("employeeRightsMap")) {
			EmployeeCreationHandler.doEmployeeRightsMapping( request, response);
			EmployeeSearchHandler.doEmployeeRightsSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/employeeRightsSearch.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("loadDesignation")) 
		{
			EmployeeCreationHandler.loadEmployeeDesignation(request, response);
		}
		
		
		
		
		
	}

}
