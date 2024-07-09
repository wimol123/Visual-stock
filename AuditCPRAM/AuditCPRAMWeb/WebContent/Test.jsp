<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<link href="${context}/assets/css/custom.css" rel="stylesheet"
	type="text/css" />
<link href="assets/global/plugins/datatables/datatables.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css"
	rel="stylesheet" type="text/css" />
<link href="assets/css/fix_datatable.css" rel="stylesheet"
	type="text/css" />
<link href="${context }/assets/global/plugins/style.bundle.css"
	rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Kanit">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.0.0-beta.20/css/uikit.css">
	<link rel="stylesheet" href="${context }/assets/global/plugins/thailand/jquery.Thailand.min.css">
<script src="${context}/assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
<script src="assets/global/plugins/datatables/datatables.min.js"
	type="text/javascript"></script>
<script
	src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js"
	type="text/javascript"></script>
<script src="assets/global/scripts/app.js" type="text/javascript"></script>
<%-- <script src="${context }/assets/global/plugins/bundle.js" type="text/javascript"></script> --%>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.0.0-beta.20/js/uikit.min.js"></script>
	<script type="text/javascript" src="${context}/assets/global/plugins/thailand/dependencies/zip.js/zip.js"></script>
	<script type="text/javascript" src="${context}/assets/global/plugins/thailand/dependencies/JQL.min.js"></script>
	<script type="text/javascript" src="${context}/assets/global/plugins/thailand/dependencies/typeahead.bundle.js"></script>
	<script src="${context }/assets/global/plugins/thailand/jquery.Thailand.min.js" type="text/javascript"></script>
	
	

</head>
<!-- end::Head -->
<!-- end::Body -->
<body>

<div class="row">
<div class="col-md-12">
<div class="portlet light">
	<div class="portlet-body">
		<table class="table table-striped table-bordered table-hover order-column table_managed">
    	<thead>
    		<tr>
    			<th>เกณฑ์การพิจารณา</th>
    			<th>ปัญหาที่พบ</th>
    			<th>สถานะ</th>
    			<th>วันที่ดำเนินการ</th>
    			<th>หลักฐาน</th>
    			<th>หลักฐานการแก้ไข</th>
    			<th>ตัวเลือก</th>
    		</tr>
    	</thead>
    	<tbody>
    		<tr>
    			<td>1.1.1 ต้องพิจารณาถึงกิจกรรมต่าง ๆ และสภาพแวดล้อมในบริเวณปฏิบัติงานซึ่งอาจเกิดผลกระทบในทางเสียหาย และต้องกำหนดการป้องกันการปนเปื้อนในสถานที่ปฏิบัติงานต้องทบทวนอยู่เสมอเพื่อให้มั่นใจว่าคงประสิทธิภาพ</td>
    			<td>sghfjggj</td>
    			<td>ผ่าน</td>
    			<td>14/05/2019 17:38:37</td>
    			<td>
    			
    			<button type="button" class="btn btn-primary" id="btn_save_appoint">หลักฐาน</button></td>
    			<td>
    			
    			<button type="button" class="btn btn-warning" id="btn_save_appoint">หลักฐานการแก้ไข</button></td>
    			<td>
    			
    			<button type="button" class="btn btn-primary" id="btn_save_appoint">อนุมัติการแก้ไขปัญหา</button><button type="button" class="btn btn-warning" id="btn_save_appoint">ไม่อนุมัติการแก้ไขปัญหา</button><button type="button" class="btn btn-danger" id="btn_save_appoint">ยกเลิกปัญหา</button></td>
    			
    		</tr>
    	</tbody>
    </table>
	</div>
</div>
</div>
</div>
    

</body>
<!-- end::Body -->
</html>
