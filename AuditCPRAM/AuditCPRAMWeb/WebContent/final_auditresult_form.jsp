<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
	<meta charset="utf-8" />
	
	 <link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
     <link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
     <link href="assets/css/fix_datatable.css" rel="stylesheet" type="text/css" />
     <link href="${contextPath}/assets/global/plugins/bootstrap-checkbox/css/bootstrap-checkbox.css" rel="stylesheet" type="text/css">
     <link href="${contextPath}/assets/global/plugins/bootstrap-fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
     <link href="${contextPath}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css">
     <link href="${contextPath}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.css" rel="stylesheet" type="text/css">
     
     
     <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="${contextPath}/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
     <script src="${contextPath}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
     <script src="${contextPath}/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.th.min.js" type="text/javascript"></script>
     <script src="${contextPath}/assets/global/plugins/bootstrap-fileinput/js/plugins/piexif.min.js" type="text/javascript"></script>
     <script src="${contextPath}/assets/global/plugins/bootstrap-fileinput/js/plugins/sortable.min.js" type="text/javascript"></script>
     <script src="${contextPath}/assets/global/plugins/bootstrap-fileinput/js/plugins/purify.min.js" type="text/javascript"></script>
     <script src="${contextPath}/assets/global/plugins/randexp.min.js" type="text/javascript"></script>
     <script src="${contextPath}/assets/global/plugins/jquery-print/jQuery.print.js" type="text/javascript"></script>
     <script src="${contextPath}/assets/global/plugins/printThis.js" type="text/javascript"></script>
     
     
     <style type="text/css">
     	/* #dialog_detail_car>.modal-dialog {
  			width: 100% !important;
  			height: 100% !important;
  			margin: 0 !important;
  			padding: 0 !important;
		}

		#dialog_detail_car>.modal-dialog>.modal-content {
  			height: auto;
  			min-height: 100% !important;
  			min-width: 100% !important;
  			border-radius: 0 !important;
		}  */
		.lcl_fade_oc.lcl_pre_show #lcl_overlay,
.lcl_fade_oc.lcl_pre_show #lcl_window,
.lcl_fade_oc.lcl_is_closing #lcl_overlay,
.lcl_fade_oc.lcl_is_closing #lcl_window {
	opacity: 0 !important;
}
.lcl_fade_oc.lcl_is_closing #lcl_overlay {
	-webkit-transition-delay: .15s !important; 
	transition-delay: .15s !important;
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
                 <a href="final_auditresult.jsp">สรุปผลการประเมิน</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span> รายละเอียดสรุปผลการประเมิน </span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
     <input class="hide" id="pass_param" value="${param.pass_param}">
     <div class="row" id="section-summary-final-audit-result">
     	<div class="col-md-12">
     		<div class="portlet light" id='portlet-detail-report-final'>
     			<div class="portlet-title">
     				<div class="caption font-green-sharp">
     					<i class="icon-speech"></i>
     					<span class="caption-subject bold uppercase"> รายละเอียดสรุปผลการประเมิน </span>
     				</div>
     				
     				<div class="actions">
     					<div class="btn-group">
	                         <button   class="btn sbold red-soft" style="display: none" id="see_car_detail"> รายละเอียดใบคาร์
	                         	<i class="fa fa-ticket"></i>
	                         </button>
	                     </div>
     					<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>    					
     				
     					<a class="btn btn-circle btn-icon-only btn-default font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">
			     			<i class="fa fa-angle-down fa-lg rotate-icon"></i>
			     		</a>
     				</div>
     				
     				<div class="row" style="padding-top: 10px;">
     					<div class="col-md-2 col-md-offset-1">
     						<!-- <label> </label> -->
     						<span class="caption-subject">สถานะสรุปผลการประเมิน</span>
     					</div>
     					<div class="col-md-3 label_status"></div>
     					<input class="hide" id="final_auditresult_status_id" />
     				</div>  				
     			</div>
     			
     			<div class="portlet-body form form-body-collapse">     				
     					
     				<div class="row">
     					<div class="col-md-2 col-md-offset-5">
     						<h4>CPRAM CO., Ltd</h4>     						
     					</div>
     					<div class="col-md-3 col-md-offset-2">
     						
     						<span>แผนการประเมินเลขที่ : </span>
     						<span id="checklistPlanNo"></span>
     					</div>
     				</div>
     				<div class="row">
     					<div class="col-md-offset-4 col-md-6">
     						<div class="col-md-11">
     							<h4>Quality Assurance Department</h4>
     						</div>     						
     					</div>
     				</div>
     				
     				
     				<div class="row" style="padding-top: 2%;">
     					<div class="col-md-12">
     						<table class="table table-bordered table-hover order-column table_managed">
     							<tbody>
     								<tr>
     									<td style="width:80%;">
     										<span style="font-size: 16px;font-weight: bold;">Supplier : </span>
     										<span style="font-size: 16px;" id="supplier_name"></span>   									
     									</td>
     									<td>
     										<span style="font-size: 16px;font-weight: bold;">Date : </span>
     										<span style="font-size: 16px;" id="date_val"></span>  
     									</td>
     								</tr>
     								<tr>
     									<td colspan="2">
     										<div class="row section-audit-type">     										
     										
     											<div class="col-md-offset-1 col-md-3">
     												<div class="form-sm-checkboxes readonly">
     													<div class="sm-checkbox-list">  
		                                       				<div class="checkbox">
		                                       					<label style="font-size: 16px;">
			                                       					<input class="audit_type" type="checkbox" id="audit_round" value="1" readonly="readonly">
			                                       					<span class="cr"><i class="cr-icon fa fa-check font-green"></i></span>
			                                       					<span>ตรวจประเมินประจำปี</span>
		                                       					</label>
		                                       				</div>
		                                    			</div>
     												</div>
     											</div>
     										
     											<div class="col-md-4">
     												<div class="form-sm-checkboxes readonly">
     													<div class="sm-checkbox-list">  
		                                       				<div class="checkbox">
		                                       					<label style="font-size: 16px;">
			                                       					<input class="audit_type" type="checkbox" id="audit_new_supplier" value="2">
			                                       					<span class="cr"><i class="cr-icon fa fa-check font-green"></i></span>
			                                       					<span>ตรวจประเมิน RM/Supplier รายใหม่</span>
		                                       					</label>
		                                       				</div>
		                                    			</div>
     												</div>
     											</div>
     											
     											<div class="col-md-3">
     												<div class="form-sm-checkboxes readonly">
     													<div class="sm-checkbox-list">  
		                                       				<div class="checkbox">
		                                       					<label style="font-size: 16px;">
			                                       					<input class="audit_type" type="checkbox" id="audit_observe" value="3">
			                                       					<span class="cr"><i class="cr-icon fa fa-check font-green"></i></span>
			                                       					<span>ตรวจติดตามปัญหา</span>
		                                       					</label>
		                                       				</div>
		                                    			</div>
     												</div>
     											</div>
     											
     										</div>
     									</td>
     								</tr>
     								
     								<tr>
     									<td colspan="2">
     										<span style="font-size: 16px;font-weight: bold;">สถานที่ประเมิน : </span>
     										<span style="font-size: 16px;" id="location_name"></span>  
     									</td>
     								</tr>
     								
     							</tbody>
     						</table>
     					</div>
     				</div>
     				
     				
     				<div class="row" style="padding-top: 1%;">
     					<div class="col-md-12">
     						<table class="table table-bordered table-hover order-column table_managed" id="table_car_result">
			     				<thead>
			     					<tr >
			     						<th style="text-align: center;">ปัญหา (Problem)</th>
			     						<th style="text-align: center;">Criteria</th>
			     						<th style="text-align: center;">ระดับ</th>
			     						<th style="text-align: center;">Corrective Action</th>
			     						<th style="text-align: center;">Complete Date</th>
			     						<th style="text-align: center;">Due Date</th>
			     						<th style="text-align: center;display: none;" class='section-detail-car'>  รายละเอียด</th>
			     						<th style="text-align: center;display: none;" class='section-evidence-solve-car'>การแก้ไข</th>
			     						<th style="text-align: center;display: none;" class='section-edit-problem'>แก้ไขปัญหา</th>
			     					</tr>
			     				</thead>
			     				<tbody>
			     					<!-- <tr>
			     						<td>1.พบการจัดเก็บสินค้าสำเร็จรูปและหมูซีกไว้ในพื้นที่เดียวกัน</td>
			     						<td style="text-align: center;" >GMP </br> 1.5</td>
			     						<td style="text-align: center;">Major</td>
			     						<td></td>
			     						<td></td>
			     					</tr>	  -->    						
			     				</tbody>
			     			</table>
     					</div>
     				</div>
     				
     				<div class="row">
     					<div class="col-md-1 col-md-offset-1">
     						<span style="font-weight: bold;">สรุปผล</span>
     					</div>
     					     					
     					<div class="col-md-2">
     						<div class="form-group form-group-sm">
     							<label class="control-label col-md-6" style="font-weight: bold;"> Critical:</label>
     							<div class="col-md-6">
     								<input type="text" class="form-control input-sm " id="critical_val" name="critical_val" value="" readonly="readonly">
     							</div>
     						</div>
     					</div>
     					
     					<div class="col-md-2">
     						<div class="form-group form-group-sm">
     							<label class="control-label col-md-6" style="font-weight: bold;"> Major:</label>
     							<div class="col-md-6">
     								<input type="text" class="form-control input-sm" id="major_val" name="major_val" value="" readonly="readonly">
     							</div>
     						</div>
     					</div>
     					
     					<div class="col-md-2">
     						<div class="form-group form-group-sm">
     							<label class="control-label col-md-6" style="font-weight: bold;"> Minor:</label>
     							<div class="col-md-6">
     								<input type="text" class="form-control input-sm" id="minor_val" name="minor_val" value="" readonly="readonly">
     							</div>
     						</div>
     					</div>
     					
     					<div class="col-md-2">
     						<div class="form-group form-group-sm">
     							<label class="control-label col-md-6" style="font-weight: bold;"> Observe:</label>
     							<div class="col-md-6">
     								<input type="text" class="form-control input-sm" id="observe_val" name="observe_val" value="" readonly="readonly">
     							</div>
     						</div>
     					</div>
     					
     				</div>
     				
     				<div class="row section-pass" style="padding-top: 1%;">
     					<div class="col-md-3 col-md-offset-1" style="margin-top: 10px;"> 
     						<span style="font-weight: bold;">ผลการตรวจประเมิน (Audit Result)</span>
     					</div>
     					
     					<div class="col-md-2">
     						<div class="form-sm-checkboxes readonly">
     							<div class="sm-checkbox-list">  
		                        	<div class="checkbox">
		                            	<label style="font-size: 16px;">
			                            	<input class="approve_result" type="checkbox" id="pass" value="Y">
			                                <span class="cr"><i class="cr-icon fa fa-check font-green"></i></span>
			                                <span>ผ่าน (Pass)</span>
		                                </label>
		                             </div>
		                        </div>
     						</div>
     					</div>
     					
     					<div class="col-md-2">
     						<div class="form-sm-checkboxes readonly">
     							<div class="sm-checkbox-list">  
		                        	<div class="checkbox">
		                            	<label style="font-size: 16px;">
			                            	<input class="approve_result" type="checkbox" id="not_pass" value="N">
			                                <span class="cr"><i class="cr-icon fa fa-check font-green"></i></span>
			                                <span>ไม่ผ่าน (Not Pass)</span>
		                                </label>
		                             </div>
		                        </div>
     						</div>
     					</div>
     					
     					<div class="col-md-2" style="margin-top: 10px;" id="section-show-grade">
     						<div class="form-group">
     							<label class="control-label col-md-6" style="font-weight: bold;"> เกรด :</label>
     							<div class="col-md-6">
     								<input type="text" class="form-control" id="grade" name="grade" value="" readonly="readonly">     								
     							</div>
     						</div>
     					</div>
     					
     					<div class="col-md-4" style="margin-top: 10px; display: none;" id="section-section-grade">
     						<div class="form-group">
     							<label class="control-label col-md-3" style="font-weight: bold;"> เกรด :</label>
     							<div class="col-md-6">
     								<select class="form-control select2 select2-container select2-container--bootstrap select2-container--below" id="grade_selector" name="grade_selector"></select>
     								<span class="font-red-mint">กรุณาเลือกเกรด เนื่องจากระบบไม่สามารถคำนวณเกรดได้</span>
     							</div>
     						</div>
     					</div>
     					
     				</div>
     				
     				
     				<div class="footer_section" style="padding-top: 5%;">
     					<div class="row">
     						<div class="col-md-12">
     							<span style="font-weight: bold;">ขอให้ทางผู้ส่งมอบตอบกลับการแก้ไข ภายใน </span>
     							<span id="accept_day" style="font-weight: bold;">7</span>
     							<span style="font-weight: bold;"> วันหลังการ Audit โดยแนบภาพปิดประเด็นและหลักฐาน ทาง Fax 02-9762265 หรือ admincpram@cpram.com</span>
     						</div>
     					</div>
     					
     					<div class="row" style="padding-top: 1%;">
     						<div class="col-md-5 col-md-offset-1">
     							<div class="row">
     								<div class="col-md-12">
     									<span style="font-weight: bold;">Auditor : </span>
     									<span id="auditor_list"></span>
     								</div>
     							</div>
     							
     							<div class="row" style="padding-top: 2%;">
     								<div class="col-md-2">
     									<span style="font-weight: bold;">Supplier:</span>     									 
     								</div>
     								<div class="col-md-4">
     									<img class="img-responsive" id="signature" name="signature">
     								</div>
     							</div>
     							
     						</div>
     						<div class="col-md-6">
     							<div class="row">
     								<div class="form-group">
			     						<label class="control-label col-md-4" style="font-weight: bold;"> วันที่ปิดคำขอแก้ไข :</label>
			     						<div class="col-md-4">
			     							<input type="text" class="form-control" id="dateApproval" name="dateApproval" value="" readonly="readonly">
			     						</div>
			     					</div>
     							</div>
     							
     							<div class="row">
     								<div class="col-md-12">
     									<span style="font-weight: bold;">ชื่อผู้ตรวจสอบ : </span>
     									<span id="approval"></span>
     								</div>
     							</div>
     							
     						</div>
     					</div>
     					
     				</div>
     				     				
     				
     				<div class="section_btn" style="padding-top: 1%;">
     					<div class="row">
     						<div id="bottom_btn" class="col-md-2 ">
     							<button type="button" class="btn purple-intense btn_process" id="btn_save_grade" style="display: none;" >ยืนยันการเลือกเกรด</button>
     							<button type="button" class="btn yellow-casablanca btn_process" id="btn_send_result_to_supplier" style="display: none;" >ส่งสรุปผลการประเมินให้กับซัพพลายเออร์</button>
     							<button type="button" class="btn green-steel btn_process" id="btn_accept_auditresult"  style="display: none;"> รับทราบสรุปผลการประเมิน   </button>
     							<button type="button" class="btn blue-steel btn_process" id="btn_confirm_car" style="display: none;" > บันทึกสรุปผลการประเมิน</button>
     							<button type="button" class="btn blue-steel btn_process" id="btn_approve_car" style="display: none;" > อนุมัติผลการประเมิน</button>
     						    
     						
     						</div>
     						<!--  <div id="edit_btn" class="col-md-2" style="display: flex;align-items: center;justify-content: center;">
     							<button type="button" class="btn blue-steel btn_process w-100" id="btn_edit_final" style="display: flex;"data-toggle="modal" data-target="#editModal" > แก้ไข</button>
     						</div>
     						-->
     						<!-- 
     						<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
							  <div class="modal-dialog" role="document">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title" id="exampleModalLabel">หมายเหตุ</h5>
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							          <span aria-hidden="true">&times;</span>
							        </button>
							      </div>
							      <div class="modal-body">
							        <textarea class="form-control" id="edit_field"></textarea>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
							        <button type="button" id="btn_edit_finalresult" class="btn btn-primary">ยืนยัน</button>
							      </div>
							    </div>
							  </div>
							</div>
							-->	     						
     					</div>
     					<br/>
     					<div class="row">
     						<div id="print_btn" class="col-md-offset-4">
     							<button type="button" class="btn green-sharp btn_process" id="btn_print_final_audit_result" style="display: none;"> <i class="fa fa-print" aria-hidden="true"></i> Print </button>
     							<button type="button" class="btn green-sharp btn_process" id="btn_log_final_audit_result" style="display: none;"> ประวัติการดำเนินการ </button>
     						</div>
     					</div>
     				</div>
     				
     			</div>
     		</div>
     	</div>
     </div>
     
     
     <%-- Dialog Add Checklist --%>
	<div id="dialog_detail_car" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static"  >	<!-- style=" z-index: 100000000 !important;" -->
     	<div class="modal-dialog modal-lg">
     		<div class="modal-content">    
     		 		
     			<div class="modal-header">     				
     				<div class="row">
     					<div class="col-md-9">
        					<h4>รายละเอียดใบคาร์</h4>
     					</div>
     					<div class="col-md-3">
			            	  
     						<button type="button" class="close" data-dismiss="modal">&times;</button>
     					</div>
     				</div>
        			
        			
      			</div>
      			
      			<div class="modal-body">
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-12">
      							<input class="form-control hide" id="detail-car-string-json" >
      						</div>
      					</div>
      				</div>
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-2 col-md-offset-1">
      							<label class="bold">เลขที่ใบคาร์ : </label>
      						</div>
      						<div class="col-md-8">
      							<label id="car_no"></label>
      						</div>
      					</div>
      				</div>
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-2 col-md-offset-1">      							
      							<label class="bold">วันที่เข้าตรวจ : </label>
      						</div>
      						<div class="col-md-8">
      							<label id="date_appoint"></label>
      						</div>
      					</div>
      				</div>      				
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-2 col-md-offset-1">
      							<label class="bold">ผู้ตรวจ : </label>
      						</div>
      						<div class="col-md-8">
      							<label id="auditor"></label>
      						</div>
      					</div>
      				</div>
      				
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-3 col-md-offset-1">
      							<label class="bold"> เกณฑ์การพิจารณา </label>
      						</div>
      					</div>
      					<div class="row">
      						<div class="col-md-10 col-md-offset-1">
      							<label id="questionOfCar"> </label>
      						</div>
      					</div>
      				</div>
      				
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-3 col-md-offset-1">
      							<label class="bold"> ปัญหาที่พบ </label>
      						</div>
      					</div>
      					<div class="row">
      						<div class="col-md-11 col-md-offset-1">
      							<label id="problemOfCar"> </label>
      						</div>
      					</div>
      				</div>
      				
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-3 col-md-offset-1">
      							<label class="bold"> หลักฐานประกอบการประเมิน </label>
      						</div>
      					</div>
      					<div class="row">
      						<div class="col-md-11 col-md-offset-1">
      							<div id="evidenceText">
      								
      							</div>
      						</div>
      					</div>
      					<div class="row" style="padding-top: 30px;">
      						<div class="col-md-6 col-md-offset-3">
      							<div id="carousel-evidence-section">
								 
								  <!-- comment -->
								  <!--	<ol class="carousel-indicators">
								  	<li data-target="#carousel-evidence" data-slide-to="0" class="active" style="display: none;"></li>
								  </ol>
								  
								  <div class="carousel-inner">
								  	<div class="item active" style="display: none;">
								  	</div>
								  </div>
								  
								  <a class="left carousel-control" href="#carousel-evidence" data-slide="prev">
								    <span class="glyphicon glyphicon-chevron-left"></span>
								  </a>
								  <a class="right carousel-control" href="#carousel-evidence" data-slide="next">
								    <span class="glyphicon glyphicon-chevron-right"></span>
								  </a>  -->
								   <!-- comment-->
								  
								</div>
      						</div>
      					</div>
      					<div class="row" style="padding-top: 10px;">
      						<div class="col-md-5 col-md-offset-4">
      							<label class="bold" id="lable_description_evidence"> หลักฐานประกอบการประเมินประเภทรูปภาพ</label>
      						</div>
      					</div>
      				</div>
      				
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-2 col-md-offset-9">
      							 <img class="img-responsive" id="signature_in_car_detail"> 
      						</div>
      					</div>
      					<div class="row">
      						<div class="col-md-3 col-md-offset-9">
      							<label>ลายเซ็นซัพพลายเออร์</label>
      						</div>
      					</div>
      				</div>
      			
      			</div>
     		</div>
     	</div>
     </div>
     
     <%-- Dialog Add Checklist --%>
	<div id="dialog_solve_detail_car" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static"  >	<!-- style=" z-index: 100000000 !important;" -->
     	<div class="modal-dialog modal-lg">
     		<div class="modal-content">    
     		 		
     			<div class="modal-header">     				
     				<div class="row">
     					<div class="col-md-9">
        					<h4>การแก้ไข</h4>
     					</div>
     					<div class="col-md-3">
			            	<!-- <button  class="btn green" id="btn_solve_car" > ส่งหลักฐานการแก้ไข   <i class="fa fa-folder"></i></button>   -->
     						<button type="button" class="close" data-dismiss="modal">&times;</button>
     					</div>
     				</div>
        			
        			
      			</div>
      			
      			<div class="modal-body">
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-12">
      							<input class="form-control hide" id="detail_car_json" >
      							<input class="form-control hide" id="solve_detail_car_json">
      						</div>
      					</div>
      				</div>
      				
      				
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-3 col-md-offset-1">
      							<label class="bold"> เกณฑ์การพิจารณา :  </label>
      						</div>
      					</div>
      					<div class="row">
      						<div class="col-md-10 col-md-offset-1">
      							<label id="questionOfCarDetail"> </label>
      						</div>
      					</div>
      				</div>
      				
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-3 col-md-offset-1">
      							<label class="bold"> ปัญหาที่พบ : </label>
      						</div>
      					</div>
      					<div class="row">
      						<div class="col-md-11 col-md-offset-1">
      							<label id="problemOfCarDetail"> </label>
      						</div>
      					</div>
      				</div>
      				
      				<div class="form-group">
      					<div class="row">
      						<label class="control-label col-md-1 col-md-offset-1 bold">สถานะ:</label>      						
      						<div class="col-md-10">
      							<label id="statusCarDetail"></label>
      						</div>
      					</div>      					
      				</div>
      				
      				<div class="form-group reason-section" style="display: none;">
      					<div class="row">
      						<div class="col-md-3 col-md-offset-1">
      							<label class="bold"> เหตุผล: </label>
      						</div>
      					</div>
      					<div class="row">
      						<div class="col-md-11 col-md-offset-1">
      							<label id="reason"> </label>
      						</div>
      					</div>
      				</div>
      				
      				<div class="form-group">
      					<div class="row">
      						<label class="control-label col-md-2 col-md-offset-1 bold" style="margin-right: -50px !important;"> Due Date :<span class="require_Asterisk">*</span></label> 
      						
      						<div class="col-md-4" >
								<div class="input-group date datepicker-dialog" data-provide="datepicker" id="due_date_input_section">
									<input type="text" class="form-control input-sm" readonly="readonly" id="due_date_dialog" name="due_date_dialog">
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</div>
								</div>
								<label id="due_date_text" style="display: none;"></label>
							</div>
      					</div>
      					<div class="row">
      						<label class="control-label col-md-8 col-md-offset-1" style="font-size: 12px;">   หมายเหตุ : Due Date เป็นวันที่ซัพพลายเออร์คาดว่าจะดำเนินการแก้ไขปัญหานี้ให้แล้วเสร็จ </label> 
      					</div>	
      					
      				</div>
      				
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-4 col-md-offset-4"> 
	      						<button class="btn green-seagreen" id="save_due_date_solve_car" style="margin-left : 40px;">บันทึก Due Date   <i class="fa fa-save"></i>  </button>
	      					</div>
      					</div>
      				</div>
      				
      				
      				<div class="form-group" style="display: none;" id="solve_evidence_portlet_section">
      					<div class="row">
      						<div class="col-md-12">
      							<div class="portlet light" id="portlet-solve-car-evidence">
      								<div class="portlet-title">
      									<div class="caption font-green-sharp">
						                    <span class="caption-subject bold uppercase"> การแก้ไข </span>
					             		</div>
					             		<div class="actions">
											
										</div> 
      								</div>
      								
      								<div class="portlet-body">
      									
      									<div class="row">
      										<div class="col-md-12">
      											<div class="form-group">
      												<div class="row">
      													<div class="col-md-4">
      														<label> วิธีการดำเนินการ:  </label><span class="require_Asterisk">*</span>
      													</div>
      												</div>
      												
    												<textarea class="form-control" id="solve_evidence_text" placeholder="กรุณาใส่ข้อความตัวอักษร" rows="3"></textarea> 
    												<input class="form-control hide" id="evidenceTextTypeId">   												
	    											<!-- <div class="row" style="padding-top: 10px;">
	    												<div class="col-md-offset-9 col-md-2">
	    													<button class="btn yellow" id="save_txt_solve_evidence" style="display: none;">บันทึกวิธีการดำเนินการ   <i class="fa fa-save"></i>  </button>
	    												</div>
	    											</div> -->
      											</div>
      										</div>
      									</div>
      									
      									
      									<div class="form-group">
      										<div class="row">
      											<div class="col-md-6">    
      												<div class="row">
      													<div class="col-md-4">
      														<label>หลักฐานปัญหา :  </label>  
      													</div>
      													<div class="col-md-4">
      														<button  class="btn green btn-sm" id="btn_solve_car" style="display: none;"> ส่งหลักฐานการแก้ไข   <i class="fa fa-folder"></i></button>
      													</div>
      												</div>  												
      												    
      																								
      											</div>
      											
      										</div>
      									</div>
      									
      									<div class="list-picture-section">		
      									</div>
      									<br>
      									<div class="list-document-section" style="display : none;">
      										<div class="row">
      											<div class="col-md-8">
      												<form role="form" id="evidence_document_preview_form" enctype="multipart/form-data">
						      							<input id="evidence_document_preview_supplier" type="file" name="evidence_document_preview_supplier[]" multiple data-preview-file-type="file/*" accept=".doc, .docx, .pdf">
						      						</form>
      											</div>
      										</div>
      									</div>
      							</div>
      						</div>
      					</div>
      					     					
      				</div>
      				
      				<div class="row">
      					<div class="col-md-4 col-md-offset-5"> 
      						<button class="btn yellow" id="save_txt_solve_evidence" style="display: none;">บันทึกวิธีการดำเนินการ   <i class="fa fa-save"></i>  </button>
      					</div>
      				</div>
      			
      			</div>
     		</div>
     	</div>
     </div>
     </div>
     
      <%-- Dialog Add Checklist --%>
	<div id="dialog_upload_solve_detail_car" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static" >
		<div class="modal-dialog modal-md">
     		<div class="modal-content">
     			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal">&times;</button>
        			<h4>แก้ไขใบคาร์</h4>
      			</div>
      			<div class="modal-body">
      				<!-- <div class="form-group">
      					<div class="row">
      						<label class="control-label col-sm-2">ข้อความ:</label>
	      					<div class="col-sm-10">
	      						<textarea class="form-control" id="evidence_text" placeholder="กรุณาใส่ข้อความตัวอักษร" rows="5"></textarea>
	    					</div>
      					</div>      					
      				</div> -->
      				<div class="form-group">
      					<div class="row">
                        	<div class="col-md-offset-2 col-md-10">
                        		<div id="error-choose-file" class="center-block" style="width:800px;display:none"></div>
                        	</div>
                        </div>
      					<div class="row">
      						<label class="control-label col-sm-2">รูปภาพ:</label>
	      					<div class="col-sm-10">
	      						<form role="form" id="evidence_form" enctype="multipart/form-data">
	      							<input id="evidence_supplier" type="file" name="evidence_supplier[]" multiple data-preview-file-type="image/*" accept=".jpg, .jpeg, .png">
	      						</form>
                        		<span class="font-grey-salsa"> ขนาดของภาพต้องไม่เกิน 300 KB  และต้องมีนามสุกลของไฟล์ภาพเป็น jpg, jpeg, png เท่านั้น</span>
	    					</div>
      					</div>
      					<br><br>
						<div class="row">
      						<label class="control-label col-sm-2">เอกสาร:</label>
	      					<div class="col-sm-10">
	      						<form role="form" id="evidence_document_form" enctype="multipart/form-data">
	      							<input id="evidence_document_supplier" type="file" name="evidence_document_supplier[]" multiple data-preview-file-type="file/*" accept=".doc, .docx, .pdf">
	      						</form>
                        		<span class="font-grey-salsa"> ขนาดของเอกสารต้องไม่เกิน 2 MB และต้องมีนามสุกลของไฟล์เอกสารเป็น doc, docx, pdf เท่านั้น</span>
	    					</div>
      					</div>     					
      				</div>
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-offset-7 col-md-2">
      							<button type="button" class="btn green-meadow" id="btn_edit_car" > ส่งหลักฐานยืนยันการแก้ไขปัญหา  <i class="fa fa-cloud-upload fa-1x"></i></button>
      						</div>
      						
      					</div>
      				</div>
      				
      			</div>
     		</div>
     	</div>
	</div>
	
	<div id="dialog_edit_problem" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static" >
		<div class="modal-dialog modal-md">
     		<div class="modal-content">
     			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal">&times;</button>
        			<h4>แก้ไขปัญหา</h4>
      			</div>
      			<div class="modal-body">
      				<div class="form-group hide">
      					<div class="row">
      						<input class="form-control hide" id="detail_edit_problem" >
      					</div>
      				</div>
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-2 col-md-offset-1">
      							<label class="bold">เลขที่ใบคาร์ : </label>
      						</div>
      						<div class="col-md-8">
      							<label id="car_no_editProblem"></label>
      						</div>
      					</div>
      				</div>
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-2 col-md-offset-1">      							
      							<label class="bold">วันที่เข้าตรวจ : </label>
      						</div>
      						<div class="col-md-8">
      							<label id="date_appoint_editProblem"></label>
      						</div>
      					</div>
      				</div>      				
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-2 col-md-offset-1">
      							<label class="bold">ผู้ตรวจ : </label>
      						</div>
      						<div class="col-md-8">
      							<label id="auditor_editProblem"></label>
      						</div>
      					</div>
      				</div>
      				
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-3 col-md-offset-1">
      							<label class="bold"> เกณฑ์การพิจารณา </label>
      						</div>
      					</div>
      					<div class="row">
      						<div class="col-md-10 col-md-offset-1">
      							<label id="questionOfCar_editProblem"> </label>
      						</div>
      					</div>
      				</div>
      				
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-3 col-md-offset-1">
      							<label class="bold"> ปัญหาที่พบ </label>
      						</div>
      					</div>
      					<div class="row">
      						<div class="col-md-11 col-md-offset-1">
      							<textarea class="form-control" id="problemOfCar_editProblem" rows="5"></textarea>
      						</div>
      					</div>
      				</div>
      				<div class="form-group">
      					<div class="row">
      						<div class="col-md-offset-7 col-md-2">
      							<button type="button" class="btn green-meadow" id="btn_save_edit_problem" > ยืนยันการแก้ไขปัญหา  </button>
      						</div>
      						
      					</div>
      				</div>
      			</div>
     		</div>
     	</div>
	</div>
	
	<%-- Dialog Log --%>
	<div id="dialog_final_audit_log" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static"  >	<!-- style=" z-index: 100000000 !important;" -->
     	<div class="modal-dialog modal-md">
     		<div class="modal-content">
     			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal">&times;</button>
        			<h4>ประวัติการดำเนินการ</h4>
      			</div>
      			<div class="modal-body">
      				<table class="table table-bordered table-hover order-column table_managed" id="table_log_result">
			     		<thead>
			     			<tr >
			     				<th style="text-align: center;">ลำดับ</th>
			     				<th style="text-align: center;">การดำเนินการ</th>
			     				<th style="text-align: center;">ผู้ดำเนินการ</th>
			     				<th style="text-align: center;">วัน เวลา ที่ดำเนินการ</th>
			     			</tr>
			     		</thead>
			     		<tbody>
			     		</tbody>
			     	</table>      				
      			</div>
     		</div>
     	</div>
     </div>
    
    <form action="car_form.jsp" method="post">
		<input class="form-control hide" id="pass_param_car" name="pass_param_car">
	</form>

    <script src="${contextPath}/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/global/plugins/jquery-file-upload/js/cors/jquery.postmessage-transport.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js" type="text/javascript"></script>
	<script src="${contextPath}/assets/js/page_finalauditresultform.js" type="text/javascript" ></script>
</body>
</html>