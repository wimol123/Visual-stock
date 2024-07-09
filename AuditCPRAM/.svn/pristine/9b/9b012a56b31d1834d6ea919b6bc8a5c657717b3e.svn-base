<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="th.co.gosoft.audit.cpram.model.UserModel"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%-- <%
		String contentPath = request.getContextPath();
	%> --%>
    <meta charset="utf-8" />
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<title>Audit Supplier CPRAM</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- <meta content="width=device-width, initial-scale=1" name="viewport" /> -->
    <meta content="Preview page of Metronic Admin Theme #2 for statistics, charts, recent events and reports" name="description" />
    <meta content="" name="author" />
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
    
    <link href="${contextPath}/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath}/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath}/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <link href="${contextPath}/assets/global/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
	<link href="${contextPath}/assets/global/plugins/select2/css/select2.min.css"	rel="stylesheet" type="text/css" />
	<link href="${contextPath}/assets/global/plugins/select2/css/select2-bootstrap.min.css"	rel="stylesheet" type="text/css" />
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN THEME GLOBAL STYLES -->
    <link href="${contextPath}/assets/global/css/components-md.min.css" rel="stylesheet" id="style_components" type="text/css" />
    <link href="${contextPath}/assets/global/css/plugins-md.min.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath}/assets/global/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
    <!-- END THEME GLOBAL STYLES -->
    <!-- BEGIN THEME LAYOUT STYLES -->
    <link href="${contextPath}/assets/layout/css/layout.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath}/assets/layout/css/themes/grey.css" rel="stylesheet" type="text/css" id="style_color" />
    <link href="${contextPath}/assets/layout/css/custom.min.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath}/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
    <!-- END THEME LAYOUT STYLES -->
    <link rel="shortcut icon" href="favicon.ico" /> 
    
    <link href="${contextPath}/assets/css/custom.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath}/assets/global/plugins/custom-block-ui/css/custom-block-ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath}/assets/global/plugins/bootstrap-linecontrol-editor/css/editor.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath}/assets/global/plugins/bootstrap-fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${contextPath}/assets/global/plugins/light-slider/css/lightslider.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath}/assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css" rel="stylesheet" >
    <link href="${contextPath}/assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" >
    <link href="${contextPath}/assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet">
    <link href="${contextPath}/assets/global/plugins/bootstrap-fileinput/themes/explorer/theme.css" rel="stylesheet">
        
    <script src="${contextPath}/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/global/plugins/custom-block-ui/js/custom-block-ui.js" type="text/javascript"></script>  
    
    <script src="${contextPath}/assets/js/custom.js?v=2" type="text/javascript"></script>
    <script src="${contextPath}/assets/js/custom_dialog.js" type="text/javascript"></script>
    <%-- <script type="text/javascript"> var contextPath='<%=request.getContextPath()%>';</script> --%>
    <script type="text/javascript"> var contextPath="${pageContext.request.contextPath}";</script>
    <script src="${contextPath}/assets/global/plugins/select2/js/select2.min.js" type="text/javascript" ></script>
    <script src="${contextPath}/assets/global/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/js/jquery.form.min.js"></script> 
    <script src="${contextPath}/assets/global/plugins/bootstrap-fileinput/js/plugins/piexif.min.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/global/plugins/bootstrap-fileinput/js/plugins/sortable.min.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/global/plugins/bootstrap-fileinput/js/plugins/purify.min.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/global/plugins/jquery-bloodhound/js/bloodhound.js" type="text/javascript"></script>
   	<script src="${contextPath}/assets/global/plugins/jquery-typeahead/js/typeahead.jquery.js" type="text/javascript"></script>
   	<script src="${contextPath}/assets/global/plugins/jquery-textchange/jquery.textchange.min.js"></script>
    
    <script src="${contextPath}/assets/global/plugins/lightbox/js/lc_lightbox.lite.js" type="text/javascript"></script>
    <link href="${contextPath}/assets/global/plugins/lightbox/css/lc_lightbox.css" rel="stylesheet" type="text/css">
    <link href="${contextPath}/assets/global/plugins/lightbox/skins/dark.css" rel="stylesheet" type="text/css">
    <script src="${contextPath}/assets/global/plugins/lightbox/lib/AlloyFinger/alloy_finger.min.js" type="text/javascript"></script>
	<!-- https://seantheme.com/color-admin-v1.3/ui_simple_line_icons.html -->
	
    <decorator:head />
</head>
<body class="page-sidebar-closed-hide-logo page-container-bg-solid page-md page-header-fixed page-sidebar-fixed" id="body_template">
	
		<%
		//sesionAuthen
			String name="";
			String menu="";
			String emeremail="";
			String emertel="";
			String emerurl="";
			if(session != null && request.getSession().getAttribute("sessionAuthen") != null)  {
				UserModel adminModel = (UserModel)request.getSession().getAttribute("sessionAuthen");
				name = adminModel.getFullname();				
				menu = adminModel.getUserMenu();
				emeremail = adminModel.getEmerEmail();
				emertel = adminModel.getEmerTelephone();
				emerurl = adminModel.getEmerUrl();
			}
	    %>

        <!-- BEGIN HEADER -->
        <div class="page-header navbar navbar-fixed-top">
            <!-- BEGIN HEADER INNER -->
            <div class="page-header-inner ">
                <!-- BEGIN LOGO -->
                <div class="page-logo">
                    <a href="home.jsp">
                        <img src="${contextPath}/assets/images/cpram_logo.png" alt="logo" class="logo-default" /> </a>
                    <div class="menu-toggler sidebar-toggler">
                        <!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
                    </div>
                </div>
                <!-- END LOGO -->
                <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"> </a>
                <!-- END RESPONSIVE MENU TOGGLER -->
                <!-- BEGIN PAGE TOP -->
                <div class="page-top">
                    <form class="search-form search-form-expanded" action="page_general_search_3.html" method="GET" style="display:none;">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search..." name="query">
                            <span class="input-group-btn">
                                <a href="javascript:;" class="btn submit">
                                    <i class="icon-magnifier"></i>
                                </a>
                            </span>
                        </div>
                    </form>
                    <!-- END HEADER SEARCH BOX -->
                    
                    <div class="navbar-emer">
                    	<p class="emer">ติดต่อผู้ดูแลระบบกรณีฉุกเฉิน</p>
                    	<p class="emer"><% out.print("Email : " + emeremail + " , Tel : " + emertel + " , Website : " + emerurl);%></p> 
                    </div>
                    
                    <!-- BEGIN TOP NAVIGATION MENU -->
                    <div class="top-menu">
                        <ul class="nav navbar-nav pull-right">
                            <%-- <li class="dropdown dropdown-extended dropdown-inbox" id="header_inbox_bar">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                    <i class="icon-envelope-open"></i>
                                    <span class="badge badge-default"> 2 </span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="external">W
                                        <h3>You have
                                            <span class="bold">2 New</span> Messages</h3>
                                        <a href="app_inbox.html">view all</a>
                                    </li>
                                    <li>
                                        <ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
                                            <li>
                                                <a href="#">
                                                    <span class="photo">
                                                        <img src="${contextPath}/assets/images/demo_logo1.png" class="img-circle" alt=""> </span>
                                                    <span class="subject">
                                                        <span class="from"> บริษัทอาหารทะเล จำกัด </span>
                                                        <span class="time">2 นาทีที่แล้ว </span>
                                                    </span>
                                                    <span class="message"> รายละเอียด... </span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <span class="photo">
                                                        <img src="${contextPath}/assets/images/demo_logo2.png" class="img-circle" alt=""> </span>
                                                    <span class="subject">
                                                        <span class="from"> บริษัท กุ้งหอยปูปลา จำดัด </span>
                                                        <span class="time">16 นาทีที่แล้ว </span>
                                                    </span>
                                                    <span class="message"> รายละเอียด... </span>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </li> --%>
                            <!-- END INBOX DROPDOWN -->
                            <!-- BEGIN USER LOGIN DROPDOWN -->
                            <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                            <li class="dropdown dropdown-user">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                    <%-- <img alt="" class="img-circle" src="<%=contentPath%>/assets/layout/img/avatar3_small.jpg" /> --%>
                                    <span class="username username-hide-on-mobile"> <% out.print(name);%> </span>
                                    <i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-default">
                                	<li>
                                        <a href="profile.jsp">
                                            <i class="icon-layers"></i>ข้อมูลส่วนตัว </a>
                                    </li>
                                    <li>
                                        <a href="${contextPath}/logout">
                                            <i class="icon-logout"></i> Log Out </a>
                                    </li>
                                </ul>
                            </li>
                            <!-- END USER LOGIN DROPDOWN -->
                        </ul>
                    </div>
                    <!-- END TOP NAVIGATION MENU -->
                    	</div>
                    </div>                    
                    
                </div>
                <!-- END PAGE TOP -->
            </div>
            <!-- END HEADER INNER -->
        </div>
        <!-- END HEADER -->
        <!-- BEGIN HEADER & CONTENT DIVIDER -->
        <div class="clearfix"> </div>
        <!-- END HEADER & CONTENT DIVIDER -->
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
            <div class="page-sidebar-wrapper">
                <div class="page-sidebar navbar-collapse collapse">
                    <ul class="page-sidebar-menu  page-header-fixed page-sidebar-menu-compact" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                    
                    	<% out.print(menu);%> 
                    	
                          
                    </ul>
                    <!-- END SIDEBAR MENU -->
                </div>
                <!-- END SIDEBAR -->
            </div>
            <!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
                    
 					<decorator:body />
                
					
			        <%-- Dialog Confirm --%>
					<div id="gobalDialogConfirm" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000" data-keyboard="false" data-backdrop="static">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
									<h4 class="modal-title"><div id="gobalDialogConfirmTitle"></div></h4>
								</div>
							<div class="modal-body">
							<div class="form-horizontal">
								<div id="gobalDialogConfirmBody"></div>
								</div>
							</div>
								<div class="modal-footer">
									<button type="button" id="gobal_confim_cancel" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
									<button type="button" id="gobal_confim_confirm" class="btn green" data-dismiss="modal">ตกลง</button>
								</div>
							</div>
						</div>
					</div>
					<%-- Dialog --%>
					<%-- Dialog Confirm PDPA --%>
					<div id="gobalDialogConfirmPDPA" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000" data-keyboard="false" data-backdrop="static">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
									<h4 class="modal-title"><div id="gobalDialogConfirmTitlePDPA"></div></h4>
								</div>
							<div class="modal-body">
							<div class="form-horizontal">
								<div id="gobalDialogConfirmBodyPDPA"></div>
								</div>
							</div>
								<div class="modal-footer">
									<button type="button" id="gobal_confim_cancelPDPA" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
									<button type="button" id="gobal_confim_confirmPDPA" class="btn green" data-dismiss="modal">ยินยอม</button>
								</div>
							</div>
						</div>
					</div>
					<%-- Dialog --%>
                </div>
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
        </div>
        <!-- END CONTAINER -->
        
		<!-- BEGIN FOOTER -->
        <div class="page-footer">
         <div class="page-footer-inner">
             <div class="scroll-to-top">
                 <i class="icon-arrow-up"></i>
             </div>
         </div>
         </div>
         <!-- END FOOTER -->

 	
 	<!--[if lt IE 9]>
	<script src="../assets/global/plugins/respond.min.js"></script>
	<script src="../assets/global/plugins/excanvas.min.js"></script> 
	<script src="../assets/global/plugins/ie8.fix.min.js"></script> 
	<![endif]-->
    <!-- BEGIN CORE PLUGINS -->
    
    <script src="${contextPath}/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>    
    <script src="${contextPath}/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>   
    <script src="${contextPath}/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/layout/scripts/jquery.slimscroll.min.js" type="text/javascript"></script>
    
    <!-- END CORE PLUGINS -->
 	
    <!-- BEGIN THEME GLOBAL SCRIPTS -->
    <script src="${contextPath}/assets/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/global/scripts/app.min.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
    <!-- END THEME GLOBAL SCRIPTS -->
 	
    <!-- BEGIN THEME LAYOUT SCRIPTS -->
    <script src="${contextPath}/assets/global/plugins/jquery-repeater/jquery.repeater.js" type="text/javascript"></script>    
    <script src="${contextPath}/assets/global/plugins/jquery_validation/js/jquery.validate.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/global/plugins/jquery_validation/js/additional-methods.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/layout/scripts/layout.min.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/layout/scripts/demo.min.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/layout/scripts/quick-nav.min.js" type="text/javascript"></script>
    
    <script src="${contextPath}/assets/global/plugins/jquery-bootrap-wizard/jquery.bootstrap.wizard.js" type="text/javascript"></script> 
    <script src="${contextPath}/assets/global/plugins/bootstrap-linecontrol-editor/js/editor.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/global/plugins/bootstrap-fileinput/js/fileinput.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/global/plugins/bootstrap-fileinput/js/locales/th.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/global/plugins/bootstrap-fileinput/themes/explorer/theme.js"></script>
    <script src="${contextPath}/assets/global/plugins/light-slider/js/lightslider.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/global/plugins/jquery-multithread/js/multithread.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
    
	<script src="${contextPath}/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js" type="text/javascript"></script>
	<script src="${contextPath}/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-process.js" type="text/javascript"></script>
	<script src="${contextPath}/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-ui.js" type="text/javascript"></script>
    <!-- END THEME LAYOUT SCRIPTS -->
            
            
</body>
</html>