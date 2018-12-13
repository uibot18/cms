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
                   <h4 class="modal-title" id="myLargeModalLabel">Customer Display</h4>
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
               </div>
               <div class="modal-body">
               		<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Customer Name</label>
		                                        <div class="col-sm-8">
		                                           <%=AppUtil.getNullToEmpty(ledgerMstDO.getLedgerName() )%>
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Contact Person Name</label>
		                                        <div class="col-sm-8">
		                                           <%=AppUtil.getNullToEmpty(personalDO.getFirstName() )%>
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Door No</label>
		                                        <div class="col-sm-8">
		                                            <%=AppUtil.getNullToEmpty(addressDO.getDooNo() )%>
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Street Name</label>
		                                        <div class="col-sm-8">
		                                           <%=AppUtil.getNullToEmpty(addressDO.getStreetName() )%>
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                    <div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">City</label>
		                                        <div class="col-sm-8">
		                                           <%=AppUtil.getNullToEmpty(addressDO.getCity() )%>
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">State</label>
		                                        <div class="col-sm-8">
		                                          <%=AppUtil.getNullToEmpty(addressDO.getState() )%>
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Land Mark</label>
		                                        <div class="col-sm-8">
		                                        <%=AppUtil.getNullToEmpty(addressDO.getLandMark() )%>
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Road Name</label>
		                                        <div class="col-sm-8">
		                                          <%=AppUtil.getNullToEmpty(addressDO.getRoadName() )%>
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                	<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Pin Code</label>
		                                        <div class="col-sm-8">
		                                            <%=AppUtil.getNullToEmpty(addressDO.getPincode() )%>
		                                        </div>
		                                    </div>
                                		</div>
                                	
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Email</label>
		                                        <div class="col-sm-8">
		                                            <%=AppUtil.getNullToEmpty(contactDO.getEmail1() )%>
		                                        </div>
		                                    </div>
                                		</div>
                                		
                                		
                                	</div>
                                	
                                	
                                		
                                	<div class="row">
                                	
                                	<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Mobile Number</label>
		                                        <div class="col-sm-8">
		                                           <%=AppUtil.getNullToEmpty(contactDO.getMobile1() )%>
		                                        </div>
		                                    </div>
                                		</div>
                                	<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Web Site</label>
		                                        <div class="col-sm-8">
		                                           <%=AppUtil.getNullToEmpty(contactDO.getWebsite() )%>
		                                        </div>
		                                    </div>
                                		</div>
                                		
                                		
                                	</div> 
                                	
                                	
                                	<div class="row">
                                	<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">PAN</label>
		                                        <div class="col-sm-8">
		                                         <%=AppUtil.getNullToEmpty(panNo)%>
		                                        </div>
		                                    </div>
                                		</div>
                                	
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">GST</label>
		                                        <div class="col-sm-8">
		                                            <%=AppUtil.getNullToEmpty( gstinNo )%>
		                                        </div>
		                                    </div>
                                		</div>
                                		</div>   
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-danger m-t-10 float-right"  data-dismiss="modal"><i class="fas fa-times"></i> Cancel</button>
	              
               </div>
               </form>
           </div>
           <!-- /.modal-content -->
       </div>
       <!-- /.modal-dialog -->


<script type="text/javascript">

</script>


