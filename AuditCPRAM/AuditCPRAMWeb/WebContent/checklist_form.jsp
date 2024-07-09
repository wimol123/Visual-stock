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
     
     <script src="${context}/assets/global/plugins/jquery.min.js" type="text/javascript"></script>  
     <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.th.min.js" type="text/javascript"></script>
   	 <script src="${context}/assets/global/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js" type="text/javascript"></script>
   	 <script src="${context}/assets/global/plugins/select2/js/select2.min.js" type="text/javascript" ></script>
   	 
   	 
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
                 <a href="checklist.jsp">เช็คลิสต์ฟอร์ม Audit Supplier (คิดเกรด)</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span> รายละเอียดเช็คลิสต์ฟอร์ม Audit Supplier (คิดเกรด) </span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
     
     <input class="hide" id="checklist_id_receive" name="checklist_id_receive" value="${param.checklistId}"/>
     
     <div class="row">
     	<div class="col-md-12">
     		<!-- BEGIN EXAMPLE TABLE PORTLET-->
     		<div class="portlet light" id='portlet-detail-checklist'>
     			<div class="portlet-title">
     				<div class="caption font-green-sharp">
             			<i class="icon-speech"></i>
	                    <span class="caption-subject bold uppercase"> รายละเอียดเช็คลิสต์ฟอร์ม Audit Supplier (คิดเกรด) </span>
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
     				<form role="form" class="form-horizontal" id="form-detail-checklist">
     					<div class="row">
     						<div class="col-md-6">
     							<div class="form-group">
		     						<label class="control-label col-md-4" for="checklist_title"> ชื่อเช็คลิสต์ 
		     							<span class="required"> * </span>
		     						</label>
		     						<div class="col-md-8">
		      							<input type="text" class="form-control" id="checklist_title" name="checklist_title" placeholder="กรุณาใส่ชื่อเช็คลิสต์" value="">      							
		    						</div>    						
		     					</div>
     						</div>
     						
     						<div class="col-md-6 hide">
     							<div class="form-group">
     								<label class="control-label col-md-4" for="checklist_id"> หมายเลขเช็คลิสต์ </label>
     								<div class="col-md-8">
		      							<input type="text" class="form-control" id="checklist_id" name="checklist_id" placeholder="กรุณาใส่หมายเลขเช็คลิสต์ ">      							
		    						</div>   
     							</div>
     						</div>
     						
     					</div>
     					
     					
     					<div class="row">
     						<div class="col-md-6">
     							<div class="form-group">
     								<label class="control-label col-md-4" for="select_product_type"> ประเภทสินค้า 
		     							<span class="required"> * </span>
		     						</label>
     								<div class="col-md-8">
     									<select class="form-control select2 select2-container select2-container--bootstrap select2-container--below" id="select_product_type" name="select_product_type"  ></select>
     								</div>
     							</div>
     						</div>
     						<div class="col-md-6">
     							<div class="form-group">
		     						<label class="control-label col-md-4" for="scope_checklist"> ขอบข่ายการตรวจประเมิน 
		     							<span class="required"> * </span>
		     						</label>
		     						<div class="col-md-8">
		      							<input type="text" class="form-control" id="scope_checklist" name="scope_checklist" placeholder="กรุณาใส่ขอบข่ายการตรวจประเมิน" value="">      							
		    						</div>    					
		     					</div>
     						</div>
     					</div>
     					
     					<div class="row">     
     						<div class="col-md-6">
     							<div class="form-group">     						
		     						<label class="control-label col-md-4" > Effective Date 
		     							<span class="required"> * </span>
		     						</label>
		     						<div class="col-md-8">
		     							<div class="input-group date datepicker-effective" data-provide="datepicker">
    										<input type="text" class="form-control" readonly="readonly" id="effective_date" name="effective_date">
    										<div class="input-group-addon">
        										<span class="glyphicon glyphicon-calendar"></span>
    										</div>
										</div>
		     							<!-- <input type="text" class="form-control" id="date_audit" name="date_audit" placeholder="กรุณาใส่วันที่ตรวจประเมิน">     	 -->						
		     						</div>
		     					</div>
     						</div>						
     						<div class="col-md-6">
     							<div class="form-group">     						
		     						<label class="control-label col-md-4" > Expire Date 
		     							<span class="required"> * </span>
		     						</label>
		     						<div class="col-md-8">
		     							<div class="input-group date datepicker-expire" data-provide="datepicker">
    										<input type="text" class="form-control" readonly="readonly" id="expire_date" name="expire_date">
    										<div class="input-group-addon">
        										<span class="glyphicon glyphicon-calendar"></span>
    										</div>
										</div>
		     							<input type="text" class="form-control hide" id="date_expire_history" name="date_expire_history" readonly="readonly">				
		     						</div>
		     					</div>
     						</div>
     						
     					</div>
     								     					
     					
     					<div class="form-group">
     						<label class="control-label col-md-2" for="description"> คำอธิบาย
     							<!-- <span class="required"> * </span> -->
     						</label>
     						<div class="col-md-10">
     							<textarea rows="2" class="form-control" id="description" name="description">เอกสารฉบับนี้ใช้สำหรับการตรวจประเมินผู้ขายของบริษัท CP เท่านั้น ข้อกำหนดเฉพาะบางส่วน ถูกกำหนดขึ้นตามหลักการของ Thai Gap / EUREPGAP มาตรฐานวัตถุดิบของ CP และข้อกำหนดจากลูกค้าบางส่วนซึ่งครอบคลุมถึงวัตถุดิบ รวมทั้งกฏหมายที่เกี่ยวข้อง </textarea>
     						</div>
     					</div>
     					
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
     					<hr>
     					
     					<div class="row section-approve-condition">     					
     						<div class="col-md-12">
     						
     							<div class="form-group">
		     						<label class="control-label col-md-2"> การอนุมัติผู้ขาย
		     							<span class="required"> * </span>
		     						</label>
		     						<div class="col-md-10">
		     							<a class="btn sbold green" id="btn-add-condition" data-toggle="modal" href="#dialog_add_condition"> เพิ่มเงื่อนไขการอนุมัติผู้ขาย 
		                           			<i class="fa fa-plus"></i>
		                        		</a>	
		     						</div>
		     						<div class="col-md-offset-2">
		     							<input type="text" class="form-control input-large" style="visibility: hidden!Important; height:0px; width:0px;" id="table_condition_tmp" name="table_condition_tmp"/>  
		     						</div>   				
		     					</div>
		     					
		     					<div class="form-group">
		     						<div class="col-md-8 col-md-offset-2">
		     							<table class="table table-striped table-bordered table-hover order-column table_managed table_condition" id="table_condition">
		     								<thead>
		     									<tr>    									
		     									</tr>
		     									<!-- <tr>
		     										<th> Grade </th>
		     										<th> Critical </th>
		     										<th> Major </th>
		     										<th> Minor </th>
		     										<th> Corrective Action </th>
		     										<th> ลบข้อมูล </th>
		     									</tr> -->
		     								</thead>
		     							</table>
		     						</div>
		     					</div>
		     					<hr>
     						
     						</div>     					
     					</div>
     					
     					
     					<div class="row">
     						<div class="col-md-4">
     							<div class="form-group">
     								<label class="control-label col-md-6"> สถานะ
		     							<span class="required"> * </span>
		     						</label>
		     						<div class="col-md-6">			
			           					<input class="checkbox_active_status" type="checkbox" name="dal_chb_activeSatatus" data-on-text="ใช้งาน" data-off-text="ไม่ใช้งาน" id="status_checklist">	           			
			       					</div>
     							</div>
     							
     						</div>
     						
     						<div class="col-md-6">
     							<div class="form-group">
     								<label class="control-label col-md-5"> รับทราบใบ CARS ภายใน
		     							<span class="required"> * </span>
		     						</label>
		     						<div class="col-md-6">			
			           					<input type="text" class="form-control" id="dayOfCarAccept" name="dayOfCarAccept" placeholder="จำนวนวัน">	           			
			       					</div>
     							</div>
     							
     						</div>
     						
     						<div class="col-md-2">
     							<div class="form-group"> 				
				     				<a class="btn sbold green" id="btn-next" > ต่อไป 
				                    	<i class="fa fa-angle-right fa-2x"></i>
				                    </a>						
		     					</div>
     						</div>
     						
     					</div>
     					     					
     					
     					
     				</form>
     				
     			</div>
     			
     		</div>
     	</div>
     </div>
     
     
     <div class="row">
     	<div class="col-md-12">
     		<div class="portlet light" id="portlet-checklist-section">
     			<div class="portlet-title">
     				<div class="caption font-green-sharp">
             			<i class="icon-puzzle"></i>
	                    <span class="caption-subject bold uppercase"> ข้อตรวจรายการเช็คลิสต์ </span>
             		</div>
     				<div class="actions">
     					<a class="btn sbold green" id="btn-create-template" data-toggle="modal" href="#dialog_add_checklist"> เพิ่มหัวข้อการประเมิน
                           	<i class="fa fa-plus"></i>
                        </a>
     					<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
     					<!-- <a class = "btn" href="javascript:;" id="submit_btn">Click Me</a> -->
     				
     					<a class="btn btn-circle btn-icon-only btn-default font-blue-ebonyclay collapsed" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">
			     			<i class="fa fa-angle-down fa-lg rotate-icon"></i>
			     		</a>
     				</div>
     			</div>
     			<div class="portlet-body form form-body-collapse portlet-body-summary">
     				
     				
     				<div class="portlet light portlet-template hide">
	     				
		     			<div class="portlet-title">
		     				<div class="caption font-green-sharp">
			                    <span class="caption-subject bold name-topic-section"> GMP </span>
		             		</div>
		             		<div class="actions">			             			
		             											
		                        <div class="dropdown">
		                        	
								    <button class="btn dropdown-toggle" data-toggle="dropdown" >
								    	ตัวเลือก <i class="fa fa-ellipsis-v" aria-hidden="true"></i>								    	
								    </button>
								    <ul class="dropdown-menu pull-right">
								    	<li>
								      		<a class="btn sbold default add-sub-topic" href="javascript:;"> 
	                             				<i class="fa fa-plus"></i> เพิ่มหัวข้อย่อย
	                         				</a>
	                         			</li>
								      	<li>
								      		<a class="btn sbold default add-group-question" href="javascript:;"> 
	                             				<i class="fa fa-plus"></i> เพิ่มกลุ่มคำถาม
	                         				</a>
										</li>
										<li>
								      		<a class="btn sbold default edit-topic" href="javascript:;"> 
	                             				<i class="fa fa-pencil"></i> แก้ไขหัวข้อ
	                         				</a>
										</li>
								      	<li>
								      		<a class="btn sbold default remove-topic" href="javascript:;"> 
	                             				<i class="fa fa-minus"></i> ลบหัวข้อ
	                         				</a>
								      	</li>
								    </ul>
								</div>		                         
		                        
		                        <p class="uniqueTopic hide"></p>	
							</div> 		
							
							
												
		     			</div>
		     					
		     					
		     			<div class="portlet-body form">
		     				
		     				<form>
		     				
		     					<div class="sub-topic-section" style="margin-left:30px;">
		     						<div class="form-group">
		     							<div class="form-section-right">
		     								<a href="javascript:;" class="add-group-question font-green-soft" data-toggle="tooltip" data-trigger="hover"  title="เพิ่มกลุ่มคำถาม" ><i class="fa fa-plus"></i> </a>
		     								&nbsp;
		     								<a href="javascript:;" class="edit-sub-topic font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขหัวข้อย่อย" ><i class="fa fa-pencil"></i> </a>
		     								&nbsp;
		     								<a href="javascript:;" class="remove-sub-topic font-red-thunderbird" data-toggle="tooltip"  data-trigger="hover"  data-placement="left" title="ลบหัวข้อย่อย"><i class="fa fa-times"></i> </a>	
		     								<p class="uniqueSubTopic hide"></p>   								
		     							</div>
		     							<h4 class="form-section bold font-blue-soft name-sub-topic">  สภาพแวดล้อมภายนอก  </h4>
		     							
		     							<div class="group-question" style="margin-left:30px;">
		     								<div class="form-group">
		     									<div class="form-section-right">  
		     										
		     											<!-- <h5 class="name-group-question" style="float:left;display:inline-block;">ที่ตั้ง</h5> -->
		     											
			     									<a class="font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">
			     								 		<i class="fa fa-angle-down fa-lg rotate-icon"></i>
			     								 	</a>
			     								 	&nbsp;
			     								 	<a href="javascript:;" class="add-question font-green-soft" data-toggle="tooltip" data-trigger="hover" title="เพิ่มคำถาม"><i class="fa fa-plus"></i> </a>
		     										&nbsp;
		     										<a href="javascript:;" class="edit-group-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขกลุ่มคำถาม" ><i class="fa fa-pencil"></i> </a>
		     										&nbsp;
			     								 	<a href="javascript:;"  class="remove-group-question font-red-thunderbird" data-toggle="tooltip" data-placement="left" data-trigger="hover" title="ลบกลุ่มคำถาม"><i class="fa fa-times"></i> </a>
			     											
		     										<p class="uniqueGroupQuestion hide"></p>
		     									 </div>
		     									<h5 class="form-section form-section-left name-group-question"> ที่ตั้ง  </h5>
		     									
		     									<div class="form-group form-body-collapse name-question-section" style="margin-left:30px;">
				     								<ul class="question-list-group" style="margin-top:5px;"></ul>
				     								<!-- <ul>
				     									<li>
					     									<div class="form-group">
					     										ต้องพิจารณาถึงกิจกรรมต่างๆ และสภาพแวดล้อมในบริเวณปฏิบัติงานซึ่งอาจเกิดผลกระทบในทางเสียหาย และต้องกำหนดการป้องกันการปนเปื้อนสู่ผลิตภัณฑ์ กรณีมีการกำหนดการป้องกันการปนเปื้อนในสถานที่ปฏิบัติงาน
					     										 <a href="javascript:;" class="edit-group-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>
					     										 <a href="javascript:;"  class="remove-group-question font-red-thunderbird" data-toggle="tooltip" data-placement="top" data-trigger="hover" title="ลบคำถาม"><i class="fa fa-times"></i> </a>
					     										 <a class="font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย"><i class="fa fa-angle-down fa-lg rotate-icon"></i></a>
			     								 				<div class="form-body-collapse">
			     								 					<ul>
			     								 						<li>
			     								 							<span>เลือกตอบ 1 ข้อ</span>
			     								 							<div class="m-list-timeline m-list-timeline--skin-light">
									     								 		<div class="m-list-timeline__items">
									     								 			<div class="row">
									     								 				<div class="col-md-3">
									     								 					<div class="m-list-timeline__item">
																						<span class="m-list-timeline__badge m-list-timeline__badge--danger"></span>
																						<span class="m-list-timeline__text">Critical</span>
																					</div>
									     								 				</div>
									     								 				<div class="col-md-3">
									     								 				<div class="m-list-timeline__item">
																						<span class="m-list-timeline__badge m-list-timeline__badge--warning"></span>
																						<span class="m-list-timeline__text">Major</span>
																					</div>
									     								 				</div>
									     								 				<div class="col-md-3">
									     								 				<div class="m-list-timeline__item">
																						<span class="m-list-timeline__badge m-list-timeline__badge--primary"></span>
																						<span class="m-list-timeline__text">Minor</span>
																					</div>
									     								 				</div>
									     								 			</div>
									     								 			
																					
																					
									     								 		</div>
									     								 	</div>
			     								 						</li>
			     								 						
			     								 					</ul>
			     								 				</div>
					     									</div>
				     									  </li>
				     									  
				     									<li>
				     									
				     									<div class="form-group">
				     										อาณาเขตบริเวณทำเลที่ตั้งต้องกำหนดอย่างชัดเจน
				     											<a href="javascript:;" class="edit-group-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>
					     										<a href="javascript:;"  class="remove-group-question font-red-thunderbird" data-toggle="tooltip" data-placement="top" data-trigger="hover" title="ลบคำถาม"><i class="fa fa-times"></i> </a>
					     										 <a class="font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">
			     								 					<i class="fa fa-angle-down fa-lg rotate-icon"></i>
			     								 				</a>
			     								 				<div class="form-body-collapse">
			     								 					<ul>
			     								 						<li>Comment</li>			     								 						
			     								 					</ul>
			     								 				</div>
				     										
				     									</div>
				     									 
				     									
				     									
				     									</li>
				     								</ul> -->
				     								
				     								
				     								
				     								
				     							</div>
		     								</div>
		     							</div>
		     						</div>		     						
		     					</div>
		     				
		     						     					
		     				</form>
		     				
		     			</div> 
		     			
	     			</div>
	     			
	     			<!--  Dummy Data-->
	     			<!-- <div class="portlet light portlet-summary">
	     				
		     			<div class="portlet-title">
		     				<div class="caption font-green-sharp">
			                    <span class="caption-subject bold name-topic-section"> Gmp </span>
		             		</div>
		             		<div class="actions">			             			
		             											
		                        <div class="dropdown">
		                        	
								    <button class="btn dropdown-toggle" data-toggle="dropdown" >
								    	ตัวเลือก <i class="fa fa-ellipsis-v" aria-hidden="true"></i>								    	
								    </button>
								    <ul class="dropdown-menu pull-right">
								    	<li>
								      		<a class="btn sbold default add-sub-topic" href="javascript:;"> 
	                             				<i class="fa fa-plus"></i> เพิ่มหัวข้อย่อย
	                         				</a>
	                         			</li>
								      	<li>
								      		<a class="btn sbold default add-group-question" href="javascript:;"> 
	                             				<i class="fa fa-plus"></i> เพิ่มกลุ่มคำถาม
	                         				</a>
										</li>
										<li>
								      		<a class="btn sbold default edit-topic" href="javascript:;"> 
	                             				<i class="fa fa-pencil"></i> แก้ไขหัวข้อ
	                         				</a>
										</li>
								      	<li>
								      		<a class="btn sbold default remove-topic" href="javascript:;"> 
	                             				<i class="fa fa-minus"></i> ลบหัวข้อ
	                         				</a>
								      	</li>
								    </ul>
								</div>		                         
		                        
		                        <p class="uniqueTopic hide">435254374568569</p>	
							</div> 		
							
							
												
		     			</div>
		     					
		     					
		     			<div class="portlet-body form">
		     				
		     				<form>
		     				
		     					<div class="sub-topic-section" style="margin-left:30px;">
		     						<div class="form-group">
		     							<div class="form-section-right">
		     								<a href="javascript:;" class="add-group-question font-green-soft" data-toggle="tooltip" data-trigger="hover"  title="เพิ่มกลุ่มคำถาม" ><i class="fa fa-plus"></i> </a>
		     								&nbsp;
		     								<a href="javascript:;" class="edit-sub-topic font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขหัวข้อย่อย" ><i class="fa fa-pencil"></i> </a>
		     								&nbsp;
		     								<a href="javascript:;" class="remove-sub-topic font-red-thunderbird" data-toggle="tooltip"  data-trigger="hover"  data-placement="left" title="ลบหัวข้อย่อย"><i class="fa fa-times"></i> </a>	
		     								<p class="uniqueSubTopic hide">121212121212</p>   								
		     							</div>
		     							<h4 class="form-section bold font-blue-soft name-sub-topic">  สภาพแวดล้อมภายนอก  </h4>
		     							
		     							<div class="group-question" style="margin-left:30px;">
		     								<div class="form-group">
		     									<div class="form-section-right">  
		     										
		     											<h5 class="name-group-question" style="float:left;display:inline-block;">ที่ตั้ง</h5>
		     											
			     									<a class="font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">
			     								 		<i class="fa fa-angle-down fa-lg rotate-icon"></i>
			     								 	</a>
			     								 	&nbsp;
			     								 	<a href="javascript:;" class="add-question font-green-soft" data-toggle="tooltip" data-trigger="hover" title="เพิ่มคำถาม"><i class="fa fa-plus"></i> </a>
		     										&nbsp;
		     										<a href="javascript:;" class="edit-group-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขกลุ่มคำถาม" ><i class="fa fa-pencil"></i> </a>
		     										&nbsp;
			     								 	<a href="javascript:;"  class="remove-group-question font-red-thunderbird" data-toggle="tooltip" data-placement="left" data-trigger="hover" title="ลบกลุ่มคำถาม"><i class="fa fa-times"></i> </a>
			     											
		     										<p class="uniqueGroupQuestion hide">444444555555</p>
		     									 </div>
		     									<h5 class="form-section form-section-left name-group-question"> ที่ตั้ง  </h5>
		     									
		     									<div class="form-group form-body-collapse name-question-section" style="margin-left:30px;">
				     								<ul class="question-list-group" style="margin-top:5px;"></ul>
				     								<ul class="question-list-group" style="margin-top:5px;">
				     									<li class="question-list">
					     									<div class="form-group">
					     										<div class="form-section-right">
					     											<a href="javascript:;" class="edit-group-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>
					     										 	<a href="javascript:;"  class="remove-group-question font-red-thunderbird" data-toggle="tooltip" data-placement="top" data-trigger="hover" title="ลบคำถาม"><i class="fa fa-times"></i> </a>
					     										 	<a class="font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย"><i class="fa fa-angle-down fa-lg rotate-icon"></i></a>
					     											<p class="uniqueQuestion hide">00000000000000</p>
					     										</div>
					     										<p class="question-name form-section-left" style="margin: 5px 0;">ต้องพิจารณาถึงกิจกรรมต่างๆ และสภาพแวดล้อมในบริเวณปฏิบัติงานซึ่งอาจเกิดผลกระทบในทางเสียหาย และต้องกำหนดการป้องกันการปนเปื้อนสู่ผลิตภัณฑ์ กรณีมีการกำหนดการป้องกันการปนเปื้อนในสถานที่ปฏิบัติงาน</p>
					     										 
			     								 				
			     								 				<div class="form-body-collapse">
			     								 					<ul>
			     								 						<li>
			     								 							<span>เลือกตอบ 1 ข้อ</span>
			     								 							<p class="questionTypeId hide">1</p>
			     								 							<div class="m-list-timeline m-list-timeline--skin-light">
									     								 		<div class="m-list-timeline__items">
									     								 			<div class="row">
									     								 				<div class="col-md-3">
									     								 					<div class="m-list-timeline__item item_answer">
																								<span class="m-list-timeline__badge m-list-timeline__badge--danger"></span>
																								<span class="m-list-timeline__text answerName">Critical</span>
																								<span class="m-list-timeline__time answerId hide">1</span>
																							</div>
									     								 				</div>
									     								 				<div class="col-md-3">
									     								 					<div class="m-list-timeline__item item_answer">
																								<span class="m-list-timeline__badge m-list-timeline__badge--warning"></span>
																								<span class="m-list-timeline__text answerName">Major</span>
																								<span class="m-list-timeline__time answerId hide">2</span>
																							</div>
									     								 				</div>
									     								 				<div class="col-md-3">
										     								 				<div class="m-list-timeline__item item_answer">
																								<span class="m-list-timeline__badge m-list-timeline__badge--primary"></span>
																								<span class="m-list-timeline__text answerName">Minor</span>
																								<span class="m-list-timeline__time answerId hide">3</span>
																							</div>
									     								 				</div>
									     								 			</div>
																					
									     								 		</div>
									     								 	</div>
			     								 						</li>
			     								 						
			     								 					</ul>
			     								 				</div>
					     									</div>
				     									  </li>
				     									  
				     									<li class="question-list">
				     									
				     									<div class="form-group">
					     										<div class="form-section-right">
					     											<a href="javascript:;" class="edit-group-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>
					     										 	<a href="javascript:;"  class="remove-group-question font-red-thunderbird" data-toggle="tooltip" data-placement="top" data-trigger="hover" title="ลบคำถาม"><i class="fa fa-times"></i> </a>
					     										 	<a class="font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย"><i class="fa fa-angle-down fa-lg rotate-icon"></i></a>
					     											<p class="uniqueQuestion hide">000000000000111</p>
					     										</div>
					     										<p class="question-name form-section-left" style="margin: 5px 0;">อาณาเขตบริเวณทำเลที่ตั้งต้องกำหนดอย่างชัดเจน</p>
					     										 
			     								 				
			     								 				<div class="form-body-collapse">
			     								 					<ul>
			     								 						<li>
			     								 							<span>เขียนตอบ</span>
			     								 							<p class="questionTypeId hide">3</p>
			     								 							<div class="m-list-timeline m-list-timeline--skin-light">
									     								 		<div class="m-list-timeline__items">
									     								 			<div class="row">
									     								 				<div class="col-md-3">
									     								 					<div class="m-list-timeline__item item_answer">
																								<span class="m-list-timeline__badge m-list-timeline__badge--danger"></span>
																								<span class="m-list-timeline__text answerName">Comment</span>
																								<span class="m-list-timeline__time answerId hide">5</span>
																							</div>
									     								 				</div>
									     								 				
									     								 			</div>
																					
									     								 		</div>
									     								 	</div>
			     								 						</li>
			     								 						
			     								 					</ul>
			     								 				</div>
					     									</div>		  
				     									</li>
				     								</ul>
				     								
				     								
				     								
				     								
				     							</div>
		     								</div>
		     							</div>
		     						</div>		     						
		     					</div>
		     				
		     						     					
		     				</form>
		     				
		     			</div> 
		     			
	     			</div> -->
	     			<!--  Dummy Data-->
     				
     				     				
     				
     			</div>
     			
     			<div class="portlet-footer">
     				<!-- <div class="form-group section-btn-navigator">
     					<div class="row">
     						<div class="col-md-2">
     							<a class="btn sbold green" id="btn-prev" > 
		            				<i class="fa fa-angle-left fa-2x"></i> ย้อนกลับ
		            			</a>
     						</div>     							
     					</div>
     				</div> -->
     			</div>
     			
     			
     		</div>
     	</div>
     </div>
     
     
     <%-- Dialog --%>
	<div id="dialog_add_condition" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static"><!-- style=" z-index: 100000000 !important;" -->
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <!-- <div class="modal-header">
		    <div class="row">
				<div class="col-md-12">
		        	<button type="button" class="close btn-lg" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title"> เพิ่มเงื่อนไขการอนุมัติผู้ขาย  </h4>
				</div>
			</div>
	      </div> -->
	      
	      <div class="modal-body">
	      
	      	<div class="portlet light" id="form_wizard_rule">
	      	
	      		<div class="portlet-title">
	      			<div class="caption font-green-sharp">
                		<i class=" icon-doc"></i>
                		<span class="caption-subject font-green bold uppercase"> เพิ่มเงื่อนไขการอนุมัติผู้ขาย -
                			<span class="step-title"> ขั้นตอนที่ 1 จาก 2 </span>
                		</span>
                	</div>
                	<div class="actions">
                    	<a class="btn btn-circle btn-icon-only btn-default" data-dismiss="modal" href="javascript:;">
                       		<span aria-hidden="true" style="font-size: 14px;">&times;</span>
                        </a>
                   	</div>
	      		</div>
	      		
	      		<div class="portlet-body form">
	      			<form class="form-horizontal" action="#" id="form_body_rule" method="POST">
	      				<div class="form-wizard">
	      				
	      					<div class="form-body">
	      						<ul class="nav nav-pills nav-justified steps">
	      							<li>
	      								<a href="#tabGrade" data-toggle="tab" class="step">
                                    		<span class="number"> 1 </span>
                                   			<span class="desc">
                                    			<i class="fa fa-check"></i> เกรด
                                    		</span>
                                    	</a>
	      							</li>
	      							
	      							<li>
	      								<a href="#tabCondition" data-toggle="tab" class="step">
                                    		<span class="number"> 2 </span>
                                   			<span class="desc">
                                    			<i class="fa fa-check"></i> เงื่อนไข 
                                    		</span>
                                    	</a>
	      							</li>	     
	      							
	      							<li>
	      								<a href="#tabCorrectiveAction" data-toggle="tab" class="step">
                                    		<span class="number"> 3 </span>
                                   			<span class="desc">
                                    			<i class="fa fa-check"></i> CorrectiveAction 
                                    		</span>
                                    	</a>
	      							</li>	
	      							 							
	      						</ul>	
	      						
	      						<div id="bar" class="progress progress-striped active" role="progressbar">
                                	<div class="progress-bar progress-bar-warning"> </div>
                                </div>
                                
                                <div class="tab-content"> 
                                
                                	<div class="alert alert-danger display-none">
                                    	<button class="close" data-dismiss="alert">&times;</button> กรุณากรอกข้อมูลในช่องด้านล่างให้ครบถ้วน 
                                    </div>
                                    <div class="alert alert-success display-none">
                                    	<button class="close" data-dismiss="alert">&times;</button>  
                                    </div>
                                    
                                    <div class="tab-pane" id="tabGrade">
                                    	<div class="form-group">
                                    		<label class="control-label col-md-offset-2 col-md-3"> เกรด 
                                        		<span class="required"> * </span>
                                        	</label>
                                        	<div class="col-md-4">
                                        		 <select class="form-control select2 select2-container select2-container--bootstrap select2-container--below" id="grade_list" name="grade_list"></select>
                                        	</div>
                                    	</div>
                                    </div>
                                    
                                    <div class="tab-pane" id="tabCondition">
                                    	<div class="repeater">
                                    		<div data-repeater-list="group-condition" class="condition">
                                    			<div data-repeater-item>
                                    				
                                    				<div class="row">
                                    					<div class="col-md-11">
                                    						<div class="portlet light">
                                    							<div class="portlet-body">                                  								
                                    								
                                    								
                                    								<div class="row condition-section">
                                    									
                                    									<label class="control-label col-md-2"> ผลลัพธ์ 
                                        									<span class="required"> * </span>
                                        								</label>
                                        								<div class="col-md-2">                                        									
                                        									<select class="form-control result_list">
                                        										
																			</select>
							                                        		<span class="help-block">  </span>
                                        								</div>
                                        								<label class="control-label col-md-2"> ตัวดำเนินการ 
                                        									<span class="required"> * </span>
                                        								</label>
                                        								<div class="col-md-2">
                                        									<select class="form-control operator_list">
																			    <option>&#60;</option>
																			    <option>&#8804;</option>
																			    <option>&#62;</option>
																			    <option>&#8805;</option>
																			    <option>&#61;</option>
																			    <option>-</option>
																			</select>
							                                        		<span class="help-block">  </span>
                                        								</div>
                                        								
                                        								<div class="col-md-2">
                                        									<div class="form-group">
                                        									<input type="text" class="form-control number1" name="number1"/>
                                        									<span class="help-block">  </span>
                                        									</div>  
                                        								</div>
                                        								<div class="form-group">
                                        									<div class="col-md-2 hide">                                        									
                                        									<input type="text" class="form-control number2" name="number2" />
                                        									<span class="help-block">  </span>
                                        								</div> 
                                        								</div>
                                        								
                                        								
                                    								</div>
                                    								
                                    							</div>
                                    						</div>
                                    					</div>
                                    					<div class="col-md-1">
		                                        			<a data-repeater-delete class="btn sbold red" href="javascript:;">
		                             							<i class="fa fa-minus"></i>
		                         							</a>
		                                        		</div>
                                    				</div>
                                    				
                                    			</div>
                                    		</div>
                                    		<hr/>
                                    		<div class="form-group">
        										<div class="col-md-2 col-md-offset-10">
        											<a data-repeater-create type="button" class="btn sbold blue">เพิ่มเงื่อนไข
        												<i class="fa fa-plus"></i>
        											</a>
        										</div>
        									</div>  
                                    	</div>
                                    </div>
                                    
                                    
                                    <div class="tab-pane" id="tabCorrectiveAction">
                                    	<div class="form-group">
                                    		<label class="control-label col-md-offset-1 col-md-3"> CorrectiveAction 
                                        		<span class="required"> * </span>
                                        	</label>
                                        	<div class="col-md-6">
                                        		 <textarea rows="3" class="form-control" id="CorrectiveAction" name="CorrectiveAction"></textarea>
                                        		<span class="help-block">  </span>
                                        	</div>
                                    	</div>
                                    </div>
                                    
                                </div>   
	      						   							      							      					
	      					</div>
	      					
	      					
	      					
	      					<div class="form-actions">
	      						<div class="row">
                                	<div class="col-md-offset-9 col-md-3">
                                		<a href="javascript:;" class="btn default button-previous">
                                			<i class="fa fa-angle-left"></i> ย้อนกลับ 
                                		</a>
                                		<a href="javascript:;" class="btn green button-next"> ต่อไป
                                			<i class="fa fa-angle-right"></i>
                                		</a>
                                		<a href="javascript:;" class="btn green button-submit"> ยืนยัน
                                			<i class="fa fa-check"></i>
                                		</a>
                                	</div>
                                </div>
	      					</div>
	      					
	      					
	      					
	      				</div>
	      			</form>
	      		</div>
	      		
	      	</div>

	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
	
	
	<%-- Dialog Add Checklist --%>
	<div id="dialog_add_checklist" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static"  >	<!-- style=" z-index: 100000000 !important;" -->
     	<div class="modal-dialog modal-lg">
     		<div class="modal-content">
     		
     			<div class="modal-body">
     				<div class="portlet light" id="form_wizard">
     					<div class="portlet-title">
	      					<div class="caption font-green-sharp">
                            	<i class=" icon-doc"></i>
                            	<span class="caption-subject font-green bold uppercase"> เพิ่มแม่เช็คลิสต์ -
                            		<span class="step-title"> ขั้นตอนที่ 1 จาก 5 </span>
                            	</span>
                            </div>	      					
	      					<div class="actions">
                                <a class="btn btn-circle btn-icon-only btn-default" data-dismiss="modal" href="javascript:;">
                                   <i class="icon-power"></i>
                                </a>
                            </div>
	      				</div>
	      				
	      				<div class="portlet-body form">
	      					<form class="form-horizontal" action="#" id="form_process" method="POST">	      					
	      						<div class="form-wizard">
	      							<div class="form-body">
	      								
	      							<ul class="nav nav-pills nav-justified steps">
	      							
                                    <li>
                                    	<a href="#tabTopic" data-toggle="tab" class="step">
                                    		<span class="number"> 1 </span>
                                   			<span class="desc">
                                    			<i class="fa fa-check"></i> หัวข้อ 
                                    		</span>
                                    	</a>
                                    	
                                    </li>
                                    <li>
                                    	<a href="#tabSubTopic" data-toggle="tab" class="step">
                                    		<span class="number"> 2 </span>
                                    		<span class="desc">
                                    			<i class="fa fa-check"></i> หัวข้อย่อย 
                                    		</span>
                                    	</a>
                                    </li>
                                    <li>
                                    	<a href="#tabQuestionGroup" data-toggle="tab" class="step">
                                    		<span class="number"> 3 </span>
                                    		<span class="desc">
                                    			<i class="fa fa-check"></i> กลุ่มคำถาม 
                                    		</span>
                                    	</a>
                                    </li>
                                    <li>
                                    	<a href="#tabQuestion" data-toggle="tab" class="step">
                                    		<span class="number"> 4 </span>
                                    		<span class="desc">
                                    			<i class="fa fa-check"></i> คำถาม 
                                    		</span>
                                    	</a>
                                    </li>
                                    <li>
                                    	<a href="#tabSummary" data-toggle="tab" class="step">
                                    		<span class="number"> 5 </span>
                                    		<span class="desc">
                                    			<i class="fa fa-check"></i> สรุป 
                                    		</span>
                                    	</a>
                                    </li>
	      						</ul>
	      						
	      						
	      						<div id="bar" class="progress progress-striped active" role="progressbar">
                                	<div class="progress-bar progress-bar-warning"> </div>
                                </div>
                                
                                
                                <div class="tab-content"> 
                                
                                	<div class="alert alert-danger display-none">
                                    	<button class="close" data-dismiss="alert">&times;</button> กรุณากรอกข้อมูลในช่องด้านล่างให้ครบถ้วน 
                                    </div>
                                    <div class="alert alert-success display-none">
                                    	<button class="close" data-dismiss="alert">&times;</button>  
                                    </div>                               
                                    
                                    <div class="tab-pane" id="tabTopic">                                	
                                    	<div class="form-group">
                                        	<label class="control-label col-md-offset-1 col-md-3">ชื่อหัวข้อ 
                                        		<span class="required"> * </span>
                                        	</label>                                        	
                                        	<div class="col-md-4">
                                        		<input type="text" class="form-control" name="name_topic" id="name_topic" value=""/>
                                        		<span class="help-block">  </span>
                                        	</div>                                       	
                                        </div>  
                                        <div class="form-group">
                                        	<label class="control-label col-md-offset-1 col-md-3">รายละเอียดหัวข้อ 
                                        		
                                        	</label>                                        	
                                        	<div class="col-md-4">
                                        		<textarea rows="3" class="form-control" id="description_topic" name="description_topic"></textarea>
                                        		<span class="help-block">  </span>
                                        	</div>                                       	
                                        </div>  
                                    </div>
                                    
                                    <div class="tab-pane" id="tabSubTopic">
                                    	<div class="form-group">
                                        	<label class="control-label col-md-offset-1 col-md-3">ชื่อย่อยหัวข้อ</label>                                        	
                                        	<div class="col-md-4">
                                        		<input type="text" class="form-control" name="name_sub_topic" id="name_sub_topic" value=""/>
                                        		<span class="help-block">  </span>
                                        	</div>                                       	
                                        </div>  
                                        <div class="form-group">
                                        	<label class="control-label col-md-offset-1 col-md-3">รายละเอียดหัวข้อย่อย 
                                        		
                                        	</label>                                        	
                                        	<div class="col-md-4">
                                        		<textarea rows="3" class="form-control" id="description_sub_topic" name="description_sub_topic"></textarea>
                                        		<span class="help-block">  </span>
                                        	</div>                                       	
                                        </div> 
                                    </div>
                                    
                                    
                                    <div class="tab-pane" id="tabQuestionGroup">
                                    	<div class="form-group">
                                        	<label class="control-label col-md-offset-1 col-md-3">ชื่อกลุ่มคำถาม 
                                        		<span class="required"> * </span>
                                        	</label>                                        	
                                        	<div class="col-md-4">
                                        		<input type="text" class="form-control" name="name_group_question" id="name_group_question" value=""/>
                                        		<span class="help-block">  </span>
                                        	</div>                                       	
                                        </div>  
                                        <div class="form-group">
                                        	<label class="control-label col-md-offset-1 col-md-3">รายละเอียดกลุ่มคำถาม 
                                        		
                                        	</label>                                        	
                                        	<div class="col-md-4">
                                        		<!-- <div class="txtEditor" id='editor'></div> -->
                                        		<textarea rows="3" class="form-control" id="description_group_question" name="description_group_question"></textarea>
                                        		<span class="help-block">  </span>
                                        	</div>         
                                        	                       	
                                        </div> 
                                    </div>
                                    
                                    <div class="tab-pane" id="tabQuestion">
                                    	<div class="repeater">
                                    		<div data-repeater-list="group-a" class="question">
                                    			<div data-repeater-item>
        											
        											<div class="row">
        												<div class="col-md-12">
        													<div class="form-group">
        														<label class="control-label col-md-2">คำถาม 
	                                        						<span class="required"> * </span>
		                                        				</label> 
		                                        				<div class="col-md-9">		                                        					
		                                        					<textarea rows="3" class="form-control question_th txtEditor" name="question" id="question" > <!-- ต้องพิจารณาถึงกิจกรรมต่างๆและสภาพแวดล้อมในบริเวณปฏิบัติงานซึ่งอาจเกิดผลกระทบในทางเสียหาย --></textarea>
		                                        				</div>
		                                        				<div class="col-md-1">
		                                        					<a data-repeater-delete class="btn sbold red" href="javascript:;">
		                             									<i class="fa fa-minus"></i>
		                         									</a>
		                                        				</div>
        													</div>        													
        												</div> 
        											</div>
        											
        											<div class="row">      
        												<div class="col-md-6">
        													<div class="form-group">
        														<label class="control-label col-md-4">ประเภทคำตอบ 
	                                        						<span class="required"> * </span>
		                                        				</label> 
		                                        				<div class="col-md-6">
		                                        					<select class="form-control select2 select2-container select2-container--bootstrap select2-container--below question_type_selection" placeholder="เลือกประเภทคำถาม" style="width:auto !important">		                                        					 
																    </select>
		                                        				</div>
        													</div>
        												</div>  												
        												<div class="col-md-6">
        													<div class="form-group">      
		                                        				<label class="control-label col-md-2"> คำตอบ 
	                                        						<span class="required"> * </span>
		                                        				</label> 
		                                        				<label class="control-label col-md-4 label-null-select" >
		                                        					กรุณาเลือกประเภทคำตอบ
		                                        				</label>
		                                        				<div class="col-md-6 form-md-checkboxes answerlist"> 
		                                        					<div class="md-checkbox-list">  
		                                        						
		                                        					</div>
		                                        				</div> 																
        													</div>
        												</div>
        											</div>
        											
        											
        											
      											</div>
                                    		</div>
                                    		<hr/>
                                    		<div class="form-group">
        										<div class="col-md-2 col-md-offset-10">
        											<a data-repeater-create type="button" class="btn sbold blue" id="repeat-section-question">เพิ่มคำถาม
        												<i class="fa fa-plus"></i>
        											</a>
        										</div>
        									</div>                                   		
                                    		
                                    	</div>
                                    	
                                    </div>
                                    
                                    <div class="tab-pane" id="tabSummary">
                                    	<div class="container">
                                    		
                                    		<h3 class="block"> สรุปการสร้างแม่แบบเช็คลิสต์ </h3>
                                    		<h4 class="form-section"> หัวข้อ  </h4>
                                    		
											<div class="row">
												<!-- <div class="form-group"> -->
	                                            	<label class="control-label col-md-3"> ชื่อหัวข้อ :</label>
	                                            	<div class="col-md-4">
	                                            		<p class="form-control-static" id="name_topic_summary"> </p>
	                                            	</div>
	                                            <!-- </div> -->
	                                        </div>
	                                        <div class="row">
	                                        	<!-- <div class="form-group"> -->
	                                            	<label class="control-label col-md-3"> รายละเอียดหัวข้อ :</label>
	                                            	<div class="col-md-4">
	                                            		<p class="form-control-static" id="description_topic_summary"> - </p>
	                                            	</div>
	                                            <!-- </div> -->
	                                        </div>
	                                            
											
                                    		
                                            
                                            <h4 class="form-section">  หัวข้อย่อย  </h4>
                                            <div class="row">
                                            	<!-- <div class="form-group"> -->
	                                            	<label class="control-label col-md-3"> ชื่อหัวข้อย่อย :</label>
	                                            	<div class="col-md-4">
	                                            		<p class="form-control-static" id="name_sub_topic_summary"> </p>
	                                            	</div>
	                                           <!--  </div> -->
                                            </div>
                                    		
                                    		<div class="row">
                                    			<!-- <div class="form-group"> -->
	                                            	<label class="control-label col-md-3"> รายละเอียดหัวข้อย่อย :</label>
	                                            	<div class="col-md-4">
	                                            		<p class="form-control-static" id="description_sub_topic_summary"> - </p>
	                                            	</div>
	                                            <!-- </div> -->
                                    		</div>
                                            
                                            
                                            
                                            <h4 class="form-section">  กลุ่มคำถาม  </h4>
                                            <div class="row">
                                            	<!-- <div class="form-group"> -->
	                                            	<label class="control-label col-md-3"> ชื่อกลุ่มคำถาม :</label>
	                                            	<div class="col-md-4">
	                                            		<p class="form-control-static" id="name_group_question_summary"> </p>
	                                            	</div>
	                                            <!-- </div> -->
                                            </div>
                                            
                                            <div class="row">
                                            	<!-- <div class="form-group"> -->
	                                            	<label class="control-label col-md-3"> รายละเอียดกลุ่มคำถาม :</label>
	                                            	<div class="col-md-4">
	                                            		<p class="form-control-static" id="description_group_question_summary">- </p>
	                                            	</div>
	                                            <!-- </div> -->
                                            </div>
                                            
                                            
                                            
                                            <h4 class="form-section">  คำถาม  </h4>     
                                            
                                            <div class="list-group question-section">                                            	    
                                            	                       	
                                            <!-- <div class="mt-element-list">
                                            	<div class="mt-list-head list-todo blue">
                                            		<div class="list-head-title-container">
                                            			<h3 class="list-title">My Projects</h3>
                                            		</div>
                                            	</div>
                                            	<div class="mt-list-container list-todo" id="accordion1" role="tablist" aria-multiselectable="true">
                                            		<div class="list-todo-line"></div>
                                            			<ul>                                            			
                                            				<li class="mt-list-item">
                                            					<div class="list-todo-item grey">
                                            						<a class="list-toggle-container" data-toggle="collapse" data-parent="#accordion1" onclick=" " href="#task-1" aria-expanded="false">
                                            							<div class="list-toggle done uppercase">
                                            								<div class="list-toggle-title bold">Metronic Database</div>
                                                                            <div class="badge badge-default pull-right bold">3</div>
                                            							</div>
                                            						</a>
                                            						<div class="task-list panel-collapse collapse in" id="task-1">
                                            							<ul>
	                                            							<li class="task-list-item done"> 
	                                                                        	<div class="task-content">
	                                                                        		<h4 class="uppercase bold">
	                                                                        			<a href="javascript:;">Database Optimization</a>
	                                                                        		</h4>                                                                                            
	                                                                        	</div>
	                                                                        </li>
                                            							</ul>
                                            						</div>
                                            					</div>
                                            				</li>
                                            				
                                            			</ul>
                                            		</div>
                                            	</div> -->
                                            	
                                            	
                                            	
                                            	
                                            </div> 
                                            
                                            
                                            
                                    	</div>                                    	
                                    </div>                                    
                                
                                </div>
	      							
	      					</div>
	      					
	      							<div class="form-actions">
                                        <div class="row">
                                            <div class="col-md-offset-9 col-md-3">
                                                    <a href="javascript:;" class="btn default button-previous-step">
                                                                <i class="fa fa-angle-left"></i> ย้อนกลับ </a>
                                                            <a href="javascript:;" class="btn green button-next-step"> ต่อไป
                                                                <i class="fa fa-angle-right"></i>
                                                            </a>
                                                            <a href="javascript:;" class="btn green button-confirm"> ยืนยัน
                                                                <i class="fa fa-check"></i>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>  
	      					
	      						</div>
	      					</form>
	      				</div>
	      				
     				</div>     			
     			</div>
     			
     			
     		</div>
     	</div>
     </div>	
	<%-- Dialog Add Checklist --%>
	
	<%-- Dialog Edit Topic, SubTopic, QuestionGroup --%>
	<div id="dialog_edit_Not_Question" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000" data-keyboard="false" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
		    <div class="row">
				<div class="col-md-12">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title">แก้ไขหัวข้อ</h4>
				</div>
			</div>
	      </div>
	      <div class="modal-body">
	        <form role="form" id="edit_Not_Question">
	            <div class="form-body">
					<div class="form-group">
	       				<label class="label-title">ชื่อหัวข้อ
	       					
	       				</label><span class="required"> * </span>
	           			<input class="form-control" type="text" name="title" id="title"> 
	       			</div>	
	       			<div class="form-group">
	       				<label class="label-detail">รายละเอียดหัวข้อ       					
	       				</label>
	           			<textarea class="form-control" type="text" name="detail" id="detail"></textarea> 
	       			</div>	
	       			<div class="form-group hide">
	       				<div class="row">
	       					<div class="col-md-6">
		       					<label class="label-UniqueId">UniqueId</label><span class="required"> * </span>
		           				<input class="form-control" type="text" name="uniqueId" id="uniqueId"> 
		       				</div>
		       				<div class="col-md-6">
		       					<label class="label-EvalType">EvalType</label><span class="required"> * </span>
		           				<input class="form-control" type="text" name="evalType" id="evalType"> 
		       				</div>
	       				</div>
	       				
	       				
	       			</div>	
	            </div>
	            <div class="form-actions">
	                
	            </div>
	        </form> 
	        <button type="button" class="btn green" id="submit_dialog_edit_Not_Question">บันทึก</button>                              
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog Edit Topic, SubTopic, QuestionGroup --%>
	
	<%-- Dialog Edit Question --%>
	<div id="dialog_edit_Question" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000" data-keyboard="false" data-backdrop="static">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
		    <div class="row">
				<div class="col-md-12">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title">แก้ไขคำถาม</h4>
				</div>
			</div>
	      </div>
	      <div class="modal-body">
	        <form role="form" id="edit_Question">
	            <div class="form-body">
					
	       			<div class="form-group">
	       				<label class="label-detail-question">คำถาม :      					
	       				</label><span class="required"> * </span>
	           			<textarea class="form-control txtEditor" type="text" name="detail-question" id="detail-question"></textarea> 
	       			</div>	
	       			<div class="form-group hide">
	       				<label class="label-UniqueId-Question">UniqueId	       					
	       				</label><span class="required"> * </span>
	           			<input class="form-control" type="text" name="uniqueId-question" id="uniqueId-question"> 
	       			</div>	
	       			
	       			<div class="row">      
        				<div class="col-md-6">
        					<div class="form-group">
        						<label class="control-label col-md-4">ประเภทคำตอบ 
	                            	<span class="required"> * </span>
		                        </label> 
		                        <div class="col-md-8">
		                        	<select class="form-control select2 select2-container select2-container--bootstrap select2-container--below question_type_edit" placeholder="เลือกประเภทคำถาม" style="width:auto !important">		                                        					 
									</select>
		                        </div>
        					</div>
        				</div>  												
        				<div class="col-md-6">
        					<div class="form-group">      
		                    	<label class="control-label col-md-3"> คำตอบ 
	                            	<span class="required"> * </span>
		                        </label> 
		                        <label class="control-label col-md-6 label-null-select" >กรุณาเลือกประเภทคำตอบ</label>
		                        <div class="col-md-6 form-md-checkboxes answerlist"> 
		                        	<div class="md-checkbox-list">  
		                                        						
		                        	</div>
		                        </div> 																
        					</div>
        				</div>
        			</div>
	       			
	            </div>
	        </form> 
	        <button type="button" class="btn green" id="submit_dialog_edit_Question">บันทึก</button>                              
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog Edit Question --%>
	
	<!-- BEGIN QUICK NAV -->
        <nav class="quick-nav">
            <a class="quick-nav-trigger" href="#0">
                <span aria-hidden="true"></span>
            </a>
            <ul>
                <li>
                    <a href="javascript:;" class="active" id="save-checklist">
                        <span>บันทึกเช็คลิสต์</span>
                        <i class="fa fa-save fa-5x" style="font-size: 25px;"></i>
                    </a>
                </li>               
            </ul>
            <span aria-hidden="true" class="quick-nav-bg"></span>
        </nav>
        <div class="quick-nav-overlay"></div>
        <!-- END QUICK NAV -->
        
        <script src="${context}/assets/js/custom_checklist.js" type="text/javascript" ></script>	
     	<script src="${context}/assets/js/page_checklistform.js" type="text/javascript"></script> 
     

</body>
</html>