package com.cms.user.login;

public enum UserType {

	ADMIN("admin"),
	EMPLOYEE("employee"),
	CUSTOMER("customer"),
	;
	
	private String type;
	UserType( String type ) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	
}
