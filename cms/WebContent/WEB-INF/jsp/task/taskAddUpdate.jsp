<%@page import="com.cms.employee.handler.EmployeeCreationHandler"%>
<%@page import="com.cms.task.bean.TaskMasterDO"%>
<%@page import="com.application.util.PageUtil"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
TaskMasterDO taskDO = (TaskMasterDO)request.getAttribute("taskDO");
if( taskDO==null ){ taskDO=new  TaskMasterDO(); } 

String formName="Tsk_frm_"+Math.abs( new Random().nextInt(9999) );
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
    	<form id="<%=formName%>" action="task?action=save" method="post">
        <div class="modal-header">
            <h4 class="modal-title">Service Creation</h4>
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        </div>
        <div class="modal-body">
        <%=PageUtil.getAlert(request) %>
			<input type="hidden" name="action" value="save">
			<input type="hidden" name="taskId" value="<%=taskDO.getTaskId()%>">
			<input type="hidden" name="taskConfigId" value="<%=taskDO.getTaskConfigId()%>">
			<input type="hidden" name="taskId" value="<%=taskDO.getTaskId()%>">
			
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
				    	<label for="serviceName" class="control-label">Task Code : </label>
					    <span><%=taskDO.getTaskId() %></span>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
					    <label for="serviceName" class="control-label">Task Status : </label>
					    <span><%=taskDO.getTaskStatus() %></span>
					</div>
				</div>
			</div>
			
			<div class="form-group">
			    <label for="taskDate" class="control-label">Task Date<span style="color: #f62d51;">*</span></label>
			    <input type="text" name="taskDate" class="form-control date_picker" id="taskDate" value="<%=taskDO.getTaskDate()%>" placeholder="Task Date" autocomplete="false">
			</div>
			
			<div class="form-group">
			    <label for="" class="control-label">Task Period<span style="color: #f62d51;">*</span></label>
			    <div class="row">
			    	<div class="col-md-6">
			    		<input type="text" name="taskDateFrom" class="form-control date_picker" id="taskDateFrom" value="<%=taskDO.getTaskDateFrom()%>" placeholder="From">
			    	</div>
			    	<div class="col-md-6">
			    		<input type="text" name="taskDateTo" class="form-control date_picker" id="taskDateTo" value="<%=taskDO.getTaskDateTo()%>" placeholder="To" >
			    	</div>
			    	
			    </div>
			</div>
			<div class="form-group">
				<label for="" class="control-label">Assigned To<span style="color: #f62d51;">*</span></label>
				<select class="select2 form-control" name="taskAssignedTo" id="taskAssignedTo" style="width: 100%; height:26px;">
                	<option></option>
                	<%=EmployeeCreationHandler.formEmployeeOption(""+taskDO.getAssignedTo(), "") %>
            	</select>
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
	
	$('.select2').select2();
	$('.date_picker').datepicker({
		autoclose:true,
		todayBtn:'linked',
		todayHighlight:true,
		format:'dd/mm/yyyy'
	}); 
	try{		
		$('#<%=formName%>').validate({
			errorClass: 'invalid',
			validClass: 'valid',
			errorPlacement: function(error, element) {
				error.insertAfter(element);
			},
			rules: {
				taskDateFrom: { required: true },
				taskDateTo: { required: true },
				taskAssignedTo: { required: true }
			},
			messages: {
				taskDateFrom: { required: 'Task From Date is required' },
				taskDateTo: { required: 'Task From To is required' },
				taskAssignedTo: { required: 'Assigned To is required' }
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