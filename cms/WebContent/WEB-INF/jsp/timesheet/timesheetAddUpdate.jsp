<%@page import="com.cms.timesheet.handler.TimesheetCreationController"%>
<%@page import="com.cms.timesheet.bean.TaskTimeSheetMasterDO"%>
<%@page import="com.application.util.PageUtil"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
TaskTimeSheetMasterDO timeSheetMstDO = (TaskTimeSheetMasterDO)request.getAttribute("timeSheetMstDO"); 
if(timeSheetMstDO==null){ timeSheetMstDO=new TaskTimeSheetMasterDO(); }

String formName="tim_sht_frm_"+Math.abs( new Random().nextInt(9999) );
%>
<div class="modal-dialog modal-lg">
    <div class="modal-content">
    	<form id="<%=formName%>" action="timesheet?action=save" method="post">
    	<input type="hidden" name="timesheetId" value="<%=timeSheetMstDO.getTimeSheetId()%>">
    	<input type="hidden" name="shiftId" value="<%=timeSheetMstDO.getShiftId()%>">
    	<input type="hidden" name="status" value="<%=timeSheetMstDO.getStatus()%>">
        <div class="modal-header">
            <h4 class="modal-title">TIME SHEET</h4>
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        </div>
        <div class="modal-body">
        <%=PageUtil.getAlert(request) %>
			<input type="hidden" name="action" value="save">
			<div class="row">
				<div class='col-sm-3'>
		            <input type='text' class="form-control date_time_picker" id='datetimepicker1'>
		        </div>
			</div>
			<br>
			<div style="clear: both;"></div>
			<div class="row">
			<div class="col-md-12">
				<div class="table-responsive" style="overflow: visible !important;">
				<%
					int childSize=timeSheetMstDO.getTimeSheetChildList().size();
					//if(childSize==0){ childSize=1; }
				%>
					<input type="hidden" id="rowCount" value="<%=childSize==0?1:childSize%>">
					<table class="table">
						<thead class="bg-success" style="color: white;">
							<tr>
								<th align="center"><div style="width: 30px;">
								<button type="button" id="btn_addRow">+</button>
								</div></th>
								<th>Start Time <span style="color: #f62d51;">*</span></th>
								<th>End Time <span style="color: #f62d51;">*</span></th>
								<th>Type <span style="color: #f62d51;">*</span></th>
								<th>Particular <span style="color: #f62d51;">*</span></th>
								<th>Comments</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody id="data_container">
							<%=TimesheetCreationController.generateTimeSheetTable(request, formName, timeSheetMstDO ) %>
						</tbody>
					</table>
				</div>
			</div>
		</div>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-default waves-effect waves-light">Save</button>
            <button type="button" class="btn waves-effect" data-dismiss="modal">Close</button>
        </div>
        </form>
    </div>
</div>

<script type="text/javascript">

$(document).ready( function(){
	initPage();
	try{		
		$('#<%=formName%>').validate({
			errorClass: 'invalid',
			validClass: 'valid',
			errorPlacement: function(error, element) {
				error.insertAfter($(element).parent().last());
			},
			rules: {
				serviceName: { required: true }
			},
			messages: {
				startTime: { required: 'Service Name is required' }
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
	
	$('#<%=formName%>').on('click', '#btn_addRow', function(){
		
		var sno=parseInt($('#<%=formName%> #rowCount').val()); if(isNaN(sno)){ sno=1;}
		sno++;
		var param='action=loadTimeSheetRow&formName=<%=formName%>&sno='+sno;
		$.getJSON('timesheet?'+param,function(response){
			
			if(response.data!=null && typeof(response)!='undefined'){
				$('#<%=formName%> #data_container').append(response.data);
				$('#<%=formName%> #rowCount').val(sno);
				initPage();
			}
		});
	});
	
	$('#<%=formName%>').on('click', '.del_row', function(){
		var id=$(this).attr('id');
		var sno=id.replace('del_row_', '');
		$('#<%=formName%> #row_'+sno).remove();
	});
	
	$('#<%=formName%>').on('change', '.refType', function(){
		
		var sno = (''+$(this).attr('id')).replace('<%=formName%>_refType_', '');
		
		var refTypeId=$(this).val();
		$('#<%=formName%>_particularsId_'+sno).html('<option value="">-Please Select-</option>');
		if(refTypeId!=null && refTypeId!=''){
			var param='action=loadParticulars&refTypeId='+refTypeId;
			$.getJSON('timesheet?'+param,function(response){
				if(response.data!=null && typeof(response)!='undefined'){
					$('#<%=formName%>_particularsId_'+sno).append(response.data);
					initPage();
				}
			});
		}
		
	});
	
	
});

function <%=formName %>reset(){
	$('#<%=formName %> #serviceName').val('');$('#<%=formName %> #serviceName').attr('value', '');
}
</script>
</html>