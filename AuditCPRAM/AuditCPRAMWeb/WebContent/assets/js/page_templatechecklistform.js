var objFormWizard = null;
var countColumn = 1;
var tmpNameTemplate = '';

$(function(){	
	var stringReceive = getReceiveParam();
	if(stringReceive != ""){	
		getDetailTemplate(stringReceive,ProcessTemplate);		
	}
	
	if($('#name_template').val().trim() == ''){
		$('#name_template').closest('div').removeClass('input-group');
		
	}else{
		$('#name_template').closest('div').addClass('input-group');
		$('.edit-name-template').removeClass('hide');
		$('#name_template').attr('readonly',true);
		/*$('.save-name-template').hide();*/
	}
	
	FormWizard.init();
	loadEval.init();
	$('#template-checklist').validate({
		focusInvalid : !1,
		errorClass: "help-block help-block-error",
		rules : {
			name_template : {
				required: !0
			}
		},
		messages : {
			name_template : {
				required: 'กรุณาใส่ชื่อแม่แบบเช็คลิสต์ '
			}
		},
		highlight: function(e) {			
            $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
        },
        unhighlight: function(e) {
            $(e).closest(".form-group").removeClass("has-error")
        }
		
	});
	
});


function ProcessTemplate(data){
	var objReceiveParam = JSON.parse(data);
	$.each(objReceiveParam,function(index,value){
		var questionDetail = [];
		
		$.each(value.question,function(index,value){
			if(value.questionDetail !=''){
				questionDetail.push({
					name : value.questionDetail,
					uniqueId : value.uniqueId
				});
			}
				
		});
		objectToGenDispaly = {
			template : {name : value.template.title, description : value.template.detail, uniqueId : value.template.uniqueId},
			topic : {name : value.topic.topicTitle , description : value.topic.topicDetail, uniqueId : value.topic.uniqueId},
			subTopic : {name : value.subTopic.subTopicTitle, description : value.subTopic.subTopicDetail, uniqueId : value.subTopic.uniqueId},
			groupQuestion : {name : value.groupQuestion.questionGroupTitle, description : value.groupQuestion.questionGroupDetail, uniqueId : value.groupQuestion.uniqueId},
			question : questionDetail
		};
		
		generateElements(objectToGenDispaly);	
		$('.portlet-summary').hide().fadeIn(300);
		
	});
}


$('#btn-create-template').on('click',function(){
	if(!$('#name_template').next().find('.save-name-template').hasClass('hide') && $('#name_template').closest('div').hasClass('input-group')){
		customMessageDialog('warning',"คำเตือน","กรุณากดปุ่มบันทึกชื่อแม่แบบก่อน",null);
		return false;
	}
	if($('#template-checklist').valid()){
		$('#dialog_add_template_checklist').modal('show');
	}	
	
});

var FormWizard = function() {
	return{
		init : function(){
			function e(e) {
				
			}
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
                    		required: !0
                    	},
                    	name_group_question : {
                    		required: !0
                    	},
                    	question : {
                    		required: !0
                    	}
                    },
                    messages : {
                    	name_topic : {
                    		required: "กรุณาใส่ชื่อหัวข้อ"
                    	},
                    	name_sub_topic : {
                    		required: "กรุณาใส่ชื่อหัวข้อย่อย"
                    	},
                    	name_group_question : {
                    		required: "กรุณาใส่ชื่อกลุ่มคำถาม"
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
                    1 == o ? $("#form_wizard").find(".button-previous").hide() : $("#form_wizard").find(".button-previous").show(), o >= i ? ($("#form_wizard").find(".button-next").hide(), $("#form_wizard").find(".button-submit").show()) : ($("#form_wizard").find(".button-next").show(), $("#form_wizard").find(".button-submit").hide()), App.scrollTo($("body"))
				};	
					
				/*$(obj).rules( "add", {
					  required: true,
					  messages: {
						  required: "กรุณาใส่คำถามอย่างน้อย 1 คำถาม"
					  }
				});*/
				
				
				$("#form_wizard").bootstrapWizard({
                    'nextSelector': ".button-next",
                    'previousSelector': ".button-previous",                   
                    onTabClick: function(e, r, t, i) {                    	
                        return !1
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
                $("#form_wizard").find(".button-previous").hide(), 
                $("#form_wizard .button-submit").click(function() {                	
                	generateElements(objFormWizard);  
                	submitToDB(objFormWizard);
                	$('#dialog_add_template_checklist').modal('hide');
                }).hide()
			}
		}
	}
}();


$('#dialog_add_template_checklist').on('hidden.bs.modal', function () {
	$("#form_wizard").bootstrapWizard('first');
	$("#form_wizard").find(".button-previous").hide();
	$("#form_wizard").find(".button-next").show(); 
	$("#form_wizard").find(".button-submit").hide();
	$(".step-title", $("#form_wizard")).text("ขั้นตอนที่ 1 จาก 5"), jQuery("li", $("#form_wizard")).removeClass("done");
	$('#name_topic').val('');
	$('#description_topic').val('');
	$('#name_sub_topic').val('');
	$('#description_sub_topic').val('');
	$('#name_group_question').val('');
	$('#description_group_question').val('');
	FormRepeater.clearAll();
});

$('.edit-name-template').on('click',function(){
	$(".edit-name-template").addClass("hide");
	$(".save-name-template").removeClass("hide");
	$('#name_template').attr('readonly',false);
	tmpNameTemplate = $('#name_template').val().trim();
	
});

$('.save-name-template').on('click',function(){
	$(".save-name-template").addClass("hide");
	$(".edit-name-template").removeClass("hide");
	$('#name_template').attr('readonly',true);
	
	ConfirmModal.setupModal("warning","การยืนยัน","ยืนยันการแก้ไขชื่อแม่แบบนี้ใช่หรือไม่ ?");
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			if($('#name_template').val().trim() != tmpNameTemplate){
				if($('#name_template').val().trim() != ""){
					let objToEdit = {
						title : $('#name_template').val().trim(),
						detail : "",
						statusId : '1',
						uniqueId : $('#name_template').closest('.input-group').next().text().trim(),
						evalTypeId : 1
					};
					updateTemplate(objToEdit);
				}else{
					$('#name_template').val(tmpNameTemplate);
					BlockUi.blockPage();
					setTimeout(function(){
						BlockUi.unBlockPage();
					},100);
				}
			}else{
				BlockUi.blockPage();
				setTimeout(function(){
					BlockUi.unBlockPage();
				},500);
			}
		}else{
			
		}			
	});
	
	
});

function getReceiveParam(){
	var paramReceiveString = '';
	return paramReceiveString = decodeURI($('#paramReceive').val().trim());
}

function loadDataInFormWizard(){	
	var id = 1;
	BlockUi.blockPosition('#dialog_add_template_checklist');
	var nameTemplate = $('#name_template').val().trim();
	var descriptionTemplate = '';
	var uniqueTemplate = generate.randomByDate() + id++;
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
		var val = editor.getText($(this)).trim();
		if(val !=''){
			var uniqueQuestion = generate.randomByDate() + id++;
			questionDetail.push({
				name : val,
				uniqueId : uniqueQuestion
			});
		}
			
	});
	
	
	
	objFormWizard = {
		template : {name : nameTemplate, description : descriptionTemplate, uniqueId : uniqueTemplate},
		topic : {name : nameTopic , description : descriptionTopic, uniqueId : uniqueTopic},
		subTopic : {name : nameSubTopic, description : descriptionSubTopic, uniqueId : uniqueSubTopic},
		groupQuestion : {name : nameGroupQuestion, description : descriptionGroupQuestion, uniqueId : uniqueGroupQuestion},
		question : questionDetail
	};
	
	$('#name_topic_summary').text(objFormWizard.topic.name);
	if(objFormWizard.topic.description != ''){
		$('#description_topic_summary').text(objFormWizard.topic.description);
	}
		
	
	$('#name_sub_topic_summary').text(objFormWizard.subTopic.name);
	if(objFormWizard.subTopic.description != '')
		$('#description_sub_topic_summary').text(objFormWizard.subTopic.description);
	
	$('#name_group_question_summary').text(objFormWizard.groupQuestion.name);
	if(objFormWizard.groupQuestion.description != '')
		$('#description_group_question_summary').text(objFormWizard.groupQuestion.description);
	
	$('.question-section').empty();
	$.each(objFormWizard.question,function(index,value){
		$('.question-section').append($('<div class="list-group-item">')
								.append($('<h4 class="list-group-item-heading" style="white-space: pre-wrap;">'+value.name+'</h4>'))
								/*.append($('<p class="list-group-item-text" style="white-space: pre-wrap;">'+objFormWizard.question.nameEN[index]+'</p>'))*/
							  );
	});

	setTimeout(function(){
		BlockUi.unBlockPosition('#dialog_add_template_checklist');
	},500);
	
}


$('body').on('click','.remove-question',function(){
	let objBtnRemove = $(this);
	let uniqueId = objBtnRemove.closest('li').find('.uniqueQuestion').text().trim();
	ConfirmModal.setupModal("warning","การยืนยัน","ยืนยันการลบคำถามนี้ใช่หรือไม่ ?");
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue)
			deleteElement(uniqueId,objBtnRemove.closest('li'));
			//objBtnRemove.closest('li').slideUp('slow', function() { $(this).remove(); });
	});
	
	
});


$('body').on('click','.remove-group-question',function(){
	let objBtnRemove = $(this);
	ConfirmModal.setupModal("warning","การยืนยัน","ยืนยันการลบกลุ่มคำถามนี้ใช่หรือไม่ ?");
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			let questionlist = objBtnRemove.closest('.group-question').find('.name-question-section').find('ul').find('li');
			$.each(questionlist,function(){				
				deleteElement($(this).find('.uniqueQuestion').text(),null);
			});
			let uniqueId = (objBtnRemove.closest('.form-section-right').find('.uniqueGroupQuestion').text());
			deleteElement(uniqueId,objBtnRemove.closest('.group-question'));
		}
			//objBtnRemove.closest('.form-group').slideUp('slow', function() { $(this).remove(); });
	});
	
});


$('body').on('click','.remove-sub-topic',function(){
	var objBtnRemove = $(this);
	ConfirmModal.setupModal("warning","การยืนยัน","ยืนยันการลบหัวข้อย่อยนี้ใช่หรือไม่ ?");
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			$.each(objBtnRemove.closest('.form-section-right').closest('.form-group').find('.group-question').find('.form-group'),function(){				
				
				if($(this).find('.uniqueGroupQuestion').length > 0){					
					deleteElement($(this).find('.uniqueGroupQuestion').text(),null);
				}else if($(this).find('.uniqueQuestion').length > 0){		
					$.each($(this).find('.uniqueQuestion'),function(){
						deleteElement($(this).text(),null);
					});
				}
			});
			deleteElement(objBtnRemove.closest('.form-section-right').find('.uniqueSubTopic').text(),objBtnRemove.closest('.form-section-right').closest('.form-group'));
		}
			//objBtnRemove.closest('.form-group').slideUp('slow', function() { $(this).remove(); });
	});
	
});

$('body').on('click','.remove-topic',function(){
	var objBtnRemove = $(this);
	ConfirmModal.setupModal("warning","การยืนยัน","ยืนยันการลบหัวข้อนี้ใช่หรือไม่ ?");
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			$.each(objBtnRemove.closest('.portlet-title').next().find('.sub-topic-section').find('.form-group'),function(){
				if($(this).find('.uniqueSubTopic').length > 0){
					deleteElement($(this).find('.uniqueSubTopic').text(),null);
				}else if($(this).find('.uniqueGroupQuestion').length > 0){
					deleteElement($(this).find('.uniqueGroupQuestion').text(),null);
				}else if($(this).find('.uniqueQuestion').length > 0){
					$.each($(this).find('.uniqueQuestion'),function(){
						deleteElement($(this).text(),null);
					});
				}
			});
			deleteElement(objBtnRemove.closest('.actions').find('.uniqueTopic').text(),objBtnRemove.closest('.row'));
			//closest('actions').find('.uniqueTopic').text()
		}
			//objBtnRemove.closest('.row').slideUp('slow', function() { $(this).remove(); });
	});
});


$('body').on('click','.add-question',function(){
	genFormWizardFromElement.addElement($(this));
	$('#dialog_add_template_checklist').modal('show');
});

$('body').on('click','.add-group-question',function(){
	genFormWizardFromElement.addElement($(this));
	$('#dialog_add_template_checklist').modal('show');
});

$('body').on('click','.add-sub-topic',function(){
	genFormWizardFromElement.addElement($(this));
	$('#dialog_add_template_checklist').modal('show');
});

$('body').on('click','.edit-question',function(){
	var questionHtml = $(this).closest('li').html().trim();
	var questionTmp = questionHtml.split('&nbsp;&nbsp;<a href="javascript:;"');
	var nameQuestion = questionTmp[0].trim();	
	var uniqueId = $(this).closest('li').find('.uniqueQuestion').text().trim();
	var obj = {
		value1 : nameQuestion,
		value2 : uniqueId,
		action : 'question'			
	};
	modalEdit.initial(obj);
	//modalEdit.initial($(this));
});

$('body').on('click','.edit-group-question',function(){
	var groupQuestionSection = $(this).closest('.form-group').find('.name-group-question').text().trim().split(':');
	var nameGroupQuestion = groupQuestionSection[0].trim();
	var descriptionGroupQuestion = groupQuestionSection[1].trim();
	var uniqueId = $(this).closest('.form-section-right').find('.uniqueGroupQuestion').text().trim();
	var obj = {
		value1 : nameGroupQuestion,
		value2 : descriptionGroupQuestion,
		value3 : uniqueId,
		action : 'groupQuestion'			
	};
	modalEdit.initial(obj);
});

$('body').on('click','.edit-sub-topic',function(){
	var subTopicSection = $(this).closest('.form-group').find('.name-sub-topic').text().trim().split(':');
	var nameSubTopic = subTopicSection[0].trim();
	var descriptionSubTopic = subTopicSection[1].trim();
	var uniqueId = $(this).closest('.form-section-right').find('.uniqueSubTopic').text().trim();
	var obj = {
		value1 : nameSubTopic,
		value2 : descriptionSubTopic,
		value3 : uniqueId,
		action : 'subTopic'			
	};
	modalEdit.initial(obj);
});


$('body').on('click','.edit-topic',function(){
	var topicSection = $(this).closest('.portlet-title').find('.name-topic-section').text().trim().split(':');
	var nameTopic = topicSection[0].trim();
	var descriptionTopic = topicSection[1].trim();
	var uniqueId = $(this).closest('.actions').find('.uniqueTopic').text().trim();
	var obj = {
		value1 : nameTopic,
		value2 : descriptionTopic,
		value3 : uniqueId,
		action : 'topic'			
	};
	modalEdit.initial(obj);
});

$('#dialog_edit_editor').on('click','#btn_submit_dialog_edit_editor',function(){	
	let objToEdit = {
		title : '',
		detail : editor.getText($('#name_edit_question')).trim(),
		statusId : '1',
		uniqueId : $('#unique_edit_question').val().trim(),
		evalTypeId : 5
	};
	$('#dialog_edit_editor').modal('hide');
	ConfirmModal.setupModal("warning","การยืนยัน","ยืนยันการแก้ไขข้อมูลนี้ใช่หรือไม่ ?");
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			updateTemplate(objToEdit);
		}			
		else{
			$('#dialog_edit_editor').modal('show');
		}
			
	});
});

$('#dialog_edit').on('click','#btn_submit_dialog_edit',function(){
	let evalTypeId = 0;
	switch($('#dialog_edit #label_name_edit').text()){
		case "ชื่อกลุ่มคำถาม" :
			evalTypeId = 4;
			break;
		case "ชื่อหัวข้อย่อย" :
			evalTypeId = 3;
			break;
		case "ชื่อหัวข้อ" :
			evalTypeId = 2;
			break;
		default :
			evalTypeId = 1;
	}
	let objToEdit = {
		title : $('#name_edit').val().trim(),
		detail : $('#description_edit').val().trim(),
		statusId : '1',
		uniqueId : $('#unique_edit').val().trim(),
		evalTypeId : evalTypeId
	};
	$('#dialog_edit').modal('hide');	
	ConfirmModal.setupModal("warning","การยืนยัน","ยืนยันการแก้ไขข้อมูลนี้ใช่หรือไม่ ?");
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			updateTemplate(objToEdit);
		}			
		else{
			$('#dialog_edit').modal('show');
		}
			
	});
});


function updateTemplate(objUpdate){
	$.ajax({
		url : contextPath+'/api/template/update_template/',
		type: 'POST',
	    contentType: 'application/json',
	    data : JSON.stringify(objUpdate),
	    beforeSend : function(arr, $form, options){
	    	BlockUi.blockPage();
	    },
	    complete : function(){
	    	setTimeout(function(){
	    		BlockUi.unBlockPage();
	    	},500);	    	
	    },
		success : function(data) {				
			if(data.result){
				customMessageDialog("success", "สถานะการดำเนินการ", data.message, null);
			}else{
				customMessageDialog("warning", "สถานะการดำเนินการ", data.message, null);
			} 	  
			$.each($(".portlet-summary").closest('.row'),function(i){				
				$(this).fadeOut(300, function() { 
					$(this).remove();						
					if(($(".portlet-summary").closest('.row').length) == 0){
						getDetailTemplate(getReceiveParam(),ProcessTemplate);
					}
				});				
			});			
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
			customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseJSON.message, null);
		}
	});
}

function generateElements(objForm){
	
	if($('#name_template').val().trim() != '' && $('#paramReceive').val().trim() == ''){
		if(!$('#name_template').is('[readonly]')){
			$('#uniqueIdTemplate').text(objForm.template.uniqueId);
		}		
	}else if($('#name_template').val().trim() == '' && $('#paramReceive').val().trim() != ''){
		$('#name_template').val(objForm.template.name);
		$('#uniqueIdTemplate').text(objForm.template.uniqueId);
	}
	var resulDupp = DupplicateElement(objForm);	
	if(!resulDupp){
		var elementTemplate = $('.portlet-body-summary').find('.portlet-template');
		elementTemplate.find('.name-topic-section').text(objForm.topic.name+" : "+objForm.topic.description);
		elementTemplate.find('.actions').find('.uniqueTopic').text(objForm.topic.uniqueId);
		elementTemplate.find('.portlet-body').find('.name-sub-topic').text(objForm.subTopic.name+" : "+objForm.subTopic.description);
		elementTemplate.find('.portlet-body').find('.name-sub-topic').prev().find('.uniqueSubTopic').text(objForm.subTopic.uniqueId);
		elementTemplate.find('.portlet-body').find('.group-question').find('.name-group-question').html(objForm.groupQuestion.name+" : "+objForm.groupQuestion.description);
		elementTemplate.find('.portlet-body').find('.group-question').find('.uniqueGroupQuestion').text(objForm.groupQuestion.uniqueId);
		var htmlLi = '';
		
		$.each(objForm.question,function(index,val){			
			if(val !="")
				htmlLi += "<li>"+val.name+'&nbsp;&nbsp;<a href="javascript:;" class="edit-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>&nbsp;<a href="javascript:;" data-toggle="tooltip" data-trigger="hover" title="ลบคำถาม" class="remove-question font-red-thunderbird"><i class="fa fa-times"></i> </a><p class="uniqueQuestion hide">'+val.uniqueId+'</p>'+"</li>";
		});
		
		elementTemplate.find('.portlet-body').find('.sub-topic-section').find('.group-question').find('.name-question-section').find('ul').html(htmlLi);
		
		var cloneObj = $('.portlet-template').clone().removeClass('hide').removeClass('portlet-template').addClass('portlet-summary');
		if(objForm.subTopic.name == ''){
			cloneObj.find('.portlet-body').find('.name-sub-topic').prev().remove();
			cloneObj.find('.portlet-body').find('.name-sub-topic').remove();
			cloneObj.find('.portlet-title').find('.actions').find('.dropdown').find('.add-sub-topic').closest('li').remove();
		}else{
			cloneObj.find('.portlet-title').find('.actions').find('.dropdown').find('.add-group-question').closest('li').remove();
		}
		if(objForm.groupQuestion.name == ''){
			cloneObj.find('.portlet-body').find('.name-group-question').closest('.form-group').remove();
		}
		
		$('.portlet-body-summary')
			.append($('<div class="row">')
					.append($('<div class="col-md-12">')
							.append(cloneObj)
							)
					);
		
	}
	
	toolTips.init();
	collpseAndExpand.init();
	if(!$('#name_template').is('[readonly]')){
		$('#name_template').prop('readonly', true);
		$('#name_template').closest('div').addClass('input-group');
		$('.edit-name-template').removeClass('hide');
	}
}




function DupplicateElement(objForm){
	var checkDupplicateTopic = false;
	var checkDupplicateSubTopic = false;
	var checkDupplicateQuestionGroup = false;

	var elementPortlet = $('.portlet-body-summary').find('.portlet-summary');
	//console.log(elementPortlet);
	$.each(elementPortlet,function(index,value){		
		var topicElement = $(this).find('.name-topic-section');
		var tmpTopic = topicElement.text().trim().split(":");
		
		if(tmpTopic[0].trim().toLowerCase() == objForm.topic.name.toLowerCase()){
			checkDupplicateTopic = true;
			var subTopicElementList = $(this).find('.sub-topic-section').find('.name-sub-topic');
			$.each(subTopicElementList,function(){
				var tmpSubTopic = $(this).text().trim().split(":");
				if(tmpSubTopic[0].trim() == objForm.subTopic.name){
					checkDupplicateSubTopic = true;
					var questionGroupList = $(this).closest('.form-group').find('.group-question').find('.name-group-question');
					$.each(questionGroupList,function(index,value){
						var tmpGroupQuestion = $(this).text().trim().split(":");
						if(tmpGroupQuestion[0].trim() == objForm.groupQuestion.name){							
							checkDupplicateQuestionGroup = true;
							var htmlLiQuestion = ""; 
							//var questionList = $(this).closest('.group-question').find('.name-question-section').find('ul').find('li');
							var questionList = $(this).next().find('ul').find('li');
							$.each(objForm.question,function(index,val){
								if(val !=""){
									var checkDuppQuestion = false;
									$.each(questionList,function(){
										var questionTmp = $(this).text().split(" ");
										if(questionTmp[0].trim() == val){
											checkDuppQuestion = true;
										}
									});
									
									if(!checkDuppQuestion)
										htmlLiQuestion += "<li>"+val.name+'&nbsp;&nbsp;<a href="javascript:;" class="edit-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>&nbsp;<a href="javascript:;" data-toggle="tooltip" data-trigger="hover" title="ลบคำถาม" class="remove-question font-red-thunderbird" ><i class="fa fa-times"></i> </a><p class="uniqueQuestion hide">'+val.uniqueId+'</p>'+"</li>";
																			
								}								
								
									
							});
							$(this).next().find('ul').append(htmlLiQuestion);
							//$(this).closest('.group-question').find('.name-question-section').find('ul').append(htmlLiQuestion);
							
						}
					});
					
					if(!checkDupplicateQuestionGroup){
						var htmlLiQuestion = ""; 
						$.each(objForm.question,function(index,val){
							if(val !="")
								htmlLiQuestion += "<li>"+val.name+'&nbsp;&nbsp;<a href="javascript:;" class="edit-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>&nbsp;<a href="javascript:;" data-toggle="tooltip" data-trigger="hover" title="ลบคำถาม" class="remove-question font-red-thunderbird" ><i class="fa fa-times"></i> </a><p class="uniqueQuestion hide">'+val.uniqueId+'</p>'+"</li>";
						});
						$(this).closest('.form-group').find('.group-question').append($('<div class="form-group">')
								
								.append($('<div class="form-section-right">')
										.append($('<a class="font-blue-ebonyclay collapsed" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">')
												.append($('<i class="fa fa-angle-up fa-lg rotate-icon"></i>'))
												.append('&nbsp;')
												)
										.append('&nbsp;')
										.append('&nbsp;')
										.append($('<a href="javascript:;" class="add-question font-green-soft" data-toggle="tooltip" data-trigger="hover" title="เพิ่มคำถาม">')
												.append($('<i class="fa fa-plus">'))
												.append('&nbsp;')
												)
										.append('&nbsp;')
										.append('&nbsp;')
										.append($('<a href="javascript:;" class="edit-group-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขกลุ่มคำถาม" >')
												.append($('<i class="fa fa-pencil">'))
												.append('&nbsp;')
												)
										.append('&nbsp;')
										.append('&nbsp;')
										.append($('<a href="javascript:;"  class="remove-group-question font-red-thunderbird" data-toggle="tooltip" data-placement="left" data-trigger="hover" title="ลบกลุ่มคำถาม"><i class="fa fa-times"></i> </a>'))
										.append($('<p class="uniqueGroupQuestion hide">'+objForm.groupQuestion.uniqueId+'</p>'))
										)
								
								.append($('<h5 class="form-section form-section-left name-group-question"> '+objForm.groupQuestion.name+' : '+objForm.groupQuestion.description+'  </h5>'))
								
								.append($('<div class="form-group form-body-collapse name-question-section" style="margin-left:30px;">')
										.append($('<ul>').append(htmlLiQuestion))
										)
								);
						
						//$(this).find('.group-question').find('.name-question-section').find('<ul>').html(htmlLiQuestion);
					}
				}
			});
			if(!checkDupplicateSubTopic){
				var htmlLiQuestion = ""; 				
				
				if(objForm.subTopic.name == ''){
					var subTopicSection = $(this).find('.portlet-body').find('.sub-topic-section');
					var DuppGroupQuestion = false;
					$.each(subTopicSection.children(),function(){
						var tmpGroupQuestion = $(this).find('.name-group-question').text().trim().split(":");
						if(tmpGroupQuestion[0].trim() == objForm.groupQuestion.name){
							DuppGroupQuestion = true;
							var questionList = $(this).find('.name-question-section').find('ul').find('li');
							$.each(objForm.question,function(index,val){
								if(val !=""){
									var checkDuppQuestion = false;
									$.each(questionList,function(){
										var questionTmp = $(this).text().split(" ");
										if(questionTmp[0].trim() == val){
											checkDuppQuestion = true;
										}
									});
									
									if(!checkDuppQuestion)
										htmlLiQuestion += "<li>"+val.name+'&nbsp;&nbsp;<a href="javascript:;" class="edit-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>&nbsp;<a href="javascript:;" data-toggle="tooltip" data-trigger="hover" title="ลบคำถาม" class="remove-question font-red-thunderbird" ><i class="fa fa-times"></i> </a><p class="uniqueQuestion hide">'+val.uniqueId+'</p>'+"</li>";					
								}	
							});
							
							$(this).find('.name-question-section').find('ul').append(htmlLiQuestion);
						}
					});
					
					if(!DuppGroupQuestion){
						$.each(objForm.question,function(index,val){
							if(val !="")
								htmlLiQuestion += "<li>"+val.name+'&nbsp;&nbsp;<a href="javascript:;" class="edit-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>&nbsp;<a href="javascript:;" data-toggle="tooltip" data-trigger="hover" title="ลบคำถาม" class="remove-question font-red-thunderbird" ><i class="fa fa-times"></i> </a><p class="uniqueQuestion hide">'+val.uniqueId+'</p>'+"</li>";
						});
						subTopicSection
						.append($('<div class="form-group">')
								.append($('<div class="group-question" style="margin-left:30px;">')
										.append($('<div class="form-group">')
																				
												.append($('<div class="form-section-right">')
														.append($('<a class="font-blue-ebonyclay collapsed" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">')
																.append($('<i class="fa fa-angle-up fa-lg rotate-icon"></i>'))
																.append('&nbsp;')
																)
														.append('&nbsp;')
														.append('&nbsp;')
														.append($('<a href="javascript:;" class="add-question font-green-soft" data-toggle="tooltip" data-trigger="hover" title="เพิ่มคำถาม">')
																.append($('<i class="fa fa-plus">'))
																.append('&nbsp;')
																)
														.append('&nbsp;')
														.append('&nbsp;')
														.append($('<a href="javascript:;" class="edit-group-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขกลุ่มคำถาม" >')
															.append($('<i class="fa fa-pencil">'))
															.append('&nbsp;')
															)
														.append('&nbsp;')
														.append('&nbsp;')
														.append($('<a href="javascript:;"  class="remove-group-question font-red-thunderbird" data-toggle="tooltip" data-placement="left" data-trigger="hover" title="ลบกลุ่มคำถาม"><i class="fa fa-times"></i> </a>'))
														
														.append($('<p class="uniqueGroupQuestion hide">'+objForm.groupQuestion.uniqueId+'</p>'))
														)
												
												.append($('<h5 class="form-section form-section-left name-group-question"> '+objForm.groupQuestion.name+' : '+objForm.groupQuestion.description+'  </h5>'))
																								
												.append($('<div class="form-group form-body-collapse name-question-section" style="margin-left:30px;">')
														.append($('<ul>')
																.append($(htmlLiQuestion))
																)
														)
												)										
										)
								);
					}
					
					
					
				}else{
					$.each(objForm.question,function(index,val){
						if(val !="")
							htmlLiQuestion += "<li>"+val.name+'&nbsp;&nbsp;<a href="javascript:;" class="edit-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>&nbsp;<a href="javascript:;" data-toggle="tooltip" data-trigger="hover" title="ลบคำถาม" class="remove-question font-red-thunderbird" ><i class="fa fa-times"></i> </a><p class="uniqueQuestion hide">'+val.uniqueId+'</p>'+"</li>";
					});
					var subTopicSection = $(this).find('.portlet-body').find('.sub-topic-section');
					subTopicSection.append($('<div class="form-group">')
							.append($('<div class="form-section-right">')
									.append($('<a href="javascript:;" class="add-group-question font-green-soft" data-toggle="tooltip" data-trigger="hover"  title="เพิ่มกลุ่มคำถาม" >')
											.append($('<i class="fa fa-plus">'))
											.append('&nbsp;')
											)
									.append('&nbsp;')
									.append('&nbsp;')
									.append($('<a href="javascript:;" class="edit-sub-topic font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขหัวข้อย่อย" >')
											.append($('<i class="fa fa-pencil">'))
											.append('&nbsp;')
											)
									.append('&nbsp;')
									.append('&nbsp;')
									.append($('<a href="javascript:;" class="remove-sub-topic font-red-thunderbird" data-toggle="tooltip"  data-trigger="hover"  data-placement="left" title="ลบหัวข้อย่อย">')
											.append($('<i class="fa fa-times"></i>'))											
											)
									.append($('<p class="uniqueSubTopic hide">'+objForm.subTopic.uniqueId+'</p>'))
									)
							.append($('<h4 class="form-section bold font-blue-soft name-sub-topic"> '+objForm.subTopic.name+' : '+objForm.subTopic.description+'  </h4>'))
							.append($('<div class="group-question" style="margin-left:30px;">')
									.append($('<div class="form-group">')
											.append($('<div class="form-section-right">')
													.append($('<a class="font-blue-ebonyclay collapsed" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">')
															.append($('<i class="fa fa-angle-up fa-lg rotate-icon"></i>'))
															.append('&nbsp;')
															)
													.append('&nbsp;')
													.append('&nbsp;')
													.append($('<a href="javascript:;" class="add-question font-green-soft" data-toggle="tooltip" data-trigger="hover" title="เพิ่มคำถาม">')
															.append($('<i class="fa fa-plus">'))
															.append('&nbsp;')
															)
													.append('&nbsp;')
													.append('&nbsp;')
													.append($('<a href="javascript:;" class="edit-group-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขกลุ่มคำถาม" >')
															.append($('<i class="fa fa-pencil">'))
															.append('&nbsp;')
															)
													.append('&nbsp;')
													.append('&nbsp;')
													.append($('<a href="javascript:;"  class="remove-group-question font-red-thunderbird" data-toggle="tooltip" data-placement="left" data-trigger="hover" title="ลบกลุ่มคำถาม"><i class="fa fa-times"></i> </a>'))
													.append($('<p class="uniqueGroupQuestion hide">'+objForm.groupQuestion.uniqueId+'</p>'))
													)
											
											.append($('<h5 class="form-section form-section-left name-group-question"> '+objForm.groupQuestion.name+' : '+objForm.groupQuestion.description+'  </h5>'))
																																	
											.append($('<div class="form-group form-body-collapse name-question-section" style="margin-left:30px;">')
													.append($('<ul>')
															.append($(htmlLiQuestion))
															)
													)									
											)
									)
							);
					
				}
				
			}
		}
		
	});
	

	//collpseAndExpand.init();
	return checkDupplicateTopic;
	
}


function submitToDB(objForm){
	
	//objForm['template'] = {name : $('#name_template').val().trim(),description:""};
	arrayQuestion = [];
	$.each(objForm.question,function(i,val){
		arrayQuestion.push({questionDetail : val.name, statusId : '1', uniqueId : val.uniqueId});
	});
	objToDB = {
		groupQuestion : {questionGroupTitle : objForm.groupQuestion.name, questionGroupDetail : objForm.groupQuestion.description, statusId : '1',uniqueId : objForm.groupQuestion.uniqueId},
		subTopic : {subTopicTitle : objForm.subTopic.name, subTopicDetail : objForm.subTopic.description, statusId : '1', uniqueId : objForm.subTopic.uniqueId},
		template : {title : objForm.template.name, detail : objForm.template.description, statusId : '1', uniqueId : objForm.template.uniqueId},
		topic : {topicTitle : objForm.topic.name, topicDetail : objForm.topic.description, statusId : '1', uniqueId : objForm.topic.uniqueId},
		question : arrayQuestion
	}
	
	$.ajax({
		url : contextPath+'/api/template/insert_template/',
		type: 'POST',
	    contentType: 'application/json',
	    data : JSON.stringify(objToDB),
	    beforeSend : function(arr, $form, options){
	    	BlockUi.blockPage();
	    },
	    complete : function(){
	    	BlockUi.unBlockPage();
	    },
		success : function(data) {			
			if(data.result){
				loadEval.init();
				customMessageDialog("success", "สถานะการดำเนินการ", data.message, null);
			}else{
				customMessageDialog("warning", "สถานะการดำเนินการ", data.message, null);
			} 	  
		},
		error: function (jqXHR, textStatus, errorThrown) {
			customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseJSON.message, null);
		}
	});
}

var modalEdit = function(){
	return{
		initial : function(object){
			switch (object.action) {
				case 'question' :
					$('#dialog_edit_editor .modal-title').html('แก้ไขคำถาม');
					$('#dialog_edit_editor #label_name_edit').text('คำถาม');
					$('#dialog_edit_editor #label_description_edit').text('คำถามภาษาอังกฤษ');
					editor.setText("#dialog_edit_editor #name_edit_question",object.value1.trim());
					$("#dialog_edit_editor #unique_edit_question").val(object.value2.trim());
					$('#dialog_edit_editor').modal('show');
					break;
					
				case 'groupQuestion' :
					$('#dialog_edit #name_edit').removeClass('txtEditor');
					$('#dialog_edit .modal-title').html('แก้ไขกลุ่มคำถาม');
					$('#dialog_edit #label_name_edit').text('ชื่อกลุ่มคำถาม');
					$('#dialog_edit #name_edit').val(object.value1);
					$('#dialog_edit #label_description_edit').text('รายละเอียดกลุ่มคำถาม');
					$('#dialog_edit #description_edit').val(object.value2);
					$('#dialog_edit #unique_edit').val(object.value3)
					$('#dialog_edit').modal('show');
					break;
					
				case 'subTopic' : 
					$('#dialog_edit .modal-title').html('แก้ไขหัวข้อย่อย');
					$('#dialog_edit #label_name_edit').text('ชื่อหัวข้อย่อย');
					$('#dialog_edit #name_edit').val(object.value1);
					$('#dialog_edit #label_description_edit').text('รายละเอียดหัวข้อย่อย');
					$('#dialog_edit #description_edit').val(object.value2);
					$('#dialog_edit #unique_edit').val(object.value3)
					$('#dialog_edit').modal('show');
					break;
					
				case 'topic' :
					$('#dialog_edit .modal-title').html('แก้ไขหัวข้อ');
					$('#dialog_edit #label_name_edit').text('ชื่อหัวข้อ');
					$('#dialog_edit #name_edit').val(object.value1);
					$('#dialog_edit #label_description_edit').text('รายละเอียดหัวข้อ');
					$('#dialog_edit #description_edit').val(object.value2);
					$('#dialog_edit #unique_edit').val(object.value3)
					$('#dialog_edit').modal('show');
					break;
					
				default :
					
			};
			
		}
	}
}();


function getDetailTemplate(evalTemplateId,callback){
	let returnval = '';
	$.ajax({
		url : contextPath + "/api/template/get_template_by_id/",
		type : 'POST',
		data : JSON.stringify(evalTemplateId),
		contentType : "application/json",
		beforeSend : function(arr, $form, options){
			BlockUi.blockPage();
	    },
	    complete : function(){
	    	BlockUi.unBlockPage();
	    },
		success : function(data) {				
			returnval = JSON.stringify(data);	
			loadEval.init();
			callback(returnval);
		},
		error: function (jqXHR, textStatus, errorThrown) {
		  customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
		}
	});	
	
}

function deleteElement(uniqueId,element){
	
	$.ajax({
		url : contextPath + "/api/template/delete_template_unique_id/",
		type : 'DELETE',
		data : uniqueId.toString(),
		contentType : "application/json",
		beforeSend : function(arr, $form, options){
			BlockUi.blockPage();
	    },
	    complete : function(){
	    	BlockUi.unBlockPage();
	    },
		success : function(data) {				
			if(data.result){				
				if(element != null){
					customMessageDialog("success", "สถานะการดำเนินการ", data.message, null);
					element.slideUp('slow', function() { $(this).remove(); });
				}				
			}else{
				customMessageDialog("warning", "สถานะการดำเนินการ", data.message, null);
			} 
		},
		error: function (jqXHR, textStatus, errorThrown) {
		  customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
		}
	});	
}

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
				$("#form_wizard").find(".button-previous").show();
				$("#form_wizard").find(".button-next").show();
				
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
					$("#form_wizard").find(".button-previous").hide();
					pageFormWizard = 1;
				}
				$("#form_wizard").bootstrapWizard('show',pageFormWizard-1);
				$(".step-title", $("#form_wizard")).text("ขั้นตอนที่ "+pageFormWizard+" จาก 5");
				
				$("#form_wizard li").each(function(index) {
					 if(index < pageFormWizard-1)
						 $(this).addClass("done");		     
				});
				
			}catch(error){
				console.log(error);
				pageFormWizard = 1;
				$("#form_wizard").find(".button-previous").hide();
			}
			
		},
		
		editElement : function(_element){
			var pageFormWizard = 1;
			try{
				
				if(_element.hasClass('edit-topic')){
					pageFormWizard = 1;
					genFormWizardFromElement.findData(_element);
				}		
				else if(_element.hasClass('edit-sub-topic')){
					pageFormWizard = 2;
					genFormWizardFromElement.findData(_element);
				}	
				else if(_element.hasClass('edit-group-question')){
					pageFormWizard = 3;
					genFormWizardFromElement.findData(_element);
				}				
				else if(_element.hasClass('edit-question')){
					pageFormWizard = 4;
					genFormWizardFromElement.findData(_element);
				}
				else{
					$("#form_wizard").find(".button-previous").hide();
					pageFormWizard = 1;
					genFormWizardFromElement.findData(_element);
				}
				
			}catch(Exception){
				pageFormWizard = 1;
			}			
			
		},
		
		findData : function(_ele){
			var resultObj = {
				topic : {name : '',description : ''},
				subTopic : {name : '',description : ''},
				groupQuestion : {name : '',description : ''},
				question : {name : '',description : ''}
			};
			try{
				
				var elementPortlet = _ele.closest('.portlet-summary');
				var topicTmp = elementPortlet.find('.portlet-title').find('.name-topic-section').text().split(":");
				var nameTopic = topicTmp[0].trim();
				var descriptionTopic = topicTmp[1].trim();
				
				resultObj.topic.name = nameTopic;
				resultObj.topic.description = descriptionTopic;
				
				
				
			}catch(e){
				console.log(e);
			}
			
			console.log(resultObj);
			
		}
		
	}
	
		
}();



var loadEval = function(){
	return{
		init : function(){
			//loadEval.template();
			loadEval.topic();
			loadEval.subTopic();
			loadEval.groupQuestion();
		},
		template : function(){
			$.ajax({
				url: contextPath + "/api/template/get_template_list",
			    type: 'GET',
			    async: true,
			    contentType: 'application/json; charset=UTF-8',
			    success : function(data, msg, jqXHR) {
			    	 	 
			    	let dataSource = [];
			    	$.each(data,function(i,value){
			    		dataSource.push({
			    			id : value.evalTemplateId,
			    			name : value.title
			    		});
			    	});
			    	autoComplete.init('#name_template',dataSource);
				},
				error: function (jqXHR, textStatus, errorThrown) {
					customMessageDialog("error", "เกิดข้อผิดพลาด", errorThrown, null);
			    },
			});
		},
		topic : function(){
			$.ajax({
				url: contextPath + "/api/eval_topic/get_eval_topic",
			    type: 'GET',
			    async: true,
			    contentType: 'application/json; charset=UTF-8',
			    success : function(data, msg, jqXHR) { 	 
			    	let dataSource = [];
			    	$.each(data,function(i,value){
			    		dataSource.push({
			    			id : value.evalTopicId,
			    			name : value.topicTitle,
			    			detail : value.topicDetail,
			    			type : 2
			    		});
			    	});
			    	autoComplete.init('#name_topic',dataSource);
				},
				error: function (jqXHR, textStatus, errorThrown) {
					customMessageDialog("error", "เกิดข้อผิดพลาด", errorThrown, null);
			    },
			});
		},
		subTopic : function(){
			$.ajax({
				url: contextPath + "/api/eval_sub_topic/get_eval_sub_topic",
			    type: 'GET',
			    async: true,
			    contentType: 'application/json; charset=UTF-8',
			    success : function(data, msg, jqXHR) { 	 
			    	let dataSource = [];
			    	$.each(data,function(i,value){
			    		dataSource.push({
			    			id : value.evalSubTopicId,
			    			name : value.subTopicTitle,
			    			detail : value.subTopicDetail,
			    			type : 3
			    		});
			    	});
			    	autoComplete.init('#name_sub_topic',dataSource);
				},
				error: function (jqXHR, textStatus, errorThrown) {
					customMessageDialog("error", "เกิดข้อผิดพลาด", errorThrown, null);
			    },
			});
		},
		groupQuestion : function(){
			$.ajax({
				url: contextPath + "/api/eval_group_question/get_eval_group_question",
			    type: 'GET',
			    async: true,
			    contentType: 'application/json; charset=UTF-8',
			    success : function(data, msg, jqXHR) { 	
			    	let dataSource = [];
			    	$.each(data,function(i,value){
			    		dataSource.push({
			    			id : value.questionGroupId,
			    			name : value.questionGroupTitle,
			    			detail : value.questionGroupDetail,
			    			type : 4
			    		});
			    	});
			    	autoComplete.init('#name_group_question',dataSource);
				},
				error: function (jqXHR, textStatus, errorThrown) {
					customMessageDialog("error", "เกิดข้อผิดพลาด", errorThrown, null);
			    },
			});
		}
	}
}();

