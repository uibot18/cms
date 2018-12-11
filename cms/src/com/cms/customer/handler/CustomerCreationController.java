package com.cms.customer.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AjaxModel;
import com.application.util.AjaxUtil;
import com.application.util.AppDateUtil;
import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.common.master.bean.CommonDocumentStoreDO;
import com.cms.customer.bean.SalesCustomerMasterDO;
import com.cms.customer.dao.SalesCustomerMasterDAO;
import com.cms.finance.LedgerRefType;
import com.cms.finance.bean.FinanceLedgerMasterDO;
import com.cms.finance.bean.FinancePartyAddressDetailsDO;
import com.cms.finance.bean.FinancePartyContactDetailsDO;
import com.cms.finance.bean.FinancePartyPersonalDetailsDO;
import com.cms.user.login.LoginDetail;
import com.cms.user.login.util.LoginUtil;

public class CustomerCreationController {

	public static void doCustomerSave(HttpServletRequest request, HttpServletResponse response) {

		SalesCustomerMasterDO customerDO=costructDTO( request, response);
		if(customerDO!=null) {

			if(customerDO.getCustomerId()==0) {
				//				insert
				int customerId=SalesCustomerMasterDAO.insert(null, customerDO);
				if(customerId!=0) {
					customerDO =SalesCustomerMasterDAO.getSalesCustomerMasterByCustomerId(null, customerId, true);
					request.setAttribute(PageAlertType.SUCCESS.getType(), "Customer Detail Successfully Saved..!");
				}else {
					request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Customer Details..!");
				}
			}else {
				//update	
				if( SalesCustomerMasterDAO.update(null, customerDO) ) {
					customerDO =SalesCustomerMasterDAO.getSalesCustomerMasterByCustomerId(null, customerDO.getCustomerId(), true);
					request.setAttribute(PageAlertType.SUCCESS.getType(), "Customer Detail Successfully Saved..!");
				}else {
					request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Customer Details..!");
				}
			}

		}else {
			request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Customer Details..!");
		}

		if(customerDO==null) { customerDO=new SalesCustomerMasterDO(); }
		request.setAttribute("customerDO", customerDO);

	}

	private static SalesCustomerMasterDO costructDTO(HttpServletRequest request, HttpServletResponse response) {

		LoginDetail logindetail = LoginUtil.getLoginDetail(request);
		String loginId=logindetail.getLoginId();

//		LoginDetail loginDetail=(LoginDetail) request.getSession().getAttribute( LoginEnum.LOGIN_DETAIL.getType() );

		int companyId=AppUtil.getNullToInteger( request.getParameter("companyId") , 1);
		int customerId=AppUtil.getNullToInteger( request.getParameter("customerId") );
		int ledgerId=AppUtil.getNullToInteger( request.getParameter("ledgerId") );
		String customerName=AppUtil.getNullToEmpty( request.getParameter("customerName") );
		String doorNo=AppUtil.getNullToEmpty( request.getParameter("doorNo") );
		String streetName=AppUtil.getNullToEmpty( request.getParameter("streetName") );
		String roadName=AppUtil.getNullToEmpty( request.getParameter("roadName") );
		String landMark=AppUtil.getNullToEmpty( request.getParameter("landMark") );
		String city=AppUtil.getNullToEmpty( request.getParameter("city") );
		String state=AppUtil.getNullToEmpty( request.getParameter("state") );
		String pincode=AppUtil.getNullToEmpty( request.getParameter("pinCode") );
		String contactPerson=AppUtil.getNullToEmpty( request.getParameter("contactPerson") );
		String email=AppUtil.getNullToEmpty( request.getParameter("email") );
		String mobile=AppUtil.getNullToEmpty( request.getParameter("mobileNumber") );
		String webSite=AppUtil.getNullToEmpty( request.getParameter("webSite") );
		String pan=AppUtil.getNullToEmpty( request.getParameter("pan") );
		String gst=AppUtil.getNullToEmpty( request.getParameter("gst") );

		SalesCustomerMasterDO customerDTO=new SalesCustomerMasterDO();
		customerDTO.setCustomerId(customerId);
		customerDTO.setLedgerId(ledgerId);
		customerDTO.setCreatedUser( loginId );
		customerDTO.setCreatedDate( AppDateUtil.currentDate(true) );
		customerDTO.setUpdateUser(loginId);
		customerDTO.setUpdateDate( AppDateUtil.currentDate(true) );

		FinanceLedgerMasterDO ledgerDO=new FinanceLedgerMasterDO();
		ledgerDO.setLedgerId( ledgerId );
		ledgerDO.setCompanyId( companyId ); 
		ledgerDO.setGroupId( 15 );
		ledgerDO.setLedgerName( customerName );
		ledgerDO.setRef_type( LedgerRefType.CUSTOMER.getType() );
		ledgerDO.setRef_id( customerId );
		ledgerDO.setBoolDeleteStatus(false);
		ledgerDO.setCreatedUser( loginId );
		ledgerDO.setCreatedDate( AppDateUtil.currentDate(true) );
		ledgerDO.setUpdateUser(loginId);
		ledgerDO.setUpdateDate( AppDateUtil.currentDate(true) );

		customerDTO.setLedgerMasterDO( ledgerDO );

		FinancePartyPersonalDetailsDO personalDO=new FinancePartyPersonalDetailsDO();
		personalDO.setPartyType(20);
		personalDO.setFirstName(contactPerson);
		personalDO.setDob("01/01/1000");
		personalDO.setMaritalStatus("single");
		personalDO.setBloodGroup("none");
		personalDO.setCreatedUser( loginId );
		personalDO.setCreatedDate( AppDateUtil.currentDate(true) );
		personalDO.setUpdateUser(loginId);
		personalDO.setUpdateDate( AppDateUtil.currentDate(true) );

		customerDTO.setPersonalDO(personalDO);

		FinancePartyAddressDetailsDO addressDO=new FinancePartyAddressDetailsDO();
		addressDO.setLedgerId( ledgerId );
		addressDO.setRefType(  LedgerRefType.CUSTOMER.getType() );
		addressDO.setRefId( customerId );
		addressDO.setAddressTypeId( 15 );
		addressDO.setDooNo( doorNo );
		addressDO.setStreetName( streetName );
		addressDO.setRoadName( roadName );
		addressDO.setLandMark( landMark );
		addressDO.setCity( city );
		addressDO.setState( state );
		addressDO.setPincode( pincode );
		addressDO.setCreatedUser( loginId );
		addressDO.setCreatedDate( AppDateUtil.currentDate(true) );
		addressDO.setUpdateUser(loginId);
		addressDO.setUpdateDate( AppDateUtil.currentDate(true) );

		ArrayList<FinancePartyAddressDetailsDO> addressDetailList=new ArrayList<FinancePartyAddressDetailsDO>();
		addressDetailList.add(addressDO);
		customerDTO.setAddressDetailList(addressDetailList);

		FinancePartyContactDetailsDO contactDTO=new FinancePartyContactDetailsDO();
		contactDTO.setLedgerId( ledgerId );
		contactDTO.setRefType(  LedgerRefType.CUSTOMER.getType()  );
		contactDTO.setRefId( customerId );
		contactDTO.setContactTypeId(14);
		contactDTO.setStdCode1("");
		contactDTO.setPhone1("");
		contactDTO.setExtn1("");
		contactDTO.setStdCode2("");
		contactDTO.setPhone2("");
		contactDTO.setExtn2("");
		contactDTO.setCountryCode1("91");
		contactDTO.setMobile1(mobile);
		contactDTO.setCountryCode2("");
		contactDTO.setMobile2("");
		contactDTO.setEmail1(email);
		contactDTO.setEmail2("");
		contactDTO.setWebsite(webSite);
		contactDTO.setCreatedUser( loginId );
		contactDTO.setCreatedDate( AppDateUtil.currentDate(true) );
		contactDTO.setUpdateUser(loginId);
		contactDTO.setUpdateDate( AppDateUtil.currentDate(true) );

		ArrayList<FinancePartyContactDetailsDO> contactDTOs=new ArrayList<FinancePartyContactDetailsDO>();
		contactDTOs.add( contactDTO );
		customerDTO.setContactDetailList( contactDTOs );


		CommonDocumentStoreDO docDTO_gst=new CommonDocumentStoreDO();
		docDTO_gst.setDocumentTypeId( 11 );
		docDTO_gst.setNameInDocument("");
		docDTO_gst.setDocumentNo( gst );
		docDTO_gst.setDocumentIssueDate("01/10/1000");
		docDTO_gst.setValidUpto("01/01/1000");
		docDTO_gst.setRefType( LedgerRefType.CUSTOMER.getType() );
		docDTO_gst.setRefId( customerId );
		docDTO_gst.setCreatedUser( loginId );
		docDTO_gst.setCreatedDate( AppDateUtil.currentDate(true) );
		docDTO_gst.setUpdateUser(loginId);
		docDTO_gst.setUpdateDate( AppDateUtil.currentDate(true) );

		CommonDocumentStoreDO docDTO_pan=new CommonDocumentStoreDO();
		docDTO_pan.setDocumentTypeId( 10 );
		docDTO_pan.setNameInDocument("");
		docDTO_pan.setDocumentNo( pan );
		docDTO_pan.setDocumentIssueDate("01/01/1000");
		docDTO_pan.setValidUpto("01/01/1000");
		docDTO_pan.setRefType( LedgerRefType.CUSTOMER.getType() );
		docDTO_pan.setRefId( customerId );
		docDTO_pan.setCreatedUser( loginId );
		docDTO_pan.setCreatedDate( AppDateUtil.currentDate(true) );
		docDTO_pan.setUpdateUser(loginId);
		docDTO_pan.setUpdateDate( AppDateUtil.currentDate(true) );

		ArrayList<CommonDocumentStoreDO> docDTOs=new ArrayList<CommonDocumentStoreDO>();
		docDTOs.add(docDTO_gst);
		docDTOs.add(docDTO_pan);
		customerDTO.setDocList(docDTOs);

		return customerDTO;
	}

	public static void doCustomerEdit(HttpServletRequest request, HttpServletResponse response) {

		int customerId=AppUtil.getNullToInteger( request.getParameter("customerId") );

		SalesCustomerMasterDO customerDO =SalesCustomerMasterDAO.getSalesCustomerMasterByCustomerId(null, customerId, true);
		if(customerDO==null) { customerDO=new SalesCustomerMasterDO(); }

		request.setAttribute("customerDO", customerDO);
	}
	
	public static String customerOption(String selValues) {
		
		Map<String, String> customerMap =SalesCustomerMasterDAO.loadCustomerMap(null, "");
		if(customerMap==null) { customerMap=new HashMap<String, String>(); }
		return AppUtil.formOption(customerMap, selValues);

	}

	public static void doCustomerDelete(HttpServletRequest request, HttpServletResponse response) {

		int customerId=AppUtil.getNullToInteger( request.getParameter("customerId") );
		String loginid="Admin";
		SalesCustomerMasterDO customerDO =new SalesCustomerMasterDO();
		customerDO.setCustomerId(customerId);
		customerDO.setUpdateUser(loginid);
		customerDO.setBoolDeleteStatus(true);
		AjaxModel model=new AjaxModel();
		if(SalesCustomerMasterDAO.deleteupdate(null, customerDO)) {
			model.setMessage(" Deleted Successfully");
		}else {
			model.setMessage(" Unable to Delete");model.setErrorExists(true);
		}
		AjaxUtil.sendResponse(request, response, model);
		
	}
}
