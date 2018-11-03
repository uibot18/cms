<!DOCTYPE html>
<%@page import="com.cms.cms_package.handler.PackageCreationController"%>
<%@page import="com.cms.service.handler.ServiceCreationController"%>
<%@page import="com.application.util.AppDateUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cms.common.search.SearchEnum"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Random"%>
<html class="loading" lang="en" data-textdirection="ltr">
  
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="">
    <title>CMS-</title>
    <link rel="apple-touch-icon" href="./resource/app-assets/images/ico/apple-icon-120.png">
    <link rel="shortcut icon" type="image/x-icon" href="./resource/app-assets/images/ico/favicon.ico">
  </head>
  <body class="vertical-layout vertical-menu 2-columns   menu-expanded fixed-navbar" data-open="click" data-menu="vertical-menu" data-color="bg-gradient-x-purple-blue" data-col="2-columns">
   <%@include file="header.jsp" %>
  
   <!-- Content start -->
   <%
   Map<String, String> requestMap= (Map<String,String>)request.getAttribute( SearchEnum.REQUEST_MAP.getKeyName() );
   if(requestMap==null){ requestMap=new HashMap<String, String>(); }

   Map<String, Object> resultMap=(Map<String, Object>)request.getAttribute( SearchEnum.RESULT_MAP.getKeyName() );
   if(resultMap==null){ resultMap=new HashMap<String, Object>(); }
   
   String customerName=AppUtil.getNullToEmpty( requestMap.get("customerName") );
   String contactPerson=AppUtil.getNullToEmpty( requestMap.get("contactPerson") );
   String serviceName=AppUtil.getNullToEmpty( requestMap.get("serviceName") );
   String packageName=AppUtil.getNullToEmpty( requestMap.get("packageName") );

   
   
   String formName="hlydy_frm_"+Math.abs( new Random().nextInt(9999));
   %>
   <div class="app-content content">
      <div class="content-wrapper">
        <div class="content-wrapper-before"></div>
        <div class="content-header row">
          <div class="content-header-left col-md-4 col-12 mb-2">
            <h3 class="content-header-title">Customer Booking</h3>
          </div>
          <div class="content-header-right col-md-8 col-12">
            <div class="breadcrumbs-top float-md-right">
              <div class="breadcrumb-wrapper mr-1">
                <!-- <ol class="breadcrumb">
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
			                <h4 class="card-title">Customer Booking Search</h4>
			                <a class="heading-elements-toggle"><i class="la la-ellipsis-v font-medium-3"></i></a>
			                <div class="heading-elements">
			                    <ul class="list-inline mb-0">
			                    	<li>
			                    		<a class="" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="customerBooking?action=add"> 
			                    			<b>+&nbsp;Add</b>
										</a>
			                    	</li>
			                   	</ul>
			                </div>
			            </div>
			            <div class="card-content collapse show">
			                <div class="card-body border-top-blue-grey border-top-lighten-5 ">
			                    <form id="<%=formName %>" class="form" action="customerBooking" method="post">
				                    <input type="hidden" name="action" value="search">
									<div class="form-body">
				                        <div class="row">
				                        	<div class="col-md-6">
						                        <div class="form-group">
													<label for="timesheetinput1">Customer Name</label>
													<div class="position-relative has-icon-left">
														<input type="text" id="customerName" class="form-control" placeholder="Customer Name" name="customerName" value="<%=customerName%>">
													</div>
												</div>
					                        </div>
					                        
					                        <div class="col-md-6">
						                        <div class="form-group">
													<label for="timesheetinput1">Contact Person</label>
													<div class="position-relative has-icon-left">
														<input type="text" id="contactPerson" class="form-control singledate" placeholder="Contact Person" name="contactPerson" value="<%=contactPerson%>">
													</div>
												</div>
					                        </div>
				                        </div>
				                        <div class="row">
				                        	<div class="col-md-6">
						                        <div class="form-group">
													<label for="timesheetinput1">Service Person</label>
													<div class="position-relative has-icon-left">
														<select id="serviceName" class="form-control" placeholder="service Name" name="serviceName">
															<option></option>
															<%=ServiceCreationController.serviceOption("", ""+serviceName)%>
														</select>
													</div>
												</div>
					                        </div>
					                        <div class="col-md-6">
						                        <div class="form-group">
													<label for="timesheetinput1">Package Person</label>
													<div class="position-relative has-icon-left">
														<select id="packageName" class="form-control" placeholder="Package Name" name="packageName">
															<option></option>
															<%=PackageCreationController.packageOption("", ""+packageName)%>
														</select>
													</div>
												</div>
					                        </div>
				                        </div>
				                        
										
									</div>
									<div class="form-actions right">
										<button type="submit" class="btn btn-primary">
											<i class="fa fa-check-square-o"></i> Search
										</button>
										<button type="button" class="btn btn-danger mr-1" onclick="<%=formName%>reset()">
											<i class="ft-x"></i> Reset
										</button>
										
									</div>
								</form>
			                </div>
			            </div>
			        </div>
			    </div>
			</section>
			
			<section class="row">
			    <div class="col-md-12 col-sm-12">
			        <div id="with-header" class="card">
			            <div class="card-header">
			                <h4 class="card-title">Holiday List</h4>
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
			                   <form id="<%=formName %>_tble" class="form" action="#" method="post">
			                	<div class="table-responsive">
									<table class="table">
										<thead class="bg-primary white">
											<tr>
												<th>#</th>
												<th>Sale ID</th>
												<th>Sale Date</th>
												<th>Customer Name</th>
												<th>Email</th>
												<th>Mobile</th>
												<th>Holiday Type</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
										<%
										List<Map<String, Object>> resultList=(List<Map<String, Object>>)resultMap.get( SearchEnum.RESULT_LIST.getKeyName() );
										if(resultList==null){ resultList= new ArrayList<Map<String, Object>>();  }
										int sno=1;
										for(Map<String, Object> searchData:resultList){
											
											int sale_id=AppUtil.getNullToInteger( (String)searchData.get("COL#1") );
											String sale_date=AppDateUtil.convertToAppDate((String)searchData.get("COL#2"), false, true);
											String customer_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#3") );
											String email=AppUtil.getNullToEmpty( (String)searchData.get("COL#4") );
											String mobile=AppUtil.getNullToEmpty( (String)searchData.get("COL#5") );
										%>
											<tr>
												<th scope="row"><%=sno %></th>
												<td><%=sale_id  %></td>
												<td><%=sale_date  %></td>
												<td><%=customer_name  %></td>
												<td><%=email  %></td>
												<td><%=mobile  %></td>
												<td>
													<a data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="customerBooking?action=edit&saleId=<%=sale_id%>">Edit</a> &nbsp;&nbsp;
													<a class='<%=formName %>_delete' href="javascript:;" ahref="customerBooking?action=delete&saleId=<%=sale_id%>">delete</a></td>
											</tr>
										<%sno++;
										} %>
											
										</tbody>
									</table>
								</div> 
								</form>
			                </div>
			            </div>
			        </div>
			    </div>
			</section>
			
        </div>
      </div>
    </div>
   
   
   <!-- Content End -->
   
   			

			
   
   <%@include file="footer.jsp" %>
  </body>
<script type="text/javascript">

$(document).ready(function(){
	
	

	
});

function <%=formName %>reset(){
	$('#<%=formName %> #holidayName').val('');$('#<%=formName %> #holidayName').attr('value', '');
	$('#<%=formName %> #holidayType').val('');$('#<%=formName %> #holidayType').attr('value', '');
	$('#<%=formName %> #holidaySubType').val('');$('#<%=formName %> #holidaySubType').attr('value', '');
	$('#<%=formName %> #holidayDate').val('');$('#<%=formName %> #holidayDate').attr('value', '');
}

$('#<%=formName %>_tble').on('click', '.<%=formName %>_delete', function(){
	
	if(confirm("Do You Want Remove this ?")==true){
	var params=$(this).attr("ahref");
	var trobj=$(this);
	$.getJSON(params,function(data){
		if(data.errorExists=='true'){
		}
		else{
			$(trobj).closest("tr").remove();
		}
		alert(data.message);
	});
	
	}
	});
</script>

</html>