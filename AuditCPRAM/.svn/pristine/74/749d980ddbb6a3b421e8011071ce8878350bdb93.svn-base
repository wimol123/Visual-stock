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
     <link href="${context}/assets/global/plugins/bootstrap-checkbox/css/bootstrap-checkbox.css" rel="stylesheet" type="text/css">
     <link href="${context}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css">
     <link href="${context}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.css" rel="stylesheet" type="text/css">
     
     <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
     <!-- <script src="assets/pages/scripts/table-datatables-managed.js" type="text/javascript"></script> -->
     <script src="${context}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.th.min.js" type="text/javascript"></script>
     

	<style type="text/css">
   	 	.close{
   	 		text-indent : 0px; !important
   	 		width: 14px;
			height: 21px;
   	 	}
   	 	.bootstrap-switch-large{
   			 width: 200px;
		}
		
/*    .table-responsive {
   overflow-x: inherit;
   
}
table.dataTable td:nth-child(2) 
{
    max-width:120px;
    word-wrap: break-word;
    white-space: nowrap;
    overflow: hidden;  
    text-overflow: ellipsis;
} */

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
                 <a href="supplier.jsp">ซัพพลายเออร์</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span>รายละเอียดซัพพลายเออร์</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
     <input class="hide" id="supplier_id" value="${param.input_supplierId}">
     <div class="row">
		<div class="col-md-6">
     		<div class="portlet light " id="portlet-supplierDetail">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-badge"></i>
                        <span class="caption-subject bold uppercase"> ข้อมูลซัพพลายเออร์  </span>
                    </div>
                    <div class="actions">						
						<!-- <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a> -->
					</div> 
                </div>
                <div class="portlet-body form">
                    <form role="form" id="supplier_form" enctype="multipart/form-data">
                        <div class="form-body">
                        	<div class="row">
                        		<div class="col-md-12">
                        			<div id="error-choose-file" class="center-block" style="width:800px;display:none"></div>
                        		</div>
                        	</div>
                        	<div class="row">
                        		<div class="col-md-6">
                        			<div class="form-group files-input-section">
                        				<input id="logo" name="logo" type="file" class="file" data-preview-file-type="image/*" accept=".jpg, .jpeg, .png" >
                        				<span class="font-grey-salsa"> ชื่อไฟล์ภาพต้องเป็นภาษาอังกฤษหรือตัวเลข  และต้องมีนามสุกลของไฟล์ภาพเป็น jpg, jpeg, png เท่านั้น</span>
                        			</div>  
                        			                     			
                        		</div>
                        		<div class="col-md-6 hide">
                        			<div class="form-group show-image">
                        				<img class="img-thumbnail" id="shown-logo" name="shown-logo">
                        			</div> 
                        			<button type="button" id="btn_change_logo" class="btn btn-sm info"> เปลี่ยนโลโก้ใหม่ </button>
                        		</div>
                        		<div class="col-md-6">
                        			<div class="row hide">
                        				<div class="col-md-12">
                        					<div class="form-group">
							                	<label>Supplier ID</label>
							                    <input class="form-control"  type="text" id="supplierId" name="supplierId" > 
							                </div>
                        				</div>
                        			</div>
                        			<div class="row hide">
                        				<div class="col-md-12">
                        					<div class="form-group">
							                	<label>Logo Path</label>
							                    <input class="form-control"  type="text" id="logoPath" name="logoPath" > 
							                </div>
                        				</div>
                        			</div>
                        			<div class="row">
                        				<div class="col-md-12">
                        					<div class="form-group">
							                	<label>หมายเลขซัพพลายเออร์</label>
							                    <input class="form-control" placeholder="หมายเลขซัพพลายเออร์" type="text" id="supplier_Code" name="supplier_Code" > 
							                </div>
                        				</div>
                        			</div>
                        			
                        			<div class="row">
                        				<div class="col-md-12">
                        					<div class="form-group">
							                	<label>บริษัท</label><span class="required"> * </span>
							                    <input class="form-control" placeholder="บริษัท" type="text" id="supplier_company" name="supplier_company" > 
							                </div>
                        				</div>
                        			</div>
                        			
						      		<div class="row">
						      			<div class="col-md-12">
						      				<div class="form-group">
							                	<label>ชื่อผู้ติดต่อ</label><!-- <label class="require_Asterisk"> &#x2A;</label> --><span class="required"> * </span>
							                    <input class="form-control" placeholder="ชื่อผู้ติดต่อ" type="text" id="contact_name" name="contact_name" value=""> 
							                </div>	
						      			</div>
						      		</div>					                		                
					                	                        			
                        		</div>
                        	</div>
                            <br/>    
                            
			                <div class="row">
			                	<div class="col-md-8">
			                		<div class="form-group">
			                			<label> 
			                				ที่อยู่
			                				<span class="required"> * </span>
			                				<span class="font-grey-salsa"> - เช่น เลขที่ อาคาร ตึก ชั้น ถนน </span> 
			                			</label>
			                    		<input class="form-control" placeholder="ที่อยู่" type="text" id="address" name="address" value=""> 
			                		</div>			                	
			                	</div>			                	
			                	<div class="col-md-4">
			                		<div class="form-group">
			                			<label> ตำบล/แขวง </label><span class="required"> * </span>
			                    		<input class="form-control" placeholder="ตำบล/แขวง" type="text" id="sub_district" name="sub_district"> 
			                		</div>			                	
			                	</div>		
			                	                	
			                </div>
			                
			                <div class="row hide">
			                	<div class="col-md-4">
			                		<div class="form-group">
			                			<label> AddressId </label><span class="required"> * </span>
			                    		<input class="form-control" placeholder="address_id" type="text" id="address_id" name="address_id"> 
			                		</div>			                	
			                	</div>	         	
			                	<div class="col-md-4">
			                		<div class="form-group">
			                			<label> SubDistrictId </label><span class="required"> * </span>
			                    		<input class="form-control" placeholder="Sub District Id" type="text" id="sub_district_id" name="sub_district_id"> 
			                		</div>			                	
			                	</div>		
			                	                	
			                </div>
			                
			                <div class="row">	
			                	<div class="col-md-4">
			                		<div class="form-group">
			                			<label> อำเภอ/เขต </label><span class="required"> * </span>
			                    		<input class="form-control" placeholder="อำเภอ/เขต" type="text" id="district" name="district"> 
			                		</div>			                	
			                	</div>			                	
			                	<div class="col-md-4">
			                		<div class="form-group">
			                			<label> จังหวัด </label><span class="required"> * </span>
			                    		<input class="form-control" placeholder="จังหวัด" type="text" id="province" name="province"> 
			                		</div>			                	
			                	</div>
			                	<div class="col-md-4">
			                		<div class="form-group">
			                			<label> รหัสไปรษณีย์ </label><span class="required"> * </span>
			                    		<input class="form-control" placeholder="รหัสไปรษณีย์" type="text" id="postcode" name="postcode"> 
			                		</div>			                	
			                	</div>
			                </div>
			                
			                <div class="row hide">
			                	<div class="col-md-4">
			                		<div class="form-group">
			                			<label> DistrictId </label><span class="required"> * </span>
			                    		<input class="form-control" placeholder="DistrictId" type="text" id="district_id" name="district_id"> 
			                		</div>			                	
			                	</div>				                	
			                	<div class="col-md-4">
			                		<div class="form-group">
			                			<label> ProvinceId </label><span class="required"> * </span>
			                    		<input class="form-control" placeholder="ProvinceId" type="text" id="province_id" name="province_id"> 
			                		</div>			                	
			                	</div>
			                	
			                </div>
			                
			                <div class="form-group">
			                	<label>ชื่อเข้าใช้งาน</label><span class="required"> * </span>
			                    <input id="userlan_dialog" name="userlan_dialog" class="form-control" placeholder="ชื่อเข้าใช้งาน" type="text" value=""> 
			                </div>
				      		<!-- <div class="form-group hide">
			                	<label>รหัสผ่าน</label><span class="required"> * </span><br>
			                	<div class="font-grey-salsa">
			                		<span>
			                		 	- มีความยาวอย่างน้อย 8 ตัวอักษรและไม่เกิน 12 ตัวอักษร และประกอบด้วย 			                		 
			                		</span><br>
			                		<div style="margin-left: 4em;">
			                			<span>- อักษรตัวใหญ่ (A-Z)&nbsp;และตัวเล็ก (a-z)</span><br>
			                			<span>- ตัวเลข  (0-9)</span> <br>
			                			<span>- สัญลักษณ์ (เช่น *, !, @, $, %, ^ )  </span><br>
			                		</div>
			                		<span>
			                		 	- รหัสผ่านห้ามเป็นภาษาไทย		                		 
			                		</span><br>
			                	</div>
			                	
			                	
			                    <input id="password_dialog" name="password_dialog" class="form-control" placeholder="รหัสผ่าน" type="password" value="adminComA"> 
			                    <span class="label label-default">ใช้เฉพาะ กรณี login ด้วย Username </span>
			                </div> -->
				      		<div class="form-group">
			                	<label>อีเมล์</label><span class="required"> * </span>
			                    <input class="form-control" placeholder="อีเมล์"  type="text" id="supplier_email" name="supplier_email" value=""> 
			                </div>
				      		<div class="form-group">
			                	<label>เบอร์โทรศัพท์</label><span class="required"> * </span>
			                    <input class="form-control" placeholder="เบอร์โทรศัพท์" type="text" id="supplier_telephone" name="supplier_telephone" value=""> 
			                </div>
			                
			                
			                
				      		<!-- <div class="form-group">
	       						<label> Green Card </label> <br/>           	
	       						<input class="checkbox-status" type="checkbox" name="status_greenCard" data-on-text="อนุมัติ" data-off-text="ไม่อนุมัติ" id="status_greenCard" checked>	           			
	       					</div> -->
			                
			                <div class="row">
			                	<div class="col-md-4">
			                		<div class="form-group">
			       						<label> AVL </label> <br/>           			
			           					<input class="checkbox-status" type="checkbox" name="status_avl" data-on-text="อนุมัติ" data-off-text="ไม่อนุมัติ" id="status_avl" checked>	           			
			       					</div>
			                	</div>
			                	<div class="col-md-4">
			                		<div class="form-group">
			       						<label>Green Card</label> <br/>           			
			           					<input class="checkbox-status" type="checkbox" name="status_greenCard" data-on-text="ได้รับ" data-off-text="ไม่ได้รับ" id="status_greenCard" checked>		           			
			       					</div>
			                	</div>
			                	<div class="col-md-4">
			                		<div class="form-group">
			       						<label>สถานะ</label> <br/>           			
			           					<input class="checkbox-status" type="checkbox" name="status_supplier" data-on-text="ใช้งาน" data-off-text="ไม่ใช้งาน" id="status_supplier" checked>		           			
			       					</div>
			                	</div>
			                	
			                </div>    
			                
			                <div class="row">
			                	
			                	<div class="col-md-4">
			                		<div class="form-group">
			                			<label>รอบเข้าตรวจ</label><span class="required">*</span>
			                			<div class="input-group">
			                				<input class="form-control" placeholder="จำนวนวัน" type="text" id="auditRound" name="auditRound">
			                				<div class="input-group-addon">
        										<span>วัน</span>
    										</div>
			                			</div>				                   	
					                </div>
			                	</div>
			                	<div class="col-md-5 expire-greencard">
			                		<div class="form-group">
				      					<label>วันหมดอายุของ Green Card</label><span class="required"> * </span>
			                    		<div class="input-group date datepicker-dialog" data-provide="datepicker">
    										<input type="text" class="form-control" readonly="readonly" id="greenCardExpire" name="greenCardExpire">
    										<div class="input-group-addon">
        										<span class="glyphicon glyphicon-calendar"></span>
    										</div>
										</div>
				      				</div>
			                	</div>
			                	<div class="col-md-1 hide">
			                		<div class="form-group">
			                			<label>วันหมดอายุ Green Card</label><span class="required">*</span>
					                    <input class="form-control" placeholder="จำนวนวัน" type="text" id="greenCardExpireHistory" name="greenCardExpireHistory" readonly="readonly">
			                		</div>
			                	</div>
			                </div>
			                 
                        </div>
                        <div class="form-actions">
                            <button type="button" id="btn_submitsupplier" class="btn green"> บันทึก </button>
                        </div>
                    </form>
                </div>
            </div>
            
            
            <div class="portlet light option-supplier" id="portlet-contract">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-users"></i>
                        <span class="caption-subject bold uppercase"> ช่องทางติดต่อ </span>
                    </div>
                    <div class="actions">
	                     <div class="btn-group">
	                         <button   class="btn sbold green hide"  data-toggle="modal" href="#form_add_contact"> เพิ่มช่องทางติดต่อ
	                         	<i class="fa fa-plus"></i>
	                         </button>
	                     </div>
	                     					
						<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
						
	                 </div>
                                    
                </div>
                <div class="portlet-body form">
                	<!-- <div class="table-responsive">        -->         	
                	
                     <table class="table table-striped table-bordered table-hover" id="table_contact" width="100%">
                         <thead>
                             <tr>
                             	 <th width="2px"> ลำดับ </th>
                             	 <th>contactId</th>
                                 <th> ชื่อ </th>
                                 <th> รายละเอียด </th>
                                 <th> เบอร์โทรศัพท์ </th>
                                 <th> อีเมล์ </th>
                                 <th> เลือก </th>                                 
                             </tr>
                         </thead>
                        
                     </table>
                     
                    <!--  </div> -->
                </div>
            </div>
            
          <div class="portlet light option-supplier" id="supplierQuestionaireDocument">
            	<div class="portlet-title">
            		<div class="caption font-green-sharp">
                    	<i class="icon-question"></i>
                        <span class="caption-subject bold uppercase"> แบบสอบถาม</span>
                    </div>
            	</div>
            	<div class="portlet-body">
            	</div>
            	
            </div>
            
            
            
     	</div>
		<div class="col-md-6">		
     		
     		
     		<div class="portlet light option-supplier" id="portlet-location-produce">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-star"></i>
                        <span class="caption-subject bold uppercase"> สถานที่ผลิต </span>
                    </div>
                    <div class="actions">
	                     <div class="btn-group">
	                         <button   class="btn sbold green hide"  data-toggle="modal" href="#dialog_add_location_produce"> เพิ่มสถานที่ผลิตของซัพพลายเออร์
	                         	<i class="fa fa-plus"></i>
	                         </button>
	                     </div>
	                    					
						<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
				
	                 </div>                                    
                </div>
                <div class="portlet-body form">                
                
                	<div class="form-group">
                     	<div class="row">
                     		<div class="col-md-5">
                     			<label> ชื่อประเภทสินค้า  </label>
    							<input type="text" class="form-control input-sm" id="produce_product_type">
                     		</div>    
                     		<div class="col-md-5">
                     			<label> ชื่อสถานที่ผลิต  </label>
    							<input type="text" class="form-control input-sm" id="produce_location">
                     		</div>    
                     	</div>                     	
                     </div>                     
                     
                     <div class="form-group">
                     	<div class="row">
                     		<div class="col-md-12">
                     			<button type="button" class="btn btn-primary btn-sm" style="margin-top:8px;" id="btn_search_produce">
     								<b>ค้นหา</b>
     							</button>
     							<button type="button" class="btn default btn-sm" style="margin-top:8px;" id="btn_clear_produce">
     								<b> ล้างข้อมูล </b>
     							</button>
                     		</div>                     		                 		
                     	</div>
                     </div>
                
                     <table class="table table-striped table-bordered table-hover order-column table_managed" id="table_product_mapping_supplier">
                         <thead>
                             <tr>
                             	<th> ลำดับ  </th>
                             	<th> ชื่อประเภทสินค้า </th>
                             	<th> ชื่อสถานที่ผลิต </th>
                             	<th> เลือก </th>
                             </tr>
                         </thead>
                         <!-- <tbody>
                             <tr class="odd gradeX">
                                 <td> Product A </td>
                                 <td>
                                 	<a href="javascript:;" class="btn btn-xs default">
                                                     <i class="icon-close "></i> Remove </a>
                                 </td>
                             </tr>
                             <tr class="odd gradeX">
                                 <td> Product B </td>
                                 <td>
                                 	<a href="javascript:;" class="btn btn-xs default">
                                                     <i class="icon-close "></i> Remove </a>
                                 </td>
                             </tr>
                         </tbody> -->
                     </table>
                </div>
            </div>
            
            
            
     		<div class="portlet light option-supplier" id="apoint_supplier">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-calendar"></i>
                        <span class="caption-subject bold uppercase"> นัดหมาย</span>
                    </div>         
                    <div class="actions">
	                     <div class="btn-group">
	                         <!--  <a   class="btn default" href="admin_appoint_form.jsp"> เพิ่มนัดหมาย
	                         	<i class="fa fa-plus"></i>
	                         </a>-->
	                         <a   class="btn default hide" href="appoint.jsp"> เพิ่มนัดหมาย
	                         	<i class="fa fa-plus"></i>
	                         </a>                         				
							
	                     </div>
	                     
	                     <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
	                 </div>     
                </div>
                <div class="portlet-body form">
                   	
                   <table class="table table-bordered table-striped" id="table_show_appoint">
				            <tbody>
				                <!-- <tr>
				                    <td style="width:30%">
				                        01/08/2018  13.00
				                    </td>
				                    <td style="width:50%"> เอกสารการจดทะเบียนบริษัท </td>
				                    <td style="width:20%"> <a class="btn btn-xs default"><i class="icon-magnifier"></i> ดูรายละเอียด </a> </td>
				                    <td style="width:10%" class="hide"> 1111 </td>
				                    <td style="width:10%" class="hide"> admin </td>
				                </tr>
				                <tr>
				                    <td>
				                        10/08/2018  10.00
				                    </td>
				                    <td>แจ้งเข้า Audit</td>
				                </tr> -->
				            </tbody>
				        </table>
				        
				        <form class='hide' action="${context}/appoint_form.jsp" method="post" id="passDataToForm">     	
     						<input type="text" id="detailAppoint" name="detailAppoint">
    					</form>
                </div>
            </div>
            
            
            <div class="portlet light option-supplier" id="supplierStandardDocument">
            	<div class="portlet-title">
            		<div class="caption font-green-sharp">
                    	<i class="fa fa-folder-open-o"></i>
                        <span class="caption-subject bold uppercase"> เอกสารรับรองมาตรฐาน</span>
                    </div>        
                    <div class="actions">	                     					
						<!-- <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a> -->					
	                 </div> 
            	</div>
            	<div class="portlet-body">
            	
            		<!-- <div class="form-group">
            			<div class="row">
	            			<div class="col-md-12">
	            				<div class="caption font-green-sharp">
	            					<span class="bold" style="overflow-wrap: break-word;">sdgklsdhfgishdkg</span>
	            				</div>	            				
	            			</div>
	            		</div>
            		</div>
            		
            		<div class="form-group">
            			<div class="row">
	            			<div class="col-md-12">
	            				<table class="table table-bordered table-striped">
	            					<tbody>
	            						<tr>
	            							<td style="width:80%"><a href="#"><img src="assets/images/icon_word.png" width="30" /></a><span style="overflow-wrap: break-word;">&nbsp;Individual Development Plan Form as of 2019.xlsx</span></td>
	            							<td style="width: 20%"><a class="btn btn-xs default"><i class="icon-download"></i> ดาวน์โหลด </a></td>
	            						</tr>
	            					</tbody>
	            				</table>
	            			</div>
	            		</div>
	            	</div> -->
            		
            	</div>
            </div>
            
            
             
     		<div class="portlet light option-supplier hide">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-folder-alt"></i>
                        <span class="caption-subject bold uppercase"> เอกสาร</span>
                    </div>        
                    <div class="actions">
	                     <div class="btn-group">
	                         <a class="btn default" href="appoint_doc.jsp"> เพิ่มการขอเอกสาร
	                         	<i class="fa fa-plus"></i>
	                         </a>
	                     </div>
	                     					
						<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
					
	                 </div> 
                </div>
                <div class="portlet-body form">
                   	
                   <table class="table table-bordered table-striped">
				            <tbody>
				                <tr>
				                    <td style="width:50%"> เอกสารการจดทะเบียนบริษัท </td>
				                    <td style="width:20%">
				                        <a href="#"><img src="assets/images/icon_word.png" width="30" /></a>
				                    </td>
				                    <td style="width:30%">
				                        01/08/2018  13.00
				                    </td>
				                </tr>
				                <tr>
				                    <td>เอกสารรายการประเภทสินค้า</td>
				                    <td>
				                        <a href="#"><img src="assets/images/icon_excel.png" width="30" /></a>
				                    </td>
				                    <td>
				                        10/08/2018  10.00
				                    </td>
				                </tr>
				            </tbody>
				        </table>
                </div>
            </div>
            
            
            
            <div class="portlet light option-supplier hide">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-note"></i>
                        <span class="caption-subject bold uppercase"> ผลการทำแบบทดสอบเช็คลิสต์ </span>
                    </div>
                    
                    <div class="actions">						
						<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
					</div> 
                                    
                </div>
                <div class="portlet-body form">
                     <table class="table table-striped table-bordered table-hover order-column table_managed">
                         <thead>
                             <tr>
                                 <th> ชื่อเช็คลิสต์</th>
                                 <th>  </th>
                             </tr>
                         </thead>
                         <tbody>
                             <tr class="odd gradeX">
                                 <td> Ingredient </td>
                                 <td>
                                 	<a  data-toggle="modal" href="#form_view_checklist" class="btn btn-xs default">
                                                     <i class="icon-magnifier"></i> ดูรายละเอียด </a>
                                 </td>
                             </tr>
                             <tr class="odd gradeX">
                                 <td> Seafood </td>
                                 <td>
                                 	<a  data-toggle="modal" href="#form_view_checklist" class="btn btn-xs default">
                                                     <i class="icon-magnifier"></i> ดูรายละเอียด </a>
                                 </td>
                             </tr>
                         </tbody>
                     </table>
                </div>
            </div>
            
            
     	</div>
     </div>
     
     
     <!-- <div class="row">
		<div class="col-md-6">		
		
     		
            
            
     	</div>
		<div class="col-md-6">
		
		
     		
            
            
            
     	</div>
     </div> -->
     

	
	
	<%-- Dialog --%>
	<div id="dialog_add_location_produce" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000" data-keyboard="false" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        
		    <div class="row">
				<div class="col-md-12">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title">เพิ่มสถานที่ผลิตของซัพพลายเออร์</h4>
				</div>
			</div>
		           
	      </div>
	      <div class="modal-body">	      
	      	
            <form role="form" id="location_produce_form" >          
            	<div class="form-body">  		
            		<div class="row">
            			<div class="col-md-12">
            				<div class="form-group">
	      						<label>ประเภทสินค้า </label><span class="required"> * </span>
				        		<select class="form-control select2 select2-container select2-container--bootstrap select2-container--below" placeholder="กรุณาเลือกประเภทสินค้า" style="width:auto !important" id="product_type_selection" name="product_type_selection"></select>
	      					</div>
            			</div>            		
            		</div>
            		
            		<div class="row">
            			<div class="col-md-12">
            				<div class="form-group">
            					<label>ชื่อสถานที่ผลิต  </label><span class="required"> * </span>
            					<input id="location_name_produce" name="location_name_produce" class="form-control" placeholder="กรุณาใส่ชื่อสถานที่ผลิต" type="text"> 
            				</div>	
            			</div>
            		</div>
            		
            		<div class="row hide">
            			<div class="col-md-12">
            				<div class="form-group">
	      						<label>Location Produce Id </label><span class="required"> * </span>
				        		<input id="location_Id_produce" name="location_Id_produce" class="form-control" placeholder="กรุณาใส่ชื่อสถานที่ผลิต" type="text">
	      					</div>
            			</div>            		
            		</div>
            		
            		<div class="row">
            			<div class="col-md-12">
            				<div class="form-group">
            					<div class="form-md-checkboxes">
            						<div class="checkbox">
            							<label style="font-size: 16px;">
            								<input type="checkbox" id="checkbox_same_mainlocation" value="" checked>
            								<span class="cr"><i class="cr-icon fa fa-check font-green"></i></span>
            								<span> ที่อยู่เดียวกับที่อยู่จดทะเบียนตามกฎหมาย</span>
            							</label>
            						</div>
            					</div>            					
            				</div>
            			</div>
            		</div>
            		
            		<div class="group-detail-address">
            		
            			<div class="row">
	            			<div class="col-md-4">
			                	<div class="form-group">
			                		<label> ที่อยู่ </label><span class="required"> * </span>
			                    	<input class="form-control" placeholder="ที่อยู่" type="text" id="address_produce" name="address_produce" value=""> 
			                	</div>			                	
			                </div>			                	
			                <div class="col-md-4">
			                	<div class="form-group">
			                		<label> ตำบล/แขวง </label><span class="required"> * </span>
			                    	<input class="form-control" placeholder="ตำบล/แขวง" type="text" id="sub_district_produce" name="sub_district_produce"> 
			                	</div>			                	
			                </div>		
			                <div class="col-md-4">
			                	<div class="form-group">
			                		<label> อำเภอ/เขต </label><span class="required"> * </span>
			                    	<input class="form-control" placeholder="อำเภอ/เขต" type="text" id="district_produce" name="district_produce"> 
			                	</div>			                	
			                </div>	
            			</div>
            			
            			<div class="row hide">
			                <div class="col-md-4">
			                	<div class="form-group">
			                		<label> AddressId </label><span class="required"> * </span>
			                    	<input class="form-control" placeholder="address_id" type="text" id="address_id_produce" name="address_id_produce"> 
			                	</div>			                	
			                </div>	         	
			                <div class="col-md-4">
			                	<div class="form-group">
			                		<label> SubDistrictId </label><span class="required"> * </span>
			                    	<input class="form-control" placeholder="Sub District Id" type="text" id="sub_district_id_produce" name="sub_district_id_produce"> 
			                	</div>			                	
			                </div>		
			                <div class="col-md-4">
			                	<div class="form-group">
			                		<label> DistrictId </label><span class="required"> * </span>
			                    	<input class="form-control" placeholder="DistrictId" type="text" id="district_id_produce" name="district_id_produce"> 
			                	</div>			                	
			                </div>	                	
			              </div>
			                
			              <div class="row">			                	
			              	<div class="col-md-4">
			                	<div class="form-group">
			                		<label> จังหวัด </label><span class="required"> * </span>
			                    	<input class="form-control" placeholder="จังหวัด" type="text" id="province_produce" name="province_produce"> 
			                	</div>			                	
			                </div>
			                <div class="col-md-4">
			                	<div class="form-group">
			                		<label> รหัสไปรษณีย์ </label><span class="required"> * </span>
			                    	<input class="form-control" placeholder="รหัสไปรษณีย์" type="text" id="postcode_produce" name="postcode_produce"> 
			                	</div>			                	
			                </div>
			               </div>
			                
			               <div class="row hide">			                	
			                	<div class="col-md-4">
			                		<div class="form-group">
			                			<label> ProvinceId </label><span class="required"> * </span>
			                    		<input class="form-control" placeholder="ProvinceId" type="text" id="province_id_produce" name="province_id_produce"> 
			                		</div>			                	
			                	</div>			                	
			                </div>
            			
            		</div>
            		
	      			
	      		</div>
            </form>
          	
          	<div class="form-actions">
	             <button type="button" id="btn_submit_location_produce" class="btn green">บันทึก</button>
	        </div>     	
	      	
	      	                                              
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
	
	<%-- Dialog --%>
	<div id="form_add_contact" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000" data-keyboard="false" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        
		    <div class="row">
				<div class="col-md-12">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title">ช่องทางการติดต่อ</h4>
				</div>
			</div>
		           
	      </div>
	      <div class="modal-body">
	             
            <div class="form-body">
            	<form role="form" id="supplier_contract_form" >
            		<div class="form-group">
				         <label>รหัสพนักงาน</label><!-- <label class="require_Asterisk option-require"> &#x2A;</label> -->
				         <input id="employee_id_contract_modal" name="employee_id_contract_modal" class="form-control" placeholder="รหัสพนักงาน" type="text"> 
				    </div>
				    <div class="form-group">
				    	<label>กลุ่มผู้ใช้งาน</label><label class="require_Asterisk"> &#x2A;</label>
				        <select class="form-control select2 select2-container select2-container--bootstrap select2-container--below" placeholder="กลุ่มผู้ใช้งาน" style="width:auto !important" id="user_group_selection" name="user_group_selection"></select> 
				    </div>
				    <div class="form-group">
				    	<label>ชื่อ - นามสกุลผู้ติดต่อ</label><label class="require_Asterisk"> &#x2A;</label>
				        <input class="form-control" placeholder="ชื่อ - นามสกุลผู้ติดต่อ" type="text" id="contact_name_contract_modal" name="contact_name_contract_modal"> 
				    </div>
				    <div class="form-group form-description">
				    	<label>รายละเอียด</label><label class="require_Asterisk"> &#x2A;</label>
				        <input class="form-control" placeholder="รายละเอียด เช่น ฝ่ายบัญชี" type="text" id="description_contract_modal" name="description_contract_modal"> 
				    </div>
				    <div class="form-group">
				        <label>ชื่อเข้าใช้งาน</label><label class="require_Asterisk"> &#x2A;</label>
				        <input id="userlan_contract_modal" class="form-control" placeholder="ชื่อสำหรับให้ผู้ใช้งานของซัพพลายเออร์เข้าใช้งานระบบ" type="text" name="userlan_contract_modal"> 
				    </div>
				    
				   <!--  <div class="form-group">
				        <label>รหัสผ่าน</label><label class="require_Asterisk password_require"> &#x2A;</label>
				        <div class="font-grey-salsa">
				        	<span>- มีความยาวอย่างน้อย 8 ตัวอักษรและไม่เกิน 12 ตัวอักษร และประกอบด้วย  </span><br>
				        	<div style="margin-left: 4em;">
				        		<span>- อักษรตัวใหญ่ (A-Z)&nbsp;และตัวเล็ก (a-z)</span><br>
				        		<span>- ตัวเลข  (0-9)</span> <br>
				        		<span>- สัญลักษณ์ (เช่น *, !, @, $, %, ^ )  </span><br>
				        	</div>
				        	<span>- รหัสผ่านห้ามเป็นภาษาไทย </span><br>
				        </div>
				        <input id="password_contract_modal" class="form-control" placeholder="รหัสผ่าน" type="password"> 
				        <span class="label label-default">ใช้เฉพาะ กรณี login ด้วย Username </span>
				    </div> -->
				    
				    <div class="form-group">
				        <label>เบอร์โทรศัพท์</label><label class="require_Asterisk"> &#x2A;</label>
				        <input class="form-control" placeholder="เบอร์โทรศัพท์" type="text" id="contact_tel_contract_modal" name="contact_tel_contract_modal"> 
				    </div>
				    <div class="form-group">
				    	<label>อีเมล์</label><label class="require_Asterisk"> &#x2A;</label>
				        <input class="form-control" placeholder="อีเมล์" type="text" id="contact_email_contract_modal" name="contact_email_contract_modal"> 
				    </div>
				    	<input class="form-control hide" type="text" id="contact_id_contract_modal">
            	 	</form> 
	             </div>
	             
	             <div class="form-actions">
	                 <button type="button" id="btn_dialog_contact" class="btn green">บันทึก</button>
	             </div>             
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
	
	
	<%-- Dialog --%>
	<div id="form_view_checklist" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        
		    <div class="row">
				<div class="col-md-6">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title"> ผลการทำแบบทดสอบ Checklist</h4>
				</div>
			</div>
		           
	      </div>
	      <div class="modal-body">
	      
             
            
                                               
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
	<script src="assets/js/page_supplierform.js" type="text/javascript"></script>   
	
</body>
</html>