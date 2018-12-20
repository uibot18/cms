package com.cms.finance.bean;

public class FinancePartyPersonalDetailsDO {
	
	private int personalId=0;
	private int ledgerId=0;
	private int partyType=0;
	private int salutation=0;
	private String firstName="";
	private String middleName="";
	private String lastName="";
	private int relationTypeId=0;
	private int salutationRel=0;
	private String firstNameRel="";
	private String middleNameRel="";
	private String lastNameRel="";
	private String dob="";
	private int age=0;
	private int gender=0;
	private String maritalStatus="none";
	private String bloodGroup="none";
	private boolean boolResidingWithPrimary=false;
	private boolean boolDeleteStatus=false;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";
	
	public int getPersonalId() {
		return personalId;
	}
	public void setPersonalId(int personalId) {
		this.personalId = personalId;
	}
	public int getLedgerId() {
		return ledgerId;
	}
	public void setLedgerId(int ledgerId) {
		this.ledgerId = ledgerId;
	}
	public int getPartyType() {
		return partyType;
	}
	public void setPartyType(int partyType) {
		this.partyType = partyType;
	}
	public int getSalutation() {
		return salutation;
	}
	public void setSalutation(int salutation) {
		this.salutation = salutation;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getRelationTypeId() {
		return relationTypeId;
	}
	public void setRelationTypeId(int relationTypeId) {
		this.relationTypeId = relationTypeId;
	}
	public int getSalutationRel() {
		return salutationRel;
	}
	public void setSalutationRel(int salutationRel) {
		this.salutationRel = salutationRel;
	}
	public String getFirstNameRel() {
		return firstNameRel;
	}
	public void setFirstNameRel(String firstNameRel) {
		this.firstNameRel = firstNameRel;
	}
	public String getMiddleNameRel() {
		return middleNameRel;
	}
	public void setMiddleNameRel(String middleNameRel) {
		this.middleNameRel = middleNameRel;
	}
	public String getLastNameRel() {
		return lastNameRel;
	}
	public void setLastNameRel(String lastNameRel) {
		this.lastNameRel = lastNameRel;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public boolean isBoolResidingWithPrimary() {
		return boolResidingWithPrimary;
	}
	public void setBoolResidingWithPrimary(boolean boolResidingWithPrimary) {
		this.boolResidingWithPrimary = boolResidingWithPrimary;
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
		return "FinancePartyPersonalDetails [personalId=" + personalId + ", ledgerId=" + ledgerId + ", partyType="
				+ partyType + ", salutation=" + salutation + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", relationTypeId=" + relationTypeId + ", salutationRel=" + salutationRel
				+ ", firstNameRel=" + firstNameRel + ", middleNameRel=" + middleNameRel + ", lastNameRel=" + lastNameRel
				+ ", dob=" + dob + ", age=" + age + ", gender=" + gender + ", maritalStatus=" + maritalStatus
				+ ", bloodGroup=" + bloodGroup + ", boolResidingWithPrimary=" + boolResidingWithPrimary
				+ ", boolDeleteStatus=" + boolDeleteStatus + ", createdUser=" + createdUser + ", createdDate="
				+ createdDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate + "]";
	}
	
}
