<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<c:set var="context" value="${pageContext.request.contextPath}" />
    <link href="assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css" />
    
    <script src="assets/global/plugins/moment.min.js" type="text/javascript"></script>
    <script src="assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
    <script src="assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
    
    <script src="assets/apps/scripts/calendar.js" type="text/javascript"></script>
</head>
<body>

     <!-- BEGIN PAGE HEADER-->
     <div class="page-bar">
         <ul class="page-breadcrumb">
             <li>
                 <i class="icon-home"></i>
                 <a href="home.jsp">Home</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <a href="admin_appoint.jsp">Appoint</a>
                 <i class="fa fa-angle-right"></i>
             </li>
             <li>
                 <span>Calendar</span>
             </li>
         </ul>
     </div>
     <!-- END PAGE HEADER-->


	<div class="row">
	     <div class="col-md-12">
	         <div class="portlet light portlet-fit  calendar">
				<div class="portlet-title">      
                    <div class="actions">
	                     <div class="btn-group">
	                         <a class="btn sbold default" href="supplier_appoint.jsp"> ดูรายการทั้งหมด
	                             <i class="icon-list"></i>
	                         </a>
	                     </div>
	                 </div> 
                </div>
	             <div class="portlet-title">
	                 <div class="caption">
	                     <i class=" icon-calendar font-green"></i>
	                     <span class="caption-subject font-green sbold uppercase">Calendar</span>
	                 </div>
	             </div>
	             <div class="portlet-body">
	                 <div class="row">
	                     <div class="col-md-12 col-sm-12">
	                         <div id="calendar" class="has-toolbar"> </div>
	                     </div>
	                 </div>
	             </div>
	         </div>
	     </div>
	 </div>



	<%-- Dialog --%>
	<div id="dialog_detail" class="modal fade" tabindex="-1" role="dialog" style="z-index:12000">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
		    <div class="row">
				<div class="col-md-6">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title">รายละเอียด</h4>
				</div>
			</div>
		           
	      </div>
	      <div class="modal-body">
	                     
                  
     			<div class="row">
				<div class="col-md-12">
				        <table class="table table-bordered table-striped">
				            <tbody>
				                <tr>
				                    <td style="width:25%"> นัดโดย </td>
				                    <td style="width:70%">
				                        Auditor A
				                    </td>
				                </tr>
				                <tr>
				                    <td> นัดกับ </td>
				                    <td>
				                        	บริษัท A
				                    </td>
				                </tr>
				                <tr>
				                    <td> วันที่นัด </td>
				                    <td>
				                        01/08/2018  13.00
				                    </td>
				                </tr>
				                <tr>
				                    <td> รายละเอียด </td>
				                    <td>
				                        	ตรวจ Supplier ใหม่
				                    </td>
				                </tr>
				                <tr>
				                    <td> สร้างการนัดหมายโดย </td>
				                    <td>
				                        	Admin
				                    </td>
				                </tr>
				            </tbody>
				        </table>
				        <a class="btn btn-sm green" href="admin_message_form.jsp" > ดูรายละเอียด
                                                    <i class="fa fa-search"></i>
                                                </a>
				    </div>
				    </div>




 
	      </div>
	    </div>
	  </div>
	</div>
	<%-- Dialog --%>


	<script src="${context}/assets/js/page_appointcalendar.js" type="text/javascript"></script>



</body>
</html>