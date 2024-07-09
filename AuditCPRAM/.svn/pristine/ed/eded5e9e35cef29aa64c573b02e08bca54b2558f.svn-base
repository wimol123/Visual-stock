<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
	 <c:set var="context" value="${pageContext.request.contextPath}" />
	 <style type="text/css">
   	 	.close{
   	 		text-indent : 0px; !important
   	 		width: 14px;
			height: 21px;
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
                 <a href="dashboard.jsp">หน้าหลัก</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <a href="checklist.jsp">เช็คลิสต์</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span>เช็คลิสต์ฟอร์ม</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
            
     <div class="row">
		<div class="col-md-6">
     		<div class="portlet light ">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-note"></i>
                        <span class="caption-subject bold uppercase"> รายละเอียด</span>
                    </div>
                </div>
                <div class="portlet-body form">
                    <form role="form">
                        <div class="form-body">
				      		<div class="form-group">
			                	<label>หัวข้อ</label>
			                	<input class="form-control hide" type="text" id="checklistId" value="${param.checklistId}"> 
			                	<input class="form-control hide" type="text" id="checklistStatusActive" value="${param.checklistStatus}"> 
			                    <input class="form-control" placeholder="หัวข้อ"   type="text" id="checklistName" value="${param.checklistName}"> 
			                </div>			     
			                
				      		<div class="form-group">
	       						<label>สถานะ</label> <br/>           			
	           					<input class="checkbox_active_status" type="checkbox" name="dal_chb_activeSatatus" data-on-text="ใช้งาน" data-off-text="ไม่ใช้งาน" id="status_checklist" checked>	           			
	       					</div>
			                
                        </div>
                        <div class="form-actions">
                            <button type="button" class="btn green" id="btn_save_checklist"> บันทึก  </button>
                        </div>
                    </form>
                </div>
            </div>
     	</div>
		<div class="col-md-6 hide">
     		<div class="portlet light option-checklist">
				<div class="portlet-title">
                	<div class="caption font-red-sunglo">
                    	<i class="icon-grid  font-red-sunglo"></i>
                        <span class="caption-subject bold"> กลุ่มคำถาม</span>
                    </div>
                    <div class="actions">
	                     <div class="btn-group">
	                         <button   class="btn sbold"  data-toggle="modal" href="#dialog_add_group"> เพิ่มกลุ่มคำถาม
	                         	<i class="fa fa-plus"></i>
	                         </button>
	                     </div>
	                 </div>
                </div>
                <div class="portlet-body form">
                
                	<ul class="list-group">
                        <li class="list-group-item"> นโยบายคุณภาพและความปลอดภัย </li>
                        <li class="list-group-item"> HACCP </li>
                        <li class="list-group-item"> ความปลอดภัยของผลิตภัณฑ์และการจัดการระบบ </li>
                        <li class="list-group-item"> มาตรฐานการจัดการพืนB ทีEและอาคาร </li>
                    </ul>
                </div>
            </div>
     	</div>
     </div>
     
     <div class="row">
		<div class="col-md-12">
     		<div class="portlet light option-checklist">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-list "></i>
                        <span class="caption-subject bold uppercase"> คำถาม</span>
                    </div>
                    <div class="actions">
	                     <div class="btn-group">
	                         <button   class="btn sbold green"  data-toggle="modal" href="#dialog_add_question"> เพิ่มคำถามลงในเช็คลิสต์
	                         	<i class="fa fa-plus"></i>
	                         </button>
	                     </div>
	                 </div>
                </div>
                <div class="portlet-body form">

					<div class="form-group">
                     	<div class="row">
                     		<div class="col-md-3">
                     			<label>  คำถาม  </label>
    							<input type="text" class="form-control input-sm" id="question_textbox">
                     		</div>
                     		<div class="col-sm-3">
                     			<label>  กลุ่มคำถาม  </label>
    							<input type="text" class="form-control input-sm" id="questionGroup_textbox">
                     		</div>
                     		<div class="col-md-3">
                     			<label>  สถานะ  </label>
    							<select class="form-control form-control-sm" id="select_status">
  									<option value="">--- ทั้งหมด ---</option>
  									<option value="1">ใช้งาน</option>
  									<option value="0">ไม่ใช้งาน</option>
								</select>
                     		</div>
                     	</div>                     	
                     </div>                     
                     
                     <div class="form-group">
                     	<div class="row">
                     		<div class="col-md-4">
                     			<button type="button" class="btn btn-primary btn-sm" style="margin-top:8px;" id="btn_search">
     								<b>ค้นหา</b>
     							</button>
     							<button type="button" class="btn default btn-sm" style="margin-top:8px;" id="btn_clear">
     								<b> ล้างข้อมูล </b>
     							</button>
                     		</div>                     		                 		
                     	</div>
                     </div>

                     <table class="table table-striped table-bordered table-hover order-column table_managed" id="table_question">
                         <thead>
                             <tr>
                             	 <th> ลำดับ </th>
                                 <th>  คำถาม </th>
                                 <th>QuestionId</th>
                                 <th>  กลุ่มคำถาม </th>
                                 <th>QuestionGroupId</th>
                                 <th width="10px">  สถานะ    </th>
                                 <th width="10px"> เลือก </th>
                             </tr>
                         </thead>
                         
                     </table>



                </div>
            </div>
     	</div>
     </div>
     
     	
	<%-- Dialog --%>
	<div id="dialog_add_group" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
		    <div class="row">
				<div class="col-md-6">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title">เพิ่มกลุ่มคำถาม</h4>
				</div>
			</div>
	      </div>
	      <div class="modal-body">
	        <form role="form">
	            <div class="form-body">
					<div class="form-group">
	       				<label>ชื่อกลุ่มคำถาม</label>
	           			<input class="form-control" placeholder="ชื่อกลุ่มคำถาม" type="text"> 
	       			</div>	
	            </div>
	            <div class="form-actions">
	                <button type="submit" class="btn green">บันทึก</button>
	            </div>
	        </form>                               
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
	
	<%-- Dialog --%> 
	<div id="dialog_add_question" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
		    <div class="row">
				<div class="col-md-12">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title">เพิ่มคำถามลงใน เช็คลิสต์</h4>
				</div>
			</div>
	      </div>
	      <div class="modal-body">
	        <form role="form">
	            <div class="form-body">
	            
	            	<div class="form-group">
	            	
	            		            		
	            		<h4> คำถาม  </h4>
	            		<div class="btn-group">
	                         <a class="btn sbold blue" href="question.jsp?action=add"> เพิ่มคำถาม
	                         	<i class="fa fa-plus"></i>
	                         </a>
	                     </div>
	                     <br /><br />
			             <select class="form-control select2 select2-container select2-container--bootstrap select2-container--below" id="question_Dropdown" name="question_Dropdown"  style="width:auto !important" ></select>
	            		
			             
			        </div>
	            
	            	<div class="form-group">
	            	
	            		
	            		<h4> กลุ่มคำถาม  </h4>
	            		<div class="btn-group">
	                         <a class="btn sbold blue" href="question_group.jsp?action=add"> เพิ่มกลุ่มคำถาม
	                         	<i class="fa fa-plus"></i>
	                         </a>
	                     </div>
	                     <br /><br />
			             <!-- <select class="form-control select2 select2-container select2-container--bootstrap select2-container--below" id="questionGroup_Dropdown" name="questionGroup_Dropdown" style="width:auto !important" ></select> -->
	            		<input class="form-control" placeholder="กรุณาเลือกคำถาม กลุ่มคำถามจะปรากฎขึ้น" type="text" id="questionGroupName_Modal" readonly="readonly">
			             
			        </div>
			        
			        
			        
			        
			        
					<!--  <div class="form-group">
	       				<label>กลุ่มคำถาม</label>
                         <div class="mt-radio-list">
                             <label class="mt-radio mt-radio-outline" > นโยบายคุณภาพและความปลอดภัย
                                 <input value="1" name="radioType" checked="true" type="radio">
                                 <span></span>
                             </label>
                             <label class="mt-radio mt-radio-outline"> HACCP
                                 <input value="2" name="radioType" type="radio">
                                 <span></span>
                             </label>
                             <label class="mt-radio mt-radio-outline"> ความปลอดภัยของผลิตภัณฑ์และการจัดการระบบ 
                                 <input value="3" name="radioType" type="radio">
                                 <span></span>
                             </label>
                             <label class="mt-radio mt-radio-outline"> มาตรฐานการจัดการพืนB ทีEและอาคาร 
                                 <input value="4" name="radioType" type="radio">
                                 <span></span>
                             </label>
                         </div>
                    </div>-->
                    
					<!--  <div class="form-group">
	       				<label>คำถาม</label>
	           			<textarea rows="3" class="form-control" placeholder="คำถาม" ></textarea>
	       			</div>	-->
	       			
		      		<div class="form-group" style="display:none;">
	                	<label>Answer Type</label>
                         <div class="mt-radio-inline">
                             <label class="mt-radio mt-radio-outline" > Radio
                                 <input value="1" name="radioType" checked="true" type="radio">
                                 <span></span>
                             </label>
                             <label class="mt-radio mt-radio-outline"> Checkbox
                                 <input value="2" name="radioType" type="radio">
                                 <span></span>
                             </label>
                         </div>
	                </div>
	       			
					<div class="form-group" style="display:none;">
	       				<label>Answer</label>
		           		<input class="form-control" placeholder="Answer" type="text"> 
	       				<a class="btn btn-circle btn-icon-only btn-default" href="javascript:;" style="font-size: :30px;margin:5px 0px 10px 0px;"> + </a>
	       			</div>	
                                 
	            
	            
	            </div>
	            
	            <div class="form-group">
	       			<label>สถานะ</label> <br/>           			
	           		<input class="checkbox_active_status" type="checkbox" name="dal_chb_activeSatatus" data-on-text="ใช้งาน" data-off-text="ไม่ใช้งาน" id="status_question">	           			
	       		</div>
	            <div class="form-actions">
	                <button type="button" class="btn green" id="btn_save_question">บันทึก</button>
	            </div>
	        </form>                            
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
	<div class="bgLoader"></div>
	<div class="imgLoader"></div>
	  
	<script src="assets/js/page_checklistform2.js" type="text/javascript"></script> 
	
</body>
</html>