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
    
    <style type="text/css">
		.esc_row_style{
		    border: 1px solid #cacfe7;
    		padding: 4px;
    		margin: 1px;
		}    
    </style>
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
											
										</div>
										
										<div class="row">
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
													<div class="col-md-2">
													<div class="form-group">
														<label for="timesheetinput1">Ticket Duration</label>
														<div class="position-relative ">
															<input type="text" id="ticketDuration" class="form-control" placeholder="" name="ticketDuration" value="<%=taskConfigDO.getTicketDuration() %>"  >
														</div>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
													<label for="timesheetinput1">&nbsp;</label>
														<div class="position-relative has-icon-left">
															<select id="ticketDurationUom" class="form-control" placeholder="" name="ticketDurationUom" >
										                  		<option value="na">--select--</option>
										                  		<%=AppUtil.formOption(durationMap, ""+taskConfigDO.getTicketDurationUom()) %>
															</select>
														</div>
													</div>
												</div>
										</div>
										
										<hr>
										
										<div class="row">
											<div class="col-md-12">
												<label><b>Escalation</b></label><button type="button" id="btn_escalationAdd">+</button>&nbsp;<button type="button" id="btn_escalationDelete">-</button>
												<input type="hidden" name="esc_rowCount" id="esc_rowCount" value="<%=escalationChildList.size()%>">
											</div>
										</div>
										<div id="escalationContainer">
										
										<%
										int sno=1;
										for(TaskConfigEscalationChildDO escalationChild : escalationChildList){
											
											%>
											<div class="row esc_row esc_row_style">
												<div class="col-md-4">
													<div class="form-group">
														<label for="">Department</label>
														<div class="position-relative has-icon-left">
															<select id="esc_departmentName_<%=sno %>" class="form-control" placeholder="Department" name="esc_department_<%=sno %>" >
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
															<select id="esc_designation_<%=sno %>" class="form-control" placeholder="Designation" name="esc_designation_<%=sno %>" >
							                            		<option></option>
																<%=EmployeeCreationHandler.formDesignationOption(""+escalationChild.getDesignation() )%>
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
																<%=EmployeeCreationHandler.formEmployeeOption(""+escalationChild.getEmpId() )%>
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
									
										<hr>
										
										<div class="row">
											<div class="col-md-12">
												<label class="form-check-label" for="configType_na"><b>No of days after the event execution in order</b></label>
											</div>
										</div>
										<div class="" id="configType_div_na" style="margin-left: 30px;">
											
											
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
										
										<div class="form-actions right">
											<button type="submit" class="btn btn-primary">
												<i class="fa fa-check-square-o"></i> Save
											</button>
											<button type="button" class="btn btn-danger mr-1" onclick="<%=formName %>reset()">
												<i class="ft-x"></i> Reset
											</button>
										</div>
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
			console.log(item.id+":::"+item.value);
			$('#processName').append('<option value='+item.id+'>'+item.value+'</option>');
		});
		
	});
});



</script>
</html>