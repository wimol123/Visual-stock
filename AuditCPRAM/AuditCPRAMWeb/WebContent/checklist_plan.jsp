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
     <link href="${context}/assets/global/plugins/bootstrap-clockpicker/css/bootstrap-clockpicker.css" rel="stylesheet" type="text/css">
     <link href="${context}/assets/global/plugins/bootstrap-clockpicker/css/jquery-clockpicker.css" rel="stylesheet" type="text/css">
     <link href="${context}/assets/global/plugins/bootstrap-tagsinput/css/bootstrap-tagsinput.css" rel="stylesheet" type="text/css">
     <link href="${context}/assets/global/plugins/bootstrap-tagsinput/css/bootstrap-tagsinput-typeahead.css" rel="stylesheet" type="text/css">
     
     <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
      <script src="${context}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.th.min.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-clockpicker/js/bootstrap-clockpicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-clockpicker/js/jquery-clockpicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js" type="text/javascript"></script>
 	 <script src="${context}/assets/global/plugins/jquery-bloodhound/js/bloodhound.js" type="text/javascript"></script>
   	 <script src="${context}/assets/global/plugins/jquery-typeahead/js/typeahead.jquery.js" type="text/javascript"></script>
		
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
   		
   		.tooltip.primary .tooltip-inner          { background-color:    #337ab7; }
		.tooltip.primary.top > .tooltip-arrow    { border-top-color:    #337ab7; }
		.tooltip.primary.right > .tooltip-arrow  { border-right-color:  #337ab7; }
		.tooltip.primary.bottom > .tooltip-arrow { border-bottom-color: #337ab7; }
		.tooltip.primary.left > .tooltip-arrow   { border-left-color:   #337ab7; }
		
		.tt-hint {
			opacity: .3 !important
		}
		.tt-menu {
			width: 100%;
			border: 1px solid #eee;
			border-top: none
		}

		.tt-suggestion {
			padding: 10px 5px;
			background: #fff;
			border-bottom: 1px solid #eee;
			cursor: pointer
		}
		.input-group .form-control:not(:first-child):not(:last-child){
			border-radius: 4px;
		}
	</style>
	
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
                 <span>แผนการประเมิน</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
     
     <div class="row">     
     	<div class="col-md-12">
     		<div class="portlet light ">
     			<div class="portlet-title">
     				<div class="caption font-green-sharp">
	                    <i class="icon-map"></i>
	                    <span class="caption-subject bold uppercase"> แผนการประเมิน </span>	                    
	                </div>             
        			<div class="actions">
						<a class="btn sbold green" data-toggle="modal" href="#dialog_add_checklistplan" style="display: none;"> เพิ่มแผนการประเมิน
                          	<i class="fa fa-plus"></i>
                       	</a>
						<!-- <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a> -->
					</div> 
     			</div>
     			
     			<div class="portlet-body">
     			
     				<div class="form-group">
                     	<div class="row">
                     		<div class="col-md-3">
                     			<label> เลขที่แผนการประเมิน </label>
    							<input type="text" class="form-control input-sm" id="checklist_plan_no_search">
                     		</div>
                     		<div class="col-md-3">
                     			<label> หัวข้อเช็คลิสต์  </label>
    							<input type="text" class="form-control input-sm" id="checklist_plan_title_search">
                     		</div>
                     		<div class="col-md-3">
                     			<label> ชื่อบริษัท </label>
    							<input type="text" class="form-control input-sm" id="supplier_company_search">
                     		</div>
                     		<div class="col-md-3">
                     			<label> วันที่เวลานัดหมาย  </label>
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
                     			<label>  สถานะ  </label>
    							<select class="form-control form-control-sm select-sm" id="checklist_plan_status">
  									<option value="">--- ทั้งหมด ---</option>
  									<option value="1">  รอเข้าตรวจ   </option>
  									<option value="2"> รอยืนยันผลการตรวจ </option>
  									<option value="3">  ยืนยันผลการตรวจ   </option>
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
     			
     			
                     <table class="table table-striped table-bordered table-hover order-column" id="table_ChecklistPlan">
                         <thead>
                             <tr>
                             	 <th> ลำดับ </th>
                             	 <th> เลขที่แผนการประเมิน </th>
                             	 <th> หัวข้อเช็คลิสต์ </th>
                                 <th> ชื่อบริษัท </th>
                                 <th> ประเภทสินค้า </th>
                                 <th> วันเวลานัดหมาย </th>
                                 <th> สถานะ </th>
                                 <th> เลือก </th>
                             </tr>
                         </thead>
                         
                     </table>
                 </div>
     			
     		</div>
     	</div>     
     </div>
     
     <div class="row hide">
     	<div class="col-md-6">
     		<form action="${context}/checklist_plan_form.jsp" method="post">
     			<div class="form-group">
     				<input id="checklistPlanDetail" name="checklistPlanDetail" readonly="readonly">
     			</div>
     		</form>
     	</div>
     </div>
     
       <%-- Dialog --%>

	<div id="dialog_add_checklistplan" class="modal fade" role="dialog" tabindex="-1" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
		    		<div class="row">
						<div class="col-md-12">
		        			<button type="button" class="close btn-lg" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        			<h4 class="modal-title"> 
		        			 	สร้างแผนการประเมิน
		        			</h4>
						</div>
					</div>
	      		</div>
	      		
	      		<div class="modal-body">
	      			<form role="form" id="checklistplan_form" >
                        
                        <div class="form-body">
				      		<div class="form-group">
			                	<label>สร้างแผนโดย</label><span class="required"> * </span>
			                   	<div class="alert alert-info">
			                    	<strong class="userCreateChecklistPlan_Label"></strong>
			                    </div>                              
			                </div>
			                <div class="form-group hide">
			                	<label> Appoint Id  </label><span class="required"> * </span>
			                	<input class="form-control" id="userCreateChecklistPlan_ID" name="userCreateChecklistPlan_ID" type="text"   >
			                </div>
			                
				      		<div class="form-group">
			                	<label>Auditor</label><span class="required"> * </span>
			                    <div class="input-group">	         		
			                    	<input type="text" class="form-control input-large"  id="auditName_Dialog" name="auditName_Dialog" placeholder= "กรุณาเลือกผู้ออกตรวจ" >
                                     <span class="input-group-btn">
                                         <button class="btn yellow" type="button" data-toggle="modal" href="#dialog_search_auditor">ค้นหา</button>
                                     </span>             
                                 </div>  
                                 <input id="dummy_auditName" name="dummy_auditName" type="text" style="visibility: hidden!Important; height:1px;width:1px;"/>                                                  
			                </div>
			                
			                <div class="form-group hide">
			                	<label> Auditor ID  </label><span class="required"> * </span>
			                	<input class="form-control input-large" id="AuditorId_Dialog" name="AuditorId_Dialog" type="text">
			                </div>
			                
			                <div class="form-group">
			                	<label>ผู้ติดตาม</label>
			                    <div class="input-group">	         		
			                    	<input type="text" class="form-control input-large"  id="entourge_Dialog"  placeholder= "กรุณาเลือกผู้ติดตาม (Option)" >
                                     <span class="input-group-btn">
                                         <button class="btn yellow" type="button" data-toggle="modal" href="#dialog_search_entourge">ค้นหา</button>
                                     </span>             
                                 </div>                                                    
			                </div>
			                
				      		<div class="form-group">
			                	<label>นัดกับ</label><span class="required"> * </span>			                	
			                    <div class="input-group">
                                     <input class="form-control" placeholder="กรุณาเลือกซัพพลายเออร์"  type="text" id="supplierCompany_Dialog" name="supplierCompany_Dialog" >
                                     <span class="input-group-btn">
                                         <button class="btn yellow" type="button" data-toggle="modal" href="#dialog_search_supplier">ค้นหา</button>
                                     </span>
                                 </div>    
                                 <input id="dummy_supplierCompany" name="dummy_supplierCompany" type="text" style="visibility: hidden!Important; height:1px;width:1px;"/>                                                
			                </div>
			                
			                <div class="form-group hide">
			                	<label> Supplier ID  </label><span class="required"> * </span>
			                	<input class="form-control" placeholder="SupplierID"  type="text" id="supplierID_Dialog" name="supplierID_Dialog">
			                </div>
			                
			                <div class="form-group form-group-location">
			                	<label>สถานที่ผลิตของซัพพลายเออร์</label><span class="required"> * </span>			                	
			                    <div class="input-group">
                                     <input class="form-control" placeholder="กรุณาเลือกสถานที่ผลิตซัพพลายเออร์"  type="text" id="supplierLocation_Dialog" name="supplierLocation_Dialog" >
                                     <span class="input-group-btn">
                                         <button class="btn yellow" type="button" data-toggle="modal" href="#dialog_search_location">ค้นหา</button>
                                     </span>
                                 </div>    
                                 <input id="dummy_Location" name="dummy_Location" type="text" style="visibility: hidden!Important; height:1px;width:1px;"/>                                                
			                </div>
			                
			                <div class="form-group hide">
			                	<label> Location ID  </label><span class="required"> * </span>
			                	<input class="form-control" placeholder="LocationID"  type="text" id="LocationID_dialog">
			                </div>
				      		
				      		<div class="row">				      			
				      			<div class="col-md-6">
				      				<div class="form-group">
				      					<label>วันนัด</label><span class="required"> * </span>
			                    		<div class="input-group date datepicker-dialog" data-provide="datepicker">
    										<input type="text" class="form-control" readonly="readonly" id="appoint_date" name="appoint_date">
    										<div class="input-group-addon">
        										<span class="glyphicon glyphicon-calendar"></span>
    										</div>
										</div>
				      				</div>
				      			</div>
				      			
				      			<div class="col-md-6">
				      				<div class="form-group">
				      					<label>เวลานัด</label><span class="required"> * </span>
			                    		<div class="input-group clockpicker-dialog" >
    										<input type="text" class="form-control" readonly="readonly" id="appoint_time" name="appoint_time" placeholder="Now">
    										<span class="input-group-addon">
        										<span class="glyphicon glyphicon-time"></span>
    										</span>
										</div>
				      				</div>
			                	</div>								
			                </div>
			               
			         
                        </div>
                        
                        <div class="form-actions">
                            <button type="button" id="btn_submit_checklistplan" class="btn green"> บันทึก </button>
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
					<!-- <div style="float:right">
		            	<a  class="btn sbold"  href="admin_supplier.jsp"> เพิ่ม Supplier ใหม่
			            	<i class="fa fa-plus"></i>
			            </a>  
		            </div> -->
				</div>
			</div>
		           
	      </div>
	      <div class="modal-body">
	      	<div class="form-group">
	      		<div class="row">
	      			<div class="col-md-4">
                     	<label> ชื่อ  </label>
    					<input type="text" class="form-control input-sm" id="textbox_search_supplier_company">
                    </div>
                    <div class="col-md-4">
                     	<label> หมายเลขซัพพลายเออร์  </label>
    					<input type="text" class="form-control input-sm" id="textbox_search_supplier_code">
                    </div>        
                    <div class="col-md-4">
                     	<label> Green Card </label>
    					<select class="form-control form-control-sm select-sm" id="select_search_greencard_status">
  							<option value="">--- ทั้งหมด ---</option>
  							<option value="Y"> ได้รับ </option>
  							<option value="N"> ไม่ได้รับ </option>
						</select>
                    </div>                  
	      		</div>
	      		<div class="row">
	      			<div class="col-md-4" style="margin-top: 25px;">
                    	<button type="button" class="btn btn-primary btn-sm"  id="btn_search_supplier_table">
     						<b>ค้นหา</b>
     					</button>
     					<button type="button" class="btn default btn-sm" id="btn_clear_search_supplier_table">
     						<b> ล้างข้อมูล </b>
     					</button>
                    </div>  
	      		</div>
	      	</div>
                     <table class="table table-striped table-bordered table-hover order-column " id="table_search_supplier">
                         <thead>
                             <tr>
                                 <th> ชื่อ </th>
                                 <th>หมายเลขซัพพลายเออร์</th>
                                 <th> Green Card</th>
                                 <th>  </th>
                             </tr>
                         </thead>
                        
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
		            	<!-- <a  class="btn sbold"  href="admin_auditor_form.jsp"> เพิ่ม Auditor ใหม่
			            	<i class="fa fa-plus"></i>
			            </a>  --> 
		            </div>
				</div>
			</div>
		           
	      </div>
	      <div class="modal-body">
	      	<div class="form-group">
	      		<div class="row">
	      			<div class="col-md-4">
                     	<label> ชื่อ  </label>
    					<input type="text" class="form-control input-sm" id="auditor_name_dialog">
                    </div>
                    <div class="col-md-4">
                     	<label> อีเมล์  </label>
    					<input type="text" class="form-control input-sm" id="auditor_email_dialog">
                    </div>
                    <div class="col-md-4" style="margin-top: 25px;">
                    	<button type="button" class="btn btn-primary btn-sm"  id="btn_search_auditor_table">
     						<b>ค้นหา</b>
     					</button>
     					<button type="button" class="btn default btn-sm" id="btn_clear_auditor_table">
     						<b> ล้างข้อมูล </b>
     					</button>
                    </div>  
	      		</div>
	      	</div>
	      	
	      	<div class="form-group">
	      		<div class="row">
	      			<div class="col-md-12">
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
                     	</table>
	      			</div>
	      		</div>
	      	</div>                                 
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
	
	
	<%-- Dialog --%>
	<div id="dialog_search_entourge" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        
		    <div class="row">
				<div class="col-md-6">
		        	<h4 class="modal-title">เลือกผู้ติดตาม</h4>
				</div>				
			</div>
		           
	      </div>
	      <div class="modal-body">
	      	<div class="form-group">
	      		<div class="row">
	      			<div class="col-md-4">
                     	<label> ชื่อ  </label>
    					<input type="text" class="form-control input-sm" id="entourge_name_dialog">
                    </div>
                    <div class="col-md-4">
                     	<label> อีเมล์  </label>
    					<input type="text" class="form-control input-sm" id="entourge_email_dialog">
                    </div>
                    <div class="col-md-4">
                     	<label> กลุ่มผู้ใช้งาน </label>
    					<input type="text" class="form-control input-sm" id="entourge_group_dialog">
                    </div>                    
	      		</div>
	      		<div class="row">
	      			<div class="col-md-4" style="margin-top: 25px;">
                    	<button type="button" class="btn btn-primary btn-sm"  id="btn_search_entourge_table">
     						<b>ค้นหา</b>
     					</button>
     					<button type="button" class="btn default btn-sm" id="btn_clear_entourge_table">
     						<b> ล้างข้อมูล </b>
     					</button>
                    </div>  
	      		</div>
	      	</div>
	      	
	      	<div class="form-group">
	      		<div class="row">
	      			<div class="col-md-12">
	      				<table class="table table-striped table-bordered table-hover order-column table_managed" id="table_search_entourge">
                         <thead>
                             <tr>
                                 <th> ชื่อ </th>
                                 <th> อีเมล์</th>
                                 <th> กลุ่มผู้ใช้งาน</th>
                                 <th>  </th>
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
	<%-- Dialog --%>
	
	
	<%-- Dialog --%>
	<div id="dialog_search_location" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        
		    <div class="row">
				<div class="col-md-6">
		        	<h4 class="modal-title">เลือกสถานที่ผลิตของซัพพลายเออร์</h4>
				</div>				
			</div>
		           
	      </div>
	      <div class="modal-body">
	      	<div class="form-group">
	      		<div class="row">
	      			<div class="col-md-4">
                     	<label> สถานที่ผลิต  </label>
    					<input type="text" class="form-control input-sm" id="location_produce_name_dialog">
                    </div>                    
	      		</div>
	      		<div class="row">
	      			<div class="col-md-4" style="margin-top: 25px;">
                    	<button type="button" class="btn btn-primary btn-sm"  id="btn_search_location_produce_table">
     						<b>ค้นหา</b>
     					</button>
     					<button type="button" class="btn default btn-sm" id="btn_clear_search_location_produce_table">
     						<b> ล้างข้อมูล </b>
     					</button>
                    </div>  
	      		</div>
	      	</div>
	      	
	      	<div class="form-group">
	      		<div class="row">
	      			<div class="col-md-12">
	      				<table class="table table-striped table-bordered table-hover order-column table_managed" id="table_search_location_produce">
                         <thead>
                             <tr>
                                 <th> ลำดับ </th>
                                 <th> สถานที่ผลิต</th>
                                 <th> ที่อยู่</th>
                                 <th>  </th>
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
	<%-- Dialog --%>
     
	<script src="${context}/assets/js/page_checklistplan.js" type="text/javascript"></script>
</body>
</html>