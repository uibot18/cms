package com.cms.timesheet.bean;

import java.util.ArrayList;
import java.util.List;

public class TaskTimeSheetMasterDO { 

	private int timeSheetId=0;
	private String assignedTo="";
	private int shiftId=0;
	private String fromDate="";
	private String toDate="";
	private boolean boolDeleteStatus;
	private String status="";
	private String approvedBy="";
	private String approvedOn="";
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";
	
	private List<TaskTimeSheetChildDO> timeSheetChildList = new ArrayList<TaskTimeSheetChildDO>();


	public void setTimeSheetId(int timeSheetId){
		this.timeSheetId=timeSheetId;
	}
	public int getTimeSheetId(){
		return this.timeSheetId;
	}
	public void setAssignedTo(String assignedTo){
		this.assignedTo=assignedTo;
	}
	public String getAssignedTo(){
		return this.assignedTo;
	}
	public void setShiftId(int shiftId){
		this.shiftId=shiftId;
	}
	public int getShiftId(){
		return this.shiftId;
	}
	public void setFromDate(String fromDate){
		this.fromDate=fromDate;
	}
	public String getFromDate(){
		return this.fromDate;
	}
	public void setToDate(String toDate){
		this.toDate=toDate;
	}
	public String getToDate(){
		return this.toDate;
	}
	public void setBoolDeleteStatus(boolean boolDeleteStatus){
		this.boolDeleteStatus=boolDeleteStatus;
	}
	public boolean getBoolDeleteStatus(){
		return this.boolDeleteStatus;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return this.status;
	}
	public void setApprovedBy(String approvedBy){
		this.approvedBy=approvedBy;
	}
	public String getApprovedBy(){
		return this.approvedBy;
	}
	public void setApprovedOn(String approvedOn){
		this.approvedOn=approvedOn;
	}
	public String getApprovedOn(){
		return this.approvedOn;
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
	public List<TaskTimeSheetChildDO> getTimeSheetChildList() {
		return timeSheetChildList;
	}
	public void setTimeSheetChildList(List<TaskTimeSheetChildDO> timeSheetChildList) {
		this.timeSheetChildList = timeSheetChildList;
	}

	
	
}