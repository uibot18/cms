package com.cms.holiday.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.search.SearchEnum;
import com.cms.common.search.util.SearchUtil;

public class HolidayTypeSearchController {

	public static void doSearch( HttpServletRequest request, HttpServletResponse response ) {

		Map<String, String> requestMap =constructRequstMap( request );

		String query=constructQuery( requestMap, request, response  );
		SearchUtil.generateSearhResult(request, response, query);

		request.setAttribute(SearchEnum.REQUEST_MAP.getKeyName(), requestMap);
	}

	private static String constructQuery(Map<String, String> requestMap, HttpServletRequest request, HttpServletResponse response) {

		String holidayType=AppUtil.getNullToEmpty( requestMap.get("holidayType") );

		String query=" SELECT holiday_type_id, holiday_type, bool_delete_status, created_user, created_date, update_user, update_date " + 
				"FROM admin_holiday_type where 0=0 ";

		if( !holidayType.isEmpty() ) { query+=" AND holiday_type like'%"+holidayType+"%' "; }

		return query;
	}

	private static Map<String, String>  constructRequstMap(HttpServletRequest request) {

		Map<String, String> requestMap=new HashMap<String, String>();

		String holidayType=AppUtil.getNullToEmpty( request.getParameter("holidayType") );

		requestMap.put("holidayType", holidayType);

		return requestMap;
	}

}
