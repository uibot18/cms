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

<div class="modal-dialog modal-xl" role="document" style="margin-left: 20%;">
<div class="modal-content">
<form class="form" action="holiday?action=save" method="post" id="<%=formName%>">
	<input type="hidden" name="holidayId" value="<%=holidayDO.getHolidayId()%>">
	<div class="modal-header">
		<h4 class="modal-title" id="myModalLabel16">Holiday Form</h4>
		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	<div class="modal-body">
		<!-- <div class="alert alert-success alert-dismissible mb-2" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>Well done!</strong> You successfully read this
			<a href="#" class="alert-link">important</a> alert message.
		</div> -->
		                 
	<div class="form-body">
		
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label for="timesheetinput1">Holiday Name</label>
					<div class="position-relative has-icon-left">
						<input type="text" id="holidayType" class="form-control" placeholder="Holiday Name" name="holidayName" value="<%=AppUtil.getNullToEmpty(holidayDO.getHolidayName() )%>" required="required">
						<div class="form-control-position">
							<i class="fas fa-unlock-alt"></i>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="timesheetinput1">Holiday Config</label>
					<div class="position-relative has-icon-left">
						<select id="holidaySubType" class="form-control" placeholder="Holiday Config" name="holidaySubType">
							<%=AppUtil.formOption(holidaySubTypeMap, holidayDO.getHolidaySubType() ) %>
						</select>
						<div class="form-control-position">
							<i class="fas fa-unlock-alt"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label for="timesheetinput1">Holiday Date</label>
					<div class="position-relative has-icon-left">
						<input type="text" id="holidayType" class="form-control" placeholder="Holiday Date" name="holidayDate" value="<%=AppUtil.getNullToEmpty(holidayDO.getHoliday() )%>" required="required">
						<div class="form-control-position">
							<i class="fas fa-unlock-alt"></i>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="timesheetinput1">Holiday Type</label>
					<div class="position-relative has-icon-left">
						<select id="holidayType" class="form-control" placeholder="Holiday Type" name="holidayType">
									<option></option>
									<%=HolidayTypeCreationController.formHolidayTypeOption(""+holidayDO.getHolidayTypeId())%>
							</select>
						<div class="form-control-position">
							<i class="fas fa-unlock-alt"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn grey btn-secondary" data-dismiss="modal">Close</button>
		<button type="button" class="btn grey btn-secondary" onclick="<%=formName %>reset()">Reset</button>
		<button type="submit" class="btn btn-danger">Save</button>
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
		 	   async:false,
		 	   success:function(data){
		 		   $('#CMS-POPUP-MODEL').html(data);
		 	   }
		    }); 
		e.preventDefault();
	});
	
	
});

function <%=formName %>reset(){
	$('#<%=formName %> #holidayType').val('');$('#<%=formName %> #holidayType').attr('value', '');
}
</script>
</html>