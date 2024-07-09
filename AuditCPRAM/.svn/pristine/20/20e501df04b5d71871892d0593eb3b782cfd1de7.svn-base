<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
     <script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.th.min.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-clockpicker/js/bootstrap-clockpicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-clockpicker/js/jquery-clockpicker.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js" type="text/javascript"></script>
     <!-- <script src="assets/pages/scripts/table-datatables-managed.js" type="text/javascript"></script> -->
     
     <style type="text/css">
     	.bootstrap-tagsinput{
  			width: 100% !important;
  			
		}
	
		.bootstrap-tagsinput .tag{
   	 		font-size: 12px !important;
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
    
     

     
     <div class="row">
		<div class="col-md-7">
     		<div class="portlet light " id="portlet-main-appoint">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-calendar"></i>
                        <span class="caption-subject bold uppercase">รายละเอียด</span>
                    </div>
                </div>
                <div class="portlet-body form">
                    <form role="form" id="form-appoint-detail">
                        <div class="form-body">
				      		<div class="form-group">
			                	<label>นัดโดย</label>
			                   	<div class="alert alert-info">
			                    	<strong class="appointName_Label">Admin</strong>
			                    </div>
                              	<div class="input-group hide">
			                		<input class="form-control" type="text" id="appointNameId">
			                	</div>
			                </div>
			                
			                <div class="form-group hide">
			                	<label>Appoint Id</label><span class="required"> * </span>
			                	<div class="input-group">
			                		<input class="form-control" type="text" id="appointId" value="${param.detailAppoint }">
			                	</div>
			                </div>
			                
			                <div class="form-group hide">
			                	<label>Appoint Status Id</label><span class="required"> * </span>
			                	<div class="input-group">
			                		<input class="form-control" type="text" id="appointStatusId">
			                	</div>
			                </div>
			                
				      		<div class="form-group">
			                	<label>Auditor</label><span class="required"> * </span>
			                    <div class="input-group">
                                     <input class="form-control" placeholder= "กรุณาเลือกผู้ออกตรวจ" type="text" id="AuditorName">
                                     <span class="input-group-btn">
                                         <button class="btn yellow" type="button" data-toggle="modal" href="#dialog_search_auditor">ค้นหา</button>
                                     </span>
                                 </div>
                                 
                                 <input id="dummy_auditName" name="dummy_auditName" type="text" style="visibility: hidden!Important; height:1px;width:1px;"/>      
                                                    
			                </div>
			                
			                <div class="form-group">
			                	<label>ผู้ติดตาม</label>
			                    <div class="input-group">
                                     <input class="form-control" placeholder= "กรุณาเลือกผู้ติดตาม" type="text" id="entourgeName">
                                     <span class="input-group-btn">
                                         <button class="btn yellow" type="button" data-toggle="modal" href="#dialog_search_entourge">ค้นหา</button>
                                     </span>
                                 </div>
                                                    
			                </div>
			                
				      		<div class="form-group">
			                	<label>นัดกับ</label><span class="required"> * </span>
                                <input class="form-control" type="text" id="supplierCompany" name="supplierCompany" readonly="readonly">
                                     <!-- <span class="input-group-btn">
                                         <button class="btn yellow" type="button" data-toggle="modal" href="#dialog_search_supplier">ค้นหา</button>
                                     </span> -->                                                    
			                </div>
			                
			                <div class="form-group hide">
			                	<label>SupplierId</label><span class="required"> * </span>
                                <input class="form-control" type="text" id="supplierId" readonly="readonly">
                            </div>
                            
                            <div class="form-group">
			                	<label>สถานที่ผลิตของซัพพลายเออร์</label><span class="required"> * </span>
                                <input class="form-control" type="text" id="LocationProduce" name="LocationProduce" readonly="readonly" >
                                     <!-- <span class="input-group-btn">
                                         <button class="btn yellow" type="button" data-toggle="modal" href="#dialog_search_supplier">ค้นหา</button>
                                     </span> -->                                                    
			                </div>
			                
			                <div class="form-group hide">
			                	<label>LocationId</label><span class="required"> * </span>
                                <input class="form-control" type="text" id="locationId" readonly="readonly">
                            </div>
			                
				      		<div class="form-group apponit-datetime">
				      			<div class="row">
				      				<div class="col-md-6">
				      					<label>วันนัด</label>
			                    		<input class="form-control" type="text" id="appoint_date" name="appoint_date" readonly="readonly"> 
				      				</div>
				      				<div class="col-md-6">
				      					<label>เวลานัด</label>
			                    		<input class="form-control" type="text" id="appoint_time" name="appoint_time" readonly="readonly"> 
				      				</div>
				      			</div>	
			                </div>
			                
			                <div class="form-group apponit-datetime-pickers">
			                	<div class="row">	
			                		<div class="col-md-6">
			                			<label>วันนัด</label><span class="required"> * </span>
			                			<div class="input-group date datepicker-from">			                				
			                				<input type="text" class="form-control" readonly="readonly" id="appoint_date_pickers" name="appoint_date_pickers">
    										<div class="input-group-addon">
        										<span class="glyphicon glyphicon-calendar"></span>
    										</div>
			                			</div>
			                		</div>
			                		
			                		<div class="col-md-6">
			                			<label>เวลานัด</label><span class="required"> * </span>
			                			<div class="input-group date timepicker-from">			                				
			                				<input type="text" class="form-control" readonly="readonly" id="appoint_time_pickers" name="appoint_time_pickers">
    										<div class="input-group-addon">
        										<span class="glyphicon glyphicon-time"></span>
    										</div>
			                			</div>
			                		</div>
			                		
			                	</div>
			                </div>
			                
				      		<div class="form-group">
			                	<label>หัวข้อ</label><span class="required"> * </span>
			                    <input class="form-control" placeholder="หัวข้อ" type="text" id="appoint_title" name="appoint_title"> 
			                </div>
			                
				      		<div class="form-group">
			                	<label>รายละเอียด</label>
			                    <textarea rows="3" class="form-control" id="appoint_detail"></textarea>
			                </div>
			       
   
   
                        </div>
                        <div class="form-actions">
                            <button type="button" class="btn green-jungle" id="btn_save_appoint">บันทึก</button>
                            <button type="button" class="btn red-mint" id="btn_cancel_appoint" style="display: none;">ยกเลิกการนัดหมาย</button>
                        </div>
                    </form>
                </div>
            </div>
     	</div>
		<div class="col-md-5">
     		
     		<div class="portlet light hide" id="portlet-appoint-change">
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
	                       <!--   <li class="out">
	                             <div class="avatar">Supplier A</div>
	                             <div class="message">
	                                 <span class="arrow"> </span>
	                                 <span class="datetime"> 02/08/2018, 10:30 </span>
	                                 <span class="body"> ขอเลื่อนเป็นวันที่ 6/08/2018 เวลา 13.00 น. เนื่องจากที่ บริษัทมีการซ้อมหนีไฟครับ </span>
	                             </div>
	                         </li>
	                         <li class="in">
	                             <div class="avatar">Admin</div>
	                             <div class="message">
	                                 <span class="arrow"> </span>
	                                 <span class="datetime"> 01/08/2018, 20:11 </span>
	                                 <span class="body"> แจ้งเข้า Audit ในวันที่  05.08.2018 ในเวลา 13.00 น. </span>
	                             </div>
	                         </li> -->
	                         
	                          <%-- <li class="in">
	                             <div class="avatar">Admin</div>
	                             <div class="message">
	                                 <span class="arrow"> </span>
	                                 <span class="datetime"> 01/08/2018, 20:11 </span>
	                                 <span class="body"> แจ้งเข้า Audit ในวันที่  05.08.2018 ในเวลา 13.00 น. </span>
	                                 <a href="javascript:void(0);" title="<b>รายละเอียด</b>" data-toggle="popover" data-trigger="focus" data-placement="right" data-content="<div><b>Example popover</b> - content</div>">รายละเอียดเพิ่มเติม</a>
	                             </div>
	                         </li>
	                          <li class="out">
	                             <div class="avatar">Supplier A</div>
	                             <div class="message">
	                                 <span class="arrow"> </span>
	                                 <span class="datetime"> 02/08/2018, 10:30 </span>
	                                 <span class="body"> ขอเลื่อนเป็นวันที่ 6/08/2018 เวลา 13.00 น. เนื่องจากที่ บริษัทมีการซ้อมหนีไฟครับ </span>
	                                 <a href="javascript:void(0);" title="<h1>รายละเอียด</h1>" data-toggle="popover" data-placement="left" data-trigger="focus" data-content="Click anywhere in the document to close this popover">รายละเอียดเพิ่มเติม</a>
	                             </div>
	                         </li>  --%>
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
	
	
	
	 <script src="assets/js/page_appointform.js" type="text/javascript"></script>   
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