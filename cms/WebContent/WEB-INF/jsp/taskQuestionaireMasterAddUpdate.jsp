<%@page import="com.cms.questionnaire.bean.TaskQuestionaireChildDO"%>
<%@page import="com.cms.questionnaire.bean.TaskQuestionaireDetailsDO"%>
<%@page import="java.util.List"%>
<%@page import="com.cms.task.config.handler.TaskConfigCreationController"%>
<%@page import="com.cms.common.master.CmnGroupName"%>
<%@page import="com.cms.common.master.bean.CommonMasterDO"%>
<%@page import="java.util.Random"%>
<%@page import="com.cms.holiday.bean.AdminHolidayTypeDO"%>
<%@page import="com.application.util.AppUtil"%>
<%@page import="com.cms.common.master.bean.CommonDocumentStoreDO"%>
<%@page import="com.cms.finance.bean.FinancePartyContactDetailsDO"%>
<%@page import="com.cms.finance.bean.FinancePartyAddressDetailsDO"%>
<%@page import="com.cms.finance.bean.FinancePartyPersonalDetailsDO"%>
<%@page import="com.cms.finance.bean.FinanceLedgerMasterDO"%>
<%@page import="com.cms.customer.bean.SalesCustomerMasterDO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cms.user.login.bean.LoginMasterBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
CommonMasterDO serviceDO=(CommonMasterDO)request.getAttribute("serviceDO");
if( serviceDO==null ){ serviceDO=new  CommonMasterDO(); } 

String formName="quest_frm_"+Math.abs( new Random().nextInt(9999) );

int taskConfigId=AppUtil.getNullToInteger( (String)request.getAttribute("taskConfigId") );
List<TaskQuestionaireDetailsDO> taskQuestionaireList=(List<TaskQuestionaireDetailsDO>)request.getAttribute("taskQuestionaireList");
if(taskQuestionaireList==null){ taskQuestionaireList=new ArrayList<TaskQuestionaireDetailsDO>(); }

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
		.questionnaire_row_style{
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
            <h3 class="content-header-title">Task Questionnaire</h3>
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
			                <h4 class="card-title">Task Questionnaire Add Form</h4>
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
			                    <form class="form" action="taskQuestionnaire" method="post" id="<%=formName%>">
				                    <input type="hidden" name="action" value="save">
			                    
									<div class="form-body">
										
										<div class="row">
											<div class="col-md-6">
						                        <div class="form-group">
													<label for="timesheetinput1">Task Name</label>
													<div class="position-relative has-icon-left">
														<select id="taskConfigId" class="form-control" placeholder="Task Name" name="taskConfigId" >
						                            		<option>-- Please Select--</option>
															<%=TaskConfigCreationController.taskOption(""+taskConfigId, "") %>
														</select>
													</div>
												</div>
					                        </div>
					                        
										</div>
										
										<div class="row">
											<div class="col-md-12">
												<label><b>Questionnaire&nbsp;&nbsp;</b></label>
												<button type="button" id="btn_questAdd">+</button>&nbsp;
												<button type="button" id="btn_questDelete">-</button>
												<input type="hidden" name="questionnaire_rowCount" id="questionnaire_rowCount" value="<%=taskQuestionaireList.size()%>">
											</div>
										</div>
										<div id="questionnaire_container">
										<%
										int minRow=1;
										for(TaskQuestionaireDetailsDO questDetDO : taskQuestionaireList){
										
										%>
											<div class="questionnaire_row_style questionnaire_row" id="questionnaire_row_<%=minRow %>">
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label for="timesheetinput1">Questionnaire Name</label>
															<div class="position-relative has-icon-left">
																<input type="text" id="questionnaireName_<%=minRow %>" class="form-control" placeholder="Questionnaire Name" name="questionnaireName_<%=minRow %>" value="<%=questDetDO.getQuestionaireName() %>" required="required">
																<input type="hidden" id="questionnaireId_<%=minRow %>" class="form-control" name="questionnaireId_<%=minRow %>" value="<%=questDetDO.getQuestionaireId()%>">
																<div class="form-control-position">
																	<i class="fas fa-unlock-alt"></i>
																</div>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="row" style="margin-top: 30px;">
															<div class="col-md-3">
																<div class="form-check">
																	<label class="form-check-label" for="refTaskConfigType_after"><b>Answer Type</b></label>
																</div>
															</div>
															<%
															String answerType=questDetDO.getAnswerType();
															if(answerType.isEmpty() || answerType.equals("na")){ answerType="single"; }
															%>
															<div class="col-md-3">
																<div class="form-check">
																	<input class="form-check-input" type="radio" name="answerType_<%=minRow %>" id="answerType_single" value="single" <%=questDetDO.getAnswerType().equals("single")?"checked":"" %>>
																	<label class="form-check-label" for="answerType_single">Single</label>
																</div>
															</div>
															<div class="col-md-3">
																<div class="form-check">
																	<input class="form-check-input" type="radio" name="answerType_<%=minRow %>" id="answerType_multiselect" value="multiselect" <%=questDetDO.getAnswerType().equals("multiselect")?"checked":"" %>>
																	<label class="form-check-label" for="refTaskConfigType_after">Multi</label>
																</div>
															</div>
															<div class="col-md-3">
																<div class="form-check">
																	<input class="form-check-input" type="radio" name="answerType_<%=minRow %>" id="answerType_user_text" value="user_text" <%=questDetDO.getAnswerType().equals("user_text")?"checked":"" %>>
																	<label class="form-check-label" for="refTaskConfigType_after">User Text</label>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div id="optionDiv">
													<div class="row" style="margin: 3px;" >
														<div class="col-md-6">
															<div class="form-check">
																<label class="form-check-label" for=""><b>Options</b></label>
																<button type="button" class="btn_optionAdd" data-row="<%=minRow %>">+</button>&nbsp;
																<button type="button" class="btn_optionDelete" data-row="<%=minRow %>">-</button>
																<input type="hidden" name="optionRowCount_<%=minRow %>" id="optionRowCount_<%=minRow %>" class="optionRowCount" value="<%=questDetDO.getQuestionaryChildList().size()%>">
															</div>
														</div>
													</div>
													<div class="row optionContainer"> 
													<%
													int subRow=1;
													for(TaskQuestionaireChildDO childDO : questDetDO.getQuestionaryChildList()){
														
													%>
														<div class="col-md-3 optionRow">
															<div class="form-group">
																<div class="position-relative has-icon-left">
																	<input type="text" id="taskOption_<%=minRow %>_<%=subRow %>" class="form-control taskOption" placeholder="Option Name" name="taskOption_<%=minRow %>_<%=subRow %>" value="<%=childDO.getOption() %>" required="required">
																	<div class="form-control-position">
																		<i class="fas fa-unlock-alt"></i>
																	</div>
																</div>
															</div>
														</div>
													<%subRow++;
													} %>
													</div>
												</div>	
											</div>
										<%
										minRow++;
										} 
										%>
										
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
   
<div class="questionnaire_row_style questionnaire_row" id="<%=formName%>_tmp_questRow" style="display: none;">
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label for="timesheetinput1">Questionnaire Name</label>
				<div class="position-relative has-icon-left">
					<input type="text" id="" class="form-control questionnaireName" placeholder="Questionnaire Name" name="" value="" required="required">
					<div class="form-control-position">
						<i class="fas fa-unlock-alt"></i>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="row" style="margin-top: 30px;">
				<div class="col-md-3">
					<div class="form-check">
						<label class="form-check-label" for=""><b>Answer Type</b></label>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-check">
						<input class="form-check-input answerType answerType_single" type="radio" name="" id="" value="single" checked="checked">
						<label class="form-check-label" for="">Single</label>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-check">
						<input class="form-check-input answerType answerType_multiselect" type="radio" name="" id="" value="multiselect">
						<label class="form-check-label" for="">Multi</label>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-check">
						<input class="form-check-input answerType answerType_user_text" type="radio" name="" id="" value="user_text">
						<label class="form-check-label" for="">User Text</label>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="optionDiv">
		<div class="row" style="margin: 3px;" >
			<div class="col-md-6">
				<div class="form-check">
					<label class="form-check-label" for=""><b>Options</b></label>
					<button type="button" class="btn_optionAdd" data-row="">+</button>&nbsp;
					<button type="button" class="btn_optionDelete" data-row="">-</button>
					<input type="hidden" class="optionRowCount" value="">
				</div>
			</div>
		</div>
		<div class="row optionContainer" >
		</div>
	</div>	
</div>

<div class="col-md-3 optionRow" id="<%=formName%>_tmp_optionRow" style="display: none;">
	<div class="form-group">
		<div class="position-relative has-icon-left">
			<input type="text" id="" class="form-control taskOption" placeholder="Option Name" name="" value="" required="required">
			<div class="form-control-position">
				<i class="fas fa-unlock-alt"></i>
			</div>
		</div>
	</div>
</div>
   
  </body>
<script type="text/javascript">

$(document).ready( function(){
	
	$('#<%=formName %>').on('click','#btn_questAdd', function(){
		$(this).css('pointer-events', 'none');
		var sno=$('#<%=formName %> #questionnaire_rowCount').val(); if(isNaN(sno) || sno==''){ sno=0;}
		sno=parseInt(sno)+1;
		
		var cloneObj=$('#<%=formName%>_tmp_questRow').clone();
		$(cloneObj).removeAttr('style');
		$(cloneObj).attr('id', 'questionnaire_row_'+sno);
		$(cloneObj).find('.questionnaireName').attr('name', 'questionnaireName_'+sno);
		$(cloneObj).find('.questionnaireName').attr('id', 'questionnaireName_'+sno);
		
		$(cloneObj).find('.answerType').attr('name', 'answerType_'+sno);
		//$(cloneObj).find('.answerType').attr('id', 'esc_designation_'+sno);
		
		$(cloneObj).find('.btn_optionAdd').attr('data-row', sno);
		$(cloneObj).find('.btn_optionDelete').attr('data-row', sno);
		
		$(cloneObj).find('.optionRowCount').attr('name', 'optionRowCount_'+sno);
		$(cloneObj).find('.optionRowCount').attr('id', 'optionRowCount_'+sno);
		$(cloneObj).find('.optionRowCount').val(0);
		
		$('#<%=formName %> #questionnaire_container').append(cloneObj);
		$('#<%=formName %> #questionnaire_rowCount').val(sno);
		$(this).css('pointer-events', '');
	});
	
	$('#<%=formName %>').on('click','#btn_questDelete', function(){
		if($("#<%=formName %> .questionnaire_row").length>0){
			var sno=$('#<%=formName %> #questionnaire_rowCount').val(); if(isNaN(sno) || sno==''){ sno=0;}
			sno=parseInt(sno)-1;
			$("#<%=formName %> .questionnaire_row").last().remove();
			$('#<%=formName %> #questionnaire_rowCount').val(sno);
			
		}
		
	});
	
	$('#<%=formName %>').on('click','.btn_optionAdd', function(){
		$(this).css('pointer-events', 'none');
		var mainRow=$(this).attr('data-row');
		var subRow=$('#<%=formName %> #optionRowCount_'+mainRow).val(); if(isNaN(subRow) || subRow==''){ subRow=0;}
		subRow=parseInt(subRow)+1;
		
		var cloneObj=$('#<%=formName%>_tmp_optionRow').clone();
		$(cloneObj).removeAttr('style'); $(cloneObj).removeAttr('id');
		$(cloneObj).find('.taskOption').attr('name', 'taskOption_'+mainRow+'_'+subRow);
		$(cloneObj).find('.taskOption').attr('id', 'taskOption_'+mainRow+'_'+subRow);
		
		$('#<%=formName %> #questionnaire_row_'+mainRow+' .optionContainer').append(cloneObj);
		$('#<%=formName %> #optionRowCount_'+mainRow).val(subRow);
		$(this).css('pointer-events', '');
	});
	
	$('#<%=formName %>').on('click','.btn_optionDelete', function(){
		var mainRow=$(this).attr('data-row');
		if($('#<%=formName %> #questionnaire_row_'+mainRow+' .optionRow').length>0){
			var subRow=$('#<%=formName %> #optionRowCount_'+mainRow).val(); if(isNaN(subRow) || subRow==''){ subRow=0;}
			subRow=parseInt(subRow)-1;
			$('#<%=formName %> #questionnaire_row_'+mainRow+' .optionRow').last().remove();
			$('#<%=formName %> #optionRowCount_'+mainRow).val(subRow);
			alert(subRow);
		}
	});
	
});

function <%=formName %>reset(){
	$('#<%=formName %> #serviceName').val('');$('#<%=formName %> #serviceName').attr('value', '');
}
</script>
</html>