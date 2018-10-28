package com.cms.holiday.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppDateUtil;
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

		String holidayType=AppUtil.getNullToEmpty( requestMap.get("holidayType") );
		String holidayName=AppUtil.getNullToEmpty( requestMap.get("holidayName") );
		String holidayDate=AppUtil.getNullToEmpty( requestMap.get("holidayDate") );
		holidayDate=AppDateUtil.convertToDBDate(holidayDate, false, false);
		

		String query=" SELECT a.holiday_id, a.holiday_type_id, b.holiday_type, a.holiday_sub_type, a.holiday_name, a.holiday " + 
				"FROM admin_holiday_details a, admin_holiday_type b "+
				" WHERE a.holiday_type_id=b.holiday_type_id AND a.bool_delete_status=0 AND b.bool_delete_status=0 ";

		if( !holidayType.isEmpty() ) { query+=" AND a.holiday_type_id ="+holidayType; }
		if( !holidayName.isEmpty() ) { query+=" AND a.holiday_name like'%"+holidayName+"%' "; }
		if( !holidayDate.isEmpty() ) { query+=" AND a.holiday='"+holidayDate+"' "; }

		return query;
	}

	private static Map<String, String>  constructRequstMap(HttpServletRequest request) {

		Map<String, String> requestMap=new HashMap<String, String>();

		String holidayType=AppUtil.getNullToEmpty( request.getParameter("holidayType") );
		String holidayName=AppUtil.getNullToEmpty( request.getParameter("holidayName") );
		String holidayDate=AppUtil.getNullToEmpty( request.getParameter("holidayDate") );

		requestMap.put("holidayType", holidayType);
		requestMap.put("holidayName", holidayName);
		requestMap.put("holidayDate", holidayDate);

		return requestMap;
	}

}
