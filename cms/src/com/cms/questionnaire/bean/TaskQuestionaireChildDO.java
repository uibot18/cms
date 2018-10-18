package com.cms.questionnaire.bean;

public class TaskQuestionaireChildDO { 

	private int questionaireChildId=0;
	private int questionaireId=0;
	private String option="";
	private boolean boolDeleteStatus;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";


	public void setQuestionaireChildId(int questionaireChildId){
		this.questionaireChildId=questionaireChildId;
	}
	public int getQuestionaireChildId(){
		return this.questionaireChildId;
	}
	public void setQuestionaireId(int questionaireId){
		this.questionaireId=questionaireId;
	}
	public int getQuestionaireId(){
		return this.questionaireId;
	}
	public void setOption(String option){
		this.option=option;
	}
	public String getOption(){
		return this.option;
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