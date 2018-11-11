<!-- footer -->
 <footer class="footer text-center">
       All Rights Reserved by ui-bot. Designed and Developed by <a href="">ui-bot</a>.
</footer>   
<!-- End footer -->

<!-- All Jquery -->
    
<script src="./static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="./static/assets/libs/jquery-validation/dist/jquery.validate.min.js"></script>

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

<script type="text/javascript">

$("#CMS-POPUP-MODEL").on('show.bs.modal', function (e) {
	var url=$(e.relatedTarget).attr('data-url')
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
    
});
$("#CMS-POPUP-MODEL").on('shown.bs.modal', function () {

});
$("#CMS-POPUP-MODEL").on('hide.bs.modal', function () {
	
});
$("#CMS-POPUP-MODEL").on('hidden.bs.modal', function () {
	$('#CMS-POPUP-MODEL').html('');
	location.reload();
});

</script>
 