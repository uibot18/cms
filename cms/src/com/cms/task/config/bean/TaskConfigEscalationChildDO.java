package com.cms.task.config.bean;

public class TaskConfigEscalationChildDO { 

	private int taskConfigEscalationId=0;
	private int taskConfigId=0;
	private String department="";
	private String designation="";
	private String empId="";
	private int ticketDuration=0;
	private String ticketDurationUom="na";
	private boolean boolDeleteStatus;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";


	public void setTaskConfigEscalationId(int taskConfigEscalationId){
		this.taskConfigEscalationId=taskConfigEscalationId;
	}
	public int getTaskConfigEscalationId(){
		return this.taskConfigEscalationId;
	}
	public void setTaskConfigId(int taskConfigId){
		this.taskConfigId=taskConfigId;
	}
	public int getTaskConfigId(){
		return this.taskConfigId;
	}
	public void setDepartment(String department){
		this.department=department;
	}
	public String getDepartment(){
		return this.department;
	}
	public void setDesignation(String designation){
		this.designation=designation;
	}
	public String getDesignation(){
		return this.designation;
	}
	public void setEmpId(String empId){
		this.empId=empId;
	}
	public String getEmpId(){
		return this.empId;
	}
	public void setTicketDuration(int ticketDuration){
		this.ticketDuration=ticketDuration;
	}
	public int getTicketDuration(){
		return this.ticketDuration;
	}
	public void setTicketDurationUom(String ticketDurationUom){
		this.ticketDurationUom=ticketDurationUom;
	}
	public String getTicketDurationUom(){
		return this.ticketDurationUom;
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
	
	@Override
	public String toString() {
		return "TaskConfigEscalationChildDO [taskConfigEscalationId=" + taskConfigEscalationId + ", taskConfigId="
				+ taskConfigId + ", department=" + department + ", designation=" + designation + ", empId=" + empId
				+ ", ticketDuration=" + ticketDuration + ", ticketDurationUom=" + ticketDurationUom
				+ ", boolDeleteStatus=" + boolDeleteStatus + ", createdUser=" + createdUser + ", createdDate="
				+ createdDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate + "]";
	}
	
}