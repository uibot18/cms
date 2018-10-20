
<%@page import="com.application.util.PageUtil"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="com.cms.cms_package.handler.PackageCreationController"%>
<%@page import="java.util.Random"%>
<%@page import="com.cms.common.master.bean.CommonMasterDO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
CommonMasterDO packageDO=(CommonMasterDO)request.getAttribute("processDO");
if( packageDO==null ){ packageDO=new  CommonMasterDO(); } 

String formName="Proc_frm_"+Math.abs( new Random().nextInt(9999) );
%>

<div class="modal-dialog modal-xl" role="document" style="margin-left: 33%;width: 600px;">
	<div class="modal-content">
		<form class="form" action="process?action=save" method="post" id="<%=formName%>">
			<input type="hidden" name="action" value="save">
            <input type="hidden" name="processId" value="<%=packageDO.getCmnMasterId()%>">
            <input type="hidden" name="groupId" value="<%=packageDO.getCmnGroupId()%>">
            <input type="hidden" name="levelNo" value="<%=packageDO.getLevelNo()%>">
            
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel16">Process Creation</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<%=PageUtil.getAlert(request) %>
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="timesheetinput1">Package Name</label>
								<div class="position-relative has-icon-left">
									<select id="packageName" class="form-control" placeholder="Package Name" name="packageName" required="required">
	                            		<option></option>
										<%=PackageCreationController.packageOption("", ""+packageDO.getParentId()) %>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="timesheetinput1">Process Name</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="processName" class="form-control" placeholder="Process Name" name="processName" value="<%=AppUtil.getNullToEmpty(packageDO.getCmnMasterName() )%>" required="required">
									<div class="form-control-position">
										<i class="fas fa-unlock-alt"></i>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn" data-dismiss="modal">Cancel</button>
				<%-- <button type="button" class="btn grey btn-secondary" onclick="<%=formName %>reset()">Reset</button> --%>
				<button type="submit" class="btn btn-success">Save</button>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">

$(document).ready( function(){
	$('#<%=formName%>').submit(function(e){
		var frm=$(this);
		$.ajax({
		 	   url:$(frm).attr('action'),
		 	   data:$(frm).serialize(),
		 	   beforeSend:function(){
		 		  $('#CMS-POPUP-MODEL').html('<center> <img alt="" src="./resource/img/loader.gif"></center>');
		 	   },
		 	   success:function(data){
		 		   $('#CMS-POPUP-MODEL').html(data);
		 	   }
		    }); 
		e.preventDefault();
	});
});

function <%=formName %>reset(){
	$('#<%=formName %> #serviceName').val('');$('#<%=formName %> #serviceName').attr('value', '');
	$('#<%=formName %> #packageName').val('');$('#<%=formName %> #packageName').attr('value', '');
}
</script>
</html>