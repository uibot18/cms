package com.cms.timesheet.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.search.SearchEnum;
import com.cms.common.search.util.SearchUtil;

public class TimesheetSearchController {

	public static void doSearch( HttpServletRequest request, HttpServletResponse response ) {

		Map<String, String> requestMap =constructRequstMap( request );

		String query=constructQuery( requestMap, request, response  );
		SearchUtil.generateSearhResult(request, response, query);

		request.setAttribute(SearchEnum.REQUEST_MAP.getKeyName(), requestMap);
	}

	private static String constructQuery(Map<String, String> requestMap, HttpServletRequest request, HttpServletResponse response) {

		String taskName=AppUtil.getNullToEmpty( requestMap.get("taskName") );
		int assignedTo=AppUtil.getNullToInteger( requestMap.get("assignedTo") );
		String taskType=AppUtil.getNullToEmpty( requestMap.get("taskType") );
		String timeSheetFrom=AppUtil.getNullToEmpty( requestMap.get("timeSheetFrom") );
		String timeSheetTo=AppUtil.getNullToEmpty( requestMap.get("timeSheetTo") );

		String query="SELECT a.time_sheet_id, from_date, to_date, a.assigned_to, b.first_name AS assigned_to_name, \r\n" + 
				"a.shift_id, c.shift_name, a.status, a.approved_by, d.first_name AS approved_by_name, a.approved_on \r\n" + 
				"FROM \r\n" + 
				"(task_time_sheet_master a, adm_employee_master_view b, admin_employee_shift_master c) LEFT JOIN adm_employee_master_view d ON a.approved_by=d.emp_id \r\n" + 
				"WHERE a.assigned_to=b.emp_id AND a.shift_id=c.shift_id AND a.bool_delete_status=0 AND a.bool_delete_status=0 ";

		if( !taskName.isEmpty() ) { query+=" "; }
		if( assignedTo!=0 ) { query+=" AND a.assigned_to="+assignedTo+" "; }

		return query;
	}

	private static Map<String, String>  constructRequstMap(HttpServletRequest request) {

		Map<String, String> requestMap=new HashMap<String, String>();
		String taskName=AppUtil.getNullToEmpty( request.getParameter("taskName") );
		int assignedTo=AppUtil.getNullToInteger( request.getParameter("assignedTo") );
		String taskType=AppUtil.getNullToEmpty( request.getParameter("taskType") );
		String timeSheetFrom=AppUtil.getNullToEmpty( request.getParameter("timeSheetFrom") );
		String timeSheetTo=AppUtil.getNullToEmpty( request.getParameter("timeSheetTo") );

		requestMap.put("taskName", taskName);
		requestMap.put("assignedTo", ""+assignedTo);
		requestMap.put("taskType", taskType);
		requestMap.put("timeSheetFrom", timeSheetFrom);
		requestMap.put("timeSheetTo", timeSheetTo);

		return requestMap;
	}

}
