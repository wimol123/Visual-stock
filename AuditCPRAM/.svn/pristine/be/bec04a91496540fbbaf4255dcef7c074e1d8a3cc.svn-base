var questionaireDocumentObj = null, supplierQuestionaireDocumentObj = null, userObjSession = null;;
$(function(){
	loadResource();
});

function loadResource(){
	loadSession();
	loadQuestionaireDocumentDataList();
	loadSupplierQuestionaireDocumentDataList();
	generateStandardDocument();
}

function loadQuestionaireDocumentDataList(){

	let url = contextPath+'/api/questionaire_document/questionaire_document_list';
	ajaxProcess.submit(url, 'GET', null, '#section-questionair-document', (data)=>{
		if(data.result){
			questionaireDocumentObj = JSON.parse(data.message);
			
		}		
	},{async : false});
	
}

function loadSupplierQuestionaireDocumentDataList(){
	let url = contextPath+'/api/supplier_questionaire_document/supplier_questionaire_document_list/{}';
	ajaxProcess.submit(url, 'GET', null, '#section-questionair-document', (data)=>{
		if(data.result){
			supplierQuestionaireDocumentObj = JSON.parse(data.message);
		}		
	},{async : false});
}

function loadSession(){
	let url = contextPath +"/api/user/get_session_user";
	
	$.ajax({
		url : url,
		type : 'GET',
		contentType: "application/json",
		async : false,
		success : function(data){
			userObjSession = JSON.parse(data.message);			
		}
	});
}

function generateStandardDocument(questionaireDocumentArray, supplierQuestionaireDocumentArray){
	if(questionaireDocumentArray == null){
		questionaireDocumentArray = questionaireDocumentObj;
	}
	
	if(supplierQuestionaireDocumentArray == null){
		supplierQuestionaireDocumentArray = supplierQuestionaireDocumentObj;
	}
	
	
	if(questionaireDocumentArray.length > 0){
		let numOfStandardInRow = 2;
		let numOfRow = Math.ceil(questionaireDocumentArray.length/numOfStandardInRow);
		let wideColumn = Math.ceil(12/numOfStandardInRow);
		
		for(i = 0; i< numOfRow; i++ ){
			let htmlPortlet = '<div class="row form-group">';
				for(j = numOfStandardInRow*i; (j < numOfStandardInRow*(i+1)) && (j < questionaireDocumentArray.length); j++){
					htmlPortlet += '<div class="col-md-'+wideColumn+'">';
						htmlPortlet += genrateSectionStandardDocument(questionaireDocumentArray[j], supplierQuestionaireDocumentArray);
					htmlPortlet += '</div>';
				}
			htmlPortlet += '</div>';
			$('#section-questionair-document').find('.body-questionair-document-list').append(htmlPortlet);
		}
		initalFileInputDocumentTemplate();
		initalFileInputQuestionaireDocument();
	}	
}

function genrateSectionStandardDocument(questionaireDocumentO, supplierQuestionaireDocumentArray){
	if(questionaireDocumentO == null || typeof questionaireDocumentO === "undefined"){
		return false;
	}else{
	
		let supplierQuestionaireDocumentByType = null, questionaireDocumentTemplate = null;
		
		if(supplierQuestionaireDocumentArray != null && supplierQuestionaireDocumentArray.length > 0){
			supplierQuestionaireDocumentByType = $.grep(supplierQuestionaireDocumentArray, function(v){
				return v.questionaireDocumentId.questionaireDocumentId == questionaireDocumentO.questionaireDocumentId && v.questionaireDocumentTypeId.questionaireDocumentTypeId == "2";
			});
			
			questionaireDocumentTemplate = $.grep(supplierQuestionaireDocumentArray, function(v){
				return v.questionaireDocumentId.questionaireDocumentId == questionaireDocumentO.questionaireDocumentId && v.questionaireDocumentTypeId.questionaireDocumentTypeId == "1";
			});
		}
				
		let html = '<div class="portlet light questionaire_document_portlet">';
		
			html += '<div class="portlet-title">';
				html += '<div class="caption font-green-sharp">';
					html += '<span class="caption-subject bold uppercase questionaire_document_name">'+questionaireDocumentO.questionaireDocumentName+'</span>';
					html += '<input class="form-control questionaire_document_id hide" value="'+questionaireDocumentO.questionaireDocumentId+'" />';
				html += '</div>';
				html += '<div class="actions">';
					html += '<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>';
					html += '<a class="btn btn-circle btn-icon-only btn-default font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">';
						html += '<i class="fa fa-angle-down rotate-icon"></i>';
					html += '</a>';
				html += '</div>';
			html += '</div>';
			
			html += '<div class="portlet-body form form-body-collapse">';
				html+='<div class="row">';
            		html+= '<div class="col-md-12">';
            			html+= '<div class="center-block error-choose-file" style="width:800px;display:none"></div>';
            		html += '</div>';
            	html += '</div>';
            	
            	html += '<div class="caption font-blue-ebonyclay">';
            		if(userObjSession.userGroupId.userGroupId == "3" || userObjSession.userGroupId.userGroupId == "4"){
            			html += '<h4 class="caption-subject"><i class="fa fa-bars" aria-hidden="true"></i> ดาวน์โหลดแบบฟอร์ม '+questionaireDocumentO.questionaireDocumentName+'</h4>'
            		}else{
            			html += '<h4 class="caption-subject"><i class="fa fa-bars" aria-hidden="true"></i> อัพโหลดแบบฟอร์ม '+questionaireDocumentO.questionaireDocumentName+'</h4>'
            		}        			
        		html += '</div>';
        		
        		
				let optionFileInputTemplate = {
					layoutTemplates : {main2: '{preview} {remove} {browse} {upload}'},
					defaultPreviewContent : '<img src="'+contextPath+'/assets/images/picture-attachment.png" alt="Your Standard Document"><h6 class="text-muted">เลือกไฟล์</h6>',					
					showClose: false,
				    showCaption: false,
				    showBrowse: true,
					showUpload: true,
					showRemove: false,
					fileActionSettings: {
			        	showUpload: false,
			        	showDownload : true,
			        	showZoom : false,
			        	showDrag : false,
			        	showOther : true,
			        	showRemove : true
			        }
				} 
				
				if(userObjSession.userGroupId.userGroupId == "3" || userObjSession.userGroupId.userGroupId == "4"){
					optionFileInputTemplate.layoutTemplates = {main2: '{preview}'};
					optionFileInputTemplate.defaultPreviewContent = '<h5 class="text-muted">ไม่พบแบบฟอร์มแบบสอบถาม</h5>';
					optionFileInputTemplate.showBrowse = false;
					optionFileInputTemplate.showUpload = false;
					optionFileInputTemplate.fileActionSettings.showOther = false;
					optionFileInputTemplate.fileActionSettings.showRemove = false;
					//optionFileInputTemplate.dropZoneEnabled = false;
				}		
				            	
            	if(questionaireDocumentTemplate != null && questionaireDocumentTemplate.length > 0){
        			let initialPreviewData = [];
					let initialPreviewConfigData = [];
					
					$(questionaireDocumentTemplate).each(function(i,v){
						let typeFile = (v.supplierQuestionaireDocumentLocation.split('/')[v.supplierQuestionaireDocumentLocation.split('/').length-1]).trim().split('.')[(v.supplierQuestionaireDocumentLocation.split('/')[v.supplierQuestionaireDocumentLocation.split('/').length-1]).trim().split('.').length-1];
    					let fileName = (v.supplierQuestionaireDocumentLocation.split('/')[v.supplierQuestionaireDocumentLocation.split('/').length-1]).trim();
    					initialPreviewData.push(v.supplierQuestionaireDocumentLocation);
    					initialPreviewConfigData.push({
							type : typeFile.trim(),
							caption : fileName,
							filename : fileName,
							downloadUrl : v.supplierQuestionaireDocumentLocation.trim(),
							key : v.supplierQuestionaireDocumentId.trim(),
							url : contextPath+'/api/supplier_questionaire_document/delete_supplier_questionaire_document'
						});
					});
					
					$.extend(optionFileInputTemplate, {initialPreview : initialPreviewData, initialPreviewConfig : initialPreviewConfigData});					
					
					html += '<div class="row">';
						html += '<div class="col-md-12 section-fileinput-document-template">';
							html += '<form method="POST" enctype="multipart/form-data" accept-charset="UTF-8">';
    							html += '<input class="questionaire-document-template-input file" type="file" name="questionaire-document-template-input" data-preview-file-type="files/*" accept=".doc, .pdf, .docx">';
    							html += '<input class="form-control optionPreviewTemplate hide" type="text" name="optionPreviewTemplate" value="'+URI.encode(JSON.stringify(optionFileInputTemplate))+'">';
    						html+='</form>';
						html += '</div>';
					html += '</div>';
					
            	}else{
            		html += '<div class="row">';
						html += '<div class="col-md-12 section-fileinput-document-template">';
							html += '<form method="POST" enctype="multipart/form-data" accept-charset="UTF-8">';
								html += '<input class="questionaire-document-template-input file" type="file" name="questionaire-document-template-input" data-preview-file-type="files/*" accept=".doc, .pdf, .docx">';
								html += '<input class="form-control optionPreviewTemplate hide" type="text" name="optionPreviewTemplate" value="'+URI.encode(JSON.stringify(optionFileInputTemplate))+'">';
							html+='</form>';
						html += '</div>';
					html += '</div>';
            	}
            	
            	
            	
            	if(userObjSession.userGroupId.userGroupId == "3" || userObjSession.userGroupId.userGroupId == "4"){
            		html += '<div class="caption font-blue-ebonyclay">';
	            		html += '<h4 class="caption-subject"><i class="fa fa-bars" aria-hidden="true"></i> อัพโหลด '+questionaireDocumentO.questionaireDocumentName+'</h4>'     			
	        		html += '</div>';
	        		
	        		let optionFileInputSupplierQuestionaireDocument = {
						layoutTemplates : {main2: '{preview} {remove} {browse} {upload}'},
						defaultPreviewContent : '<img src="'+contextPath+'/assets/images/picture-attachment.png" alt="Your Standard Document"><h6 class="text-muted">เลือกไฟล์</h6>',					
						showClose: false,
					    showCaption: false,
					    showBrowse: true,
						showUpload: true,
						showRemove: false,
						fileActionSettings: {
				        	showUpload: false,
				        	showDownload : true,
				        	showZoom : false,
				        	showDrag : false,
				        	showOther : true,
				        	showRemove : true
				        }
					}
	        		
	        		if(supplierQuestionaireDocumentByType != null && supplierQuestionaireDocumentByType.length > 0){
	        			let initialPreviewDataSupplierQuestionaireDocument = [];
	        			let initialPreviewConfigDataSupplierQuestionaireDocument = [];
	        			
	        			$(supplierQuestionaireDocumentByType).each(function(i,v){
	        				let typeFile = (v.supplierQuestionaireDocumentLocation.split('/')[v.supplierQuestionaireDocumentLocation.split('/').length-1]).trim().split('.')[(v.supplierQuestionaireDocumentLocation.split('/')[v.supplierQuestionaireDocumentLocation.split('/').length-1]).trim().split('.').length-1];
	    					let fileName = (v.supplierQuestionaireDocumentLocation.split('/')[v.supplierQuestionaireDocumentLocation.split('/').length-1]).trim();
	    					initialPreviewDataSupplierQuestionaireDocument.push(v.supplierQuestionaireDocumentLocation);
	    					initialPreviewConfigDataSupplierQuestionaireDocument.push({
								type : typeFile.trim(),
								caption : fileName,
								filename : fileName,
								downloadUrl : v.supplierQuestionaireDocumentLocation.trim(),
								key : v.supplierQuestionaireDocumentId.trim(),
								url : contextPath+'/api/supplier_questionaire_document/delete_supplier_questionaire_document'
							});
	        			});
	        			
	        			$.extend(optionFileInputSupplierQuestionaireDocument, {initialPreview : initialPreviewDataSupplierQuestionaireDocument, initialPreviewConfig : initialPreviewConfigDataSupplierQuestionaireDocument});
	        			
	        			html += '<div class="row">';
    						html += '<div class="col-md-12 section-fileinput">';
    							html += '<form method="POST" enctype="multipart/form-data" accept-charset="UTF-8">';
	    							html += '<input class="supplier-questionaire-document-input file" type="file" name="supplier-questionaire-document-input[]" multiple data-preview-file-type="files/*" accept=".doc, .pdf, .docx">';
	    							html += '<input class="form-control optionPreview hide" type="text" name="optionPreview" value="'+URI.encode(JSON.stringify(optionFileInputSupplierQuestionaireDocument))+'">';
    							html+='</form>';
    						html += '</div>';
    					html += '</div>';
	        			
	        		}else{
	        			html += '<div class="row">';
    						html += '<div class="col-md-12 section-fileinput">';
    							html += '<form method="POST" enctype="multipart/form-data" accept-charset="UTF-8">';
	    							html += '<input class="supplier-questionaire-document-input file" type="file" name="supplier-questionaire-document-input[]" multiple data-preview-file-type="files/*" accept=".doc, .pdf, .docx">';
	    							html += '<input class="form-control optionPreview hide" type="text" name="optionPreview" value="'+URI.encode(JSON.stringify(optionFileInputSupplierQuestionaireDocument))+'">';
    							html+='</form>';
    						html += '</div>';
    					html += '</div>';
	        		}
            	}
            					
			html += '</div>';
		
		html+= '</div>';
		
		return html;
	}
}


function initalFileInputDocumentTemplate(){
	
	$('.questionaire-document-template-input').each(function(){
		let optionPreviewTemplateData = $(this).closest('.section-fileinput-document-template').find('.optionPreviewTemplate').val().trim();
		
		let optionFileInputDocumentTemplate = {
			theme: "explorer",
		    language: "th",
			autoOrientImage :true,
			maxFileSize: 3000,
		    minFileCount: 1,
		    maxFileCount: 1,
		    overwriteInitial: false,
		    previewFileIcon: '<i class="fa fa-file-o"></i>',
		    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
		    allowedFileExtensions: ["doc", "pdf", "docx"],
		    initialPreviewAsData: true,
		    preferIconicPreview: true, // this will force thumbnails to display icons for following file extensions
		    purifyHtml: true, // this by default purifies HTML data for preview 
		    uploadUrl: contextPath+'/api/upload/file',
	        uploadAsync: false,	        
		    previewFileIconSettings: { // configure your icon file extensions
		        'doc': '<i class="fa fa-file-word-o text-primary"></i>',
		        'pdf': '<i class="fa fa-file-pdf-o text-danger"></i>' 
		    },
		    previewFileExtSettings: { // configure the logic for determining icon file extensions
		        'doc': function(ext) {
		            return ext.match(/(doc|docx)$/i);
		        },
		        'pdf': function(ext) {
		            return ext.match(/(pdf)$/i);
		        }
		    }
		};
		
		if(optionPreviewTemplateData != "" && optionPreviewTemplateData != null){
			optionFileInputDocumentTemplate = $.extend(optionFileInputDocumentTemplate,JSON.parse(URI.decode(optionPreviewTemplateData)))
		}
		
		$(this).fileinput(optionFileInputDocumentTemplate).on('filebatchpreupload', function(event, data){
			$.each(data.files, function(i,value){
			
				let uniqueIdFile = generate.randomByDate();
				let FilenameSplit = value.name.split(".");
				let filenameNewFormat = "";
				if(FilenameSplit.length > 1){
					$(FilenameSplit).each(function(i,v){
						if(i == FilenameSplit.length-1){
							filenameNewFormat += "_"+uniqueIdFile+".";
						}
						filenameNewFormat += v;
					});
				}else{
					filenameNewFormat = data.files[0].name + "_" + uniqueIdFile;
				}
				data.form.append('file_elements', value, URI.encode(filenameNewFormat));
			});
		}).on('filebatchuploadsuccess', function(event, data) {
		
			let form = data.form, files = data.files, extra = data.extra,
	        response = data.response, reader = data.reader;
					
			
			if(response.result){
			
				let questionaireDocumentId = $(this).closest('.portlet').find('.portlet-title').find('.questionaire_document_id').val();
				let listFileSend = (form.getAll("file_elements"));
				
				let supplierQuestionaireDocumentList = [];
				$(listFileSend).each(function(index, file){
					supplierQuestionaireDocumentList.push({
						supplierQuestionaireDocumentLocation : URI.decode(file.name),
						questionaireDocumentTypeId : {
							questionaireDocumentTypeId : 1
						},
						questionaireDocumentId : {
							questionaireDocumentId : questionaireDocumentId
						},
						enable : 'Y'
					});
				});
				
				let url = contextPath+'/api/supplier_questionaire_document/insert_supplier_questionaire_document_list';
				ajaxProcess.submit(url, 'POST', supplierQuestionaireDocumentList, '#section-questionair-document', (data)=>{
				
					$('#section-questionair-document').find('.portlet-body').empty();
					loadResource();
					
				});
			}
			
		}).on('filebeforedelete', function() {
			return new Promise(function(resolve, reject) {
				ConfirmModal.setupModal("warning","ยืนยัน", "ยืนยันการลบแบบฟอร์มแบบสอบถามนี้ใช่หรือไม่?");	
				ConfirmModal.confirmResult(function(isTrue){
					if(isTrue){
						resolve();
					}
				});
			});
		});
		
	});
}

function initalFileInputQuestionaireDocument(){
	$('.supplier-questionaire-document-input').each(function(){
		let optionPreviewSupplierDocumentData = $(this).closest('.section-fileinput').find('.optionPreview').val().trim();
		
		let optionFileInputSupplierQuestionaireDocument = {
			theme: "explorer",
		    language: "th",
			autoOrientImage :true,
			maxFileSize: 3000,
		    minFileCount: 1,
		    maxFileCount: 1,
		    overwriteInitial: false,
		    previewFileIcon: '<i class="fa fa-file-o"></i>',
		    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
		    allowedFileExtensions: ["doc", "pdf", "docx"],
		    initialPreviewAsData: true,
		    preferIconicPreview: true, // this will force thumbnails to display icons for following file extensions
		    purifyHtml: true, // this by default purifies HTML data for preview 
		    uploadUrl: contextPath+'/api/upload/file',
	        uploadAsync: false,	        
		    previewFileIconSettings: { // configure your icon file extensions
		        'doc': '<i class="fa fa-file-word-o text-primary"></i>',
		        'pdf': '<i class="fa fa-file-pdf-o text-danger"></i>' 
		    },
		    previewFileExtSettings: { // configure the logic for determining icon file extensions
		        'doc': function(ext) {
		            return ext.match(/(doc|docx)$/i);
		        },
		        'pdf': function(ext) {
		            return ext.match(/(pdf)$/i);
		        }
		    }
		};
		
		if(optionPreviewSupplierDocumentData != "" && optionPreviewSupplierDocumentData != null){
			optionFileInputSupplierQuestionaireDocument = $.extend(optionFileInputSupplierQuestionaireDocument,JSON.parse(URI.decode(optionPreviewSupplierDocumentData)))
		}
		
		$(this).fileinput(optionFileInputSupplierQuestionaireDocument).on('filebatchpreupload', function(event, data){
			$.each(data.files, function(i,value){
			
				let uniqueIdFile = generate.randomByDate();
				let FilenameSplit = value.name.split(".");
				let filenameNewFormat = "";
				if(FilenameSplit.length > 1){
					$(FilenameSplit).each(function(i,v){
						if(i == FilenameSplit.length-1){
							filenameNewFormat += "_"+uniqueIdFile+".";
						}
						filenameNewFormat += v;
					});
				}else{
					filenameNewFormat = data.files[0].name + "_" + uniqueIdFile;
				}
				data.form.append('file_elements', value, URI.encode(filenameNewFormat));
			});
		}).on('filebatchuploadsuccess', function(event, data) {
		
			let form = data.form, files = data.files, extra = data.extra,
	        response = data.response, reader = data.reader;
					
			
			if(response.result){
			
				let questionaireDocumentId = $(this).closest('.portlet').find('.portlet-title').find('.questionaire_document_id').val();
				let listFileSend = (form.getAll("file_elements"));
				
				let supplierQuestionaireDocumentList = [];
				$(listFileSend).each(function(index, file){
					supplierQuestionaireDocumentList.push({
						supplierQuestionaireDocumentLocation : URI.decode(file.name),
						questionaireDocumentTypeId : {
							questionaireDocumentTypeId : 2
						},
						questionaireDocumentId : {
							questionaireDocumentId : questionaireDocumentId
						},
						enable : 'Y'
					});
				});
				
				let url = contextPath+'/api/supplier_questionaire_document/insert_supplier_questionaire_document_list';
				ajaxProcess.submit(url, 'POST', supplierQuestionaireDocumentList, '#section-questionair-document', (data)=>{
				
					$('#section-questionair-document').find('.portlet-body').empty();
					loadResource();
					
				});
			}
			
		}).on('filebeforedelete', function() {
			return new Promise(function(resolve, reject) {
				ConfirmModal.setupModal("warning","ยืนยัน", "ยืนยันการลบแบบฟอร์มแบบสอบถามนี้ใช่หรือไม่?");	
				ConfirmModal.confirmResult(function(isTrue){
					if(isTrue){
						resolve();
					}
				});
			});
		});
		
		
	});
}
