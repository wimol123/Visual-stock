<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
     <script src="assets/pages/scripts/table-datatables-managed.js" type="text/javascript"></script>
   
</head>
<body>

     <!-- BEGIN PAGE HEADER-->
     <div class="page-bar">
         <ul class="page-breadcrumb">
             <li>
                 <i class="icon-home"></i>
                 <a href="home.jsp">Home</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span>Appoint</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
                    
     
     <div class="row">
         <div class="col-md-12">
             <!-- BEGIN EXAMPLE TABLE PORTLET-->
             <div class="portlet light ">
				<div class="portlet-title">   
                    <div class="actions">
	                     <div class="btn-group">
	                         <a class="btn sbold default" href="supplier_appoint_calendar.jsp"> ดูการนัดหมายแบบปฏิทิน
	                             <i class="icon-calendar"></i>
	                         </a>
	                     </div>
	                 </div> 
                </div>
                 <div class="portlet-body">
                     <table class="table table-striped table-bordered table-hover order-column table_managed">
                         <thead>
                             <tr>
                                 <th> สร้างโดย </th>
                                 <th> Supplier </th>
                                 <th> วันที่นัด</th>
                                 <th> ประเภท </th>
                                 <th> หัวข้อ </th>
                                 <th> สถานะ </th>
                                 <th> Action </th>
                             </tr>
                         </thead>
                         <tbody>
                             <tr class="odd gradeX">
                                 <td> Admin </td>
                                 <td> Supplier A </td>
                                 <td> 03/08/2018 </td>
                                 <td> นัดหมาย </td>
                                 <td> แจ้งเข้าตรวจตามรอบ </td>
                                 <td>
                                     <span class="label label-sm label-default"> New </span>
                                 </td>
                                 <td>
                                     <div class="btn-group">
                                         <button class="btn btn-xs green dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false"> Actions
                                             <i class="fa fa-angle-down"></i>
                                         </button>
                                         <ul class="dropdown-menu pull-left" role="menu">
                                             <li>
                                                 <a href="supplier_appoint_form.jsp">
                                                     <i class="icon-pencil"></i> Edit </a>
                                             </li>
                                         </ul>
                                     </div>
                                 </td>
                             </tr>
                             <tr class="odd gradeX">
                                 <td> Admin </td>
                                 <td> Supplier C </td>
                                 <td> 01/08/2018 </td>
                                 <td> ทั่วไป </td>
                                 <td> ขอเอกสารการจดบริษัทเพิ่มเติม </td>
                                 <td>
                                     <span class="label label-sm label-warning"> Reject </span>
                                 </td>
                                 <td>
                                     <div class="btn-group">
                                         <button class="btn btn-xs green dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false"> Actions
                                             <i class="fa fa-angle-down"></i>
                                         </button>
                                         <ul class="dropdown-menu pull-left" role="menu">
                                             <li>
                                                 <a href="supplier_appoint_form.jsp">
                                                     <i class="icon-pencil"></i> Edit </a>
                                             </li>
                                         </ul>
                                     </div>
                                 </td>
                             </tr>
                         </tbody>
                     </table>
                 </div>
             </div>
             <!-- END EXAMPLE TABLE PORTLET-->
         </div>
     </div>
     

</body>
</html>