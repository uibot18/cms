<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Random"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="com.cms.common.search.SearchEnum"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
  
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
   
   <div class="container-fluid">
                <!-- Start Page Content -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                            	<h4 class="card-title">Employee Search
                            		<button type="button" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="employee?action=add" class="btn btn-primary btn-sm float-right btn-rounded" style="">
	                            		<i class="fa fa-plus"></i> 
	                            		ADD
	                            	</button>
                            	</h4>
                                 <form class="form p-t-20" id="<%=formName %>" action="employee" method="post">
                                 <input type="hidden" name="action" value="search">
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Employee Name</label>
		                                        <div class="col-sm-8">
		                                            <input type="text" id="employeeName" class="form-control" placeholder="Employee Name" name="employeeName" value="<%=employeeName%>">
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Department</label>
		                                        <div class="col-sm-8">
		                                           <input type="text" id="department" class="form-control" placeholder="Department" name="department" value="<%=department%>">
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                    <div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Designation</label>
		                                        <div class="col-sm-8">
		                                          <input type="text" id="designation" class="form-control" placeholder="designation" name="designation" value="<%=designation%>">
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Qualification</label>
		                                        <div class="col-sm-8">
		                                            <input type="text" id="qualification" class="form-control" placeholder="Qualification" name="qualification" value="<%=qualification%>">
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
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">City</label>
		                                        <div class="col-sm-8">
		                                            <input type="text" id="city" class="form-control" placeholder="City" name="city" value="<%=city%>">
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
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Email</label>
		                                        <div class="col-sm-8">
		                                         <input type="text" id="email" class="form-control" placeholder="Email" name="email" value="<%=email%>">
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
                <!-- End PAge Content -->
            </div>
   
   
<script type="text/javascript">
$(document).ready(function(){
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
