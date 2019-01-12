<!DOCTYPE html>
<%@page import="com.cms.employee.bean.UserMasterDO"%>
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
  UserMasterDO employeeDO=(UserMasterDO)request.getAttribute("employeeDO");
 if(employeeDO==null){ employeeDO=new  UserMasterDO(); }
 




 
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
 martialMap.put("Single", "Single");
 martialMap.put("Married", "Married");
 
 
 String formName="Emp_frm_"+Math.abs( new Random().nextInt(9999) );
 
 %>
 
 <style>
 
 .wizard > .actions {
    display: none;
    }
 </style>
 
 <%if(employeeDO.getEmpId()==0){ %>
 <div class="modal-dialog modal-lg" style="width: 600px;">
	<div class="modal-content" >
			<form class="tab-wizard wizard-circle"  action="employee?action=save_new" method="post" id="<%=formName%>">
					<input type="hidden" name="action" value="save_new">
					<input type="hidden" name="employeeId" value="<%=employeeDO.getEmpId()%>">
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
                                	<input type="text" id="firstName" class="form-control form-control-sm jval_name" placeholder="First Name" name="firstName" value="<%=employeeDO.getFirstName()%>" required="required" maxlength="50">
                            	</div>
                        	</div>
                  		</div>
                  		</div>
                  		<div class="row">
                  		    <div class="col-sm-12">
                  			<div class="form-group row">
                            	<label for="lastName" class="col-sm-3 p-t-5  control-label col-form-label">Last Name</label>
                            	<div class="col-sm-9">
                                	<input type="text" id="lastName" class="form-control form-control-sm jval_name" placeholder="Last Name" name="lastName" value="<%=employeeDO.getLastName()%>" maxlength="50">
                            	</div>
                        	</div>
                  		</div>
						
					</div>
					
					<div class="row">
						
                  		<div class="col-sm-12">
                  			<div class="form-group row">
                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Email<span style="color: #f62d51;">*</span></label>
                            	<div class="col-sm-9">
                                	<input type="text" id="email" class="form-control form-control-sm jval_email" placeholder="Email" name="email" value="<%=employeeDO.getEmail()%>" required="required" maxlength="80">
                            	</div>
                        	</div>
                  		</div>
                  		
                  		
					</div>
					
					<div class="row">
						
                  		<div class="col-sm-12">
                  			<div class="form-group row">
                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Role<span style="color: #f62d51;">*</span></label>
                            	<div class="col-sm-9">
                                	<%-- <input type="text" id="role" class="form-control form-control-sm jval_name" placeholder="Role" name="role" value="<%=employeeDO.getDesignationId()%>" required="required" maxlength="80"> --%>
                                	<select id="role" class="form-control custom-select select2" placeholder="Role" required="required" name="role" style="width: 100%; height:26px;">
										<option>-Please Select-</option>
										<%=EmployeeCreationHandler.formRoleOption( ""+employeeDO.getRole() )%>
									</select>
                            	</div>
                        	</div>
                  		</div>
                  		
                  		
					</div>
					
					<div class="row">
						
                  		<div class="col-sm-12">
                  			<div class="form-group row">
                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Profile<span style="color: #f62d51;">*</span></label>
                            	<div class="col-sm-9">
                                <%-- 	<input type="text" id="profile" class="form-control form-control-sm jval_name" placeholder="Profile" name="profile" value="<%=employeeDO.getDepartmentId()%>" required="required" maxlength="80"> --%>
                                <select id="profile" class="form-control custom-select select2" placeholder="Profile" required="required" name="profile" style="width: 100%; height:26px;">
										<option>-Please Select-</option>
										<%=EmployeeCreationHandler.formProfileOption( ""+employeeDO.getProfile() )%>
									</select>
                            	</div>
                        	</div>
                  		</div>
                  		
                  		
                  		<%-- <div class="col-sm-12">
                  			<div class="form-group row">
                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Department<span style="color: #f62d51;">*</span></label>
                            	<div class="col-sm-9">
                                	<input type="text" id="profile" class="form-control form-control-sm jval_name" placeholder="Profile" name="profile" value="<%=employeeDO.getDepartmentId()%>" required="required" maxlength="80">
                                <select id="department" class="form-control custom-select select2" placeholder="department" required="required" name="department" style="width: 100%; height:26px;">
										<option>-Please Select-</option>
										<%=EmployeeCreationHandler.formProfileOption( ""+employeeDO.getDepartment() )%>
									</select>
                            	</div>
                        	</div>
                  		</div> --%>
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
 <%}else{ %>
 
 
 <div class="container-fluid">
                <!-- Start Page Content -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                            	<h4 class="card-title">Employee Search
                            		
                            	</h4>
                                 <form class="form p-t-20" id="<%=formName %>" action="employee" method="post">
                                 <input type="hidden" name="action" value="save_new">
                                 <input type="hidden" name="empid" value="<%=employeeDO.getEmpId()%>">
                                 <input type="hidden" name="firstName" value="<%=employeeDO.getFirstName()%>">
                                 <input type="hidden" name="lastName" value="<%=employeeDO.getLastName()%>">
                                 <input type="hidden" name="email" value="<%=employeeDO.getEmail()%>">
                                 <input type="hidden" name="role" value="<%=employeeDO.getRole()%>">
                                 <input type="hidden" name="profile" value="<%=employeeDO.getProfile()%>">
                                 
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">First Name</label>
		                                        <div class="col-sm-8">
		                                            <%=employeeDO.getFirstName() %>
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Last Name</label>
		                                        <div class="col-sm-8">
		                                        	<%=employeeDO.getLastName() %>
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                    <div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Email</label>
		                                        <div class="col-sm-8">
		                                       <%=employeeDO.getEmail() %>
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Profile</label>
		                                                         <%=EmployeeCreationHandler.formCmnMasterDisplay( ""+employeeDO.getProfile() )%>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Role</label>
		                                        <%=EmployeeCreationHandler.formCmnMasterDisplay( ""+employeeDO.getRole() )%>
		                                    </div>
                                		</div>
                                		
                                	</div>
                                	
                                	
                                	
                                	<div class="row">
                                		
                                		<div class="col-sm-4">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Department</label>
		                                        <div class="col-sm-8">
		                                        	<select id="<%=formName %>_department" class="form-control form-control-sm select2" placeholder="Department" name="department">
														<option value="">-please Select-</option>
														<%=EmployeeCreationHandler.formDepartmentOption( ""+employeeDO.getDepartment() )%>
													</select>
		                                        </div>
		                                    </div>
                                		</div>
                                		
                                		        		
                                		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="gender" class="col-sm-4 p-t-5  control-label col-form-label">Reporting Manger</label>
                            	<div class="col-sm-8">
                                	<select id="reporting_manager" class="form-control custom-select select2" placeholder="reporting manager" name="reporting_manager" style="width: 100%; height:26px;">
									<%=EmployeeCreationHandler.formEmployeeOption( "",""+employeeDO.getEmpId() )%>
									</select>
                            	</div>
                        	</div>
                  		</div>
                                		
                                		<div class="col-sm-4">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Blood Group</label>
		                                        <div class="col-sm-8">
		                                        	<select id="<%=formName %>_blood_group" class="form-control form-control-sm select2" placeholder="Department" name="blood_group">
														<option value="">-please Select-</option>
														<%=EmployeeCreationHandler.formBloodgroupOption( ""+employeeDO.getBloodGroup() )%>
													</select>
		                                        </div>
		                                    </div>
                                		</div>
                                		
                        
                                	</div>
                                	
                                	
                                	
                                	
                                	
                                	<div class="row">
                                		
                                		<div class="col-sm-4">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">City</label>
		                                        <div class="col-sm-8">
		                                        	 <input type="text" id="city" class="form-control form-control-sm" placeholder="Pin Code" name="city" value="<%=employeeDO.getCity()%>" maxlength="50">
		                                        </div>
		                                    </div>
                                		</div>
                                		
                                		        		
                                		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="gender" class="col-sm-4 p-t-5  control-label col-form-label">State</label>
                            	<div class="col-sm-8">
                                	<select id="state" class="form-control custom-select select2" placeholder="state" name="state" style="width: 100%; height:26px;">
									<%=EmployeeCreationHandler.formStateOption( ""+employeeDO.getState() )%>
									</select>
                            	</div>
                        	</div>
                  		</div>
                                		
                                		<div class="col-sm-4">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Country</label>
		                                        <div class="col-sm-8">
		                                        	<select id="<%=formName %>_country" class="form-control form-control-sm select2" placeholder="Country" name="country">
														<option value="">-please Select-</option>
														<%=EmployeeCreationHandler.formBloodgroupOption( ""+employeeDO.getCountry() )%>
													</select>
		                                        </div>
		                                    </div>
                                		</div>
                                		
                        
                                	</div>
                                	
                                	
                                	
                                	<div class="row">
                                		
                                		<div class="col-sm-4">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Epf No</label>
		                                        <div class="col-sm-8">
		                                        	 <input type="text" id="epf_no" class="form-control form-control-sm" placeholder="Pin Code" name="epf_no" value="<%=employeeDO.getEpfNo()%>" maxlength="50">
		                                        </div>
		                                    </div>
                                		</div>
                                		
                                		        		
                                		<div class="col-sm-4">
                  			<div class="form-group row">
                            	<label for="gender" class="col-sm-4 p-t-5  control-label col-form-label">Gender</label>
                            	<div class="col-sm-8">
                                	<select id="gender" class="form-control custom-select select2" placeholder="Gender" name="gender" style="width: 100%; height:26px;">
										<option value="">-please Select-</option>
										<option value="Male" <%if(employeeDO.getGender().equalsIgnoreCase("male")){ %> selected='selected'<%} %>>Male</option>
											<option value="FeMale" <%if(employeeDO.getGender().equalsIgnoreCase("FeMale")){ %> selected='selected'<%} %>>Female</option>
												<option value="Trans" <%if(employeeDO.getGender().equalsIgnoreCase("Trans")){ %> selected='selected'<%} %>>Transgendet</option>
														
														
									</select>
                            	</div>
                        	</div>
                  		</div>
                                		
                                		<div class="col-sm-4">
                                			<div class="form-group row">
                            	<label for="martialStatus" class="col-sm-4 p-t-5  control-label col-form-label">Martial Status</label>
                            	<div class="col-sm-8">
                                	<select id="martialStatus" class="form-control custom-select select2" placeholder="Martial Status" name="martialStatus" required="required" style="width: 100%; height:26px;">
										<%=AppUtil.formOption( martialMap, ""+employeeDO.getMaritalStatus() ) %>
									</select>
                            	</div>
                        	</div>
                                		</div>
                                		
                        
                                	</div>
                                	
                                	
                                	<div class="row">
                                		<div class="col-sm-4">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Pin Code</label>
		                                        <div class="col-sm-8">
		                                            <input type="text" id="zipcode" class="form-control form-control-sm" placeholder="Pin Code" name="zipcode" value="<%=employeeDO.getZipcode()%>" maxlength="8">
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-4">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Mobile</label>
		                                        <div class="col-sm-8">
		                                           <input type="text" id="mobile" class="form-control form-control-sm" placeholder="Mobile" name="mobile" value="<%=employeeDO.getMobile()%>" maxlength="13">
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-4">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">pan_card</label>
		                                        <div class="col-sm-8">
		                                         <input type="text" id="pan_card" class="form-control form-control-sm" placeholder="pan_card" name="pan_card" value="<%=employeeDO.getPanCard()%>">
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	
                                	<div class="row">
                                		
                                		<div class="col-sm-4">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Street</label>
		                                        <div class="col-sm-8">
		                                            <input type="text" id="street" class="form-control form-control-sm" placeholder="street" name="street" value="<%=employeeDO.getState()%>" >
		                                        </div>
		                                    </div>
                                		</div>
                                		
                                		
                                		<div class="col-sm-4">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Bank Details</label>
		                                        <div class="col-sm-8">
		                                            <input type="text" id="bank_detials" class="form-control form-control-sm" placeholder="bank_detials" name="bank_detials" value="<%=employeeDO.getBankDetials()%>" >
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                     <button type="button" class="btn btn-dark m-t-10 float-right" onclick="<%=formName %>reset()">Reset</button>
                                    <button type="submit" class="btn btn-success m-r-10 m-t-10 float-right">Search</button>
                                </form>
                            </div>
                        </div>
                    </div>
   
   </div>
   </div>
 <%} %>
  
 
<script type="text/javascript">
$(document).ready(function(){
	initPage();
	<%if(employeeDO.getEmpId()==0){%>
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
	
	
	
	<%}%>
	
	
});

</script>

</html>