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
      <div class="col-2" id="Users">Profiles</div>
      <div class="col-2" id="Groups">Roles</div>
      
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
                
                <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Profiles</h4>
                            </div>
                            
                            <div>  
                            <div style="float: left; margin-left: 20px;">Profile is a set of permissions dealing with module access and operations, setup customizations</div>                          
                            <div style="float: right;margin-right: 30px;"><button type="button" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="profiles?action=add" class="btn btn-primary btn-sm float-right btn-rounded" style="">
	                            		<i class="fa fa-plus"></i> 
	                            		New Profile
	                            	</button>
	                            	</div>
                            </div>
                            <br/>
                            
                            <form id="<%=formName %>_tble" class="form" action="#" method="post">
	                            <div class="table-responsive">
	                                <table class="table table-bordered text-center">
	                                    <thead>
	                                        <tr>
												<th>#</th>
												<th>PROFILE NAME</th>
												<th>PROFILE DESCRIPTION</th>
												<th>CREATED BY</th>
												<th>MODIFIED BY</th>
											</tr>
	                                    </thead>
	                                    <tbody>
	                                    <%
									
										int sno=1;
										for(Map<String, Object> searchData:resultList){
										
											int profile_id=AppUtil.getNullToInteger( (String)searchData.get("COL#1") );
											String profile_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#2") );
											String profile_description=AppUtil.getNullToEmpty( (String)searchData.get("COL#3") );
											String created_by=AppUtil.getNullToEmpty( (String)searchData.get("COL#5") );
											String updated_by=AppUtil.getNullToEmpty( (String)searchData.get("COL#7") );
											
										
										%>
											<tr>
												<th scope="row"><%=sno %></th>
												<td>
												<a data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="profiles?action=addPermissions" href="#"><%=profile_name %></a></td>
												<td><%=profile_description %></td>
												<td><div class="user-pic" style="float: left;"><img src="./static/assets/images/users/d2.jpg" alt="users" class="rounded-circle" width="20"></div><div style=""><%=created_by %></div></td>
												<td><div class="user-pic" style="float: left;"><img src="./static/assets/images/users/d2.jpg" alt="users" class="rounded-circle" width="20"></div><div style=""><%=updated_by %></div></td>
												
											</tr>
										<%sno++;
										} %>
	                                    </tbody>
	                                </table>
	                            </div>
                            </form>
                        </div>
                    </div>
                
                <!-- End PAge Content -->
            </div>
   
   
<script type="text/javascript">

$(document).ready(function(){
	initPage();
	
	
});




</script>
