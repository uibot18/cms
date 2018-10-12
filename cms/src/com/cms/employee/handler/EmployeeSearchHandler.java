package com.cms.employee.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.search.SearchEnum;
import com.cms.common.search.util.SearchUtil;

public class EmployeeSearchHandler {

	public static void doSearch( HttpServletRequest request, HttpServletResponse response ) {

		Map<String, String> requestMap =constructRequstMap( request );

		String query=constructQuery( requestMap, request, response  );
		SearchUtil.generateSearhResult(request, response, query);

		request.setAttribute(SearchEnum.REQUEST_MAP.getKeyName(), requestMap);
	}

	private static String constructQuery(Map<String, String> requestMap, HttpServletRequest request, HttpServletResponse response) {

		   String employeeName=AppUtil.getNullToEmpty( requestMap.get("employeeName") );
		   String department=AppUtil.getNullToEmpty( requestMap.get("department") );
		   String designation=AppUtil.getNullToEmpty( requestMap.get("designation") );
		   String qualification=AppUtil.getNullToEmpty( requestMap.get("qualification") );
		   String state=AppUtil.getNullToEmpty( requestMap.get("state") );
		   String city=AppUtil.getNullToEmpty( requestMap.get("city") );
		   String pinCode=AppUtil.getNullToEmpty( requestMap.get("pinCode") );
		   String email=AppUtil.getNullToEmpty( requestMap.get("email") );
		   String mobile=AppUtil.getNullToEmpty( requestMap.get("mobile") );

		String query=" SELECT a.emp_id, CONCAT(c.first_name,' ', c.middle_name, ' ' ,c.last_name) AS emp_name, '' AS reporting_to, " + 
				"f.cmn_master_name AS department, g.cmn_master_name AS designation, d.email1 AS email, d.mobile1 AS mobile  " + 
				"FROM admin_employee_master a, finance_ledger_master b, finance_party_personal_details c, finance_party_contact_details d,  " + 
				"finance_party_address_details e, common_master f, common_master g " + 
				"WHERE a.ledger_id=b.ledger_id AND a.ledger_id=c.ledger_id AND a.ledger_id=d.ledger_id AND a.ledger_id=e.ledger_id " + 
				"AND a.department_id=f.cmn_master_id AND a.designation_id=g.cmn_master_id ";

		if( !employeeName.isEmpty() ) { query+=" AND CONCAT(c.first_name,' ', c.middle_name, ' ' ,c.last_name)  like '%"+employeeName+"%' ";  }
		if( !department.isEmpty() && !department.equals("0") ) { query+=" AND a.department_id="+department;  }
		if( !designation.isEmpty() && !designation.equals("0") ) { query+=" AND a.designation_id="+designation;  }
		if( !state.isEmpty() ) { query+=" AND e.state like '%"+state+"%' ";  }
		if( !city.isEmpty() ) { query+=" AND e.city like '%"+city+"%' ";  }
		if( !pinCode.isEmpty() ) { query+=" AND a.pincode='"+pinCode+"' "; }
		if( !mobile.isEmpty() ) { query+=" AND d.mobile1 like'%"+mobile+"%' OR d.mobile2 like'%"+mobile+"%'  "; }
		if( !email.isEmpty() ) { query+=" AND d.email1 like'%"+email+"%' OR  d.email2 like'%"+email+"%'  "; }

		query+=" group by a.emp_id ";
		return query;
	}

	private static Map<String, String>  constructRequstMap(HttpServletRequest request) {

		Map<String, String> requestMap=new HashMap<String, String>();

		String employeeName=AppUtil.getNullToEmpty( request.getParameter("employeeName") );
		String department=AppUtil.getNullToEmpty( request.getParameter("department") );
		String designation=AppUtil.getNullToEmpty( request.getParameter("designation") );
		String qualification=AppUtil.getNullToEmpty( request.getParameter("qualification") );
		String state=AppUtil.getNullToEmpty( request.getParameter("state") );
		String city=AppUtil.getNullToEmpty( request.getParameter("city") );
		String pinCode=AppUtil.getNullToEmpty( request.getParameter("pinCode") );
		String email=AppUtil.getNullToEmpty( request.getParameter("email") );
		String mobile=AppUtil.getNullToEmpty( request.getParameter("mobile") );

		requestMap.put("employeeName", employeeName);
		requestMap.put("department", department);
		requestMap.put("designation", designation);
		requestMap.put("qualification", qualification);
		requestMap.put("state", state);
		requestMap.put("city", city);
		requestMap.put("pinCode", pinCode);
		requestMap.put("mobile", mobile);
		requestMap.put("email", email);


		return requestMap;
	}

}
