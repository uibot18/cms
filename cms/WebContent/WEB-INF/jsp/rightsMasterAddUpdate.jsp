<%@page import="com.cms.rights.bean.RightsMasterDO"%>
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
RightsMasterDO rightsDO=(RightsMasterDO)request.getAttribute("rightsDO");
if( rightsDO==null ){ rightsDO=new  RightsMasterDO(); } 
int rightsMasterId=AppUtil.getNullToInteger( request.getParameter("rightsMasterId") );
int rightsId=AppUtil.getNullToInteger( request.getParameter("rightsId") );
String rightsGroupName=AppUtil.getNullToEmpty( request.getParameter("rightsGroupName") );
String rightsName=AppUtil.getNullToEmpty( request.getParameter("rightsName") );
String formName="rights_frm_"+Math.abs( new Random().nextInt(9999) );
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
    	<form id="<%=formName%>" action="rights?action=save" method="post">
        <div class="modal-header">
            <h4 class="modal-title">Rights Creation</h4>
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        </div>
        <div class="modal-body">
        <%=PageUtil.getAlert(request) %>
			<input type="hidden" name="action" value="save">
			<input type="hidden" name="rightsMasterId" value="<%=rightsDO.getRightsMasterId()%>">
	
			
			<div class="form-group">
			    <label for="rightsId" class="control-label">Rights Id<span style="color: #f62d51;">*</span></label>
			    <input type="text" name="rightsId" class="form-control numbersonly" id="rightsId" value="<%=rightsDO.getRightsId()==0?"":rightsDO.getRightsId()%>" placeholder="Rights Id">
			</div>
			
			<div class="form-group">
			    <label for="rightsGroupName" class="control-label">Rights Group Name<span style="color: #f62d51;">*</span></label>
			    <input type="text" name="rightsGroupName" class="form-control" id="rightsGroupName" value="<%=rightsDO.getRightsGroupName()%>" placeholder="Rights Group Name">
			</div>
			
			<div class="form-group">
			    <label for="rightsName" class="control-label">Rights Name<span style="color: #f62d51;">*</span></label>
			    <input type="text" name="rightsName" class="form-control" id="rightsName" value="<%=rightsDO.getRightsName()%>" placeholder="Rights Name">
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
				rightsId: { required: true },
				rightsName: { required: true },
				rightsGroupName: { required: true }
			},
			messages: {
				rightsId: { required: 'Rights Id is required' },
				rightsName: { required:  'Rights Name is required' },
				rightsGroupName: { required: 'Rights Group Name is required' }
			
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
	$('#<%=formName %> #rightsGroupName').val('');$('#<%=formName %> #rightsGroupName').attr('value', '');
	$('#<%=formName %> #rightsName').val('');$('#<%=formName %> #rightsName').attr('value', '');
	$('#<%=formName %> #rightsId').val('');$('#<%=formName %> #rightsId').attr('value', '');
}
</script>
</html>