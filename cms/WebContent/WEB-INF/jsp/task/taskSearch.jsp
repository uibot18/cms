<!DOCTYPE html>
<%@page import="com.cms.task.handler.TaskType"%>
<%@page import="com.cms.process.handler.ProcessCreationController"%>
<%@page import="com.cms.cms_package.handler.PackageCreationController"%>
<%@page import="com.cms.service.handler.ServiceCreationController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Random"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.cms.common.search.SearchEnum"%>
<%@page import="java.util.Map"%>
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
   
   String serviceName=AppUtil.getNullToEmpty( requestMap.get("serviceName") );
   String packageName=AppUtil.getNullToEmpty( requestMap.get("packageName") );
   String processName=AppUtil.getNullToEmpty( requestMap.get("processName") );
   
   String formName="Proc_frm_"+Math.abs( new Random().nextInt(9999));
   %>
    
    <!-- Main wrapper - style you can find in pages.scss -->
    <div id="main-wrapper">
        <%@include file="../header1.jsp" %>
        <!-- Page wrapper  -->
        
        <div class="page-wrapper">
            
            <!-- Container fluid  -->
            
            <div class="container-fluid">
                <!-- Start Page Content -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                            	<h4 class="card-title">SERVICE SEARCH
                            		<button type="button" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="task?action=add&taskType=<%=TaskType.Customer.getType()%>" class="btn btn-primary btn-sm float-right btn-rounded" style="">
	                            		<i class="fa fa-plus"></i> 
	                            		Customer Task
	                            	</button>
	                            	<button type="button" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="task?action=add&taskType=<%=TaskType.GeneralTask.getType()%>" class="btn btn-primary btn-sm float-right btn-rounded" style="">
	                            		<i class="fa fa-plus"></i> 
	                            		General Task
	                            	</button>
                            	</h4>
                                <form id="<%=formName %>" class="form p-t-20" action="service?action=search" method="post">
                                	<div class="row">
                                		<div class="col-sm-4">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Service Name</label>
		                                        <div class="col-sm-8">
		                                            <select class="select2 form-control custom-select"  name="serviceName" id="serviceName"  style="width: 100%; height:26px;">
					                                    <option></option>
					                                    <%=ServiceCreationController.serviceOption("", serviceName) %>
					                                </select>
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-4">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Service Name</label>
		                                        <div class="col-sm-8">
		                                            <select class="select2 form-control custom-select"  name="packageName" id="packageName"  style="width: 100%; height:26px;">
					                                    <option></option>
					                                    <%=PackageCreationController.packageOption("", packageName) %>
					                                </select>
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-4">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Service Name</label>
		                                        <div class="col-sm-8">
		                                            <select class="select2 form-control" name="processName" id="processName" style="width: 100%; height:26px;">
					                                    <option></option>
					                                    <%=ProcessCreationController.processOption("", processName) %>
					                                </select>
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
                                <h4 class="card-title">Employee List</h4>
                            </div>
                            <form id="<%=formName %>_tble" class="form" action="#" method="post">
	                            <div class="table-responsive">
	                                <table class="table table-bordered text-center">
	                                    <thead>
	                                        <tr>
	                                            <th scope="col">#</th>
												<th scope="col">Customer</th>
												<th scope="col">Create On</th>
												<th scope="col">Status</th>
												<th scope="col">Task Type</th>
												<th scope="col">Booking NO</th>
												<th scope="col">Action</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                    <%
										List<Map<String, Object>> resultList=(List<Map<String, Object>>)resultMap.get( SearchEnum.RESULT_LIST.getKeyName() );
										if(resultList==null){ resultList= new ArrayList<Map<String, Object>>();  }
										int sno=1;
										for(Map<String, Object> searchData:resultList){
											/* SELECT a.task_config_id, a.task_config_name, a.exe_order, b.cmn_master_name AS process_name, c.cmn_master_name AS package_name, d.cmn_master_name AS service_name*/
											int process_master_id=AppUtil.getNullToInteger( (String)searchData.get("COL#1") );
											String task_config_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#2") );
											int exe_order=AppUtil.getNullToInteger( (String)searchData.get("COL#3") );
											String process_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#4") );
											String package_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#5") );
											String service_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#6") );
											String customer_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#7") );
										%>
											<tr>
												<th scope="row"><%=sno %></th>
												<td><%=customer_name  %></td>
												<td><%=package_name  %></td>
												<td><%=process_name  %></td>
												<td><%=task_config_name  %></td>
												<td><%=exe_order  %></td>
												<td>
													<a data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="task?action=edit&processMasterId=<%=process_master_id%>" href="#">Edit</a> &nbsp;&nbsp;
													<%-- <a href="taskConfig?action=delete&taskConfigId=<%=task_config_id%>">delete</a>&nbsp;&nbsp; --%>
													
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
            <%@include file="../footer1.jsp"%>
        </div>
        <!-- End Page wrapper  -->
    </div>
    <!-- End Wrapper -->
    <div class="chat-windows"></div>
</body>
<script type="text/javascript">

$(document).ready(function(){
	
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
});

function <%=formName %>reset(){
	$('#<%=formName %> #serviceName').val('');$('#<%=formName %> #serviceName').attr('value', '');
	
}

</script>

</html>