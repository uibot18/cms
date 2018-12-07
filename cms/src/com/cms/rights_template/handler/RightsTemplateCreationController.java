package com.cms.rights_template.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AjaxModel;
import com.application.util.AjaxUtil;
import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.rights_template.bean.RightsTemplateDO;
import com.cms.rights_template.dao.RightsTemplateDAO;

public class RightsTemplateCreationController {

	public static void doRightsTemplateSave(HttpServletRequest request, HttpServletResponse response) {

		RightsTemplateDO rightsTemplateDO=costructDTO( request, response);
		if(rightsTemplateDO!=null) {
			String duplicate_check=RightsTemplateDAO.duplicatecheck(null,rightsTemplateDO);
			if(duplicate_check.isEmpty()) {
			if(rightsTemplateDO.getRightsTemplateId()==0) {
				//				insert
				int rightsTemplateId=RightsTemplateDAO.insert(null, rightsTemplateDO);
				if(rightsTemplateId!=0) {
					rightsTemplateDO =RightsTemplateDAO.getRightsTemplateByRightsTemplateId(null, rightsTemplateId, true);
					request.setAttribute(PageAlertType.SUCCESS.getType(), "Rights Template Details Successfully Saved..!");
				}else {
					request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Rights TEmplate Details..!");
				}
			}else {
				//update	
				if( RightsTemplateDAO.update(null, rightsTemplateDO) ) {
					rightsTemplateDO =RightsTemplateDAO.getRightsTemplateByRightsTemplateId(null, rightsTemplateDO.getRightsTemplateId(), true);
					request.setAttribute(PageAlertType.SUCCESS.getType(), "Rights Template Detail Successfully Saved..!");
				}else {
					request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Rights Template Details..!");
				}
			}
			}
			else {
				request.setAttribute(PageAlertType.ERROR.getType(), duplicate_check);
			}
		}else {
			request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Rights Template Details..!");
		}

		if(rightsTemplateDO==null) { rightsTemplateDO=new RightsTemplateDO(); }
		request.setAttribute("rightsTemplateDO", rightsTemplateDO);

	}

	private static RightsTemplateDO costructDTO(HttpServletRequest request, HttpServletResponse response) {

		String loginId="Admin";

//		LoginDetail loginDetail=(LoginDetail) request.getSession().getAttribute( LoginEnum.LOGIN_DETAIL.getType() );

		int rightsTemplateId=AppUtil.getNullToInteger( request.getParameter("rightsTemplateId") );
		String rightsTemplateName=AppUtil.getNullToEmpty( request.getParameter("rightsTemplateName") );
		RightsTemplateDO rightsTemplateDO=new RightsTemplateDO();
		rightsTemplateDO.setRightsTemplateId(rightsTemplateId);
		rightsTemplateDO.setRightsTemplateName(rightsTemplateName);
		rightsTemplateDO.setMenuIds(AppUtil.convertArrayToString(request.getParameterValues("menuIds"), ","));
		rightsTemplateDO.setRightsIds(AppUtil.convertArrayToString(request.getParameterValues("rightsIds"), ","));

		rightsTemplateDO.setCreatedUser(loginId);
		rightsTemplateDO.setUpdateUser(loginId);
	

		return rightsTemplateDO;
	}

	public static void doRightsTemplateEdit(HttpServletRequest request, HttpServletResponse response) {

		int rightsMasterId=AppUtil.getNullToInteger( request.getParameter("rightsTemplateId") );

		RightsTemplateDO rightsTemplateDO =RightsTemplateDAO.getRightsTemplateByRightsTemplateId(null, rightsMasterId, false);
		if(rightsTemplateDO==null) { rightsTemplateDO=new RightsTemplateDO(); }

		request.setAttribute("rightsTemplateDO", rightsTemplateDO);
	}
	


	public static void doRightsTemplateDelete(HttpServletRequest request, HttpServletResponse response) {

		int rightsTemplateId=AppUtil.getNullToInteger( request.getParameter("rightsTemplateId") );
		String loginid="Admin";
//		LoginDetail loginDetail=(LoginDetail) request.getSession().getAttribute( LoginEnum.LOGIN_DETAIL.getType() );
		RightsTemplateDO rightsTemplateDO =new RightsTemplateDO();
		rightsTemplateDO.setRightsTemplateId(rightsTemplateId);
		rightsTemplateDO.setBoolDeleteStatus(true);
		rightsTemplateDO.setUpdateUser(loginid);
		AjaxModel model=new AjaxModel();
		if(RightsTemplateDAO.deleteupdate(null, rightsTemplateDO)) {
			model.setMessage(" Deleted Successfully");
		}else {
			model.setMessage(" Unable to Delete");model.setErrorExists(true);
		}
		AjaxUtil.sendResponse(request, response, model);
		
	}
}
