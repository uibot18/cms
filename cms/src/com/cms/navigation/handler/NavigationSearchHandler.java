package com.cms.navigation.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.search.SearchEnum;
import com.cms.common.search.util.SearchUtil;

public class NavigationSearchHandler {

	public static void doSearch( HttpServletRequest request, HttpServletResponse response ) {
		
		Map<String, String> requestMap =constructRequstMap( request );
		
		String query=constructQuery( requestMap, request, response  );
		SearchUtil.generateSearhResult(request, response, query);
		
		request.setAttribute(SearchEnum.REQUEST_MAP.getKeyName(), requestMap);
	}

	private static String constructQuery(Map<String, String> requestMap, HttpServletRequest request, HttpServletResponse response) {
		
		String menuName=requestMap.get("menuName");
		String navigationName=requestMap.get("navigationName");

		
		String query="SELECT   a.navigation_id,a.navigation_name,a.parent_navigation_id,a.bool_is_menu,a.menu_id,a.bool_delete_status,a.created_user,a.created_date,a.update_user,a.update_date ,IFNULL(b.menu_name,'') AS menu_name " + 
				"FROM menu_navigation AS a " + 
				"LEFT JOIN menu_master AS b ON a.menu_id=b.menu_id " + 
				"WHERE 0=0 and a.bool_delete_status=0 ";
		
		if( !navigationName.isEmpty() ) { query+=" AND a.navigation_name like '%"+navigationName+"%' ";  }
		if( !menuName.isEmpty() ) { /*query+=" AND a.menu_id in("+menuName+") "; */ query+=" AND IFNULL(b.menu_name,'') like '%"+menuName+"%' ";}
	
		
		
		query+=" group by a.navigation_id ";
		return query;
	}

	private static Map<String, String>  constructRequstMap(HttpServletRequest request) {
		
		Map<String, String> requestMap=new HashMap<String, String>();
		
		String menuName=AppUtil.getNullToEmpty( request.getParameter("menuName") );
		String navigationName=AppUtil.getNullToEmpty( request.getParameter("navigationName") );
		
		requestMap.put("menuName", menuName);
		requestMap.put("navigationName", navigationName);
	
		
		return requestMap;
	}
	
}
