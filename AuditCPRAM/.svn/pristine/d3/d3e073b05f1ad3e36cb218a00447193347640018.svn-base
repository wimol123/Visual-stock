<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="th.co.gosoft.audit.cpram.model.UserModel" %>
<%@ page import="th.co.gosoft.audit.cpram.model.UserGroupModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<c:set var="context" value="${pageContext.request.contextPath}" />
		<meta charset="UTF-8">
		
		<link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
		<link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
		<link href="assets/css/fix_datatable.css" rel="stylesheet" type="text/css" />
		<link href="assets/global/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
	     
		<script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
		<script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
		<script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
		<script src="assets/global/plugins/bootstrap-datetimepicker/moment.min.js" type="text/javascript"></script>
		<script src="assets/global/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
     	<script src="assets/global/scripts/app.js" type="text/javascript"></script>
	</head>
	<body>
		<%
			UserModel user = (UserModel) request.getSession().getAttribute("sessionAuthen");
			UserGroupModel userGroup = (UserGroupModel) user.getUserGroupId();
			out.println("<input type='hidden' id='userGroupId' value='" + userGroup.getUserGroupId() + "' />");
		%>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li>
					<i class="icon-home"></i>
					<a href="home.jsp">หน้าหลัก</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>Smart PO</span>
				</li>
			</ul>
		</div>
		
		<div class="row">
	     	<div class="col-md-12">
	     		<div class="portlet light">
	     			<div class="portlet-title">
	                	<div class="caption font-green-sharp">
		                    <i class="fa fa-shopping-cart"></i>
		                    <span class="caption-subject bold uppercase"> Smart PO </span>
		               	</div>
		               	<div class="actions">
		               		<a class="btn btn-primary green supplier-hide" href="smart_po_import.jsp" role="button"> IMPORT PO </a>
		               	</div>
		          	</div>
		          	<div class="portlet-body">
		          		<div class="form-group">
		          			<div class="row">
		          				<div class="col-md-3 supplier-hide">
		          					<label> Supplier </label>
		          					<input id="supplier" type="text" class="form-control input-sm" />
		          				</div>
		          				<div class="col-md-3">
		          					<label> เลขที่ PO. </label>
		          					<input id="po_no" type="text" class="form-control input-sm" />
		          				</div>
		          				<div class="col-md-3">
		          					<label> สถานะ </label>
		          					<select id="po_status" class="form-control form-control-sm select-sm">
	  									<option value="">--- ทั้งหมด ---</option>
	  								</select>
	  							</div>
	  							<div class="col-md-3 supplier-hide">
	  								<label> ผู้บันทึก PO </label>
	                     			<input id="po_create_by" type="text" class="form-control input-sm" />
	                     		</div>
	                    	</div>
	                    	<div class="row">
	                    		<div class="col-md-3">
		          					<label> วันที่บันทึก </label>
		          					<div class="input-group date form-input-datetime">
	               						<input type="text" id="create_date_start" class="form-control input-sm" />
	               						<span class="input-group-addon">
	               							<span class="glyphicon glyphicon-calendar"></span>
	               						</span>
	            					</div>
	            				</div>
	            				<div class="col-md-3">
	            					<label> ถึง </label>
	            					<div class="input-group date form-input-datetime">
	               						<input type="text" id="create_date_end" class="form-control input-sm" />
	               						<span class="input-group-addon">
	               							<span class="glyphicon glyphicon-calendar"></span>
	               						</span>
	            					</div>
		          				</div>
		          				<div class="col-md-6 button-group">
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
	             		
	             		<button type="button" id="btn_accepted_all" class="btn btn-primary btn-sm">
	     								<b> OK ทั้งหมด </b>
	     				</button>
	     			    <button type="button" id="print_all_po_btn" class="btn btn-primary btn-sm" data-files="">
	     								<b> พิมพ์ทั้งหมด </b>
	     				</button>
	     				<button type="button" id="print_btn" class="btn btn-primary btn-sm" data-files="">
	     								<b> พิมพ์ </b>
	     				</button>
	             		<table class="table table-striped table-bordered table_managed " id="smart_po_table">
	                		<thead>
	                			<tr>
	                			    <th>
	                			            <div class="form-check" id="check_all_select">
											  	<input class="form-check-select-input" type="checkbox" 
											  		id="all_selected" onclick="selectAllPo();">
											  	<label class="form-check-label" for="all_select">
											   		เลือกทั้งหมด
											  	</label>
											</div>
									</th>
									<th>ลำดับ</th>
	                				<th>เลขที่ PO</th>
	                				<th class="supplier-hide">Supplier</th>
	                				<th>วันที่บันทึก</th>
	                				<th>สถานะ</th>
	                				<th >ส่งเมล์</th>
	                				<th >อ่าน</th>
	                				<th >พิมพ์</th>
	                				<th>Supplier ตอบรับ</th>
	                				<th>เลือก</th>
	                			</tr>
	                		</thead>               	
	                	</table>
	                	
	               	</div>
		      	</div>
		  	</div>
		</div>
		
		<div id="dialog_po_detail" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-fullscreen">
     			<div class="modal-content">
     				<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal">&times;</button>
        				<h4>รายละเอียด PO</h4>
      				</div>
      				<div class="modal-body">
      					<div class="portlet-body">
			          		<div class="form-group">
			          			<div class="row">
			          				<div class="col-md-3 text-right"><b>Purchasing Doc. : </b></div>
			          				<div class="col-md-3"><span id="poNo"></span></div>
			          				<div class="col-md-3 text-right"><b>สถานะ : </b></div>
			          				<div class="col-md-3"><span id="statusName"></span></div>
			          			</div>
			          			<div class="row">
			          				<div class="col-md-3 text-right"><b>Vendor : </b></div>
			          				<div class="col-md-3"><span id="supplierName"></span></div>
			          				<div class="col-md-3 text-right"><b>วันที่บันทึก : </b></div>
			          				<div class="col-md-3"><span id="createDate"></span></div>
			          			</div>
			          			<div class="row">
			          				<div class="col-md-3 text-right"><b>Create on(PO) : </b></div>
			          				<div class="col-md-3"><span id="poCreateDate"></span></div>
			          				<div class="col-md-3 text-right"></div>
			          				<div class="col-md-3"></div>
			          			</div>
			          			<div class="row">
			          				<div class="col-md-3 text-right"><b>ผู้บันทึก PO : </b></div>
			          				<div class="col-md-3"><span id="createBy"></span></div>
			          				<div class="col-md-3 text-right"></div>
			          				<div class="col-md-3"></div>
			          			</div>
		             		</div>
		             		<table id="po_detail_table" class="table table-striped table-bordered order-column">
		                		<thead>
		                			<tr>
		                				<th rowspan="2">Item</th>
		                				<th rowspan="2">Material Code</th>
		                				<th rowspan="2">Material Name</th>
		                				<th rowspan="2">Delivery Date</th>
		                				<th rowspan="2">Quantity</th>
		                				<th rowspan="2">Unit</th>
		                				<th rowspan="2">Net Price</th>
		                				<th rowspan="2">Net Value</th>
		                				<th class="dt-po-supplier" colspan="8">Supplier ตอบรับ PO</th>
		                			</tr>
		                			<tr>
		                				<th class="dt-po-supplier">
		                					ครั้งที่ 1 
		                					<div class="form-check" id="check_all_1">
											  	<input class="form-check-input" type="checkbox" 
											  		id="all_accepted_1" onclick="selectAll(1);">
											  	<label class="form-check-label" for="all_accepted_1">
											   		OK ทั้งหมด
											  	</label>
											</div>
		                				</th>
		                				<th class="dt-po-supplier">หมายเหตุ</th>
		                				<th class="dt-po-supplier">จัดซื้อตอบกลับ</th>
		                				<th class="dt-po-supplier">
		                					ครั้งที่ 2 
		                					<div class="form-check" id="check_all_2">
											  	<input class="form-check-input" type="checkbox" 
											  		id="all_accepted_2" onclick="selectAll(2);">
											  	<label class="form-check-label" for="all_accepted_2">
											   		OK ทั้งหมด
											  	</label>
											</div>
		                				</th>
		                				<th class="dt-po-supplier">หมายเหตุ</th>
		                				<th class="dt-po-supplier">จัดซื้อตอบกลับ</th>
		                				<th class="dt-po-supplier">
		                					ครั้งที่ 3 
		                					<div class="form-check" id="check_all_3">
											  	<input class="form-check-input" type="checkbox" 
											  		id="all_accepted_3" onclick="selectAll(3);">
											  	<label class="form-check-label" for="all_accepted_3">
											   		OK ทั้งหมด
											  	</label>
											</div>
		                				</th>
		                				<th class="dt-po-supplier">หมายเหตุ</th>
		                			</tr>
		                		</thead>                	
		                	</table>
		                	<div class="row">
		          				<div class="col-md-3"></div>
		          				<div class="col-md-6 text-center">
		          					<button type="button" id="btn_accepted" class="btn btn-primary btn-sm">
	     								<b> ส่งผลการตอบรับ PO </b>
	     							</button>
		          					<button type="button" id="btn_response" class="btn btn-primary btn-sm">
	     								<b> ส่งผลการตอบกลับ PO </b>
	     							</button>
	     							<button type="button" id="btn_print" class="btn btn-primary btn-sm">
			     						<i class="fa fa-print"></i>
			     					</button>
		          				</div>
		          				<div class="col-md-3"></div>
		          			</div>
		          			<br>
		                	<div class="accordion" id="accordionHistory">
								<div class="card">
								  	<div class="card-header" id="accordionHead">
								    	<button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapseHistory" aria-expanded="true" aria-controls="collapseHistory">
								        	<h5>ประวัติการดำเนินการ</h5>
								      	</button>
								  	</div>
									<div id="collapseHistory" class="collapse" aria-labelledby="accordionHead" data-parent="#accordionHistory">
								    	<div class="card-body">
								    		<table id="po_history_table" class="table table-striped table-bordered order-column">
						                		<thead>
						                			<tr>
						                				<th>ลำดับ</th>
						                				<th>การดำเนินการ</th>
						                				<th>วันที่ดำเนินการ</th>
						                				<th>ดำเนินการโดย</th>
						                				<th>หมายเหตุ</th>
						                			</tr>
						                		</thead>                	
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
		
		<form action="/auditsupplier/api/smart_po/print_po" method="post" target="_blank" id="print_po_form">
	    	<input type="hidden" id="print_po_no" name="print_po_no">
	    </form>
	    <form action="/auditsupplier/api/smart_po/print_many_po" method="post" target="_blank" id="print_all_po_form">
	    	<input type="hidden" id="print_all_po_no" name="print_all_po_no">
	    </form>
	    
	    <form action="/auditsupplier/api/smart_po/export_excel" method="post" target="_blank" id="export_po_form">
			<input type="hidden" id="export_supplier" name="supplier">
			<input type="hidden" id="export_po_no" name="po_no">
			<input type="hidden" id="export_po_status" name="po_status">
			<input type="hidden" id="export_po_create_by" name="po_create_by">
			<input type="hidden" id="export_create_date_start" name="create_date_start">
			<input type="hidden" id="export_create_date_end" name="create_date_end">
		</form>
		
		<script src="assets/js/page_smart_po.js" type="text/javascript"></script>
	</body>
</html>