var auditorDataTable = null;
var entourgeDataTable = null;
var objPassForm = null;
var userObj = null;

$(function(){
	if($('#appointId').val() != ""){
		try{
			
			objPassForm = JSON.parse(URI.decode($('#appointId').val().trim()));
			initial.appointCreateBy(objPassForm.appointCreateBy);
			initial.appointDetail(objPassForm.appointId);
			initial.appointHistoryList(objPassForm.appointId);	
			initial.auditorList(objPassForm.appointId);
			initial.entourgeList(objPassForm.appointId);
					
			$("#AuditorName").tagsinput({	    		
				allowDuplicates: false,
				itemValue: 'value',
				itemText: 'text',
				tagClass: 'label label-success',
				freeInput: false
			});
			
			$("#entourgeName").tagsinput({	    		
				allowDuplicates: false,
				itemValue: 'value',
				itemText: 'text',
				tagClass: 'label label-info',
				freeInput: false
			});
			
			let objectValidate = {
				element : '#form-appoint-change',
				require : ['appoint_date_change_from','appoint_time_change','appoint_detail_change'],
				message : ["กรุณาใส่วันเลื่อนนัด","กรุณาใส่เวลาเลื่อนนัด","กรุณาใส่รายละเอียดการเลื่อนนัด"],
				maxLength : [null, null, 300]
			};
			validate.init(objectValidate);			
			
			objectValidate = {
				element : '#form-appoint-detail',
				require : ['supplierCompany','LocationProduce','appoint_date','appoint_time','appoint_date_pickers','appoint_time_pickers','appoint_title'],
				message : ["กรุณาใส่ชื่อบริษัท","กรุณาเลือกสถานที่ผลิตของซัพพลายเออร์","กรุณาใส่วันนัดหมาย","กรุณาใส่เวลานัดหมาย","กรุณาเลือกวันนัดหมาย","กรุณาเลือกเวลานัดหมาย","กรุณาใส่หัวข้อ"],
				maxLength : [100, 100, null, null, null, null, 200]
			};
			validate.init(objectValidate);
			
			$.validator.addMethod("checkTags", function(value) {    
		        return ($("#AuditorName").tagsinput('items').length > 0);
		    },"กรุณาใส่รายชื่อผู้ตรวจประเมิน");
			
			$( "#dummy_auditName" ).rules( "add", {
				checkTags: true
			});
			
			$(".bootstrap-tagsinput > input").prop("readonly", true);
			
			
			
			
		}catch (e) {
			console.log(e);
			window.location.href = contextPath+"/appoint.jsp";
		}
	}else{
		window.location.href = contextPath+"/appoint.jsp";
	}
	
});



var initial = function(){
	return{
		appointDetail : function(appointId){
			let url = contextPath + "/api/appoint/get_appoint_detail/"+JSON.stringify({
				appointId : appointId
			});
			
			ajaxProcess.submit(url, 'GET', null, '#portlet-main-appoint', (data)=>{
				if(data.result){
					
					let objResponse = JSON.parse(data.message)[0];
					
					$('#supplierCompany').val(objResponse.supplierId.supplierCompany);
					$('#supplierId').val(objResponse.supplierId.supplierId);
					$('#LocationProduce').val(objResponse.locationId.locationName);
					$('#locationId').val(objResponse.locationId.id);										
					$('#appoint_title').val(objResponse.title.trim());
					$('#appoint_detail').val(objResponse.detail.trim());
					$('#appointStatusId').val(objResponse.appointStatusId.appointStatusId);
					
					let timeAppoint = objResponse.appointDate.split(" ")[1].trim().split(':');
					$('#appoint_date_pickers').val(objResponse.appointDate.split(" ")[0].trim());
					$('#appoint_time_pickers').val(timeAppoint[0]+":"+timeAppoint[1]);
					$('#appoint_date').val(objResponse.appointDate.split(" ")[0].trim());
					$('#appoint_time').val(timeAppoint[0]+":"+timeAppoint[1]);
					
					dateTime.currentDateTime('yyyy/MM/dd HH:mm:ss',(currentTimeDate)=>{
						let currentDate = new Date();
						currentDate.setDate(currentTimeDate.getDate());
						
						let currentTime = new Date();
						currentTime.setTime(currentTimeDate.getTime());	
						
						
						$('.apponit-datetime-pickers .datepicker-from').datepicker({
							language: "th",
							format: 'dd/mm/yyyy',
							orientation: "bottom auto",
							autoclose: true
						}).on('hide', function(e) {
					        if($('#appoint_date_pickers').val() == ""){
					        	$('#appoint_date_pickers').val($('#appoint_date').val());
					        }
					    });			
						$('.apponit-datetime-pickers .datepicker-from').datepicker('setDate',objResponse.appointDate.split(" ")[0].trim());
						$('.apponit-datetime-pickers .datepicker-from').datepicker('setStartDate',currentDate);
						
						$('.apponit-datetime-pickers .timepicker-from').clockpicker({	    		
							autoclose: true,
						    donetext: 'Done',
						    placement: 'bottom',
						    'default' : currentTime
						});
						$('.apponit-datetime-pickers .timepicker-from #appoint_time_pickers').val($('#appoint_time_pickers').val().trim());
						
						$('.clockpicker-change').clockpicker({	    		
							autoclose: true,
						    donetext: 'Done',
						    placement: 'bottom'
						});
						$('.clockpicker-change #appoint_time_change').val(currentTime.getHours().toString().padStart(2, '0')+":"+currentTime.getMinutes().toString().padStart(2, '0'));
						
						let dateChangeTo = new Date();
						dateChangeTo.setDate(currentDate.getDate()+1)
						$('#appoint_date_change_to').datepicker({
							language: "th",
							format: 'dd/mm/yyyy',
							orientation: "bottom auto",
							autoclose: true,
							clearBtn : true
						});
						$('#appoint_date_change_to').datepicker('setStartDate',dateChangeTo);
						
						$('#appoint_date_change_from').datepicker({
							language: "th",
							format: 'dd/mm/yyyy',
							orientation: "bottom auto",
							autoclose: true
						}).on('changeDate', function(ev){
							if($('#appoint_date_change_to').val() != ""){
								let dateTmp = $('#appoint_date_change_to').val().trim().toString().split("/");
								let ChangeToTempDate = new Date(dateTmp[2], dateTmp[1]-1, dateTmp[0]);
								if(ev.date >= ChangeToTempDate){
									let newDate = new Date(ev.date);		
									newDate.setDate(newDate.getDate() + 1);
									$('#appoint_date_change_to').datepicker('setStartDate',newDate);
									$('#appoint_date_change_to').datepicker('setDate', newDate);
								}
							}else{
								let newDate = new Date(ev.date);		
								newDate.setDate(newDate.getDate() + 1);
								$('#appoint_date_change_to').datepicker('setStartDate',newDate);
							}							
							
						});
						$('#appoint_date_change_from').datepicker('setDate',currentDate);
						$('#appoint_date_change_from').datepicker('setStartDate',currentDate);
						
						
					});
					
					if(objResponse.appointStatusId.appointStatusId == "1" || objResponse.appointStatusId.appointStatusId == "2" || objResponse.appointStatusId.appointStatusId == "3"){
						$('.apponit-datetime').hide();
						$('#btn_cancel_appoint').slideDown('slow');
					}else{
						if(objResponse.appointStatusId.appointStatusId == "5"){
							$('#btn_cancel_appoint').slideDown('slow');
						}else{
							$('#btn_cancel_appoint').slideUp('slow');
						}
						
						$("#portlet-appoint-change").addClass("disabledelements");	
						$('.apponit-datetime-pickers').hide();
						$('#btn_save_appoint').hide();
						$('#AuditorName').next().hide();
						$('#AuditorName').parent().removeClass('input-group');
						$('#entourgeName').next().hide();
						$('#entourgeName').parent().removeClass('input-group');
						$('#appoint_title').prop('readonly',true);
						$('#appoint_detail').prop('readonly',true);		
						
					}
					initial.loadSession();
				}
			});
		},
		appointCreateBy : function(userObj){
			$('.appointName_Label').text(userObj.fullname);
			$('#appointNameId').val(userObj.userId);
		},
		auditorList : function(appointId){
			let url = contextPath + "/api/appoint_mapping_user/get_auditor_mapping_appoint/"+JSON.stringify({
				appointId : appointId
			});
			ajaxProcess.submit(url, 'GET', null, '', (data)=>{
				if(data.result){
					let appointObj = JSON.parse(data.message);
					$(appointObj).each(function(i,v){
						$('#AuditorName').tagsinput('add', { "value": v.auditorId[0].userId , "text": v.auditorId[0].fullname});
					});
				}				
			});
			
			
		},
		entourgeList : function(appointId){
			let url = contextPath + "/api/appoint_entourage/get_entourage_by_appoint/"+JSON.stringify({
				appointId : appointId
			});
			ajaxProcess.submit(url, 'GET', null, '', (data)=>{
				if(data.result){
					let appointObj = JSON.parse(data.message);
					$(appointObj).each(function(i,v){
						$('#entourgeName').tagsinput('add', { "value": v.entourageId[0].userId , "text": v.entourageId[0].fullname});
					});
				}				
			});
		},
		appointHistoryList : function(appointId){
			let url = contextPath + "/api/appoint_history/appoint_history_list_by_appoint/"+JSON.stringify({
				appointId : appointId
			});
			ajaxProcess.submit(url, 'GET', null, '#portlet-history', (data)=>{
				if(data.result){
					let objHistory = JSON.parse(data.message);
					
					for(index = objHistory.length-1 ; index >= 0; index--){
						
						let userFullname = objHistory[index].appointHistoryCreateBy.fullname.trim();
						let supplierCompany = objHistory[index].appointHistoryCreateBy.supplierId[0].supplierCompany.trim();
						let dateTimeCreate = objHistory[index].createDate.toString().trim();
						
						let detailText = "<b>สถานะ : </b>"+objHistory[index].appointStatusId.appointStatusName.toString().trim()+"<br/>";
						detailText += "<b>หัวข้อ : </b>"+objHistory[index].title.toString().trim()+"<br/>";
						detailText += genDetailObjectToText(objHistory[index].detail);
						if(objHistory[index].appointHistoryCreateBy.userGroupId.userGroupId == "1" || objHistory[index].appointHistoryCreateBy.userGroupId.userGroupId == "2"){
							
							$('.chats').append(
			    					$('<li class="in">').append(
			    								$('<div class="avatar">'+userFullname+'</div>')
			    							)
			    							.append(
			    								$('<div class="message">').append($('<span class="arrow"> </span>'))
			    														  .append($('<span class="datetime"> '+dateTimeCreate+' </span>'))
			    														  .append($('<span class="body" style="overflow-wrap : break-word">'+detailText+'</span>'))
			    														  //.append($('<a href="javascript:void(0);" title="<b>รายละเอียด</b>" data-toggle="popover" data-trigger="focus" data-placement="right" data-content="'+/*detail*/+'">รายละเอียดเพิ่มเติม</a>'))
			    							)
			    							.hide()
			    							.fadeIn(300)
			    					);
							
							
						}else{
							
							$('.chats').append(
			    					$('<li class="out">').append(
			    								$('<div class="avatar">'+supplierCompany+'</div>')
			    							)
			    							.append(
			    								$('<div class="message">').append($('<span class="arrow"> </span>'))
			    														  .append($('<span class="datetime"> '+dateTimeCreate+' </span>'))
			    														  .append($('<span class="body" style="overflow-wrap : break-word">'+detailText+'</span>'))
			    														  .append($(''))
			    							)
			    							.hide()
			    							.fadeIn(300)
			    					);
							
						}
					}
					
				}
			});
		},
		auditorDataTable : function(){
			auditorDataTable = $('#table_search_audit').DataTable({
				"proccessing": true,
				"serverSide": true,
				"searching": true,
				"ajax": {
			        "url": contextPath + "/api/user/datatable/get_user_auditor_type",
			        "type": 'POST',
			        contentType: "application/json",
			        beforeSend : function(arr, $form, options){
				    	BlockUi.blockPosition('#dialog_search_auditor');
				    },
				    complete : function(){		    	
				    	setTimeout(function(){
				    		BlockUi.unBlockPosition('#dialog_search_auditor');
				    	},500);
				    },
			        data: function (d) {	        	
			        	return JSON.stringify(d);
			        }
			    },
			    "language": {
			        "url": "assets/global/plugins/datatables/Thai.json"
			    },
			    "columns": [
			    	{ "data": "fullname" ,name:"fullname"},
			    	{ "data": "userId" ,name:"user_id"},
			    	{ "data": "email" ,name:"email"},	    	
			        { "data": "userGroupId.userGroupId" ,name:"user_group_id"}
			    ],
			    "columnDefs": [
			    	{
			        	"targets": [1],
			            "visible": false,
			            "searchable": false
			        },
			        {
			        	"targets": [3],
			            "visible": false,
			            "searchable": true
			        },
			        {
			            "searchable": false,
			            "orderable": false,
			            "targets": [4]
			        },
			        {
			            "searchable": true,
			            "orderable": false,
			            "targets": [0,2]
			        },
			        {
			            "targets": 4,
			            "orderable": false,
			            "render": function (data, type, row) {
			            	
			            	
			            	var btnAction = '<button type="button" class="btn btn-default btn-select-audit"><i class="fa fa-check"></i> เลือก</button>';
			            	
			               
			                return btnAction;
			            },
			            "width": "10px"  
			        }
			    ],
			    "order": [[1, 'asc']],
			    "initComplete": function (settings, json) {
			    	
			    	$('.dataTables_filter').remove();
			    }
			});
		},
		entourgeDataTable : function(){
			entourgeDataTable = $('#table_search_entourge').DataTable({
				"proccessing": true,
				"serverSide": true,
				"searching": true,
				"ajax": {
			        "url": contextPath + "/api/user/datatable/entourge_list",
			        "type": 'POST',
			        contentType: "application/json",
			        beforeSend : function(arr, $form, options){
				    	BlockUi.blockPosition('#dialog_search_entourge');
				    },
				    complete : function(){		    	
				    	setTimeout(function(){
				    		BlockUi.unBlockPosition('#dialog_search_entourge');
				    	},500);
				    },
			        data: function (d) {	        	
			        	return JSON.stringify(d);
			        }
			    },
			    "language": {
			        "url": "assets/global/plugins/datatables/Thai.json"
			    },
			    "columns": [
			    	{ "data": "fullname" ,name:"fullname"},
			    	{ "data": "email" ,name:"email"},	    	
			        { "data": "userGroupId.userGroupName" ,name:"userGroupName"}
			    ],
			    "columnDefs": [	    	
			        {
			            "searchable": true,
			            "orderable": false,
			            "targets": [0,1,2]
			        },
			        {
			            "targets": 3,
			            "orderable": false,
			            "searchable": false,
			            "render": function (data, type, row) {
			            	
			            	
			            	var btnAction = '<button type="button" class="btn btn-default btn-select-entourge"><i class="fa fa-check"></i> เลือก</button>';
			            	
			               
			                return btnAction;
			            },
			            "width": "10px"  
			        }
			    ],
			    "order": [[2, 'asc']],
			    "initComplete": function (settings, json) {	    	
			    	$('.dataTables_filter').remove();
			    }
			});
		},
		loadSession : function(){
			let url = contextPath +"/api/user/get_session_user";
			
			$.ajax({
				url : url,
				type : 'GET',
				contentType: "application/json",
				success : function(data){
					userObj = JSON.parse(data.message);						
					if(userObj.userGroupId.userGroupId == '5'){
						$("#portlet-appoint-change").addClass("disabledelements");	
						$('.apponit-datetime-pickers').hide();
						$('#btn_save_appoint').hide();
						$('#btn_cancel_appoint').hide();
						$('#AuditorName').next().hide();
						$('#AuditorName').parent().removeClass('input-group');
						$('#entourgeName').next().hide();
						$('#entourgeName').parent().removeClass('input-group');
						$('#appoint_title').prop('readonly',true);
						$('#appoint_detail').prop('readonly',true);						
					}
				}
			});
		}
	}
}();


$("#AuditorName").on('beforeItemRemove', function(event) {
	if($('#appointStatusId').val() != "1" && $('#appointStatusId').val() != "2" && $('#appointStatusId').val() != "3"){
		event.cancel = true;
	}
	if(userObj.userGroupId.userGroupId == '5'){
		event.cancel = true;
	}
});

$("#entourgeName").on('beforeItemRemove', function(event) {
	if($('#appointStatusId').val() != "1" && $('#appointStatusId').val() != "2" && $('#appointStatusId').val() != "3"){
		event.cancel = true;
	}
	if(userObj.userGroupId.userGroupId == '5'){
		event.cancel = true;
	}
});


$('#table_search_audit').on('click','.btn-select-audit',function(){
	let data = $('#table_search_audit').DataTable().row( $(this).parents('tr') ).data();	
	let auditName = data.fullname.trim();
	let auditId = data.userId.trim();
	
	$($('#entourgeName').tagsinput('items')).each(function(i,objTagsinput){
		if(objTagsinput.value == auditId && objTagsinput.text == auditName){
			$('#entourgeName').tagsinput('remove', objTagsinput);
			return false;
		}
	});
	
	$('#AuditorName').tagsinput('add', { "value": auditId , "text": auditName});	
	$('#dialog_search_auditor').modal('toggle');
});


$('#btn_search_auditor_table').on('click',function(){
	searchAuditorTable();
});

keyUp.enter('#auditor_name_dialog', ()=>{
	searchAuditorTable();
});

keyUp.enter('#auditor_email_dialog', ()=>{
	searchAuditorTable();
});

function searchAuditorTable(){
	$('#table_search_audit').DataTable().columns(0).search($('#auditor_name_dialog').val().trim());
	$('#table_search_audit').DataTable().columns(2).search($('#auditor_email_dialog').val().trim());
	$('#table_search_audit').DataTable().draw();
}

$('#btn_clear_auditor_table').on('click',function(){
	$('#auditor_name_dialog').val('');
	$('#auditor_email_dialog').val('');
	$('#table_search_audit').DataTable().columns(0).search('');
	$('#table_search_audit').DataTable().columns(2).search('');
	$('#table_search_audit').DataTable().draw();
});

$("#dialog_search_auditor").on('shown.bs.modal', function (e) {
	if(auditorDataTable == null){
		initial.auditorDataTable();		
	}	
});



$('#dialog_search_entourge').on('click','.btn-select-entourge',function(){
	let data = $('#table_search_entourge').DataTable().row( $(this).parents('tr') ).data();	
	let entourgeName = data.fullname.trim();
	let entourgeId = data.userId.trim();
	
	let checkDupplicate = false;
	$($('#AuditorName').tagsinput('items')).each(function(i,objTagsinput){
		if(objTagsinput.value == entourgeId && objTagsinput.text == entourgeName){
			customMessageDialog('warning',"เกิดข้อผิดพลาด", "ไม่สามารถเลือกผู้ติดตามคนนี้ได้ เนื่องจากมีรายชื่อนี้ในผู้ออกตรวจแล้ว",null);
			checkDupplicate = true;
			return false;
		}
	});
	
	if(!checkDupplicate){
		$('#entourgeName').tagsinput('add', { "value": entourgeId , "text": entourgeName});
		//$('#entourge_Dialog').val(entourgeName);
		$('#dialog_search_entourge').modal('toggle');
	}
	
});

$('#btn_search_entourge_table').on('click',function(){
	searchEntourgeTable();
});

$('#btn_clear_entourge_table').on('click',function(){
	clearSearchEntourgeTable();
});

keyUp.enter('#entourge_name_dialog',()=>{
	searchEntourgeTable();
});

keyUp.enter('#entourge_email_dialog',()=>{
	searchEntourgeTable();
});

keyUp.enter('#entourge_group_dialog',()=>{
	searchEntourgeTable();
});

function searchEntourgeTable(){
	$('#table_search_entourge').DataTable().columns(0).search($('#entourge_name_dialog').val().trim());
	$('#table_search_entourge').DataTable().columns(1).search($('#entourge_email_dialog').val().trim());
	$('#table_search_entourge').DataTable().columns(2).search($('#entourge_group_dialog').val().trim());
	$('#table_search_entourge').DataTable().draw();
}

function clearSearchEntourgeTable(){
	$('#entourge_name_dialog').val('');
	$('#entourge_email_dialog').val('');
	$('#entourge_group_dialog').val('');
	$('#table_search_entourge').DataTable().columns(0).search('');
	$('#table_search_entourge').DataTable().columns(1).search('');
	$('#table_search_entourge').DataTable().columns(2).search('');
	$('#table_search_entourge').DataTable().draw();
}

$("#dialog_search_entourge").on('shown.bs.modal', function (e) {
	if(entourgeDataTable == null){
		initial.entourgeDataTable();
	}	
});


function genDetailObjectToText(detailObjString){
	let textDetail = "";
	if(detailObjString == null || detailObjString == ''){
		return textDetail;
	}else{
		let detailObj = JSON.parse(detailObjString);
		
		textDetail += "<b>รายละเอียด : </b>";
		if(('Detail') in detailObj){
			textDetail += detailObj.Detail;
		}else{
			textDetail += " - ";
		}
		textDetail += "<br/>";
		
		
		textDetail += "<b>รายชื่อผู้ออกตรวจ : </b>";
		if(('Auditor') in detailObj){
			
			$.each(detailObj.Auditor,function(index,value){
				if(detailObj.Auditor.length-1 == index){
					textDetail += value.Name;
				}else{
					textDetail += value.Name+" , ";
				}
			});
			
		}else{
			textDetail += " - ";
		}
		textDetail += "<br/>";
		
		
		textDetail += "<b>รายชื่อผู้ติดตาม : </b>";
		if(('Entourage') in detailObj){
			if(detailObj.Entourage.length > 0){
				$.each(detailObj.Entourage,function(index,value){
					if(detailObj.Entourage.length-1 == index){
						textDetail += value.Name;
					}else{
						textDetail += value.Name+" , ";
					}
				});
			}else{
				textDetail += " - ";
			}		
			
		}else{
			textDetail += " - ";
		}
		textDetail += "<br/>";
		
		
		textDetail += "<b>วันที่นัดหมาย : </b>";
		if(('AppointDate') in detailObj){
			textDetail += detailObj.AppointDate;
		}else{
			textDetail += " - ";
		}
		textDetail += "<br/>";
		
		
		textDetail += "<b>เวลานัดหมาย : </b>";
		if(('AppointTime') in detailObj){
			textDetail += detailObj.AppointTime;
		}else{
			textDetail += " - ";
		}
		textDetail += "<br/>";
		
		return textDetail;
	}
	
}



$('#btn_save_appoint').on('click',function(){
	if(!$('#form-appoint-detail').valid()){
		return false;
	}
	let auditorList = [];
	let auditorDetailList = [];
	$($('#AuditorName').tagsinput('items')).each(function(i,objTagsinput){
		auditorList.push({
			userId : objTagsinput.value,
			fullname : objTagsinput.text
		});
		
		auditorDetailList.push({
			Id : objTagsinput.value,
			Name : objTagsinput.text
		});
	});
	
	let entourgeList = [];
	let entourgeDetailList = [];
	$($('#entourgeName').tagsinput('items')).each(function(i,objTagsinput){
		entourgeList.push({
			userId : objTagsinput.value,
			fullname : objTagsinput.text
		});
		entourgeDetailList.push({
			Id : objTagsinput.value,
			Name : objTagsinput.text
		});
	});
	
	let objAppoint = {
		appointId : objPassForm.appointId,
		appointDate : $('#appoint_date_pickers').val()+" "+$('#appoint_time_pickers').val()+":00",
		title : $('#appoint_title').val().trim(),
		detail : $('#appoint_detail').val().trim(),
		auditorId : auditorList,
		entourageId : entourgeList,
		supplierId : {supplierId : $('#supplierId').val().trim(), supplierCompany : $('#supplierCompany').val().trim()},
		locationId : {id : $('#locationId').val().trim()},
		enable : 'Y',
		appointHistoryId : [{
			title : $('#appoint_title').val().trim(),
			detail : JSON.stringify({
				Detail : $('#appoint_detail').val().trim(),
				Auditor : auditorDetailList,
				Entourage : entourgeDetailList,
				AppointDate : $('#appoint_date_pickers').val().trim(),
				AppointTime : $('#appoint_time_pickers').val().trim()
			}),
			//appointStatusId : {appointStatusId : "1"},
			enable : 'Y'
		}]
	}	
	if($('#appoint_date').val() != $('#appoint_date_pickers').val() || $('#appoint_time').val() != $('#appoint_time_pickers').val()){
		// Change Date Time
		if($('#appointStatusId').val().trim() == "1"){
			objAppoint['appointStatusId'] = {appointStatusId : $('#appointStatusId').val().trim()};
			objAppoint.appointHistoryId[0]['appointStatusId'] = {appointStatusId : $('#appointStatusId').val().trim()};
			updateAppoint.withoutChangeState(objAppoint);
			
		}else if($('#appointStatusId').val().trim() == "2"){
			objAppoint['appointStatusId'] = {appointStatusId : "3"};
			objAppoint.appointHistoryId[0]['appointStatusId'] = {appointStatusId : "3"};
			updateAppoint.withChangeState(objAppoint);
			
		}else if($('#appointStatusId').val().trim() == "3"){
			objAppoint['appointStatusId'] = {appointStatusId : $('#appointStatusId').val().trim()};
			objAppoint.appointHistoryId[0]['appointStatusId'] = {appointStatusId : $('#appointStatusId').val().trim()};
			updateAppoint.withoutChangeState(objAppoint);
		}
		
	}else{
		objAppoint['appointStatusId'] = {appointStatusId : $('#appointStatusId').val().trim()};
		objAppoint.appointHistoryId[0]['appointStatusId'] = {appointStatusId : $('#appointStatusId').val().trim()};
		updateAppoint.withoutChangeState(objAppoint);
	}
});


$('#btn_cancel_appoint').on('click',function(){
	if(!$('#form-appoint-detail').valid()){
		return false;
	}
	let auditorList = [];
	let auditorDetailList = [];
	$($('#AuditorName').tagsinput('items')).each(function(i,objTagsinput){
		auditorList.push({
			userId : objTagsinput.value,
			fullname : objTagsinput.text
		});
		
		auditorDetailList.push({
			Id : objTagsinput.value,
			Name : objTagsinput.text
		});
	});
	
	let entourgeList = [];
	let entourgeDetailList = [];
	$($('#entourgeName').tagsinput('items')).each(function(i,objTagsinput){
		entourgeList.push({
			userId : objTagsinput.value,
			fullname : objTagsinput.text
		});
		entourgeDetailList.push({
			Id : objTagsinput.value,
			Name : objTagsinput.text
		});
	});
	
						
	let objAppoint = {
		appointId : objPassForm.appointId,
		appointDate : $('#appoint_date_pickers').val()+" "+$('#appoint_time_pickers').val()+":00",
		title : $('#appoint_title').val().trim(),
		detail : $('#appoint_detail').val().trim(),
		auditorId : auditorList,
		entourageId : entourgeList,
		supplierId : {supplierId : $('#supplierId').val().trim(), supplierCompany : $('#supplierCompany').val().trim()},
		locationId : {id : $('#locationId').val().trim()},
		enable : 'Y',
		appointHistoryId : [{
			title : $('#appoint_title').val().trim(),
			detail : JSON.stringify({
				Detail : $('#appoint_detail').val().trim(),
				Auditor : auditorDetailList,
				Entourage : entourgeDetailList,
				AppointDate : $('#appoint_date_pickers').val().trim(),
				AppointTime : $('#appoint_time_pickers').val().trim()
			}),
					//appointStatusId : {appointStatusId : "1"},
					enable : 'Y'
		}]
	}
			
	objAppoint['appointStatusId'] = {appointStatusId : "4"};
	objAppoint.appointHistoryId[0]['appointStatusId'] = {appointStatusId : "4"};	
	
	ConfirmModal.setupModal("warning","ยืนยัน", "การดำเนินการนี้จะทำการส่งอีเมล์ให้กับผู้ที่เกี่ยวข้องทั้งหมด ยืนยันการยกเลิกการนัดหมายนี้ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			let url = contextPath+"/api/appoint/update_appoint";
			ajaxProcess.submit(url, 'POST', objAppoint, '#portlet-main-appoint', (data)=>{
				
				$('#portlet-history').find('.portlet-body').find('ul').empty();
										
				initial.appointDetail(objPassForm.appointId);
				initial.appointHistoryList(objAppoint.appointId);
			});
		}
	});
	
});





var updateAppoint = function(){
	return {
		withoutChangeState : function(objAppoint){	
			ConfirmModal.setupModal("warning","ยืนยัน", "การดำเนินการนี้จะทำการส่งอีเมล์ให้กับผู้ที่เกี่ยวข้องทั้งหมด ยืนยันการบันทึกข้อมูลใช่หรือไม่?");	
			ConfirmModal.confirmResult(function(isTrue){
				if(isTrue){
					let url = contextPath+"/api/appoint/update_appoint";
					let plandate_url = contextPath+"/api/appoint/update_plandate_checklist_plan";
					let obj = {
							appointId: objAppoint.appointId,
							planDate: objAppoint.appointDate
					}
					ajaxProcess.submit(url, 'POST', objAppoint, '#portlet-main-appoint', (data)=>{
						$('#portlet-history').find('.portlet-body').find('ul').empty();												
						initial.appointDetail(objPassForm.appointId);
						initial.appointHistoryList(objAppoint.appointId);
						ajaxProcess.submit(plandate_url, 'POST', obj, '#portlet-main-appoint', (data)=>{
							
						});
					});
				}
			});
			
		},
		withChangeState : function(objAppoint){
			//console.log(objAppoint)
			ConfirmModal.setupModal("warning","ยืนยัน", "การดำเนินการนี้จะทำการส่งอีเมล์ให้กับผู้ที่เกี่ยวข้องทั้งหมด ยืนยันการบันทึกข้อมูลใช่หรือไม่?");	
			ConfirmModal.confirmResult(function(isTrue){
				if(isTrue){
					let url = contextPath+"/api/appoint/update_appoint";
					let plandate_url = contextPath+"/api/appoint/update_plandate_checklist_plan";
					let obj = {
							appointId: objAppoint.appointId,
							planDate: objAppoint.appointDate
					}
					ajaxProcess.submit(url, 'POST', objAppoint, '#portlet-main-appoint', (data)=>{					
						$('#portlet-history').find('.portlet-body').find('ul').empty();
						initial.appointHistoryList(objAppoint.appointId);
						initial.appointDetail(objPassForm.appointId);
						ajaxProcess.submit(plandate_url, 'POST', obj, '#portlet-main-appoint', (data)=>{
							
						});
					});
				}
			});
			
		}
	}
}();


$('#appoint_btn_change').on('click',function(){
	
	if(!$('#form-appoint-change').valid()){
		return false;
	}
	
	let auditorList = [];
	let auditorDetailList = [];
	$($('#AuditorName').tagsinput('items')).each(function(i,objTagsinput){
		auditorList.push({
			userId : objTagsinput.value,
			fullname : objTagsinput.text
		});
		
		auditorDetailList.push({
			Id : objTagsinput.value,
			Name : objTagsinput.text
		});
	});
	
	let entourgeList = [];
	let entourgeDetailList = [];
	$($('#entourgeName').tagsinput('items')).each(function(i,objTagsinput){
		entourgeList.push({
			userId : objTagsinput.value,
			fullname : objTagsinput.text
		});
		entourgeDetailList.push({
			Id : objTagsinput.value,
			Name : objTagsinput.text
		});
	});
	
	let dateChangeFrom = $('#appoint_date_change_from').val().trim();
	let dateChangeTo = $('#appoint_date_change_to').val().trim();
	let timeChange = $('#appoint_time_change').val().trim();
	let detailForChangeAppoint = $('#appoint_detail_change').val().trim();
	
	let autoAppendMessage = " - ขอเลื่อนนัดเป็น";
	if(dateChangeTo != ""){
		autoAppendMessage += "ระหว่างวันที่ "+dateChangeFrom+" ถึงวันที่ "+dateChangeTo+" ";
	}else{
		autoAppendMessage += "วันที่ "+dateChangeFrom+" ";
	}
	
	autoAppendMessage += "เวลา "+timeChange+" น. โดยประมาณ จึงแจ้งมาเพื่อพิจารณาการเลื่อนนัดในครั้งนี้";	
	detailForChangeAppoint += autoAppendMessage;
	
	let objAppoint = {
		appointId : objPassForm.appointId,
		appointDate : $('#appoint_date_pickers').val()+" "+$('#appoint_time_pickers').val()+":00",
		title : $('#appoint_title').val().trim(),
		detail : $('#appoint_detail').val().trim(),
		auditorId : auditorList,
		entourageId : entourgeList,
		supplierId : {supplierId : $('#supplierId').val().trim(), supplierCompany : $('#supplierCompany').val().trim()},
		locationId : {id : $('#locationId').val().trim()},
		appointStatusId : {appointStatusId : "2"},
		enable : 'Y',
		appointHistoryId : [{
			title : "ขอเลื่อนการเข้าตรวจ",
			detail : JSON.stringify({
				Detail : detailForChangeAppoint,
				Auditor : auditorDetailList,
				Entourage : entourgeDetailList,
				AppointDate : $('#appoint_date_pickers').val().trim(),
				AppointTime : $('#appoint_time_pickers').val().trim()
			}),
			appointStatusId : {appointStatusId : "2"},
			enable : 'Y'
		}]
	};
	
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการบันทึกข้อมูลการขอเลื่อนนัดนี้ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			let url = contextPath+"/api/appoint/update_appoint";
			ajaxProcess.submit(url, 'POST', objAppoint, '#portlet-appoint-change', (data)=>{
				if(data.result){
					$('#appoint_detail_change').val('');
					$('#portlet-history').find('.portlet-body').find('ul').empty();
					initial.appointHistoryList(objAppoint.appointId);
				}else{
					
				}
			});
		}
	});
	
	
});
/*var startDate,endDate;
var detailAppointObj;
var checkAuditorDataTable = true;
var objDetail;
$(function(){	
	BlockUi.blockPosition('#portlet-main-appoint');
	BlockUi.blockPosition('#portlet-appoint-change');
	$("#AuditorName").tagsinput({	    		
		allowDuplicates: false,
		itemValue: 'value',
		itemText: 'text',
		tagClass: 'label label-danger',
		freeInput: false
	});
	
	startDate = $('.datepicker-change-from #appoint_date_change_from').datepicker({
		language: "th",
		format: 'dd/mm/yyyy',
		orientation: "bottom auto",
		autoclose: true,
		startDate: new Date()
	});
	$('.datepicker-change-from #appoint_date_change_from').datepicker('setDate', 'today');
	var newDateStart = new Date();
	newDateStart.setDate(newDateStart.getDate() + 1);
	endDate = $('.datepicker-change-from #appoint_date_change_to').datepicker({
		language: "th",
		format: 'dd/mm/yyyy',
		orientation: "bottom auto",
		autoclose: true,
		startDate : newDateStart,
		clearBtn: true
	});
	
	startDate.on('changeDate', function(ev){			
		var newDate = new Date(ev.date);
		
		newDate.setDate(newDate.getDate() + 1);
		
		//$('.datepicker-change-from #appoint_date_change_to').datepicker('setDate',newDate);
		$('.datepicker-change-from #appoint_date_change_to').datepicker('setStartDate',newDate);
		
		//$('#appoint_date_change_to').focus();
	}).data('datepicker');		
	
	$('.input-group.datepicker-change').datepicker({
		language: "th",
		format: 'dd/mm/yyyy',
		orientation: "bottom auto",
		autoclose: false,
		startDate: new Date(),
	    multidate: true,
	    multidateSeparator: "-",
	    clearBtn: true,
	    toggleActive : true
	});	
	
	
	$('.clockpicker-change').clockpicker({	    		
		autoclose: true,
	    donetext: 'Done',
	    placement: 'bottom',
	    'default': 'now'
	});	
	
	
	if($('#detail_Appoint').val().trim() != ""){
		var detailAppointString = decodeURIComponent($('#detail_Appoint').val().trim());
		detailAppointObj = JSON.parse(detailAppointString);
		var auditorObj = JSON.parse(detailAppointObj.auditor);
		$('.appointName_Label').html(detailAppointObj.appointNameLable);
		$('#appointId').val(detailAppointObj.appointId);
		$('#supplierCompany').val(detailAppointObj.supplierCompany);
		$('#supplierId').val(detailAppointObj.supplierId);
		$('#appoint_title').val(detailAppointObj.title);
		objDetail = JSON.parse(detailAppointObj.detail);		
		$('#appoint_detail').val(objDetail.Detail);
		$('#appointNameId').val(detailAppointObj.appointNameId);
		var tmpDateTime = detailAppointObj.appointDateTime.trim().split(' ');
		var tmpTime = tmpDateTime[1].split(':');
		$('#appoint_date').val(tmpDateTime[0]);
		$('#appoint_time').val(tmpTime[0]+":"+tmpTime[1]);	
		if(detailAppointObj.appointStatusId == 1 || detailAppointObj.appointStatusId == 2 || detailAppointObj.appointStatusId == 3){
			$('.apponit-datetime').hide();
			
			$('.apponit-datetime-pickers .datepicker-from').datepicker({
				language: "th",
				format: 'dd/mm/yyyy',
				orientation: "bottom auto",
				autoclose: true
			});			
			$('.apponit-datetime-pickers .datepicker-from').datepicker('setDate',tmpDateTime[0]);
			$('.apponit-datetime-pickers .datepicker-from').datepicker('setStartDate',new Date());
			var timeTemp = tmpDateTime[1].split(':');
			var time = timeTemp[0]+':'+timeTemp[1];
			$('.apponit-datetime-pickers .timepicker-from').clockpicker({	    		
				autoclose: true,
			    donetext: 'Done',
			    placement: 'bottom',
			    //'default': 'now'
			    'default' : time
			});
			$('.apponit-datetime-pickers .timepicker-from #appoint_time_pickers').val(time);
			
		}else{
			$('.apponit-datetime-pickers').hide();
		}		
		$.each(auditorObj,function(index,value){
			$('#AuditorName').tagsinput('add', { "value": value.auditorId , "text": value.auditorName});
			
		});
		getAppointHistory($('#appointId').val());	
		loadSession();
		BlockUi.unBlockPosition('#portlet-main-appoint');
		BlockUi.unBlockPosition('#portlet-appoint-change');	
		
	}else{
		window.location.href = contextPath+"/appoint.jsp";
	}
	
});




function getAppointHistory(appointId){
	//url : contextPath + "/api/appoint_history/appoint_history_list_by_appoint/"+JSON.stringify({appointId : appointId}),
	$.ajax({
		url : contextPath + "/api/appoint/get_appoint_history/"+appointId,
        type : 'GET',
        contentType: "application/json",
        //data : JSON.stringify(objForm),
        beforeSend : function(arr, $form, options){
        	BlockUi.blockPosition('#portlet-history');
	    },
	    complete : function(){	
	    	BlockUi.unBlockPosition('#portlet-history');
	    },
	    success : function(data, textStatus, jqXHR) {
	    	console.log(data);
	    	for(index = data.appointHistory.length-1 ; index >= 0; index--){
	    		
	    		var userGroupId = data.user[index].userGroupId.userGroupId.trim();
	    		var userFullname = data.user[index].fullName.trim();
	    		var dateTime = data.appointHistory[index].createDate.trim();
	    		var title = data.appointHistory[index].title.trim();
	    		var detail = data.appointHistory[index].detail.trim();
	    		var supplierCompany = data.supplierId.supplierCompany.trim();
	    		
	    		
	    		if(userGroupId == 1 || userGroupId == 2){
	    			var detailSplit = detail.trim().split("รายชื่อผู้เข้าตรวจ : ");
	    			if(detailSplit.length > 1){
	    				detailAppoint = detailSplit[0].trim();
	    				detailSplit = detailSplit[1].trim().split("วันที่นัดหมายเข้าตรวจ : ");
	    				detailAudit = detailSplit[0].trim();
	    				if(detailSplit.length > 1){
	    					detailSplit = detailSplit[1].trim().split("เวลานัดหมายเข้าตรวจ : ");
	    					detailDate = detailSplit[0].trim();
	    					if(detailSplit.length > 1){
	    						detailSplit = detailSplit[1].trim().split(" ");
	    						detailTime = detailSplit[0].trim();
	    						if(detailSplit.length > 1){
	    							detailFreeText = detailSplit[1].trim();
	    						}
	    					}
	    				}
	    			}else{
	    				detailAppoint = detail;
	    			}
	    			var textDetail = prepareDetailHistory(JSON.parse(detail));
	    			
	    			$('.chats').append(
	    					$('<li class="in">').append(
	    								$('<div class="avatar">'+userFullname+'</div>')
	    							)
	    							.append(
	    								$('<div class="message">').append($('<span class="arrow"> </span>'))
	    														  .append($('<span class="datetime"> '+dateTime+' </span>'))
	    														  .append($('<span class="body">'+textDetail+'</span>'))
	    														  .append($('<a href="javascript:void(0);" title="<b>รายละเอียด</b>" data-toggle="popover" data-trigger="focus" data-placement="right" data-content="'+detail+'">รายละเอียดเพิ่มเติม</a>'))
	    							)
	    							.hide()
	    							.fadeIn(300)
	    					);
	    		}else{
	    			$('.chats').append(
	    					$('<li class="out">').append(
	    								$('<div class="avatar">'+supplierCompany+'</div>')
	    							)
	    							.append(
	    								$('<div class="message">').append($('<span class="arrow"> </span>'))
	    														  .append($('<span class="datetime"> '+dateTime+' </span>'))
	    														  .append($('<span class="body">'+textDetail+'</span>'))
	    														  .append($(''))
	    							)
	    							.hide()
	    							.fadeIn(300)
	    					);
	    		}
	    	}
	    	$('[data-toggle=popover]').popover({
	    		html : true
	    	});
	    	
		},
		error: function (jqXHR, textStatus, errorThrown) {
			customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
		}
	});
}




function loadSession() {
	$.ajax({
		url : contextPath +"/api/user/get_session_user",
		type : 'GET',
		contentType: "application/json",
		success : function(data){
			$('#session_userId').val(data.userId);
		}
	});
}


$('#appoint_btn_change').on('click',function(){
	
	var appointId = $('#appointId').val().trim();
	var appointDateChangeFrom = $('#appoint_date_change_from').val().trim();
	var appointDateChangeTo = $('#appoint_date_change_to').val().trim();
	var appointTimeChange = $('#appoint_time_change').val().trim();
	var appointDetailCheange = $('#appoint_detail_change').val().trim();
	var userIdSession = $('#session_userId').val().trim();	
	
	var msgAlert = "";
	if(appointDateChangeFrom == ""){
		msgAlert += '- กรุณาเลือกวันที่ต้องการเลื่อนนัด <br/>';
	}
	if(appointTimeChange == ""){
		msgAlert += '- กรุณาเลือกเวลาที่ต้องการเลื่อนนัด <br/>';
	}
	if(appointDetailCheange == ""){
		msgAlert += '- กรุณากรอกรายละเอียดในการเลื่อนนัด <br/>';
	}
	
	
	if(msgAlert !=""){
		msgAlert = 'กรุณาตรวจสอบข้อมูล<br/>'+msgAlert;
		customMessageDialog("warning", "ตรวจสอบข้อมูล", msgAlert, null);	  
		return false;
		
	}else{
		if( userIdSession == ""){
			msgAlert = 'เกิดข้อผิดพลาด<br/> - ไม่สามารถโหลดข้อมูลผู้ใช้งานได้กรุณาโหลดหน้าเว็บใหม่';
			customMessageDialog("error", "เกิดข้อผิดพลาด", msgAlert, null);	  
			return false;
		}
		
		var autoAppendMessage = " - ขอเลื่อนนัดเป็น";
		if(appointDateChangeTo != ""){
			autoAppendMessage += "ระหว่างวันที่ "+appointDateChangeFrom+" ถึงวันที่ "+appointDateChangeTo+" ";
		}else{
			autoAppendMessage += "วันที่ "+appointDateChangeFrom+" ";
		}
		
		autoAppendMessage += "เวลา "+appointTimeChange+" น. โดยประมาณ จึงแจ้งมาเพื่อพิจารณาการเลื่อนนัดในครั้งนี้";		
		appointDetailCheange += autoAppendMessage;
		var auditorDetail = $('#AuditorName').tagsinput('items');
		var auditorName = [];
		$.each(auditorDetail,function(index,value){			
			auditorName.push(value.text);
		});
		var objDetailAppoint = {
			"Topic" : "ขอเลื่อนการเข้าตรวจ",
			"Detail" : appointDetailCheange,
			"AuditorName" : auditorName,
			"AppointDate" : $('#appoint_date').val().trim(),
			"AppointTime" : $('#appoint_time').val().trim()
		}
		
		var objForm = {
			appointId : appointId,
			//appointDate : appointDateChange + " "+ appointTimeChange+":00",
			title : "ขอเลื่อนการเข้าตรวจ",
			detail : JSON.stringify(objDetailAppoint),
			appointStatusId : {appointStatusId : '2'},
			statusId : '1',
			updateBy : userIdSession
		};
		ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการบันทึกข้อมูลการขอเลื่อนนัดนี้ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){
				$.ajax({
					url : contextPath + "/api/appoint/change_appoint_status",
			        type : 'POST',
			        contentType: "application/json",
			        data : JSON.stringify(objForm),
			        beforeSend : function(arr, $form, options){
			        	BlockUi.blockPosition('#portlet-appoint-change');
				    },
				    complete : function(){	
			        	setTimeout(function(){
			        		BlockUi.unBlockPosition('#portlet-appoint-change');
			        	},300);
				    },
				    success : function(data, textStatus, jqXHR) {
				    	if(data == true){		    		
				    		customMessageDialog("success", "ข้อความ", "สถานะการดำเนินการ <br/> - ทำการบันทึกการเลื่อนนัดหมายสำเร็จ", null);
				    		$('.chats').empty();
				    		getAppointHistory(appointId);
				    		$('.datepicker-change-from #appoint_date_change_from').datepicker('setDate', 'today');
				    		$('.datepicker-change-from #appoint_date_change_to').val('');
				    		$('#appoint_detail_change').val('');
				    		$('#appoint_time_change').val('');
				    	}else{
				    		customMessageDialog("error", "ข้อความ", "สถานะการดำเนินการ <br/> - ทำการบันทึกการเลื่อนนัดหมายไม่สำเร็จ", null);
				    	}  		    	
		  			},
		  			error: function (jqXHR, textStatus, errorThrown) {
		  				customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
		  			}
				});
			}
		});	
		
	}
});


$('#dialog_search_auditor').on('shown.bs.modal', function (e){
	if(checkAuditorDataTable){
		loadDatableSearchAudit();
		$('#table_search_audit').DataTable().columns(3).search('2');
		$('#table_search_audit').DataTable().draw();
		checkAuditorDataTable = false;
	}
});


$('#btn_save_appoint').on('click',function(){
	var sessionUserId = $('#session_userId').val().trim();
	var appointId = $('#appointId').val().trim();
	var auditorDetail = $('#AuditorName').tagsinput('items');
	var dateAppoint = $('#appoint_date').val().trim();
	var timeAppoint = $('#appoint_time').val().trim();
	var dateAppointPicker = $('#appoint_date_pickers').val().trim();
	var timeAppointPicker = $('#appoint_time_pickers').val().trim();
	var appointTitle = $('#appoint_title').val().trim();
	var appointDetail = $('#appoint_detail').val().trim();
	var statusAppoint = detailAppointObj.appointStatusId;
	
	var msgAlert = '';
	if(sessionUserId == ''){
		msgAlert += '- ไม่สามารถโหลดข้อมูลผู้ใช้ได้ กรุณาโหลดหน้าเว็บใหม่ <br/>';
	}
	if(statusAppoint == ''){
		msgAlert += '- สถานะการนัดหมายไม่ถูกต้อง กรุณาโหลดหน้าเว็บใหม่ <br/>';
	}
	if(auditorDetail == ''){
		msgAlert += '- กรุณาเลือกผู้ออกตรวจอย่างน้อย 1 คน <br/>';
	}
	if(dateAppoint == ''){
		msgAlert += '- ระบบไม่สามารถโหลดวันที่นัดหมายได้สำเร็จ กรุณาโหลดหน้าเว็บใหม่ <br/>';
	}
	if(timeAppoint == ''){
		msgAlert += '- ระบบไม่สามารถโหลดเวลานัดหมายได้สำเร็จ กรุณาโหลดหน้าเว็บใหม่ <br/>';
	}
	if(dateAppointPicker == ''){
		msgAlert += '- กรุณาเลือกวันที่นัดหมายใหม่ <br/>';
	}
	if(timeAppointPicker == ''){
		msgAlert += '- กรุณาเลือกเวลานัดหมายใหม่ <br/>';
	}
	if(appointTitle == ''){
		msgAlert += '- กรุณาใส่หัวข้อนัดหมาย <br/>';
	}
	
	if(msgAlert != ''){
		msgAlert = 'กรุณาตรวจสอบข้อมูล <br/>' + msgAlert;
		customMessageDialog("warning", "ตรวจสอบข้อมูล", msgAlert, null);	
		return false;
	}
	
	var auditorList = [];
	var auditorName = [];
	$.each(auditorDetail,function(index,value){
		var auditorObject = {
			userId : value.value,
			fullName : value.text
		};
		auditorName.push(value.text);
		auditorList.push(auditorObject);
	});
	
	var objDetailAppoint = {
			Topic : $('#appoint_title').val().trim(),
			Detail : $('#appoint_detail').val().trim(),
			AuditorName : auditorName,
			AppointDate : $('#appoint_date_pickers').val().trim(),
			AppointTime : $('#appoint_time_pickers').val().trim()
	}
	
	var dateAndTime = "";
	if(statusAppoint == 1 || statusAppoint == 2 || statusAppoint == 3){
		dateAndTime = dateAppointPicker+" "+timeAppointPicker+":00";
		if(detailAppointObj.statusId == 2){
			dateAndTime = dateAppoint+" "+timeAppoint+"-"+dateAppointPicker+" "+timeAppointPicker+":00";
		}
	}
		
	
	var objForm ={
		appointId : appointId,
		updateBy : sessionUserId,
		user : auditorList,
		appointDate : dateAndTime,
		title : appointTitle,
		detail : JSON.stringify(objDetailAppoint),
		appointStatusId : {appointStatusId : statusAppoint},
		statusId : '1'
	};
	console.log(objForm);
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการบันทึกข้อมูลการนัดหมายนี้ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			$.ajax({
				url : contextPath + "/api/appoint/appoint_detail_process",
		        type : 'POST',
		        contentType: "application/json",
		        data : JSON.stringify(objForm),
		        beforeSend : function(arr, $form, options){
		        	BlockUi.blockPosition('#portlet-main-appoint');
			    },
			    complete : function(){	
		        	setTimeout(function(){
		        		BlockUi.unBlockPosition('#portlet-main-appoint');
		        	},300);
			    },
			    success : function(data, textStatus, jqXHR) {
			    	if(data.result){		 
			    		customMessageDialog("success", "ข้อความ", data.message, null);
			    		$('.chats').empty();
			    		getAppointHistory(appointId);			    		
			    	}else{
			    		customMessageDialog("error", "ข้อความ", data.message, null);
			    	}  		    	
	  			},
	  			error: function (jqXHR, textStatus, errorThrown) {
	  				customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
	  			}
			});
		}
	});	
	
	
});



function loadDatableSearchAudit(){
	var dataTableAudit = $('#table_search_audit').DataTable({
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": contextPath + "/api/user/datatable/get_user_auditor_list",
	        "type": 'POST',
	        contentType: "application/json",
	        beforeSend : function(arr, $form, options){
	        	BlockUi.blockPosition('#dialog_search_auditor');
		    },
		    complete : function(){	
	        	setTimeout(function(){
	        		BlockUi.unBlockPosition('#dialog_search_auditor');
	        	},300);
		    },
	        data: function (d) {
	        	
	        	return JSON.stringify(d);
	        }
	    },
	    "language": {
	        "url": "assets/global/plugins/datatables/Thai.json",
	        "processing": "<span class='glyphicon glyphicon-refresh glyphicon-refresh-animate'></span>"
	    },
	    "columns": [
	    	{ "data": "fullName" ,name:"u.fullname|LIKE"},
	    	{ "data": "userId" ,name:"u.user_id|="},
	    	{ "data": "email" ,name:"u.email|LIKE"},	    	
	        { "data": "userGroupId.userGroupId" ,name:"ug.user_group_id|="}
	    ],
	    "columnDefs": [
	    	{
	        	"targets": [1],
	            "visible": false,
	            "searchable": false
	        },
	        {
	        	"targets": [3],
	            "visible": false,
	            "searchable": true
	        },
	        {
	            "searchable": false,
	            "orderable": false,
	            "targets": [4]
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [0,2]
	        },
	        {
	            "targets": 4,
	            "orderable": false,
	            "render": function (data, type, row) {
	            	
	            	
	            	var btnAction = '<button type="button" class="btn btn-default btn-select-audit"><i class="fa fa-check"></i> เลือก</button>';
	            	
	               
	                return btnAction;
	            },
	            "width": "10px"  
	        }
	    ],
	    "order": [[1, 'asc']],
	    "initComplete": function (settings, json) {
	    	
	    	$('.dataTables_filter').remove();
	    }
	});
}


$('#table_search_audit').on('click','.btn-select-audit',function(){
	var data = $('#table_search_audit').DataTable().row( $(this).parents('tr') ).data();	
	var auditName = data.fullName.trim();
	var auditId = data.userId.trim();
	$('#AuditorName').tagsinput('add', { "value": auditId , "text": auditName});
	$('#auditName_Dialog').val(auditName);
	$('#AuditorId_Dialog').val(auditId);
	$('#dialog_search_auditor').modal('toggle');
});


function prepareDetailHistory(obj){
	var auditorName = "";
	$.each(obj.AuditorName,function(index,value){
		if(obj.AuditorName.length-1 == index){
			auditorName += value;
		}else{
			auditorName += value+" , ";
		}
	});
	var detailText = "";
	if(obj.Topic == ""){
		detailText += "<b>หัวข้อ : </b>-";
	}else{
		detailText += "<b>หัวข้อ : </b>"+obj.Topic;
	}
	detailText += "<br/>";
	if(obj.Detail == ""){
		detailText += "<b>รายละเอียด : </b>-";
	}else{
		detailText += "<b>รายละเอียด : </b>"+obj.Detail;
	}
	detailText += "<br/>";
	
	if(auditorName == ""){
		detailText += "<b>รายชื่อผู้ออกตรวจ :</b> -";
	}else{
		detailText += "<b>รายชื่อผู้ออกตรวจ : </b>"+auditorName;
	}
	detailText += "<br/>";
	
	if(obj.AppointDate == ""){
		detailText += "<b>วันที่นัดหมายเข้าตรวจ :</b> -";
	}else{
		detailText += "<b>วันที่นัดหมายเข้าตรวจ :</b> "+obj.AppointDate;
	}
	detailText += "<br/>";
	
	if(obj.AppointTime == ""){
		detailText += "<b>เวลานัดหมายเข้าตรวจ  :</b> -";
	}else{
		detailText += "<b>เวลานัดหมายเข้าตรวจ  :</b> "+obj.AppointTime+" น.";
	}
	detailText += "<br/>";
	
	//detailText += obj.detailFreeText;
	return detailText;
}
*/