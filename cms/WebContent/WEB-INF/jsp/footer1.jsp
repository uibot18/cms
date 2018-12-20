<!-- footer -->
 <footer class="footer text-center">
       All Rights Reserved by ui-bot. Designed and Developed by <a href="">ui-bot</a>.
</footer>   
<!-- End footer -->

<!-- All Jquery -->
    
<script src="./static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="./static/assets/libs/jquery-validation/dist/jquery.validate.min.js"></script>
<script src="./static/assets/libs/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>

<!-- Bootstrap tether Core JavaScript -->
<script src="./static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="./static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- apps -->
<script src="./static/dist/js/app.min.js"></script>
<script src="./static/dist/js/app.init.light-sidebar.js"></script>
<script src="./static/dist/js/app-style-switcher.js"></script>
<!-- slimscrollbar scrollbar JavaScript -->
<script src="./static/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
<script src="./static/assets/extra-libs/sparkline/sparkline.js"></script>
<!--Wave Effects -->
<script src="./static/dist/js/waves.js"></script>
<!--Menu sidebar -->
<script src="./static/dist/js/sidebarmenu.js"></script>
<!--Custom JavaScript -->
<script src="./static/dist/js/custom.min.js"></script>

<script src="./static/assets/libs/select2/dist/js/select2.full.min.js"></script>
<script src="./static/assets/libs/select2/dist/js/select2.min.js"></script>
<script src="./static/dist/js/pages/forms/select2/select2.init.js"></script>

<script src="./static/assets/libs/chartist/dist/chartist.min.js"></script>
<script src="./static/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
<!--c3 charts -->
<script src="./static/assets/extra-libs/c3/d3.min.js"></script>
<script src="./static/assets/extra-libs/c3/c3.min.js"></script>
<script src="./static/assets/libs/chart.js/dist/Chart.min.html"></script>
<script src="./static/dist/js/pages/dashboards/dashboard1.js"></script>

<script src="./static/assets/libs/jquery-steps/build/jquery.steps.min.js"></script>
    <script src="./static/assets/libs/jquery-validation/dist/jquery.validate.min.js"></script>
    
    <script src="./static/assets/libs/moment/moment.js"></script>
    <script src="./static/assets/libs/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker-custom.js"></script>
<script type="text/javascript">

$("#CMS-POPUP-MODEL").on('show.bs.modal', function (e) {
	var target=$(e.relatedTarget).attr('data-target');
	if(target=='#CMS-POPUP-MODEL'){
		var url=$(e.relatedTarget).attr('data-url');
		 //$('#CMS-POPUP-MODEL').load(url);
		
		 $.ajax({
	 	   url:url,
	 	   data:'',
	 	   beforeSend:function(){
	 		  $('#CMS-POPUP-MODEL').html('<center> <img alt="" src="./resource/img/loader.gif"></center>');
	 	   },
	 	   success:function(data){
	 		   $('#CMS-POPUP-MODEL').html(data);
	 	   }
	    }); 
	}
    
});
$("#CMS-POPUP-MODEL").on('shown.bs.modal', function () {
});
$("#CMS-POPUP-MODEL").on('hide.bs.modal', function () {
});
$("#CMS-POPUP-MODEL").on('hidden.bs.modal', function () {
	$('#CMS-POPUP-MODEL').html('');
	reloadBasePage();
});

$("#CMS-POPUP-DISPLAY").on('show.bs.modal', function (e) {
	var target=$(e.relatedTarget).attr('data-target');
	if(target=='#CMS-POPUP-DISPLAY'){
		var url=$(e.relatedTarget).attr('data-url');
		 //$('#CMS-POPUP-MODEL').load(url);
		
		 $.ajax({
	 	   url:url,
	 	   data:'',
	 	   beforeSend:function(){
	 		  $('#CMS-POPUP-DISPLAY').html('<center> <img alt="" src="./resource/img/loader.gif"></center>');
	 	   },
	 	   success:function(data){
	 		   $('#CMS-POPUP-DISPLAY').html(data);
	 	   }
	    }); 
	}
    
});




$('.cms_menu_item').click(function(){
	
	var url=$(this).attr('href');
	loadData(url, '', 'CMS-PAGE-CONTAINER');
	
	//CMS-PAGE-CONTAINER
	
	return false;
});

function reloadBasePage(){
	var url = $('#CMS-PAGE-CONTAINER').attr('data-url');
	loadData(url, '', 'CMS-PAGE-CONTAINER');
}

function loadData(url, param, containerId){
	$.ajax({
		url:url,
		data:param,
		beforeSend:function(){
			$('#'+containerId).html('<center> <img alt="" src="./resource/img/loader.gif"></center>');
		},
		success:function(data){
			$('#'+containerId).attr('data-url', url);
			$('#'+containerId).html(data);
			initPage();
		 }
	});
}

function initPage(){
	$('.select2').each(function(){
		$(this).select2();
	});
	
	$('.date_picker').datepicker({
		autoclose:true,
		todayBtn:'linked',
		todayHighlight:true,
		format:'dd/mm/yyyy'
	}); 
	
	$('.date_time_picker').bootstrapMaterialDatePicker({ 
		format: 'DD/MM/YYYY HH:mm' 
	});
}

//common function

function loadDepartment(deptIds, ele){
	$(ele).html('<option vlaue="">-Please Select-</option>');
	$.getJSON('employee?action=loadDesignation&deptIds='+deptIds,function(response){
		if(!$.isEmptyObject(response.data))
		{
			$(ele).append(response.data);
		}
	});
}

function loadBankBranch(bankIds, ele){
	$(ele).html('<option>-please Select-</option>');
	if(bankIds!=null && bankIds!=''){
		$.getJSON('employee?action=loadBranch&bankId='+bankIds, function(response){
			if(!$.isEmptyObject(response.option))
			{
				$(ele).append(response.option);
			}else{alert();}
		})
	}
	
}

function isEmptyElement(ele){
	return $.isEmptyObject( $(ele).val() );
}

//Validation

jQuery.validator.addClassRules("jval_name", { lettersonly: true });
jQuery.validator.addClassRules("jval_email", { email: true });
jQuery.validator.addClassRules("jval_num", { number: true });
jQuery.validator.addClassRules("jval_name_num", { lettersAndNumber: true });
jQuery.validator.addClassRules("jval_addr", { address: true });
jQuery.validator.addClassRules("jval_dob", { appDob: true });

jQuery.validator.addMethod("lettersonly", function(value, element) {
	  return this.optional(element) || /^[a-z\s]+$/i.test(value);
}, "Plese enter letters only"); 

jQuery.validator.addMethod("lettersAndNumber", function(value, element) {
	  return this.optional(element) || /^[a-z 0-9]+$/i.test(value);
}, "Plese enter letters or number only"); 

jQuery.validator.addMethod("address", function(value, element) {
	  return this.optional(element) || /^[a-zA-Z0-9\s,\'-/]*$/i.test(value);
}, "Plese enter letters or number or , / - 'only"); 

jQuery.validator.addMethod("appDtFt", function(value, element) {
	  return this.optional(element) || /^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/i.test(value);
}, "Invalid format. please enter dd/MM/yyyy"); 

jQuery.validator.addMethod("appDob", function(value, element) {
	var retVal=false;
	if(/^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/i.test(value)){
		var dateArr=(''+value).split('/');
		var d = parseInt(dateArr[0]);
		var m = parseInt(dateArr[1]);
		var y = parseInt(dateArr[2]);
		var dt = new Date(y,m-1,d);
		var currDt = new Date();
		var dt1 = new Date(currDt.getFullYear(),currDt.getMonth(),currDt.getDate()-1);
		if((dt1-dt)>=0){
			retVal = true;
		}
	}
	  return this.optional(element) || retVal;
}, "Please enter valid DOB"); 

</script>
 