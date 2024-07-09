var ManualDocumentObj = null;
var fileList = [];

$(function() {
	chSession();
	loadResource();
	initialFileInput();
});


function loadResource() {
	loadManualDocumentData();
	generateManualDocument();
}

function chSession() {
	let url = contextPath +"/api/user/get_permission_menu";
	$.ajax({
		url : url,
		type : 'POST',
		contentType: "application/json",
		data : "manual_document.jsp",
		success : function(data){
			if(!data.result){
				window.location = "/auditsupplier/home.jsp";
			}
		}
	});
}

function loadManualDocumentData() {
	let url = contextPath + '/api/manual_document/manual_document_list'
	ajaxProcess.submit(url, 'GET', null, '#section-standard-document', (data) => {
		if (data.result) {
			ManualDocumentObj = JSON.parse(data.message)
		}
	}, { async: false });
}

function generateManualDocument(ManualDocumentArray) {
	$('#Document').find('#upload').empty();
	console.log("T3");
	if (ManualDocumentArray == null) {
		ManualDocumentArray = ManualDocumentObj;
	}
	let htmlPortlet = genrateSectionStandardDocument(ManualDocumentArray);
	$('#Document').find('#upload').append(htmlPortlet);

}


function genrateSectionStandardDocument(ManualDocumentArray) {

	let ManualDocumentByType = null;
	if (ManualDocumentArray.length > 0) {
		ManualDocumentByType = ManualDocumentArray;
	}
	console.log(ManualDocumentByType);


	let html = '<div class="portlet light standard_document_portlet">';
	if (ManualDocumentByType != null) {
		if (ManualDocumentByType.length == 0) {
			html += '<div class="row">';
			html += '<div class="col-md-12 section-fileinput">';
			html += '<input class="standard-document-input file" type="file" name="standard-document-input[]" multiple data-preview-file-type="files/*" accept=".doc, .pdf, .docx">';
			html += '<input class="form-control optionPreview hide" type="text" name="optionPreview">';
			html += '</div>';
			html += '</div>';
		} else {
			let initialPreviewData = [];
			let initialPreviewConfigData = [];

			for (var i = 0; i < ManualDocumentByType.length; i++) {


				let NameString = ManualDocumentByType[i]['documentlocation'].split('/')
				let Typefile = NameString[NameString.length - 1].split('.')
				let FileName = NameString[NameString.length - 1]
				initialPreviewData.push(ManualDocumentByType[i]['documentlocation']);

				initialPreviewConfigData.push({
					type: ManualDocumentByType[i]['document_type'],
					caption: FileName.trim(),
					filename: FileName.trim(),
					downloadUrl: ManualDocumentByType[i]['documentlocation'].trim(),
					key: ManualDocumentByType[i]['documentid'],
					url : contextPath+'/api/manual_document/delete_manual_document'
				})
			}

			console.log(initialPreviewConfigData);
			
			let optionFileInput = {
				initialPreview: initialPreviewData,
				initialPreviewConfig: initialPreviewConfigData
			}

			html += '<div class="row">';
			html += '<div class="col-md-12 section-fileinput">';
			html += '<form method="POST" enctype="multipart/form-data" accept-charset="UTF-8">';
			html += '<input class="standard-document-input file" type="file" name="standard-document-input[]" multiple data-preview-file-type="files/*" accept=".doc, .pdf, .docx">';
			html += '<input class="form-control optionPreview hide" type="text" name="optionPreview" value="' + URI.encode(JSON.stringify(optionFileInput)) + '">';
			html += '</form>';
			html += '</div>';
			html += '</div>';
		}
		
	} else {
		html += '<div class="row">';
		html += '<div class="col-md-12 section-fileinput">';
		html += '<input class="standard-document-input file" type="file" name="standard-document-input[]" multiple data-preview-file-type="files/*" accept=".doc, .pdf, .docx">';
		html += '<input class="form-control optionPreview hide" type="text" name="optionPreview">';
		html += '</div>';
		html += '</div>';
	}

	
	html += '</div>';
	return html;

}

function initialFileInput() {

	$(".standard-document-input").each(function(i, v) {
		let optionPreviewData = $(this).closest('.section-fileinput').find('.optionPreview').val().trim();

		let optionFileInput = {
			theme: "explorer",
			language: "th",
			showClose: false,
			showCaption: false,
			showBrowse: true,
			showUpload: true,
			showRemove: false,
			autoOrientImage: true,
			maxFileSize: 3000,
			minFileCount: 1,
			maxFileCount: 100,
			overwriteInitial: false,
			previewFileIcon: '<i class="fa fa-file-o"></i>',
			removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
			defaultPreviewContent: '<img src="' + contextPath + '/assets/images/picture-attachment.png" alt="Your Standard Document"><h6 class="text-muted">เลือกไฟล์</h6>',
			layoutTemplates: {
				main2: '{preview} {remove} {browse} {upload} ',
				actions: '{drag}\n' +
					'<div class="file-actions">\n' +
					'    <div class="file-footer-buttons">\n' +
					'        {upload} {download} {delete} \n' +
					'    </div>\n' +
					'</div>'
			},
			allowedFileExtensions: ["doc", "pdf", "docx"],
			initialPreviewAsData: true,
			preferIconicPreview: true, // this will force thumbnails to display icons for following file extensions
			purifyHtml: true, // this by default purifies HTML data for preview	    
			uploadUrl: contextPath + '/api/upload/file',
			uploadAsync: false,
			fileActionSettings: {
				showUpload: false,
				showDownload: true,
				showZoom: false,
				showDrag: false,
				showOther: true
			},
			preferIconicPreview: true,
			previewFileIconSettings: { // configure your icon file extensions
				'doc': '<i class="fa fa-file-word-o text-primary"></i>',
				'xls': '<i class="fas fa-file-excel text-success"></i>',
				'ppt': '<i class="fas fa-file-powerpoint text-danger"></i>',
				'pdf': '<i class="fa fa-file-pdf-o text-danger"></i>',
				'zip': '<i class="fas fa-file-archive text-muted"></i>',
				'htm': '<i class="fas fa-file-code text-info"></i>',
				'txt': '<i class="fas fa-file-text text-info"></i>',
				'mov': '<i class="fas fa-file-video text-warning"></i>',
				'mp3': '<i class="fas fa-file-audio text-warning"></i>',
				'jpg': '<i class="fa fa-file-o"></i>', 
        		'gif': '<i class="fa fa-file-o"></i>', 
        		'png': '<i class="fa fa-file-o"></i>'
        		
			},
			previewFileExtSettings: { // configure the logic for determining icon file extensions
				'doc': function(ext) {
					return ext.match(/(doc|docx)$/i);
				},
				'xls': function(ext) {
					return ext.match(/(xls|xlsx)$/i);
				},
				'ppt': function(ext) {
					return ext.match(/(ppt|pptx)$/i);
				},
				'zip': function(ext) {
					return ext.match(/(zip|rar|tar|gzip|gz|7z)$/i);
				},
				'htm': function(ext) {
					return ext.match(/(htm|html)$/i);
				},
				'txt': function(ext) {
					return ext.match(/(txt|ini|csv|java|php|js|css)$/i);
				},
				'mov': function(ext) {
					return ext.match(/(avi|mpg|mkv|mov|mp4|3gp|webm|wmv)$/i);
				},
				'mp3': function(ext) {
					return ext.match(/(mp3|wav)$/i);
				},
				
				'jpg': function(ext) {
					return ext.match(/(jpg|jpeg)$/i);
				}
			}
		};


		if (optionPreviewData != "" && optionPreviewData != null) {
			optionFileInput = $.extend(optionFileInput, JSON.parse(URI.decode(optionPreviewData)))
		}




		$(this).fileinput(optionFileInput).on('filebatchpreupload', function(event, data) {

			$.each(data.files, function(i, value) {
				let uniqueIdFile = generate.randomByDate();
				let FilenameSplit = value.name.split(".");
				let filenameNewFormat = "";

				if (FilenameSplit.length > 1) {
					$(FilenameSplit).each(function(i, v) {
						if (i == FilenameSplit.length - 1) {
							filenameNewFormat += "_" + uniqueIdFile + ".";
						}
						filenameNewFormat += v;
					});
				} else {
					filenameNewFormat = data.files[0].name + "_" + uniqueIdFile;
				}
				data.form.append('file_elements', value, URI.encode(filenameNewFormat));
			});

		}).on('filebatchuploadsuccess', function(event, data) {
			console.log("Insert Data");
			console.log(data);
			var form = data.form, files = data.files, extra = data.extra,
				response = data.response, reader = data.reader;
			if (response.result) {
			
			let listFileSend = (form.getAll("file_elements"));
			let DocumentList = [];
			
			console.log(listFileSend);
			
			$(listFileSend).each(function(i,v){
				let type = v.type.split('/')
				DocumentList.push({
					documentlocation : URI.decode(v.name),
					document_type: type[1]
				});
			});

								

			let url = contextPath + '/api/manual_document/insertManualDocumentList'
			ajaxProcess.submit(url, 'POST', DocumentList , '#section-standard-document', (data)=>{
				
				$('#Document').find('#upload').empty();
				loadResource();
				initialFileInput();		
				
			});

				console.log("OK");
				console.log(DocumentList);
			}

		}).on('filebeforedelete', function() {
			return new Promise(function(resolve, reject) {
				ConfirmModal.setupModal("warning", "ยืนยัน", "ยืนยันการลบเอกสารนี้ใช่หรือไม่?");
				ConfirmModal.confirmResult(function(isTrue) {
					if (isTrue) {
						resolve();
					}
				});
			});
		});

	});
}