<!DOCTYPE html>
<%@page import="java.util.HashMap"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cms.common.search.SearchEnum"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Random"%>
<html class="loading" lang="en" data-textdirection="ltr">
  
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png">
    <title>ui-bot</title>
    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="assets/libs/select2/dist/css/select2.min.css">
    <link href="dist/css/style.min.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
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
   
   String formName="hlydyTyp_frm_"+Math.abs( new Random().nextInt(9999));
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
                              
                              <button type="button" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="holidayType?action=add" class="btn btn-primary btn-sm float-right btn-rounded" style="">
	                            		<i class="fa fa-plus"></i> 
	                            		ADD
	                            	</button>
                              </h4>
                          <form id="<%=formName %>" class="form p-t-20" action="holidayType" method="post">     
                              <input type="hidden" name="action" value="search">
                                       <div class="row">
                                		<div class="col-sm-6">
                                			<div class="form-group row">
		                                        <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Holiday Type</label>
		                                        <div class="col-sm-8">
				                    		<input type="text" id="email" class="form-control" placeholder="Holiday Type" name="holidayType" value="<%=holidayType%>">
				                    
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
										
											int holiday_type_id=AppUtil.getNullToInteger( (String)searchData.get("COL#1") );
											String holiday_type=AppUtil.getNullToEmpty( (String)searchData.get("COL#2") );
										%>
											<tr>
												<th scope="row"><%=sno %></th>
												<td><%=holiday_type  %></td>
												<td>
													<a data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="holidayType?action=edit&holidayTypeId=<%=holiday_type_id%>">Edit</a> &nbsp;&nbsp;
													<a class='<%=formName %>_delete tt' href="javascript:;" ahref="holidayType?action=delete&holidayTypeId=<%=holiday_type_id%>">delete</a></td>
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
	
});

 function <%=formName %>reset(){
	$('#<%=formName %> #holidayType').val('');$('#<%=formName %> #holidayType').attr('value', '');
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

</html>