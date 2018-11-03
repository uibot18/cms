<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Random"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="com.cms.common.search.SearchEnum"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
   
   String employeeName=AppUtil.getNullToEmpty( requestMap.get("employeeName") );
   String department=AppUtil.getNullToEmpty( requestMap.get("department") );
   String designation=AppUtil.getNullToEmpty( requestMap.get("designation") );
   String qualification=AppUtil.getNullToEmpty( requestMap.get("qualification") );
   String state=AppUtil.getNullToEmpty( requestMap.get("state") );
   String city=AppUtil.getNullToEmpty( requestMap.get("city") );
   String pinCode=AppUtil.getNullToEmpty( requestMap.get("pinCode") );
   String email=AppUtil.getNullToEmpty( requestMap.get("email") );
   String mobile=AppUtil.getNullToEmpty( requestMap.get("mobile") );
   
   String formName="emp_frm_"+Math.abs( new Random().nextInt(9999) );
   
   %>
   
   <div class="app-content content">
      <div class="content-wrapper">
        <div class="content-wrapper-before"></div>
        <div class="content-header row">
          <div class="content-header-left col-md-4 col-12 mb-2">
            <h3 class="content-header-title">Employee</h3>
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
			                <h4 class="card-title">Employee Search</h4>
			                <a class="heading-elements-toggle"><i class="la la-ellipsis-v font-medium-3"></i></a>
			                <div class="heading-elements">
			                    <ul class="list-inline mb-0">
			                        <!-- <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
			                        <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
			                        <li><a data-action="close"><i class="ft-x"></i></a></li> -->
			                        <li>
			                    		<a  id="testId" class="" data-target="#CMS-POPUP-MODEL" data-toggle="modal"  data-url="employee?action=add"> 
			                    			<b>+&nbsp;Add</b>
										</a>
			                    	</li>
			                    </ul>
			                </div>
			            </div>
			            <div class="card-content collapse show">
			                <div class="card-body border-top-blue-grey border-top-lighten-5 ">
			                    <form id="<%=formName %>" class="form" action="employee" method="post">
			                    <input type="hidden" name="action" value="search">
							<div class="form-body">
								<div class="row">
	                    			<div class="col-md-6">
				                        <div class="form-group row">
				                        	<label class="col-md-3 label-control">Employee Name</label>
				                        	<div class="col-md-9">
				                            	<input type="text" id="employeeName" class="form-control" placeholder="Employee Name" name="employeeName" value="<%=employeeName%>">
				                            </div>
				                        </div>
				                    </div>
				                    <div class="col-md-6">
				                        <div class="form-group row">
				                        	<label class="col-md-3 label-control" >Department</label>
				                        	<div class="col-md-9">
				                            	<input type="text" id="department" class="form-control" placeholder="Department" name="department" value="<%=department%>">
			                        		</div>
				                        </div>
			                        </div>
		                        </div>
		                        
		                        <div class="row">
	                    			<div class="col-md-6">
				                        <div class="form-group row">
				                        	<label class="col-md-3 label-control" >designation</label>
				                        	<div class="col-md-9">
				                            	<input type="text" id="designation" class="form-control" placeholder="designation" name="designation" value="<%=designation%>">
				                            </div>
				                        </div>
				                    </div>
				                     <div class="col-md-6">
				                        <div class="form-group row">
				                        	<label class="col-md-3 label-control" >Qualification</label>
				                        	<div class="col-md-9">
				                            	<input type="text" id="qualification" class="form-control" placeholder="Qualification" name="qualification" value="<%=qualification%>">
			                        		</div>
				                        </div>
			                        </div>
				                    
				                    
		                        </div>
		                        
		                        <div class="row">
	                    			<div class="col-md-6">
				                        <div class="form-group row">
				                        	<label class="col-md-3 label-control" >State</label>
				                        	<div class="col-md-9">
				                            	<input type="text" id="state" class="form-control" placeholder="State" name="state" value="<%=state%>">
				                            </div>
				                        </div>
				                    </div>
				                    <div class="col-md-6">
				                        <div class="form-group row">
				                        	<label class="col-md-3 label-control" >City</label>
				                        	<div class="col-md-9">
				                            	<input type="text" id="city" class="form-control" placeholder="City" name="city" value="<%=city%>">
				                            </div>
				                        </div>
				                    </div>
				                    
		                        </div>
		                        
		                        <div class="row">
	                    			<div class="col-md-6">
				                        <div class="form-group row">
				                        	<label class="col-md-3 label-control" >Pin Code</label>
				                        	<div class="col-md-9">
				                            	<input type="text" id="pinCode" class="form-control" placeholder="Pin Code" name="pinCode" value="<%=pinCode%>">
				                            </div>
				                        </div>
				                    </div>
				                    <div class="col-md-6">
				                        <div class="form-group row">
				                        	<label class="col-md-3 label-control" >Mobile</label>
				                        	<div class="col-md-9">
				                            	<input type="text" id="mobile" class="form-control" placeholder="Mobile" name="mobile" value="<%=mobile%>">
			                        		</div>
				                        </div>
			                        </div>
		                        </div>
		                        
		                        <div class="row">
	                    			
			                        <div class="col-md-6">
				                        <div class="form-group row">
				                        	<label class="col-md-3 label-control" >Email</label>
				                        	<div class="col-md-9">
				                            	<input type="text" id="email" class="form-control" placeholder="Email" name="email" value="<%=email%>">
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
			                <h4 class="card-title">Employee List</h4>
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
												<th>Employee Name</th>
												<th>Reporting To</th>
												<th>Department</th>
												<th>Designation</th>
												<th>Mobile</th>
												<th>Email</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
										<%
										List<Map<String, Object>> resultList=(List<Map<String, Object>>)resultMap.get( SearchEnum.RESULT_LIST.getKeyName() );
										if(resultList==null){ resultList= new ArrayList<Map<String, Object>>();  }
										int sno=1;
										for(Map<String, Object> searchData:resultList){
										
											int emp_id=AppUtil.getNullToInteger( (String)searchData.get("COL#1") );
											String emp_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#2") );
											String reporting_to=AppUtil.getNullToEmpty( (String)searchData.get("COL#3") );
											String s_department=AppUtil.getNullToEmpty( (String)searchData.get("COL#4") );
											String s_designation=AppUtil.getNullToEmpty( (String)searchData.get("COL#5") );
											String s_email=AppUtil.getNullToEmpty( (String)searchData.get("COL#6") );
											String mobile_no=AppUtil.getNullToEmpty( (String)searchData.get("COL#7") );
										
										%>
											<tr>
												<th scope="row"><%=sno %></th>
												<td><%=emp_name %></td>
												<td><%=reporting_to %></td>
												<td><%=s_department %></td>
												<td><%=s_designation %></td>
												<td><%=mobile_no %></td>
												<td><%=s_email  %></td>
												<td>
													<a data-target="#CMS-POPUP-MODEL" data-toggle="modal"  data-url="employee?action=edit&employeeId=<%=emp_id%>" href="#">Edit</a> &nbsp;&nbsp;
													<a class='<%=formName %>_delete' href="javascript:;" ahref="employee?action=delete&employeeId=<%=emp_id%>">delete</a>&nbsp;&nbsp;
													<a href="employee?action=view&employeeId=<%=emp_id%>">View</a></td>
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
	
	$('#<%=formName %> #employeeName').val('');$('#<%=formName %> #employeeName').attr('value', '');
	$('#<%=formName %> #department').val('');$('#<%=formName %> #department').attr('value', '');
	$('#<%=formName %> #designation').val('');$('#<%=formName %> #designation').attr('value', '');
	$('#<%=formName %> #qualification').val('');$('#<%=formName %> #qualification').attr('value', '');
	$('#<%=formName %> #state').val('');$('#<%=formName %> #state').attr('value', '');
	$('#<%=formName %> #city').val('');$('#<%=formName %> #city').attr('value', '');
	$('#<%=formName %> #pinCode').val('');$('#<%=formName %> #pinCode').attr('value', '');
	$('#<%=formName %> #mobile').val('');$('#<%=formName %> #mobile').attr('value', '');
	$('#<%=formName %> #email').val('');$('#<%=formName %> #email').attr('value', '');
	
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