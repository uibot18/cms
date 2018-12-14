package com.cms.booking.bean;

import java.util.ArrayList;
import java.util.List;

public class SalesCustomerBookingFormDO { 

	private int saleId=0;
	private String saleDate="";
	private int customerId=0;
	private String description="";
	private boolean boolDeleteStatus;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";
	
	private List<SalesCustomerPackageDetailsDO> customerPackageList=new ArrayList<SalesCustomerPackageDetailsDO>();


	public void setSaleId(int saleId){
		this.saleId=saleId;
	}
	public int getSaleId(){
		return this.saleId;
	}
	public void setSaleDate(String saleDate){
		this.saleDate=saleDate;
	}
	public String getSaleDate(){
		return this.saleDate;
	}
	public void setCustomerId(int customerId){
		this.customerId=customerId;
	}
	public int getCustomerId(){
		return this.customerId;
	}
	public void setDescription(String description){
		this.description=description;
	}
	public String getDescription(){
		return this.description;
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
	public List<SalesCustomerPackageDetailsDO> getCustomerPackageList() {
		return customerPackageList;
	}
	public void setCustomerPackageList(List<SalesCustomerPackageDetailsDO> customerPackageList) {
		this.customerPackageList = customerPackageList;
	}
	@Override
	public String toString() {
		return "SalesCustomerBookingFormDO [saleId=" + saleId + ", saleDate=" + saleDate + ", customerId=" + customerId
				+ ", description=" + description + ", boolDeleteStatus=" + boolDeleteStatus + ", createdUser="
				+ createdUser + ", createdDate=" + createdDate + ", updateUser=" + updateUser + ", updateDate="
				+ updateDate + ", customerPackageList=" + customerPackageList + "]";
	}

	
}