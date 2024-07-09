<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="th.co.gosoft.audit.cpram.model.UserModel" %>
<%@ page import="th.co.gosoft.audit.cpram.model.UserGroupModel" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<c:set var="context" value="${pageContext.request.contextPath}" />
		<meta charset="UTF-8">
		
		<link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
		<link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
		<link href="assets/css/fix_datatable.css" rel="stylesheet" type="text/css" />
		<link href="assets/global/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
	       <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">

  
		<script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
		<script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
		<script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
		<script src="assets/global/plugins/bootstrap-datetimepicker/moment.min.js" type="text/javascript"></script>
		<script src="assets/global/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
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
					<span>Visual Stock</span>
				</li>
			</ul>
		</div>
		<div class="row">
	     	<div class="col-md-12">
	     		<div class="portlet light">
	     			<div class="portlet-title">
	                	<div class="caption font-green-sharp">
		                    <i class="fa fa-shopping-cart"></i>
		                    <span class="caption-subject bold uppercase"> Visual Stock (ตัวอย่าง)</span>
		               	</div>
		          	</div>
		          	<div class="form-group">
		          			<div class="row">
		          				<div class="col-md-3 supplier-hide">
		          					<label class="supplier-hide" > Supplier </label>
	                     			<input id="supplier_input" type="text" class="form-control input-sm supplier-hide" />
		          				</div>
		          				<div class="col-md-3">
		          					<label> Material  </label>
	                     			<input id="meterial_input" type="text" class="form-control input-sm" />
		          				</div>
	  							<div class="col-md-3">
	  								<label> Plant </label>
	                     			<input id="plant_input" type="text" class="form-control input-sm" />
	                     		</div>
	                     		<div class="col-md-3">
		          					<label> สถานะ </label>
		          					<select id="status_confirm" class="form-control form-control-sm select-sm">
	  									<option value="">--- ทั้งหมด ---</option>
	  									<option value="">รอ Supplier Confirm</option>
	  									<option value="">รอจัดซื้อตอบกลับ</option>
	  									<option value="">ส่งเมล์ไม่สำเร็จ</option>
	  									<option value="">เสร็จสมบูรณ์</option>
	  								</select>
		          				</div>
	                     		<div class="col-md-12 d-flex text-center button-group mx-auto">
		          					<button type="button" id="btn_search" class="btn btn-primary btn-sm">
	     								<b> ค้นหา </b>
	     							</button>
	     							<button type="button" id="btn_clear" class="btn default btn-sm">
	     								<b> ล้างข้อมูล </b>
	     							</button>
	     							<button type="button" id="btn_export" class="btn btn-primary btn-sm">
	     								<b> Export </b>
	     							</button>
		          				</div>
	                    	</div>               	
	                 </div> 
                     <table id="visual_stock_table" class="table table-striped table-bordered order-column" style="width:100%; height: 100%; border:black 0.5pt solid; display: none;">
				        <thead>	
				                <tr id="header-row">
				                    <th colspan="1">Po No.</th>
				                    <th colspan="1">Item No.</th>
				                    <th colspan="1">Material No.</th>
				                    <th colspan="1">Description</th>
				                    <th colspan="1">Plant</th>
				                    <th colspan="1">Total Stock</th>
				                    <th colspan="1">Unit</th>
				                    <th id='headerTime'>Deliver Date Time</th>
				                    <th>a</th>
				                </tr>

				       </thead>
                    </table>
                    
                    <table id="myTable" border="1">
    <thead>
        <!-- Header rows will be added here dynamically -->
    </thead>
    <tbody>
        <!-- Data rows will be added here dynamically -->
    </tbody>
</table>
                    
		        </div>
		     </div>
		</div>
	    <form action="/auditsupplier/api/visual_stock/export_excel" method="post" target="_blank" id="export_form">
			<input type="hidden" id="export_supplier" name="supplier_input">
			<input type="hidden" id="export_meterial" name="meterial_input">
			<input type="hidden" id="export_plant" name="plant_input">
			<input type="hidden" id="export_status" name="status_confirm">

		</form>
		<script src="assets/js/page_visual_stock.js" type="text/javascript"></script>
	</body>
</html>