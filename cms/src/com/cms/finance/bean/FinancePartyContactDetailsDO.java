package com.cms.finance.bean;

public class FinancePartyContactDetailsDO {
	

	private int contactId=0;
	private int ledgerId=0;
	private String refType="";
	private int refId=0;
	private int contactTypeId=0;
	private String stdCode1="";
	private String phone1="";
	private String extn1="";
	private String stdCode2="";
	private String phone2="";
	private String extn2="";
	private String countryCode1="";
	private String mobile1="";
	private String countryCode2="";
	private String mobile2="";
	private String email1="";
	private String email2="";
	private String website="";
	private boolean boolDeleteStatus=false;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public int getLedgerId() {
		return ledgerId;
	}
	public void setLedgerId(int ledgerId) {
		this.ledgerId = ledgerId;
	}
	public String getRefType() {
		return refType;
	}
	public void setRefType(String refType) {
		this.refType = refType;
	}
	public int getRefId() {
		return refId;
	}
	public void setRefId(int refId) {
		this.refId = refId;
	}
	public int getContactTypeId() {
		return contactTypeId;
	}
	public void setContactTypeId(int contactTypeId) {
		this.contactTypeId = contactTypeId;
	}
	public String getStdCode1() {
		return stdCode1;
	}
	public void setStdCode1(String stdCode1) {
		this.stdCode1 = stdCode1;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getExtn1() {
		return extn1;
	}
	public void setExtn1(String extn1) {
		this.extn1 = extn1;
	}
	public String getStdCode2() {
		return stdCode2;
	}
	public void setStdCode2(String stdCode2) {
		this.stdCode2 = stdCode2;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getExtn2() {
		return extn2;
	}
	public void setExtn2(String extn2) {
		this.extn2 = extn2;
	}
	public String getCountryCode1() {
		return countryCode1;
	}
	public void setCountryCode1(String countryCode1) {
		this.countryCode1 = countryCode1;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getCountryCode2() {
		return countryCode2;
	}
	public void setCountryCode2(String countryCode2) {
		this.countryCode2 = countryCode2;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
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
		return "FinancePartyContactDetails [contactId=" + contactId + ", ledgerId=" + ledgerId + ", refType=" + refType
				+ ", refId=" + refId + ", contactTypeId=" + contactTypeId + ", stdCode1=" + stdCode1 + ", phone1="
				+ phone1 + ", extn1=" + extn1 + ", stdCode2=" + stdCode2 + ", phone2=" + phone2 + ", extn2=" + extn2
				+ ", countryCode1=" + countryCode1 + ", mobile1=" + mobile1 + ", countryCode2=" + countryCode2
				+ ", mobile2=" + mobile2 + ", email1=" + email1 + ", email2=" + email2 + ", website=" + website
				+ ", boolDeleteStatus=" + boolDeleteStatus + ", createdUser=" + createdUser + ", createdDate="
				+ createdDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate + "]";
	}
	
	
}
