package com.cms.finance.bean;

public class FinancePartyBankBranchDetailsDO {
	private int branchId=0;
	private int bankId=0;
	private String area="";
	private String city="";
	private String state="";
	private String ifscCode="";
	private boolean boolDeleteStatus=false;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";
	
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public boolean isBoolDeleteStatus() {
		return boolDeleteStatus;
	}
	public void setBoolDeleteStatus(boolean boolDeleteStatus) {
		this.boolDeleteStatus = boolDeleteStatus;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return "FinancePartyBankBranchDetails [branchId=" + branchId + ", bankId=" + bankId + ", area=" + area
				+ ", city=" + city + ", state=" + state + ", ifscCode=" + ifscCode + ", boolDeleteStatus="
				+ boolDeleteStatus + ", createdUser=" + createdUser + ", createdDate=" + createdDate + ", updateUser="
				+ updateUser + ", updateDate=" + updateDate + "]";
	}
	
}
