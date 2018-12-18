package com.application.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.cms.menu.bean.MenuMasterDO;
import com.cms.menu.dao.MenuMasterDAO;
import com.cms.navigation.bean.MenuNavigationDO;
import com.cms.navigation.dao.MenuNavigationDAO;
import com.cms.user.login.LoginDetail;
import com.cms.user.login.util.LoginUtil;

public class MenuUtil {


	public static String generateMenuItem( Map<String, MenuNavigationDO> allNavMap, Map<String, MenuMasterDO> allMenuMap, Map<String, String> parentNavMap, String navId, Set<String> mappedMenuSet ) {
		StringBuffer menuItem = new StringBuffer();
		MenuNavigationDO mnuNavDO = allNavMap.get(navId);
		if(mnuNavDO!=null) {
			if(mnuNavDO.getBoolIsMenu()) 
			{
				if( mappedMenuSet.contains( ""+mnuNavDO.getMenuId() ) )
				{
					MenuMasterDO mnuMstDO = allMenuMap.get(""+mnuNavDO.getMenuId());
					menuItem.append("<li class='sidebar-item'> <a class='sidebar-link cms_menu_item' href='"+mnuMstDO.getMenuAction()+"' aria-expanded='false'><i class='mdi mdi-account-multiple'></i><span class='hide-menu'>"+mnuMstDO.getMenuName()+"</span></a></li>");
				}
			}
			else 
			{
				String[] childNavIdArr = AppUtil.getNullToEmpty( parentNavMap.get(navId) ).split(",");
				Set<String> navSet = AppUtil.convertStrArrayToSet(childNavIdArr);
				menuItem.append("<li class='sidebar-item'> <a class='has-arrow sidebar-link' href='javascript:void(0)' aria-expanded='false'><i class='mdi mdi-playlist-plus'></i> <span class='hide-menu'>"+mnuNavDO.getNavigationName()+"</span></a>");
				menuItem.append("<ul aria-expanded='false' class='collapse first-level'>");
				for (String childNavId : navSet) 
				{
					menuItem.append( generateMenuItem(allNavMap, allMenuMap, parentNavMap, childNavId, mappedMenuSet));
				}
				menuItem.append("</ul>");
				menuItem.append("</li>");
			}
		}else {
			System.out.println("");
		}
		return menuItem.toString();
	}



	public static String generateMenuItem(HttpServletRequest request) {
		
		LoginDetail loginDetail = LoginUtil.getLoginDetail(request);
		Set<String> mappedMenuSet = loginDetail.getMenuIdSet();
		if(mappedMenuSet==null) { mappedMenuSet = new HashSet<String>(); }
		
		Map<String, MenuNavigationDO> navMap = new HashMap<String, MenuNavigationDO>();

		List<MenuNavigationDO> navigationList = MenuNavigationDAO.getMenuNavigation(null, false);
		for (MenuNavigationDO menuNavigationDO : navigationList) 
		{
			navMap.put(""+menuNavigationDO.getNavigationId(), menuNavigationDO);
		}

		Map<String, MenuMasterDO> menuMap = new HashMap<String, MenuMasterDO>();
		List<MenuMasterDO> menuList = MenuMasterDAO.getMenuMaster(null, false);

		for (MenuMasterDO menuMasterDO : menuList) 
		{
			menuMap.put(""+menuMasterDO.getMenuId(), menuMasterDO);
		}

		Map<String, String> parentNavMap = MenuNavigationDAO.getParentNavigationMap(null);
		Set<String> set = MenuNavigationDAO.getRootNavigationSet(null);
		StringBuffer menuContainer=new StringBuffer();
		for (String navId : set) 
		{
			menuContainer.append( generateMenuItem(navMap, menuMap, parentNavMap, navId, mappedMenuSet) );
		}
		return menuContainer.toString();
	}
}
