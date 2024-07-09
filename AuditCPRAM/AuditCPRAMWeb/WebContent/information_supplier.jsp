<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
     <link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
     <link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
     <link href="assets/css/fix_datatable.css" rel="stylesheet" type="text/css" />
     <link href="assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css">
     <link href="assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.css" rel="stylesheet" type="text/css">
     
     <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
     <script src="assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
     <script src="assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.th.min.js" type="text/javascript"></script>
   
   	 <c:set var="context" value="${pageContext.request.contextPath}" />
   	 
   	 <style type="text/css">
   	 	.close{
   	 		text-indent : 0px; !important
   	 		width: 14px;
			height: 21px;
   	 	}
   	 </style>
   	 
</head>
<body>
	<div class="bgLoader"></div>
	<div class="imgLoader"></div>
     <!-- BEGIN PAGE HEADER-->
     <div class="page-bar">
         <ul class="page-breadcrumb">
             <li>
                 <i class="icon-home"></i>
                 <a href="home.jsp">หน้าหลัก</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span>ข้อกำหนด/ข่าวสาร</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
                    

     
     
     
     <div class="row">
         <div class="col-md-12">
             <!-- BEGIN EXAMPLE TABLE PORTLET-->
             <div class="portlet light ">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                		<i class="icon-info"></i>
                        <span class="caption-subject bold uppercase">ข้อกำหนด / ข่าวสาร</span>                         
                    </div>
                </div>
                
                 <div class="portlet-body">
                 
                 	<div class="form-group">
                     	<div class="row">
                     		<div class="col-md-3">
                     			<label>  ชื่อข้อกำหนด/ข่าวสาร  </label>
    							<input type="text" class="form-control input-sm" id="information_name">
                     		</div>
                     		<div class="col-md-3">
                     			<label>วันที่แจ้ง</label>
    							<div class="input-group date datepicker-effective" data-provide="datepicker">
    								<input type="text" class="form-control input-sm" readonly="readonly" id="sendDate_textbox">
    								<div class="input-group-addon">
        								<span class="glyphicon glyphicon-calendar"></span>
    								</div>
								</div>
                     		</div>
                     		<div class="col-md-3">
                     			<label>  สถานะการรับทราบ </label>
    							<select class="form-control form-control-sm" id="select_status">
  									<option value="">--- ทั้งหมด ---</option>
  									<option value="Y">รับทราบ</option>
  									<option value="N">ยังไม่รับทราบ</option>
								</select>
                     		</div>
                     	</div>                     	
                     </div>                     
                     
                     <div class="form-group">
                     	<div class="row">
                     		<div class="col-md-4">
                     			<button type="button" class="btn btn-primary btn-sm" style="margin-top:8px;" id="btn_search">
     								<b>ค้นหา</b>
     							</button>
     							<button type="button" class="btn default btn-sm" style="margin-top:8px;" id="btn_clear">
     								<b> ล้างข้อมูล </b>
     							</button>
                     		</div>                     		                 		
                     	</div>
                     </div>
                 
                     <table class="table table-striped table-bordered table-hover order-column table_managed" id="table_information">
                         <thead>
                             <tr>
                             	 <th> ลำดับ </th>
                             	 <th> ชื่อข้อกำหนด/ข่าวสาร</th>
                                 <th> วันที่แจ้ง </th> 
                                 <th> สถานะ</th>
                                 <th> วันที่รับทราบ </th>
                                 <th> รายละเอียด</th>                                
                             </tr>
                         </thead>
                        
                     </table>
                     
                 </div>
             </div>
             <!-- END EXAMPLE TABLE PORTLET-->
         </div>
     </div>
     
     <div class="row hide">
     	<div class="col-md-6">
     		<form action="${context}/information_form.jsp" method="post">
     			<div class="form-group">
     				<input id="informationDetail" name="informationDetail" readonly="readonly">
     			</div>
     		</form>
     	</div>
     </div>
     
     <script src="assets/js/page_information_supplier.js" type="text/javascript"></script>   



</body>
</html>