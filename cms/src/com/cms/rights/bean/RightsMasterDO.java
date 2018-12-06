package com.cms.rights.bean;

public class RightsMasterDO { 

private int rightsMasterId=0;
private int rightsId=0;
private String rightsGroupName="";
private String rightsName="";
private boolean boolDeleteStatus;
private String createdUser="";
private String createdDate="";
private String updateUser="";
private String updateDate="";


public void setRightsMasterId(int rightsMasterId){
this.rightsMasterId=rightsMasterId;
}
public int getRightsMasterId(){
return this.rightsMasterId;
}
public void setRightsId(int rightsId){
this.rightsId=rightsId;
}
public int getRightsId(){
return this.rightsId;
}
public void setRightsGroupName(String rightsGroupName){
this.rightsGroupName=rightsGroupName;
}
public String getRightsGroupName(){
return this.rightsGroupName;
}
public void setRightsName(String rightsName){
this.rightsName=rightsName;
}
public String getRightsName(){
return this.rightsName;
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