package com.cms.common.master;

public enum CmnGroupName {

	SALUTATION(1, "salutation"),
	STATUTORY_DOCUMENTS(2, "statutory documents"),
	COMMUNICATION_TYPE(3, "Communication Type"),
	ADDRESS_TYPE(4, "Address Type"),
	PARTY_TYPE(5, "party_type"),
	PARTY_RELATION(6,"Party Relation"),
	TASK_FLOW(7, "Task Flow"),
	DEPARTMENT(8, "department"),
	DESIGNATION(9, "designation"),
	SERVICE(10, "service"),
	PACKAGE(11, "package"),
	PROCESS(12, "process"),
	TIME_SHEET_PROCESS(13, "time sheet process"),
	TIME_SHEET_PROCESS_CHILD(14, "time_sheet_process_child"),
	USER_ROLES(15, "user_roles"),
	USER_PROFILES(16, "user_profiles"),
	BLOOD_GROUP(17, "blood_group"),
	STATE(18, "state"),
	COUNTRY(19, "country"),
	ROLR(20, "country"),
	;
	private int groupId;
	private String groupName;
	CmnGroupName( int groupId, String groupName ){
		this.groupId=groupId;
		this.groupName=groupName;
	}
	
	
	public int getGroupId() {
		return groupId;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
}
