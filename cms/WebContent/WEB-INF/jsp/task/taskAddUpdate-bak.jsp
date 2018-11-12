<%@page import="com.cms.task.handler.TaskType"%>
<%@page import="com.cms.task.handler.TaskCreationHandler"%>
<%@page import="com.cms.booking.dao.SalesCustomerBookingFormDAO"%>
<%@page import="com.cms.task.bean.TaskProcessMasterDO"%>
<%@page import="com.cms.customer.handler.CustomerCreationController"%>
<%@page import="com.cms.booking.handler.SalesCustomerBookingCreationHandler"%>
<%@page import="com.cms.booking.bean.SalesCustomerBookingFormDO"%>
<%@page import="com.application.util.PageUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.cms.holiday.handler.HolidayTypeCreationController"%>
<%@page import="com.cms.holiday.bean.AdminHolidayDetailsDO"%>
<%@page import="java.util.Random"%>
<%@page import="com.cms.holiday.bean.AdminHolidayTypeDO"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="com.cms.common.master.bean.CommonDocumentStoreDO"%>
<%@page import="com.cms.finance.bean.FinancePartyContactDetailsDO"%>
<%@page import="com.cms.finance.bean.FinancePartyAddressDetailsDO"%>
<%@page import="com.cms.finance.bean.FinancePartyPersonalDetailsDO"%>
<%@page import="com.cms.finance.bean.FinanceLedgerMasterDO"%>
<%@page import="com.cms.customer.bean.SalesCustomerMasterDO"%>
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
.table td, .table th {
	padding:5px;
}
</style>
<div class="modal-dialog modal-xl" role="document" style="margin-left: 20%;">
<div class="modal-content">
<form class="form" action="task?action=processSave" method="post" id="<%=formName%>">
	<input type="hidden" name="processMasterId" value="<%=taskProMstDO.getProcessMasterId()%>">
	<input type="hidden" name="processMasterStatus" value="<%=taskProMstDO.getProcessMasterStatus()%>">
	<input type="hidden" name="taskType" value="<%=taskType%>">
	<div class="modal-header">
		<h4 class="modal-title" id="myModalLabel16">Task Form</h4>
		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	<div class="modal-body" <%=taskType %>>
	<%=PageUtil.getAlert(request) %>
		                 
	<div class="form-body">
		<%
		if( taskType.equalsIgnoreCase(TaskType.Customer.getType()) ){
		%>
		
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label for="timesheetinput1">Customer Name</label>
					<div class="position-relative has-icon-left">
						<select id="customerName" class="form-control" placeholder="Customer Name" name="customerName" required="required">
							<option>-- please Select --</option>
							<%=CustomerCreationController.customerOption( ""+bookingDO.getCustomerId()) %>
						</select>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="timesheetinput1">Booking No</label>
					<div class="position-relative has-icon-left">
						<input type="text" id="saleId" class="form-control" placeholder="Sale ID" name="saleId" value="<%=AppUtil.getNullToEmpty(""+taskProMstDO.getSalesId() )%>" required="required">
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
						<thead class="bg-primary white">
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
							<%=TaskCreationHandler.generateChildTable(request, taskProMstDO) %>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
	</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn" data-dismiss="modal">Close</button>
		<%-- <button type="button" class="btn grey btn-secondary" onclick="<%=formName %>reset()">Reset</button> --%>
		<button type="submit" class="btn btn-success">Save</button>
	</div>
	</form>
</div>
</div>


<script type="text/javascript">

$(document).ready( function(){
	$('#<%=formName%>').submit(function(e){
		var frm=$(this);
		$.ajax({
		 	   url:$(frm).attr('action'),
		 	   data:$(frm).serialize(),
		 	   beforeSend:function(){
		 		  $('#CMS-POPUP-MODEL').html('<center> <img alt="" src="./resource/img/loader.gif"></center>');
		 	   },
		 	   success:function(data){
		 		   $('#CMS-POPUP-MODEL').html(data);
		 	   }
		    }); 
		e.preventDefault();
	});
	
	$('#<%=formName%>').on('click', '#process_addRow', function(){
		
		var sno=parseInt($('#<%=formName%> #rowCount').val()); if(isNaN(sno)){ sno=1;}
		sno++;
		$.getJSON('task?action=loadProcessRow&taskType=general&sno='+sno,function(response){
			
			if(response.data!=null && typeof(response)!='undefined'){
				$('#<%=formName%> #process_container').append(response.data);
				$('#<%=formName%> #rowCount').val(sno);
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
		var param='?action=loadCustomerTask&customerId='+customerId+'&salesId='+salesId;
		$('#<%=formName%> #process_container').html('');
		$.getJSON('task'+param, function(data){
			if(data.errorExists!=true){
				$('#<%=formName%> #process_container').html(data.data);
			}else{
				alert(data.message);
			}
		});
		
	});
	
	
	
});

function <%=formName %>reset(){
	$('#<%=formName %> #holidayType').val('');$('#<%=formName %> #holidayType').attr('value', '');
}
</script>
</html>