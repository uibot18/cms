package com.cms.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.cms.user.login.LoginDetail;
import com.cms.user.login.util.LoginUtil;

public class CMSFilter implements Filter {

	private static List<String> openURLs=new ArrayList<String>();
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		openURLs.add("login");
		System.out.println("Initialized....");
		
	}
	
	@Override
	public void destroy() {
		System.out.println("destroyed....");
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request= (HttpServletRequest) servletRequest;
		LoginDetail loginDetail=LoginUtil.getLoginDetail(request) ;
		System.out.println("URI: "+request.getRequestURI());
		
		String uri = request.getRequestURI();
		String url="";
		if(!uri.equals("/cms/") && uri.contains("/")) { url=uri.substring(uri.lastIndexOf("/")+1); }
		
		if(loginDetail!=null && !loginDetail.getLoginId().isEmpty() ||  url.isEmpty() || openURLs.contains(url)  ) {
			if(request.getRequestURI().equals("/cms/") ) {
				request.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
			}else {
				filterChain.doFilter(servletRequest, servletResponse);
			}
		}else {
			request.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
		}
	}
}
