package com.cms.process.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.master.CmnGroupName;
import com.cms.common.search.SearchEnum;
import com.cms.common.search.util.SearchUtil;

public class ProcessSearchController {

	public static void doSearch( HttpServletRequest request, HttpServletResponse response ) {

		Map<String, String> requestMap =constructRequstMap( request );

		String query=constructQuery( requestMap, request, response  );
		SearchUtil.generateSearhResult(request, response, query);

		request.setAttribute(SearchEnum.REQUEST_MAP.getKeyName(), requestMap);
	}

	private static String constructQuery(Map<String, String> requestMap, HttpServletRequest request, HttpServletResponse response) {

		String serviceName=AppUtil.getNullToEmpty( requestMap.get("serviceName") );
		String packageName=requestMap.get("packageName") ;
		String processName=requestMap.get("processName") ;

		String query="SELECT a.cmn_master_id, a.cmn_group_id, a.parent_id, b.cmn_master_name AS package_name, a.cmn_master_name AS service_name, a.level_no " + 
				"FROM common_master a, common_master b  " + 
				"WHERE a.cmn_group_id="+CmnGroupName.PROCESS.getGroupId()+" AND a.bool_delete_status=0 AND a.parent_id=b.cmn_master_id AND b.cmn_group_id="+CmnGroupName.PACKAGE.getGroupId()+" "
						+ " AND b.bool_delete_status=0 ";

		//if( !serviceName.isEmpty() ) { query+=" AND a.cmn_master_name like'%"+serviceName+"%' "; }
		if( !packageName.isEmpty() && !packageName.equals("0")  ) { query+=" AND a.parent_id= "+packageName; }
		if( !processName.isEmpty() ) { query+=" AND a.cmn_master_name ="+processName+" "; }

		return query;
	}

	private static Map<String, String>  constructRequstMap(HttpServletRequest request) {

		Map<String, String> requestMap=new HashMap<String, String>();

		String serviceName=AppUtil.getNullToEmpty( request.getParameter("serviceName") );
		String packageName=AppUtil.getNullToEmpty( request.getParameter("packageName") );
		String processName=AppUtil.getNullToEmpty( request.getParameter("processName") );

		requestMap.put("serviceName", serviceName);
		requestMap.put("packageName", packageName);
		requestMap.put("processName", processName);

		return requestMap;
	}

}
