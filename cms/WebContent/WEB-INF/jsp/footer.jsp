<footer class="footer fixed-bottom footer-dark navbar-border navbar-shadow">
  <div class="clearfix blue-grey lighten-2 text-sm-center mb-0 px-2"><span class="float-md-left d-block d-md-inline-block">2018  &copy; Copyright <a class="text-bold-800 grey darken-2" href="#">UI-BOT</a></span>
    <!-- <ul class="list-inline float-md-right d-block d-md-inline-blockd-none d-lg-block mb-0">
      <li class="list-inline-item"><a class="my-1" href="https://themeselection.com/" target="_blank"> More themes</a></li>
      <li class="list-inline-item"><a class="my-1" href="https://themeselection.com/support" target="_blank"> Support</a></li>
      <li class="list-inline-item"><a class="my-1" href="https://themeselection.com/products/chameleon-admin-modern-bootstrap-webapp-dashboard-html-template-ui-kit/" target="_blank"> Purchase</a></li>
    </ul> -->
  </div>
</footer>

<script src="./resource/app-assets/vendors/js/vendors.min.js" type="text/javascript"></script>
<script src="./resource/app-assets/vendors/js/forms/toggle/switchery.min.js" type="text/javascript"></script>
<script src="./resource/app-assets/js/scripts/forms/switch.min.js" type="text/javascript"></script>
<script type="text/javascript" src="./resource/app-assets/vendors/js/ui/prism.min.js"></script>
<script src="./resource/app-assets/vendors/js/charts/chartist.min.js" type="text/javascript"></script>
<script src="./resource/app-assets/vendors/js/charts/chartist-plugin-tooltip.min.js" type="text/javascript"></script>
<script src="./resource/app-assets/vendors/js/extensions/moment.min.js" type="text/javascript"></script>
<script src="./resource/app-assets/vendors/js/extensions/fullcalendar.min.js" type="text/javascript"></script>
<script src="./resource/app-assets/js/core/libraries/jquery_ui/jquery-ui.min.js" type="text/javascript"></script>
<script src="./resource/app-assets/js/core/app-menu.min.js" type="text/javascript"></script>
<script src="./resource/app-assets/js/core/app.min.js" type="text/javascript"></script>
<script src="./resource/app-assets/js/scripts/customizer.min.js" type="text/javascript"></script>
<script src="./resource/app-assets/vendors/js/jquery.sharrre.js" type="text/javascript"></script>
<script src="./resource/app-assets/js/scripts/pages/dashboard-analytics.min.js" type="text/javascript"></script>
<script src="./resource/app-assets/js/scripts/extensions/fullcalendar.min.js" type="text/javascript"></script>
<script type="text/javascript">

$("#CMS-POPUP-MODEL").on('show.bs.modal', function (e) {
	var url=$(e.relatedTarget).attr('data-url')
	
	$.ajax({
 	   url:url,
 	   data:'',
 	   async:false,
 	   success:function(data){
 		   $('#CMS-POPUP-MODEL').html(data);
 	   }
    }); 
    
});
$("#CMS-POPUP-MODEL").on('shown.bs.modal', function () {

});
$("#CMS-POPUP-MODEL").on('hide.bs.modal', function () {
	alert('hide');
});
$("#CMS-POPUP-MODEL").on('hidden.bs.modal', function () {
	alert('hidden');
	$('#CMS-POPUP-MODEL').html('');
});

</script>
 