package com.application.util;

import java.io.Serializable;

public class AjaxModel implements Serializable {

	String message="";
	int responseCode=0;
	Object data;
	boolean errorExists;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isErrorExists() {
		return errorExists;
	}
	public void setErrorExists(boolean errorExists) {
		this.errorExists = errorExists;
	}
	
	
	
}
