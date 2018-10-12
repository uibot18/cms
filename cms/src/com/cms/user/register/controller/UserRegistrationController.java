package com.cms.user.register.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cms.user.login.bean.LoginMasterBean;
import com.cms.user.login.dao.LoginMasterDAO;
public class UserRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserRegistrationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("User registration Called..!");
		String action=request.getParameter("action"); if( action==null ) { action=""; }

		if(action.isEmpty()) {

			request.setAttribute("registerDTO", new LoginMasterBean());
			request.setAttribute("registeredList",  LoginMasterDAO.getLoginMasterList(""));

			request.getRequestDispatcher("WEB-INF/jsp/userRegistration.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("save")) {
			doUserregistration(request, response);
		}

	}

	private void doUserregistration(HttpServletRequest request, HttpServletResponse response) {

		LoginMasterBean loginMasterBean=new LoginMasterBean();

		try {
			loginMasterBean.setLoginId( request.getParameter("loginId") ); 
			loginMasterBean.setPassword(request.getParameter("password"));
			loginMasterBean.setEmail( request.getParameter("email") ); 

			System.out.println("loginMasterBean: "+loginMasterBean);

			boolean isValid=true;

			if(loginMasterBean.getLoginId()==null || loginMasterBean.getLoginId().isEmpty()) { 
				isValid=false;
				request.setAttribute("err_userName", "Invalid User Name");
				System.out.println("Invalid User Name");
			}
			if(loginMasterBean.getPassword()==null || loginMasterBean.getPassword().isEmpty()) { 
				isValid=false;
				request.setAttribute("err_password", "Invalid Passord");
				System.out.println("Invalid Passord");
			}
			if(loginMasterBean.getEmail()==null || loginMasterBean.getEmail().isEmpty()) { 
				isValid=false;
				request.setAttribute("err_email", "Invalid Email");
				System.out.println("Invalid Email");
			}
			if(isValid==true) {
				LoginMasterBean loginBean=LoginMasterDAO.getLoginMasterByLoginid(loginMasterBean.getLoginId()); if(loginBean==null) { loginBean=new LoginMasterBean(); }
				System.out.println("loginBean: "+loginBean);
				if(loginBean.getLoginId()!=null && !loginBean.getLoginId().isEmpty()) {
					isValid=false;
					request.setAttribute("err_userName", "User Name Already Used");
					System.out.println("User Name Already Used");
				}
			}

			int loginId=0;
			if(isValid==true) {
				loginId=LoginMasterDAO.insert(loginMasterBean);
				request.setAttribute("msg", "User Registered Successfully..! ");
			}

			System.out.println("loginId: "+loginId);
			if(loginId!=0) {
				response.sendRedirect("userRegistration");
			}else {
				request.setAttribute("registerDTO", loginMasterBean);
				request.setAttribute("registeredList",  LoginMasterDAO.getLoginMasterList(""));
				request.getRequestDispatcher("WEB-INF/jsp/userRegistration.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
