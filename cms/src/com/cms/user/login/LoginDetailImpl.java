package com.cms.user.login;

import java.io.Serializable;

public class LoginDetailImpl implements LoginDetail, Serializable{
	
	private static final long serialVersionUID = 1L;
	private String loginId="";
	private String refType;
	private String userName;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getRefType() {
		return refType;
	}
	public void setRefType(String refType) {
		this.refType = refType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	


}
