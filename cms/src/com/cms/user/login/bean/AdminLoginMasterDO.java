package com.cms.user.login.bean;

public class AdminLoginMasterDO { 

	private int loginKey=0;
	private String loginId="";
	private String loginPwd="";
	private String refType="";
	private int refId=0;
	private boolean boolLoginActiveStatus;
	private boolean boolDeleteStatus;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";


	public void setLoginKey(int loginKey){
		this.loginKey=loginKey;
	}
	public int getLoginKey(){
		return this.loginKey;
	}
	public void setLoginId(String loginId){
		this.loginId=loginId;
	}
	public String getLoginId(){
		return this.loginId;
	}
	public void setLoginPwd(String loginPwd){
		this.loginPwd=loginPwd;
	}
	public String getLoginPwd(){
		return this.loginPwd;
	}
	public void setRefType(String refType){
		this.refType=refType;
	}
	public String getRefType(){
		return this.refType;
	}
	public void setRefId(int refId){
		this.refId=refId;
	}
	public int getRefId(){
		return this.refId;
	}
	public void setBoolLoginActiveStatus(boolean boolLoginActiveStatus){
		this.boolLoginActiveStatus=boolLoginActiveStatus;
	}
	public boolean getBoolLoginActiveStatus(){
		return this.boolLoginActiveStatus;
	}
	public void setBoolDeleteStatus(boolean boolDeleteStatus){
		this.boolDeleteStatus=boolDeleteStatus;
	}
	public boolean getBoolDeleteStatus(){
		return this.boolDeleteStatus;
	}
	public void setCreatedUser(String createdUser){
		this.createdUser=createdUser;
	}
	public String getCreatedUser(){
		return this.createdUser;
	}
	public void setCreatedDate(String createdDate){
		this.createdDate=createdDate;
	}
	public String getCreatedDate(){
		return this.createdDate;
	}
	public void setUpdateUser(String updateUser){
		this.updateUser=updateUser;
	}
	public String getUpdateUser(){
		return this.updateUser;
	}
	public void setUpdateDate(String updateDate){
		this.updateDate=updateDate;
	}
	public String getUpdateDate(){
		return this.updateDate;
	}

}