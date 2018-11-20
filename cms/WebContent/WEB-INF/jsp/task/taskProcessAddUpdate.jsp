<%@page import="com.cms.task.handler.TaskProcessCreationHandler"%>
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

SalesCustomerBookingFormDO bookingDO = SalesCustomerBookingFormDAO.getSalesCustomerBookingFormBySaleId(null, taskProMstDO.getSalesId(), false);
if(bookingDO==null){ bookingDO=new SalesCustomerBookingFormDO(); }

String formName="task_frm_"+Math.abs( new Random().nextInt(9999) );
String taskType=AppUtil.getNullToEmpty(taskProMstDO.getTaskType());
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
    	<form id="<%=formName%>" action="taskProcess?action=processSave" method="post">
        <div class="modal-header">
            <h4 class="modal-title">Service Creation</h4>
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        </div>
        <div class="modal-body">
        <%=PageUtil.getAlert(request) %>
			<input type="hidden" name="processMasterId" value="<%=taskProMstDO.getProcessMasterId()%>">
			<input type="hidden" name="processMasterStatus" value="<%=taskProMstDO.getProcessMasterStatus()%>">
			<input type="hidden" name="taskType" value="<%=taskType%>">
			<%if( taskType.equalsIgnoreCase(TaskType.Customer.getType()) ){ %>
			<div class="row">
				<div class="col-sm-4">
       				<div class="form-group row">
                 		<label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Customer Name <span style="color: #f62d51;">*</span></label>
                 		<div class="col-sm-8">
                     		<select class="select2 form-control"  name="customerName" id="customerName"  style="width: 100%; height:26px;">
                				<option></option>
                				<%=CustomerCreationController.customerOption( ""+bookingDO.getCustomerId()) %>
           					</select>
                 		</div>
             		</div>
           		</div>
           		
           		<div class="col-sm-4">
       				<div class="form-group row">
                 		<label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Booking No<span style="color: #f62d51;">*</span></label>
                 		<div class="col-sm-8">
                     		<input type="text" name="saleId" class="form-control" id="saleId" value="<%=AppUtil.getNullToEmpty(""+taskProMstDO.getSalesId() )%>" placeholder="Booking NO">
                 		</div>
             		</div>
           		</div>
           		
			</div>
			<div class="modal-footer">
				<button type="button" class="btn grey btn-secondary" onclick="<%=formName %>reset()">Reset</button>
				<button type="button" class="btn btn-success" id="<%=formName%>_search">Search</button>
			</div>
			<%} %>
			<div class="row">
			<div class="col-md-12">
				<div class="table-responsive">
				<%
					int childSize=taskProMstDO.getTaskProcessChildList().size();
					//if(childSize==0){ childSize=1; }
				%>
					<input type="hidden" id="rowCount" value="<%=childSize%>">
					<table class="table">
						<thead class="bg-primary" style="color: white;">
							<tr>
								<th align="center"><div style="width: 30px;">
								<%if(taskType.equalsIgnoreCase(TaskType.GeneralTask.getType())){ %>
								<button type="button" id="process_addRow">+</button>
								<%}else{ %> S.No
								<%} %>
								</div></th>
								<th>Service Name</th>
								<th>Package Name</th>
								<th>Process Name</th>
								<th>W.E.F</th>
								<th>Ends On</th>
								<th>Override</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody id="process_container">
							<%=TaskProcessCreationHandler.generateChildTable(request, formName, taskProMstDO) %>
						</tbody>
					</table>
				</div>
			</div>
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
	
	
$('#<%=formName%>').on('click', '#process_addRow', function(){
		
		var sno=parseInt($('#<%=formName%> #rowCount').val()); if(isNaN(sno)){ sno=1;}
		sno++;
		var param='action=loadProcessRow&taskType=general&formName=<%=formName%>&sno='+sno;
		$.getJSON('taskProcess?'+param,function(response){
			
			if(response.data!=null && typeof(response)!='undefined'){
				$('#<%=formName%> #process_container').append(response.data);
				$('#<%=formName%> #rowCount').val(sno);
				init();
			}
		});
	});
	
	$('#<%=formName%>').on('click', '.del_row', function(){
		var id=$(this).attr('id');
		var sno=id.replace('del_row_', '');
		$('#<%=formName%> #row_'+sno).remove();
	});
	
	$('#<%=formName%>').on('click', '#<%=formName%>_search', function(){
		console.log('click event');
		var salesId= $('#<%=formName%> #saleId').val();
		var customerId=$('#<%=formName%> #customerName').val();
		var param='?action=loadCustomerTask&customerId='+customerId+'&salesId='+salesId+'&formName=<%=formName%>';
		$('#<%=formName%> #process_container').html('');
		$.getJSON('taskProcess'+param, function(data){
			if(data.errorExists!=true){
				$('#<%=formName%> #process_container').html(data.data);
				init();
			}else{
				alert(data.message);
			}
		});
		
	});
	
	
	
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