<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="th.co.gosoft.audit.cpram.model.UserModel"%>
<%@page import="th.co.gosoft.audit.cpram.model.UserGroupModel"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
     <link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
     <link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
     <link href="assets/css/fix_datatable.css" rel="stylesheet" type="text/css" />
     <link href="assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css">
     <link href="assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.css" rel="stylesheet" type="text/css">
     
     <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
     <script src="assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
     <script src="assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.th.min.js" type="text/javascript"></script>
   
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


    <%
		//Check UserGroupId Permission
			UserModel adminModel = (UserModel)request.getSession().getAttribute("sessionAuthen");
			UserGroupModel groupModel = (UserGroupModel)adminModel.getUserGroupId();
			if(Integer.parseInt(groupModel.getUserGroupId()) > 2 && Integer.parseInt(groupModel.getUserGroupId()) < 6){
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
                 <span>เช็คลิสต์ฟอร์ม Audit Supplier (คิดเกรด)</span>
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
                		<i class="icon-note"></i>
                        <span class="caption-subject bold uppercase"> เช็คลิสต์ฟอร์ม Audit Supplier (คิดเกรด) </span>                         
                    </div>   
                    <div class="actions">
	                    <a   class="btn sbold green" href="checklist_form.jsp"> สร้างเช็คลิสต์ใหม่
	                    	<i class="fa fa-plus"></i>
	                    </a>
	                     <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
	                 </div> 
                </div>
                
                 <div class="portlet-body">
                 
                 	<div class="form-group">
                     	<div class="row">
                     		<div class="col-md-3">
                     			<label>  ชื่อเช็คลิสต์  </label>
    							<input type="text" class="form-control input-sm" id="ChecklistName_textbox">
                     		</div>
                     		<div class="col-md-3">
                     			<label>EffectiveDate</label>
    							<div class="input-group date datepicker-effective" data-provide="datepicker">
    								<input type="text" class="form-control input-sm" readonly="readonly" id="EffectiveDate_textbox">
    								<div class="input-group-addon">
        								<span class="glyphicon glyphicon-calendar"></span>
    								</div>
								</div>
                     		</div>
                     		<div class="col-md-3">
                     			<label> ExpireDate </label>
    							<div class="input-group date datepicker-expire" data-provide="datepicker">
    								<input type="text" class="form-control input-sm" readonly="readonly" id="ExpireDate_textbox">
    								<div class="input-group-addon">
        								<span class="glyphicon glyphicon-calendar"></span>
    								</div>
								</div>
                     		</div>
                     		
                     		<div class="col-md-2">
                     			<label>  สถานะ  </label>
    							<select class="form-control form-control-sm" id="select_status">
  									<option value="">--- ทั้งหมด ---</option>
  									<option value="Y">ใช้งาน</option>
  									<option value="N">ไม่ใช้งาน</option>
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
                 
                     <table class="table table-striped table-bordered table-hover order-column table_managed" id="table_checklist">
                         <thead>
                             <tr>
                             	 <th> ลำดับ </th>
                             	 <th>Checklist Id</th>
                                 <th> productTypeId </th> 
                                 <th> ชื่อเช็คลิสต์ </th>
                                 <th> รายละเอียด </th> 
                                 <th>EffectiveDate</th>
                                 <th>ExpireDate</th>                                
                                 <th> สถานะ </th>
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
     <div id="dialog_add_checklist" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close btn-lg" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title">เพิ่มเช็คลิสต์</h4>		    		
	      		</div>
	      		
	      		<div class="modal-body">
	      			<form role="form">
	      				<div class="form-group">
	      					<div class="row">
	      						<div class="col-md-6">
	      							<label>ชื่อเช็คลิสต์</label>
	      							<input class="form-control" placeholder="ชื่อเช็คลิสต์"   type="text" id="checklistName_modal" >
	      						</div>
	      					</div>
	      					
	      				</div>
	      				
	      				<div class="form-group">
	       					<label>สถานะ</label> <br/>           			
	           				<input type="checkbox" name="dal_chb_activeSatatus" data-on-text="ใช้งาน" data-off-text="ไม่ใช้งาน" id="dal_chb_activeSatatus" checked>	           			
	       				</div>
	       				
	       				<div class="form-actions">
	                		<button type="button" id="btn_action_modal" class="btn green">บันทึก</button>
	            		</div>
	       			
	      			</form>
	      		</div>
	      		
			</div>
		</div>
	 </div>
     <%-- Dialog --%>
     
     

     

     
     
     
     <form action="${context}/checklist_form.jsp" method="post" id="link_ChecklistForm_form" name="link_ChecklistForm_form">
     	<input class="hide" id="checklistId" name="checklistId">
     	<input class="hide" id="checklistName" name="checklistName">
     	<input class="hide" id="checklistStatus" name="checklistStatus">
     </form>         
     
     <script src="assets/js/page_checklist.js" type="text/javascript"></script>   



</body>
</html>