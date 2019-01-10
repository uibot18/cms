<!DOCTYPE html>
<%@page import="com.cms.profiles.bean.ProfileMasterDO"%>
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
ProfileMasterDO profileMasterDO = (ProfileMasterDO)request.getAttribute("profileMasterDO");
profileMasterDO=profileMasterDO==null?new ProfileMasterDO():profileMasterDO;
 
 String formName="Emp_frm_"+Math.abs( new Random().nextInt(9999) );
 
 %>
 
 <style>
 
 .wizard > .actions {
    display: none;
    }
 </style>
 <div class="modal-dialog modal-lg" style="width: 600px;">
	<div class="modal-content" >
			<form class="tab-wizard wizard-circle"  action="profiles?action=save" method="post" id="<%=formName%>">
					<input type="hidden" name="action" value="save">
						<input type="hidden" name="profileId" value="<%=profileMasterDO.getProfileId()%>">
					
			<div class="modal-header" style="">
				<h4 class="modal-title" id="myModalLabel16">Create New Profile</h4>
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
                            	<label for="firstName" class="col-sm-5 p-t-5  control-label col-form-label">Profile Name<span style="color: #f62d51;">*</span></label>
                            	<div class="col-sm-7">
                                	<input type="text" id="profileName" class="form-control form-control-sm jval_name" placeholder="Profile Name" name="profileName" value="<%=profileMasterDO.getProfileName()%>" required="required" maxlength="50">
                            	</div>
                        	</div>
                  		</div>
                  		</div>
                  		<div class="row">
                  		    <div class="col-sm-12">
                  			<div class="form-group row">
                            	<label for="lastName" class="col-sm-5 p-t-5  control-label col-form-label">Clone Profile</label>
                            	<div class="col-sm-7">
                                	<input type="text" id="cloneProfile" class="form-control form-control-sm jval_name" placeholder="Clone Profile" name="cloneProfile" value="" maxlength="50">
                            	</div>
                        	</div>
                  		</div>
						
					</div>
                  		<div class="row">
                  		    <div class="col-sm-12">
                  			<div class="form-group row">
                            	<label for="lastName" class="col-sm-5 p-t-5  control-label col-form-label">Profile Description</label>
                            	<div class="col-sm-7">
                                	<input type="text" id="profile_desc" class="form-control form-control-sm jval_name" placeholder="Profile Description" name="profile_desc" value="" maxlength="200">
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