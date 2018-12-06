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
   
	String rightsGroupName=AppUtil.getNullToEmpty( requestMap.get("rightsGroupName") );
	String rightsName=AppUtil.getNullToEmpty( requestMap.get("rightsName") );
	String rightsId=AppUtil.getNullToEmpty( requestMap.get("rightsId") );
	

   String formName="rights_frm_"+Math.abs( new Random().nextInt(9999));
   %>

<div class="container-fluid">
      <!-- Start Page Content -->
      <div class="row">
          <div class="col-12">
              <div class="card">
                  <div class="card-body">
                  	<h4 class="card-title">RIGHTS SEARCH
                  		<button type="button" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="rights?action=add" class="btn btn-primary btn-sm float-right btn-rounded" style="">
                   		<i class="fa fa-plus"></i> 
                   		ADD
                   	</button>
                  	</h4>
                      <form id="<%=formName %>" class="form p-t-20" action="rights?action=search" method="post">
                      	<div class="row">
                      		<div class="col-sm-6">
                      			<div class="form-group row">
                                <label for="rightsName" class="col-sm-3 p-t-5  control-label col-form-label">Rights Name</label>
                                <div class="col-sm-8">
                                    <input type="text" name="rightsName" class="form-control form-control-sm" id="rightsName" value="<%=rightsName %>" placeholder="Rights Name">
                                </div>
                            </div>
                      		</div>
                      		<div class="col-sm-6">
                      			<div class="form-group row">
                                <label for="rightsGroupName" class="col-sm-3 p-t-5  control-label col-form-label">Rights Group Name</label>
                                <div class="col-sm-8">
                                    <input type="text" name="rightsGroupName" class="form-control form-control-sm" id="rightsGroupName" value="<%=rightsGroupName %>" placeholder="Rights Group Name">
                                </div>
                            </div>
                      		</div>
                      	</div>
                      	
                      	      	<div class="row">
                      		<div class="col-sm-6">
                      			<div class="form-group row">
                                <label for="rightsId" class="col-sm-3 p-t-5  control-label col-form-label">Rights Id</label>
                                <div class="col-sm-8">
                                    <input type="text" name="rightsId" class="form-control numbersonly form-control-sm" id="rightsId" value="<%=rightsId %>" placeholder="Rights Id">
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
                      <h4 class="card-title">Rights List</h4>
                  </div>
                  <form id="<%=formName %>_tble" class="form" action="#" method="post">
                   <div class="table-responsive">
                       <table class="table table-bordered text-center">
                           <thead>
                               <tr>
                                   <th scope="col">#</th>
                                   <th scope="col">Rights Id</th>
                                   <th scope="col">Rights Group Name</th>
                                   <th scope="col">Rights Name</th>
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
								int rights_master_id=AppUtil.getNullToInteger( (String)searchData.get("COL#1") );
								int rights_id=AppUtil.getNullToInteger( (String)searchData.get("COL#2") );
								String group_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#3") );
								String rights_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#4") );
								
							%>
								<tr>
									<th scope="row"><%=sno %></th>
									<td><%=rights_id  %></td>
									<td><%=group_name  %></td>
									<td><%=rights_name  %></td>
									<td>
										<a data-target="#CMS-POPUP-MODEL" data-toggle="modal"  data-url="rights?action=edit&rightsMasterId=<%=rights_master_id%>" href="#">Edit</a> &nbsp;&nbsp;
										<a class='<%=formName %>_delete' href="javascript:;" ahref="rights?action=delete&rightsMasterId=<%=rights_master_id%>">delete</a></td>
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
jQuery.validator.addMethod("numbersonly", function(value, element) { return this.optional(element) || value.match(/^[0-9-]+$/);	}, " Enter Numbers Only"); 
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
	$('#<%=formName %> #rightsGroupName').val('');$('#<%=formName %> #rightsGroupName').attr('value', '');
	$('#<%=formName %> #rightsName').val('');$('#<%=formName %> #rightsName').attr('value', '');
	$('#<%=formName %> #rightsId').val('');$('#<%=formName %> #rightsId').attr('value', '');
}
</script>

