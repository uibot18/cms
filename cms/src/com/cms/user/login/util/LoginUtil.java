package com.cms.user.login.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.menu.dao.MenuMasterDAO;
import com.cms.rights.dao.RightsMasterDAO;
import com.cms.rights_template.bean.RightsTemplateDO;
import com.cms.rights_template.dao.RightsTemplateDAO;
import com.cms.user.login.LoginDetail;
import com.cms.user.login.LoginDetailImpl;
import com.cms.user.login.LoginEnum;
import com.cms.user.login.UserType;
import com.cms.user.login.bean.AdminLoginMasterDO;
import com.cms.user.login.dao.AdminLoginMasterDAO;
import com.cms.user.login.dao.LoginMasterDAO;

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
	
	
	public static void loadUserDetail(HttpServletRequest request, HttpServletResponse response, AdminLoginMasterDO loginMasterDO, LoginDetailImpl loginDetail) {
		
		String refType = AppUtil.getNullToEmpty( loginMasterDO.getRefType() );
		int refId = loginMasterDO.getRefId();
		loginDetail.setLoginId( loginMasterDO.getLoginId() );
		loginDetail.setRefType( refType );
		loginDetail.setRefId( refId );
		
		if( loginMasterDO.getRefType().equalsIgnoreCase( UserType.ADMIN.getType() ) ) 
		{
			loginDetail.setMenuIdSet( MenuMasterDAO.getMenuIdSet( null ) );
			loginDetail.setRightsIdSet( RightsMasterDAO.getAllRightsIdSet( null ) );
			loginDetail.setUserName("ADMIN");
		}
		else 
		{
			RightsTemplateDO templateDO = RightsTemplateDAO.getRightsTemplateByRightsTemplateId(null, loginMasterDO.getRightsTemplateId(), false);
			if(templateDO != null)
			{
				String[] menuIdArr = AppUtil.getNullToEmpty( templateDO.getMenuIds() ).split(",");
				loginDetail.setMenuIdSet( AppUtil.convertStrArrayToSet( menuIdArr ) );

				String[] rightsIdArr = AppUtil.getNullToEmpty( templateDO.getRightsIds() ).split(",");
				loginDetail.setRightsIdSet( AppUtil.convertStrArrayToSet( rightsIdArr ) );
			}
			
			Map<String, String> userDetail = AdminLoginMasterDAO.loadUserDetail(null, refType, refId );
			if(userDetail != null) {
				loginDetail.setUserName( AppUtil.getNullToEmpty( userDetail.get("USER_NAME") ) );
			}
			
		}
		
	}
	
	
	
}
