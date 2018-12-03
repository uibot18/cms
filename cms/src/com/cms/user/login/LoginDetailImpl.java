package com.cms.user.login;

import java.io.Serializable;
import java.util.Set;

public class LoginDetailImpl implements LoginDetail, Serializable{
	
	private static final long serialVersionUID = 1L;
	private String loginId="";
	private String refType;
	private String userName;
	private String menuIds;
	private String rightsIds;
	private Set<String> menuIdSet;
	private Set<String> rightsIdSet;
	
	
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
	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	public String getRightsIds() {
		return rightsIds;
	}
	public void setRightsIds(String rightsIds) {
		this.rightsIds = rightsIds;
	}
	public Set<String> getMenuIdSet() {
		return menuIdSet;
	}
	public void setMenuIdSet(Set<String> menuIdSet) {
		this.menuIdSet = menuIdSet;
	}
	public Set<String> getRightsIdSet() {
		return rightsIdSet;
	}
	public void setRightsIdSet(Set<String> rightsIdSet) {
		this.rightsIdSet = rightsIdSet;
	}
	
	
	
	


}
