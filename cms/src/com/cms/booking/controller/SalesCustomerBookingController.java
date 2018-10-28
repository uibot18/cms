package com.cms.booking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.booking.handler.SalesCustomerBookingCreationHandler;
import com.cms.holiday.handler.HolidaySearchController;
public class SalesCustomerBookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SalesCustomerBookingController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );
		
		if(action.equalsIgnoreCase("search")){
			HolidaySearchController.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/salesCustomerBookingSearch.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("add")){
			request.getRequestDispatcher("WEB-INF/jsp/salesCustomerBookingAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("save")){
			SalesCustomerBookingCreationHandler.doSave(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/salesCustomerBookingAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("edit")){
			SalesCustomerBookingCreationHandler.doEdit(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/salesCustomerBookingAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("loadPackRow")){
			SalesCustomerBookingCreationHandler.doPackageLoad(request, response);
		}
	}

}
