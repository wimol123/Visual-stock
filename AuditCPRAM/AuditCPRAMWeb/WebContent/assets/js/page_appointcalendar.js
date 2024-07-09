
var userGroupIdSession;
var userIdSession;
var checkSupplierDataTable = true;
var checkAuditorDataTable = true;

$(function(){
	
	/*$.ajax({
		url: contextPath+'/api/user/get_session_user',
	    type: 'GET',
	    contentType: 'application/json',
	    success : function(data, textStatus, jqXHR) {
	    	userGroupIdSession = data.userGroupId.userGroupId;
	    	userIdSession = data.userId;
	    	loadEventToCalendar();
	    }
	});*/
	
	let url = contextPath+"/api/user/get_session_user";
	ajaxProcess.submit(url, 'GET', null, '', (data)=>{
		if(data.result){
			let usrObj = (JSON.parse(data.message));			
			userGroupIdSession = usrObj.userGroupId.userGroupId;
			userIdSession = usrObj.userId;
			loadEventToCalendar();
		}
	});
	
});


function loadEventToCalendar(){
	let url = contextPath+"/api/appoint/get_appoint_detail/"+JSON.stringify({appointId : ""});
	ajaxProcess.submit(url, 'GET', null, null, (data)=>{
		if(data.result){
			let objAppointList = JSON.parse(data.message);
			console.log(objAppointList);
			$(objAppointList).each(function(i,objAppoint){
				
				let supplierCompany = objAppoint.supplierId.supplierCompany;
	    		let dateAppoint = objAppoint.appointDate;	    		   		
	    		let dateObj = new Date(dateAppoint);
	    		let dateToWeb = dateObj.getDate().toString().padStart(2, '0')+"/"+(dateObj.getMonth()+1).toString().padStart(2, '0')+"/"+dateObj.getFullYear();
	    		let timeToWeb = dateObj.getHours()+":"+dateObj.getMinutes().toString().padStart(2, '0');	    		
	    		
	    		let auditorHistoryList = [];
	    		$(objAppoint.auditorId).each(function(i,objAuditor){
	    			auditorHistoryList.push({
	    				Id : objAuditor.userId,
	    				Name : objAuditor.fullname
	    			});
	    		});	    		
	    		
	    		let entourageHistoryList = [];
	    		$(objAppoint.entourageId).each(function(i,objEntourage){
	    			entourageHistoryList.push({
	    				Id : objEntourage.userId,
	    				Name : objEntourage.fullname
	    			});
	    		});
	    		
	    		let appointObj = {
	    			appointId : objAppoint.appointId,
	    			appointCreateBy : objAppoint.appointCreateBy,
	    			supplierId : objAppoint.supplierId,
	    			appointDate : objAppoint.appointDate,
	    			title : objAppoint.title,
	    			detail : objAppoint.detail,
	    			appointStatusId : objAppoint.appointStatusId,
	    			auditorId : objAppoint.auditorId,
	    			locationId : objAppoint.locationId,
	    			entourageId : objAppoint.entourageId,
	    			groupIdSession : userGroupIdSession,
	    			appointHistoryId : [{
	    				appointId : objAppoint,
	    				title : objAppoint.title,
	    				detail : JSON.stringify({
	    					Detail : objAppoint.detial,
	    					Auditor : auditorHistoryList,
	    					Entourage : entourageHistoryList,
	    					AppointDate : objAppoint.appointDate.split(" ")[0].trim(),
	    					AppointTime : objAppoint.appointDate.split(" ")[1].trim()
	    				}),
	    				appointStatusId : objAppoint.appointStatusId
	    			}]
	    		};
	    		console.log(appointObj);
	    		let date = objAppoint.appointDate.split(" ")[0].trim().split("/");
	    		let dateNewFormat = date[2]+"/"+date[1]+"/"+date[0]+" "+objAppoint.appointDate.split(" ")[1];
	    		date = new Date(dateNewFormat);
	    		$('#calendar').fullCalendar( 'renderEvent',{
	    			id : JSON.stringify(appointObj),
	    			title : supplierCompany,
	    			start : date,
	    			color  : objAppoint.appointStatusId.statusColor.trim()
	    		},true);
	    		
	    		
			});
		}
	});
}



$(document).on('click', '#detailTask', function(){
	let eventId = JSON.parse($('#event_id').val().trim());	
	let url = contextPath +"/api/user/get_session_user";
	ajaxProcess.submit(url, 'GET', null, '',(data)=>{
		let userObj = JSON.parse(data.message);		
		if(userObj.userGroupId.userGroupId == '3' || userObj.userGroupId.userGroupId == '4'){
			$('#detailAppointSupplier').val(URI.encode(JSON.stringify({
				appointId : eventId.appointId,
				appointCreateBy : eventId.appointCreateBy
			})));
			$('#passDataToFormSupplier').submit();		
		}else{
			$('#detailAppoint').val(URI.encode(JSON.stringify({
				appointId : eventId.appointId,
				appointCreateBy : eventId.appointCreateBy
			})));
			$('#passDataToForm').submit();
		}
	});
	
});

/*function loadEventToCalendar(){
	$.ajax({
		url: contextPath+'/api/appoint/appoint_list',
	    type: 'GET',
	    contentType: 'application/json',
	    beforeSend : function(arr, $form, options){
	    	BlockUi.blockPage();
	    },
	    complete : function(){
	    	setTimeout(function(){
	    		BlockUi.unBlockPage();
	    	},500);
	    },
	    success : function(data, textStatus, jqXHR) {		    	
	    	
	    	$.each(data,function(index,val){	   
	    		var supplierCompany = val.supplierId.supplierCompany;
	    		var dateAppoint = val.appointDate;
	    		var arrayAuditor = [];
	    		var dateObj = new Date(dateAppoint);
	    		var dateToWeb = dateObj.getDate().toString().padStart(2, '0')+"/"+(dateObj.getMonth()+1).toString().padStart(2, '0')+"/"+dateObj.getFullYear();
	    		var timeToWeb = dateObj.getHours()+":"+dateObj.getMinutes().toString().padStart(2, '0');
	    		$.each(val.user,function(index,value){
	    			arrayAuditor.push({userId : value.userId,fullName : value.fullName});
	    		});
	    		
	    		var appointId = {
	    			appointId : val.appointId,
	    			appointNameLable : val.appointCreateBy.fullName,
	    			appointNameId : val.appointCreateBy.userId,
	    			auditor : arrayAuditor,
	    			supplierId : val.supplierId.supplierId,
	    			supplierCompany : val.supplierId.supplierCompany,
	    			appointDateTime : dateToWeb + " "+timeToWeb,
	    			title : val.title,
	    			detail : val.detail,
	    			statusId : val.statusId.statusId,
	    			groupIdSession : userGroupIdSession
	    		};
	    		$('#calendar').fullCalendar( 'renderEvent',{
	    			id : JSON.stringify(appointId),
	    			title : supplierCompany,
	    			start : dateAppoint
	    		},true);
	    	});
	    	
	    	$('.input-group.datepicker-dialog').datepicker({
	    		language: "th",
	    		format: 'dd/mm/yyyy',
	    		orientation: "bottom auto",
	    		autoclose: true,
	    		startDate: new Date()
	    	});
	    	$('.input-group.datepicker-dialog').datepicker('setDate', 'today');
	    	
	    	var dates = new Date();            
            dates.setTime(dates.getTime());
            
	    	var clockObj = $('.input-group.clockpicker-dialog').clockpicker({	    		
	    		autoclose: true,
	    	    donetext: 'Done',
	    	    placement: 'bottom',
	    	    'default': 'now'
	    	});
	    	$('#appoint_time_Dialog').val(dates.getHours().toString().padStart(2, '0')+":"+dates.getMinutes().toString().padStart(2, '0'));
	    		    	
	    	$("#auditName_Dialog").tagsinput({	    		
	    		allowDuplicates: false,
	    		itemValue: 'value',
	    		itemText: 'text',
	    		tagClass: 'label label-danger',
	    		freeInput: false
	    	});
	    	loadSession();
		},
		error: function (jqXHR, textStatus, errorThrown) {
			
		},
	});
}


$("#dialog_search_supplier").on('shown.bs.modal', function (e) {
	if(checkSupplierDataTable){
		loadDatableSearchSupplier();
		$('#table_search_supplier').DataTable().columns(2).search('1');
		$('#table_search_supplier').DataTable().draw();
		checkSupplierDataTable = false;
	}
	
});

$('#dialog_search_auditor').on('shown.bs.modal', function (e){
	if(checkAuditorDataTable){
		loadDatableSearchAudit();
		$('#table_search_audit').DataTable().columns(3).search('2');
		$('#table_search_audit').DataTable().draw();
		checkAuditorDataTable = false;
	}
});


function loadDatableSearchAudit(){
	var dataTableAudit = $('#table_search_audit').DataTable({
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": contextPath + "/api/user/datatable/get_user_auditor_list",
	        "type": 'POST',
	        contentType: "application/json",
	        beforeSend : function(arr, $form, options){
		    	BlockUi.blockPosition('#dialog_search_auditor');
		    },
		    complete : function(){		
		    	setTimeout(function(){
		    		BlockUi.unBlockPosition('#dialog_search_auditor');
		    	},500);
		    	
		    },
	        data: function (d) {
	        	
	        	return JSON.stringify(d);
	        }
	    },
	    "language": {
	        "url": "assets/global/plugins/datatables/Thai.json",
	        "processing": "<span class='glyphicon glyphicon-refresh glyphicon-refresh-animate'></span>"
	    },
	    "columns": [
	    	{ "data": "fullName" ,name:"u.fullname|LIKE"},
	    	{ "data": "userId" ,name:"u.user_id|="},
	    	{ "data": "email" ,name:"u.email|LIKE"},	    	
	        { "data": "userGroupId.userGroupId" ,name:"ug.user_group_id|="}
	    ],
	    "columnDefs": [
	    	{
	        	"targets": [1],
	            "visible": false,
	            "searchable": false
	        },
	        {
	        	"targets": [3],
	            "visible": false,
	            "searchable": true
	        },
	        {
	            "searchable": false,
	            "orderable": false,
	            "targets": [4]
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [0,2]
	        },
	        {
	            "targets": 4,
	            "orderable": false,
	            "render": function (data, type, row) {
	            	
	            	
	            	var btnAction = '<button type="button" class="btn btn-default btn-select-audit"><i class="fa fa-check"></i> เลือก</button>';
	            	
	               
	                return btnAction;
	            },
	            "width": "10px"  
	        }
	    ],
	    "order": [[1, 'asc']],
	    "initComplete": function (settings, json) {
	    	
	    	$('.dataTables_filter').remove();
	    }
	});
}

function loadDatableSearchSupplier(){
	var dataTableAudit = $('#table_search_supplier').DataTable({
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"processing": true,
		"ajax": {
	        "url": contextPath + "/api/supplier/datatable/get_supplier_list",
	        "type": 'POST',
	        contentType: "application/json",
	        beforeSend : function(arr, $form, options){
		    	BlockUi.blockPosition('#dialog_search_supplier');
		    },
		    complete : function(){		
		    	setTimeout(function(){
		    		BlockUi.unBlockPosition('#dialog_search_supplier');
		    	},500);
		    	
		    },
	        data: function (d) {return JSON.stringify(d);}
	    },
	    "language": {
	        "url": "assets/global/plugins/datatables/Thai.json",
	        "processing": "<span class='glyphicon glyphicon-refresh glyphicon-refresh-animate'></span>"
	    },
	    "columns": [
	    	{ "data": "supplierCompany" ,name:"supplier_company|LIKE" },
	    	{ "data": "supplierId" ,name:"supplier_id|="},
	    	{
	            "data": "statusId",
	            name : "status_id|=",
	            "render": function (data, type, row) {
	            	
	            	var dpStatus = '<span class="label label-sm label-success"> ใช้งาน </span>';
					if (data == '0' ){
						dpStatus = '<span class="label label-sm label-default"> ไม่ใช้งาน </span>';
					}
					
	                return dpStatus;
	            }
	        },
	        { "data": "userId",
		          "name":"email|LIKE",
		          "width": "100",
		          "render" : function(data, type, row){
		        	  let email = '';
		        	  $(data).each(function(i,val){
		        		  email += val.email;
		        	  });
		        	  return email;
		          }
		    }
	    ],
	    "columnDefs": [
	    	
	        {
	        	"targets": [1],
	            "visible": false,
	            "searchable": false
	        },	 
	        {
	        	"targets": [2],
	            "visible": false,
	            "searchable": true
	        },
	        {
	            "searchable": false,
	            "orderable": false,
	            "targets": [1,4]
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "width" : "100px",
	            "targets": [0,3]
	        },
	        
	        {
	            "targets": 4,
	            "orderable": false,
	            "render": function (data, type, row) {
	            	
	            	
	            	var btnAction = '<button type="button" class="btn btn-default btn-select-supplier"><i class="fa fa-check"></i> เลือก</button>';
	            	
	               
	                return btnAction;
	            },
	            "width": "10px"  
	        }
	    ],
	    "order": [[1, 'asc']],
	    "initComplete": function (settings, json) {
	    	
	    	$('.dataTables_filter').remove();
	    } 
	});
}


function loadSession() {
	$.ajax({
		url : contextPath +"/api/user/get_session_user",
		type : 'GET',
		contentType: "application/json",
		success : function(data){
			$('.appointName_Label').html(data.fullName);
			$('#appointId_Dialog').val(data.userId);
		}
	});
}

$('#dialog_add_appoint').on('shown.bs.modal', function (e){
	var dates = new Date();            
    dates.setTime(dates.getTime());
    $('#appoint_time_Dialog').val(dates.getHours().toString().padStart(2, '0')+":"+dates.getMinutes().toString().padStart(2, '0'));
});

$('#dialog_add_appoint').on('hidden.bs.modal', function (e){
	$('#auditName_Dialog').tagsinput('removeAll');
	$('#auditName_Dialog').val('');
	$('#AuditorId_Dialog').val('');
	$('#supplierCompany_Dialog').val('');
	$('#supplierID_Dialog').val('');
	$('.input-group.datepicker-dialog').datepicker('setDate', 'today');
	$('#appoint_time_Dialog').val('');
	$('#title_Dialog').val('');
	$('#Detail_Dialog').val('');
});

$( document ).on(
	    'DOMMouseScroll mousewheel scroll',
	    '#dialog_add_appoint', 
	    function(){     
	    	var t;
	        window.clearTimeout( t );
	        var t = window.setTimeout( function(){            
	            $('.input-group.datepicker-dialog').datepicker('place')
	            $('.input-group.clockpicker-dialog').clockpicker('place')
	        },100 );        
	    }
	);

$("#dialog_add_appoint").scroll(function() {
    var topPos = $("input[id$='appoint_time_Dialog']").offset().top;
    var leftPos = $("input[id$='appoint_time_Dialog']").offset().left;    
    $(".popover").css("top", topPos + 35);
    $(".popover").css("left", leftPos);
  });

$('#table_search_supplier').on('click','.btn-select-supplier',function(){
	var data = $('#table_search_supplier').DataTable().row( $(this).parents('tr') ).data();	
	var supplierName = data.supplierCompany.trim();
	var supplierId = data.supplierId.trim();
	$('#supplierCompany_Dialog').val(supplierName);
	$('#supplierID_Dialog').val(supplierId);
	$('#dialog_search_supplier').modal('toggle');
});

$('#table_search_audit').on('click','.btn-select-audit',function(){
	var data = $('#table_search_audit').DataTable().row( $(this).parents('tr') ).data();	
	var auditName = data.fullName.trim();
	var auditId = data.userId.trim();
	$('#auditName_Dialog').tagsinput('add', { "value": auditId , "text": auditName});
	$('#auditName_Dialog').val(auditName);
	$('#AuditorId_Dialog').val(auditId);
	$('#dialog_search_auditor').modal('toggle');
});

$('#btn_submit_appoint_dialog').on('click',function(){	
	var msgAlert = '';
	if($('#appointId_Dialog').val() == ''){
		msgAlert += "- ไม่สามารถโหลดข้อมูลผู้สร้างนัดหมายนี้ได้<br/>";
	}
	if($('#auditName_Dialog').val()==''){
		msgAlert += "- กรุณาเลือกผู้ออกตรวจ<br/>";
	}
	if($('#supplierCompany_Dialog').val() == ''){
		msgAlert += "- กรุณาเลือกซัพพลายเออร์<br/>";
	}
	if($('#appoint_date_Dialog').val() == ''){
		msgAlert += "- กรุณาเลือกวันนัด<br/>";
	}
	if($('#appoint_time_Dialog').val() == ''){
		msgAlert += "- กรุณาเลือกเวลานัด<br/>";
	}
	if($('#title_Dialog').val() == ''){
		msgAlert += "- กรุณากรอกหัวข้อ<br/>";
	}
	
	if(msgAlert != ""){
		msgAlert = "กรุณาตรวจสอบข้อมูล<br/>" + msgAlert;
		customMessageDialog("warning", "ตรวจสอบข้อมูล", msgAlert, null);	  
		return false;
	}else{		
		$('.bgloader').show();
    	$('.imgloader').show();
		var appointCreateBy = {userId : $('#appointId_Dialog').val().trim()};
		var supplierId = { supplierId : $('#supplierID_Dialog').val().trim()};
		var auditor = $("#auditName_Dialog").tagsinput('items');
		var arrayAuditor = [];
		var auditorName = [];
		$.each(auditor,function(index,value){
			var objAuditor = {
					userId : value.value,
					fullName : value.text
			}
			auditorName.push(value.text);
			arrayAuditor.push(objAuditor);
		});
		
		var objDetailAppoint = {
			"Topic" : $('#title_Dialog').val().trim(),
			"Detail" : $('#Detail_Dialog').val().trim(),
			"AuditorName" : auditorName,
			"AppointDate" : $('#appoint_date_Dialog').val().trim(),
			"AppointTime" : $('#appoint_time_Dialog').val().trim()
		}
		
		var objForm = {
				appointCreateBy : appointCreateBy,
				user : arrayAuditor,
				supplierId : supplierId,
				appointDate : $('#appoint_date_Dialog').val().trim()+" "+$('#appoint_time_Dialog').val().trim()+":00",
				title : $('#title_Dialog').val().trim(),
				detail : JSON.stringify(objDetailAppoint)
		};
		
		$.ajax({
			url : contextPath + "/api/appoint/insert_appoint",
	        type : 'POST',
	        contentType: "application/json",
	        data : JSON.stringify(objForm),
	        beforeSend : function(arr, $form, options){
	        	BlockUi.blockPosition('#dialog_add_appoint');
		    },
		    complete : function(){	
		    	BlockUi.unBlockPosition('#dialog_add_appoint');
		    },
		    success : function(data, textStatus, jqXHR) {
		    	if(data.result == true){
		    		customMessageDialog("success", "ข้อความ", "สถานะการดำเนินการ <br/> - ทำการเพิ่มการนัดหมายสำเร็จ", null);
		    		$('#calendar').fullCalendar('removeEvents'); 
		    		loadEventToCalendar();
		    		$('#table_appoint').DataTable().draw();
		    		$('#dialog_add_appoint').modal('toggle');
		    	}else{
		    		customMessageDialog("error", "ข้อความ", "สถานะการดำเนินการ <br/> - ทำการเพิ่มการนัดหมายไม่สำเร็จ", null);
		    	}  		    	
  			},
  			error: function (jqXHR, textStatus, errorThrown) {
  				customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
  			},
		});
	}
});



$('#detailTask').on('click',function(){
	
	var objEventId = JSON.parse($('#event_id').val().trim());
	
	var arrayAuditor = [];
	$.each(objEventId.auditor,function(index,value){
		var objAuditor = {
    			auditorId : value.userId,
    			auditorName : value.fullName
    	};
		arrayAuditor.push(objAuditor)
	});
	
	var objSubmitForm = {
		appointId : objEventId.appointId,
		appointNameLable : objEventId.appointNameLable,
		appointNameId : objEventId.appointNameId,
		auditor : JSON.stringify(arrayAuditor),
		supplierId : objEventId.supplierId,
		supplierCompany : objEventId.supplierCompany,
		appointDateTime : objEventId.appointDateTime,
		title : objEventId.title,
		detail : objEventId.detail,
		statusId : objEventId.statusId
	};
	
	$('#detailAppoint').val(encodeURIComponent(JSON.stringify(objSubmitForm)));
	
	$('#passDataToForm').submit();
});
*/