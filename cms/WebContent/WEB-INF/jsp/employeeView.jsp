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
 bloodgroupMap.put("none", "None");
 bloodgroupMap.put("a+", "A+");
 bloodgroupMap.put("a-", "A-");
 bloodgroupMap.put("b+", "B+");
 bloodgroupMap.put("b-", "B-");
 bloodgroupMap.put("o+", "O+");
 bloodgroupMap.put("o-", "O-");
 bloodgroupMap.put("ab+", "AB+");
 bloodgroupMap.put("ab-", "AB-");
 
 Map<String, String> genderMap=new HashMap<String, String>();
 genderMap.put("25", "Male");
 genderMap.put("26", "Female");
 genderMap.put("27", "Trans Gender");
 
 Map<String, String> martialMap=new HashMap<String, String>();
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
			<form class="tab-wizard wizard-circle"  action="#" method="post" id="<%=formName%>">
					<input type="hidden" name="action" value="save">
					<input type="hidden" name="employeeId" value="<%=employeeDO.getEmpId()%>">
					<input type="hidden" name="ledgerId" value="<%=employeeDO.getLedgerId()%>"> 
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel16">Employee Display</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<%=PageUtil.getAlert(request) %>
				
				
				
				<div class="form-body">
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput1">First Name</label>
								<div class="position-relative has-icon-left">
									<%=personalDO.getFirstName()%>
									
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Middle Name</label>
								<div class="position-relative has-icon-left">
									<%=personalDO.getMiddleName()%>
									
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Last Name</label>
								<div class="position-relative has-icon-left">
									<%=personalDO.getLastName()%>
									
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput1">Father's Name</label>
								<div class="position-relative has-icon-left">
									<%=personalDO.getFirstNameRel()%>
									
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Email</label>
								<div class="position-relative has-icon-left">
									<%=contactDO.getEmail1()%>
									
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Mobile</label>
								<div class="position-relative has-icon-left">
									<%=contactDO.getMobile1()%>
									
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Date of Birth</label>
								<div class="position-relative has-icon-left">
									<%=personalDO.getDob()%>
									<div class="form-control-position">
										<!-- <i class="la la-briefcase"></i> -->
									</div>
								</div>
							</div>
						</div>
						<% int gender=personalDO.getGender()==0?25:personalDO.getGender();  %>
						
						
							<div class="col-md-4">
								<div class="form-group">
									<label for="timesheetinput2">Gender</label>
									<div class="position-relative has-icon-left">
											<%=AppUtil.formDisplay( genderMap, ""+gender ) %>
									</div>
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="form-group">
									<label for="timesheetinput2">Martial Status</label>
									<div class="position-relative has-icon-left">
											<%=AppUtil.formDisplay( martialMap, ""+personalDO.getMaritalStatus() ) %>
									</div>
								</div>
							</div>
						
					</div>
					
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Blood Group</label>
								<div class="position-relative has-icon-left">
										<%=AppUtil.formDisplay(bloodgroupMap, personalDO.getBloodGroup()) %>
								</div>
							</div>
						</div>
					</div>
					
					 <hr><h4 class="card-title">Office Details</h4>
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Reporting To</label>
									<%=EmployeeCreationHandler.formEmployeeDisplay(""+employeeDO.getReportingTo(), "") %>
								
							</div>
						</div>
						
						<div class="col-md-4">
								<div class="form-group">
									<label for="timesheetinput2">Department</label>
									<div class="position-relative has-icon-left">
											<%=EmployeeCreationHandler.formCmnMasterDisplay( ""+employeeDO.getDepartmentId() )%>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="timesheetinput2">Designation</label>
									<div class="position-relative has-icon-left">
											<%=EmployeeCreationHandler.formCmnMasterDisplay( ""+employeeDO.getDesignationId())%>
									</div>
								</div>
							</div>
					</div>
					
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">ESI.No</label>
								<div class="position-relative has-icon-left">
									<%=employeeDO.getEsiNo()%>
								</div>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">EPF.No</label>
								<div class="position-relative has-icon-left">
									<%=employeeDO.getEpfNo()%>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="bankName">Bank Name</label>
								<div class="position-relative has-icon-left">
										<%Map<String, String> bankMap=FinancePartyBankDetailsDAO.loadBankMap(null); if(bankMap==null){ bankMap=new HashMap<String, String>();  } %>
										<%=AppUtil.formDisplay(bankMap, ""+branchDO.getBankId())%>
								</div>
							</div>
						</div>
						
					</div>
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label for="branchName">Branch Name</label>
								<div class="position-relative has-icon-left">
										<%
										Map<String, String> branchMap=FinancePartyBankBranchDetailsDAO.loadBranchMap(null, ""+branchDO.getBankId() ); if(bankMap==null){ bankMap=new HashMap<String, String>();  }
										%>
										<%=AppUtil.formDisplay(branchMap, ""+branchDO.getBranchId())%>
								</div>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">A/C.No</label>
								<div class="position-relative has-icon-left">
									<%=employeeDO.getBankAccountNo()%>
								</div>
							</div>
						</div>
						
					</div>
					
					
					 <hr><h4 class="card-title">Permanent Address</h4>
					
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Door No</label>
								<div class="position-relative has-icon-left">
									<%=permAddress.getDooNo()%>
								</div>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Street Name</label>
								<div class="position-relative has-icon-left">
									<%=permAddress.getStreetName()%>
								</div>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Road Name</label>
								<div class="position-relative has-icon-left">
									<%=permAddress.getRoadName()%>
								</div>
							</div>
						</div>
						
					</div>
					
					
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Land Mark</label>
								<div class="position-relative has-icon-left">
									<%=permAddress.getLandMark()%>
								</div>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">City</label>
								<div class="position-relative has-icon-left">
									<%=permAddress.getCity()%>
								</div>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">State</label>
								<div class="position-relative has-icon-left">
									<%=permAddress.getState()%>
								</div>
							</div>
						</div>
						
					</div>
					
					<div class="row">
					
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">PIN Code</label>
								<div class="position-relative has-icon-left">
									<%=permAddress.getPincode()%>
								</div>
							</div>
						</div>
						
					</div>
					
				 	<hr><h4 class="card-title">Communication Address</h4>
					
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Door No</label>
								<div class="position-relative has-icon-left">
									<%=commAddress.getDooNo()%>
								</div>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Street Name</label>
								<div class="position-relative has-icon-left">
									<%=commAddress.getStreetName()%>
								</div>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Road Name</label>
								<div class="position-relative has-icon-left">
									<%=commAddress.getRoadName()%>
								</div>
							</div>
						</div>
						
					</div>
					
					
					
					<div class="row">
						
						
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">Land Mark</label>
								<div class="position-relative has-icon-left">
									<%=commAddress.getLandMark()%>
								</div>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">City</label>
								<div class="position-relative has-icon-left">
									<%=commAddress.getCity()%>
								</div>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">State</label>
								<div class="position-relative has-icon-left">
									<%=commAddress.getState()%>
								</div>
							</div>
						</div>
						
					</div>
					
					<div class="row">
					
						<div class="col-md-4">
							<div class="form-group">
								<label for="timesheetinput2">PIN Code</label>
								<div class="position-relative has-icon-left">
									<%=commAddress.getPincode()%>
								</div>
							</div>
						</div>
						
					</div>
				</div>
				
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn" data-dismiss="modal">Cancel</button>
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
	

	

</script>

