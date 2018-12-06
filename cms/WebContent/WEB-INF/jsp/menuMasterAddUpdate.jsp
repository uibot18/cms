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
MenuMasterDO menuDO=(MenuMasterDO)request.getAttribute("menuDO");
if( menuDO==null ){ menuDO=new  MenuMasterDO(); } 

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
    	<form id="<%=formName%>" action="menu?action=save" method="post">
        <div class="modal-header">
            <h4 class="modal-title">Menu Creation</h4>
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        </div>
        <div class="modal-body">
        <%=PageUtil.getAlert(request) %>
			<input type="hidden" name="action" value="save">
			<input type="hidden" name="menuId" value="<%=menuDO.getMenuId()%>">
	
			
			<div class="form-group">
			    <label for="MenuName" class="control-label">Menu Name<span style="color: #f62d51;">*</span></label>
			    <input type="text" name="menuName" class="form-control" id="menuName" value="<%=menuDO.getMenuName()%>" placeholder="Menu Name">
			</div>
			
			<div class="form-group">
			    <label for="MenuAction" class="control-label">Menu Action<span style="color: #f62d51;">*</span></label>
			    <input type="text" name="menuAction" class="form-control" id="menuAction" value="<%=menuDO.getMenuAction()%>" placeholder="Menu Action">
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
		$('#<%=formName%>').validate({
			errorClass: 'invalid',
			validClass: 'valid',
			errorPlacement: function(error, element) {
				error.insertAfter(element);
			},
			rules: {
				menuName: { required: true },
			menuAction: { required: true }
			},
			messages: {
				menuName: { required: 'Menu Name is required' },
				menuAction: { required: 'Menu Action is required' }
			
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