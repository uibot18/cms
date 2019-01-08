package com.cms.employee.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.application.util.AjaxModel;
import com.application.util.AjaxUtil;
import com.application.util.AppDateUtil;
import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.common.master.CmnGroupName;
import com.cms.common.master.dao.CommonMasterDAO;
import com.cms.employee.bean.AdmEmployeeMasterDO;
import com.cms.employee.bean.UserMasterDO;
import com.cms.employee.dao.AdmEmployeeMasterDAO;
import com.cms.employee.dao.UserMasterDAO;
import com.cms.finance.LedgerRefType;
import com.cms.finance.bean.FinanceLedgerMasterDO;
import com.cms.finance.bean.FinancePartyAddressDetailsDO;
import com.cms.finance.bean.FinancePartyContactDetailsDO;
import com.cms.finance.bean.FinancePartyPersonalDetailsDO;
import com.cms.finance.dao.FinancePartyBankBranchDetailsDAO;
import com.cms.user.login.LoginDetail;
import com.cms.user.login.UserType;
import com.cms.user.login.dao.AdminLoginMasterDAO;
import com.cms.user.login.util.LoginUtil;

public class EmployeeCreationHandler {

	public static void doEmployeeAdd( HttpServletRequest request, HttpServletResponse response ) {
		request.setAttribute("employeeDO", new AdmEmployeeMasterDO() );
	}
	public static void doEmployeeEdit( HttpServletRequest request, HttpServletResponse response ) {
		int employeeId=AppUtil.getNullToInteger(request.getParameter("employeeId"));
		AdmEmployeeMasterDO employeeDO =AdmEmployeeMasterDAO.getAdmEmployeeMasterByEmpId(null, employeeId, true);
		request.setAttribute("employeeDO",employeeDO );
	}
	public static void saveEmployee(HttpServletRequest request, HttpServletResponse response) {

		AdmEmployeeMasterDO employeeDO= constructDO( request, response );
		System.out.println("Employee: "+employeeDO.getEmpId());
		
		if(employeeDO.getEmpId()==0) {
			int empId=AdmEmployeeMasterDAO.insert(null, employeeDO);
			if( empId!=0) {
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Employee Detail Successfully Saved..!");
				employeeDO=AdmEmployeeMasterDAO.getAdmEmployeeMasterByEmpId(null, empId, true);
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Employee Details..!");
			}
		}else {
			if(AdmEmployeeMasterDAO.update(null, employeeDO)) {
				employeeDO=AdmEmployeeMasterDAO.getAdmEmployeeMasterByEmpId(null, employeeDO.getEmpId(), true);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Employee Detail Successfully Saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Employee Details..!");
			}
		}

		if(employeeDO==null) { employeeDO=new AdmEmployeeMasterDO();  }
		request.setAttribute("employeeDO", employeeDO );
	}
	private static AdmEmployeeMasterDO constructDO(HttpServletRequest request, HttpServletResponse response) {

		String loginId="Admin";

		AdmEmployeeMasterDO employeeDO=new AdmEmployeeMasterDO();

		int employeeId=AppUtil.getNullToInteger( request.getParameter("employeeId") );
		int ledgerId=AppUtil.getNullToInteger( request.getParameter("ledgerId") );
		String firstName=AppUtil.getNullToEmpty( request.getParameter("firstName") );
		String middleName=AppUtil.getNullToEmpty( request.getParameter("middleName") );
		String lastName=AppUtil.getNullToEmpty( request.getParameter("lastName") );
		int relationTypeId=AppUtil.getNullToInteger( request.getParameter("relationTypeId") );
		String fatherName=AppUtil.getNullToEmpty( request.getParameter("fatherName") );
		String email=AppUtil.getNullToEmpty( request.getParameter("email") );
		String mobile=AppUtil.getNullToEmpty( request.getParameter("mobile") );
		String dob=AppUtil.getNullToEmpty( request.getParameter("dob"),"01/10/1000" );
		int gender=AppUtil.getNullToInteger( request.getParameter("gender") );
		String martialStatus=AppUtil.getNullToEmpty( request.getParameter("martialStatus") );
		String bloodGroup=AppUtil.getNullToEmpty( request.getParameter("bloodGroup") , "none");
		
		employeeDO.setEmpId(employeeId);
		employeeDO.setLedgerId(ledgerId);
		employeeDO.setReportingTo( AppUtil.getNullToInteger( request.getParameter("reportingTo") ) );
		employeeDO.setDepartmentId( AppUtil.getNullToInteger( request.getParameter("department") ) );
		employeeDO.setDesignationId( AppUtil.getNullToInteger( request.getParameter("designation") ) );
		employeeDO.setEsiNo( AppUtil.getNullToEmpty( request.getParameter("esiNo") ) );
		employeeDO.setEpfNo( AppUtil.getNullToEmpty( request.getParameter("epfNo") ) );
		employeeDO.setBankBranchId( AppUtil.getNullToInteger( request.getParameter("branchName") ) );
		employeeDO.setBankAccountNo( AppUtil.getNullToEmpty( request.getParameter("bankAccountNo") ) ); 
		employeeDO.setBoolDeleteStatus(false);
		employeeDO.setCreatedUser( loginId );
		employeeDO.setCreatedDate( AppDateUtil.currentDate(true) );
		employeeDO.setUpdateUser(loginId);
		employeeDO.setUpdateDate( AppDateUtil.currentDate(true) );

		FinanceLedgerMasterDO ledgerDO=new FinanceLedgerMasterDO();
		ledgerDO.setLedgerName( firstName );
		ledgerDO.setCompanyId(1);
		ledgerDO.setLedgerId( ledgerId );
		ledgerDO.setGroupId( 16 );
		ledgerDO.setRef_type( LedgerRefType.EMPLOYEE.getType() );
		ledgerDO.setRef_id( employeeId );
		ledgerDO.setBoolDeleteStatus(false);
		ledgerDO.setCreatedUser( loginId );
		ledgerDO.setCreatedDate( AppDateUtil.currentDate(true) );
		ledgerDO.setUpdateUser(loginId);
		ledgerDO.setUpdateDate( AppDateUtil.currentDate(true) );
		employeeDO.setLedgerMstDO( ledgerDO );

		FinancePartyPersonalDetailsDO personalDO=new FinancePartyPersonalDetailsDO();
		personalDO.setLedgerId(ledgerId);
		personalDO.setFirstName( firstName );
		personalDO.setMiddleName( middleName );
		personalDO.setLastName( lastName );
		personalDO.setRelationTypeId( relationTypeId );
		personalDO.setFirstNameRel( fatherName  );
		personalDO.setMaritalStatus( martialStatus );
		personalDO.setGender(gender);
		personalDO.setDob(dob);
		personalDO.setBloodGroup(bloodGroup);
		personalDO.setBoolDeleteStatus(false);
		personalDO.setCreatedUser( loginId );
		personalDO.setCreatedDate( AppDateUtil.currentDate(true) );
		personalDO.setUpdateUser(loginId);
		personalDO.setUpdateDate( AppDateUtil.currentDate(true) );
		employeeDO.setPersonalDO( personalDO );

		FinancePartyContactDetailsDO contactDO=new FinancePartyContactDetailsDO();
		contactDO.setLedgerId(ledgerId);
		contactDO.setContactTypeId(18);
		contactDO.setRefType( LedgerRefType.EMPLOYEE.getType() );
		contactDO.setRefId(employeeId);
		contactDO.setEmail1(email);
		contactDO.setMobile1(mobile);
		contactDO.setBoolDeleteStatus(false);
		contactDO.setCreatedUser( loginId );
		contactDO.setCreatedDate( AppDateUtil.currentDate(true) );
		contactDO.setUpdateUser(loginId);
		contactDO.setUpdateDate( AppDateUtil.currentDate(true) );
		employeeDO.setContactDO(contactDO);


		ArrayList<FinancePartyAddressDetailsDO> addressList=new ArrayList<FinancePartyAddressDetailsDO>();

		FinancePartyAddressDetailsDO perAddressDO=new FinancePartyAddressDetailsDO();
		perAddressDO.setLedgerId(ledgerId);
		perAddressDO.setRefType( LedgerRefType.EMPLOYEE.getType() );
		perAddressDO.setRefId(employeeId);
		perAddressDO.setAddressTypeId(15);
		perAddressDO.setDooNo( AppUtil.getNullToEmpty( request.getParameter("per_doorNo") ) );
		perAddressDO.setStreetName( AppUtil.getNullToEmpty( request.getParameter("per_streetName") )  );
		perAddressDO.setRoadName( AppUtil.getNullToEmpty( request.getParameter("per_roadName") ) );
		perAddressDO.setLandMark( AppUtil.getNullToEmpty( request.getParameter("per_landMark") ) );
		perAddressDO.setCity( AppUtil.getNullToEmpty( request.getParameter("per_city") ) );
		perAddressDO.setState( AppUtil.getNullToEmpty( request.getParameter("per_state") ) );
		perAddressDO.setPincode( AppUtil.getNullToEmpty( request.getParameter("per_pinCode") ) );
		perAddressDO.setBoolDeleteStatus(false);
		perAddressDO.setCreatedUser( loginId );
		perAddressDO.setCreatedDate( AppDateUtil.currentDate(true) );
		perAddressDO.setUpdateUser(loginId);
		perAddressDO.setUpdateDate( AppDateUtil.currentDate(true) );
		addressList.add( perAddressDO );

		FinancePartyAddressDetailsDO cmmAddressDO=new FinancePartyAddressDetailsDO();
		cmmAddressDO.setLedgerId(ledgerId);
		cmmAddressDO.setRefType( LedgerRefType.EMPLOYEE.getType() );
		cmmAddressDO.setRefId(employeeId);
		cmmAddressDO.setAddressTypeId(16);
		cmmAddressDO.setDooNo( AppUtil.getNullToEmpty( request.getParameter("cmm_doorNo") ) );
		cmmAddressDO.setStreetName( AppUtil.getNullToEmpty( request.getParameter("cmm_streetName") )  );
		cmmAddressDO.setRoadName( AppUtil.getNullToEmpty( request.getParameter("cmm_roadName") ) );
		cmmAddressDO.setLandMark( AppUtil.getNullToEmpty( request.getParameter("cmm_landMark") ) );
		cmmAddressDO.setCity( AppUtil.getNullToEmpty( request.getParameter("cmm_city") ) );
		cmmAddressDO.setState( AppUtil.getNullToEmpty( request.getParameter("cmm_state") ) );
		cmmAddressDO.setPincode( AppUtil.getNullToEmpty( request.getParameter("cmm_pinCode") ) );
		cmmAddressDO.setBoolDeleteStatus(false);
		cmmAddressDO.setCreatedUser( loginId );
		cmmAddressDO.setCreatedDate( AppDateUtil.currentDate(true) );
		cmmAddressDO.setUpdateUser(loginId);
		cmmAddressDO.setUpdateDate( AppDateUtil.currentDate(true) );
		addressList.add( cmmAddressDO );

		employeeDO.setAddressList( addressList );

		return employeeDO;
	}

	public static String formDepartmentOption( String selDepartment ) {

		String subQry=" AND cmn_group_id IN( select cmn_group_id from common_group_master where cmn_group_name='"+CmnGroupName.DEPARTMENT.getGroupName()+"' ) ";
		Map<String, String> cmnMap=CommonMasterDAO.getCommonDetMapBySubQry(null, subQry);
		if(cmnMap==null){ cmnMap=new HashMap<String, String>(); }

		return AppUtil.formOption(cmnMap, selDepartment);
	}
	public static String formDesignationOption( String deptIds, String selDesignation ) {

		deptIds = AppUtil.getNullToEmpty( deptIds );
		String subQry=" AND cmn_group_id IN( select cmn_group_id from common_group_master where cmn_group_name='"+CmnGroupName.DESIGNATION.getGroupName()+"' ) ";
		if(!deptIds.isEmpty()) { subQry+=" and parent_id in( "+deptIds+" )"; }
		Map<String, String> cmnMap=CommonMasterDAO.getCommonDetMapBySubQry(null, subQry);
		if(cmnMap==null){ cmnMap=new HashMap<String, String>(); }

		return AppUtil.formOption(cmnMap, selDesignation);
	}
	public static String formEmployeeOption( String selEmpId ) {

		Map<String, String> cmnMap=AdmEmployeeMasterDAO.loadAllEmpNameMap(null, "");
		if(cmnMap==null){ cmnMap=new LinkedHashMap<String, String>(); }

		return AppUtil.formOption(cmnMap, selEmpId);
	}
	public static String formEmployeeOption( String selEmpId, String empIdExclue ) {

		Map<String, String> cmnMap=AdmEmployeeMasterDAO.loadAllEmpNameMap(null, "", empIdExclue );
		if(cmnMap==null){ cmnMap=new LinkedHashMap<String, String>(); }

		return AppUtil.formOption(cmnMap, selEmpId);
	}
	public static String formEmployeeDisplay( String selEmpId, String empIdExclue ) {

		Map<String, String> cmnMap=AdmEmployeeMasterDAO.loadAllEmpNameMap(null, selEmpId, empIdExclue );
		if(cmnMap==null){ cmnMap=new LinkedHashMap<String, String>(); }

		return AppUtil.formDisplay(cmnMap, selEmpId);
	}
	
	public static String formCmnMasterDisplay( String masterId ) {
if(masterId.isEmpty()) masterId="0";
		String subQry=" AND cmn_master_id IN("+masterId+" ) ";
		Map<String, String> cmnMap=CommonMasterDAO.getCommonDetMapBySubQry(null, subQry);
		if(cmnMap==null){ cmnMap=new HashMap<String, String>(); }

		return AppUtil.formDisplay(cmnMap, masterId);
	}
	public static void loadBranch(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		JSONObject jsonObject=new JSONObject();
		try {
			int bankId=AppUtil.getNullToInteger( request.getParameter("bankId") );
			Map<String, String> branchMap=FinancePartyBankBranchDetailsDAO.loadBranchMap(null, ""+bankId);
			if(branchMap==null) { branchMap=new HashMap<String, String>(); }
			
			jsonObject.put("option", AppUtil.getNullToEmpty( AppUtil.formOption(branchMap, "")));
		} catch (Exception e) { e.printStackTrace(); }
		PrintWriter pw=response.getWriter();
		pw.write(jsonObject.toString() );
		pw.flush();
		pw.close();
	}
	
	public static void doEmployeeDelete( HttpServletRequest request, HttpServletResponse response ) {
		int employeeId=AppUtil.getNullToInteger(request.getParameter("employeeId"));
		String loginId="Admin";
		AdmEmployeeMasterDO employeeDO =new AdmEmployeeMasterDO();
		employeeDO.setEmpId(employeeId);
		employeeDO.setUpdateUser(loginId);
		employeeDO.setBoolDeleteStatus(true);
		
		AjaxModel model=new AjaxModel();
		if(AdmEmployeeMasterDAO.deleteupdate(null, employeeDO)) {
			model.setMessage(" Deleted Successfully");
		}else {
			model.setMessage(" Unable to Delete");model.setErrorExists(true);
		}
		AjaxUtil.sendResponse(request, response, model);
	}
	public static void doEmployeeRightsMapping(HttpServletRequest request, HttpServletResponse response) {
		
		LoginDetail detail = LoginUtil.getLoginDetail(request);
		
		String[] empIdArr = request.getParameterValues("empIds");
		Set<String> empIdSet = AppUtil.convertStrArrayToSet(empIdArr);
		System.out.println("empIdSet: "+empIdSet);
		if(empIdSet!=null) {
			for (String empIdStr : empIdSet) {
				int empId = AppUtil.getNullToInteger(empIdStr);
				int templateId=AppUtil.getNullToInteger( request.getParameter("rights_ids_"+empId) );
				System.out.println("templateId:"+templateId);
				if(templateId!=0) {
					boolean a = AdminLoginMasterDAO.updateRights(null, templateId, detail.getLoginId(), UserType.EMPLOYEE.getType(), empId); 
				}
				
			}
		}
	}
	public static void loadEmployeeDesignation(HttpServletRequest request, HttpServletResponse response) {
		AjaxModel model = new AjaxModel();
		String deptIds = AppUtil.getNullToEmpty(request.getParameter("deptIds"), "0");
		model.setData( formDesignationOption(deptIds, "") );
		AjaxUtil.sendResponse(request, response, model);
	}
	
	/*save new employee*/
	
	public static void savenewEmployee(HttpServletRequest request, HttpServletResponse response) {

		UserMasterDO userDo=constructUserDO( request, response );
			int empId=UserMasterDAO.insert(null, userDo);
			if( empId!=0) {
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Employee Detail Successfully Saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Employee Details..!");
			}

	}
	
	
	
	private static UserMasterDO constructUserDO(HttpServletRequest request, HttpServletResponse response) {

		String loginId="Admin";

		UserMasterDO employeeDO=new UserMasterDO();

		String firstName=AppUtil.getNullToEmpty( request.getParameter("firstName") );
		String middleName=AppUtil.getNullToEmpty( request.getParameter("middleName") );
		String lastName=AppUtil.getNullToEmpty( request.getParameter("lastName") );
		String email=AppUtil.getNullToEmpty( request.getParameter("email") );
		int role=AppUtil.getNullToInteger( request.getParameter("role") );
		int profile=AppUtil.getNullToInteger( request.getParameter("profile") );
		
		employeeDO.setFirstName(firstName);
		employeeDO.setLastName(lastName);
		employeeDO.setEmail(email);
		employeeDO.setProfile(profile);
		employeeDO.setRole(role);

		return employeeDO;
	}
	
	public static String formRoleOption( String selRole ) {

		String subQry=" AND cmn_group_id IN( select cmn_group_id from common_group_master where cmn_group_name='"+CmnGroupName.DEPARTMENT.getGroupName()+"' ) ";
		Map<String, String> cmnMap=CommonMasterDAO.getCommonDetMapBySubQry(null, subQry);
		if(cmnMap==null){ cmnMap=new HashMap<String, String>(); }

		return AppUtil.formOption(cmnMap, selRole);
	}
	
	
	public static String formProfileOption( String selRole ) {

		String subQry=" AND cmn_group_id IN( select cmn_group_id from common_group_master where cmn_group_name='"+CmnGroupName.SERVICE.getGroupName()+"' ) ";
		Map<String, String> cmnMap=CommonMasterDAO.getCommonDetMapBySubQry(null, subQry);
		if(cmnMap==null){ cmnMap=new HashMap<String, String>(); }

		return AppUtil.formOption(cmnMap, selRole);
	}
}
