$(function(){
	loadQuestionaireDocumentSelection();
	loadQuestionaireInfomationDataTable();
});

function loadQuestionaireDocumentSelection(){
	$.ajax({
		url: contextPath + "/api/questionaire_document/questionaire_document_list",
	    type: 'GET',
	    async: true,
	    contentType: 'application/json; charset=UTF-8',
	    success : function(data, msg, jqXHR) {
	    	if(data.message != ""){
	    		let objJson = JSON.parse(data.message);	    
	    		$('#sel_questionaire_search').append(
	    			$('<option value="">--- ทั้งหมด ---</option>')
	    		);
	    		$(objJson).each(function(i,val){
	    			$('#sel_questionaire_search').append(
    	    			$('<option value="'+val.questionaireDocumentId+'">'+val.questionaireDocumentName+'</option>')
    	    		);
	    		});
	    	}
	    }
	});
}

function loadQuestionaireInfomationDataTable(){
	$('#table_supplier_questionaire_document').DataTable({		
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": contextPath + "/api/supplier_questionaire_document/datatable/get_questionaire_document_mapping_supplier_list",
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
	    	{ "data": "supplierId.supplierCompany" ,name:"supplierCompany"},
	    	{ "data": "questionaireDocumentId.questionaireDocumentName" ,name:"questionaireDocumentName"},
	    	{
	    		"data" : "supplierQuestionaireDocumentLocation",
	    		"name" : "statusUploadQuestionaireDocument",
	    		"render" : function(data, type, row){
	    			
	    			let statusUpload = '<span class="label label-sm bg-green-meadow" style="font-size : 12px;"> อัพโหลดไฟล์แล้ว </span>';
					if (data == '' || data == null ){
						statusUpload = '<span class="label label-sm label-default" style="font-size : 12px;"> ยังไม่ได้อัพโหลด </span>';
					}
					
	                return statusUpload;
	    		},
	            "width": "70px"
	    	},
	    	{ "data": "supplierQuestionaireDocumentLocation" ,name:"supplierQuestionaireDocumentLocation", 
			    "render" : function(data, type, row){					
					let buttonDownload = '<a type="button" class="btn btn-success btn-sm" title="Download file" href="'+data+'" target="_blank"><i class="glyphicon glyphicon-download"></i><b>ดาวน์โหลดไฟล์</b></a>';
					if(data == '' || data == null){
						buttonDownload = '<button type="button" class="btn btn-success btn-sm" title="Download file" href="'+data+'" target="_blank" disabled readonly=true><i class="glyphicon glyphicon-download"></i><b>ดาวน์โหลดไฟล์</b></button>'
					}
					return buttonDownload;
				}
			},
	        { "data": "questionaireDocumentId.questionaireDocumentId" ,name:"questionaireDocumentId"},
			{ "data": "supplierId.supplierId" ,name:"supplierId"},
			{ "data": "supplierQuestionaireDocumentId" ,name:"supplierQuestionaireDocumentId"}
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
	        	"targets": [5,6,7],
	            "visible": false,
	            "searchable": false
	        },	        
	        {
	            "searchable": false,
	            "orderable": false,
	            "targets": [0,6,7]
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [1,2,3,4,5]
	        }
	    ],
	    "order": [['5', 'asc']],
	    "initComplete": function (settings, json) {	    	
	    	$('.dataTables_filter').remove();	    	   	
	    }
	});
}

$('#btn_clear').on('click',function(){
	$("#sel_questionaire_search").val($("#sel_questionaire_search option:first").val());
	$('#company_textbox').val('');
	
	$('#table_supplier_questionaire_document').DataTable().columns(1).search('');
	$('#table_supplier_questionaire_document').DataTable().columns(5).search('');
	$('#table_supplier_questionaire_document').DataTable().draw();
});

$('#btn_search').on('click',function(){
	searchQuestionaireInfomationDataTable();
});

$("#company_textbox").keyup(function(event) {
    if (event.keyCode === 13) {
    	searchQuestionaireInfomationDataTable();
    }
});

function searchQuestionaireInfomationDataTable(){
	let supplierCompany = $('#company_textbox').val().trim();
	let questionaireDocumentId = $('#sel_questionaire_search').val();
	
	$('#table_supplier_questionaire_document').DataTable().columns(1).search(supplierCompany);
	$('#table_supplier_questionaire_document').DataTable().columns(5).search(questionaireDocumentId);
	$('#table_supplier_questionaire_document').DataTable().draw();
}