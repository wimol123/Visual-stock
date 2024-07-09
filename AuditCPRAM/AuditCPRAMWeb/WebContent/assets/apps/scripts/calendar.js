var date = null;           
var currentDate = null;
var AppCalendar = function() {

    return {
        //main function to initiate the module
        init: function() {
            this.initCalendar();
        },

        initCalendar: function() {

            if (!jQuery().fullCalendar) {
                return;
            }
            
            
            /*var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();*/
            
            
           

            var h = {};

            if (App.isRTL()) {
                if ($('#calendar').parents(".portlet").width() <= 720) {
                    $('#calendar').addClass("mobile");
                    h = {
                        right: 'title, prev, next',
                        center: '',
                        left: 'agendaDay, agendaWeek, month, today'
                    };
                } else {
                    $('#calendar').removeClass("mobile");
                    h = {
                        right: 'title',
                        center: '',
                        left: 'agendaDay, agendaWeek, month, today, prev,next'
                    };
                }
            } else {
                if ($('#calendar').parents(".portlet").width() <= 720) {
                    $('#calendar').addClass("mobile");
                    h = {
                        left: 'title, prev, next',
                        center: '',
                        right: 'today,month,agendaWeek,agendaDay'
                    };
                } else {
                    $('#calendar').removeClass("mobile");
                    h = {
                        left: 'title',
                        center: '',
                        right: 'prev,next,today,month,agendaWeek,agendaDay'
                    };
                }
            }

            var initDrag = function(el) {
                // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
                // it doesn't need to have a start or end
                var eventObject = {
                    title: $.trim(el.text()) // use the element's text as the event title
                };
                // store the Event Object in the DOM element so we can get to it later
                el.data('eventObject', eventObject);
                // make the event draggable using jQuery UI
                el.draggable({
                    zIndex: 999,
                    revert: true, // will cause the event to go back to its
                    revertDuration: 0 //  original position after the drag
                });
            };

            var addEvent = function(title) {
                title = title.length === 0 ? "Untitled Event" : title;
                var html = $('<div class="external-event label label-default">' + title + '</div>');
                jQuery('#event_box').append(html);
                initDrag(html);
            };

            $('#external-events div.external-event').each(function() {
                initDrag($(this));
            });

            $('#event_add').unbind('click').click(function() {
                var title = $('#event_title').val();
                addEvent(title);
            });

            //predefined events
            $('#event_box').html("");
            addEvent("My Event 1");
            addEvent("My Event 2");
            addEvent("My Event 3");
            addEvent("My Event 4");
            addEvent("My Event 5");
            addEvent("My Event 6");
            
            var originalDate;

            $('#calendar').fullCalendar('destroy'); // destroy the calendar
            $('#calendar').fullCalendar({ //re-initialize the calendar
                header: h,
                defaultView: 'month', // change default view with available options from http://arshaw.com/fullcalendar/docs/views/Available_Views/ 
                slotMinutes: 15,
                editable: true,
                droppable: true, // this allows things to be dropped onto the calendar !!!
                timeFormat: 'HH:mm น.', // uppercase H for 24-hour clock
                drop: function(date, allDay) { // this function is called when something is dropped

                    // retrieve the dropped element's stored Event Object
                    var originalEventObject = $(this).data('eventObject');
                    // we need to copy it, so that multiple events don't have a reference to the same object
                    var copiedEventObject = $.extend({}, originalEventObject);

                    // assign it the date that was reported
                    copiedEventObject.start = date;
                    copiedEventObject.allDay = allDay;
                    copiedEventObject.className = $(this).attr("data-class");

                    // render the event on the calendar
                    // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
                    $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

                    // is the "remove after drop" checkbox checked?
                    if ($('#drop-remove').is(':checked')) {
                        // if so, remove the element from the "Draggable Events" list
                        $(this).remove();
                    }
                },
                events: [/*{
                    title: 'บริษัท  A',
                    start: '2018-09-01'
                }, {
                    title: 'บริษัท B',
                    start: '2018-09-04'
                }, {
                    title: 'บริษัท C',
                    start: '2018-09-05'
                }*/],
             	
          	   	eventClick: function(calEvent, jsEvent, view) {

          	     //alert('Event: ' + calEvent.title);
          	     //alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
          	     //alert('View: ' + view.name);

          	     // change the border color just for fun
          	     //$(this).css('border-color', 'red');
          	   	let objAppointDetail = JSON.parse(calEvent.id);
          	   	console.log(objAppointDetail)
          	   	$('#event_id').val(calEvent.id);
          	   	let stringAuditorList = "";
          	   	
          	   	$.each(objAppointDetail.auditorId,function(index,value){          	   		
          	   		stringAuditorList += value.fullname;
          	   		if(objAppointDetail.auditorId.length - 1 != index){
          	   			stringAuditorList += ", ";
          	   		}
          	   	});
          	   	
          	   	$('#row_auditor').text(stringAuditorList);
          	   	$('#row_company').text(objAppointDetail.supplierId.supplierCompany);
          	   	$('#row_appointDate').text(objAppointDetail.appointDate);
          		$('#row_title').text(objAppointDetail.title);
          		$('#row_appointBy').text(objAppointDetail.appointCreateBy.fullname);
          	    $("#dialog_detail").modal("show");
          	  },
          	 eventDragStart: function(event) {
                 originalDate = new Date(event.start);  // Make a copy of the event date
              },
          	 eventDrop: function (event, delta, revertFunc, jsEvent, ui, view ) {
          		
          		var newDate = event._start._d;      
          		var objEventId = JSON.parse(event._id);
          		if(objEventId.appointStatusId.appointStatusId == "4" || objEventId.appointStatusId.appointStatusId == "5" || objEventId.appointStatusId.appointStatusId == "6"){
          			revertFunc();
          			return;
          		}
          		
          		//newDate.setDate(originalDate.getDate() + delta._days);          	
          		if(JSON.parse(event._id).groupIdSession == 1 || JSON.parse(event._id).groupIdSession == 2){
          			if(event._start._d > currentDate){          	
              			ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการบันทึกข้อมูลการขอเลื่อนนัดนี้ใช่หรือไม่?");	
              			ConfirmModal.confirmResult(function(isTrue){
              				if(isTrue){
              					
              					let dateMoveTo = newDate.getDate().toString().padStart(2, '0')+"/"+(newDate.getMonth()+1).toString().padStart(2, '0')+"/"+newDate.getFullYear();
                      			let timeOriginal = event._start._i.getHours().toString().padStart(2, '0')+":"+event._start._i.getMinutes().toString().padStart(2, '0')+":"+event._start._i.getMilliseconds().toString().padStart(2, '0');
                      			//var dateOriginalObj = new Date(event._start._i.split(" ")[0].toString().trim());
                      			let dateOriginal = event._start._i.getDate().toString().padStart(2, '0')+"/"+(event._start._i.getMonth()+1).toString().padStart(2, '0')+"/"+event._start._i.getFullYear();                      			
                      			let dateAndTime = dateMoveTo+" "+timeOriginal;
                      			
                      			/*console.log(JSON.parse(objEventId.appointHistoryId[0].detail))
                      			console.log(objEventId);*/
                      			let auditorList = [];
                      			let entourgeList = [];
                      			$(objEventId.auditorId).each(function(i,v){
                      				auditorList.push({
                      					userId : v.userId,
                      					fullname : v.fullname
                      				});
                      			});
                      			
                      			$(objEventId.entourageId).each(function(i,v){
                      				entourgeList.push({
                      					userId : v.userId,
                      					fullname : v.fullname
                      				});
                      			});
                      			
                      			let objAppoint = {
                      				appointId : objEventId.appointId,
                      				appointDate : dateAndTime,
                      				title : objEventId.title.trim(),
                      				detail : objEventId.detail.trim(),
                      				auditorId : auditorList,
                      				entourageId : entourgeList,
                      				supplierId : {supplierId : objEventId.supplierId.supplierId, supplierCompany : objEventId.supplierId.supplierCompany},
                      				locationId : {id : objEventId.locationId.id},
                      				enable : 'Y',                      				
                      				appointHistoryId : [{
                      					title : objEventId.title.trim(),
                      					detail : JSON.stringify({
                      						Detail : objEventId.detail.trim(),
                      						Auditor : JSON.parse(objEventId.appointHistoryId[0].detail).Auditor,
                      						Entourage : JSON.parse(objEventId.appointHistoryId[0].detail).Entourage,
                      						AppointDate : dateMoveTo,
                      						AppointTime : timeOriginal
                      					}),
                      						//appointStatusId : {appointStatusId : "1"},
                      					enable : 'Y'
                      				}]
                      			}
                      			
                      			if(objEventId.appointStatusId.appointStatusId == "1"){
                      				objAppoint['appointStatusId'] = {appointStatusId : objEventId.appointStatusId.appointStatusId};
                      				objAppoint.appointHistoryId[0]['appointStatusId'] = {appointStatusId : objEventId.appointStatusId.appointStatusId};
                      				
                      			}else if(objEventId.appointStatusId.appointStatusId == "2"){
                      				objAppoint['appointStatusId'] = {appointStatusId : "3"};
                      				objAppoint.appointHistoryId[0]['appointStatusId'] = {appointStatusId : "3"};
                      				
                      			}else if(objEventId.appointStatusId.appointStatusId == "3"){
                      				objAppoint['appointStatusId'] = {appointStatusId : objEventId.appointStatusId.appointStatusId};
                      				objAppoint.appointHistoryId[0]['appointStatusId'] = {appointStatusId : objEventId.appointStatusId.appointStatusId};
                      			}
                      			
                      			
                      			let url = contextPath+"/api/appoint/update_appoint";
            					ajaxProcess.submit(url, 'POST', objAppoint, null, (data)=>{
            						if(data.result){
            							objAppoint['groupIdSession'] = objEventId.groupIdSession;            							
            							event._id = JSON.stringify(objAppoint);
                          				event.id = JSON.stringify(objAppoint);
            						}else{
            							revertFunc();
            						}
            					});
                      			/*var result = processAceptDrop(objForm);
                      			if(!result){
                      				revertFunc();
                      			}else{
                      				objEventId.appointDateTime = dateAndTime;
                      				objEventId.detail = objForm.detail;
                      				event._id = JSON.stringify(objEventId);
                      				event.id = JSON.stringify(objEventId);
                      			}*/
                      			
              				}else{
              					revertFunc();
              				}
              			});          			
              			
              		}else{
              			customMessageDialog("warning", "ตรวจสอบข้อมูล", "กรุณาตรวจสอบข้อมูล <br/> - ไม่สามารถเปลี่ยนวันที่นัดหมายได้ เนื่องจากวันที่เลื่อนนัดหมายน้อยกว่าวันที่ปัจจุบัน", null);
              			revertFunc();
              		}
          		}
          		else{
          			customMessageDialog("error", "เกิดข้อผิดพลาด", "เกิดข้อผิดพลาด <br/> - ไม่สามารถเปลี่ยนวันที่นัดหมายได้ เนื่องจากไม่มีสิทธิ์สำหรับการเลื่อนนัดหมายผ่านหน้าปฏิทินได้", null);
          			revertFunc();
          		}
          		
          		
          	 },
                
            });
            

            
        }

    };

}();

jQuery(document).ready(function() {    
	dateTime.currentDateTime('yyyy/MM/dd HH:mm:ss',(dateTimeCurrent)=>{
    	currentDate = dateTimeCurrent;
    	date = dateTimeCurrent;
    	AppCalendar.init(); 
    });
   
});

