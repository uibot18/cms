package com.cms.employee.bean;

import java.util.ArrayList;

import com.cms.finance.bean.FinanceLedgerMasterDO;
import com.cms.finance.bean.FinancePartyAddressDetailsDO;
import com.cms.finance.bean.FinancePartyContactDetailsDO;
import com.cms.finance.bean.FinancePartyPersonalDetailsDO;

public class AdmEmployeeMasterDO {
	private int EmpId=0;
	private int LedgerId=0;
	private int ReportingTo=0;
	private int DepartmentId=0;
	private int DepartmentChildId=0;
	private int DesignationId=0;
	private String EsiNo="";
	private String EpfNo="";
	private String UanNo="";
	private int BankBranchId=0;
	private String BankAccountNo="";
	private boolean boolDeleteStatus=false;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";
	
	private FinanceLedgerMasterDO ledgerMstDO=new FinanceLedgerMasterDO();
	private FinancePartyPersonalDetailsDO personalDO=new FinancePartyPersonalDetailsDO();
	private FinancePartyContactDetailsDO contactDO=new FinancePartyContactDetailsDO();
	private ArrayList<FinancePartyAddressDetailsDO> addressList=new ArrayList<FinancePartyAddressDetailsDO>();
	
	public int getEmpId() {
		return EmpId;
	}
	public void setEmpId(int empId) {
		EmpId = empId;
	}
	public int getLedgerId() {
		return LedgerId;
	}
	public void setLedgerId(int ledgerId) {
		LedgerId = ledgerId;
	}
	public int getReportingTo() {
		return ReportingTo;
	}
	public void setReportingTo(int reportingTo) {
		ReportingTo = reportingTo;
	}
	public int getDepartmentId() {
		return DepartmentId;
	}
	public void setDepartmentId(int departmentId) {
		DepartmentId = departmentId;
	}
	public int getDepartmentChildId() {
		return DepartmentChildId;
	}
	public void setDepartmentChildId(int departmentChildId) {
		DepartmentChildId = departmentChildId;
	}
	public int getDesignationId() {
		return DesignationId;
	}
	public void setDesignationId(int designationId) {
		DesignationId = designationId;
	}
	public String getEsiNo() {
		return EsiNo;
	}
	public void setEsiNo(String esiNo) {
		EsiNo = esiNo;
	}
	public String getEpfNo() {
		return EpfNo;
	}
	public void setEpfNo(String epfNo) {
		EpfNo = epfNo;
	}
	public String getUanNo() {
		return UanNo;
	}
	public void setUanNo(String uanNo) {
		UanNo = uanNo;
	}
	public int getBankBranchId() {
		return BankBranchId;
	}
	public void setBankBranchId(int bankBranchId) {
		BankBranchId = bankBranchId;
	}
	public String getBankAccountNo() {
		return BankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		BankAccountNo = bankAccountNo;
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
	public FinanceLedgerMasterDO getLedgerMstDO() {
		return ledgerMstDO;
	}
	public void setLedgerMstDO(FinanceLedgerMasterDO ledgerMstDO) {
		this.ledgerMstDO = ledgerMstDO;
	}
	public FinancePartyPersonalDetailsDO getPersonalDO() {
		return personalDO;
	}
	public void setPersonalDO(FinancePartyPersonalDetailsDO personalDO) {
		this.personalDO = personalDO;
	}
	public FinancePartyContactDetailsDO getContactDO() {
		return contactDO;
	}
	public void setContactDO(FinancePartyContactDetailsDO contactDO) {
		this.contactDO = contactDO;
	}
	public ArrayList<FinancePartyAddressDetailsDO> getAddressList() {
		return addressList;
	}
	public void setAddressList(ArrayList<FinancePartyAddressDetailsDO> addressList) {
		this.addressList = addressList;
	}
	
	@Override
	public String toString() {
		return "AdmEmployeeMasterDO [EmpId=" + EmpId + ", LedgerId=" + LedgerId + ", ReportingTo=" + ReportingTo
				+ ", DepartmentId=" + DepartmentId + ", DepartmentChildId=" + DepartmentChildId + ", DesignationId="
				+ DesignationId + ", EsiNo=" + EsiNo + ", EpfNo=" + EpfNo + ", UanNo=" + UanNo + ", BankBranchId="
				+ BankBranchId + ", BankAccountNo=" + BankAccountNo + ", boolDeleteStatus=" + boolDeleteStatus
				+ ", createdUser=" + createdUser + ", createdDate=" + createdDate + ", updateUser=" + updateUser
				+ ", updateDate=" + updateDate + ", ledgerMstDO=" + ledgerMstDO + ", personalDO=" + personalDO
				+ ", contactDO=" + contactDO + ", addressList=" + addressList + "]";
	}
	
}
