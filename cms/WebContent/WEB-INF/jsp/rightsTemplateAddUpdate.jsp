
<%@page import="java.util.Set"%>
<%@page import="com.cms.rights.bean.RightsMasterDO"%>
<%@page import="com.cms.rights.dao.RightsMasterDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.cms.menu.bean.MenuMasterDO"%>
<%@page import="com.cms.menu.dao.MenuMasterDAO"%>
<%@page import="com.cms.rights_template.bean.RightsTemplateDO"%>
<%@page import="com.application.util.PageUtil"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="com.cms.common.master.CmnGroupName"%>
<%@page import="com.cms.common.master.bean.CommonMasterDO"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cms.user.login.bean.LoginMasterBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
RightsTemplateDO rightsTemplateDO=(RightsTemplateDO)request.getAttribute("rightsTemplateDO");
if( rightsTemplateDO==null ){ rightsTemplateDO=new  RightsTemplateDO(); } 

List<MenuMasterDO> menuList=MenuMasterDAO.getMenuMaster(null, false);
menuList=menuList==null?new ArrayList<MenuMasterDO>():menuList;

List<RightsMasterDO> rightsList=RightsMasterDAO.getRightsMaster(null, false);
rightsList=rightsList==null?new ArrayList<RightsMasterDO>():rightsList;

String[] menuIds=AppUtil.getNullToEmpty(rightsTemplateDO.getMenuIds()).split(",");
Set<String> menuIds_set=AppUtil.convertStrArrayToSet(menuIds);

String[] rightsIds=AppUtil.getNullToEmpty(rightsTemplateDO.getRightsIds()).split(",");
Set<String> rightsIds_set=AppUtil.convertStrArrayToSet(rightsIds);

String formName="rightsTemp_frm_"+Math.abs( new Random().nextInt(9999) );
%>
<style>
.form-control.invalid{
	border-color: #f62d51 !important;
}
label.invalid{
	color: #f62d51 !important;
}
.form-control.valid{
	border-color: #36bea6 !important;
}

</style>
<div class="modal-dialog">
    <div class="modal-content">
    	<form id="<%=formName%>" action="rightsTemplate?action=save" method="post">
        <div class="modal-header">
            <h4 class="modal-title">Rights Template Creation</h4>
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        </div>
        <div class="modal-body">
        <%=PageUtil.getAlert(request) %>
			<input type="hidden" name="action" value="save">
			<input type="hidden" name="rightsTemplateId" value="<%=rightsTemplateDO.getRightsTemplateId()%>">
	
			
	
			
			<div class="form-group">
			    <label for="rightsGroupName" class="control-label">Rights Template Name<span style="color: #f62d51;">*</span></label>
			    <input type="text" name="rightsTemplateName" class="form-control" id="rightsTemplateName" value="<%=rightsTemplateDO.getRightsTemplateName()%>" placeholder="Rights Template Name">
			</div>
			
			<div class="menu_div" id="menu_div" >
			  <h4 class="modal-title">Menu</h4>
			
			
						<% for(MenuMasterDO menuDo:menuList){ 
							String chk="";
						
							String menuId=menuDo.getMenuId()+"";
							if(menuIds_set.contains( menuId )){ chk=" checked='checked' "; }
						%>
						<div class="col-md-5">
							<div class="form-check">
								<input type="checkbox" name="menuIds" class="form-check-input " value="<%=menuId%>" id="menuIds_<%=menuId%>"  <%=chk%> >
								<label class="form-check-label" for="defaultCheck3"><%=menuDo.getMenuName() %></label>
							</div>
						</div>
							
						<% } %>
							
					
			
			</div>
			
			<div class="Rights_div" id="Rights_div" >
			<h4 class="modal-title">Rights</h4>
		
						<% for(RightsMasterDO rightsDo:rightsList){ 
							String chk="";
						
							String rightsId=rightsDo.getRightsId()+"";
							if(rightsIds_set.contains( rightsId )){ chk=" checked='checked' "; }
						%>
						<div class="col-md-5">
							<div class="form-check">
								<input type="checkbox" name="rightsIds" class="form-check-input " value="<%=rightsId%>" id="rightsIds_<%=rightsId%>"  <%=chk%> >
								<label class="form-check-label" for="defaultCheck3"><%=rightsDo.getRightsName() %></label>
							</div>
						</div>
							
						<% } %>
							
		
			</div>
			
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-danger waves-effect waves-light">Save</button>
        </div>
        </form>
    </div>
</div>

<script type="text/javascript">
jQuery.validator.addMethod("numbersonly", function(value, element) { return this.optional(element) || value.match(/^[0-9-]+$/);	}, " Enter Numbers Only"); 
$(document).ready( function(){
	try{		
		$('#<%=formName%>').validate({
			errorClass: 'invalid',
			validClass: 'valid',
			errorPlacement: function(error, element) {
				error.insertAfter(element);
			},
			rules: {
			
				rightsTemplateName: { required: true }
			},
			messages: {
			
				rightsTemplateName: { required: 'Template Name is required' }
			
			},
			submitHandler: function(form) {
				$.ajax({
					url:$(form).attr('action'),
					data:$(form).serialize(),
					beforeSend:function(){
						$('#CMS-POPUP-MODEL').html('<center> <img alt="" src="./resource/img/loader.gif"></center>');
					},
					success:function(data){
				 		$('#CMS-POPUP-MODEL').html(data);
					}
				}); 
			}
		});
		
	}catch(e){
		alert('Something went wrong. Please Try Later..!');
	}
});

function <%=formName %>reset(){
	$('#<%=formName %> #rightsTemplateName').val('');$('#<%=formName %> #rightsTemplateName').attr('value', '');

}
</script>
</html>