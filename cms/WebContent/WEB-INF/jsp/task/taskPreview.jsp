<%@page import="com.cms.task.handler.TaskCreationHandler"%>
<%@page import="com.cms.task.handler.TaskType"%>
<%@page import="com.cms.customer.handler.CustomerCreationController"%>
<%@page import="com.cms.booking.dao.SalesCustomerBookingFormDAO"%>
<%@page import="com.cms.booking.bean.SalesCustomerBookingFormDO"%>
<%@page import="com.cms.task.bean.TaskProcessMasterDO"%>
<%@page import="com.application.util.PageUtil"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cms.user.login.bean.LoginMasterBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
TaskProcessMasterDO taskProMstDO=(TaskProcessMasterDO)request.getAttribute("taskProMstDO");
if( taskProMstDO==null ){ taskProMstDO=new  TaskProcessMasterDO(); } 

String formName="task_frm_"+Math.abs( new Random().nextInt(9999) );
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
<div class="modal-dialog modal-lg">
    <div class="modal-content">
    	<form id="<%=formName%>" action="task?action=processSave" method="post">
        <div class="modal-header">
            <h4 class="modal-title">Service Creation</h4>
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        </div>
        <div class="modal-body">
        <%=PageUtil.getAlert(request) %>
			<div class="row">
			<div class="col-md-12">
				<div class="table-responsive">
					<table class="table">
						<thead class="bg-primary" style="color: white;">
							<tr>
								<th align="center"><div style="width: 30px;">S.No</div></th>
								<th>Task Start Date</th>
								<th>Task End Date</th>
								<th>Task Name</th>
								<th>Assigned To</th>
							</tr>
						</thead>
						<tbody id="process_container">
							<%=TaskCreationHandler.generateTaskPreviewTableDisp(request, taskProMstDO) %>
						</tbody>
					</table>
				</div>
			</div>
		</div>
			
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
            <!-- <button type="submit" class="btn btn-danger waves-effect waves-light">Save</button> -->
        </div>
        </form>
    </div>
</div>

<script type="text/javascript">

$(document).ready( function(){
	init();
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

function init(){
	$('.select2').select2();
	$('.date_picker').datepicker({
		autoclose:true,
		todayBtn:'linked',
		todayHighlight:true,
		format:'dd/mm/yyyy'
	}); 
}
</script>
</html>