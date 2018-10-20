package com.cms.customer.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppDateUtil;
import com.application.util.AppUtil;
import com.cms.common.master.bean.CommonDocumentStoreDO;
import com.cms.customer.bean.SalesCustomerMasterDO;
import com.cms.customer.dao.SalesCustomerMasterDAO;
import com.cms.customer.handler.CustomerCreationController;
import com.cms.customer.handler.CustomerSearchHandler;
import com.cms.finance.LedgerRefType;
import com.cms.finance.bean.FinanceLedgerMasterDO;
import com.cms.finance.bean.FinancePartyAddressDetailsDO;
import com.cms.finance.bean.FinancePartyContactDetailsDO;
import com.cms.finance.bean.FinancePartyPersonalDetailsDO;
import com.cms.user.login.LoginDetail;
import com.cms.user.login.LoginEnum;

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

	}

}
