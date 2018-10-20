<%@page import="java.util.Random"%>
<%@page import="com.application.util.PageUtil"%>
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
SalesCustomerMasterDO customerDO=(SalesCustomerMasterDO)request.getAttribute("customerDO");
if(customerDO==null){ customerDO=new SalesCustomerMasterDO(); }

FinanceLedgerMasterDO ledgerMstDO=customerDO.getLedgerMasterDO();
if(ledgerMstDO==null){ ledgerMstDO=new FinanceLedgerMasterDO(); }

FinancePartyPersonalDetailsDO personalDO=customerDO.getPersonalDO();
if(personalDO==null){ personalDO=new FinancePartyPersonalDetailsDO(); }

FinancePartyAddressDetailsDO addressDO=null;
ArrayList<FinancePartyAddressDetailsDO> addressList=customerDO.getAddressDetailList();
if(addressList!=null && addressList.size()>0){  addressDO=addressList.get(0); }
if(addressDO==null){ addressDO=new FinancePartyAddressDetailsDO(); }

FinancePartyContactDetailsDO contactDO=null;
ArrayList<FinancePartyContactDetailsDO> contactList=customerDO.getContactDetailList();
if(contactList!=null && contactList.size()>0){ contactDO=contactList.get(0); }
if(contactDO==null){ contactDO=new FinancePartyContactDetailsDO(); }

ArrayList<CommonDocumentStoreDO> docList=customerDO.getDocList();
if(docList==null){ docList=new ArrayList<CommonDocumentStoreDO>(); }
String panNo="";
String gstinNo="";
for(CommonDocumentStoreDO docDO:docList){
	if(docDO.getDocumentTypeId()==10){ panNo=docDO.getDocumentNo(); }
	if(docDO.getDocumentTypeId()==11){ gstinNo=docDO.getDocumentNo(); }
}
String formName="cust_frm_"+Math.abs( new Random().nextInt(9999) );
%>

<div class="modal-dialog modal-xl" role="document" style="margin-left: 33%;width: 700px;">
	<div class="modal-content">
		<form class="form" action="customer?action=save" method="post" id="<%=formName%>">
			<input type="hidden" name="action" value="save">
			<input type="hidden" name="customerId" value="<%=customerDO.getCustomerId()%>">
			<input type="hidden" name="ledgerId" value="<%=customerDO.getLedgerId()%>">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel16">Customer Creation</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<%=PageUtil.getAlert(request) %>
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="timesheetinput1">Customer Name</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="timesheetinput1" class="form-control" placeholder="Customer Name" name="customerName" value="<%=AppUtil.getNullToEmpty(ledgerMstDO.getLedgerName() )%>" required="required"">
									<div class="form-control-position">
										<i class="ft-user"></i>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="timesheetinput2">Contact Person</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="timesheetinput2" class="form-control" placeholder="Contact Person" name="contactPerson" value="<%=AppUtil.getNullToEmpty(personalDO.getFirstName() )%>" required="required">
									<div class="form-control-position">
										<i class="la la-briefcase"></i>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="timesheetinput1">Door No</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="timesheetinput1" class="form-control" placeholder="Door No" name="doorNo" value="<%=AppUtil.getNullToEmpty(addressDO.getDooNo() )%>">
									<div class="form-control-position">
										<i class="fas fa-unlock-alt"></i>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="timesheetinput2">Email</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="timesheetinput2" class="form-control" placeholder="Email" name="email" value="<%=AppUtil.getNullToEmpty(contactDO.getEmail1() )%>" required="required">
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
								<label for="timesheetinput1">Street Name</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="timesheetinput1" class="form-control" placeholder="Street Name" name="streetName" value="<%=AppUtil.getNullToEmpty(addressDO.getStreetName() )%>">
									<div class="form-control-position">
										<i class="fas fa-unlock-alt"></i>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="timesheetinput2">Mobile Number</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="timesheetinput2" class="form-control" placeholder="Mobile Number" name="mobileNumber" value="<%=AppUtil.getNullToEmpty(contactDO.getMobile1() )%>" required="required">
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
								<label for="timesheetinput1">Road Name</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="timesheetinput1" class="form-control" placeholder="Rood Name" name="roadName" value="<%=AppUtil.getNullToEmpty(addressDO.getRoadName() )%>">
									<div class="form-control-position">
										<i class="fas fa-unlock-alt"></i>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="timesheetinput2">Web Site</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="timesheetinput2" class="form-control" placeholder="Website" name="webSite" value="<%=AppUtil.getNullToEmpty(contactDO.getWebsite() )%>">
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
								<label for="timesheetinput1">Land Mark</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="timesheetinput1" class="form-control" placeholder="Land Mark" name="landMark" value="<%=AppUtil.getNullToEmpty(addressDO.getLandMark() )%>">
									<div class="form-control-position">
										<i class="fas fa-unlock-alt"></i>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="timesheetinput2">PAN</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="timesheetinput2" class="form-control" placeholder="PAN" name="pan" value="<%=AppUtil.getNullToEmpty(panNo)%>" required="required">
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
								<label for="timesheetinput1">City</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="timesheetinput1" class="form-control" placeholder="City" name="city" value="<%=AppUtil.getNullToEmpty(addressDO.getCity() )%>">
									<div class="form-control-position">
										<i class="fas fa-unlock-alt"></i>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="timesheetinput2">GST</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="timesheetinput2" class="form-control" placeholder="GST" name="gst" value="<%=AppUtil.getNullToEmpty( gstinNo )%>" readonly="readonly">
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
								<label for="timesheetinput1">State</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="timesheetinput1" class="form-control" placeholder="State" name="state" value="<%=AppUtil.getNullToEmpty(addressDO.getState() )%>">
									<div class="form-control-position">
										<i class="fas fa-unlock-alt"></i>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="timesheetinput2">Pin Code</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="timesheetinput2" class="form-control" placeholder="Pin Code" name="pinCode" value="<%=AppUtil.getNullToEmpty(addressDO.getPincode() )%>">
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
				<button type="button" class="btn" data-dismiss="modal">Cancel</button>
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
});


function <%=formName %>reset(){
	$('#<%=formName %> #holidayType').val('');$('#<%=formName %> #holidayType').attr('value', '');
}

</script>


