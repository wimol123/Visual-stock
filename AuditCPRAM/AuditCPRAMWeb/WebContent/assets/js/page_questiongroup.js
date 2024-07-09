var action = getURLParameter("action");

$(function(){
	
	$('#status_questionGroup').bootstrapSwitch('state',true);
	if(action == 'add'){
		$('#dialog_add_group').modal('toggle');
	}	
	loadquestiongroupTable();
});


function loadquestiongroupTable(){
	var questionGroupTable = $('#table_questionGroup').DataTable({
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": contextPath + "/api/question_group/datatable/get_question_group_list",
	        "type": 'POST',
	        contentType: "application/json",
	        beforeSend : function(arr, $form, options){
		    	BlockUi.blockPage();
		    },
		    complete : function(){
		    	BlockUi.unBlockPage();
		    },
	        data: function (d) {
	        	
	        	return JSON.stringify(d);
	        }
	    },
	    "language": {
	        "url": "assets/global/plugins/datatables/Thai.json"
	    },"columns": [
	    	{ "data": null, "width": "30px" },
	    	{ "data": "questionGroupName" },
	    	{ "data": "questionGroupId"},
	    	{
	            "data": "statusId",
	            "render": function (data, type, row) {
	            	
	            	var dpStatus = '<span class="label label-sm label-success" style="font-size : 12px;"> ใช้งาน </span>';
					if (data == '0' ){
						dpStatus = '<span class="label label-sm label-default" style="font-size : 12px;"> ไม่ใช้งาน </span>';
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
	        	"targets": [2],
	            "visible": false,
	            "searchable": false
	        },
	        {
	            "searchable": false,
	            "orderable": false,
	            "targets": [0,2,4]
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [1,3]
	        },
	        {
	            "targets": 4,
	            "orderable": false,
	            "render": function (data, type, row) {
	            	
	            	
	            	var btnAction = '<div class="btn-group">';
	            	btnAction += '<button class="btn btn-xs blue dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false" style="font-size : 12px;"> เลือก <i class="fa fa-angle-down"></i></button>';
	    			btnAction += '<ul class="dropdown-menu pull-left" role="menu">';
	    			btnAction += 	'<li><a href="javascript:void(0);" class = "editQuestionGroup">';
	    			btnAction +=   		'<i class="icon-pencil"></i> แก้ไขข้อมูล </a>';
	    			btnAction +=	'</li>';
	    			btnAction +=	'<li><a href="javascript:void(0);" class = "removeQuestionGroup">';
	    			btnAction +=		'<i class="icon-trash"></i> ลบข้อมูล </a>';
	    			btnAction +=	'</li>';
	                btnAction += '</ul>';
	                btnAction +='</div>';
	               
	                return btnAction;
	            },
	            "width": "200px" 
	        }
	    ],
	    "order": [[2, 'asc']],
	    "initComplete": function (settings, json) {
	    	
	    	$('.dataTables_filter').remove();
	    	
	    	$.fn.bootstrapSwitch.defaults.size = 'normal';
	    	$.fn.bootstrapSwitch.defaults.onColor = 'info';
	    	$(".checkbox_active_status").bootstrapSwitch();
	    }
	});
}

$('#table_questionGroup').on('click','.btn-group .dropdown-toggle',function(){
	var data = $('#table_questionGroup').DataTable().row( $(this).parents('tr') ).data();
	
	$('#questionGroupId').val(data.questionGroupId.trim());
	$('#questionGroupName').val(data.questionGroupName.trim());
	$('#questionGroupStatus').val(data.statusId.trim());
		
});


$(document).on('click','.editQuestionGroup',function(){
	$('#questionGroupIdModal').val($('#questionGroupId').val());
	$('#questionGroupNameModal').val($('#questionGroupName').val());	
	if($('#questionGroupStatus').val() != '1')
		$('#status_questionGroup').bootstrapSwitch('state',false);
	
	$("#dialog_add_group .modal-title").html("แก้ไขกลุ่มคำถาม");
	$('#dialog_add_group').modal('toggle');
});

$('#dialog_add_group').on('hidden.bs.modal', function () {
	$("#dialog_add_group .modal-title").html("เพิ่มกลุ่มคำถาม");
	$('#questionGroupIdModal').val("");
	$('#questionGroupNameModal').val("");
	$('#status_questionGroup').bootstrapSwitch('state',true);
});

$('#btn_action_questionGroup').on('click',function(){		
	var statusProcess = processActionBtnquestionGroup();
	if(statusProcess == false){
		customMessageDialog("warning", "การแจ้งเตือน", "การแจ้งเตือน<br /> - กรุณากรอกข้อมูลชื่อกลุ่มคำถาม", null);
		return;
	}	
	$('#dialog_add_group').modal('hide');
});

$(document).on('click','.removeQuestionGroup',function(){
	var url = contextPath+'/api/question_group/delete_question_group/'+$('#questionGroupId').val().trim();
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการลบข้อมูลนี้ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			$.ajax({
				url : url,
				type: 'DELETE',
			    contentType: 'application/json',
			    beforeSubmit : function(arr, $form, options){
			    	BlockUi.blockPage();
			    },
			    complete : function(){
			    	BlockUi.unBlockPage();
			    },
			    success: function(data, textStatus, jqXHR){
			    	customMessageDialog("success","สถานะการดำเนินการ","สถานะการดำเนินการ<br/> - ทำการลบข้อมูลสำเร็จ",null);
			    	$('#table_questionGroup').DataTable().draw();
			    },
			    error: function(jqXHR, textStatus, errorThrown){
			    	customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
			    }
			});
		}
	});
	
	//return false;
});

$('#btn_clear').on('click',function(){
	$('#questiongroup_textbox').val('');
	$("#select_status").val($("#select_status option:first").val());
	
	$('#table_questionGroup').DataTable().columns(1).search('');
	$('#table_questionGroup').DataTable().columns(3).search('');
	$('#table_questionGroup').DataTable().draw();
});


$('#btn_search').on('click',function(){
	var questionGroupName = $('#questiongroup_textbox').val().trim();
	var statusId = $('#select_status').val().trim();
	
	$('#table_questionGroup').DataTable().columns(1).search(questionGroupName);
	$('#table_questionGroup').DataTable().columns(3).search(statusId);
	$('#table_questionGroup').DataTable().draw();
});


function processActionBtnquestionGroup(){
	var objForm = {
			questionGroupId : $('#questionGroupIdModal').val().trim(),
			questionGroupName : $('#questionGroupNameModal').val().trim(),
			statusId : $('#status_questionGroup').prop('checked')
	};
	
	var checkProcess = 'Update';
	var url = contextPath+'/api/question_group/update_question_group/'+JSON.stringify(objForm);
	var msgAlert = "- ทำการอัพเดตข้อมูลสำเร็จ";
	if($('#questionGroupIdModal').val() == ""){
		checkProcess = 'Insert';
		url = contextPath+'/api/question_group/insert_question_group/'+JSON.stringify(objForm);
		msgAlert = "- ทำการเพิ่มข้อมูลสำเร็จ";
	}
	
	if($('#questionGroupNameModal').val() == ''){
		return false;
	}
	msgAlert = "สถานะการดำเนินการ <br />"+msgAlert;
	
	if(checkProcess == 'Update'){		
		$('#dialog_add_group').modal('toggle');
		ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการบันทึกการแก้ไขข้อมูลนี้ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){
				insertAndUpdate(url,msgAlert);
			}else{
				$('#questionGroupIdModal').val($('#questionGroupId').val());
				$('#questionGroupNameModal').val($('#questionGroupName').val());	
				if($('#questionGroupStatus').val() != '1')
					$('#status_questionGroup').bootstrapSwitch('state',false);
				
				$("#dialog_add_group .modal-title").html("แก้ไขกลุ่มคำถาม");
				$('#dialog_add_group').modal('toggle');
			}
		});
	}else{
		insertAndUpdate(url,msgAlert);
	}
	
	
	return true;
		
}


function insertAndUpdate(url,msgAlert){    
	$.ajax({
		url : url,
		type: 'POST',
	    contentType: 'application/json',
	    beforeSubmit : function(arr, $form, options){
	    	BlockUi.blockPosition('#dialog_add_group');
	    },
	    complete : function(){
	    	setTimeout(function(){
	    		BlockUi.unBlockPosition('#dialog_add_group');
	    	},500);	    	
	    },
	    success: function(data, textStatus, jqXHR){
	    	if(data.result){
	    		customMessageDialog("success", "สถานะดำเนินการ", data.message, null);
	    		$('#table_questionGroup').DataTable().draw();
		    	$('#dialog_add_group').modal('hide');
	    	}else{
	    		customMessageDialog("error", "สถานะดำเนินการ", data.message, null);
	    	}   
	    },
	    error: function (jqXHR, exception){
	    	var data = jqXHR.responseJSON;
	    	customMessageDialog("error", "เกิดข้อผิดพลาด", data.message, null);
	    }
	});
}

