package com.cms.task.bean;

public class TaskMasterQuestionaireDO { 

	private int taskQuestId=0;
	private int taskId=0;
	private int questionaireId=0;
	private String options="";
	private String description="";
	private boolean boolDeleteStatus;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";


	public void setTaskQuestId(int taskQuestId){
		this.taskQuestId=taskQuestId;
	}
	public int getTaskQuestId(){
		return this.taskQuestId;
	}
	public void setTaskId(int taskId){
		this.taskId=taskId;
	}
	public int getTaskId(){
		return this.taskId;
	}
	public void setQuestionaireId(int questionaireId){
		this.questionaireId=questionaireId;
	}
	public int getQuestionaireId(){
		return this.questionaireId;
	}
	public void setOptions(String options){
		this.options=options;
	}
	public String getOptions(){
		return this.options;
	}
	public void setDescription(String description){
		this.description=description;
	}
	public String getDescription(){
		return this.description;
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

}