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
     <!-- <script src="assets/pages/scripts/table-datatables-managed.js" type="text/javascript"></script> -->
   
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
                 <span>กลุ่มคำถาม</span>
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
                    	<i class="icon-grid"></i>
                        <span class="caption-subject bold uppercase"> กลุ่มคำถาม</span>
                    </div>
                    <div class="actions">
	                     <button   class="btn sbold green"  data-toggle="modal" href="#dialog_add_group"> เพิ่มกลุ่มคำถาม
	                         <i class="fa fa-plus"></i>
	                     </button>	 
	                     <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
	                 </div>
                </div>
                
                 <div class="portlet-body">
                 
                 
                 	<div class="form-group">
                     	<div class="row">
                     		<div class="col-md-3">
                     			<label> ชื่อกลุ่มคำถาม  </label>
    							<input type="text" class="form-control input-sm" id="questiongroup_textbox">
                     		</div>
                     		<div class="col-md-3">
                     			<label>  สถานะ  </label>
    							<select class="form-control form-control-sm select-sm" id="select_status">
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
                 
                 
                 
                     <table class="table table-striped table-bordered table-hover order-column table_managed " id="table_questionGroup">
                         <thead>
                             <tr>
                             	 <th> ลำดับ </th>
                                 <th> ชื่อกลุ่มคำถาม </th>
                                 <th> QuestionGroupId </th>
                                 <th style="width:10%"> สถานะ </th>
                                 <th style="width:10%"> เลือก </th>
<!--                                  <th style="width:10%"></th> -->
                             </tr>
                         </thead>
                         
                     </table>
                     
                 </div>
             </div>
             <!-- END EXAMPLE TABLE PORTLET-->
         </div>
     </div>
    
     <%-- Dialog --%>
	<div id="dialog_add_group" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
		    <div class="row">
				<div class="col-md-12">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title">เพิ่มกลุ่มคำถาม</h4>
				</div>
			</div>
			
	      </div>
	      <div class="modal-body">
	        <form role="form">
	            <div class="form-body">
	            
	            	<div class="form-group hide">
	       				<label>Id ชื่อกลุ่มคำถาม</label>
	           			<input class="form-control" type="text" id="questionGroupIdModal"> 
	       			</div>	
					<div class="form-group">
	       				<label>ชื่อกลุ่มคำถาม</label>
	           			<input class="form-control" placeholder="ชื่อกลุ่มคำถาม" type="text" id="questionGroupNameModal"> 
	       			</div>	
	       			
	       			<div class="form-group">
	       				<label>สถานะ</label> <br/>           			
	           			<input class="checkbox_active_status" type="checkbox" name="dal_chb_activeSatatus" data-on-text="ใช้งาน" data-off-text="ไม่ใช้งาน" id="status_questionGroup">	           			
	       			</div>
			        
			        
			        
			        
	            </div>	            
	            
	            <div class="form-actions">
	                <button type="button" class="btn green" id="btn_action_questionGroup"> บันทึก  </button>
	            </div>
	        </form>                               
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
    
    
    <form class="hide">
    	<input type="text" id="questionGroupId">
    	<input type="text" id="questionGroupName">
    	<input type="text" id="questionGroupStatus">
    </form>
    
    <script src="assets/js/page_questiongroup.js" type="text/javascript"></script>   
             
</body>
</html>