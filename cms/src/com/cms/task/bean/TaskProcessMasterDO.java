package com.cms.task.bean;

import java.util.ArrayList;
import java.util.List;

public class TaskProcessMasterDO { 

	private int processMasterId=0;
	private String taskType="General";
	private int salesId=0;
	private String processMasterStatus="pending";
	private boolean boolDeleteStatus;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";
	
	private List<TaskProcessChildDO> taskProcessChildList=new ArrayList<TaskProcessChildDO>();


	public void setProcessMasterId(int processMasterId){
		this.processMasterId=processMasterId;
	}
	public int getProcessMasterId(){
		return this.processMasterId;
	}
	public void setTaskType(String taskType){
		this.taskType=taskType;
	}
	public String getTaskType(){
		return this.taskType;
	}
	public void setSalesId(int salesId){
		this.salesId=salesId;
	}
	public int getSalesId(){
		return this.salesId;
	}
	public void setProcessMasterStatus(String processMasterStatus){
		this.processMasterStatus=processMasterStatus;
	}
	public String getProcessMasterStatus(){
		return this.processMasterStatus;
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
	public List<TaskProcessChildDO> getTaskProcessChildList() {
		return taskProcessChildList;
	}
	public void setTaskProcessChildList(List<TaskProcessChildDO> taskProcessChildList) {
		this.taskProcessChildList = taskProcessChildList;
	}
	@Override
	public String toString() {
		return "TaskProcessMasterDO [processMasterId=" + processMasterId + ", taskType=" + taskType + ", salesId="
				+ salesId + ", processMasterStatus=" + processMasterStatus + ", boolDeleteStatus=" + boolDeleteStatus
				+ ", createdUser=" + createdUser + ", createdDate=" + createdDate + ", updateUser=" + updateUser
				+ ", updateDate=" + updateDate + ", taskProcessChildList=" + taskProcessChildList + "]";
	}
	
	
	

}