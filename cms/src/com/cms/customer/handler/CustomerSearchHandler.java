package com.cms.customer.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.cms.common.search.SearchEnum;
import com.cms.common.search.util.SearchUtil;

public class CustomerSearchHandler {

	public static void doSearch( HttpServletRequest request, HttpServletResponse response ) {
		
		Map<String, String> requestMap =constructRequstMap( request );
		
		String query=constructQuery( requestMap, request, response  );
		SearchUtil.generateSearhResult(request, response, query);
		
		request.setAttribute(SearchEnum.REQUEST_MAP.getKeyName(), requestMap);
	}

	private static String constructQuery(Map<String, String> requestMap, HttpServletRequest request, HttpServletResponse response) {
		
		String customerName=requestMap.get("customerName");
		String contactPersonName=requestMap.get("contactPersonName");
		String city=requestMap.get("city");
		String email=requestMap.get("email");
		String mobile=requestMap.get("mobile");
		String state=requestMap.get("state");
		String webSite=requestMap.get("webSite");
		String pinCode=requestMap.get("pinCode");
		String panOrGst=requestMap.get("panOrGst");
		
		String query="SELECT  " + 
				"a.customer_id, a.ledger_id, b.ledger_name AS customer_name, c.first_name AS contact_person_name, d.mobile1, d.email1, d.website  " + 
				"FROM sales_customer_master a, finance_ledger_master b, finance_party_personal_details c, finance_party_contact_details d " + 
				"WHERE a.bool_delete_status=0 AND a.ledger_id=b.ledger_id AND b.ref_type='sales_customer_master' AND b.ref_id=a.customer_id AND a.bool_delete_status=0 " + 
				"AND a.ledger_id=c.ledger_id AND d.ref_type='sales_customer_master' AND d.ref_id=a.customer_id AND d.bool_delete_status=0  ";
		
		if( !customerName.isEmpty() ) { query+=" AND b.ledger_name like '%"+customerName+"%' ";  }
		if( !contactPersonName.isEmpty() ) { query+=" AND c.first_name like '%"+contactPersonName+"%' ";  }
		if( !email.isEmpty() ) { query+=" AND d.email1 like '%"+email+"%' ";  }
		if( !mobile.isEmpty() ) { query+=" AND d.mobile1 like '%"+mobile+"%' ";  }
		if( !webSite.isEmpty() ) { query+=" AND d.website like '%"+webSite+"%' ";  }
		
		if( !city.isEmpty() || !city.isEmpty() || !pinCode.isEmpty()  ) {
			query+=" AND a.ledger_id in( "+
					 " SELECT ledger_id FROM finance_party_address_details a "+
					" WHERE  0=0 ";
			if( !city.isEmpty() ) { query+=" a.city like '%"+city+"%' "; }
			if( !state.isEmpty() ) { query+=" a.state like '%"+state+"%' "; }
			if( !pinCode.isEmpty() ) { query+=" a.pincode='"+pinCode+"' "; }
			query+= ") ";  
		}
		if( !panOrGst.isEmpty() ) { 
			query+="  AND a.customer_id in( "+
					 "  SELECT ref_id from common_document_store a"
					 + "where  a.document_type_id in(10,11) and a.ref_type='finance_company_master' "
					 + " AND a.doc_no like'%"+panOrGst+"%'  )";  
		}
		query+=" group by a.customer_id ";
		return query;
	}

	private static Map<String, String>  constructRequstMap(HttpServletRequest request) {
		
		Map<String, String> requestMap=new HashMap<String, String>();
		
		String customerName=AppUtil.getNullToEmpty( request.getParameter("customerName") );
		String contactPersonName=AppUtil.getNullToEmpty( request.getParameter("contactPersonName") );
		String city=AppUtil.getNullToEmpty( request.getParameter("city") );
		String email=AppUtil.getNullToEmpty( request.getParameter("email") );
		String mobile=AppUtil.getNullToEmpty( request.getParameter("mobile") );
		String state=AppUtil.getNullToEmpty( request.getParameter("state") );
		String webSite=AppUtil.getNullToEmpty( request.getParameter("webSite") );
		String pinCode=AppUtil.getNullToEmpty( request.getParameter("pinCode") );
		String panOrGst=AppUtil.getNullToEmpty( request.getParameter("panOrGst") );
		
		requestMap.put("customerName", customerName);
		requestMap.put("contactPersonName", contactPersonName);
		requestMap.put("city", city);
		requestMap.put("email", email);
		requestMap.put("mobile", mobile);
		requestMap.put("state", state);
		requestMap.put("webSite", webSite);
		requestMap.put("pinCode", pinCode);
		requestMap.put("panOrGst", panOrGst);
		
		return requestMap;
	}
	
}
