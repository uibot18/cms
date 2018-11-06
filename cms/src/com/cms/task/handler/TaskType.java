package com.cms.task.handler;

public enum TaskType {

	GeneralTask("general"),
	Customer("customer"),
	;
	
	private String type;
	
	TaskType(String type){
		this.type=type;
	}

	public String getType() {
		return type;
	}
}