package com.cms.timesheet.bean;

public class TaskTimeSheetChildDO { 

private int timeSheetChildId=0;
private int timeSheetId=0;
private String startTime="";
private String endTime="";
private int refType=0;
private int particularsId=0;
private String comments="";
private boolean boolDeleteStatus;
private String createdUser="";
private String createdDate="";
private String updateUser="";
private String updateDate="";


public void setTimeSheetChildId(int timeSheetChildId){
this.timeSheetChildId=timeSheetChildId;
}
public int getTimeSheetChildId(){
return this.timeSheetChildId;
}
public void setTimeSheetId(int timeSheetId){
this.timeSheetId=timeSheetId;
}
public int getTimeSheetId(){
return this.timeSheetId;
}
public void setStartTime(String startTime){
this.startTime=startTime;
}
public String getStartTime(){
return this.startTime;
}
public void setEndTime(String endTime){
this.endTime=endTime;
}
public String getEndTime(){
return this.endTime;
}
public void setRefType(int refType){
this.refType=refType;
}
public int getRefType(){
return this.refType;
}
public void setParticularsId(int particularsId){
this.particularsId=particularsId;
}
public int getParticularsId(){
return this.particularsId;
}
public void setComments(String comments){
this.comments=comments;
}
public String getComments(){
return this.comments;
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