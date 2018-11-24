
<%@page import="com.application.util.PageUtil"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="com.cms.cms_package.handler.PackageCreationController"%>
<%@page import="java.util.Random"%>
<%@page import="com.cms.common.master.bean.CommonMasterDO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
CommonMasterDO packageDO=(CommonMasterDO)request.getAttribute("processDO");
if( packageDO==null ){ packageDO=new  CommonMasterDO(); } 

String formName="Proc_frm_"+Math.abs( new Random().nextInt(9999) );
%>

<style>
.form-control.invalid{
	border-color: #f62d51 !important;
}
label.invalid{
	color: #f62d51 !important;
}
.form-control.valid{
	border-color: #36bea6 !important;
}

</style>



<div class="modal-dialog">
    <div class="modal-content">
		<form class="form" action="process?action=save" method="post" id="<%=formName%>">
			<input type="hidden" name="action" value="save">
            <input type="hidden" name="processId" value="<%=packageDO.getCmnMasterId()%>">
            <input type="hidden" name="groupId" value="<%=packageDO.getCmnGroupId()%>">
            <input type="hidden" name="levelNo" value="<%=packageDO.getLevelNo()%>">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel16">Process Creation</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<hr>
			<div class="modal-body">
				<%=PageUtil.getAlert(request) %>
				<div class="form-body">
				
								<label for="timesheetinput1">Package Name<span style="color: #f62d51;">*</span></label>
									<select id="packageName" class="form-control" placeholder="Package Name" name="packageName" required="required">
	                            		<option></option>
										<%=PackageCreationController.packageOption("", ""+packageDO.getParentId()) %>
									</select>
						</div>
							<div class="form-group">
								<label for="timesheetinput1">Process Name<span style="color: #f62d51;">*</span></label>
									<input type="text" id="processName" class="form-control" placeholder="Process Name" name="processName" value="<%=AppUtil.getNullToEmpty(packageDO.getCmnMasterName() )%>" required="required">
									<!-- <div class="form-control-position">
										<i class="fas fa-unlock-alt"></i>
									</div> -->
								</div>
								</div>
			<div class="modal-footer">
				 <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
            	<button type="submit" class="btn btn-danger waves-effect waves-light">Save</button>
			</div>
		</form>
	</div>
</div>





<script type="text/javascript">

$(document).ready( function(){
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

function <%=formName %>reset(){
	$('#<%=formName %> #serviceName').val('');$('#<%=formName %> #serviceName').attr('value', '');
	$('#<%=formName %> #packageName').val('');$('#<%=formName %> #packageName').attr('value', '');
}
</script>
</html>