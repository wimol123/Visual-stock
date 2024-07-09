<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	 <c:set var="context" value="${pageContext.request.contextPath}" />
     <link href="${context}/assets/css/custom.css" rel="stylesheet" type="text/css" />  
     <link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
     <link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
     <link href="assets/css/fix_datatable.css" rel="stylesheet" type="text/css" />
     <link href="${context}/assets/css/form_wizard.css" rel="stylesheet" type="text/css" />
     
     <script src="${context}/assets/global/plugins/jquery.min.js" type="text/javascript"></script>  
     <script src="assets/global/scripts/datatable.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
     <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
     <script src="assets/global/scripts/app.js" type="text/javascript"></script>
     <script src="${context}/assets/global/plugins/jquery-bloodhound/js/bloodhound.js" type="text/javascript"></script>
    <script src="${context}/assets/global/plugins/bootstrap-typeahead/js/bootstrap3-typeahead.js" type="text/javascript"></script>
   
	 <style type="text/css">
	 	.portlet > .portlet-title > .caption-form{
	 		font-size: 16px;
	 		width: 700px;
	 	}
	 	
	 	#dialog_add_template_checklist>.modal-dialog {
  			width: 100%;
  			height: 100%;
  			margin: 0;
  			padding: 0;
		}

		#dialog_add_template_checklist>.modal-dialog>.modal-content {
  			height: auto;
  			min-height: 100%;
  			min-width: 100%;
  			border-radius: 0;
		} 
		
		a {
    		text-decoration: none !important;
		}
		
	 	
	 </style>
</head>
<body>


	<!-- BEGIN PAGE HEADER-->
     <div class="page-bar">
         <ul class="page-breadcrumb">
             <li>
                 <i class="icon-home"></i>
                 <a href="dashboard.jsp">หน้าหลัก</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <a href="template_checklist.jsp">แม่แบบเช็คลิสต์</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span>รายละเอียดแม่แบบเช็คลิสต์</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->
     
     <!--  BEGIN PAGE BODY -->
     
     <div class="row">
     	<div class="col-sm-12">
     		<input class="hide" id="paramReceive" name="paramReceive" value="${param.value_param}">
     		<div class="portlet light">
     			<div class="portlet-title">
     				<div class="caption caption-form">             			
	                    <form class="form-horizontal" id="template-checklist">
    						<div class="form-group">
      							<label class="control-label font-green-sharp col-md-3">ชื่อแม่แบบเช็คลิสต์
      								<span class="required"> * </span> :
      							</label>
      							<div class="col-md-6">
      								<!-- <input type="text" class="form-control" placeholder="กรุณาใส่ชื่อแม่แบบเช็คลิสต์" id="name_template" name="name_template"> -->
     								<div class="form-group">
                                    	<div class="input-group">
                                    		<input type="text" class="form-control" placeholder="กรุณาใส่ชื่อแม่แบบเช็คลิสต์" id="name_template" name="name_template">
                                    		<span class="input-group-btn">
                                    			<button class="btn font-dark edit-name-template hide" type="button" data-toggle="tooltip" data-trigger="hover" title="แก้ไขชื่อแม่แบบ" ><i class="fa fa-pencil fa-lg"></i>แก้ไข  </button>
                                    			<button class="btn font-dark save-name-template hide" type="button" data-toggle="tooltip" data-trigger="hover" title="บันทึกชื่อแม่แบบ" ><i class="fa fa-save fa-lg"></i>บันทึก  </button>
                                    		</span>
                                    	</div>  
                                    	<p class="uniqueIdTemplate hide" id="uniqueIdTemplate"></p>                                              
                                	</div>
      							</div>
      							
    						</div>
	                    </form>
             		</div>
             		
             		<div class="actions">
						<a class="btn sbold green" id="btn-create-template" > เพิ่มหัวข้อการประเมิน <!-- data-toggle="modal" href="#dialog_add_template_checklist" -->
                           	<i class="fa fa-plus"></i>
                        </a>	
                        <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>					
					</div>       		
     			</div>  
     			
     			<div class="portlet-body portlet-body-summary">
     				
     				<div class="portlet light portlet-template hide">
	     				
		     			<div class="portlet-title">
		     				<div class="caption font-green-sharp">
			                    <span class="caption-subject bold uppercase name-topic-section"> GMP </span>
		             		</div>
		             		<div class="actions">			             			
		             											
		                        <div class="dropdown">
		                        	
								    <button class="btn dropdown-toggle" data-toggle="dropdown" >
								    	ตัวเลือก <i class="fa fa-ellipsis-v" aria-hidden="true"></i>								    	
								    </button>
								    <ul class="dropdown-menu pull-right">
								    	<li>
								      		<a class="btn sbold default add-sub-topic" href="javascript:;"> 
	                             				<i class="fa fa-plus"></i> เพิ่มหัวข้อย่อย
	                         				</a>
	                         			</li>
								      	<li>
								      		<a class="btn sbold default add-group-question" href="javascript:;"> 
	                             				<i class="fa fa-plus"></i> เพิ่มกลุ่มคำถาม
	                         				</a>
										</li>
										<li>
								      		<a class="btn sbold default edit-topic" href="javascript:;"> 
	                             				<i class="fa fa-pencil"></i> แก้ไขหัวข้อ
	                         				</a>
										</li>
								      	<li>
								      		<a class="btn sbold default remove-topic" href="javascript:;"> 
	                             				<i class="fa fa-minus"></i> ลบหัวข้อ
	                         				</a>
								      	</li>
								    </ul>
								</div>		                         
		                        
		                        <p class="uniqueTopic hide"></p>	
							</div> 		
							
							
												
		     			</div>
		     					
		     					
		     			<div class="portlet-body form">
		     				
		     				<form>
		     				
		     					<div class="sub-topic-section" style="margin-left:30px;">
		     						<div class="form-group">
		     							<div class="form-section-right">
		     								<a href="javascript:;" class="add-group-question font-green-soft" data-toggle="tooltip" data-trigger="hover"  title="เพิ่มกลุ่มคำถาม" ><i class="fa fa-plus"></i> </a>
		     								&nbsp;
		     								<a href="javascript:;" class="edit-sub-topic font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขหัวข้อย่อย" ><i class="fa fa-pencil"></i> </a>
		     								&nbsp;
		     								<a href="javascript:;" class="remove-sub-topic font-red-thunderbird" data-toggle="tooltip"  data-trigger="hover"  data-placement="left" title="ลบหัวข้อย่อย"><i class="fa fa-times"></i> </a>	
		     								<p class="uniqueSubTopic hide"></p>   								
		     							</div>
		     							<h4 class="form-section bold font-blue-soft name-sub-topic">  สภาพแวดล้อมภายนอก  </h4>
		     							
		     							<div class="group-question" style="margin-left:30px;">
		     								<div class="form-group">
		     									<div class="form-section-right">  
		     										
		     											<!-- <h5 class="name-group-question" style="float:left;display:inline-block;">ที่ตั้ง</h5> -->
		     											
			     									<a class="collapsed font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">
			     								 		<i class="fa fa-angle-down fa-lg rotate-icon"></i>
			     								 	</a>
			     								 	&nbsp;
			     								 	<a href="javascript:;" class="add-question font-green-soft" data-toggle="tooltip" data-trigger="hover" title="เพิ่มคำถาม"><i class="fa fa-plus"></i> </a>
		     										&nbsp;
		     										<a href="javascript:;" class="edit-group-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขกลุ่มคำถาม" ><i class="fa fa-pencil"></i> </a>
		     										&nbsp;
			     								 	<a href="javascript:;"  class="remove-group-question font-red-thunderbird" data-toggle="tooltip" data-placement="left" data-trigger="hover" title="ลบกลุ่มคำถาม"><i class="fa fa-times"></i> </a>
			     											
		     										<p class="uniqueGroupQuestion hide"></p>
		     									 </div>
		     									<h5 class="form-section form-section-left name-group-question"> ที่ตั้ง  </h5>
		     									
		     									<div class="form-group form-body-collapse name-question-section" style="margin-left:30px;">
				     								<ul style="margin-top:5px;"></ul>
				     								<!-- <ul>
				     									<li>ต้องพิจารณาถึงกิจกรรมต่างๆ และสภาพแวดล้อมในบริเวณปฏิบัติงานซึ่งอาจเกิดผลกระทบในทางเสียหาย และต้องกำหนดการป้องกันการปนเปื้อนสู่ผลิตภัณฑ์ กรณีมีการกำหนดการป้องกันการปนเปื้อนในสถานที่ปฏิบัติงาน</li>
				     									<li>อาณาเขตบริเวณทำเลที่ตั้งต้องกำหนดอย่างชัดเจน</li>
				     								</ul> -->
				     							</div>
		     								</div>
		     							</div>
		     						</div>		     						
		     					</div>
		     				
		     						     					
		     				</form>
		     				
		     			</div>     					
	     					
	     			</div>
     				
     				
     				
     			</div>
     			
     		</div>
     		
     	</div>
     </div>

     <div id="dialog_add_template_checklist" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static">	
     	<div class="modal-dialog modal-lg">
     		<div class="modal-content">
     		
     			<div class="modal-body">
     				<div class="portlet light" id="form_wizard">
     					<div class="portlet-title">
	      					<div class="caption font-green-sharp">
                            	<i class=" icon-doc"></i>
                            	<span class="caption-subject font-green bold uppercase"> เพิ่มแม่แบบเช็คลิสต์ -
                            		<span class="step-title"> ขั้นตอนที่ 1 จาก 5 </span>
                            	</span>
                            </div>	      					
	      					<div class="actions">
                                <a class="btn btn-circle btn-icon-only btn-default" data-dismiss="modal" href="javascript:;">
                                   <i class="icon-power"></i>
                                </a>
                            </div>
	      				</div>
	      				
	      				<div class="portlet-body form">
	      					<form class="form-horizontal" action="#" id="form_process" method="POST">	      					
	      						<div class="form-wizard">
	      							<div class="form-body">
	      								
	      							<ul class="nav nav-pills nav-justified steps">
	      							
                                    <li>
                                    	<a href="#tabTopic" data-toggle="tab" class="step">
                                    		<span class="number"> 1 </span>
                                   			<span class="desc">
                                    			<i class="fa fa-check"></i> หัวข้อ 
                                    		</span>
                                    	</a>
                                    	
                                    </li>
                                    <li>
                                    	<a href="#tabSubTopic" data-toggle="tab" class="step">
                                    		<span class="number"> 2 </span>
                                    		<span class="desc">
                                    			<i class="fa fa-check"></i> หัวข้อย่อย 
                                    		</span>
                                    	</a>
                                    </li>
                                    <li>
                                    	<a href="#tabQuestionGroup" data-toggle="tab" class="step">
                                    		<span class="number"> 3 </span>
                                    		<span class="desc">
                                    			<i class="fa fa-check"></i> กลุ่มคำถาม 
                                    		</span>
                                    	</a>
                                    </li>
                                    <li>
                                    	<a href="#tabQuestion" data-toggle="tab" class="step">
                                    		<span class="number"> 4 </span>
                                    		<span class="desc">
                                    			<i class="fa fa-check"></i> คำถาม 
                                    		</span>
                                    	</a>
                                    </li>
                                    <li>
                                    	<a href="#tabSummary" data-toggle="tab" class="step">
                                    		<span class="number"> 5 </span>
                                    		<span class="desc">
                                    			<i class="fa fa-check"></i> สรุป 
                                    		</span>
                                    	</a>
                                    </li>
	      						</ul>
	      						
	      						
	      						<div id="bar" class="progress progress-striped active" role="progressbar">
                                	<div class="progress-bar progress-bar-warning"> </div>
                                </div>
                                
                                
                                <div class="tab-content"> 
                                
                                	<div class="alert alert-danger display-none">
                                    	<button class="close" data-dismiss="alert">&times;</button> กรุณากรอกข้อมูลในช่องด้านล่างให้ครบถ้วน 
                                    </div>
                                    <div class="alert alert-success display-none">
                                    	<button class="close" data-dismiss="alert">&times;</button>  
                                    </div>                               
                                    
                                    <div class="tab-pane" id="tabTopic">                                	
                                    	<div class="form-group">
                                        	<label class="control-label col-md-offset-1 col-md-3">ชื่อหัวข้อ 
                                        		<span class="required"> * </span>
                                        	</label>                                        	
                                        	<div class="col-md-4">
                                        		<input type="text" class="form-control" name="name_topic" id="name_topic" value=""/>
                                        		<span class="help-block">  </span>
                                        	</div>                                       	
                                        </div>  
                                        <div class="form-group">
                                        	<label class="control-label col-md-offset-1 col-md-3">รายละเอียดหัวข้อ 
                                        		
                                        	</label>                                        	
                                        	<div class="col-md-4">
                                        		<textarea rows="3" class="form-control" id="description_topic" name="description_topic"></textarea>
                                        		<span class="help-block">  </span>
                                        	</div>                                       	
                                        </div>  
                                    </div>
                                    
                                    <div class="tab-pane" id="tabSubTopic">
                                    	<div class="form-group">
                                        	<label class="control-label col-md-offset-1 col-md-3">ชื่อย่อยหัวข้อ</label>                                        	
                                        	<div class="col-md-4">
                                        		<input type="text" class="form-control" name="name_sub_topic" id="name_sub_topic" value=""/>
                                        		<span class="help-block">  </span>
                                        	</div>                                       	
                                        </div>  
                                        <div class="form-group">
                                        	<label class="control-label col-md-offset-1 col-md-3">รายละเอียดหัวข้อย่อย 
                                        		
                                        	</label>                                        	
                                        	<div class="col-md-4">
                                        		<textarea rows="3" class="form-control" id="description_sub_topic" name="description_sub_topic"></textarea>
                                        		<span class="help-block">  </span>
                                        	</div>                                       	
                                        </div> 
                                    </div>
                                    
                                    
                                    <div class="tab-pane" id="tabQuestionGroup">
                                    	<div class="form-group">
                                        	<label class="control-label col-md-offset-1 col-md-3">ชื่อกลุ่มคำถาม 
                                        		<span class="required"> * </span>
                                        	</label>                                        	
                                        	<div class="col-md-4">
                                        		<input type="text" class="form-control" name="name_group_question" id="name_group_question" value=""/>
                                        		<span class="help-block">  </span>
                                        	</div>                                       	
                                        </div>  
                                        <div class="form-group">
                                        	<label class="control-label col-md-offset-1 col-md-3">รายละเอียดกลุ่มคำถาม 
                                        		
                                        	</label>                                        	
                                        	<div class="col-md-4">
                                        		<!-- <div class="txtEditor" id='editor'></div> -->
                                        		<textarea rows="3" class="form-control" id="description_group_question" name="description_group_question"></textarea>
                                        		<span class="help-block">  </span>
                                        	</div>         
                                        	                       	
                                        </div> 
                                    </div>
                                    
                                    <div class="tab-pane" id="tabQuestion">
                                    	<div class="repeater">
                                    		<div data-repeater-list="group-a" class="question">
                                    			<div data-repeater-item>
        											
        											<div class="row">
        												<div class="col-md-12">
        													<div class="form-group">
        														<label class="control-label col-md-2">คำถาม 
	                                        						<span class="required"> * </span>
		                                        				</label> 
		                                        				<div class="col-md-9">		                                        					
		                                        					<textarea rows="3" class="form-control question_th txtEditor" name="question" id="question" > <!-- ต้องพิจารณาถึงกิจกรรมต่างๆและสภาพแวดล้อมในบริเวณปฏิบัติงานซึ่งอาจเกิดผลกระทบในทางเสียหาย --></textarea>
		                                        				</div>
		                                        				<div class="col-md-1">
		                                        					<a data-repeater-delete class="btn sbold red" href="javascript:;">
		                             									<i class="fa fa-minus"></i>
		                         									</a>
		                                        				</div>
        													</div>
        													
        												</div>        												
        												
        											</div>
        											
      											</div>
                                    		</div>
                                    		<hr/>
                                    		<div class="form-group">
        										<div class="col-md-2 col-md-offset-10">
        											<a data-repeater-create type="button" class="btn sbold blue">เพิ่มคำถาม
        												<i class="fa fa-plus"></i>
        											</a>
        										</div>
        									</div>                                   		
                                    		
                                    	</div>
                                    	
                                    </div>
                                    
                                    <div class="tab-pane" id="tabSummary">
                                    	<div class="container">
                                    		
                                    		<h3 class="block"> สรุปการสร้างแม่แบบเช็คลิสต์ </h3>
                                    		<h4 class="form-section"> หัวข้อ  </h4>
                                    		
                                    		<div class="form-group">
                                            	<label class="control-label col-md-3"> ชื่อหัวข้อ :</label>
                                            	<div class="col-md-4">
                                            		<p class="form-control-static" id="name_topic_summary"> </p>
                                            	</div>
                                            </div>
                                            <div class="form-group">
                                            	<label class="control-label col-md-3"> รายละเอียดหัวข้อ :</label>
                                            	<div class="col-md-4">
                                            		<p class="form-control-static" id="description_topic_summary"> - </p>
                                            	</div>
                                            </div>
                                            
                                            <h4 class="form-section">  หัวข้อย่อย  </h4>
                                    		<div class="form-group">
                                            	<label class="control-label col-md-3"> ชื่อหัวข้อย่อย :</label>
                                            	<div class="col-md-4">
                                            		<p class="form-control-static" id="name_sub_topic_summary"> </p>
                                            	</div>
                                            </div>
                                            <div class="form-group">
                                            	<label class="control-label col-md-3"> รายละเอียดหัวข้อย่อย :</label>
                                            	<div class="col-md-4">
                                            		<p class="form-control-static" id="description_sub_topic_summary"> - </p>
                                            	</div>
                                            </div>
                                            
                                            
                                            <h4 class="form-section">  กลุ่มคำถาม  </h4>
                                            <div class="form-group">
                                            	<label class="control-label col-md-3"> ชื่อกลุ่มคำถาม :</label>
                                            	<div class="col-md-4">
                                            		<p class="form-control-static" id="name_group_question_summary"> </p>
                                            	</div>
                                            </div>
                                            <div class="form-group">
                                            	<label class="control-label col-md-3"> รายละเอียดกลุ่มคำถาม :</label>
                                            	<div class="col-md-4">
                                            		<p class="form-control-static" id="description_group_question_summary">- </p>
                                            	</div>
                                            </div>
                                            
                                            
                                            <h4 class="form-section">  คำถาม  </h4>     
                                            
                                            <div class="list-group question-section">
                                            	<!-- <div class="list-group-item">
                                            		<h4 class="list-group-item-heading"></h4>
                                            		<p class="list-group-item-text"></p>
                                            	</div>   -->                                         	
                                            	
                                            </div> 
                                            
                                            
                                            
                                    	</div>                                    	
                                    </div>                                    
                                
                                </div>
	      							
	      					</div>
	      					
	      							<div class="form-actions">
                                        <div class="row">
                                            <div class="col-md-offset-9 col-md-3">
                                                    <a href="javascript:;" class="btn default button-previous">
                                                                <i class="fa fa-angle-left"></i> ย้อนกลับ </a>
                                                            <a href="javascript:;" class="btn green button-next"> ต่อไป
                                                                <i class="fa fa-angle-right"></i>
                                                            </a>
                                                            <a href="javascript:;" class="btn green button-submit"> ยืนยัน
                                                                <i class="fa fa-check"></i>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>  
	      					
	      						</div>
	      					</form>
	      				</div>
	      				
     				</div>     			
     			</div>
     			
     			
     		</div>
     	</div>
     </div>
     
     
     
     <%-- Dialog --%>
	<div id="dialog_edit" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
		    <div class="row">
				<div class="col-md-12">
		        	<button type="button" class="close btn-lg" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title">เพิ่มคำถาม</h4>
				</div>
			</div>
	      </div>
	      <div class="modal-body">
	        <form role="form">
	            <div class="form-body">
	            
					<div class="form-group">
			             <label id="label_name_edit"> กลุ่มคำถาม  </label>
			             <input type="text" class="form-control" name="name_edit" id="name_edit"/>
			        </div>
			        
	       				
					<div class="form-group">
	       				<label id="label_description_edit">คำถาม</label>
	           			<textarea rows="3" class="form-control" id="description_edit" name="description_edit"></textarea>
	       			</div>	
	       			
	       			<div class="form-group hide">
	       				<label id="label_uniqueID_edit"> UniqueID  </label>
			             <input type="text" class="form-control" name="unique_edit" id="unique_edit"/>
	       			</div>
                                 
	            
	            
	            </div>	           
	            <div class="form-actions">
	                <button type="button" id="btn_submit_dialog_edit" class="btn green">บันทึก</button>
	            </div>
	        </form>                            
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
	
	
	<%-- Dialog --%>
	<div id="dialog_edit_editor" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
		    <div class="row">
				<div class="col-md-12">
		        	<button type="button" class="close btn-lg" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title">เพิ่มคำถาม</h4>
				</div>
			</div>
	      </div>
	      <div class="modal-body">
	        <form role="form">
	            <div class="form-body">
	            
					<div class="form-group">
			             <label id="label_name_edit"> กลุ่มคำถาม  </label>
			             <textarea rows="3" class="form-control txtEditor" name="name_edit_question" id="name_edit_question"></textarea>
			        </div>
			        
	       				
					<div class="form-group hide">
	       				 <label id="label_uniqueID_edit_question"> UniqueID  </label>
			             <input type="text" class="form-control" name="unique_edit_question" id="unique_edit_question"/>
	       			</div>	
	       			
	            </div>	           
	            <div class="form-actions">
	                <button type="button" id="btn_submit_dialog_edit_editor" class="btn green">บันทึก</button>
	            </div>
	        </form>                            
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>
     
     
     <!--  END PAGE BODY -->
	<script src="${context }/assets/js/page_templatechecklistform.js" type="text/javascript"></script>   

</body>
</html>