package com.cms.task.config.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.master.CmnGroupName;
import com.cms.common.search.SearchEnum;
import com.cms.common.search.util.SearchUtil;

public class TaskConfigSearchController {

	public static void doSearch( HttpServletRequest request, HttpServletResponse response ) {

		Map<String, String> requestMap =constructRequstMap( request );

		String query=constructQuery( requestMap, request, response  );
		SearchUtil.generateSearhResult(request, response, query);

		request.setAttribute(SearchEnum.REQUEST_MAP.getKeyName(), requestMap);
	}

	private static String constructQuery(Map<String, String> requestMap, HttpServletRequest request, HttpServletResponse response) {

		String serviceName=AppUtil.getNullToEmpty( requestMap.get("serviceName") );
		String packageName=AppUtil.getNullToEmpty( requestMap.get("packageName") );
		String processName=AppUtil.getNullToEmpty( requestMap.get("processName") );


		String query="SELECT a.task_config_id, a.task_config_name, a.exe_order, b.cmn_master_name AS process_name, "
				+ "c.cmn_master_name AS package_name, d.cmn_master_name AS service_name " + 
				"FROM task_config_master a, common_master b, common_master c, common_master d " + 
				"WHERE a.process_id=b.cmn_master_id AND b.cmn_group_id="+CmnGroupName.PROCESS.getGroupId()+ " " + 
				"AND b.parent_id=c.cmn_master_id AND c.cmn_group_id="+CmnGroupName.PACKAGE.getGroupId()+" " + 
				"AND c.parent_id=d.cmn_master_id AND d.cmn_group_id="+CmnGroupName.SERVICE.getGroupId()+ " " ;

		if( !serviceName.isEmpty() && !serviceName.equals("0") ) { query+=" AND d.cmn_master_id="+serviceName+" "; }
		if( !packageName.isEmpty() && !packageName.equals("0")  ) { query+=" AND c.cmn_master_id="+packageName+" "; }
		if( !processName.isEmpty()  && !processName.equals("0") ) { query+=" AND b.cmn_master_id="+processName+" "; }

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
