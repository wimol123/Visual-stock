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
     <link href="${context}/assets/global/plugins/bootstrap-checkbox/css/bootstrap-checkbox.css" rel="stylesheet" type="text/css">
     
     <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
     <script src="assets/global/scripts/dotdotdot.js" type="text/javascript"></script>
	
	<style type="text/css">
		.Editor-editor{
   	 		height: 200px !important;
   	 	}
   	 	/* .content_checklist{
   	 		text-overflow: ellipsis;
  			overflow: hidden;
  			width: 90%;
  			height: 1.2em; 
  			/* white-space: nowrap; */
   	 	} */
   	 	
   	 	.info-button {cursor: pointer;}
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
                 <a href="checklist_plan.jsp">แผนการประเมิน</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span> รายละเอียดแผนการประเมิน </span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
     
     <input class="hide" id="checklist_plan_id_receive" name="checklist_plan_id_receive" value="${param.checklistPlanDetail}"/>
     
     <div class="row">
     	<div class="col-md-12">
     		<div class="portlet light" id='portlet-detail-checklist-plan'>
     			<div class="portlet-title">
     				<div class="caption font-green-sharp">     					
     					<i class="icon-speech"></i>
     					<span class="caption-subject bold uppercase"> รายละเอียดแผนการประเมิน </span>     					
     				</div>
     				
     				
     				
     				<div class="actions">
     					<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
     					<!-- <a class = "btn" href="javascript:;" id="submit_btn">Click Me</a> -->
     				
     					<a class="btn btn-circle btn-icon-only btn-default font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">
			     			<i class="fa fa-angle-down fa-lg rotate-icon"></i>
			     		</a>
     				</div>
     				
     				<div class="row" style="padding-top: 10px;">
     					<div class="col-md-2 col-md-offset-2">
     						<!-- <label> </label> -->
     						<span class="caption-subject">สถานะแผนการประเมิน : </span>
     					</div>
     					<div class="col-md-3 label_status"></div>
     				</div>
     				     				
     			</div>
     			
     			<div class="portlet-body form form-body-collapse">
     				<form role="form" class="form-horizontal" id="form-detail-checklist-plan">
     				
     					<div class="row">
     						<div class="col-md-6">
     							<div class="form-group">
		     						<label class="control-label col-md-4"> หัวข้อแผนการประเมิน 
		     							<span class="required"> * </span>
		     						</label>
		     						<div class="col-md-8">
		      							<input type="text" class="form-control" id="checklistplan_title" name="checklistplan_title" placeholder="กรุณาใส่หัวข้อแผนการประเมิน" value="" readonly="readonly">      							
		    						</div>    						
		     					</div>
     						</div>
     						
     						<div class="col-md-6 hide">
     							<div class="form-group">
     								<label class="control-label col-md-4"> หมายเลขแผนการประเมิน </label>
     								<div class="col-md-8">
		      							<input type="text" class="form-control" id="checklistplan_id" name="checklistplan_id" placeholder="กรุณาใส่หมายเลขเช็คลิสต์ " readonly="readonly">      							
		    						</div>   
     							</div>
     						</div>     		
     						<div class="col-md-6">
     							<div class="form-group">
     								<label class="control-label col-md-4"> เลขที่แผนการประเมิน </label>
     								<div class="col-md-8">
		      							<input type="text" class="form-control" id="checklistplan_number" name="checklistplan_number" readonly="readonly">      							
		    						</div>   
     							</div>
     						</div>   				
     					</div>
     					
     					<div class="row">
     						<div class="col-md-6">
     							<div class="form-group">
     								<label class="control-label col-md-4"> ประเภทสินค้า 
		     							<span class="required"> * </span>
		     						</label>
     								<div class="col-md-8">
     									<input type="text" class="form-control" id="productType_name" name="productType_name" placeholder="กรุณาใส่ขอบข่ายการตรวจประเมิน" value="" readonly="readonly">
     									<input type="text" class="form-control hide" id="productType_id" name="productType_id" placeholder="กรุณาใส่ขอบข่ายการตรวจประเมิน" value="" readonly="readonly">
     								</div>
     							</div>
     						</div>
     						<div class="col-md-6">
     							<div class="form-group">
		     						<label class="control-label col-md-4"> ขอบข่ายการตรวจประเมิน 
		     							<span class="required"> * </span>
		     						</label>
		     						<div class="col-md-8">
		      							<input type="text" class="form-control" id="scope_checklistplan" name="scope_checklistplan" placeholder="กรุณาใส่ขอบข่ายการตรวจประเมิน" value="" readonly="readonly">      							
		    						</div>    					
		     					</div>
     						</div>
     					</div>
     					
     					<div class="row">
     						<div class="col-md-6">
     							<div class="form-group">
     								<label class="control-label col-md-4"> ชื่อบริษัท 
		     							<span class="required"> * </span>
		     						</label>
     								<div class="col-md-8">
     									<input type="text" class="form-control" id="supplier_company" name="supplier_company" placeholder="กรุณาใส่ชื่อบริษัท" value="" readonly="readonly">
     									<input type="text" class="form-control hide" id="supplier_id" name="supplier_id" value="" readonly="readonly">
     								</div>
     							</div>
     						</div>
     						<div class="col-md-6">
     							<div class="form-group">
		     						<label class="control-label col-md-4"> สถานที่ตั้ง 
		     							<span class="required"> * </span>
		     						</label>
		     						<div class="col-md-8">
		      							<input type="text" class="form-control" id="location_name" name="location_name" value="" readonly="readonly">
		      							<input type="text" class="form-control hide" id="location_id" name="location_id" value="" readonly="readonly">          							
		    						</div>    					
		     					</div>
     						</div>
     					</div>
     					
     					<div class="row">
     						<div class="col-md-12">
     							<div class="form-group">
		     						<label class="control-label col-md-2"> ที่อยู่ 
		     							<span class="required"> * </span>
		     						</label>
		     						<div class="col-md-6">
		      							<input type="text" class="form-control" id="address" name="address" value="" readonly="readonly">
		      							<input type="text" class="form-control hide" id="address_id" name="address_id" value="" readonly="readonly">          							
		    						</div>    					
		     					</div>
     						</div>
     					</div>
     					
     					<div class="row hide">     						     						
     						<div class="col-md-1">
     							<input type="text" class="form-control" id="sub_district_id" name="sub_district_id" value="" readonly="readonly"> 
     						</div>
     						
     						<div class="col-md-1">
     							<input type="text" class="form-control" id="district_id" name="district_id" value="" readonly="readonly"> 
     						</div>
     						
     						<div class="col-md-1">
     							<input type="text" class="form-control" id="province_id" name="province_id" value="" readonly="readonly"> 
     						</div>     						
     					</div>
     					
     					<div class="row">
     						<div class="col-md-6">
     							<div class="form-group">
     								<label class="control-label col-md-4"> วันที่เข้าตรวจ 
		     							<span class="required"> * </span>
		     						</label>
     								<div class="col-md-8">
     									<input type="text" class="form-control" id="plan_date" name="plan_date" value="" readonly="readonly">
     								</div>
     							</div>
     						</div>
     						<div class="col-md-6">
     							<div class="form-group">
		     						<label class="control-label col-md-4"> เวลาเข้าตรวจ 
		     							<span class="required"> * </span>
		     						</label>
		     						<div class="col-md-8">
		      							<input type="text" class="form-control" id="plan_time" name="plan_time" value="" readonly="readonly">       							
		    						</div>    					
		     					</div>
     						</div>
     					</div>
     					
     					
     					
     					<div class="row">
     						<div class="col-md-12">
     							<div class="form-group">
		     						<label class="control-label col-md-2" for="description"> คำอธิบาย
		     							<!-- <span class="required"> * </span> -->
		     						</label>
		     						<div class="col-md-10">
		     							<textarea rows="2" class="form-control" id="description" name="description" readonly="readonly"> </textarea>
		     						</div>
		     					</div>  
     						</div>     						  					
     					</div>
     					     					
     					<div class="row">
     						<div class="col-md-12">
     							<div class="form-group">
		     						<label class="control-label col-md-2" for="Criteria"> เกณฑ์การให้คะแนน
		     							<span class="required"> * </span>
		     						</label>
		     						<div class="col-md-10">
		     							<textarea rows="2" class="form-control txtEditor" id="Criteria" ></textarea>
		     						</div> 
		     						<div class="col-md-offset-2">
		     							<input type="text" class="form-control input-large" style="visibility: hidden!Important; height:0px; width:0px;" id="Criteria_tmp" name="Criteria_tmp"/>  
		     						</div>     		
		     					</div>
     						</div>
     					</div>
     					<hr>
     					
     					<div class="row section-approve-condition">     					
     						<div class="col-md-12">     								     					
		     					<div class="form-group">
		     						<div class="col-md-8 col-md-offset-2">
		     							<table class="table table-striped table-bordered table-hover order-column table_managed table_condition" id="table_condition">
		     								<thead>
		     									<tr>    									
		     									</tr>
		     								</thead>
		     							</table>
		     						</div>
		     					</div>
		     					<hr>     						
     						</div>     					
     					</div>
     					
     					
     					
     				</form>     			
     			</div>
     			
     		</div>
     	</div>
     </div>



	<div class="row">
     	<div class="col-md-12">
     		<div class="portlet light" id='portlet-audit-result'>
     			<div class="portlet-title">
     				<div class="caption font-green-sharp">
     					<i class="icon-book-open"></i>
     					<span class="caption-subject bold uppercase"> ผลการตรวจ </span>
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
     				     				
     				<!-- <div class="row">
     					<div class="col-md-12">
     						<table class="table table-bordered table-hover order-column table_managed" >
     							<thead>
     								<tr >
     								<th width="60%" style="text-align: center;">ข้อตรวจ</th>
     								<th width="10%" style="text-align: center;">Auditor A</th>
     								<th width="10%" style="text-align: center;">Auditor B</th>
     								</tr>
     								
     							</thead>
     							<tbody>
     								<tr class="bg-default">
     									<td colspan="3">
     									<div class="row">
     										<div class="col-md-12">
     											<div class="form-inline">
     												<div class="form-group">
     													<div class="content_checklist">I. GMP : GMP System </div>
     													<span class="evalPlanId">11</span>
     												</div>
     												&nbsp;&nbsp;
     												<div class="form-group"> 
     													data-content="Some content inside the popover"
     													<div class="info-button" data-toggle="popover" title="Note" ><i class="fa fa-sticky-note-o" style="font-size: 16px;" ></i></div>
     													
     													<div class="info-button" data-toggle="popover" title="Note" ><i class="fa fa-sticky-note-o" style="font-size: 16px;" ></i></div>
     												</div>
     											</div>
     											
     																					
     										</div>
     									</div>
     									</td>
     								</tr>
     								<tr class="bg-grey-steel">
     									<td colspan="3">
     										<div class="row">
     											<div class="col-md-12" style="padding-left: 50px;">
     												<div class="content_checklist">สภาวะแวดล้อมภายนอก : มาตรฐานสภาวะแวดล้อมภายนอก</div>
     											</div>
     										</div>
     									</td>
     								</tr>
     								<tr class="bg-grey-cararra">
     									<td colspan="3">
     										<div class="row">
     											<div class="col-md-12" style="padding-left: 80px;">
     												<div class="content_checklist">1.1 ที่ตั้ง : ที่ตั้ง : ทำเลต้องตั้งอยู่ในบริเวณที่ทำให้สามารถผลิต ผลิตภัณฆ์ที่ปลอดภัยและสอดคล้องกับกฏหมาย และได้รับการบำรุงรักษาเพื่อป้องกันการปนเปื้อน</div>
     												
     											</div>
     										</div>
     									</td>
     								</tr>
     								<tr>
     									
     									<td>
     										<div class="row">
     											<div class="col-md-12" style="padding-left: 100px;">
     												<table width="100%">
     													<tr>
     														<td class="content_checklist">1.1.1 ต้องพิจารณาถึงกิจกรรมต่าง ๆ และสภาพแวดล้อมในบริเวณปฏิบัติงานซึ่งอาจเกิดผลกระทบในทางเสียหาย และต้องกำหนดการป้องกันการปนเปื้อนในสถานที่ปฏิบัติงานต้องทบทวนอยู่เสมอเพื่อให้มั่นใจว่าคงประสิทธิภาพ</td>
     														
     													</tr>
     												</table>
     												
     												   												
     											</div>
     										</div>
     									</td>
     									<td style="text-align: center;">
     										<div class="form-sm-checkboxes">
     											<div class="sm-checkbox-list">  
		                                       		<div class="checkbox">
		                                       			<label style="font-size: 16px;">
		                                       				<input type="checkbox" class="answerId" value="" checked>
		                                       				<span class="cr"><i class="cr-icon fa fa-check font-green"></i></span>
		                                       				<span class="answerName">Critical</span>
		                                       			</label>
		                                       		</div>
		                                    	</div>
     										</div>
     										
     									</td>
     									<td style="text-align: center;">Minor</td>
     								</tr>
     							</tbody>
     							
     						</table>
     					</div>
     				</div> -->
     				<div class="row">
     					<div class="col-md-12">
     					
     						<table class="table table-bordered table-hover order-column table_managed" id="table_audit_result">
     							<thead>
     								<!-- <tr >
     								<th width="60%" style="text-align: center;">ข้อตรวจ</th>
     								<th width="10%" style="text-align: center;">Auditor A</th>
     								<th width="10%" style="text-align: center;">Auditor B</th>
     								</tr> -->
     								
     							</thead>
     							<tbody>
     								
     								<!-- <tr class="bg-default">
     									<td colspan="3">
     									<div class="row">
     										<div class="col-md-12">
     											<div class="content_checklist">I. GMP : GMP System</div>     											
     										</div>
     									</div>
     									</td>
     								</tr>
     								<tr class="bg-grey-steel">
     									<td colspan="3">
     										<div class="row">
     											<div class="col-md-12" style="padding-left: 50px;">
     												<div class="content_checklist">สภาวะแวดล้อมภายนอก : มาตรฐานสภาวะแวดล้อมภายนอก</div>
     											</div>
     										</div>
     									</td>
     								</tr>
     								<tr class="bg-grey-cararra">
     									<td colspan="3">
     										<div class="row">
     											<div class="col-md-12" style="padding-left: 80px;">
     												<div class="content_checklist">1.1 ที่ตั้ง : ที่ตั้ง : ทำเลต้องตั้งอยู่ในบริเวณที่ทำให้สามารถผลิต ผลิตภัณฆ์ที่ปลอดภัยและสอดคล้องกับกฏหมาย และได้รับการบำรุงรักษาเพื่อป้องกันการปนเปื้อน</div>
     												
     											</div>
     										</div>
     									</td>
     								</tr>
     								<tr>
     									<td>
     										<div class="row">
     											<div class="col-md-12" style="padding-left: 100px;">
     												<table width="100%">
     													<tr>
     														<td class="content_checklist">1.1.1 ต้องพิจารณาถึงกิจกรรมต่าง ๆ และสภาพแวดล้อมในบริเวณปฏิบัติงานซึ่งอาจเกิดผลกระทบในทางเสียหาย และต้องกำหนดการป้องกันการปนเปื้อนในสถานที่ปฏิบัติงานต้องทบทวนอยู่เสมอเพื่อให้มั่นใจว่าคงประสิทธิภาพ</td>
     														<td width="5%">
     															<div><i class="fa fa-info-circle" style="font-size: 16px;" aria-hidden="true"></i></div>  
     														</td>
     													</tr>
     												</table>
     												
     												   												
     											</div>
     										</div>
     									</td>
     									<td style="text-align: center;">Critical</td>
     									<td style="text-align: center;">Minor</td>
     								</tr>
     								<tr>
     									<td>
     										<div class="row">
     											<div class="col-md-12" style="padding-left: 100px;">
     												<div class="content_checklist">1.1.2 อาณาเขตบริเวณทำเลที่ตั้งต้องกำหนดอย่างชัดเจน</div>
     											</div>
     										</div>
     									</td>
     									<td style="text-align: center;">Major</td>
     									<td style="text-align: center;">Major</td>
     								</tr>
     								<tr class="bg-grey-cararra">
     									<td colspan="3">
     										<div class="row">
     											<div class="col-md-12" style="padding-left: 80px;">
     												<div class="content_checklist">1.2 อาณาเขต ขอบเขตและอาณาบริเวณ : ทุกบริเวณปฏิบัติงานต้องสมบูรณ์และคงสภาพให้เป็นไปตามมาตรฐานที่เหมาะสม</div>
     											</div>
     										</div>
     									</td>
     								</tr>
     								<tr>
     									<td>
     										<div class="row">
     											<div class="col-md-12" style="padding-left: 100px;">
     												<div class="content_checklist">1.2.1 บริเวณภายนอกต้องดูแลให้อยู่ในสภาพเรียบร้อย หากด้านนอกอาคารมีการปลูกหญ้าหรือต้นไม้ ต้องได้รับการดูแลให้อยู่ในสภาพดี</div>
     											</div>
     										</div>
     									</td>
     									<td style="text-align: center;">Minor</td>
     									<td style="text-align: center;">Critical</td>
     								</tr>
     								<tr>
     									<td>
     										<div class="row">
     											<div class="col-md-12" style="padding-left: 100px;">
     												<div class="content_checklist">1.2.2 พื้นที่รอบนอกอาคารที่ต้องใช้สำหรับการผลิตหรือจัดเก็บผลิตภัณฑ์ต้องสะอาดไม่มีสิ่งกีดขวาง ถ้าเป็นผนังติดกัน ต้องมีวิธีการป้องกันผลิตภัณฑ์และความสะอาดในระดับที่เหมาะสม</div>
     											</div>
     										</div>
     									</td>
     									<td style="text-align: center;">Minor</td>
     									<td style="text-align: center;">Critical</td>
     								</tr>  -->
     							</tbody>
     						</table>
     					</div>
     				</div>
     			
     			</div>
     			
     		</div>
     	</div>
     </div>
     
     
     	<%-- Dialog evidence media --%>
	<div id="dialog_evidence_media" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static"  >	<!-- style=" z-index: 100000000 !important;" -->
     	<div class="modal-dialog modal-md">
     		<div class="modal-content">    
     		 		
     			<div class="modal-header">     				
     				<div class="row">
     					<div class="col-md-9">
        					<h4 class="header-txt">หลักฐานการเข้าตรวจประเมิน</h4>
     					</div>
     					<div class="col-md-3">
			            	<!-- <button  class="btn green" id="btn_solve_car" > ส่งหลักฐานการแก้ไข   <i class="fa fa-folder"></i></button>   -->
     						<button type="button" class="close" data-dismiss="modal">&times;</button>
     					</div>
     				</div>
        			
        			
      			</div>
      			
      			<div class="modal-body">
      									
      				<div class="row">
      					<div class="col-md-12">
      						<div class="form-group">
      							<label>  รูปภาพ :  </label>      												
      						</div>
      					</div>
      				</div>
      									
      				<div class="list-picture-section">		
      				</div>
      				      				      				
      				
     		</div>
     	</div>
     </div>
     </div>
     
     
     

	<form action="car_form.jsp" method="post" id="form_pass_param" class="hide">
		<input class="form-control" name="pass_param_car" id="pass_param_car">
	</form>

	<!-- BEGIN QUICK NAV -->
        <nav class="quick-nav" style="display:none;">
            <a class="quick-nav-trigger" href="#0">
                <span aria-hidden="true"></span>
            </a>
            <ul>
                <li>
                    <a href="javascript:;" class="active" id="save-result_checklistPlan">
                        <span style="font-size: 16px;">ยืนยันผลการเข้าตรวจ</span>
                        <i class="fa fa-save fa-5x" style="font-size: 25px;"></i>
                    </a>
                    <a href="javascript:;" class="" id="cancel-checklist-plan">
                        <span style="font-size: 16px;">ยกเลิกแผนการประเมินนี้</span>
                        <i class="fa fa-ban fa-5x" style="font-size: 25px;"></i>
                    </a>
                    <a href="javascript:;" class="" id="detail-car" style="display: none;">
                        <span style="font-size: 16px;">รายละเอียดใบคาร์</span>
                        <i class="fa fa-ticket fa-5x" style="font-size: 25px;"></i>
                    </a>
                    <a href="javascript:;" class="" id="final_audit_result" style="display: none;">
                        <span style="font-size: 16px;">สรุปผลการเข้าตรวจ</span>
                        <i class="icon-screen-desktop" style="font-size: 20px;"></i>
                    </a>
                </li>               
            </ul>
            <span aria-hidden="true" class="quick-nav-bg"></span>
        </nav>
        <div class="quick-nav-overlay"></div>
        <!-- END QUICK NAV -->
        
    <form action="final_auditresult_form.jsp" method="post" id="pass_form_finalauditresult">
    	<input type="text" class="hide" id="pass_param" name="pass_param">
    </form>
	<script src="${context}/assets/js/page_checklistplanform.js?v=20240526" type="text/javascript" ></script>
</body>
</html>