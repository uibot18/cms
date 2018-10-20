<!DOCTYPE html>
<%@page import="com.cms.cms_package.handler.PackageCreationController"%>
<%@page import="com.cms.service.handler.ServiceCreationController"%>
<%@page import="com.cms.holiday.handler.HolidayTypeCreationController"%>
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
   
   String serviceName=AppUtil.getNullToEmpty( requestMap.get("serviceName") );
   String packageName=AppUtil.getNullToEmpty( requestMap.get("packageName") );
   String processName=AppUtil.getNullToEmpty( requestMap.get("processName") );
   
   String formName="Proc_frm_"+Math.abs( new Random().nextInt(9999));
   %>
   <div class="app-content content">
      <div class="content-wrapper">
        <div class="content-wrapper-before"></div>
        <div class="content-header row">
          <div class="content-header-left col-md-4 col-12 mb-2">
            <h3 class="content-header-title">Process</h3>
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
			                <h4 class="card-title">Process Search</h4>
			                <a class="heading-elements-toggle"><i class="la la-ellipsis-v font-medium-3"></i></a>
			                <div class="heading-elements">
			                    <ul class="list-inline mb-0">
			                        <!-- <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
			                        <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
			                        <li><a data-action="close"><i class="ft-x"></i></a></li> -->
			                        <li>
			                    		<a  id="testId" class="" data-target="#CMS-POPUP-MODEL" data-toggle="modal"  data-url="process?action=add"> 
			                    			<b>+&nbsp;Add</b>
										</a>
			                    	</li>
			                    </ul>
			                </div>
			            </div>
			            <div class="card-content collapse show">
			                <div class="card-body border-top-blue-grey border-top-lighten-5 ">
			                    <form id="<%=formName %>" class="form" action="process" method="post">
				                    <input type="hidden" name="action" value="search">
									<div class="form-body">
				                        
				                        <div class="row">
					                        <%-- <div class="col-md-6">
						                        <div class="form-group row">
						                        	<label class="col-md-3 label-control" >Service Name</label>
						                        	<div class="col-md-9">
						                            	<select id="timesheetinput2" class="form-control" placeholder="Service Name" name="serviceName" ">
						                            		<option></option>
															<%=ServiceCreationController.serviceOption("", serviceName) %>
														</select>
					                        		</div>
						                        </div>
					                        </div> --%>
					                        
					                        <div class="col-md-6">
						                        <div class="form-group row">
						                        	<label class="col-md-3 label-control" >Package Name</label>
						                        	<div class="col-md-9">
						                            	<select id="timesheetinput2" class="form-control" placeholder="Package Name" name="packageName" ">
						                            		<option></option>
															<%=PackageCreationController.packageOption("", packageName) %>
														</select>
					                        		</div>
						                        </div>
					                        </div>
					                        
					                        <div class="col-md-6">
						                        <div class="form-group row">
						                        	<label class="col-md-3 label-control" >Process Name</label>
						                        	<div class="col-md-9">
						                            	<input type="text" id="packageName" class="form-control" placeholder="Process Name" name="processName" value="<%=processName%>">
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
			                <h4 class="card-title">Package List</h4>
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
												<th>Package Name</th>
												<th>Process Name</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
										<%
										List<Map<String, Object>> resultList=(List<Map<String, Object>>)resultMap.get( SearchEnum.RESULT_LIST.getKeyName() );
										if(resultList==null){ resultList= new ArrayList<Map<String, Object>>();  }
										int sno=1;
										for(Map<String, Object> searchData:resultList){
											/* SELECT a.cmn_master_id, a.cmn_group_id, a.parent_id, b.cmn_master_name AS service_name, a.cmn_master_name AS package_name, a.level_no  */
											int package_id=AppUtil.getNullToInteger( (String)searchData.get("COL#1") );
											int package_group_id=AppUtil.getNullToInteger( (String)searchData.get("COL#2") );
											int parent_id=AppUtil.getNullToInteger( (String)searchData.get("COL#3") );
											String package_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#4") );
											String process_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#5") );
											int level_no=AppUtil.getNullToInteger( (String)searchData.get("COL#6") );
										%>
											<tr>
												<th scope="row"><%=sno %></th>
												<td><%=package_name  %></td>
												<td><%=process_name  %></td>
												<td>
													<a data-target="#CMS-POPUP-MODEL" data-toggle="modal"  data-url="process?action=edit&processId=<%=package_id%>" href="#">Edit</a> &nbsp;&nbsp;
													<a href="process?action=delete&processId=<%=package_id%>">delete</a></td>
											</tr>
										<%sno++;
										} %>
											
										</tbody>
									</table>
								</div> 
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

</script>

</html>