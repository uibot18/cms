package com.cms.user.login;

public enum LoginEnum {

	LOGIN_DETAIL("login_obj"),
	;
	
	private String type;
	LoginEnum(String type){
		this.type=type;
	}
	
	public String getType() {
		return type;
	}
	
}
