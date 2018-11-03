package com.cms.holiday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.holiday.handler.HolidayCreationController;
import com.cms.holiday.handler.HolidaySearchController;
public class HolidayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HolidayController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );
		
		if(action.equalsIgnoreCase("search")){
			HolidaySearchController.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/holidaySearch.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("add")){
			//HolidayTypeSearchController.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/holidayAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("save")){
			HolidayCreationController.doSave(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/holidayAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("edit")){
			HolidayCreationController.doEdit(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/holidayAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("delete")){
			HolidayCreationController.doDelete(request, response);
		}
	}

}
