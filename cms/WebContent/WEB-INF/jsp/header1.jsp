<!-- Topbar header - style you can find in pages.scss -->
<%@page import="java.util.Random"%>
<link href="./static/dist/css/style.min.css" rel="stylesheet">
<link href="./static/assets/libs/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="./static/assets/libs/select2/dist/css/select2.min.css">
<link href="./static/assets/libs/jquery-steps/jquery.steps.css" rel="stylesheet">
    <link href="./static/assets/libs/jquery-steps/steps.css" rel="stylesheet">
<%
String rVal=""+Math.abs( new Random().nextInt(9999));
%>
<header class="topbar">
    <nav class="navbar top-navbar navbar-expand-md navbar-dark">
        <div class="navbar-header">
            <!-- This is for the sidebar toggle which is visible on mobile only -->
            <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
            
            <!-- Logo -->
            
            <a class="navbar-brand" href="home">
                <!-- Logo icon -->
                <b class="logo-icon">
                    <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
                    <!-- Dark Logo icon -->
                    <!-- <img src="./static/assets/images/logo-icon.png" alt="homepage" class="dark-logo" /> -->
                    <!-- Light Logo icon -->
                    <img src="./static/img/logo.png?q=<%=rVal%>" alt="homepage" class="light-logo" />
                </b>
                <!--End Logo icon -->
                <!-- Logo text -->
                <!-- <span class="logo-text">
                     dark Logo text
                     <img src="./static/assets/images/logo-text.png" alt="homepage" class="dark-logo" />
                     Light Logo text    
                     <img src="./static/assets/images/logo-light-text.png" class="light-logo" alt="homepage" />
                </span> -->
                <span class="logo-text" style="color: white; font-size: 30px; font-weight: bold;">CMS</span>
            </a>
            
            <!-- End Logo -->
            
            
            <!-- Toggle which is visible on mobile only -->
            
            <a class="topbartoggler d-block d-md-none waves-effect waves-light" href="javascript:void(0)" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><i class="ti-more"></i></a>
        </div>
        
        <!-- End Logo -->
        
        <div class="navbar-collapse collapse" id="navbarSupportedContent">
            
            <!-- toggle and nav items -->
            
            <ul class="navbar-nav float-left mr-auto">
                <li class="nav-item d-none d-md-block"><a class="nav-link sidebartoggler waves-effect waves-light" href="javascript:void(0)" data-sidebartype="mini-sidebar"><i class="mdi mdi-menu font-24"></i></a></li>
                
                <!-- Search -->
                
                <li class="nav-item search-box"> <a class="nav-link waves-effect waves-dark" href="javascript:void(0)"><i class="ti-search"></i></a>
                    <form class="app-search position-absolute">
                        <input type="text" class="form-control form-control-sm" placeholder="Search &amp; enter"> <a class="srh-btn"><i class="ti-close"></i></a>
                    </form>
                </li>
            </ul>
            
            <!-- Right side toggle and nav items -->
            
            <ul class="navbar-nav float-right">
                
                <!-- create new -->
                
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="flag-icon flag-icon-us"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right  animated bounceInDown" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-us"></i> English</a>
                        <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-fr"></i> French</a>
                        <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-es"></i> Spanish</a>
                        <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-de"></i> German</a>
                    </div>
                </li>
                
                <!-- Comment -->
                
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle waves-effect waves-dark" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="mdi mdi-bell font-24"></i>
                        
                    </a>
                    <div class="dropdown-menu dropdown-menu-right mailbox animated bounceInDown">
                        <span class="with-arrow"><span class="bg-primary"></span></span>
                        <ul class="list-style-none">
                            <li>
                                <div class="drop-title bg-primary text-white">
                                    <h4 class="m-b-0 m-t-5">4 New</h4>
                                    <span class="font-light">Notifications</span>
                                </div>
                            </li>
                            <li>
                                <div class="message-center notifications">
                                    <!-- Message -->
                                    <a href="javascript:void(0)" class="message-item">
                                        <span class="btn btn-danger btn-circle"><i class="fa fa-link"></i></span>
                                        <span class="mail-contnet">
                                            <h5 class="message-title">Luanch Admin</h5> <span class="mail-desc">Just see the my new admin!</span> <span class="time">9:30 AM</span> </span>
                                    </a>
                                    <!-- Message -->
                                    <a href="javascript:void(0)" class="message-item">
                                        <span class="btn btn-success btn-circle"><i class="ti-calendar"></i></span>
                                        <span class="mail-contnet">
                                            <h5 class="message-title">Event today</h5> <span class="mail-desc">Just a reminder that you have event</span> <span class="time">9:10 AM</span> </span>
                                    </a>
                                    <!-- Message -->
                                    <a href="javascript:void(0)" class="message-item">
                                        <span class="btn btn-info btn-circle"><i class="ti-settings"></i></span>
                                        <span class="mail-contnet">
                                            <h5 class="message-title">Settings</h5> <span class="mail-desc">You can customize this template as you want</span> <span class="time">9:08 AM</span> </span>
                                    </a>
                                    <!-- Message -->
                                    <a href="javascript:void(0)" class="message-item">
                                        <span class="btn btn-primary btn-circle"><i class="ti-user"></i></span>
                                        <span class="mail-contnet">
                                            <h5 class="message-title">Pavan kumar</h5> <span class="mail-desc">Just see the my admin!</span> <span class="time">9:02 AM</span> </span>
                                    </a>
                                </div>
                            </li>
                            <li>
                                <a class="nav-link text-center m-b-5" href="javascript:void(0);"> <strong>Check all notifications</strong> <i class="fa fa-angle-right"></i> </a>
                            </li>
                        </ul>
                    </div>
                </li>
                
                <!-- End Comment -->
                
                
                <!-- Messages -->
                
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle waves-effect waves-dark" href="#" id="2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="font-24 mdi mdi-comment-processing"></i>
                        
                    </a>
                    <div class="dropdown-menu dropdown-menu-right mailbox animated bounceInDown" aria-labelledby="2">
                        <span class="with-arrow"><span class="bg-danger"></span></span>
                        <ul class="list-style-none">
                            <li>
                                <div class="drop-title text-white bg-danger">
                                    <h4 class="m-b-0 m-t-5">5 New</h4>
                                    <span class="font-light">Messages</span>
                                </div>
                            </li>
                            <li>
                                <div class="message-center message-body">
                                    <!-- Message -->
                                    <a href="javascript:void(0)" class="message-item">
                                        <span class="user-img"> <img src="./static/assets/images/users/1.jpg" alt="user" class="rounded-circle"> <span class="profile-status online pull-right"></span> </span>
                                        <span class="mail-contnet">
                                            <h5 class="message-title">Pavan kumar</h5> <span class="mail-desc">Just see the my admin!</span> <span class="time">9:30 AM</span> </span>
                                    </a>
                                    <!-- Message -->
                                    <a href="javascript:void(0)" class="message-item">
                                        <span class="user-img"> <img src="./static/assets/images/users/2.jpg" alt="user" class="rounded-circle"> <span class="profile-status busy pull-right"></span> </span>
                                        <span class="mail-contnet">
                                            <h5 class="message-title">Sonu Nigam</h5> <span class="mail-desc">I've sung a song! See you at</span> <span class="time">9:10 AM</span> </span>
                                    </a>
                                    <!-- Message -->
                                    <a href="javascript:void(0)" class="message-item">
                                        <span class="user-img"> <img src="./static/assets/images/users/3.jpg" alt="user" class="rounded-circle"> <span class="profile-status away pull-right"></span> </span>
                                        <span class="mail-contnet">
                                            <h5 class="message-title">Arijit Sinh</h5> <span class="mail-desc">I am a singer!</span> <span class="time">9:08 AM</span> </span>
                                    </a>
                                    <!-- Message -->
                                    <a href="javascript:void(0)" class="message-item">
                                        <span class="user-img"> <img src="./static/assets/images/users/4.jpg" alt="user" class="rounded-circle"> <span class="profile-status offline pull-right"></span> </span>
                                        <span class="mail-contnet">
                                            <h5 class="message-title">Pavan kumar</h5> <span class="mail-desc">Just see the my admin!</span> <span class="time">9:02 AM</span> </span>
                                    </a>
                                </div>
                            </li>
                            <li>
                                <a class="nav-link text-center link" href="javascript:void(0);"> <b>See all e-Mails</b> <i class="fa fa-angle-right"></i> </a>
                            </li>
                        </ul>
                    </div>
                </li>
                
                <!-- End Messages -->
                
                
                <!-- User profile and search -->
                
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark pro-pic" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="./static/assets/images/users/1.jpg" alt="user" class="rounded-circle" width="31"></a>
                    <div class="dropdown-menu dropdown-menu-right user-dd animated flipInY">
                        <span class="with-arrow"><span class="bg-primary"></span></span>
                        <div class="d-flex no-block align-items-center p-15 bg-primary text-white m-b-10">
                            <div class=""><img src="./static/assets/images/users/1.jpg" alt="user" class="img-circle" width="60"></div>
                            <div class="m-l-10">
                                <h4 class="m-b-0">Admin</h4>
                                <p class=" m-b-0">Admin@ui-bot.com</p>
                            </div>
                        </div>
                        <a class="dropdown-item" href="javascript:void(0)"><i class="ti-user m-r-5 m-l-5"></i> My Profile</a>
                        <a class="dropdown-item" href="javascript:void(0)"><i class="ti-wallet m-r-5 m-l-5"></i> My Balance</a>
                        <a class="dropdown-item" href="javascript:void(0)"><i class="ti-email m-r-5 m-l-5"></i> Inbox</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="javascript:void(0)"><i class="ti-settings m-r-5 m-l-5"></i> Account Setting</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="logout"><i class="fa fa-power-off m-r-5 m-l-5"></i> Logout</a>
                        <div class="dropdown-divider"></div>
                        <div class="p-l-30 p-10"><a href="javascript:void(0)" class="btn btn-sm btn-success btn-rounded">View Profile</a></div>
                    </div>
                </li>
                
                <!-- User profile and search -->
                
            </ul>
        </div>
    </nav>
</header>

<!-- End Topbar header -->


<!-- Left Sidebar - style you can find in sidebar.scss  -->

<aside class="left-sidebar">
    <!-- Sidebar scroll-->
    <div class="scroll-sidebar">
        <!-- Sidebar navigation-->
        <nav class="sidebar-nav">
            <ul id="sidebarnav">
                <!-- User Profile-->
                <li>
                    <!-- User Profile-->
                    <div class="user-profile d-flex no-block dropdown m-t-20">
                        <div class="user-pic"><img src="./static/assets/images/users/1.jpg" alt="users" class="rounded-circle" width="40" /></div>
                        <div class="user-content hide-menu m-l-10">
                            <a href="javascript:void(0)" class="" id="Userdd" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="op-5 user-email">Admin@ui-bot.com</span>
                            </a>
                        </div>
                    </div>
                    <!-- End User Profile-->
                </li>
                               
                <li class="sidebar-item"> <a class="sidebar-link waves-effect waves-dark" href="home" aria-expanded="false"><i class="ti-loop"></i><span class="hide-menu">Back To Home</span></a></li>
                <li class="sidebar-item"> <a class="sidebar-link waves-effect waves-dark has-arrow" href="javascript:void(0)" aria-expanded="false"><i class="mdi mdi-notification-clear-all"></i><span class="hide-menu">Master</span></a>
                    <ul aria-expanded="false" class="collapse first-level">
                        <li class="sidebar-item"><a href="service?action=search" class="sidebar-link cms_menu_item"><i class="mdi mdi-octagram"></i><span class="hide-menu"> Service</span></a></li>
                        <li class="sidebar-item"><a href="package?action=search" class="sidebar-link cms_menu_item"><i class="mdi mdi-octagram"></i><span class="hide-menu"> Package</span></a></li>
                        <li class="sidebar-item"><a href="process?action=search" class="sidebar-link cms_menu_item"><i class="mdi mdi-octagram"></i><span class="hide-menu"> Process</span></a></li>
                        <li class="sidebar-item"> <a class="has-arrow sidebar-link" href="javascript:void(0)" aria-expanded="false"><i class="mdi mdi-playlist-plus"></i> <span class="hide-menu">HoliDay</span></a>
                            <ul aria-expanded="false" class="collapse second-level">
                                <li class="sidebar-item"><a href="holidayType?action=search" class="sidebar-link cms_menu_item"><i class="mdi mdi-octagram"></i><span class="hide-menu"> Holiday Type</span></a></li>
                                <li class="sidebar-item"><a href="holiday?action=search" class="sidebar-link cms_menu_item"><i class="mdi mdi-octagram"></i><span class="hide-menu"> Holiday Search</span></a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li class="sidebar-item"> <a class="sidebar-link cms_menu_item" href="customer?action=search" aria-expanded="false"><i class="mdi mdi-account-multiple"></i><span class="hide-menu">Customer</span></a></li>
                <li class="sidebar-item"> <a class="sidebar-link cms_menu_item" href="employee?action=search" aria-expanded="false"><i class="mdi mdi-account-multiple-plus"></i><span class="hide-menu">Employee</span></a></li>
                <li class="sidebar-item"> <a class="sidebar-link cms_menu_item" href="taskConfig?action=search" aria-expanded="false"><i class="mdi mdi-content-paste"></i><span class="hide-menu">Task Configuration</span></a></li>
                <li class="sidebar-item"> <a class="sidebar-link" href="taskQuestionnaire?action=search" aria-expanded="false"><i class="mdi mdi-check-circle"></i><span class="hide-menu">Task Questionnaire</span></a></li>
                <li class="sidebar-item"> <a class="sidebar-link" href="customerBooking?action=search" aria-expanded="false"><i class="mdi mdi-notification-clear-all"></i><span class="hide-menu">Customer Booking</span></a></li>
                <li class="sidebar-item"> <a class="sidebar-link cms_menu_item" href="taskProcess?action=search" aria-expanded="false"><i class="fas fa-tasks"></i><span class="hide-menu">Task Process</span></a></li>
                <li class="sidebar-item"> <a class="sidebar-link cms_menu_item" href="task?action=search" aria-expanded="false"><i class="fas fa-folder-plus"></i><span class="hide-menu">Task</span></a></li>
                <li class="sidebar-item"> <a class="sidebar-link cms_menu_item" href="menu?action=search" aria-expanded="false"><i class="fas fa-ellipsis-h"></i><span class="hide-menu">Menu</span></a></li>
                <li class="sidebar-item"> <a class="sidebar-link cms_menu_item" href="navigation?action=search" aria-expanded="false"><i class="fas fa-compass"></i><span class="hide-menu">Navigation</span></a></li>
                 <li class="sidebar-item"> <a class="sidebar-link cms_menu_item" href="rights?action=search" aria-expanded="false"><i class="far fa-copyright"></i><span class="hide-menu">Rights</span></a></li>
                 <li class="sidebar-item"> <a class="sidebar-link cms_menu_item" href="rightsTemplate?action=search" aria-expanded="false"><i class="mdi mdi-notification-clear-all"></i><span class="hide-menu">Rights Template</span></a></li>
                
            </ul>
        </nav>
        <!-- End Sidebar navigation -->
    </div>
    <!-- End Sidebar scroll-->
</aside>

<!-- End Left Sidebar - style you can find in sidebar.scss  -->



<!-- Popup Model Start -->
<div id="CMS-POPUP-MODEL" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Service Creation</h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">Service Name:</label>
                        <input type="text" class="form-control" id="recipient-name">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-danger waves-effect waves-light">Save</button>
            </div>
        </div>
    </div>
</div>
<!-- Popup Model End -->




