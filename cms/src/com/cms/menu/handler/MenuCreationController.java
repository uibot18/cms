package com.cms.menu.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AjaxModel;
import com.application.util.AjaxUtil;
import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.menu.bean.MenuMasterDO;
import com.cms.menu.dao.MenuMasterDAO;

public class MenuCreationController {

	public static void doMenuSave(HttpServletRequest request, HttpServletResponse response) {

		MenuMasterDO menuDO=costructDTO( request, response);
		if(menuDO!=null) {
			String duplicate_check=MenuMasterDAO.duplicatecheck(null,menuDO);
			if(duplicate_check.isEmpty()) {
			if(menuDO.getMenuId()==0) {
				//				insert
				int menuId=MenuMasterDAO.insert(null, menuDO);
				if(menuId!=0) {
					menuDO =MenuMasterDAO.getMenuMasterByMenuId(null, menuId, true);
					request.setAttribute(PageAlertType.SUCCESS.getType(), "Menu Detail Successfully Saved..!");
				}else {
					request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Menu Details..!");
				}
			}else {
				//update	
				if( MenuMasterDAO.update(null, menuDO) ) {
					menuDO =MenuMasterDAO.getMenuMasterByMenuId(null, menuDO.getMenuId(), true);
					request.setAttribute(PageAlertType.SUCCESS.getType(), "Menu Detail Successfully Saved..!");
				}else {
					request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Menu Details..!");
				}
			}
			}
			else {
				request.setAttribute(PageAlertType.ERROR.getType(), duplicate_check);
			}
		}else {
			request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Customer Details..!");
		}

		if(menuDO==null) { menuDO=new MenuMasterDO(); }
		request.setAttribute("menuDO", menuDO);

	}

	private static MenuMasterDO costructDTO(HttpServletRequest request, HttpServletResponse response) {

		String loginId="Admin";

//		LoginDetail loginDetail=(LoginDetail) request.getSession().getAttribute( LoginEnum.LOGIN_DETAIL.getType() );

		int menuId=AppUtil.getNullToInteger( request.getParameter("menuId") );
		String menuName=AppUtil.getNullToEmpty( request.getParameter("menuName") );
		String menuAction=AppUtil.getNullToEmpty( request.getParameter("menuAction") );
		MenuMasterDO menuDo=new MenuMasterDO();
		menuDo.setMenuId(menuId);
		menuDo.setMenuName(menuName);
		menuDo.setMenuAction(menuAction);
		menuDo.setCreatedUser(loginId);
		menuDo.setUpdateUser(loginId);
	

		return menuDo;
	}

	public static void doMenuEdit(HttpServletRequest request, HttpServletResponse response) {

		int menuId=AppUtil.getNullToInteger( request.getParameter("menuId") );

		MenuMasterDO menuDO =MenuMasterDAO.getMenuMasterByMenuId(null, menuId, false);
		if(menuDO==null) { menuDO=new MenuMasterDO(); }

		request.setAttribute("menuDO", menuDO);
	}
	
	public static String parentMenuOption(String selValues) {
		
		Map<String, String> menuMap =MenuMasterDAO.loadParentMenuMap(null, "");
		if(menuMap==null) { menuMap=new HashMap<String, String>(); }
		return AppUtil.formOption(menuMap, selValues);

	}

	public static void doMenuDelete(HttpServletRequest request, HttpServletResponse response) {

		int menuId=AppUtil.getNullToInteger( request.getParameter("menuId") );
		String loginid="Admin";
		MenuMasterDO menuDO =new MenuMasterDO();
		menuDO.setMenuId(menuId);
		menuDO.setBoolDeleteStatus(true);
		menuDO.setUpdateUser(loginid);
		AjaxModel model=new AjaxModel();
		if(MenuMasterDAO.deleteupdate(null, menuDO)) {
			model.setMessage(" Deleted Successfully");
		}else {
			model.setMessage(" Unable to Delete");model.setErrorExists(true);
		}
		AjaxUtil.sendResponse(request, response, model);
		
	}
}
