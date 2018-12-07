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
AdminHolidayDetailsDO holidayDO=(AdminHolidayDetailsDO)request.getAttribute("holidayDO");
if( holidayDO==null ){ holidayDO=new  AdminHolidayDetailsDO(); } 

Map<String, String> holidaySubTypeMap=new HashMap<String, String>();
holidaySubTypeMap.put("generic", "Generic");
holidaySubTypeMap.put("specific", "Specific");

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
		<form class="form" action="holiday?action=save" method="post" id="<%=formName%>">
			<input type="hidden" name="holidayId" value="<%=holidayDO.getHolidayId()%>">
			
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel16">Holiday Form</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<%=PageUtil.getAlert(request) %>
				<div class="form-body">
				
								<label for="timesheetinput1">Holiday Name<span style="color: #f62d51;">*</span></label>
									<input type="text" id="holidayType" class="form-control" placeholder="Holiday Name" name="holidayName" value="<%=AppUtil.getNullToEmpty(holidayDO.getHolidayName() )%>" required="required">
						</div>
							<div class="form-group">
								<label for="timesheetinput1">Holiday Config</label>
									<select id="holidaySubType" class="form-control" placeholder="Holiday Config" name="holidaySubType">
							<%=AppUtil.formOption(holidaySubTypeMap, holidayDO.getHolidaySubType() ) %>
						</select>
								</div>
							<div class="form-group">
								<label for="timesheetinput1">Holiday Date<span style="color: #f62d51;">*</span></label>
									<input type="text" id="holidayDate" class="form-control date_picker input-sm" placeholder="Holiday Date" name="holidayDate" value="<%=AppUtil.getNullToEmpty(holidayDO.getHoliday() )%>" required="required">
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

				holidayDate: { required: true }
			},
			messages: {
				holidayDate: { required: 'Holiday Date is required' }

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