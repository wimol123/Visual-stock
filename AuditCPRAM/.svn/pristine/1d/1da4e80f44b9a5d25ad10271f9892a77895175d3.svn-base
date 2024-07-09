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
     <link href="${context}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css">
     <link href="${context}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.css" rel="stylesheet" type="text/css">
     <link href="${context}/assets/global/plugins/bootstrap-clockpicker/css/bootstrap-clockpicker.css" rel="stylesheet" type="text/css">
     <link href="${context}/assets/global/plugins/bootstrap-clockpicker/css/jquery-clockpicker.css" rel="stylesheet" type="text/css">
     <link href="${context}/assets/global/plugins/bootstrap-tagsinput/css/bootstrap-tagsinput.css" rel="stylesheet" type="text/css">
     
     <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
     <script src="assets/pages/scripts/table-datatables-managed.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.th.min.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-clockpicker/js/bootstrap-clockpicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-clockpicker/js/jquery-clockpicker.js" type="text/javascript"></script>
     
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
                 <a href="appoint.jsp">การนัดหมาย</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span>รายละเอียดการนัดหมาย</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
            
     <div class="row hide">
     	<input class="form-control hide" type="text" id="appointDetail" value="${param.detailAppointSupplier}">
     </div>
     <div class="row">
		<div class="col-md-7">
     		<div class="portlet light " id="portlet-detail-appoint">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-calendar"></i>
                        <span class="caption-subject bold uppercase">รายละเอียด</span>
                    </div>
                </div>
                <div class="portlet-body form">
                
                	                
                   <table class="table table-bordered table-striped">
		            <tbody>
		            	<tr>
		                    <td style="width:20%"> สถานะ </td>
		                    <td style="width:80%">
		                    	<div >
		                    		<span class="label" style="font-size:12px;text-align: center;" id="status_text"></span>
                					<input class="form-control hide" id="status_id">
		                    	</div>
		                    	
		                    </td>
		                </tr>
		                <tr>
		                    <td style="width:20%"> เข้าตรวจโดย </td>
		                    <td style="width:80%" id="auditor_row"></td>
		                </tr>
		                <tr>
		                    <td style="width:20%"> ผู้ติดตาม </td>
		                    <td style="width:80%" id="entourage_row"></td>
		                </tr>
		                <tr>
		                    <td style="width:20%"> วัน/เวลานัดหมาย </td>
		                    <td style="width:80%" id="appointDateTime_row"></td>
		                </tr>
		                <tr>
		                    <td style="width:20%"> สถานที่ </td>
		                    <td style="width:80%" id="location_row"></td>
		                </tr>		               
		                <tr>
		                    <td style="width:20%"> หัวข้อ</td>
		                    <td style="width:80%" id="title_row"></td>
		                </tr>
		                <tr>
		                    <td style="width:20%">รายละเอียด</td>
		                    <td style="width:80%" id="detail"></td>
		                </tr>
		                
		            </tbody>
		        	</table>
		        	
     				<input class="form-control hide" type="text" id="appointDetailObject" >
		        	
		        	<div class="form-actions">
                    	<button type="button" class="btn green-jungle" id="btn_accept_appoint" style="display: none;">ยืนยันการนัดหมาย</button>
                    	<!-- <button type="button" class="btn red-mint" id="btn_cancel_appoint" style="display: none;">ยกเลิกการนัดหมาย</button> -->
                    </div>
		        	
                </div>
            </div>  
     	</div>
		<div class="col-md-5">
     		
     		<div class="portlet light" id="portlet-appoint-change">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-calendar"></i>
                        <span class="caption-subject bold uppercase"> เลือนนัด</span>
                    </div>            
                </div>
                <div class="portlet-body form">
     		    	<form id="form-appoint-change">
				      	<div class="form-group">
				      		<div class="row">
				      			<div class="col-md-10">
				      				<label>ช่วงวันที่</label><span class="required"> * </span>				      							      				
			                		<div class="input-group date datepicker-change-from">			
			                			<input type="text" class="form-control" readonly="readonly" id="appoint_date_change_from" name="appoint_date_change_from" />
			                			
    									<span class="input-group-addon">ถึง : </span>
    									
   										<input type="text" class="form-control" readonly="readonly" id="appoint_date_change_to" />     
   										        			
    									<!-- <input type="text" class="form-control input-sm" readonly="readonly" id="appoint_date_change">
    									<div class="input-group-addon">
        									<span class="glyphicon glyphicon-calendar"></span>
    									</div> -->
									</div>
				      			</div>				      		
				      			
				      		</div>
				      		<div class="row">
				      			<div class="col-md-6">
				      				<label>เวลา</label><span class="required"> * </span>
			                		<div class="input-group clockpicker-change" >
    									<input type="text" class="form-control" readonly="readonly" id="appoint_time_change" name="appoint_time_change" placeholder="Now">
    									<span class="input-group-addon">
        									<span class="glyphicon glyphicon-time"></span>
    									</span>
									</div>
				      			</div>
				      		</div>
			            	
			            </div>
			      		<div class="form-group">
		                	<label>รายละเอียดการเลื่อนนัด</label><span class="required"> * </span>
		                    <textarea rows="3" class="form-control" id="appoint_detail_change" name="appoint_detail_change"></textarea>
		                </div>
                        <div class="form-actions">
                            <button type="button" class="btn green" id="appoint_btn_change">บันทึก</button>
                        </div>
                   </form>
                </div>
            </div>
     		
     		
     		<div class="portlet light " id="portlet-history">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-bubble"></i>
                        <span class="caption-subject bold uppercase"> ประวัติ</span>
                    </div>            
                </div>
                <div class="portlet-body form">
                   	
			     <div class="row">
					
					<div class="col-md-12">          
	                     <ul class="chats" style="padding-top:15px;">	                      
	                     </ul>                     
                	</div>
                	
					<div class="col-md-12 type_general">  
                	</div>   
            	</div>
                </div>
            </div>
            
            
     	</div>
     </div>
     
     
     
	<%-- Dialog --%>
	<div id="dialog_search_supplier" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        
		    <div class="row">
				<div class="col-md-6">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
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
	      
                     <table class="table table-striped table-bordered table-hover order-column table_managed">
                         <thead>
                             <tr>
                                 <th> ชื่อ </th>
                                 <th> อีเมล์</th>
                                 <th>  </th>
                             </tr>
                         </thead>
                         <tbody>
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
                         </tbody>
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
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
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
	      
                     <table class="table table-striped table-bordered table-hover order-column table_managed">
                         <thead>
                             <tr>
                                 <th> ชื่อ </th>
                                 <th> อีเมล์</th>
                                 <th>  </th>
                             </tr>
                         </thead>
                         <tbody>
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
                         </tbody>
                     </table>
                     
                     
            
            
                                               
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
	
	 <script type="text/javascript" src="${context}/assets/js/page_supplierappointform.js"></script>
	       
     <script  type="text/javascript">
	   	$('#radio_group_type input').on('change', function() {
	   		var type = $('input[name=radioType]:checked').val();

			$(".type_appoint").hide();
			$(".type_car").hide();
			$(".type_general").hide();

	   		if (type==1){
				$(".type_general").show();
			}
	   		if (type==2){
				$(".type_appoint").show();
			}
	   		if (type==3){
				$(".type_car").show();
			}
	   		
	   		
		});
		$(".type_appoint").hide();
		$(".type_car").hide();
   </script>
   
   
</body>
</html>