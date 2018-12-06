package com.cms.menu.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.search.SearchEnum;
import com.cms.common.search.util.SearchUtil;

public class MenuSearchHandler {

	public static void doSearch( HttpServletRequest request, HttpServletResponse response ) {
		
		Map<String, String> requestMap =constructRequstMap( request );
		
		String query=constructQuery( requestMap, request, response  );
		SearchUtil.generateSearhResult(request, response, query);
		
		request.setAttribute(SearchEnum.REQUEST_MAP.getKeyName(), requestMap);
	}

	private static String constructQuery(Map<String, String> requestMap, HttpServletRequest request, HttpServletResponse response) {
		
		String menuName=requestMap.get("menuName");
		String menuAction=requestMap.get("menuAction");

		
		String query="select   menu_id, menu_name, menu_action, bool_delete_status, created_user, created_date, update_user, update_date from menu_master where 0=0 AND bool_delete_status=0  ";
		
		if( !menuName.isEmpty() ) { query+=" AND menu_name like '%"+menuName+"%' ";  }
		if( !menuAction.isEmpty() ) { query+=" AND menu_action like '%"+menuAction+"%' ";  }
	
		
		
		query+=" group by menu_id ";
		return query;
	}

	private static Map<String, String>  constructRequstMap(HttpServletRequest request) {
		
		Map<String, String> requestMap=new HashMap<String, String>();
		
		String menuName=AppUtil.getNullToEmpty( request.getParameter("menuName") );
		String menuAction=AppUtil.getNullToEmpty( request.getParameter("menuAction") );
		
		requestMap.put("menuName", menuName);
		requestMap.put("menuAction", menuAction);
	
		
		return requestMap;
	}
	
}
