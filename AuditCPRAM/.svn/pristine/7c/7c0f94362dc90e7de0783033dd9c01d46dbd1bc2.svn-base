<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
     <link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
     <link href="assets/css/fix_datatable.css" rel="stylesheet" type="text/css" />
    <link href="assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css" />
    <link href="${context }/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css">
     <link href="${context }/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.css" rel="stylesheet" type="text/css">
     <link href="${context }/assets/global/plugins/bootstrap-clockpicker/css/bootstrap-clockpicker.css" rel="stylesheet" type="text/css">
     <link href="${context }/assets/global/plugins/bootstrap-clockpicker/css/jquery-clockpicker.css" rel="stylesheet" type="text/css">
     <link href="${context }/assets/global/plugins/bootstrap-tagsinput/css/bootstrap-tagsinput.css" rel="stylesheet" type="text/css">
     <link href="${context }/assets/global/plugins/bootstrap-tagsinput/css/bootstrap-tagsinput-typeahead.css" rel="stylesheet" type="text/css">
    
    <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
    <script src="assets/global/plugins/moment.min.js" type="text/javascript"></script>
    <script src="assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
    <script src="assets/global/plugins/fullcalendar/lang/th.js" type="text/javascript"></script>
    <script src="assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>    
    <script src="assets/apps/scripts/calendar.js" type="text/javascript"></script>
    <script src="${context}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.th.min.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-clockpicker/js/bootstrap-clockpicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-clockpicker/js/jquery-clockpicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js" type="text/javascript"></script>
 	
 	<style type="text/css">
   		.clockpicker-popover {
			z-index: 999999 !important;
		}
				
		.bootstrap-tagsinput{
  			width: 100% !important;
  			
		}
	
		.bootstrap-tagsinput .tag{
   	 		font-size: 12px !important;
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
                 <a href="dashboard.jsp"> หน้าหลัก </a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <a href="appoint.jsp"> การนัดหมาย </a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span> ปฏิทินการนัดหมาย </span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->


	<div class="row">
	     <div class="col-md-12">
	         <div class="portlet light portlet-fit  calendar">
				<div class="portlet-title">
                	<div class="caption font-red-sunglo">
                         <!-- <a class="btn sbold green"  href="appoint.jsp"> สร้างการนัดหมายdata-toggle="modal"
                             <i class="fa fa-plus"></i>
                         </a> -->
                         <a class="btn sbold blue hide" href="appoint_doc.jsp"> สร้างการขอเอกสาร
                             <i class="fa fa-plus"></i>
                         </a>
                    </div>        
                    <div class="actions">
	                     <div class="btn-group">
	                         <a class="btn sbold default" href="appoint.jsp"> ดูรายการทั้งหมด
	                             <i class="icon-list"></i>
	                         </a>
	                     </div>
	                     
	                 </div> 
                </div>
	             <div class="portlet-title">
	                 <div class="caption">
	                     <i class=" icon-calendar font-green"></i>
	                     <span class="caption-subject font-green sbold uppercase"> ปฏิทินการนัดหมาย </span>
	                 </div>
	             </div>
	             <div class="portlet-body">
	                 <div class="row">
	                     <div class="col-md-12 col-sm-12">
	                         <div id="calendar" class="has-toolbar"> </div>
	                     </div>
	                 </div>
	             </div>
	         </div>
	     </div>
	 </div>



	<%-- Dialog --%>
	<div id="dialog_detail" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
		    <div class="row">
				<div class="col-md-6">
		        	<!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
		        	<h4 class="modal-title">รายละเอียด</h4>
				</div>
			</div>
		           
	      </div>
	      <div class="modal-body">
	                     
                  
     			<div class="row">
				<div class="col-md-12">
				        <table class="table table-bordered table-striped">
				            <tbody>
				                <tr>
				                    <td style="width:25%"> ผู้ออกตรวจ </td>
				                    <td style="width:70%" id="row_auditor">
				                       
				                    </td>
				                </tr>
				                <tr>
				                    <td> นัดกับ </td>
				                    <td id="row_company">
				                        	
				                    </td>
				                </tr>
				                <tr>
				                    <td> วันที่นัด </td>
				                    <td id="row_appointDate">
				                       
				                    </td>
				                </tr>
				                <tr>
				                    <td> รายละเอียด </td>
				                    <td id="row_title">
				                        	
				                    </td>
				                </tr>
				                <tr>
				                    <td> สร้างการนัดหมายโดย </td>
				                    <td id="row_appointBy">
				                        	
				                    </td>
				                </tr>
				                
				            </tbody>
				        </table>
				        <input class="hide" type="text" id="event_id">
				        <a class="btn btn-sm green" href="javascript:void(0);" id="detailTask" > ดูรายละเอียด
                                                    <i class="fa fa-search"></i>
                                                </a>
				    </div>
				    </div>




 
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
	
	<%-- Dialog --%>

	<div id="dialog_add_appoint" class="modal fade" role="dialog" tabindex="-1" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
		    		<div class="row">
						<div class="col-md-12">
		        			<button type="button" class="close btn-lg" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        			<h4 class="modal-title"> 
		        			 	สร้างการนัดหมาย	
		        			</h4>
						</div>
					</div>
	      		</div>
	      		
	      		<div class="modal-body">
	      			<form role="form" id="user_form" >
                        
                        <div class="form-body">
				      		<div class="form-group">
			                	<label>นัดโดย</label>
			                   	<div class="alert alert-info">
			                    	<strong class="appointName_Label">Admin</strong>
			                    </div>                              
			                </div>
			                <div class="form-group hide">
			                	<label> Appoint Id  </label>
			                	<input class="form-control" placeholder="AppointID" id="appointId_Dialog" type="text"   >
			                </div>
			                
				      		<div class="form-group">
			                	<label>Auditor</label><label class="require_Asterisk"> &#x2A;</label>
			                    <div class="input-group">	         		
			                    	<input type="text" class="form-control input-large"  id="auditName_Dialog"  placeholder= "กรุณาเลือกผู้ออกตรวจ" >
                                     
                                     <span class="input-group-btn">
                                         <button class="btn yellow" type="button" data-toggle="modal" href="#dialog_search_auditor">ค้นหา</button>
                                     </span>             
                                 </div>                                                    
			                </div>
			                
			                <div class="form-group hide">
			                	<label> Auditor ID  </label>
			                	<input class="form-control input-large" id="AuditorId_Dialog" type="text">
			                </div>
			                
				      		<div class="form-group">
			                	<label>นัดกับ</label><label class="require_Asterisk"> &#x2A;</label>
			                    <div class="input-group">
                                     <input class="form-control" placeholder="กรุณาเลือกซัพพลายเออร์"  type="text" id="supplierCompany_Dialog" readonly="readonly">
                                     <span class="input-group-btn">
                                         <button class="btn yellow" type="button" data-toggle="modal" href="#dialog_search_supplier">ค้นหา</button>
                                     </span>
                                 </div>
                                                    
			                </div>
			                
			                <div class="form-group hide">
			                	<label> Supplier ID  </label>
			                	<input class="form-control" placeholder="SupplierID"  type="text" id="supplierID_Dialog">
			                </div>
			                
				      		<div class="form-group">
				      			<div class="row">
				      				<div class="col-md-6">
				      					<label>วันนัด</label><label class="require_Asterisk"> &#x2A;</label>
			                    		<div class="input-group date datepicker-dialog" data-provide="datepicker">
    										<input type="text" class="form-control" readonly="readonly" id="appoint_date_Dialog">
    										<div class="input-group-addon">
        										<span class="glyphicon glyphicon-calendar"></span>
    										</div>
										</div>
				      				</div>
				      				<div class="col-md-6">
				      					<label>เวลานัด</label><label class="require_Asterisk"> &#x2A;</label>
			                    		<div class="input-group clockpicker-dialog" >
    										<input type="text" class="form-control" readonly="readonly" id="appoint_time_Dialog" placeholder="Now">
    										<span class="input-group-addon">
        										<span class="glyphicon glyphicon-time"></span>
    										</span>
										</div>
				      				</div>
			                	</div>
								
			                </div>
			                
				      		<div class="form-group">
			                	<label>หัวข้อ</label><label class="require_Asterisk"> &#x2A;</label>
			                    <input class="form-control" placeholder="หัวข้อ" type="text" id="title_Dialog"> 
			                </div>
			                
				      		<div class="form-group">
			                	<label>รายละเอียด</label>
			                    <textarea rows="3" class="form-control" id="Detail_Dialog"></textarea>
			                </div>
			         
                        </div>
                        
                        <div class="form-actions">
                            <button type="button" id="btn_submit_appoint_dialog" class="btn green"> บันทึก </button>
                        </div>
                        
                    </form>
	      		</div>
			</div>
		</div>
	</div> 

	<%-- Dialog --%>
	
	<%-- Dialog --%>
	<div id="dialog_search_supplier" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        
		    <div class="row">
				<div class="col-md-6">
		        	
		        	<h4 class="modal-title">เลือก Supplier</h4>
				</div>
				<div class="col-md-6">
					<div style="float:right">
		            	<a  class="btn sbold"  href="admin_supplier.jsp"> เพิ่ม Supplier ใหม่
			            	<i class="fa fa-plus"></i>
			            </a>  
		            </div>
				</div>
			</div>
		           
	      </div>
	      <div class="modal-body">
	      
                     <table class="table table-striped table-bordered table-hover order-column " id="table_search_supplier">
                         <thead>
                             <tr>
                                 <th> ชื่อ </th>
                                 <th>Supplier Id</th>
                                 <th>Status</th>
                                 <th> อีเมล์</th>
                                 <th>  </th>
                             </tr>
                         </thead>
                         <!-- <tbody>
                             <tr class="odd gradeX">
                                 <td> Supplier A </td>
                                 <td>
                                     SupplierA@email.com
                                 </td>
                                 <td>
                                     <button type="button" class="btn btn-default"><i class="fa fa-check"></i> เลือก</button>
                                 </td>
                             </tr>
                             <tr class="odd gradeX">
                                 <td> Supplier B </td>
                                 <td>
                                     SupplierB@email.com
                                 </td>
                                 <td>
                                     <button type="button" class="btn btn-default"><i class="fa fa-check"></i> เลือก</button>
                                 </td>
                             </tr>
                         </tbody> -->
                     </table>
                     
                     
            
            
                                               
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
	
	                              
			        
	<%-- Dialog --%>
	<div id="dialog_search_auditor" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        
		    <div class="row">
				<div class="col-md-6">
		        	<h4 class="modal-title">เลือก Auditor</h4>
				</div>
				<div class="col-md-6">
					<div style="float:right">
		            	<a  class="btn sbold"  href="admin_auditor_form.jsp"> เพิ่ม Auditor ใหม่
			            	<i class="fa fa-plus"></i>
			            </a>  
		            </div>
				</div>
			</div>
		           
	      </div>
	      <div class="modal-body">
	      
                     <table class="table table-striped table-bordered table-hover order-column table_managed" id="table_search_audit">
                         <thead>
                             <tr>
                                 <th> ชื่อ </th>
                                 <th>Audit Id</th>
                                 <th> อีเมล์</th>
                                 <th>userGroupId</th>
                                 <th>  </th>
                             </tr>
                             
                         </thead>
                         <!-- <tbody>
                             <tr class="odd gradeX">
                                 <td> Auditor A </td>
                                 <td>
                                     AuditorA@email.com
                                 </td>
                                 <td>
                                     <button type="button" class="btn btn-default"><i class="fa fa-check"></i> เลือก</button>
                                 </td>
                             </tr>
                             <tr class="odd gradeX">
                                 <td> Auditor B </td>
                                 <td>
                                     AuditorB@email.com
                                 </td>
                                 <td>
                                     <button type="button" class="btn btn-default"><i class="fa fa-check"></i> เลือก</button>
                                 </td>
                             </tr>
                         </tbody> -->
                     </table>
                     
                     
            
            
                                               
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
	
	
	<form class='hide' action="${context}/appoint_form.jsp" method="post" id="passDataToForm">
     	
     	<input type="text" id="detailAppoint" name="detailAppoint">
     </form>
     
     <form class='hide' action="${context}/supplier_appoint_form.jsp" method="post" id="passDataToFormSupplier">     	
     	<input type="text" id="detailAppointSupplier" name="detailAppointSupplier">
     </form>
	
	<script src="${context}/assets/js/page_appointcalendar.js" type="text/javascript"></script>
	


</body>
</html>