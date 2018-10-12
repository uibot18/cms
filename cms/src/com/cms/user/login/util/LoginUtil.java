package com.cms.user.login.util;

import javax.servlet.http.HttpServletRequest;

import com.cms.user.login.LoginDetail;
import com.cms.user.login.LoginDetailImpl;
import com.cms.user.login.LoginEnum;

public class LoginUtil {

	public static LoginDetail getLoginDetail( HttpServletRequest request ) {
		LoginDetail detail=null;
		if(request!=null) {
			detail=(LoginDetail) request.getSession().getAttribute( LoginEnum.LOGIN_DETAIL.getType() );
		}
		return detail==null?new LoginDetailImpl():detail;
	}
	
	public static void removeLoginDetail( HttpServletRequest request ) {
		if(request!=null) {
			request.getSession().removeAttribute( LoginEnum.LOGIN_DETAIL.getType() );
		}
	}
}
