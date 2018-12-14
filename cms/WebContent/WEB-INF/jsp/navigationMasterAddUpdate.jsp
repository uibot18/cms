<%@page import="com.cms.menu.handler.MenuCreationController"%>
<%@page import="com.cms.navigation.handler.NavigationCreationController"%>
<%@page import="com.cms.navigation.bean.MenuNavigationDO"%>
<%@page import="com.cms.menu.bean.MenuMasterDO"%>
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
MenuNavigationDO menunavDO=(MenuNavigationDO)request.getAttribute("menunavDO");
if( menunavDO==null ){ menunavDO=new  MenuNavigationDO(); } 

String formName="Menu_frm_"+Math.abs( new Random().nextInt(9999) );
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
    	<form id="<%=formName%>" action="navigation?action=save" method="post">
        <div class="modal-header">
            <h4 class="modal-title">Navigation Creation</h4>
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        </div>
        <div class="modal-body">
        <%=PageUtil.getAlert(request) %>
			<input type="hidden" name="action" value="save">
			<input type="hidden" name="navigationId" value="<%=menunavDO.getNavigationId()%>">
	
			<div class="form-group">
		    	<label for="parent_navigationId" class="control-label">Parent Navigation Name</label>
		       	<select id="parent_navigationId" class="form-control " placeholder="Parent Navigation" name="parent_navigationId" >
          			<option>Root</option>
					<%=NavigationCreationController.parentNavigationOption( ""+menunavDO.getParentNavigationId(),""+menunavDO.getNavigationId()) %>
				</select>
			</div>
			
			<div class="form-group">
			    <label for="navigationName" class="control-label">Navigation Name<span style="color: #f62d51;">*</span></label>
			    <input type="text" name="navigationName" class="form-control" id="navigationName" value="<%=menunavDO.getNavigationName()%>" placeholder="Navigation Name">
			</div>
			
			<div class="form-group">
			    <!-- <label for="MenuAction" class="control-label">Is Menu</label> -->
			    <div class="form-check">
			    	<input type="checkbox" name="isMenu" class="form-check-input " value="1" id="isMenu" <%=menunavDO.getBoolIsMenu()?"checked":"" %>  >
			    	<label class="form-check-label" for="isMenu">Is Menu</label>
			    </div>
			</div>
			
			
				<div class="form-group" id="menu_div">
			    <label for="menuId" class="control-label">Menu<span style="color: #f62d51;">*</span></label>
			           <select id="menuId" class="form-control select2" placeholder="Menu Name"  name="menuId" style="width: 100%; height:26px;" >
						                            		<option></option>
															<%=MenuCreationController.parentMenuOption(""+menunavDO.getMenuId()) %>
														</select>
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

$(document).ready( function(){
	try{		
		$('.select2').select2();
		<%
		if(menunavDO.getBoolIsMenu()){
		%>
		$('#<%=formName%> #menu_div').show();
		$('#<%=formName%> #menu_div').attr('required',true);
		<%}else{%>
		$('#<%=formName%> #menu_div').hide();
		$('#<%=formName%> #menu_div').attr('required',false);
		<%}%>
		
		$('#<%=formName %>').on('change', '#isMenu', function(){
		
			if($(this).is(":checked")){
			$('#<%=formName%> #menu_div').show();
			$('#<%=formName%> #menuId').attr("required","required");
		}
		else{
			$('#<%=formName%> #menu_div').hide();
			$('#<%=formName%> #menuId').removeAttr("required","required");
			 $('#<%=formName%> #menuId option:selected').removeAttr("selected");	$('#<%=formName%> #menuId option:selected').prop("selected",false); 
		}
			
		});
		$('#<%=formName%>').validate({
			errorClass: 'invalid',
			validClass: 'valid',
			errorPlacement: function(error, element) {
				error.insertAfter(element);
			},
			rules: {
				
				//navigationName: { required: true }
			},
			messages: {
				menuId: { required: 'Menu Name is required' },
				navigationName: { required: 'Navigation Name is required' }
			
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
	$('#<%=formName %> #menuName').val('');$('#<%=formName %> #menuName').attr('value', '');
	$('#<%=formName %> #menuAction').val('');$('#<%=formName %> #menuAction').attr('value', '');
}
</script>
</html>