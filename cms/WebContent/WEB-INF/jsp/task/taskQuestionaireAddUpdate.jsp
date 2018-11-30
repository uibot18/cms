<%@page import="com.cms.task.bean.TaskMasterQuestionaireDO"%>
<%@page import="com.cms.questionnaire.bean.TaskQuestionaireDetailsDO"%>
<%@page import="java.util.List"%>
<%@page import="com.cms.task.handler.TaskCreationHandler"%>
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
List<TaskQuestionaireDetailsDO> configQuestionnaireList = (List<TaskQuestionaireDetailsDO>)request.getAttribute("configQuestionnaireList");
if(configQuestionnaireList == null){ configQuestionnaireList=new ArrayList<TaskQuestionaireDetailsDO>(); }

List<TaskMasterQuestionaireDO> taskMstQuestionnaireList = (List<TaskMasterQuestionaireDO>)request.getAttribute("taskMstQuestionnaireList");
if(taskMstQuestionnaireList == null){ taskMstQuestionnaireList=new ArrayList<TaskMasterQuestionaireDO>(); }

int taskId=AppUtil.getNullToInteger((String)request.getAttribute("taskId") );

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
    	<form id="<%=formName%>" action="task?action=questionnaireSave" method="post">
        <div class="modal-header">
            <h4 class="modal-title">Questionnaire</h4>
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        </div>
        <div class="modal-body">
        <%=PageUtil.getAlert(request) %>
        	<input type="hidden" name="taskId" value="<%=taskId%>">
			<input type="hidden" name="rowCount" value="<%=configQuestionnaireList.size() %>">
			<%=TaskCreationHandler.constructQuetionaireData(request, configQuestionnaireList, taskMstQuestionnaireList) %>
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