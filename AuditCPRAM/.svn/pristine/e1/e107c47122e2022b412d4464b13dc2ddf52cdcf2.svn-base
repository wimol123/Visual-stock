var action = getURLParameter("action");

$(function(){
	if(action == 'add'){
		$('#dialog_add_question').modal('toggle');
	}	
	loadQuestionGroup();	
	//getQuestion();	
	
	loadQuestionTable();
	
});



function loadQuestionTable(){
	var questionGroupTable = $('#table_question').DataTable({
		
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": contextPath + "/api/question/datatable/get_question_list",
	        "type": 'POST',
	        contentType: "application/json",
	        beforeSend : function(arr, $form, options){
		    	$('.bgLoader').show();
		    	$('.imgLoader').show();
		    },
		    complete : function(){
		    	$('.bgLoader').hide();
		    	$('.imgLoader').hide();
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
	    	{ "data": "questionName" },
	    	{ "data": "questionId"},
	    	{ "data": "questionGroupName"},
	    	{ "data": "questionGroupId"},
	    	{
	            "data": "statusId",
	            "render": function (data, type, row) {
	            	
	            	var dpStatus = '<span class="label label-sm label-success"> ใช้งาน </span>';
					if (data == '0' ){
						dpStatus = '<span class="label label-sm label-default"> ไม่ใช้งาน </span>';
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
	        	"targets": [2,4],
	            "visible": false,
	            "searchable": false
	        },
	        {
	            "searchable": false,
	            "orderable": false,
	            "targets": [0,6]
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [1,3,5]
	        },
	        {
	            "targets": 6,
	            "orderable": false,
	            "render": function (data, type, row) {
	            	
	            	
	            	var btnAction = '<div class="btn-group">';
	            	btnAction += '<button class="btn btn-xs blue dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false"> เลือก <i class="fa fa-angle-down"></i></button>';
	    			btnAction += '<ul class="dropdown-menu pull-left" role="menu">';
	    			btnAction += 	'<li><a href="javascript:void(0);" class = "editQuestion">';
	    			btnAction +=   		'<i class="icon-pencil"></i> แก้ไขข้อมูล </a>';
	    			btnAction +=	'</li>';
	    			btnAction +=	'<li><a href="javascript:void(0);" class = "removeQuestion">';
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


$('#btn_action_modal').on('click',function(){
	var objForm = {
			questionId : $('#questionId_modal').val().trim(),
			questionName : $('#questionName_modal').val().trim(),
			statusId : $('#status_question').prop('checked'),
			questionGroupId : $('#questionGroup_Dropdown').val().trim()			
	};
	if(objForm.questionGroupId == "inactive"){
		objForm.questionGroupId = $('#questionGroupId').val().trim();
	}
	
	var msgAlert='';
	if(objForm.questionName == ""){
		msgAlert = "- คำถาม";
	}
	if(objForm.questionGroupId == ""){
		msgAlert = "- ชื่อกลุ่มคำถาม";
	}
	
	if(msgAlert != ""){
		msgAlert = "กรุณาตรวจสอบข้อมูล <br />"+msgAlert;
		customMessageDialog("warning", "ตรวจสอบข้อมูล", msgAlert, null);
		return;
	}
	
	
	var cmd_Action = 'Insert';
	var url = contextPath+'/api/question/insert_question/'+JSON.stringify(objForm);
	
	if(objForm.questionId != ""){
		var cmd_Action = 'Update';
		var url = contextPath+'/api/question/update_question/'+JSON.stringify(objForm);
	}
	
	if(cmd_Action == 'Insert'){
		updateAndInsert(url,cmd_Action);
	}else{
		$('#dialog_add_question').modal('toggle');
		ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการบันทึกการแก้ไขข้อมูลนี้ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){
				updateAndInsert(url,cmd_Action);
			}else{
				prepareModalBeforeOpen();
			}
		});
	}
	
	//console.log(url);
	
});


function updateAndInsert(url,cmd_Action){
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
	    	if(cmd_Action == "Insert"){
	    		customMessageDialog("success", "ดำเนินการสำเร็จ", "สถานะการดำเนินการ<br/> - ทำการเพิ่มข้อมูลสำเร็จ", null);
	    	}else{
	    		customMessageDialog("success", "ดำเนินการสำเร็จ", "สถานะการดำเนินการ<br/> - ทำการอัพเดตข้อมูลสำเร็จ", null);
	    	}
	    	$('#btn_search').trigger('click');
	    	$('#dialog_add_question').modal('hide');
	    },
	    error: function(jqXHR, textStatus, errorThrown){
	    	customMessageDialog("error", "เกิดข้อผิดพลาด", errorThrown, null);
	    }
	});
}



$('#table_question').on('click','.btn-group .dropdown-toggle',function(){
	
	var data = $('#table_question').DataTable().row( $(this).parents('tr') ).data();
	
	$('#questionId').val(data.questionId);
	$('#questionName').val(data.questionName);
	$('#questionGroupName').val(data.questionGroupName);
	$('#questionGroupId').val(data.questionGroupId);
	$('#questionActive_status').val(data.statusId);	
});


$('#dialog_add_question').on('hidden.bs.modal', function () {
	$('#questionGroup_Dropdown').empty();
	loadQuestionGroup();
	$("#dialog_add_question .modal-title").html("เพิ่มคำถาม");
	$('#questionId_modal').val("");
	$('#questionName_modal').val("");	
	$('#status_question').bootstrapSwitch('state',false);
});


$(document).on('click','.editQuestion',function(){
	prepareModalBeforeOpen();
});


function prepareModalBeforeOpen(){
	loadQuestionGroup();
	$("#dialog_add_question .modal-title").html("แก้ไขคำถาม");
	$('#questionId_modal').val($('#questionId').val());
	$('#questionName_modal').val($('#questionName').val());
	$('#questionGroup_Dropdown').val($('#questionGroupId').val());
	$('#questionGroup_Dropdown').trigger('change');
	
	if( $('#questionGroup_Dropdown :selected').text() == ""){
		
		if ($('#questionGroup_Dropdown').find("option[value='inactive']").length) {
		    $('#questionGroup_Dropdown').val('inactive').trigger('change');
		} else { 
			var newOption = new Option($('#questionGroupName').val()+' (Inactive)', 'inactive',true,true);
		    $('#questionGroup_Dropdown').append(newOption).trigger('change');
		} 		
	}
	
	$('#dialog_add_question').modal('toggle');
	if($('#questionActive_status').val() == '1'){
		$('#status_question').bootstrapSwitch('state',true);
	}else{
		$('#status_question').bootstrapSwitch('state',false);
	}
}


$(document).on('click','.removeQuestion',function(){
	
	url = contextPath + "/api/question/delete_question/"+$('#questionId').val().trim();
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการลบข้อมูลนี้ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			$.ajax({
				url : url,
				type: 'DELETE',
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
			    	customMessageDialog("success", "ดำเนินการสำเร็จ", "สถานะการดำเนินการ<br/> - ทำการลบข้อมูลสำเร็จ", null);
			    		    	
			    	$('#btn_search').trigger('click');	
			    },
			    error: function(jqXHR, textStatus, errorThrown){
			    	customMessageDialog("error", "เกิดข้อผิดพลาด", errorThrown, null);
			    }
			});
		}
	});
	
	
});

var mainQuestionGroupData;
function mapQuestionGroupForSelect2Data(questionGroupData) {
	var data = [];
	for(var i in questionGroupData) {    
		var item = questionGroupData[i];   
		data.push({ 
			id : item.questionGroupId,
			text  : item.questionGroupName
		});
	}
	return data;
}

function loadQuestionGroup(){
	
	$.ajax({
		url: contextPath + "/api/question_group/get_question_group_list",
		type: 'GET',
		success : function(data, msg, jqXHR) {
			mainQuestionGroupData = mapQuestionGroupForSelect2Data(data);
			
	    	$("#questionGroup_Dropdown").select2({
	    		placeholder: "เลือกกลุ่มคำถาม",
	    		 theme: "bootstrap",
	    		 data: mainQuestionGroupData
	    	});
	    	
		},
		error: function (jqXHR, textStatus, errorThrown) {
			customMessageDialog("error", "เกิดข้อผิดพลาด", errorThrown, null);
	    }
	});
	
}







$('#btn_clear').on('click',function(){
	$('#question_textbox').val('');
	$('#questionGroup_textbox').val('');
	$("#select_status").val($("#select_status option:first").val());
	$('#table_question').DataTable().columns(1).search('');
	$('#table_question').DataTable().columns(3).search('');
	$('#table_question').DataTable().columns(5).search('');
	$('#table_question').DataTable().draw();
});


$('#btn_search').on('click',function(){
	var questionName = $('#question_textbox').val().trim();
	var questionGroupName = $('#questionGroup_textbox').val().trim();
	var status = $('#select_status').val().trim();
	
	$('#table_question').DataTable().columns(1).search(questionName);
	$('#table_question').DataTable().columns(3).search(questionGroupName);
	$('#table_question').DataTable().columns(5).search(status);
	$('#table_question').DataTable().draw();
});







