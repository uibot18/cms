package com.cms.holiday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.holiday.handler.HolidayTypeCreationController;
import com.cms.holiday.handler.HolidayTypeSearchController;
public class HolidayTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HolidayTypeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );
		
		if(action.equalsIgnoreCase("search")){
			HolidayTypeSearchController.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/holidayTypeSearch.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("add")){
			//HolidayTypeSearchController.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/holidayTypeAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("save")){
			HolidayTypeCreationController.doSave(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/holidayTypeAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("edit")){
			HolidayTypeCreationController.doEdit(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/holidayTypeAddUpdate.jsp").forward(request, response);
		}
	}

}
