var checkProcess = '';
$(function(){
	$('.bgLoader').show();
	$('.imgLoader').show();
	if($('#checklistStatusActive').val() == '0'){
		$('#status_checklist').bootstrapSwitch('state',false);
	}
	
	checkProcess = 'Update';
	if($('#checklistId').val() == ''){
		checkProcess = 'Insert';
		$(".option-checklist").addClass("disabledelements");	
	}else{
		var checklistId = $('#checklistId').val().trim();
		getQuestion(checklistId);
		//getQuestionGroup(checklistId);
	}
	
	
		
	//loadQuestionGroup();
	loadQuestion();
	$('.bgLoader').hide();
	$('.imgLoader').hide();	
	
	$.fn.bootstrapSwitch.defaults.size = 'normal';
	$.fn.bootstrapSwitch.defaults.onColor = 'info';
	$(".checkbox_active_status").bootstrapSwitch();
	

		
});


$('#dialog_add_question').on('hidden.bs.modal', function () {
	$('#question_Dropdown').empty();
	loadQuestion();
	$('#question_Dropdown').val(null);
	$('#question_Dropdown').trigger('change');
	$('#questionGroupName_Modal').val(null);
	$('#status_question').bootstrapSwitch('state',false);
	$('#question_Dropdown').prop('disabled','');
	$('#dialog_add_question .modal-title').html("เพิ่มคำถามลงในเช็คลิสต์");
});

$('#btn_save_checklist').on('click',function(){
	if($('#checklistName').val() == ""){
		customMessageDialog("warning", "เกิดข้อผิดพลาด", "กรุณากรอกหัวข้อเช็คลิสต์", null);
		return;
	}
	var url='';
	$(this).blur();
	var objForm = {
			checklistId : $('#checklistId').val(),
			checklistName : $('#checklistName').val(),
			statusId : $('#status_checklist').prop('checked')
	};
	if(checkProcess == 'Insert'){
		url = contextPath+'/api/checklist/insert_checklist/'+JSON.stringify(objForm);
		ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการเพิ่มข้อมูลนี้ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){
				insertAndUpdate(url);
			}
		});	
	}else{
		url = contextPath+'/api/checklist/update_checklist/'+JSON.stringify(objForm);
		
		ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการบันทึกการแก้ข้อมูลนี้ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){
				insertAndUpdate(url);
			}
		});		
		
	}	
});


function insertAndUpdate(url){
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
	    	if(checkProcess == 'Insert'){
	    		customMessageDialog("success", "สถานะการดำเนินการ", "สถานะการดำเนินการ <br/> - ทำการเพิ่มข้อมูลสำเร็จ", null);
	    		$('.bgLoader').show();
		    	$('.imgLoader').show();
	    		setTimeout(
	  		    		function() 
	  		    		{
	  		    			window.location.href = 'checklist.jsp';
	  		    		}, 1000);
	    	}else{
	    		customMessageDialog("success", "สถานะการดำเนินการ", "สถานะการดำเนินการ <br/> - ทำการอัพเดตข้อมูลสำเร็จ", null);
	    	}
	    	
	    },
	    error: function (jqXHR, exception){
	    	customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
	    }	
	});
}



$('#btn_save_question').on('click',function(){
	
	var obj = {
			questionId : $('#question_Dropdown').val(),
			checklistId : $('#checklistId').val(),
			statusId : $('#status_question').prop('checked')
	};
	
	if(obj.questionId == null || obj.questionId == ""){
		customMessageDialog("warning", "ตรวจสอบข้อมูล", "ตรวจสอบข้อมูล <br /> - กรุณาเลือกคำถาม", null);
		return;
	}
	
	
	var msgAlert = "สถานะการดำเนินการ <br /> - ทำการเพิ่มข้อมูลสำเร็จ";
	var url = contextPath + "/api/question/insert_question_by_checklist_id/"+JSON.stringify(obj);
	
	if($('#question_Dropdown').prop('disabled') == true){
		msgAlert = "สถานะการดำเนินการ <br /> - ทำการแก้ไขข้อมูลสำเร็จ";
		url = contextPath + "/api/question/update_question_by_checklist_id/"+JSON.stringify(obj);
	}
	
	
	$.ajax({
		url: url,
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
		success : function(data, msg, jqXHR) {
			if(jqXHR.responseJSON == false){
				customMessageDialog("warning", "ตรวจสอบข้อมูล", "ตรวจสอบข้อมูล <br /> - การดำเนินการของข้อมูลนี้ไม่สำเร็จ เนื่องจากมีข้อมูลนี้อยู่แล้ว", null);
				return;
			}
			customMessageDialog("success", "สถานะการดำเนินการ", msgAlert, null);
			$('#dialog_add_question').modal('toggle');
			//getQuestion(obj.checklistId.trim());
			$('#table_question').DataTable().draw();
		},
		error: function (jqXHR, textStatus, errorThrown) {
			customMessageDialog("error", "เกิดข้อผิดพลาด", errorThrown, null);
	    }
	});
	
});

$('#question_Dropdown').on('select2:select', function (e) {
    var data = e.params.data;
    var questionId = data.id.trim();    
    onChangeDropdownQuestion(questionId);
    
});



$(document).on('click','.editQuestionQuestionGroup',function(){
	var data = $('#table_question').DataTable().row( $(this).parents('tr') ).data();

	$('#dialog_add_question .modal-title').html("แก้ไขคำถาม");
	var questionId = data.questionId;
	$('#question_Dropdown').val(questionId);
	$('#question_Dropdown').trigger('change');
	$('#question_Dropdown').prop('disabled','disabled');
	onChangeDropdownQuestion(questionId);
	if($('#question_Dropdown :selected').text() ==''){
		if ($('#question_Dropdown').find("option[value='inactive']").length) {
		    $('#question_Dropdown').val('inactive').trigger('change');
		} else { 
			var newOption = new Option(data.questionName+' (Inactive)', questionId,true,true);
		    $('#question_Dropdown').append(newOption).trigger('change');
		} 	
	}
	
	if(data.statusId == '1'){
		$('#status_question').bootstrapSwitch('state',true);
	}
	$('#dialog_add_question').modal('toggle');
	
});


$(document).on('click','.removeQuestionQuestionGroup',function(){
	var data = $('#table_question').DataTable().row( $(this).parents('tr') ).data();
		
	var obj = {
		questionId : data.questionId,
		checklistId : $('#checklistId').val()
	};
	
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการลบข้อมูลนี้ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			$.ajax({
				url: contextPath + "/api/question/delete_question_by_checklist_id/"+JSON.stringify(obj),
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
				success : function(data, msg, jqXHR) {
					customMessageDialog("success", "สถานะการทำรายการ","สถานะการทำรายการ <br /> - ทำการลบข้อมูลสำเร็จ", null);
					$('#table_question').DataTable().draw();
				},
				error: function (jqXHR, textStatus, errorThrown) {
					customMessageDialog("error", "เกิดข้อผิดพลาด", errorThrown, null);
			    }
			});
		}
	});

	
	
});


$('#btn_search').on('click',function(){
	var questionName = $('#question_textbox').val().trim();
	var questionGroupName = $('#questionGroup_textbox').val().trim();
	var statusId = $('#select_status').val();

	$('#table_question').DataTable().columns(1).search(questionName);
	$('#table_question').DataTable().columns(3).search(questionGroupName);
	$('#table_question').DataTable().columns(5).search(statusId);
	$('#table_question').DataTable().draw();
	
});

$('#btn_clear').on('click',function(){
	
	$('#question_textbox').val('');
	$('#questionGroup_textbox').val('');
	$("#select_status").val($("#select_status option:first").val());
	$('#btn_search').trigger('click');
});

function onChangeDropdownQuestion(questionId){
	$.ajax({
		url: contextPath + "/api/question_group/get_question_group_by_question_id/"+questionId,
		type: 'GET',
		contentType: 'application/json',
		beforeSend : function(arr, $form, options){
	    	$('.bgLoader').show();
	    	$('.imgLoader').show();
	    },
	    complete : function(){
	    	$('.bgLoader').hide();
	    	$('.imgLoader').hide();
	    },
		success : function(data, msg, jqXHR) {
			var questionGroupName = data.questionGroupName.trim();
			$('#questionGroupName_Modal').val(questionGroupName);
		},
		error: function (jqXHR, textStatus, errorThrown) {
			customMessageDialog("error", "เกิดข้อผิดพลาด", errorThrown, null);
	    }
	});
}

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


var mainQuestionData;
function mapQuestionForSelect2Data(questionData) {	
	var data = [];
	for(var i in questionData) {    
		var item = questionData[i];   
		data.push({ 
			id : item.questionId,
			text  : item.questionName
		});
	}
	return data;
}

function loadQuestion(){
	
	$.ajax({
		url: contextPath + "/api/question/get_question_list_by_active",
		type: 'GET',
		success : function(data, msg, jqXHR) {
			mainQuestionData = mapQuestionForSelect2Data(data);
			
	    	$("#question_Dropdown").select2({
	    		placeholder: "เลือกคำถาม",
	    		 theme: "bootstrap",
	    		 data: mainQuestionData
	    	});
	    	$('#question_Dropdown').val(null);
	    	$('#question_Dropdown').trigger('change');
		},
		error: function (jqXHR, textStatus, errorThrown) {
			customMessageDialog("error", "เกิดข้อผิดพลาด", errorThrown, null);
	    }
	});
	
}


function getQuestion(_checklistId){
	var url = contextPath+'/api/question/datatabale/get_question_by_checklist_id/'+_checklistId;	

	var questionQuestionGroupTable = $('#table_question').DataTable({
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": url,
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
	    },"columns": [
	    	{ "data": null, "width": "30px" },
	    	{ "data": "questionName" },
	    	{ "data": "questionId"},
	    	{ "data": "questionGroupName" },
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
	    			btnAction += 	'<li><a href="javascript:void(0);" class = "editQuestionQuestionGroup">';
	    			btnAction +=   		'<i class="icon-pencil"></i> แก้ไขข้อมูล </a>';
	    			btnAction +=	'</li>';
	    			btnAction +=	'<li><a href="javascript:void(0);" class = "removeQuestionQuestionGroup">';
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
	    	
	    	
	    }
	});
	
}



