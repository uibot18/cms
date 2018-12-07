package com.cms.rights_template.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.search.SearchEnum;
import com.cms.common.search.util.SearchUtil;

public class RightsTemplateSearchHandler {

	public static void doSearch( HttpServletRequest request, HttpServletResponse response ) {
		
		Map<String, String> requestMap =constructRequstMap( request );
		
		String query=constructQuery( requestMap, request, response  );
		SearchUtil.generateSearhResult(request, response, query);
		
		request.setAttribute(SearchEnum.REQUEST_MAP.getKeyName(), requestMap);
	}

	private static String constructQuery(Map<String, String> requestMap, HttpServletRequest request, HttpServletResponse response) {
		
		String rightsTemplateName=requestMap.get("rightsTemplateName");
		
		String query="select   rights_template_id, rights_template_name, menu_ids, rights_ids, bool_delete_status, created_user, created_date, update_user, update_date from rights_template where 0=0 AND bool_delete_status=0  ";
		
		if( !rightsTemplateName.isEmpty() ) { query+=" AND rights_template_name like '%"+rightsTemplateName+"%' ";  }
		
		query+=" group by rights_template_id ";
		return query;
	}

	private static Map<String, String>  constructRequstMap(HttpServletRequest request) {
		
		Map<String, String> requestMap=new HashMap<String, String>();
		
		String rightsTemplateName=AppUtil.getNullToEmpty( request.getParameter("rightsTemplateName") );
		requestMap.put("rightsTemplateName", rightsTemplateName);
		return requestMap;
	}
	
}
