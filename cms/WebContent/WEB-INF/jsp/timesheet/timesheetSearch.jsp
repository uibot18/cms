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
   
   String serviceName=AppUtil.getNullToEmpty( requestMap.get("serviceName") );
   String formName="service_frm_"+Math.abs( new Random().nextInt(9999));
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
                                <label for="fname" class="col-sm-3 p-t-5  control-label col-form-label">Service Name</label>
                                <div class="col-sm-8">
                                    <input type="text" name="serviceName" class="form-control form-control-sm" id="serviceName" value="<%=serviceName %>" placeholder="Service Name">
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
                      <h4 class="card-title">Employee List</h4>
                  </div>
                  <form id="<%=formName %>_tble" class="form" action="#" method="post">
                   <div class="table-responsive">
                       <table class="table table-bordered text-center">
                           <thead>
                               <tr>
                                   <th scope="col">#</th>
                                   <th scope="col">Service Name</th>
                                   <th scope="col">Action</th>
                               </tr>
                           </thead>
                           <tbody>
                           <%
							List<Map<String, Object>> resultList=(List<Map<String, Object>>)resultMap.get( SearchEnum.RESULT_LIST.getKeyName() );
							if(resultList==null){ resultList= new ArrayList<Map<String, Object>>();  }
							int sno=1;
							for(Map<String, Object> searchData:resultList){
								/* SELECT cmn_master_id, cmn_group_id, parent_id, cmn_master_name AS service_name, level_no */
								int cmn_master_id=AppUtil.getNullToInteger( (String)searchData.get("COL#1") );
								int cmn_group_id=AppUtil.getNullToInteger( (String)searchData.get("COL#2") );
								int parent_id=AppUtil.getNullToInteger( (String)searchData.get("COL#3") );
								String service_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#4") );
								int level_no=AppUtil.getNullToInteger( (String)searchData.get("COL#5") );
							%>
								<tr>
									<th scope="row"><%=sno %></th>
									<td><%=service_name  %></td>
									<td>
										<a data-target="#CMS-POPUP-MODEL" data-toggle="modal"  data-url="timesheet?action=edit&serviceId=<%=cmn_master_id%>" href="#">Edit</a> &nbsp;&nbsp;
										<a class='<%=formName %>_delete' href="javascript:;" ahref="timesheet?action=delete&serviceId=<%=cmn_master_id%>">delete</a></td>
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

function <%=formName %>reset(){
	$('#<%=formName %> #serviceName').val('');$('#<%=formName %> #serviceName').attr('value', '');
	
}
</script>

