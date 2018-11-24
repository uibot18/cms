<!DOCTYPE html>
<%@page import="com.cms.holiday.handler.HolidayTypeCreationController"%>
<%@page import="com.application.util.AppDateUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cms.common.search.SearchEnum"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Random"%>
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
    
    <!-- Main wrapper - style you can find in pages.scss -->
   
  
   <!-- Content start -->
   <%
   Map<String, String> requestMap= (Map<String,String>)request.getAttribute( SearchEnum.REQUEST_MAP.getKeyName() );
   if(requestMap==null){ requestMap=new HashMap<String, String>(); }

   Map<String, Object> resultMap=(Map<String, Object>)request.getAttribute( SearchEnum.RESULT_MAP.getKeyName() );
   if(resultMap==null){ resultMap=new HashMap<String, Object>(); }
   
   String holidayType=AppUtil.getNullToEmpty( requestMap.get("holidayType") );
   String holidayName=AppUtil.getNullToEmpty( requestMap.get("holidayName") );
   String holidayDate=AppUtil.getNullToEmpty( requestMap.get("holidayDate") );
   
   
   String formName="hlydy_frm_"+Math.abs( new Random().nextInt(9999));
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
                             
                              <h4 class="card-title">Holiday Search
                              
                              <button type="button" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="holiday?action=add" class="btn btn-primary btn-sm float-right btn-rounded" style="">
	                            		<i class="fa fa-plus"></i> 
	                            		ADD
	                            	</button>
                              </h4>
                          <form id="<%=formName %>" class="form p-t-20" action="holiday" method="post">     
                              <input type="hidden" name="action" value="search">
                                       <div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Holiday Type</label>
		                                        <div class="col-sm-8">
				                    			<select id="holidayType" class="form-control" placeholder="Holiday Type" name="holidayType">
															<option></option>
															<%=HolidayTypeCreationController.formHolidayTypeOption(holidayType)%>
													</select>
				                    
				                            </div>
		                                    </div>
                                		</div>
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Holiday Name</label>
		                                        <div class="col-sm-8">
                                		<input type="text" id="holidayName" class="form-control" placeholder="Holiday Name" name="holidayName" value="<%=holidayName%>">
                                		             </div>
		                                    </div>
                                		</div>
                                	</div>
                                	
                                	
                                	      <div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Date</label>
		                                        <div class="col-sm-8">
				                    			<input type="text" id="holidayDate" class="form-control date_picker input-sm " placeholder="Date" name="holidayDate" value="<%=holidayDate%>">
				                    
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
                                <h4 class="card-title">Holiday List</h4>
                            </div>
                            <form id="<%=formName %>_tble" class="form" action="#" method="post">  
		
			                	<div class="table-responsive">
									<table class="table">
										<thead >
											<tr>
												<th>#</th>
												<th>Holiday Name</th>
												<th>Holiday Date</th>
												<th>Holiday Config</th>
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
										
											int holiday_id=AppUtil.getNullToInteger( (String)searchData.get("COL#1") );
											int holiday_type_id=AppUtil.getNullToInteger( (String)searchData.get("COL#2") );
											String holiday_type=AppUtil.getNullToEmpty( (String)searchData.get("COL#3") );
											String holiday_sub_type=AppUtil.getNullToEmpty( (String)searchData.get("COL#4") );
											String holiday_day_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#5") );
											String holiday_date=AppDateUtil.convertToAppDate((String)searchData.get("COL#6"), false, true);
										%>
											<tr>
												<th scope="row"><%=sno %></th>
												<td><%=holiday_day_name  %></td>
												<td><%=holiday_date  %></td>
												<td><%=holiday_sub_type  %></td>
												<td><%=holiday_type  %></td>
												<td>
													<a data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="holiday?action=edit&holidayId=<%=holiday_id%>">Edit</a> &nbsp;&nbsp;
													<a class='<%=formName %>_delete' href="javascript:;" ahref="holiday?action=delete&holidayId=<%=holiday_id%>">delete</a></td>
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
   <!-- Content End -->
  </body>
   
<script type="text/javascript">

$(document).ready(function(){
	
	
	init();
	
});

function <%=formName %>reset(){
	$('#<%=formName %> #holidayName').val('');$('#<%=formName %> #holidayName').attr('value', '');
	$('#<%=formName %> #holidayType').val('');$('#<%=formName %> #holidayType').attr('value', '');
	$('#<%=formName %> #holidaySubType').val('');$('#<%=formName %> #holidaySubType').attr('value', '');
	$('#<%=formName %> #holidayDate').val('');$('#<%=formName %> #holidayDate').attr('value', '');
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
	
	
function init(){
	$('.select2').select2();
	$('.date_picker').datepicker({
		autoclose:true,
		todayBtn:'linked',
		todayHighlight:true,
		format:'dd/mm/yyyy'
	}); 
}
</script>

</html>