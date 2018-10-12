package com.cms.finance.bean;

public class FinancePartyAddressDetailsDO {
	

	private int addresId=0;
	private int ledgerId=0;
	private String refType="";
	private int refId=0;
	private int addressTypeId=0;
	private String dooNo="";
	private String streetName="";
	private String roadName="";
	private String areaName="";
	private String landMark="";
	private String city="";
	private String state="";
	private String pincode="";
	private boolean boolDeleteStatus=false;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";
	
	public int getAddresId() {
		return addresId;
	}
	public void setAddresId(int addresId) {
		this.addresId = addresId;
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
	public int getAddressTypeId() {
		return addressTypeId;
	}
	public void setAddressTypeId(int addressTypeId) {
		this.addressTypeId = addressTypeId;
	}
	public String getDooNo() {
		return dooNo;
	}
	public void setDooNo(String dooNo) {
		this.dooNo = dooNo;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
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
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
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
		return "FinancePartyAddressDetails [addresId=" + addresId + ", ledgerId=" + ledgerId + ", refType=" + refType
				+ ", refId=" + refId + ", addressTypeId=" + addressTypeId + ", dooNo=" + dooNo + ", streetName="
				+ streetName + ", roadName=" + roadName + ", areaName=" + areaName + ", landMark=" + landMark
				+ ", city=" + city + ", state=" + state + ", pincode=" + pincode + ", boolDeleteStatus="
				+ boolDeleteStatus + ", createdUser=" + createdUser + ", createdDate=" + createdDate + ", updateUser="
				+ updateUser + ", updateDate=" + updateDate + "]";
	}
	
}
