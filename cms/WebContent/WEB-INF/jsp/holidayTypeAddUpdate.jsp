<%@page import="com.application.util.PageUtil"%>
<%@page import="java.util.Random"%>
<%@page import="com.cms.holiday.bean.AdminHolidayTypeDO"%>
<%@page import="com.application.util.AppUtil"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cms.user.login.bean.LoginMasterBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
AdminHolidayTypeDO holidayTypeDO=(AdminHolidayTypeDO)request.getAttribute("holidayTypeDO");
if( holidayTypeDO==null ){ holidayTypeDO=new  AdminHolidayTypeDO(); } 

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
    	<form class="form" action="holidayType?action=save" method="post" id="<%=formName%>">
        <div class="modal-header">
            <h4 class="modal-title">Holiday Type Creation</h4>
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        </div>
        <div class="modal-body">
        <%=PageUtil.getAlert(request) %>
			<input type="hidden" name="holidayTypeId" value="<%=holidayTypeDO.getHolidayTypeId()%>">
			
			
			<div class="form-group">
			    <label for="serviceName" class="control-label">Holiday Type<span style="color: #f62d51;">*</span></label>
			    <input type="text" id="holidayType" class="form-control" placeholder="Holiday Type" name="holidayType" value="<%=AppUtil.getNullToEmpty(holidayTypeDO.getHolidayType() )%>" required="required">
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
				holidayType: { required: true }
			},
			messages: {
				holidayType: { required: 'Holiday Type is required' }
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
	$('#<%=formName %> #holidayType').val('');$('#<%=formName %> #holidayType').attr('value', '');
}
</script>
