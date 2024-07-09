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
		<link href="assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css">
	     
		<script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
		<script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
		<script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
		<script src="assets/global/plugins/bootstrap-datetimepicker/moment.min.js" type="text/javascript"></script>
		<script src="assets/global/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
     	<script src="assets/global/scripts/app.js" type="text/javascript"></script>
     	<script src="assets/global/plugins/select2/js/select2.min.js" type="text/javascript"></script>
     	
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
					<span>Graph Data</span>
				</li>
			</ul>
		</div>
		<div class="row">
	     	<div class="col-md-12">
	     		<div class="portlet light">
	     			<div class="portlet-title">
	                	<div class="caption font-green-sharp">
		                    <i class="fa fa-shopping-cart"></i>
		                    <span class="caption-subject bold uppercase"> Graph Data (ตัวอย่าง)</span>
		               	</div>
		          	</div>
		          	<div class="form-group">
		          			<div class="row">
		          				<div class="col-md-3 supplier-hide">
		          					<label> รูปแบบช่วงเวลา </label>
		          					<select id="graph_x" class="form-control form-control-sm select-sm">
	  									<option value="day">--- รายวัน ---</option>
	  									<option value="month">--- รายเดือน ---</option>
	  								</select>
		          				</div>
		          				<div class="col-md-3">
		          					<label> ข้อมูลที่ต้องการดู </label>
		          					<select id="graph_y" class="form-control form-control-sm select-sm">
	  									<option value="supplier">--- supplier ---</option>
	  									<option value="material">--- material ---</option>
	  								</select>
		          				</div>

	                    	</div>               	
	                 </div>
	                 <div class="form-group-supplier">
		          			<div class="row">
		          				<div class="col-md-3">
		          					<label> ระบุ Supplier </label>
		          					<select multiple tabindex="-1" id="graph_y_supplier" class="form-control form-control-sm select-sm">
		          					    <option value="">--- ข้อมูลที่ต้องการดู ---</option>
	  								</select>
		          				</div>
	                     		<div class="col-md-3 button-group">
		          					<button type="button" id="btn_supplier_search" class="btn btn-primary btn-sm">
	     								<b> ค้นหา </b>
	     							</button>
	     						</div>
	                    	</div>               	
	                 </div>
	                 <div class="form-group-material">
		          			<div class="row">
		          				<div class="col-md-3">
		          					<label> ระบุ Material </label>
		          					<select multiple tabindex="-1" id="graph_y_material" class="form-control form-control-sm select-sm">
		          					    <option value="">--- ข้อมูลที่ต้องการดู ---</option>
	  								</select>
		          				</div>
	                     		<div class="col-md-3 button-group">
		          					<button type="button" id="btn_material_search" class="btn btn-primary btn-sm">
	     								<b> ค้นหา </b>
	     							</button>
	     						</div>
	                    	</div>               	
	                 </div>
	                
	                 <div id="container" style="height: 12cm"></div>
		        </div>
		     </div>
		</div>
		
		
	  <script src="assets/js/page_graph_data.js" type="text/javascript"></script>
	  <script type="text/javascript" src="https://fastly.jsdelivr.net/npm/echarts@5.5.0/dist/echarts.min.js"></script>
	  
	</body>
</html>