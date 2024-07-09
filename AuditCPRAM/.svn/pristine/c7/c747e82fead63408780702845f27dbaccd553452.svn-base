<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="th.co.gosoft.audit.cpram.model.UserModel"%>
<%@page import="th.co.gosoft.audit.cpram.model.UserGroupModel"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />	
</head>
<body>


    <%
		//Check UserGroupId Permission
			UserModel adminModel = (UserModel)request.getSession().getAttribute("sessionAuthen");
			UserGroupModel groupModel = (UserGroupModel)adminModel.getUserGroupId();
			if(Integer.parseInt(groupModel.getUserGroupId()) > 1){
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
                 <span>การตั้งค่า</span>
             </li>             
         </ul>
         <div class="page-toolbar">
         	<div class="btn-group pull-right">
         		<button type="button" class="btn btn-fit-height green-jungle" id="reload-config" > <i class="fa fa-refresh fa-1x" aria-hidden="true"></i>
         		โหลดการตั้งค่าใหม่                
                </button>
         	</div>
         </div>
         
     </div>
     <!-- END PAGE HEADER-->
     
     
     <div class="row">
     	<div class="col-md-6">
     		<div class="portlet light " id="portlet-config-ldap">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="fa fa-shield"></i><!-- icon-paper-plane -->
                        <span class="caption-subject bold">การตั้งค่า LDAP</span>
                    </div>  
                    <div class="actions hide">
						<a class="btn sbold yellow-soft" id="test_connection_ldap"> 
						 	<i class="fa fa-plug"></i>
							ทดสอบการเชื่อมต่อ                        
	                    </a>
					</div>        
                </div>
                
                
                <div class="portlet-body form">
                	<form id="config-ldap-form">
                   		<table class="table table-bordered table-striped">
		            		<tbody>
		                		<tr>
		                    		<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-exchange fa-1x"></i>&nbsp;&nbsp;	                    
		                    					Domain <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="โดเมนในการเชื่อต่อ LDAP" value="" type="text" id="domain_ldap" name="domain_ldap">
		                        		</div> 
		                    		</td>
		               			</tr>	
		               			<tr>
		                    		<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-search fa-1x"></i>&nbsp;&nbsp;	                    
		                    					SearchBase <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="SearchBase ของ LDAP" value="" type="text" id="SearchBase_ldap" name="SearchBase_ldap">
		                        		</div> 
		                    		</td>
		               			</tr>	   
		               			<tr>
		                    		<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-server fa-1x"></i>&nbsp;&nbsp;	                    
		                    					Server <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="server ของ  LDAP" value="" type="text" id="server_ldap" name="server_ldap">
		                        		</div> 
		                    		</td>
		               			</tr>	  
		               			
		               			<tr>
		                    		<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-user fa-1x"></i>&nbsp;&nbsp;	                    
		                    					Username <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="username ของ  LDAP" value="" type="text" id="username_ldap" name="username_ldap">
		                        		</div> 
		                    		</td>
		               			</tr>
		               			
		               			
		               			<tr>
		                    		<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-key fa-1x"></i>&nbsp;&nbsp;	                    
		                    					Password <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="password ของ  LDAP" value="" type="text" id="password_ldap" name="password_ldap">
		                        		</div> 
		                    		</td>
		               			</tr>
		               			             
		            		</tbody>		      
		        		</table>
		        	</form>
                    <div class="form-actions">
                    	<button type="button" class="btn green" id="btn_save_config_ldap">บันทึกข้อมูล</button>
                    </div>
                        
                </div>
                
            </div>
     	</div>
     	
     	
     	<div class="col-md-6">
     		<div class="portlet light " id="portlet-config-system">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-wrench"></i>
                        <span class="caption-subject bold">การตั้งค่าระบบ</span>
                    </div>         
                </div>
                
                <div class="portlet-body form">
                	<form id="config-system-form">
                		<table class="table table-bordered table-striped">
		            		<tbody>
		                		<!-- <tr>
		                    		<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-key fa-1x"></i>&nbsp;&nbsp;	                    
		                    					SaltedString <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="SaltString ของรหัสผ่าน" value="" type="text" id="SaltedString" name="SaltedString">
		                        		</div> 
		                    		</td>
		               			</tr> -->
		               			<tr>
		               				<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-sitemap fa-1x"></i>&nbsp;&nbsp;	                    
		                    					Path Temp <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="ที่อยู่ของโฟลเดอร์ Temp" value="" type="text" id="path_temp" name="path_temp">
		                        		</div> 
		                    		</td>
		               			</tr>
		               			<tr>
		               				<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-sitemap fa-1x"></i>&nbsp;&nbsp;	                    
		                    					Pathโลโกซัพพลายเออร์ <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="ที่อยู่ของโฟลเดอร์โลโกซัพพลายเออร์" value="" type="text" id="path_supplier_logo" name="path_supplier_logo">
		                        		</div> 
		                    		</td>
		               			</tr>
		               			<tr>
		               				<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-sitemap fa-1x"></i>&nbsp;&nbsp;	                    
		                    					Pathโลโกซีพีแรม <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="ที่อยู่ของโฟลเดอร์โลโกซีพีแรม" value="" type="text" id="path_logo_cpram" name="path_logo_cpram">
		                        		</div> 
		                    		</td>
		               			</tr>
		               			<tr>
		               				<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-calendar-o fa-1x"></i>&nbsp;&nbsp;	                    
		                    					ค่าเริ่มต้นจำนวนวันหมดอายุของเอกสาร <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="กรุณาใส่เป็นจำนวนเต็มเท่านั้น" value="" type="text" id="supplier_standard_document_expire_day_default" name="supplier_standard_document_expire_day_default">
		                        		</div> 
		                    		</td>
		               			</tr>
		               		</tbody>
		               	</table>
		               	
                	</form>
                	<div class="form-actions">
                    	<button type="button" class="btn green" id="btn_save_config_system">บันทึกข้อมูล</button>
                    </div>
                	
               	</div>
            </div>
        </div>
     	
     	
     </div>
     
     
     <div class="row">
        <div class="col-md-6">
     		<div class="portlet light " id="portlet-config-email">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-envelope"></i>
                        <span class="caption-subject bold">การตั้งค่าอีเมล</span>
                    </div>         
                </div>
                
                <div class="portlet-body form">
                	<form id="config-email-form">
                	
                		<wbr>
                		<div class="caption font-blue-ebonyclay">
                			              		
                			<h5 class="caption-subject"><i class="fa fa-bars" aria-hidden="true"></i>
                			การตั้งค่าพื้นฐานการส่งอีเมล</h5>                			
                		</div>
                		
                		<table class="table table-bordered table-striped">
		            		<tbody>
		                		
		               			<tr>
		               				<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-server fa-1x"></i>&nbsp;&nbsp;	                    
		                    					Server SMTP <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="ที่อยู่ของ Server SMTP" value="" type="text" id="smtp_mail_server" name="smtp_mail_server">
		                        		</div> 
		                    		</td>
		               			</tr>
		               			<tr>
		               				<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-plug fa-1x"></i>&nbsp;&nbsp;	                    
		                    					Port SMTP <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="หมายเลขพอร์ต SMTP" value="" type="text" id="smtp_mail_port" name="smtp_mail_port">
		                        		</div> 
		                    		</td>
		               			</tr>
		               			<tr>
		               				<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-paper-plane-o fa-1x"></i>&nbsp;&nbsp;	                    
		                    					Sender Email <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="อีเมล์ผู้ส่ง" value="" type="text" id="smtp_mail_sender" name="smtp_mail_sender">
		                        		</div> 
		                    		</td>
		               			</tr>
		               			<tr style="display: none;">
		               				<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-unlock-alt fa-1x"></i>&nbsp;&nbsp;	                    
		                    					Password Sender
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="รหัสผ่านของอีเมล์ผู้ส่ง" value="" type="password" id="smtp_mail_password_sender" name="smtp_mail_password_sender">
		                        		</div> 
		                    		</td>
		               			</tr>
		               			
		               			<tr>
		               				<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-terminal fa-1x"></i>&nbsp;&nbsp;	                    
		                    					Enable Authentification <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="checkbox-status" type="checkbox" name="status_enable_authentification" data-on-text="True" data-off-text="False" id="status_enable_authentification" checked="checked">
		                        		</div> 
		                    		</td>
		               			</tr>
		               			<tr>
		               				<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-terminal fa-1x"></i>&nbsp;&nbsp;	                    
		                    					Enable Start TLS <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="checkbox-status" type="checkbox" name="status_enable_start_TLS" data-on-text="True" data-off-text="False" id="status_enable_start_TLS" checked="checked">
		                        		</div> 
		                    		</td>
		               			</tr>
		               			
		               		</tbody>
		               	</table>
		               	
		               	
		               	<wbr>
                		<div class="caption font-blue-ebonyclay">
                			<h5 class="caption-subject"><i class="fa fa-bars" aria-hidden="true"></i>
                			การตั้งค่าเนื้อหาของอีเมล์</h5>                			
                		</div>
                		<table class="table table-bordered table-striped">
		            		<tbody>
		                		
		               			<tr>
		               				<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-envelope fa-1x"></i>&nbsp;&nbsp;	                    
		                    					อีเมล์ของผู้ดูแลระบบสำหรับการติดต่อเวลาพบปัญหา <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="อีเมล์ของผู้ดูแลระบบ" value="" type="text" id="mail_admin" name="mail_admin">
		                        		</div> 
		                    		</td>
		               			</tr>
		               			<tr>
		               				<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-phone fa-1x"></i>&nbsp;&nbsp;	                    
		                    					เบอร์โทรสำหรับการติดต่อเวลาพบปัญหา <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="เบอร์โทร" value="" type="text" id="telephone" name="telephone">
		                        		</div> 
		                    		</td>
		               			</tr>
		               			<tr>
		               				<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-map-marker fa-1x"></i>&nbsp;&nbsp;	                    
		                    					ที่อยู่เว็บไซต์สำหรับการเข้าใช้งานระบบ <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="ที่อยู่เว็บไซต์สำหรับการเข้าใช้งานระบบ" value="" type="text" id="url_system" name="url_system">
		                        		</div> 
		                    		</td>
		               			</tr>
		               			              			
		               			
		               			
		               		</tbody>
		               	</table>
                		
		               	
                	</form>
                	<div class="form-actions">
                    	<button type="button" class="btn green" id="btn_save_config_mail">บันทึกข้อมูล</button>
                    </div>
                	
               	</div>
                
                
            </div>
        </div>
     	<div class="col-md-6">
     		<div class="portlet light " id="portlet-config-emergancy">
				<div class="portlet-title">
                	<div class="caption font-green-sharp">
                    	<i class="icon-envelope"></i>
                        <span class="caption-subject bold">การตั้งค่าผู้ดูแลระบบกรณีฉุกเฉิน</span>
                    </div>         
                </div>
                
                <div class="portlet-body form">
                	<form id="config-emergency-form">
		               	
		               	<wbr>
                		<div class="caption font-blue-ebonyclay">
                			<h5 class="caption-subject"><i class="fa fa-bars" aria-hidden="true"></i>
                			การตั้งค่าเนื้อหาของผู้ดูแลระบบกรณีฉุกเฉิน</h5>                			
                		</div>
                		<table class="table table-bordered table-striped">
		            		<tbody>
		                		
		               			<tr>
		               				<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-envelope fa-1x"></i>&nbsp;&nbsp;	                    
		                    					อีเมล์ของผู้ดูแลระบบสำหรับการติดต่อเวลาพบปัญหา <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="อีเมล์ของผู้ดูแลระบบ" value="" type="text" id="mail_emergency" name="mail_emergency">
		                        		</div> 
		                    		</td>
		               			</tr>
		               			<tr>
		               				<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-phone fa-1x"></i>&nbsp;&nbsp;	                    
		                    					เบอร์โทรสำหรับการติดต่อเวลาพบปัญหา <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="เบอร์โทร" value="" type="text" id="telephone_emergency" name="telephone_emergency">
		                        		</div> 
		                    		</td>
		               			</tr>
		               			<tr>
		               				<td style="width:40%"> 
		                    			<div class="row">
		                    				<div class="col-md-12">
		                    					<i class="fa fa-map-marker fa-1x"></i>&nbsp;&nbsp;	                    
		                    					ที่อยู่เว็บไซต์สำหรับการเข้าใช้งานระบบ <label class="require_Asterisk"> &#x2A;</label>
		                    				</div>		                    		
		                    			</div>		                    	                 	
		                    
		                    		</td>
		                    		<td style="width:60%">
		                    			<div class="form-group">
		                        			<input class="form-control" placeholder="ที่อยู่เว็บไซต์สำหรับการเข้าใช้งานระบบ" value="" type="text" id="url_system_emergency" name="url_system_emergency">
		                        		</div> 
		                    		</td>
		               			</tr>	               			              			
		               					               			
		               		</tbody>
		               	</table>
                		
		               	
                	</form>
                	<div class="form-actions">
                    	<button type="button" class="btn green" id="btn_save_config_emergency">บันทึกข้อมูล</button>
                    </div>
                	
               	</div>
                
                
            </div>
        </div>
             
     </div>
     
     
     <script src="assets/js/page_setting.js" type="text/javascript"></script>     
     
</body>
</html>