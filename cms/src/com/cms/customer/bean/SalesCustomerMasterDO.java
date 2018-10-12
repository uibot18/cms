package com.cms.customer.bean;

import java.util.ArrayList;

import com.cms.common.master.bean.CommonDocumentStoreDO;
import com.cms.finance.bean.FinanceLedgerMasterDO;
import com.cms.finance.bean.FinancePartyAddressDetailsDO;
import com.cms.finance.bean.FinancePartyContactDetailsDO;
import com.cms.finance.bean.FinancePartyPersonalDetailsDO;

public class SalesCustomerMasterDO {
	
	private int customerId=0;
	private int ledgerId=0;
	private boolean boolDeleteStatus=false;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";
	
	private ArrayList<FinancePartyAddressDetailsDO> addressDetailList=new ArrayList<FinancePartyAddressDetailsDO>();
	private ArrayList<FinancePartyContactDetailsDO> contactDetailList=new ArrayList<FinancePartyContactDetailsDO>();
	private ArrayList<CommonDocumentStoreDO> docList=new ArrayList<CommonDocumentStoreDO>();
	private FinanceLedgerMasterDO ledgerMasterDO=new FinanceLedgerMasterDO();
	private FinancePartyPersonalDetailsDO personalDO=new FinancePartyPersonalDetailsDO();
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getLedgerId() {
		return ledgerId;
	}
	public void setLedgerId(int ledgerId) {
		this.ledgerId = ledgerId;
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
	public FinanceLedgerMasterDO getLedgerMasterDO() {
		return ledgerMasterDO;
	}
	public void setLedgerMasterDO(FinanceLedgerMasterDO ledgerMasterDO) {
		this.ledgerMasterDO = ledgerMasterDO;
	}
	public ArrayList<FinancePartyAddressDetailsDO> getAddressDetailList() {
		return addressDetailList;
	}
	public void setAddressDetailList(ArrayList<FinancePartyAddressDetailsDO> addressDetailList) {
		this.addressDetailList = addressDetailList;
	}
	public ArrayList<FinancePartyContactDetailsDO> getContactDetailList() {
		return contactDetailList;
	}
	public void setContactDetailList(ArrayList<FinancePartyContactDetailsDO> contactDetailList) {
		this.contactDetailList = contactDetailList;
	}
	public ArrayList<CommonDocumentStoreDO> getDocList() {
		return docList;
	}
	public void setDocList(ArrayList<CommonDocumentStoreDO> docList) {
		this.docList = docList;
	}
	public FinancePartyPersonalDetailsDO getPersonalDO() {
		return personalDO;
	}
	public void setPersonalDO(FinancePartyPersonalDetailsDO personalDO) {
		this.personalDO = personalDO;
	}
	
	@Override
	public String toString() {
		return "SalesCustomerMasterDO [customerId=" + customerId + ", ledgerId=" + ledgerId + ", boolDeleteStatus="
				+ boolDeleteStatus + ", createdUser=" + createdUser + ", createdDate=" + createdDate + ", updateUser="
				+ updateUser + ", updateDate=" + updateDate + ", addressDetailList=" + addressDetailList
				+ ", contactDetailList=" + contactDetailList + ", docList=" + docList + ", ledgerMasterDO="
				+ ledgerMasterDO + ", personalDO=" + personalDO + "]";
	}
	
}
