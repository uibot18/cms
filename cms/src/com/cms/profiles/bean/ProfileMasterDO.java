package com.cms.profiles.bean;

public class ProfileMasterDO { 

private int profileId=0;
private String profileName="";
private String profileDescription="";
private String profileRights="";
private String createdBy="";
private String createdOn="";
private String updatedBy="";
private String updatedOn="";


public void setProfileId(int profileId){
this.profileId=profileId;
}
public int getProfileId(){
return this.profileId;
}
public void setProfileName(String profileName){
this.profileName=profileName;
}
public String getProfileName(){
return this.profileName;
}
public void setProfileDescription(String profileDescription){
this.profileDescription=profileDescription;
}
public String getProfileDescription(){
return this.profileDescription;
}
public void setProfileRights(String profileRights){
this.profileRights=profileRights;
}
public String getProfileRights(){
return this.profileRights;
}
public void setCreatedBy(String createdBy){
this.createdBy=createdBy;
}
public String getCreatedBy(){
return this.createdBy;
}
public void setCreatedOn(String createdOn){
this.createdOn=createdOn;
}
public String getCreatedOn(){
return this.createdOn;
}
public void setUpdatedBy(String updatedBy){
this.updatedBy=updatedBy;
}
public String getUpdatedBy(){
return this.updatedBy;
}
public void setUpdatedOn(String updatedOn){
this.updatedOn=updatedOn;
}
public String getUpdatedOn(){
return this.updatedOn;
}

}