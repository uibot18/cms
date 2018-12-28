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
System.out.print("true");
%>


       <div class="modal-dialog modal-lg">
           <div class="modal-content">
           <form class="form" action="customer?action=save" method="post" id="<%=formName%>">
           		<input type="hidden" name="action" value="save">
			<input type="hidden" name="customerId" value="<%=customerDO.getCustomerId()%>">
			<input type="hidden" name="ledgerId" value="<%=customerDO.getLedgerId()%>">
               <div class="modal-header">
                   <h4 class="modal-title" id="myLargeModalLabel">Customer Creation</h4>
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
               </div>
               <div class="modal-body">
               		<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="customerName" class="col-sm-3 p-t-5  control-label col-form-label">Customer Name<span style="color: #f62d51;">*</span></label>
		                                        <div class="col-sm-8">
		                                           <input type="text" id="customerName" class="form-control" placeholder="Customer Name" name="customerName" value="<%=AppUtil.getNullToEmpty(ledgerMstDO.getLedgerName() )%>" required="required"">
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="contactPerson" class="col-sm-3 p-t-5  control-label col-form-label">Contact Person Name<span style="color: #f62d51;">*</span></label>
		                                        <div class="col-sm-8">
		                                           <input type="text" id="contactPerson" class="form-control" placeholder="Contact Person" name="contactPerson" value="<%=AppUtil.getNullToEmpty(personalDO.getFirstName() )%>" required="required">
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Door No</label>
		                                        <div class="col-sm-8">
		                                            <input type="text" id="timesheetinput1" class="form-control" placeholder="Door No" name="doorNo" value="<%=AppUtil.getNullToEmpty(addressDO.getDooNo() )%>">
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Street Name</label>
		                                        <div class="col-sm-8">
		                                           <input type="text" id="timesheetinput1" class="form-control" placeholder="Street Name" name="streetName" value="<%=AppUtil.getNullToEmpty(addressDO.getStreetName() )%>">
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                    <div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">City</label>
		                                        <div class="col-sm-8">
		                                           <input type="text" id="timesheetinput1" class="form-control" placeholder="City" name="city" value="<%=AppUtil.getNullToEmpty(addressDO.getCity() )%>">
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">State</label>
		                                        <div class="col-sm-8">
		                                          <input type="text" id="timesheetinput1" class="form-control" placeholder="State" name="state" value="<%=AppUtil.getNullToEmpty(addressDO.getState() )%>">
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Land Mark</label>
		                                        <div class="col-sm-8">
		                                        <input type="text" id="timesheetinput1" class="form-control" placeholder="Land Mark" name="landMark" value="<%=AppUtil.getNullToEmpty(addressDO.getLandMark() )%>">
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Road Name</label>
		                                        <div class="col-sm-8">
		                                          <input type="text" id="timesheetinput1" class="form-control" placeholder="Road Name" name="roadName" value="<%=AppUtil.getNullToEmpty(addressDO.getRoadName() )%>">
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                	<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="pinCode" class="col-sm-3 p-t-5  control-label col-form-label">Pin Code</label>
		                                        <div class="col-sm-8">
		                                            <input type="text" id="pinCode" class="form-control numbersonly" placeholder="Pin Code" name="pinCode" value="<%=AppUtil.getNullToEmpty(addressDO.getPincode() )%>">
		                                        </div>
		                                    </div>
                                		</div>
                                	
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Email<span style="color: #f62d51;">*</span></label>
		                                        <div class="col-sm-8">
		                                            <input type="text" id="email" class="form-control email" placeholder="Email" name="email" value="<%=AppUtil.getNullToEmpty(contactDO.getEmail1() )%>" required="required">
		                                        </div>
		                                    </div>
                                		</div>
                                		
                                		
                                	</div>
                                	
                                	
                                		
                                	<div class="row">
                                	
                                	<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="mobileNumber" class="col-sm-3 p-t-5  control-label col-form-label">Mobile Number<span style="color: #f62d51;">*</span></label>
		                                        <div class="col-sm-8">
		                                           <input type="text" id="mobileNumber" class="form-control numbersonly" placeholder="Mobile Number" maxlength="15" name="mobileNumber" value="<%=AppUtil.getNullToEmpty(contactDO.getMobile1() )%>" required="required">
		                                        </div>
		                                    </div>
                                		</div>
                                	<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Web Site</label>
		                                        <div class="col-sm-8">
		                                           <input type="text" id="timesheetinput2" class="form-control " placeholder="Website" name="webSite" value="<%=AppUtil.getNullToEmpty(contactDO.getWebsite() )%>">
		                                        </div>
		                                    </div>
                                		</div>
                                		
                                		
                                	</div> 
                                	
                                	
                                	<div class="row">
                                	<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="pan" class="col-sm-3 p-t-5  control-label col-form-label">PAN<span style="color: #f62d51;">*</span></label>
		                                        <div class="col-sm-8">
		                                         <input type="text" id="pan" class="form-control panno" placeholder="PAN" name="pan" value="<%=AppUtil.getNullToEmpty(panNo)%>" required="required">
		                                        </div>
		                                    </div>
                                		</div>
                                	
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">GST</label>
		                                        <div class="col-sm-8">
		                                            <input type="text" id="timesheetinput2" class="form-control" placeholder="GST" name="gst" readonly="readonly" value="<%=AppUtil.getNullToEmpty( gstinNo )%>">
		                                        </div>
		                                    </div>
                                		</div>
                                		</div>   
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-danger m-t-10 float-right"  data-dismiss="modal"><i class="fas fa-times"></i> Cancel</button>
	               <button type="submit" class="btn btn-success m-r-10 m-t-10 float-right"><i class="fa fa-check"></i> Save</button>
               </div>
               </form>
           </div>
           <!-- /.modal-content -->
       </div>
       <!-- /.modal-dialog -->


<script type="text/javascript">

jQuery.validator.addMethod("lettersonly", function(value, element) {return this.optional(element) || value.match(/^[a-zA-Z ]+$/);	}, " Enter Characters Only"); 
jQuery.validator.addMethod("alphanumeric",function(value, element) {return this.optional(element) || value == value.match(/^[a-z0-9A-Z]+$/);	}, " Enter Characters, Numbers Only"); 
jQuery.validator.addMethod("Decimal", function(value, element)  { return this.optional(element) ||value.match(/^[0.0-9.9]+$/);}," Enter Decimal Only" ); 
jQuery.validator.addMethod("numbersonly", function(value, element) { return this.optional(element) || value.match(/^[0-9-]+$/);	}, " Enter Numbers Only"); 
jQuery.validator.addMethod("alphaSpl", function(value, element) {return this.optional(element) || value.match(/^[a-zA-Z-,. 0-9\/]+$/); }," Enter Characters,Numbers,Space,slash,hypen and comma Only " ); 
jQuery.validator.addMethod("email", function(value, element) {   return this.optional(element) ||value.match(/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/);	 }," Enter valid Email id" ); 
jQuery.validator.addMethod("panno", function(value, element) {   return this.optional(element) ||value.match( /^([A-Z]{5})(\d{4})([A-Z]{1})$/); }," Enter valid Pan No" );	
jQuery.validator.addClassRules("web", {url:true,maxlength:75}); 

$(document).ready( function(){
	try{		
		$('#<%=formName%>').validate({
			errorClass: 'invalid',
			validClass: 'valid',
			errorPlacement: function(error, element) {
				error.insertAfter(element);
			},
			rules: {
				pan: { required: true },
				mobileNumber:{required:true},
				
			},
			messages: {
				pan: { required: 'PAN number is required' },
				mobileNumber:{required:'Mobile Number is required'},
				contactPerson:{required:'Contact Person Name is required'},
				email:{required:'Email is required'},
				customerName:{required:'Customer Name is required'}
				
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


