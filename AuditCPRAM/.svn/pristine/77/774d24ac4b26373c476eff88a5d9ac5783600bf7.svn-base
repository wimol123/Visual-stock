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
        <link href="${contextPath}/assets/css/custom.css" rel="stylesheet" type="text/css" />
        <link href="${contextPath}/assets/global/plugins/custom-block-ui/css/custom-block-ui.css" rel="stylesheet" type="text/css" />
        <link href="${contextPath}/assets/global/plugins/bootstrap-linecontrol-editor/css/editor.css" rel="stylesheet" type="text/css" />
    	<script type="text/javascript"> var contextPath='<%=request.getContextPath()%>';</script>
    	<style type="text/css">
    		.login .content label{
    			color: #e73d4a;
    		}
    	</style>
        </head>
    <!-- END HEAD -->
    

    <body class=" login">
        <!-- BEGIN LOGO -->
        <div class="logo">
            <%-- <img src="${contextPath}/assets/images/cpram_logo.png" alt="CPRAM" /> --%>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN LOGIN -->
        <div class="content">
            <!-- BEGIN LOGIN FORM -->
            <form id="frm_forgetpass" class="login-form" method="post">
            	<div class="form-group">
            		<div class="col-md-offset-4">
            			<img src="${contextPath}/assets/images/cpram_logo.png" alt="CPRAM" />
            		</div>            		
            	</div>
                <h3 class="form-title">ลืมรหัสผ่าน</h3>
                <div class="alert alert-danger display-hide">
                    <button class="close" data-close="alert"></button>
                    <span> Enter any username and password. </span>
                </div>
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">Email</label>
                    <div class="input-icon">
                        <i class="fa fa-envelope"></i>
                        <input class="form-control" type="text" autocomplete="off" placeholder="Email" name="email" id="txt_email" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">Enter Username</label>
                    <div class="input-icon">
                        <i class="fa fa-user"></i>
                        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="username" name="username" id="txt_username" /> 
                    </div> 
                    <div class="pull-right forget-password-block">
                		<a id="forget-password" class="forget-password" href="index.jsp" style="font-size: 13px;">กลับไปที่หน้าเข้าสู่ระบบ</a> 
                	</div>                    
                </div>
                
                <br />
                <div class="form-actions">
                	<!-- <button type="button" id="btn_forget_password" class="btn green"> ลืมรหัสผ่าน </button> -->
                	<div class="row">
                		<div class="col-md-12">
                			<button type="button" id="btn_regenpassword" class="btn green btn-block" > สร้างรหัสผ่านใหม่ </button>    <!-- pull-right  -->   
                		</div>
                	</div>
                                
                </div>
                
            </form>
            <!-- END LOGIN FORM -->
        </div>
        <!-- END LOGIN -->
        <!-- BEGIN COPYRIGHT -->
        <div class="copyright"> </div>
        
	        <%-- Dialog --%>
		<div id="dialog_success_genPassword" class="modal fade" tabindex="-1" data-keyboard="false" data-backdrop="static" role="dialog" style="z-index:12000">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		       	<!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
		       	<h4 class="modal-title">สถานะการทำงาน</h4>
		      </div>
		      <div class="modal-body">
		        <!-- <form role="form" id="form-add-product-type">
		            <div class="form-body">						
		       			<div class="form-group">
		       				
		       			</div>
		            </div>
		            <hr>
		            <div class="form-actions">
		                <button type="button" id="dal_btn_saveProduct" class="btn green">บันทึก</button>
		            </div>
		        </form>    
		        <hr>  -->       
		        <p Style="font-size: 16px;"> ระบบได้ทำการสร้างรหัสผ่านใหม่ให้ท่านเรียบร้อยแล้ว โดยรหัสผ่านใหม่จะถูกส่งไปยังอีเมล์ โปรดตรวจสอบอีเมล์และกดปุ่มดำเนินการต่อ </p>                   
		      </div>
		      <div class="modal-footer">
		      	<a type="button" href="index.jsp" class="btn green">ดำเนินการต่อ</a>
		      </div>
		    </div>
		  </div>
		</div>
		<%-- Dialog --%>
        
        
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
        <script src="${contextPath}/assets/js/page_forgetpassword.js" type="text/javascript"></script>     
        <script src="${contextPath}/assets/global/plugins/bootstrap-linecontrol-editor/js/editor.js" type="text/javascript"></script>   
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <!-- END THEME LAYOUT SCRIPTS -->
    </body>

</html>