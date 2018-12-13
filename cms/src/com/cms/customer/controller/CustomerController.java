package com.cms.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.customer.handler.CustomerCreationController;
import com.cms.customer.handler.CustomerSearchHandler;

public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );

		if(action.equalsIgnoreCase("search")) { 
			CustomerSearchHandler.doSearch(request, response);
			request.getRequestDispatcher("WEB-INF/jsp/customerSearch.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("add")) {
			request.getRequestDispatcher("WEB-INF/jsp/customerAddUpdate.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("save")) {
			CustomerCreationController.doCustomerSave( request, response );
			request.getRequestDispatcher("WEB-INF/jsp/customerAddUpdate.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("edit")) {
			CustomerCreationController.doCustomerEdit( request, response );
			request.getRequestDispatcher("WEB-INF/jsp/customerAddUpdate.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("delete")) {
			CustomerCreationController.doCustomerDelete( request, response );
		}
		else if(action.equalsIgnoreCase("view")) {
			CustomerCreationController.doCustomerEdit( request, response );
			request.getRequestDispatcher("WEB-INF/jsp/customerView.jsp").forward(request, response);
		}

	}

}
