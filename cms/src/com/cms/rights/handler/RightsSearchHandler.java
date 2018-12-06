package com.cms.rights.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.search.SearchEnum;
import com.cms.common.search.util.SearchUtil;

public class RightsSearchHandler {

	public static void doSearch( HttpServletRequest request, HttpServletResponse response ) {
		
		Map<String, String> requestMap =constructRequstMap( request );
		
		String query=constructQuery( requestMap, request, response  );
		SearchUtil.generateSearhResult(request, response, query);
		
		request.setAttribute(SearchEnum.REQUEST_MAP.getKeyName(), requestMap);
	}

	private static String constructQuery(Map<String, String> requestMap, HttpServletRequest request, HttpServletResponse response) {
		
		String rightsGroupName=requestMap.get("rightsGroupName");
		String rightsName=requestMap.get("rightsName");
		String rightsId=requestMap.get("rightsId");
		
		String query="select   rights_master_id, rights_id, rights_group_name, rights_name, bool_delete_status, created_user, created_date, update_user, update_date from rights_master where 0=0 AND bool_delete_status=0  ";
		
		if( !rightsGroupName.isEmpty() ) { query+=" AND rights_group_name like '%"+rightsGroupName+"%' ";  }
		if( !rightsName.isEmpty() ) { query+=" AND rights_name like '%"+rightsName+"%' ";  }
		if( !rightsId.isEmpty() ) { query+=" AND rights_id like '%"+rightsId+"%' ";  }
	
		
		
		query+=" group by rights_master_id ";
		return query;
	}

	private static Map<String, String>  constructRequstMap(HttpServletRequest request) {
		
		Map<String, String> requestMap=new HashMap<String, String>();
		
		String rightsGroupName=AppUtil.getNullToEmpty( request.getParameter("rightsGroupName") );
		String rights_name=AppUtil.getNullToEmpty( request.getParameter("rightsName") );
		String rightsId=AppUtil.getNullToEmpty( request.getParameter("rightsId") );
		
		requestMap.put("rightsGroupName", rightsGroupName);
		requestMap.put("rightsName", rights_name);
		requestMap.put("rightsId", rightsId);
	
		
		return requestMap;
	}
	
}
