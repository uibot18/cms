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
durationMap.put("hour", "Hour");
durationMap.put("minute", "Minute");

%>
<!DOCTYPE html>
<html class="loading" lang="en" data-textdirection="ltr">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="">
    <title>CMS-Service</title>
    <link rel="apple-touch-icon" href="./resource/app-assets/images/ico/apple-icon-120.png">
    <link rel="shortcut icon" type="image/x-icon" href="./resource/app-assets/images/ico/favicon.ico">
  </head>
  <body class="vertical-layout vertical-menu 2-columns   menu-expanded fixed-navbar" data-open="click" data-menu="vertical-menu" data-color="bg-gradient-x-purple-blue" data-col="2-columns">
   <%@include file="header.jsp" %>
   
   <!-- Content Start -->
  <div class="app-content content">
      <div class="content-wrapper">
        <div class="content-wrapper-before"></div>
        <div class="content-header row">
          <div class="content-header-left col-md-4 col-12 mb-2">
            <h3 class="content-header-title">Task Configuration</h3>
          </div>
          <div class="content-header-right col-md-8 col-12">
            <div class="breadcrumbs-top float-md-right">
              <div class="breadcrumb-wrapper mr-1">
               <!--  <ol class="breadcrumb">
                  <li class="breadcrumb-item"><a href="index.html">Home</a>
                  </li>
                  <li class="breadcrumb-item"><a href="#">Dashboard</a>
                  </li>
                  <li class="breadcrumb-item active">Customer add
                  </li>
                </ol> -->
              </div>
            </div>
          </div>
        </div>
        <div class="content-body">
			<section class="row">
			    <div class="col-md-12 col-sm-12">
			        <div id="with-header" class="card">
			            <div class="card-header">
			                <h4 class="card-title">Task Configuration Add Form</h4>
			                <a class="heading-elements-toggle"><i class="la la-ellipsis-v font-medium-3"></i></a>
			                <div class="heading-elements">
			                    <ul class="list-inline mb-0">
			                        <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
			                        <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
			                        <li><a data-action="close"><i class="ft-x"></i></a></li>
			                    </ul>
			                </div>
			            </div>
			            <div class="card-content collapse show">
			                <div class="card-body border-top-blue-grey border-top-lighten-5 ">
			                    <form class="form" action="taskConfig" method="post" id="<%=formName%>">
				                    <input type="hidden" name="action" value="save">
				                     <input type="hidden" name="taskConfigId" value="<%=taskConfigDO.getTaskConfigId()%>">
			                    
									<div class="form-body">
										
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label for="timesheetinput1">Service Name</label>
													<div class="position-relative has-icon-left">
														<select id="serviceName" class="form-control" placeholder="Service Name" name="serviceName" >
						                            		<option></option>
															<%=ServiceCreationController.serviceOption("", ""+serviceDO.getCmnMasterId() ) %>
														</select>
													</div>
												</div>
											</div>
											
											<div class="col-md-4">
												<div class="form-group">
													<label for="timesheetinput1">Package Name</label>
													<div class="position-relative has-icon-left">
														<select id="packageName" class="form-control" placeholder="Package Name" name="packageName" >
						                            		<option></option>
															<%=PackageCreationController.packageOption("", ""+packageDO.getCmnMasterId() ) %>
														</select>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label for="timesheetinput1">Process Name</label>
													<div class="position-relative has-icon-left">
														<select id="processName" class="form-control" placeholder="Process Name" name="processName" >
						                            		<option></option>
															<%=ProcessCreationController.processOption("", ""+processDO.getCmnMasterId() ) %>
														</select>
													</div>
												</div>
											</div>
										</div>
										
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label for="timesheetinput1">Task Config Name</label>
													<div class="position-relative has-icon-left">
														<input type="text" id="taskConfigName" class="form-control" placeholder="Task Config Name" name="taskConfigName" value="<%=AppUtil.getNullToEmpty( taskConfigDO.getTaskConfigName() )%>" required="required">
														<div class="form-control-position">
															<i class="fas fa-unlock-alt"></i>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label for="timesheetinput1">Execution Order</label>
													<div class="position-relative has-icon-left">
														<input type="text" id="executionOrder" class="form-control" placeholder="Execution Order" name="executionOrder" value="<%=taskConfigDO.getExeOrder()%>" required="required">
														<div class="form-control-position">
															<i class="fas fa-unlock-alt"></i>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label for="timesheetinput1">Department</label>
													<div class="position-relative has-icon-left">
														<select id="departmentName" class="form-control" placeholder="Department" name="department" >
						                            		<option></option>
															<%=EmployeeCreationHandler.formDepartmentOption(""+taskConfigDO.getDepartment() )%>
														</select>
													</div>
												</div>
											</div>
										</div>
										
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label for="timesheetinput1">Designation</label>
													<div class="position-relative has-icon-left">
														<select id="designation" class="form-control" placeholder="Designation" name="designation" >
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
														<select id="empId" class="form-control" placeholder="Employee" name="empId" >
						                            		<option></option>
															<%=EmployeeCreationHandler.formEmployeeOption(""+taskConfigDO.getEmpId() )%>
														</select>
													</div>
												</div>
											</div>
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
													<input type="checkbox" name="weeklyWeekDay" class="form-check-input" value="<%=day%>" id="weeklyWeekDay_<%=day%>"  <%=chk%> >
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
													<div class="form-check">
														<input class="form-check-input" type="radio" name="yearlyYearSpecific" id="yearlyYearSpecific_year" value="true" <%=taskConfigDO.isBoolYearlyYearSpecific()?"checked":"" %>>
														<label class="form-check-label" for="yearlyYearSpecific_year">Recur</label>
													</div>
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
													<div class="form-check">
														<input class="form-check-input" type="radio" name="yearlyYearSpecific" id="yearlyYearSpecific_month" value="false" <%=taskConfigDO.isBoolYearlyYearSpecific()?"":"checked" %>>
														<label class="form-check-label" for="yearlyYearSpecific_month">On The</label>
													</div>
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
												<label class="form-check-label" for="configType_na"><b>Ends After</b></label>
											</div>
										</div>
										<div class="configType_div" id="configType_div_na" style="margin-left: 30px;">
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
											<div class="col-md-2">
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
											<div class="col-md-2">
												<label class="form-check-label" for="startType_duration">Duration</label>
											</div>
											<div class="col-md-2">
												<div class="form-group">
													<div class="position-relative ">
														<input type="text" id="duration" class="form-control" placeholder="" name="duration" value="<%=taskConfigDO.getDuration()%>" >
													</div>
												</div>
											</div>
											
											<div class="col-md-2">
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
									
									<div class="form-actions right">
										<button type="submit" class="btn btn-primary">
											<i class="fa fa-check-square-o"></i> Save
										</button>
										<button type="button" class="btn btn-danger mr-1" onclick="<%=formName %>reset()">
											<i class="ft-x"></i> Reset
										</button>
									</div>
								</form>
			                </div>
			            </div>
			        </div>
			    </div>
			</section>
			
        </div>
      </div>
    </div>
   <!-- Content End  -->
   
   <%@include file="footer.jsp" %>
  </body>
<script type="text/javascript">

$(document).ready( function(){
	$('#<%=formName %> #configType_<%=taskConfigDO.getConfigType()%>').prop('checked', 'checked');
	$('#configType_div_<%=taskConfigDO.getConfigType()%>').show();
	
	$('#<%=formName %>').on('change', '.configType', function(){
		var configType=$(this).val();
		$('.configType_div').hide();
		$('#configType_div_'+configType).show();
	});
});

function <%=formName %>reset(){
	$('#<%=formName %> #serviceName').val('');$('#<%=formName %> #serviceName').attr('value', '');
}
</script>
</html>