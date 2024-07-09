var standardDocumentObj = null;
var supplierStandardDocumentObj = null;
var fileList = [];

$(function(){
	chSession() 
	loadResource();
	initialFileInput();
});

function loadResource(){
	loadStandardDocumentData();
	loadSupplierStandardDocumentData();
	generateStandardDocument();
	
	dateTime.currentDateTime('yyyy/MM/dd HH:mm:ss',(currentTimeDate)=>{
		$('.input-group.datepicker-dialog').datepicker({
			language: "th",
			format: 'dd/mm/yyyy',
			orientation: "bottom auto",
			autoclose: true,
			startDate: currentTimeDate,
			container : '#dialog_assign_expire_date_supplier_standard_document'
		}).on('hide', function(e) {
	        if($('#expire_date_standard_document_dialog').val() == ""){
	        	$('#expire_date_standard_document_dialog').val($('#expire_date_standard_document_temp').val());
	        }
		});		
	});
	
}

function chSession() {
	let url = contextPath +"/api/user/get_permission_menu";
	$.ajax({
		url : url,
		type : 'POST',
		contentType: "application/json",
		data : "supplier_standard_document.jsp",
		success : function(data){
			if(!data.result){
				window.location = "/auditsupplier/home.jsp";
			}
		}
	});
}

function loadStandardDocumentData(){
	let url = contextPath+'/api/standard_document/standard_documet_list';
	ajaxProcess.submit(url, 'GET', null, '#section-standard-document', (data)=>{
		if(data.result){
			standardDocumentObj = JSON.parse(data.message)
		}		
	},{async : false});
	
}

function loadSupplierStandardDocumentData(){

	let url = contextPath+'/api/supplier_standard_document/supplier_standard_document_list/{}'
	ajaxProcess.submit(url, 'GET', null, '#section-standard-document', (data)=>{
		if(data.result){
			supplierStandardDocumentObj = JSON.parse(data.message)
		}
	},{async : false});	
	
}

function generateStandardDocument(standardDocumentArray, supplierStandardDocumentArray){
	if(standardDocumentArray == null){
		standardDocumentArray = standardDocumentObj;
	}
	
	if(supplierStandardDocumentArray == null){
		supplierStandardDocumentArray = supplierStandardDocumentObj;
	}
	
	if(standardDocumentArray.length > 0){
		let numOfStandardInRow = 2;
		let numOfRow = Math.ceil(standardDocumentArray.length/numOfStandardInRow);
		let wideColumn = Math.ceil(12/numOfStandardInRow);
		for(i = 0; i< numOfRow; i++ ){
			let htmlPortlet = '<div class="row form-group">';
			for(j = numOfStandardInRow*i; (j < numOfStandardInRow*(i+1)) && (j < standardDocumentArray.length); j++){
				htmlPortlet += '<div class="col-md-'+wideColumn+'">';
					htmlPortlet += genrateSectionStandardDocument(standardDocumentArray[j], supplierStandardDocumentArray);
				htmlPortlet += '</div>';
			}
			htmlPortlet += '</div>';
			
			$('#section-standard-document').find('.body-standard-document-list').append(htmlPortlet);
		}
	}
}




function genrateSectionStandardDocument(standardDocumentO, supplierStandardDocumentArray){
	console.log("standardDocumentO",standardDocumentO);
	console.log("supplierStandardDocumentArray",supplierStandardDocumentArray);
	if(standardDocumentO == null || typeof standardDocumentO === "undefined"){
		return false;
	}else{
		let supplierStandardDocumentByType = null;
		if(supplierStandardDocumentArray != null && supplierStandardDocumentArray.length > 0){
			supplierStandardDocumentByType = $.grep(supplierStandardDocumentArray, function(v){
				return v.standardDocumentId.standardDocumentId == standardDocumentO.standardDocumentId && v.standardDocumentId.standardDocumentName == standardDocumentO.standardDocumentName;
			});
		}
		let html = '<div class="portlet light standard_document_portlet">';
		
			html += '<div class="portlet-title">';
				html += '<div class="caption font-green-sharp">';
					html += '<span class="caption-subject bold uppercase standard_document_name">'+standardDocumentO.standardDocumentName+'</span>';
					html += '<input class="form-control standard_document_id hide" value="'+standardDocumentO.standardDocumentId+'" />';
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
            	
            	if(supplierStandardDocumentByType != null){
            		if(supplierStandardDocumentByType.length == 0){
    					html += '<div class="row">';
    						html += '<div class="col-md-12 section-fileinput">';
    							html += '<input class="standard-document-input file" type="file" name="standard-document-input[]" multiple data-preview-file-type="files/*" accept=".doc, .pdf, .docx">';
								html += '<input class="form-control optionPreview hide" type="text" name="optionPreview">';
								html += '<label style="color:red">หลังจากอัพโหลดแล้ว กรุณาระบุวันที่หมดอายุให้ตรงตามวันที่จริง</label>' ;
    						html += '</div>';
    					html += '</div>';
    				}else{
    					let initialPreviewData = [];
    					let initialPreviewConfigData = [];
    					
    					
    					$(supplierStandardDocumentByType).each(function(i,v){
    						let typeFile = (v.supplierStandardDocumentLocation.split('/')[v.supplierStandardDocumentLocation.split('/').length-1]).trim().split('.')[(v.supplierStandardDocumentLocation.split('/')[v.supplierStandardDocumentLocation.split('/').length-1]).trim().split('.').length-1];
    						let fileName = (v.supplierStandardDocumentLocation.split('/')[v.supplierStandardDocumentLocation.split('/').length-1]).trim()
    						initialPreviewData.push(v.supplierStandardDocumentLocation);
    						initialPreviewConfigData.push({
    							type : typeFile.trim(),
    							caption : fileName + "<br/><br/>วันหมดอายุ:"+v.supplierStandardDocumentExpireDate.split(" ")[0]+ "<p style='display:inline; color:red; font-size:20px'><b>" +checkExpdate(v.supplierStandardDocumentExpireDate.split(" ")[0]) +"<b/><p/>",
    							filename : fileName,
    							downloadUrl : v.supplierStandardDocumentLocation.trim(),
    							key : v.supplierStandardDocumentId.trim(),
    							url : contextPath+'/api/supplier_standard_document/delete_supplier_standard_document',
    							frameAttr : {
    								expireDate : v.supplierStandardDocumentExpireDate,
    								supplierStandardDocumentId : v.supplierStandardDocumentId.trim(),
    								createBy : v.createUser.username,
    								createDate : v.createDate,
    								updateBy : v.updateUser.username,
    								updateDate : v.updateDate
    							}
    						})
    					});
    					
    					
    					let optionFileInput = {
    						initialPreview : initialPreviewData,
    						initialPreviewConfig : initialPreviewConfigData
    					} 
    					
    					html += '<div class="row">';
    						html += '<div class="col-md-12 section-fileinput">';
    							html += '<form method="POST" enctype="multipart/form-data" accept-charset="UTF-8">';
	    							html += '<input class="standard-document-input file" type="file" name="standard-document-input[]" multiple data-preview-file-type="files/*" accept=".doc, .pdf, .docx">';
	    							html += '<input class="form-control optionPreview hide" type="text" name="optionPreview" value="'+URI.encode(JSON.stringify(optionFileInput))+'">';
	    							html += '<label style="color:red">หลังจากอัพโหลดแล้ว กรุณาระบุวันที่หมดอายุให้ตรงตามวันที่จริง</label>' ;
	    						html+='</form>';
    						html += '</div>';
    					html += '</div>';
    					
    				}
            	}else{
            		html += '<div class="row">';
						html += '<div class="col-md-12 section-fileinput">';
							html += '<input class="standard-document-input file" type="file" name="standard-document-input[]" multiple data-preview-file-type="files/*" accept=".doc, .pdf, .docx">';
							html += '<input class="form-control optionPreview hide" type="text" name="optionPreview">';
							html += '<label style="color:red">หลังจากอัพโหลดแล้ว กรุณาระบุวันที่หมดอายุให้ตรงตามวันที่จริง</label>' ;
						html += '</div>';
					html += '</div>';
            	}	
			html += '</div>';
			
		html+= '</div>';
		return html;
	}
}


$(document).on('click', '.btn-file-expire-date', function(){
	let fileObjectRecord = $(this).closest('tr');
	let fileExpireDateText = "", supplierStandardDocumentId = 0;
	let createBy = "", createDate = "", updateBy = "", updateDate = "";
    $('#update_file').hide();
    
	
	if(fileObjectRecord.is('[supplierStandardDocumentId]')){
		supplierStandardDocumentId = fileObjectRecord.attr('supplierStandardDocumentId');
	}
	if(fileObjectRecord.is('[expireDate]')){
		fileExpireDateText = fileObjectRecord.attr('expireDate');
		fileExpireDateText = fileExpireDateText.split(" ")[0];
	}
	if(fileObjectRecord.is('[createBy]') && fileObjectRecord.is('[createDate]')){
		createBy = fileObjectRecord.attr('createBy');
		createDate = fileObjectRecord.attr('createDate');
        $('#create_file_by').text(createBy);
        $('#create_file_date').text(createDate);
	}
	if(fileObjectRecord.is('[updateBy]') && fileObjectRecord.is('[updateDate]')){
		updateBy = fileObjectRecord.attr('updateBy');
		updateDate = fileObjectRecord.attr('updateDate');
		if(updateBy != createBy && updateDate != createDate){
	        $('#update_file_by').text(updateBy);
	        $('#update_file_date').text(updateDate);
	        $('#update_file').show();
		}
	}
	
	$('#expire_date_standard_document_temp').val(fileExpireDateText);
	$('#fileRecordDetailObject').text(fileObjectRecord);
	$('#standard_document_id').val(supplierStandardDocumentId);
	
	
	$('.input-group .datepicker-dialog').datepicker('setStartDate',new Date());
	$('.input-group .datepicker-dialog').datepicker('clearDates');
	$('.input-group .datepicker-dialog').datepicker('setDate',fileExpireDateText);
	$('#expire_date_standard_document_dialog').val(fileExpireDateText);

	$('#dialog_assign_expire_date_supplier_standard_document').modal('show');

});

$('#save_expire_date_standard_document').on('click', function(){
	let supplierStandardDocumentId = $('#standard_document_id').val();
	let fileExpireDateText = $('#expire_date_standard_document_dialog').val();
	
	if(fileExpireDateText == "" || fileExpireDateText == null){
		customMessageDialog("error", "เกิดข้อผิดพลาด", "กรุณาใส่วันหมดอายุของเอกสารรับรองมาตรฐาน", null);
		return false;
	}
	
	if(supplierStandardDocumentId != 0){
		let url = contextPath+'/api/supplier_standard_document/update_expire_date_supplier_standard_document'
		let supplierStandardDocumentModelRequest = {
			supplierStandardDocumentId : supplierStandardDocumentId,
			supplierStandardDocumentExpireDate : fileExpireDateText+" 00:00:00"
		};
		ajaxProcess.submit(url, 'POST', supplierStandardDocumentModelRequest, '#dialog_assign_expire_date_supplier_standard_document', (data)=>{
			if(data.result){
				$('#section-standard-document').find('.portlet-body').empty();
				loadResource();
				initialFileInput();		
				$('#dialog_assign_expire_date_supplier_standard_document').modal('hide');
			}
		});	
		
	}
	
});


function checkExpdate(ExpDate){
	var ExpDay = ExpDate.charAt(0)+""+ExpDate.charAt(1);
	var Expmonth = ExpDate.charAt(3)+""+ExpDate.charAt(4);
	var Expyear = ExpDate.charAt(6)+""+ExpDate.charAt(7)+ExpDate.charAt(8)+ExpDate.charAt(9);
	var ExpForm = Expmonth +"/" +ExpDay +"/" +Expyear
	
	var date = new Date();
	
	var MM = date.getMonth()+1;
	var DD = date.getDate();
	var YYYY = date.getFullYear();
	
	var dateForm = MM+"/"+DD+"/"+YYYY;
	
	var date1 = new Date(dateForm); 
	var date2 = new Date(ExpForm);
	
	var Difference_In_Time = date2.getTime() - date1.getTime();
	var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);
	
	if(Difference_In_Days <= 30){
		return "!!!"
	}else{
		return ""
	}
	
}


function initialFileInput(){
	$(".standard-document-input").each(function(i,v){
		let optionPreviewData = $(this).closest('.section-fileinput').find('.optionPreview').val().trim();
		
		let optionFileInput = {
			theme: "explorer",
		    language: "th",
		    showClose: false,
		    showCaption: false,
		    showBrowse: true,
			showUpload: true,
			showRemove: false,
			autoOrientImage :true,
			maxFileSize: 3000,
		    minFileCount: 1,
		    maxFileCount: 100,
		    overwriteInitial: false,
		    previewFileIcon: '<i class="fa fa-file-o"></i>',
		    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',	
		    defaultPreviewContent: '<img src="'+contextPath+'/assets/images/picture-attachment.png" alt="Your Standard Document"><h6 class="text-muted">เลือกไฟล์</h6>',
		    layoutTemplates: {main2: '{preview} {remove} {browse} {upload} ',
		    				actions: '{drag}\n' +
							            '<div class="file-actions">\n' +
							            '    <div class="file-footer-buttons">\n' +
							            '        {upload} {download} {delete} {other} \n' +
							            '		 <button type="button" class="btn btn-sm btn-kv btn-default btn-outline-secondary btn-file-expire-date" title="วันหมดอายุของไฟล์" style="display:none;"><i class="glyphicon glyphicon-calendar"></i></button>'+
							            '    </div>\n' +
							            '</div>'},
		    allowedFileExtensions: ["doc", "pdf", "docx"],
		    initialPreviewAsData: true,
		    preferIconicPreview: true, // this will force thumbnails to display icons for following file extensions
		    purifyHtml: true, // this by default purifies HTML data for preview	    
		    uploadUrl: contextPath+'/api/upload/file',
	        uploadAsync: false,
	        fileActionSettings: {
	        	showUpload: false,
	        	showDownload : true,
	        	showZoom : false,
	        	showDrag : false,
	        	showOther : true
	        },
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
		        'jpg': '<i class="fas fa-file-image text-danger"></i>', 
		        'gif': '<i class="fas fa-file-image text-muted"></i>', 
		        'png': '<i class="fas fa-file-image text-primary"></i>'    
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
		        }
		    }
		};
		
		
		if(optionPreviewData != "" && optionPreviewData != null){
			optionFileInput = $.extend(optionFileInput,JSON.parse(URI.decode(optionPreviewData)))
		}
		$(this).fileinput(optionFileInput).on('filebatchpreupload', function(event, data){	
						
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
			var form = data.form, files = data.files, extra = data.extra,
	        response = data.response, reader = data.reader;
			if(response.result){
				let standardDocId = $(this).closest('.portlet').find('.portlet-title').find('.standard_document_id').val().trim();
				let listFileSend = (form.getAll("file_elements"));
				let supplierStandardDocumentList = [];
				$(listFileSend).each(function(i,v){
					supplierStandardDocumentList.push({
						supplierStandardDocumentLocation : URI.decode(v.name),
						standardDocumentId : {
							standardDocumentId : standardDocId
						},
						enable : 'Y'
					});
				});
				let url = contextPath+'/api/supplier_standard_document/insert_supplier_standard_document_list';
				ajaxProcess.submit(url, 'POST', supplierStandardDocumentList, '#section-standard-document', (data)=>{
					
					$('#section-standard-document').find('.portlet-body').empty();
					loadResource();
					initialFileInput();		
					
				});
			}
						
		}).on('filebeforedelete', function() {
			return new Promise(function(resolve, reject) {
				ConfirmModal.setupModal("warning","ยืนยัน", "ยืนยันการลบเอกสารรับรองมาตรฐานนี้ใช่หรือไม่?");	
				ConfirmModal.confirmResult(function(isTrue){
					if(isTrue){
						resolve();
					}
				});
			});
		});
		
		$(this).closest('.section-fileinput').find('.btn-file-expire-date').each(function(i,v){
			$(this).show();
		});
		
	});
	
	
	
	
	
}