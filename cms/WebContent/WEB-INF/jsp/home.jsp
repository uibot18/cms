<!DOCTYPE html>
<html class="loading" lang="en" data-textdirection="ltr">
  
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="">
    <title>Admin User Request</title>
    <link rel="apple-touch-icon" href="./resource/app-assets/images/ico/apple-icon-120.png">
    <link rel="shortcut icon" type="image/x-icon" href="./resource/app-assets/images/ico/favicon.ico">
       <link rel="stylesheet" type="text/css" href="./resource/app-assets/css/plugins/calendars/fullcalendar.min.css">
    <link rel="stylesheet" type="text/css" href="./resource/app-assets/vendors/css/calendars/fullcalendar.min.css">
  </head>
  <body class="vertical-layout vertical-menu 2-columns   menu-expanded fixed-navbar" data-open="click" data-menu="vertical-menu" data-color="bg-gradient-x-purple-blue" data-col="2-columns">
   <%@include file="header.jsp" %>
   
   <!-- Content start -->
       <div class="app-content content">
      <div class="content-wrapper">
        <div class="content-wrapper-before"></div>
        <div class="content-header row">
        </div>
        <div class="content-body"><!-- Revenue, Hit Rate & Deals -->
<div class="row">
    <div class="col-lg-8 col-md-12">
        <div class="card">
            <div class="card-header">
                <h4 class="card-title">Task Monthly Report</h4>
                <a class="heading-elements-toggle"><i class="la la-ellipsis-v font-medium-3"></i></a>
                <div class="heading-elements">
                    <ul class="list-inline mb-0">
                        <li><button type="button" class="btn btn-glow btn-round btn-bg-gradient-x-red-pink">More</button></li>
                    </ul>
                </div>
            </div>
            <div class="card-content collapse show">
                <div class="card-body p-0 pb-0">
                    <div class="chartist">
                        <div id="project-stats" class="height-350 areaGradientShadow1"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-4 col-md-12">
        <div class="row">
            <div class="col-12">
                <div class="card pull-up">
                    <div class="card-header">
                        <h4 class="card-title float-left">Project X</h4><a class="heading-elements-toggle"><i class="la la-ellipsis-v font-medium-3"></i></a>
                        <span class="badge badge-pill badge-info float-right m-0">In Progress</span>
                    </div>
                    <div class="card-content collapse show">
                        <div class="card-body pt-0 pb-1">
                            <h6 class="text-muted font-small-3"> Completed Task : 4/10</h6>
                            <div class="progress progress-sm mt-1 mb-0 box-shadow-2">
                                <div class="progress-bar bg-gradient-x-info" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <div class="media d-flex">
                                <div class="align-self-center">
                                    <h6 class="text-bold-600 mt-2"> Client: <span class="info">Xeon Inc.</span></h6>
                                    <h6 class="text-bold-600 mt-1"> Deadline: <span class="blue-grey">5th June, 2018</span></h6>
                                </div>
                                <div class="media-body text-right mt-2">
                                    <ul class="list-unstyled users-list">
                                        <li data-toggle="tooltip" data-popup="tooltip-custom" data-original-title="John Doe" class="avatar avatar-sm pull-up">
                                            <img class="media-object rounded-circle" src="./resource/app-assets/images/portrait/small/avatar-s-19.png" alt="Avatar">
                                        </li>
                                        <li data-toggle="tooltip" data-popup="tooltip-custom" data-original-title="Katherine Nichols" class="avatar avatar-sm pull-up">
                                            <img class="media-object rounded-circle" src="./resource/app-assets/images/portrait/small/avatar-s-18.png" alt="Avatar">
                                        </li>
                                        <li data-toggle="tooltip" data-popup="tooltip-custom" data-original-title="Joseph Weaver" class="avatar avatar-sm pull-up">
                                            <img class="media-object rounded-circle" src="./resource/app-assets/images/portrait/small/avatar-s-17.png" alt="Avatar">
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="card pull-up bg-gradient-directional-danger">
                    <div class="card-header bg-hexagons-danger">
                        <h4 class="card-title white">Analytics</h4>
                        <a class="heading-elements-toggle"><i class="la la-ellipsis-v font-medium-3"></i></a>
                        <div class="heading-elements">
                            <ul class="list-inline mb-0">
                                <li>
                                    <a class="btn btn-sm btn-white danger box-shadow-1 round btn-min-width pull-right" href="#" target="_blank">Report <i class="ft-bar-chart pl-1"></i></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="card-content collapse show bg-hexagons-danger">
                        <div class="card-body">
                            <div class="media d-flex">
                                <div class="align-self-center width-100">
                                    <div id="Analytics-donut-chart" class="height-100 donutShadow"></div>
                                </div>
                                <div class="media-body text-right mt-1">
                                    <h3 class="font-large-2 white">12,515</h3>
                                    <h6 class="mt-1"><span class="text-muted white">Analytics in the <a href="#" class="darken-2 white">last year.</a></span></h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--/ Revenue, Hit Rate & Deals -->
<!-- Emails Products & Avg Deals -->
<div class="row">
    <div class="col-md-12 col-lg-8">
        <div class="card">
            <div class="card-header p-1">
                <h4 class="card-title float-left">Project X - <span class="blue-grey lighten-2 font-small-3 mb-0">24 DEC 2017 - 09 APR 2019</span></h4>
                <span class="badge badge-pill badge-info float-right m-0">Approved</span>
            </div>
            <div class="card-content collapse show">
                <div class="card-footer text-center p-1">
                    <div class="row">
                        <div class="col-md-3 col-12 border-right-blue-grey border-right-lighten-5 text-center">
                            <p class="blue-grey lighten-2 mb-0">Tasks</p>
                            <p class="font-medium-5 text-bold-400">26</p>
                        </div>
                        <div class="col-md-3 col-12 border-right-blue-grey border-right-lighten-5 text-center">
                            <p class="blue-grey lighten-2 mb-0">Completed</p>
                            <p class="font-medium-5 text-bold-400">58%</p>
                        </div>
                        <div class="col-md-3 col-12 border-right-blue-grey border-right-lighten-5 text-center">
                            <p class="blue-grey lighten-2 mb-0">Pending</p>
                            <p class="font-medium-5 text-bold-400">42%</p>
                        </div>
                        <div class="col-md-3 col-12 text-center">
                            <p class="blue-grey lighten-2 mb-0">Version</p>
                            <p class="font-medium-5 text-bold-400">4.5</p>
                        </div>
                    </div>
                    <hr>
                    <span class="text-muted"><a href="#" class="danger darken-2">Project X</a> Statistics</span>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-12 col-lg-4">
        <div class="card pull-up border-top-info border-top-3 rounded-0">
            <div class="card-header">
                <h4 class="card-title">New Clients <span class="badge badge-pill badge-info float-right m-0">5+</span></h4>
            </div>
            <div class="card-content collapse show">
                <div class="card-body p-1">
                    <h4 class="font-large-1 text-bold-400">18.5% <i class="ft-users float-right"></i></h4>
                </div>
                <div class="card-footer p-1">
                    <span class="text-muted"><i class="la la-arrow-circle-o-up info"></i> 23.67% increase</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!--/ Emails Products & Avg Deals -->
<!-- Chat and Recent Projects -->
<div class="row match-height">
    <div class="col-xl-12 col-lg-12 col-md-12">
        <div class="card">
                <div class="card-header">
                    <h4 class="card-title">Task Calendar</h4>
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
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-3">
                                <div id='external-events'>
                                    <h4>Draggable Events</h4>
                                    <div class="fc-events-container">
                                        <div class='fc-event' data-color='#2D95BF'>All Day Event</div>
                                        <div class='fc-event' data-color='#48CFAE'>Long Event</div>
                                        <div class='fc-event' data-color='#50C1E9'>Meeting</div>
                                        <div class='fc-event' data-color='#FB6E52'>Small Task</div>
                                        <div class='fc-event' data-color='#ED5564'>Lunch</div>
                                        <div class='fc-event' data-color='#F8B195'>Conference Meeting</div>
                                        <div class='fc-event' data-color='#6C5B7B'>Party</div>
                                        <p>
                                            <input type='checkbox' id='drop-remove' />
                                            <label for='drop-remove'>remove after drop</label>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-9">
                                <div id='fc-external-drag'></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</div>
<!--/ Products sell and New Orders -->
<!-- Total earning & Recent Sales  -->

<!--/ Total earning & Recent Sales  -->
        </div>
      </div>
    </div>
   <!-- Content End -->
   
   <%@include file="footer.jsp" %>
  </body>
 
</html>