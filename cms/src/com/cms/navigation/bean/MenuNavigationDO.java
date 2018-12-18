package com.cms.navigation.bean;

public class MenuNavigationDO { 

private int navigationId=0;
private String navigationName="";
private int parentNavigationId=0;
private boolean boolIsMenu;
private int menuId=0;
private int menuOrder=0;
private boolean boolDeleteStatus;
private String createdUser="";
private String createdDate="";
private String updateUser="";
private String updateDate="";


public void setNavigationId(int navigationId){
this.navigationId=navigationId;
}
public int getNavigationId(){
return this.navigationId;
}
public void setNavigationName(String navigationName){
this.navigationName=navigationName;
}
public String getNavigationName(){
return this.navigationName;
}
public void setParentNavigationId(int parentNavigationId){
this.parentNavigationId=parentNavigationId;
}
public int getParentNavigationId(){
return this.parentNavigationId;
}
public void setBoolIsMenu(boolean boolIsMenu){
this.boolIsMenu=boolIsMenu;
}
public boolean getBoolIsMenu(){
return this.boolIsMenu;
}
public void setMenuId(int menuId){
this.menuId=menuId;
}
public int getMenuId(){
return this.menuId;
}
public int getMenuOrder() {
	return menuOrder;
}
public void setMenuOrder(int menuOrder) {
	this.menuOrder = menuOrder;
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