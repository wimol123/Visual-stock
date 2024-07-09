$(function(){
	if($('#appointDetail').val() != ""){
		let appointDetailObj = JSON.parse(URI.decode($('#appointDetail').val().trim()));
		loadAppointDetail(appointDetailObj.appointId);
		appointHistoryList(appointDetailObj.appointId);
		let objectValidate = {
			element : '#form-appoint-change',
			require : ['appoint_date_change_from','appoint_time_change','appoint_detail_change'],
			message : ["กรุณาใส่วันเลื่อนนัด","กรุณาใส่เวลาเลื่อนนัด","กรุณาใส่รายละเอียดการเลื่อนนัด"]
		};
		validate.init(objectValidate);
		validate.add({
			element : "#appoint_detail_change",
			rules : [{maxlength : 300}],
			message : ["สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร"]
		});
	}
});


$('#btn_accept_appoint').on('click',function(){
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการยืนยันการนัดหมายนี้ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			actionConfirmAndCancel("5");
		}
	});
	
});

$('#btn_cancel_appoint').on('click',function(){
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการยกเลิกการนัดหมายนี้ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			actionConfirmAndCancel("4");
		}
	});
});

function actionConfirmAndCancel(appointStatusId){
	let detailAppointObj = JSON.parse(URI.decode($('#appointDetailObject').val()));
	
	let auditorDetailList = [];
	$(detailAppointObj.auditorId).each(function(i,v){
		auditorDetailList.push({
			Id : v.userId,
			Name : v.fullname
		});
	});
	
	let entourgeDetailList = [];
	$(detailAppointObj.entourageId).each(function(i,v){
		entourgeDetailList.push({
			Id : v.userId,
			Name : v.fullname
		});
	});
	
	let timeSplit = detailAppointObj.appointDate.split(" ")[1].trim().split(":");
	let objAppoint = {
		appointId : detailAppointObj.appointId,
		appointDate : detailAppointObj.appointDate.trim(),
		title : detailAppointObj.title.trim(),
		detail : detailAppointObj.detail.trim(),
		auditorId : detailAppointObj.auditorId,
		entourageId : detailAppointObj.entourageId,
		supplierId : {supplierId : detailAppointObj.supplierId.supplierId.trim(), supplierCompany : detailAppointObj.supplierId.supplierCompany.trim()},
		locationId : {id : detailAppointObj.locationId.id.trim()},
		appointStatusId : {appointStatusId : appointStatusId},
		enable : 'Y',
		appointHistoryId : [{
			title : detailAppointObj.title.trim(),
			detail : JSON.stringify({
				Detail : detailAppointObj.detail.trim(),
				Auditor : auditorDetailList,
				Entourage : entourgeDetailList,
				AppointDate : detailAppointObj.appointDate.split(" ")[0].trim(),
				AppointTime : timeSplit[0]+":"+timeSplit[1]
			}),
			appointStatusId : {appointStatusId : appointStatusId},
			enable : 'Y'
		}]
	};

	BlockUi.blockPosition('#portlet-appoint-change','warning', "กำลังบันทึกข้อมูลการนัดหมาย กรุณารอสักครู่");
	BlockUi.blockPosition('#portlet-history','warning', "กำลังบันทึกข้อมูลการนัดหมาย กรุณารอสักครู่");
	let url = contextPath+"/api/appoint/update_appoint";
	ajaxProcess.submit(url, 'POST', objAppoint, '#portlet-detail-appoint', (data)=>{
		if(data.result){
			$('#appoint_detail_change').val('');
			$('#portlet-history').find('.portlet-body').find('ul').empty();
			$("#btn_accept_appoint").slideUp();
			//$("#btn_cancel_appoint").slideUp();
			appointHistoryList(objAppoint.appointId);
			initialAppointChange(detailAppointObj);
			loadAppointDetail(objAppoint.appointId);
		}
		BlockUi.unBlockPosition('#portlet-appoint-change');
		BlockUi.unBlockPosition('#portlet-history');
		
	});
}

function loadAppointDetail(appointId){
	
	let url = contextPath + "/api/appoint/get_appoint_detail/"+JSON.stringify({
		appointId : appointId
	});
	ajaxProcess.submit(url, 'GET', null, '#portlet-detail-appoint', (data)=>{
		if(data.result){
			
			let detailAppoint = JSON.parse(data.message)[0];
			initialAppointChange(detailAppoint);
			$('#appointDetailObject').val(URI.encode(JSON.stringify(detailAppoint)));
			$('#status_text').text(detailAppoint.appointStatusId.appointStatusName.trim());
			$('#status_id').val(detailAppoint.appointStatusId.appointStatusId.trim());
			$('#status_text').css('background-color', detailAppoint.appointStatusId.statusColor);
			
			
			if(detailAppoint.auditorId.length > 0){
				let auditorText = "";
				$(detailAppoint.auditorId).each(function(i,v){	
					if(i != 0 && i < detailAppoint.auditorId.length){
						auditorText += "  ,  ";
					}
					auditorText += v.fullname;					
				});
				$('#auditor_row').html('<div> '+auditorText+' </div>');
			}else{
				$('#auditor_row').html('<div> - </div>');
			}
			
			if(detailAppoint.entourageId.length > 0){
				let entourageText = "";
				$(detailAppoint.entourageId).each(function(i,v){	
					if(i != 0 && i < detailAppoint.entourageId.length){
						entourageText += "  ,  ";
					}
					entourageText += v.fullname;					
				});
				
				$('#entourage_row').html('<div> '+entourageText+' </div>');
			}else{
				$('#entourage_row').html('<div> - </div>');
			}
			
			$('#appointDateTime_row').html('<div>'+detailAppoint.appointDate.trim()+'</div>');
			$('#location_row').html('<div>'+detailAppoint.locationId.locationName.trim()+'</div>');
			$('#title_row').html('<div>'+detailAppoint.title+'</div>');
			let detail = " - ";
			if(detailAppoint.detail != "")
				detail = detailAppoint.detail;
			$('#detail').html('<div>'+detail+'</div>');
			
			
			dateTime.currentDateTime('yyyy/MM/dd HH:mm:ss',(currentTimeDate)=>{				
				let dateAppoint = new Date(detailAppoint.appointDate.split(" ")[0].trim().replace( /(\d{2})\/(\d{2})\/(\d{4})/, "$2/$1/$3") + " "+detailAppoint.appointDate.split(" ")[1].trim());
				if(currentTimeDate < dateAppoint){
					if(detailAppoint.appointStatusId.appointStatusId == '1' || detailAppoint.appointStatusId.appointStatusId == '3'){
						$("#btn_accept_appoint").slideDown("slow");
						//$("#btn_cancel_appoint").slideDown("slow");
					}
				}
			});
		}		
	});
}


$('#appoint_btn_change').on('click',function(){
	if(!$('#form-appoint-change').valid()){
		return false;
	}
	
	let detailAppointObj = JSON.parse(URI.decode($('#appointDetailObject').val()));
	
	let auditorDetailList = [];
	$(detailAppointObj.auditorId).each(function(i,v){
		auditorDetailList.push({
			Id : v.userId,
			Name : v.fullname
		});
	});
	
	let entourgeDetailList = [];
	$(detailAppointObj.entourageId).each(function(i,v){
		entourgeDetailList.push({
			Id : v.userId,
			Name : v.fullname
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
	let timeSplit = detailAppointObj.appointDate.split(" ")[1].trim().split(":");
	
	
	let objAppoint = {
		appointId : detailAppointObj.appointId,
		appointDate : detailAppointObj.appointDate.trim(),
		title : detailAppointObj.title.trim(),
		detail : detailAppointObj.detail.trim(),
		auditorId : detailAppointObj.auditorId,
		entourageId : detailAppointObj.entourageId,
		supplierId : {supplierId : detailAppointObj.supplierId.supplierId.trim(), supplierCompany : detailAppointObj.supplierId.supplierCompany.trim()},
		locationId : {id : detailAppointObj.locationId.id.trim()},
		appointStatusId : {appointStatusId : "2"},
		enable : 'Y',
		appointHistoryId : [{
			title : "ขอเลื่อนการเข้าตรวจ",
			detail : JSON.stringify({
				Detail : detailForChangeAppoint,
				Auditor : auditorDetailList,
				Entourage : entourgeDetailList,
				AppointDate : detailAppointObj.appointDate.split(" ")[0].trim(),
				AppointTime : timeSplit[0]+":"+timeSplit[1]
			}),
			appointStatusId : {appointStatusId : "2"},
			enable : 'Y'
		}]
	};
	
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการบันทึกข้อมูลการขอเลื่อนนัดนี้ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			BlockUi.blockPosition('#portlet-detail-appoint','warning', "กำลังบันทึกข้อมูลการเลื่อนนัด กรุณารอสักครู่");
			BlockUi.blockPosition('#portlet-history','warning', "กำลังบันทึกข้อมูลการเลื่อนนัด กรุณารอสักครู่");
			let url = contextPath+"/api/appoint/update_appoint";
			ajaxProcess.submit(url, 'POST', objAppoint, '#portlet-appoint-change', (data)=>{
				if(data.result){
					$('#appoint_detail_change').val('');
					$('#portlet-history').find('.portlet-body').find('ul').empty();
					$("#btn_accept_appoint").slideUp();
					//$("#btn_cancel_appoint").slideUp();
					appointHistoryList(objAppoint.appointId);
					initialAppointChange(detailAppointObj);
					loadAppointDetail(objAppoint.appointId);
				}
				BlockUi.unBlockPosition('#portlet-detail-appoint');
				BlockUi.unBlockPosition('#portlet-history');
				
			});
		}
	});
	
});

function initialAppointChange(detailAppoint){
	if(detailAppoint.appointStatusId.appointStatusId == '2' || detailAppoint.appointStatusId.appointStatusId == '4' || detailAppoint.appointStatusId.appointStatusId == '5'|| detailAppoint.appointStatusId.appointStatusId == '6'){
		$("#portlet-appoint-change").addClass("disabledelements");
	}else{
		dateTime.currentDateTime('yyyy/MM/dd HH:mm:ss',(currentTimeDate)=>{
			let currentDate = new Date();
			currentDate.setDate(currentTimeDate.getDate());
			
			let currentTime = new Date();
			currentTime.setTime(currentTimeDate.getTime());	
			
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
	}
}


function appointHistoryList (appointId){
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
				if(objHistory[index].appointHistoryCreateBy.userGroupId.userGroupId == "3" || objHistory[index].appointHistoryCreateBy.userGroupId.userGroupId == "4"){
					
					$('.chats').append(
	    					$('<li class="in">').append(
	    								$('<div class="avatar">'+supplierCompany+'</div>')
	    							)
	    							.append(
	    								$('<div class="message">').append($('<span class="arrow"> </span>'))
	    														  .append($('<span class="datetime"> '+dateTimeCreate+' </span>'))
	    														  .append($('<span class="body">'+detailText+'</span>'))
	    														  .append($(''))
	    							)
	    							.hide()
	    							.fadeIn(300)
	    					);
					
					
				}else{
					
					$('.chats').append(
	    					$('<li class="out">').append(
	    								$('<div class="avatar">'+userFullname+'</div>')
	    							)
	    							.append(
	    								$('<div class="message">').append($('<span class="arrow"> </span>'))
	    														  .append($('<span class="datetime"> '+dateTimeCreate+' </span>'))
	    														  .append($('<span class="body">'+detailText+'</span>'))
	    														  //.append($('<a href="javascript:void(0);" title="<b>รายละเอียด</b>" data-toggle="popover" data-trigger="focus" data-placement="right" data-content="'+/*detail*/+'">รายละเอียดเพิ่มเติม</a>'))
	    							)
	    							.hide()
	    							.fadeIn(300)
	    					);
					
					
					
				}
			}
			
		}
	});
}


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