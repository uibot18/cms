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
SalesCustomerBookingFormDO bookingDO=(SalesCustomerBookingFormDO)request.getAttribute("bookingDO");
if( bookingDO==null ){ bookingDO=new  SalesCustomerBookingFormDO(); } 

Map<String, String> holidaySubTypeMap=new HashMap<String, String>();
holidaySubTypeMap.put("generic", "Generic");
holidaySubTypeMap.put("specific", "Specific");

String formName="cust_bk_frm_"+Math.abs( new Random().nextInt(9999) );

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
.table td, .table th {
	padding:5px;
}
</style>




<div class="modal-dialog">
    <div class="modal-content">
    	<form id="<%=formName%>" action="customerBooking?action=save" class='form p-t-20' method="post">
    		<input type="hidden" name="saleId" value="<%=bookingDO.getSaleId()%>">
        <div class="modal-header">
            <h4 class="modal-title">Booking Form</h4>
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        </div>
        <div class="modal-body">
        <%=PageUtil.getAlert(request) %>
			
			
					
					
					
					<div class="row">
                      		<div class="col-sm-6">
                      			<div class="form-group row">
                                <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Customer Name<span style="color: #f62d51;">*</span></label>
                                <div class="col-sm-8">
                                     <select id="customerName" class="form-control select" placeholder="Customer Name" name="customerName" required="required">
							<option></option>
							<%=CustomerCreationController.customerOption( ""+bookingDO.getCustomerId()) %>
						</select>
									
                                </div>
                            </div>
                      		</div>
                      		
                      		
                      		<div class="col-sm-6">
                      			<div class="form-group row">
                                <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Booking Date<span style="color: #f62d51;">*</span></label>
                                <div class="col-sm-8">
                                 <input type="text" id="bookingDate" class="form-control date_picker" placeholder="Booking Date" name="bookingDate" value="<%=bookingDO.getSaleDate()%>" required="required">
                                </div>
                            </div>
                      		</div>
                      	</div>
					
					<div class="row">
			<div class="col-md-12">
				<div class="table-responsive">
				<%
					int childSize=bookingDO.getCustomerPackageList().size();
					if(childSize==0){ childSize=1; }
				%>
					<input type="hidden" id="rowCount" value="<%=childSize%>">
					<table class="table">
						<thead class="">
							<tr>
								<th align="center"><div style="width: 30px;"><button type="button" id="pack_addRow">+</button></div></th>
								<th>Service Name</th>
								<th>Package Name</th>
								<th>W.E.F</th>
								<th>Ends On</th>
								<th>Overide</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody id="pack_container"><%=SalesCustomerBookingCreationHandler.generatePackageTable(request, bookingDO) %></tbody>
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
	try{		
		$('#<%=formName%>').validate({
			errorClass: 'invalid',
			validClass: 'valid',
			errorPlacement: function(error, element) {
				error.insertAfter(element);
			},
			rules: {
				bookingDate: { required: true },
				customerName: { required: true }
			},
			messages: {
				bookingDate: { required: 'Booking Date is required' },
				customerName: { required: 'Customer Name is required' }
			
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
	
	$('#<%=formName%>').on('click', '#pack_addRow', function(){
		
		var sno=parseInt($('#<%=formName%> #rowCount').val()); if(isNaN(sno)){ sno=1;}
		sno++;
		$.getJSON('customerBooking?action=loadPackRow&sno='+sno,function(response){
			
			if(response.data!=null && typeof(response)!='undefined'){
				$('#<%=formName%> #pack_container').append(response.data);
				$('#<%=formName%> #rowCount').val(sno);
			}
		});
	});
	
	$('#<%=formName%>').on('click', '.del_row', function(){
		var id=$(this).attr('id');
		var sno=id.replace('del_row_', '');
		$('#<%=formName%> #row_'+sno).remove();
	});
	


function <%=formName %>reset(){
	$('#<%=formName %> #holidayType').val('');$('#<%=formName %> #holidayType').attr('value', '');
}
</script>
</html>