package com.cms.menu.bean;

public class MenuMasterDO { 

private int menuId=0;
private String menuName="";
private String menuAction="";
private boolean boolDeleteStatus;
private String createdUser="";
private String createdDate="";
private String updateUser="";
private String updateDate="";


public void setMenuId(int menuId){
this.menuId=menuId;
}
public int getMenuId(){
return this.menuId;
}
public void setMenuName(String menuName){
this.menuName=menuName;
}
public String getMenuName(){
return this.menuName;
}
public void setMenuAction(String menuAction){
this.menuAction=menuAction;
}
public String getMenuAction(){
return this.menuAction;
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