
<%@page import="com.application.util.PageUtil"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="com.cms.service.handler.ServiceCreationController"%>
<%@page import="java.util.Random"%>
<%@page import="com.cms.common.master.bean.CommonMasterDO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
CommonMasterDO packageDO=(CommonMasterDO)request.getAttribute("packageDO");
if( packageDO==null ){ packageDO=new  CommonMasterDO(); } 

String formName="pkg_frm_"+Math.abs( new Random().nextInt(9999) );

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
		<form class="form" action="package?action=save" method="post" id="<%=formName%>">
			<input type="hidden" name="action" value="save">
			<input type="hidden" name="packageId" value="<%=packageDO.getCmnMasterId()%>">
			<input type="hidden" name="groupId" value="<%=packageDO.getCmnGroupId()%>">
			<input type="hidden" name="parentId" value="<%=packageDO.getParentId()%>">
			<input type="hidden" name="levelNo" value="<%=packageDO.getLevelNo()%>">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel16">Package Creation</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<hr>
			<div class="modal-body">
				<%=PageUtil.getAlert(request) %>
				<div class="form-body">
				
								<label for="timesheetinput1">Service Name<span style="color: #f62d51;">*</span></label>
									<select id="timesheetinput2" class="form-control" placeholder="Service Name" name="serviceName" required="required">
	                            		<option></option>
										<%=ServiceCreationController.serviceOption("", ""+packageDO.getParentId()) %>
									</select>
						</div>
							<div class="form-group">
								<label for="timesheetinput1">Package Name<span style="color: #f62d51;">*</span></label>
									<input type="text" id="packageName" class="form-control" placeholder="Package Name" name="packageName" value="<%=AppUtil.getNullToEmpty(packageDO.getCmnMasterName() )%>" required="required">
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
				serviceName: { required: true },
				packageName: { required: true }
			},
			messages: {
				serviceName: { required: 'Service Name is required' },
				packageName: { required: 'Package Name is required' }
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