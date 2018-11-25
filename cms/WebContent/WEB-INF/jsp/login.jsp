<!DOCTYPE html>
<html dir="ltr">


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="./static/assets/images/favicon.png">
    <title>ui-bot</title>
    <!-- Custom CSS -->
    <link href="./static/dist/css/style.min.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<style>
.form-control.invalid{
	border-color: #f62d51 !important;
}
label.invalid{
	color: #f62d51 !important;
}
.form-control.valid{
	border-color: #36bea6 !important;
}

</style>
<body>
    <div class="main-wrapper">
        
        <!-- Preloader - style you can find in spinners.css -->
        
        <div class="preloader">
            <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
            </div>
        </div>
        
        <!-- Preloader - style you can find in spinners.css -->
        
        
        <!-- Login box.scss -->
        
        <div class="auth-wrapper d-flex no-block justify-content-center align-items-center" style="background:url(./static/assets/images/big/auth-bg.jpg) no-repeat center center;">
            <div class="auth-box">
                <div id="loginform">
                    <div class="logo">
                        <span class="db"><img src="./static/img/logo.png" alt="logo" /></span>
                        <h5 class="font-medium m-b-20">CMS Admin Login</h5>
                    </div>
                    <!-- Form -->
                    <div class="row">
                        <div class="col-12">
                            <form class="form-horizontal m-t-20" id="cms_login_frm" action="login" method="post">
                            	<input type="hidden"  name="action" value="validate">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="ti-user"></i></span>
                                    </div>
                                    <input type="text" name="userName" class="form-control form-control-lg" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1" required="required">
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon2"><i class="ti-pencil"></i></span>
                                    </div>
                                    <input type="password" name="password" class="form-control form-control-lg" placeholder="Password" aria-label="Password" aria-describedby="basic-addon1" required="required">
                                </div>
                                <div class="form-group row">
                                    <div class="col-md-12">
                                        <div class="custom-control custom-checkbox">
                                            <a href="javascript:void(0)" id="to-recover" class="text-dark float-right"><i class="fa fa-lock m-r-5"></i> Forgot pwd?</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group text-center">
                                    <div class="col-xs-12 p-b-20">
                                        <button class="btn btn-block btn-lg btn-info" type="submit">Log In</button>
                                    </div>
                                </div>
                                
                            </form>
                        </div>
                    </div>
                </div>
                <div id="recoverform">
                    <div class="logo">
                        <span class="db"><img src="./static/assets/images/logo-icon.png" alt="logo" /></span>
                        <h5 class="font-medium m-b-20">Recover Password</h5>
                        <span>Enter your Email and instructions will be sent to you!</span>
                    </div>
                    <div class="row m-t-20">
                        <!-- Form -->
                        <form class="col-12" action="">
                            <!-- email -->
                            <div class="form-group row">
                                <div class="col-12">
                                    <input class="form-control form-control-lg" type="email" required="" placeholder="Username">
                                </div>
                            </div>
                            <!-- pwd -->
                            <div class="row m-t-20">
                                <div class="col-12">
                                    <button class="btn btn-block btn-lg btn-danger" type="submit" name="action">Reset</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Login box.scss -->
        
        
        <!-- Page wrapper scss in scafholding.scss -->
        
        
        <!-- Page wrapper scss in scafholding.scss -->
        
        
        <!-- Right Sidebar -->
        
        
        <!-- Right Sidebar -->
        
    </div>
    
    <!-- All Required js -->
    
    <script src="./static/assets/libs/jquery/dist/jquery.min.js"></script>
    <script src="./static/assets/libs/jquery-validation/dist/jquery.validate.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="./static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="./static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    
    <!-- This page plugin js -->
    
    <script>
    $('[data-toggle="tooltip"]').tooltip();
    $(".preloader").fadeOut();
    // ============================================================== 
    // Login and Recover Password 
    // ============================================================== 
    $('#to-recover').on("click", function() {
        $("#loginform").slideUp();
        $("#recoverform").fadeIn();
    });
    
    /* $(document).ready(function(){
    	try{		
    		$('#cms_login_frm').validate({
    			errorClass: 'invalid',
    			validClass: 'valid',
    			errorPlacement: function(error, element) {
    				error.insertAfter(element.parent());
    			},
    			rules: {

    				userName: { required: true },
    				password: { required: true }
    			},
    			messages: {
    				userName: { required: 'User Name is required' },
    				password: { required: 'Password is required' }

    			},
    			submitHandler: function(form) {
    				$.ajax({
    					url:$(form).attr('action'),
    					data:$(form).serialize(),
    					beforeSend:function(){
    						$('#CMS-POPUP-MODEL').html('<center> <img alt="" src="./resource/img/loader.gif"></center>');
    					},
    					success:function(data){
    						location.href="home";
    					}
    				}); 
    			}
    		});
    		
    	}catch(e){
    		alert('Something went wrong. Please Try Later..!');
    	}
    }); */
    
    
    
    </script>
</body>


</html>