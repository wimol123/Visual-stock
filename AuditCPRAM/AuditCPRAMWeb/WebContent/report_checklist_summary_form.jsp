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
                 <a href="report_checklist_supplier.jsp">รายงานสรุปการตรวจราย Supplier</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span>รายละเอียด</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
            
     <div class="row">
		<div class="col-md-12">
		
		
     		<div class="portlet light ">
				<div class="portlet-title">
                	<div class="caption font-red-sunglo">
                    	<i class="icon-note  font-red-sunglo"></i>
                        <span class="caption-subject bold"> Checklist</span>
                    </div>   
                </div>
                <div class="portlet-body form">
                   	
                     <table class="table table-striped table-bordered table-hover order-column">
                         <thead>
                             <tr>
                                 <th> หัวข้อ </th>
                                 <th> เข้าตรวจโดย </th>
                                 <th> วันที่ตรวจ </th>
                                 <th> Action </th>
                             </tr>
                         </thead>
                         <tbody>
                             <tr class="odd gradeX">
                                 <td> Ingredient </td>
                                 <td> Auditor A </td>
                                 <td> 01/08/2018 </td>
                                 <td>
									<a data-toggle="modal" href="#dialog_add_question" class="btn btn-xs default"><i class="icon-question "></i> ดูคำถาม</a>
                                 </td>
                             </tr>
                             <tr class="odd gradeX">
                                 <td> Seafood </td>
                                 <td> Auditor A </td>
                                 <td> 01/09/2018 </td>
                                 <td>
									<a data-toggle="modal" href="#dialog_add_question" class="btn btn-xs default"><i class="icon-question "></i> ดูคำถาม</a>
                                 </td>
                             </tr>
                         </tbody>
                     </table>
                     
                     
                </div>
            </div>
            
            

     	</div>
     </div>
     
     
     
	<%-- Dialog --%>
	<div id="dialog_add_question" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
		    <div class="row">
				<div class="col-md-6">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title">คำถาม</h4>
				</div>
			</div>
	      </div>
	      <div class="modal-body">
	        <form role="form">
	            <div class="form-body">
	                 
	            
                     <table class="table table-striped table-bordered table-hover order-column">
                         <thead>
                             <tr>
                                 <th> คำถาม </th>
                                 <th> Grade </th>
                             </tr>
                         </thead>
                         <tbody>
                             <tr class="odd gradeX">
                                 <td> ระบบบริหารคุณภาพและความปลอดภัยอาหาร  </td>
                                 <td>
									Critical
                                 </td>
                             </tr>
                             <tr class="odd gradeX">
                                 <td> ความมุ่งมั่นของฝ่ ายบริหาร  </td>
                                 <td>
									Observe
                                 </td>
                             </tr>
                         </tbody>
                     </table>
	            
	            </div>
	            <div class="modal-footer">
                    <button type="button" class="btn dark btn-outline" data-dismiss="modal">ปิด</button>
                </div>
	        </form>                            
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
	
	
</body>
</html>