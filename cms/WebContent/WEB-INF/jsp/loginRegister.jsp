<%@page import="com.cms.user.login.bean.LoginMasterBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html class="loading" lang="en" data-textdirection="ltr">
  
<!-- Mirrored from themeselection.com/demo/chameleon-admin-template/html/ltr/vertical-menu-template/register.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 18 Jul 2018 09:45:17 GMT -->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta name="description" content="Chameleon Admin is a modern Bootstrap 4 webapp &amp; admin dashboard html template with a large number of components, elegant design, clean and organized code.">
    <meta name="keywords" content="admin template, Chameleon admin template, dashboard template, gradient admin template, responsive admin template, webapp, eCommerce dashboard, analytic dashboard">
    <meta name="author" content="ThemeSelect">
    <title>CMS</title>
    <link rel="apple-touch-icon" href="../../../app-assets/images/ico/apple-icon-120.png">
    <link rel="shortcut icon" type="image/x-icon" href="https://themeselection.com/demo/chameleon-admin-template/app-assets/images/ico/favicon.ico">
    <link href="https://fonts.googleapis.com/css?family=Muli:300,300i,400,400i,600,600i,700,700i%7CComfortaa:300,400,700" rel="stylesheet">
    <link href="../../../../../../maxcdn.icons8.com/fonts/line-awesome/1.1/css/line-awesome.min.html" rel="stylesheet">
    <!-- BEGIN VENDOR CSS-->
    <link rel="stylesheet" type="text/css" href="./resource/app-assets/css/vendors.min.css">
    <link rel="stylesheet" type="text/css" href="./resource/app-assets/vendors/css/forms/toggle/switchery.min.css">
    <link rel="stylesheet" type="text/css" href="./resource/app-assets/css/plugins/forms/switch.min.css">
    <link rel="stylesheet" type="text/css" href="./resource/app-assets/css/core/colors/palette-switch.min.css">
    <!-- END VENDOR CSS-->
    <!-- BEGIN CHAMELEON  CSS-->
    <link rel="stylesheet" type="text/css" href="./resource/app-assets/css/app.min.css">
    <!-- END CHAMELEON  CSS-->
    <!-- BEGIN Page Level CSS-->
    <link rel="stylesheet" type="text/css" href="./resource/app-assets/css/core/menu/menu-types/vertical-menu.min.css">
    <link rel="stylesheet" type="text/css" href="./resource/app-assets/css/core/colors/palette-gradient.min.css">
    <link rel="stylesheet" type="text/css" href="./resource/app-assets/css/pages/login-register.min.css">
    <!-- END Page Level CSS-->
    <!-- BEGIN Custom CSS-->
    <link rel="stylesheet" type="text/css" href="./resource/assets/css/style.css">
    <!-- END Custom CSS-->
  </head>
  <body class="vertical-layout vertical-menu 1-column  bg-full-screen-image menu-expanded blank-page blank-page" data-open="click" data-menu="vertical-menu" data-color="bg-gradient-x-purple-blue" data-col="1-column">
    <!-- ////////////////////////////////////////////////////////////////////////////-->
    <div class="app-content content">
      <div class="content-wrapper">
        <div class="content-wrapper-before"></div>
        <div class="content-header row">
        </div>
        <div class="content-body"><section class="flexbox-container">
    <div class="col-12 d-flex align-items-center justify-content-center">
        <div class="col-md-4 col-10 box-shadow-2 p-0">
            <div class="card border-grey border-lighten-3 px-1 py-1 m-0">
                <div class="card-header border-0">
                    <div class="text-center mb-1">
                        <img src="./resource/app-assets/images/logo/logo.png" alt="branding logo">
                    </div>
                    <div class="font-large-1  text-center">
                        Become A Member
                    </div>
                </div>
                <div class="card-content">
<%
String err_username=(String)request.getAttribute("err_userName"); if(err_username==null){ err_username=""; }
String err_password=(String)request.getAttribute("err_password"); if(err_password==null){ err_password=""; }
String err_email=(String)request.getAttribute("err_email"); if(err_email==null){ err_email=""; }

LoginMasterBean loginDTO=(LoginMasterBean)request.getAttribute("loginDTO"); if(loginDTO==null){ loginDTO=new  LoginMasterBean();}


%>
                    <div class="card-body">
                        <form class="form-horizontal" action="login" method="post">
                         	<input type="hidden"  name="action" value="register_save">
                            <fieldset class="form-group position-relative has-icon-left">
                                <input type="text" name="userName" class="form-control round" id="user-name" placeholder="Choose Username" required value="<%=loginDTO.getLoginId()%>">
                                <span  style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;<%=err_username %></span>
                                <div class="form-control-position">
                                    <i class="ft-user"></i>
                                </div>
                            </fieldset>
                            <fieldset class="form-group position-relative has-icon-left">
                                <input type="email" name="email" class="form-control round" id="user-email" placeholder="Your Email Address" required value="<%=loginDTO.getEmail()%>">
                                <span  style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;<%=err_email %></span>
                                <div class="form-control-position">
                                    <i class="ft-mail"></i>
                                </div>
                            </fieldset>
                            <fieldset class="form-group position-relative has-icon-left">
                                <input type="password" name="password" class="form-control round" id="user-password" placeholder="Enter Password" required value="<%=loginDTO.getPassword()%>">
                                <span  style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;<%=err_password %></span>
                                <div class="form-control-position">
                                    <i class="ft-lock"></i>
                                </div>
                            </fieldset>

                            <div class="form-group text-center">
                                <button type="submit" class="btn round btn-block btn-glow btn-bg-gradient-x-purple-blue col-12 mr-1 mb-1">Register</button>
                            </div>

                        </form>
                    </div>
                    <p class="card-subtitle line-on-side text-muted text-center font-small-3 mx-2 my-2 ">
                        <span>OR Sign Up Using</span>
                    </p>
                    <div class="text-center">
                        <a href="#" class="btn btn-social-icon round mr-1 mb-1 btn-facebook">
                            <span class="ft-facebook"></span>
                        </a>
                        <a href="#" class="btn btn-social-icon round mr-1 mb-1 btn-twitter">
                            <span class="ft-twitter"></span>
                        </a>
                        <a href="#" class="btn btn-social-icon round mr-1 mb-1 btn-instagram">
                            <span class="ft-instagram"></span>
                        </a>
                    </div>

                    <p class="card-subtitle text-muted text-right font-small-3 mx-2 my-1">
                        <span>Already a member ?
                            <a href="login" class="card-link">Sign In</a>
                        </span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>
        </div>
      </div>
    </div>
    <!-- ////////////////////////////////////////////////////////////////////////////-->

    <!-- BEGIN VENDOR JS-->
    <script src="./resource/app-assets/vendors/js/vendors.min.js" type="text/javascript"></script>
    <script src="./resource/app-assets/vendors/js/forms/toggle/switchery.min.js" type="text/javascript"></script>
    <script src="./resource/app-assets/js/scripts/forms/switch.min.js" type="text/javascript"></script>
    <!-- BEGIN VENDOR JS-->
    <!-- BEGIN PAGE VENDOR JS-->
    <script src="./resource/app-assets/vendors/js/forms/validation/jqBootstrapValidation.js" type="text/javascript"></script>
    <!-- END PAGE VENDOR JS-->
    <!-- BEGIN CHAMELEON  JS-->
    <script src="./resource/app-assets/js/core/app-menu.min.js" type="text/javascript"></script>
    <script src="./resource/app-assets/js/core/app.min.js" type="text/javascript"></script>
    <!-- END CHAMELEON  JS-->
    <!-- BEGIN PAGE LEVEL JS-->
    <script src="./resource/app-assets/js/scripts/forms/form-login-register.min.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL JS-->
  </body>

<!-- Mirrored from themeselection.com/demo/chameleon-admin-template/html/ltr/vertical-menu-template/register.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 18 Jul 2018 09:45:17 GMT -->
</html>