<%@page import="com.cms.rights_template.dao.RightsTemplateDAO"%>
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
   
   Map<String, String> rightsMap = RightsTemplateDAO.getRightsNameMap(null, "");
   
   String serviceName=AppUtil.getNullToEmpty( requestMap.get("serviceName") );
   String formName="rights_frm_"+Math.abs( new Random().nextInt(9999));
   %>

<div class="container-fluid">
      <!-- Start Page Content -->
      <div class="row">
          <%-- <div class="col-12">
              <div class="card">
                  <div class="card-body">
                  	<h4 class="card-title">Employee Rights Search
                  		<!-- <button type="button" data-toggle="modal" data-target="#CMS-POPUP-MODEL" data-url="service?action=add" class="btn btn-primary btn-sm float-right btn-rounded" style="">
                   		<i class="fa fa-plus"></i> 
                   		ADD
                   	</button> -->
                  	</h4>
                      <form id="<%=formName %>" class="form p-t-20" action="employee?action=employeeRightsSearch" method="post">
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
          </div> --%>
          <div class="col-12">
              <div class="card">
                  <div class="card-body">
                      <h4 class="card-title">Employee Rights List</h4>
                  </div>
                  <form id="<%=formName %>_tble" class="form" action="employee?action=employeeRightsMap" method="post">
                   <div class="table-responsive">
                       <table class="table table-bordered text-center">
                           <thead>
                               <tr>
                                   <th scope="col">#</th>
                                   <th><label class=""><input type="checkbox" id="<%=formName %>idAll"></label> </th>
                                   <th scope="col">Employee Name</th>
                                   <th scope="col">Rights</th>
                               </tr>
                           </thead>
                           <tbody>
                           <%
							List<Map<String, Object>> resultList=(List<Map<String, Object>>)resultMap.get( SearchEnum.RESULT_LIST.getKeyName() );
							if(resultList==null){ resultList= new ArrayList<Map<String, Object>>();  }
							int sno=1;
							for(Map<String, Object> searchData:resultList){
								/* a.emp_id, a.first_name, b.rights_template_id, c.rights_template_name */
								int emp_id=AppUtil.getNullToInteger( (String)searchData.get("COL#1") );
								String employee_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#2") );
								int rights_template_id=AppUtil.getNullToInteger( (String)searchData.get("COL#3") );
								String rights_template_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#4") );
							%>
								<tr>
									<th scope="row"><%=sno %></th>
									<td>
										<label class=""><input type="checkbox" name="empIds" class="<%=formName %>ids" value="<%=emp_id%>"></label> 
									</td>
										
									<td><%=employee_name  %></td>
									<td><select id="rights_ids_<%=sno %>" class="form-control rights_ids" placeholder="Rights" name="rights_ids_<%=emp_id%>">
	                            		<option></option>
										<%=AppUtil.formOption(rightsMap, ""+rights_template_id) %>
									</select></td>
								</tr>
							<%sno++;
							} %>
                           </tbody>
                       </table>
                   </div>
                   <button type="submit" id="<%=formName %>_btn_approve" class="btn btn-success m-r-10 m-t-10 float-right" style="display: none;">Map Rights</button>
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
	
	
	try{		
		$('#<%=formName %>_tble').validate({
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
	
	
	$('#<%=formName %>_tble').on('click', '#<%=formName %>idAll', function(){
		if($(this).is(':checked')){
			$('.<%=formName %>ids').prop('checked', true);
		}else{
			$('.<%=formName %>ids').prop('checked', false);
		}
		<%=formName%>_approvelBtnDiaplay();
	});
	
	$('#<%=formName %>_tble').on('click', '.<%=formName %>ids', function(){
		var total=$('.<%=formName %>ids').length;
		var checked=$('.<%=formName %>ids:checked').length;
		
		if(total!=0){
			if(checked==total){
				$('#<%=formName %>idAll').prop('checked', true);
			}else{
				$('#<%=formName %>idAll').prop('checked', false);
			}
		}
		<%=formName%>_approvelBtnDiaplay();
	});
});

function <%=formName%>_approvelBtnDiaplay(){
	if($('.<%=formName %>ids:checked').length > 0){
		$('#<%=formName %>_btn_approve').show();
	}else{
		$('#<%=formName %>_btn_approve').hide();
	}
}
function <%=formName %>reset(){
	$('#<%=formName %> #serviceName').val('');$('#<%=formName %> #serviceName').attr('value', '');
	
}



</script>

