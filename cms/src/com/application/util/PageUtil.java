package com.application.util;

import javax.servlet.http.HttpServletRequest;

public class PageUtil {

	public static String getAlert(HttpServletRequest request) {
		
		for (PageAlertType type : PageAlertType.values()) {
			
			String content=AppUtil.getNullToEmpty( (String)request.getAttribute( type.getType() ) );
			if(content!=null && !content.isEmpty()) {
				return getAlert(type, content);
			}
		}
		return "";
	}

	private static String getAlert(PageAlertType type, String content) {

		String alert="";

		switch (type) {
		case SUCCESS:
			alert="<div class='alert alert-success alert-dismissible mb-2' role='alert'>\r\n" + 
					"	<button type='button' class='close' data-dismiss='alert' aria-label='Close'>\r\n" + 
					"		<span aria-hidden='true'>&times;</span>\r\n" + 
					"	</button>\r\n" + 
					"	<strong>Success!</strong> " + AppUtil.getNullToEmpty(content)+
					//"	<a href='#' class='alert-link'>important</a> alert message.\r\n" + 
					"</div>";
			break;
		case ERROR:
			alert="<div class='alert alert-danger alert-dismissible mb-2' role='alert'>\r\n" + 
					"	<button type='button' class='close' data-dismiss='alert' aria-label='Close'>\r\n" + 
					"		<span aria-hidden='true'>&times;</span>\r\n" + 
					"	</button>\r\n" + 
					"	<strong>Error!</strong> " + AppUtil.getNullToEmpty(content)+
					//"	<a href='#' class='alert-link'>important</a> alert message.\r\n" + 
					"</div>";
			break;
		
		}

		return alert;

	}
}
