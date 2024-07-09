var colorListTimeline = {Critical : "danger", Major : "warning", Minor : "primary", Observe : "success", Comment : "brand", Conform : "accent", "N/A" : "metal"};
var checklist = function(){
	return{
		generateElements : function(objForm){
			
			let checkDupplicateTopic = checklist.DupplicateElement(objForm);	
			if(!checkDupplicateTopic){
				let elementTemplate = $('.portlet-body-summary').find('.portlet-template');
				elementTemplate.find('.name-topic-section').text(objForm.topic.title+" : "+objForm.topic.detail);
				elementTemplate.find('.actions').find('.uniqueTopic').text(objForm.topic.uniqueId);
				elementTemplate.find('.portlet-body').find('.name-sub-topic').text(objForm.subTopic.title+" : "+objForm.subTopic.detail);
				elementTemplate.find('.portlet-body').find('.name-sub-topic').prev().find('.uniqueSubTopic').text(objForm.subTopic.uniqueId);
				elementTemplate.find('.portlet-body').find('.group-question').find('.name-group-question').html(objForm.groupQuestion.title+" : "+objForm.groupQuestion.detail);
				elementTemplate.find('.portlet-body').find('.group-question').find('.uniqueGroupQuestion').text(objForm.groupQuestion.uniqueId);
				let htmlLi = '';
				
				$.each(objForm.question,function(index,objQuestion){
					if(!$.isEmptyObject(objQuestion)){
						htmlLi += checklist.generateQuestionHtml(objQuestion);
						/*htmlLi += '<li class="question-list">';
						htmlLi += '<div class="form-group">';
						htmlLi += objQuestion.name + '&nbsp;&nbsp;';
						htmlLi += '<a href="javascript:;" class="edit-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>&nbsp;';
						htmlLi += '<a href="javascript:;" data-toggle="tooltip" data-trigger="hover" title="ลบคำถาม" class="remove-question font-red-thunderbird"><i class="fa fa-times"></i> </a>&nbsp;';
						htmlLi += '<a class="font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย"><i class="fa fa-angle-down fa-lg rotate-icon"></i></a>&nbsp;';
						htmlLi += '<p class="uniqueQuestion hide">'+objQuestion.uniqueId+'</p>';
						htmlLi += '<div class="form-body-collapse">';
						htmlLi += '<ul>';
						htmlLi += '<li>';
						htmlLi += '<span>'+objQuestion.questionTypeName+'</span>';
						htmlLi += '<p class="questionTypeId hide">'+objQuestion.questionTypeId+'</p>';
						htmlLi += '<div class="m-list-timeline m-list-timeline--skin-light">';
						htmlLi += '<div class="m-list-timeline__items">';
						htmlLi += '<div class="row">';
						$(objQuestion.answer).each(function(i,objAnswer){
							htmlLi += '<div class="col-md-3">';
							htmlLi += '<div class="m-list-timeline__item">';
							htmlLi += '<span class="m-list-timeline__badge m-list-timeline__badge--'+colorListTimeline[objAnswer.answerName]+'"></span>';
							htmlLi += '<span class="m-list-timeline__text answerName">'+objAnswer.answerName+'</span>';
							htmlLi += '<span class="m-list-timeline__time answerId hide">'+objAnswer.answerId+'</span>';
							htmlLi += '</div>';
							htmlLi += '</div>';
						});
						htmlLi += '</div>';
						htmlLi += '</div>';
						htmlLi += '</div>';
						htmlLi += '</li>';
						htmlLi += '</ul>';
						htmlLi += '</div>';
						htmlLi += '</div>';
						htmlLi += "</li>";*/
					}
					//if(val !="")
						//htmlLi += "<li>"+val.name+'&nbsp;&nbsp;<a href="javascript:;" class="edit-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>&nbsp;<a href="javascript:;" data-toggle="tooltip" data-trigger="hover" title="ลบคำถาม" class="remove-question font-red-thunderbird"><i class="fa fa-times"></i> </a><p class="uniqueQuestion hide">'+val.uniqueId+'</p>'+"</li>";
				});
				
				elementTemplate.find('.portlet-body').find('.sub-topic-section').find('.group-question').find('.name-question-section').find('ul').html(htmlLi);
				
				var cloneObj = $('.portlet-template').clone().removeClass('hide').removeClass('portlet-template').addClass('portlet-summary');
				if(objForm.subTopic.title == ''){
					cloneObj.find('.portlet-body').find('.name-sub-topic').prev().remove();
					cloneObj.find('.portlet-body').find('.name-sub-topic').remove();
					cloneObj.find('.portlet-title').find('.actions').find('.dropdown').find('.add-sub-topic').closest('li').remove();
				}else{
					cloneObj.find('.portlet-title').find('.actions').find('.dropdown').find('.add-group-question').closest('li').remove();
				}
				if(objForm.groupQuestion.title == ''){
					cloneObj.find('.portlet-body').find('.name-group-question').closest('.form-group').remove();
				}
				
				$('.portlet-body-summary')
					.append($('<div class="row">')
							.append($('<div class="col-md-12">')
									.append(cloneObj)
									)
							);
				
			}
			
			if($('#portlet-checklist-section').find('.portlet-title').find('.actions').find('a[data-toggle="collapse"]').hasClass('collapsed')){
				$('#portlet-checklist-section').find('.portlet-title').find('.actions').find('a[data-toggle="collapse"]').click();
			}
			toolTips.init();
			collpseAndExpand.init();
			
		},
		DupplicateElement : function(objForm){
			var checkDupplicateTopic = false;
			var checkDupplicateSubTopic = false;
			var checkDupplicateQuestionGroup = false;

			var elementPortlet = $('.portlet-body-summary').find('.portlet-summary');
			//console.log(elementPortlet);
			$.each(elementPortlet,function(index,value){		
				var topicElement = $(this).find('.name-topic-section');
				var tmpTopic = topicElement.text().trim().split(":");
				
				if(tmpTopic[0].trim().toLowerCase() == objForm.topic.title.toLowerCase()){
					checkDupplicateTopic = true;
					var subTopicElementList = $(this).find('.sub-topic-section').find('.name-sub-topic');
					$.each(subTopicElementList,function(){
						var tmpSubTopic = $(this).text().trim().split(":");
						if(tmpSubTopic[0].trim() == objForm.subTopic.title){
							checkDupplicateSubTopic = true;
							var questionGroupList = $(this).closest('.form-group').find('.group-question').find('.name-group-question');
							$.each(questionGroupList,function(index,value){
								var tmpGroupQuestion = $(this).text().trim().split(":");
								if(tmpGroupQuestion[0].trim() == objForm.groupQuestion.title){							
									checkDupplicateQuestionGroup = true;
									var htmlLiQuestion = ""; 
									//var questionList = $(this).closest('.group-question').find('.name-question-section').find('ul').find('li');
									var questionList = $(this).next().find('ul').find('li.question-list');
									$.each(objForm.question,function(index,val){
										if(val !=""){
											var checkDuppQuestion = false;
											$.each(questionList,function(){
												var questionTmp = $(this).text().split(" ");
												if(questionTmp[0].trim() == val.detail){
													checkDuppQuestion = true;
												}
											});
											
											if(!checkDuppQuestion){
												htmlLiQuestion += checklist.generateQuestionHtml(val);
											}
												//htmlLiQuestion += "<li>"+val.name+'&nbsp;&nbsp;<a href="javascript:;" class="edit-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>&nbsp;<a href="javascript:;" data-toggle="tooltip" data-trigger="hover" title="ลบคำถาม" class="remove-question font-red-thunderbird" ><i class="fa fa-times"></i> </a><p class="uniqueQuestion hide">'+val.uniqueId+'</p>'+"</li>";
																					
										}								
										
											
									});
									$(this).next().find('ul.question-list-group').append(htmlLiQuestion);
									//$(this).closest('.group-question').find('.name-question-section').find('ul').append(htmlLiQuestion);
									
								}
							});
							
							if(!checkDupplicateQuestionGroup){
								var htmlLiQuestion = ""; 
								$.each(objForm.question,function(index,val){
									//if(val !="")
										//htmlLiQuestion += "<li>"+val.name+'&nbsp;&nbsp;<a href="javascript:;" class="edit-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>&nbsp;<a href="javascript:;" data-toggle="tooltip" data-trigger="hover" title="ลบคำถาม" class="remove-question font-red-thunderbird" ><i class="fa fa-times"></i> </a><p class="uniqueQuestion hide">'+val.uniqueId+'</p>'+"</li>";
									htmlLiQuestion += checklist.generateQuestionHtml(val);
								});
								$(this).closest('.form-group').find('.group-question').append($('<div class="form-group">')
										
										.append($('<div class="form-section-right">')
												.append($('<a class="font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">')
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
										
										.append($('<h5 class="form-section form-section-left name-group-question"> '+objForm.groupQuestion.title+' : '+objForm.groupQuestion.detail+'  </h5>'))
										
										.append($('<div class="form-group form-body-collapse name-question-section" style="margin-left:30px;">')
												.append($('<ul class="question-list-group" style="margin-top:5px;">').append(htmlLiQuestion))
												)
										);
								
								//$(this).find('.group-question').find('.name-question-section').find('<ul>').html(htmlLiQuestion);
							}
						}
					});
					if(!checkDupplicateSubTopic){
						var htmlLiQuestion = ""; 				
						
						if(objForm.subTopic.title == ''){
							var subTopicSection = $(this).find('.portlet-body').find('.sub-topic-section');
							var DuppGroupQuestion = false;
							$.each(subTopicSection.children(),function(){
								var tmpGroupQuestion = $(this).find('.name-group-question').text().trim().split(":");
								if(tmpGroupQuestion[0].trim() == objForm.groupQuestion.title){
									DuppGroupQuestion = true;
									var questionList = $(this).find('.name-question-section').find('ul').find('li');
									$.each(objForm.question,function(index,val){
										if(val !=""){
											var checkDuppQuestion = false;
											$.each(questionList,function(){
												var questionTmp = $(this).text().split(" ");
												if(questionTmp[0].trim() == val.detail){
													checkDuppQuestion = true;
												}
											});
											
											if(!checkDuppQuestion)
												htmlLiQuestion += checklist.generateQuestionHtml(val);
												//htmlLiQuestion += "<li>"+val.name+'&nbsp;&nbsp;<a href="javascript:;" class="edit-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>&nbsp;<a href="javascript:;" data-toggle="tooltip" data-trigger="hover" title="ลบคำถาม" class="remove-question font-red-thunderbird" ><i class="fa fa-times"></i> </a><p class="uniqueQuestion hide">'+val.uniqueId+'</p>'+"</li>";					
										}	
									});
									
									$(this).find('.name-question-section').find('ul.question-list-group').append(htmlLiQuestion);
								}
							});
							
							if(!DuppGroupQuestion){
								$.each(objForm.question,function(index,val){
									//if(val !="")
										//htmlLiQuestion += "<li>"+val.name+'&nbsp;&nbsp;<a href="javascript:;" class="edit-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>&nbsp;<a href="javascript:;" data-toggle="tooltip" data-trigger="hover" title="ลบคำถาม" class="remove-question font-red-thunderbird" ><i class="fa fa-times"></i> </a><p class="uniqueQuestion hide">'+val.uniqueId+'</p>'+"</li>";
									htmlLiQuestion += checklist.generateQuestionHtml(val);
								});
								subTopicSection
								.append($('<div class="form-group">')
										.append($('<div class="group-question" style="margin-left:30px;">')
												.append($('<div class="form-group">')
																						
														.append($('<div class="form-section-right">')
																.append($('<a class="font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">')
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
														
														.append($('<h5 class="form-section form-section-left name-group-question"> '+objForm.groupQuestion.title+' : '+objForm.groupQuestion.detail+'  </h5>'))
																										
														.append($('<div class="form-group form-body-collapse name-question-section" style="margin-left:30px;">')
																.append($('<ul class="question-list-group" style="margin-top:5px;">')
																		.append($(htmlLiQuestion))
																		)
																)
														)										
												)
										);
							}
							
							
							
						}else{
							$.each(objForm.question,function(index,val){
								//if(val !="")
									//htmlLiQuestion += "<li>"+val.name+'&nbsp;&nbsp;<a href="javascript:;" class="edit-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>&nbsp;<a href="javascript:;" data-toggle="tooltip" data-trigger="hover" title="ลบคำถาม" class="remove-question font-red-thunderbird" ><i class="fa fa-times"></i> </a><p class="uniqueQuestion hide">'+val.uniqueId+'</p>'+"</li>";
								htmlLiQuestion += checklist.generateQuestionHtml(val);
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
									.append($('<h4 class="form-section bold font-blue-soft name-sub-topic"> '+objForm.subTopic.title+' : '+objForm.subTopic.detail+'  </h4>'))
									.append($('<div class="group-question" style="margin-left:30px;">')
											.append($('<div class="form-group">')
													.append($('<div class="form-section-right">')
															.append($('<a class="font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย">')
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
													
													.append($('<h5 class="form-section form-section-left name-group-question"> '+objForm.groupQuestion.title+' : '+objForm.groupQuestion.detail+'  </h5>'))
																																			
													.append($('<div class="form-group form-body-collapse name-question-section" style="margin-left:30px;">')
															.append($('<ul class="question-list-group" style="margin-top:5px;">')
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
			
		},
		getChecklistDisplay : function(){

			let evalForm = [];
			if($('.portlet-summary').length > 0){
				$('.portlet-summary').each(function(){
					if($(this).find('.name-topic-section').length > 0){
						$($(this).find('.name-topic-section')).each(function(){
							let topicSplit =  $(this).text().split(':');
							let nameTopic = '', detailTopic = '';
							if(topicSplit.length < 2){
								nameTopic = topicSplit[0];
							}else{
								nameTopic = topicSplit[0];
								detailTopic = topicSplit[1];
							}
							let topicObj = {
								title : nameTopic.trim(),
								detail : detailTopic.trim(),
								uniqueId : $(this).parent().next().find('.uniqueTopic').text().trim(),
								evalTypeId : {evalTypeId :"2"},
								questionTypeId : {questionTypeId : "0"},
								requireAnwser : 'N',
								uniqueIdParent : '0'
							};
							evalForm.push(topicObj);
							
							let subTopicSection = $(this).closest('.portlet-summary').find('.portlet-body').find('.sub-topic-section');
							if(subTopicSection.find('.name-sub-topic').length > 0){
								$(subTopicSection.find('.name-sub-topic')).each(function(){
									//console.log($(this).text());
									//let subTopicSplit =  $(this).find('.name-sub-topic').text().split(':');
									let subTopicSplit =  $(this).text().split(':');
									let nameSubTopic = '', detailSubTopic = '';
									if(subTopicSplit.length < 2){
										nameSubTopic = subTopicSplit[0];
									}else{
										nameSubTopic = subTopicSplit[0];
										detailSubTopic = subTopicSplit[1];
									}
									let subTopicObj = {
										title : nameSubTopic.trim(),
										detail : detailSubTopic.trim(),
										uniqueId : $(this).parent().find('.uniqueSubTopic').text().trim(),
										evalTypeId : {evalTypeId :"3"},
										questionTypeId : {questionTypeId : "0"},
										requireAnwser : 'N',
										uniqueIdParent : topicObj.uniqueId
									};
									evalForm.push(subTopicObj);
									
									if($(this).parent().find('.group-question').find('.name-group-question').length > 0){
										$($(this).parent().find('.group-question').find('.name-group-question')).each(function(){
											
											//let groupQuestionSplit =  $(this).find('.name-group-question').text().split(':');
											let groupQuestionSplit =  $(this).text().split(':');
											let nameGroupQuestion = '', detailGroupQuestion = '';
											if(groupQuestionSplit.length < 2){
												nameGroupQuestion = groupQuestionSplit[0];
											}else{
												nameGroupQuestion = groupQuestionSplit[0];
												detailGroupQuestion = groupQuestionSplit[1];
											}
											let groupQuestionObj = {
												title : nameGroupQuestion.trim(),
												detail : detailGroupQuestion.trim(),
												uniqueId : $(this).prev().find('.uniqueGroupQuestion').text().trim(),
												evalTypeId : {evalTypeId :"4"},
												questionTypeId : {questionTypeId : "0"},
												requireAnwser : 'N',
												uniqueIdParent : subTopicObj.uniqueId
											};
											evalForm.push(groupQuestionObj);
											
											let questionSection = $(this).next().find('.question-list-group li.question-list');
											if(questionSection.length > 0){
												$(questionSection).each(function(){
													//let evalFormArray = [];
													if($(this).find('.form-body-collapse').find('.item_answer').length > 0){
														let answerArray = [];
														$($(this).find('.form-body-collapse').find('.item_answer')).each(function(){
															answerArray.push({
																answerId : $(this).find('.answerId').text().trim(),
																answerDetail : $(this).find('.answerName').text().trim()
															});
														});
														
														let questionObj = {
															title : '',
															detail : $(this).find('.question-name').html().trim(),
															uniqueId : $(this).find('.uniqueQuestion').text().trim(),
															evalTypeId : {evalTypeId :"5"},
															questionTypeId : {questionTypeId : $(this).find('.form-body-collapse').find('.questionTypeId').text().trim()},
															requireAnwser : 'N',
															answerId : answerArray,
															uniqueIdParent : groupQuestionObj.uniqueId
														};
														
														evalForm.push(questionObj)
													}else{
														customMessageDialog('warning', 'เตือนการตรวจสอบข้อมูล', 'กรุณาเลือกคำตอบให้ครบถ้วน', null);
														evalForm = [];
													}
													
																										
													/*evalFormArray.push(topicObj);	
													evalFormArray.push(subTopicObj);
													evalFormArray.push(groupQuestionObj);
													evalFormArray.push(questionObj);
													evalForm.push(evalFormArray);*/
												});
											}else{
												customMessageDialog('warning', 'เตือนการตรวจสอบข้อมูล', 'กรุณาสร้างคำถามให้ครบถ้วน', null);
												evalForm = [];
											}											
										});
									}else{
										customMessageDialog('warning', 'เตือนการตรวจสอบข้อมูล', 'กรุณาสร้างกลุ่มคำถามให้ครบถ้วน', null);
										evalForm = [];
									}
								});
							}else{
																
								if($(subTopicSection).find('.group-question').find('.name-group-question').length > 0){
									$($(subTopicSection).find('.group-question').find('.name-group-question')).each(function(){
										
										//let groupQuestionSplit =  $(this).find('.name-group-question').text().split(':');
										let groupQuestionSplit =  $(this).text().split(':');
										let nameGroupQuestion = '', detailGroupQuestion = '';
										if(groupQuestionSplit.length < 2){
											nameGroupQuestion = groupQuestionSplit[0];
										}else{
											nameGroupQuestion = groupQuestionSplit[0];
											detailGroupQuestion = groupQuestionSplit[1];
										}
										let groupQuestionObj = {
											title : nameGroupQuestion.trim(),
											detail : detailGroupQuestion.trim(),
											uniqueId : $(this).prev().find('.uniqueGroupQuestion').text().trim(),
											evalTypeId : {evalTypeId :"4"},
											questionTypeId : {questionTypeId : "0"},
											requireAnwser : 'N',
											uniqueIdParent : topicObj.uniqueId
										};
										evalForm.push(groupQuestionObj);
										
										let questionSection = $(this).next().find('.question-list-group').find('li.question-list');
										
										if(questionSection.length > 0){
											$(questionSection).each(function(){
												
												if($(this).find('.form-body-collapse').find('.item_answer').length > 0){
													let answerArray = [];
													$($(this).find('.form-body-collapse').find('.item_answer')).each(function(){
														answerArray.push({
															answerId : $(this).find('.answerId').text().trim(),
															answerDetail : $(this).find('.answerName').text().trim()
														});
													});
													
													let questionObj = {
														title : '',
														detail : $(this).find('.question-name').html().trim(),
														uniqueId : $(this).find('.uniqueQuestion').text().trim(),
														evalTypeId : {evalTypeId :"5"},
														questionTypeId : {questionTypeId : $(this).find('.form-body-collapse').find('.questionTypeId').text().trim()},
														requireAnwser : 'N',
														answerId : answerArray,
														uniqueIdParent : groupQuestionObj.uniqueId
													};
																										
													/*evalFormArray.push(topicObj);	
													evalFormArray.push(groupQuestionObj);
													evalFormArray.push(questionObj);*/
													evalForm.push(questionObj);
												}else{
													customMessageDialog('warning', 'เตือนการตรวจสอบข้อมูล', 'กรุณาเลือกคำตอบให้ครบถ้วน', null);
													evalForm = [];
												}
												
											});
										}else{
											customMessageDialog('warning', 'เตือนการตรวจสอบข้อมูล', 'กรุณาสร้างคำถามให้ครบถ้วน', null);
											evalForm = [];
										}										
									});
								}else{									
									customMessageDialog('warning', 'เตือนการตรวจสอบข้อมูล', 'กรุณาสร้างกลุ่มคำถามให้ครบถ้วน', null);
									evalForm = [];									
								}
								
							}
						});
					}else{
						customMessageDialog('warning', 'เตือนการตรวจสอบข้อมูล', 'กรุณาสร้างหัวข้อให้ครบถ้วน', null);
						evalForm = [];						
					}
				});
				return evalForm;
			}else{	
				customMessageDialog('warning', 'เตือนการตรวจสอบข้อมูล', 'กรุณาสร้างข้อตรวจรายการเช็คลิสต์', null);
				return [];
			}			
		
		},
		getChecklistDisplay2 : function(){
			let evalForm = [];
			if($('.portlet-summary').length > 0){
				$('.portlet-summary').each(function(){
					if($(this).find('.name-topic-section').length > 0){
						$($(this).find('.name-topic-section')).each(function(){
							let topicSplit =  $(this).text().split(':');
							let nameTopic = '', detailTopic = '';
							if(topicSplit.length < 2){
								nameTopic = topicSplit[0];
							}else{
								nameTopic = topicSplit[0];
								detailTopic = topicSplit[1];
							}
							let topicObj = {
								title : nameTopic.trim(),
								detail : detailTopic.trim(),
								uniqueId : $(this).parent().next().find('.uniqueTopic').text().trim(),
								evalTypeId : {evalTypeId :"2"},
								questionTypeId : {questionTypeId : "0"},
								requireAnwser : 'N'								
							};
							let subTopicSection = $(this).closest('.portlet-summary').find('.portlet-body').find('.sub-topic-section');
							if(subTopicSection.find('.name-sub-topic').length > 0){
								$(subTopicSection.find('.name-sub-topic')).each(function(){
									//console.log($(this).text());
									//let subTopicSplit =  $(this).find('.name-sub-topic').text().split(':');
									let subTopicSplit =  $(this).text().split(':');
									let nameSubTopic = '', detailSubTopic = '';
									if(subTopicSplit.length < 2){
										nameSubTopic = subTopicSplit[0];
									}else{
										nameSubTopic = subTopicSplit[0];
										detailSubTopic = subTopicSplit[1];
									}
									let subTopicObj = {
										title : nameSubTopic.trim(),
										detail : detailSubTopic.trim(),
										uniqueId : $(this).parent().find('.uniqueSubTopic').text().trim(),
										evalTypeId : {evalTypeId :"3"},
										questionTypeId : {questionTypeId : "0"},
										requireAnwser : 'N'	
									};
									
									if($(this).parent().find('.group-question').find('.name-group-question').length > 0){
										$($(this).parent().find('.group-question').find('.name-group-question')).each(function(){
											
											//let groupQuestionSplit =  $(this).find('.name-group-question').text().split(':');
											let groupQuestionSplit =  $(this).text().split(':');
											let nameGroupQuestion = '', detailGroupQuestion = '';
											if(groupQuestionSplit.length < 2){
												nameGroupQuestion = groupQuestionSplit[0];
											}else{
												nameGroupQuestion = groupQuestionSplit[0];
												detailGroupQuestion = groupQuestionSplit[1];
											}
											let groupQuestionObj = {
												title : nameGroupQuestion.trim(),
												detail : detailGroupQuestion.trim(),
												uniqueId : $(this).prev().find('.uniqueGroupQuestion').text().trim(),
												evalTypeId : {evalTypeId :"4"},
												questionTypeId : {questionTypeId : "0"},
												requireAnwser : 'N'	
											};
											let questionSection = $(this).next().find('.question-list-group li.question-list');
											if(questionSection.length > 0){
												$(questionSection).each(function(){
													let evalFormArray = [];
													let answerArray = [];
													$($(this).find('.form-body-collapse').find('.item_answer')).each(function(){
														answerArray.push({
															answerId : $(this).find('.answerId').text().trim(),
															answerDetail : $(this).find('.answerName').text().trim()
														});
													});
													
													let questionObj = {
														title : '',
														detail : $(this).find('.question-name').html().trim(),
														uniqueId : $(this).find('.uniqueQuestion').text().trim(),
														evalTypeId : {evalTypeId :"5"},
														questionTypeId : {questionTypeId : $(this).find('.form-body-collapse').find('.questionTypeId').text().trim()},
														requireAnwser : 'N',
														answerId : answerArray
													};
																										
													evalFormArray.push(topicObj);	
													evalFormArray.push(subTopicObj);
													evalFormArray.push(groupQuestionObj);
													evalFormArray.push(questionObj);
													evalForm.push(evalFormArray);
												});
											}
											
										});
									}
								});
							}else{
																
								if($(subTopicSection).find('.group-question').find('.name-group-question').length > 0){
									$($(subTopicSection).find('.group-question').find('.name-group-question')).each(function(){
										
										//let groupQuestionSplit =  $(this).find('.name-group-question').text().split(':');
										let groupQuestionSplit =  $(this).text().split(':');
										let nameGroupQuestion = '', detailGroupQuestion = '';
										if(groupQuestionSplit.length < 2){
											nameGroupQuestion = groupQuestionSplit[0];
										}else{
											nameGroupQuestion = groupQuestionSplit[0];
											detailGroupQuestion = groupQuestionSplit[1];
										}
										let groupQuestionObj = {
											title : nameGroupQuestion.trim(),
											detail : detailGroupQuestion.trim(),
											uniqueId : $(this).prev().find('.uniqueGroupQuestion').text().trim(),
											evalTypeId : {evalTypeId :"4"},
											questionTypeId : {questionTypeId : "0"},
											requireAnwser : 'N'	
										};
										let questionSection = $(this).next().find('.question-list-group').find('li.question-list');
										console.log(questionSection)
										if(questionSection.length > 0){
											$(questionSection).each(function(){
												let evalFormArray = [];
												let answerArray = [];
												$($(this).find('.form-body-collapse').find('.item_answer')).each(function(){
													answerArray.push({
														answerId : $(this).find('.answerId').text().trim(),
														answerDetail : $(this).find('.answerName').text().trim()
													});
												});
												
												let questionObj = {
													title : '',
													detail : $(this).find('.question-name').html().trim(),
													uniqueId : $(this).find('.uniqueQuestion').text().trim(),
													evalTypeId : {evalTypeId :"5"},
													questionTypeId : {questionTypeId : $(this).find('.form-body-collapse').find('.questionTypeId').text().trim()},
													requireAnwser : 'N',
													answerId : answerArray
												};
																									
												evalFormArray.push(topicObj);	
												evalFormArray.push(groupQuestionObj);
												evalFormArray.push(questionObj);
												evalForm.push(evalFormArray);
											});
										}
										
									});
								}
								
							}
						});
					}
				});
				return evalForm;
			}else{				
				return evalForm;
			}			
		},
		generateQuestionHtml : function(objQuestion){
			let htmlLi = "";
			if(!$.isEmptyObject(objQuestion)){
				htmlLi += '<li class="question-list">';
				htmlLi += '<div class="form-group">';				
				htmlLi += '<div class="form-section-right">';
				htmlLi += '<a href="javascript:;" class="edit-question font-dark" data-toggle="tooltip" data-trigger="hover"  title="แก้ไขคำถาม" ><i class="fa fa-pencil"></i> </a>&nbsp;';
				htmlLi += '<a href="javascript:;" data-toggle="tooltip" data-trigger="hover" title="ลบคำถาม" class="remove-question font-red-thunderbird"><i class="fa fa-times"></i> </a>&nbsp;';
				htmlLi += '<a class="font-blue-ebonyclay" data-toggle="collapse" data-trigger="hover"  title="ย่อ/ขยาย"><i class="fa fa-angle-down fa-lg rotate-icon"></i></a>&nbsp;';
				htmlLi += '<p class="uniqueQuestion hide">'+objQuestion.uniqueId+'</p>';
				htmlLi += '</div>';
				htmlLi += '<div class="question-name form-section-left" style="margin: 5px 0;">'+objQuestion.detail +'</div>';
				htmlLi += '<div class="form-body-collapse">';
				htmlLi += '<ul>';
				htmlLi += '<li>';
				htmlLi += '<span>'+objQuestion.questionTypeId.name+'</span>';
				htmlLi += '<p class="questionTypeId hide">'+objQuestion.questionTypeId.questionTypeId+'</p>';
				htmlLi += '<div class="m-list-timeline m-list-timeline--skin-light">';
				htmlLi += '<div class="m-list-timeline__items">';
				htmlLi += '<div class="row">';
				$(objQuestion.answerId).each(function(i,objAnswer){
					htmlLi += '<div class="col-md-3">';
					htmlLi += '<div class="m-list-timeline__item item_answer">';
					htmlLi += '<span class="m-list-timeline__badge m-list-timeline__badge--'+colorListTimeline[objAnswer.answerDetail]+'"></span>';
					htmlLi += '<span class="m-list-timeline__text answerName">'+objAnswer.answerDetail+'</span>';
					htmlLi += '<span class="m-list-timeline__time answerId hide">'+objAnswer.answerId+'</span>';
					htmlLi += '</div>';
					htmlLi += '</div>';
				});
				htmlLi += '</div>';
				htmlLi += '</div>';
				htmlLi += '</div>';
				htmlLi += '</li>';
				htmlLi += '</ul>';
				htmlLi += '</div>';
				htmlLi += '</div>';
				htmlLi += "</li>";
			}
			return htmlLi;
		}
	}	
}();