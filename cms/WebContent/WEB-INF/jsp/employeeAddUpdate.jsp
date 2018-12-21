<!DOCTYPE html>
<%@page import="com.application.util.PageUtil"%>
<%@page import="java.util.Random"%>
<%@page import="com.cms.finance.bean.FinancePartyBankBranchDetailsDO"%>
<%@page import="com.cms.finance.dao.FinancePartyBankBranchDetailsDAO"%>
<%@page import="com.cms.finance.dao.FinancePartyBankDetailsDAO"%>
<%@page import="com.cms.finance.bean.FinancePartyBankDetailsDO"%>
<%@page import="com.cms.employee.handler.EmployeeCreationHandler"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="com.cms.finance.bean.FinancePartyContactDetailsDO"%>
<%@page import="com.cms.finance.bean.FinancePartyAddressDetailsDO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cms.finance.bean.FinanceLedgerMasterDO"%>
<%@page import="com.cms.finance.bean.FinancePartyPersonalDetailsDO"%>
<%@page import="com.cms.employee.bean.AdmEmployeeMasterDO"%>
<html class="loading" lang="en" data-textdirection="ltr">
 <%
  AdmEmployeeMasterDO employeeDO=(AdmEmployeeMasterDO)request.getAttribute("employeeDO");
 if(employeeDO==null){ employeeDO=new  AdmEmployeeMasterDO(); }
 
 FinanceLedgerMasterDO ledgerMstDO=employeeDO.getLedgerMstDO();
 if(ledgerMstDO==null){ ledgerMstDO=new FinanceLedgerMasterDO(); }
 
 FinancePartyPersonalDetailsDO personalDO= employeeDO.getPersonalDO();
 if(personalDO==null){ personalDO=new FinancePartyPersonalDetailsDO(); }
 
 FinancePartyContactDetailsDO contactDO=employeeDO.getContactDO();
 if(contactDO==null){ contactDO=new FinancePartyContactDetailsDO(); }
  
 ArrayList<FinancePartyAddressDetailsDO> addressList=employeeDO.getAddressList();
 if(addressList==null){ addressList=new ArrayList<FinancePartyAddressDetailsDO>(); }
 
 FinancePartyAddressDetailsDO permAddress=null;
 FinancePartyAddressDetailsDO commAddress=null;
 for(FinancePartyAddressDetailsDO addressDO : addressList){
	 if( addressDO.getAddressTypeId()==15 ){ permAddress= addressDO; }
	 if( addressDO.getAddressTypeId()==16 ){ commAddress= addressDO; }
 }
 
 if(permAddress==null){ permAddress=new FinancePartyAddressDetailsDO(); }
 if(commAddress==null){ commAddress=new FinancePartyAddressDetailsDO(); }
 
 employeeDO.getBankBranchId();
 
 FinancePartyBankBranchDetailsDO branchDO=FinancePartyBankBranchDetailsDAO.getFinancePartyBankBranchDetailsByBranchId(null, employeeDO.getBankBranchId(), false);
 if(branchDO==null){ branchDO=new FinancePartyBankBranchDetailsDO(); }

int bankId=branchDO.getBankId();
 
 Map<String, String> bloodgroupMap=new HashMap<String, String>();
 bloodgroupMap.put("none", "-Please Select-");
 bloodgroupMap.put("a+", "A+");
 bloodgroupMap.put("a-", "A-");
 bloodgroupMap.put("b+", "B+");
 bloodgroupMap.put("b-", "B-");
 bloodgroupMap.put("o+", "O+");
 bloodgroupMap.put("o-", "O-");
 bloodgroupMap.put("ab+", "AB+");
 bloodgroupMap.put("ab-", "AB-");
 
 Map<String, String> genderMap=new HashMap<String, String>();
 genderMap.put("0", "-Please Select-");
 genderMap.put("25", "Male");
 genderMap.put("26", "Female");
 genderMap.put("27", "Trans Gender");
 
 Map<String, String> martialMap=new HashMap<String, String>();
 martialMap.put("none", "-Please Select-");
 martialMap.put("single", "Single");
 martialMap.put("married", "Married");
 
 String formName="Emp_frm_"+Math.abs( new Random().nextInt(9999) );
 
 %>
 
 <style>
 
 .wizard > .actions {
    display: none;
    }
 </style>
 <div class="modal-dialog modal-lg">
	<div class="modal-content">
			<form class="tab-wizard wizard-circle"  action="employee?action=save" method="post" id="<%=formName%>">
					<input type="hidden" name="action" value="save">
					<input type="hidden" name="employeeId" value="<%=employeeDO.getEmpId()%>">
					<input type="hidden" name="ledgerId" value="<%=employeeDO.getLedgerId()%>"> 
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel16">Employee Creation</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<%=PageUtil.getAlert(request) %>
				
				<div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                            	<div class="form-body">
					<div class="row">
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="firstName" class="col-sm-4 p-t-5  control-label col-form-label">First Name<span style="color: #f62d51;">*</span></label>
                            	<div class="col-sm-8">
                                	<input type="text" id="firstName" class="form-control form-control-sm jval_name" placeholder="First Name" name="firstName" value="<%=personalDO.getFirstName()%>" required="required" maxlength="50">
                            	</div>
                        	</div>
                  		</div>
                  		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="middleName" class="col-sm-4 p-t-5  control-label col-form-label">Middle Name</label>
                            	<div class="col-sm-8">
                                	<input type="text" id="middleName" class="form-control form-control-sm jval_name" placeholder="Middle Name" name="middleName" value="<%=personalDO.getMiddleName()%>" maxlength="50">
                            	</div>
                        	</div>
                  		</div>
                  		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="lastName" class="col-sm-4 p-t-5  control-label col-form-label">Last Name</label>
                            	<div class="col-sm-8">
                                	<input type="text" id="lastName" class="form-control form-control-sm jval_name" placeholder="Last Name" name="lastName" value="<%=personalDO.getLastName()%>" maxlength="50">
                            	</div>
                        	</div>
                  		</div>
						
					</div>
					
					<div class="row">
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="fatherName" class="col-sm-4 p-t-5  control-label col-form-label">Father's Name</label>
                            	<div class="col-sm-8">
                                	<input type="hidden" id="relationTypeId" class="form-control" placeholder="First Name" name="relationTypeId" value="22" required="required" maxlength="50">
									<input type="text" id="fatherName" class="form-control form-control-sm jval_name" placeholder="Father's Name" name="fatherName" value="<%=personalDO.getFirstNameRel()%>">
                            	</div>
                        	</div>
                  		</div>
                  		
                  		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="email" class="col-sm-4 p-t-5  control-label col-form-label">Email<span style="color: #f62d51;">*</span></label>
                            	<div class="col-sm-8">
                                	<input type="text" id="email" class="form-control form-control-sm jval_email" placeholder="Email" name="email" value="<%=contactDO.getEmail1()%>" required="required" maxlength="80">
                            	</div>
                        	</div>
                  		</div>
                  		
                  		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="mobile" class="col-sm-4 p-t-5  control-label col-form-label">Mobile</label>
                            	<div class="col-sm-8">
                                	<input type="text" id="mobile" class="form-control form-control-sm jval_num" placeholder="Mobile" name="mobile" value="<%=contactDO.getMobile1()%>" maxlength="13">
                            	</div>
                        	</div>
                  		</div>
					</div>
					
					<div class="row">
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="dob" class="col-sm-4 p-t-5  control-label col-form-label">Date of Birth</label>
                            	<div class="col-sm-8">
                                	<input type="text" id="dob" class="form-control form-control-sm date_picker jval_dob" placeholder="Date of Birth" name="dob" value="<%=personalDO.getDob()%>">
                            	</div>
                        	</div>
                  		</div>
						<% int gender=personalDO.getGender();  %>
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="gender" class="col-sm-4 p-t-5  control-label col-form-label">Gender</label>
                            	<div class="col-sm-8">
                                	<select id="gender" class="form-control custom-select select2" placeholder="Gender" name="gender" required="required" style="width: 100%; height:26px;">
										<%=AppUtil.formOption( genderMap, ""+gender ) %>
									</select>
                            	</div>
                        	</div>
                  		</div>
                  		
                  		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="martialStatus" class="col-sm-4 p-t-5  control-label col-form-label">Martial Status</label>
                            	<div class="col-sm-8">
                                	<select id="martialStatus" class="form-control custom-select select2" placeholder="Martial Status" name="martialStatus" required="required" style="width: 100%; height:26px;">
										<%=AppUtil.formOption( martialMap, ""+personalDO.getMaritalStatus() ) %>
									</select>
                            	</div>
                        	</div>
                  		</div>
							
					</div>
					
					<div class="row">
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="martialStatus" class="col-sm-4 p-t-5  control-label col-form-label">Blood Group</label>
                            	<div class="col-sm-8">
                                	<select id="bloodGroup" class="form-control custom-select select2" placeholder="Blood Group" name="bloodGroup" style="width: 100%; height:26px;">
										<%=AppUtil.formOption(bloodgroupMap, personalDO.getBloodGroup()) %>
									</select>
                            	</div>
                        	</div>
                  		</div>
					
					</div>
					
					 <hr><h4 class="card-title">Office Details</h4>
					<div class="row">
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="<%=formName %>_reportingTo" class="col-sm-4 p-t-5  control-label col-form-label">Reporting To</label>
                            	<div class="col-sm-8">
                                	<select id="<%=formName %>_reportingTo" class="form-control custom-select select2" placeholder="Reporting To" name="reportingTo" style="width: 100%; height:26px;">
										<option>-Please Select-</option>
										<%=EmployeeCreationHandler.formEmployeeOption(""+employeeDO.getReportingTo(), ""+employeeDO.getEmpId()) %>
									</select>
                            	</div>
                        	</div>
                  		</div>
                  		
                  		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="<%=formName %>_reportingTo" class="col-sm-4 p-t-5  control-label col-form-label">Department</label>
                            	<div class="col-sm-8">
                                	<select id="<%=formName %>_department" class="form-control custom-select select2" placeholder="Department" name="department" style="width: 100%; height:26px;">
										<option>-Please Select-</option>
										<%=EmployeeCreationHandler.formDepartmentOption( ""+employeeDO.getDepartmentId() )%>
									</select>
                            	</div>
                        	</div>
                  		</div>
                  		
                  		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="<%=formName %>_designation" class="col-sm-4 p-t-5  control-label col-form-label">Designation</label>
                            	<div class="col-sm-8">
                                	<select id="<%=formName %>_designation" class="form-control custom-select select2" placeholder="Designation" name="designation" style="width: 100%; height:26px;">
										<option>-Please Select-</option>
										<%=EmployeeCreationHandler.formDesignationOption(""+employeeDO.getDepartmentId(), ""+employeeDO.getDesignationId())%>
									</select>
                            	</div>
                        	</div>
                  		</div>
					
					</div>
					
					<div class="row">
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="esiNo" class="col-sm-4 p-t-5  control-label col-form-label">ESI.No</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="esiNo" class="form-control form-control-sm jval_name_num" placeholder="ESI.No" name="esiNo" value="<%=employeeDO.getEsiNo()%>" maxlength="20">
                            	</div>
                        	</div>
                  		</div>
                  		
                  		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="epfNo" class="col-sm-4 p-t-5  control-label col-form-label">EPF.No</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="epfNo" class="form-control form-control-sm jval_name_num" placeholder="EPF.No" name="epfNo" value="<%=employeeDO.getEpfNo()%>" maxlength="20">
                            	</div>
                        	</div>
                  		</div>
						
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="<%=formName %>_bankName" class="col-sm-4 p-t-5  control-label col-form-label">Bank Name</label>
                            	<div class="col-sm-8">
                                	<select id="<%=formName %>_bankName" class="form-control custom-select select2" placeholder="Bank Name" name="bankName" style="width: 100%; height:26px;">
										<%Map<String, String> bankMap=FinancePartyBankDetailsDAO.loadBankMap(null); if(bankMap==null){ bankMap=new HashMap<String, String>();  } %>
										<option>-Please Select-</option>
										<%=AppUtil.formOption(bankMap, ""+branchDO.getBankId())%>
									</select>
                            	</div>
                        	</div>
                  		</div>
					</div>
					
					<div class="row">
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="<%=formName %>_branchName" class="col-sm-4 p-t-5  control-label col-form-label">Branch Name</label>
                            	<div class="col-sm-8">
                                	<select id="<%=formName %>_branchName" class="form-control custom-select select2" placeholder="Branch Name" name="branchName" style="width: 100%; height:26px;">
										<option>-Please Select-</option>
										<% Map<String, String> branchMap=FinancePartyBankBranchDetailsDAO.loadBranchMap(null, ""+branchDO.getBankId() ); %>
										<%=AppUtil.formOption(branchMap, ""+branchDO.getBranchId())%>
									</select>
                            	</div>
                        	</div>
                  		</div>
						
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="bankAccountNo" class="col-sm-4 p-t-5  control-label col-form-label">A/C.No</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="bankAccountNo" class="form-control form-control-sm jval_num" placeholder="A/C.No" name="bankAccountNo" value="<%=employeeDO.getBankAccountNo()%>" maxlength="20">
                            	</div>
                        	</div>
                  		</div>
						
					</div>
					
					 <hr><h4 class="card-title">Permanent Address</h4>
					
					<div class="row">
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="per_doorNo" class="col-sm-4 p-t-5  control-label col-form-label">Door No</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="per_doorNo" class="form-control form-control-sm jval_addr" placeholder="Door No" name="per_doorNo" value="<%=permAddress.getDooNo()%>" maxlength="15">
                            	</div>
                        	</div>
                  		</div>
                  		
                  		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="per_streetName" class="col-sm-4 p-t-5  control-label col-form-label">Street Name</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="per_streetName" class="form-control form-control-sm jval_name_num" placeholder="Street Name" name="per_streetName" value="<%=permAddress.getStreetName()%>" maxlength="80">
                            	</div>
                        	</div>
                  		</div>
                  		
                  		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="per_roadName" class="col-sm-4 p-t-5  control-label col-form-label">Road Name</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="per_roadName" class="form-control form-control-sm jval_name_num" placeholder="Road Name" name="per_roadName" value="<%=permAddress.getRoadName()%>" maxlength="80">
                            	</div>
                        	</div>
                  		</div>
						
					</div>
					
					
					<div class="row">
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="per_landMark" class="col-sm-4 p-t-5  control-label col-form-label">Land Mark</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="per_landMark" class="form-control form-control-sm jval_addr" placeholder="Land Mark" name="per_landMark" value="<%=permAddress.getLandMark()%>" maxlength="100">
                            	</div>
                        	</div>
                  		</div>
                  		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="per_city" class="col-sm-4 p-t-5  control-label col-form-label">City</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="per_city" class="form-control form-control-sm jval_name" placeholder="City" name="per_city" value="<%=permAddress.getCity()%>" maxlength="50">
                            	</div>
                        	</div>
                  		</div>
                  		
                  		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="per_state" class="col-sm-4 p-t-5  control-label col-form-label">State</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="per_state" class="form-control form-control-sm jval_name" placeholder="State" name="per_state" value="<%=permAddress.getState()%>" maxlength="50">
                            	</div>
                        	</div>
                  		</div>
						
					</div>
					
					<div class="row">
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="per_pinCode" class="col-sm-4 p-t-5  control-label col-form-label">PIN Code</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="per_pinCode" class="form-control form-control-sm jval_num" placeholder="PIN Code" name="per_pinCode" value="<%=permAddress.getPincode()%>" maxlength="10">
                            	</div>
                        	</div>
                  		</div>
					</div>
					
				 	<hr><h4 class="card-title">Communication Address</h4>
					
					<div class="row">
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="cmm_doorNo" class="col-sm-4 p-t-5  control-label col-form-label">Door No</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="cmm_doorNo" class="form-control form-control-sm jval_addr" placeholder="Door No" name="cmm_doorNo" value="<%=permAddress.getDooNo()%>" maxlength="15">
                            	</div>
                        	</div>
                  		</div>
                  		
                  		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="cmm_streetName" class="col-sm-4 p-t-5  control-label col-form-label">Street Name</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="cmm_streetName" class="form-control form-control-sm jval_name_num" placeholder="Street Name" name="cmm_streetName" value="<%=permAddress.getStreetName()%>" maxlength="100">
                            	</div>
                        	</div>
                  		</div>
                  		
                  		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="cmm_roadName" class="col-sm-4 p-t-5  control-label col-form-label">Road Name</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="cmm_roadName" class="form-control form-control-sm jval_name_num" placeholder="Road Name" name="cmm_roadName" value="<%=permAddress.getRoadName()%>" maxlength="100">
                            	</div>
                        	</div>
                  		</div>
						
					</div>
					
					
					
					<div class="row">
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="cmm_landMark" class="col-sm-4 p-t-5  control-label col-form-label">Land Mark</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="cmm_landMark" class="form-control form-control-sm jval_addr" placeholder="Land Mark" name="cmm_landMark" value="<%=permAddress.getLandMark()%>" maxlength="100">
                            	</div>
                        	</div>
                  		</div>
						
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="cmm_city" class="col-sm-4 p-t-5  control-label col-form-label">City</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="cmm_city" class="form-control form-control-sm jval_name" placeholder="City" name="cmm_city" value="<%=permAddress.getCity()%>" maxlength="80">
                            	</div>
                        	</div>
                  		</div>
                  		
                  		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="cmm_city" class="col-sm-4 p-t-5  control-label col-form-label">State</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="cmm_state" class="form-control form-control-sm jval_name" placeholder="State" name="cmm_state" value="<%=permAddress.getState()%>" maxlength="80">
                            	</div>
                        	</div>
                  		</div>
						
					</div>
					
					<div class="row">
						<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="cmm_pinCode" class="col-sm-4 p-t-5  control-label col-form-label">PIN Code</label>
                            	<div class="col-sm-8">
                            		<input type="text" id="cmm_pinCode" class="form-control form-control-sm jval_num" placeholder="PIN Code" name="cmm_pinCode" value="<%=permAddress.getPincode()%>" maxlength="10">
                            	</div>
                        	</div>
                  		</div>
					</div>
				</div>
                            </div>
                        </div>
                    </div>
	            </div>
				
				
				<%-- <form class="tab-wizard wizard-circle"  action="employee?action=save" method="post" id="<%=formName%>">
					<input type="hidden" name="action" value="save">
					<input type="hidden" name="employeeId" value="<%=employeeDO.getEmpId()%>">
					<input type="hidden" name="ledgerId" value="<%=employeeDO.getLedgerId()%>"> 
						<h6>Personal Info</h6>
                                    <section>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="firstName1">First Name :</label>
                                                    <input type="text" class="form-control" id="firstName1"> </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="lastName1">Last Name :</label>
                                                    <input type="text" class="form-control" id="lastName1"> </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="emailAddress1">Email Address :</label>
                                                    <input type="email" class="form-control" id="emailAddress1"> </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="phoneNumber1">Phone Number :</label>
                                                    <input type="tel" class="form-control" id="phoneNumber1"> </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="location1">Select City :</label>
                                                    <select class="custom-select form-control" id="location1" name="location">
                                                        <option value="">Select City</option>
                                                        <option value="Amsterdam">India</option>
                                                        <option value="Berlin">USA</option>
                                                        <option value="Frankfurt">Dubai</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="date1">Date of Birth :</label>
                                                    <input type="date" class="form-control" id="date1"> </div>
                                            </div>
                                        </div>
                                    </section>
                                    <!-- Step 2 -->
                                    <h6>Office Details</h6>
                                    <section>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="jobTitle1">Job Title :</label>
                                                    <input type="text" class="form-control" id="jobTitle1"> </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="videoUrl1">Company Name :</label>
                                                    <input type="text" class="form-control" id="videoUrl1">
                                                </div>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label for="shortDescription1">Job Description :</label>
                                                    <textarea name="shortDescription" id="shortDescription1" rows="6" class="form-control"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                    <!-- Step 3 -->
                                    <h6>Permanent Address</h6>
                                    <section>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="int1">Interview For :</label>
                                                    <input type="text" class="form-control" id="int1"> </div>
                                                <div class="form-group">
                                                    <label for="intType1">Interview Type :</label>
                                                    <select class="custom-select form-control" id="intType1" data-placeholder="Type to search cities" name="intType1">
                                                        <option value="Banquet">Normal</option>
                                                        <option value="Fund Raiser">Difficult</option>
                                                        <option value="Dinner Party">Hard</option>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label for="Location1">Location :</label>
                                                    <select class="custom-select form-control" id="Location1" name="location">
                                                        <option value="">Select City</option>
                                                        <option value="India">India</option>
                                                        <option value="USA">USA</option>
                                                        <option value="Dubai">Dubai</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="jobTitle2">Interview Date :</label>
                                                    <input type="date" class="form-control" id="jobTitle2">
                                                </div>
                                                <div class="form-group">
                                                    <label>Requirements :</label>
                                                    <div class="c-inputs-stacked">
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" id="customRadio6" name="customRadio" class="custom-control-input">
                                                            <label class="custom-control-label" for="customRadio6">Employee</label>
                                                        </div>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" id="customRadio7" name="customRadio" class="custom-control-input">
                                                            <label class="custom-control-label" for="customRadio7">Contract</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                    <!-- Step 4 -->
                                    <h6>Communication Address</h6>
                                    <section>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="behName1">Behaviour :</label>
                                                    <input type="text" class="form-control" id="behName1">
                                                </div>
                                                <div class="form-group">
                                                    <label for="participants1">Confidance</label>
                                                    <input type="text" class="form-control" id="participants1">
                                                </div>
                                                <div class="form-group">
                                                    <label for="participants1">Result</label>
                                                    <select class="custom-select form-control" id="participants1" name="location">
                                                        <option value="">Select Result</option>
                                                        <option value="Selected">Selected</option>
                                                        <option value="Rejected">Rejected</option>
                                                        <option value="Call Second-time">Call Second-time</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="decisions1">Comments</label>
                                                    <textarea name="decisions" id="decisions1" rows="4" class="form-control"></textarea>
                                                </div>
                                                <div class="form-group">
                                                    <label>Rate Interviwer :</label>
                                                    <div class="c-inputs-stacked">
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" id="customRadio1" name="customRadio" class="custom-control-input">
                                                            <label class="custom-control-label" for="customRadio1">1 star</label>
                                                        </div>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" id="customRadio2" name="customRadio" class="custom-control-input">
                                                            <label class="custom-control-label" for="customRadio2">2 star</label>
                                                        </div>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" id="customRadio3" name="customRadio" class="custom-control-input">
                                                            <label class="custom-control-label" for="customRadio3">3 star</label>
                                                        </div>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" id="customRadio4" name="customRadio" class="custom-control-input">
                                                            <label class="custom-control-label" for="customRadio4">4 star</label>
                                                        </div>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" id="customRadio5" name="customRadio" class="custom-control-input">
                                                            <label class="custom-control-label" for="customRadio5">5 star</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
								</form> --%>
				
				
				
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn" data-dismiss="modal">Cancel</button>
				<%-- <button type="button" class="btn grey btn-secondary" onclick="<%=formName %>reset()">Reset</button> --%>
				<button type="submit" class="btn btn-success">Save</button>
			</div>
			</form>
	</div>
</div>
 
 <%
String qualificationForm="qual_frm_"+Math.abs( new Random().nextInt(9999));
if(employeeDO.getEmpId()!=0 && false){ %>
<section class="row">
    <div class="col-md-12 col-sm-12">
        <div id="with-header" class="card">
            <div class="card-header">
                <h4 class="card-title">Qualification</h4>
                <a class="heading-elements-toggle"><i class="la la-ellipsis-v font-medium-3"></i></a>
                <div class="heading-elements">
                    <ul class="list-inline mb-0">
                        <li><a data-action="collapse" id="<%=qualificationForm%>_expand"><i class="ft-plus"></i></a></li>
                       <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                       <li><a data-action="close"><i class="ft-x"></i></a></li>
                   </ul>
               </div>
           </div>
           <div class="card-content collapse">
               <div class="card-body border-top-blue-grey border-top-lighten-5 ">
               	<form class="form" action="employee" method="post" id="<%=qualificationForm%>">
               		<input type="hidden" name="action" value="qualification_save">
               		<input type="hidden" name="employeeId" value="">
               		<input type="hidden" name="employeeQualificationId" value="">
                	<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Course</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="course" class="form-control" placeholder="Course" name="course" value="">
									<div class="form-control-position">
										<!-- <i class="la la-briefcase"></i> -->
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Major</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="major" class="form-control" placeholder="PIN Code" name="major" value="">
									<div class="form-control-position">
										<!-- <i class="la la-briefcase"></i> -->
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Grade</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="grade" class="form-control" placeholder="Grade" name="grade" value="">
									<div class="form-control-position">
										<!-- <i class="la la-briefcase"></i> -->
									</div>
								</div>
							</div>
						</div>
						
					</div>
                	<div class="row">
                		<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Year of Completion</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="yearOfCompletion" class="form-control" placeholder="Year of Completion" name="yearOfCompletion" value="">
									<div class="form-control-position">
										<!-- <i class="la la-briefcase"></i> -->
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">School/ College/ University</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="instuition" class="form-control" placeholder="Grade" name="instuition" value="">
									<div class="form-control-position">
										<!-- <i class="la la-briefcase"></i> -->
										</div>
									</div>
								</div>
							</div>
							
						</div>
	                	<div class="form-actions right">
							<button type="submit" class="btn btn-primary"> <i class="fa fa-check-square-o"></i> Save</button>
							<button type="button" class="btn btn-danger mr-1"><i class="ft-x"></i> Reset</button>
						</div>
					</form>
                	<div class="table-responsive">
						<table class="table">
							<thead class="bg-primary white">
								<tr>
									<th>#</th>
									<th>Course</th>
									<th>Major</th>
									<th>Grade/Per</th>
									<th>Passed Out</th>
									<th>Institution</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
							
								<tr>
									<th scope="row">1</th>
									<td>aaaa</td>
									<td>bbb</td>
									<td>ccc</td>
									<td>ddd</td>
									<td>ffff</td>
									<td>
										<a id="qualification_edit">Edit</a> &nbsp;&nbsp;
										<a id="qualification_delete">Delete</a></td>
								</tr>
								
							</tbody>
						</table>
					</div> 
                </div>
            </div>
        </div>
    </div>
</section>

<%} %>
 
 
<script type="text/javascript">
$(document).ready(function(){
	initPage();
	try{		
		$('#<%=formName%>').validate({
			errorClass: 'invalid',
			validClass: 'valid',
			errorPlacement: function(error, element) {
				error.insertAfter(element);
			},
			rules: {
				
			},
			messages: {
				lettersonly:'Plese enter letters only',
				firstName:{ required:'First Name is required'},
				email:{ required:"Email is required" }
			},
			submitHandler: function(form) {
				$.ajax({
					url:$(form).attr('action'),
					data:$(form).serialize(),
					beforeSend:function(){
						$('#CMS-POPUP-MODEL').html('<center> <img alt="" src="./resource/img/loader.gif"></center>');
					},
					success:function(data){
				 		$('#CMS-POPUP-MODEL').html(data);
					}
				});
			}
		});
		
	}catch(e){
		alert('Something went wrong. Please Try Later..!');
	}
	
	$('#<%=formName %>_department').change(function(){
		loadDepartment( $(this).val(), $('#<%=formName %>_designation') );
	});
	
	/* $(".tab-wizard").steps({
	    headerTag: "h6",
	    bodyTag: "section",
	    transitionEffect: "fade",
	    titleTemplate: '<span class="step">#index#</span> #title#',
	    labels: {
	        finish: "Submit"
	    },
	    onFinished: function(event, currentIndex) {
	        swal("Form Submitted!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed lorem erat eleifend ex semper, lobortis purus sed.");

	    }
	}); */
	
	$('#<%=formName%>').on('change', '#<%=formName %>_bankName', function(){
		loadBankBranch($(this).val(), $('#<%=formName %>_branchName'))
	});
	
	$('#<%=qualificationForm%>_expand').click( function(){
		
	});
	
	
});

</script>

</html>