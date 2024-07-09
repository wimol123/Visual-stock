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
        <link href="${contextPath}/assets/pages/css/login-4.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="${contextPath}/assets/images/favicon.ico" /> 
        <link href="${contextPath}/assets/global/plugins/custom-block-ui/css/custom-block-ui.css" rel="stylesheet" type="text/css" />
        <link href="${contextPath}/assets/global/plugins/bootstrap-linecontrol-editor/css/editor.css" rel="stylesheet" type="text/css" />
    	<script type="text/javascript"> var contextPath='<%=request.getContextPath()%>';</script>
        </head>
    <!-- END HEAD -->

    <body class=" login">
    
    	<input class="form-control hide" id="receiverParam" name="receiverParam" value="${param.pass_param}">
        <!-- BEGIN LOGO -->
		<div class="logo">
			<%-- <img src="${contextPath}/assets/images/cpram_logo.png" alt="CPRAM" /> --%>
		</div>
		<!-- END LOGO -->
        <!-- BEGIN LOGIN -->
        <div class="content">
            <!-- BEGIN LOGIN FORM -->
            <form id="frm_login" class="login-form" method="post">
            	<div class="form-group">
            		<div class="col-md-offset-4">
            			<img src="${contextPath}/assets/images/cpram_logo.png" alt="CPRAM" />
            		</div>            		
            	</div>
            	<div class="form-group">
            		<h3 class="form-title">เข้าสู่ระบบ Audit Supplier</h3>
                	<div class="alert alert-danger display-hide">
                    	<button class="close" data-close="alert"></button>
                    	<span> Enter any username and password. </span>
                	</div>            		
            	</div>
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">Username</label>
                    <div class="input-group">
                        <div class="input-group-addon"><i class="fa fa-user"></i></div>
                        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Username" name="username" id="txt_username" /> </div>
                </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">Enter password</label>
                    <div class="input-group" id="show_hide_password">
                        <div class="input-group-addon"><i class="fa fa-lock"></i></div>
                        <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="Password" name="password" id="txt_password" /> 
                    	<div class="input-group-addon">
				        	<a href=""><i id="i-eye" class="fa fa-eye-slash" aria-hidden="true"></i></a>
				      	</div>
                    </div>
                    <div class="pull-right forget-password-block">
                		<a id="forget-password" class="forget-password" href="forget_password.jsp" style="font-size: 13px;">ลืมรหัสผ่าน</a> 
                	</div> 
                </div>
                
                <br />
                <div class="form-actions">
                	<!-- <button type="button" id="btn_forget_password" class="btn green"> ลืมรหัสผ่าน </button> -->
                	<div class="row">
                		<div class="col-md-12">
                			<button type="submit" id="bt_submit" class="btn green btn-block"> เข้าสู่ระบบ </button>    <!-- pull-right  -->   
                		</div>
                	</div>
                                
                </div>
                
            </form>
            
            <form action="${contextPath}/change_password.jsp" method="post">
     			<div class="form-group">
     				<input id="userId" name="userId" type="hidden">
     			</div>
     		</form>
            <!-- END LOGIN FORM -->
        </div>
        <!-- END LOGIN -->
        <!-- BEGIN COPYRIGHT -->
        <div class="copyright"> </div>
        
        <form method="post" id="form_control_redirect" class="hide">
        	<input class="form-control" id="pass_param" name="pass_param">
        </form>
        
		<%-- Dialog Message --%>
		<div id="gobalDialog" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title"><div id="gobalDialogTitle"></div></h4>
		      </div>
		      <div class="modal-body">
					<div class="form-horizontal">
						<div id="gobalDialogBody"></div>
					</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">ปิด</button>
		      </div>
		    </div>
		  </div>
		</div>
		<%-- Dialog --%>
		
	<%-- Modal --%>
	
	<div class="modal fade" id="pdpaModal" tabindex="-1" role="dialog" aria-labelledby="pdpaModalTitle" aria-hidden="true">
  		<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable" role="document" style="width:60%;">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h4 class="modal-title" id="pdpaModal Title" style="display: inline;" >PDPA</h1>
	        		<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="display: inline;">
	          			<span aria-hidden="true">&times;</span>
	        		</button>
      			</div>
      			<div class="modal-body" id="PdpaMassage" >
      				<div style='word-wrap: break-word;' >
      					<iframe src="" height="900" width="100%" id="iframeLink"></iframe>
      				</div>
      			</div>
      			<div class="modal-footer">
      				
      				<div id="PdpaDetail" >
						
      				</div>
      				<br/>
        			<button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="UpdateCancelPdpa()">ไม่ยินยอม</button>
        			<button type="button" class="btn btn-primary" onclick="UpdatePdpa()"  >ยินยอม</button>
      			</div>
    		</div>
  		</div>
	</div>
	
	<%-- Modal --%>
	<div class="modal fade" id="privacyModal" tabindex="-1" role="dialog" aria-labelledby="privacyModalTitle" aria-hidden="true">
  		<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable" role="document" style="width:60%;">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h4 class="modal-title" id="privacyModal Title" style="display: inline;" >Privacy Notice</h1>
	        		<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="display: inline;">
	          			<span aria-hidden="true">&times;</span>
	        		</button>
      			</div>
      			<div class="modal-body" id="privacyMassage" >
      				<div style='word-wrap: break-word;' >
      					
      					 <iframe src="" height="900" width="100%" id="iframePrivacyLink"></iframe>  <!-- //when deploy --> 
      					 <!-- <iframe src="https://drive.google.com/file/d/1cZyCKSvhVVcwxhcjCAF7_u1s0PXkFZxz/preview" width="100%" height="900" allow="autoplay"></iframe> -->  
      				</div>
      			</div>
      			<div class="modal-footer">
        			<button type="button" class="btn btn-primary" onclick="acceptPrivacy()">รับทราบ</button>
      			</div>
    		</div>
  		</div>
	</div>
	
	<%-- Modal --%>
	<div class="modal fade" id="termsModal" tabindex="-1" role="dialog" aria-labelledby="termsModalTitle" aria-hidden="true">
  		<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable" role="document" style="width:60%;">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h4 class="modal-title" id="termsModal Title" style="display: inline;" >ข้อตกลงการใช้บริการ</h1>
	        		<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="display: inline;">
	          			<span aria-hidden="true">&times;</span>
	        		</button>
      			</div>
      			<div class="modal-body" id="privacyMassage" >
      				<div style='word-wrap: break-word;' >      					
      					 <iframe src="" height="900" width="100%" id="iframeTermsLink"></iframe>  <!-- //when deploy --> 
      				</div>
      			</div>
      			<div class="modal-footer">
        			<button type="button" class="btn btn-primary" onclick="submitTerms()">รับทราบ</button>
      			</div>
    		</div>
  		</div>
	</div>





	<%-- Modal --%>
	
	

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
        <script src="${contextPath}/assets/js/page_login.js" type="text/javascript"></script>     
        <script src="${contextPath}/assets/global/plugins/bootstrap-linecontrol-editor/js/editor.js" type="text/javascript"></script>   
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <!-- END THEME LAYOUT SCRIPTS -->
    </body>

</html>