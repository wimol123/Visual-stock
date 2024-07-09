<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	 <c:set var="context" value="${pageContext.request.contextPath}" />
     <link href="${context}/assets/css/custom.css" rel="stylesheet" type="text/css" />  
     <link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
     <link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
     <link href="assets/css/fix_datatable.css" rel="stylesheet" type="text/css" />
     <script src="${context}/assets/global/plugins/jquery.min.js" type="text/javascript"></script>  
     <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
   	 
   	 
   	 <style type="text/css">
   	 	.close{
   	 		text-indent : 0px; !important
   	 		width: 14px;
			height: 21px;
   	 	}
   	 	
   	 	
   	 	
   	 </style>
	 
</head>
<body>

	<!-- BEGIN PAGE HEADER-->
     <div class="page-bar">
         <ul class="page-breadcrumb">
             <li>
                 <i class="icon-home"></i>
                 <a href="dashboard.jsp">หน้าหลัก</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span>แม่แบบเช็คลิสต์</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
     
     <div class="row">
     	<div class="col-md-12">
     		<!-- BEGIN EXAMPLE TABLE PORTLET-->
     		<div class="portlet light">
     		
     			<div class="portlet-title">
             		<div class="caption font-green-sharp">
             			<i class="icon-puzzle"></i>
	                    <span class="caption-subject bold uppercase"> แม่แบบเช็คลิสต์ </span>
             		</div>
             		<div class="actions">
						<a class="btn sbold green" href="template_checklist_form.jsp" > เพิ่มแม่แบบเช็คลิสต์
                           	<i class="fa fa-user-plus"></i>
                        </a>	
                        <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>					
					</div> 
             	</div>
             	
             	
             	<div class="portlet-body">
             	
             		<div class="form-group">
             			<div class="row">
             				<div class="col-md-3">
             					<label>  ชื่อแม่แบบเช็คลิสต์  </label>
    							<input type="text" class="form-control input-sm" id="template_checklist_name">
             				</div>
             				<div class="col-md-3">
             					<label>  สถานะ  </label>
    							<select class="form-control form-control-sm select-sm" id="select_status">
  									<option value="">--- ทั้งหมด ---</option>
  									<option value="1"> ใช้งาน </option>
  									<option value="0"> ไม่ใช้งาน </option>
								</select>
             				</div>
             			</div>
             		</div>
             		
             		<div class="form-group">
                     	<div class="row">
                     		<div class="col-md-4">
                     			<button type="button" data-loading-text="Searching..." class="btn btn-primary btn-sm" style="margin-top:8px;" id="btn_search">
     								<b>ค้นหา</b>
     							</button>
     							<button type="button" data-loading-text="Clearing..." class="btn default btn-sm" style="margin-top:8px;" id="btn_clear">
     								<b> ล้างข้อมูล </b>
     							</button>
                     		</div>                     		                 		
                     	</div>
                     </div>
             		
             		<table class="table table-striped table-bordered table-hover order-column table_managed" id="table_template_checklist">
                         <thead>
                             <tr>
                             	<th> ลำดับ </th>                             	 
                                 <th> Template ID </th>
                                 <th> ชื่อแม่แบบเช็คลิสต์ </th>
                                 <th> สถานะ </th>
                                 <th> เลือก </th>
                             </tr>
                         </thead>
                         
                     </table>
                     
                     
                     
             	</div>    
             	         	
             	
     		</div>
     	</div>
     </div>
     
     <form class="hide" action="template_checklist_form.jsp" id="form_pass_param" method="POST">
     	<input id="value_param" name="value_param" />
     </form>
     
    <script type="text/javascript" src="${context}/assets/js/page_templatechecklist.js"></script>



</body>
</html>