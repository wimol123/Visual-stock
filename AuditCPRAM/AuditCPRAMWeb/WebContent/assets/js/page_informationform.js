//var objFormWizard = null, tableCondition = null;
var informationPicture = [];
var informationPictureObj = null;
var informationDocument = [];
var informationDocumentObj = null;
var objInformationDetail = null;
var product = null;
var supList = [];
var isBrowse = true;
var textShowInput = "เลือกไฟล์";
var iconFile = '{upload} {download} {delete}';
$(function(){
	$.fn.bootstrapSwitch.defaults.size = 'normal';
	$.fn.bootstrapSwitch.defaults.onColor = 'success';
	$('.btn_accept').hide();
	if($('#information_detail').val() != ""){
		$(':button[type="send"]').prop('disabled', false);
		let stringInformationDetail = URI.decode($('#information_detail').val());
		console.log("1234",stringInformationDetail);
		objInformationDetail = JSON.parse(stringInformationDetail);
		console.log(objInformationDetail);
		if(objInformationDetail.typeDetail == "supplier"){
			setTimeout(function(){
				$("#link").prop("href", "information_supplier.jsp");
			},500);
		}
		$("#information_id").val(objInformationDetail.informationId);
		getInformationDetail();
	}else{
		isBrowse = false;
		$(':button[type="send"]').prop('disabled', true);
		$('#btn_send_information').prop('disabled', true);
		$("#text-image").show();
		$("#text-file").show();
		loadResourcePicture();
		loadResourceDocument();
		genProductType();
	}
	loadSupplier();
});

function loadResourcePicture() {
	if($("#information_id").val() != ""){
		loadPictureListData();
	}
	genrateInformationPicture();
	initialPictureInput();
}

function loadResourceDocument() {
	if($("#information_id").val() != ""){
		loadDocumentListData();
	}
	genrateInformationDocument();
	initialFileInput();
}

function loadPictureListData() {
	let json = {
			informationId: $("#information_id").val()
	};
	let url = contextPath + '/api/information/get_information_picture_list'
	ajaxProcess.submit(url, 'POST', json, '#information_form', (data)=>{
		if (data.result) {
			informationPictureObj = JSON.parse(data.message)
		}
	}, { async: false });
}

function loadDocumentListData() {
	let json = {
			informationId: $("#information_id").val()
	};
	let url = contextPath + '/api/information/get_information_document_list'
	ajaxProcess.submit(url, 'POST', json, '#information_form', (data)=>{
		if (data.result) {
			informationDocumentObj = JSON.parse(data.message)
		}
	}, { async: false });
}


function genrateInformationPicture(InformationPictureArray) {
	$('#image').find('#upload').empty();
	if (InformationPictureArray == null) {
		InformationPictureArray = informationPictureObj;
	}
	let htmlPortlet = genrateSectionInformationPicture(InformationPictureArray);
	console.log("htmlPortlet",htmlPortlet);
	$('#image').find('#upload').append(htmlPortlet);

}

function genrateInformationDocument(InformationDocumentArray) {
	$('#document').find('#upload').empty();
	if (InformationDocumentArray == null) {
		InformationDocumentArray = informationDocumentObj;
	}
	let htmlPortlet = genrateSectionInformationDocument(InformationDocumentArray);
	console.log("htmlPortlet",htmlPortlet);
	$('#document').find('#upload').append(htmlPortlet);

}

function genProductType(){
	let url = contextPath + "/api/product_type/product_type_list"
	ajaxProcess.submit(url, 'GET', null, null, function(data){
		let dataObj = JSON.parse(data.message);
    	var dataSource = [];
    	$(dataObj).each(function(i,value){
    		dataSource.push({
    			id : value.productTypeId,
    			text  : value.name
    		});
    	});
    	$("#select_product_type").select2({
    		/*dropdownParent: $('.portlet-body'),*/
    		placeholder: "เลือกประเภทสินค้า",
    		theme: "bootstrap",
    		data: dataSource
    	});
    	if($('#information_detail').val() == ""){
    		$(".group_product").hide();
    	}
    	$('#select_product_type').val(product).trigger('change');
	});	
	
}

$("#select_group_type").on('change',function(){
	$(".filter-option-inner-inner").html("กรุณาเลือก Supplier");
	if($("#select_group_type").val() == "1"){
		$(".group_product").show();
		$(".group_supplier").hide();
	}else if($("#select_group_type").val() == "2"){
		$(".group_supplier").show();
		$(".group_product").hide();
	}else{
		$(".group_product").hide();
		$(".group_supplier").hide();
	}
});

function loadSupplier(){
	console.log("supList.size()",supList.length);
	$.ajax({
		url: contextPath + "/api/supplier/supplier_list",
	    type: 'GET',
	    async: true,
	    contentType: 'application/json; charset=UTF-8',
	    success : function(data, msg, jqXHR) {
	    	if(data.result == true){
	    		try{
	    			var str = '';
	    			let objJSON = JSON.parse(data.message);
	    			var i = 0;
	    			var countSup = 0;
	    			$.each(objJSON,function(index,value){
	    				var opt;
	    				if(supList[i] < value.supplierId){
	    					i++;
	    				}
	    				if(supList[i] == value.supplierId){
	    					i++;
	    					countSup++;
		    				opt = $("<option selected>");
	    				}else{
		    				opt = $("<option>");
	    				}
	    				$(opt).val(value.supplierId);
	    				$(opt).text(value.supplierCode +" "+value.supplierCompany);
	    				str += opt[0].outerHTML;
	    			});
	    			if(countSup > 0){
	    				text = "เลือก "+ countSup +" รายการ";
		    			$("#select_supplier_text").val(text);
	    			}
	    			
	    			$('#select_supplier').append(str);
	    			$('select[name="select_supplier"]').bootstrapDualListbox({
	    				    nonSelectedListLabel: 'ซัพพลายเออร์ที่สามารถเลือกได้',
	    				    selectedListLabel: 'ซัพพลายเออร์ที่เลือกแล้ว',
	    				    preserveSelectionOnMove: 'moved',
	    				    moveOnSelect: true,
	    				    moveAllLabel: 'Move all',
	    				    removeAllLabel: 'Remove all',
	    				    infoTextEmpty: 'ไม่มีรายการ',
	    				    infoText: 'มี {0} รายการ'
	    			});
	    		}catch(e){
	    			customMessageDialog("error", "เกิดข้อผิดพลาด", e, null);
	    		}
	    	}
	    	
	    	  	
		},
		error: function (jqXHR, textStatus, errorThrown) {
			try{
				customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseJSON.message, null);
			}catch(Execption){
				customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
			}
			
	    },
	});
}

function initialPictureInput() {
	$(".standard-picture-input").each(function(i, v) {
		let optionPreviewData = $(this).closest('.section-pictureinput').find('.optionPreview').val().trim();
		let optionFileInput = {
			theme: "explorer",
			language: "th",
			showClose: false,
			showCaption: false,
			showBrowse: isBrowse,
			showUpload: true,
			showRemove: false,
			autoOrientImage: true,
			maxFileSize: 3000,
			minFileCount: 1,
			maxFileCount: 100,
			overwriteInitial: false,
			previewFileIcon: '<i class="fa fa-file-o"></i>',
			removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
			defaultPreviewContent: '<img src="' + contextPath + '/assets/images/picture-attachment.png" alt="Your Standard Document"><h6 class="text-muted">'+textShowInput+'</h6>',
			layoutTemplates: {
				main2: '{preview} {remove} {browse} {upload} ',
				actions: '{drag}\n' +
					'<div class="file-actions">\n' +
					'    <div class="file-footer-buttons">\n' +
					iconFile+'  \n' +
					'    </div>\n' +
					'</div>'
			},
			allowedFileExtensions: ["jpg", "jpeg", "png"],
			initialPreviewAsData: true,
			preferIconicPreview: true, // this will force thumbnails to display icons for following file extensions
			purifyHtml: true, // this by default purifies HTML data for preview	    
			uploadUrl: contextPath + '/api/upload/picture',
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
				data.form.append('picture_elements', value, URI.encode(filenameNewFormat));
			});

		}).on('filebatchuploadsuccess', function(event, data) {
			console.log("Insert Data");
			console.log(data);
			var form = data.form, files = data.files, extra = data.extra,
				response = data.response, reader = data.reader;
			if (response.result) {
				let informationId = $('#information_id').val();
				let listFileSend = (form.getAll("picture_elements"));
	
				console.log("listFileSend",listFileSend);
				let informationDocumentList = [];
				$(listFileSend).each(function(i,v){
					informationDocumentList.push({
						informationDocumentLocation : URI.decode(v.name),
						informationDocumentType : 'img',
						informationId : {
							informationId : informationId
						}
					});
				});
				console.log("test",informationDocumentList);
				let url = contextPath+'/api/information/insert_information_document_list';
				ajaxProcess.submit(url, 'POST', informationDocumentList, '#information_form', (data)=>{
					$('#information_form').find('.portlet-body').empty();
					loadResourcePicture();
				});
			}

		}).on('filebeforedelete', function() {
			return new Promise(function(resolve, reject) {
				ConfirmModal.setupModal("warning", "ยืนยัน", "ยืนยันการลบรูปภาพนี้ใช่หรือไม่?");
				ConfirmModal.confirmResult(function(isTrue) {
					if (isTrue) {
						resolve();
					}
				});
			});
		});

	});
}

function initialFileInput(){
	
	$(".standard-document-input").each(function(i, v) {
		let optionPreviewData = $(this).closest('.section-fileinput').find('.optionPreview').val().trim();

		let optionFileInput = {
			theme: "explorer",
			language: "th",
			showClose: false,
			showCaption: false,
			showBrowse: isBrowse,
			showUpload: true,
			showRemove: false,
			autoOrientImage: true,
			maxFileSize: 3000,
			minFileCount: 1,
			maxFileCount: 100,
			overwriteInitial: false,
			previewFileIcon: '<i class="fa fa-file-o"></i>',
			removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
			defaultPreviewContent: '<img src="' + contextPath + '/assets/images/picture-attachment.png" alt="Your Standard Document"><h6 class="text-muted">'+textShowInput+'</h6>',
			layoutTemplates: {
				main2: '{preview} {remove} {browse} {upload} ',
				actions: '{drag}\n' +
					'<div class="file-actions">\n' +
					'    <div class="file-footer-buttons">\n' +
					iconFile+ ' \n' +
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
				let informationId = $('#information_id').val();
				let listFileSend = (form.getAll("file_elements"));
	
				console.log("listFileSend",listFileSend);
				let informationDocumentList = [];
				$(listFileSend).each(function(i,v){
					informationDocumentList.push({
						informationDocumentLocation : URI.decode(v.name),
						informationDocumentType : 'doc',
						informationId : {
							informationId : informationId
						}
					});
				});
				console.log("test",informationDocumentList);
				let url = contextPath+'/api/information/insert_information_document_list';
				ajaxProcess.submit(url, 'POST', informationDocumentList, '#information_form', (data)=>{
					$('#information_form').find('.portlet-body').empty();
					loadResourceDocument();
				});
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

$("#select_supplier").on('change',function(){
	try{
		var select_supplier_length = $("#select_supplier").val().length;

		console.log($('[name="select_supplier"]').val());
		console.log("select_supplier_length",select_supplier_length);
		var length = $('#select_supplier option').length;
		if(select_supplier_length > 2){
			setTimeout(function(){
				$(".filter-option-inner-inner").html(select_supplier_length+" OF "+length+" SELECTED");
			},1);
		}else if(select_supplier_length == 0){
			setTimeout(function(){
				$(".filter-option-inner-inner").html("กรุณาเลือก Supplier");
			},1);
		}
	}catch(err) {
		setTimeout(function(){
			$(".filter-option-inner-inner").html("กรุณาเลือก Supplier");
		},1);
	}
});

function genrateSectionInformationPicture(InformationPictureArray){
		let InformationPictureByType = null;
		if (InformationPictureArray != null) {
			InformationPictureByType = InformationPictureArray;
		}
		console.log("InformationDocumentByType",InformationPictureByType);
		
		let html = '<div class="portlet light standard_picture_portlet">';
		
			html += '<div class="portlet-body form form-body-collapse">';
				html+='<div class="row">';
            		html+= '<div class="col-md-12">';
            			html+= '<div class="center-block error-choose-file" style="width:800px;display:none"></div>';
            		html += '</div>';
            	html += '</div>';
            	
            	if(InformationPictureByType != null){
            		if(InformationPictureByType.length == 0){
    					html += '<div class="row">';
    						html += '<div class="col-md-12 section-pictureinput">';
    							html += '<input class="standard-picture-input file" type="file" name="standard-picture-input[]" multiple data-preview-file-type="files/*" accept=".jpg, .jpeg, .png">';
    							html += '<input class="form-control optionPreview hide" type="text" name="optionPreview">';
    						html += '</div>';
    					html += '</div>';
    				}else{
    					let initialPreviewData = [];
    					let initialPreviewConfigData = [];
    					$(InformationPictureByType).each(function(i,v){
    						
    						let typeFile = (v.informationDocumentLocation.split('/')[v.informationDocumentLocation.split('/').length-1]).trim().split('.')[(v.informationDocumentLocation.split('/')[v.informationDocumentLocation.split('/').length-1]).trim().split('.').length-1];
    						let fileName = (v.informationDocumentLocation.split('/')[v.informationDocumentLocation.split('/').length-1]).trim()
    						initialPreviewData.push(v.informationDocumentLocation);
    						initialPreviewConfigData.push({
    							type : typeFile.trim(),
    							caption : fileName,
    							filename : fileName,
    							downloadUrl : v.informationDocumentLocation.trim(),
    							key : v.informationDocumentId.trim(),
    							url : contextPath+'/api/information/delete_information_document',
    						})
    					});
    					console.log("initialPreviewData",initialPreviewData);
    					console.log("initialPreviewConfigData",initialPreviewConfigData);
    					let optionFileInput = {
    						initialPreview : initialPreviewData,
    						initialPreviewConfig : initialPreviewConfigData
    					} 
    					console.log("optionFileInput",optionFileInput);
    					
    					html += '<div class="row">';
    						html += '<div class="col-md-12 section-pictureinput">';
    							html += '<form method="POST" enctype="multipart/form-data" accept-charset="UTF-8">';
	    							html += '<input class="standard-picture-input file" type="file" name="standard-picture-input[]" multiple data-preview-file-type="files/*" accept=".jpg, .jpeg, .png">';
	    							html += '<input class="form-control optionPreview hide" type="text" name="optionPreview" value="'+URI.encode(JSON.stringify(optionFileInput))+'">';
	    						html+='</form>';
    						html += '</div>';
    					html += '</div>';
    					
    				}
            	}else{
            		html += '<div class="row">';
						html += '<div class="col-md-12 section-pictureinput">';
							html += '<input class="standard-picture-input file" type="file" name="standard-picture-input[]" multiple data-preview-file-type="files/*" accept=".jpg, .jpeg, .png">';
							html += '<input class="form-control optionPreview hide" type="text" name="optionPreview">';
						html += '</div>';
					html += '</div>';
            	}	
				
			html += '</div>';
			
		html+= '</div>';
		return html;
}


function genrateSectionInformationDocument(InformationDocumentArray){
		let InformationDocumentByType = null;
		if (InformationDocumentArray != null) {
			InformationDocumentByType = InformationDocumentArray;
		}
		console.log("InformationDocumentByType",InformationDocumentByType);
		
		let html = '<div class="portlet light standard_document_portlet">';
		
			html += '<div class="portlet-body form form-body-collapse">';
				html+='<div class="row">';
            		html+= '<div class="col-md-12">';
            			html+= '<div class="center-block error-choose-file" style="width:800px;display:none"></div>';
            		html += '</div>';
            	html += '</div>';
            	
            	if(InformationDocumentByType != null){
            		if(InformationDocumentByType.length == 0){
    					html += '<div class="row">';
    						html += '<div class="col-md-12 section-fileinput">';
    							html += '<input class="standard-document-input file" type="file" name="standard-document-input[]" multiple data-preview-file-type="files/*" accept=".doc, .docx, .pdf">';
    							html += '<input class="form-control optionPreview hide" type="text" name="optionPreview">';
    						html += '</div>';
    					html += '</div>';
    				}else{
    					let initialPreviewData = [];
    					let initialPreviewConfigData = [];
    					$(InformationDocumentByType).each(function(i,v){
    						
    						let typeFile = (v.informationDocumentLocation.split('/')[v.informationDocumentLocation.split('/').length-1]).trim().split('.')[(v.informationDocumentLocation.split('/')[v.informationDocumentLocation.split('/').length-1]).trim().split('.').length-1];
    						let fileName = (v.informationDocumentLocation.split('/')[v.informationDocumentLocation.split('/').length-1]).trim()
    						initialPreviewData.push(v.informationDocumentLocation);
    						initialPreviewConfigData.push({
    							type : typeFile.trim(),
    							caption : fileName,
    							filename : fileName,
    							downloadUrl : v.informationDocumentLocation.trim(),
    							key : v.informationDocumentId.trim(),
    							url : contextPath+'/api/information/delete_information_document',
    						})
    					});
    					console.log("initialPreviewData",initialPreviewData);
    					console.log("initialPreviewConfigData",initialPreviewConfigData);
    					let optionFileInput = {
    						initialPreview : initialPreviewData,
    						initialPreviewConfig : initialPreviewConfigData
    					} 
    					console.log("optionFileInput",optionFileInput);
    					
    					html += '<div class="row">';
    						html += '<div class="col-md-12 section-fileinput">';
    							html += '<form method="POST" enctype="multipart/form-data" accept-charset="UTF-8">';
	    							html += '<input class="standard-document-input file" type="file" name="standard-document-input[]" multiple data-preview-file-type="files/*" accept=".doc, .docx, .pdf">';
	    							html += '<input class="form-control optionPreview hide" type="text" name="optionPreview" value="'+URI.encode(JSON.stringify(optionFileInput))+'">';
	    						html+='</form>';
    						html += '</div>';
    					html += '</div>';
    					
    				}
            	}else{
            		html += '<div class="row">';
						html += '<div class="col-md-12 section-fileinput">';
							html += '<input class="standard-document-input file" type="file" name="standard-document-input[]" multiple data-preview-file-type="files/*" accept=".doc, .docx, .pdf">';
							html += '<input class="form-control optionPreview hide" type="text" name="optionPreview">';
						html += '</div>';
					html += '</div>';
            	}	
				
			html += '</div>';
			
		html+= '</div>';
		return html;
}


function getInformationDetail(){
	let url = contextPath+'/api/information/get_information';
	ajaxProcess.submit(url, 'POST', objInformationDetail, '#information_form', (data)=>{
		if(data.result == true){
			let objJSON = JSON.parse(data.message);
			console.log("objJSON",objJSON);
			$("#information_title").val(objJSON.informationTitle);
			$("#description").val(objJSON.description);
			$("#select_group_type").val(objJSON.groupType).trigger('change');
			if(objJSON.groupType == "1"){
				product = objJSON.productTypeId.productTypeId;
			}else if(objJSON.groupType == "2"){
				console.log("objJSON.supplierIdList",objJSON.supplierIdList);
				$(objJSON.supplierIdList).each(function(i,v){	
					supList.push(v);
				});
			}

			if(objJSON.informationType != null && objJSON.informationType != "1"){
				if(objInformationDetail.acceptStatus == "" || objInformationDetail.acceptStatus == null){
					generateTableInformationDetail(objJSON.informationDetailId);
				}else{
					getInformationDetailBySupplier();
				}
				isBrowse = false;
				textShowInput = "ไม่มีไฟล์";
			}
		}
		loadResourcePicture();
		loadResourceDocument();
		genProductType();
	});
}

function getInformationJson(){
	let informationId = $('#information_id').val();
	let informationTitle = $("#information_title").val();
	let description = $("#description").val();	
	let groupType = $("#select_group_type").val();	
	let supplierIdList = "" , productType = 0;
	
	if(groupType == "1"){
		productType = $("#select_product_type").val();
		supplierIdList = [];
	}else if(groupType == "2"){
		supplierIdList = $('[name="select_supplier"]').val();
	}
	let productTypeId = {
			productTypeId : productType
	};
	let objInformation = {
		informationId : (informationId=='')?0:informationId,
		informationTitle : informationTitle,
		description : description,
		groupType : groupType,
		productTypeId : productTypeId,
		supplierIdList : supplierIdList
	};
	return objInformation;
}

function generateTableInformationDetail(informationDetailId){
	if(informationDetailId != null && informationDetailId != ''){
		if(informationDetailId.length == 0){
			$('#table_information_detail > tbody').append($('<tr>').append($('<td colspan="4" style="text-align : center;">').append($("<span>ไม่พบการส่งข้อมูลข้อกำหนด/ข่าวสาร</span>"))));
		}else{
			let htmlTB = "";
			$(informationDetailId).each(function(i,v){	
				console.log("vvvvvvvvv",v);
				htmlTB += "<tr>";
					htmlTB += "<td style='text-align: center;'>";
						htmlTB += (i+1);
					htmlTB += "</td>";
					
					htmlTB += "<td style='text-align: center;'>";
						//htmlTB += v.auditResultId.evalPlanId.title;
						htmlTB += v.supplierId.supplierCompany;
					htmlTB += "</td>";
					
					htmlTB += "<td style='text-align: center;'>";
						htmlTB += v.acceptDate;
					htmlTB += "</td>";

					htmlTB += "<td style='text-align: center;'>";
						htmlTB += v.acceptBy.username;
					htmlTB += "</td>";
				htmlTB += "</tr>";
			});
			$('#table_information_detail > tbody').append(htmlTB);
			
		}
	}else{
		$('#table_information_detail > tbody').append($('<tr>').append($('<td colspan="4" style="text-align : center;">').append($("<span>ไม่พบการส่งข้อมูลข้อกำหนด/ข่าวสาร</span>"))));
	}
	$('.row_table_information_detail').show();
	disabledInput();
	
}

function getInformationDetailBySupplier(){
	if(objInformationDetail.acceptStatus == "N"){
		$('.btn_save').hide();
		$('.btn_accept').show();
	}else{
		$("#accept_by").val(objInformationDetail.acceptBy);
		$("#accept_date").val(objInformationDetail.acceptDate);
		$('.row_accept').show();
	}
	disabledInput();
}

function disabledInput(){
	$('#information_title').prop('readonly', true);
	$('#select_group_type').prop('disabled', true);
	$('#select_product_type').prop('disabled', true);
	$('#supplier_button').prop('disabled', true);
	$('#description').prop('readonly', true);
	$(".textInput").hide();
	
	$('#btn_save_information').hide();
	$('#btn_send_information').hide();
	iconFile = '{download}';
}


$('#btn_send_information').on('click',function(){
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการแจ้งข้อมูลข่าวสารนี้ให้ซัพพลายเออร์ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if (isTrue){
			let objInformation = getInformationJson();
			let url = contextPath + "/api/information/send_information";
			ajaxProcess.submit(url, 'POST', objInformation, '#portlet-detail-information', (data)=>{
				if(data.result){
					customMessageDialog('success', 'การบันทึกข้อมูล', 'บันทึกข้อมูลสำเร็จ', null);
					let json = {
						informationId: $("#information_id").val(),
						typeDetail : "admin"
					};
					$('#informationDetail').val(URI.encode(JSON.stringify(json)));
					$('#informationDetail').closest('form').submit();
				}
			});
		}
	});
});


$('#btn_save_information').on('click',function(){
		let objInformation = getInformationJson();
		if(objInformation.informationId == ''){
			ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการบันทึกข้อมูลนี้ใช่หรือไม่?");	
			ConfirmModal.confirmResult(function(isTrue){
				if (isTrue){
					let url = contextPath + "/api/information/insert_information";
					ajaxProcess.submit(url, 'POST', objInformation, '#portlet-detail-information', (data)=>{
						if(data.result){
							console.log("json",JSON.parse(data.message));
							let result = JSON.parse(data.message);
//							$('#information_id').val(result.informationId);
							customMessageDialog('success', 'การบันทึกข้อมูล', 'บันทึกข้อมูลสำเร็จ', null);
							let json = {
								informationId: result.informationId,
								typeDetail : "admin"
							}
							$('#informationDetail').val(URI.encode(JSON.stringify(json)));
							$('#informationDetail').closest('form').submit();
						}
					});
				}
			});
			
		}else{
			ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการบันทึกการแก้ข้อมูลนี้ใช่หรือไม่?");	
			ConfirmModal.confirmResult(function(isTrue){
				if (isTrue){
					let url = contextPath + "/api/information/update_information";
					ajaxProcess.submit(url, 'POST', objInformation, '#portlet-detail-information', (data)=>{
						if(data.result){
							let json = {
								informationId: $("#information_id").val(),
								typeDetail : "admin"
							};
							$('#informationDetail').val(URI.encode(JSON.stringify(json)));
							$('#informationDetail').closest('form').submit();
						}
					});
				}
			});
		}
		
//	}	

});

$('#btn_accept_information').on('click',function(){
	ConfirmModal.setupModal("warning","ยืนยัน", "การกดรับทราบให้ทางผู้ส่งมอบตรวจสอบรายละเอียดข้อมูลที่แจ้งก่อนกดยืนยันรับทราบ ( สามารถกดรับทราบได้ เพียง user เดียว/บริษัท)");	
	ConfirmModal.confirmResult(function(isTrue){
		if (isTrue){
			let objInformation = getInformationJson();
			let url = contextPath + "/api/information/accept_information";
			ajaxProcess.submit(url, 'POST', objInformation, '#portlet-detail-information', (data)=>{
				if(data.result){
					customMessageDialog('success', 'การบันทึกข้อมูล', 'บันทึกข้อมูลสำเร็จ', null);
					let objJSON = JSON.parse(data.message);
					console.log("objJSON",objJSON);
					let json = {
						informationId: $("#information_id").val(),
						typeDetail : "supplier",
						acceptStatus : objJSON.informationDetailId[0].acceptStatus,
						acceptDate : objJSON.informationDetailId[0].acceptDate,
						acceptBy : objJSON.informationDetailId[0].acceptBy.username
					};
					$('#informationDetail').val(URI.encode(JSON.stringify(json)));
					$('#informationDetail').closest('form').submit();
				}
			});
		}
	});
});


$('#btn_submit_supplier').on('click',function(){
	let supplier_select = 0;
	console.log($('[name="select_supplier"]').val());
	if($('[name="select_supplier"]').val() != null){
		supplier_select =  $('[name="select_supplier"]').val().length;
	}
	let length = $('select[name="select_supplier"]').length;
	if(supplier_select > 0){
		let text = "เลือก "+ supplier_select +" รายการ";
		$("#select_supplier_text").val(text);
	}
	$('#dialog_select_supplier').modal('toggle');
});

