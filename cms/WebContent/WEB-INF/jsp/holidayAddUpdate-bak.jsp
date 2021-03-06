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
<!DOCTYPE html>
<html class="loading" lang="en" data-textdirection="ltr">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="">
    <title>CMS-Holiday</title>
    <link rel="apple-touch-icon" href="./resource/app-assets/images/ico/apple-icon-120.png">
    <link rel="shortcut icon" type="image/x-icon" href="./resource/app-assets/images/ico/favicon.ico">
  </head>
  <body class="vertical-layout vertical-menu 2-columns   menu-expanded fixed-navbar" data-open="click" data-menu="vertical-menu" data-color="bg-gradient-x-purple-blue" data-col="2-columns">
   <%-- <%@include file="header.jsp" %> --%>
   
   <!-- Content Start -->
  <div class="app-content content">
      <div class="content-wrapper">
        <div class="content-wrapper-before"></div>
        <div class="content-header row">
          <div class="content-header-left col-md-4 col-12 mb-2">
            <h3 class="content-header-title">Holiday</h3>
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
			                <h4 class="card-title">Holiday Add Form</h4>
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
			                    <form class="form" action="holiday" method="post" id="<%=formName%>">
			                    <input type="hidden" name="action" value="save">
			                     <input type="hidden" name="holidayId" value="<%=holidayDO.getHolidayId()%>">
			                    
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
							<div class="form-actions right">
								<button type="submit" class="btn btn-primary">
									<i class="fa fa-check-square-o"></i> Save
								</button>
								<button type="button" class="btn btn-danger mr-1" onclick="<%=formName %>reset()">
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
   
  <%--  <%@include file="footer.jsp" %> --%>
  </body>
<script type="text/javascript">

$(document).ready( function(){
	
});

function <%=formName %>reset(){
	$('#<%=formName %> #holidayType').val('');$('#<%=formName %> #holidayType').attr('value', '');
}
</script>
</html>