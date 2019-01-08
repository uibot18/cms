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
 <div class="modal-dialog modal-lg" style="width: 600px;">
	<div class="modal-content" >
			<form class="tab-wizard wizard-circle"  action="employee?action=save" method="post" id="<%=formName%>">
					<input type="hidden" name="action" value="save">
					<input type="hidden" name="employeeId" value="<%=employeeDO.getEmpId()%>">
					<input type="hidden" name="ledgerId" value="<%=employeeDO.getLedgerId()%>"> 
			<div class="modal-header" style="">
				<h4 class="modal-title" id="myModalLabel16">Add New User</h4>
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
						<div class="col-sm-12">
                  			<div class="form-group row">
                            	<label for="firstName" class="col-sm-3 p-t-5  control-label col-form-label">First Name<span style="color: #f62d51;">*</span></label>
                            	<div class="col-sm-9">
                                	<input type="text" id="firstName" class="form-control form-control-sm jval_name" placeholder="First Name" name="firstName" value="<%=personalDO.getFirstName()%>" required="required" maxlength="50">
                            	</div>
                        	</div>
                  		</div>
                  		</div>
                  		<div class="row">
                  		    <div class="col-sm-12">
                  			<div class="form-group row">
                            	<label for="lastName" class="col-sm-3 p-t-5  control-label col-form-label">Last Name</label>
                            	<div class="col-sm-9">
                                	<input type="text" id="lastName" class="form-control form-control-sm jval_name" placeholder="Last Name" name="lastName" value="<%=personalDO.getLastName()%>" maxlength="50">
                            	</div>
                        	</div>
                  		</div>
						
					</div>
					
					<div class="row">
						
                  		<div class="col-sm-12">
                  			<div class="form-group row">
                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Email<span style="color: #f62d51;">*</span></label>
                            	<div class="col-sm-9">
                                	<input type="text" id="email" class="form-control form-control-sm jval_email" placeholder="Email" name="email" value="<%=contactDO.getEmail1()%>" required="required" maxlength="80">
                            	</div>
                        	</div>
                  		</div>
                  		
                  		
					</div>
					
					<div class="row">
						
                  		<div class="col-sm-12">
                  			<div class="form-group row">
                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Role<span style="color: #f62d51;">*</span></label>
                            	<div class="col-sm-9">
                                	<input type="text" id="role" class="form-control form-control-sm jval_name" placeholder="Role" name="role" value="<%=employeeDO.getDesignationId()%>" required="required" maxlength="80">
                            	</div>
                        	</div>
                  		</div>
                  		
                  		
					</div>
					
					<div class="row">
						
                  		<div class="col-sm-12">
                  			<div class="form-group row">
                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Profile<span style="color: #f62d51;">*</span></label>
                            	<div class="col-sm-9">
                                	<input type="text" id="profile" class="form-control form-control-sm jval_name" placeholder="Profile" name="profile" value="<%=employeeDO.getDepartmentId()%>" required="required" maxlength="80">
                            	</div>
                        	</div>
                  		</div>
                  		
                  		
					</div>
					
				
					
					
				</div></div></div></div></div>
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn" data-dismiss="modal">Cancel</button>
				<%-- <button type="button" class="btn grey btn-secondary" onclick="<%=formName %>reset()">Reset</button> --%>
				<button type="submit" class="btn btn-success">Save</button>
			</div>
			</form>
	</div>
</div>
 
  
 
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
	
	
	

	
	
});

</script>

</html>