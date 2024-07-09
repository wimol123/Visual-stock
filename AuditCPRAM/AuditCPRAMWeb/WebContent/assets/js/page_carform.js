var userGroupId = null;
var optionFileInput = {
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
    	showDrag : false,
    	showRemove : false
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

$(function(){
	
	
	$('#evidence_document_preview_supplier').fileinput(optionFileInput);
	
	let url = contextPath+'/api/user/get_session_user';
	ajaxProcess.submit(url, 'GET', null, '', (data)=>{
		let objData = JSON.parse(data.message);
		userGroupId = objData.userGroupId.userGroupId;
	},{async:false});
	
	if($('#receive_param').val() != "" && $('#receive_param').val() != null){
		let carObj = JSON.parse(URI.decode($('#receive_param').val().trim()));
		
		$('#carString').val($('#receive_param').val().trim());
		if(carObj['carStatusId']['carStatusId'] == "1" || carObj['carStatusId']['carStatusId'] == "2"){
			if(userGroupId == "2")
				$('#cancel-car').slideDown();
		}
		loadCarDetail(carObj);		
	}else{
		window.location.href = 'car.jsp';
	}
});



function loadCarDetail(carObj){
	
	let url = contextPath+'/api/car_detail/car_detail_list/'+JSON.stringify({carId : {carId : carObj.carId}});
	ajaxProcess.submit(url, 'GET', null, null, (data)=>{
		if(data.result){
			let carDetailObj = JSON.parse(data.message);
			console.log(carDetailObj)
			generateCarDetailSection(carDetailObj)
		}
	});
}


function generateCarDetailSection(carDetailObj){
	
	let numOfElementInRow = 2;
	let numOfRow = Math.ceil(carDetailObj.length/numOfElementInRow);
	let html = '';
	for(let i=0; i<numOfRow; i++){
		html += '<div class="row">';
		for(let j = numOfElementInRow*i; (j < numOfElementInRow*(i+1)) && j< carDetailObj.length ; j++){
			html += '<div class="col-md-6">';
				html += '<div class="portlet light">';
					html += '<div class="portlet-body form">';
						html += generateCarDetailTable(carDetailObj[j]);
						if(carDetailObj[j].completed == ""){
							if(carDetailObj[j].enable == "Y"){
								if(userGroupId == "2"){
									html += '<div class="row">';
										html += '<div class="col-md-4"><button type="button" class="btn green-jungle btn_accept_car_detail">อนุมัติการแก้ไขปัญหา</button></div>';
										html += '<div class="col-md-4"><button type="button" class="btn yellow-gold btn_decline_car_detail">ไม่อนุมัติการแก้ไขปัญหา</button></div>';
										html += '<div class="col-md-2"><button type="button" class="btn red-mint btn_cancel_car_detail">ยกเลิกปัญหา</button></div>';
									html += '</div>';
								}								
							}
						}else if(carDetailObj[j].completed == "N"){
							let carObj = JSON.parse(URI.decode($('#receive_param').val().trim()));
							if(userGroupId == "2" && carObj.carStatusId.carStatusId != "4"){
								html += '<div class="row">';
									html += '<div class="col-md-4"><button type="button" class="btn green-jungle btn_accept_car_detail">อนุมัติการแก้ไขปัญหา</button></div>';
									html += '<div class="col-md-2"><button type="button" class="btn red-mint btn_cancel_car_detail">ยกเลิกปัญหา</button></div>';
								html += '</div>';
							}							
						}
						
					html += '</div>';
				html += '</div>';
			html += '</div>';
			
			
		}
		html += '</div>';
	}
	$(html).hide().appendTo('.car-detail-section').slideDown();
}


$('#cancel-car').on('click', function(){
	let carObj = JSON.parse(URI.decode($('#carString').val().trim()));
	carObj['enable'] = 'Y';
	carObj['carStatusId']['carStatusId'] = '5';
	ReasonModal.setupModal(null, 'เหตุผลในการยกเลิกใบคาร์','<div class="row"><label class="control-label col-sm-2">เหตุผล:</label><div class="col-sm-10"><textarea class="form-control" id="reason_cancel_car" placeholder="กรุณาใส่เหตุผลในการยกเลิกใบคาร์นี้"></textarea></div></div>')
	ReasonModal.confirmResult(function(isTrue){
		if(isTrue){
			if($('#reason_cancel_car').val() == ""){
				customMessageDialog('warning','การแจ้งเตือน', 'กรุณาใส่เหตุผลในการยกเลิกใบคาร์', null);
			}else{
				carObj['carDetail'] = [{remark : $('#reason_cancel_car').val().trim()}];
				$('#dialogReason').modal('hide');
				let url = contextPath + '/api/car/update_state_car';
				ajaxProcess.submit(url, 'POST', carObj, null, (data)=>{
					$('#cancel-car').slideUp();
					setTimeout(()=>{
						window.location.href = 'car.jsp';
					},500)		
				});
			}
		}else{
			$('#dialogReason').modal('hide');
		}
	});
	/**/
});

$(document).on('click', '.see_more_evidence', function(){
	let evidenceObj = JSON.parse(URI.decode($(this).parent().find('.evidenceDetail').val()));
	if(evidenceObj.length > 0){
		generateSolveCarEvidence(evidenceObj);
	}else{
		$('.list-picture-section').empty();
		let html = '<div class="row form-group">';
			html += '<div class="col-md-9 col-md-offset-2">';
				html += '<H4>ไม่พบข้อมูลของหลักฐานการแก้ไขประเภทรูปภาพ </H4>';
			html += '</div>';
		html += '</div>';
		$('.list-picture-section').append(html);
		$('#solve_evidence_text').val('');
	}
	$('.header-txt').text('หลักฐานการแก้ไข')
	$('#dialog_solve_detail_car').modal('show');
	
});

$(document).on('click', '.see_more_evidence_audit', function(){
	let evidenceObj = JSON.parse(URI.decode($(this).parent().find('.evidenceAuditDetail').val()));
	if(evidenceObj.length > 0){
		generateSolveCarEvidence(evidenceObj);
	}else{
		$('.list-picture-section').empty();
		let html = '<div class="row form-group">';
			html += '<div class="col-md-9 col-md-offset-2">';
				html += '<H4>ไม่พบข้อมูลของหลักฐานประเภทรูปภาพ </H4>';
			html += '</div>';
		html += '</div>';
		$('.list-picture-section').append(html);
		$('#solve_evidence_text').val('');
	}
	$('.header-txt').text('หลักฐาน')
	$('#dialog_solve_detail_car').modal('show');
	
});

$(document).on('click', '.btn_accept_car_detail', function(){
	let evidenceList = JSON.parse(URI.decode($(this).closest('.portlet').find('.evidenceDetail').val()));
	if(evidenceList.length > 0){
		ConfirmModal.setupModal(null, 'การยืนยัน', 'ยืนยันการอนุมัตินี้ใช่หรือไม่');
		ConfirmModal.confirmResult((isTrue)=>{
			if(isTrue){
				processButton('accept', $(this));
			}
		});
		
	}else{
		customMessageDialog('error','คำเตือน',"ไม่สามารถอนุมัติการแก้ไขปัญหาได้เนื่องจากไม่พบหลักฐานการแก้ไขปัญหา กรุณารอซัพพลายเออร์ส่งหลักฐาน",null);
	}
	
});


$(document).on('click', '.btn_decline_car_detail', function(){
	let btnElement = $(this);
	let evidenceList = JSON.parse(URI.decode($(this).closest('.portlet').find('.evidenceDetail').val()));
	if(evidenceList.length > 0){
		ReasonModal.setupModal(null, 'เหตุผลในการไม่อนุมัติการแก้ไข','<div class="row"><label class="control-label col-sm-2">เหตุผล:</label><div class="col-sm-10"><textarea class="form-control" id="reason_decline" placeholder="กรุณาใส่เหตุผลในการไม่อนุมัติการแก้ไขนี้"></textarea></div></div>')
		ReasonModal.confirmResult(function(isTrue){
			if(isTrue){
				if($('#reason_decline').val() == ""){
					customMessageDialog('warning','การแจ้งเตือน', 'กรุณาใส่เหตุผลในการไม่อนุมัติการแก้ไข', null);
				}else{
					processButton('decline',btnElement);
					$('#cancel_add_reason').unbind();
					$('#confirm_add_reason').unbind();
				}
			}else{
				$('#reason_decline').val('');
				$('#dialogReason').modal('hide');
			}
		});
	}else{
		customMessageDialog('error','คำเตือน',"ไม่สามารถกดปุ่มไม่อนุมัติการแก้ไขปัญหาได้เนื่องจากไม่พบหลักฐานการแก้ไขปัญหา กรุณารอซัพพลายเออร์ส่งหลักฐาน",null);
	}
	
});


$(document).on('click', '.btn_cancel_car_detail', function(){
	let btn = $(this);	
	
	ReasonModal.setupModal(null, 'เหตุผลในการยกเลิก','<div class="row"><label class="control-label col-sm-2">เหตุผล:</label><div class="col-sm-10"><textarea class="form-control" id="reason_cancel" placeholder="กรุณาใส่เหตุผลในการยกเลิกปัญหานี้"></textarea></div></div>')
	ReasonModal.confirmResult(function(isTrue){				
		if(isTrue){
			if($('#reason_cancel').val() == ""){
				customMessageDialog('warning','การแจ้งเตือน', 'กรุณาใส่เหตุผลในการยกเลิกปัญหานี้', null);
			}else{				
				processButton('cancel',btn);
				$('#cancel_add_reason').unbind();
				$('#confirm_add_reason').unbind();
			}
		}else{
			$('#reason_cancel').val('');
			$('#dialogReason').modal('hide');
		}
	});
	
});

function processButton(action, elementsBtn){
	
	let checklistDetailObj = JSON.parse(URI.decode(elementsBtn.closest('.portlet').find('.checklistDetailString').val()));
	let carDetailObj = {};
	let blockPosition = null;
	let bodyPortlet = elementsBtn.closest('.portlet-body');
	let url = contextPath +'/api/car_detail/update_state';
	
	if(action === 'accept'){
		carDetailObj = {
			auditResultId : checklistDetailObj.auditResultId,
			carId : checklistDetailObj.carId,
			completed : 'Y',
			detail : checklistDetailObj.detail,
			remark : '',
			enable : 'Y' 
		};
		blockPosition = elementsBtn.closest('.portlet');
	}else if(action === 'decline'){
		carDetailObj = {
			auditResultId : checklistDetailObj.auditResultId,
			carId : checklistDetailObj.carId,
			completed : 'N',
			detail : checklistDetailObj.detail,
			remark : $('#reason_decline').val().trim(),
			enable : 'Y'
		};
		blockPosition = '#dialogReason';
		
	}else if(action === 'cancel'){
		carDetailObj = {
			auditResultId : checklistDetailObj.auditResultId,
			carId : checklistDetailObj.carId,
			completed : '',
			detail : checklistDetailObj.detail,
			remark : $('#reason_cancel').val().trim(),
			enable : 'N'
		};
		blockPosition = '#dialogReason';
		
	}else{
		return false;
	}
	
	
	
	
	ajaxProcess.submit(url, 'POST', carDetailObj, blockPosition, (data)=>{
		if(data.result){		
			
			url = contextPath+'/api/car_detail/car_detail_list/'+JSON.stringify({carId : {carId : checklistDetailObj.carId.carId}});
			ajaxProcess.submit(url, 'GET', null, '', (data)=>{
				if(data.result){
					
					
					let carDetailObj = $.grep(JSON.parse(data.message), (v)=>{
						return v.auditResultId.answerId.answerId == checklistDetailObj.auditResultId.answerId.answerId 
							&& v.auditResultId.auditorId.userId == checklistDetailObj.auditResultId.auditorId.userId
							&& v.auditResultId.checklistPlanId.checklistPlanId == checklistDetailObj.auditResultId.checklistPlanId.checklistPlanId
							&& v.auditResultId.evalPlanId.evalPlanId == checklistDetailObj.auditResultId.evalPlanId.evalPlanId
							&& v.detail == checklistDetailObj.detail;
					});
					bodyPortlet.find('.btn_accept_car_detail').remove();
					bodyPortlet.find('.btn_cancel_car_detail').remove();
					bodyPortlet.find('.btn_decline_car_detail').remove();
					bodyPortlet.slideUp().empty();
					let htmlCarDetailTable = generateCarDetailTable(carDetailObj[0]);
					if(action === 'decline'){
						$('#reason_decline').val('');
						$('#dialogReason').modal('hide');
						htmlCarDetailTable += '<div class="row">';
						htmlCarDetailTable += '<div class="col-md-4"><button type="button" class="btn green-jungle btn_accept_car_detail">อนุมัติการแก้ไขปัญหา</button></div>';
						htmlCarDetailTable += '<div class="col-md-4"><button type="button" class="btn red-mint btn_cancel_car_detail">ยกเลิกปัญหา</button></div>';
						htmlCarDetailTable += '</div>';
					}else if(action === 'cancel'){
						$('#reason_cancel').val('');
						$('#dialogReason').modal('hide');
					}					
					setTimeout(()=>{
						$(htmlCarDetailTable).appendTo(bodyPortlet);
						bodyPortlet.slideDown();
					},50);
					
					elementsBtn = null;
					
					url = contextPath+'/api/car/car_list/'+JSON.stringify({carId : checklistDetailObj.carId.carId});
					ajaxProcess.submit(url, 'GET', null, null, (data)=>{
						if(data.result){
							let car = JSON.parse(data.message);
							
							if(car[0].carStatusId.carStatusId == "5"){
								$('#cancel-car').slideUp();
							}
						}
					});
					
				}
			});
						
		}
	});
	
	
	
	
}



function generateCarDetailTable(carDetailObj){
	
	let tb = '<table class="table table-bordered table-striped">';
	//let url = contextPath+'/api/evidence/evidence_solve_car/'+URI.encode(JSON.stringify({auditResultId : carDetailObj.auditResultId}));
	let url = contextPath+'/api/evidence/evidence_solve_car/';
	let objRequestEvidence = {auditResultId : carDetailObj.auditResultId};
	ajaxProcess.submit(url, 'POST', objRequestEvidence, '', (data)=>{
		let statusCarDetail = '';
		let evidenceSolveCar = URI.encode(data.message);
		
		let remarkCancel = "";
		let remarkDecline = "";
		if(carDetailObj.completed == ""){
			if(carDetailObj.enable == "Y"){
				if(JSON.parse(data.message).length > 0){
					statusCarDetail = "รอพิจารณาหลักฐาน";
				}else{
					statusCarDetail = "รอยืนยันการแก้ไขปัญหา";
				}				
			}else{
				statusCarDetail = "ยกเลิกปัญหา";
				remarkCancel = carDetailObj.remark;
			}
		}else{
			if(carDetailObj.completed == "Y"){
				statusCarDetail = "ผ่าน";
			}else{
				statusCarDetail = "ไม่ผ่าน";
				remarkDecline = carDetailObj.remark;
			}
		}
		
			tb += '<tbody>';
				tb += '<tr><td style="width:40%"><div class="row"><div class="col-md-12"><i class="fa fa-list-ol fa-1x"></i>&nbsp;&nbsp;หมายเลขใบคาร์</div></div></td><td style="width:60%"><span style="overflow-wrap: break-word;">'+carDetailObj.carId.carNo+'</span></td></tr>';
				
				tb += '<tr><td><div class="row"><div class="col-md-12"><i class="fa fa-list-ol fa-1x"></i>&nbsp;&nbsp;หมายเลขแผนการเข้าตรวจ</div></div></td><td><span style="overflow-wrap: break-word;">'+carDetailObj.auditResultId.checklistPlanId.checklistPlanNo+'</span></td></tr>';
				
				tb += '<tr><td><div class="row"><div class="col-md-12"><i class="fa fa-pencil-square-o fa-1x"></i>&nbsp;&nbsp;เกณฑ์การพิจารณา</div></div></td><td><span style="overflow-wrap: break-word;">'+carDetailObj.auditResultId.evalPlanId.detail+'</span></td></tr>';
				
				tb += '<tr><td><div class="row"><div class="col-md-12"><i class="fa fa-exclamation-triangle fa-1x"></i>&nbsp;&nbsp;ปัญหาที่พบ</div></div></td><td><span style="overflow-wrap: break-word;">'+carDetailObj.detail+'</span></td></tr>';
				tb += '<tr><td><div class="row"><div class="col-md-12"><i class="fa fa-check fa-1x"></i>&nbsp;&nbsp;สถานะ</div></div></td><td><span style="overflow-wrap: break-word;">'+statusCarDetail+'</span></td></tr>';
				if(remarkCancel != ""){
					tb += '<tr><td><div class="row"><div class="col-md-12"><i class="fa fa-bullhorn fa-1x"></i>&nbsp;&nbsp;เหตุผลในการยกเลิก</div></div></td><td><span style="overflow-wrap: break-word;">'+remarkCancel+'</span></td></tr>';
				}
				if(remarkDecline != ""){
					tb += '<tr><td><div class="row"><div class="col-md-12"><i class="fa fa-bullhorn fa-1x"></i>&nbsp;&nbsp;เหตุผลในการไม่อนุมัติการแก้ปัญหา</div></div></td><td><span style="overflow-wrap: break-word;">'+remarkDecline+'</span></td></tr>';
				}
				if(carDetailObj.completeDate != "" && carDetailObj.completeDate != null){
					tb += '<tr><td><div class="row"><div class="col-md-12"><i class="fa fa-calendar-o fa-1x"></i>&nbsp;&nbsp;วันที่ดำเนินการเสร็จสิ้น</div></div></td><td><span style="overflow-wrap: break-word;">'+carDetailObj.completeDate+'</span></td></tr>';
				}
				if(carDetailObj.evidenceId != null && carDetailObj.evidenceId.length > 0){
					
					tb += '<tr><td><div class="row"><div class="col-md-12"><i class="fa fa-paperclip fa-1x"></i>&nbsp;&nbsp;หลักฐาน</div></div></td><td><button type="button" class="btn btn-default see_more_evidence_audit">ดูรายละเอียด</button><input class="form-control evidenceAuditDetail hide" value="'+URI.encode(JSON.stringify(carDetailObj.evidenceId))+'"/></td></tr>';
				}
				tb += '<tr><td><div class="row"><div class="col-md-12"><i class="fa fa-paperclip fa-1x"></i>&nbsp;&nbsp;หลักฐานการแก้ไข</div></div></td><td><button type="button" class="btn btn-default see_more_evidence">ดูรายละเอียด</button><input class="form-control evidenceDetail hide" value="'+evidenceSolveCar+'"/></td></tr>';
				
				tb += '<tr class="hide"><td><div class="row"><div class="col-md-12"><i class="fa fa-list-ol fa-1x"></i>&nbsp;&nbsp;Car Id</div></div></td><td><input class="form-control carId" value="'+carDetailObj.carId.carId+'"/></td></tr>';
				tb += '<tr class="hide"><td><div class="row"><div class="col-md-12"><i class="fa fa-list-ol fa-1x"></i>&nbsp;&nbsp;CheklistPlan Id</div></div></td><td><input class="form-control checklistPlanId" value="'+carDetailObj.auditResultId.checklistPlanId.checklistPlanId+'"/></td></tr>';
				tb += '<tr class="hide"><td><div class="row"><div class="col-md-12"><i class="fa fa-list-ol fa-1x"></i>&nbsp;&nbsp;EvalPlanId</div></div></td><td><input class="form-control evalPlanId" value="'+carDetailObj.auditResultId.evalPlanId.evalPlanId+'"/></td></tr>';
				tb += '<tr class="hide"><td><div class="row"><div class="col-md-12"><i class="fa fa-list-ol fa-1x"></i>&nbsp;&nbsp;ChecklistDetailString</div></div></td><td><input class="form-control checklistDetailString" value="'+URI.encode(JSON.stringify(carDetailObj))+'"/></td></tr>';
			tb += '</tbody>';
		tb+= '</table>';
		
	},{async : false});
	
	return tb;
	
}


function generateSolveCarEvidence(evidenceObj){
	console.log("evidenceObj",evidenceObj);
	$('.list-picture-section').empty();
	$('#evidence_document_preview_supplier').fileinput('reset');
	$('#evidence_document_preview_supplier').fileinput('clear');
	$('#evidence_document_preview_supplier').fileinput('destroy');	
    		
	let evidencePictureType = $.grep(evidenceObj, function(v){
		return v.evidenceTypeId.evidenceTypeId == "1";
	});
	let evidenceTextType = $.grep(evidenceObj, function(v){
		return v.evidenceTypeId.evidenceTypeId == "2";
	});
	let evidenceDocumentType = $.grep(evidenceObj, function(v){
		return v.evidenceTypeId.evidenceTypeId == "5";
	});
	
	$(evidenceTextType).each(function(i,v){
		$('#solve_evidence_text').val(v.data);
	});
	
	
	
	if(evidencePictureType.length > 0){
		let numOfPictureInRow = 3;
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
		});
	}else{
		let html = '<div class="row form-group">';
			html += '<div class="col-md-9 col-md-offset-2">';
				html += '<H4>ไม่พบข้อมูลของหลักฐานการแก้ไขประเภทรูปภาพ  </H4>';
			html += '</div>';
		html += '</div>';
		$('.list-picture-section').append(html);
	}
	
	if(evidenceDocumentType.length > 0){
	
		let initialPreviewData = [];
		let initialPreviewConfigData = [];
		$(evidenceDocumentType).each(function(i,v){
			let typeFile = (v.data.split('/')[v.data.split('/').length-1]).trim().split('.')[(v.data.split('/')[v.data.split('/').length-1]).trim().split('.').length-1];
			let fileName = (v.data.split('/')[v.data.split('/').length-1]).trim();
			initialPreviewData.push(v.data);
			initialPreviewConfigData.push({
				type : typeFile.trim(),
				caption : fileName,
				downloadUrl : v.data.trim(),
				key : v.evidenceId.trim()
			});
		});
	
		
		optionFileInput = $.extend(optionFileInput, {initialPreview : initialPreviewData, initialPreviewConfig : initialPreviewConfigData});
		console.log(optionFileInput)
		$('#evidence_document_preview_supplier').fileinput('destroy');
		$('#evidence_document_preview_supplier').fileinput(optionFileInput);					
		
	}else{
		delete optionFileInput.initialPreview;
		delete optionFileInput.initialPreviewConfig;
		
		$('#evidence_document_preview_supplier').fileinput(optionFileInput);
	}
	
		
}

var ReasonModal = function(){
	return{
		setupModal: function(type, title, message) {
			$("#dialogReasonHeader").html(title);
			$("#dialogReasonBody").html(message);
			$("#dialogReason").modal('show');
		},
		confirmResult: function(callback){
			$('#confirm_add_reason').on('click', function(e){
				//console.log("Confirm");
				if (callback!=null){
					//$('#cancel_add_reason').unbind();
					//$('#confirm_add_reason').unbind();
					return callback(true);
				}
			})
			$('#cancel_add_reason').on('click', function(e){
				//console.log("Cancel");
				if (callback!=null){
					$('#cancel_add_reason').unbind();
					$('#confirm_add_reason').unbind();
					return callback(false);
				}
			})
		}
		
	};
}();

function openFileNewTab(data,filename){
	
	if(data.includes("application/pdf")){
		var pdf_newTab = window.open("");
		pdf_newTab.document.write(
    		"<html><head></head><body><iframe  width='100%' height='100%' src='"+data+"'></iframe></body></html>"
    	);
	} else if(data.includes("image/")){
		var image = new Image();
	    image.src = data;
	    image.style = "display: block;-webkit-user-select: none;margin: auto;background-color: hsl(0, 0%, 90%);transition: background-color 300ms;";
	    var w = window.open("");
		var html = "<html><head></head><body style='margin: 0px; background: #0e0e0e; height: 100%'> <a href='"+data+"' download='"+filename+"'>"+image.outerHTML+"</a> </body></html>"
		w.document.write(html);
	} else {
		const downloadLink = document.createElement("a");
	     downloadLink.href = data;
	     downloadLink.download = filename;
	     downloadLink.click();
	}    
}