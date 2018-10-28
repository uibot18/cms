package com.cms.booking.bean;

public class SalesCustomerPackageDetailsDO { 

	private int salesPackageId=0;
	private int salesId=0;
	private int packageId=0;
	private String processStartsFrom="";
	private String processEndsOn="";
	private boolean boolOveride;
	private boolean boolDeleteStatus;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";


	public void setSalesPackageId(int salesPackageId){
		this.salesPackageId=salesPackageId;
	}
	public int getSalesPackageId(){
		return this.salesPackageId;
	}
	public void setSalesId(int salesId){
		this.salesId=salesId;
	}
	public int getSalesId(){
		return this.salesId;
	}
	public void setPackageId(int packageId){
		this.packageId=packageId;
	}
	public int getPackageId(){
		return this.packageId;
	}
	public void setProcessStartsFrom(String processStartsFrom){
		this.processStartsFrom=processStartsFrom;
	}
	public String getProcessStartsFrom(){
		return this.processStartsFrom;
	}
	public void setProcessEndsOn(String processEndsOn){
		this.processEndsOn=processEndsOn;
	}
	public String getProcessEndsOn(){
		return this.processEndsOn;
	}
	public boolean isBoolOveride() {
		return boolOveride;
	}
	public void setBoolOveride(boolean boolOveride) {
		this.boolOveride = boolOveride;
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