package com.cms.user.login;

import java.util.Set;

public interface LoginDetail {
	String getLoginId();
	String getRefType();
	int getRefId();
	String getUserName();
	String getMenuIds(); 
	String getRightsIds();
	Set<String> getMenuIdSet();
	Set<String> getRightsIdSet();

}
