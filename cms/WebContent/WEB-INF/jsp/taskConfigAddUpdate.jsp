<%@page import="com.application.util.PageUtil"%>
<%@page import="com.cms.employee.dao.AdmEmployeeMasterDAO"%>
<%@page import="com.cms.common.search.util.CommonAjaxUtil"%>
<%@page import="com.cms.task.config.bean.TaskConfigEscalationChildDO"%>
<%@page import="com.cms.task.config.handler.TaskConfigCreationController"%>
<%@page import="com.cms.holiday.handler.HolidayTypeCreationController"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.cms.employee.handler.EmployeeCreationHandler"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="com.cms.process.handler.ProcessCreationController"%>
<%@page import="com.cms.cms_package.handler.PackageCreationController"%>
<%@page import="com.cms.service.handler.ServiceCreationController"%>
<%@page import="com.cms.common.master.bean.CommonMasterDO"%>
<%@page import="com.cms.common.master.dao.CommonMasterDAO"%>
<%@page import="java.util.Random"%>
<%@page import="com.cms.task.config.bean.TaskConfigMasterDO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
TaskConfigMasterDO taskConfigDO=(TaskConfigMasterDO)request.getAttribute("taskConfigDO");
if( taskConfigDO==null ){ taskConfigDO=new  TaskConfigMasterDO(); } 

int processId= taskConfigDO.getProcessId();

CommonMasterDO processDO= CommonMasterDAO.getCommonMasterByCmnMasterId(null, processId, false);
if(processDO==null){ processDO=new CommonMasterDO(); }

CommonMasterDO packageDO= CommonMasterDAO.getCommonMasterByCmnMasterId(null, processDO.getParentId(), false);
if(packageDO==null){ packageDO=new CommonMasterDO(); }

CommonMasterDO serviceDO= CommonMasterDAO.getCommonMasterByCmnMasterId(null, packageDO.getParentId(), false);
if(serviceDO==null){ serviceDO=new CommonMasterDO(); }

String formName="task_cnf_frm_"+Math.abs( new Random().nextInt(9999) );

Map<String, String> dayMap=new HashMap<String, String>();
dayMap.put("sunday", "Sunday");
dayMap.put("monday", "Monday");
dayMap.put("tuesday", "Tuesday");
dayMap.put("wednessday", "Wednessday");
dayMap.put("thursday", "Thursday");
dayMap.put("friday", "Friday");
dayMap.put("saturday", "Saturday");

String[] weeklyWeekDayArr= AppUtil.getNullToEmpty( taskConfigDO.getWeeklyWeekDay() ).split(",") ;

List<String> weeklyWeekDayList=Arrays.asList( weeklyWeekDayArr );
if(weeklyWeekDayList==null){ weeklyWeekDayList=new ArrayList<String>(); }

Map<String, String> monthlyEveryWeekMap=new LinkedHashMap<String, String>();
monthlyEveryWeekMap.put("first", "First");
monthlyEveryWeekMap.put("second", "Second");
monthlyEveryWeekMap.put("third", "Third");
monthlyEveryWeekMap.put("fourth", "Fourth");
monthlyEveryWeekMap.put("last", "Last");

Map<String, String> monthMap=new LinkedHashMap<String, String>();
monthMap.put("1", "january");
monthMap.put("2", "feb");
monthMap.put("3", "mar");
monthMap.put("4", "apr");
monthMap.put("5", "may");
monthMap.put("6", "june");
monthMap.put("7", "july");
monthMap.put("8", "augest");

Map<String, String> durationMap=new LinkedHashMap<String, String>();
durationMap.put("day", "Day");
durationMap.put("hour", "Hour");
durationMap.put("minute", "Minute");

List<TaskConfigEscalationChildDO> escalationChildList = taskConfigDO.getEscalationChildList();
if(escalationChildList==null){ escalationChildList=new ArrayList<TaskConfigEscalationChildDO>(); }

Map<String,String> emp_name_map=null;
if(!taskConfigDO.getDepartment().equalsIgnoreCase("0") && !taskConfigDO.getDesignation().equalsIgnoreCase("0") && !taskConfigDO.getDepartment().equalsIgnoreCase("") && !taskConfigDO.getDesignation().equalsIgnoreCase("")){
String subqry="  and a.department_id ="+taskConfigDO.getDepartment()+" AND a.designation_id="+taskConfigDO.getDesignation()+" ";
 emp_name_map=AdmEmployeeMasterDAO.EmpNameMapBySubry(null,subqry);
 }
if(emp_name_map==null) { emp_name_map=new HashMap<String, String>(); }
%>

<style type="text/css">
	.esc_row_style{
	    border: 1px solid #cacfe7;
   		padding: 4px;
   		margin: 1px;
	} 
	.form-control{
	font-size: .975rem !important;
    line-height: 1.45 !important;
    height: 2rem ;
    padding: .95rem 1.2rem;
    border-radius: .21rem !important;
    padding-right: 1rem !important;
    padding-left: 1.7rem !important;
}  
.col-md-4, .col-md-8{
float: left;
} 
select.form-control:not([size]):not([multiple]) {
    height: 2rem ;
}
.btn-sm {
    font-size: .875rem;
    padding: .25rem .5rem;
    border-radius: .21rem;
}
</style>

 <div class="modal-dialog modal-lg">
	<div class="modal-content">
		<form class="form" action="taskConfig?action=save" method="post" id="<%=formName%>">
			<input type="hidden" name="action" value="save">
            <input type="hidden" name="taskConfigId" value="<%=taskConfigDO.getTaskConfigId()%>">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel16">Task Configuration Creation</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<%=PageUtil.getAlert(request) %>
				<div class="form-body">
					<%-- <div class="form-group">
						<label class="col-md-3 label-control" for="projectinput5">Service Name</label>
						<div class="col-md-9">
	                       <select id="serviceName" class="form-control" placeholder="Service Name" name="serviceName" >
                           		<option></option>
								<%=ServiceCreationController.serviceOption("", ""+serviceDO.getCmnMasterId() ) %>
							</select>
	                    </div>
                    </div> --%>
				
					<div class="row mb-2">
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Service Name</label>
						<div class="col-md-8">
	                       <select id="serviceName" class="form-control" placeholder="Service Name" name="serviceName" >
                           		<option></option>
								<%=ServiceCreationController.serviceOption("", ""+serviceDO.getCmnMasterId() ) %>
							</select>
	                    </div>
                    </div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Package Name</label>
						<div class="col-md-8">
	                       <select id="packageName" class="form-control" placeholder="Package Name" name="packageName" >
                           		<option></option>
								<%=CommonAjaxUtil.commonmasteroptionbyparentId(""+serviceDO.getCmnMasterId(), ""+packageDO.getCmnMasterId()) %>
							</select>
	                    </div>
                    </div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Process Name</label>
						<div class="col-md-8">
	                       <select id="processName" class="form-control" placeholder="Process Name" name="processName" >
                           		<option></option>
								<%=CommonAjaxUtil.commonmasteroptionbyparentId( ""+packageDO.getCmnMasterId(),""+processDO.getCmnMasterId() ) %>
							</select>
	                    </div>
                    </div>
						</div>
					</div>
					
					<div class="row mb-2">
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Task Config Name</label>
						<div class="col-md-8">
	                       <input type="text" id="taskConfigName" class="form-control" placeholder="Task Config Name" name="taskConfigName" value="<%=AppUtil.getNullToEmpty( taskConfigDO.getTaskConfigName() )%>" required="required">
	                    </div>
                    </div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Execution Order</label>
						<div class="col-md-8">
	                       <input type="text" id="executionOrder" class="form-control" placeholder="Execution Order" name="executionOrder" value="<%=taskConfigDO.getExeOrder()%>" required="required">
	                    </div>
                    </div>
						</div>
						
					</div>
					
					<div class="row mb-2">
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Department</label>
						<div class="col-md-8">
	                       <select id="departmentName" class="form-control" placeholder="Department" name="department" >
                           		<option></option>
								<%=EmployeeCreationHandler.formDepartmentOption(""+taskConfigDO.getDepartment() )%>
							</select>
	                    </div>
                    </div>
						</div>
						<div class="col-md-4">
							
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Designation</label>
						<div class="col-md-8">
	                       <select id="designation" class="form-control" placeholder="Designation" name="designation" >
                           		<option></option>
									<%=CommonAjaxUtil.commonmasteroptionbyparentId(""+taskConfigDO.getDepartment(), ""+taskConfigDO.getDesignation()) %>
							</select>
	                    </div>
                    </div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Employee</label>
						<div class="col-md-8">
                       <select id="empId" class="form-control" placeholder="Employee" name="empId" >
                          		<option></option>
							
							<%=AppUtil.formOption(emp_name_map, ""+taskConfigDO.getEmpId()) %>
						</select>
	                    </div>
                    </div>
						</div>
						</div>
						<div class="row mb-2">
								<div class="col-md-4">
								<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Ticket Duration</label>
						<div class="col-md-4">
                       <input type="text" id="ticketDuration" class="form-control" placeholder="" name="ticketDuration" value="<%=taskConfigDO.getTicketDuration() %>"  >
	                    </div>
	                    <div class="col-md-4">
								<div class="form-group">
                       <select id="ticketDurationUom" class="form-control" placeholder="" name="ticketDurationUom" >
	                  		<option value="na">--select--</option>
	                  		<%=AppUtil.formOption(durationMap, ""+taskConfigDO.getTicketDurationUom()) %>
						</select>
                    </div>
							</div>
                    </div>
							</div>
					</div>
					
					<hr>
					
					
					<!-- ----------------display---------------- -->
					
					
					<!-- <div class="row mb-2">
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Service Name <b class="float-right">:</b></label>
						<div class="col-md-8">
	                       <b>nmaeee</b>
	                    </div>
                    </div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Package Name <b class="float-right">:</b></label>
						<div class="col-md-8">
	                       <b>nmaeee</b>
	                    </div>
                    </div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Process Name <b class="float-right">:</b></label>
						<div class="col-md-8">
	                      <b>nmaeee</b>
	                    </div>
                    </div>
						</div>
					</div>
					<div class="row mb-2">
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Task Config Name <b class="float-right">:</b></label>
						<div class="col-md-8">
	                       <b>nmaeee</b>
	                    </div>
                    </div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Execution Order <b class="float-right">:</b></label>
						<div class="col-md-8">
	                       <b>nmaeee</b>
	                    </div>
                    </div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Department <b class="float-right">:</b></label>
						<div class="col-md-8">
	                      <b>nmaeee</b>
	                    </div>
                    </div>
						</div>
					</div>
					<div class="row mb-2">
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Designation <b class="float-right">:</b></label>
						<div class="col-md-8">
	                       <b>nmaeee</b>
	                    </div>
                    </div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Employee<b class="float-right">:</b></label>
						<div class="col-md-8">
	                       <b>nmaeee</b>
	                    </div>
                    </div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Ticket Duration<b class="float-right">:</b></label>
						<div class="col-md-8">
	                      <b>nmaeee</b>
	                    </div>
                    </div>
						</div>
					</div>
					
					<div class="row mb-2">
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Daily <b class="float-right">:</b></label>
						<div class="col-md-8">
	                       <b>10 Days</b>
	                    </div>
                    </div>
						</div>
						</div>
						<div class="row mb-2">
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Starts at<b class="float-right">:</b></label>
						<div class="col-md-8">
	                       <b>10</b>
	                    </div>
                    </div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Duration<b class="float-right">:</b></label>
						<div class="col-md-8">
	                      <b>10 days</b>
	                    </div>
                    </div>
						</div>
					</div> -->
					
					<!-- ----------------display---------------- -->
					
					
					
					
					<div class="row">
						<div class="col-md-12">
							<label class="mr-1"><b>Escalation :</b></label>
							<button type="button" class="btn btn-icon btn-success btn-sm" id="btn_escalationAdd"><i class="fa fa-plus"></i></button>
							<button type="button" class="btn btn-icon btn-danger mr-1 btn-sm" id="btn_escalationDelete"><i class="fa fa-minus"></i></button>
							<input type="hidden" name="esc_rowCount" id="esc_rowCount" value="<%=escalationChildList.size()%>">
						</div>
					</div>
					<div id="escalationContainer">
					
					<%
					int sno=1;
					for(TaskConfigEscalationChildDO escalationChild : escalationChildList){
						
						Map<String,String> esc_emp_name_map=null;
						if(!escalationChild.getDepartment().equalsIgnoreCase("0") && !escalationChild.getDesignation().equalsIgnoreCase("0") && !escalationChild.getDepartment().equalsIgnoreCase("") && !escalationChild.getDesignation().equalsIgnoreCase("")){
						String subqry="  and a.department_id ="+escalationChild.getDepartment()+" AND a.designation_id="+escalationChild.getDesignation()+" ";
						esc_emp_name_map=AdmEmployeeMasterDAO.EmpNameMapBySubry(null,subqry);
						 }
						if(esc_emp_name_map==null) { esc_emp_name_map=new HashMap<String, String>(); }
						%>
						<div class="row esc_row esc_row_style">
							<div class="col-md-4">
								<div class="form-group">
									<label for="">Department</label>
									<div class="position-relative has-icon-left">
										<select id="esc_department_<%=sno %>" class="form-control esc_department" placeholder="Department" name="esc_department_<%=sno %>" >
		                            		<option></option>
											<%=EmployeeCreationHandler.formDepartmentOption(""+escalationChild.getDepartment() )%>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="">Designation</label>
									<div class="position-relative has-icon-left">
										<select id="esc_designation_<%=sno %>" class="form-control esc_designation" placeholder="Designation" name="esc_designation_<%=sno %>" >
		                            		<option></option>
											<%-- <%=EmployeeCreationHandler.formDesignationOption(""+escalationChild.getDesignation() )%> --%>
											<%=CommonAjaxUtil.commonmasteroptionbyparentId(""+escalationChild.getDepartment(), ""+escalationChild.getDesignation()) %>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="">Employee</label>
									<div class="position-relative has-icon-left">
										<select id="esc_empId_<%=sno %>" class="form-control" placeholder="Employee" name="esc_empId_<%=sno %>" >
		                            		<option></option>
											
											<%=AppUtil.formOption(esc_emp_name_map, ""+taskConfigDO.getEmpId()) %>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label for="timesheetinput1">Ticket Duration</label>
									<div class="position-relative ">
										<input type="text" id="esc_ticketDuration_<%=sno %>" class="form-control esc_ticketDuration" placeholder="" name="esc_ticketDuration_<%=sno %>" value="<%=escalationChild.getTicketDuration() %>"  >
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
								<label for="timesheetinput1">&nbsp;</label>
									<div class="position-relative has-icon-left">
										<select id="esc_ticketDurationUom_<%=sno %>" class="form-control esc_ticketDurationUom" placeholder="" name="esc_ticketDurationUom_<%=sno %>" >
					                  		<option value="na">--select--</option>
					                  		<%=AppUtil.formOption(durationMap, ""+escalationChild.getTicketDurationUom()) %>
										</select>
									</div>
								</div>
							</div>
						</div>
						
					<%	
					sno++;
					}
					
					%>
					</div>
					
					<hr>
					
					<div class="row">
						<div class="col-md-12">
							<div class="form-check">
								<input class="form-check-input configType" type="radio" name="configType" id="configType_daily" value="daily">
								<label class="form-check-label" for="configType_daily"><b>Daily</b></label>
							</div>
						</div>
					</div>
					<div class="configType_div" id="configType_div_daily" style="margin-left: 30px;display: none;">
						<div class="row">
							<div class="col-md-1" >
								<div class="form-check">
									<input class="form-check-input" type="radio" name="dailyEveryWeekDay" id="dailyEveryWeekDay_false" value="false" <%=taskConfigDO.isBoolDailyEveryWeekDay()?"" :"checked"%>>
									<label class="form-check-label" for="configType_daily">Every</label>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative has-icon-left">
										<input type="text" id="dailyEveryDay" class="form-control" placeholder="" name="dailyEveryDay" value="<%=taskConfigDO.getDailyEveryDay()%>" required="required">
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<label for="">Day</label>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2" >
								<div class="form-check">
									<input class="form-check-input" type="radio" name="dailyEveryWeekDay" id="dailyEveryWeekDay_false" value="true" <%=taskConfigDO.isBoolDailyEveryWeekDay()?"checked" :""%>>
									<label class="form-check-label" for="configType_daily">Every Week Day</label>
								</div>
							</div>
						</div>
					</div>
					
					<hr>
					
					<div class="row">
						<div class="col-md-12">
							<div class="form-check">
								<input class="form-check-input configType" type="radio" name="configType" id="configType_weekly" value="weekly">
								<label class="form-check-label" for="configType_weekly"><b>Weekly</b></label>
							</div>
						</div>
					</div>
					
					<div class="configType_div" id="configType_div_weekly" style="margin-left: 30px;display: none;">
						<div class="row">
							<div class="col-md-1">
								<label for="">Every</label>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative has-icon-left">
										<input type="text" id="weeklyEveryWeek" class="form-control" placeholder="" name="weeklyEveryWeek" value="<%=taskConfigDO.getWeeklyEveryWeek()%>" required="required">
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<label for="">Week(s) on </label>
							</div>
						</div>
						<div class="row">
						<% for(Entry<String, String> dayEntry :dayMap.entrySet()){ 
							String chk="";
						
							String day=dayEntry.getKey();
							if(weeklyWeekDayList.contains( day )){ chk=" checked='checked' "; }
						%>
						<div class="col-md-3">
							<div class="form-check">
								<input type="checkbox" name="weeklyWeekDay" class="form-check-input weeklydays" value="<%=day%>" id="weeklyWeekDay_<%=day%>"  <%=chk%> >
								<label class="form-check-label" for="defaultCheck3"><%=dayEntry.getValue() %></label>
							</div>
						</div>
							
						<% } %>
							
						</div>
					</div>
					
					<hr>
					<%
						int monthlyEveryMonth1=0;
						int monthlyEveryMonth2=0;
						if( taskConfigDO.isBoolMonthlyDaySpecfic() ){
							monthlyEveryMonth2=taskConfigDO.getMonthlyEveryMonth();
						}else{
							monthlyEveryMonth1=taskConfigDO.getMonthlyEveryMonth();
						}
										
					%>
					<div class="row">
						<div class="col-md-12">
							<div class="form-check">
								<input class="form-check-input configType" type="radio" name="configType" id="configType_monthly" value="monthly">
								<label class="form-check-label" for="configType_monthly"><b>Monthly</b></label>
							</div>
						</div>
					</div>
					<div class="configType_div" id="configType_div_monthly" style="margin-left: 30px;display: none;">
						<div class="row">
							<div class="col-md-1">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="monthlyDaySpecfic" id="monthlyDaySpecfic_date" value="false" <%=taskConfigDO.isBoolMonthlyDaySpecfic()?"":"checked" %>>
									<label class="form-check-label" for="monthlyDaySpecfic_date">Day</label>
								</div>
						</div>
						
						
							
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative ">
										<input type="text" id="monthlyEveryMonthDay" class="form-control" placeholder="" name="monthlyEveryMonthDay" value="<%=taskConfigDO.getMonthlyEveryMonthDay()%>" required="required">
									</div>
								</div>
							</div>
							<div class="">
								<label for="">Of Every</label>
							</div>
							
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative has-icon-left">
										<input type="text" id="monthlyEveryMonth1" class="form-control" placeholder="" name="monthlyEveryMonth1" value="<%=monthlyEveryMonth1%>" required="required">
									</div>
								</div>
							</div>
							
							<div class="col-md-1">
								<label for="">Month</label>
							</div>
						
						</div>
						<div class="row">
							<div class="col-md-1">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="monthlyDaySpecfic" id="monthlyDaySpecfic_day" value="true" <%=taskConfigDO.isBoolMonthlyDaySpecfic()?"checked":"" %>>
									<label class="form-check-label" for="monthlyDaySpecfic_day">The</label>
								</div>
							</div>
							
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative ">
										<select id="monthlyEveryWeek" class="form-control" placeholder="" name="monthlyEveryWeek" >
		                            		<option></option>
											<%=AppUtil.formOption( monthlyEveryWeekMap, taskConfigDO.getMonthlyEveryWeek())%>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative ">
										<select id="monthlyEveryWeekWeekday" class="form-control" placeholder="" name="monthlyEveryWeekWeekday" >
		                            		<option></option>
											<%=AppUtil.formOption( dayMap, taskConfigDO.getMonthlyEveryWeekWeekday())%>
										</select>
									</div>
								</div>
							</div>
							
							<div class="">
								<label for="">Of Every</label>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative ">
										<input type="text" id="monthlyEveryMonth2" class="form-control" placeholder="" name="monthlyEveryMonth2" value="<%=monthlyEveryMonth2%>" required="required">
									</div>
								</div>
							</div>
							<div class="">
								<label for="">Month</label>
							</div>
						</div>
					</div>
					
					<hr>
					
					<div class="row">
						<div class="col-md-12">
							<div class="form-check">
								<input class="form-check-input configType" type="radio" name="configType" id="configType_yearly" value="yearly">
								<label class="form-check-label" for="configType_yearly"><b>Yearly</b></label>
							</div>
						</div>
					</div>
					<div class="configType_div" id="configType_div_yearly" style="margin-left: 30px;display: none;">
						<div class="row">
							<div class="col-md-1">
								<label class="form-check-label" for="yearlyYearSpecific_year">Recur</label>
								<%-- <div class="form-check">
									<input class="form-check-input" type="radio" name="yearlyYearSpecific" id="yearlyYearSpecific_year" value="true" <%=taskConfigDO.isBoolYearlyYearSpecific()?"checked":"" %>>
									<label class="form-check-label" for="yearlyYearSpecific_year">Recur</label>
								</div> --%>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative ">
										<input type="text" id="yearlyEveryYear" class="form-control" placeholder="" name="yearlyEveryYear" value="<%=taskConfigDO.getYearlyEveryYear()%>" required="required">
									</div>
								</div>
							</div>
							<div class="">
								<label for="">Year(s)</label>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-1">
								<label class="form-check-label" for="yearlyYearSpecific_month">On The</label>
								<%-- <div class="form-check">
									<input class="form-check-input" type="radio" name="yearlyYearSpecific" id="yearlyYearSpecific_month" value="false" <%=taskConfigDO.isBoolYearlyYearSpecific()?"":"checked" %>>
									<label class="form-check-label" for="yearlyYearSpecific_month">On The</label>
								</div> --%>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative ">
										<select id="yearlyEveryWeek" class="form-control" placeholder="" name="yearlyEveryWeek" >
		                            		<option></option>
											<%=AppUtil.formOption( monthlyEveryWeekMap, taskConfigDO.getYearlyEveryWeek() )%>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative ">
										<select id="yearlyEveryWeekWeek" class="form-control" placeholder="" name="yearlyEveryWeekWeek" >
		                            		<option></option>
											<%=AppUtil.formOption( dayMap, taskConfigDO.getYearlyEveryWeekWeek() )%>
										</select>
									</div>
								</div>
							</div>
							
							<div class="">
								<label for="">Of </label>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative ">
										<select id="yearlyEveryMonth" class="form-control" placeholder="" name="yearlyEveryMonth" >
		                            		<option></option>
											<%=AppUtil.formOption( monthMap, ""+taskConfigDO.getYearlyEveryMonth() )%>
										</select>
									</div>
								</div>
							</div>
						</div>
						
					</div>
					
					<hr>
					
					<div class="row">
						<div class="col-md-12">
							<div class="form-check">
								<input class="form-check-input configType" type="radio" name="configType" id="configType_holidays" value="holidays">
								<label class="form-check-label" for="configType_holidays"><b>On Holiday Type</b></label>
							</div>
						</div>
					</div>
					<div class="configType_div" id="configType_div_holidays" style="margin-left: 30px;display: none;">
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
								<label for="timesheetinput1">Holiday Type</label>
									<div class="position-relative ">
										<select id="holidayIds" class="form-control" placeholder="" name="holidayIds" multiple="multiple" >
		                            		<option></option>
											<%=HolidayTypeCreationController.formHolidayTypeOption(""+taskConfigDO.getHolidayIds() )%>
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<hr>
					
					<div class="row">
						<div class="col-md-12">
							<div class="form-check">
								<input class="form-check-input configType" type="radio" name="configType" id="configType_event" value="event">
								<label class="form-check-label" for="configType_event"><b>No of days after the event execution in order</b></label>
							</div>
						</div>
					</div>
					<div class="configType_div" id="configType_div_event" style="margin-left: 30px;display: none;">
						
						<div class="row">
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative ">
										<input type="text" id="taskExeUnit" class="form-control" placeholder="" name="taskExeUnit" value="<%=taskConfigDO.getTaskExeUnit()%>"  >
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative ">
										<select id="taskExeUnitUom" class="form-control" placeholder="" name="taskExeUnitUom" >
		                            		<%=AppUtil.formOption(durationMap, ""+taskConfigDO.getTaskExeUnitUom()) %>
										</select>
									</div>
								</div>
							</div>
						</div>
						
						<%
						String refTaskConfigType=AppUtil.getNullToEmpty(taskConfigDO.getRefTaskConfigType());
						int refTaskConfigId1=0, refTaskConfigId2=0;
						if( refTaskConfigType.equals("after") ){ refTaskConfigId1=taskConfigDO.getRefTaskConfigId(); }
						else if( refTaskConfigType.equals("before") ){ refTaskConfigId2=taskConfigDO.getRefTaskConfigId(); }
						%>
						
						<div class="row" <%=refTaskConfigId1 %>>
							<div class="col-md-2">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="refTaskConfigType" id="refTaskConfigType_after" value="after" <%=refTaskConfigType.equals("after")?"checked":"" %>>
									<label class="form-check-label" for="refTaskConfigType_after">After</label>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative ">
										<select id="refTaskConfigId1" class="form-control" placeholder="" name="refTaskConfigId1" >
		                            		<option value="na">-- N/A--</option>
											<%=TaskConfigCreationController.taskOption(""+refTaskConfigId1, ""+taskConfigDO.getTaskConfigId())%>
										</select>
									</div>
								</div>
							</div>
							
						</div>
						
						<div class="row">
							<div class="col-md-2">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="refTaskConfigType" id="refTaskConfigType_before" value="before" <%=refTaskConfigType.equals("before")?"checked":"" %>>
									<label class="form-check-label" for="refTaskConfigType_before">Before</label>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative ">
										<select id="refTaskConfigId2" class="form-control" placeholder="" name="refTaskConfigId2" >
		                            		<option value="na">-- N/A--</option>
											<%=TaskConfigCreationController.taskOption(""+refTaskConfigId2, ""+taskConfigDO.getTaskConfigId())%>
										</select>
									</div>
								</div>
							</div>
						</div>
						
					</div>
					
					<hr>
					
					
					
					
					
					<div class="row">
						<div class="col-md-12">
							<label class="form-check-label" for="configType_na"><b>Ends After</b></label>
						</div>
					</div>
					<div class="" id="configType_div_na" style="margin-left: 30px;">
						<div class="row">
							<div class="col-md-2">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="noEndDate" id="noEndDate_false" value="false" <%=taskConfigDO.isBoolNoEndDate()?"":"checked" %>>
									<label class="form-check-label" for="noEndDate_false">No of Recurrance</label>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative ">
										<input type="text" id="endAfterNoOfRec" class="form-control" placeholder="" name="endAfterNoOfRec" value="<%=taskConfigDO.getEndAfterNoOfRec()%>" required="required">
									</div>
								</div>
							</div>
							
						</div>
						<div class="row">
							<div class="col-md-2">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="noEndDate" id="noEndDate_true" value="true" <%=taskConfigDO.isBoolNoEndDate()?"checked":"" %>>
									<label class="form-check-label" for="configType_na">No End Date</label>
								</div>
						</div>
							
						</div>
					</div>
					
					<hr>
					
					
					
					<div class="row">
						<div class="col-md-1">
							<label class="form-check-label" for="startType_time">Starts At</label>
						</div>
						
						<div class="col-md-2">
							<div class="form-group">
								<div class="position-relative ">
									<input type="text" id="startTime" class="form-control" placeholder="" name="startTime" value="<%=taskConfigDO.getStartTime()%>"  >
								</div>
							</div>
						</div>
						
					</div>
					<div class="row">
						<div class="col-md-1">
							<label class="form-check-label" for="startType_duration">Duration</label>
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<div class="position-relative ">
									<input type="text" id="duration" class="form-control" placeholder="" name="duration" value="<%=taskConfigDO.getDuration()%>" >
								</div>
							</div>
						</div>
						
						<div class="col-md-1">
							<div class="form-group">
								<div class="position-relative ">
									<select id="durationType" class="form-control" placeholder="" name="durationType" >
	                            		<option value="na">-- N/A--</option>
										<%=AppUtil.formOption( durationMap,""+taskConfigDO.getDurationType() )%>
									</select>
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


   
<div class="row esc_row esc_row_style" id="<%=formName%>_emp_esc_row" style="display: none;">
	<div class="col-md-4">
		<div class="form-group">
			<label for="">Department</label>
			<div class="position-relative has-icon-left">
				<select id="" class="form-control esc_department" placeholder="Department" name="" >
              			<option></option>
					<%=EmployeeCreationHandler.formDepartmentOption(""+taskConfigDO.getDepartment() )%>
				</select>
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group">
			<label for="">Designation</label>
			<div class="position-relative has-icon-left">
				<select id="" class="form-control esc_designation" placeholder="Designation" name="" >
                	<option></option>
					<%=EmployeeCreationHandler.formDesignationOption("", ""+taskConfigDO.getDesignation() )%>
				</select>
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group">
			<label for="timesheetinput1">Employee</label>
			<div class="position-relative has-icon-left">
				<select id="" class="form-control esc_empId" placeholder="Employee" name="" >
                        		<option></option>
					<%=EmployeeCreationHandler.formEmployeeOption(""+taskConfigDO.getEmpId() )%>
				</select>
			</div>
		</div>
	</div>
	<div class="col-md-2">
		<div class="form-group">
			<label for="timesheetinput1">Ticket Duration</label>
			<div class="position-relative ">
				<input type="text" id="" class="form-control esc_ticketDuration" placeholder="" name="" value=""  >
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group">
		<label for="timesheetinput1">&nbsp;</label>
			<div class="position-relative has-icon-left">
				<select id="" class="form-control esc_ticketDurationUom" placeholder="" name="" >
                 		<option value="na">--select--</option>
                 		<option value="day">Day</option>
                 		<option value="hour">Hour</option>
                 		<option value="minute">Minute</option>
				</select>
			</div>
		</div>
	</div>
</div>
   
   
  </body>
<script type="text/javascript">

$(document).ready( function(){
	$('#<%=formName %> #configType_<%=taskConfigDO.getConfigType()%>').prop('checked', 'checked');
	$('#configType_div_<%=taskConfigDO.getConfigType()%>').show();
	
	$('#<%=formName %>').on('change', '.configType', function(){
		var configType=$(this).val();
		$('.configType_div').hide();
		$('#configType_div_'+configType).show();
		

		$('#dailyEveryDay').attr("val","0"); $('#dailyEveryDay').val("0");
		$('#weeklyEveryWeek').attr("val","0");$('#weeklyEveryWeek').val("0");
		$('.weeklydays').prop("checked",false);
		
		$('#monthlyEveryMonthDay').attr("val","0");$('#monthlyEveryMonthDay').val("0");
		$('#monthlyEveryMonth1').attr("val","0");$('#monthlyEveryMonth1').val("0");
		$('#monthlyEveryMonth2').attr("val","0");$('#monthlyEveryMonth2').val("0");
		
		
		$('#monthlyEveryWeek option:selected').removeAttr("selected");	$('#monthlyEveryWeek option:selected').prop("selected",false);
		$('#monthlyEveryWeekWeekday option:selected').removeAttr("selected");	$('#monthlyEveryWeekWeekday option:selected').prop("selected",false);
		
		$('#yearlyEveryYear').attr("val","0");$('#yearlyEveryYear').val("0");
		
		$('#yearlyEveryWeek option:selected').removeAttr("selected");	$('#yearlyEveryWeek option:selected').prop("selected",false);
		$('#yearlyEveryWeekWeek option:selected').removeAttr("selected");	$('#yearlyEveryWeekWeek option:selected').prop("selected",false);
		$('#yearlyEveryMonth option:selected').removeAttr("selected");	$('#yearlyEveryMonth option:selected').prop("selected",false);
		
		
		$('#holidayIds option:selected').removeAttr("selected");	$('#holidayIds option:selected').prop("selected",false);
		
		
		
	});
	
	$('#<%=formName %>').on('click','#btn_escalationAdd', function(){
		$(this).css('pointer-events', 'none');
		var sno=$('#<%=formName %> #esc_rowCount').val(); if(isNaN(sno) || sno==''){ sno=0;}
		sno=parseInt(sno)+1;
		var escRow=$('#<%=formName%>_emp_esc_row').clone();
		$(escRow).removeAttr('style');
		$(escRow).find('.esc_department').attr('name', 'esc_department_'+sno);
		$(escRow).find('.esc_department').attr('id', 'esc_department_'+sno);
		
		$(escRow).find('.esc_designation').attr('name', 'esc_designation_'+sno);
		$(escRow).find('.esc_designation').attr('id', 'esc_designation_'+sno);
		
		$(escRow).find('.esc_empId').attr('name', 'esc_empId_'+sno);
		$(escRow).find('.esc_empId').attr('id', 'esc_empId_'+sno);
		
		$(escRow).find('.esc_ticketDuration').attr('name', 'esc_ticketDuration_'+sno);
		$(escRow).find('.esc_ticketDuration').attr('id', 'esc_ticketDuration_'+sno);
		
		$(escRow).find('.esc_ticketDurationUom').attr('name', 'esc_ticketDurationUom_'+sno);
		$(escRow).find('.esc_ticketDurationUom').attr('id', 'esc_ticketDurationUom_'+sno);
		
		$('#<%=formName %> #escalationContainer').append(escRow);
		$('#<%=formName %> #esc_rowCount').val(sno);
		$(this).css('pointer-events', '');
	});
	$('#<%=formName %>').on('click','#btn_escalationDelete', function(){
		if($("#<%=formName %> .esc_row").length>0){
			var sno=$('#<%=formName %> #esc_rowCount').val(); if(isNaN(sno) || sno==''){ sno=0;}
			sno=parseInt(sno)-1;
			$("#<%=formName %> .esc_row").last().remove();
			$('#<%=formName %> #esc_rowCount').val(sno);
		}
		
	});
	
	
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
}

$('#<%=formName %>').on('change', '#serviceName', function(){
	var service_name_value=$('#serviceName').val();
	console.log(service_name_value);
	$('#packageName option:selected').remove();
	$('#packageName').find('option').remove();
	$('#packageName').append('<option ></option>');
	$('#processName option:selected').remove();
	$('#processName').find('option').remove();
	$('#processName').append('<option ></option>');
	$.getJSON('commonajax?action=ajax&type=cmn_master&parentid='+service_name_value,function(data){
		$.each(data.result,function(index,item){
			console.log(item.id+":::"+item.value);
			$('#packageName').append('<option value='+item.id+'>'+item.value+'</option>');
		});
		
	});
});


$('#<%=formName %>').on('change', '#packageName', function(){
	var package_name_value=$('#packageName').val();
	console.log(package_name_value);
	$('#processName option:selected').remove();
	$('#processName').find('option').remove();
	$('#processName').append('<option ></option>');
	$.getJSON('commonajax?action=ajax&type=cmn_master&parentid='+package_name_value,function(data){
		$.each(data.result,function(index,item){
			$('#processName').append('<option value='+item.id+'>'+item.value+'</option>');
		});
		
	});
});


$('#<%=formName %>').on('change', '#departmentName', function(){
	var depart_name_value=$('#departmentName').val();
	
	$('#designation option:selected').remove();
	$('#designation').find('option').remove();
	$('#designation').append('<option ></option>');
	
	$('#empId option:selected').remove();
	$('#empId').find('option').remove();
	$('#empId').append('<option ></option>');
	
	$.getJSON('commonajax?action=ajax&type=cmn_master&parentid='+depart_name_value,function(data){
		
		$.each(data.result,function(index,item){
			console.log(item.id+":::"+item.value);
			$('#designation').append('<option value='+item.id+'>'+item.value+'</option>');
		});
		
	});
});


$('#<%=formName %>').on('change', '#designation', function(){
	var designation_value=$('#designation').val();
	var department_value=$('#departmentName').val();
	
	$('#empId option:selected').remove();
	$('#empId').find('option').remove();
	$('#empId').append('<option ></option>');
	$.getJSON('commonajax?action=ajax&type=employee&department='+department_value+'&designation='+designation_value,function(data){
		
		$('#<%=formName%> #empId').append(data.option);
	
		
	});
});


$('#<%=formName %>').on('change', '.esc_department', function(){
	
	var depart_id=this.id;
	var des_id=depart_id.replace('esc_department_','esc_designation_');
	var emp_id=depart_id.replace('esc_department_','esc_empId_');
	var department_value=$('#'+depart_id).val();
	
	$('#'+des_id+' option:selected').remove();
	$('#'+des_id).find('option').remove();
	$('#'+des_id).append('<option ></option>');
	$('#'+emp_id+' option:selected').remove();
	$('#'+emp_id).find('option').remove();
	$('#'+emp_id).append('<option ></option>');
	
	$.getJSON('commonajax?action=ajax&type=cmn_master&parentid='+department_value,function(data){
		$.each(data.result,function(index,item){
			$('#'+des_id).append('<option value='+item.id+'>'+item.value+'</option>');
		});
		
	});
});


$('#<%=formName %>').on('change', '.esc_designation', function(){
	var des_id=this.id;
	var dep_id=des_id.replace('esc_designation_','esc_department_');
	var emp_id=des_id.replace('esc_designation_','esc_empId_');
	var designation_value=$('#'+des_id).val();
	var department_value=$('#'+dep_id).val();
	
	$('#'+emp_id+' option:selected').remove();
	$('#'+emp_id).find('option').remove();
	$('#'+emp_id).append('<option ></option>');
	
	$.getJSON('commonajax?action=ajax&type=employee&department='+department_value+'&designation='+designation_value,function(data){
		
		$('#<%=formName%> #'+emp_id).append(data.option);
	
		
	});
	});


$('#<%=formName%> input[type=radio][name=refTaskConfigType]').change(function(){
	if(this.value=='before'){
		$('#refTaskConfigId1 option:selected').removeAttr("selected");	$('#refTaskConfigId1 option:selected').prop("selected",false);
	}else if(this.value=='after'){
		$('#refTaskConfigId2 option:selected').removeAttr("selected");	$('#refTaskConfigId2 option:selected').prop("selected",false);
	}
});



$('#<%=formName%> input[type=radio][name=noEndDate]').change(function(){
	if(this.value=='true'){
		$('#endAfterNoOfRec').attr("val","0");	$('#endAfterNoOfRec').val("0");
	}else if(this.value=='false'){
		
	}
});


$('#<%=formName%> input[type=radio][name=dailyEveryWeekDay]').change(function(){
	if(this.value=='true'){
		$('#dailyEveryDay').attr("val","0");	$('#dailyEveryDay').val("0");
	}else if(this.value=='false'){
		
	}
});


$('#<%=formName%> input[type=radio][name=monthlyDaySpecfic]').change(function(){
	if(this.value=='true'){
		$('#monthlyEveryMonthDay').attr("val","0");	$('#monthlyEveryMonthDay').val("0");
		$('#monthlyEveryMonth1').attr("val","0");	$('#monthlyEveryMonth1').val("0");
	}else if(this.value=='false'){
		$('#monthlyEveryWeek option:selected').removeAttr("selected");	$('#monthlyEveryWeek option:selected').prop("selected",false);
		$('#monthlyEveryWeekWeekday option:selected').removeAttr("selected");	$('#monthlyEveryWeekWeekday option:selected').prop("selected",false);
		$('#monthlyEveryMonth2').attr("val","0");	$('#monthlyEveryMonth2').val("0");
	}
});


$('#<%=formName%> input[type=radio][name=yearlyYearSpecific]').change(function(){
	if(this.value=='true'){
		$('#yearlyEveryWeekWeek option:selected').removeAttr("selected");	$('#yearlyEveryWeekWeek option:selected').prop("selected",false);
		$('#yearlyEveryWeek option:selected').removeAttr("selected");	$('#yearlyEveryWeek option:selected').prop("selected",false);
		$('#yearlyEveryMonth option:selected').removeAttr("selected");	$('#yearlyEveryMonth option:selected').prop("selected",false);
	}else if(this.value=='false'){
		
		$('#yearlyEveryYear').attr("val","0");	$('#yearlyEveryYear').val("0");
	}
});



</script>
</html>