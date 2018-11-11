package com.cms.service.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.master.CmnGroupName;
import com.cms.common.search.SearchEnum;
import com.cms.common.search.util.SearchUtil;

public class ServiceSearchController {

	public static void doSearch( HttpServletRequest request, HttpServletResponse response ) {

		Map<String, String> requestMap =constructRequstMap( request );

		String query=constructQuery( requestMap, request, response  );
		SearchUtil.generateSearhResult(request, response, query);

		request.setAttribute(SearchEnum.REQUEST_MAP.getKeyName(), requestMap);
	}

	private static String constructQuery(Map<String, String> requestMap, HttpServletRequest request, HttpServletResponse response) {

		String serviceName=AppUtil.getNullToEmpty( requestMap.get("serviceName") );


		String query="SELECT cmn_master_id, cmn_group_id, parent_id, cmn_master_name AS service_name, level_no " + 
				"FROM common_master WHERE cmn_group_id="+CmnGroupName.SERVICE.getGroupId()+" AND bool_delete_status=0 ";

		if( !serviceName.isEmpty() ) { query+=" AND cmn_master_name like'%"+serviceName+"%' "; }

		return query;
	}

	private static Map<String, String>  constructRequstMap(HttpServletRequest request) {

		Map<String, String> requestMap=new HashMap<String, String>();

		String serviceName=AppUtil.getNullToEmpty( request.getParameter("serviceName") );

		requestMap.put("serviceName", serviceName);

		return requestMap;
	}

}
