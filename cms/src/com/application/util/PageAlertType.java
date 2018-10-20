package com.application.util;

public enum PageAlertType {

	SUCCESS("success"),
	ERROR("error"),
	INFO("info"),
	WARNING("warning"),
	;
	
	private String type;
	PageAlertType(String type){
		this.type=type;
	}
	
	public String getType() {
		return type;
	}
	
	
}
