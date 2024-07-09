<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<html>
	<head>
		<meta charset="UTF-8">
		
		<link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
		<link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
		<link href="assets/css/fix_datatable.css" rel="stylesheet" type="text/css" />
		
		<script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
		<script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
		<script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
		<script src="assets/global/scripts/app.js" type="text/javascript"></script>
	</head>
	<body>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li>
					<i class="icon-home"></i>
					<a href="home.jsp">หน้าหลัก</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="smart_po.jsp">Smart PO</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>Import PO</span>
				</li>
			</ul>
		</div>
		<div class="row">
	     	<div class="col-md-12">
	     		<div class="portlet light">
	     			<div class="portlet-title">
	                	<div class="caption font-green-sharp">
		                    <i class="fa fa-upload"></i>
		                    <span class="caption-subject bold uppercase"> Import PO </span>
		               	</div>
		               	<div class="actions">
		               		<button type="button" class="btn green-sharp btn_process" id="btn_import_history">
		               			ประวัติการ Import PO
		               		</button>
		               	</div>
		          	</div>
		          	<div class="portlet-body">
		          		<div class="form-group">
		          			<div class="row">
		          				<div class="col-md-12">
  									<input id="formFile" type="file" accept=".txt">
									<div id="errorFile" class="help-block"></div>
								</div>
							</div>
							<div class="row">
						     	<div class="col-md-12">
									<div class="import-result">
										<br>
										<h4>ผลการ Import ข้อมูล</h4>
										<div class="all-record">รายการข้อมูลทั้งหมด <label id="allRecord"></label> รายการ</div>
										<div class="success-record">บันทึกข้อมูลสำเร็จ <label id="successRecord"></label> รายการ</div>
										<div class="fail-record">บันทึกข้อมูลไม่สำเร็จ <label id="failRecord"></label> รายการ</div>
										<br>
										<div class="table-fail">
											รายการข้อมูลที่บันทึกไม่สำเร็จ
											<table class="table">
											  	<thead class="thead-light">
											    	<tr class="table-active">
											      		<th scope="col" width="5%">แถว</th>
											      		<th scope="col" width="75%">ข้อมูล</th>
											      		<th scope="col" width="20%">สาเหตุ</th>
											    	</tr>
											  	</thead>
											  	<tbody>
											  	</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
		          	</div>
		      	</div>
		  	</div>
		</div>
		
		<div id="dialog_import_history" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-md">
     			<div class="modal-content">
     				<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal">&times;</button>
        				<h4>ประวัติการ Import PO</h4>
      				</div>
      				<div class="modal-body">
      					<table id="table_import_history" class="table table-striped table-bordered order-column">
			     			<thead>
			     				<tr >
				     				<th style="text-align: center;">ดำเนินการเมื่อ</th>
				     				<th style="text-align: center;">ดำเนินการโดย</th>
				     				<th style="text-align: center;">รูปแบบ</th>
				     				<th style="text-align: center;">ไฟล์ข้อมูล</th>
			     				</tr>
			     			</thead>
			     		</table>      				
      				</div>
     			</div>
     		</div>
     	</div>
     	
  		<form action="/auditsupplier/api/smart_po/download_file_po" method="post" target="_blank" id="download_file_po">
	    	<input type="hidden" id="download_file_name" name="download_file_name">
	    	<input type="hidden" id="download_file_type" name="download_file_type">
	    </form>
		
		<script src="assets/js/page_smart_po_import.js" type="text/javascript"></script>
	</body>
</html>