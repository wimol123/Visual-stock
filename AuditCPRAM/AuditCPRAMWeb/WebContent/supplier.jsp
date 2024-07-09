<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="th.co.gosoft.audit.cpram.model.UserModel"%>
<%@page import="th.co.gosoft.audit.cpram.model.UserGroupModel"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	 <meta charset="utf-8" />
	 <c:set var="context" value="${pageContext.request.contextPath}" />
     <link href="${context}/assets/css/custom.css" rel="stylesheet" type="text/css" />  
     <link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
     <link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
     <link href="assets/css/fix_datatable.css" rel="stylesheet" type="text/css" />
     <link href="${context}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css">
     <link href="${context}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.css" rel="stylesheet" type="text/css">
     
     <script src="${context}/assets/global/plugins/jquery.min.js" type="text/javascript"></script>  
     <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
     <%-- <script type="text/javascript"> var contextPath='<%=request.getContextPath()%>';</script> --%>
     <script src="${context}/assets/global/plugins/jquery-bloodhound/js/bloodhound.js" type="text/javascript"></script>
   	 <script src="${context}/assets/global/plugins/jquery-typeahead/js/typeahead.jquery.js" type="text/javascript"></script>
   	 <script src="${context}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.th.min.js" type="text/javascript"></script>
   	 
   	 <style type="text/css">
   	 	.close{
   	 		text-indent : 0px; !important
   	 		width: 14px;
			height: 21px;
   	 	}
   	 	
   	 	.twitter-typeahead {
	width: 100%
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

.tt-cursor, .tt-suggestion:hover {
	background: #f5f5f5
}

.twitter-typeahead {
	width: 100%
}

.tt-hint {
	opacity: .3 !important
}
	.krajee-default.file-preview-frame.file-preview-frame{
		margin: 0px;
		width: 100%;
	}
	
   	.kv-avatar .krajee-default.file-preview-frame,.kv-avatar .krajee-default.file-preview-frame:hover {
    margin: 0;
    padding: 0;
    border: none;
    box-shadow: none;
    text-align: center;
    
}
.kv-avatar {
    display: inline-block;
}
.kv-avatar .file-input {
    display: table-cell;
    width: 100%;
}
.kv-reqd {
    color: red;
    font-family: monospace;
    font-weight: normal;
}
	</style>
	 
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
                 <span>ซัพพลายเออร์</span>
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
             			<i class="icon-speech"></i>
	                    <span class="caption-subject bold uppercase"> ซัพพลายเออร์ </span>
             		</div>
             		<div class="actions">
						<a class="btn sbold green hide" data-toggle="modal" href="#dialog_add_supplier"> เพิ่ม ซัพพลายเออร์
                           	<i class="fa fa-plus"></i>
                        </a>
						<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
					</div> 
             	</div>
             
                 <div class="portlet-body">
                     
                     <div class="form-group">
                     	<div class="row">
                     		<div class="col-md-3">
                     			<label>  บริษัท  </label>
    							<input type="text" class="form-control input-sm" id="company_textbox">
                     		</div>
                     		<div class="col-md-3">
                     			<label> Vender Code </label>
    							<input type="text" class="form-control input-sm" id="supplier_code_textbox">
                     		</div>
                     		<div class="col-md-3">
                     			<label>  Green Card  </label>
    							<select class="form-control form-control-sm select-sm" id="select_green_card">
  									<option value="">--- ทั้งหมด ---</option>
  									<option value="Y"> ได้รับ </option>
  									<option value="N"> ไม่ได้รับ </option>
								</select>
                     		</div>
                     		<div class="col-md-3">
                     			<label>  สถานะ  </label>
    							<select class="form-control form-control-sm select-sm" id="select_status">
  									<option value="">--- ทั้งหมด ---</option>
  									<option value="Y"> ใช้งาน </option>
  									<option value="N"> ไม่ใช้งาน </option>
								</select>
                     		</div>
                     	</div>                     	
                     </div>                     
                     
                     <div class="form-group">
                     	<div class="row">
                     		<div class="col-md-4">
                     			<button type="button" data-loading-text="Searching..." class="btn btn-primary btn-sm" style="margin-top:8px;" id="btn_search">
     								<b>ค้นหา</b>
     							</button>
     							<button type="button" data-loading-text="Clearing..." class="btn default btn-sm" style="margin-top:8px;" id="btn_clear">
     								<b> ล้างข้อมูล </b>
     							</button>
                     		</div>                     		                 		
                     	</div>
                     </div>
                     
                     <table class="table table-striped table-bordered table-hover order-column table_managed" id="table_supplier">
                         <thead>
                             <tr>
                             	<th> ลำดับ </th>                             	 
                                 <th> บริษัท </th>
                                 <th> Vender Code </th>
                                 <th> GreenCard </th>
                                 <th> สถานะ </th>
                                 <th> SupplierId </th>
                                 <th> เลือก </th>
                             </tr>
                         </thead>
                         
                     </table>
                 </div>
             </div>
             <!-- END EXAMPLE TABLE PORTLET-->
         </div>
     </div>
     
     
     <%-- Dialog --%>
	<div id="dialog_add_supplier" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg ">
			<div class="modal-content">
				<div class="modal-header">
		    		<div class="row">
						<div class="col-md-12">									        			
		        			<button type="button" class="close btn-lg" data-dismiss="modal" aria-label="Close">		        				
		        				<span aria-hidden="true">&times;</span>
		        			</button>
		        			<h4 class="modal-title">เพิ่มซัพพลายเออร์</h4>
						</div>
					</div>
	      		</div>
	      		
	      		<div class="modal-body">
	      			<form role="form" id="supplier_form" enctype="multipart/form-data">
                        <div class="form-body">
                        	<div class="row">
                        		<div class="col-md-12">
                        			<div id="error-choose-file" class="center-block" style="width:800px;display:none"></div>
                        		</div>
                        	</div>
                        	<div class="row">
                        		<div class="col-md-4">
                        			<div class="form-group">
                        				<input id="logo" name="logo" type="file" class="file" data-preview-file-type="image/*" accept=".jpg, .jpeg, .png" >
                        				<span class="font-grey-salsa"> ชื่อไฟล์ภาพต้องเป็นภาษาอังกฤษหรือตัวเลข  และต้องมีนามสุกลของไฟล์ภาพเป็น jpg, jpeg, png เท่านั้น</span>
                        			</div>                        			
                        		</div>
                        		<div class="col-md-8">
                        			<div class="row">
                        				<div class="col-md-12">
                        					<div class="form-group">
							                	<label>Vender Code </label>
							                    <input class="form-control" placeholder="Vender Code" type="text" id="supplier_Code_modal" name="supplier_Code_modal" value=""> 
							                </div>
                        				</div>
                        			</div>
                        			
                        			<div class="row">
                        				<div class="col-md-12">
                        					<div class="form-group">
							                	<label>บริษัท</label><span class="required"> * </span>
							                    <input class="form-control" placeholder="บริษัท" type="text" id="supplier_company_modal" name="supplier_company_modal" value=""> 
							                </div>
                        				</div>
                        			</div>
                        			
						      		<div class="row">
						      			<div class="col-md-12">
						      				<div class="form-group">
							                	<label>ชื่อผู้ติดต่อ</label><!-- <label class="require_Asterisk"> &#x2A;</label> --><span class="required"> * </span>
							                    <input class="form-control" placeholder="ชื่อผู้ติดต่อ" type="text" id="contact_name_modal" name="contact_name_modal" value=""> 
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
			                			
			                    		<input class="form-control" placeholder="ที่อยู่" type="text" id="address_modal" name="address_modal" value=""> 
			                		</div>			                	
			                	</div>			                	
			                	<div class="col-md-4">
			                		<div class="form-group">
			                			<label> ตำบล/แขวง </label><span class="required"> * </span>
			                    		<input class="form-control" placeholder="ตำบล/แขวง" type="text" id="sub_district_modal" name="sub_district_modal"> 
			                		</div>			                	
			                	</div>		
			                	               	
			                </div>
			                
			                <div class="row hide">
			                			                	
			                	
			                	<div class="col-md-offset-8 col-md-4">
			                		<div class="form-group">
			                			<label> DistrictId </label><span class="required"> * </span>
			                    		<input class="form-control" placeholder="DistrictId" type="text" id="district_id_modal" name="district_id_modal"> 
			                		</div>			                	
			                	</div>	                	
			                </div>
			                
			                <div class="row">		
			                	<div class="col-md-4">
			                		<div class="form-group">
			                			<label> อำเภอ/เขต </label><span class="required"> * </span>
			                    		<input class="form-control" placeholder="อำเภอ/เขต" type="text" id="district_modal" name="district_modal"> 
			                		</div>			                	
			                	</div>	 	                	
			                	<div class="col-md-4">
			                		<div class="form-group">
			                			<label> จังหวัด </label><span class="required"> * </span>
			                    		<input class="form-control" placeholder="จังหวัด" type="text" id="province_modal" name="province_modal"> 
			                		</div>			                	
			                	</div>
			                	<div class="col-md-4">
			                		<div class="form-group">
			                			<label> รหัสไปรษณีย์ </label><span class="required"> * </span>
			                    		<input class="form-control" placeholder="รหัสไปรษณีย์" type="text" id="postcode_modal" name="postcode_modal"> 
			                		</div>			                	
			                	</div>
			                </div>
			                
			                <div class="row hide">		
			                	<div class=" col-md-4">
			                		<div class="form-group">
			                			<label> SubDistrictId </label><span class="required"> * </span>
			                    		<input class="form-control" placeholder="Sub District Id" type="text" id="sub_district_id_modal" name="sub_district_id_modal"> 
			                		</div>			                	
			                	</div>			                	
			                	<div class="col-md-4">
			                		<div class="form-group">
			                			<label> ProvinceId </label><span class="required"> * </span>
			                    		<input class="form-control" placeholder="ProvinceId" type="text" id="province_id_modal" name="province_id_modal"> 
			                		</div>			                	
			                	</div>
			                	
			                </div>
			                
			                <div class="form-group">
			                	<label>ชื่อเข้าใช้งาน</label><span class="required"> * </span>
			                    <input id="userlan_dialog" name="userlan_dialog" class="form-control" placeholder="ชื่อสำหรับให้ผู้ใช้งานของซัพพลายเออร์เข้าใช้งานระบบ" type="text" value=""> 
			                </div>
				      		<div class="form-group hide">
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
			                	
			                	
			                    <input id="password_dialog" name="password_dialog" class="form-control" placeholder="รหัสผ่าน" type="password" value=""> 
			                    <span class="label label-default">ใช้เฉพาะ กรณี login ด้วย Username </span>
			                </div>
				      		<div class="form-group">
			                	<label>อีเมล์</label><span class="required"> * </span>
			                    <input class="form-control" placeholder="อีเมล์"  type="text" id="supplier_email_modal" name="supplier_email_modal" value=""> 
			                </div>
				      		<div class="form-group">
			                	<label>เบอร์โทรศัพท์</label><span class="required"> * </span>
			                    <input class="form-control" placeholder="เบอร์โทรศัพท์" type="text" id="supplier_telephone_modal" name="supplier_telephone_modal" value=""> 
			                </div>
			                
			                
			                
				      		<!-- <div class="form-group">
	       						<label> Green Card </label> <br/>           	
	       						<input class="checkbox-status" type="checkbox" name="status_greenCard" data-on-text="อนุมัติ" data-off-text="ไม่อนุมัติ" id="status_greenCard" checked>	           			
	       					</div> -->
			                
			                <div class="row">
			                	<div class="col-md-3">
			                		<!--  -->
					                <div class="form-group">
			       						<label> AVL </label><span class="required">*</span> <br/>           			
			           					<input class="checkbox-status" type="checkbox" name="status_avl" data-on-text="อนุมัติ" data-off-text="ไม่อนุมัติ" id="status_avl">	           			
			       					</div>
			                	</div>
			                	
			                	<div class="col-md-3">
			                		<div class="form-group">
			       						<label>สถานะ</label><span class="required">*</span> <br/>           			
			           					<input class="checkbox-status" type="checkbox" name="status_supplier" data-on-text="ใช้งาน" data-off-text="ไม่ใช้งาน" id="status_supplier" checked>		           			
			       					</div>
			                	</div>
			                	
			                	<div class="col-md-3">
			                		<div class="form-group">
			                			<label>รอบเข้าตรวจ</label><span class="required">*</span>
			                			<div class="input-group">
			                				<input class="form-control" placeholder="จำนวนวัน" type="text" id="auditRound" name="auditRound">
			                				<div class="input-group-addon">
        										<span>วัน</span>
    										</div>
			                			</div>
					                     
					                   	<!-- <div class="alert alert-info">
					                	<label>รอบเข้าตรวจ</label>
					                    <input class="form-control" placeholder="จำนวนวัน" type="text"> 
					                    </div> -->
					                </div>
			                	</div>
			                	
			                </div>
			                
			                
			                <div class="row">
			                	<div class="col-md-3">
			                		<div class="form-group">
			       						<label>Green Card</label><span class="required">*</span> <br/>           			
			           					<input class="checkbox-status" type="checkbox" name="status_greenCard" data-on-text="ได้รับ" data-off-text="ไม่ได้รับ" id="status_greenCard">		           			
			       					</div>
			                	</div>
			                	<div class="col-md-3 expire-greencard" style="display: none;">
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
			                </div>
				      		
			               
                        </div>
                        <div class="form-actions">
                            <button type="button" id="btn_submitsupplier_modal" class="btn green"> บันทึก </button>
                        </div>
                    </form>
	      		</div>
			</div>
		</div>
	</div>
    
     <form action="${context}/supplier_form.jsp" method="post" id="submithiddenform">
     	<input class="hide" type="text" value="" id="input_supplierId" name="input_supplierId">
     </form>
    
    <script src="${context}/assets/js/custom_dialog.js" type="text/javascript"></script>
    <script src="assets/js/page_supplier.js" type="text/javascript"></script>   

             
</body>
</html>