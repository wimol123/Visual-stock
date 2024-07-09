<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="th.co.gosoft.audit.cpram.model.UserModel"%>
<%@page import="th.co.gosoft.audit.cpram.model.UserGroupModel"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	
	<meta charset="utf-8" />
	<link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
	<link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
	<link href="assets/css/fix_datatable.css" rel="stylesheet" type="text/css" />
	<script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
	<script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
	<script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
	<script src="assets/global/scripts/app.js" type="text/javascript"></script> 
	
	<link href="assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
	
     <!-- <script src="assets/pages/scripts/table-datatables-managed.js" type="text/javascript"></script> -->
    <style type="text/css">
    	label {
		    display: inline !important;
		}
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


     <!-- BEGIN PAGE HEADER-->
     <div class="page-bar">
         <ul class="page-breadcrumb">
             <li>
                 <i class="icon-home"></i>
                 <a href="home.jsp">หน้าหลัก</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span>ประเภทสินค้า</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
         <form id="productForm" method="post" action="product_form.jsp">
			<input type="hidden" name="hd_productTypeId" id="hd_productTypeId" />
		</form>	
	<!-- BEGIN PAGE LIST -->
     <div class="row" id="container_list" style="display:none">
         <div class="col-md-12">
             <div class="portlet light ">
             	<div class="portlet-title">
	             	<div class="caption font-green-sharp">
	                    <i class="icon-speech"></i>
	                    <span class="caption-subject bold uppercase"> ข้อมูลประเภทสินค้า </span>	                    
	                </div>             
        			<div class="actions">
						<a class="btn sbold green" data-toggle="modal" href="#dialog_add_product"> เพิ่ม ประเภทสินค้า
                          	<i class="fa fa-plus"></i>
                       	</a>
						<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
					</div>     			
                </div>
                
               	<div class="form-group">
					<div class="row">
						<div class="col-md-4">	
							<label class="control-label">ชื่อประเภทสินค้า</<  </label>
							<input type="text" class="form-control" placeholder="ชื่อประเภทสินค้า"   id="txt_productName" />
						</div>	
						<div class="col-md-4">
							<label class="control-label">สถานะ</label>
							<select class="form-control select2 select2-container select2-container--bootstrap select2-container--below" id="sel_Status" name="sel_Status"  >
								<option value=""> --- ทั้งหมด ---</option>
								<option value="Y">ใช้งาน</option>
								<option value="N">ไม่ใช้งาน</option>
							</select>	
						</div>					
						<!--<div class="col-md-4">
							<label class="control-label">Supplier</label>
							<select class="form-control select2 select2-container select2-container--bootstrap select2-container--below" id="sel_Supplier" name="sel_Supplier"  >
							</select>	
						</div>-->
						<div class="col-md-4">	</div>
					</div>
				</div>
				<div class="form-group">
                	<div class="row">
                		<div class="col-md-12">
               		 		<button type="button" class="btn btn-primary btn-sm" id="btn_SearchSubmit" >  ค้นหา  </button>&nbsp;<button type="button" id="btn_SearchClear" class="btn default btn-sm" > ล้างข้อมูล </button>
                		</div>
                    </div>      
                </div>     
                
                 
                 <div class="portlet-body">
                     <table class="table table-striped table-bordered table-hover order-column table_managed" id="table_product">
                         <thead>
                             <tr>
                             	 <th> ลำดับ </th>
                             	 <th> Id </th>
                                 <th> ชื่อประเภทสินค้า </th>
                                 <th> สถานะ </th>
                                 <th> เลือก </th>
                             </tr>
                         </thead>
                         
                     </table>
                 </div>
             </div>
         </div>
     </div>
     <!-- END PAGE LIST -->	
    
    <%-- Dialog --%>
	<div id="dialog_add_product" class="modal fade" tabindex="-1" data-keyboard="false" data-backdrop="static" role="dialog" style="z-index:12000">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	       	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	       	<h4 class="modal-title">เพิ่มประเภทสินค้า</h4>
	      </div>
	      <div class="modal-body">
	        <form role="form" id="form-add-product-type">
	            <div class="form-body">
					<div class="form-group">
	       				<label>ชื่อประเภทสินค้า</label><span class="required"> * </span>
	           			<input class="form-control" name="dal_txt_productTypeName" id="dal_txt_productTypeName" placeholder="ชื่อประเภทสินค้า" type="text"> 
	       			</div>	
	       			<div class="form-group">
	       				<label>สถานะ</label><span class="required"> * </span> <br/>           			
	           			<input type="checkbox" name="dal_chb_activeSatatus" data-on-text="ใช้งาน" data-off-text="ไม่ใช้งาน" id="dal_chb_activeSatatus" checked>	           			
	       			</div>	
	       			<div class="form-group"></div>
	            </div>
	            <hr>
	            <div class="form-actions">
	                <button type="button" id="dal_btn_saveProduct" class="btn green">บันทึก</button>
	            </div>
	        </form>                               
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
    
	<script src="assets/js/page_product.js?1.5" type="text/javascript"></script>
    <script src="assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>        
</body>
</html>