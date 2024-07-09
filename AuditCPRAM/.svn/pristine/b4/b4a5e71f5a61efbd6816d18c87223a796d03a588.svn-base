var userGroupId = null;
$(function(){
	loadFinalAuditResult();
	let url = contextPath+'/api/user/get_session_user';
	ajaxProcess.submit(url, 'GET', null, '', (data)=>{
		let objData = JSON.parse(data.message);
		userGroupId = objData.userGroupId.userGroupId;
	},{async:false});
	$('.input-group.datepicker-form').datepicker({
		language: "th",
		format: 'dd/mm/yyyy',
		orientation: "bottom auto",
		autoclose: true,
		clearBtn : true
	});
});


$(document).on('click','.edit_final_auditresult',function(){
	let data = $('#table_final_auditresult').DataTable().row( $(this).parents('tr') ).data();
	$('#pass_param').val(data.checklistPlanId.checklistPlanId);
	$('#pass_param').closest('form').submit();
});

keyUp.enter('#checklist_plan_no_search', ()=>{
	search();
});

keyUp.enter('#supplier_name_search', ()=>{
	search();
});

$('#btn_search').on('click',function(){
	search();
});

$('#btn_clear').on('click',function(){
	clearSearch();
});


function search(){
	$('#table_final_auditresult').DataTable().columns(1).search($('#checklist_plan_no_search').val().trim());
	$('#table_final_auditresult').DataTable().columns(2).search($('#supplier_name_search').val().trim());
	$('#table_final_auditresult').DataTable().columns(3).search($('#plan_date_textbox').val().trim());
	$('#table_final_auditresult').DataTable().columns(5).search($('#final_audit_status').val().trim());
	
	$('#table_final_auditresult').DataTable().draw();
}

function clearSearch(){
	$('#checklist_plan_no_search').val('');
	$('#supplier_name_search').val('');
	$('#plan_date_textbox').val('');
	$('#final_audit_status').val('');
	
	$('#table_final_auditresult').DataTable().columns(1).search('');
	$('#table_final_auditresult').DataTable().columns(2).search('');
	$('#table_final_auditresult').DataTable().columns(3).search('');
	$('#table_final_auditresult').DataTable().columns(4).search('');
	$('#table_final_auditresult').DataTable().columns(5).search('');
	$('#table_final_auditresult').DataTable().draw();
}

function loadFinalAuditResult(){
	$('#table_final_auditresult').DataTable({
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": contextPath + "/api/final_audit_result/datatable/final_audit_result_list",
	        "type": 'POST',
	        contentType: "application/json",
	        beforeSend : function(arr, $form, options){
		    	BlockUi.blockPage();
		    },
		    complete : function(){
		    	setTimeout(function(){
		    		BlockUi.unBlockPage();
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
	    	{ "data": null, "width": "30px" },
	    	{ "data": "checklistPlanNo", name : "checklistPlanNo" },
	    	{ "data": "supplierData", 
	    	  "name":"supplierData",
	    	  "render" : function (data, type, row) {
	    		  let dataSupplier = JSON.parse(data);
	    		  return dataSupplier.supplierCompany.trim();
	    	  }
	    	},
	    	{ "data": "planDate", "name":"planDate"},
	    	{ "data": "grade", name:"grade"},
	        { 
	    		"data": "finalAuditResultStatusId",
	    		"render": function (data, type, row) {	 
	    			let numOfEvidenceSolveCar = 0, foundCar = true;
	    			
	    			if(row.finalAuditResultStatusId.finalAuditResultStatusId == "3"){	    				
	    					    					
	    				if(row.carId.carId != "" && row.carId.carId != null){
	    					let url = contextPath+'/api/car_detail/car_detail_list/'+JSON.stringify({carId : {carId : row.carId.carId}});
	    					ajaxProcess.submit(url, 'GET', null, '', (data)=>{
	    						
	    						if(data.result){
	    							let carDetail = (JSON.parse(data.message));
	    							/*carDetail = $.grep(carDetail, function(v){
	    								return v.enable == "Y";
	    							});*/
	    							$(carDetail).each(function(i,v){
	    								if(v.completed == "" && v.enable == 'Y'){
	    									/*let urlSearchEvidence = contextPath+'/api/evidence/evidence_solve_car/'+JSON.stringify({
			    								auditResultId : {
			    									checklistPlanId : {checklistPlanId : row.checklistPlanId.checklistPlanId},
			    									evalPlanId : {evalPlanId : v.auditResultId.evalPlanId.evalPlanId}
			    								}
			    							});*/
	    									let urlSearchEvidence = contextPath+'/api/evidence/evidence_solve_car/';
	    									let evidenceRequest = {
				    								auditResultId : {
				    									checklistPlanId : {checklistPlanId : row.checklistPlanId.checklistPlanId},
				    									evalPlanId : {evalPlanId : v.auditResultId.evalPlanId.evalPlanId}
				    								}
				    							};
	    									ajaxProcess.submit(urlSearchEvidence, 'POST', evidenceRequest, '', (data)=>{
	    										if(data.result){
	    											let evidenceArr = JSON.parse(data.message);
	    											let evidenceSolveCar = $.grep(evidenceArr, function(e){
	    						    					return e.actionType == "S";
	    						    				});
	    											numOfEvidenceSolveCar += evidenceSolveCar.length;
	    										}
		    									
		    								},{async : false});
	    								}
	    								
	    							});
	    							
	    							if(carDetail != null && carDetail != ""){
	    								carDetail = $.grep(carDetail, function(v){
		    								return v.enable == "Y";
		    							});
	    								if(carDetail.length == 0){
		    								foundCar = false;
		    							}
	    							}else{
	    								foundCar = false;
	    							}	    							
	    							/*if(carDetail.length == 0){
	    								foundCar = false;
	    							}*/	    							
	    						}
	    						
	    					},{async : false});
	    				}
		    				
		    				  
	    			}
	    			  		
	    			let dpStatus = '';
	    			if(numOfEvidenceSolveCar > 0){
	    				if(userGroupId == "3" || userGroupId == "4"){
	    					dpStatus = '<span class="label" style="background-color:'+data.finalAuditResultStatusColor+';font-size:12px;"> '+data.finalAuditResultStatusName+' </span>';
	    				}else{
	    					dpStatus = '<span class="label" style="background-color:'+data.finalAuditResultStatusColor+';font-size:12px;"> '+data.finalAuditResultStatusName+' </span>&nbsp;&nbsp;<i data-toggle="tooltip" title="มีหลักฐานรอการตรวจสอบ" class="fa fa-flag text-warning fa-1x"></i>';	    						    					
	    				}
	    				
	    			}else{
	    				if(!foundCar){
	    					if(userGroupId == "2"){
	    						dpStatus = '<span class="label" style="background-color:'+data.finalAuditResultStatusColor+';font-size:12px;"> '+data.finalAuditResultStatusName+' </span>&nbsp;&nbsp;<i data-toggle="tooltip" title="รอการอนุมัติ" class="fa fa-flag text-warning fa-1x"></i>';
	    					}else{
	    						dpStatus = '<span class="label" style="background-color:'+data.finalAuditResultStatusColor+';font-size:12px;"> '+data.finalAuditResultStatusName+' </span>';
	    					}	    					
	    				}else{
	    					dpStatus = '<span class="label" style="background-color:'+data.finalAuditResultStatusColor+';font-size:12px;"> '+data.finalAuditResultStatusName+' </span>';
	    				}
	    			}
	            		            	
	                return dpStatus;
	            },
	            name: "finalAuditResultStatusId"
	        }
	    ],
	    "columnDefs": [
	    	{
	            "searchable": false,
	            "orderable": false,
	            "targets": 0,
	            "render": function (data, type, full, meta) {
	                return meta.settings._iDisplayStart + meta.row + 1;
	            }
	        },       
	        {
	            "searchable": false,
	            "orderable": false,
	            "targets": [0,6]
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [1,2,3,4,5]
	        },
	        {
	            "targets": 6,
	            "orderable": false,
	            "width" : "100px",
	            "render": function (data, type, row) {
	            	
	            	
	            	var btnAction = '<div class="btn-group">';
	            	btnAction += '<button class="btn btn-xs blue dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false" style="font-size:12px;"> เลือก <i class="fa fa-angle-down"></i></button>';
	    			btnAction += '<ul class="dropdown-menu pull-left" role="menu">';
	    			btnAction += 	'<li><a href="javascript:void(0);" class = "edit_final_auditresult">';
	    			btnAction +=   		'<i class="icon-magnifier"></i> ดูรายละเอียด </a>';
	    			btnAction +=	'</li>';	
	                btnAction += '</ul>';
	                btnAction +='</div>';
	                
	               
	                return btnAction;
	            }
	        }
	    ],
	    "order": [],
	    "initComplete": function (settings, json) {
	    	$('.dataTables_filter').remove();  
	    	$('[data-toggle="tooltip"]').each(function(i,v){
				$(this).tooltip();
			});
	    	$('#table_final_auditresult').DataTable().on( 'draw', function () {
	    		$('[data-toggle="tooltip"]').each(function(i,v){
					$(this).tooltip();
				});
	    	});
	    }
	});
	
	
}



