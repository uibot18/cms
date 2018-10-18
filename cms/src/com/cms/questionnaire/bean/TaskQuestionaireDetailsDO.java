package com.cms.questionnaire.bean;

import java.util.ArrayList;
import java.util.List;

public class TaskQuestionaireDetailsDO { 

	private int questionaireId=0;
	private int taskConfigId=0;
	private String questionaireName="";
	private String answerType="na";
	private String description="";
	private boolean boolDeleteStatus;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";
	
	
	private List<TaskQuestionaireChildDO> questionaryChildList=new ArrayList<TaskQuestionaireChildDO>();


	public void setQuestionaireId(int questionaireId){
		this.questionaireId=questionaireId;
	}
	public int getQuestionaireId(){
		return this.questionaireId;
	}
	public void setTaskConfigId(int taskConfigId){
		this.taskConfigId=taskConfigId;
	}
	public int getTaskConfigId(){
		return this.taskConfigId;
	}
	public void setQuestionaireName(String questionaireName){
		this.questionaireName=questionaireName;
	}
	public String getQuestionaireName(){
		return this.questionaireName;
	}
	public void setAnswerType(String answerType){
		this.answerType=answerType;
	}
	public String getAnswerType(){
		return this.answerType;
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
	public List<TaskQuestionaireChildDO> getQuestionaryChildList() {
		return questionaryChildList;
	}
	public void setQuestionaryChildList(List<TaskQuestionaireChildDO> questionaryList) {
		this.questionaryChildList = questionaryList;
	}

}