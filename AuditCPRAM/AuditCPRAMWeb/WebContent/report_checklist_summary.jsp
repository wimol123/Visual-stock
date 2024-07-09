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
                 <a href="dashboard.jsp">Home</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span>รายงานสรุปภาพรวมการตรวจ</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
                    
     
     <div class="row">
         <div class="col-md-12">
             <!-- BEGIN EXAMPLE TABLE PORTLET-->
             <div class="portlet light ">
				<div class="portlet-title">
                	<div class="caption font-red-sunglo">
                	
                		<div class="row">
							<div class="col-md-4">  
			             		<input class="form-control" placeholder="วันที่เริ่มต้น" type="text"> 
	                		</div>
							<div class="col-md-4">  
			             		<input class="form-control" placeholder="วันที่สิ้นสุด" type="text"> 
	                		</div>
                		</div>
                		<div class="row" style="padding-top: 10px;">
							<div class="col-md-10">  
			             		<textarea rows="3" class="form-control" placeholder="Supplier" ></textarea>
	                		</div>
							<div class="col-md-2">  
                		 		<button type="submit" class="btn green">ค้นหา</button>
	                		</div>
                		</div>
                		
                    </div>
                    <div class="actions">
                          <a class="btn default" href="javascript:;">
                          	<i class="fa fa-file-pdf-o"></i>
                              Export to CSV
                          </a>
                     </div>        
                </div>
                
                 <div class="portlet-body">
                     <table class="table table-striped table-bordered table-hover order-column table_managed">
                         <thead>
                             <tr>
                                 <th> วันที่ตรวจ </th>
                                 <th> การตรวจทั้งหมด </th>
                                 <th> Critical </th>
                                 <th> Major </th>
                                 <th> Minor </th>
                                 <th> Observe </th>
                                 <th> N/A </th>
                             </tr>
                         </thead>
                         <tbody>
                             <tr class="odd gradeX">
                                 <td>10/09/2018</td>
                                 <td>35</td>
                                 <td>1</td>
                                 <td>2</td>
                                 <td>1</td>
                                 <td>1</td>
                                 <td>30</td>
                             </tr>
                             <tr class="odd gradeX">
                                 <td>11/09/2018</td>
                                 <td>10</td>
                                 <td>1</td>
                                 <td>0</td>
                                 <td>0</td>
                                 <td>0</td>
                                 <td>40</td>
                             </tr>
                         </tbody>
                     </table>
                 </div>
             </div>
             <!-- END EX
             <!-- END EXAMPLE TABLE PORTLET-->
         </div>
     </div>
     



</body>
</html>