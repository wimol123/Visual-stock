
var dataSourceQuestionType = [];

function getURLParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam)
        {
            return sParameterName[1];
        }
    }
	return "";
}

function isValidEmail(emailAddress) {
    //var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
    var pattern = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    return pattern.test(emailAddress);
}

function getFileSizeByUrl(url){
	let request = $.ajax({
        type: "HEAD",
        async: false,
        cache:false,
        url: url,
        crossDomain:false,
        success: function () {}
    });
	let filsize = formatSizeUnits(request.getResponseHeader("Content-Length"));
	
}

function formatSizeUnits(bytes){
    if      (bytes>=1073741824) {bytes=(bytes/1073741824).toFixed(2)+' GB';}
    else if (bytes>=1048576)    {bytes=(bytes/1048576).toFixed(2)+' MB';}
    else if (bytes>=1024)       {bytes=(bytes/1024).toFixed(2)+' KB';}
    else if (bytes>1)           {bytes=bytes+' bytes';}
    else if (bytes==1)          {bytes=bytes+' byte';}
    else                        {bytes='0 byte';}
    return bytes;
}

$(document).ready(function () {
	
	BlockUi.init();
	FormRepeater.init();
	portletOption.fullScreen();
	collpseAndExpand.init();
	collpseAndExpand.onClick();
	toolTips.init();
	editor.init();
	
	
  $(".require_interger").keypress(function (e) {
     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
     return false;
    }
   });
  
  $.validator.addMethod("pwcheckconsists", function(value) {
		return /^[A-Za-z0-9\d=!\*!@$%^]*$/.test(value) // consists of only these
		/*&& /[a-z]/.test(value) // has a lowercase letter
		&& /[A-Z]/.test(value) // has a upercase letter
		&& /\d/.test(value) // has a digit
*/	});
  $.validator.addMethod("pwchecklowercase", function(value) {
		return /[a-z]/.test(value) 
  });
  
  $.validator.addMethod("pwcheckuppercase", function(value) {
		return /[A-Z]/.test(value) 
  });
  
  $.validator.addMethod("pwcheckdigit", function(value) {
		return /\d/.test(value)
  });
  
  $.validator.addMethod("pwcheckspacialcharactor",function(value){
	  return /[*!@$%^]/.test(value);
  });
  
  $.validator.addMethod("validateEmail", function(value, element) {
	  //return /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
	  if (isValidEmail(value))
		  return true;
	  else
		  return false;
  }, 
  	"กรุณาใส่อีเมล์ให้ถูกต้อง");
  
   $.validator.addMethod("pwcheck1n1c", function(value) {
		return /^(?=.*\d)(?=.*[a-z,A-Z]).{12,}$/.test(value)
	});
});



function getQuestionTypeList(){
	 //let url = contextPath+'/api/question_type/question_type_list';
	let url = contextPath+'/api/answer/answer_list_grade_calculator/'+JSON.stringify({
		checklistTypeId : 1
	});
		ajaxProcess.submit(url,'GET',null,null,(data)=>{
			if(data.result){
				
				let objQuestionType = JSON.parse(data.message);
				
				dataSourceQuestionType = [];
				$(objQuestionType).each(function(i,v){
					dataSourceQuestionType.push({
		    			id : v.questionTypeId,
		    			text  : v.name
		    		});
				});
			}else{
				customMessageDialog("error", "เกิดข้อผิดพลาด", data.message, null);
			}
			
		});
	}


var BlockUi = function (){
	return{
		
//========================== Initi BlockUI Function *Require before call Block Function ==========================
		init : function(){
			mApp.init({});
		},
		
//========================== Block Page Function ==================================================
		blockPage : function(_state, _message){
			if(_state == null || _state == '')
				_state = "info";
			if(_message == null || _message == '')
				_message = "กำลังโหลดข้อมูล โปรดรอ...";			
			mApp.blockPage({
                overlayColor: "#000000",
                type: "loader",
                state: _state,
                message: _message
            });
		},		
		unBlockPage : function(){
			mApp.unblockPage();
		},
		blockPageWithTimeOut : function(_state, _message, _timeOut){
			//2e3: short for 2000
			if(_state == null || _state == '')
				_state = "info";
			if(_message == null || _message == '')
				_message = "กำลังโหลดข้อมูล โปรดรอ...";
			if(_timeOut == null || _timeOut == '')
				_timeOut = 2e3;
			
			BlockUi.blockPage(_state,_message);	
			
            setTimeout(function() {
            	BlockUi.unBlockPage();
            }, _timeOut);		
			
		},
		blockPageWithTimeOutAndRedirect : function(_state, _message, _link, _timeOut){
			if(_state == null || _state == '')
				_state = "info";
			if(_message == null || _message == '')
				_message = "กำลังโหลดข้อมูล โปรดรอ...";
			if(_link == null || _link == '')
				_link = 'dashboard.jsp';
			if(_timeOut == null || _timeOut == '')
				_timeOut = 2e3;
			
			
			BlockUi.blockPage(_state,_message);		
            setTimeout(function() {                
                window.location.href = _link;
            }, _timeOut);            
		},
		
//========================== Block Postion Function ==================================================
		blockPosition : function(_position,_state, _message){
			if(_state == null || _state == '')
				_state = "info";
			if(_message == null || _message == '')
				_message = "กำลังโหลดข้อมูล โปรดรอ...";
			if(_position == null || _position == ''){
				_position = "body";
			}
			if(_position != 'body'){	
				var e = $(_position).closest(".portlet");
				if(e.hasClass("portlet-fullscreen")){
					BlockUi.blockPage();
				}else{
					var resultFindClass = $(_position).find('.modal-content');
					if(resultFindClass.length != 0){
						_position = _position + " .modal-content";
					}
					mApp.blockPosition(_position,{
						overlayColor: "#000000",
		                type: "loader",
		                state: _state,
		                message: _message
					});
				}
				
			}else{
				BlockUi.blockPage();
			}
			
		},
		unBlockPosition : function(_position){
			if(_position == null || _position == '')
				_position = "body";
			if(_position != 'body'){
				var e = $(_position).closest(".portlet");
				if(e.hasClass("portlet-fullscreen")){
					BlockUi.unBlockPage();
				}else{
					var resultFindClass = $(_position).find('.modal-content');
					if(resultFindClass.length != 0){
						_position = _position + " .modal-content";
					}					
					mApp.unblockPosition(_position);
				}				
			}else{
				BlockUi.unBlockPage();
			}
			
		},
		blockPositionWithTimeOut : function(_position, _state, _message, _timeOut){
			//2e3: short for 2000
			if(_state == null || _state == '')
				_state = "info";
			if(_message == null || _message == '')
				_message = "กำลังโหลดข้อมูล โปรดรอ...";
			if(_timeOut == null || _timeOut == '')
				_timeOut = 2e3;
			if(_position == null || _position == '')
				_position = "body";
			
			var resultFindClass = $(_position).find('.modal-content');
			if(resultFindClass.length != 0){
				_position = _position + " .modal-content";
			}
			
			BlockUi.blockPosition(_position, _state, _message);
			
			setTimeout(function(){
				BlockUi.unBlockPosition(_position);
			},_timeOut);
		},
		blockPositionWithTimeOutAndRedirect : function(_position, _state, _message, _link, _timeOut){
			if(_state == null || _state == '')
				_state = "info";
			if(_message == null || _message == '')
				_message = "กำลังโหลดข้อมูล โปรดรอ...";
			if(_link == null || _link == '')
				_link = 'dashboard.jsp';
			if(_timeOut == null || _timeOut == '')
				_timeOut = 2e3;
			if(_position == null || _position == '')
				_position = "body";
			
			var resultFindClass = $(_position).find('.modal-content');
			if(resultFindClass.length != 0){
				_position = _position + " .modal-content";
			}
			
			BlockUi.blockPosition(_position, _state, _message);	
            setTimeout(function() {                
                window.location.href = _link;
            }, _timeOut);            
		}
		
	}
}();


var portletOption = function(){
	
	return{
		
		fullScreen : function(){
			$('body').on('click', '.portlet > .portlet-title .fullscreen',function(t){
				
				t.preventDefault();
				var e = $(this).closest(".portlet");
				if (e.hasClass("portlet-fullscreen")){				
					$(this).removeClass("on"), e
							.removeClass("portlet-fullscreen"), $("body")
							.removeClass("page-portlet-fullscreen"), e
							.children(".portlet-body")
							.css("height", "auto");
				}					
				else {
					var o = App.getViewPort().height
							- e.children(".portlet-title").outerHeight()
							- parseInt(e.children(".portlet-body").css(
									"padding-top"))
							- parseInt(e.children(".portlet-body").css(
									"padding-bottom"));
					$(this).addClass("on"), 
							e.addClass("portlet-fullscreen"), 
							$("body").addClass("page-portlet-fullscreen"), 
							e.children(".portlet-body").css("height", o);
				}
			});			
		}
		
	}
	
}();



var FormRepeater = function() {
    return {
        init: function() {        	
            $(".repeater").each(function() {
            	getQuestionTypeList();
                $(this).repeater({
                	initEmpty: true,
                    show: function () {                    	
                    	var elementRepeater = $(this).closest('[data-repeater-list]');
                    	//console.log(elementRepeater.find('select').select2('destroy').next())
                    	if(elementRepeater.hasClass('condition')){     
                    		//var numberOfResultList = $(this).find('.result_list').find('option').length;   
                    		//let numberOfResultList = $('#tabCondition').find('.result_list').find('option').length;
                    		let numberOfResultList = answerListByChecklistType.length;
                    		if(elementRepeater.find('[data-repeater-item]').length > numberOfResultList){
                    			$(this).remove();
                    		}else{
                    			var elementForAppend = $(this);
                    			var count = 0;
                    			$(answerListByChecklistType).each(function(i,v){
            						elementForAppend.find('.portlet-body').find('.result_list').append($('<option value ="'+v.answerId+'">'+v.answerDetail+'</option>'));                    						
            					});
                    			$.each(elementRepeater.find('.result_list'),function(index,val){
                    				if(count < elementRepeater.find('.result_list').length-1){
                    					
                    					$(this).find('option').not(':selected').remove();                    					
                    					$(elementForAppend.find('.portlet-body').find('.result_list option[value='+$(this).find('option:selected').val()+']')).remove();
                    					//$(elementForAppend.find('.result_list option[value='+$(this).find('option:selected').text()+']')).remove();
                    					++count;
                    				}                    				                    				                  				
                    			});
                    		}                    			
                    	}
                    	else if(elementRepeater.hasClass('question')){
                    		editor.init($(this));                    		
                    	}else{
                    		
                    	}
                    	
                    	//console.log(elementRepeater.find('[data-repeater-item]').find('.question_type_selection'))
                    	//.hasClass('select2-hidden-accessible')
                    	$.each(elementRepeater.find('[data-repeater-item]').find('.question_type_selection'),function(i,ele){
                    		if(!$(this).hasClass('select2-hidden-accessible')){
                    			
                    			$(this).select2({
                    				dropdownParent: $('#tabQuestion'),
                    	    		placeholder: "กรุณาเลือกประเภทคำตอบ",
                    	    		theme: "bootstrap",
                    	    		data: dataSourceQuestionTypeMapping
                    			});
                    			$(this).val(null).trigger('change');
                    		}
                    	});
                    	
                    	
                    	
                    	$(this).slideDown();   
                    },
                    hide: function (deleteElement) {     
                    	var elementRepeater = $(this).closest('[data-repeater-list]');
                    	if(elementRepeater.find('[data-repeater-item]').length > 1)
                    		$(this).slideUp(deleteElement);
                    	else{
                    		if(elementRepeater.hasClass('question'))
                    			customMessageDialog('warning',"คำเตือน", "ไม่สามารถนำออกได้ เนื่องจากต้องมีอย่างน้อย 1 คำถาม", null);
                    		else if(elementRepeater.hasClass('condition'))
                    			customMessageDialog('warning',"คำเตือน", "ไม่สามารถนำออกได้ เนื่องจากต้องมีอย่างน้อย 1 เงื่อนไข", null);
                    		else
                    			customMessageDialog('warning',"คำเตือน", "ไม่สามารถนำออกได้", null);
                    	}   
                    	
                    },
                    ready: function (setIndexes) {
                        $dragAndDrop.on('drop', setIndexes);
                    }
                })
            })
        },
        clearAll : function(){
        	$('[data-repeater-list]').empty();
        	$('[data-repeater-create]').click();
        }
    }
}();



var toolTips = function(){
	return{
		init : function(){
			$('[data-toggle="tooltip"]').tooltip(); 
			$('[data-toggle="collapse"]').tooltip();
		}
	}
}();

var collpseAndExpand = function(){
	return{		
		init:function(){
			var element = $('body').find('a[data-toggle="collapse"]');
			/*var body = element.closest(".form-group").children(".form-body-collapse");
			if(body.length == 0){
				body = element.closest(".portlet").children(".form-body-collapse");
			}*/
			$(element).each(function(){
				var body = $(this).closest(".form-group").children(".form-body-collapse");
				if(body.length == 0){
					body = $(this).closest(".portlet").children(".form-body-collapse");
				}
				var icon = $(this).find('.rotate-icon');
				
				if($(this).hasClass('collapsed')){
					body.hide();
					icon.addClass("fa-angle-down");
					icon.removeClass("fa-angle-up");
				}else{
					body.show();
					icon.removeClass("fa-angle-down");
					icon.addClass("fa-angle-up");
				}	
			});
			/*var icon = element.find('.rotate-icon');
			
			if(element.hasClass('collapsed')){
				body.hide();
				icon.addClass("fa-angle-down");
				icon.removeClass("fa-angle-up");
			}else{
				body.show();
				icon.removeClass("fa-angle-down");
				icon.addClass("fa-angle-up");
			}	*/		
		},
		onClick : function(){
			$('body').on('click','a[data-toggle="collapse"]',function(t){
				let flag = false;
				var e = $(this).closest(".form-group").children(".form-body-collapse");
				if(e.length == 0){
					e = $(this).closest(".portlet").children(".form-body-collapse");
					flag = true;
				}
				var icon = $(this).find('.rotate-icon');
				if($(this).hasClass("collapsed")){
					$(this).removeClass("collapsed");
					if(flag)
						e.slideDown(500);
					else
						e.slideDown(200);
					icon.removeClass("fa-angle-down");
					icon.addClass("fa-angle-up");
				}else{
					$(this).addClass("collapsed");
					if(flag)
						e.slideUp(500);
					else
						e.slideUp(200);					
					icon.addClass("fa-angle-down");
					icon.removeClass("fa-angle-up");
				}
				
			})
		}
	}
}();

var editor = function(){
	return{
		init : function(item){
			var optionObject = {
					'insert_link':false,
					'insertoptions' : false,
					'extraeffects' : false,
					'advancedoptions' : false,
					'screeneffects':false,
					'unlink':false,
					'insert_img':false,
					'hr_line':false,
					'strikeout':false,
					'print':false,
					'source':false,
					'splchars':false,
					'insert_table':false,
					'select_all':false,
					'togglescreen':false,
					'font_size':false,
					'fonts':false,
					'styles':false,
				};
			
			if(item == null){
				$('.txtEditor').each(function(){
					$(this).Editor(optionObject);
				});
			}else{
				if($.type( item ) === "string"){
					$(item).Editor(optionObject);
				}else{
					var textEditorObj = item.find('.txtEditor');
					if(textEditorObj.length > 0){
						$.each(textEditorObj,function(){
							$(this).Editor(optionObject);
						});	
					}
				}										
			}
		},
	
		setText : function(element,text){
			$(element).Editor("setText", text);
		},
		getText : function(element){
			var val = '';
			try{
				val = $(element).Editor("getText");
			}catch(e){
				val = '';
			}
			return val;
		}
	
	}
}();


var validate = function(){
	return{
		init : function(o){
			/*
			 * o = {
			 * 		element : 'element form to Validate (id or class)> Type = String',
			 * 		require : 'name of element to validate > Type = Array EX.['name_ele1','name_ele2']',
			 * 		message : 'is massage to validate match to require > Type = Arrya EX.['กรุณาใส่ข้อมูล 1','กรุณาใส่ข้อมูล 2']',
			 * 		maxLength : 'is Option Of field' > Type = Array EX.[1,10]',
			 * }
			 */
			if(o == null)
				return false;
			if(o.require == null || o.require == [])
				return false;
			var ruleObj = {};
			var messageObj = {};
			$.each(o.require,function(index,val){	
				if(val != ""){
					if(val == 'audit_name_tmp'){
						ruleObj[val] = 'checkTags';
					}else if(val == 'Criteria_tmp'){
						ruleObj[val] = 'checkEditor';
					}else if(val == 'table_condition_tmp'){
						ruleObj[val] = 'checkTableCondition';
					}else{
						ruleObj[val] = {required: !0};
						
					}
				}							
			});
			
			if(o.message != null || o.message != []){
				$.each(o.message,function(index,val){
					messageObj[o.require[index]] = {required: val};
				});
			}
			
			if ('maxLength' in o){
				if(o.maxLength != null || o.maxLength != []){
					$.each(o.require,function(index,val){
						if(o.maxLength[index] != "" && o.maxLength[index] != 0 && o.maxLength[index] != null){
							ruleObj[val].maxlength = o.maxLength[index];
							messageObj[val].maxlength = jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร")
						}
					});
				}
			}
			
			
			let v = $(o.element).validate({
				focusInvalid : !1,
				errorClass: "help-block help-block-error",
				rules : ruleObj,
				messages : messageObj,
				highlight: function(e) {			
		            $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
		        },
		        unhighlight: function(e) {
		            $(e).closest(".form-group").removeClass("has-error")
		        },
		        resetForm: function() {
		            if ( $.fn.resetForm )
		                $( this.currentForm ).resetForm();
		            this.submitted = {};
		            this.prepareForm();
		            this.hideErrors();
		            var elements = this.elements();
		            
		            elements.removeClass( this.settings.errorClass );
		            if (this.settings.resetForm){
		                for (var i = 0; i < elements.length; i++){
		                    this.settings.resetForm.call( this, elements[i], this.settings.errorClass, this.settings.validClass);
		                }
		            }
		            this.reset();
		        }
				
			});
			
			return v;
		},
		add : function(o){
			/*o = {
				element : 'element form to Validate (id or class)> Type = String',
				rules : '',
				message : '',
			};*/
			if(o == null)
				return false;
			if(o.element == null || o.element == [])
				return false;
			
			var ruleObj = {};
			if(o.rules != null || o.rules != []){
				$(o.rules).each(function(i,v){
					$.extend( ruleObj, v);
				});
			}
			
			var message = {};
			if(o.message != null || o.message != []){
				$(o.message).each(function(i,v){					
					if(v != '' && v != null){
						message[Object.keys(o.rules[i])[0]] = v;
					}else{
						if(Object.keys(o.rules[i])[0] == 'maxlength'){
							message[Object.keys(o.rules[i])[0]] = jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร");
						}else if(Object.keys(o.rules[i])[0] == 'required'){
							message[Object.keys(o.rules[i])[0]] = 'กรุณาใส่ข้อมูล';
						}
					}
				});
			}
			
			ruleObj['messages'] = message;
			if(ruleObj != null && ruleObj != {} && o.element != null && o.element != ''){
				$( o.element ).rules( "add", ruleObj);
			}			
		},
		remove : function(element, _rules){
			if(element == '' && element == null)
				return false;
			else{
				if(_rules == null || _rules == '' || _rules == []){
					$( element ).rules( "remove" );
				}else{
					if($.isArray(_rules)){
						var rule = null;
						$(_rules).each(function(i, v){
							if(i > 0 && i < _rules.length-1){
								rule += ', ';
							}
							rule += v;
						});
						
						if(rule != null){
							$( element ).rules( "remove" , rule);
						}
					}else{
						$( element ).rules( "remove" , _rules);
					}
				}
			}
		},
		equalTo : function(o){
			/*o = {
				element : 'element form to Validate (id or class)> Type = String',
				equalTo : '',
				message : '',
			};*/
			if(o == null)
				return false;
			if(o.element == null || o.element == [])
				return false;
			console.log(o);
			
			
			/*var ruleObj = {};
			if(o.rules != null || o.rules != []){
				$(o.rules).each(function(i,v){
					$.extend( ruleObj, v);
				});
			}
			
			var message = {};
			if(o.message != null || o.message != []){
				$(o.message).each(function(i,v){					
					if(v != '' && v != null){
						message[Object.keys(o.rules[i])[0]] = v;
					}else{
						if(Object.keys(o.rules[i])[0] == 'maxlength'){
							message[Object.keys(o.rules[i])[0]] = jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร");
						}else if(Object.keys(o.rules[i])[0] == 'required'){
							message[Object.keys(o.rules[i])[0]] = 'กรุณาใส่ข้อมูล';
						}
					}
				});
			}
			
			ruleObj['messages'] = message;
			if(ruleObj != null && ruleObj != {} && o.element != null && o.element != ''){
				$( o.element ).rules( "add", ruleObj);
			}*/			
		}
	}
}();

var autoComplete = function(){
	return{		
		init : function(_ele,_data){			
			$(_ele).typeahead({
	    		source: _data,
	    		autoSelect: false,
	    		showHintOnFocus : false,
	    		fitToElement : true,
	    		afterSelect : autoComplete.selectionFunction
	    	});
		},
		selectionFunction : function(item, element){			
			switch(item.type){
				case 1:
					break;
				case 2:
					$('#description_topic').val(item.detail);
					break;
				case 3:
					$('#description_sub_topic').val(item.detail);
					break;
				case 4:
					$('#description_group_question').val(item.detail);
					break;
				case 5:					
					break;
			}
		}
	}
}();

var generate = function(){
	return{
		randomByDate : function(){
			return Date.now();
		}
	} 
}();

var URI = function(){
	return{
		encode : function(data){
			return encodeURIComponent(data);
		},
		decode : function(data){
			try {    
			    data = decodeURIComponent(data);
			} catch (e) {			    
			    data = decodeURIComponent(data.replaceAll('%', '%25'));
			}
			return data;
		}
	}
}();

var bootstrapSwitch = function(){
	return{
		init : function(){
			$('.checkbox-status').each(function(i,v){
				//$.fn.bootstrapSwitch.defaults.size = 'normal';
		    	$.fn.bootstrapSwitch.defaults.onColor = 'success';
		    	$(this).bootstrapSwitch();
			});
		}
	}
}();

var keyUp = function(){
	return{
		enter : function(element,action){
			$(element).keyup(function(event) {
			    if (event.keyCode === 13) {
			    	if(action != null){
				        return action();
			    	}
			    }
			});
		}
	}
}();


var dateTime = function(){
	return{
		currentDateTime : function(format,callbackFn){
			let dateTimeCurrent = null;
			if(format == "" || format == null){
				format = "";
			}else{
				format = URI.encode(format);
			}
			//let url = contextPath+'/api/date_time/current_date_time/'+format;
			let url = contextPath+'/api/date_time/current_date_time';
			$.ajax({
				url : url,
				type : 'POST',
				data : format,
				processData : false,
				success : function(data){
					if(data.result){
						try{
							let dateTimeString = JSON.parse(data.message).currentDateTime;	
							dateTimeCurrent = new Date(dateTimeString.trim());
							return callbackFn(dateTimeCurrent);
						}catch(e){
							dateTimeCurrent = new Date();
							return callbackFn(dateTimeCurrent);
						}			
					}
				},
				error: function (jqXHR, textStatus, errorThrown) {
					try{
						customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseJSON.message, null);
					}catch (e) {
						customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
					}					  
				}
			});
			/*ajaxProcess.submit(url, 'GET', null, '', (data) =>{
				if(data.result){
					try{
						let dateTimeString = JSON.parse(data.message).currentDateTime;	
						dateTimeCurrent = new Date(dateTimeString.trim());
						return callbackFn(dateTimeCurrent);
					}catch(e){
						dateTimeCurrent = new Date();
						return callbackFn(dateTimeCurrent);
					}			
				}
			});*/
		}		
	}
}();

var validateInnerText = function(){
	return{
		valid2 : function(_event, _pattern){
			 // Disallow anything not matching the regex pattern (A to Z uppercase, a to z lowercase and white space)
		    // For more on JavaScript Regular Expressions, look here: https://developer.mozilla.org/en-US/docs/JavaScript/Guide/Regular_Expressions
			// Retrieving the key from the char code passed in event.which
		    // For more info on even.which, look here: http://stackoverflow.com/q/3050984/114029
			//alert(event.keyCode);
		    
		    // For the keyCodes, look here: http://stackoverflow.com/a/3781360/114029
		    // keyCode == 8  is backspace
		    // keyCode == 37 is left arrow
		    // keyCode == 39 is right arrow
		    // englishAlphabetAndWhiteSpace.test(key) does the matching, that is, test the key just typed against the regex pattern
			let pattern = '';
			if(_pattern == '' || _pattern == null){
				pattern = /[A-Za-z0-9 ]/g;
			}else{
				pattern = _pattern;
			}
			let key = String.fromCharCode(_event.which);
			let resultValid = false;
			
			if (_event.keyCode == 8 /*|| _event.charCode == 32*/ || _event.keyCode == 37 || _event.keyCode == 39 || pattern.test(key)) {
				resultValid = true;
		    }else{
		    	// If we got this far, just return false because a disallowed key was typed.
		    	resultValid = false;
		    }
			return resultValid;
		},
		valid : function(validChars, event){
			var keyChar = String.fromCharCode(event.which || event.keyCode);
		    return validChars.test(keyChar) ? keyChar : false;
		},
		validByElement : function(element, _pattern){	
			$(element).on("keypress", function(event) {		
				var pattern = '';
				if(_pattern == '' || _pattern == null){
					pattern = /[A-Za-z0-9 ]/g;
				}else{
					pattern = _pattern;
				}
			    return validateInnerText.valid2(event, pattern);
			})
			/*$(element).on("paste",function(e){
			    e.preventDefault();
			})*/
		}
	}
}();

var ajaxProcess = function(){
	return{
		submit : function(url, method, d, posBlock,callback,option){
			
			if(url == null || url == "")
				return false;
			if(method == null || method == "")
				method = "GET";
			
			if(d != null && d != ""){
				d = JSON.stringify(d);
			}else{
				d = '';
			}		
			
			let positionBlock = [];
			if(posBlock == null){
				positionBlock = null;
			}else if(posBlock == ''){
				positionBlock = [];
			}else{
				if($.type(posBlock) === "object"){
					positionBlock = [posBlock];
					
				}else if($.type(posBlock) === "string"){
					positionBlock = posBlock.split(',');
				}
							
			}				
			
			$.ajax($.extend({
				url : url,
				type : method,
				data : d,
				contentType: 'application/json; charset=UTF-8',
				beforeSend : function(arr, $form, options){
					if(positionBlock == null)
						BlockUi.blockPage();
					else if(positionBlock.length == 0)
						return;
					else if(positionBlock.length > 0){
						$(positionBlock).each(function(i,v){
							if($.type(v) === "string")
								v = v.trim();							
							BlockUi.blockPosition(v);
						});
					}
			    },
			    complete : function(){
			    	setTimeout(function(){
			    		if(positionBlock == null)
							BlockUi.unBlockPage();
						else if(positionBlock.length == 0)
							return;
						else if(positionBlock.length > 0){
							$(positionBlock).each(function(i,v){
								if($.type(v) === "string")
									v = v.trim();	
								BlockUi.unBlockPosition(v);
							});
						}
			    	},1000);
			    },
			    success : function(data) {	
			    	
			    	try{
			    		
			    		if(data.result == true){			    			
			    			try{			
			    				if(data.message != "" && data.message != null){
			    					JSON.parse(data.message);
			    				}		    				
			    				//return callback(dataObj);
			    			}catch(e){					    				
			    				customMessageDialog("success", "สถานะการดำเนินการ", data.message, null);
			    			}	    								
				    	}else{
				    		customMessageDialog("error", "สถานะการดำเนินการ", data.message, null);						
				    	}			    		
			    	}catch(e){
			    		customMessageDialog("error", "สถานะการดำเนินการ", data.message, null);	
			    		//return callback(data);
			    	}finally{
			    		if(callback != null)
				    		return callback(data);
			    	}				    	
			    	
				},
				error: function (jqXHR, textStatus, errorThrown) {
					
					try{
						customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseJSON.message, null);
					}catch (e) {
						customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
					}					  
				}
			},option));
		}
	}
}();
