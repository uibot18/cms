package com.cms.common.master.bean;

public class CommonDocumentStoreDO {

	private int documentId=0;
	private int documentTypeId=0;
	private String nameInDocument="";
	private String documentNo="";
	private String documentIssueDate="";
	private String validUpto="";
	private String refType="";
	private int refId=0;
	private boolean boolDeleteStatus=false;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";
	
	public int getDocumentId() {
		return documentId;
	}
	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}
	public int getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(int documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	public String getNameInDocument() {
		return nameInDocument;
	}
	public void setNameInDocument(String nameInDocument) {
		this.nameInDocument = nameInDocument;
	}
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	public String getDocumentIssueDate() {
		return documentIssueDate;
	}
	public void setDocumentIssueDate(String documentIssueDate) {
		this.documentIssueDate = documentIssueDate;
	}
	public String getValidUpto() {
		return validUpto;
	}
	public void setValidUpto(String validUpto) {
		this.validUpto = validUpto;
	}
	public String getRefType() {
		return refType;
	}
	public void setRefType(String refType) {
		this.refType = refType;
	}
	public int getRefId() {
		return refId;
	}
	public void setRefId(int refId) {
		this.refId = refId;
	}
	public boolean isBoolDeleteStatus() {
		return boolDeleteStatus;
	}
	public void setBoolDeleteStatus(boolean boolDeleteStatus) {
		this.boolDeleteStatus = boolDeleteStatus;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return "CommonDocumentStore [documentId=" + documentId + ", documentTypeId=" + documentTypeId
				+ ", nameInDocument=" + nameInDocument + ", document_no=" + documentNo + ", documentIssueDate="
				+ documentIssueDate + ", validUpto=" + validUpto + ", refType=" + refType + ", refId=" + refId
				+ ", boolDeleteStatus=" + boolDeleteStatus + ", createdUser=" + createdUser + ", createdDate="
				+ createdDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate + "]";
	}

	
}
