var supplierDataTable = null;
var auditorDataTable = null;
var entourgeDataTable = null;
var locationProduceDataTable = null;
var validateFormChecklistPlan = null;
var supplierList = null;
var locationList = null;

$(function(){
	$('.form-group-location').hide();
	init.checklistPlanDataTable();
	//init.loadSession();
	init.loadDateTimePicker();
	loadSupplierAutoComplete.init();
	
	$("#auditName_Dialog").tagsinput({	    		
		allowDuplicates: false,
		itemValue: 'value',
		itemText: 'text',
		tagClass: 'label label-success',
		freeInput: false
	});
	
	$("#entourge_Dialog").tagsinput({	    		
		allowDuplicates: false,
		itemValue: 'value',
		itemText: 'text',
		tagClass: 'label label-info',
		freeInput: false
	});
	
	let objectValidate = {
			element : '#checklistplan_form',
			require : ['appointCreateId_Dialog','auditName_Dialog','AuditorId_Dialog','supplierID_Dialog','appoint_date','appoint_time','title_Dialog'],
			message : ["ไม่สามารถโหลดข้อมูลได้","กรุณาเลือกผู้ออกตรวจ","กรุณาเลือกผู้ออกตรวจ","กรุณาเลือกซัพพลายเออร์","กรุณาเลือกวันนัด","กรุณาเลือกเวลานัด","กรุณาใส่หัวข้อ"]
		}
		validateFormChecklistPlan = validate.init(objectValidate);
		
		$.validator.addMethod("checkTags", function(value) {    
	        return ($("#auditName_Dialog").tagsinput('items').length > 0);
	    },"กรุณาใส่รายชื่อผู้ตรวจประเมิน");
		
		$( "#dummy_auditName" ).rules( "add", {
			checkTags: true
		});
		
		$.validator.addMethod("checkSupplier", function(value) {    
	        return ($('#supplierCompany_Dialog').typeahead('val') != '');
	    },"กรุณาเลือกซัพพลายเออร์");
		
		$( "#dummy_supplierCompany" ).rules( "add", {
			checkSupplier: true
		});
		
		$.validator.addMethod("checkLocation", function(value) {    
	        return ($('#supplierLocation_Dialog').typeahead('val') != '');
	    },"กรุณาเลือกสถานที่ผลิตของซัพพลายเอร์");
		
		$( "#dummy_Location" ).rules( "add", {
			checkLocation: true
		});
		
	$(".bootstrap-tagsinput > input").prop("readonly", true);
	
	$('.input-group.datepicker-form').datepicker({
		language: "th",
		format: 'dd/mm/yyyy',
		orientation: "bottom auto",
		autoclose: true,
		clearBtn : true
	});
});

$("#dialog_add_checklistplan").on('hidden.bs.modal', function (e) {
	$("#auditName_Dialog").tagsinput('removeAll');
	$('#AuditorId_Dialog').val('');
	$('#entourge_Dialog').tagsinput('removeAll');
	$('#supplierCompany_Dialog').typeahead('val','');
	$('.form-group-location').hide();
	$('#title_Dialog').val('');
	$('#Detail_Dialog').val('');
	validateFormChecklistPlan.resetForm();
	
	$($('#checklistplan_form').find('.form-group')).each(function(){
		if($(this).hasClass('has-error')){
			$(this).removeClass('has-error');
		}
	});	
	init.loadDateTimePicker();
});

$('#btn_submit_checklistplan').on('click',function(){
	
	let checkInputSupplier = false;
	let checkInputLocationProduce = false;
	$(supplierList).each(function(i,v){
		if(v.supplierCompany == $('#supplierCompany_Dialog').typeahead('val').trim() && v.supplierId == $('#supplierID_Dialog').val().trim()){
			checkInputSupplier = true;
		}
	});
	if(!checkInputSupplier){
		$('#supplierCompany_Dialog').typeahead('val','');
		$('#supplierID_Dialog').val('');
	}
	
	$(locationList).each(function(i,v){
		if(v.locationName == $('#supplierLocation_Dialog').typeahead('val').trim()){
			checkInputLocationProduce = true;
		}
	});
	
	if(!checkInputLocationProduce){
		$('#supplierLocation_Dialog').typeahead('val','');
		$('#LocationID_dialog').val('');
	}
	
	if($('#checklistplan_form').valid()){
		let auditorList = [];
		$($("#auditName_Dialog").tagsinput('items')).each(function(i,v){
			auditorList.push({
				auditorId : {
					userId : v.value,
					fullname : v.text
				},
				assignPlanStatusId : {assignPlanStatusId  : "1"}
			});
		});
		
		let entourageList = [];
		$($("#entourge_Dialog").tagsinput('items')).each(function(i,v){
			entourageList.push({
				userId : v.value,
				fullname : v.text
			});
		});
		
		let objChecklistPlan = {
			supplierId : {supplierId : $('#supplierID_Dialog').val().trim(), supplierCompany : $('#supplierCompany_Dialog').typeahead('val')},
			supplierCompany : $('#supplierCompany_Dialog').typeahead('val'),
			checklistTypeId : {checklistTypeId : '1'},
			checklistTypeName : 'Audit Supplier',
			locationId : {id : $('#LocationID_dialog').val().trim(), locationName : $('#supplierLocation_Dialog').val().trim()},
			planDate : $('#appoint_date').val().trim() + " " + $('#appoint_time').val().trim()+":00",
			checklistPlanStatusId : {checklistPlanStatusId : "1"},
			enable : "Y",
			assignPlanId : auditorList,
			checklistPlanEntourageId : entourageList
		};
		
		let url = contextPath+'/api/checklist_plan/insert_checklist_plan';
		ajaxProcess.submit(url, 'POST', objChecklistPlan, '#dialog_add_checklistplan', (data)=>{
			if(data.result){
				$('#dialog_add_checklistplan').modal('hide');
				$('#table_ChecklistPlan').DataTable().draw();
			}
		});
	}
});

$(document).on('click','.seeMoreChecklistPlan',function(){
	let data = $('#table_ChecklistPlan').DataTable().row( $(this).parents('tr') ).data();	
	$('#checklistPlanDetail').val(URI.encode(JSON.stringify(data)));
	$('#checklistPlanDetail').closest('form').submit();
});

$(document).on('click', '.cancel-checklist-plan', function(){
	let data = $('#table_ChecklistPlan').DataTable().row( $(this).parents('tr') ).data();
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการยกเลิกแผนการเข้าตรวจนี้ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){				
			let url = contextPath+'/api/checklist_plan/cancel_checklist_plan';
			ajaxProcess.submit(url, 'POST', data, null, (data)=>{
				if(data.result){
					$('#table_ChecklistPlan').DataTable().draw();				
				}
			});
		}
	});
	
});



keyUp.enter('#checklist_plan_no_search', ()=>{
	search();
});

keyUp.enter('#checklist_plan_title_search', ()=>{
	search();
});

keyUp.enter('#supplier_company_search', ()=>{
	search();
});

keyUp.enter('#plan_date_textbox', ()=>{
	search();
});

$('#btn_search').on('click',function(){
	search();
});

$('#btn_clear').on('click',function(){
	clearSearch();
});


function search(){
	$('#table_ChecklistPlan').DataTable().columns(1).search($('#checklist_plan_no_search').val().trim());
	$('#table_ChecklistPlan').DataTable().columns(2).search($('#checklist_plan_title_search').val().trim());
	$('#table_ChecklistPlan').DataTable().columns(3).search($('#supplier_company_search').val().trim());
	$('#table_ChecklistPlan').DataTable().columns(5).search($('#plan_date_textbox').val().trim());	
	$('#table_ChecklistPlan').DataTable().columns(6).search($('#checklist_plan_status').val().trim());
	$('#table_ChecklistPlan').DataTable().draw();
}

function clearSearch(){
	$('#checklist_plan_no_search').val('');
	$('#checklist_plan_title_search').val('');
	$('#supplier_company_search').val('');
	$('#plan_date_textbox').val('');
	$('#checklist_plan_status').val('');
	
	$('#table_ChecklistPlan').DataTable().columns(1).search('');
	$('#table_ChecklistPlan').DataTable().columns(2).search('');
	$('#table_ChecklistPlan').DataTable().columns(3).search('');
	$('#table_ChecklistPlan').DataTable().columns(4).search('');
	$('#table_ChecklistPlan').DataTable().columns(5).search('');
	$('#table_ChecklistPlan').DataTable().columns(6).search('');
	$('#table_ChecklistPlan').DataTable().draw();
}

var init = function(){
	return{
		checklistPlanDataTable : function(){
			$('#table_ChecklistPlan').DataTable({
				"proccessing": true,
			    "serverSide": true,
			    "searching": true,
			    "ajax": {
			        "url": contextPath + "/api/checklist_plan/datatabale/checklist_plan_list",
			        "type": 'POST',
			        beforeSend: function(){ BlockUi.blockPage(); },
			        contentType: 'application/json',
			        data: function ( d ) {
			            return JSON.stringify(d);
			        },
			        complete : function(){  	    	
			        	setTimeout(function(){ 
			  	    		BlockUi.unBlockPage();
						  }, 500)
			  	    }
			    },    
			    "language": {
			        "url": "assets/global/plugins/datatables/Thai.json"
			    },
			    "columns": [
			        { "data": null, "width": "30px" },
			        { "data" : "checklistPlanNo", name : "checklistPlanNo"},
			        { "data": "checklistTitle", name: "checklistTitle" },
			        { "data": "supplierId.supplierCompany", name: "supplierCompany"},
			        { "data": "productTypeName", name: "productTypeName"},
			        { "data": "planDate", name: "planDate"},
			        {
			            "data": "checklistPlanStatusId",
			            "render": function (data, type, row) {
			            	let dpStatus = '<span class="label" style="background-color:'+data.statusColor+';font-size:12px;"> '+data.checklistPlanStatusName+' </span>';			            	
			                return dpStatus;
			            },
			            //"width": "150px",
			            name: "checklistPlanStatusId"
			        }
			    ],
			    "columnDefs": [
			        {
			            "searchable": false,
			            "orderable": false,
			            "targets": 0,
			            "render": function (data, type, full, meta) {
			                return meta.settings._iDisplayStart + meta.row + 1;
			            }
			        },
			        {
			            "searchable": true,
			            "orderable": false,
			            "targets": [1,2,3,4,5,6]
			        },
			        {
			            "targets": 7,
			            "orderable": false,
			            "render": function (data, type, row) {
			            	
			            	var btnAction = '<div class="btn-group">';
			            	btnAction += '<button class="btn btn-xs blue dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false" style="font-size : 12px;"> เลือก <i class="fa fa-angle-down"></i></button>';
			    			btnAction += '<ul class="dropdown-menu pull-right" role="menu">';
			    			btnAction += 	'<li><a href="javascript:void(0);" class = "seeMoreChecklistPlan">';//ChangePage
			    			btnAction +=   		'<i class="icon-magnifier"></i> ดูรายละเอียด </a>';
			    			btnAction +=	'</li>';
			    			if(row.checklistPlanStatusId.checklistPlanStatusId == "1"){
			    				let url= contextPath+'/api/user/get_session_user'
			    				ajaxProcess.submit(url, 'GET', null, '', (data)=>{
			    					let objSession = JSON.parse(data.message);
			    					if(objSession.userGroupId.userGroupId == '2'){
			    						btnAction += 	'<li><a href="javascript:void(0);" class = "cancel-checklist-plan">';//ChangePage
						    			btnAction +=   		'<i class="fa fa-ban"></i> ยกเลิกแผนการเข้าตรวจ </a>';
						    			btnAction +=	'</li>';
			    					}
			    				},{async : false});			    				
			    			}
			                btnAction += '</ul>';
			                btnAction +='</div>';
			                return btnAction;
			            },
			            //"width": "200px" 
			        }
			    ],
			    "order": [],
			    "initComplete": function (settings, json) {
			    	$(".dataTables_filter").remove();
			    	init.loadSession();
			    }
			});
		},
		loadSession : function(){
			let url= contextPath+'/api/user/get_session_user'
			ajaxProcess.submit(url, 'GET', null, '', (data)=>{
				let objSession = JSON.parse(data.message);
				$('.userCreateChecklistPlan_Label').text(objSession.fullname);
				$('#userCreateChecklistPlan_ID').text(objSession.userId);
				if(objSession.userGroupId.userGroupId == '2'){
					$('a[href="#dialog_add_checklistplan"]').slideDown('slow');
				}else if(objSession.userGroupId.userGroupId == '3' || objSession.userGroupId.userGroupId == '4' || objSession.userGroupId.userGroupId == '5'){
					$('.cancel-checklist-plan').remove();
				}
			});
		},
		loadDateTimePicker : function(){
			dateTime.currentDateTime('yyyy/MM/dd HH:mm:ss',(currentTimeDate)=>{
				let dateCurrent = new Date();
				dateCurrent.setDate(currentTimeDate.getDate());
				let timeCurrent = new Date();
				timeCurrent.setTime(currentTimeDate.getTime());
				
				$('.input-group.datepicker-dialog').datepicker({
					language: "th",
					format: 'dd/mm/yyyy',
					orientation: "top auto",
					autoclose: true,
					startDate: currentTimeDate
				});
				//$('.input-group.datepicker-dialog').datepicker('setDate', 'today');
				$('.input-group.datepicker-dialog').datepicker('setDate', dateCurrent);
				
				var clockObj = $('.clockpicker-dialog').clockpicker({	    		
					autoclose: true,
				    donetext: 'Done',
				    placement: 'top',
				    'default': timeCurrent
				});
				$('#appoint_time').val(timeCurrent.getHours().toString().padStart(2, '0')+":"+timeCurrent.getMinutes().toString().padStart(2, '0'));
				
			});
		}
	}
}();

$("#dialog_search_auditor").on('shown.bs.modal', function (e) {
	if(auditorDataTable == null){
		loadTableAuditor();
	}	
});



$('#btn_search_auditor_table').on('click',function(){
	searchAuditorTable();
});

keyUp.enter('#auditor_name_dialog', ()=>{
	searchAuditorTable();
});

keyUp.enter('#auditor_email_dialog', ()=>{
	searchAuditorTable();
});

function searchAuditorTable(){
	$('#table_search_audit').DataTable().columns(0).search($('#auditor_name_dialog').val().trim());
	$('#table_search_audit').DataTable().columns(2).search($('#auditor_email_dialog').val().trim());
	$('#table_search_audit').DataTable().draw();
}

$('#btn_clear_auditor_table').on('click',function(){
	$('#auditor_name_dialog').val('');
	$('#auditor_email_dialog').val('');
	$('#table_search_audit').DataTable().columns(0).search('');
	$('#table_search_audit').DataTable().columns(2).search('');
	$('#table_search_audit').DataTable().draw();
});


$('#table_search_audit').on('click','.btn-select-audit',function(){
	let data = $('#table_search_audit').DataTable().row( $(this).parents('tr') ).data();	
	let auditName = data.fullname.trim();
	let auditId = data.userId.trim();
	
	$($('#entourge_Dialog').tagsinput('items')).each(function(i,objTagsinput){
		if(objTagsinput.value == auditId && objTagsinput.text == auditName){
			$('#entourge_Dialog').tagsinput('remove', objTagsinput);
			return false;
		}
	});
	
	$('#auditName_Dialog').tagsinput('add', { "value": auditId , "text": auditName});
	$('#auditName_Dialog').val(auditName);
	$('#AuditorId_Dialog').val(auditId);
	$('#dialog_search_auditor').modal('toggle');
});

function loadTableAuditor(){
	auditorDataTable = $('#table_search_audit').DataTable({
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": contextPath + "/api/user/datatable/get_user_auditor_type",
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
	        "url": "assets/global/plugins/datatables/Thai.json"
	    },
	    "columns": [
	    	{ "data": "fullname" ,name:"fullname"},
	    	{ "data": "userId" ,name:"user_id"},
	    	{ "data": "email" ,name:"email"},	    	
	        { "data": "userGroupId.userGroupId" ,name:"user_group_id"}
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
	    "order": [],
	    "initComplete": function (settings, json) {
	    	
	    	$('.dataTables_filter').remove();
	    }
	});
}


$('#btn_search_entourge_table').on('click',function(){
	searchEntourgeTable();
});

$('#btn_clear_entourge_table').on('click',function(){
	clearSearchEntourgeTable();
});

keyUp.enter('#entourge_name_dialog',()=>{
	searchEntourgeTable();
});

keyUp.enter('#entourge_email_dialog',()=>{
	searchEntourgeTable();
});

keyUp.enter('#entourge_group_dialog',()=>{
	searchEntourgeTable();
});

function searchEntourgeTable(){
	$('#table_search_entourge').DataTable().columns(0).search($('#entourge_name_dialog').val().trim());
	$('#table_search_entourge').DataTable().columns(1).search($('#entourge_email_dialog').val().trim());
	$('#table_search_entourge').DataTable().columns(2).search($('#entourge_group_dialog').val().trim());
	$('#table_search_entourge').DataTable().draw();
}

function clearSearchEntourgeTable(){
	$('#entourge_name_dialog').val('');
	$('#entourge_email_dialog').val('');
	$('#entourge_group_dialog').val('');
	$('#table_search_entourge').DataTable().columns(0).search('');
	$('#table_search_entourge').DataTable().columns(1).search('');
	$('#table_search_entourge').DataTable().columns(2).search('');
	$('#table_search_entourge').DataTable().draw();
}

$("#dialog_search_entourge").on('shown.bs.modal', function (e) {
	if(entourgeDataTable == null){
		loadTableEntourge();
	}
	
});


$('#dialog_search_entourge').on('click','.btn-select-entourge',function(){
	let data = $('#table_search_entourge').DataTable().row( $(this).parents('tr') ).data();	
	let entourgeName = data.fullname.trim();
	let entourgeId = data.userId.trim();
	
	let checkDupplicate = false;
	$($('#auditName_Dialog').tagsinput('items')).each(function(i,objTagsinput){
		if(objTagsinput.value == entourgeId && objTagsinput.text == entourgeName){
			customMessageDialog('warning',"เกิดข้อผิดพลาด", "ไม่สามารถเลือกผู้ติดตามคนนี้ได้ เนื่องจากมีรายชื่อนี้ในผู้ออกตรวจแล้ว",null);
			checkDupplicate = true;
			return false;
		}
	});
	
	if(!checkDupplicate){
		$('#entourge_Dialog').tagsinput('add', { "value": entourgeId , "text": entourgeName});
		$('#entourge_Dialog').val(entourgeName);
		$('#dialog_search_entourge').modal('toggle');
	}
	
});

function loadTableEntourge(){
	entourgeDataTable = $('#table_search_entourge').DataTable({
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": contextPath + "/api/user/datatable/entourge_list",
	        "type": 'POST',
	        contentType: "application/json",
	        beforeSend : function(arr, $form, options){
		    	BlockUi.blockPosition('#dialog_search_entourge');
		    },
		    complete : function(){		    	
		    	setTimeout(function(){
		    		BlockUi.unBlockPosition('#dialog_search_entourge');
		    	},500);
		    },
	        data: function (d) {	        	
	        	return JSON.stringify(d);
	        }
	    },
	    "preDrawCallback": function( settings ) {
	       console.log(settings)
	      },
	    "language": {
	        "url": "assets/global/plugins/datatables/Thai.json"
	    },
	    "columns": [
	    	{ "data": "fullname" ,name:"fullname"},
	    	{ "data": "email" ,name:"email"},	    	
	        { "data": "userGroupId.userGroupName" ,name:"userGroupName"}
	    ],
	    "columnDefs": [	    	
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [0,1,2]
	        },
	        {
	            "targets": 3,
	            "orderable": false,
	            "searchable": false,
	            "render": function (data, type, row) {
	            	
	            	
	            	var btnAction = '<button type="button" class="btn btn-default btn-select-entourge"><i class="fa fa-check"></i> เลือก</button>';
	            	
	               
	                return btnAction;
	            },
	            "width": "10px"  
	        }
	    ],
	    "order": [],
	    "initComplete": function (settings, json) {	    	
	    	$('.dataTables_filter').remove();
	    }
	});
}


$('#btn_search_supplier_table').on('click',function(){
	searchSupplierTable();
});

$('#btn_clear_search_supplier_table').on('click',function(){
	clearSearchSupplierTable();
});

keyUp.enter('#textbox_search_supplier_company',()=>{
	searchSupplierTable();
});

keyUp.enter('#textbox_search_supplier_code',()=>{
	searchSupplierTable();
});

/*$( "#select_search_greencard_status" ).change(function() {
	searchSupplierTable();
});*/

function searchSupplierTable(){
	$('#table_search_supplier').DataTable().columns(0).search($('#textbox_search_supplier_company').val().trim());
	$('#table_search_supplier').DataTable().columns(1).search($('#textbox_search_supplier_code').val().trim());
	$('#table_search_supplier').DataTable().columns(2).search($('#select_search_greencard_status').val().trim());
	$('#table_search_supplier').DataTable().draw();
}
function clearSearchSupplierTable(){
	$('#textbox_search_supplier_company').val('');
	$('#textbox_search_supplier_code').val('');
	$("#select_search_greencard_status").val($("#select_search_greencard_status option:first").val());
	$('#table_search_supplier').DataTable().columns(0).search('');
	$('#table_search_supplier').DataTable().columns(1).search('');
	$('#table_search_supplier').DataTable().columns(2).search('');
	$('#table_search_supplier').DataTable().draw();
}

$("#dialog_search_supplier").on('shown.bs.modal', function (e) {
	if(supplierDataTable == null){
		loadTableSupplier();
	}	
});

$('#dialog_search_supplier').on('click','.btn-select-supplier',function(){
	let data = $('#table_search_supplier').DataTable().row( $(this).parents('tr') ).data();		
	let supplierName = data.supplierCompany.trim();
	let supplierId = data.supplierId.trim();
	
	$("#supplierCompany_Dialog").typeahead('val',supplierName);
	$('#supplierID_Dialog').val(supplierId);
	$('#dialog_search_supplier').modal('hide');
	loadSupplierLocation.load();
});

$('#supplierCompany_Dialog').on('input', function () {
	$(supplierList).each(function(i,v){
		if(v.supplierCompany.trim() != $('#supplierCompany_Dialog').typeahead('val').trim().trim()){
			$('#supplierID_Dialog').val('');
			$(".form-group-location").slideUp();
		}
	});
});

function loadTableSupplier(){
	supplierDataTable = $('#table_search_supplier').DataTable({
		"proccessing": true,
		"serverSide": true,
		"searching": true,
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
	        data: function (d) {	        	
	        	return JSON.stringify(d);
	        }
	    },
	    "language": {
	        "url": "assets/global/plugins/datatables/Thai.json"
	    },
	    "columns": [
	    	{ "data": "supplierCompany" ,name:"supplierCompany"},
	    	{ "data": "supplierCode" ,name:"supplierCode"},	    	
	    	{
	    		"data" : "isGreenCard",
	    		"name" : "isGreenCard",
	    		"render" : function(data, type, row){
	    			
	    			let greenCard = '<span class="label label-sm bg-green-meadow" style="font-size : 12px;"> ได้รับ </span>';
					if (data == 'N' ){
						greenCard = '<span class="label label-sm label-default" style="font-size : 12px;"> ไม่ได้รับ </span>';
					}
					
	                return greenCard;
	    		},
	            "width": "70px"
	    	}
	    ],
	    "columnDefs": [	    	
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [0,1,2]
	        },
	        {
	            "targets": 3,
	            "orderable": false,
	            "searchable": false,
	            "render": function (data, type, row) {
	            	
	            	
	            	var btnAction = '<button type="button" class="btn btn-default btn-select-supplier"><i class="fa fa-check"></i> เลือก</button>';
	            	
	               
	                return btnAction;
	            },
	            "width": "10px"  
	        }
	    ],
	    "order": [],
	    "initComplete": function (settings, json) {	    	
	    	$('.dataTables_filter').remove();
	    }
	});
}

var historySupplierId = 0;
$('#dialog_search_location').on('shown.bs.modal',function(e){
	if(historySupplierId != $('#supplierID_Dialog').val().trim()){
		if(locationProduceDataTable != null){
			locationProduceDataTable.destroy();
		}
		loadSupplierLocation.loadLocationProduceTable();
		historySupplierId = $('#supplierID_Dialog').val().trim();
	}
	
});

$(document).on('click','.btn-select-location-produce',function(e){
	let data = $('#table_search_location_produce').DataTable().row( $(this).parents('tr') ).data();	
	$('#supplierLocation_Dialog').typeahead('val',data.locationName.trim());
	$('#LocationID_dialog').val(data.id);
	$('#dialog_search_location').modal('hide');
});


$('#btn_search_location_produce_table').on('click',function(){
	searchLocationProduceTable();
});

$('#btn_clear_search_location_produce_table').on('click',function(){
	clearSearchLocationProduceTable();
});

keyUp.enter('#location_produce_name_dialog',()=>{
	searchLocationProduceTable();
});

function searchLocationProduceTable(){	
	$('#table_search_location_produce').DataTable().columns(1).search($('#location_produce_name_dialog').val().trim());
	$('#table_search_location_produce').DataTable().draw();
}

function clearSearchLocationProduceTable(){
	$('#location_produce_name_dialog').val('');
	$('#table_search_location_produce').DataTable().columns(1).search('');
	$('#table_search_location_produce').DataTable().draw();
}


var loadSupplierAutoComplete = function(){
	return{
		init : function(){
			let url = contextPath+'/api/supplier/supplier_list';
			ajaxProcess.submit(url, 'GET', null, null, (data)=>{
				if(data.result){
					let supplierObj = JSON.parse(data.message);
					supplierList = supplierObj;
					loadSupplierAutoComplete.callback(supplierObj);
				}
				
			});
		},
		callback : function(supplierObjArray) {
			let autoComplete_size = 12;
			let tempalate = {
				empty: "ไม่พบข้อมูล",
				suggestion: function(e) {		
					if(e.supplierCode == ""){
						return "<div>" + e.supplierCompany + "</div>";
					}else{
						return "<div>" + e.supplierCode + " » " + e.supplierCompany + "</div>";
					}
				}
			};
			
			let supplier = new Bloodhound({
				queryTokenizer: Bloodhound.tokenizers.whitespace,
				datumTokenizer: Bloodhound.tokenizers.obj.whitespace('supplierCode','supplierCompany'),
				local : supplierObjArray			    			    
			});
			supplier.initialize();
			
			$("#supplierCompany_Dialog").typeahead({
				hint: true,
				highlight: true,
				minLength: 1
		    },
		    {
		    	limit : autoComplete_size,
		    	display: function(item){ return item.supplierCompany},
		    	source: supplier.ttAdapter(),
		    	templates : tempalate
		    }).on('typeahead:selected', function(event, obj) {		    	
		    	$('#supplierID_Dialog').val(obj.supplierId);
		    	loadSupplierLocation.load();
		    });
		}
	}
}();


var loadSupplierLocation = function(){
	return{
		load : function(){
			$('#supplierLocation_Dialog').typeahead('val','');
			$('#LocationID_dialog').val('');
			let url = contextPath+"/api/supplier_product_type_address_mapping/supplier_location/"+JSON.stringify({supplierId : $('#supplierID_Dialog').val().trim()});
			ajaxProcess.submit(url, 'GET', null, '#dialog_add_appoint', (data)=>{
				if(data.result){
					locationList = JSON.parse(data.message);
					loadSupplierLocation.loadLocationAutoComplete(JSON.parse(data.message));
					$(".form-group-location").slideDown("slow");					
				}else{
					locationList = null;
				}
			});
			
		},
		loadLocationAutoComplete : function(data){
			let autoComplete_size = 12;
			let tempalate = {
				empty: "ไม่พบข้อมูล ",
				suggestion: function(e) {			
					return "<div>" + e.locationName + " » " + e.addressId.address + " "+ e.addressId.subDistrictId.name + " "+ e.addressId.districtId.name+" "+e.addressId.provinceId.name+" "+ e.addressId.postcode+"</div>"
				}
			};
			
			let location = new Bloodhound({
				queryTokenizer: Bloodhound.tokenizers.whitespace,
				datumTokenizer: Bloodhound.tokenizers.obj.whitespace('locationName'),
				local : data			    			    
			});
			location.initialize();
			
			$('#supplierLocation_Dialog').typeahead('destroy');
			$('#supplierLocation_Dialog').typeahead({
				hint: true,
				highlight: true,
				minLength: 1
		    },
		    {
		    	limit : autoComplete_size,
		    	display: function(item){ return item.locationName},
		    	source: location.ttAdapter(),
		    	templates : tempalate
		    }).on('typeahead:selected', function(event, obj) {		    	
		    	$('#LocationID_dialog').val(obj.id);		    
		    });
			
		},
		loadLocationProduceTable : function(){
			locationProduceDataTable = $('#table_search_location_produce').DataTable({
				"proccessing": true,
				"serverSide": true,
				"searching": true,
				"ajax": {
			        "url": contextPath + "/api/supplier_product_type_address_mapping/datatable/supplier_produce_mapping_product_type_by_supplier",
			        "type": 'POST',
			        contentType: "application/json",
			        beforeSend : function(arr, $form, options){
				    	BlockUi.blockPosition('#dialog_search_location');
				    },
				    complete : function(){		    	
				    	setTimeout(function(){
				    		BlockUi.unBlockPosition('#dialog_search_location');
				    	},500);
				    },
			        data: function (d) {	        	
			        	let data =  $.extend( {}, d, {
			                "optionString": JSON.stringify({supplierId : $('#supplierID_Dialog').val().trim()})
			            });
			        	
			        	return JSON.stringify(data);
			        }
			    },
			    "language": {
			        "url": "assets/global/plugins/datatables/Thai.json"
			    },
			    "columns": [
			    	{ "data": null, "width": "2px" },
			    	{ "data": "locationName","name" : "locationName","width": "100px"},
			    	{ "data": "addressId","width": "100px",
			    	  "render" : function(data, type, row) {
			    		  let addressText = data.address +" ";
			    		  addressText += data.subDistrictId.name + " ";
			    		  addressText += data.districtId.name + " ";
			    		  addressText += data.provinceId.name + " ";
			    		  addressText += data.postcode;			    		  
			    		  return addressText;
			    	  }
			    	}			    	
			    ],
			    "columnDefs": [	  
			    	{
			            "searchable": false,
			            "orderable": false,
			            "targets": 0,
			            "render": function (data, type, full, meta) {
			                return meta.settings._iDisplayStart + meta.row + 1;
			            }
			        },
			        {
			            "searchable": true,
			            "orderable": false,
			            "targets": [1]
			        },
			        {
			            "searchable": false,
			            "orderable": false,
			            "targets": [0,2]
			        },
			        {
			            "targets": 3,
			            "orderable": false,
			            "searchable": false,
			            "render": function (data, type, row) {
			            	
			            	
			            	let btnAction = '<button type="button" class="btn btn-default btn-select-location-produce"><i class="fa fa-check"></i> เลือก</button>';
			            	
			               
			                return btnAction;
			            },
			            "width": "10px"  
			        }
			    ],
			    "order": [[0, 'asc']],
			    "initComplete": function (settings, json) {	    	
			    	$('.dataTables_filter').remove();
			    }
			});
		}
	}
}();

$( document ).on(
	    'DOMMouseScroll mousewheel scroll',
	    '#dialog_add_checklistplan', 
	    function(){     
	    	var t;
	        window.clearTimeout( t );
	        var t = window.setTimeout( function(){            
	            $('.input-group.datepicker-dialog').datepicker('place')
	            $('.input-group.clockpicker-dialog').clockpicker('place')
	        },100 );        
	    }
	);

