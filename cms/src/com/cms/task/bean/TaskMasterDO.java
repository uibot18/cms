package com.cms.task.bean;

import com.application.util.AppUtil;
import com.cms.task.config.bean.TaskConfigMasterDO;

public class TaskMasterDO { 

	private int taskId=0;
	private int processChildId=0;
	private String taskParticulars="";
	private int taskConfigId=0;
	private int processId=0;
	private int exeOrder=0;
	private int assignedTo=0;
	private String taskDate="";
	private String taskDateFrom="";
	private String taskDateTo="";
	private String taskStatus="pending";
	private String pauseTimeStart="";
	private String pauseTimeEnd="";
	private int pauseCount=0;
	private String taskDescription="";
	private String refType="";
	private int refId=0;
	private boolean boolDeleteStatus;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";

	public TaskMasterDO() {}

	public TaskMasterDO(int taskId, int processChildId, String taskParticulars, int taskConfigId, int processId,
			int exeOrder, int assignedTo, String taskDate, String taskDateFrom, String taskDateTo, String taskStatus,
			String pauseTimeStart, String pauseTimeEnd, int pauseCount, String taskDescription, String refType,
			int refId, boolean boolDeleteStatus, String createdUser, String createdDate, String updateUser,
			String updateDate) {
		super();
		this.taskId = taskId;
		this.processChildId = processChildId;
		this.taskParticulars = taskParticulars;
		this.taskConfigId = taskConfigId;
		this.processId = processId;
		this.exeOrder = exeOrder;
		this.assignedTo = assignedTo;
		this.taskDate = taskDate;
		this.taskDateFrom = taskDateFrom;
		this.taskDateTo = taskDateTo;
		this.taskStatus = taskStatus;
		this.pauseTimeStart = pauseTimeStart;
		this.pauseTimeEnd = pauseTimeEnd;
		this.pauseCount = pauseCount;
		this.taskDescription = taskDescription;
		this.refType = refType;
		this.refId = refId;
		this.boolDeleteStatus = boolDeleteStatus;
		this.createdUser = createdUser;
		this.createdDate = createdDate;
		this.updateUser = updateUser;
		this.updateDate = updateDate;
	}
	public void setTaskId(int taskId){
		this.taskId=taskId;
	}
	public int getTaskId(){
		return this.taskId;
	}
	public void setProcessChildId(int processChildId){
		this.processChildId=processChildId;
	}
	public int getProcessChildId(){
		return this.processChildId;
	}
	public void setTaskParticulars(String taskParticulars){
		this.taskParticulars=taskParticulars;
	}
	public String getTaskParticulars(){
		return this.taskParticulars;
	}
	public void setTaskConfigId(int taskConfigId){
		this.taskConfigId=taskConfigId;
	}
	public int getTaskConfigId(){
		return this.taskConfigId;
	}
	public void setProcessId(int processId){
		this.processId=processId;
	}
	public int getProcessId(){
		return this.processId;
	}
	public void setExeOrder(int exeOrder){
		this.exeOrder=exeOrder;
	}
	public int getExeOrder(){
		return this.exeOrder;
	}
	public void setAssignedTo(int assignedTo){
		this.assignedTo=assignedTo;
	}
	public int getAssignedTo(){
		return this.assignedTo;
	}
	public void setTaskDate(String taskDate){
		this.taskDate=taskDate;
	}
	public String getTaskDate(){
		return this.taskDate;
	}
	public void setTaskDateFrom(String taskDateFrom){
		this.taskDateFrom=taskDateFrom;
	}
	public String getTaskDateFrom(){
		return this.taskDateFrom;
	}
	public void setTaskDateTo(String taskDateTo){
		this.taskDateTo=taskDateTo;
	}
	public String getTaskDateTo(){
		return this.taskDateTo;
	}
	public void setTaskStatus(String taskStatus){
		this.taskStatus=taskStatus;
	}
	public String getTaskStatus(){
		return this.taskStatus;
	}
	public void setPauseTimeStart(String pauseTimeStart){
		this.pauseTimeStart=pauseTimeStart;
	}
	public String getPauseTimeStart(){
		return this.pauseTimeStart;
	}
	public void setPauseTimeEnd(String pauseTimeEnd){
		this.pauseTimeEnd=pauseTimeEnd;
	}
	public String getPauseTimeEnd(){
		return this.pauseTimeEnd;
	}
	public void setPauseCount(int pauseCount){
		this.pauseCount=pauseCount;
	}
	public int getPauseCount(){
		return this.pauseCount;
	}
	public void setTaskDescription(String taskDescription){
		this.taskDescription=taskDescription;
	}
	public String getTaskDescription(){
		return this.taskDescription;
	}
	public void setRefType(String refType){
		this.refType=refType;
	}
	public String getRefType(){
		return this.refType;
	}
	public void setRefId(int refId){
		this.refId=refId;
	}
	public int getRefId(){
		return this.refId;
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
	public void set(TaskConfigMasterDO taskConfigDO ) {
		
		this.taskId = 0;
		this.processChildId = 0;
		this.taskParticulars = taskConfigDO.getTaskConfigName();
		this.taskConfigId = taskConfigDO.getTaskConfigId();
		this.processId = taskConfigDO.getProcessId();
		this.exeOrder = taskConfigDO.getExeOrder();
		this.assignedTo = AppUtil.getNullToInteger( taskConfigDO.getEmpId() );
		this.taskDate = "";
		this.taskDateFrom = "01/01/1000";
		this.taskDateTo = "01/01/1000";
		this.taskStatus = "pending";
		this.pauseTimeStart = "00:00:00";
		this.pauseTimeEnd = "00:00:00";
		this.pauseCount = 0;
		this.taskDescription = "";
		this.refType = "";
		this.refId = 0;
	}
	public void set(TaskMasterDO taskDO, String startDate, String endDate) {
		this.taskId = taskDO.getTaskId();
		this.processChildId = taskDO.getProcessChildId();
		this.taskParticulars = taskDO.getTaskParticulars();
		this.taskConfigId = taskDO.getTaskConfigId();
		this.processId = taskDO.getProcessId();
		this.exeOrder = taskDO.getExeOrder();
		this.assignedTo = taskDO.getAssignedTo();
		this.taskDate = taskDO.getTaskDate();
		this.taskDateFrom = startDate;
		this.taskDateTo = endDate;
		this.taskStatus = taskDO.getTaskStatus();
		this.pauseTimeStart = taskDO.getPauseTimeStart();
		this.pauseTimeEnd = taskDO.getPauseTimeEnd();
		this.pauseCount = taskDO.getPauseCount();
		this.taskDescription = taskDO.getTaskDescription();
		this.refType = taskDO.getRefType();
		this.refId = taskDO.getRefId();
		this.boolDeleteStatus = taskDO.getBoolDeleteStatus();
		this.createdUser = taskDO.getCreatedUser();
		this.createdDate = taskDO.getCreatedDate();
		this.updateUser = taskDO.getUpdateUser();
		this.updateDate = taskDO.getUpdateDate();
	}

}