<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <head>
        <meta charset="utf-8" />
        <title>Audit Supplier CPRAM</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="Preview page of Metronic Admin Theme #4 for " name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
        <link href="${contextPath}/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="${contextPath}/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="${contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${contextPath}/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="${contextPath}/assets/global/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="${contextPath}/assets/global/css/components-md.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="${contextPath}/assets/global/css/plugins-md.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN PAGE LEVEL STYLES -->
        <%-- <link href="${contextPath}/assets/pages/css/login-4.css" rel="stylesheet" type="text/css" /> --%>
        <!-- END PAGE LEVEL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="${contextPath}/assets/images/favicon.ico" /> 
        <link href="${contextPath}/assets/global/plugins/custom-block-ui/css/custom-block-ui.css" rel="stylesheet" type="text/css" />
        <link href="${contextPath}/assets/global/plugins/bootstrap-linecontrol-editor/css/editor.css" rel="stylesheet" type="text/css" />
    	<script type="text/javascript"> var contextPath='<%=request.getContextPath()%>';</script>
        </head>
    <!-- END HEAD -->

    <body >
        
        
        <div id="static" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
        	<div class="modal-dialog">
        		<div class="modal-content">
        			<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        				<h4 class="modal-title">Redirect Page</h4>
        			</div>
        			<div class="modal-body">
        				<div class="row">
        					<div class="col-md-1 col-md-offset-1" style="padding-top:30px;">
        						<div class="m-loader  m-loader--info m-loader--lg"></div>        						
        					</div>
        					<div class="col-md-10">
        						<p> กรุณารอสักครู่ ระบบกำลังเปลี่ยนเส้นทางไปยังหน้าสรุปผลการเข้าตรวจ </p>
        					</div>
        				</div>
        				
        			</div>
        			<div class="modal-footer">
        			</div>
        		</div>
        	</div>
        </div>        
        
        <form action="index.jsp" method="post" id="pass_form_finalauditresult">
    		<input type="text" class="hide" id="pass_param" name="pass_param">
    	</form>
        
        
        <!-- END COPYRIGHT -->
        <!--[if lt IE 9]>
		<script src="${contextPath}/assets/global/plugins/respond.min.js"></script>
		<script src="${contextPath}/assets/global/plugins/excanvas.min.js"></script> 
		<script src="${contextPath}/assets/global/plugins/ie8.fix.min.js"></script> 
		<![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="${contextPath}/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="${contextPath}/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${contextPath}/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="${contextPath}/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${contextPath}/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="${contextPath}/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="${contextPath}/assets/global/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
        <script src="${contextPath}/assets/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="${contextPath}/assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="${contextPath}/assets/global/plugins/jquery_validation/js/jquery.validate.js" type="text/javascript"></script>
    	<script src="${contextPath}/assets/global/plugins/jquery_validation/js/additional-methods.js" type="text/javascript"></script>
        <script src="${contextPath}/assets/global/plugins/custom-block-ui/js/custom-block-ui.js" type="text/javascript"></script>
    	<script src="${contextPath}/assets/js/custom.js" type="text/javascript"></script>
        <script src="${contextPath}/assets/js/custom_dialog.js" type="text/javascript"></script>
        <script src="${contextPath}/assets/js/page_connector_final_audit_result.js" type="text/javascript"></script>     
        <script src="${contextPath}/assets/global/plugins/bootstrap-linecontrol-editor/js/editor.js" type="text/javascript"></script>   
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <!-- END THEME LAYOUT SCRIPTS -->
    </body>

</html>