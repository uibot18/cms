package com.cms.timesheet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.timesheet.handler.TimesheetCreationController;
import com.cms.timesheet.handler.TimesheetSearchController;
public class TimesheetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TimesheetController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );
		
		if(action.equalsIgnoreCase("search")){
			TimesheetSearchController.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/timesheet/timesheetSearch.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("add")){
			TimesheetCreationController.doAdd(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/timesheet/timesheetAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("save")){
			TimesheetCreationController.doSave(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/timesheet/timesheetAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("edit")){
			TimesheetCreationController.doEdit(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/timesheet/timesheetAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("loadTimeSheetRow")){
			TimesheetCreationController.doLoadTimeSheetRow(request, response);
		}
		else if(action.equalsIgnoreCase("delete")){
			TimesheetCreationController.doDelete(request, response);
		}
		else if(action.equalsIgnoreCase("loadParticulars")){
			TimesheetCreationController.doLoadParticulars(request, response);
		}
		else if(action.equalsIgnoreCase("approval")){
			TimesheetCreationController.doApproval(request, response);
			TimesheetSearchController.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/timesheet/timesheetSearch.jsp").forward(request, response);
		}
		else {
			System.out.println("service not available...");
		}
	}

}
