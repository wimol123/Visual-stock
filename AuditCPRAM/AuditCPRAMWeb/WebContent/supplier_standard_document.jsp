<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="th.co.gosoft.audit.cpram.model.UserModel"%>
<%@page import="th.co.gosoft.audit.cpram.model.UserGroupModel"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
	<meta charset="utf-8" />
	 <c:set var="context" value="${pageContext.request.contextPath}" />
     <link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
     <link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
     <link href="assets/css/fix_datatable.css" rel="stylesheet" type="text/css" />
	<!-- <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous"> -->
	 <link href="assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css">
     <link href="assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.css" rel="stylesheet" type="text/css">
	   
     <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
     <script src="assets/pages/scripts/table-datatables-managed.js" type="text/javascript"></script>  
     <script src="assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
     <script src="assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.th.min.js" type="text/javascript"></script>
     
   
</head>
<body>



    <%
		//Check UserGroupId Permission
			UserModel adminModel = (UserModel)request.getSession().getAttribute("sessionAuthen");
			UserGroupModel groupModel = (UserGroupModel)adminModel.getUserGroupId();
			if(Integer.parseInt(groupModel.getUserGroupId()) != 3 && Integer.parseInt(groupModel.getUserGroupId()) != 4){
				out.println("<script>window.location.replace('/auditsupplier/home.jsp')</script>");
			}
	%>



	<!-- BEGIN PAGE HEADER-->
     <div class="page-bar">
         <ul class="page-breadcrumb">
             <li>
                 <i class="icon-home"></i>
                 <a href="home.jsp">หน้าหลัก</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span>เอกสารรับรองมาตรฐาน</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
     
     
     <div class="portlet light" id="section-standard-document">
     	<div class="portlet-title">
     		<div class="caption font-green-sharp">
            	<i class="icon-docs"></i>
            	<span class="caption-subject bold uppercase"> เอกสารรับรองมาตรฐาน </span>
            </div> 
            <div class="actions">            		
           		<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
        		<a class="btn btn-circle btn-icon-only btn-default font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">
			     	<i class="fa fa-angle-down fa-lg rotate-icon"></i>
			     </a>	        	
			</div> 
     	</div>
     	<div class="portlet-body body-standard-document-list form form-body-collapse">
     		
     	</div>
     </div>
     
     
     <div class="row hide">
     	<div class="col-md-6">
     		<div class="portlet light template_standard_document_portlet">
		     	<div class="portlet-title">
		     		<div class="caption font-green-sharp">
		            	<span class="caption-subject bold uppercase standard_document_name"></span>
		            	<input class="form-control standard_document_id" value="1">
		            </div> 
		            <div class="actions">            		
		           		<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
		        		<a class="btn btn-circle btn-icon-only btn-default font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">
					     	<i class="fa fa-angle-down rotate-icon"></i>
					     </a>	        	
					</div> 
		     	</div>
		     	<div class="portlet-body form form-body-collapse">
		     		
		     	</div>
		     </div>
     	</div>
     </div>  
     
     
     <%-- Dialog Edit Expire Date --%>
	<div id="dialog_assign_expire_date_supplier_standard_document" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static"  >	<!-- style=" z-index: 100000000 !important;" -->
     	<div class="modal-dialog modal-lg">
     		<div class="modal-content">    
     		 		
     			<div class="modal-header">     				
     				<div class="row">
     					<div class="col-md-9">
        					<h4>กำหนดวันหมดอายุของเอกสารรับรองมาตรฐาน</h4>
     					</div>
     					<div class="col-md-3">
			            	<!-- <button  class="btn green" id="btn_solve_car" > ส่งหลักฐานการแก้ไข   <i class="fa fa-folder"></i></button>   -->
     						<button type="button" class="close" data-dismiss="modal">&times;</button>
     					</div>
     				</div>     			
        			
      			</div>
      			
      			<div class="modal-body">
      				
      				<div class="form-group hide">
      					<div class="row">
      						      						
      						<div class="col-md-12" >
								<div class="input-group">
									<input type="text" class="form-control input-sm" readonly="readonly" id="standard_document_id" name="standard_document_id">		
															
								</div>
							</div>
      					</div>      					
      				</div>
      				
      				<div class="form-group">
      					<div class="row">
      						<label class="control-label col-md-4" style="text-align:right">อัพโหลดไฟล์โดย : </label> 
      						<label class="control-label col-md-3" style="text-align:left" id="create_file_by"></label> 
      						<label class="control-label col-md-2" style="text-align:right">เมื่อวันที่</label> 
      						<label class="control-label col-md-3" style="text-align:left" id="create_file_date"></label> 
      						<div style="display:none" id="update_file">
	      						<label class="control-label col-md-4" style="text-align:right">Update วันหมดอายุของเอกสารโดย : </label> 
	      						<label class="control-label col-md-3" style="text-align:left" id="update_file_by"></label> 
	      						<label class="control-label col-md-2" style="text-align:right">เมื่อวันที่</label> 
	      						<label class="control-label col-md-3" style="text-align:left" id="update_file_date"></label> 
      						</div>
      						
      						<label class="control-label col-md-5 col-md-offset-1" style="text-align:right"> วันหมดอายุของเอกสารรับรองมาตรฐาน :<span class="require_Asterisk">*</span></label> 
      						
      						<div class="col-md-4" >
      							<input type="text" class="form-control input-sm hide" readonly="readonly" id="expire_date_standard_document_temp" name="expire_date_standard_document_temp">
								<div class="input-group date datepicker-dialog" data-provide="datepicker" id="expire_date_standard_document_input_section">
									<input type="text" class="form-control input-sm" readonly="readonly" id="expire_date_standard_document_dialog" name="expire_date_standard_document_dialog">
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</div>
								</div>
							</div>
      					</div>      					
      				</div>
      				
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-4 col-md-offset-4"> 
	      						<button class="btn green-seagreen" id="save_expire_date_standard_document" >บันทึกวันหมดอายุของเอกสารรับรองมาตรฐาน  <i class="fa fa-save"></i>  </button>
	      					</div>
      					</div>
      				</div>
      				      				
      				
     		</div>
     	</div>
     </div>
     </div>
 
     
	<script src="${context}/assets/js/page_supplierstandarddocument.js" type="text/javascript"></script>   
	
</body>
</html>