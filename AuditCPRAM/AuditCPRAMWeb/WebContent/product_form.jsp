<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                 <a href="product.jsp">ประเภทสินค้า</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span>รายละเอียด ประเภทสินค้า</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
            
     <div class="row">
		<div class="col-md-6">

     		<div class="portlet light option-supplier" id="product-deatil-portlet">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-star font-green-sharp"></i>
                        <span class="caption-subject bold"> ข้อมูลประเภทสินค้า</span>
                    </div>
                    <div class="actions">
                    	<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
                    </div>
                </div>
                <div class="portlet-body form">
                    <form role="form" id="product-type-form">
                        <div class="form-body">
                        	<div class="form-group hide">
			       				<label>รหัสประเภทสินค้า : </label>
			           			<input class="form-control" name="txt_productTypeId" id="txt_productTypeId" type="text" value="${param.hd_productTypeId }"> 
			       			</div>	
				      		<div class="form-group">
			       				<label>ชื่อประเภทสินค้า : </label><span class="required"> * </span>
			           			<input class="form-control" name="txt_productTypeName" id="txt_productTypeName" placeholder="ชื่อประเภทสินค้า" type="text"> 
			       			</div>	
			       			<div class="form-group">
			       				<label>สถานะ : </label><span class="required"> * </span> <br/>           			
			           			<input type="checkbox" name="chb_activeSatatus" data-on-text="ใช้งาน" data-off-text="ไม่ใช้งาน" id="chb_activeSatatus" checked>	           			
			       			</div>
                        </div>
                        <div class="form-actions">
                        	<button type="button" id="btnSaveProduct" class="btn green btnSaveProduct">บันทึก</button>
                        </div>
                        
                    </form>
                </div>
            </div>

            <div class="portlet light option-product portlet-checklist">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-note font-green-sharp"></i>
                        <span class="caption-subject bold"> เช็คลิสต์ </span>
                    </div>
                    <div class="actions">
	                     <div class="btn-group">
	                         <a  class="btn sbold green" href="checklist_form.jsp"> เพิ่มเช็คลิสต์ใหม่
	                         	<i class="fa fa-plus"></i>
	                         </a>
	                     </div>
	                     <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
	                 </div>
                                    
                </div>
                <div class="portlet-body form">
                
                	<div class="form-group">
                     	<div class="row">
                     		<div class="col-md-6">
                     			<label> หัวข้อเช็คลิสต์  </label>
    							<input type="text" class="form-control input-sm" id="txt_ChecklistTitle">    							
                     		</div>    
                     		<div class="col-md-6">
                     			<label style="width:100%"> &nbsp;</label>
                     			<button type="button" class="btn btn-primary btn-sm" style="margin-top:0px;" id="btn_SearchChecklist">
     								<b>ค้นหา</b>
     							</button>
     							<button type="button" class="btn default btn-sm" style="margin-top:0px;" id="btn_ClearSearchChecklist">
     								<b> ล้างข้อมูล </b>
     							</button>
	                     				                     	
                     		</div> 
                     	</div>                     	
                     </div>  
                   	
                     <table class="table table-striped table-bordered table-hover order-column table_managed" id="table_checklist">
                         <thead>
                             <tr>
                             	<th>ChecklistId</th>
                                <th>หัวข้อเช็คลิสต์ </th>
                                <th> เลือก </th>
                             </tr>
                         </thead>
                         <!-- <tbody>
                             <tr class="odd gradeX">
                                 <td> Audit CheckList (Package) </td>
                                 <td>
                                     <div class="btn-group">
                                         <button class="btn btn-xs green dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false"> Actions
                                             <i class="fa fa-angle-down"></i>
                                         </button>
                                         <ul class="dropdown-menu pull-left" role="menu">
                                             <li>
                                                 <a href="javascript:;">
                                                     <i class="icon-close "></i> Remove </a>
                                             </li>
                                         </ul>
                                     </div>
                                 </td>
                             </tr>
                         </tbody> -->
                     </table>                     
                     
                </div>
           	</div>
            
     	</div>
     	
		<div class="col-md-6">     		
     		<div class="portlet light option-product" id="portlet_supplier_mapping_product">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-badge font-green-sharp" ></i>
                        <span class="caption-subject bold uppercase"> ซัพพลายเออร์ </span>
                    </div>
                    <div class="actions">	                     
	                     <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
	                 </div>                                    
                </div>
                <div class="portlet-body form">                
                
                	<div class="form-group">
                     	<div class="row">
                     		<div class="col-md-6">
                     			<label> ชื่อบริษัท  </label>
    							<input type="text" class="form-control input-sm" id="txt_SupplierCompany">    							
                     		</div>    
                     		<div class="col-md-6">
                     			<label> สถานที่  </label>
    							<input type="text" class="form-control input-sm" id="txt_location">    							
                     		</div>
                     	</div>  
                     	<div class="row">
                     		<div class="col-md-6">
                     			<label style="width:100%"> &nbsp;</label>
                     			<button type="button" class="btn btn-primary btn-sm" style="margin-top:0px;" id="btn_SearchSupplier">
     								<b>ค้นหา</b>
     							</button>
     							<button type="button" class="btn default btn-sm" style="margin-top:0px;" id="btn_ClearSearchSupplier">
     								<b> ล้างข้อมูล </b>
     							</button>	                     				                     	
                     		</div> 
                     	</div>                   	
                     </div>                     
                     
                     <table class="table table-striped table-bordered table-hover order-column table_managed" id="table_supplier_mapping_product">
                         <thead>
                             <tr>
                             	<th> ลำดับ  </th>
                             	<th> Supplier Id  </th>
                             	<th> ชื่อบริษัท </th>                             	
                             	<th> สถานที่  </th>                             	
                             	<th> เลือก </th>
                             </tr>
                         </thead>
                     </table>
                </div>
            </div>            
     	</div>     	
     </div>
	
	<%-- Dialog --%>
	<div id="form_add_product" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        
		    <div class="row">
				<div class="col-md-6">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title">Select Supplier</h4>
				</div>
				<div class="col-md-6">
					<div style="float:right">
		            	<a  class="btn sbold"  href="product.jsp"> Add New Supplier
			            	<i class="fa fa-plus"></i>
			            </a>  
		            </div>
				</div>
			</div>
		           
	      </div>
	      <div class="modal-body">
                <table class="table table-striped table-bordered table-hover order-column table_managed">
                    <thead>
                        <tr>
                            <th> Supplier </th>
                            <th> Action </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="odd gradeX">
                            <td> Supplier A </td>
                            <td>
                                <button type="button" class="btn btn-default"><i class="fa fa-check"></i> Select</button>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td> Supplier B </td>
                            <td>
                                <button type="button" class="btn btn-default"><i class="fa fa-check"></i> Select</button>
                            </td>
                        </tr>
                    </tbody>
                </table>                          
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
	
	
	
	
	<script src="assets/js/page_product_form.js?1.6" type="text/javascript"></script>
    <script src="assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script> 
</body>
</html>