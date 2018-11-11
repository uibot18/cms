<%@page import="com.application.util.PageUtil"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="com.cms.common.master.CmnGroupName"%>
<%@page import="com.cms.common.master.bean.CommonMasterDO"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cms.user.login.bean.LoginMasterBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
CommonMasterDO serviceDO=(CommonMasterDO)request.getAttribute("serviceDO");
if( serviceDO==null ){ serviceDO=new  CommonMasterDO(); } 

String formName="Hldy_frm_"+Math.abs( new Random().nextInt(9999) );
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
    	<form id="<%=formName%>" action="service?action=save" method="post">
        <div class="modal-header">
            <h4 class="modal-title">Service Creation</h4>
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        </div>
        <div class="modal-body">
        <%=PageUtil.getAlert(request) %>
			<input type="hidden" name="action" value="save">
			<input type="hidden" name="serviceId" value="<%=serviceDO.getCmnMasterId()%>">
			<input type="hidden" name="groupId" value="<%=serviceDO.getCmnGroupId()%>">
			<input type="hidden" name="parentId" value="<%=serviceDO.getParentId()%>">
			<input type="hidden" name="levelNo" value="<%=serviceDO.getLevelNo()%>">
			
			<div class="form-group">
			    <label for="serviceName" class="control-label">Service Name<span style="color: #f62d51;">*</span></label>
			    <input type="text" name="serviceName" class="form-control" id="serviceName" value="<%=serviceDO.getCmnMasterName()%>" placeholder="Service Name">
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
				serviceName: { required: true }
			},
			messages: {
				serviceName: { required: 'Service Name is required' }
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
}
</script>
</html>