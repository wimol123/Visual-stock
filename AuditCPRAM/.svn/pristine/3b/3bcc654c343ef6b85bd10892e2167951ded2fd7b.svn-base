
$(function(){		
	chSession()
	loadChecklistToTable();
	$.fn.bootstrapSwitch.defaults.size = 'normal';
	$.fn.bootstrapSwitch.defaults.onColor = 'info';
	$("#dal_chb_activeSatatus").bootstrapSwitch();
	$('.input-group.datepicker-effective').datepicker({
		language: "th",
		format: 'dd/mm/yyyy',
		orientation: "bottom auto",
		autoclose: true
	});
	$('.input-group.datepicker-expire').datepicker({
		language: "th",
		format: 'dd/mm/yyyy',
		orientation: "bottom auto",
		autoclose: true
	});
});
function chSession() {
	let url = contextPath +"/api/user/get_permission_menu";
	$.ajax({
		url : url,
		type : 'POST',
		contentType: "application/json",
		data : "checklist.jsp",
		success : function(data){
			if(!data.result){
				window.location = "/auditsupplier/home.jsp";
			}
		}
	});
}



function loadChecklistToTable(){
	var checklistTable = $('#table_checklist').DataTable({
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": contextPath + "/api/checklist/datatabale/checklist_list",
	        "type": 'POST',
	        contentType: "application/json",
	        beforeSend : function(arr, $form, options){
		    	BlockUi.blockPage();
		    },
		    complete : function(){
		    	setTimeout(function(){
		    		BlockUi.unBlockPage();
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
	    	{ "data": null, "width": "30px" },
	    	{ "data": "checklistId" },
	    	{ "data": "productTypeId.productTypeId"},
	    	{ "data": "checklistTitle", "name": "checklistTitle"},
	    	{ "data" : "description", "name" : "description"},
	    	{ "data" : "effectiveDate", "name" : "effectiveDate"},
	    	{ "data" : "expireDate", "name" : "expireDate"},
	    	{
	            "data": "enable",
	            "name": "enable",
	            "render": function (data, type, row) {
	            	
	            	var dpStatus = '<span class="label label-sm label-success" style="font-size:12px;"> ใช้งาน </span>';
					if (data == 'N' ){
						dpStatus = '<span class="label label-sm label-default" style="font-size:12px;"> ไม่ใช้งาน </span>';
					}
					
	                return dpStatus;
	            },
	            "width": "150px" 
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
	        	"targets": [1,2,4],
	            "visible": false,
	            "searchable": false
	        },	        
	        {
	            "searchable": false,
	            "orderable": false,
	            "targets": [0,8]
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [3,5,6,7]
	        },
	        {
	            "targets": 8,
	            "orderable": false,
	            "render": function (data, type, row) {
	            	
	            	
	            	var btnAction = '<div class="btn-group">';
	            	btnAction += '<button class="btn btn-xs blue dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false" style="font-size:12px;"> เลือก <i class="fa fa-angle-down"></i></button>';
	    			btnAction += '<ul class="dropdown-menu pull-left" role="menu">';
	    			btnAction += 	'<li><a href="javascript:void(0);" class = "editChecklist">';
	    			btnAction +=   		'<i class="icon-pencil"></i> แก้ไขข้อมูล </a>';
	    			btnAction +=	'</li>';
	    			btnAction +=	'<li><a href="javascript:void(0);" class = "removeChecklist">';
	    			btnAction +=		'<i class="icon-trash"></i> ลบข้อมูล </a>';
	    			btnAction +=	'</li>';
	                /*btnAction +=	'<li><a href="javascript:void(0);" class = "messageChecklist">';
	                btnAction +=		'<i class="icon-envelope "></i> Message</a>';
	                btnAction +=	'</li>';*/
	                btnAction += '</ul>';
	                btnAction +='</div>';
	               
	                return btnAction;
	            },
	            "width": "200px" 
	        }
	    ],
	    "order": [[1, 'asc']],
	    "initComplete": function (settings, json) {	    	
	    	$('.dataTables_filter').remove();
	    	
	    } 
	    
	    
	});
	
}


$(document).on('click','.btn-group .dropdown-toggle',function(){
	let data = $('#table_checklist').DataTable().row( $(this).parents('tr') ).data();
	$('#checklistId').val(data.checklistId.trim());
	
	///$('#checklistName').val(data.checklistName.trim());	
	//var flagActive = (data[1] == '<span class="label label-sm label-success"> Active </span>') ? '1' :'0';	
	/*var flagActive = data.statusId;
	$('#checklistStatus').val(flagActive);*/
});

$(document).on('click','a.editChecklist',function(){
	$('#link_ChecklistForm_form').submit();	
});

$('#btn_action_modal').on('click',function(){
	var checklistName = $('#checklistName_modal').val().trim();
	var status = $('#dal_chb_activeSatatus').prop('checked');
	if(checklistName == ""){
		customMessageDialog("warning", "ตรวจสอบข้อมูล", "ตรวจสอบข้อมูล <br/> - กรุณากรอกหัวข้อ Checklist", null);
		return;
	}
	
	$(this).blur();
	var objForm = {
			checklistId : '',
			checklistName : checklistName,
			statusId : status
	};
	
	url = contextPath+'/api/checklist/insert_checklist/'+JSON.stringify(objForm);
	$.ajax({
		url : url,
		type: 'POST',
		contentType: 'application/json',
		beforeSend : function(arr, $form, options){
	    	$('.bgLoader').show();
	    	$('.imgLoader').show();
	    },
	    complete : function(){
	    	$('.bgLoader').hide();
	    	$('.imgLoader').hide();
	    },
	    success: function(data, textStatus, jqXHR){	    	
	    	customMessageDialog("success", "สถานะการดำเนินการ", "สถานะการดำเนินการ<br/> - ทำการเพิ่มข้อมูลสำเร็จ", null);
	    	$('#table_checklist').DataTable().draw();
	    	$('#dialog_add_checklist').modal('toggle');
	    },
	    error: function (jqXHR, exception){
	    	customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
	    }	
	});
	
});

$('#dialog_add_checklist').on('hidden.bs.modal', function () {	
	$('#checklistName_modal').val('');
	$('#dal_chb_activeSatatus').bootstrapSwitch('toggleState', true, true);
	//$('#dal_chb_activeSatatus').bootstrapSwitch('state', !data, true);
});


$(document).on('click','a.removeChecklist',function(){
	let url = contextPath+'/api/checklist/delete_checklist';
	let objChecklist = {checklistId : $('#checklistId').val().trim()};
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการลบข้อมูลนี้ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			ajaxProcess.submit(url, "DELETE", objChecklist, null, (data)=>{
				if(data.result){
					$('#table_checklist').DataTable().draw();
				}
			});
		}
	});	
	
	
});








$('#btn_clear').on('click',function(){
	$('#ChecklistName_textbox').val('');
	$("#select_status").val($("#select_status option:first").val());
	$('#EffectiveDate_textbox').val('');
	$('#ExpireDate_textbox').val('');
	search();
});


$('#btn_search').on('click',function(){
	search();
});

$("#ChecklistName_textbox").keyup(function(event) {
    if (event.keyCode === 13) {
    	search();
    }
});


function search(){
	let checklistName = $('#ChecklistName_textbox').val().trim();
	let statusId = $('#select_status').val().trim();
	let effectiveDate = $('#EffectiveDate_textbox').val().trim();
	let expireDate = $('#ExpireDate_textbox').val().trim();
	
	$('#table_checklist').DataTable().columns(3).search(checklistName);
	$('#table_checklist').DataTable().columns(5).search(effectiveDate);
	$('#table_checklist').DataTable().columns(6).search(expireDate);
	$('#table_checklist').DataTable().columns(7).search(statusId);
	$('#table_checklist').DataTable().draw();
}

