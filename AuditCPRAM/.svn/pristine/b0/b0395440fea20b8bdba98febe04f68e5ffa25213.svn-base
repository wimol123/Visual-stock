<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="th.co.gosoft.audit.cpram.model.UserModel"%>
<%@page import="th.co.gosoft.audit.cpram.model.UserGroupModel"%>
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
			UserModel adminModel = (UserModel) request.getSession().getAttribute("sessionAuthen");
			UserGroupModel groupModel = (UserGroupModel) adminModel.getUserGroupId();
			if (Integer.parseInt(groupModel.getUserGroupId()) != 1 
					&& Integer.parseInt(groupModel.getUserGroupId()) != 3 
					&& Integer.parseInt(groupModel.getUserGroupId()) != 7
					&& Integer.parseInt(groupModel.getUserGroupId()) != 5) {
				out.println("<script>window.location.replace('/auditsupplier/home.jsp')</script>");
			}
	 	%>
	 	
		<div class="bgLoader"></div>
		<div class="imgLoader"></div>
     	
     	<!-- BEGIN PAGE HEADER -->
     	<div class="page-bar">
         	<ul class="page-breadcrumb">
             	<li>
                 	<i class="icon-home"></i>
                 	<a href="home.jsp">หน้าหลัก</a>
                 	<i class="fa fa-angle-right"></i>
             	</li>
             	<li>
                 	<a href="users.jsp">ผู้ใช้งาน</a>
             	</li>
         	</ul>
     	</div>
     	<!-- END PAGE HEADER-->
     
     	<input type="hidden" id="hd_emp_id" />
     
     	<!-- BEGIN PAGE LIST -->
     	<div class="row" id="container_list" style="display:none">
         	<div class="col-md-12">
             	<div class="portlet light ">
             		<div class="portlet-title">
             			<div class="caption font-green-sharp">
             				<i class="icon-user"></i>
	                    	<span class="caption-subject bold uppercase"> ผู้ใช้งาน </span>
             			</div>
             			<div class="actions">
							<a class="btn sbold green" data-toggle="modal" href="#dialog_add_user" > เพิ่มผู้ใช้งาน
                           		<i class="fa fa-user-plus"></i>
                        	</a>	
                        	<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>					
						</div> 
             		</div>
             
                 	<div class="portlet-body">
                      	<div class="form-group">
                     		<div class="row search-form">
                     			<div class="col-md-3 element-search">
                     				<label>  ชื่อเข้าใช้งาน  </label>
    								<input type="text" class="form-control input-sm" id="username_textbox">
                     			</div>
                     			<div class="col-md-3">
                     				<label>  ชื่อ-นามสกุล  </label>
    								<input type="text" class="form-control input-sm" id="fullname_textbox">
                     			</div>
                     			<div class="col-md-3">
                     				<label>  กลุ่มผู้ใช้  </label>
                     				<select class="form-control form-control-sm select-sm" id="sel_group_search" name="sel_group_search">
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
                     
                     	<div class="table-responsive">
                     		<table class="table table-striped table-bordered table-hover order-column table_managed" id="table_user">
	                         	<thead>
	                             	<tr>
	                             		<th>ลำดับ</th>
	                             		<th>userId</th>
	                                 	<th> ชื่อเข้าใช้งาน </th>
	                                 	<th>employeeId</th>
	                                 	<th> ชื่อ </th>
	                                 	<th>userGroupId</th>                                 
	                                 	<th> กลุ่มผู้ใช้ </th>
	                                 	<th> สถานะ </th>
	                                 	<th> เลือก </th>
	                             	</tr>
	                         	</thead>
	                         	<tbody>
	                         	</tbody>
	                     	</table>
                     	</div>
                 	</div>
             	</div>
         	</div>
     	</div>
     	<!-- END PAGE LIST -->
     
     	<input type="hidden" id="hd_user_id" />
     
    	<%-- Dialog --%>
		<div id="dialog_add_user" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
		    			<div class="row">
							<div class="col-md-12">
		        				<button type="button" class="close btn-lg" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        				<h4 class="modal-title"> 
		        			 		เพิ่มผู้ใช้งาน 	
		        				</h4>
							</div>
						</div>
	      			</div>
	      			<div class="modal-body">
	      				<form role="form" id="user_form" >
                        	<div class="form-body">
				      			<div class="form-group">
				                	<label>กลุ่มผู้ใช้งาน</label><label class="require_Asterisk"> &#x2A;</label>
				                    <select class="form-control select2 select2-container select2-container--bootstrap select2-container--below" id="sel_group_dialog" name="sel_group_dialog" style="width:auto !important" ></select>
				                </div>
				                <div class="form-group form-company-name">
				                	<label>บริษัท</label><label class="require_Asterisk"> &#x2A;</label>
				                    <select class="form-control select2 select2-container select2-container--bootstrap select2-container--below" id="sel_supplier_company_dialog" name="sel_supplier_company_dialog" style="width:auto !important" ></select>
				                </div>
					      		<div class="form-group">
				                	<label>รหัสพนักงาน</label><label class="require_Asterisk option-require"> &#x2A;</label>
				                    <input id="employee_id_dialog" name = "employee_id_dialog" class="form-control" placeholder="รหัสพนักงาน" type="text"> 
				                </div>
				                <div class="form-group form-description">
				                	<label>รายละเอียด</label><label class="require_Asterisk"> &#x2A;</label>
				                	<input id="description_dialog" name = "description_dialog" class="form-control" placeholder="กรุณาใส่รายละเอียด เช่น ฝ่ายบัญชี" type="text"> 
				                </div>
					      		<div class="form-group">
				                	<label>ชื่อ - นามสกุล</label><label class="require_Asterisk"> &#x2A;</label>
				                    <input id="name_dialog" name = "name_dialog" class="form-control" placeholder="ชื่อ - นามสกุล" type="text"> 
				                </div>
					      		<div class="form-group">
				                	<label>ชื่อเข้าใช้งาน</label><label class="require_Asterisk"> &#x2A;</label>
				                	<div class="font-grey-salsa" id="hint-username" style="display: none;">
				                		<span> - Username (login เครื่อง)  </span><label class="require_Asterisk"> &#x2A;</label>
				                	</div>
				                    <input id="userlan_dialog" name="userlan_dialog" class="form-control" placeholder="ชื่อเข้าใช้งาน" type="text"> 
				                </div>
					      		<div class="form-group hide">
				                	<label>รหัสผ่าน</label><label class="require_Asterisk password_require"> &#x2A;</label>
				                	<div class="font-grey-salsa">
							        	<span>- มีความยาวอย่างน้อย 8 ตัวอักษรและไม่เกิน 12 ตัวอักษร และประกอบด้วย  </span><br>
							        	<div style="margin-left: 4em;">
							        		<span>- อักษรตัวใหญ่ (A-Z)&nbsp;และตัวเล็ก (a-z)</span><br>
							        		<span>- ตัวเลข  (0-9)</span> <br>
							        		<span>- สัญลักษณ์ (เช่น *, !, @, $, %, ^ )  </span><br>
							        	</div>
							        	<span>- รหัสผ่านห้ามเป็นภาษาไทย </span><br>
							        </div>
				                    <input id="password_dialog" class="form-control" placeholder="รหัสผ่าน" type="password"> 
				                    <span class="label label-default">ใช้เฉพาะ กรณี login ด้วย Username </span>
				                </div>
					      		<div class="form-group">
				                	<label>อีเมล์</label><label class="require_Asterisk"> &#x2A;</label>
				                    <input id="email_dialog" name="email_dialog" class="form-control" placeholder="อีเมล์" type="text"> 
				                </div>
					      		<div class="form-group">
				                	<label>เบอร์โทรศัพท์</label><label class="require_Asterisk"> &#x2A;</label>
				                    <input name="telephone_dialog" class="form-control" placeholder="เบอร์โทรศัพท์" type="text" id="telephone_dialog"> 
				                </div>
				                <div class="form-group">
		       						<label>สถานะ</label> <br/>           			
		           					<input class="checkbox_active_status" type="checkbox" name="dal_chb_activeSatatus" data-on-text="ใช้งาน" data-off-text="ไม่ใช้งาน" id="status_user_dialog">	           			
		       					</div>
                        	</div>
                  		</form>
                  		<div class="form-actions">
                  			<% if (Integer.parseInt(groupModel.getUserGroupId()) == 3) { %>
                  				<button type="button" id="btn_submit_user" class="btn green"> บันทึก </button>
                  			<% } %>
                  			<% if (Integer.parseInt(groupModel.getUserGroupId()) == 1 
                  					|| Integer.parseInt(groupModel.getUserGroupId()) == 7
                  					|| Integer.parseInt(groupModel.getUserGroupId()) == 5) { %>
                  				<button type="button" id="btn_submit_admin" class="btn green"> บันทึก </button>
                  			<% } %>
                  		</div>
	      			</div>
				</div>
			</div>
		</div> 
		<%-- Dialog --%>
    	
     	<script src="assets/js/page_users.js" type="text/javascript"></script>

	</body>
</html>
