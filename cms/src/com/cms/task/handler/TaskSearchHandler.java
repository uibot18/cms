package com.cms.task.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppDateUtil;
import com.application.util.AppUtil;
import com.cms.common.search.SearchEnum;
import com.cms.common.search.util.SearchUtil;

public class TaskSearchHandler {

	public static void doSearch( HttpServletRequest request, HttpServletResponse response ) {

		Map<String, String> requestMap =constructRequstMap( request );

		String query=constructQuery( requestMap, request, response  );
		SearchUtil.generateSearhResult(request, response, query);

		request.setAttribute(SearchEnum.REQUEST_MAP.getKeyName(), requestMap);
	}

	private static String constructQuery(Map<String, String> requestMap, HttpServletRequest request, HttpServletResponse response) {

		String customerName=AppUtil.getNullToEmpty( requestMap.get("customerName") );
		String taskName=AppUtil.getNullToEmpty( requestMap.get("taskName") );
		String taskCode=AppUtil.getNullToEmpty( requestMap.get("taskCode") );
		String taskStatus=AppUtil.getNullToEmpty( requestMap.get("taskStatus") );
		String assignedTo=AppUtil.getNullToEmpty( requestMap.get("assignedTo") );
		String taskType=AppUtil.getNullToEmpty( requestMap.get("taskType") );
		String taskDateFrom=AppUtil.getNullToEmpty( requestMap.get("taskDateFrom") );
		String taskDateTo=AppUtil.getNullToEmpty( requestMap.get("taskDateTo") );

		taskDateFrom=AppDateUtil.convertToDBDate(taskDateFrom, false, false);
		taskDateTo=AppDateUtil.convertToDBDate(taskDateTo, false, false);

		String query="SELECT a.task_id, a.task_date_from, a.task_date_to, a.task_config_id, b.task_config_name, a.assigned_to, c.first_name, a.task_status " + 
				"FROM task_master a, task_config_master b, adm_employee_master_view c " + 
				"WHERE a.task_config_id=b.task_config_id AND a.assigned_to=c.emp_id " + 
				"AND a.bool_delete_status=0 AND b.bool_delete_status=0 " ;


		if( !customerName.isEmpty() && !customerName.equals("0") ) { 
			query+=" AND a.process_child_id IN( SELECT process_child_id FROM task_process_master a, task_process_child b, sales_customer_booking_form c  "
					+ " WHERE a.process_master_id=b.process_master_id AND a.sales_id=c.sale_id AND a.bool_delete_status=0 "
					+ "AND b.bool_delete_status=0 AND c.customer_id="+customerName+" ) "; 
		}
		if( !taskName.isEmpty() && !taskName.equals("0")  ) {
			query+=" AND a.task_config_id IN( "+taskName+" ) "; 
		}
		if( !taskCode.isEmpty()  && !taskCode.equals("0") ) { 
			query+="AND a.task_id IN( "+taskCode+" ) "; 
		}
		if( !taskStatus.isEmpty() ) { 
			query+="AND a.task_status IN( '"+taskStatus.replace(",", "','")+"' ) "; 
		}
		if( !assignedTo.isEmpty()  && !assignedTo.equals("0") ) { 
			query+="AND a.assigned_to IN( "+assignedTo+" ) "; 
		}
		if( !taskType.isEmpty()  && !taskType.equals("0") ) { 
			query+=" AND a.process_child_id IN( SELECT process_child_id FROM task_process_master a, task_process_child b "
					+ " WHERE a.process_master_id=b.process_master_id AND a.bool_delete_status=0 "
					+ "AND b.bool_delete_status=0 AND a.task_type='"+taskType+"' ) "; 
		}
		if( !taskDateFrom.isEmpty() &&  !taskDateTo.isEmpty() ) { 
			query+=" AND a.task_Date between '"+taskDateFrom+"' and '"+taskDateTo+"' ";
		}
		if( !taskDateFrom.isEmpty() &&  taskDateTo.isEmpty() ) { 
			query+=" AND a.task_Date >= '"+taskDateFrom+"' ";
		}
		if( taskDateFrom.isEmpty() &&  !taskDateTo.isEmpty() ) { 
			query+=" AND a.task_Date <= '"+taskDateTo+"' ";
		}

		return query;
	}

	private static Map<String, String>  constructRequstMap(HttpServletRequest request) {

		Map<String, String> requestMap=new HashMap<String, String>();

		String customerName=AppUtil.getNullToEmpty( request.getParameter("customerName") );
		String taskName=AppUtil.getNullToEmpty( request.getParameter("taskName") );
		String taskCode=AppUtil.getNullToEmpty( request.getParameter("taskCode") );
		String taskStatus=AppUtil.getNullToEmpty( request.getParameter("taskStatus") );
		String assignedTo=AppUtil.getNullToEmpty( request.getParameter("assignedTo") );
		String taskType=AppUtil.getNullToEmpty( request.getParameter("taskType") );
		String taskDateFrom=AppUtil.getNullToEmpty( request.getParameter("taskDateFrom") );
		String taskDateTo=AppUtil.getNullToEmpty( request.getParameter("taskDateTo") );

		requestMap.put("customerName", customerName);
		requestMap.put("taskName", taskName);
		requestMap.put("taskCode", taskCode);
		requestMap.put("taskStatus", taskStatus);
		requestMap.put("assignedTo", assignedTo);
		requestMap.put("taskType", taskType);
		requestMap.put("taskDateFrom", taskDateFrom);
		requestMap.put("taskDateTo", taskDateTo);

		return requestMap;
	}

}
