var objFinalAudit = null;
var evidencePicture = [];
var evidenceDocument = [];
var userGroupId = null;
$(function(){
	let url = contextPath+'/api/user/get_session_user';
	ajaxProcess.submit(url, 'GET', null, '', (data)=>{
		let objData = JSON.parse(data.message);
		userGroupId = objData.userGroupId.userGroupId;
	},{async:false});
	
	dateTime.currentDateTime('yyyy/MM/dd HH:mm:ss',(currentTimeDate)=>{
		$('.input-group.datepicker-dialog').datepicker({
			language: "th",
			format: 'dd/mm/yyyy',
			orientation: "bottom auto",
			autoclose: true,
			startDate: currentTimeDate,
			container : '#dialog_solve_detail_car'
		});		
	});
	
	
	let checklistPlanId = $('#pass_param').val().trim();
	if(checklistPlanId != "" && checklistPlanId != null){
		loadDetailFinalAuditResult(checklistPlanId);
	}else{
		window.location.href = 'final_auditresult.jsp';
	}	
				
	$("#evidence_supplier").fileinput({ 
		theme: 'explorer',
		language: "th",
	    minFileCount: 1,
	    maxFileCount: 10,
	    //maxFileSize: 300,
	    showClose: false,
	    showCaption: false,
	    showBrowse: true,
		showUpload: true,
		showRemove: false,
		browseOnZoneClick: true,
		removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
		elErrorContainer: '#error-choose-file',
		msgErrorClass: 'alert alert-block alert-danger',
	    defaultPreviewContent: '<img src="'+contextPath+'/assets/images/picture-attachment.png" alt="Your Logo"><h6 class="text-muted">คลิกเพื่อเลือกรูปภาพ</h6>',
	    layoutTemplates: {main2: '{preview} {remove} {browse} {upload}'},
	    allowedFileExtensions: ["jpg", "jpeg", "png"],
	    overwriteInitial: false,	   
	    initialPreviewAsData: true, // identify if you are sending preview data only and not the raw markup
	    initialPreviewFileType: 'image', // image is the default and can be overridden in config below	   
	    purifyHtml: true, // this by default purifies HTML data for preview	    
	    uploadUrl: contextPath+'/api/upload/picture',
        uploadAsync: false,
        fileActionSettings: {
        	showUpload: false,
        }
	}).on('filebatchpreupload', function(event, data) {	
		
		$.each(data.files, function(i,value){
			try{
				let uniqueIdPicture = generate.randomByDate();
				let filesName = value.name;
				let filesType = value.type.split('/')[1];
				if(filesType == "jpeg" || filesType == "jpg"){
					filesName = filesName.split('.'+filesType)[0];
					filesName = filesName.split('.jpg')[0];
				}
				let filesNameNew = (filesName.split('.'+filesType)[0]).concat('_').concat(uniqueIdPicture).concat('.').concat(filesType);
				data.form.append('picture_elements', value, filesNameNew);
				evidencePicture.push({filename : filesNameNew, uniqueId : uniqueIdPicture})
			}catch (e) {
				
			}
		});
		
	}).on('filebatchselected', function(event, files) {		
		if(files.length > 0){				
			
			$('#input-id').fileinput('addToStack', files); 
			/*evidencePicture = $('#evidence_supplier').fileinput('getFileStack');
			console.log(evidencePicture)*/
		}			
	}).on('filesuccessremove', function(event, id, index) {
		//evidencePicture = [];
	}).on('filebatchuploadsuccess', function(event, data) {		
	    var form = data.form, files = data.files, extra = data.extra,
	        response = data.response, reader = data.reader;
	    console.log('File batch upload success', form, files, extra, response, reader);
	});
	
	$("#evidence_document_supplier").fileinput({ 
		theme: "explorer",
	    language: "th",
	    uploadUrl: "/file-upload-batch/2",
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
	    layoutTemplates: {main2: '{preview} {remove} {browse} {upload}'},
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
        	showDrag : false
        },
	    previewFileIconSettings: { // configure your icon file extensions
	        'doc': '<i class="fa fa-file-word-o text-primary"></i>',
	        'pdf': '<i class="fa fa-file-pdf-o text-danger"></i>',    
	    },
	    previewFileExtSettings: { // configure the logic for determining icon file extensions
	        'doc': function(ext) {
	            return ext.match(/(doc|docx)$/i);
	        },
	        'pdf': function(ext) {
	            return ext.match(/(pdf)$/i);
	        }
	    }
	}).on('filebatchpreupload', function(event, data){	
			
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
			evidenceDocument.push({filename : filenameNewFormat, uniqueId : uniqueIdFile})
		});
		
	});
	
	$('#evidence_document_preview_supplier').fileinput();
	
});



$('#btn_edit_car').on('click',function(){
	/*evidencePicture = $('#evidence_supplier').fileinput('getFileStack');
	console.log(evidencePicture)*/
	let listEvidenceObj = [];
	if(evidencePicture.length > 0){
				
		$(evidencePicture).each(function(i,v){
			listEvidenceObj.push({
				auditResultId : {
					checklistPlanId : {checklistPlanId : $('#pass_param').val().trim()},
					auditorId : {userId : '0'},
					evalPlanId : {evalPlanId : (JSON.parse(URI.decode($('#detail_car_json').val().trim()))).auditResultId.evalPlanId.evalPlanId}
				},
				evidenceTypeId : {evidenceTypeId : "1"},
				data : v.filename,
				actionType : 'S',
				enable : 'Y'
			});
		});
		
		
	}
	
	if(evidenceDocument.length > 0){
	
		$(evidenceDocument).each(function(i,v){
			listEvidenceObj.push({
				auditResultId : {
					checklistPlanId : {checklistPlanId : $('#pass_param').val().trim()},
					auditorId : {userId : '0'},
					evalPlanId : {evalPlanId : (JSON.parse(URI.decode($('#detail_car_json').val().trim()))).auditResultId.evalPlanId.evalPlanId}
				},
				evidenceTypeId : {evidenceTypeId : "5"},
				data : v.filename,
				actionType : 'S',
				enable : 'Y'
			});
		});
		
	}
	
	if(listEvidenceObj != null && listEvidenceObj.length > 0){
	
		let url = contextPath+"/api/evidence/insert_evidence";
		ajaxProcess.submit(url, 'POST', listEvidenceObj, '#dialog_upload_solve_detail_car', (data)=>{
			if(data.result){
				setTimeout(()=>{
					//url = contextPath+'/api/evidence/evidence_solve_car/'+URI.encode(JSON.stringify({auditResultId : JSON.parse($('#detail_car_json').val()).auditResultId}));
					url = contextPath+'/api/evidence/evidence_solve_car/';
					let objRequestEvidence = {auditResultId : JSON.parse($('#detail_car_json').val()).auditResultId};
					ajaxProcess.submit(url, 'POST', objRequestEvidence, '', (data)=>{
						if(data.result){
							loadCar(JSON.parse($('#detail_car_json').val()).carId.carId);
							generateEvidenceSolveCar(JSON.parse(data.message), JSON.parse($('#detail_car_json').val().trim()))
						}
					});
					$('#dialog_upload_solve_detail_car').modal('hide');
				},50);				
			}
		});
		
	}else{
		customMessageDialog('warning',"การแจ้งเตือน", "กรุณาเพิ่มและอัพโหลดรูปภาพหรือเอกสารก่อนทำการส่งหลักฐาน",null);
	}
	
	
	
});

$('#save_due_date_solve_car').on('click', function(){
	if($('#due_date_dialog').val().trim() == ""){
		customMessageDialog('warning',"การแจ้งเตือน", "กรุณาเลือกวันที่ Due Date ก่อนทำการบันทึก",null);
		return false;
	}else{
		let detailCarJsonObj = JSON.parse($('#detail_car_json').val());
		let objRequestCarDetail = {
			auditResultId : detailCarJsonObj.auditResultId,
			carId : detailCarJsonObj.carId,
			dueDate : $('#due_date_dialog').val().trim()
		}
		let url = contextPath+"/api/car_detail/update_due_date";
		ajaxProcess.submit(url, 'POST', objRequestCarDetail, '#dialog_solve_detail_car', (data)=>{
			if(data.result){
				$('#due_date_text').text($('#due_date_dialog').val().trim());
				$('#save_due_date_solve_car').slideUp();
				$('#due_date_input_section').slideUp();
				$('#due_date_text').slideDown('slow');
				$('#solve_evidence_portlet_section').slideDown('slow');	
				let detailCarJsonObj = JSON.parse($('#detail_car_json').val().trim());
				detailCarJsonObj.dueDate = $('#due_date_dialog').val().trim()+" 00:00:00";
				$('#detail_car_json').val(JSON.stringify(detailCarJsonObj));
				
				url = contextPath+'/api/evidence/evidence_solve_car/';
				let objRequestEvidence = {auditResultId : JSON.parse($('#detail_car_json').val()).auditResultId};
				ajaxProcess.submit(url, 'POST', objRequestEvidence, '', (data)=>{
					if(data.result){
						loadCar(JSON.parse($('#detail_car_json').val()).carId.carId);
						generateEvidenceSolveCar(JSON.parse(data.message), JSON.parse($('#detail_car_json').val().trim()))
					}
				});
				
			}
		});
	}
	
});

$('#save_txt_solve_evidence').on('click', function(){
	if($('#solve_evidence_text').val().trim() == ""){
		customMessageDialog('warning',"การแจ้งเตือน", "กรุณาใส่ข้อความก่อนทำการบันทึก",null);
		return false;
	}
	let listEvidenceObj = [{		
		auditResultId : {
			checklistPlanId : {checklistPlanId : $('#pass_param').val().trim()},
			auditorId : {userId : '0'},
			evalPlanId : {evalPlanId : (JSON.parse(URI.decode($('#detail_car_json').val().trim()))).auditResultId.evalPlanId.evalPlanId}
		},
		evidenceTypeId : {evidenceTypeId : "2"},
		data : $('#solve_evidence_text').val().trim(),
		actionType : 'S',
		enable : 'Y',
		evidenceId : $('#evidenceTextTypeId').val().trim()
	}];
	
	if($('#evidenceTextTypeId').val().trim() == "" || $('#evidenceTextTypeId').val().trim() == null){
		ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการยืนยันการบันทึกข้อมูลนี้ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){
				let url = contextPath+"/api/evidence/insert_evidence";
				ajaxProcess.submit(url, 'POST', listEvidenceObj, '#dialog_solve_detail_car', (data)=>{
					if(data.result){
						setTimeout(()=>{
							//url = contextPath+'/api/evidence/evidence_solve_car/'+URI.encode(JSON.stringify({auditResultId : JSON.parse($('#detail_car_json').val()).auditResultId}));
							url = contextPath+'/api/evidence/evidence_solve_car/';
							let objRequestEvidence = {auditResultId : JSON.parse($('#detail_car_json').val()).auditResultId};
							ajaxProcess.submit(url, 'POST', objRequestEvidence, '', (data)=>{
								if(data.result){
									loadCar(JSON.parse($('#detail_car_json').val()).carId.carId);
									generateEvidenceSolveCar(JSON.parse(data.message), JSON.parse($('#detail_car_json').val().trim()))
								}
							});
							$('#dialog_upload_solve_detail_car').modal('hide');
						},50);				
					}
				});
			}
		});
	}else{
		ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการยืนยันการบันทึกการเปลี่ยนแปลงนี้ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){
				let url = contextPath+"/api/evidence/update_evidence";
				ajaxProcess.submit(url, 'POST', listEvidenceObj, '#dialog_solve_detail_car', (data)=>{
					if(data.result){
						setTimeout(()=>{
							//url = contextPath+'/api/evidence/evidence_solve_car/'+URI.encode(JSON.stringify({auditResultId : JSON.parse($('#detail_car_json').val()).auditResultId}));
							url = contextPath+'/api/evidence/evidence_solve_car/';
							let objRequestEvidence = {auditResultId : JSON.parse($('#detail_car_json').val()).auditResultId};
							ajaxProcess.submit(url, 'POST', objRequestEvidence, '', (data)=>{
								if(data.result){
									loadCar(JSON.parse($('#detail_car_json').val()).carId.carId);
									generateEvidenceSolveCar(JSON.parse(data.message), JSON.parse($('#detail_car_json').val().trim()))
								}
							});
						},50);				
					}
				});
			}
		});
	}
});

$('#dialog_upload_solve_detail_car').on('hidden.bs.modal', function () {
	evidencePicture = [];
	evidenceDocument = [];
	$('#evidence_supplier').fileinput('reset');
	$('#evidence_supplier').fileinput('clear');
	$('#evidence_document_supplier').fileinput('reset');
	$('#evidence_document_supplier').fileinput('clear');
});

$('#dialog_solve_detail_car').on('hidden.bs.modal', function () {
	$('#evidence_document_preview_supplier').fileinput('reset');
	$('#evidence_document_preview_supplier').fileinput('clear');
	$('#evidence_document_preview_supplier').fileinput('destroy');
	$('.list-document-section').slideUp();
});




$('#btn_save_grade').on('click', function(){
	let grade = $('#grade_selector').val();
	if(grade == null || grade == ""){
		customMessageDialog('warning',"การแจ้งเตือน", "กรุณาเลือกเกรด",null);
	}else{
		objFinalAudit["grade"] = grade.trim();
		
		ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการยืนยันการเลือกเกรดนี้ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){
				let url = contextPath+'/api/final_audit_result/update_grade';
				ajaxProcess.submit(url, 'POST', objFinalAudit, null, (data)=>{
					if(data.result){
						managePass(objFinalAudit.pass, objFinalAudit.grade);
						manageAuditType(objFinalAudit.auditType, objFinalAudit.grade);
						manageGrade(objFinalAudit.grade);
						manageState(objFinalAudit);
					}
				});
			}
		});
	}
	
});


$('#btn_send_result_to_supplier').on('click', function(){
	
	let auditType = "";
	$('.audit_type').each(function(){
		if($(this).prop('checked')){
			auditType = $(this).val();
			return false;
		}
	});
	if(auditType == ""){
		customMessageDialog('warning',"การแจ้งเตือน", "กรุณาเลือกประเภทการเข้าตรวจ",null);
	}else{
		
		ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการยืนยันการส่งสรุปผลการเข้าตรวจนี้ให้ซัพพลายเออร์ ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){
				objFinalAudit["auditType"] = auditType.trim();
				let url = contextPath+'/api/final_audit_result/update_final_audit_result';
				ajaxProcess.submit(url, 'POST', objFinalAudit, null, (data) =>{
					loadDetailFinalAuditResult($('#pass_param').val().trim());
				});
			}
		});
		
	}
});


$('#btn_accept_auditresult').on('click', function(){
	ConfirmModal.setupModal("warning","ยืนยัน", "ท่านได้ทำการตรวจสอบและรับทราบสรุปผลการเข้าตรวจนี้ ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			let url = contextPath+'/api/final_audit_result/update_final_audit_result';
			ajaxProcess.submit(url, 'POST', objFinalAudit, null, (data) =>{
				loadDetailFinalAuditResult($('#pass_param').val().trim());
			});
		}
	});
});

$('#btn_approve_car').on('click', function(){
	ConfirmModal.setupModal("warning","ยืนยัน", "ท่านได้ทำการตรวจสอบและอนุมัติสรุปผลการเข้าตรวจนี้ ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			$('#print_btn').addClass("col-md-offset-3");
			$('#bottom_btn').removeClass("col-md-offset-3");
			let url = contextPath+'/api/final_audit_result/update_final_audit_result';
			ajaxProcess.submit(url, 'POST', objFinalAudit, null, (data) =>{
				loadDetailFinalAuditResult($('#pass_param').val().trim());
			});
		}
	});
});

$('#btn_edit_finalresult').on('click', function(){
	
	ConfirmModal.setupModal("warning","ยืนยัน", "ดำเนินการต่อหรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			let editfield = $('#edit_field').val();
			objFinalAudit["pass"] = "E"
			objFinalAudit["reason"] = editfield.trim();
			let url = contextPath+'/api/final_audit_result/update_final_audit_result';
			ajaxProcess.submit(url, 'POST', objFinalAudit, null, (data) =>{
				loadDetailFinalAuditResult($('#pass_param').val().trim());
			});
		}
	});
});


$('#btn_confirm_car').on('click', function(){
	let resultApproveFinalAuditresult = "";
	$('.approve_result').each(function(){
		if($(this).prop('checked')){
			resultApproveFinalAuditresult = $(this).val();
			return false;
		}
	});
	
	if(resultApproveFinalAuditresult == ""){
		customMessageDialog('warning',"การแจ้งเตือน", "กรุณาเลือกผลการตรวจประเมิน",null);
	}else{
		objFinalAudit['pass'] = resultApproveFinalAuditresult;		
		ConfirmModal.setupModal("warning","ยืนยัน", "ยืนยันการบันทึกสรุปผลการเข้าตรวจนี้ ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){
				let completeSolveCar = true;
				$('#table_car_result').find('.detail_car_json').each(function(){
					let carDetailObj = (JSON.parse(URI.decode($(this).val().trim())));
					if(carDetailObj.enable == "Y" && (carDetailObj.completed == "" || carDetailObj.completed == "N")){
						completeSolveCar = false;
						return false;
					}
				});
				console.log(objFinalAudit);
				if(resultApproveFinalAuditresult == 'N'){
					let url = contextPath+'/api/final_audit_result/update_final_audit_result';
					ajaxProcess.submit(url, 'POST', objFinalAudit, null, (data) =>{
						loadDetailFinalAuditResult($('#pass_param').val().trim());
					});
				}else{
					if(completeSolveCar){
						let url = contextPath+'/api/final_audit_result/update_final_audit_result';
						ajaxProcess.submit(url, 'POST', objFinalAudit, null, (data) =>{
							loadDetailFinalAuditResult($('#pass_param').val().trim());
						});
					}else{
						customMessageDialog('warning',"การแจ้งเตือน", "ไม่สามารถบันทึกข้อมูลได้ เนื่องจากประเด็นใบคาร์ทั้งหมดยังไม่ผ่าน",null);
					}
				}
				
			}
		});
	}
});

$('#btn_print_final_audit_result').on('click', function(){
	
	let finalAuditResultRequestModel = {
		checklistPlanNo : $('#checklistPlanNo').text().trim()
	}
	
	ajaxProcess.submit(contextPath+"/api/cryptography/encrypt_rsa", 'POST', {dataEncrypt : JSON.stringify(finalAuditResultRequestModel)}, null, (data)=>{
		if(data.result){
			let encodeResult = JSON.parse(data.message).encodeResult;
			let url = contextPath+"/api/final_audit_result/print_final_audit_result";
			$('<form action="'+ url +'" method="POST"><input type="text" id="checklistPlanNo" name="checklistPlanNo" value="'+encodeResult+'"></form>').appendTo('body').submit().remove();
			//window.location = contextPath+"/api/final_audit_result/print_final_audit_result/"+URI.encode(encodeResult);
			/*$.ajax({
		        url: contextPath+"/api/final_audit_result/print_final_audit_result/"+URI.encode(encodeResult),
		        method: 'GET',
		        dataType : 'binary',
		        xhrFields: {
		            responseType: 'blob'
		        },
		        success: function (data, textStatus, jqXHR) {
		        	console.log(textStatus)
		        	console.log(jqXHR)
		        	let disposition = jqXHR.getResponseHeader('content-disposition');
		            let matches = /"([^"]*)"/.exec(disposition);
		            let filename = disposition.split('attachment;filename=')[1];
		            var blob = new Blob([jqXHR.responseText], { type: 'application/pdf' });
		            var link = document.createElement('a');
		            link.href = window.URL.createObjectURL(blob);
		            link.download = filename;

		            document.body.appendChild(link);

		            link.click();

		            document.body.removeChild(link);
		            var a = document.createElement('a');
		            var url = window.URL.createObjectURL(data);
		            a.href = url;
		            a.download = 'myfile.pdf';
		            document.body.append(a);
		            a.click();
		            a.remove();
		            window.URL.revokeObjectURL(url);
		        }
		    });*/
		}
	});
	//var rdx = new RandExp(/^(?:[0-9A-Z]{6,8}-){4}[0-9A-Z]{6,8}$/);
	//console.log(rdx.gen())
	
	//window.location.href = url;
});

$('#btn_log_final_audit_result').on('click', function(){
	loadLogHistory();	
	$('#dialog_final_audit_log').modal('show');
});

function loadDetailFinalAuditResult(checklistPlanId){
	let url = contextPath+"/api/final_audit_result/final_audit_result_detail/"+JSON.stringify({checklistPlanId : {checklistPlanId : checklistPlanId}});
	ajaxProcess.submit(url, 'GET', null, null, (data)=>{
		
		if(data.result){
			console.log("TESTTESTTEST");
			console.log(data);
			objFinalAudit = JSON.parse(data.message)[0];
			$("#signature").attr('src','data:image/png;base64,'+objFinalAudit.signatureOfSupplier);	
			$('#date_val').text(objFinalAudit.planDate.split(" ")[0].trim());
			$('#accept_day').text(objFinalAudit.checklistPlanId.noOfCarAcceptDay.trim());
			$('#checklistPlanNo').text(objFinalAudit.checklistPlanNo.trim());
			if(objFinalAudit.completeDate == ""){
				$('#dateApproval').val(objFinalAudit.completeDate);
			}else{
				$('#dateApproval').val(objFinalAudit.completeDate.split(" ")[0].trim());
			}
			
			
			manageAuditType(objFinalAudit.auditType, objFinalAudit.grade);
			manageGrade(objFinalAudit.grade);
			manageAuditor(objFinalAudit.auditor);
			manageApprove(objFinalAudit.approvalName);
			managePass(objFinalAudit.pass, objFinalAudit.grade);
			if(objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId == "4" || objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId == "5"){
				generateCarDetailFromText(objFinalAudit.carDetailData);
				if(userGroupId == "2"){
					manageLinkToCar(objFinalAudit.carId.carId)
				}				
			}else{
				loadCar(objFinalAudit.carId.carId);
			}
			
			manageState(objFinalAudit);
			
			
			let supplierData = JSON.parse(objFinalAudit.supplierData);
			$('#supplier_name').text(supplierData.supplierCompany.trim());
			
			let supplierProductAddressMappingData = JSON.parse(objFinalAudit.supplierProductAddressMappingData);
			$('#location_name').text(supplierProductAddressMappingData.locationName+" "+supplierProductAddressMappingData.address+" "+supplierProductAddressMappingData.subDistrict+" "+supplierProductAddressMappingData.district+" "+supplierProductAddressMappingData.province+" "+supplierProductAddressMappingData.postcode);
			
			let concludeData = JSON.parse(objFinalAudit.conclude);
			$('#critical_val').val(concludeData.Critical);
			$('#major_val').val(concludeData.Major);
			$('#minor_val').val(concludeData.Minor);
			$('#observe_val').val(concludeData.Observe);
			
		}
	});
}


function manageAuditType(auditType, grade){
	
	if(auditType != ""){
		if(auditType == "1"){
			$('#audit_round').prop('checked',true);
		}else if(auditType == "2"){
			$('#audit_new_supplier').prop('checked',true);
		}else if(auditType == "3"){
			$('#audit_observe').prop('checked',true);
		}else{
			if(grade != null && grade != ""){
				if(userGroupId == "2"){
					$('.section-audit-type').find('.form-sm-checkboxes').each(function(){
						if($(this).hasClass('readonly')){
							$(this).removeClass('readonly');
						}
					});
				}
			}
		}
	}else{
		if(grade != null && grade != ""){
			if(userGroupId == "2"){
				$('.section-audit-type').find('.form-sm-checkboxes').each(function(){
					if($(this).hasClass('readonly')){
						$(this).removeClass('readonly');
					}
				});
			}			
		}
		
	}
	
}


function managePass(pass, grade){
	
	if(pass != "" && pass != null){
		if(pass == "Y"){
			$('#pass').prop('checked',true);
		}else{
			$('#not_pass').prop('checked',true);
		}
	}else{
		if(grade != null && grade != ""){
			
			if(userGroupId == "2"){
				console.log(userGroupId == "2")
				$('.section-pass').find('.form-sm-checkboxes').each(function(){
					if($(this).hasClass('readonly')){
						$(this).removeClass('readonly');
					}
				});
			}			
		}
	}
}

function manageGrade(grade){
	if(grade != "" && grade != null){
		$('#section-section-grade').slideUp();
		$('#section-show-grade').slideDown();		
		$('#grade').val(grade.trim());
	}else{
		
		if(userGroupId == "2"){
			customMessageDialog('warning',"การแจ้งเตือน", "กรุณาเลือกเกรด และกดปุ่มยืนยันการเลือกเกรด เนื่องจากระบบไม่สามารถคำนวณเกรดได้",null);
			let url = contextPath+'/api/grade/grade_list';
			ajaxProcess.submit(url, 'GET', null, '', (data)=>{
				
				let dataSource = [];
				if(data.result){
					let dataObj = JSON.parse(data.message);
					
					$(dataObj).each(function(i,v){
						dataSource.push({
			    			id : v.gradeId,
			    			text  : v.gradeId
			    		});
					});
				}
				
				$("#grade_selector").select2({
		    		placeholder: "",
		    		theme: "bootstrap",
		    		data: dataSource
		    	});
		    	$('#grade_selector').val(null).trigger('change');
		    	$('#section-show-grade').slideUp();
		    	$('#section-section-grade').slideDown();
		    	
			});
		}else{
			$('#section-section-grade').slideUp();
			$('#section-show-grade').slideDown();
			$('#grade').val(grade.trim());
		}
		
	}
}

function manageAuditor(auditor){
	if(auditor !="" && auditor != null){
		let auditorList = JSON.parse(auditor);
		if(auditorList.length == 0){
			$('#auditor_list').text("-");
		}else if(auditorList.length == 1){
			$('#auditor_list').text(JSON.parse(auditorList[0]).fullname);
		}else{
			let auditList = "";
			$(auditorList).each(function(i,v){
				auditList += JSON.parse(v).fullname;
				if(i != auditorList.length-1){
					auditList +=", ";
				}
			});
			$('#auditor_list').text(auditList);
		}
	}else{
		$('#auditor_list').text("-");
	}
}

function manageApprove(aprovalName){
	
	if(aprovalName != "" && aprovalName != null){
		let aprovalList = JSON.parse(aprovalName);
		$('#approval').text(aprovalList.fullname);
	}else{
		$('#approval').text("-");
	}
}


function manageState(objFinalAudit){
	$('.label_status').empty();
	$('#final_auditresult_status_id').val(objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId);
	$('<span class="label" style="background-color:'+objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusColor+';font-size:12px;"> '+objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusName+' </span>').appendTo($('.label_status'));	
	$('.btn_process').hide();
	let flagShowBtnProcess = false;
	$('.section-audit-type').find('.form-sm-checkboxes').each(function(){
		$(this).addClass('readonly');
	});
	$('.section-pass').find('.form-sm-checkboxes').each(function(){
		$(this).addClass('readonly');
	});
	let url = contextPath+'/api/user/get_session_user';
	ajaxProcess.submit(url, 'GET', null, '', (data)=>{
		let userModelObj = JSON.parse(data.message);
		if(userModelObj == null){
			if(objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId == "2"){
				//$('#btn_accept_auditresult').slideDown();
			}
		}else{
			if(userModelObj.userGroupId.userGroupId == "3" || userModelObj.userGroupId.userGroupId == "4" ){
				$("#bottom_btn").addClass("col-md-offset-3");
				if(objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId == "2"){
					$('#btn_accept_auditresult').slideDown();
					flagShowBtnProcess = true;
				}else if(objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId == "3"){
					$('#btn_solve_car').slideDown();
					$('#save_txt_solve_evidence').slideDown();
					flagShowBtnProcess = true;
				}
			}else if(userModelObj.userGroupId.userGroupId == "1" || userModelObj.userGroupId.userGroupId == "2"){
				
				if(objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId == "1"){
					if(objFinalAudit.grade == "" || objFinalAudit.grade == null){
						if(userGroupId == "2"){
							$('#btn_confirm_car').parent().removeClass('col-md-offset-4').addClass('col-md-offset-5');
							$('#section-show-grade').slideUp();
							$('#section-section-grade').slideDown('slow');					
							$('#btn_save_grade').slideDown();		
							flagShowBtnProcess = true;
						}
					}else{
						if(userGroupId == "2"){
							$('.section-audit-type').find('.form-sm-checkboxes').each(function(){
								if($(this).hasClass('readonly')){
									$(this).removeClass('readonly');
								}						
							});
							$('#btn_save_grade').slideUp();
							$('#btn_send_result_to_supplier').parent().removeClass('col-md-offset-5').addClass('col-md-offset-4');
							$('#btn_send_result_to_supplier').slideDown();
							flagShowBtnProcess = true;
						}						
					}
				}else if(objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId == "3"){
					if(userGroupId == 2){
						$('.section-pass').find('.form-sm-checkboxes').each(function(){
							if($(this).hasClass('readonly')){
								$(this).removeClass('readonly');
							}						
						});
						$('#btn_confirm_car').parent().removeClass('col-md-offset-5').addClass('col-md-offset-4');
						$('#btn_confirm_car').slideDown();
						flagShowBtnProcess = true;
					}
					
				}
			}else if(userModelObj.userGroupId.userGroupId == "6" ){
				if(objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId == "6"){
					$("#bottom_btn").addClass("col-md-offset-3");
					$('#btn_approve_car').slideDown();
					$('#btn_edit_final').slideDown();
					flagShowBtnProcess = true;
				}
			}
			
			if(!flagShowBtnProcess){
				$('#btn_print_final_audit_result').parent().prev().removeClass('col-md-offset-4');
				$('#btn_print_final_audit_result').parent().prev().removeClass('col-md-offset-5');				
				$('#btn_print_final_audit_result').parent().addClass('col-md-offset-4');
				
			}
			$('#btn_print_final_audit_result').slideDown();
			$('#btn_log_final_audit_result').slideDown();
			
		}
		
	},{async : false});
}

$('.approve_result').on('click',function(){
	if($(this).prop('checked')){
		$('.approve_result').not($(this)).prop('checked',false);
	}	
});

$(document).on('click', '.btn_car_detail', function(){
	let carDetailObj = (JSON.parse(URI.decode($(this).parent().find('.detail_car_json').val())));
	$('#detail-car-string-json').val($(this).parent().find('.detail_car_json').val());
	$('#signature_in_car_detail').attr('src', 'data:image/png;base64,'+objFinalAudit.signatureOfSupplier);
	$('#car_no').text(carDetailObj.carId.carNo.trim())
	$('#date_appoint').text(objFinalAudit.planDate.split(" ")[0].trim());
	$('#auditor').text($('#auditor_list').text())
	$('#questionOfCar').text(carDetailObj.auditResultId.evalPlanId.detail.trim());
	$('#problemOfCar').text(carDetailObj.detail);
	$('#evidenceText').empty();
	$('#carousel-evidence-section').empty();
	let evidenceImage = "";
	
	let evidencePictureType = $.grep(carDetailObj.evidenceId, function(v) {
		return v.evidenceTypeId.evidenceTypeId == "1";
	});
	if(evidencePictureType.length > 0){
		
		let htmlIndicator='', htmlInner='';
		$(evidencePictureType).each(function(i,v){
			//$('<div class="item"><img class="img-responsive" src="'+v.data+'"><div class="carousel-caption"></div></div>').appendTo('.carousel-inner');
			//$('<li data-target="#carousel-evidence" data-slide-to="'+i+'"></li>').appendTo('.carousel-indicators');
			htmlIndicator += '<li data-target="#carousel-evidence" data-slide-to="'+i+'"></li>';
			htmlInner += '<div class="item"><img class="img-responsive" src="'+v.data+'"><div class="carousel-caption"></div></div>';
		});
		
		let htmlCarousel = '<div class="carousel slide" data-ride="carousel" id="carousel-evidence">';
			htmlCarousel += '<ol class="carousel-indicators">'+htmlIndicator+'</ol>';
			htmlCarousel += '<div class="carousel-inner">'+htmlInner+'</div>';
			htmlCarousel += '<a class="left carousel-control" href="#carousel-evidence" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>';
			htmlCarousel += '<a class="right carousel-control" href="#carousel-evidence" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>';
		htmlCarousel += '</div>';
		$('#carousel-evidence-section').append(htmlCarousel);
		$('.item').first().addClass('active');
		$('.carousel-indicators > li').first().addClass('active');
		$('#carousel-evidence').carousel();
		
		
		//<div class="item active" style="display:none;" ></div>
		//<li data-target="#carousel-evidence" data-slide-to="0" class="active" style="display:none;"></li>
		$('#lable_description_evidence').show();
		/*if($('.carousel-inner').is(':empty')){
			$('#carousel-evidence').hide();
			$('#lable_description_evidence').hide();
		}else{
			$('#carousel-evidence').show();
			$('#lable_description_evidence').show();
		}*/
	}else{
		$('#lable_description_evidence').hide();
	}
	
	let evidenceTextType = $.grep(carDetailObj.evidenceId, function(v) {
		return v.evidenceTypeId.evidenceTypeId == "2";
	});
	if(evidenceTextType.length > 0){
		$(evidenceTextType).each(function(i,v){			
			$('<div class="m-list-timeline m-list-timeline--skin-light"><div class="m-list-timeline__items"><div class="row"><div class="col-md-12"><div class="m-list-timeline__item"><span class="m-list-timeline__badge m-list-timeline__badge--success"></span><span class="m-list-timeline__text" style="font-size : 16px;">'+v.data+'</span></div></div></div></div>').appendTo('#evidenceText');
		});
	}
	
	
	
	$('#dialog_detail_car').modal('show');
	
});


$('#btn_solve_car').on('click', function(){
	$('#dialog_upload_solve_detail_car').modal('show');
});


$(document).on('click','.btn_evidence_solve_car',function(){
	let evidenceSolveCarObj = JSON.parse(URI.decode($(this).parent().find('.detail_evidence_solve').val()));
	let carDetailObj = (JSON.parse(URI.decode($(this).closest('tr').find('.detail_car_json').val())));
	generateEvidenceSolveCar(evidenceSolveCarObj, carDetailObj);
});

$(document).on('click', '.deleteEvidencePicture', function(){
	let evidenceId = $(this).closest('.row').find('.evidenceIdElement').val().trim();
	ConfirmModal.setupModal("warning","ยืนยัน", "ยืนยันการลบรูปภาพนี้ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			
			let url = contextPath+'/api/evidence/delete_evidence';
			let evidenceObj = {evidenceId : evidenceId};			
						
			ajaxProcess.submit(url, 'DELETE', evidenceObj, '#dialog_solve_detail_car', (data)=>{
				if(data.result){						
					//url = contextPath+'/api/evidence/evidence_solve_car/'+URI.encode(JSON.stringify({auditResultId : JSON.parse($('#detail_car_json').val()).auditResultId}));
					url = contextPath+'/api/evidence/evidence_solve_car/';
					let objRequestEvidence = {auditResultId : JSON.parse($('#detail_car_json').val()).auditResultId};
					ajaxProcess.submit(url, 'POST', objRequestEvidence, '', (data)=>{
						if(data.result){
							loadCar(JSON.parse($('#detail_car_json').val()).carId.carId);
							generateEvidenceSolveCar(JSON.parse(data.message), JSON.parse($('#detail_car_json').val().trim()))
						}
					});					
				}
			});
		}
	});

});

function generateEvidenceSolveCar(evidenceObj, carDetailObj){
	
	$('.list-picture-section').empty();
	$('#detail_car_json').val(JSON.stringify(carDetailObj));
	$('#solve_detail_car_json').val(JSON.stringify(evidenceObj));
	$('#questionOfCarDetail').html(carDetailObj.auditResultId.evalPlanId.detail.trim());
	$('#problemOfCarDetail').text(carDetailObj.detail);
	let state = "", reason = "";
	if(carDetailObj.completed != ""){
		if(carDetailObj.completed == "Y"){
			state = "ผ่าน";
			$('#btn_solve_car').slideUp();
			$('#save_txt_solve_evidence').slideUp();
			$('#solve_evidence_text').attr('readonly',true);
		}else{
			state = "ไม่ผ่าน";
			$('#btn_solve_car').slideDown();
			$('#save_txt_solve_evidence').slideDown();
			$('#solve_evidence_text').attr('readonly',false);
			reason = carDetailObj.remark;
		}
	}else{
		if(carDetailObj.enable == "Y"){
			state = "ผู้เข้าตรวจรอยืนยันการแก้ไขปัญหา กรุณาส่งหลักฐานการแก้ไข";
			$('#btn_solve_car').slideDown();
			$('#save_txt_solve_evidence').slideDown();
			$('#solve_evidence_text').attr('readonly',false);
		}else{
			state = "ยกเลิกปัญหา";
			$('#btn_solve_car').slideUp();
			$('#save_txt_solve_evidence').slideUp();
			$('#solve_evidence_text').attr('readonly',true);
			reason = carDetailObj.remark;
		}
	}
	
	$('#statusCarDetail').text(state);
	if(reason == ""){
		$('.reason-section').hide();
	}else{
		$('#reason').text(reason);
		$('.reason-section').slideDown();
	}
	
	if($('#final_auditresult_status_id').val().trim() == "4" || $('#final_auditresult_status_id').val().trim() == "5" ){
		$('#due_date_input_section').hide();
		if(carDetailObj.dueDate != null && carDetailObj.dueDate != ""){
			$('#due_date_text').text(carDetailObj.dueDate.trim().split(" ")[0].trim());
		}else{
			$('#due_date_text').text("-");
		}		
		$('#due_date_text').show();
		$('#save_due_date_solve_car').hide();
		$('#solve_evidence_portlet_section').show();
	}else{
		if(carDetailObj.dueDate != null && carDetailObj.dueDate != ""){
			$('#due_date_input_section').hide();
			$('#due_date_text').text(carDetailObj.dueDate.trim().split(" ")[0].trim());
			$('#due_date_text').show();
			$('#save_due_date_solve_car').hide();
			$('#solve_evidence_portlet_section').show();
		}else{
			$('#due_date_input_section').show();
			$('#due_date_text').hide();
			$('#save_due_date_solve_car').show();
			$('#solve_evidence_portlet_section').hide();
		}
	}
	
	
	
	
	let evidencePictureType = $.grep(evidenceObj, function(v) {
		return v.evidenceTypeId.evidenceTypeId == "1";
	});
	
	let evidenceDocumentType = $.grep(evidenceObj, function(v) {
		return v.evidenceTypeId.evidenceTypeId == "5";
	});
	
	if(evidenceDocumentType.length == 0 && evidencePictureType.length == 0){
		let html = '<div class="row form-group">';
			html += '<div class="col-md-7 col-md-offset-2">';
				html += '<H4>ไม่พบข้อมูลของหลักฐานการแก้ไข กรุณาส่งหลักฐานการแก้ไข</H4>';
			html += '</div>';
		html += '</div>';
		$('.list-picture-section').append(html);
	}else{
		if(evidencePictureType.length > 0){
			let numOfPictureInRow = 4;
			let numOfRow = Math.ceil(evidencePictureType.length/numOfPictureInRow);
			for(i = 0; i< numOfRow; i++ ){
				let htmlPicture = '<div class="row form-group">';
				for(j = numOfPictureInRow*i; (j < numOfPictureInRow*(i+1)) && (j < evidencePictureType.length); j++){
					
					htmlPicture += '<div class="col-md-3">';
						htmlPicture += '<div class="row form-group">';
						htmlPicture += '<div class="col-md-12">';
							htmlPicture += '<a class="elem" href="'+evidencePictureType[j].data+'" title="'+evidencePictureType[j].data.split('/')[evidencePictureType[j].data.split('/').length-1]+'" data-lcl-txt="" data-lcl-author="" data-lcl-thumb="'+evidencePictureType[j].data+'">';
							htmlPicture += '<img src="'+evidencePictureType[j].data+'" class="img-thumbnail">';
							htmlPicture += '</a>';
						htmlPicture += '</div>';
						htmlPicture += '</div>';
						
						htmlPicture += '<div class="row ">';
						htmlPicture += '<div class="col-md-8">';
							/*htmlPicture += '<input class="form-control" value="'+(evidencePictureType[j].data.split('/')[evidencePictureType[j].data.split('/').length-1]).split('.')[0]+'">' */
							htmlPicture += '<p style="overflow-wrap: break-word;">'+(evidencePictureType[j].data.split('/')[evidencePictureType[j].data.split('/').length-1]).split('.')[0]+'</p>';
							htmlPicture += '<input class="form-control hide evidenceIdElement" value="'+evidencePictureType[j].evidenceId.trim()+'">';
						htmlPicture += '</div>';
						if(objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId == "3" && carDetailObj.completed != "Y"){
							htmlPicture += '<div class="col-md-2">';
								htmlPicture += '<button class="btn btn-danger deleteEvidencePicture"><i class="fa fa-trash"></i>  </button>';
							htmlPicture += '</div>';
						}					
						htmlPicture += '</div>';
						
					htmlPicture += '</div>';
				}
				htmlPicture += '</div>';
				$('.list-picture-section').append(htmlPicture);
			}
			
			lc_lightbox('.elem', {
				wrap_class: 'lcl_fade_oc',
				gallery : true,	
				thumb_attr: 'data-lcl-thumb', 
				fullscreen: true, 
				skin: 'dark',
				radius: 0,
				padding	: 0,
				border_w: 0,
			});
			
		}
		
		if(evidenceDocumentType.length > 0){
		
			let initialPreviewData = [];
    		let initialPreviewConfigData = [];
    		
    		
    		$('#evidence_document_preview_supplier').fileinput('reset');
			$('#evidence_document_preview_supplier').fileinput('clear');
    		$('#evidence_document_preview_supplier').fileinput('destroy');
    		
			$(evidenceDocumentType).each(function(i,v){
				let typeFile = (v.data.split('/')[v.data.split('/').length-1]).trim().split('.')[(v.data.split('/')[v.data.split('/').length-1]).trim().split('.').length-1];
    			let fileName = (v.data.split('/')[v.data.split('/').length-1]).trim();
    			
    			initialPreviewData.push(v.data);
				initialPreviewConfigData.push({
					type : typeFile.trim(),
					caption : fileName,
					downloadUrl : v.data.trim(),
					key : v.evidenceId.trim(),
					url : contextPath+'/api/evidence/delete_evidence_document'
				})
				
			});
			
			
			let optionFileInput = {
				theme: "explorer",
			    language: "th",
			    showClose: false,
			    showCaption: false,
			    showBrowse: false,
				showUpload: false,
				showRemove: false,
				autoOrientImage :true,
				maxFileSize: 3000,
			    minFileCount: 1,
			    maxFileCount: 100,
			    overwriteInitial: false,
			    previewFileIcon: '<i class="fa fa-file-o"></i>',
			    initialPreview : initialPreviewData,
			    initialPreviewConfig : initialPreviewConfigData,
			    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',	
			    defaultPreviewContent: '<h5 class="text-muted">ไม่พบไฟล์เอกสารการแก้ไขปัญหา</h5>',
			    layoutTemplates: {main2: '{preview}'},
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
		        	showDrag : false	
		        },
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
			
			$('#evidence_document_preview_supplier').fileinput(optionFileInput).on('filebeforedelete', function() {
				return new Promise(function(resolve, reject) {
					ConfirmModal.setupModal("warning","ยืนยัน", "ยืนยันการลบเอกสารการแก้ไขปัญหานี้ใช่หรือไม่?");	
					ConfirmModal.confirmResult(function(isTrue){
						if(isTrue){
							resolve();
							let filesCount = $('#evidence_document_preview_supplier').fileinput('getPreview').content.length;
							
							if(filesCount <= 1){
								$('#evidence_document_preview_supplier').fileinput('reset');
								$('#evidence_document_preview_supplier').fileinput('clear');
								$('.list-document-section').slideUp();
								
								if(evidencePictureType.length == 0){
									let html = '<div class="row form-group">';
										html += '<div class="col-md-7 col-md-offset-2">';
											html += '<H4>ไม่พบข้อมูลของหลักฐานการแก้ไข กรุณาส่งหลักฐานการแก้ไข</H4>';
										html += '</div>';
									html += '</div>';
									$('.list-picture-section').append(html);
								}
								
							}
						}
					});
				});
			});
			
			$('.list-document-section').slideDown();
			
		}
	}	
	
	
	let evidenceTextType = $.grep(evidenceObj, function(v) {
		return v.evidenceTypeId.evidenceTypeId == "2";
	});
	
	if(evidenceTextType.length > 0){
		$(evidenceTextType).each(function(i, v){
			$('#solve_evidence_text').val(v.data);
			$('#evidenceTextTypeId').val(v.evidenceId);
		});
	}else{
		$('#solve_evidence_text').val('');
		$('#evidenceTextTypeId').val('');
	}
	
	$('#dialog_solve_detail_car').modal('show');
}

function loadCar(carId){
	let url = contextPath+'/api/car_detail/car_detail_list/'+JSON.stringify({
		carId : {carId : carId}
	});
	console.log("TESTTEST00");
	console.log(url);
	ajaxProcess.submit(url, 'GET', null, '#table_car_result', (data)=>{
		if(data.result){
			$('#table_car_result > tbody').empty();
			let carResult = JSON.parse(data.message);
			console.log("carResult",carResult)
			if(carResult != null && carResult != ''){
				carResult = $.grep(carResult, function(v){
					return v.enable == 'Y';
				});
				
				if(carResult.length == 0){
					$('#table_car_result > tbody').append($('<tr>').append($('<td colspan="6" style="text-align : center;">').append($("<span>ไม่พบใบคาร์</span>"))));
				}else{

					console.log("carResult",carResult);
					if(userGroupId == "2"){
						manageLinkToCar(carId);
					}
					let htmlTB = "";
					$(carResult).each(function(i,v){
						htmlTB += "<tr>";
							htmlTB += "<td>";
								htmlTB += v.detail;
							htmlTB += "</td>";
							
							htmlTB += "<td style='text-align: center;'>";
								//htmlTB += v.auditResultId.evalPlanId.title;
								htmlTB += v.auditResultId.evalPlanId.detail;
							htmlTB += "</td>";
							
							htmlTB += "<td style='text-align: center;'>";
								htmlTB += v.auditResultId.answerDetail;
							htmlTB += "</td>";
							
							htmlTB += "<td>";
							let correctiveaction="";
							ajaxProcess.submit(contextPath+'/api/evidence/evidence_solve_car/', 'POST', {auditResultId : v.auditResultId}, '', (data)=>{	
								if(data.result){
									if(data.message != "" && data.message != null){
										let evidenceList = JSON.parse(data.message);
										$(evidenceList).each(function(i,val){
											if(val.evidenceTypeId.evidenceTypeId == "2"){
												htmlTB += val.data;
											}
										});
									}
								}
							},{async : false});	
							htmlTB += "</td>";
							
							htmlTB += "<td style='text-align: center;'>";
								if(v.completed == "Y"){
									htmlTB += v.completeDate.split(" ")[0];
								}							
							htmlTB += "</td>";
							
							htmlTB += "<td style='text-align: center;'>";
							if(v.dueDate != "" && v.dueDate != null){
								htmlTB += v.dueDate.split(" ")[0];
							}							
							htmlTB += "</td>";
							
							htmlTB += "<td class='section-detail-car' style='display : none;'>";
								htmlTB += '<div class="btn-group">';
									htmlTB += '<button class="btn btn-xs blue btn_car_detail" type="button" aria-expanded="false" style="font-size:12px;">ดูรายละเอียด</button>';
									htmlTB += '<input class="detail_car_json hide" value="'+URI.encode(JSON.stringify(v))+'" >'
								htmlTB += '</div>';
							htmlTB += "</td>";
							
							//let url = contextPath+'/api/evidence/evidence_solve_car/'+URI.encode(JSON.stringify({auditResultId : v.auditResultId}));
							let url = contextPath+'/api/evidence/evidence_solve_car/';
							let objRequestEvidence = {auditResultId : v.auditResultId};
							ajaxProcess.submit(url, 'POST', objRequestEvidence, '', (data)=>{		
								console.log("true");
								htmlTB += "<td class='section-evidence-solve-car' style='display : none;'>";
								htmlTB += '<div class="btn-group">';
								if(data.result){
									let dataEvidenceSolve = JSON.parse(data.message);				
									console.log(dataEvidenceSolve);
									if(dataEvidenceSolve.length > 0){
										
										if(v.completeDate != "" && v.completeDate != null){
											htmlTB += '<button class="btn btn-xs green-meadow btn_evidence_solve_car" type="button" aria-expanded="false" style="font-size:12px;">การตรวจสอบเสร็จสิ้น</button>';
										}else{
											if(v.completed == "N" && v.enable == "Y"){
												htmlTB += '<button class="btn btn-xs yellow-casablanca btn_evidence_solve_car" type="button" aria-expanded="false" style="font-size:12px;">รอดำเนินการแก้ไข</button>';
											}else{
												htmlTB += '<button class="btn btn-xs yellow-casablanca btn_evidence_solve_car" type="button" aria-expanded="false" style="font-size:12px;">รอตรวจสอบ</button>';
											}											
										}										
										htmlTB += '<input class="detail_evidence_solve hide" value="'+URI.encode(JSON.stringify(dataEvidenceSolve))+'" >'
									}else{
										htmlTB += '<button class="btn btn-xs blue btn_evidence_solve_car" type="button" aria-expanded="false" style="font-size:12px;">การแก้ไข</button>';
										htmlTB += '<input class="detail_evidence_solve hide" value="'+URI.encode(JSON.stringify(dataEvidenceSolve))+'" >'
									}
								}else{
									htmlTB += '<button class="btn btn-xs blue btn_evidence_solve_car" type="button" aria-expanded="false" style="font-size:12px;" disabled>การแก้ไข</button>';
								}
								htmlTB += '</div>';
								htmlTB += "</td>";
								
							},{async : false});
							
							htmlTB += "<td class='section-edit-problem' style='display : none;'>";
							htmlTB += '<div class="btn-group">';
							if(v.canEditProblem == "Y"){
								htmlTB += '<button class="btn btn-xs green btn_edit_problem" type="button"  style="font-size:12px;"> แก้ไข</button>';
//								htmlTB += '<ul class="dropdown-menu pull-left" role="menu">';
//									htmlTB += '<li><a href="javascript:void(0);" class = "btn_edit_problem">';
										htmlTB += '<input class="detail_edit_problem hide" value="'+URI.encode(JSON.stringify(v))+'" >'
//										htmlTB += '<i class="icon-pencil"></i> แก้ไขข้อมูล </a>';
//									htmlTB += '</li>';
//								htmlTB += '</ul>';
							}else{
								htmlTB += '<button class="btn btn-xs green btn_edit_problem" type="button"  style="font-size:12px;" disabled="true"> แก้ไข</button>';
								htmlTB += '<input class="detail_edit_problem hide" value="'+URI.encode(JSON.stringify(v))+'" >'
							}
							htmlTB += '</div>';					
						htmlTB += "</td>";
						htmlTB += "</tr>";
					});
					$('#table_car_result > tbody').append(htmlTB);
					
					let url = contextPath+'/api/user/get_session_user';
					ajaxProcess.submit(url, 'GET', null, '',(data)=>{
						let userSessionObj = JSON.parse(data.message);
						
						if(userSessionObj.userGroupId.userGroupId == "3" || userSessionObj.userGroupId.userGroupId == "4"){
							$($('#table_car_result').find('.section-detail-car')).each(function(){
								$(this).fadeIn();
							});
							if(objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId === "3" || objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId === "4"){
								$($('#table_car_result').find('.section-evidence-solve-car')).each(function(){
									$(this).fadeIn();
								});
							}
							
						}

						if(userSessionObj.userGroupId.userGroupId == "2" && (objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId != "4" && objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId != "5")){
							$($('#table_car_result').find('.section-edit-problem')).each(function(){
								$(this).fadeIn();
							});
						}
						
					});
				}
			}else{
				$('#table_car_result > tbody').append($('<tr>').append($('<td colspan="5" style="text-align : center;">').append($("<span>ไม่พบใบคาร์</span>"))));
			}
			
			$('#dialog_edit_problem').modal('hide');
					
		}
	});
}

function generateCarDetailFromText(carDetailString){
	if(carDetailString == null || carDetailString == ''){
		$('#table_car_result > tbody').empty();
		$('#table_car_result > tbody').append($('<tr>').append($('<td colspan="5" style="text-align : center;">').append($("<span>ไม่พบใบคาร์</span>"))));
		return false;
	}
	let carDetailObj = JSON.parse(carDetailString);
	
	$('#table_car_result > tbody').empty();
	carDetailObj = $.grep(carDetailObj, function(v){
		return v.enable == 'Y';
	});
	if(carDetailObj.length == 0){
		$('#table_car_result > tbody').append($('<tr>').append($('<td colspan="5" style="text-align : center;">').append($("<span>ไม่พบใบคาร์</span>"))));
	}else{
		let htmlTB = "";
		$(carDetailObj).each(function(i,v){
			
			let evidenceCar = $.grep(v.evidenceId, function(ev){
				return ev.actionType == "A";
			});
			let evidenceSolveCar = $.grep(v.evidenceId, function(ev){
				return ev.actionType == "S";
			});
			htmlTB += "<tr>";
				htmlTB += "<td>";
					htmlTB += v.detail;
				htmlTB += "</td>";
				
				htmlTB += "<td style='text-align: center;'>";
					//htmlTB += v.auditResultId.evalPlanId.title;
					htmlTB += v.auditResultId.evalPlanId.detail;
				htmlTB += "</td>";
				
				htmlTB += "<td style='text-align: center;'>";
					htmlTB += v.auditResultId.answerDetail;
				htmlTB += "</td>";
				
				htmlTB += "<td>";
					$(evidenceSolveCar).each(function(i,val){
						if(val.evidenceTypeId.evidenceTypeId == "2"){
							htmlTB += val.data;
						}
					});
				htmlTB += "</td>";			
				
				htmlTB += "<td style='text-align: center;'>";
					if(v.completed == "Y"){
						htmlTB += v.completeDate.split(" ")[0];
					}							
				htmlTB += "</td>";
				
				htmlTB += "<td style='text-align: center;'>";
					if('dueDate' in v){
						if(v.dueDate != "" && v.dueDate != null){
							htmlTB += v.dueDate.split(" ")[0];
						}
					}
				htmlTB += "</td>";
				
				v['evidenceId'] = evidenceCar;
				htmlTB += "<td class='section-detail-car' style='display : none;'>";
					htmlTB += '<div class="btn-group">';
						htmlTB += '<button class="btn btn-xs blue btn_car_detail" type="button" aria-expanded="false" style="font-size:12px;">ดูรายละเอียด</button>';
						htmlTB += '<input class="detail_car_json hide" value="'+URI.encode(JSON.stringify(v))+'" >'
					htmlTB += '</div>';
				htmlTB += "</td>";
				
				htmlTB += "<td class='section-evidence-solve-car' style='display : none;'>";
					htmlTB += '<div class="btn-group">';
						if(evidenceSolveCar.length > 0){
							if(v.completeDate != "" && v.completeDate != null){
								htmlTB += '<button class="btn btn-xs green-meadow btn_evidence_solve_car" type="button" aria-expanded="false" style="font-size:12px;">การตรวจสอบเสร็จสิ้น</button>';
							}else{
								htmlTB += '<button class="btn btn-xs yellow-casablanca btn_evidence_solve_car" type="button" aria-expanded="false" style="font-size:12px;">รอตรวจสอบ</button>';
							}
							htmlTB += '<input class="detail_evidence_solve hide" value="'+URI.encode(JSON.stringify(evidenceSolveCar))+'" >';
						}else{
							htmlTB += '<button class="btn btn-xs blue btn_evidence_solve_car" type="button" aria-expanded="false" style="font-size:12px;">การแก้ไข</button>';
							htmlTB += '<input class="detail_evidence_solve hide" value="'+URI.encode(JSON.stringify(evidenceSolveCar))+'" >';
						}
					htmlTB += '</div>';
				htmlTB += "</td>";

				htmlTB += "<td class='section-edit-problem' style='display : none;'>";
					htmlTB += '<div class="btn-group">';
						htmlTB += '<button class="btn btn-xs green btn_edit_problem" type="button"  style="font-size:12px;"> แก้ไข</button>';
//						htmlTB += '<button class="btn btn-xs blue dropdown-toggle btn_edit_problem" type="button" data-toggle="dropdown" aria-expanded="false" style="font-size:12px;"> เลือก <i class="fa fa-angle-down"></i></button>';
//						htmlTB += '<ul class="dropdown-menu pull-left" role="menu">';
//							htmlTB += '<li><a href="javascript:void(0);" class = "btn_edit_problem">';
								htmlTB += '<input class="detail_edit_problem hide" value="'+URI.encode(JSON.stringify(v))+'" >'
//								htmlTB += '<i class="icon-pencil"></i> แก้ไขข้อมูล </a>';
//							htmlTB += '</li>';
//						htmlTB += '</ul>';
					htmlTB += '</div>';					
				htmlTB += "</td>";
				
			htmlTB += "</tr>";
		});
		
		$('#table_car_result > tbody').append(htmlTB);
		
		let url = contextPath+'/api/user/get_session_user';
		ajaxProcess.submit(url, 'GET', null, '',(data)=>{
			let userSessionObj = JSON.parse(data.message);
			
			if(userSessionObj.userGroupId.userGroupId == "3" || userSessionObj.userGroupId.userGroupId == "4"){
				$($('#table_car_result').find('.section-detail-car')).each(function(){
					$(this).fadeIn();
				});
				if(objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId === "3" || objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId === "4"){
					$($('#table_car_result').find('.section-evidence-solve-car')).each(function(){
						$(this).fadeIn();
					});
				}
			}
			if(userSessionObj.userGroupId.userGroupId == "2" && (objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId != "4" && objFinalAudit.finalAuditResultStatusId.finalAuditResultStatusId != "5")){
				$($('#table_car_result').find('.section-edit-problem')).each(function(){
					$(this).fadeIn();
				});
			}
		});
		
	}
}

$('.audit_type').on('click',function(){
	if($(this).prop('checked')){
		$('.audit_type').not($(this)).prop('checked',false);
	}	
});

$('#see_car_detail').on('click', function(){
	$('#pass_param_car').parent().submit();
});

function manageLinkToCar(carId){
	
	if(carId != ""){
		let url = contextPath+'/api/car/car_list/'+JSON.stringify({carId : carId});
		ajaxProcess.submit(url, 'GET', null, '', (data)=>{
			if(data.result){
				let car = JSON.parse(data.message);
				if(car.length > 0){
					$('#pass_param_car').val(URI.encode(JSON.stringify(car[0])));	
					$('#see_car_detail').slideDown();
				}				
			}
		});		
	}	
}

$(document).on('click','.editProblem',function(){
//	var data = $('#table_car_result').DataTable().row( $(this).parents('tr') ).data();
//	console.log("data",data)
	var userId = "39";
	$('.password_require').hide();
	loadDataForm(userId);
//	v.auditResultId.evalPlanId.evalPlanId
});

function loadDataForm(userId) {
	$.ajax({
		url : contextPath + "/api/user/user_deatil_by_id/"+JSON.stringify( {userId : userId} ),
		type : 'GET',
		beforeSend : function(){
			BlockUi.blockPage();
		},
		complete : function(){
			BlockUi.unBlockPage();
		},
		success : function(data, msg, jqXHR) {			
			
			if(data.message != ""){
//				try{
//					let userObj = JSON.parse(data.message);
//					$(userObj).each(function(i,val){
//						$('#dialog_add_user .modal-title').html("แก้ไขข้อมูลผู้ใช้งาน");
//						$("#hd_user_id").val(val.userId);
//						$("#employee_id_dialog").val(val.employeeId);
//						$('#employee_id_dialog').prop('readonly', true);
//						$("#name_dialog").val(val.fullname);
//						$("#userlan_dialog").val(val.username);
//						$('#userlan_dialog').prop('readonly', true);
//						//$("#password_dialog").val(data.password);
//						$("#password_dialog").val('');						
//						$("#email_dialog").val(val.email);
//						$("#telephone_dialog").val(val.telephone);
//						$("#description_dialog").val(val.description);
//						if(val.enable=='Y'){
//							$('#chk_status').prop('checked', true);
//							$('#status_user_dialog').bootstrapSwitch('state',true);
//						}else{
//							$('#status_user_dialog').bootstrapSwitch('state',false);
//						}
//						
//						if($("#sel_group_dialog").val() != '3'){
//							$('.option-require').show();
//							
//							$('#employee_id_dialog').rules("add", {	
//								required : true,
//								maxlength : 10,
//								messages:{
//									required: 'กรุณาใส่รหัสพนักงาน',
//					        		maxlength : jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร")
//								}
//							});
//							
//						}else{
//							$('.option-require').hide();
//							$('#employee_id_dialog').rules("remove");
//						}
//						
//				    	$('#sel_group_dialog').val(val.userGroupId.userGroupId);
//				    	$('#sel_group_dialog').trigger('change');
//				    	if(val.supplierId != null){
//				    		$(val.supplierId).each(function(i,v){				    			
//				    			if(v.supplierId != "0"){				    				
//				    				$('#sel_supplier_company_dialog').val(parseInt(v.supplierId));
//								    $('#sel_supplier_company_dialog').trigger('change');
//				    			}
//				    		});
//				    	}
//				    	/*if(data.supplier.supplierId != 0){	    		
//				    		$('#sel_supplier_company_dialog').val(data.supplier.supplierId);
//						    $('#sel_supplier_company_dialog').trigger('change');	    			    		
//				    	}*/
//				    	$('#dialog_add_user').modal('toggle');
//					});
//				}catch (e) {
//					customMessageDialog("error", "ตรวจสอบข้อมูล", e, null);
//				}
				
			}
			
		},
		error : function(jqXHR, textStatus, errorThrown) {	
			try{
				customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseJSON.message, null);
			}catch(Execption){
				customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
			}
			
		},
	});
}

$(document).on('click', '.btn_edit_problem', function(){
	let carDetailObj = (JSON.parse(URI.decode($(this).parent().find('.detail_edit_problem').val())));
	$('#detail_edit_problem').val(JSON.stringify(carDetailObj));
	$('#car_no_editProblem').text(carDetailObj.carId.carNo.trim())
	$('#date_appoint_editProblem').text(objFinalAudit.planDate.split(" ")[0].trim());
	$('#auditor_editProblem').text($('#auditor_list').text())
	$('#questionOfCar_editProblem').html(carDetailObj.auditResultId.evalPlanId.detail.trim());
	$('#problemOfCar_editProblem').val(carDetailObj.detail);
	
	$('#dialog_edit_problem').modal('show');
});

$('#btn_save_edit_problem').on('click', function(){
	let carDetailObj = JSON.parse($('#detail_edit_problem').val());
	let objRequestCarDetail = {
		auditResultId : carDetailObj.auditResultId,
		detail : $('#problemOfCar_editProblem').val().trim()
	}
	let url = contextPath+"/api/car_detail/update_car_detail";
	ajaxProcess.submit(url, 'POST', objRequestCarDetail, '#dialog_edit_problem', (data)=>{
		if(data.result){
			setTimeout(()=>{
				url = contextPath+'/api/evidence/evidence_solve_car/';
				let objRequestEvidence = {auditResultId : carDetailObj.auditResultId};
				ajaxProcess.submit(url, 'POST', objRequestEvidence, '', (data)=>{
					if(data.result){
						loadCar(JSON.parse($('#detail_edit_problem').val()).carId.carId);
					}
				});
			},50);				
		}
	});
});

function loadLogHistory(){
	var checklistPlanId = $('#pass_param').val().trim();
	let url = contextPath+'/api/final_audit_result/final_audit_result_log_list/'+JSON.stringify({
		checklistPlanId : {checklistPlanId : checklistPlanId}
	});
	ajaxProcess.submit(url, 'GET', null, '#table_car_result', (data)=>{
		if(data.result){
			$('#table_log_result > tbody').empty();
			let logResult = JSON.parse(data.message);
			console.log("logResult",logResult)
			if(logResult != null && logResult != ''){				
				if(logResult.length == 0){
					$('#table_log_result > tbody').append($('<tr>').append($('<td colspan="4" style="text-align : center;">').append($("<span>ไม่พบประวัติการดำเนินการ</span>"))));
				}else{
				console.log("logResult",logResult);
				let htmlTB = "";
				$(logResult).each(function(i,v){
					htmlTB += "<tr>";
					
					htmlTB += "<td style='text-align: center;'>";
					htmlTB += i+1;
					htmlTB += "</td>";
					
					htmlTB += "<td style='text-align: left;'>";
					htmlTB += v.detail;
					htmlTB += "</td>";
					
					htmlTB += "<td style='text-align: left;'>";
					htmlTB += v.createByName;
					htmlTB += "</td>";
							
					htmlTB += "<td style='text-align: left;'>";
					htmlTB += v.createDate;
					htmlTB += "</td>";
					htmlTB += "</tr>";
				});
				$('#table_log_result > tbody').append(htmlTB);
					
				}
			}else{
				$('#table_log_result > tbody').append($('<tr>').append($('<td colspan="4" style="text-align : center;">').append($("<span>ไม่พบประวัติการดำเนินการ</span>"))));
			}
					
		}
	});
}

