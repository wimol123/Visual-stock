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
     <script src="assets/pages/scripts/table-datatables-managed.js" type="text/javascript"></script>
     
</head>
<body>

     <!-- BEGIN PAGE HEADER-->
     <div class="page-bar">
         <ul class="page-breadcrumb">
             <li>
                 <i class="icon-home"></i>
                 <a href="dashboard.jsp">Home</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <a href="supplier_message.jsp">Message</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span>Message Form</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
            
     <div class="row">
		<div class="col-md-6">
     		<div class="portlet light ">
				<div class="portlet-title">
                	<div class="caption font-red-sunglo">
                    	<i class="icon-envelope font-red-sunglo"></i>
                        <span class="caption-subject bold"> Message Detail</span>
                    </div>
                </div>
                <div class="portlet-body form">
                
                
                
                   <table class="table table-bordered table-striped">
					<tbody>
		                <tr>
		                    <td style="width:30%">
		                        Sender
		                    </td>
		                    <td style="width:70%"> Admin </td>
		                </tr>
		                <tr>
		                    <td>Type</td>
		                    <td>ทั่วไป</td>
		                </tr>
		                <tr>
		                    <td>Date</td>
		                    <td>05.08.2018</td>
		                </tr>
		                <tr>
		                    <td>Title</td>
		                    <td>นัดเข้า Audit</td>
		                </tr>
		                <tr>
		                    <td>Description</td>
		                    <td>รายละเอียด ...</td>
		                </tr>
				    </tbody>
				   </table>
                
                </div>
            </div>
     	</div>
		<div class="col-md-6">
     		
     		
     		<div class="portlet light ">
				<div class="portlet-title">
                	<div class="caption font-red-sunglo">
                    	<i class="icon-clock font-red-sunglo"></i>
                        <span class="caption-subject bold"> History</span>
                    </div>            
                </div>
                <div class="portlet-body form">
                   	
			     <div class="row">
                	
					<div class="col-md-12">          
	                     <ul class="chats" style="padding-top:15px;">
	                         <li class="out">
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
	                         </li>
	                     </ul>
                     
                	</div>
                	
					<div class="col-md-12 type_general">  
                	</div>
                	
                	
            	</div>
					
					   
                                    
                                    
                     
                </div>
            </div>
            
            
     	</div>
     </div>
     
     
     <div class="row">
		<div class="col-md-6">
		
		
     		<div class="portlet light">
				<div class="portlet-title">
                	<div class="caption font-red-sunglo">
                    	<i class="icon-calendar font-red-sunglo"></i>
                        <span class="caption-subject bold"> Appoint</span>
                    </div>            
                </div>
                <div class="portlet-body form">
     		           
     		           
			      		<div class="form-group" id="radio_group_type">
                              <div class="mt-radio-inline">
                                  <label class="mt-radio mt-radio-outline" > Accept
                                      <input value="1" name="radioType" checked="true" type="radio">
                                      <span></span>
                                  </label>
                                  <label class="mt-radio mt-radio-outline"> Reject
                                      <input value="2" name="radioType" type="radio">
                                      <span></span>
                                  </label>
                              </div>
		                </div>
     		           
     		           <div class="type_appoint">
					      	<div class="form-group">
				            	<label>Change Date</label>
				                <input class="form-control" value="06/08/2018" type="text"> 
				            </div>
				      		<div class="form-group">
			                	<label>Description</label>
			                    <textarea rows="3" class="form-control"></textarea>
			                </div>
	                        <div class="form-actions">
	                            <button type="submit" class="btn green">Submit</button>
	                        </div>
     		           </div>
                </div>
            </div>
     		
     		
     		<div class="portlet light">
				<div class="portlet-title">
                	<div class="caption font-red-sunglo">
                    	<i class="icon-folder-alt font-red-sunglo"></i>
                        <span class="caption-subject bold">  Document</span>
                    </div>            
                </div>
                <div class="portlet-body form">
     		           <div>
					      	<div class="form-group">
				            	<label>File</label>
				                <input type="file" />
				            </div>
				      		<div class="form-group">
			                	<div style="float:left;"><img src="assets/images/icon_word.png" width="50" /></div>
			                	<div style="float:left;margin-left:15px;"><img src="assets/images/icon_excel.png" width="50" /></div>
			                	<div style="float:left;margin-left:15px;"><img src="assets/images/icon_pdf.png" width="50" /></div>
			                	<div style="clear:both"></div>
			                </div>
				      		<div class="form-group">
			                	<label>Description</label>
			                    <textarea rows="3" class="form-control"></textarea>
			                </div>
	                        <div class="form-actions">
	                            <button type="submit" class="btn green">Submit</button>
	                        </div>
     		           </div>
                </div>
            </div>
            
            
     	</div>
     </div>
     
     
	<%-- Dialog --%>
	<div id="dialog_search_supplier" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000">
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
		            	<a  class="btn sbold"  href="admin_supplier.jsp"> Add New Supplier
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
                                 <th> Name </th>
                                 <th> E-mail</th>
                                 <th> Action </th>
                             </tr>
                         </thead>
                         <tbody>
                             <tr class="odd gradeX">
                                 <td> Supplier A </td>
                                 <td>
                                     SupplierA@email.com
                                 </td>
                                 <td>
                                     <button type="button" class="btn btn-default"><i class="fa fa-check"></i> Select</button>
                                 </td>
                             </tr>
                             <tr class="odd gradeX">
                                 <td> Supplier B </td>
                                 <td>
                                     SupplierB@email.com
                                 </td>
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
	
	                              
			 
     <script  type="text/javascript">
	   	$('#radio_group_type input').on('change', function() {
	   		var type = $('input[name=radioType]:checked').val();

			$(".type_appoint").hide();

	   		if (type==2){
				$(".type_appoint").show();
			}
	   		
	   		
		});
		$(".type_appoint").hide();
   </script>
   
</body>
</html>