package com.cms.rights_template.bean;

public class RightsTemplateDO { 

private int rightsTemplateId=0;
private String rightsTemplateName="";
private String menuIds="";
private String rightsIds="";
private boolean boolDeleteStatus;
private String createdUser="";
private String createdDate="";
private String updateUser="";
private String updateDate="";


public void setRightsTemplateId(int rightsTemplateId){
this.rightsTemplateId=rightsTemplateId;
}
public int getRightsTemplateId(){
return this.rightsTemplateId;
}
public void setRightsTemplateName(String rightsTemplateName){
this.rightsTemplateName=rightsTemplateName;
}
public String getRightsTemplateName(){
return this.rightsTemplateName;
}
public void setMenuIds(String menuIds){
this.menuIds=menuIds;
}
public String getMenuIds(){
return this.menuIds;
}
public void setRightsIds(String rightsIds){
this.rightsIds=rightsIds;
}
public String getRightsIds(){
return this.rightsIds;
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