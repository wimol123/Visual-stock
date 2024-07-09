<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
	<meta charset="utf-8" />
	<link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
     <link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
     <link href="assets/css/fix_datatable.css" rel="stylesheet" type="text/css" />
     
     <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
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
                 <span>การจัดการประเภทเอกสาร</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
     
     
      <div class="row">
		<div class="col-md-12">      
              
     		<div class="portlet light" id="Document-table">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-layers"></i>
                        <span class="caption-subject bold"> การจัดการประเภทเอกสาร</span>
                    </div>         
                </div>
                <div class="portlet-body form">
                	<table class="table">
					  <thead class="thead-light">
					    <tr>
					      <th scope="col">ลำดับ</th>
					      <th scope="col">ชื่อเอกสาร</th>
					      <th scope="col">ใช้งาน</th>
					      <th scope="col">Action</th>
					    </tr>
					  </thead>
					  <tbody id="Table-CRUD">
					  
					    
					  </tbody>
					</table>
               
                    <div class="form-actions">
                    	<button type="button" style="Margin:5px;" class="btn green" data-toggle="modal" data-target="#exampleModalLong">เพิ่มข้อมูล</button>
                        <button type="button" style="Margin:5px;" class="btn green" id="btn_save_update">บันทึกข้อมูล</button>
                        </div>           		
                </div>
            </div>
     	</div>
	</div>
     
     
	<script src="assets/js/page_document.js" type="text/javascript"></script>
	
	<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h4 class="modal-title" id="exampleModalLongTitle" style="display: inline;" >เพิ่มประเภทเอกสาร</h1>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="display: inline;">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
			    <div class="form-group">
					<label>ชื่อเอกสาร</label>
					<input type="text" class="form-control" id="Document_name" name="Document_name" placeholder="Enter Document Name">
				</div>
				
				<label>สถานะการใช้งานเอกสาร</label>
				<div class="form-check">
				  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" checked>
				  <label class="form-check-label" for="flexRadioDefault1">
				    Yes
				  </label>
				</div>
				
				<div class="form-check">
				  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2">
				  <label class="form-check-label" for="flexRadioDefault2">
				    No
				  </label>
				</div>
				
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">ยกเลิก</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="Add()">บันทึก</button>
	      </div>
	    </div>
	  </div>
	</div>
	</div>
	
	
	
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h4 class="modal-title" id="editModal Title" style="display: inline;" >เเก้ไขชื่อเอกสาร</h1>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="display: inline;">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
			    <div class="form-group">
					<label>ชื่อเอกสาร</label>
					<input type="text" class="form-control" id="Edit_Document_name" name="Edit_Document_name" placeholder="Enter Document Name">
				</div>
				
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">ยกเลิก</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="editname()">บันทึก</button>
	      </div>
	    </div>
	  </div>
	</div>
	</div>
	
	
	
	
</body>
</html>