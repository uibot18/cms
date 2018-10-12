package com.cms.holiday.bean;

public class AdminHolidayTypeDO { 

private int holidayTypeId=0;
private String holidayType="";
private boolean boolDeleteStatus;
private String createdUser="";
private String createdDate="";
private String updateUser="";
private String updateDate="";


public void setHolidayTypeId(int holidayTypeId){
this.holidayTypeId=holidayTypeId;
}
public int getHolidayTypeId(){
return this.holidayTypeId;
}
public void setHolidayType(String holidayType){
this.holidayType=holidayType;
}
public String getHolidayType(){
return this.holidayType;
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