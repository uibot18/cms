package com.cms.navigation.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AjaxModel;
import com.application.util.AjaxUtil;
import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.navigation.bean.MenuNavigationDO;
import com.cms.navigation.dao.MenuNavigationDAO;
import com.cms.user.login.LoginDetail;
import com.cms.user.login.util.LoginUtil;

public class NavigationCreationController {

	public static void doNavigationSave(HttpServletRequest request, HttpServletResponse response) {

		MenuNavigationDO menunavDO=costructDTO( request, response);
		if(menunavDO!=null) {
			String duplicate_check=MenuNavigationDAO.duplicatecheck(null,menunavDO);
			if(duplicate_check.isEmpty()) {
				if(menunavDO.getNavigationId()==0) {
					//				insert
					int menunavId=MenuNavigationDAO.insert(null, menunavDO);
					if(menunavId!=0) {
						menunavDO =MenuNavigationDAO.getMenuNavigationByNavigationId(null, menunavId, true);
						request.setAttribute(PageAlertType.SUCCESS.getType(), "Navigation Detail Successfully Saved..!");
					}else {
						request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Navigation Details..!");
					}
				}else {
					//update	
					if( MenuNavigationDAO.update(null, menunavDO) ) {
						menunavDO =MenuNavigationDAO.getMenuNavigationByNavigationId(null, menunavDO.getNavigationId(), true);
						request.setAttribute(PageAlertType.SUCCESS.getType(), "Navigation Detail Successfully Saved..!");
					}else {
						request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Navigation Details..!");
					}
				}
			}
			else {
				request.setAttribute(PageAlertType.ERROR.getType(), duplicate_check);
			}
		}else {
			request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Navigation Details..!");
		}

		if(menunavDO==null) { menunavDO=new MenuNavigationDO(); }
		request.setAttribute("menunavDO", menunavDO);

	}

	private static MenuNavigationDO costructDTO(HttpServletRequest request, HttpServletResponse response) {

		LoginDetail loginDetail = LoginUtil.getLoginDetail(request);
		String loginId=loginDetail.getLoginId();

		int navigationId=AppUtil.getNullToInteger( request.getParameter("navigationId") );
		int parent_navigationId=AppUtil.getNullToInteger( request.getParameter("parent_navigationId") );
		String navigationName=AppUtil.getNullToEmpty( request.getParameter("navigationName") );
		String is_menu=AppUtil.getNullToEmpty( request.getParameter("isMenu") );
		int menuId=AppUtil.getNullToInteger( request.getParameter("menuId") );
		int menuOrder=AppUtil.getNullToInteger( request.getParameter("menuOrder") );

		MenuNavigationDO menunavDo=new MenuNavigationDO();
		menunavDo.setNavigationId(navigationId);
		menunavDo.setParentNavigationId(parent_navigationId);
		menunavDo.setMenuId(menuId);
		menunavDo.setNavigationName(navigationName);
		menunavDo.setMenuOrder( menuOrder );
		menunavDo.setBoolIsMenu(is_menu.equalsIgnoreCase("1")?true:false);
		menunavDo.setCreatedUser(loginId);
		menunavDo.setUpdateUser(loginId);


		return menunavDo;
	}

	public static void doNavigationEdit(HttpServletRequest request, HttpServletResponse response) {

		int navigationId=AppUtil.getNullToInteger( request.getParameter("navigationId") );

		MenuNavigationDO menunavDO =MenuNavigationDAO.getMenuNavigationByNavigationId(null, navigationId, false);
		if(menunavDO==null) { menunavDO=new MenuNavigationDO(); }
		request.setAttribute("menunavDO", menunavDO);


	}

	public static String parentNavigationOption(String selValues,String navigation_id) {

		Map<String, String> menuMap =MenuNavigationDAO.loadParentnavigationMap(null, navigation_id);
		if(menuMap==null) { menuMap=new HashMap<String, String>(); }
		return AppUtil.formOption(menuMap, selValues);

	}

	public static void doNavigationDelete(HttpServletRequest request, HttpServletResponse response) {

		int navigationId=AppUtil.getNullToInteger( request.getParameter("navigationId") );
		String loginid="Admin";
		MenuNavigationDO menunavDO =new MenuNavigationDO();
		menunavDO.setNavigationId(navigationId);
		menunavDO.setBoolDeleteStatus(true);
		menunavDO.setUpdateUser(loginid);
		AjaxModel model=new AjaxModel();
		if(MenuNavigationDAO.deleteupdate(null, menunavDO)) {
			model.setMessage(" Deleted Successfully");
		}else {
			model.setMessage(" Unable to Delete");model.setErrorExists(true);
		}
		AjaxUtil.sendResponse(request, response, model);

	}
}
