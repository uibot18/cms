package com.cms.rights.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AjaxModel;
import com.application.util.AjaxUtil;
import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.rights.bean.RightsMasterDO;
import com.cms.rights.dao.RightsMasterDAO;

public class RightsCreationController {

	public static void doRightsSave(HttpServletRequest request, HttpServletResponse response) {

		RightsMasterDO rightsDO=costructDTO( request, response);
		if(rightsDO!=null) {
			String duplicate_check=RightsMasterDAO.duplicatecheck(null,rightsDO);
			if(duplicate_check.isEmpty()) {
			if(rightsDO.getRightsMasterId()==0) {
				//				insert
				int rightsmasterId=RightsMasterDAO.insert(null, rightsDO);
				if(rightsmasterId!=0) {
					rightsDO =RightsMasterDAO.getRightsMasterByRightsMasterId(null, rightsmasterId, true);
					request.setAttribute(PageAlertType.SUCCESS.getType(), "Rights Detail Successfully Saved..!");
				}else {
					request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Rights Details..!");
				}
			}else {
				//update	
				if( RightsMasterDAO.update(null, rightsDO) ) {
					rightsDO =RightsMasterDAO.getRightsMasterByRightsMasterId(null, rightsDO.getRightsMasterId(), true);
					request.setAttribute(PageAlertType.SUCCESS.getType(), "Rights Detail Successfully Saved..!");
				}else {
					request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Rights Details..!");
				}
			}
			}
			else {
				request.setAttribute(PageAlertType.ERROR.getType(), duplicate_check);
			}
		}else {
			request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Rights Details..!");
		}

		if(rightsDO==null) { rightsDO=new RightsMasterDO(); }
		request.setAttribute("rightsDO", rightsDO);

	}

	private static RightsMasterDO costructDTO(HttpServletRequest request, HttpServletResponse response) {

		String loginId="Admin";

//		LoginDetail loginDetail=(LoginDetail) request.getSession().getAttribute( LoginEnum.LOGIN_DETAIL.getType() );

		int rightsMasterId=AppUtil.getNullToInteger( request.getParameter("rightsMasterId") );
		int rightsId=AppUtil.getNullToInteger( request.getParameter("rightsId") );
		String rightsGroupName=AppUtil.getNullToEmpty( request.getParameter("rightsGroupName") );
		String rightsName=AppUtil.getNullToEmpty( request.getParameter("rightsName") );
		RightsMasterDO rightsDo=new RightsMasterDO();
		rightsDo.setRightsMasterId(rightsMasterId);
		rightsDo.setRightsId(rightsId);
		rightsDo.setRightsGroupName(rightsGroupName);
		rightsDo.setRightsName(rightsName);
		rightsDo.setCreatedUser(loginId);
		rightsDo.setUpdateUser(loginId);
	

		return rightsDo;
	}

	public static void doRightsEdit(HttpServletRequest request, HttpServletResponse response) {

		int rightsMasterId=AppUtil.getNullToInteger( request.getParameter("rightsMasterId") );

		RightsMasterDO rightsDO =RightsMasterDAO.getRightsMasterByRightsMasterId(null, rightsMasterId, false);
		if(rightsDO==null) { rightsDO=new RightsMasterDO(); }

		request.setAttribute("rightsDO", rightsDO);
	}
	


	public static void doRightsDelete(HttpServletRequest request, HttpServletResponse response) {

		int rightsMasterId=AppUtil.getNullToInteger( request.getParameter("rightsMasterId") );
		String loginid="Admin";
		RightsMasterDO rightsDO =new RightsMasterDO();
		rightsDO.setRightsMasterId(rightsMasterId);
		rightsDO.setBoolDeleteStatus(true);
		rightsDO.setUpdateUser(loginid);
		AjaxModel model=new AjaxModel();
		if(RightsMasterDAO.deleteupdate(null, rightsDO)) {
			model.setMessage(" Deleted Successfully");
		}else {
			model.setMessage(" Unable to Delete");model.setErrorExists(true);
		}
		AjaxUtil.sendResponse(request, response, model);
		
	}
}
