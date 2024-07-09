<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<c:set var="context" value="${pageContext.request.contextPath}" />
	
	<meta charset="utf-8" />
	
	 <link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
     <link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
     <link href="assets/css/fix_datatable.css" rel="stylesheet" type="text/css" />
     <link href="${context}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css">
     <link href="${context}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.css" rel="stylesheet" type="text/css">
     
     <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
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
                 <span>สรุปผลการประเมิน</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
     
     <div class="row">
     	<div class="col-md-12">
     		<div class="portlet light">
     			<div class="portlet-title">
                	<div class="caption font-green-sharp">
	                    <i class="icon-screen-desktop"></i>
	                    <span class="caption-subject bold uppercase"> สรุปผลการประเมิน </span>	                    
	                </div>  
                    <div class="actions">
                    	<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
                    </div>    
                </div>
                
                <div class="portlet-body">
                
                	<div class="form-group">
                     	<div class="row">
                     		<div class="col-md-3">
                     			<label> เลขที่แผนการประเมิน  </label>
    							<input type="text" class="form-control input-sm" id="checklist_plan_no_search">
                     		</div>
                     		
                     		<div class="col-md-3">
                     			<label> บริษัท  </label>
                     			<input type="text" class="form-control input-sm" id="supplier_name_search">
                     		</div> 
                     		                     		
                     		<div class="col-md-3">
                     			<label> วันที่นัดหมาย  </label>
                     			<div class="input-group date datepicker-form" data-provide="datepicker">
    								<input type="text" class="form-control input-sm" readonly="readonly" id="plan_date_textbox">
    								<div class="input-group-addon">
        								<span class="glyphicon glyphicon-calendar"></span>
    								</div>
								</div>
                     		</div>
                     		
                     		<div class="col-md-3">
                     			<label>  สถานะ  </label>
    							<select class="form-control form-control-sm select-sm" id="final_audit_status">
  									<option value="">--- ทั้งหมด ---</option>
  									<option value="1">   รอแจ้งผลการตรวจ  </option>
  									<option value="2"> รอซัพพลายเออร์รับทราบ </option>
  									<option value="3"> ดำเนินการแก้ไข</option>
  									<option value="6"> รออนุมัติ </option>
  									<option value="4"> เสร็จสิ้น </option>
  									<option value="5"> ยกเลิก </option>
								</select>
                     		</div>                		
                     		             		
                     		
                     		
                     	</div>
                     	<div class="row">
                     		<div class="col-md-3" style="margin-top:  30px;">
                     			<button type="button" class="btn btn-primary btn-sm"  id="btn_search">
     								<b>ค้นหา</b>
     							</button>
     							<button type="button" class="btn default btn-sm" id="btn_clear">
     								<b> ล้างข้อมูล </b>
     							</button>
                     		</div>
                     	</div>
                     </div>
                     
                     
                	
                	<table class="table table-striped table-bordered table-hover order-column" id="table_final_auditresult">
                		<thead>
                			<tr>
                				<th>ลำดับ</th>
                				<th>เลขที่แผนการประเมิน</th>
                				<th>บริษัท</th>
                				<th>วันเวลานัดหมาย</th>
                				<th>เกรด</th>
                				<th>สถานะ</th>
                				<th>เลือก</th>
                			</tr>
                		</thead>                	
                	</table>
                </div>                
     		</div>
     	</div>
     </div>
     
     
     <form action="final_auditresult_form.jsp" method="post" id="pass_form_finalauditresult">
    	<input type="text" class="hide" id="pass_param" name="pass_param">
    </form>
     
     <script src="${context}/assets/js/page_finalauditresult.js" type="text/javascript" ></script>

</body>
</html>