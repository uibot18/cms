<%@page import="com.cms.task.config.handler.TaskConfigCreationController"%>
<%@page import="com.cms.customer.handler.CustomerCreationController"%>
<%@page import="com.cms.employee.handler.EmployeeCreationHandler"%>
<%@page import="com.cms.employee.controller.EmployeeController"%>
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
    
   <%
   Map<String, String> requestMap= (Map<String,String>)request.getAttribute( SearchEnum.REQUEST_MAP.getKeyName() );
   if(requestMap==null){ requestMap=new HashMap<String, String>(); }

   Map<String, Object> resultMap=(Map<String, Object>)request.getAttribute( SearchEnum.RESULT_MAP.getKeyName() );
   if(resultMap==null){ resultMap=new HashMap<String, Object>(); }
   
   String customerName=AppUtil.getNullToEmpty( requestMap.get("customerName") );
   String taskName=AppUtil.getNullToEmpty( requestMap.get("taskName") );
   String taskCode=AppUtil.getNullToEmpty( requestMap.get("taskCode") );
   String taskStatus=AppUtil.getNullToEmpty( requestMap.get("taskStatus") );
   String assignedTo=AppUtil.getNullToEmpty( requestMap.get("assignedTo") );
   String taskType=AppUtil.getNullToEmpty( requestMap.get("taskType") );
   String taskDateFrom=AppUtil.getNullToEmpty( requestMap.get("taskDateFrom") );
   String taskDateTo=AppUtil.getNullToEmpty( requestMap.get("taskDateTo") );
   
   String formName="Proc_frm_"+Math.abs( new Random().nextInt(9999));
   Map<String, String> statusMap=new HashMap<String, String>(){
	   {
		  put("pending", "Pending");
		  put("open", "Open");
		  put("started", "Started");
		  put("process", "Process");
		  put("clarification", "Clarification");
		  put("close", "Close");
		  put("pause", "Pause");
	   }
   };
   
   %>
    
    
    <div class="container-fluid">
                <!-- Start Page Content -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                            	<h4 class="card-title">TASK SEARCH
	                            	<%-- <button type="button" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="task?action=add&taskType=<%=TaskType.GeneralTask.getType()%>" class="btn btn-primary btn-sm float-right btn-rounded" style="">
	                            		<i class="fa fa-plus"></i> 
	                            		General Task
	                            	</button> --%>
                            	</h4>
                                <form id="<%=formName %>" class="form p-t-20" action="task?action=search" method="post">
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Customer Name</label>
		                                        <div class="col-sm-8">
		                                            <select class="select2 form-control" name="customerName" id="customerName" style="width: 100%; height:26px;">
					                                    <option></option>
					                                    <%=CustomerCreationController.customerOption(customerName) %>
					                                </select>
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Task Name</label>
		                                        <div class="col-sm-8">
		                                            <select class="select2 form-control" name="taskName" id="taskName" style="width: 100%; height:26px;">
					                                    <option></option>
					                                    <%=TaskConfigCreationController.taskOption(taskName, "") %>
					                                </select>
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Code</label>
		                                        <div class="col-sm-8">
		                                            <input type="text" name="taskCode" class="form-control form-control-sm" id="serviceName" value="<%=taskCode %>" placeholder="Code">
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Task Date</label>
		                                        <div class="col-sm-4">
		                                            <input type="text" name="taskDateFrom" class="form-control form-control-sm date_picker" id="serviceName" value="<%=taskDateFrom %>" placeholder="From">
		                                        </div>
		                                        <div class="col-sm-4">
		                                            <input type="text" name="taskDateTo" class="form-control form-control-sm date_picker" id="serviceName" value="<%=taskDateTo %>" placeholder="To">
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Assigned To</label>
		                                        <div class="col-sm-8">
		                                            <select class="select2 form-control" name="assignedTo" id="assignedTo" style="width: 100%; height:26px;">
					                                    <option></option>
					                                    <%=EmployeeCreationHandler.formEmployeeOption(assignedTo, "") %>
					                                </select>
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Task type</label>
		                                        <div class="col-sm-8">
		                                            <select class="select2 form-control" name="taskType" id="taskType" style="width: 100%; height:26px;" taskType="<%=taskType%>">
					                                    <option></option>
					                                    <%for(TaskType tType : TaskType.values()){%>
					                                    	<option <%=tType.getType().equalsIgnoreCase(taskType)?"selected":"" %> value="<%=tType.getType()%>"><%=tType.getType() %></option>
					                                    <%} %>
					                                </select>
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Task Status</label>
		                                        <div class="col-sm-8">
		                                            <select class="select2 form-control" name="taskStatus" id="taskStatus" style="width: 100%; height:26px;">
					                                    <option></option>
					                                    <%=AppUtil.formOption(statusMap, taskStatus) %>
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
                                <h4 class="card-title">Task List</h4>
                            </div>
                            <form id="<%=formName %>_tble" class="form" action="taskProcess?action=approveTaskProcess" method="post">
	                            <div class="table-responsive">
	                                <table class="table table-bordered text-center">
	                                    <thead>
	                                        <tr>
	                                            <th scope="col">S.No</th>
	                                            <%-- <th><label class=""><input type="checkbox" id="<%=formName %>processIdAll"></label> </th> --%>
												<th scope="col">Code</th>
												<th scope="col">Start Date</th>
												<th scope="col">End Date</th>
												<th scope="col">Task Name</th>
												<!-- <th scope="col">Priority</th> -->
												<th scope="col">Status</th>
												<th scope="col">Assign To</th>
												<th scope="col">Action</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                    <%
										List<Map<String, Object>> resultList=(List<Map<String, Object>>)resultMap.get( SearchEnum.RESULT_LIST.getKeyName() );
										if(resultList==null){ resultList= new ArrayList<Map<String, Object>>();  }
										int sno=1;
										for(Map<String, Object> searchData:resultList){
											//a.task_id, a.task_date_from, a.task_date_to, a.task_config_id, b.task_config_name, a.assigned_to, c.first_name
											int task_id=AppUtil.getNullToInteger( (String)searchData.get("COL#1") );
											String task_date_from=AppUtil.getNullToEmpty( (String)searchData.get("COL#2") );
											String task_date_to=AppUtil.getNullToEmpty( (String)searchData.get("COL#3") );
											int task_config_id=AppUtil.getNullToInteger( (String)searchData.get("COL#4") );
											String task_config_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#5") );
											int assigned_to=AppUtil.getNullToInteger( (String)searchData.get("COL#6") );
											String assigned_to_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#7") );
											String task_status=AppUtil.getNullToEmpty( (String)searchData.get("COL#8") );
											
										%>
											<tr>
												<td scope="row"><%=sno %></td>
												<%-- <td>
													<label class=""><input type="checkbox" name="processIds" class="<%=formName %>processIds" value="<%=process_master_id%>"></label> 
												</td> --%>
												<td>
													<a data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="task?action=display&taskId=<%=task_id%>" href="#"><%=task_id %></a> 
												</td>
												<td><%=task_date_from %></td>
												<td><%=task_date_to %></td>
												<td><%=task_config_name %></td>
												<%-- <td><%=priority %></td> --%>
												<td><%=task_status %></td>
												<td><%=assigned_to_name %></td>
												<td>
												<%if(task_status.equalsIgnoreCase("pending")){ %>
													<a data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="task?action=edit&taskId=<%=task_id%>" href="#">Edit</a> &nbsp;&nbsp;
													<a data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="task?action=questionnaire&taskId=<%=task_id%>" href="#">Questionnaire</a> &nbsp;&nbsp;
													<a class="btn_taskAction" data-type="start" href="javascript:;" ahref="task?action=taskAction&type=start&taskId=<%=task_id%>">start</a> &nbsp;
													
													<%-- <a href="taskConfig?action=delete&taskConfigId=<%=task_config_id%>">delete</a>&nbsp;&nbsp; --%>
												<%} %>
												<%if(task_status.equalsIgnoreCase("started")){ %>
													<a class="btn_taskAction" data-type="start" href="javascript:;" ahref="task?action=taskAction&type=complete&taskId=<%=task_id%>">complete</a> &nbsp;
												<%} %>
											
											</tr>
										<%sno++;
										} %>
	                                    </tbody>
	                                </table>
	                            </div>
	                            <button type="submit" id="<%=formName %>_btn_approve" class="btn btn-success m-r-10 m-t-10 float-right" style="display: none;">Approve</button>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- End PAge Content -->
            </div>
    
<script type="text/javascript">

$(document).ready(function(){
	
	$('.date_picker').datepicker({
		autoclose:true,
		todayBtn:'linked',
		todayHighlight:true,
		format:'dd/mm/yyyy'
	}); 
	
	try{		
		$('#<%=formName%>').validate({
			errorClass: 'invalid',
			validClass: 'valid',
			errorPlacement: function(error, element) {
				error.insertAfter(element);
			},
			rules: {
			},
			messages: {
			},
			submitHandler: function(form) {
				$.ajax({
					url:$(form).attr('action'),
					data:$(form).serialize(),
					beforeSend:function(){
						$('#CMS-PAGE-CONTAINER').html('<center> <img alt="" src="./resource/img/loader.gif"></center>');
					},
					success:function(data){
				 		$('#CMS-PAGE-CONTAINER').html(data);
					}
				}); 
			}
		});
		
	}catch(e){
		alert('Something went wrong. Please Try Later..!');
	}
	
	<%-- try{		
		$('#<%=formName%>_tble').validate({
			errorClass: 'invalid',
			validClass: 'valid',
			errorPlacement: function(error, element) {
				error.insertAfter(element);
			},
			rules: {
				processIds: { required: true }
			},
			messages: {
				processIds: { required: 'Please Select' }
			},
			submitHandler: function(form) {
				/* $.ajax({
					url:$(form).attr('action'),
					data:$(form).serialize(),
					beforeSend:function(){
						$('#CMS-POPUP-MODEL').html('<center> <img alt="" src="./resource/img/loader.gif"></center>');
					},
					success:function(data){
				 		$('#CMS-POPUP-MODEL').html(data);
					}
				});  */
				alert("1");
				$(form).submit();
				alert("2");
			}
		});
		
	}catch(e){
		alert('Something went wrong. Please Try Later..!');
	} --%>
	
	
	
	$('#<%=formName %>_tble').on('click', '.btn_taskAction', function(){

		var thisObj=$(this);
		var urlQry=$(this).attr('ahref');
		var type=$(this).attr('data-type');
		$.getJSON(urlQry, function(data){
			if(data.errorExists==false){
				if(type=='start'){
					/* $(thisObj).text('Complete');
					$(thisObj).attr('data-type', 'complete'); */
					$('#<%=formName %>').find('button[type="submit"]').click();
				}else if(type=='complete'){
					$(thisObj).remove();
				}
				
				
			}else{
				alert(data.message);
			}
		});

	});
	
	
});


function <%=formName %>reset(){
	$('#<%=formName %> #serviceName').val('');$('#<%=formName %> #serviceName').attr('value', '');
	
}

</script>
