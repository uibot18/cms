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
   
   String holidayType=AppUtil.getNullToEmpty( requestMap.get("holidayType") );
   String holidayName=AppUtil.getNullToEmpty( requestMap.get("holidayName") );
   String holidayDate=AppUtil.getNullToEmpty( requestMap.get("holidayDate") );
   
   
   String formName="hlydy_frm_"+Math.abs( new Random().nextInt(9999));
   %>
   <div class="app-content content">
      <div class="content-wrapper">
        <div class="content-wrapper-before"></div>
        <div class="content-header row">
          <div class="content-header-left col-md-4 col-12 mb-2">
            <h3 class="content-header-title">Holiday</h3>
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
			                <h4 class="card-title">Holiday Search</h4>
			                <a class="heading-elements-toggle"><i class="la la-ellipsis-v font-medium-3"></i></a>
			                <div class="heading-elements">
			                    <ul class="list-inline mb-0">
			                    	<li>
			                    		<a  id="testId" class="" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="holiday?action=add"> 
			                    			<b>+&nbsp;Add</b>
										</a>
			                    	</li>
			                        <!-- <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
			                        <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
			                        <li><a data-action="close"><i class="ft-x"></i></a></li> -->
			                    </ul>
			                </div>
			            </div>
			            <div class="card-content collapse show">
			                <div class="card-body border-top-blue-grey border-top-lighten-5 ">
			                    <form id="<%=formName %>" class="form" action="holiday" method="post">
				                    <input type="hidden" name="action" value="search">
									<div class="form-body">
				                        <div class="row">
					                        <div class="col-md-6">
						                        <div class="form-group row">
						                        	<label class="col-md-3 label-control" >Holiday Type</label>
						                        	<div class="col-md-9">
						                            	<select id="holidayType" class="form-control" placeholder="Holiday Type" name="holidayType">
															<option></option>
															<%=HolidayTypeCreationController.formHolidayTypeOption(holidayType)%>
													</select>
					                        		</div>
						                        </div>
					                        </div>
					                        <div class="col-md-6">
						                        <div class="form-group row">
						                        	<label class="col-md-3 label-control" >Holiday Name</label>
						                        	<div class="col-md-9">
						                            	<input type="text" id="holidayName" class="form-control" placeholder="Holiday Name" name="holidayName" value="<%=holidayName%>">
					                        		</div>
						                        </div>
					                        </div>
				                        </div>
				                        <div class="row">
			                    			
					                        <div class="col-md-6">
						                        <div class="form-group row">
						                        	<label class="col-md-3 label-control" >Date</label>
						                        	<div class="col-md-9">
						                            	<input type="text" id="holidayDate" class="form-control" placeholder="Date" name="holidayDate" value="<%=holidayDate%>">
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
			                <h4 class="card-title">Holiday List</h4>
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