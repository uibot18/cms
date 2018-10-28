package com.cms.common.test;

import org.json.JSONObject;

import com.application.util.AjaxModel;

public class Test{
	public static void main(String[] args)  {
//		DevUtil.generateDOAndDAO(DBConnection.getConnection(), "sales_customer_booking_form");
//		DevUtil.generateDOAndDAO(DBConnection.getConnection(), "sales_customer_package_details");

		JSONObject object=new JSONObject(new AjaxModel());
		
		System.out.println(object);
		
	}
}