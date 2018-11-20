package com.cms.task.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.search.SearchEnum;
import com.cms.common.search.util.SearchUtil;

public class TaskProcessSearchHandler {

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


		String query="SELECT a.process_master_id, a.task_type, a.sales_id, a.process_master_status, a.created_date, d.customer_id, d.first_name, d.sale_date " + 
				"FROM task_process_master a " + 
				"LEFT JOIN ( SELECT b.sale_id, b.sale_date, b.customer_id, c.first_name "
				+ "FROM sales_customer_booking_form b, sales_customer_master_view c " + 
				"WHERE b.customer_id = c.customer_id ) AS d ON d.sale_id = a.sales_id " ;

		
		if( !serviceName.isEmpty() && !serviceName.equals("0") ) { 
			query+=" AND a.process_master_id IN( SELECT process_master_id FROM task_process_child WHERE service_id="+serviceName+" ) "; 
		}
		if( !packageName.isEmpty() && !packageName.equals("0")  ) {
			query+=" AND a.process_master_id IN( SELECT process_master_id FROM task_process_child WHERE package_id="+packageName+" ) "; 
		}
		if( !processName.isEmpty()  && !processName.equals("0") ) { 
			query+="AND a.process_master_id IN( SELECT process_master_id FROM task_process_child WHERE process_id="+processName+" ) "; 
		}

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
