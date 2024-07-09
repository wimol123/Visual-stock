var checkAuditorDataTable = true;
var checklistTypeId = 1;
var answerListByChecklistType=[];
var objFormWizard = null, tableCondition = null;
var answerMappingTable = [];
var validateDialogEditNotQuestion = null;
var dataSourceQuestionTypeMapping;
$(window).keypress(function(event) {
    if (!(event.which == 115 && event.ctrlKey) && !(event.which == 19)){ return true;}  
    ConfirmModal.setupModal("warning","การยืนยัน","ยืนยันการบันทึกข้อมูลในหน้านี้ใช่หรือไม่ ?");
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			$( "#save-checklist" ).click();
		}
	});
    event.preventDefault();
    return false;
});
$(function(){
	genAnswer();	
	$.fn.bootstrapSwitch.defaults.size = 'normal';
	$.fn.bootstrapSwitch.defaults.onColor = 'success';
	$(".checkbox_active_status").bootstrapSwitch();
	$('#status_checklist').bootstrapSwitch('state',true);	
	//initialdataTable();
	genProductType();
	genGrade();	
	genSelectionQuestionType();
	
	
	//genSelectionTemplate();
	FormWizard.init();
	FormWizard.initChecklist();
	let objectValidate = {
		element : '#form-detail-checklist',
		require : ['checklist_title','select_product_type','scope_checklist','Criteria_tmp','table_condition_tmp','select_checklist_type', 'dayOfCarAccept'],
		message : ["กรุณาใส่ชื่อเช็คลิสต์","กรุณาเลือกประเภทสินค้า","กรุณาใส่ขอบข่ายการตรวจประเมิน","","","กรุณาเลือกประเภทของเช็คลิสต์", "กรุณาใส่จำนวนวันรับทราบใบ CARS"],
		maxLength : [100, null, 200, null, null, null, 11]
	};
	validate.init(objectValidate);
	objectValidate = {
		element : '#edit_Not_Question',
		require : ['title'],
		message : ["กรุณาใส่ข้อมูล"],
		maxLength : [100]
	};
	validateDialogEditNotQuestion = validate.init(objectValidate);
	
	objectValidate = {
		element : '#edit_Question',
		require : ['uniqueId-question'],
		message : ["กรุณาใส่ข้อมูล"],
		maxLength : [100]
	};
	validate.init(objectValidate);
	
	/*currentDate(function(dateCurrent){
		
		if(dateCurrent == null){
			dateCurrent = new Date();
		}
		let effectiveDateStart = dateCurrent;
		effectiveDateStart.setDate(effectiveDateStart.getDate() + 1);
		let effectiveDate = $('.input-group.datepicker-effective').datepicker({
			language: "th",
			format: 'dd/mm/yyyy',
			orientation: "bottom auto",
			autoclose: true,
			//startDate: effectiveDateStart
		});
		
		$('.input-group.datepicker-effective').datepicker('setDate', effectiveDateStart);	
		effectiveDate.on('changeDate', function(ev){
			let dateTmp = $('#expire_date').val().trim().toString().split("/");
			let expireDateTemp = new Date(dateTmp[2], dateTmp[1]-1, dateTmp[0]);
			if(ev.date >= expireDateTemp){
				let newDate = new Date(ev.date);		
				newDate.setDate(newDate.getDate() + 1);
				$('.input-group.datepicker-expire').datepicker('setStartDate',newDate);
				$('.input-group.datepicker-expire').datepicker('setDate', newDate);
			}		
		}).data('datepicker');
	});
	
	currentDate(function(dateCurrent){
		//let expireDateStart = new Date();
		if(dateCurrent == null){
			dateCurrent = new Date();
		}
		let expireDateStart = dateCurrent;
		expireDateStart.setDate(expireDateStart.getDate() + 2);
		
		$('.input-group.datepicker-expire').datepicker({
			language: "th",
			format: 'dd/mm/yyyy',
			orientation: "bottom auto",
			autoclose: true,
			//startDate: expireDateStart
		});
		if($('#checklist_id_receive').val() == ""){
			$('.input-group.datepicker-expire').datepicker('setStartDate',expireDateStart);
		}
		$('.input-group.datepicker-expire').datepicker('setDate', expireDateStart);
	});*/
	
	dateTime.currentDateTime('yyyy/MM/dd HH:mm:ss',(dateTimeCurrent)=>{
		let effectiveDateStart = new Date();
		effectiveDateStart.setDate(dateTimeCurrent.getDate() + 1);
		
		let expireDateStart = new Date();
		expireDateStart.setDate(dateTimeCurrent.getDate() + (365*2));
		
		let effectiveDate = $('.input-group.datepicker-effective').datepicker({
			language: "th",
			format: 'dd/mm/yyyy',
			orientation: "bottom auto",
			autoclose: true,
			//startDate: effectiveDateStart
		});
		
		$('.input-group.datepicker-effective').datepicker('setDate', effectiveDateStart);	
		effectiveDate.on('changeDate', function(ev){
			let dateTmp = $('#expire_date').val().trim().toString().split("/");
			let expireDateTemp = new Date(dateTmp[2], dateTmp[1]-1, dateTmp[0]);
			if(ev.date >= expireDateTemp){
				let newDate = new Date(ev.date);		
				newDate.setDate(newDate.getDate() + 1);
				$('.input-group.datepicker-expire').datepicker('setStartDate',newDate);
				$('.input-group.datepicker-expire').datepicker('setDate', newDate);
			}		
		}).data('datepicker');
		
		
		$('.input-group.datepicker-expire').datepicker({
			language: "th",
			format: 'dd/mm/yyyy',
			orientation: "bottom auto",
			autoclose: true,
			startDate: expireDateStart
		}).on('hide', function(e) {
			if($('#expire_date').val() == ""){
				$('#expire_date').val($('#date_expire_history').val().trim());
			}
		});
		$('.input-group.datepicker-expire').datepicker('setStartDate',expireDateStart);		
		$('.input-group.datepicker-expire').datepicker('setDate', expireDateStart);
		
	});
	
	
	
	$.validator.addMethod("checkTags", function(value) {    
        return ($("#audit_name").tagsinput('items').length > 0);
    },"กรุณาใส่รายชื่อทีมผู้ตรวจประเมิน");
	$.validator.addMethod("checkEditor", function(value) {    
        return (editor.getText('#Criteria') != '');
    },"กรุณาใส่เกณฑ์การให้คะแนน");
	$.validator.addMethod("checkTableCondition", function(value) {   		
        return ($('#table_condition').DataTable().rows().data().length != 0);
    },"กรุณาเพิ่มเงื่อนไขการอนุมัติผู้ขาย");
	/*$.validator.addMethod("checkInputCondition", function(value) {   
		let validateResult = true;		
		$('.number1').each(function(){
			console.log($(this));
			if($(this).val() == '')
				validateResult = false;
		});
		return validateResult;
       //return ($("#audit_name").tagsinput('items').length > 0);
    },"กรุณากรอกข้อมูลให้ครบถ้วน");*/
	$.validator.addMethod("classNumberRequired", $.validator.methods.required,
	"กรุณากรอกข้อมูลให้ครบถ้วน");
	$.validator.addMethod("classNumberCheckNum", $.validator.methods.number,
	"กรุณาใส่ตัวเลขเท่านั้น");
	$.validator.addMethod("classNumberCheckMoreThanZero", function(value, element){
		if(value >= 0){
			return true;
		}else{
			return false;
		}
	},"กรุณาใส่ตัวเลขมากกว่า 0");
	/*$.validator.addMethod("classNumberCheckBetween", function(value, element){
		let elementDivColumn = $(element).closest('.form-group').parent();
		if($(element).closest('.form-group').parent().next().length > 0){
			if(!elementDivColumn.next().children().hasClass('hide')){
				if(elementDivColumn.next().children().find('input').val() > value){
					return true;
				}else{
					return false;
				}
			}else{
				return true;
			}
		}else{
			if($(element).closest('.form-group').prev().find('input').val() < value){
				return true;
			}else{
				return false;
			}
		}
		//console.log($(element).closest('.form-group').parent().next().children().hasClass('hide'));
	   
	}, "กรุณากรอกข้อมูลให้ถูกต้อง"); */
	$.validator.addClassRules("number1", {
		classNumberRequired: true,
		classNumberCheckNum : true,
		//classNumberCheckBetween : true,
		classNumberCheckMoreThanZero : true
	});
	$.validator.addClassRules("number2", {
		classNumberRequired: true,
		classNumberCheckNum : true,
		//classNumberCheckBetween : true,
		classNumberCheckMoreThanZero : true
	});
	
	$.validator.addMethod("classQuestionTypeRequire", $.validator.methods.required,"กรุณาเลือกประเภทคำตอบ");
	$.validator.addClassRules("question_type_selection", {
		classQuestionTypeRequire : true		
	});
	$.validator.addClassRules("question_type_edit", {
		classQuestionTypeRequire : true		
	});
	
	$.validator.addMethod("classAnswerListRequire", function(value,element){
		return $('.checkbox-answer:checked').size() > 0;
	},"กรุณาเลือกประเภทคำตอบ");	
	
	$.validator.addClassRules("form-md-checkboxes",{
		classAnswerListRequire : true
	});
	$.validator.addClassRules('answerlist',{
		classAnswerListRequire : true
	});
	
	$( "#dayOfCarAccept" ).rules( "add", {
		number: true,
		digits : true,
		messages : {
			number : "กรุณาใส่ข้อมูลเป็นจำนวนเต็มเท่านั้น",
			digits : "กรุณาใส่ข้อมูลเป็นจำนวนเต็มเท่านั้น"
		}
	});
	
});


$('#dialog_search_auditor').on('shown.bs.modal', function (e){
	if(checkAuditorDataTable){
		loadDatableSearchAudit();
		$('#table_search_audit').DataTable().columns(3).search('2');
		$('#table_search_audit').DataTable().draw();
		checkAuditorDataTable = false;
	}
});

$('#btn-next').on('click', function(){
	if($('#form-detail-checklist').valid()){
		if(!$('#portlet-detail-checklist').find('.portlet-title').find('a[data-toggle="collapse"]').hasClass('collapsed')){
			$('#portlet-detail-checklist').find('.portlet-title').find('a[data-toggle="collapse"]').click();	
			if($('#portlet-detail-checklist').closest('.row').next().find('.portlet-title').find('a[data-toggle="collapse"]').hasClass('collapsed')){
				$('#portlet-detail-checklist').closest('.row').next().find('.portlet-title').find('a[data-toggle="collapse"]').click();
			}
		}
	}	
});


$('#btn-prev').on('click',function(){	
	if(!$('#portlet-checklist-section').find('.portlet-title').find('a[data-toggle="collapse"]').hasClass('collapsed')){
		$('#portlet-checklist-section').find('.portlet-title').find('a[data-toggle="collapse"]').click();
		if($('#portlet-checklist-section').closest('.row').prev().find('.portlet-title').find('a[data-toggle="collapse"]').hasClass('collapsed')){
			$('#portlet-checklist-section').closest('.row').prev().find('.portlet-title').find('a[data-toggle="collapse"]').click();
		}
	}
});

$('#save-checklist').on('click',function(){
	BlockUi.blockPage();
	if($('#portlet-detail-checklist').find('a[data-toggle="collapse"]').hasClass('collapsed')){
		$('#portlet-detail-checklist').find('a[data-toggle="collapse"]').removeClass('collapsed');
	}
	if($('#portlet-checklist-section').find('a[data-toggle="collapse"]').hasClass('collapsed')){
		$('#portlet-checklist-section').find('a[data-toggle="collapse"]').removeClass('collapsed');
	}
	collpseAndExpand.init();
	let arrayEvalForm = checklist.getChecklistDisplay();
	
	if (typeof arrayEvalForm === 'undefined' || arrayEvalForm.length == 0 || arrayEvalForm == null) {
		//customMessageDialog('warning', 'เตือนการตรวจสอบข้อมูล', 'กรุณาสร้างข้อตรวจรายการเช็คลิสต์', null);
		BlockUi.unBlockPage();
		return false;
	}
	if($('#form-detail-checklist').valid()){		
		
		let checklistId = $('#checklist_id').val().trim();
		let checklistTitle = $('#checklist_title').val().trim();
		let checklist_Type_Id = checklistTypeId;
		let productTypeId = $('#select_product_type').val().trim();
		let checklistScope = $('#scope_checklist').val().trim();
		let scoringCriteria = editor.getText('#Criteria').trim();
		let description = $('#description').val().trim();
		let effective_date = $('#effective_date').val().trim();
		let expire_date = $('#expire_date').val().trim();
		let dayOfCarAccept = $('#dayOfCarAccept').val().trim();
		let enable = 'N';
		if($('#status_checklist').prop('checked')){
			enable = 'Y';
		}
		
		let dataCondition = [];
		$('#table_condition').DataTable().rows().every( function ( rowIdx, tableLoop, rowLoop ) {
			let data = this.data();
			let conditionArray = [];
			$(answerMappingTable).each(function(i,objAnswer){
				if(data[objAnswer.indexOfColumnInTable] != ''){
					let oCondition = getCondition(data[objAnswer.indexOfColumnInTable].trim());
					oCondition['result_type'] = objAnswer.answerId;
					conditionArray.push(oCondition);
				}
			});
			dataCondition.push({
		    	grade : data[0].toString().trim(),
		    	condition : conditionArray,
		    	corrective_action : data[answerMappingTable.length+1].toString().trim()
		    });
		});
		
		let objChecklist = {
			checklistId : checklistId,
			checklistTitle : checklistTitle,
			checklistTypeId : {
				checklistTypeId : checklist_Type_Id.toString()
			},
			productTypeId : {
				productTypeId : productTypeId
			},
			checklistScope : checklistScope,
			scoringCriteria : scoringCriteria,
			approveSupplierRule : JSON.stringify(dataCondition),
			description : description,
			noOfCarAcceptDay : dayOfCarAccept,
			effectiveDate : effective_date,
			expireDate : expire_date,
			enable : enable,
			evalFormId : arrayEvalForm
		};
		
		
		if(objChecklist.checklistId == ''){
			let url = contextPath + "/api/checklist/insert_checklist"
			ajaxProcess.submit(url, "POST", objChecklist, null, function(data){			
				if(data.result){
					BlockUi.blockPage('warning');
					setTimeout(() => {
						BlockUi.blockPage('warning');
						window.location.href = contextPath+"/checklist.jsp";
					},500);				
				}
			});
		}else{
			console.log(JSON.stringify(objChecklist));
			let url = contextPath + "/api/checklist/update_checklist"
			ajaxProcess.submit(url, "POST", objChecklist, null, function(data){			
				if(data.result){
					BlockUi.blockPage('warning');
					setTimeout(() => {
						BlockUi.blockPage('warning');
						window.location.href = contextPath+"/checklist.jsp";
					},500);				
				}
			},{headers : {"Content-Encoding" : "compress"}});
		}
		
	}else{
		BlockUi.unBlockPage();
	}	
});

function loadEvalForm(){
	if($('#checklist_id_receive').val() != ""){
		let dataSubmit = {checklistId : $('#checklist_id_receive').val().trim()};
		let url = contextPath+'/api/checklist/checklist_list/'+JSON.stringify(dataSubmit);
		ajaxProcess.submit(url, 'GET', null, "#portlet-detail-checklist", (data) => {
			if(data.result){
				let arrayChecklist = JSON.parse(data.message);
				$(arrayChecklist).each(function(i,objChecklist){
					
					$('#checklist_title').val(objChecklist.checklistTitle);
					$('#checklist_id').val(objChecklist.checklistId);
					$('#scope_checklist').val(objChecklist.checklistScope);
					$('#description').val(objChecklist.description);
					$('#dayOfCarAccept').val(objChecklist.noOfCarAcceptDay);
					editor.setText('#Criteria',objChecklist.scoringCriteria);
					if(objChecklist.enable == 'N'){
						$('#status_checklist').bootstrapSwitch('state',false);
					}
					let expireDateString = objChecklist.expireDate.split(" ")[0].split("/");
					let effectiveDateString = objChecklist.effectiveDate.split(" ")[0].split("/");
					let expireDateNewFormat = expireDateString[1]+"/"+expireDateString[0]+"/"+expireDateString[2];
					let effectiveDateNewFormat = effectiveDateString[1]+"/"+effectiveDateString[0]+"/"+effectiveDateString[2];
					
					$('.input-group.datepicker-expire').datepicker('setDate', new Date(expireDateNewFormat));
					$('.input-group.datepicker-effective').datepicker('setDate', new Date(effectiveDateNewFormat));
					$('#expire_date').val(objChecklist.expireDate.split(" ")[0]);
					$('#date_expire_history').val(objChecklist.expireDate.split(" ")[0]);
					
					$('#select_product_type').val(parseInt(objChecklist.productTypeId.productTypeId));
				    $('#select_product_type').trigger('change');
					let arrayApproveSupplierRule = JSON.parse(objChecklist.approveSupplierRule);
					
					let btnRemove = '<button type="button" class = "removeCondition btn btn-default btn-sm">';
					btnRemove += '<i class="icon-trash"></i> ลบเงื่อนไข </button>';
					$(arrayApproveSupplierRule).each(function(i,valueApproveSupplierRule){
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
						arrayPushTable.push(btnRemove);
						$('#table_condition').dataTable().fnAddData(arrayPushTable);
					});
					
					
				});
				
				if($('#checklist_id').val() != ''){
					let url = contextPath+'/api/eval_form/eval_form_list/'+JSON.stringify({checklistId : {checklistId : $('#checklist_id').val().trim()}});
					ajaxProcess.submit(url, 'GET', null, '#portlet-checklist-section', (data) => {
						if(data.result){
							let checklistObj = JSON.parse(data.message);
							if(checklistObj.checklistToFrontEnd.length > 0){
								$(checklistObj.checklistToFrontEnd).each(function(i,v){
									checklist.generateElements(v); 
								});
							}else{
								
							}
						}						
						
					});
				}
			}
		});
	}
	else{
		
		
	}
}

var FormWizard = function() {
	return{
		init : function(){
			function e(e) {
				
			}
			if(jQuery().bootstrapWizard){				
				var r = $("#form_body_rule"),
                t = $(".alert-danger", r),
                i = $(".alert-success", r);
				r.validate({
					doNotHideMessage: !0,
                    errorElement: "span",
                    errorClass: "help-block help-block-error",
                    focusInvalid: !1,
                    rules: {
                    	grade_list : {
                    		required: !0
                    	},
                    	CorrectiveAction : {
                    		required: !0
                    	}
                    },
                    messages : {
                    	grade_list : {
                    		required: "กรุณาเลือกเกรด"
                    	},
                    	CorrectiveAction : {
                    		required: "กรุณาใส่คำอธิบายเงื่อนไข"
                    	}
                    },
                    invalidHandler: function(e, r) {
                        i.hide(), t.show(), App.scrollTo(t, -200)
                    },
                    highlight: function(e) {
                        $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
                    },
                    unhighlight: function(e) {
                        $(e).closest(".form-group").removeClass("has-error")
                    },
                    submitHandler: function(e) {
                        i.show(), t.hide(), e[0].submit()
                    }
				});						
				var o = function(e, r, t) {
                    var i = r.find("li").length,
                    o = t + 1;
                    $(".step-title", $("#form_wizard_rule")).text("ขั้นตอนที่ " + (t + 1) + " จาก " + i), jQuery("li", $("#form_wizard_rule")).removeClass("done");
                    for (var n = r.find("li"), s = 0; s < t; s++) jQuery(n[s]).addClass("done");                    
                    1 == o ? $("#form_wizard_rule").find(".button-previous").hide() : $("#form_wizard_rule").find(".button-previous").show(), o >= i ? ($("#form_wizard_rule").find(".button-next").hide(), $("#form_wizard_rule").find(".button-submit").show()) : ($("#form_wizard_rule").find(".button-next").show(), $("#form_wizard_rule").find(".button-submit").hide())/*, App.scrollTo($("body"))*/
				};	
					
				
				/*$('.number1').rules( "add", {
					  checkInputCondition : true
				});*/
				
				
				$("#form_wizard_rule").bootstrapWizard({
                    'nextSelector': ".button-next",
                    'previousSelector': ".button-previous",                   
                    onTabClick: function(e, r, t, i) {                    	
                        return !1
                    },
                    onNext: function(e, a, n) {                          	
                        return i.hide(), t.hide(), 0 != r.valid() && void o(e, a, n)                        
                    },
                    onPrevious: function(e, r, a) {
                        i.hide(), t.hide(), o(e, r, a)
                    },
                    onTabShow: function(e, r, t) {
                        var i = r.find("li").length,
                            a = t + 1,
                            o = a / i * 100;
                        $("#form_wizard_rule").find(".progress-bar").css({
                            width: o + "%"
                        });
                        if(o == 100){
                        	$("#form_wizard_rule").find(".progress-bar").addClass('progress-bar-success');
                        	$("#form_wizard_rule").find(".progress-bar").removeClass('progress-bar-warning');                        	
                        }else{
                        	$("#form_wizard_rule").find(".progress-bar").removeClass('progress-bar-success');
                        	$("#form_wizard_rule").find(".progress-bar").addClass('progress-bar-warning'); 
                        }                       
                    }
                }), 
                $("#form_wizard_rule").find(".button-previous").hide(), 
                $("#form_wizard_rule .button-submit").click(function() {
                	if(r.valid()){
                		submitAction();  
                    	$('#dialog_add_template_checklist').modal('hide');
                	}                	
                }).hide()
			}
		},
		initChecklist : function(){
			
			if(jQuery().bootstrapWizard){				
				var r = $("#form_process"),
                t = $(".alert-danger", r),
                i = $(".alert-success", r);
				r.validate({
					doNotHideMessage: !0,
                    errorElement: "span",
                    errorClass: "help-block help-block-error",
                    focusInvalid: !1,
                    rules: {
                    	name_topic : {
                    		required: !0,
                    		maxlength : 100
                    	},
                    	name_sub_topic : {
                    		maxlength : 100
                    	},
                    	name_group_question : {
                    		required: !0,
                    		maxlength : 100
                    	},
                    	question : {
                    		required: !0,
                    		maxlength : 100
                    	}
                    },
                    messages : {
                    	name_topic : {
                    		required: "กรุณาใส่ชื่อหัวข้อ",
                    		maxlength : jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร")
                    	},
                    	name_sub_topic : {
                    		required: "กรุณาใส่ชื่อหัวข้อย่อย",
                    		maxlength : jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร")
                    	},
                    	name_group_question : {
                    		required: "กรุณาใส่ชื่อกลุ่มคำถาม",
                    		maxlength : jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร")
                    	}
                    },
                    invalidHandler: function(e, r) {
                        i.hide(), t.show(), App.scrollTo(t, -200)
                    },
                    highlight: function(e) {
                        $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
                    },
                    unhighlight: function(e) {
                        $(e).closest(".form-group").removeClass("has-error")
                    },
                    submitHandler: function(e) {
                        i.show(), t.hide(), e[0].submit()
                    }
				});						
				var o = function(e, r, t) {
                    var i = r.find("li").length,
                    o = t + 1;
                    $(".step-title", $("#form_wizard")).text("ขั้นตอนที่ " + (t + 1) + " จาก " + i), jQuery("li", $("#form_wizard")).removeClass("done");
                    for (var n = r.find("li"), s = 0; s < t; s++) jQuery(n[s]).addClass("done");                    
                    1 == o ? $("#form_wizard").find(".button-previous-step").hide() : $("#form_wizard").find(".button-previous-step").show(), o >= i ? ($("#form_wizard").find(".button-next-step").hide(), $("#form_wizard").find(".button-confirm").show()) : ($("#form_wizard").find(".button-next-step").show(), $("#form_wizard").find(".button-confirm").hide()), App.scrollTo($("body"))
				};	
					
								
				$("#form_wizard").bootstrapWizard({
                    'nextSelector': ".button-next-step",
                    'previousSelector': ".button-previous-step",                   
                    onTabClick: function(e, r, t, i) {                    	
                        return false;
                    },
                    onNext: function(e, a, n) { 
                    	$('.question_th').closest('.form-group').removeClass('has-error');
                    	if(n == 4){
                    		if($(".question_th").Editor("getText").trim('<br>') == ''){
                    			t.show();
                    			customMessageDialog("error", "ข้อความ", 'กรุณาใส่คำถามอย่างน้อย 1 คำถาม', null);
                    			$('.question_th').closest('.form-group').addClass('has-error');                    			
                    			return false;
                    		}
                    		if(!$('.answerId:checked').size() > 0){
                    			t.show();
                    			customMessageDialog("error", "ข้อความ", 'กรุณาเลือกคำตอบอย่างน้อย 1 คำตอบ', null);
                    			$('.answerId').closest('.form-group').addClass('has-error');
                    			return false;
                    		}else{
                    			if($('.answerId').closest('.form-group').hasClass('has-error'))
                    				$('.answerId').closest('.form-group').removeClass('has-error')
                    		}
                    	}
                        return i.hide(), t.hide(), 0 != r.valid() && void o(e, a, n)                        
                    },
                    onPrevious: function(e, r, a) {
                        i.hide(), t.hide(), o(e, r, a)
                    },
                    onTabShow: function(e, r, t) {
                        var i = r.find("li").length,
                            a = t + 1,
                            o = a / i * 100;
                        $("#form_wizard").find(".progress-bar").css({
                            width: o + "%"
                        });
                        if(o == 100){
                        	$("#form_wizard").find(".progress-bar").addClass('progress-bar-success');
                        	$("#form_wizard").find(".progress-bar").removeClass('progress-bar-warning');                        	
                        }else{
                        	$("#form_wizard").find(".progress-bar").removeClass('progress-bar-success');
                        	$("#form_wizard").find(".progress-bar").addClass('progress-bar-warning'); 
                        }
                        if($('#tabSummary').hasClass('active')){
                        	loadDataInFormWizard();                        	
                        }
                    }
                }), 
                $("#form_wizard").find(".button-previous-step").hide(), 
                $("#form_wizard .button-confirm").click(function() {                	
                	checklist.generateElements(objFormWizard);  
                	//submitToDB(objFormWizard);
                	$('#dialog_add_checklist').modal('hide');
                }).hide()
			}
		}
	}
}();


function loadDataInFormWizard(){	
	var id = 1;
	BlockUi.blockPosition('#dialog_add_checklist');
	//var nameTemplate = $('#name_template').val().trim();
	//var descriptionTemplate = '';
	//var uniqueTemplate = generate.randomByDate() + id++;
	var nameTopic = $('#name_topic').val().trim();
	var descriptionTopic = $('#description_topic').val().trim();
	var uniqueTopic = generate.randomByDate() + id++;
	var nameSubTopic = $('#name_sub_topic').val().trim();
	var descriptionSubTopic = $('#description_sub_topic').val().trim();
	var uniqueSubTopic = generate.randomByDate() + id++;
	var nameGroupQuestion = $('#name_group_question').val().trim();
	var descriptionGroupQuestion = $('#description_group_question').val().trim();	
	var uniqueGroupQuestion = generate.randomByDate() + id++;
	var questionDetail = [];
	
	$('.question_th').each(function(index,value){
		let val = editor.getText($(this)).trim();
		if(val !=''){
			let questionTypeId = $(this).closest('[data-repeater-item]').find('.question_type_selection ').val();
			let questionTypeName = $(this).closest('[data-repeater-item]').find('.question_type_selection option:selected').text().trim();
			if(questionTypeId != ''){
				if($(this).closest('[data-repeater-item]').find('.answerId:checked').size() > 0){
					let uniqueQuestion = generate.randomByDate() + id++;
					let objQuestion = {
						detail : val,
						uniqueId : uniqueQuestion,
						questionTypeId : {
							questionTypeId : questionTypeId,
							name : questionTypeName
						}
					};
					let arrayAnswer =[];
					$($(this).closest('[data-repeater-item]').find('.answerId:checked')).each(function(i,element){
						arrayAnswer.push({
							answerId : $(element).val(),
							answerDetail : $(element).parent().find('.answerName').text()
						});
					});
					objQuestion['answerId'] = arrayAnswer;
					questionDetail.push(objQuestion);
				}
			}
			/*let uniqueQuestion = generate.randomByDate() + id++;
			questionDetail.push({
				name : val,
				uniqueId : uniqueQuestion
			});*/
		}
			
	});
	
	if(questionDetail.length <= 0){
		$("#form_wizard").find(".button-previous-step").click();
	}
	
	objFormWizard = {
		//template : {name : nameTemplate, description : descriptionTemplate, uniqueId : uniqueTemplate},
		topic : {title : nameTopic , detail : descriptionTopic, uniqueId : uniqueTopic},
		subTopic : {title : nameSubTopic, detail : descriptionSubTopic, uniqueId : uniqueSubTopic},
		groupQuestion : {title : nameGroupQuestion, detail : descriptionGroupQuestion, uniqueId : uniqueGroupQuestion},
		question : questionDetail
	};
	
	$('#name_topic_summary').text('');
	$('#description_topic_summary').text('');
	$('#name_sub_topic_summary').text('');
	$('#description_sub_topic_summary').text('');
	$('#name_group_question_summary').text('');
	$('#description_group_question_summary').text('');
	
	
	$('#name_topic_summary').text(objFormWizard.topic.title);
	if(objFormWizard.topic.detail != ''){
		$('#description_topic_summary').text(objFormWizard.topic.detail);
	}
			
	$('#name_sub_topic_summary').text(objFormWizard.subTopic.title);
	if(objFormWizard.subTopic.detail != '')
		$('#description_sub_topic_summary').text(objFormWizard.subTopic.detail);
	
	$('#name_group_question_summary').text(objFormWizard.groupQuestion.title);
	if(objFormWizard.groupQuestion.detail != '')
		$('#description_group_question_summary').text(objFormWizard.groupQuestion.detail);
	
	$('.question-section').empty();
	$.each(objFormWizard.question,function(i,v){
		let uniqueTmp = generate.randomByDate() + id++;
		
		let elementQuestion = $('.question-section')
			.append($('<div class="mt-element-list">')
					.append($('<div class="mt-list-head list-todo blue-soft">')
							.append($('<div class="list-head-title-container">')
									.append($('<h4 class="list-title">'+v.detail+'</h4>'))
									)
							)
					.append($('<div class="mt-list-container list-todo" id="'+v.uniqueId+'" role="tablist" aria-multiselectable="true">')
							.append($('<div class="list-todo-line"></div>'))
							.append($('<ul>')
									.append($('<li class="mt-list-item">')
											.append($('<div class="list-todo-item grey">')
													.append($('<a class="list-toggle-container" data-toggle="collapse" data-parent="#'+v.uniqueId+'" onclick=" " href="#'+uniqueTmp+'" aria-expanded="false">')
															.append($('<div class="list-toggle done uppercase">')
																	.append($('<div class="list-toggle-title bold">'+v.questionTypeId.name+'</div>'))
																	.append($('<div class="badge badge-default pull-right bold">'+v.answerId.length+'</div>'))
																	)
															)
													.append($('<div class="task-list panel-collapse collapse in" id="'+uniqueTmp+'">')
															.append($('<ul class="listAnswer">'))
															)																										
													
													)
											)
									)
							)
				);
		
		let answerSummary = "";
		$(v.answerId).each(function(index,object){
			
			answerSummary += object.answerDetail;
			if(index >= 0 && index < v.answerId.length-1){
				answerSummary += ", ";
			}
			
		});
		
		$(elementQuestion).find('#'+uniqueTmp).find('.listAnswer')
		.append($('<li class="task-list-item done">')
				.append($('<div class="task-content">')
						.append($('<h4 class="uppercase bold">')
								.append($('<a href="javascript:;">'+answerSummary+'</a>'))
								)
					)
		)
		
	});
	/*
	$.each(objFormWizard.question,function(index,value){
		$('.question-section').append($('<div class="list-group-item">')
								.append($('<h4 class="list-group-item-heading" style="white-space: pre-wrap;">'+value.name+'</h4>'))
								.append($('<p class="list-group-item-text" style="white-space: pre-wrap;">'+objFormWizard.question.nameEN[index]+'</p>'))
							  );
	});*/

	setTimeout(function(){
		BlockUi.unBlockPosition('#dialog_add_checklist');
	},500);
	
}

$('#dialog_add_condition').on('hidden.bs.modal', function () {
	$("#form_wizard_rule").bootstrapWizard('first');
	$("#form_wizard_rule").find(".button-previous").hide();
	$("#form_wizard_rule").find(".button-next").show(); 
	$("#form_wizard_rule").find(".button-submit").hide();
	$(".step-title", $("#form_wizard_rule")).text("ขั้นตอนที่ 1 จาก 2"), jQuery("li", $("#form_wizard_rule")).removeClass("done");	
	FormRepeater.clearAll();
	$('#grade_list').find('option:eq(0)').prop('selected', true);
	$('.operator_list').find('option:eq(0)').prop('selected', true);
	$('#CorrectiveAction').val('');
	$('#grade_list').empty();
	$('.alert-danger').hide();
	genGrade();
});


$('#dialog_add_checklist').on('hidden.bs.modal', function () {
	$("#form_wizard").bootstrapWizard('first');
	$("#form_wizard").find(".button-previous-step").hide();
	$("#form_wizard").find(".button-next-step").show(); 
	$("#form_wizard").find(".button-confirm").hide();
	$(".step-title", $("#form_wizard")).text("ขั้นตอนที่ 1 จาก 5"), jQuery("li", $("#form_wizard")).removeClass("done");	
	$('#name_topic').val('');
	$('#description_topic').val('');
	$('#name_sub_topic').val('');
	$('#description_sub_topic').val('');
	$('#name_group_question').val('');
	$('#description_group_question').val('');
	
	FormRepeater.clearAll();
});

$('#table_search_audit').on('click','.btn-select-audit',function(){
	var data = $('#table_search_audit').DataTable().row( $(this).parents('tr') ).data();	
	var auditName = data.fullName.trim();
	var auditId = data.userId.trim();
	$('#audit_name').tagsinput('add', { "value": auditId , "text": auditName});
	$('#dialog_search_auditor').modal('toggle');
});

$('#dialog_add_condition').on('change','.operator_list',function(){
	let operation = ($(this).find('option:selected').html()).toString();	
	let elementInput = $(this).closest('div').next().next().children();
	if(operation == "-"){		
		if(elementInput.hasClass('hide')){
			$(elementInput).hide().removeClass("hide").slideDown('3000');			
		}
	}else{
		if(!elementInput.hasClass('hide')){
			$(elementInput).slideUp('3000',function(){
				$(elementInput).addClass("hide");
			});
		}		
	}
});


$(document).on('click','.edit-topic', function(){
	let topicTitleAndDetail = $(this).closest('.portlet-title').find('.name-topic-section').text().trim();
	let uniqueId = $(this).closest('.actions').find('.uniqueTopic').text().trim();
	$('#dialog_edit_Not_Question .modal-title').text("แก้ไขหัวข้อ");
	$('.label-title').text('ชื่อหัวข้อ :');
	$('.label-detail').text("รายละเอียดหัวข้อ :");
	$('#title').val(topicTitleAndDetail.split(":")[0].trim());
	$('#detail').val(topicTitleAndDetail.split(":")[1].trim());
	$('#uniqueId').val(uniqueId);
	$('#evalType').val('.name-topic-section');
	$('#dialog_edit_Not_Question').modal('show');
});

$(document).on('click','.edit-sub-topic', function(){
	let subTopicTitleAndDetail = $(this).closest('.form-group').find('.name-sub-topic').text().trim();
	let uniqueId = $(this).closest('.form-section-right').find('.uniqueSubTopic').text().trim();
	$('#dialog_edit_Not_Question .modal-title').text("แก้ไขหัวข้อย่อย");
	$('.label-title').text('ชื่อหัวข้อย่อย :');
	$('.label-detail').text("รายละเอียดหัวข้อย่อย :");
	$('#title').val(subTopicTitleAndDetail.split(":")[0].trim());
	$('#detail').val(subTopicTitleAndDetail.split(":")[1].trim());
	$('#uniqueId').val(uniqueId);
	$('#evalType').val('.name-sub-topic');
	$('#dialog_edit_Not_Question').modal('show');
});

$(document).on('click','.edit-group-question',function(){
	let groupQuestionTitleAndDetail = $(this).closest('.form-group').find('.name-group-question').text().trim();
	let uniqueId = $(this).closest('.form-section-right').find('.uniqueGroupQuestion').text().trim();
	$('#dialog_edit_Not_Question .modal-title').text("แก้ไขกลุ่มคำถาม");
	$('.label-title').text('ชื่อกลุ่มคำถาม :');
	$('.label-detail').text("รายละเอียดกลุ่มคำถาม :");
	$('#title').val(groupQuestionTitleAndDetail.split(":")[0].trim());
	$('#detail').val(groupQuestionTitleAndDetail.split(":")[1].trim());
	$('#uniqueId').val(uniqueId);
	$('#evalType').val('.name-group-question');
	$('#dialog_edit_Not_Question').modal('show');
});

$(document).on('click','.edit-question',function(){
	let question = ($(this).closest('.form-group').find('.question-name').html().trim());
	let uniqueId = $(this).closest('.form-section-right').find('.uniqueQuestion').text().trim();
	let questionTypeId = $(this).closest('.form-group').find('.form-body-collapse').find('.questionTypeId').text().trim();
	let arrayItemAnswerId = ($(this).closest('.form-group').find('.form-body-collapse').find('.m-list-timeline__items').find('.answerId'));
	
	editor.setText('#detail-question',question);
	$('#uniqueId-question').val(uniqueId);
	$('.question_type_edit').val(questionTypeId).trigger('change');
	setTimeout(()=>{
		
		$($('#dialog_edit_Question').find('.answerId')).each(function(i,elementCheckbox){
			$(arrayItemAnswerId).each(function(){
				if(elementCheckbox.value == $(this).text()){
					$(elementCheckbox).prop('checked', true);
					return false;
				}
			});			
		});
		$('#dialog_edit_Question').modal('show');
	},300);
	
});


$(document).on('click','.add-sub-topic',function(){
	genFormWizardFromElement.addElement($(this));
	$('#dialog_add_checklist').modal('show');
});

$(document).on('click','.add-group-question',function(){
	genFormWizardFromElement.addElement($(this));
	$('#dialog_add_checklist').modal('show');
});

$(document).on('click','.add-question',function(){
	genFormWizardFromElement.addElement($(this));
	$('#dialog_add_checklist').modal('show');
});


$(document).on('click','.remove-topic',function(){	
	let objBtnRemove = $(this);
	ConfirmModal.setupModal("warning","การยืนยัน","ยืนยันการลบหัวข้อนี้ใช่หรือไม่ ?");
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			if($('#checklist_id').val() != ""){
				objBtnRemove.closest('.row').slideUp('slow', function() { $(this).remove(); });
			}else{
				objBtnRemove.closest('.row').slideUp('slow', function() { $(this).remove(); });
			}
			/*$.each(objBtnRemove.closest('.portlet-title').next().find('.sub-topic-section').find('.form-group'),function(){
				if($(this).find('.uniqueSubTopic').length > 0){
					deleteElement($(this).find('.uniqueSubTopic').text(),null);
				}else if($(this).find('.uniqueGroupQuestion').length > 0){
					deleteElement($(this).find('.uniqueGroupQuestion').text(),null);
				}else if($(this).find('.uniqueQuestion').length > 0){
					$.each($(this).find('.uniqueQuestion'),function(){
						deleteElement($(this).text(),null);
					});
				}
			});*/
			//deleteElement(objBtnRemove.closest('.actions').find('.uniqueTopic').text(),objBtnRemove.closest('.row'));
		}
	});
});


$(document).on('click','.remove-sub-topic',function(){
	let objBtnRemove = $(this);
	ConfirmModal.setupModal("warning","การยืนยัน","ยืนยันการลบหัวข้อย่อยนี้ใช่หรือไม่ ?");
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			if($('#checklist_id').val() != ""){
				objBtnRemove.closest('.form-section-right').closest('.form-group').slideUp('slow', function() { $(this).remove(); });
			}else{
				objBtnRemove.closest('.form-section-right').closest('.form-group').slideUp('slow', function() { $(this).remove(); });
			}
		}
	});
});

$(document).on('click','.remove-group-question',function(){
	let objBtnRemove = $(this);
	ConfirmModal.setupModal("warning","การยืนยัน","ยืนยันการลบกลุ่มคำถามนี้ใช่หรือไม่ ?");
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			if($('#checklist_id').val() != ""){
				objBtnRemove.closest('.form-section-right').closest('.form-group').slideUp('slow', function() { $(this).remove(); });
			}else{
				objBtnRemove.closest('.form-section-right').closest('.form-group').slideUp('slow', function() { $(this).remove(); });
			}
		}
	});
});

$(document).on('click','.remove-question',function(){
	let objBtnRemove = $(this);
	ConfirmModal.setupModal("warning","การยืนยัน","ยืนยันการลบคำถามนี้ใช่หรือไม่ ?");
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			if($('#checklist_id').val() != ""){
				objBtnRemove.closest('.form-section-right').closest('.form-group').closest('.question-list').slideUp('slow', function() { $(this).remove(); });
			}else{
				objBtnRemove.closest('.form-section-right').closest('.form-group').closest('.question-list').slideUp('slow', function() { $(this).remove(); });
			}
		}
	});
});

$('#submit_dialog_edit_Not_Question').on('click',function(){
	if($('#edit_Not_Question').valid()){
		if($('#evalType').val() == '.name-topic-section'){
			$('.portlet-summary p:contains("'+$('#uniqueId').val().trim()+'")').closest('.portlet-title').find($('#evalType').val().trim()).text($('#title').val().trim()+":"+$('#detail').val().trim());
		}else{
			$('.portlet-summary p:contains("'+$('#uniqueId').val().trim()+'")').closest('.form-group').find($('#evalType').val().trim()).text($('#title').val().trim()+":"+$('#detail').val().trim());
		}
		$('#dialog_edit_Not_Question').modal('hide');
		/*console.log();*/
		/*if($('#checklist_id').val() != ''){
			let url = contextPath + "/api/eval_form/update_eval_form_by_uniqueId/";
			let objEvalForm = {
				title : $('#title').val().trim(),
				detail : $('#detail').val().trim(),
				uniqueId : $('#uniqueId').val().trim()
			};
			ajaxProcess.submit(url, 'POST', objEvalForm, '#dialog_edit_Not_Question', (dataResponse)=>{
				if(dataResponse.result){
					let url = contextPath+'/api/eval_form/eval_form_list/'+JSON.stringify({checklistId : {checklistId : $('#checklist_id').val().trim()}});
					ajaxProcess.submit(url, 'GET', null, '#portlet-checklist-section', (data) => {
						if(data.result){
							let checklistObj = JSON.parse(data.message);
							if(checklistObj.checklistToFrontEnd.length > 0){
								$('.portlet-summary').remove();
								$(checklistObj.checklistToFrontEnd).each(function(i,v){								
									checklist.generateElements(v); 
								});
							}else{
								
							}
						}						
						
					});
					$('#dialog_edit_Not_Question').modal('hide');
				}
			});
		}else{
			console.log($('.portlet-summary p:contains("'+$('#uniqueId').val().trim()+'")'));
		}	*/	
	}
});


$('#submit_dialog_edit_Question').on('click',function(){
	let messageAlert = '';
	let questionTemp = editor.getText('#detail-question').replace(/(&nbsp;)*/g,"");
	let questionDetail = questionTemp.replace(/(<br>)*/g,"");
	
	if($('.answerId:checked').size() == 0){
		messageAlert += '-กรุณาเลือกคำตอบอย่างน้อย 1 คำตอบ</br>';
	}
	if(questionDetail == ''){
		messageAlert += '-กรุณาใส่คำถาม</br>';
	}
	if(messageAlert != ''){
		customMessageDialog('warning','ตรวจสอบข้อมูล','กรุณาตรวจสอบข้อมูล</br>'+messageAlert,null);
		return false;
	}
	
	
	let answerList = '';	
	$($('#dialog_edit_Question').find('.md-checkbox-list').find('.answerId')).each(function(){
		if($(this).prop('checked')){			
			answerList += '<div class="col-md-3">';
			answerList += '<div class="m-list-timeline__item item_answer">';
			answerList += '<span class="m-list-timeline__badge m-list-timeline__badge--'+colorListTimeline[$(this).parent().find('.answerName').text().trim()]+'"></span>';
			answerList += '<span class="m-list-timeline__text answerName">'+$(this).parent().find('.answerName').text().trim()+'</span>';
			answerList += '<span class="m-list-timeline__time answerId hide">'+$(this).val()+'</span>';
			answerList += '</div>';
			answerList += '</div>';			
		}
	});
	let questionType = {questionTypeId : $('.question_type_edit').val()};
	$('.portlet-summary p:contains("'+$('#uniqueId-question').val().trim()+'")').closest('.form-group').find('.question-name').html(questionDetail);
	$('.portlet-summary p:contains("'+$('#uniqueId-question').val().trim()+'")').closest('.form-group').find('.form-body-collapse').find('.questionTypeId').prev().text($('.question_type_edit option:selected').text());
	$('.portlet-summary p:contains("'+$('#uniqueId-question').val().trim()+'")').closest('.form-group').find('.form-body-collapse').find('.questionTypeId').text(questionType.questionTypeId);	
	$('.portlet-summary p:contains("'+$('#uniqueId-question').val().trim()+'")').closest('li.question-list').find('.form-body-collapse').find('.m-list-timeline__items').find('.row').empty().append(answerList);
	$('#dialog_edit_Question').modal('hide');
	/*let objEvalForm = {
		title : '',
		detail : questionDetail,
		questionTypeId : questionType,
		answerId : arrayAnswer,
		uniqueId : $('#uniqueId-question').val().trim()
	};	*/
	
	
	/*let url = contextPath + "/api/eval_form/update_eval_form_by_uniqueId/";
	if($('#checklist_id').val() != ''){
		
		ajaxProcess.submit(url, 'POST', objEvalForm, '#dialog_edit_Question', (dataResponse)=>{
			if(dataResponse.result){
				let url = contextPath+'/api/eval_form/eval_form_list/'+JSON.stringify({checklistId : {checklistId : $('#checklist_id').val().trim()}});
				ajaxProcess.submit(url, 'GET', null, '#portlet-checklist-section', (data) => {
					if(data.result){
						let checklistObj = JSON.parse(data.message);
						if(checklistObj.checklistToFrontEnd.length > 0){
							$('.portlet-summary').remove();
							$(checklistObj.checklistToFrontEnd).each(function(i,v){								
								checklist.generateElements(v); 
							});
						}else{
							
						}
					}						
					
				});
				$('#dialog_edit_Question').modal('hide');
			}
		});
	}*/
	
});

/*$('#sel1').on('change',function(){
	let value = ($(this).find('option:selected').html()).toString();
	if($(this).val() == "1"){
		if($('#answer-1-only').hasClass('hide')){
			if(!$('#answer-morethan-1').hasClass('hide')){
				$('#answer-morethan-1').slideUp('3000',function(){
					$('#answer-morethan-1').addClass("hide");
				});
			}
			$('#answer-1-only').hide().removeClass("hide").slideDown('3000');
		}
	}else if($(this).val()=="2"){
		if($('#answer-morethan-1').hasClass('hide')){
			if(!$('#answer-1-only').hasClass('hide')){
				$('#answer-1-only').slideUp('3000',function(){
					$('#answer-1-only').addClass("hide");
				});
			}
			$('#answer-morethan-1').hide().removeClass("hide").slideDown('3000');
		}
	}else{
		$('#answer-1-only').hide().addClass("hide").slideDown('3000');
		$('#answer-morethan-1').hide().addClass("hide").slideDown('3000');
	}
});*/

$('#table_condition').on('click','.removeCondition',function(){
	$('#table_condition').DataTable().row($(this).parents('tr')).remove().draw();
});

var dataTableCondition = null;
function submitAction(){
	let grade = $('#grade_list').val().trim();
	let correctiveAction = $('#CorrectiveAction').val().trim();
	let objCondition = {Grade : grade};
	let standardAnswer = [];
	$(answerListByChecklistType).each(function(i,v){
		let objTmp = {};
		objTmp[v.answerDetail] = '';
		standardAnswer.push(objTmp);
	});
	let btnRemove = '<button type="button" class = "removeCondition btn btn-default btn-sm">';
	btnRemove += '<i class="icon-trash"></i> ลบเงื่อนไข </button>';
	let arrayAnswer = [];
	$.each($('.condition').find('.condition-section'),function(i,val){
		let resultSelect = ($(this).find('.result_list').val().trim());
		let operation = $(this).find('.operator_list').val();
		let number1 = $(this).find('.number1').val();
		let number2 = $(this).find('.number2').val();
		let objAnswer = {};
		$(answerListByChecklistType).each(function(i,v){
			if(resultSelect == v.answerId){
				if(operation == "-"){
					objAnswer[v.answerDetail] = (number1+''+operation+''+number2).toString();
				}else{
					objAnswer[v.answerDetail] = (operation+' '+number1).toString();
				}
				
				arrayAnswer.push(objAnswer);
			}
		});
		
	});	

	objCondition["CorrectiveAction"] = correctiveAction;
		
	$.extend({
	    keys:    function(obj){
	        var a = [];
	        $.each(obj, function(k){ a.push(k) });
	        return a;
	    }
	})
	
	$.each(arrayAnswer,function(i,val){		
		let keyInput = $.keys(val);		
		$.each(standardAnswer,function(i,v){
			let keyStandard = $.keys(v);
			if(keyInput[0] == keyStandard[0]){
				v[keyInput[0]] = val[keyInput[0]];
			}
		});
	});
	
	let arrayDataTable = [];
	arrayDataTable.push(objCondition['Grade']);	
	
	$(standardAnswer).each(function(i,val){
		let key = $.keys(val);
		arrayDataTable.push(val[key[0]]);
	});	
	arrayDataTable.push(objCondition['CorrectiveAction']);
	arrayDataTable.push(btnRemove);
	$('#table_condition').dataTable().fnAddData(arrayDataTable);	
	$('#dialog_add_condition').modal('hide');
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
    	$('#select_product_type').val(null).trigger('change');
    	loadEvalForm();
	});	
	
}

function genGrade(){
	let url = contextPath + "/api/grade/grade_list/"+JSON.stringify({
		checklistTypeId : checklistTypeId
	});
	ajaxProcess.submit(url, 'GET', null, '', function(data){
		if(data.result){
			
			let dataObj = JSON.parse(data.message);
			let dataSource = [];
			$(dataObj).each(function(i,v){
				dataSource.push({
	    			id : v.gradeId,
	    			text  : v.gradeId
	    		});
			});
			
			$("#grade_list").select2({
	    		dropdownParent: $('#dialog_add_condition'),
	    		placeholder: "กรุณาเลือกเกรด",
	    		theme: "bootstrap",
	    		data: dataSource
	    	});
			//$('#grade_list').val(null).trigger('change');
			
		}else{
			customMessageDialog("error", "เกิดข้อผิดพลาด", data.message, null);
		}
		
	});
}

function genAnswer(){
	let url = contextPath+'/api/answer/answer_list_grade_calculator/'+JSON.stringify({
		checklistTypeId : checklistTypeId
	});
	ajaxProcess.submit(url, 'GET', null, null, function(data){
		if(data.result){
			answerListByChecklistType = JSON.parse(data.message);
			
			
			$('#form-detail-checklist').find('#table_condition').find('thead').find('tr').append($('<th>Grade</th>'));
			$(answerListByChecklistType).each(function(i,v){
				$('.result_list').append($('<option value ="'+v.answerId+'">'+v.answerDetail+'</option>'));
				$('#form-detail-checklist').find('#table_condition').find('thead').find('tr').append($('<th>'+v.answerDetail+'</th>'));
				answerMappingTable.push({
					answerId : v.answerId,
					answerName : v.answerDetail,
					indexOfColumnInTable : i+1
				});
			});
			
			/*$(answerListByChecklistType).each(function(i,v){
				
			});		*/	
			$('#form-detail-checklist').find('#table_condition').find('thead').find('tr').append($('<th>CorrectiveAction</th>'));
			$('#form-detail-checklist').find('#table_condition').find('thead').find('tr').append($('<th>ลบข้อมูล</th>'));
			initialdataTable();
		}
	});
}

function genSelectionQuestionType(){
	let url = contextPath+'/api/question_type_checklist_type_mapping/question_type_by_checklist_type/'+JSON.stringify({checklistTypeId : checklistTypeId});
	//let url = contextPath+'/api/question_type/question_type_list';
	ajaxProcess.submit(url,'GET',null,null,(data)=>{
		if(data.result){
			let objQuestionType = JSON.parse(data.message);
			
			let dataSource = [];
			$(objQuestionType).each(function(i,v){
				dataSource.push({
	    			id : v.questionTypeId,
	    			text  : v.name
	    		});
			});
			dataSourceQuestionTypeMapping = dataSource;
			$(".question_type_selection").select2({
	    		dropdownParent: $('#tabQuestion'),
	    		placeholder: "กรุณาเลือกประเภทคำตอบ",
	    		theme: "bootstrap",
	    		data: dataSource
	    	});
			$('.question_type_selection').val(null).trigger('change');
			
			$(".question_type_edit").select2({
	    		dropdownParent: $('#dialog_edit_Question'),
	    		placeholder: "กรุณาเลือกประเภทคำตอบ",
	    		theme: "bootstrap",
	    		data: dataSource
	    	});
			$('.question_type_edit').val(null).trigger('change');
		}else{
			customMessageDialog("error", "เกิดข้อผิดพลาด", data.message, null);
		}
		
	});
	
}

function currentDate(callbackFunction){
	let dateCurrent = null;
	let url = contextPath+'/api/date_time/current_date';
	ajaxProcess.submit(url, 'GET', null, null, (data) =>{
		if(data.result){
			try{
				let dateString = JSON.parse(data.message).currentDate;	
				dateCurrent = new Date(dateString);
				return callbackFunction(dateCurrent);
			}catch(e){
				dateCurrent = null;
				return callbackFunction(dateCurrent);
			}			
		}
	});
	
}


$('#tabQuestion').on('change','.question_type_selection',function(){
	if($(this).val() != null){
		let objQuestionType = {
				questionTypeId : $(this).val().trim()
		};
		let url = contextPath+'/api/answer/answer_list_in_question_type/'+JSON.stringify(objQuestionType);
		let elementSelect2 = $(this);
		
		if(!elementSelect2.closest('.row').find('.label-null-select').hasClass('hide')){
			elementSelect2.closest('.row').find('.label-null-select').addClass('hide');
		}
		let time = 0;
		//console.log(elementSelect2.closest('.row').find('.form-md-checkboxes').find('.md-checkbox-list').find('.checkbox-answer'));
		if(elementSelect2.closest('.row').find('.form-md-checkboxes').find('.md-checkbox-list').find('.checkbox').length > 0){
			time = 150;
		}
		elementSelect2.closest('.row').find('.form-md-checkboxes').find('.md-checkbox-list').slideUp(time,function(){
			elementSelect2.closest('.row').find('.form-md-checkboxes').find('.md-checkbox-list').empty();
			
			ajaxProcess.submit(url, 'GET', null, null, function(data){
				let objAnswer = JSON.parse(data.message);
				$(objAnswer).each(function(i,v){
					
					elementSelect2.closest('.row').find('.form-md-checkboxes').find('.md-checkbox-list')
					.append($('<div class="checkbox">')
							.append($('<label style="font-size: 16px;">')
									.append($('<input type="checkbox" class="answerId" value="'+v.answerId+'" checked>'))
									.append($('<span class="cr"><i class="cr-icon fa fa-check font-green"></i></span>'))
									.append($('<span class="answerName">'+v.answerDetail+'</span>'))									
									)
							).hide()
							.slideDown(300);
				})
				
			});
			
		})
		
		
	}else{
		
	}
	
});

$('#dialog_edit_Question').on('change','.question_type_edit',function(){
	if($(this).val() != null){
		let objQuestionType = {
				questionTypeId : $(this).val().trim()
		};
		let url = contextPath+'/api/answer/answer_list_in_question_type/'+JSON.stringify(objQuestionType);
		let elementSelect2 = $(this);
		
		if(!elementSelect2.closest('.row').find('.label-null-select').hasClass('hide')){
			elementSelect2.closest('.row').find('.label-null-select').addClass('hide');
		}
		let time = 0;
		//console.log(elementSelect2.closest('.row').find('.form-md-checkboxes').find('.md-checkbox-list').find('.checkbox-answer'));
		if(elementSelect2.closest('.row').find('.form-md-checkboxes').find('.md-checkbox-list').find('.checkbox').length > 0){
			time = 0;
		}
		elementSelect2.closest('.row').find('.form-md-checkboxes').find('.md-checkbox-list').slideUp(time,function(){
			elementSelect2.closest('.row').find('.form-md-checkboxes').find('.md-checkbox-list').empty();
			
			ajaxProcess.submit(url, 'GET', null, null, function(data){
				let objAnswer = JSON.parse(data.message);
				$(objAnswer).each(function(i,v){
					
					elementSelect2.closest('.row').find('.form-md-checkboxes').find('.md-checkbox-list')
					.append($('<div class="checkbox">')
							.append($('<label style="font-size: 16px;">')
									.append($('<input type="checkbox" class="answerId" value="'+v.answerId+'">'))
									.append($('<span class="cr"><i class="cr-icon fa fa-check font-green"></i></span>'))
									.append($('<span class="answerName">'+v.answerDetail+'</span>'))									
									)
							).hide()
							.slideDown(300);
				})
				
			});
			
		})
		
		
	}else{
		
	}
	
});

function getCondition(dataString){

	let val = '',operation = '';			
	if (dataString.indexOf('−') != -1) {
		//Operation Is −
		let indexOperation = dataString.indexOf('−');
		operation = '-';
		val = dataString.substring(0, indexOperation-1).trim() +'-'+dataString.substring(indexOperation+1).trim();
	}else if(dataString.indexOf('-') != -1){
		let indexOperation = dataString.indexOf('-');
		operation = '-';
		val = dataString.substring(0, indexOperation).trim() +'-'+dataString.substring(indexOperation+1).trim();
	}else{
		operation = dataString.split(" ")[0].trim();
		val = dataString.split(" ")[1].trim();
	}
	return {op : operation, value : val};
}

$("#select_checklist_type").on('change',function(){
	//console.log($('#form-detail-checklist').find('.section-approve-condition'));
});
function initialdataTable(){
	tableCondition = $('#table_condition').dataTable({
		"searching": false,
		"language": {
	        "url": "assets/global/plugins/datatables/Thai.json"
	    },
	    "columnDefs": [
	    	{
	            "searchable": false,
	            "orderable": false,
	            "targets": [1,2,3,4,5]
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
	return tableCondition;
}

$('#dialog_edit_Not_Question').on('hidden.bs.modal', function () {
	validateDialogEditNotQuestion.resetForm();
	$($('#edit_Not_Question').find('.form-group')).each(function(){
		if($(this).hasClass('has-error')){
			$(this).removeClass('has-error');
		}
	});	
});


var genFormWizardFromElement = function(){
	return{
		
		addElement : function(_element){
			
			var pageFormWizard = 1;
			try{
				
				var elementPortlet = _element.closest('.portlet-summary');
				var topicTmp = elementPortlet.find('.portlet-title').find('.name-topic-section').text().split(":");
				var nameTopic = topicTmp[0].trim();
				var descriptionTopic = topicTmp[1].trim();
				var nameSubTopic = '';
				var descriptionSubTopic = '';
				var nameGroupQuestion = '';
				var descriptionGroupQuestion = '';	
				var nameQuestion = '';
				var descriptionQuestion = '';
				$("#dialog_add_checklist #form_wizard").find(".button-previous-step").show();
				$("#dialog_add_checklist #form_wizard").find(".button-next-step").show();
				
				if(_element.hasClass('add-sub-topic')){
					$('#name_topic').val(nameTopic);
					$('#description_topic').val(descriptionTopic);
					pageFormWizard = 2;
				}
				else if(_element.hasClass('add-group-question')){
					 
					if(_element.closest('.form-group').find('.name-sub-topic').length != 0){
						var subTopicTmp = _element.closest('.form-group').find('.name-sub-topic').text().trim().split(":");
						nameSubTopic = subTopicTmp[0].trim();
						descriptionSubTopic = subTopicTmp[1].trim();
						
					}	
					
					$('#name_topic').val(nameTopic);
					$('#description_topic').val(descriptionTopic);
					
					$('#name_sub_topic').val(nameSubTopic);
					$('#description_sub_topic').val(descriptionSubTopic);
					pageFormWizard = 3;
				}
				else if(_element.hasClass('add-question')){
					var elementGroupFormGroupQuestion = _element.closest('.form-group');
					var groupQuestionTmp = elementGroupFormGroupQuestion.find('.name-group-question').text().trim().split(":");
					console.log(groupQuestionTmp);
					nameGroupQuestion = groupQuestionTmp[0].trim();
					descriptionGroupQuestion = groupQuestionTmp[1].trim();
					if(elementGroupFormGroupQuestion.closest('.group-question').closest('.form-group').find('.name-sub-topic').length != 0){
						var subTopicTmp = elementGroupFormGroupQuestion.closest('.group-question').closest('.form-group').find('.name-sub-topic').text().trim().split(":");
						nameSubTopic = subTopicTmp[0].trim();
						descriptionSubTopic = subTopicTmp[1].trim();
					}			
					
					$('#name_topic').val(nameTopic);
					$('#description_topic').val(descriptionTopic);
					
					$('#name_sub_topic').val(nameSubTopic);
					$('#description_sub_topic').val(descriptionSubTopic);
					
					$('#name_group_question').val(nameGroupQuestion);
					$('#description_group_question').val(descriptionSubTopic);
					
					pageFormWizard = 4;
				}				
				else{
					$("#dialog_add_checklist #form_wizard").find(".button-previous-step").hide();
					pageFormWizard = 1;
				}
				$("#dialog_add_checklist #form_wizard").bootstrapWizard('show',pageFormWizard-1);
				$(".step-title", $("#dialog_add_checklist #form_wizard")).text("ขั้นตอนที่ "+pageFormWizard+" จาก 5");
				
				$("#dialog_add_checklist #form_wizard li").each(function(index) {
					 if(index < pageFormWizard-1)
						 $(this).addClass("done");		     
				});
				
			}catch(error){
				console.log(error);
				pageFormWizard = 1;
				$("#dialog_add_checklist #form_wizard").find(".button-previous-step").hide();
			}
			
		}
	}
}();