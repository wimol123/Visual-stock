<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	 <c:set var="context" value="${pageContext.request.contextPath}" />
     <link href="${context}/assets/css/custom.css" rel="stylesheet" type="text/css" />  
     <link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
     <link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
     <link href="assets/css/fix_datatable.css" rel="stylesheet" type="text/css" />
     <link href="${context}/assets/css/form_wizard.css" rel="stylesheet" type="text/css" />
     <link href="${context}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css">
     <link href="${context}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.css" rel="stylesheet" type="text/css">
     <link href="${context}/assets/global/plugins/bootstrap-tagsinput/css/bootstrap-tagsinput.css" rel="stylesheet" type="text/css">
     <link href="${context}/assets/global/plugins/bootstrap-tagsinput/css/bootstrap-tagsinput-typeahead.css" rel="stylesheet" type="text/css">
     <link href="${context}/assets/global/plugins/bootstrap-checkbox/css/bootstrap-checkbox.css" rel="stylesheet" type="text/css">
     <link href="multiselect.css" rel="stylesheet">
     
<%--      <script src="${context}/assets/global/plugins/jquery.min.js" type="text/javascript"></script>   --%>
     <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
<!--      <script src="assets/global/scripts/app.js" type="text/javascript"></script> -->
     <script src="${context}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.th.min.js" type="text/javascript"></script>
   	 <script src="${context}/assets/global/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js" type="text/javascript"></script>
   	 <script src="${context}/assets/global/plugins/select2/js/select2.min.js" type="text/javascript" ></script>

	<!-- common libraries -->
<!-- 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"> -->
<!-- 	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script> -->
<!-- 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script> -->
	
	<!-- plugin -->
	<script src="https://www.virtuosoft.eu/code/bootstrap-duallistbox/bootstrap-duallistbox/v3.0.2/jquery.bootstrap-duallistbox.js"></script>

	<link rel="stylesheet" type="text/css" href="https://www.virtuosoft.eu/code/bootstrap-duallistbox/bootstrap-duallistbox/v3.0.2/bootstrap-duallistbox.css">
   	 <style type="text/css">
   	 	.close{
   	 		text-indent : 0px; !important
   	 		width: 14px;
			height: 21px;
   	 	}
   	 	
   	 	.bootstrap-tagsinput, .bootstrap-tagsinput > input{
  			width: 100% !important;
  			
		}
	
		.bootstrap-tagsinput .tag{
   	 		font-size: 12px !important;
   		}   	 	
   	 	
   	 	#dialog_add_checklist>.modal-dialog {
  			width: 100% !important;
  			height: 100% !important;
  			margin: 0 !important;
  			padding: 0 !important;
		}

		#dialog_add_checklist>.modal-dialog>.modal-content {
  			height: auto;
  			min-height: 100% !important;
  			min-width: 100% !important;
  			border-radius: 0 !important;
		} 
   	 	.Editor-editor{
   	 		height: 200px !important;
   	 	}
   	 	
   	 	.quick-nav{
   	 		z-index: 10000;
   	 	}
   	 	
   	 	.quick-nav-overlay{
   	 		z-index: 9999;
   	 	}
		.navbar {
			display: inline;
		}
		.navbar-fixed-top {
		    position: fixed;
		}
 		.portlet{
 		    font-family: "Open Sans",sans-serif;
 			font-size: 14px !important; 
 		} 
 		.btn{ 
  			height: 34px;  
  		    padding: 6px 24px 6px 12px;  
 		    border-radius: 4px; 
 		    font-family: "Open Sans",sans-serif; 
 		    border-radius: 4px; 
 			font-size: 14px !important; 
 		} 
 		.form-control-css{ 
  			height: 34px !important;  
  		    padding: 6px 24px 6px 12px !important;  
 		    border-radius: 4px !important; 
 		    font-family: "Open Sans",sans-serif !important; 
 			font-size: 14px !important; 
 		} 
 		
 		.form-control-css{ 
  			height: 34px !important;  
  		    padding: 6px 24px 6px 12px !important;  
 		    border-radius: 4px !important; 
 		    font-family: "Open Sans",sans-serif !important; 
 			font-size: 14px !important; 
 		}
 		.file-zoom-dialog{
 			 z-index: 999999 !important;
 		}.modal{
 			 z-index: 999999 !important;
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
                 <a id="link" href="information.jsp">ข้อกำหนด/ข่าวสาร</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span> รายละเอียดข้อกำหนด/ข่าวสาร </span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
     
<%--      <input class="hide" id="information_id" name="information_id" value="${param.informationId}"/> --%>
     <input class="hide" id="information_detail" name="information_detail" value="${param.informationDetail}"/>
     <input class="hide" id="select_sup_hidden" name="select_sup_hidden" value=""/>
     
     <div class="row">
     	<div class="col-md-12">
     		<!-- BEGIN EXAMPLE TABLE PORTLET-->
     		<div class="portlet light" id='portlet-detail-information'>
     			<div class="portlet-title">
     				<div class="caption font-green-sharp">
             			<i class="icon-speech"></i>
	                    <span class="caption-subject bold uppercase"> รายละเอียดข้อกำหนด/ข่าวสาร </span>
             		</div>
     				<div class="actions">
     					<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
     					<!-- <a class = "btn" href="javascript:;" id="submit_btn">Click Me</a> -->
     				
     					<a class="btn btn-circle btn-icon-only btn-default font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">
			     			<i class="fa fa-angle-down fa-lg rotate-icon"></i>
			     		</a>
     				</div>
     			</div>
     			<div class="portlet-body form form-body-collapse">
     				<form role="form" class="form-horizontal" id="form-detail-information">
     					<div class="row">
     						<div class="col-md-4">
     							<div class="form-group">
		     						<label class="control-label col-md-8" for="information_title"> เลขที่ข้อกำหนด/ข่าวสาร
		     							<span class="required"> * </span>
		     						</label>
		     						<div class="col-md-4">
		      							<input type="text" readonly="true" class="form-control form-control-css select2 select2-container select2-container--bootstrap select2-container--below" id="information_id" name="information_id" value="">      							
		    						</div>    						
		     					</div>
     						</div>
     						<div class="col-md-8">
     							<div class="form-group">
		     						<label class="control-label col-md-4" for="information_title"> ชื่อข้อกำหนด/ข่าวสาร
		     							<span class="required"> * </span>
		     						</label>
		     						<div class="col-md-8">
		      							<input type="text" class="form-control form-control-css select2 select2-container select2-container--bootstrap select2-container--below" id="information_title" name="information_title" placeholder="กรุณาใส่ชื่อข้อกำหนด/ข่าวสาร" value="">      							
		    						</div>    						
		     					</div>
     						</div>
     					</div>
     					
     					
     					<div class="row">
     						<div class="col-md-4">
     							<div class="form-group">
     								<label class="control-label col-md-6" for="select_type">กลุ่มที่แจ้ง
		     							<span class="required"> * </span>
		     						</label>
     								<div class="col-md-6">
     									<select class="form-control form-control-css select2 select2-container select2-container--bootstrap select2-container--below" id="select_group_type" name="select_group_type">
	     									<option value="">--- กรุณาเลือก ---</option>
		  									<option value="1">   ประเภทสินค้า  </option>
		  									<option value="2"> ซัพพลายเออร์ </option>
     									</select>
     								</div>
     							</div>
     						</div>
     						<div class="col-md-8">
     							<div class="form-group group_product" >
     								<label class="control-label col-md-4" for="select_product_type"> ประเภทสินค้า 
		     							<span class="required"> * </span>
		     						</label>
     								<div class="col-md-8">
     									<select class="form-control select2 select2-container select2-container--bootstrap select2-container--below" id="select_product_type" name="select_product_type"></select>
     								</div>
     							</div>
     							<div class="form-group group_supplier" style="display : none;">
     								<label class="control-label col-md-3" for="select_supplier"> ซัพพลายเออร์
		     							<span class="required"> * </span>
		     						</label>
     									<div class="col-md-5">
	     									<input type="text" class="form-control form-control-css select2 select2-container select2-container--bootstrap select2-container--below" id="select_supplier_text" name="select_supplier_text" placeholder="กรุณาเลือกซัพพลายเออร์" value="" readonly>      							
     									</div>
	     								<button class="btn yellow" type="button" id="supplier_button" data-toggle="modal" href="#dialog_select_supplier">เลือก</button>
     							</div>
     						</div>
     					</div>
     					
     					<div class="row">
     						<div class="col-md-12">
		     					<div class="form-group">
		     						<label class="control-label col-md-2" for="description"> รายละเอียด
		     							<span class="required"> * </span>
		     						</label>
		     						<div class="col-md-10">
		     							<textarea rows="4" class="form-control portlet" id="description" name="description" placeholder="กรุณาใส่รายละเอียด"></textarea>
		     						</div>
		     					</div>
		     				</div>
		     			</div>
		     			
		     			<div class="section_btn" style="padding-top: 1%;">
	     					<div class="row">
	     						<div class="btn_save" style="text-align: center">
	     							<button type="button" class="btn green-sharp btn_process" id="btn_save_information"> <i class="fa fa-print" aria-hidden="true"></i> บันทึก </button>
	     						</div>
	     					</div>
	     				</div>
     					
						<div class="form-group">
	      					<div class="row">
	                        	<div class="col-md-offset-2 col-md-10">
	                        		<div id="error-choose-file" class="center-block" style="width:800px;display:none"></div>
	                        	</div>
	                        </div>
	      					<div class="row">
								<div class="col-md-12">                   
						            
						     		<div class="portlet light" id="image">
										<div class="portlet-title">
						                	<div class="caption font-green-sharp">
						                    	<i class="icon-docs"></i>
						                        <span class="caption-subject bold"> รูปภาพ </span>
						                        <span id="text-image" class="caption-subject" style="display:none"> (กรุณาบันทึกข้อมูล ก่อนทำการแนบรูป) </span>
						                    </div>         
						                </div>
						                <div class="portlet-body form" id="upload">
											
						                </div>
	                        			<span class="font-grey-salsa textInput"> ขนาดของภาพต้องไม่เกิน 300 KB  และต้องมีนามสุกลของไฟล์ภาพเป็น jpg, jpeg, png เท่านั้น</span>
						            </div>
						     	</div>
							</div>
	      					<div class="row">
								<div class="col-md-12">                   
						            
						     		<div class="portlet light" id="document">
										<div class="portlet-title">
						                	<div class="caption font-green-sharp">
						                    	<i class="icon-docs"></i>
						                        <span class="caption-subject bold"> เอกสาร </span>
						                        <span id="text-file" class="caption-subject" style="display:none"> (กรุณาบันทึกข้อมูล ก่อนทำการแนบไฟล์) </span>
						                    </div>         
						                </div>
						                <div class="portlet-body form" id="upload">
											
						                </div>
	                        		<span class="font-grey-salsa textInput"> ขนาดของเอกสารต้องไม่เกิน 2 MB และต้องมีนามสุกลของไฟล์เอกสารเป็น doc, docx, pdf เท่านั้น</span>
						            </div>
						     	</div>
							</div>
	      				</div>
	      				
	      				<div class="row row_accept" style="display : none;">
     						<div class="col-md-6">
     							<div class="form-group">
		     						<label class="control-label col-md-4" for="accept_by"> รับทราบโดย
		     						</label>
		     						<div class="col-md-7">
		      							<input type="text" readonly="true" class="form-control form-control-css select2 select2-container select2-container--bootstrap select2-container--below" id="accept_by" name="accept_by" value="">      							
		    						</div>    						
		     					</div>
     						</div>
     						<div class="col-md-6">
     							<div class="form-group">
		     						<label class="control-label col-md-4" for="accept_date"> วันที่รับทราบ
		     						</label>
		     						<div class="col-md-6">
		      							<input type="text" readonly="true" class="form-control form-control-css select2 select2-container select2-container--bootstrap select2-container--below" id="accept_date" name="accept_date" value="">      							
		    						</div>    						
		     					</div>
     						</div>
     					</div>
      						
      					<div class="row row_table_information_detail" style="display : none;">     					 
      						<div class="col-md-12"> 
		     					 <div class="form-group">
		     						<div class="col-md-8 col-md-offset-2">
		     							<table class="table table-striped table-bordered table-hover order-column table_information_detail" id="table_information_detail" >
		     								<thead>
		     									<tr>    									
		     									</tr>
		     									 <tr>
		     										<th style="text-align: center;"> ลำดับ </th>
		     										<th style="text-align: center;"> บริษัท </th>
		     										<th style="text-align: center;"> วันที่รับทราบ </th>
		     										<th style="text-align: center;"> รับทราบโดย </th>
		     									</tr> 
		     								</thead>
		     								<tbody>
		     								
		     								</tbody>
		     							</table>
		     						</div>
		     					</div>
		     					<hr> 
     					
      						</div>     					 
      					</div> 
     					
     					<div class="section_btn" style="padding-top: 1%;">
	     					<div class="row">
	     						<div class=" btn_accept" style="text-align: center">
	     							<button type="button" class="btn green-sharp btn_process" id="btn_accept_information"> <i class="fa fa-print" aria-hidden="true"></i> รับทราบข่าวสาร </button>
	     						</div>
	     						<div  style="text-align: center">
	     							<button type="button" class="btn blue-sharp btn_process" id="btn_send_information"> <i class="fa fa-print" aria-hidden="true" disabled="true"></i> แจ้ง Supplier </button>
	     						</div>
	     					</div>
	     				</div>
     										
     				</form>
     			</div>
     			
     		</div>
     	</div>
     </div>
     
    <%-- Dialog --%>
	<div id="dialog_select_supplier" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        
		    <div class="row">
				<div class="col-md-12">									        			
		        	<button type="button" class="close btn-lg" data-dismiss="modal" aria-label="Close">		        				
		        		<span aria-hidden="true">&times;</span>
		        	</button>
		        	<h4 class="modal-title">เลือกซัพพลายเออร์</h4>
				</div>	
			</div>
		           
	      </div>
	      <div class="modal-body">
	      	<div class="form-group" style="padding: 15px;">
	      		<div class="row">
					<div class="col">
						<select multiple="multiple" size="10" id="select_supplier" name="select_supplier" title="select_supplier">
						</select>
						<br>
						<div class="row">
							<div class="col-md-6 offset-md-6">
                  				<button type="button" id="btn_submit_supplier" class="btn green"> ยืนยัน </button>
						    </div>
						 </div>
					</div>
				</div>
	      	</div>
	      	                         
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
     
     
     <div class="row hide">
     	<div class="col-md-6">
     		<form action="${context}/information_form.jsp" method="post">
     			<div class="form-group">
     				<input id="informationDetail" name="informationDetail" readonly="readonly">
     			</div>
     		</form>
     	</div>
     	<div class="col-md-6">
     		<form action="${context}/information_supplier.jsp" method="post">
     			<div class="form-group">
     				<input id="informationSupplier" name="informationSupplier" readonly="readonly">
     			</div>
     		</form>
     	</div>
     </div>  
     
      <%-- Dialog --%>
     <script src="${context}/assets/js/page_informationform.js" type="text/javascript"></script> 
	     

</body>
</html>