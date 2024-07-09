<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
     <!-- <script src="assets/pages/scripts/table-datatables-managed.js" type="text/javascript"></script> -->
   
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
                 <a href="car.jsp">ใบคาร์</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span> รายละเอียดใบคาร์ </span>
             </li>
         </ul>
         
         <div class="page-toolbar">
         	<div class="btn-group pull-right">
         		<input class="form-control hide" id="carString">
         		<button type="button" class="btn btn-fit-height red" id="cancel-car" style="display: none;"> <i class="fa fa-ban fa-1x" aria-hidden="true"></i>
         		ยกเลิกใบคาร์    
                </button>
         	</div>
         </div>
     </div>
     <!-- END PAGE HEADER-->
            
     <!-- <div class="row">
		<div class="col-md-8">
     		<div class="portlet light ">
				<div class="portlet-title">
                	<div class="caption font-red-sunglo">
                    	<i class="icon-note font-red-sunglo"></i>
                        <span class="caption-subject bold"> รายละเอียด</span>
                    </div>
                </div>
                <div class="portlet-body form">
                
                
                     <table class="table  table-striped table-bordered table-hover order-column">
                         <tbody>
                             <tr>
                                 <th> Checklist </th>
                                 <td>
                                     Packaging
                                 </td>
                             </tr>
                             <tr>
                                 <th> กลุ่มคำถาม </th>
                                 <td>
                                    	 นโยบายคุณภาพและความปลอดภัย 
                                 </td>
                             </tr>
                             <tr>
                                 <th> คำถาม </th>
                                 <td>
                                   	  ระบบบริหารคุณภาพความปลอดภัยอาหาร 
                                 </td>
                             </tr>
                             <tr>
                                 <th> คะแนน </th>
                                 <td>
                                     Critical 
                                 </td>
                             </tr>
                             <tr>
                                 <th> สาเหตุ </th>
                                 <td>
                                     - 
                                 </td>
                             </tr>
                             <tr>
                                 <th> Auditor</th>
                                 <td>
                                     Auditor A 
                                 </td>
                             </tr>
                             <tr>
                                 <th> Supplier</th>
                                 <td>
                                     Copany A 
                                 </td>
                             </tr>
                             <tr>
                                 <th> สถานะ </th>
                                 <td>
					                <label class="mt-checkbox mt-checkbox-outline">
					                	<input type="checkbox"> ใช้งาน
					                    <span></span>
					                </label>
                                 </td>
                             </tr>
                         </tbody>
                     </table>
                     
                    <form role="form">
                        <div class="form-actions">
                            <button type="submit" class="btn green">บันทึก</button>
                        </div>
                    </form>
                </div>
            </div>
     	</div> -->
     	
     	<div class="row">
     		<div class="col-md-3">
     			<input class="form-control hide" name="receive_param" id="receive_param" value="${param.pass_param_car}">
     		</div>
     	</div>
     	<!-- CarNo, ChecklistPlanNo, เกณฑ์การพิจารณา, ปัญหาที่พบ, สถานะ, วันที่ดำเนินการเสร็จสิ้น, หลักฐาน(Button) -->
     	<div class="car-detail-section">
     		<!-- <div class="row">
     			<div class="col-md-6">
     				<div class="portlet light">
     					<div class="portlet-body form">
     						<table class="table table-bordered table-striped">
     							<tbody>
     								<tr>
     									<td style="width:40%"> 
			                    			<div class="row">
			                    				<div class="col-md-12">
			                    					<i class="fa fa-list-ol fa-1x"></i>&nbsp;&nbsp;	                    
		                    						หมายเลขใบคาร์
			                    				</div>
			                    			</div>
		                    			</td>
		                    			<td style="width:60%"></td>
     								</tr>
     								<tr>
     									<td style="width:40%"> 
			                    			<div class="row">
			                    				<div class="col-md-12">
			                    					<i class="fa fa-list-ol fa-1x"></i>&nbsp;&nbsp;	                    
		                    						หมายเลขแผนการเข้าตรวจ
			                    				</div>
			                    			</div>
		                    			</td>
		                    			<td></td>
     								</tr>
     								<tr>
     									<td style="width:40%"> 
			                    			<div class="row">
			                    				<div class="col-md-12">
			                    					<i class="fa fa-pencil-square-o fa-1x"></i>&nbsp;&nbsp;	                    
		                    						เกณฑ์การพิจารณา
			                    				</div>
			                    			</div>
		                    			</td>
		                    			<td></td>
     								</tr>
     								<tr>
     									<td style="width:40%"> 
			                    			<div class="row">
			                    				<div class="col-md-12">
			                    					<i class="fa fa-exclamation-triangle fa-1x"></i>&nbsp;&nbsp;	                    
		                    						ปัญหาที่พบ
			                    				</div>
			                    			</div>
		                    			</td>
		                    			<td></td>
     								</tr>
     								<tr>
     									<td style="width:40%"> 
			                    			<div class="row">
			                    				<div class="col-md-12">
			                    					<i class="fa fa-check fa-1x"></i>&nbsp;&nbsp;	                    
		                    						สถานะ
			                    				</div>
			                    			</div>
		                    			</td>
		                    			<td></td>
     								</tr>
     								<tr>
     									<td style="width:40%"> 
			                    			<div class="row">
			                    				<div class="col-md-12">
			                    					<i class="fa fa-calendar-o fa-1x"></i>&nbsp;&nbsp;	                    
		                    						วันที่ดำเนินการเสร็จสิ้น
			                    				</div>
			                    			</div>
		                    			</td>
		                    			<td></td>
     								</tr>
     								<tr>
     									<td style="width:40%"> 
			                    			<div class="row">
			                    				<div class="col-md-12">
			                    					<i class="fa fa-paperclip fa-1x"></i>&nbsp;&nbsp;	                    
		                    						หลักฐาน
			                    				</div>
			                    			</div>
		                    			</td>
		                    			<td></td>
     								</tr>
     							</tbody>
     						</table>
     						
     						<div class="row">
     							<div class="col-md-4 col-md-offset-2">
     								<button type="button" class="btn green-jungle" id="btn_save_appoint">อนุมัติการแก้ไขปัญหา</button>
     							</div>
     							<div class="col-md-2">     								
                            		<button type="button" class="btn red-mint" id="btn_cancel_appoint">ยกเลิกปัญหา</button>
     							</div>
     						</div>
     						
     					</div>
     				</div>
     			</div>
     			<div class="col-md-6">
     				<div class="portlet light">
     					
     				</div>
     			</div>
     		</div> -->
     	</div>
     	
     	<%-- Dialog Add Checklist --%>
	<div id="dialog_solve_detail_car" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static"  >	<!-- style=" z-index: 100000000 !important;" -->
     	<div class="modal-dialog modal-md">
     		<div class="modal-content">    
     		 		
     			<div class="modal-header">     				
     				<div class="row">
     					<div class="col-md-9">
        					<h4 class="header-txt">การแก้ไข</h4>
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
      							<label> ข้อความ:  </label>
    							<textarea class="form-control" id="solve_evidence_text" placeholder="ไม่พบข้อมูล" rows="3" readonly="readonly"></textarea> 
      						</div>
      					</div>
      				</div>
      									
      				<div class="row">
      					<div class="col-md-12">
      						<div class="form-group">
      							<label>  รูปภาพ :  </label>      												
      						</div>
      					</div>
      				</div>
      									
      				<div class="list-picture-section">		
      				</div>
      				<br>
      				<div class="row">
      					<div class="col-md-12">
      						<div class="form-group">
      							<label>  เอกสาร :  </label>      												
      						</div>
      					</div>
      				</div>
      				<div class="list-document-section">
						<div class="row">
							<div class="col-md-12">
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
     
     
    <%-- Dialog Confirm --%>
	<div id="dialogReason" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title"><div id="dialogReasonHeader"></div></h4>
				</div>
				<div class="modal-body">
					<div class="form-horizontal">
						<div id="dialogReasonBody"></div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" id="cancel_add_reason" class="btn btn-default">ยกเลิก</button>
					<button type="button" id="confirm_add_reason" class="btn green">ตกลง</button>
				</div>
			</div>
		</div>
	</div>
	<%-- Dialog --%>
     	
	<script type="text/javascript" src = "assets/js/page_carform.js"></script>
</body>
</html>