<%@page import="com.cms.cms_package.handler.PackageCreationController"%>
<%@page import="com.cms.service.handler.ServiceCreationController"%>
<%@page import="com.cms.task.config.handler.TaskConfigCreationController"%>
<%@page import="com.cms.holiday.handler.HolidayTypeCreationController"%>
<%@page import="com.application.util.AppDateUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cms.common.search.SearchEnum"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Random"%>
  

  
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

   
   
   String formName="cust_bk"+Math.abs( new Random().nextInt(9999));
   %>
 
 
 <div class="container-fluid">
      <!-- Start Page Content -->
      <div class="row">
          <div class="col-12">
              <div class="card">
                  <div class="card-body">
                  	<h4 class="card-title">Customer Booking Search
                  		<button type="button" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="customerBooking?action=add" class="btn btn-primary btn-sm float-right btn-rounded" style="">
                   		<i class="fa fa-plus"></i> 
                   		ADD
                   	</button>
                  	</h4>
                      <form id="<%=formName %>" class="form p-t-20" action="customerBooking?action=search" method="post">
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
                                <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Contact Person</label>
                                <div class="col-sm-8">
                                 <input type="text" id="contactPerson" class="form-control singledate" placeholder="Contact Person" name="contactPerson" value="<%=contactPerson%>">
                                </div>
                            </div>
                      		</div>
                      	</div>
                      	
                      	
                      	<div class="row">
                      		<div class="col-sm-6">
                      			<div class="form-group row">
                                <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Service Name</label>
                                <div class="col-sm-8">
                                     <select id="serviceName" class="form-control select2" placeholder="service Name" name="serviceName">
															<option></option>
															<%=ServiceCreationController.serviceOption("", ""+serviceName)%>
														</select>
                                </div>
                            </div>
                      		</div>
                      		
                      		
                      		<div class="col-sm-6">
                      			<div class="form-group row">
                                <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Package Name</label>
                                <div class="col-sm-8">
                                <select id="packageName" class="form-control select2" placeholder="Package Name" name="packageName">
															<option></option>
								<%=PackageCreationController.packageOption("", ""+packageName)%>  
								                  </select>            </div>
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
                      <h4 class="card-title">Customer Booking List </h4>
                  </div>
                  <form id="<%=formName %>_tble" class="form" action="#" method="post">
                   <div class="table-responsive">
                       <table class="table table-bordered text-center">
                           <thead>
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
      <!-- End PAge Content -->
  </div>
 
   
<script type="text/javascript">

$(document).ready(function(){
	
});

function <%=formName %>reset(){
	$('#<%=formName %> #customerName').val('');$('#<%=formName %> #customerName').attr('value', '');
	$('#<%=formName %> #contactPerson').val('');$('#<%=formName %> #contactPerson').attr('value', '');
	$('#<%=formName%> #packageName option:selected').removeAttr("selected");	$('#<%=formName%> #packageName option:selected').prop("selected",false);
	$('#<%=formName%> #serviceName option:selected').removeAttr("selected");	$('#<%=formName%> #serviceName option:selected').prop("selected",false);
}


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

