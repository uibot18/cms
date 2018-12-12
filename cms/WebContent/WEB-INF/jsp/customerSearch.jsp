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

<div class="container-fluid">
                <!-- Start Page Content -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                        	<div class="card-header bg-danger">
                            	<h4 class="m-b-0 text-white">CUSTOMER SEARCH
                            		<button type="button" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="customer?action=add" class="btn btn-light btn-sm float-right btn-rounded" style="">
	                            		<i class="fa fa-plus"></i> 
	                            		ADD
	                            	</button>
	                            	<button type="button" data-toggle="modal" data-target=".bs-example-modal-lg" class="m-r-1 btn btn-light btn-sm float-right btn-rounded" style="">
	                            		<i class="fa fa-eye"></i> 
	                            		VIEW
	                            	</button> 
                            	</h4>
                           	</div>
                            <div class="card-body">
                                 <form id="<%=formName %>" class="form" action="customer" method="post">
			          mj          <input type="hidden" name="action" value="search">
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
                                <h4 class="card-title">Customer List</h4>
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
   <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
       <div class="modal-dialog modal-lg">
           <div class="modal-content">
               <div class="modal-header">
                   <h4 class="modal-title" id="myLargeModalLabel">Task Configuration Display</h4>
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
               </div>
               <div class="modal-body">
               		<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-4 p-t-5  control-label col-form-label">Customer Name <b class="float-right"> :</b></label>
		                                        <div class="col-sm-7 p-t-5">
		                                             <p class="form-control-static m-b-0"> 11/06/1987 </p>
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-4 p-t-5  control-label col-form-label">Contact Person Name <b class="float-right"> :</b></label>
		                                        <div class="col-sm-7 p-t-5">
		                                             <p class="form-control-static m-b-0"> 11/06/1987 </p>
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-4 p-t-5  control-label col-form-label">Door No <b class="float-right"> :</b></label>
		                                        <div class="col-sm-7 p-t-5">
		                                             <p class="form-control-static m-b-0"> 11/06/1987 </p>
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-4 p-t-5  control-label col-form-label">Street Name <b class="float-right"> :</b></label>
		                                        <div class="col-sm-7 p-t-5">
		                                             <p class="form-control-static m-b-0"> 11/06/1987 </p>
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                    <div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-4 p-t-5  control-label col-form-label">City <b class="float-right"> :</b></label>
		                                        <div class="col-sm-7 p-t-5">
		                                             <p class="form-control-static m-b-0"> 11/06/1987 </p>
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-4 p-t-5  control-label col-form-label">State <b class="float-right"> :</b></label>
		                                        <div class="col-sm-7 p-t-5">
		                                             <p class="form-control-static m-b-0"> 11/06/1987 </p>
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-4 p-t-5  control-label col-form-label">Land Mark <b class="float-right"> :</b></label>
		                                        <div class="col-sm-7 p-t-5">
		                                             <p class="form-control-static m-b-0"> 11/06/1987 </p>
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-4 p-t-5  control-label col-form-label">Pin Code <b class="float-right"> :</b></label>
		                                        <div class="col-sm-7 p-t-5">
		                                             <p class="form-control-static m-b-0"> 11/06/1987 </p>
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-4 p-t-5  control-label col-form-label">Email <b class="float-right"> :</b></label>
		                                        <div class="col-sm-7 p-t-5">
		                                             <p class="form-control-static m-b-0"> 11/06/1987 </p>
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-4 p-t-5  control-label col-form-label">Web Site <b class="float-right"> :</b></label>
		                                        <div class="col-sm-7 p-t-5">
		                                             <p class="form-control-static m-b-0"> 11/06/1987 </p>
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>
                                	<div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-4 p-t-5  control-label col-form-label">PAN <b class="float-right"> :</b></label>
		                                        <div class="col-sm-7 p-t-5">
		                                             <p class="form-control-static m-b-0"> 11/06/1987 </p>
		                                        </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-4 p-t-5  control-label col-form-label">GST <b class="float-right"> :</b></label>
		                                        <div class="col-sm-7 p-t-5">
		                                             <p class="form-control-static m-b-0"> 11/06/1987 </p>
		                                        </div>
		                                    </div>
                                		</div>
                                	</div>    
               </div>
               <div class="modal-footer">
                   <button type="submit" class="btn btn-danger m-t-10 float-right"><i class="fas fa-times"></i> Cancel</button>
	               <button type="submit" class="btn btn-success m-r-10 m-t-10 float-right"><i class="fa fa-check"></i> Save</button>
               </div>
           </div>
           <!-- /.modal-content -->
       </div>
       <!-- /.modal-dialog -->
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
