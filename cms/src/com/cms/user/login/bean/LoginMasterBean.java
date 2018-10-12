package com.cms.user.login.bean;

public class LoginMasterBean {
private int id=0;
private String loginId="";
private String password="";
private String email="";
private boolean isActive=true;
private String status="active";
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getLoginId() {
	return loginId;
}
public void setLoginId(String loginId) {
	this.loginId = loginId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public boolean isActive() {
	return isActive;
}
public void setActive(boolean isActive) {
	this.isActive = isActive;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
@Override
public String toString() {
	return "LoginMasterBean [id=" + id + ", loginId=" + loginId + ", password=" + password + ", email=" + email
			+ ", isActive=" + isActive + ", status=" + status + "]";
}


}
