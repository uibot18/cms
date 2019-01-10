package com.cms.common.master.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.search.SearchEnum;
import com.cms.common.search.util.SearchUtil;

public class CommonMasterSearchHandler {

	
	public static void doSearch( HttpServletRequest request, HttpServletResponse response ) {

		Map<String, String> requestMap =constructRequstMap( request );

		String query=constructQuery( requestMap, request, response  );
		SearchUtil.generateSearhResult(request, response, query);

		request.setAttribute(SearchEnum.REQUEST_MAP.getKeyName(), requestMap);
	}
	
	
	private static String constructQuery(Map<String, String> requestMap, HttpServletRequest request, HttpServletResponse response) {

		

		String query="select   profile_id, profile_name, profile_description, profile_rights, created_by, created_on, updated_by, updated_on from profile_master ";
		return query;
	}

	private static Map<String, String>  constructRequstMap(HttpServletRequest request) {

		Map<String, String> requestMap=new HashMap<String, String>();


		return requestMap;
	}
}
