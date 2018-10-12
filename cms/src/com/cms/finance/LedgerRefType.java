package com.cms.finance;

public enum LedgerRefType {

	CUSTOMER("sales_customer_master"),
	EMPLOYEE("adm_employee_master"),
	;
	private String type;
	LedgerRefType(String type){
		this.type=type;
	}
	public String getType() {
		return type;
	}
	
	
	
}
