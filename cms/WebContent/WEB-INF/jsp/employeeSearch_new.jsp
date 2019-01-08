<%@page import="com.cms.employee.handler.EmployeeCreationHandler"%>
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
   
   <!-- header -->
   
  <div class="row">
   <div class="col-12">
    <div class="card">
   <div   class="card-body" style="margin-bottom: 2px solid;color: black;!important;">
      
     
      <div class="row">  
      <div class="col-2" id="Users">Users</div>
      <div class="col-2" id="Groups">Groups</div>
      <div class="col-2" id="Activate_Users">Activate Users</div>
      </div>
      </div>
         
	</div>
     </div>
     </div>
   <%
   List<Map<String, Object>> resultList=(List<Map<String, Object>>)resultMap.get( SearchEnum.RESULT_LIST.getKeyName() );
	if(resultList==null){ resultList= new ArrayList<Map<String, Object>>();  }
   
   int user_count =0;
   user_count = resultList.size();
   
   %>
   
                <!-- Start Page Content -->
                <div class="row">
                    <div class="col-6">
                        <div class="card">
                            <div class="card-body">
                            	<h4 class="card-title">Active Users(<%=user_count%>)
                            		<button type="button" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="employee?action=add_new" class="btn btn-primary btn-sm float-right btn-rounded" style="">
	                            		<i class="fa fa-plus"></i> 
	                            		New User
	                            	</button>
                            	</h4>
                                 <form class="form p-t-20" id="<%=formName %>" action="employee" method="post">
                                 <input type="hidden" name="action" value="search">
                                	<div class="row">
                                		<div class="col-sm-12">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-6 p-t-5  control-label col-form-label">Active Users</label>
		                                        <div class="col-sm-6">
		                                            <input type="text" id="employeeName" class="form-control form-control-sm" placeholder="Employee Name" name="employeeName" value="<%=employeeName%>" maxlength="25">
		                                        </div>
		                                    </div>
                                		</div>
                                	
                                	</div>
                                  
                                   <div class="table-responsive click_user" style="overflow-y:auto;overflow-x:unset; cursor:pointer;">
	                                
	                                 <ul>
	                                    <%
										
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
											<li>
											<div class="row" style="margin-bottom: 20px !important;">
											
												<div class="col-sm-1"><input type="checkbox" id="emp_id_<%=emp_id%>"/></div>												
												<div class="col-sm-2"><div class="user-pic"><img src="./static/assets/images/users/d2.jpg" alt="users" class="rounded-circle" width="40"></div></div>
												<div class="col-sm-9" style=""><b><%=emp_name %></b><br/><%=s_designation %><br/><%=s_email  %></div>										
												
												<%-- 
												<div class="col-sm-3">
													<a data-target="#CMS-POPUP-MODEL" data-toggle="modal"  data-url="employee?action=edit&employeeId=<%=emp_id%>" href="#">Edit</a> &nbsp;&nbsp;
													<a class='<%=formName %>_delete' href="javascript:;" ahref="employee?action=delete&employeeId=<%=emp_id%>">delete</a>&nbsp;&nbsp;
												<a data-target="#CMS-POPUP-DISPLAY" data-toggle="modal"  data-url="employee?action=view&employeeId=<%=emp_id%>" href="#">View</a> &nbsp;&nbsp;
												</div> --%>
												
												</div>
											</li>
										<%sno++;
										} %>
	                                  
	                                </ul>
	                            </div>
                                  
                                </form>
                            </div>
                        </div>
                    </div>
   
   
   
   <div class="col-6" id="view_users" style="display:none;">
   
                        <div class="card">
                            <div class="card-body" style="float:right">
                               <button type="button" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="employee?action=add_new" class=" btn-sm float-right" style="color:red;background-color:#FFFFFF;border:red solid 1px;">
	                            		Deactivate User
	                            	</button>
                            </div>
                            <form id="<%=formName %>_tble" class="form" action="#" method="post">
	                            <div class="table-responsive" style="overflow-y:auto;overflow-x:unset;">
	                            <div class="row" style="margin-bottom: 20px !important;">											
												<div class="col-sm-2"><div class="user-pic"><img src="./static/assets/images/users/d2.jpg" alt="users" class="rounded-circle" width="40"></div></div>
												<div class="col-sm-8" style=""><b>Jesu</b><br/><br/>Jesu.itsoft@gmail.com</div>										
								</div>
								
								
								<div id="single_emp_view_toggle" Myattr="show" style="cursor:pointer;"><span style="color:blue;">Show Details</span></div><br/>
								
								<div id="single_emp_view" style="display:none;">	
								
								<div class="row">
                                		<div class="col col-sm-12">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-6 p-t-5  control-label col-form-label"><spna style="font-weight:bold;">User Information</spna></label>
		                                    </div>
                                		</div>
                                	
                                	</div>
								
								
															
								<div class="row">						
			                  	<div class="col-sm-12">
			                  			<div class="form-group row">
			                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">First Name</label>
			                            	<div class="col-sm-9">
			                                	<!-- <input type="text" id="profile" class="form-control form-control-sm jval_name" placeholder="Profile" name="profile" value="" required="required" maxlength="80"> -->
			                            	</div>
			                        	</div>
			                  		</div>  	
								</div>
								
								<div class="row">						
			                  	<div class="col-sm-12">
			                  			<div class="form-group row">
			                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Last Name</label>
			                            	<div class="col-sm-9">
			                                	<!-- <input type="text" id="profile" class="form-control form-control-sm jval_name" placeholder="Profile" name="profile" value="" required="required" maxlength="80"> -->
			                            	</div>
			                        	</div>
			                  		</div>  	
								</div>
								<div class="row">						
			                  	<div class="col-sm-12">
			                  			<div class="form-group row">
			                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Email</label>
			                            	<div class="col-sm-9">
			                                	<!-- <input type="text" id="profile" class="form-control form-control-sm jval_name" placeholder="Profile" name="profile" value="" required="required" maxlength="80"> -->
			                            	</div>
			                        	</div>
			                  		</div>  	
								</div>
								<div class="row">						
			                  	<div class="col-sm-12">
			                  			<div class="form-group row">
			                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Role</label>
			                            	<div class="col-sm-9">
			                                	<!-- <input type="text" id="profile" class="form-control form-control-sm jval_name" placeholder="Profile" name="profile" value="" required="required" maxlength="80"> -->
			                            	</div>
			                        	</div>
			                  		</div>  	
								</div>
								<div class="row">						
			                  	<div class="col-sm-12">
			                  			<div class="form-group row">
			                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Profile</label>
			                            	<div class="col-sm-9">
			                                	<!-- <input type="text" id="profile" class="form-control form-control-sm jval_name" placeholder="Profile" name="profile" value="" required="required" maxlength="80"> -->
			                            	</div>
			                        	</div>
			                  		</div>  	
								</div>
								
								<div class="row">						
			                  	<div class="col-sm-12">
			                  			<div class="form-group row">
			                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Alias</label>
			                            	<div class="col-sm-9">
			                                	<!-- <input type="text" id="profile" class="form-control form-control-sm jval_name" placeholder="Profile" name="profile" value="" required="required" maxlength="80"> -->
			                            	</div>
			                        	</div>
			                  		</div>  	
								</div>
								<div class="row">						
			                  	<div class="col-sm-12">
			                  			<div class="form-group row">
			                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Phone</label>
			                            	<div class="col-sm-9">
			                                	<!-- <input type="text" id="profile" class="form-control form-control-sm jval_name" placeholder="Profile" name="profile" value="" required="required" maxlength="80"> -->
			                            	</div>
			                        	</div>
			                  		</div>  	
								</div>
								<div class="row">						
			                  	<div class="col-sm-12">
			                  			<div class="form-group row">
			                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Mobile</label>
			                            	<div class="col-sm-9">
			                                	<!-- <input type="text" id="profile" class="form-control form-control-sm jval_name" placeholder="Profile" name="profile" value="" required="required" maxlength="80"> -->
			                            	</div>
			                        	</div>
			                  		</div>  	
								</div>
								<div class="row">						
			                  	<div class="col-sm-12">
			                  			<div class="form-group row">
			                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Website</label>
			                            	<div class="col-sm-9">
			                                	<!-- <input type="text" id="profile" class="form-control form-control-sm jval_name" placeholder="Profile" name="profile" value="" required="required" maxlength="80"> -->
			                            	</div>
			                        	</div>
			                  		</div>  	
								</div>
								<div class="row">						
			                  	<div class="col-sm-12">
			                  			<div class="form-group row">
			                            	<label for="email" class="col-sm-3 p-t-5  control-label col-form-label">Fax</label>
			                            	<div class="col-sm-9">
			                                	<!-- <input type="text" id="profile" class="form-control form-control-sm jval_name" placeholder="Profile" name="profile" value="" required="required" maxlength="80"> -->
			                            	</div>
			                        	</div>
			                  		</div>  	
								</div>
								
								
								</div>
								
								
								
	                            
	                            </div>
	                           
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
				employeeName :{
					lettersonly: true, 
					minlength:{
						depends: function(ele){ return !$.isEmptyObject(ele); },
		                param: 3
					}
				},
				qualification:{
					lettersonly: true, 
					minlength:{
						depends: function(ele){ return !$.isEmptyObject(ele); },
		                param: 3
					}
				},
				state:{
					lettersonly: true, 
					minlength:{
						depends: function(ele){ return !$.isEmptyObject(ele); },
		                param: 3
					}
				},
				city:{
					lettersonly: true, 
					minlength:{
						depends: function(ele){ return !$.isEmptyObject(ele); },
		                param: 3
					}
				},
				pinCode:{
					number: true
					/* minlength:{
						depends: function(ele){ return !$.isEmptyObject(ele); },
		                param: 3 
					} */
				},
				mobile:{
					number: true,
					minlength:{
						depends: function(ele){ return !$.isEmptyObject(ele); },
		                param: 3
					}
				},
				email:{
					email: true,
					/* minlength:{
						depends: function(ele){ return !$.isEmptyObject(ele); },
		                param: 3
					} */
				},
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
	
	$('#<%=formName %>_department').change(function(){
		loadDepartment( $(this).val(), $('#<%=formName %>_designation') );
	});
	
	
	$("#<%=formName %> .click_user").on("click",function(){
		console.log("hiii");
		$("#view_users").show();
	});
	
	$("#<%=formName %>_tble #single_emp_view_toggle").on("click",function(){
		var id= this.id;
		var disp_mode = $("#"+id).attr("Myattr");
	console.log("disp_mode==>"+disp_mode);
		if(disp_mode=="show"){
			$("#single_emp_view").show();
			$("#single_emp_view_toggle").html("<span style='color:blue;'>Hide Details</span>");
			$("#single_emp_view_toggle").attr("Myattr","hide");
			
		}else
			{
				$("#single_emp_view").hide();
				$("#single_emp_view_toggle").html("<span style='color:blue;'>Show Details</span>");
				$("#single_emp_view_toggle").attr("Myattr","show");
			}
		
	});
	
	
});


function <%=formName %>reset(){
	
	$('#<%=formName %> #employeeName').val(''); $('#<%=formName %> #employeeName').attr('value', '');
	$('#<%=formName %>_department').select2("val", [""]);
	$('#<%=formName %>_designation').select2("val", [""]);
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
		if(data.errorExists==true)
		{
		}
		else{
			$(trobj).closest("tr").remove();
		}
		alert(data.message);
	});
	
	}
});
</script>
