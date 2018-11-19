<%@page import="java.util.Random"%>
<%@page import="java.util.List"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.cms.common.search.SearchEnum"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cms.user.login.bean.LoginMasterBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="./static/assets/images/favicon.png">
    <title>ui-bot</title>
</head>
<body>
    
    <!-- Preloader - style you can find in spinners.css -->
    <div class="preloader">
        <div class="lds-ripple">
            <div class="lds-pos"></div>
            <div class="lds-pos"></div>
        </div>
    </div>
<%
Map<String, String> requestMap= (Map<String,String>)request.getAttribute( SearchEnum.REQUEST_MAP.getKeyName() );
if(requestMap==null){ requestMap=new HashMap<String, String>(); }

Map<String, Object> resultMap=(Map<String, Object>)request.getAttribute( SearchEnum.RESULT_MAP.getKeyName() );
if(resultMap==null){ resultMap=new HashMap<String, Object>(); }

String customerName=AppUtil.getNullToEmpty( requestMap.get("customerName") );
String contactPersonName=AppUtil.getNullToEmpty( requestMap.get("contactPersonName") );
String city=AppUtil.getNullToEmpty( requestMap.get("city") );
String email=AppUtil.getNullToEmpty( requestMap.get("email") );
String mobile=AppUtil.getNullToEmpty( requestMap.get("mobile") );
String state=AppUtil.getNullToEmpty( requestMap.get("state") );
String webSite=AppUtil.getNullToEmpty( requestMap.get("webSite") );
String pinCode=AppUtil.getNullToEmpty( requestMap.get("pinCode") );
String panOrGst=AppUtil.getNullToEmpty( requestMap.get("panOrGst") );

String formName="cust_Srh_frm_"+Math.abs( new Random().nextInt(9999) );
%>


   
    <!-- Main wrapper - style you can find in pages.scss -->
    <div id="main-wrapper">
        <%@include file="header1.jsp" %>
        <!-- Page wrapper  -->
        
        <div class="page-wrapper">
            
            <!-- Container fluid  -->
            
            <div class="container-fluid">
                <!-- Start Page Content -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                            	<h4 class="card-title">CUSTOMER SEARCH
                            		<button type="button" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="customer?action=add" class="btn btn-primary btn-sm float-right btn-rounded" style="">
	                            		<i class="fa fa-plus"></i> 
	                            		ADD
	                            	</button>
                            	</h4>
                                 <form id="<%=formName %>" class="form" action="customer" method="post">
			                    <input type="hidden" name="action" value="search">
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Customer Name</label>
		                                        <div class="col-sm-8">
		                                           <input type="text" id="customerName" class="form-control" placeholder="Customer Name" name="customerName" value="<%=customerName%>">
		                                        </div>
		                                    </div>
                                		</div>
                                		
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Contact Person Name</label>
		                                        <div class="col-sm-8">
		                                            <input type="text" id="contactPersonName" class="form-control" placeholder="Contact Person Name" name="contactPersonName" value="<%=contactPersonName%>">
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	
                                	
                                	
                                	
                  <div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">City</label>
		                                        <div class="col-sm-8">
		                                            <input type="text" id="city" class="form-control" placeholder="City" name="city" value="<%=city%>">
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Email</label>
		                                        <div class="col-sm-8">
		                                           <input type="text" id="email" class="form-control" placeholder="Email" name="email" value="<%=email%>">
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">State</label>
		                                        <div class="col-sm-8">
		                                           <input type="text" id="state" class="form-control" placeholder="State" name="state" value="<%=state%>">
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Mobile</label>
		                                        <div class="col-sm-8">
		                                            <input type="text" id="mobile" class="form-control" placeholder="Mobile" name="mobile" value="<%=mobile%>">
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Pin Code</label>
		                                        <div class="col-sm-8">
		                                           <input type="text" id="pinCode" class="form-control" placeholder="Pin Code" name="pinCode" value="<%=pinCode%>">
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Web Site</label>
		                                        <div class="col-sm-8">
		                                           <input type="text" id="webSite" class="form-control" placeholder="Web Site" name="webSite" value="<%=webSite%>">
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">PAN/GST</label>
		                                        <div class="col-sm-8">
		                                            <input type="text" id="panOrGst" class="form-control" placeholder="PAN/GST" name="panOrGst" value="<%=panOrGst%>">
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                  <button type="button" class="btn btn-dark m-t-10 float-right" onclick="<%=formName %>reset()">Reset</button>
                                    <button type="submit" class="btn btn-success m-r-10 m-t-10 float-right">Search</button>
                                </form>
                            </div>
                        </div>
                    </div>
   
   
   
   
   <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Added  User</h4>
                            </div>
                            <form id="<%=formName %>_tble" class="form" action="#" method="post">
	                            <div class="table-responsive">
	                                <table class="table table-bordered text-center">
	                                    <thead>
	                                        <tr>
												<th>#</th>
												<th>Customer Name</th>
												<th>Contact Person</th>
												<th>Email</th>
												<th>Mobile</th>
												<th>Web Site</th>
												<th>Action</th>
											</tr>
	                                    </thead>
	                                    <tbody>
	                                    <%
										List<Map<String, Object>> resultList=(List<Map<String, Object>>)resultMap.get( SearchEnum.RESULT_LIST.getKeyName() );
										if(resultList==null){ resultList= new ArrayList<Map<String, Object>>();  }
										int sno=1;
										for(Map<String, Object> searchData:resultList){
										
											int customer_id=AppUtil.getNullToInteger( (String)searchData.get("COL#1") );
											int ledger_id=AppUtil.getNullToInteger( (String)searchData.get("COL#2") );
											String customer_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#3") );
											String contact_person_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#4") );
											String mobile_no=AppUtil.getNullToEmpty( (String)searchData.get("COL#5") );
											String s_email=AppUtil.getNullToEmpty( (String)searchData.get("COL#6") );
											String web_site=AppUtil.getNullToEmpty( (String)searchData.get("COL#7") );
										
										%>
											<tr>
												<th scope="row"><%=sno %></th>
												<td><%=customer_name %></td>
												<td><%=contact_person_name %></td>
												<td><%=s_email %></td>
												<td><%=mobile_no %></td>
												<td><%=web_site %></td>
												<td>
													<a data-target="#CMS-POPUP-MODEL" data-toggle="modal"  data-url="customer?action=edit&customerId=<%=customer_id%>" href="#">Edit</a> &nbsp;&nbsp;
													<a href="customer?action=view&customerId=<%=customer_id%>">View</a>
													<a class='<%=formName %>_delete' href="javascript:;" ahref="customer?action=delete&customerId=<%=customer_id%>">Delete</a>
													</td>
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
                <!-- End PAge Content -->
            </div>
            <!-- End Container fluid  -->
            <%@include file="footer1.jsp"%>
        </div>
        <!-- End Page wrapper  -->
    </div>
    <!-- End Wrapper -->
    <div class="chat-windows"></div>
   
		
  </body>
<script type="text/javascript">

function <%=formName %>reset(){
	$('#<%=formName %> #customerName').val('');$('#<%=formName %> #customerName').attr('value', '');
	$('#<%=formName %> #contactPersonName').val('');$('#<%=formName %> #contactPersonName').attr('value', '');
	$('#<%=formName %> #city').val('');$('#<%=formName %> #city').attr('value', '');
	$('#<%=formName %> #email').val('');$('#<%=formName %> #email').attr('value', '');
	$('#<%=formName %> #mobile').val('');$('#<%=formName %> #mobile').attr('value', '');
	$('#<%=formName %> #state').val('');$('#<%=formName %> #state').attr('value', '');
	$('#<%=formName %> #webSite').val('');$('#<%=formName %> #webSite').attr('value', '');
	$('#<%=formName %> #pinCode').val('');$('#<%=formName %> #pinCode').attr('value', '');
	$('#<%=formName %> #panOrGst').val('');$('#<%=formName %> #panOrGst').attr('value', '');
}



$('#<%=formName %>_tble').on('click', '.<%=formName %>_delete', function(){
	
	if(confirm("Do You Want Remove this ?")==true){
	var params=$(this).attr("ahref");
	var trobj=$(this);
	$.getJSON(params,function(data){
		if(data.errorExists==true){
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