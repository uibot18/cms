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
%>
<!DOCTYPE html>
<html class="loading" lang="en" data-textdirection="ltr">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="">
    <title>Admin User Request</title>
    <link rel="apple-touch-icon" href="./resource/app-assets/images/ico/apple-icon-120.png">
    <link rel="shortcut icon" type="image/x-icon" href="./resource/app-assets/images/ico/favicon.ico">
  </head>
  <body class="vertical-layout vertical-menu 2-columns   menu-expanded fixed-navbar" data-open="click" data-menu="vertical-menu" data-color="bg-gradient-x-purple-blue" data-col="2-columns">
   <%@include file="header.jsp" %>
   
   <!-- Content Start -->
  <div class="app-content content">
      <div class="content-wrapper">
        <div class="content-wrapper-before"></div>
        <div class="content-header row">
          <div class="content-header-left col-md-4 col-12 mb-2">
            <h3 class="content-header-title">Customer</h3>
          </div>
          <div class="content-header-right col-md-8 col-12">
            <div class="breadcrumbs-top float-md-right">
              <div class="breadcrumb-wrapper mr-1">
               <!--  <ol class="breadcrumb">
                  <li class="breadcrumb-item"><a href="index.html">Home</a>
                  </li>
                  <li class="breadcrumb-item"><a href="#">Dashboard</a>
                  </li>
                  <li class="breadcrumb-item active">Customer add
                  </li>
                </ol> -->
              </div>
            </div>
          </div>
        </div>
        <div class="content-body">
			<section class="row">
			    <div class="col-md-12 col-sm-12">
			        <div id="with-header" class="card">
			            <div class="card-header">
			                <h4 class="card-title">Customer Add Form</h4>
			                <a class="heading-elements-toggle"><i class="la la-ellipsis-v font-medium-3"></i></a>
			                <div class="heading-elements">
			                    <ul class="list-inline mb-0">
			                        <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
			                        <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
			                        <li><a data-action="close"><i class="ft-x"></i></a></li>
			                    </ul>
			                </div>
			            </div>
			            <div class="card-content collapse show">
			                <div class="card-body border-top-blue-grey border-top-lighten-5 ">
			                    <form class="form" action="customer" method="post">
			                    <input type="hidden" name="action" value="save">
			                     <input type="hidden" name="customerId" value="<%=customerDO.getCustomerId()%>">
			                     <input type="hidden" name="ledgerId" value="<%=customerDO.getLedgerId()%>">
			                    
							<div class="form-body">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="timesheetinput1">Customer Name</label>
											<div class="position-relative has-icon-left">
												<input type="text" id="timesheetinput1" class="form-control" placeholder="Customer Name" name="customerName" value="<%=AppUtil.getNullToEmpty(ledgerMstDO.getLedgerName() )%>">
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
												<input type="text" id="timesheetinput2" class="form-control" placeholder="Contact Person" name="contactPerson" value="<%=AppUtil.getNullToEmpty(personalDO.getFirstName() )%>">
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
												<input type="text" id="timesheetinput2" class="form-control" placeholder="Email" name="email" value="<%=AppUtil.getNullToEmpty(contactDO.getEmail1() )%>">
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
												<input type="text" id="timesheetinput2" class="form-control" placeholder="Mobile Number" name="mobileNumber" value="<%=AppUtil.getNullToEmpty(contactDO.getMobile1() )%>">
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
												<input type="text" id="timesheetinput2" class="form-control" placeholder="PAN" name="pan" value="<%=AppUtil.getNullToEmpty(panNo)%>">
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
												<input type="text" id="timesheetinput2" class="form-control" placeholder="GST" name="gst" value="<%=AppUtil.getNullToEmpty( gstinNo )%>">
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
							<div class="form-actions right">
								<button type="submit" class="btn btn-primary">
									<i class="fa fa-check-square-o"></i> Save
								</button>
								<button type="button" class="btn btn-danger mr-1">
									<i class="ft-x"></i> Reset
								</button>
							</div>
						</form>
			                </div>
			            </div>
			        </div>
			    </div>
			</section>
			<!-- <section class="row">
			    <div class="col-md-12 col-sm-12">
			        <div id="with-header" class="card">
			            <div class="card-header">
			                <h4 class="card-title">Added Admin User</h4>
			                <a class="heading-elements-toggle"><i class="la la-ellipsis-v font-medium-3"></i></a>
			                <div class="heading-elements">
			                    <ul class="list-inline mb-0">
			                        <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
			                        <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
			                        <li><a data-action="close"><i class="ft-x"></i></a></li>
			                    </ul>
			                </div>
			            </div>
			            <div class="card-content collapse show">
			                <div class="card-body border-top-blue-grey border-top-lighten-5 ">
			                	<div class="table-responsive">
									<table class="table">
										<thead class="bg-primary white">
											<tr>
												<th>#</th>
												<th>User Name</th>
												<th>Login Id</th>
												<th>Email</th>
												<th>Mobile</th>
												<th>Status</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<th scope="row">1</th>
												<td>Mark</td>
												<td>mark123</td>
												<td>@mdo</td>
												<td>979779979</td>
												<td>Pending</td>
												<td>Edit</td>
											</tr>
											<tr>
												<th scope="row">1</th>
												<td>Mark</td>
												<td>mark123</td>
												<td>@mdo</td>
												<td>979779979</td>
												<td>Pending</td>
												<td>Edit</td>
											</tr>
											<tr>
												<th scope="row">1</th>
												<td>Mark</td>
												<td>mark123</td>
												<td>@mdo</td>
												<td>979779979</td>
												<td>Pending</td>
												<td>Edit</td>
											</tr>
										</tbody>
									</table>
								</div> 
			                </div>
			            </div>
			        </div>
			    </div>
			</section> -->
        </div>
      </div>
    </div>
   <!-- Content End  -->
   
   <%@include file="footer.jsp" %>
  </body>

</html>