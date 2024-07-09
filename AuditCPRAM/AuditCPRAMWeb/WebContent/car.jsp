<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="th.co.gosoft.audit.cpram.model.UserModel"%>
<%@page import="th.co.gosoft.audit.cpram.model.UserGroupModel"%>
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
     <script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.th.min.js" type="text/javascript"></script>
   
</head>
<body>


    <%
		//Check UserGroupId Permission
			UserModel adminModel = (UserModel)request.getSession().getAttribute("sessionAuthen");
			UserGroupModel groupModel = (UserGroupModel)adminModel.getUserGroupId();
			if(Integer.parseInt(groupModel.getUserGroupId()) > 2 && Integer.parseInt(groupModel.getUserGroupId()) < 5){
				out.println("<script>window.location.replace('/auditsupplier/home.jsp')</script>");
			}
	%>



     <!-- BEGIN PAGE HEADER-->
     <div class="page-bar">
         <ul class="page-breadcrumb">
             <li>
                 <i class="icon-home"></i>
                 <a href="home.jsp">หน้าหลัก</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span>ใบคาร์</span>
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
	                    <i class="fa fa-ticket"></i>
	                    <span class="caption-subject bold uppercase"> ใบคาร์ </span>	                    
	                </div>  
                    <div class="actions">
                    	<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
                    </div>      
                </div>
                
                 <div class="portlet-body">
                     <!-- <table class="table table-striped table-bordered table-hover order-column table_managed">
                         <thead>
                             <tr>
                                 <th> Checklist </th>
                                 <th> กลุ่มคำถาม </th>
                                 <th> คำถาม </th>
                                 <th> คะแนน </th>
                                 <th> สาเหตุ </th>
                                 <th style="width:10%"> สถานะ </th>
                                 <th style="width:10%">  </th>
                             </tr>
                         </thead>
                         <tbody>
                             <tr class="odd gradeX">
                                 <td> Packaging </td>
                                 <td> นโยบายคุณภาพและความปลอดภัย </td>
                                 <td> ระบบบริหารคุณภาพความปลอดภัยอาหาร </td>
                                 <td> Critical </td>
                                 <td> - </td>
                                 <td>
                                     <span class="label label-sm label-success"> Active </span>
                                 </td>
                                 <td>
                                     <div class="btn-group">
                                         <button class="btn btn-xs green dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false"> Actions
                                             <i class="fa fa-angle-down"></i>
                                         </button>
                                         <ul class="dropdown-menu pull-left" role="menu">
                                             <li>
                                                 <a href="car_form.jsp">
                                                     <i class="icon-pencil"></i> Edit </a>
                                             </li>
                                         </ul>
                                     </div>
                                 </td>
                             </tr>
                         </tbody>
                     </table> -->
                     
                     <div class="form-group">
                     	<div class="row">
                     		<div class="col-md-3">
                     			<label> หมายเลขใบ CAR  </label>
    							<input type="text" class="form-control input-sm" id="car_no_search">
                     		</div>
                     		<div class="col-md-3">
                     			<label> ชื่อเช็คลิสต์  </label>
    							<input type="text" class="form-control input-sm" id="checklist_plan_title_search">
                     		</div>
                     		<div class="col-md-3">
                     			<label> ซัพพลายเออร์ </label>
    							<input type="text" class="form-control input-sm" id="supplier_company_search">
                     		</div>
                     		<div class="col-md-3">
                     			<label> วันที่เข้าตรวจ  </label>
                     			<div class="input-group date datepicker-form" data-provide="datepicker">
    								<input type="text" class="form-control input-sm" readonly="readonly" id="plan_date_textbox">
    								<div class="input-group-addon">
        								<span class="glyphicon glyphicon-calendar"></span>
    								</div>
								</div>
                     		</div>
                     		
                     		
                     		
                     	</div>
                     </div>
                     
                     <div class="form-group">
                     	<div class="row">
                     		<div class="col-md-3">
                     			<label> เลขที่แผนการเข้าตรวจ  </label>
    							<input type="text" class="form-control input-sm" id="checklist_plan_no_search">
                     		</div>
                     		<div class="col-md-3">
                     			<label> กำหนดวันรับทราบ  </label>
                     			<div class="input-group date datepicker-form" data-provide="datepicker">
    								<input type="text" class="form-control input-sm" readonly="readonly" id="due_date_textbox">
    								<div class="input-group-addon">
        								<span class="glyphicon glyphicon-calendar"></span>
    								</div>
								</div>
                     		</div>
                     		<div class="col-md-3">
                     			<label>  สถานะ  </label>
    							<select class="form-control form-control-sm select-sm" id="car_status">
  									<option value="">--- ทั้งหมด ---</option>
  									<!-- <option value="1">  ได้รับ   </option>
  									<option value="3"> ผ่าน </option>
  									<option value="4"> ไม่ผ่าน </option>
  									<option value="5"> ยกเลิก </option> -->
								</select>
                     		</div>
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
                     
                     <table class="table table-striped table-bordered table-hover order-column" id="table_car">
                     	<thead>
                     		<tr>
                     			<th> ลำดับ </th>
                     			<th>วันที่เข้าตรวจ</th>
                     			<th>หมายเลขใบ CAR</th>
                     			<th><center>เลขที่<br>แผนการเข้าตรวจ</center></th>
                     			<th>ชื่อเช็คลิสต์</th>
                     			<th>ซัพพลายเออร์</th>
                     			<th><center>กำหนด<br>วันรับทราบ</center></th>
                     			<th>สถานะ</th>
                     			<th>เลือก</th>
                     		</tr>
                     	</thead>
                     </table>        
                 </div>
             </div>
             <!-- END EXAMPLE TABLE PORTLET-->
         </div>
     </div>
     
	<form action="car_form.jsp" method="post">
		<input class="form-control hide" id="pass_param_car" name="pass_param_car">
	</form>
	<script type="text/javascript" src="assets/js/page_car.js"></script>

</body>
</html>