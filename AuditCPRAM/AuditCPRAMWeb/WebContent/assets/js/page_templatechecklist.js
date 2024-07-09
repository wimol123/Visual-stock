var checkListTable = null;
$(function(){
	loadChecklist();
});

$('#btn_clear').on('click',function(){
	$("#select_status").val($("#select_status option:first").val());
	$('#template_checklist_name').val('');
	
	$('#table_template_checklist').DataTable().columns(2).search('');
	$('#table_template_checklist').DataTable().columns(3).search('');
	$('#table_template_checklist').DataTable().draw();
});


$('#btn_search').on('click',function(){
		
	var templateName = $('#template_checklist_name').val().trim();
	var status = $('#select_status').val().trim();	
	
	$('#table_template_checklist').DataTable().columns(2).search(templateName);
	$('#table_template_checklist').DataTable().columns(3).search(status);
	$('#table_template_checklist').DataTable().draw();

});

$("#template_checklist_name").keyup(function(event) {
    if (event.keyCode === 13) {
        $("#btn_search").click();
    }
});
/*$( "#select_status" ).change(function() {
	$("#btn_search").click();
});*/


function loadChecklist(){
	checkListTable = $('#table_template_checklist').DataTable({
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax" : {
			"url": contextPath + "/api/template/datatable/get_template_list",
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
	    	{ "data": null, "width": "2px" },
	    	{ "data": "evalTemplateId" },
	    	{ "data": "title", name : "title" },
	    	{ "data": "statusId",
	            "render": function (data, type, row) {
	            	
	            	var dpStatus = '<span class="label label-sm label-success" style="font-size : 12px;"> ใช้งาน </span>';
					if (data == '0' ){
						dpStatus = '<span class="label label-sm label-default" style="font-size : 12px;"> ไม่ใช้งาน </span>';
					}
					
	                return dpStatus;
	            },
	            "width": "20px",
	            name : "status_id"
	    			
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
	        	"targets": [1],
	            "visible": false,
	            "searchable": false,
	            "orderable": true
	        },
	        {
	            "searchable": false,
	            "orderable": false,
	            "targets": [0,4]
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [2,3]
	        },
	        {
	            "targets": 4,
	            "orderable": false,
	            "render": function (data, type, row) {
	            	var btnAction = '<div class="btn-group">';
	            	btnAction += '<button class="btn btn-xs blue dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false" style="font-size : 12px;"> เลือก <i class="fa fa-angle-down"></i></button>';
	    			btnAction += '<ul class="dropdown-menu pull-left" role="menu">';
	    			btnAction += 	'<li><a href="javascript:void(0);" class = "editTemplate">';
	    			btnAction +=   		'<i class="icon-pencil"></i> แก้ไขข้อมูล </a>';
	    			btnAction +=	'</li>';
	    			btnAction +=	'<li><a href="javascript:void(0);" class = "removeTemplate">';
	    			btnAction +=		'<i class="icon-trash"></i> ลบข้อมูล </a>';
	    			btnAction +=	'</li>';
	                btnAction += '</ul>';
	                btnAction +='</div>';
	               
	                return btnAction;
	            },
	            "width":"80px"
	        }
	    ],
	    "order": [[1, 'asc']],
	    "initComplete": function (settings, json) {
	    	
	    	$('.dataTables_filter').remove();
	    	
	    	
	    }
	    
	});
}

$('body').on('click','.editTemplate',function(){
	var data = $('#table_template_checklist').DataTable().row( $(this).parents('tr') ).data();
	var templateId = data.evalTemplateId.trim();
	$('#value_param').val(encodeURI(templateId));
 	$('#form_pass_param').submit();
	/*$.ajax({
		url : contextPath + "/api/template/get_template_by_id/",
		type : 'POST',
		data : JSON.stringify(templateId),
		contentType : "application/json",
		beforeSend : function(arr, $form, options){
			BlockUi.blockPage('warning');
	    },
	    complete : function(){
	    	BlockUi.unBlockPage();
	    },
		success : function(data) {	
	     	var listDetailTemplate = JSON.stringify(data);
	     	$('#value_param').val(encodeURI(listDetailTemplate));
	     	$('#form_pass_param').submit();
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
		  customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
		}
	});*/
	
});

$('body').on('click','.removeTemplate',function(){
	var data = $('#table_template_checklist').DataTable().row( $(this).parents('tr') ).data();
	var templateId = data.evalTemplateId.trim();
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการลบข้อมูลนี้ใช่หรือไม่?");
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			$.ajax({
				url : contextPath + "/api/template/delete_template",
				type : 'DELETE',
				data : JSON.stringify(templateId),
				contentType : "application/json; charset=utf-8",
				beforeSend : function(arr, $form, options){
					BlockUi.blockPage();
			    },
			    complete : function(){
			    	BlockUi.unBlockPage();
			    },
				success : function(data) {			
				 if(data.result){
					 customMessageDialog("success", "สถานะการดำเนินการ", data.message, null);
				 }else{
					 customMessageDialog("warning", "สถานะการดำเนินการ", data.message, null);
				 }
			     
			     $('#table_template_checklist').DataTable().draw();			    	  
				},
				error: function (jqXHR, textStatus, errorThrown) {
				  customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseJSON.message, null);
				},
			});
		}
	});
});