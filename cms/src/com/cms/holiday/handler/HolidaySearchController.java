package com.cms.holiday.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.search.SearchEnum;
import com.cms.common.search.util.SearchUtil;

public class HolidaySearchController {

	public static void doSearch( HttpServletRequest request, HttpServletResponse response ) {

		Map<String, String> requestMap =constructRequstMap( request );

		String query=constructQuery( requestMap, request, response  );
		SearchUtil.generateSearhResult(request, response, query);

		request.setAttribute(SearchEnum.REQUEST_MAP.getKeyName(), requestMap);
	}

	private static String constructQuery(Map<String, String> requestMap, HttpServletRequest request, HttpServletResponse response) {

		String customerName=AppUtil.getNullToEmpty( requestMap.get("customerName") );
		String contactPerson=AppUtil.getNullToEmpty( requestMap.get("contactPerson") );
		String serviceName=AppUtil.getNullToEmpty( requestMap.get("serviceName") );
		String packageName=AppUtil.getNullToEmpty( requestMap.get("packageName") );
		
		/*holidayDate=AppDateUtil.convertToDBDate(holidayDate, false, false);*/
		

		String query=" SELECT a.sale_id, a.sale_date, c.first_name, IFNULL(c.off_email1, c.off_email2) email, IFNULL(c.off_mobile1, c.off_mobile2) AS mobile " + 
				"FROM sales_customer_booking_form a, sales_customer_package_details b, sales_customer_master_view c " + 
				"WHERE a.sale_id=b.sales_id AND a.bool_delete_status=0 AND b.bool_delete_status=0 " + 
				"AND a.customer_id=c.customer_id ";

		if( !customerName.isEmpty() ) { query+=" AND c.first_name like '%"+customerName+"%' "; }
		//if( !contactPerson.isEmpty() ) { query+=" AND c.first_name like '%"+contactPerson+"%' "; }
		if( !serviceName.isEmpty() && !serviceName.equals("0") ) { query+=" AND b.package_id in( SELECT cmn_master_id FROM common_master WHERE parent_id="+serviceName+" )  "; }
		if( !packageName.isEmpty() && !packageName.equals("0") ) { query+=" AND b.package_id in( "+packageName+" )  "; }
		query+=" GROUP BY a.sale_id ";
		return query;
	}

	private static Map<String, String>  constructRequstMap(HttpServletRequest request) {

		Map<String, String> requestMap=new HashMap<String, String>();

		String customerName=AppUtil.getNullToEmpty( request.getParameter("customerName") );
		String contactPerson=AppUtil.getNullToEmpty( request.getParameter("contactPerson") );
		String serviceName=AppUtil.getNullToEmpty( request.getParameter("serviceName") );
		String packageName=AppUtil.getNullToEmpty( request.getParameter("packageName") );

		requestMap.put("customerName", customerName);
		requestMap.put("contactPerson", contactPerson);
		requestMap.put("serviceName", serviceName);
		requestMap.put("packageName", packageName);

		return requestMap;
	}

}
