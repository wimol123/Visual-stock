<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
	<meta charset="utf-8" />
	 <c:set var="context" value="${pageContext.request.contextPath}" />
     <link href="${context}/assets/css/custom.css" rel="stylesheet" type="text/css" />  
     <link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
     <link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
     <link href="assets/css/fix_datatable.css" rel="stylesheet" type="text/css" />
     <link href="${context}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css">
     <link href="${context}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.css" rel="stylesheet" type="text/css">
     
     <script src="${context}/assets/global/plugins/jquery.min.js" type="text/javascript"></script>  
     <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
     <%-- <script type="text/javascript"> var contextPath='<%=request.getContextPath()%>';</script> --%>
     <script src="${context}/assets/global/plugins/jquery-bloodhound/js/bloodhound.js" type="text/javascript"></script>
   	 <script src="${context}/assets/global/plugins/jquery-typeahead/js/typeahead.jquery.js" type="text/javascript"></script>
   	 <script src="${context}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.th.min.js" type="text/javascript"></script>
</head>
<body>

	<!-- BEGIN PAGE HEADER-->
     <div class="page-bar">
         <ul class="page-breadcrumb">
             <li>
                 <i class="icon-home"></i>
                 <a href="home.jsp">หน้าหลัก</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span>ข้อมูลการทำแบบสอบถาม</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
     
     <div class="portlet light">
     	<div class="portlet-title">
     		<div class="caption font-green-sharp">
            	<i class="icon-info"></i>
            	<span class="caption-subject bold uppercase"> ข้อมูลการทำแบบสอบถาม </span>
            </div> 
            <div class="actions">            		
           		<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
        		<a class="btn btn-circle btn-icon-only btn-default font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">
			     	<i class="fa fa-angle-down fa-lg rotate-icon"></i>
			     </a>	        	
			</div> 
     	</div>
     	<div class="portlet-body form form-body-collapse">
     	
     		<div class="form-group">	
     			<div class="row">
     			
     				<div class="col-md-3">
               			<label>  บริษัท  </label>
						<input type="text" class="form-control input-sm" id="company_textbox">
               		</div>
               		<div class="col-md-3">
               			<label>  ชื่อแบบสอบถาม  </label>
						<select class="form-control form-control-sm select-sm" id="sel_questionaire_search" name="sel_questionaire_search"></select>
               		</div>
               		
     				<div class="col-md-4">
               			<button type="button" data-loading-text="Searching..." class="btn btn-primary btn-sm" style="margin-top:20px;" id="btn_search">
							<b>ค้นหา</b>
						</button>
						<button type="button" data-loading-text="Clearing..." class="btn default btn-sm" style="margin-top:20px;" id="btn_clear">
							<b> ล้างข้อมูล </b>
						</button>
               		</div> 
     			</div>                    	
            </div>
     	
     		<table class="table table-striped table-bordered table-hover order-column table_managed" id="table_supplier_questionaire_document">
                 <thead>
                     <tr>
                     	<th> ลำดับ </th>                             	 
                         <th> บริษัท </th>
                         <th> ชื่อแบบสอบถาม </th>
                         <th> สถานะการอัพโหลด </th>
                         <th> ดาวน์โหลด </th>
                         <th> QuestionaireDocumentId </th>
                         <th> SupplierId </th>
                         <th> SupplierQuestionaireDocumentId </th>
                     </tr>
                 </thead>
                 
             </table>
     		
     	</div>
     </div>
     
     <script src="assets/js/page_questionaire_infomation.js" type="text/javascript"></script>   

</body>
</html>