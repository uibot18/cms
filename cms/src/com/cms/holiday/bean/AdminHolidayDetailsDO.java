package com.cms.holiday.bean;

public class AdminHolidayDetailsDO { 

private int holidayId=0;
private int holidayTypeId=0;
private String holidayName="";
private String holidaySubType="";
private String holiday="";
private boolean boolDeleteStatus;
private String createdUser="";
private String createdDate="";
private String updateUser="";
private String updateDate="";


public void setHolidayId(int holidayId){
this.holidayId=holidayId;
}
public int getHolidayId(){
return this.holidayId;
}
public void setHolidayTypeId(int holidayTypeId){
this.holidayTypeId=holidayTypeId;
}
public int getHolidayTypeId(){
return this.holidayTypeId;
}
public void setHolidayName(String holidayName){
this.holidayName=holidayName;
}
public String getHolidayName(){
return this.holidayName;
}
public void setHolidaySubType(String holidaySubType){
this.holidaySubType=holidaySubType;
}
public String getHolidaySubType(){
return this.holidaySubType;
}
public void setHoliday(String holiday){
this.holiday=holiday;
}
public String getHoliday(){
return this.holiday;
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