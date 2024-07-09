<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />	
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
                 <span>ข้อมูลส่วนตัว</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
              
     <div class="row">
		<div class="col-md-6">      
              
     		<div class="portlet light " id="portlet-profile">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-layers"></i>
                        <span class="caption-subject bold"> ข้อมูลส่วนตัว</span>
                    </div>         
                </div>
                <div class="portlet-body form">
                <form id="user-form">
                   <table class="table table-bordered table-striped">
		            <tbody>
		                <tr>
		                    <td style="width:40%"> 
		                    	<div class="row">
		                    		<div class="col-md-12">
		                    			<i class="fa fa-users fa-1x"></i>&nbsp;&nbsp;	                    
		                    			กลุ่มผู้ใช้งาน <label class="require_Asterisk"> &#x2A;</label>
		                    		</div>		                    		
		                    	</div>		                    	                 	
		                    
		                    </td>
		                    <td style="width:60%">
		                    	<div class="row">
		                    		<div class="col-md-12">
		                    			<h5 id="user_group_name">Administrator</h5>
		                    		</div>
		                    	</div>
				            	
		                    </td>
		                </tr>
		                <tr>
		                    <td> 
		                    	<div class="form-group">
			                    	<div class="row">
			                    		<div class="col-md-12">
			                    			<i class="fa fa-user fa-1x"></i>&nbsp;&nbsp;&nbsp;		                    
			                    			ชื่อเข้าใช้งาน  <label class="require_Asterisk"> &#x2A;</label>
			                    		</div>		                    		
			                    	</div>
		                    	</div>
							</td>
		                    <td>
		                    	<div class="form-group">
		                        	<input class="form-control" placeholder="ชื่อเข้าใช้งานสำหรับเข้าใช้งานระบบ" value="" type="text" readonly="readonly" id="username" name="username">
		                        </div> 
		                    </td>
		                </tr>
		                <tr>
		                    <td> 
		                    	<div class="row">
		                    		<div class="col-md-12">
		                    			<i class="fa fa-key fa-1x"></i>&nbsp;&nbsp;&nbsp;		                    
		                    			รหัสผ่าน
		                    		</div>		                    		
		                    	</div>		                    	 
		                    </td>
		                    <td>
		                    	<div class="form-group">
				            		<input class="form-control" placeholder="รหัสผ่านสำหรับเข้าใช้งานระบบ" type="password" id="password" name="password"> 
				            	</div>
		                    </td>
		                </tr>
		                <tr>
		                    <td> 
		                    	<div class="row">
		                    		<div class="col-md-12">
		                    			<i class="icon-book-open"></i>&nbsp;&nbsp;&nbsp;		                    
		                    			 ชื่อ <label class="require_Asterisk"> &#x2A;</label> 
		                    		</div>		                    		
		                    	</div>	
							</td>
		                    <td>
		                    	<div class="form-group">
		                    		<input class="form-control" placeholder="ชื่อสำหรับใช้แสดงในระบบ" type="text" id="fullname" name="fullname">
		                    	</div>
		                    </td>
		                </tr>
		                <tr>
		                    <td>
		                    	<div class="row">
		                    		<div class="col-md-12">
		                    			<i class="fa fa-envelope fa-1x"></i>&nbsp;&nbsp;&nbsp;		                    
		                    			 อีเมล์  <label class="require_Asterisk"> &#x2A;</label> 
		                    		</div>		                    		
		                    	</div>	
		                    	
		                    </td>
		                    <td>
		                    	<div class="form-group">
		                    		<input class="form-control" placeholder="อีเมล์ที่ใช้ที่ต่อภายในระบบ" type="text" id="email" name="email">
		                    	</div>
		                    	
		                    </td>
		                </tr>
		                <tr>
		                    <td> 
		                    	<div class="row">
		                    		<div class="col-md-12">
		                    			<i class="fa fa-phone-square fa-1x"></i>&nbsp;&nbsp;&nbsp;		                    
		                    			<label>เบอร์โทรศัพท์</label> <label class="require_Asterisk"> &#x2A;</label>
		                    		</div>		                    		
		                    	</div>	
							</td>
		                    <td>
		                    	<div class="form-group">
		                    		<input class="form-control" placeholder="เบอร์โทรศัพท์ที่ใช้ติดต่อ" type="text" id="telephone" name="telephone">
		                    	</div>
		                    	
		                    </td>
		                </tr>
		            </tbody>
		      
		        	</table>
		        </form>
		        		<div style="text-align:center">
		        			<a data-target="#pdpaModal" data-toggle="modal" href="pdpaModal" >บันทึก การยินยอมจาก Supplier ในการใช้ข้อมูลส่วนบุคคล</a>
		        		</div>
		        		                        
                        <div class="form-actions">
                            <button type="button" class="btn green" id="btn_save_update">บันทึกข้อมูล</button>
                        </div>
                        
                </div>
            </div>
		
     	</div>
     	
     	
	</div>
	
	<%-- Modal --%>
	
	<div class="modal fade" id="pdpaModal" tabindex="-1" role="dialog" aria-labelledby="pdpaModalTitle" aria-hidden="true">
  		<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable" role="document" style="width:60%;">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h4 class="modal-title" id="pdpaModal Title" style="display: inline;" >PDPA</h1>
	        		<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="display: inline;">
	          			<span aria-hidden="true">&times;</span>
	        		</button>
      			</div>
      			<div class="modal-body" id="PdpaMassage" >
      				<div style='word-wrap: break-word;' >
      					<iframe src="" height="900" width="100%" id="iframeLink"></iframe>
      				</div>
      			</div>
      			<div class="modal-footer">
      				
      				<div id="PdpaDetail" style="text-align:center">
						
      				</div>
      				<br/>
        			<button type="button" class="btn btn-secondary" data-dismiss="modal">ปิด</button>
      			</div>
    		</div>
  		</div>
	</div>
	
	
     <script src="assets/js/page_profile.js" type="text/javascript"></script>
     	
     
</body>
</html>