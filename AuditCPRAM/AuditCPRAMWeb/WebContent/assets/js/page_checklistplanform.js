var answerListByChecklistType = null;
var objChecklistPlan = null;
var numOfAuditor = null;
var objStyleInTableAuditResult = 
	[{	'topic' : {"ClassColor" : "bg-default", "padding" : "0px;"},
		'sub_topic' : {"ClassColor" : "bg-grey-steel", "padding" : "50px;"},
		'question_group' : {"ClassColor" : "bg-grey-cararra", "padding" : "80px;"},
		'question' : {"ClassColor" : "", "padding" : "100px;"},
	}];

$(function(){
	
	if($('#checklist_plan_id_receive').val() != ""){
		
		
		let stringChecklistPlan = URI.decode($('#checklist_plan_id_receive').val());
		objChecklistPlan = JSON.parse(stringChecklistPlan);
		
		init.loadText(objChecklistPlan);
		init.loadCondition(JSON.parse(objChecklistPlan.approveSupplierRule), objChecklistPlan.checklistTypeId.checklistTypeId);
		init.loadTableAuditResult(objChecklistPlan);
	}
	$('.info-button').css('cursor', 'pointer'); 
});


$(document).on('click','.answerId',function(){
	let resultChecked = true;
	if($(this).prop('checked')){
		if($(this).closest('tr').find('.answerId:checked').size() > 1){
			$(this).closest('tr').find('.answerId').not($(this)).prop('checked',false);
		}
	}else{
		if($(this).closest('tr').find('.answerId:checked').size() == 0){
			resultChecked = false;
		}
	}
	return resultChecked;
});


$('#save-result_checklistPlan').on('click', function(){
	
	ConfirmModal.setupModal(null, "ยืนยันการบันทึกผลการเข้าตรวจ", "ต้องการยืนยันผลการเข้าตรวจครั้งนี้ใช่หรือไม่");
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			
			let flagError = false;
			
			let listObjAuditResult = [];
			let checkChooseAnswer = true;
			$($('#table_audit_result').find('.has-error')).each(function(){
				$(this).removeClass('has-error');
			});
			$('.evalPlan_Question_section').each(function(){
				if($(this).parent().find('.answerId').length > 0){
					let answerId = "";
					let auditor_Id = "";
					if(numOfAuditor > 1){
						$($(this).parent().find('.answerId')).each(function(){
							if($(this).prop('checked')){
								answerId = ($(this).val()).trim();
								auditor_Id = $(this).parent().find('.auditorId').text().trim();
							}					
						});
					}
					else if(numOfAuditor == 1){
						answerId = $(this).parent().find('.answerId').text().trim();
						auditor_Id = $(this).parent().find('.auditorId').text().trim();
					}
					if(answerId == ""){
						$(this).parent().addClass('has-error');
						$('html, body').animate({
						    scrollTop: $(this).parent().offset().top
						  }, 800)
						listObjAuditResult = [];
						if(checkChooseAnswer){
							customMessageDialog('warning', 'การแจ้งเตือน', 'กรุณาเลือกผลการเข้าตรวจ', null);
							checkChooseAnswer = false;
						}				
						flagError = true;
						//return false;
					}else{
						listObjAuditResult.push({
							checklistPlanId : {checklistPlanId : $('#checklistplan_id').val().trim()},
							auditorId : {userId : auditor_Id},
							evalPlanId : {evalPlanId : $(this).find('.evalPlanId').text().trim()},
							answerId : {answerId : answerId},
							accepted : 'Y'
						});
						
					}
				}else{
					//customMessageDialog('warning', 'การแจ้งเตือน', 'ไม่สามารถบันทึกข้อมูลได้ เนื่องจากผลการตรวจยังไม่ครบ', null);
					//listObjAuditResult = [];
					//return false;			
				}
				
			});
			
			if(listObjAuditResult != [] && listObjAuditResult.length > 0 && listObjAuditResult != null && !flagError) {
				listObjAuditResult = sortByKeyAsc(listObjAuditResult, 'checklistPlanId.checklistPlanId');
				let url = contextPath +'/api/audit_result/update_audit_result';
				ajaxProcess.submit(url, 'POST', listObjAuditResult, null, (data)=>{
					setTimeout(function(){
						$('#pass_param').val(listObjAuditResult[0].checklistPlanId.checklistPlanId);
						$('#pass_form_finalauditresult').submit();
					},80)
					
				});
			}else{
				if(checkChooseAnswer){
					customMessageDialog('warning', 'การแจ้งเตือน', 'ไม่สามารถบันทึกข้อมูลได้ เนื่องจากผลการตรวจยังไม่ครบ', null);
				}
				
			}
			
		}
	});
	
	
	
});

$('#final_audit_result').on('click',function(){		
	BlockUi.blockPage();
	setTimeout(()=>{
		$('#pass_param').val(objChecklistPlan.checklistPlanId);
		$('#pass_form_finalauditresult').submit();
	},200)
});

$('#detail-car').on('click',function(){
	BlockUi.blockPage();
	setTimeout(()=>{
		$('#pass_param_car').val(URI.encode(JSON.stringify(objChecklistPlan.carId)));
		$('#form_pass_param').submit();
	},200)
	
});

$('#cancel-checklist-plan').on('click', function(){
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการยกเลิกแผนการเข้าตรวจนี้ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			let url = contextPath+'/api/checklist_plan/cancel_checklist_plan';
			ajaxProcess.submit(url, 'POST', objChecklistPlan, null, (data)=>{
				if(data.result){
					setTimeout(()=>{
						window.location.href = 'checklist_plan.jsp';
					},500);					
				}
			});
		}
	});
	
});

$(document).on('click', '.btn-show-evidence', function(){
	let evidenceMediaList = JSON.parse($(this).find('.evidence-picture-list').text());
	$('.list-picture-section').empty();
	if(evidenceMediaList.length > 0){
		let numOfPictureInRow = 3;
		let numOfRow = Math.ceil(evidenceMediaList.length/numOfPictureInRow);
		
		for(i = 0; i< numOfRow; i++ ){
			let htmlPicture = '<div class="row form-group">';
			for(j = numOfPictureInRow*i; (j < numOfPictureInRow*(i+1)) && (j < evidenceMediaList.length); j++){
				htmlPicture += '<div class="col-md-3">';
					htmlPicture += '<div class="row form-group">';
					htmlPicture += '<div class="col-md-12">';
						htmlPicture += '<a class="elem" href="'+evidenceMediaList[j].location+'" title="'+evidenceMediaList[j].location.split('/')[evidenceMediaList[j].location.split('/').length-1]+'" data-lcl-txt="" data-lcl-author="" data-lcl-thumb="'+evidenceMediaList[j].location+'">';
						htmlPicture += '<img src="'+evidenceMediaList[j].location+'" class="img-thumbnail">';
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
		
		$('#dialog_evidence_media').modal('show');
	}
});

function sortByKeyAsc(array, key) {
    return array.sort(function (a, b) {
        var x = a[key]; var y = b[key];
        return ((x < y) ? -1 : ((x > y) ? 1 : 0));
    });
}
var init = function(){
	return{
		loadText : function(objChecklistPlan){
			
			let url = contextPath+'/api/user/get_session_user';
			ajaxProcess.submit(url, 'GET', null, '', (data)=>{
				let objData = JSON.parse(data.message);
				if(objData.userGroupId.userGroupId == "2" || objData.userGroupId.userGroupId == "1" || objData.userGroupId.userGroupId == "5"){
					if(objChecklistPlan.checklistPlanStatusId.checklistPlanStatusId == "1"){						
						$('#save-result_checklistPlan').hide();						
					}else if(objChecklistPlan.checklistPlanStatusId.checklistPlanStatusId == "2"){
						$('#cancel-checklist-plan').hide();
					}else if(objChecklistPlan.checklistPlanStatusId.checklistPlanStatusId == "3" || objChecklistPlan.checklistPlanStatusId.checklistPlanStatusId == "4" || objChecklistPlan.checklistPlanStatusId.checklistPlanStatusId == "5"){
						$('#cancel-checklist-plan').hide();
						$('#save-result_checklistPlan').hide();
						$('#final_audit_result').show();
					}
					if(objData.userGroupId.userGroupId != "2" && (objChecklistPlan.checklistPlanStatusId.checklistPlanStatusId == "1" || objChecklistPlan.checklistPlanStatusId.checklistPlanStatusId == "2")){
						$('.quick-nav').hide();
					}else{
						$('.quick-nav').show();
					}
					
				}
			});
			
			$('.label_status').empty();
			$('<span class="label" style="background-color:'+objChecklistPlan.checklistPlanStatusId.statusColor+';font-size:12px;"> '+objChecklistPlan.checklistPlanStatusId.checklistPlanStatusName+' </span>').appendTo($('.label_status'));			
			$('#checklistplan_title').val(objChecklistPlan.checklistTitle);
			$('#checklistplan_id').val(objChecklistPlan.checklistPlanId);
			$('#productType_name').val(objChecklistPlan.productTypeId.name);
			$('#productType_id').val(objChecklistPlan.productTypeId.productTypeId);
			$('#scope_checklistplan').val(objChecklistPlan.checklistScope);
			$('#supplier_company').val(objChecklistPlan.supplierId.supplierCompany);
			$('#supplier_id').val(objChecklistPlan.supplierId.supplierId);
			$('#location_name').val(objChecklistPlan.locationId.locationName);
			$('#location_id').val(objChecklistPlan.locationId.id);
			let addressString = objChecklistPlan.locationId.addressId.address + "  ";
			addressString += objChecklistPlan.locationId.addressId.subDistrictId.name + "  ";
			addressString += objChecklistPlan.locationId.addressId.districtId.name + "  ";
			addressString += objChecklistPlan.locationId.addressId.provinceId.name + "  ";
			addressString += objChecklistPlan.locationId.addressId.postcode;
			$('#address').val(addressString);			
			$('#address_id').val(objChecklistPlan.locationId.addressId.addressId);
			$('#sub_district_id').val(objChecklistPlan.locationId.addressId.subDistrictId.subDistrictId);
			$('#district_id').val(objChecklistPlan.locationId.addressId.districtId.districtId);
			$('#province_id').val(objChecklistPlan.locationId.addressId.provinceId.provinceId);
			$('#plan_date').val(objChecklistPlan.planDate.split(" ")[0].trim());
			let time = objChecklistPlan.planDate.split(" ")[1].trim();
			$('#plan_time').val(time.split(":")[0].trim()+":"+time.split(":")[1].trim());
			$('#description').val(objChecklistPlan.description);
			editor.setText('#Criteria', objChecklistPlan.scoringCriteria);
			$('#checklistplan_number').val(objChecklistPlan.checklistPlanNo)
			
		},
		loadCondition : function(objCondition, checklistTypeId){			
			
			
			let url = contextPath+'/api/answer/answer_list_grade_calculator/'+JSON.stringify({
				checklistTypeId : checklistTypeId
			});
			ajaxProcess.submit(url, 'GET', null, '#portlet-detail-checklist-plan', function(data){
				if(data.result){
					answerListByChecklistType = JSON.parse(data.message);
					$('#table_condition').find('thead').find('tr').append($('<th>Grade</th>'));
					$(answerListByChecklistType).each(function(i,v){						
						$('#table_condition').find('thead').find('tr').append($('<th>'+v.answerDetail+'</th>'));
					});
					$('#table_condition').find('thead').find('tr').append($('<th>CorrectiveAction</th>'));
					$('#table_condition').dataTable({
						"searching": false,
						"language": {
					        "url": "assets/global/plugins/datatables/Thai.json"
					    },
					    "columnDefs": [
					    	{
					            "searchable": false,
					            "orderable": false,
					            "targets": [1,2,3,4]
					        },{
					        	"searchable": false,
					            "orderable": true,
					            "targets": [0]
					        }
					    ],
					    "order": [[0, 'asc']],
					    "initComplete": function (settings, json) {	    	
					    	$('.dataTables_filter').remove();
					    	$('.dataTables_length').remove();
					    	$('.dataTables_info').closest('div.row').remove();
					    }
					});
					$(objCondition).each(function(i,valueApproveSupplierRule){
						let arrayPushTable = [];
						arrayPushTable.push(valueApproveSupplierRule.grade);
						$(answerListByChecklistType).each(function(i,v){
							let answerCondition = '';
							$(valueApproveSupplierRule.condition).each(function(i,vCondition){
								if(vCondition.result_type == v.answerId){
									if(vCondition.op == '-'){
										answerCondition = vCondition.value;
									}else{
										answerCondition = vCondition.op +" "+ vCondition.value;
									}
									return false;
								}
							});
							arrayPushTable.push(answerCondition);
						});
						
						arrayPushTable.push(valueApproveSupplierRule.corrective_action);
						$('#table_condition').dataTable().fnAddData(arrayPushTable);
					});
				}
			});
			
		},
		loadTableAuditResult : function(checklistPlanIdObj){
			
			let url = contextPath+"/api/assign_plan/get_assign_plan_list/"+JSON.stringify({
				checklistPlanId : {	checklistPlanId : checklistPlanIdObj.checklistPlanId }
			});
			
			ajaxProcess.submit(url, 'GET', null, '#portlet-audit-result', (data)=>{
				let dataObjAssignPlan = (JSON.parse(data.message));		
				let urlSession = contextPath+'/api/user/get_session_user';
				let userGroupId = null;
				ajaxProcess.submit(urlSession, 'GET', null, '', (data)=>{
					let objData = JSON.parse(data.message);
					userGroupId=objData.userGroupId.userGroupId;
				},{async:false});
				
				if(checklistPlanIdObj.checklistPlanStatusId.checklistPlanStatusId == "1" || checklistPlanIdObj.checklistPlanStatusId.checklistPlanStatusId == "2"){					
					numOfAuditor = dataObjAssignPlan.length;
					if(numOfAuditor == 1){
						//$('#save-result_checklistPlan').hide();
						//init.loadCarDetail();
					}					
					if(userGroupId != "2"){
						$('.quick-nav').hide();						
					}
				}
				else if(checklistPlanIdObj.checklistPlanStatusId.checklistPlanStatusId == "3" || checklistPlanIdObj.checklistPlanStatusId.checklistPlanStatusId == "4" || checklistPlanIdObj.checklistPlanStatusId.checklistPlanStatusId == "5"){
					//$('.quick-nav').hide();
					$('#save-result_checklistPlan').remove();
					$('#cancel-checklist-plan').remove();					
					$('#final_audit_result').show();
					$('.quick-nav').show();
					numOfAuditor = 1;
					init.loadCarDetail();
				}else if(checklistPlanIdObj.checklistPlanStatusId.checklistPlanStatusId == "6"){
					numOfAuditor = 0;
					$('.quick-nav').remove();
				}				
				let numOfColumnInTable = numOfAuditor + 1;
				let widthColumnChecklist = (100 - (numOfAuditor * 12));
				let htmlHeaderTable = '<tr><th width="'+widthColumnChecklist+'%" style="text-align: center;">ข้อตรวจ</th>';
				
				if(checklistPlanIdObj.checklistPlanStatusId.checklistPlanStatusId == "1" || checklistPlanIdObj.checklistPlanStatusId.checklistPlanStatusId == "2"){
					$(dataObjAssignPlan).each(function(i,v){
						if('auditorId' in v){
							if(v.auditorId != null){
								htmlHeaderTable += '<th width="12%" style="text-align: center;">'+v.auditorId.fullname+'</th>';
							}							
						}
					});
				}else if(checklistPlanIdObj.checklistPlanStatusId.checklistPlanStatusId == "3" || checklistPlanIdObj.checklistPlanStatusId.checklistPlanStatusId == "4" || checklistPlanIdObj.checklistPlanStatusId.checklistPlanStatusId == "5"){
					htmlHeaderTable += '<th width="12%" style="text-align: center;">ผลการตรวจ</th>';
				}			
				
				htmlHeaderTable += '</tr>';
				$('#table_audit_result thead').append(htmlHeaderTable);
				
				let url = contextPath+"/api/eval_plan/eval_plan_list/"+JSON.stringify({
					checklistPlanId : { checklistPlanId : checklistPlanIdObj.checklistPlanId }
				});
				
				ajaxProcess.submit(url, 'GET', null, '', (data)=>{
					let evalPlanObjList = JSON.parse(data.message);
					
					if(evalPlanObjList.length > 0){
						console.log("evalPlanObjList",evalPlanObjList)
						let styleInTB = objStyleInTableAuditResult[0];
						$(evalPlanObjList).each(function(i, evalPlanObj){
							
							let htmlInTB = '<tr class="'+styleInTB[evalPlanObj.evalTypeName].ClassColor+'">';
							
							if(evalPlanObj.evalTypeId.evalTypeId != '5'){
								htmlInTB += '<td colspan="'+numOfColumnInTable+'">';
							}else{
								htmlInTB += '<td class="evalPlan_Question_section">';
							}
							htmlInTB += '<div class="row">';
							if(evalPlanObj.evalTypeId.evalTypeId != '2'){
								htmlInTB += '<div class="col-md-12" style="padding-left: '+styleInTB[evalPlanObj.evalTypeName].padding+'">';
							}else{
								htmlInTB += '<div class="col-md-12">';
							}
							
							
							
							let evidencePictureList = [];
							if(userGroupId != '3' && userGroupId != '4'){
								let urlGetEvidence = contextPath+"/api/evidence/get_evidence";
							
								let evidenceModelRequest = {
									auditResultId : {
										checklistPlanId : {
											checklistPlanId : evalPlanObj.checklistPlanId.checklistPlanId
										},
										evalPlanId : {
											evalPlanId : evalPlanObj.evalPlanId
										}
									},
									evidenceTypeId : {
										evidenceTypeId : 1
									},
									actionType : "A"
								}
								
								ajaxProcess.submit(urlGetEvidence, 'POST', evidenceModelRequest, '', (dataResponse)=>{
									let evidenceResponse = JSON.parse(dataResponse.message);
									$(evidenceResponse).each(function(index,evidence){
										evidencePictureList.push({
											location : evidence.data
										});
									})								
								},{async: false});
							}							
							
							
							if(evalPlanObj.evalTypeId.evalTypeId != '5'){
								
								if(evalPlanObj.auditResultId.length > 0 && evalPlanObj.auditResultId != []){
									let note = "";
									$(evalPlanObj.auditResultId).each(function(i,v){
										if(v.note != "" && v.note != null){
											note += "<div class='row'>";
												note += "<div class='col-md-12'>";
													note += "<h4> -"+v.auditorId.fullname+"</h4>";
												note += "</div>";
											note += "</div>";
											
											note += "<div class='row'>";
												note += "<div class='col-md-12'>";
													note += "&nbsp;<span>"+v.note+"</span>";
												note += "</div>";
											note += "</div>";
											
										}					
																				
									});
									if(note != ""){
										htmlInTB +='<div class="form-inline">';
											htmlInTB +='<div class="form-group">';
												htmlInTB += '<div class="content_checklist">'+evalPlanObj.title+' : '+evalPlanObj.detail+'</div>';
											htmlInTB +='</div>';
											htmlInTB +='&nbsp';
											htmlInTB +='<div class="form-group">';
												htmlInTB += '<div class="info-button" tabindex="0" data-trigger="focus" data-toggle="popover" data-placement="top" title="Note" data-content="'+note+'"><i class="fa fa-sticky-note-o" style="font-size: 16px;" ></i></div>'												
											htmlInTB +='</div>';
											
											if(evidencePictureList != null && evidencePictureList.length > 0){
												htmlInTB +='&nbsp';
												htmlInTB +='<div class="form-group">';
													htmlInTB += '<div class="info-button btn-show-evidence"><i class="fa fa-picture-o" style="font-size: 16px;" ></i><label class="evidence-picture-list hide">'+JSON.stringify(evidencePictureList)+'</label></div>'												
												htmlInTB +='</div>';
											}
											
										htmlInTB +='</div>';
									}else{
										htmlInTB +='<div class="form-inline">';
											htmlInTB +='<div class="form-group">';
												htmlInTB += '<div class="content_checklist">'+evalPlanObj.title+' : '+evalPlanObj.detail+'</div>'
											htmlInTB +='</div>';
											if(evidencePictureList != null && evidencePictureList.length > 0){
												htmlInTB +='&nbsp';
												htmlInTB +='<div class="form-group">';
													htmlInTB += '<div class="info-button btn-show-evidence"><i class="fa fa-picture-o" style="font-size: 16px;" ></i><label class="evidence-picture-list hide">'+JSON.stringify(evidencePictureList)+'</label></div>'												
												htmlInTB +='</div>';
											}
										htmlInTB +='</div>';
									}
								}else{
									htmlInTB += '<div class="content_checklist">'+evalPlanObj.title+' : '+evalPlanObj.detail+'</div>'
								}								
							}
														
							else{
								
								if(evalPlanObj.auditResultId.length > 0 && evalPlanObj.auditResultId != []){
									let note = "";
									$(evalPlanObj.auditResultId).each(function(i,v){
										if(v.note != ""){
											note += "<div class='row'>";
												note += "<div class='col-md-12'>";
													note += "<h4> -"+v.auditorId.fullname+"</h4>";
												note += "</div>";
											note += "</div>";
											
											note += "<div class='row'>";
												note += "<div class='col-md-12'>";
													note += "&nbsp;<span>"+v.note+"</span>";
												note += "</div>";
											note += "</div>";
											
										}										
									});
									if(note != ""){
										htmlInTB +='<div class="form-inline">';
											htmlInTB +='<div class="form-group">';
												htmlInTB += '<div class="content_checklist question_eval">'+evalPlanObj.detail+'</div>';
												htmlInTB += '<span class="evalPlanId hide">'+evalPlanObj.evalPlanId+'</span>';												
											htmlInTB +='</div>';
											htmlInTB +='&nbsp';
											htmlInTB +='<div class="form-group">';
												htmlInTB += '<div class="info-button" tabindex="0" data-trigger="focus" data-toggle="popover" data-placement="top" title="Note" data-content="'+note+'"><i class="fa fa-sticky-note-o" style="font-size: 16px;" ></i></div>'												
											htmlInTB +='</div>';
											if(evidencePictureList != null && evidencePictureList.length > 0){
												htmlInTB +='&nbsp';
												htmlInTB +='<div class="form-group">';
													htmlInTB += '<div class="info-button btn-show-evidence"><i class="fa fa-picture-o" style="font-size: 16px;" ></i><label class="evidence-picture-list hide">'+JSON.stringify(evidencePictureList)+'</label></div>'												
												htmlInTB +='</div>';
											}
										htmlInTB +='</div>';
									}else{
										htmlInTB +='<div class="form-inline">';
											htmlInTB +='<div class="form-group">';
												htmlInTB += '<div class="content_checklist question_eval">'+evalPlanObj.detail+'</div>';
												htmlInTB += '<span class="evalPlanId hide">'+evalPlanObj.evalPlanId+'</span>';
											htmlInTB +='</div>';
											if(evidencePictureList != null && evidencePictureList.length > 0){
												htmlInTB +='&nbsp';
												htmlInTB +='<div class="form-group">';
													htmlInTB += '<div class="info-button btn-show-evidence"><i class="fa fa-picture-o" style="font-size: 16px;" ></i><label class="evidence-picture-list hide">'+JSON.stringify(evidencePictureList)+'</label></div>'												
												htmlInTB +='</div>';
											}
										htmlInTB +='</div>';
									}
								}else{
									htmlInTB += '<div class="content_checklist question_eval">'+evalPlanObj.detail+'</div>';
									htmlInTB += '<span class="evalPlanId hide">'+evalPlanObj.evalPlanId+'</span>';
								}	
							}
							
							htmlInTB += '</div>';	
							htmlInTB += '</div>';
							htmlInTB += '</td>';
							if(evalPlanObj.evalTypeId.evalTypeId == '5'){
								if(numOfAuditor != 0){					
									
									$(evalPlanObj.auditResultId).each(function(i,v){	
										htmlInTB += '<td style="text-align: center;">';
										if(userGroupId == '2'){
											if(v.answerDetail != ""){
												if(numOfAuditor == 1){
													htmlInTB += '<span class="answerId hide">'+v.answerId.answerId+'</span>';
													htmlInTB += '<span class="answerName">'+v.answerDetail+'</span>';
													htmlInTB += '<span class="auditorId hide">'+v.auditorId.userId+'</span>';
												}else{
														
													htmlInTB += '<div class="form-sm-checkboxes">';
													htmlInTB += '<div class="sm-checkbox-list">';
													htmlInTB += '<div class="checkbox">';
													htmlInTB += '<label style="font-size: 16px;">';
													htmlInTB += '<input type="checkbox" class="answerId" value="'+v.answerId.answerId+'">';
													htmlInTB += '<span class="cr"><i class="cr-icon fa fa-check font-green"></i></span>';
													htmlInTB += '<span class="answerName">'+v.answerDetail+'</span>';
													htmlInTB += '<span class="auditorId hide">'+v.auditorId.userId+'</span>';
													htmlInTB += '</label>'
														htmlInTB += '</div>';
													htmlInTB += '</div>';
													htmlInTB += '</div>';
												}											
											}
										}else{											
											htmlInTB += '<span class="answerId hide">'+v.answerId.answerId+'</span>';
											htmlInTB += '<span class="answerName">'+v.answerDetail+'</span>';
											htmlInTB += '<span class="auditorId hide">'+v.auditorId.userId+'</span>';
										}
										
										htmlInTB += '</td>';									
										
									});
								}
								
							}
							htmlInTB += "</tr>";
							$('#table_audit_result').find('tbody').append($(htmlInTB));
							
							$('[data-toggle="popover"]').popover({
								 html: true,
								 template:
						                '<div class="popover">\
						            		<div class="arrow"></div>\
						            		<div class="popover-header">\
						            			\
						            			<h3 class="popover-title"></h3>\
						            		</div>\
						            		<div class="popover-content"></div>\
						            	</div>'
							});
						});
					}else{
						$('#table_audit_result').find('tbody').empty();
						let html = '<tr>';
						html += '<td colspan="'+numOfColumnInTable+'" style="text-align : center;">';
						html += 'ไม่พบข้อมูลผลการตรวจ';
						html += '</td>';
						html += '</tr>';
						$('#table_audit_result').find('tbody').append($(html));
					}
					
					
					$('.info-button').css('cursor', 'pointer');
					
					
				});
				
				
				/*$('.content_checklist').dotdotdot({
					callback: function( isTruncated ) {
						
						if(isTruncated){
							console.log($(this).closest('tr'));
							$(this).closest('tr').append($('<td width="5%"><div><i class="fa fa-info-circle" style="font-size:16px;"></i></div></td>'))
						
						}
					},
					ellipsis: '...',  //The HTML to add as ellipsis. 
					truncate: "letter", // How to cut off the text/html: 'word'/'letter'/'children' 
			        watch : 'window' // Whether to update the ellipsis: true/'window' 
				});*/
				
			});
		},
		loadCarDetail : function(){
			if(objChecklistPlan.checklistPlanStatusId.checklistPlanStatusId != "1" && objChecklistPlan.checklistPlanStatusId.checklistPlanStatusId != "2"){
				let url = contextPath+'/api/car_detail/car_detail_list/'+JSON.stringify({auditResultId : {checklistPlanId : {checklistPlanId : objChecklistPlan.checklistPlanId}}});
				ajaxProcess.submit(url, 'GET', null, '', (data)=>{
					let resultMessage = JSON.parse(data.message);
					if(resultMessage != null && resultMessage.length > 0){											
						$('#detail-car').show();						
					}
				});
			}
			
		}
	}
}();

