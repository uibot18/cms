package com.cms.common.master.bean;

public class CommonGroupMasterDO { 

	private int cmnGroupId=0;
	private int parentGroupId=0;
	private String cmnGroupName="";
	private int noOfLevels=0;
	private int levelNo=0;
	private boolean boolDeleteStatus;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";


	public void setCmnGroupId(int cmnGroupId){
		this.cmnGroupId=cmnGroupId;
	}
	public int getCmnGroupId(){
		return this.cmnGroupId;
	}
	public void setParentGroupId(int parentGroupId){
		this.parentGroupId=parentGroupId;
	}
	public int getParentGroupId(){
		return this.parentGroupId;
	}
	public void setCmnGroupName(String cmnGroupName){
		this.cmnGroupName=cmnGroupName;
	}
	public String getCmnGroupName(){
		return this.cmnGroupName;
	}
	public void setNoOfLevels(int noOfLevels){
		this.noOfLevels=noOfLevels;
	}
	public int getNoOfLevels(){
		return this.noOfLevels;
	}
	public void setLevelNo(int levelNo){
		this.levelNo=levelNo;
	}
	public int getLevelNo(){
		return this.levelNo;
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

	public String toString() {
		return "CommonGroupMasterDO [cmnGroupId=" + cmnGroupId + ", parentGroupId=" + parentGroupId + ", cmnGroupName="
				+ cmnGroupName + ", noOfLevels=" + noOfLevels + ", levelNo=" + levelNo + ", boolDeleteStatus="
				+ boolDeleteStatus + ", createdUser=" + createdUser + ", createdDate=" + createdDate + ", updateUser="
				+ updateUser + ", updateDate=" + updateDate + "]";
	}
	
}