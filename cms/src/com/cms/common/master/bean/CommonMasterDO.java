package com.cms.common.master.bean;

public class CommonMasterDO { 

	private int cmnMasterId=0;
	private int cmnGroupId=0;
	private int parentId=0;
	private String cmnMasterName="";
	private int levelNo=0;
	private boolean boolDeleteStatus;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";


	public void setCmnMasterId(int cmnMasterId){
		this.cmnMasterId=cmnMasterId;
	}
	public int getCmnMasterId(){
		return this.cmnMasterId;
	}
	public void setCmnGroupId(int cmnGroupId){
		this.cmnGroupId=cmnGroupId;
	}
	public int getCmnGroupId(){
		return this.cmnGroupId;
	}
	public void setParentId(int parentId){
		this.parentId=parentId;
	}
	public int getParentId(){
		return this.parentId;
	}
	public void setCmnMasterName(String cmnMasterName){
		this.cmnMasterName=cmnMasterName;
	}
	public String getCmnMasterName(){
		return this.cmnMasterName;
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
		return "CommonMasterDO [cmnMasterId=" + cmnMasterId + ", cmnGroupId=" + cmnGroupId + ", parentId=" + parentId
				+ ", cmnMasterName=" + cmnMasterName + ", levelNo=" + levelNo + ", boolDeleteStatus=" + boolDeleteStatus
				+ ", createdUser=" + createdUser + ", createdDate=" + createdDate + ", updateUser=" + updateUser
				+ ", updateDate=" + updateDate + "]";
	}

}