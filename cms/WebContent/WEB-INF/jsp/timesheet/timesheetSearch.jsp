<%@page import="com.application.util.AppDateUtil"%>
<%@page import="com.cms.employee.handler.EmployeeCreationHandler"%>
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
   
   String taskName=AppUtil.getNullToEmpty( requestMap.get("taskName") );
   String assignedTo=AppUtil.getNullToEmpty( requestMap.get("assignedTo") );
   String taskType=AppUtil.getNullToEmpty( requestMap.get("taskType") );
   String timeSheetFrom=AppUtil.getNullToEmpty( requestMap.get("timeSheetFrom") );
   String timeSheetTo=AppUtil.getNullToEmpty( requestMap.get("timeSheetTo") );
   
   
   String formName="tim_sht_frm_"+Math.abs( new Random().nextInt(9999));
   %>

<div class="container-fluid">
      <!-- Start Page Content -->
      <div class="row">
          <div class="col-12">
              <div class="card">
                  <div class="card-body">
                  	<h4 class="card-title">TIMESHEET SEARCH
                  		<button type="button" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="timesheet?action=add" class="btn btn-primary btn-sm float-right btn-rounded" style="">
                   		<i class="fa fa-plus"></i> 
                   		ADD
                   	</button>
                  	</h4>
                      <form id="<%=formName %>" class="form p-t-20" action="timesheet?action=search" method="post">
                      	<div class="row">
                      		<div class="col-sm-6">
                      			<div class="form-group row">
	                                <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Task Name</label>
	                                <div class="col-sm-8">
	                                    <input type="text" name="taskName" class="form-control form-control-sm" id="taskName" value="<%=taskName %>" placeholder="Task Name">
	                                </div>
	                            </div>
                      		</div>
                      		<div class="col-sm-6">
                  				<div class="form-group row">
                               		<label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Assigned To</label>
                               		<div class="col-sm-8">
                                   		<select class="select2 form-control" name="assignedTo" id="assignedTo" style="width: 100%; height:26px;">
                              				<option <%=assignedTo.isEmpty()|| assignedTo.equals("0")?"selected":"" %> disabled="disabled">-Please Select-</option>
                           					<%=EmployeeCreationHandler.formEmployeeOption(assignedTo, "") %>
                      					</select>
                               		</div>
                       			</div>
                     		</div>
                      	</div>
                      	<div class="row">
                      		<div class="col-sm-6">
                  				<div class="form-group row">
                               		<label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Task Type</label>
                               		<div class="col-sm-8">
                                   		<select class="select2 form-control" name="taskType" id="taskType" style="width: 100%; height:26px;">
                              				<option <%=taskType.isEmpty()?"selected":"" %> disabled="disabled" value="">-Please Select-</option>
                           					<option <%=taskType.equalsIgnoreCase("general")?"selected":"" %> value="general">General</option>
                           					<option <%=taskType.equalsIgnoreCase("customer")?"selected":"" %> value="customer">Customer</option>
                      					</select>
                               		</div>
                       			</div>
                     		</div>
                      		<div class="col-sm-6">
                      			<div class="form-group row">
	                                <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Time Sheet Date</label>
	                                <div class="col-sm-4">
	                                    <input type="text" name="timeSheetFrom" class="form-control form-control-sm date_picker" id="timeSheetFrom" value="<%=timeSheetFrom %>" placeholder="From">
	                                </div>
	                                <div class="col-sm-4">
	                                    <input type="text" name="timeSheetTo" class="form-control form-control-sm date_picker" id="timeSheetTo" value="<%=timeSheetTo %>" placeholder="TO">
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
                      <h4 class="card-title">TIME SHEET LIST</h4>
                  </div>
                  <form id="<%=formName %>_tble" class="form" action="timesheet?action=approval" method="post">
                   <div class="table-responsive">
                       <table class="table table-bordered text-center">
                           <thead>
                               <tr>
                                   <th scope="col">#</th>
                                   <th><label class=""><input type="checkbox" id="<%=formName %>idAll"></label> </th>
                                   <th scope="col">Start Time</th>
                                   <th scope="col">End Time</th>
                                   <th scope="col">Shift Name</th>
                                   <th scope="col">Assigned To</th>
                                   <th scope="col">Status</th>
                                   <th scope="col">Approved By</th>
                                   <th scope="col">Approved On</th>
                                   <th scope="col">Action</th>
                               </tr>
                           </thead>
                           <tbody>
                           <%
							List<Map<String, Object>> resultList=(List<Map<String, Object>>)resultMap.get( SearchEnum.RESULT_LIST.getKeyName() );
							if(resultList!=null && !resultList.isEmpty()){ 
								int sno=1;
								for(Map<String, Object> searchData:resultList){
									/*  '1000-01-01' AS start_date, '1000-01-01' AS end_date, a.time_sheet_id, a.assigned_to, b.first_name AS assigned_to_name,
									a.shift_id, '' AS shift_name, a.status, a.approved_by, c.first_name AS approved_by_name, a.approved_on
									*/
									
									int time_sheet_id=AppUtil.getNullToInteger( (String)searchData.get("COL#1") );
									String start_date=AppDateUtil.convertToAppDate((String)searchData.get("COL#2"), true, true); if(start_date.contains("1000")){ start_date=""; }
									String end_date=AppDateUtil.convertToAppDate((String)searchData.get("COL#3"), true, true); if(end_date.contains("1000")){ end_date=""; }
									int assigned_to=AppUtil.getNullToInteger( (String)searchData.get("COL#4") );
									String assigned_to_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#5") );
									int shift_id=AppUtil.getNullToInteger( (String)searchData.get("COL#6") );
									String shift_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#7") );
									String status=AppUtil.getNullToEmpty( (String)searchData.get("COL#8") );
									String approved_by=AppUtil.getNullToEmpty( (String)searchData.get("COL#9") );
									String approved_by_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#10") );
									String approved_on=AppDateUtil.convertToAppDate((String)searchData.get("COL#11"), true, true); if(approved_on.contains("1000")){ approved_on=""; }
								%>
									<tr>
										<td scope="row"><%=sno %></td>
										<td>
										<%if(!status.equalsIgnoreCase("approved")){ %>
										<label class=""><input type="checkbox" name="ids" class="<%=formName %>ids" value="<%=time_sheet_id%>"></label>
										<%} %>
										</td>
										<td><%=start_date  %></td>
										<td><%=end_date  %></td>
										<td><%=shift_name  %></td>
										<td><%=assigned_to_name  %></td>
										<td><%=status  %></td>
										<td><%=approved_by_name  %></td>
										<td><%=approved_on  %></td>
										<td>
											<a data-target="#CMS-POPUP-MODEL" data-toggle="modal"  data-url="timesheet?action=edit&timesheetId=<%=time_sheet_id%>" href="#">Edit</a> &nbsp;&nbsp;
											<a class='<%=formName %>_delete' href="javascript:;" ahref="timesheet?action=delete&timesheetId=<%=time_sheet_id%>">delete</a></td>
									</tr>
								<%sno++;
								} 
							}else{ %>
							<tr><td colspan="20"><h5>No Record Found..!</h5></td></tr>
							<%} %>
                           </tbody>
                       </table>
                   </div>
                   <button type="submit" id="<%=formName %>_btn_approve" class="btn btn-success m-r-10 m-t-10 float-right" style="display: none;">Map Rights</button>
                  </form>
              </div>
          </div>
      </div>
      <!-- End PAge Content -->
  </div>

<script type="text/javascript">

$(document).ready(function(){
	initPage();
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
				 		initPage();
					}
				}); 
			}
		});
		
	}catch(e){
		alert('Something went wrong. Please Try Later..!');
	}
	
	
	try{		
		$('#<%=formName %>_tble').validate({
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
	
	
	
	$('#<%=formName %>_tble').on('click', '#<%=formName %>idAll', function(){
		if($(this).is(':checked')){
			$('.<%=formName %>ids').prop('checked', true);
		}else{
			$('.<%=formName %>ids').prop('checked', false);
		}
		<%=formName%>_approvelBtnDiaplay();
	});
	
	$('#<%=formName %>_tble').on('click', '.<%=formName %>ids', function(){
		var total=$('.<%=formName %>ids').length;
		var checked=$('.<%=formName %>ids:checked').length;
		
		if(total!=0){
			if(checked==total){
				$('#<%=formName %>idAll').prop('checked', true);
			}else{
				$('#<%=formName %>idAll').prop('checked', false);
			}
		}
		<%=formName%>_approvelBtnDiaplay();
	});
	
	
	
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

function <%=formName%>_approvelBtnDiaplay(){
	if($('.<%=formName %>ids:checked').length > 0){
		$('#<%=formName %>_btn_approve').show();
	}else{
		$('#<%=formName %>_btn_approve').hide();
	}
}

function <%=formName %>reset(){
	$('#<%=formName %> #serviceName').val('');$('#<%=formName %> #serviceName').attr('value', '');
	
}
</script>

