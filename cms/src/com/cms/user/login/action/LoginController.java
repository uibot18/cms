package com.cms.user.login.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cms.user.login.LoginDetail;
import com.cms.user.login.LoginDetailImpl;
import com.cms.user.login.LoginEnum;
import com.cms.user.login.bean.LoginMasterBean;
import com.cms.user.login.dao.LoginMasterDAO;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action"); if(action==null) { action=""; }
		if(action.equalsIgnoreCase("")) {
			request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("register")) {
			request.getRequestDispatcher("WEB-INF/jsp/userRegistration.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("password-recovery")) {
			request.getRequestDispatcher("WEB-INF/jsp/passwordRcover.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("register_save")) {
			doUserRegistration(request, response);
		}else if(action.equalsIgnoreCase("validate")) {
			doLoginvalidation(request, response);
		}

	}

	private void doLoginvalidation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userName= request.getParameter("userName"); if(userName==null) { userName=""; }
		String password= request.getParameter("password"); if(password==null) { password=""; }
		
		System.out.println("userName: "+userName);
		System.out.println("password: "+password);
		//request.setAttribute("msg", "");
		boolean validLogin=false;
		if(!userName.isEmpty() && !password.isEmpty()) {
			LoginMasterBean loginDTO = LoginMasterDAO.getLoginMasterByLoginid(userName); if(loginDTO==null) { loginDTO= new LoginMasterBean(); }
			System.out.println("loginDTO:"+loginDTO);
			if(password.equals(loginDTO.getPassword())) {
				validLogin=true;
				
				HttpSession session = request.getSession();
				LoginDetailImpl loginDetail=new LoginDetailImpl();
				loginDetail.setLoginId( loginDTO.getLoginId() );
				loginDetail.setRefType( "" );
				loginDetail.setUserName("Vijay");
				session.setAttribute(LoginEnum.LOGIN_DETAIL.getType(), loginDetail );
				
			}
		}
		
		if(validLogin==true) {
			
			
			response.sendRedirect("home");
			//request.getRequestDispatcher("WEB-INF/jsp/home.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
			request.setAttribute("err", "Invalid User Name or Password..!");
		}
		
		
		
	}

	private void doUserRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginMasterBean loginMasterBean=new LoginMasterBean();

		try {
			loginMasterBean.setLoginId( request.getParameter("userName") ); 
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
				response.sendRedirect("login");
//				request.getRequestDispatcher("login").forward(request, response);
			}else {
				request.getRequestDispatcher("WEB-INF/jsp/loginRegister.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
