package com.cms.user.login.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cms.user.login.util.LoginUtil;


public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public LogoutController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginUtil.removeLoginDetail(request);
		response.sendRedirect("login");
		
		/*String action=request.getParameter("action"); if(action==null) { action=""; }
		if(action.equalsIgnoreCase("")) {
			request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
		}*/
	}

	
}
