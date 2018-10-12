package com.cms.common.search;

public enum SearchEnum {

	REQUEST_MAP("requestMap"),
	RESULT_MAP("resultMap"),
	RESULT_LIST("resultList"),
	;
	
	private String keyName;
	
	SearchEnum( String keyName ){
		this.keyName=keyName;
	}

	public String getKeyName() {
		return keyName;
	}
	
}
