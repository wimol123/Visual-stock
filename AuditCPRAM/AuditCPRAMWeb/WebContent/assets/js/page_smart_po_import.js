$(document).ready(function() {
	loadImportHistoryTable();
	
    $("#formFile").fileinput({
		language: "th",
        showPreview: false,
        showCancel: false,
        allowedFileExtensions: ["txt"],
        elErrorContainer: "#errorFile",
        uploadUrl: contextPath + '/api/smart_po/import_po',
        uploadAsync: true
    }).on('fileloaded', function(event, file, previewId, fileId, index, reader) {
    	$(".import-result").hide();
	}).on('filepreupload', function(event, data, previewId, index, fileId) {
    	BlockUi.blockPage();
	}).on('fileuploaded', function(event, data, previewId, index, fileId) {
		if (data.response.result) {
			$('#formFile').fileinput('clear').fileinput('enable');
			let result = JSON.parse(data.response.message);
			let valid = result.valid;
			let invalid = result.invalid;
			let invalidLine = result.invalidLine;
			let invalidPOErrorMsg = result.invalidPOErrorMsg;
			$(".import-result").show();
			$("#allRecord").html(valid.length + invalid.length);
			$("#successRecord").html(valid.length);
			$("#failRecord").html(invalid.length);
			
			if (invalid.length > 0) {
				$(".table-fail").show();
				$(".table-fail tbody").html("");
				for (let i = 0; i < invalid.length; i++) {
				  	$(".table-fail tbody").append(
						"<tr>" +
				  			"<td>" + invalidLine[i] + "</td>" +
				  			"<td>" + invalid[i] + "</td>" +
				  			"<td>" + invalidPOErrorMsg[i] + "</td>" +
				  		"</tr>");
				}
			} else {
				$(".table-fail").hide();
			}
		} else {
			alert(data.response.message);
		}
		
    	BlockUi.unBlockPage();
	}).on('fileuploaderror', function(event, data, msg) {
    	alert("พบข้อผิดพลาด กรุณาลองใหม่อีกครั้ง");
    	BlockUi.unBlockPage();
	});
	
	$(".import-result").hide();
});

$('#btn_import_history').on('click', function() {
	loadImportHistory();
	$('#dialog_import_history').modal('show');
});

function loadImportHistory() {
	ajaxProcess.submit(
		contextPath + '/api/smart_po/import_po_history', 
		'GET', 
		null, 
		'', 
		(data) => {
			if (data.result && data.message != null && data.message != "") {
				$('#table_import_history').dataTable().fnClearTable();
				$('#table_import_history').dataTable().fnAddData(JSON.parse(data.message.trim()));
			}
		}
	);
}

function loadImportHistoryTable() {
	$('#table_import_history').DataTable({
		searching: false,
		paging: false,
		info: false,
		language: {
	        url: "assets/global/plugins/datatables/Thai.json"
	    },
	    columns: [
			{ "data": "createDate",				name: "createDate" },
	    	{ "data": "createByName", 			name: "createByName" },
	    	{ "data": "type", 					name: "type" },
	    	{ "data": "fileName",				name: "fileName" }
	    ],
	    columnDefs: [
			{
	            "targets": [0,1,2],
	            "searchable": false,
	            "orderable": false
	        },
	        {
	            "targets": 3,
	            "className": "dt-center",
	            "searchable": false,
	            "orderable": false,
	            "render": function(data, type, row, meta) {
					var btnDownload = '' +
						'<a href="javascript:void(0);" class="btn_download_file_po">' +
						'	<i class="fa fa-download" aria-hidden="true"></i>' +
						'</a>';
					
					return btnDownload;
	            }
	        }
	    ],
	    "order": [],
	    "initComplete": function() {
	    	$('.dataTables_filter').remove();
	    	$('[data-toggle="tooltip"]').each(function() {
				$(this).tooltip();
			});
	    	$('#table_import_history').DataTable().on( 'draw', function () {
	    		$('[data-toggle="tooltip"]').each(function() {
					$(this).tooltip();
				});
	    	});
	    }
	});
}

$(document).on('click', '.btn_download_file_po', function() {
	let data = $('#table_import_history').DataTable().row($(this).parents('tr')).data();
	$('#download_file_name').val(data.fileName);
	$('#download_file_type').val(data.type);
	$("#download_file_po").submit();
});