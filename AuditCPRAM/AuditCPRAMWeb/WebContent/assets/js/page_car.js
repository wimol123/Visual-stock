var userGroupId = null;
$(function(){
	chSession()
	let url = contextPath+'/api/user/get_session_user';
	ajaxProcess.submit(url, 'GET', null, '', (data)=>{
		let objData = JSON.parse(data.message);
		userGroupId = objData.userGroupId.userGroupId;
	},{async:false});
	
	
	loadCarTable();
	loadStatusCar();
	
	$('.input-group.datepicker-form').datepicker({
		language: "th",
		format: 'dd/mm/yyyy',
		orientation: "bottom auto",
		autoclose: true,
		clearBtn : true
	});
	
});
function chSession() {
	let url = contextPath +"/api/user/get_permission_menu";
	$.ajax({
		url : url,
		type : 'POST',
		contentType: "application/json",
		data : "car.jsp",
		success : function(data){
			if(!data.result){
				window.location = "/auditsupplier/home.jsp";
			}
		}
	});
}

keyUp.enter('#car_no_search', ()=>{
	search();
});

keyUp.enter('#checklist_plan_title_search', ()=>{
	search();
});

keyUp.enter('#due_date_textbox', ()=>{
	search();
});

keyUp.enter('#plan_date_textbox', ()=>{
	search();
});

keyUp.enter('#supplier_company_search', ()=>{
	search();
});
keyUp.enter('#checklist_plan_no_search', ()=>{
	search();
});

$('#btn_search').on('click',function(){
	search();
});

$('#btn_clear').on('click',function(){
	clearSearch();
});

$(document).on('click', '.editCar', function(){
	let data = $('#table_car').DataTable().row( $(this).parents('tr') ).data();
	$('#pass_param_car').val(URI.encode(JSON.stringify(data)));
	$('#pass_param_car').closest('form').submit();
});

function search(){
	$('#table_car').DataTable().columns(1).search($('#plan_date_textbox').val().trim());
	$('#table_car').DataTable().columns(2).search($('#car_no_search').val().trim());
	$('#table_car').DataTable().columns(3).search($('#checklist_plan_no_search').val().trim());
	$('#table_car').DataTable().columns(4).search($('#checklist_plan_title_search').val().trim());
	$('#table_car').DataTable().columns(5).search($('#supplier_company_search').val().trim());	
	$('#table_car').DataTable().columns(6).search($('#due_date_textbox').val().trim());
	$('#table_car').DataTable().columns(7).search($('#car_status').val().trim());
	$('#table_car').DataTable().draw();
}

function clearSearch(){
	$('#plan_date_textbox').val('');
	$('#car_no_search').val('');
	$('#checklist_plan_title_search').val('');
	$('#due_date_textbox').val('');
	$('#car_status').val('');
	$('#supplier_company_search').val('');
	$('#checklist_plan_no_search').val('');
	$('#table_car').DataTable().columns(1).search('');
	$('#table_car').DataTable().columns(2).search('');
	$('#table_car').DataTable().columns(3).search('');
	$('#table_car').DataTable().columns(4).search('');
	$('#table_car').DataTable().columns(5).search('');
	$('#table_car').DataTable().columns(6).search('');
	$('#table_car').DataTable().columns(7).search('');
	$('#table_car').DataTable().draw();
}

function loadStatusCar(){
	ajaxProcess.submit(contextPath + "/api/status/get_status_list/StatusOfCar", 'GET', null, '', (data)=>{
		if(data.result && data.message != null && data.message != ""){
			let listStatus = JSON.parse(data.message.trim());
			$(listStatus).each(function(i,v){
				$('#car_status').append('<option value="'+v.statusId+'"> '+v.statusName+'</option>')
			});
		}
	});
}

function loadCarTable(){
	let dataTableCar = $('#table_car').DataTable({
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": contextPath + "/api/car/datatable/car_list",
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
	    	{ "data": null, "width": "5px" },
	    	{ "data": "checklistPlanId.planDate", name : "planDate" , "width": "5px"},
	    	{ "data": "carNo", name : "carNo" , "width": "5px"},
	    	{ "data": "checklistPlanId.checklistPlanNo", name:"checklistPlanNo", "width": "5px"},
	    	{ "data": "checklistPlanId.checklistTitle", name:"checklistTitle", "width": "5px"},
	    	{ "data": "checklistPlanId.supplierCompany", name:"supplierCompany", "width": "5px"},
	    	{ "data": "dueDate", name : "dueDate", "width": "5px"},
	        { 
	    		"data": "carStatusId",
	    		"render": function (data, type, row) {
	    			let evidenceSolveCar = 0
	    			if(data.carStatusId == "1"){
	    				
	    				let url = contextPath+'/api/car_detail/car_detail_list/'+JSON.stringify({carId : {carId : row.carId}});
    					ajaxProcess.submit(url, 'GET', null, '', (data)=>{
    						if(data.result){
    							let carDetail = (JSON.parse(data.message));
    							
    							$(carDetail).each(function(i,v){
    								if(v.completed == "" && v.enable == 'Y'){
    									/*let urlSearchEvidence = contextPath+'/api/evidence/evidence_solve_car/'+JSON.stringify({
		    								auditResultId : {
		    									checklistPlanId : {checklistPlanId : row.checklistPlanId.checklistPlanId},
		    									evalPlanId : {evalPlanId : v.auditResultId.evalPlanId.evalPlanId}
		    								}
		    							});*/
    									let urlSearchEvidence = contextPath+'/api/evidence/evidence_solve_car/';
    									let objRequestEvidence = {
    		    								auditResultId : {
    		    									checklistPlanId : {checklistPlanId : row.checklistPlanId.checklistPlanId},
    		    									evalPlanId : {evalPlanId : v.auditResultId.evalPlanId.evalPlanId}
    		    								}
    		    							};
    									ajaxProcess.submit(urlSearchEvidence, 'POST', objRequestEvidence, '', (data)=>{
    										if(data.result){
    											
    											let evidenceArr = JSON.parse(data.message);
    											
    											let evidenceSolveCartmp = $.grep(evidenceArr, function(e){
    						    					return e.actionType == "S";
    						    				});
    											evidenceSolveCar += evidenceSolveCartmp.length;
    										}
	    									
	    								},{async : false});
    								}
    								
    							});
    							
    						}
    						
    					},{async : false});
    					
    					
	    			}		
	    			
	    			let dpStatus = '';
	    			if(evidenceSolveCar > 0){
	    				if(userGroupId == "3" || userGroupId == "4"){
	    					dpStatus = '<span class="label" style="background-color:'+data.statusColor+';font-size:12px;"> '+data.carStatusName+' </span>';	
	    				}else{
	    					dpStatus = '<span class="label" style="background-color:'+data.statusColor+';font-size:12px;"> '+data.carStatusName+' </span>&nbsp;<i data-toggle="tooltip" title="มีหลักฐานรอการตรวจสอบ" class="fa fa-flag text-warning fa-1x"></i>';	
	    				}
	    				
	    			}else{
	    				dpStatus = '<span class="label" style="background-color:'+data.statusColor+';font-size:12px;"> '+data.carStatusName+' </span>';	 
	    			}        	
	                return dpStatus;
	            },
	            "width": "70px",
	            name: "carStatusId"
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
	            "targets": 8,
	            "orderable": false,
	            
	            "render": function (data, type, row) {
	            	
	            	
	            	var btnAction = '<div class="btn-group">';
	            	btnAction += '<button class="btn btn-xs blue dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false" style="font-size:12px;"> เลือก <i class="fa fa-angle-down"></i></button>';
	    			btnAction += '<ul class="dropdown-menu pull-right" role="menu">';
	    			btnAction += 	'<li><a href="javascript:void(0);" class = "editCar">';
	    			btnAction +=   		'<i class="icon-magnifier"></i> ดูรายละเอียด </a>';
	    			btnAction +=	'</li>';	    			
	                btnAction += '</ul>';
	                btnAction +='</div>';
	                
	               
	                return btnAction;
	            },"width" : "200px"
	        },	        
	        {
	            "searchable": false,
	            "orderable": false,
	            "targets": [0,8]
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [1,2,3,4,5,6,7]
	        }
	    ],
	    "order": [],
	    "initComplete": function (settings, json) {	    	
	    	$('.dataTables_filter').remove();   
	    	$('[data-toggle="tooltip"]').each(function(i,v){
				$(this).tooltip();
			});
	    	$('#table_car').DataTable().on( 'draw', function () {
	    		$('[data-toggle="tooltip"]').each(function(i,v){
					$(this).tooltip();
				});
	    	});
	    }
	});
}