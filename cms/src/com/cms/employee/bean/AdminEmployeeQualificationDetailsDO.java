package com.cms.employee.bean;

public class AdminEmployeeQualificationDetailsDO { 

	private int empQualId;
	private int empId;
	private String courseName;
	private String major;
	private String gradePer;
	private String yearOfPassing;
	private String schoolColUniv;
	private boolean boolDeleteStatus;
	private String createdUser;
	private String createdDate;
	private String updateUser;
	private String updateDate;
	
	public void setEmpQualId(int empQualId){
		this.empQualId=empQualId;
	}
	public int getEmpQualId(){
		return this.empQualId;
	}public void setEmpId(int empId){
		this.empId=empId;
	}public int getEmpId(){
		return this.empId;
	}public void setCourseName(String courseName){
		this.courseName=courseName;
	}public String getCourseName(){
		return this.courseName;
	}public void setMajor(String major){
		this.major=major;
	}public String getMajor(){
		return this.major;
	}public void setGradePer(String gradePer){
		this.gradePer=gradePer;
	}public String getGradePer(){
		return this.gradePer;
	}public void setYearOfPassing(String yearOfPassing){
		this.yearOfPassing=yearOfPassing;
	}public String getYearOfPassing(){
		return this.yearOfPassing;
	}public void setSchoolColUniv(String schoolColUniv){
		this.schoolColUniv=schoolColUniv;
	}public String getSchoolColUniv(){
		return this.schoolColUniv;
	}public void setBoolDeleteStatus(boolean boolDeleteStatus){
		this.boolDeleteStatus=boolDeleteStatus;
	}public boolean getBoolDeleteStatus(){
		return this.boolDeleteStatus;
	}public void setCreatedUser(String createdUser){
		this.createdUser=createdUser;
	}public String getCreatedUser(){
		return this.createdUser;
	}public void setCreatedDate(String createdDate){
		this.createdDate=createdDate;
	}public String getCreatedDate(){
		return this.createdDate;
	}public void setUpdateUser(String updateUser){
		this.updateUser=updateUser;
	}public String getUpdateUser(){
		return this.updateUser;
	}public void setUpdateDate(String updateDate){
		this.updateDate=updateDate;
	}public String getUpdateDate(){
		return this.updateDate;
	}
	@Override
	public String toString() {
		return "AdminEmployeeQualificationDetailsDO [empQualId=" + empQualId + ", empId=" + empId + ", courseName="
				+ courseName + ", major=" + major + ", gradePer=" + gradePer + ", yearOfPassing=" + yearOfPassing
				+ ", schoolColUniv=" + schoolColUniv + ", boolDeleteStatus=" + boolDeleteStatus + ", createdUser="
				+ createdUser + ", createdDate=" + createdDate + ", updateUser=" + updateUser + ", updateDate="
				+ updateDate + "]";
	}
	
}