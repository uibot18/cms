<%@page import="com.application.util.PageUtil"%>
<%@page import="java.util.Random"%>
<%@page import="com.cms.holiday.bean.AdminHolidayTypeDO"%>
<%@page import="com.application.util.AppUtil"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cms.user.login.bean.LoginMasterBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
AdminHolidayTypeDO holidayTypeDO=(AdminHolidayTypeDO)request.getAttribute("holidayTypeDO");
if( holidayTypeDO==null ){ holidayTypeDO=new  AdminHolidayTypeDO(); } 

String formName="Hldy_frm_"+Math.abs( new Random().nextInt(9999) );

%>

<div class="modal-dialog modal-xl" role="document" style="margin-left: 33%;width: 550px;">
	<div class="modal-content">
		<form class="form" action="holidayType?action=save" method="post" id="<%=formName%>">
			<input type="hidden" name="holidayTypeId" value="<%=holidayTypeDO.getHolidayTypeId()%>">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel16">Holiday Creation</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<%=PageUtil.getAlert(request) %>
				<div class="form-body">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="timesheetinput1">Holiday Type</label>
								<div class="position-relative has-icon-left">
									<input type="text" id="holidayType" class="form-control" placeholder="Holiday Type" name="holidayType" value="<%=AppUtil.getNullToEmpty(holidayTypeDO.getHolidayType() )%>" required="required">
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
	$('#<%=formName %> #holidayType').val('');$('#<%=formName %> #holidayType').attr('value', '');
}
</script>
