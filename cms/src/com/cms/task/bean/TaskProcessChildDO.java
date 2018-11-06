package com.cms.task.bean;

import java.util.ArrayList;
import java.util.List;

public class TaskProcessChildDO { 

	private int processChildId=0;
	private int processMasterId=0;
	private int serviceId=0;
	private int packageId=0;
	private int processId=0;
	private String processStartsOn="01/01/1000";
	private String processEndsOn="01/01/1000";
	private boolean boolOverride;
	private boolean boolDeleteStatus;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";
	
	private List<TaskMasterDO> taskList=new ArrayList<TaskMasterDO>();


	public void setProcessChildId(int processChildId){
		this.processChildId=processChildId;
	}
	public int getProcessChildId(){
		return this.processChildId;
	}
	public void setProcessMasterId(int processMasterId){
		this.processMasterId=processMasterId;
	}
	public int getProcessMasterId(){
		return this.processMasterId;
	}
	public void setServiceId(int serviceId){
		this.serviceId=serviceId;
	}
	public int getServiceId(){
		return this.serviceId;
	}
	public void setPackageId(int packageId){
		this.packageId=packageId;
	}
	public int getPackageId(){
		return this.packageId;
	}
	public void setProcessId(int processId){
		this.processId=processId;
	}
	public int getProcessId(){
		return this.processId;
	}
	public void setProcessStartsOn(String processStartsOn){
		this.processStartsOn=processStartsOn;
	}
	public String getProcessStartsOn(){
		return this.processStartsOn;
	}
	public void setProcessEndsOn(String processEndsOn){
		this.processEndsOn=processEndsOn;
	}
	public String getProcessEndsOn(){
		return this.processEndsOn;
	}
	public boolean isBoolOverride() {
		return boolOverride;
	}
	public void setBoolOverride(boolean boolOverride) {
		this.boolOverride = boolOverride;
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
	public List<TaskMasterDO> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<TaskMasterDO> taskList) {
		this.taskList = taskList;
	}
	@Override
	public String toString() {
		return "TaskProcessChildDO [processChildId=" + processChildId + ", processMasterId=" + processMasterId
				+ ", serviceId=" + serviceId + ", packageId=" + packageId + ", processId=" + processId
				+ ", processStartsOn=" + processStartsOn + ", processEndsOn=" + processEndsOn + ", boolOverride="
				+ boolOverride + ", boolDeleteStatus=" + boolDeleteStatus + ", createdUser=" + createdUser
				+ ", createdDate=" + createdDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate
				+ ", taskList=" + taskList + "]";
	}
	
}