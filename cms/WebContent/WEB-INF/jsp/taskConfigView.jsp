<%@page import="com.cms.holiday.dao.AdminHolidayTypeDAO"%>
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
if(!taskConfigDO.getEmpId().equals("0")) subqry+=" and a.emp_id="+taskConfigDO.getEmpId()+" ";
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
				<h4 class="modal-title" id="myModalLabel16">Task Configuration Display</h4>
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
								<%=AppUtil.getCommonMasterValueById(serviceDO.getCmnMasterId() ) %>
	                    </div>
                    </div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Package Name</label>
						<div class="col-md-8">
								<%=AppUtil.getCommonMasterValueById(packageDO.getCmnMasterId() ) %>
	                    </div>
                    </div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Process Name</label>
						<div class="col-md-8">
								<%=AppUtil.getCommonMasterValueById(processDO.getCmnMasterId() ) %>
	                    </div>
                    </div>
						</div>
					</div>
					
					<div class="row mb-2">
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Task Config Name</label>
						<div class="col-md-8">
	                       <%=AppUtil.getNullToEmpty( taskConfigDO.getTaskConfigName() )%>
	                    </div>
                    </div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Execution Order</label>
						<div class="col-md-8">
	                       <%=taskConfigDO.getExeOrder()%>
	                    </div>
                    </div>
						</div>
						
					</div>
					
					<div class="row mb-2">
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Department</label>
						<div class="col-md-8">
								<%=AppUtil.getCommonMasterValueById(Integer.parseInt(taskConfigDO.getDepartment())  ) %>
	                    </div>
                    </div>
						</div>
						<div class="col-md-4">
							
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Designation</label>
						<div class="col-md-8">
									<%=AppUtil.getCommonMasterValueById(Integer.parseInt(taskConfigDO.getDesignation())  ) %>
                    </div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Employee</label>
						<div class="col-md-8">
							
							<%=AppUtil.formDisplay(emp_name_map, ""+taskConfigDO.getEmpId()) %>
	                    </div>
                    </div>
						</div>
						</div>
						<div class="row mb-2">
								<div class="col-md-4">
								<div class="form-group">
						<label class="col-md-4 label-control" for="projectinput5">Ticket Duration</label>
						<div class="col-md-4">
                       <%=taskConfigDO.getTicketDuration() %>
	                    </div>
	                    <div class="col-md-4">
								<div class="form-group">
	                  		<%=AppUtil.formDisplay(durationMap, ""+taskConfigDO.getTicketDurationUom()) %>
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
						
						if(!escalationChild.getEmpId().equalsIgnoreCase("0")) subqry=subqry+" and a.emp_id="+escalationChild.getEmpId();
						esc_emp_name_map=AdmEmployeeMasterDAO.EmpNameMapBySubry(null,subqry);
						 }
						if(esc_emp_name_map==null) { esc_emp_name_map=new HashMap<String, String>(); }
						%>
						<div class="row esc_row esc_row_style">
							<div class="col-md-4">
								<div class="form-group">
									<label for="">Department</label>
									<div class="position-relative has-icon-left">
											<%=AppUtil.getCommonMasterValueById(Integer.parseInt(escalationChild.getDepartment())  ) %>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="">Designation</label>
									<div class="position-relative has-icon-left">
											<%=AppUtil.getCommonMasterValueById(Integer.parseInt(escalationChild.getDesignation())  ) %>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="">Employee</label>
									<div class="position-relative has-icon-left">
											
											<%=AppUtil.formDisplay(esc_emp_name_map, ""+taskConfigDO.getEmpId()) %>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label for="timesheetinput1">Ticket Duration</label>
									<div class="position-relative ">
										<%=escalationChild.getTicketDuration() %>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
								<label for="timesheetinput1">&nbsp;</label>
									<div class="position-relative has-icon-left">
					                  		<%=AppUtil.formDisplay(durationMap, ""+escalationChild.getTicketDurationUom()) %>
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
					
					
					
					
					<%
					
					String Recrence_det="";
					String config_type=" No of days after the event execution in order ";										
										
									if(taskConfigDO.getConfigType().equalsIgnoreCase("daily")){
										config_type=" Daily";
										if(taskConfigDO.isBoolDailyEveryWeekDay()){
											Recrence_det=" EVERY "+taskConfigDO.getDailyEveryDay()+ " DAY.";
										}else if(taskConfigDO.isBoolDailyEveryWeekDay()){
											Recrence_det=Recrence_det+" Every week Day ";
										}
									}
									
									else if(taskConfigDO.getConfigType().equalsIgnoreCase("weekly")){config_type=" Weekly";
										Recrence_det=Recrence_det+" Every "+taskConfigDO.getWeeklyEveryWeek()+" Week(s) on ";
										
										Recrence_det=Recrence_det+ " "+AppUtil.formDisplay(dayMap, taskConfigDO.getWeeklyWeekDay());
										
								
									}
									
									else if(taskConfigDO.getConfigType().equalsIgnoreCase("monthly")){ config_type=" Monthly";
										int monthlyEveryMonth1=0;
										int monthlyEveryMonth2=0;
										if( taskConfigDO.isBoolMonthlyDaySpecfic() ){
											monthlyEveryMonth2=taskConfigDO.getMonthlyEveryMonth();
										}else{
											monthlyEveryMonth1=taskConfigDO.getMonthlyEveryMonth();
										}
										
										
										 if(taskConfigDO.isBoolMonthlyDaySpecfic()){
											 Recrence_det=Recrence_det+ " Day "+taskConfigDO.getMonthlyEveryMonthDay()+" Of Every "+monthlyEveryMonth1+" Month";
										 }						 
										 else{
											 Recrence_det=Recrence_det+ " The "+AppUtil.formDisplay( monthlyEveryWeekMap, taskConfigDO.getMonthlyEveryWeek()) + " "+AppUtil.formDisplay( dayMap, taskConfigDO.getMonthlyEveryWeekWeekday())+" Of Every";
											 Recrence_det=Recrence_det+" "+monthlyEveryMonth2+" Month";
										 }
									}
									
									else if(taskConfigDO.getConfigType().equalsIgnoreCase("yearly")){ config_type=" Yearly";
										 Recrence_det=Recrence_det+" Recur "+taskConfigDO.getYearlyEveryYear()+" Year(s) On The "+AppUtil.formDisplay(monthlyEveryWeekMap, taskConfigDO.getYearlyEveryWeek() )+"";
										 Recrence_det=Recrence_det+" "+AppUtil.formDisplay( dayMap, taskConfigDO.getYearlyEveryWeekWeek() )+" of "+AppUtil.formDisplay( monthMap, ""+taskConfigDO.getYearlyEveryMonth() );
									}
									else if(taskConfigDO.getConfigType().equalsIgnoreCase("holidays")){ config_type=" On Holiday Type ";
										
										Map<String, String> dayMap2=AdminHolidayTypeDAO.getHolidayTypeMap(null, " and holiday_type_id in ("+taskConfigDO.getHolidayIds()+")");
										if(dayMap2==null){dayMap2=new HashMap<String,String> ();}
										 Recrence_det=Recrence_det+""+AppUtil.formDisplay( dayMap, taskConfigDO.getHolidayIds() );
									}
									else if(taskConfigDO.getConfigType().equalsIgnoreCase("event")){ config_type=" No of days after the event execution in order ";
									
									String refTaskConfigType=AppUtil.getNullToEmpty(taskConfigDO.getRefTaskConfigType());
									int refTaskConfigId1=0, refTaskConfigId2=0;
									if( refTaskConfigType.equals("after") ){ refTaskConfigId1=taskConfigDO.getRefTaskConfigId(); }
									else if( refTaskConfigType.equals("before") ){ refTaskConfigId2=taskConfigDO.getRefTaskConfigId(); }
									 Recrence_det=Recrence_det+""+taskConfigDO.getTaskExeUnit()+" "+AppUtil.formDisplay(durationMap, ""+taskConfigDO.getTaskExeUnitUom());
									 
									 if(refTaskConfigType.equals("after")){
										 Recrence_det=Recrence_det+""+TaskConfigCreationController.taskDisplay(""+refTaskConfigId1, ""+taskConfigDO.getTaskConfigId());
									 }else if(refTaskConfigType.equals("before")){
										 Recrence_det=Recrence_det+""+TaskConfigCreationController.taskDisplay(""+refTaskConfigId2, ""+taskConfigDO.getTaskConfigId());
									 }
									 
									}
					%>

					<div class="col-md-6">
								<div class="form-group">
									<label for="timesheetinput2"><%=config_type %></label>
									<div class="position-relative has-icon-left">
											<%=Recrence_det%>
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
							<%if(taskConfigDO.isBoolNoEndDate()){ %>
								<div class="form-check">
								
									
									<label class="form-check-label" for="noEndDate_false">No of Recurrance</label>
								
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<div class="position-relative ">
										<%=taskConfigDO.getEndAfterNoOfRec()%>
									</div>
								</div>
							</div>
							<%}else if(taskConfigDO.isBoolNoEndDate()) {%>
							<label class="form-check-label" for="configType_na">No End Date</label>
							<%} %>
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
									<%=taskConfigDO.getStartTime()%>
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
									<%=taskConfigDO.getDuration()%>
								</div>
							</div>
						</div>
						
						<div class="col-md-1">
							<div class="form-group">
								<div class="position-relative ">
										<%=AppUtil.formDisplay( durationMap,""+taskConfigDO.getDurationType() )%>
								</div>
							</div>
						</div>
					</div>
				
					
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn" data-dismiss="modal">Cancel</button>
			</div>
		</form>
	</div>
</div>


   
<%-- <div class="row esc_row esc_row_style" id="<%=formName%>_emp_esc_row" style="display: none;">
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
					<%=EmployeeCreationHandler.formDesignationOption(""+taskConfigDO.getDesignation() )%>
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
    --%>
   
  </body>
<script type="text/javascript">




</script>
</html>