
var action = getURLParameter("action");
var myId = getURLParameter("myId");
var userSession = null;

if (action==''){
	$("#container_list").show();
	$("#container_form").hide();
	loadDataList();
	
}else if (action=='add'){
	laodGroup();
	$("#container_list").hide();
	$("#container_form").show();
	
}else if (action=='edit'){
	laodGroup();
	$("#container_list").hide();
	$("#container_form").show();
	//loadDataForm();
	
}

$(function(){
	chSession();
	loadSession();
	laodGroup();
	loadSupplier();
	BlockUi.blockPage();

	$.fn.bootstrapSwitch.defaults.size = 'normal';
	$.fn.bootstrapSwitch.defaults.onColor = 'success';
	$(".checkbox_active_status").bootstrapSwitch();
	$('#status_user_dialog').bootstrapSwitch('state',true);
	$('.form-company-name').hide();
	$('.form-description').hide();
	$('.option-require').show();
	validator = $('#user_form').validate({
		focusInvalid : !1,
		errorClass: "help-block help-block-error",
		highlight: function(e) {			
            $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
        },
        unhighlight: function(e) {
            $(e).closest(".form-group").removeClass("has-error")
        },resetForm: function() {
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
        },
        rules: {
        	sel_group_dialog: {
        		required: true
        	},
        	sel_supplier_company_dialog : {
        		required: true
        	},
        	employee_id_dialog : {
        		required: true,
        		maxlength : 20
        	},
        	name_dialog : {
        		required: true,
        		maxlength : 100
        	},
        	userlan_dialog : {
        		required: true,
        		maxlength : 50
        	},
        	email_dialog : {
        		required: true,
        		validateEmail : true,
        		maxlength : 50
        	},
        	telephone_dialog : {
        		required: true,
        		maxlength : 30
        	},
        	description_dialog : {
        		required: true,
        		maxlength : 100
        	}
        },
        messages : {
        	sel_group_dialog: {
        		required: 'กรุณาเลือกกลุ่มผู้ใช้งาน'
        	},
        	sel_supplier_company_dialog : {
        		required: "กรุณาเลือกบริษัท"
        	},
        	employee_id_dialog : {
        		required: 'กรุณาใส่รหัสพนักงาน',
        		maxlength : jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด 20 ตัวอักษร")
        	},
        	name_dialog : {
        		required: 'กรุณาใส่ชื่อ',
        		maxlength : jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร")
        	},
        	userlan_dialog : {
        		required: "กรุณาใส่ชื่อเข้าใช้งาน",
        		maxlength : jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร")
        	},
        	email_dialog : {
        		required: 'กรุณาใส่อีเมล์',
        		maxlength : jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร")
        	},
        	telephone_dialog : {
        		required: "กรุณาใส่เบอร์โทรศัพท์",
        		maxlength : jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร")
        	},
        	description_dialog : {
        		required: "กรุณาใส่รายละเอียด",
        		maxlength : jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร")
        	} 
        }

	});
	
	//validateInnerText.validByElement('#userlan_dialog', null);
	//validateInnerText.validByElement('#telephone_dialog', /[0-9]/g);
	
	
});

$('#userlan_dialog').on("keypress", function(e){
	return validateInnerText.valid(/[A-Za-z0-9 _]/g, e)
});

$('#telephone_dialog').on("keypress", function(e){
	return validateInnerText.valid(/[0-9 ,]/g, e)
});

$('#email_dialog').on("keyup", function(){
	var validateMail = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if (validateMail.test(this.value)) {
		$('#email_dialog').valid()
    }
    else {
    	$('#email_dialog').valid()                               
    }	
});

/*function validateKeypress(validChars, event) {
    var keyChar = String.fromCharCode(event.which || event.keyCode);
    return validChars.test(keyChar) ? keyChar : false;
}*/

function loadSession() {
	let url = contextPath + "/api/user/get_session_user";
	$.ajax({
		url : url,
		type : 'GET',
		contentType: "application/json",
		success : function(data){
			userSession = JSON.parse(data.message);
			if (userSession.userGroupId.userGroupId == '3'
					|| userSession.userGroupId.userGroupId == '4'
					|| userSession.userGroupId.userGroupId == '8') {
				$('.form-company-name').show();
			}
		}
	});
}

function chSession() {
	let url = contextPath +"/api/user/get_permission_menu";
	$.ajax({
		url : url,
		type : 'POST',
		contentType: "application/json",
		data : "users.jsp",
		success : function(data){
			if(!data.result){
				window.location = "/auditsupplier/home.jsp";
			}
		}
	});
}

// ================================== LIST
function loadDataList() {
	var dataFromLoad = null;
	var userTable = $('#table_user').DataTable({
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": contextPath + "/api/user/datatable/get_user_list",
	        "type": 'POST',
	        contentType: "application/json",
	        beforeSend : function(arr, $form, options){
				
				// แก้ไขปัญหา load table user ไม่ขึ้นในบางครั้งทำให้หน้าค้าง
				try {
					BlockUi.blockPage(); 
				} catch (e) { 
					console.log(e);
				}
				
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
	    	{ "data": "userId" },
	    	{ "data": "username" ,name:"username"},
	    	{ "data": "employeeId", name:"employeeId"},
	    	{ "data": "fullname" ,name:"fullname"},
	    	{ "data": "userGroupId.userGroupId", name:"userGroupId"},
	    	{ "data": "userGroupId.userGroupName" ,name:"userGroupName"},
	    	{
	            "data": "enable",
	            "render": function (data, type, row) {
	            	
	            	var dpStatus = '<span class="label label-sm label-success" style="font-size:12px;"> ใช้งาน </span>';
					if (data == 'N' ){
						dpStatus = '<span class="label label-sm label-default" style="font-size:12px;"> ไม่ใช้งาน </span>';
					}
					
	                return dpStatus;
	            },
	            "width": "150px",
	            name: "enable"
	        },/*,
	        { "data": "supplierId"}*/
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
	        	"targets": [1,3,5],
	            "visible": false,
	            "searchable": false
	        },	        
	        {
	            "searchable": false,
	            "orderable": false,
	            "targets": [0,8]
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [2,4,6,7]
	        },
	        {
	            "targets": 8,
	            "orderable": false,
	            "render": function (data, type, row) {
	            	
	            	var btnAction = '<div class="btn-group">';
	            	btnAction += '<button class="btn btn-xs blue dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false" style="font-size:12px;"> เลือก <i class="fa fa-angle-down"></i></button>';
	    			btnAction += '<ul class="dropdown-menu pull-left" role="menu">';
	    			btnAction += 	'<li><a href="javascript:void(0);" class = "editUser">';
	    			btnAction +=   		'<i class="icon-pencil"></i> แก้ไขข้อมูล </a>';
	    			btnAction +=	'</li>';
	    			if(row.userGroupId.userGroupId != '1'){
	    				btnAction +=	'<li><a href="javascript:void(0);" class = "removeUser">';
		    			btnAction +=		'<i class="icon-trash"></i> ลบข้อมูล </a>';
		    			btnAction +=	'</li>';
	    			}	    			
	    			if(row.userGroupId.userGroupId != '1' && row.userGroupId.userGroupId != '2' && row.userGroupId.userGroupId != '5'  && row.userGroupId.userGroupId != '6'){
	    				btnAction +=	'<li><a href="javascript:void(0);" class = "generatePassword">';
		    			btnAction +=		'<i class="icon-key"></i> สร้างรหัสผ่านใหม่ </a>';
		    			btnAction +=	'</li>';
	    			}	    			
	                btnAction += '</ul>';
	                btnAction +='</div>';
	               
	                return btnAction;
	            },
	            "width": "200px" 
	        }
	    ],
	    "order": [[1, 'asc']],
	    "initComplete": function (settings, json) {
	    	
	    	$('.dataTables_filter').remove();	    	
	    	
	    } 
	    
	});
	
	
	
	
}




//================================== Form Option

var mainSupplierData;


function mapSupplierForSelect2Data(supplierData){
	var data = [];
	$.each(supplierData,function(index,value){
		data.push({
			id : value.supplierId,
			text : value.supplierCompany
		});
	});
	return data;
}

function loadSearchGroup(){
	$.ajax({
		url: contextPath + "/api/user_group/user_group_list",
	    type: 'GET',
	    async: true,
	    contentType: 'application/json; charset=UTF-8',
	    success : function(data, msg, jqXHR) {
	    	if(data.message != ""){
	    		let objJson = JSON.parse(data.message);	    
	    		$('#sel_group_search').append(
	    			$('<option value="">--- ทั้งหมด ---</option>')
	    		);
	    		$(objJson).each(function(i,val){
	    			$('#sel_group_search').append(
    	    			$('<option value="'+val.userGroupId+'">'+val.userGroupName+'</option>')
    	    		);
	    		});
	    	}
	    }
	});
}

function laodGroup(){
	$.ajax({
		url: contextPath + "/api/user_group/user_group_list",
	    type: 'GET',
	    async: true,
	    contentType: 'application/json; charset=UTF-8',
	    success : function(data, msg, jqXHR) {
	    	let dataList = [];
	    	if(data.message != ""){
	    		try{
	    			let objJson = JSON.parse(data.message);	  
	    			$('#sel_group_search').empty();
	    			$('#sel_group_search').append(
	    					$('<option value="">--- ทั้งหมด ---</option>')
	    			);
	    	    	$(objJson).each(function(i,val){
	    	    		dataList.push({
	    	    			id : val.userGroupId,
	    	    			text : val.userGroupName
	    	    		});
	    	    		$('#sel_group_search').append(
    	    				$('<option value="'+val.userGroupId+'">'+val.userGroupName+'</option>')
    	    			);
	    	    	});
	    			
	    		}catch (e) {
	    			customMessageDialog("error", "ตรวจสอบข้อมูล", e, null);
				}
	    	}
	    	
	    	 
	    	
	    	$("#sel_group_dialog").select2({
	    		dropdownParent: $('#dialog_add_user'),
	    		placeholder: "เลือกกลุ่มผู้ใช้งาน",
	    		theme: "bootstrap",
	    		data: dataList
	    	});
	    	
	    	  	
		},
		error: function (jqXHR, textStatus, errorThrown) {
			customMessageDialog("error", "เกิดข้อผิดพลาด", errorThrown, null);
	    },
	});
		
}


function loadSupplier(){
	$.ajax({
		url: contextPath + "/api/supplier/supplier_list",
	    type: 'GET',
	    async: true,
	    contentType: 'application/json; charset=UTF-8',
	    success : function(data, msg, jqXHR) {
	    	if(data.result == true){
	    		try{
	    			let objJSON = JSON.parse(data.message);
	    			var data = [];
	    			$.each(objJSON,function(index,value){
	    				data.push({
	    					id : value.supplierId,
	    					text : value.supplierCompany
	    				});
	    			}); 
	    	    	
	    	    	$("#sel_supplier_company_dialog").select2({
	    	    		dropdownParent: $('#dialog_add_user'),
	    	    		placeholder: "เลือกบริษัทซัพพลายเออร์",
	    	    		theme: "bootstrap",
	    	    		data: data
	    	    	});
	    		}catch(e){
	    			customMessageDialog("error", "เกิดข้อผิดพลาด", e, null);
	    		}
	    	}
	    	
	    	  	
		},
		error: function (jqXHR, textStatus, errorThrown) {
			try{
				customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseJSON.message, null);
			}catch(Execption){
				customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
			}
			
	    },
	});
}

$('#dialog_add_user').on('shown.bs.modal', function () {
	if($("#hd_user_id").val() != ''){
		if($('#password_dialog').closest(".form-group").hasClass('hide')){			
			if($('#sel_group_dialog').val() != "2"){
				//$('#password_dialog').closest(".form-group").removeClass('hide');
			}
		}else{
			if($('#sel_group_dialog').val() == "2"){
				//$('#password_dialog').closest(".form-group").addClass('hide');
			}
		}
	}
});

$('#dialog_add_user').on('hidden.bs.modal', function () {
	validator.resetForm();
	$('#dialog_add_user .modal-title').html("เพิ่มผู้ใช้งาน");
	$("#hd_user_id").val('');
	$("#employee_id_dialog").val('');
	$('#employee_id_dialog').prop('readonly', false);
	$("#name_dialog").val('');
	$("#userlan_dialog").val('');
	$('#userlan_dialog').prop('readonly', false);
	$("#password_dialog").val('');
	$("#email_dialog").val('');
	$("#telephone_dialog").val('');
	$('#status_user_dialog').bootstrapSwitch('state',true);
	$('#sel_group_dialog').empty();
	$('.password_require').show();
	$(".form-company-name").hide();
	$(".form-description").hide();
	$(".form-description").val('');
	$('#sel_supplier_company_dialog').empty();	
	$('#password_dialog').closest(".form-group").addClass('hide');	
	$($('#user_form').find('.form-group')).each(function(i,e){		
		if($(this).hasClass('has-error')){
			$(this).removeClass('has-error');
		}
	});
	laodGroup();
	loadSupplier();
	if (userSession.userGroupId.userGroupId == '3'
			|| userSession.userGroupId.userGroupId == '4'
			|| userSession.userGroupId.userGroupId == '8') {
		$('.form-company-name').show();
	}
});


//================================== Form
function loadDataForm(userId) {
	$.ajax({
		url : contextPath + "/api/user/user_deatil_by_id/"+JSON.stringify( {userId : userId} ),
		type : 'GET',
		beforeSend : function(){
			BlockUi.blockPage();
		},
		complete : function(){
			BlockUi.unBlockPage();
		},
		success : function(data, msg, jqXHR) {			
			
			if(data.message != ""){
				try{
					let userObj = JSON.parse(data.message);
					$(userObj).each(function(i,val){
						$('#dialog_add_user .modal-title').html("แก้ไขข้อมูลผู้ใช้งาน");
						$("#hd_user_id").val(val.userId);
						$("#employee_id_dialog").val(val.employeeId);
						$('#employee_id_dialog').prop('readonly', true);
						$("#name_dialog").val(val.fullname);
						$("#userlan_dialog").val(val.username);
						$('#userlan_dialog').prop('readonly', true);
						//$("#password_dialog").val(data.password);
						$("#password_dialog").val('');						
						$("#email_dialog").val(val.email);
						$("#telephone_dialog").val(val.telephone);
						$("#description_dialog").val(val.description);
						if(val.enable=='Y'){
							$('#chk_status').prop('checked', true);
							$('#status_user_dialog').bootstrapSwitch('state',true);
						}else{
							$('#status_user_dialog').bootstrapSwitch('state',false);
						}
						
						if($("#sel_group_dialog").val() != '3'){
							$('.option-require').show();
							
							$('#employee_id_dialog').rules("add", {	
								required : true,
								maxlength : 10,
								messages:{
									required: 'กรุณาใส่รหัสพนักงาน',
					        		maxlength : jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร")
								}
							});
							
						}else{
							$('.option-require').hide();
							$('#employee_id_dialog').rules("remove");
						}
						
				    	$('#sel_group_dialog').val(val.userGroupId.userGroupId);
				    	$('#sel_group_dialog').trigger('change');
				    	if(val.supplierId != null){
				    		$(val.supplierId).each(function(i,v){				    			
				    			if(v.supplierId != "0"){				    				
				    				$('#sel_supplier_company_dialog').val(parseInt(v.supplierId));
								    $('#sel_supplier_company_dialog').trigger('change');
				    			}
				    		});
				    	}
				    	/*if(data.supplier.supplierId != 0){	    		
				    		$('#sel_supplier_company_dialog').val(data.supplier.supplierId);
						    $('#sel_supplier_company_dialog').trigger('change');	    			    		
				    	}*/
				    	$('#dialog_add_user').modal('toggle');
					});
				}catch (e) {
					customMessageDialog("error", "ตรวจสอบข้อมูล", e, null);
				}
				
			}
			
		},
		error : function(jqXHR, textStatus, errorThrown) {	
			try{
				customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseJSON.message, null);
			}catch(Execption){
				customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
			}
			
		},
	});
}
$('#btn_submit_admin').on('click',function(){
	
	if($("#password_dialog").val() != ''){
		$('#password_dialog').rules("add", {
			minlength: 8,
			maxlength : 12,
			pwcheckconsists : true,
			pwchecklowercase : true,
			pwcheckuppercase : true,
			pwcheckdigit : true,
			pwcheckspacialcharactor : true,
			messages:{
				minlength: jQuery.validator.format("กรุณาใส่รหัสผ่านมากกว่า  {0} ตัวอักษร"),
				maxlength: jQuery.validator.format("กรุณาใส่รหัสผ่านไม่เกิน  {0} ตัวอักษร"),
				pwcheckconsists : "กรุณาใส่รหัสผ่านให้ถูกต้องตามเงื่อนไขที่กำหนดไว้",
				pwchecklowercase : "รหัสผ่านจะต้องประกอบด้วยตัวอักษรตัวเล็กอย่างน้อย 1 ตัวอักษร",
				pwcheckuppercase : "รหัสผ่านจะต้องประกอบด้วยตัวอักษรตัวใหญ่อย่างน้อย 1 ตัวอักษร",
				pwcheckdigit : "รหัสผ่านจะต้องประกอบด้วยตัวเลขอย่างน้อย 1 ตัวเลข",
				pwcheckspacialcharactor : "รหัสผ่านจะต้องประกอบด้วยอักขระพิเศษอย่างน้อย 1 ตัวอักษร"
			}
		});
	}else{
		$('#password_dialog').rules("remove");
	}
	
	if($('#user_form').valid()){
		
		let userId = $("#hd_user_id").val().trim();
		let userEmployeeId = $("#employee_id_dialog").val().trim();
		let userFullname = $("#name_dialog").val().trim();
		let username = $("#userlan_dialog").val().trim();
		let password = $("#password_dialog").val().trim();
		let userEmail = $("#email_dialog").val().trim();
		let userTelephone = $("#telephone_dialog").val().trim();
		let enable = 'N';
		let userGroupId = $("#sel_group_dialog").val().trim();	
		let supplierId = null;
		if($("#sel_supplier_company_dialog").val() != null &&$("#sel_supplier_company_dialog").val() != ''){
			supplierId = $("#sel_supplier_company_dialog").val().trim();
		}else{
			supplierId = $("#sel_supplier_company_dialog").val();
		}
		
		let description = $('#description_dialog').val().trim();
		
		if($("#status_user_dialog").prop('checked'))
			enable = 'Y';
		
		let objUser = {
			userId : userId,
			employeeId : userEmployeeId,
			userGroupId : {userGroupId : userGroupId},
			username : username,
			password : password,
			fullname : userFullname,
			email : userEmail,
			telephone : userTelephone,
			enable : enable,
			description : description,
			supplierId : [{
				supplierId : supplierId
			}]
		};
				if(objUser.userId == ""){
					//=============== INSERT ======================
					//$('#dialog_add_user').modal('toggle');
					/*ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการบันทึกข้อมูลนี้ใช่หรือไม่?");	
					ConfirmModal.confirmResult(function(isTrue){
						if(isTrue){
							ajaxSubmitUser(contextPath + "/api/user/insert_user",objUser);
						}else{
							$("#hd_user_id").val(objUser.userId);
							$("#employee_id_dialog").val(objUser.employeeId);
							$("#name_dialog").val(objUser.fullname);
							$("#userlan_dialog").val(objUser.username);
							$("#password_dialog").val(objUser.password);
							$("#email_dialog").val(objUser.email);
							$("#telephone_dialog").val(objUser.telephone);
							$("#description_dialog").val(objUser.description);
							$('#status_user_dialog').bootstrapSwitch('state',true);
							if(objUser.enable == 'N')
								$('#status_user_dialog').bootstrapSwitch('state',false);
							$('#sel_group_dialog').val(objUser.userGroupId.userGroupId);
					    	$('#sel_group_dialog').trigger('change');
					    	$(objUser.supplierId).each(function(i,v){
					    		$('#sel_supplier_company_dialog').val(v.supplierId);
						    	$('#sel_supplier_company_dialog').trigger('change');
					    	});
					    	
							$('#dialog_add_user').modal('toggle');
						}
					});*/
					
					let url = contextPath + "/api/user/insert_user";
					ajaxProcess.submit(url, 'POST', objUser, '#dialog_add_user', (data)=>{
						if(data.result){
							$('#table_user').DataTable().draw();
							$('#dialog_add_user').modal('toggle');
						}
					});
					
				}else{
					//=============== UPDATE ======================			
					//$('#dialog_add_user').modal('toggle');
					/*ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการแก้ไขข้อมูลนี้ใช่หรือไม่?");	
					ConfirmModal.confirmResult(function(isTrue){
						if(isTrue){
							ajaxSubmitUser(contextPath + "/api/user/update_user",objUser);
						}else{
							loadDataForm(objUser.userId);
							//$('#dialog_add_user').modal('toggle');
						}
					});*/
					
					let url = contextPath + "/api/user/update_user";
					ajaxProcess.submit(url, 'POST', objUser, '#dialog_add_user', (data)=>{
						if(data.result){
							$('#table_user').DataTable().draw();
							$('#dialog_add_user').modal('toggle');
						}
					});
					
				}

		
		
	}
});
$('#btn_submit_user').on('click',function(){
	
	if($("#password_dialog").val() != ''){
		$('#password_dialog').rules("add", {
			minlength: 8,
			maxlength : 12,
			pwcheckconsists : true,
			pwchecklowercase : true,
			pwcheckuppercase : true,
			pwcheckdigit : true,
			pwcheckspacialcharactor : true,
			messages:{
				minlength: jQuery.validator.format("กรุณาใส่รหัสผ่านมากกว่า  {0} ตัวอักษร"),
				maxlength: jQuery.validator.format("กรุณาใส่รหัสผ่านไม่เกิน  {0} ตัวอักษร"),
				pwcheckconsists : "กรุณาใส่รหัสผ่านให้ถูกต้องตามเงื่อนไขที่กำหนดไว้",
				pwchecklowercase : "รหัสผ่านจะต้องประกอบด้วยตัวอักษรตัวเล็กอย่างน้อย 1 ตัวอักษร",
				pwcheckuppercase : "รหัสผ่านจะต้องประกอบด้วยตัวอักษรตัวใหญ่อย่างน้อย 1 ตัวอักษร",
				pwcheckdigit : "รหัสผ่านจะต้องประกอบด้วยตัวเลขอย่างน้อย 1 ตัวเลข",
				pwcheckspacialcharactor : "รหัสผ่านจะต้องประกอบด้วยอักขระพิเศษอย่างน้อย 1 ตัวอักษร"
			}
		});
	}else{
		$('#password_dialog').rules("remove");
	}
	
	if($('#user_form').valid()){
		
		let userId = $("#hd_user_id").val().trim();
		let userEmployeeId = $("#employee_id_dialog").val().trim();
		let userFullname = $("#name_dialog").val().trim();
		let username = $("#userlan_dialog").val().trim();
		let password = $("#password_dialog").val().trim();
		let userEmail = $("#email_dialog").val().trim();
		let userTelephone = $("#telephone_dialog").val().trim();
		let enable = 'N';
		let userGroupId = $("#sel_group_dialog").val().trim();	
		let supplierId = null;
		if($("#sel_supplier_company_dialog").val() != null &&$("#sel_supplier_company_dialog").val() != ''){
			supplierId = $("#sel_supplier_company_dialog").val().trim();
		}else{
			supplierId = $("#sel_supplier_company_dialog").val();
		}
		
		let description = $('#description_dialog').val().trim();
		
		if($("#status_user_dialog").prop('checked'))
			enable = 'Y';
		
		let objUser = {
			userId : userId,
			employeeId : userEmployeeId,
			userGroupId : {userGroupId : userGroupId},
			username : username,
			password : password,
			fullname : userFullname,
			email : userEmail,
			telephone : userTelephone,
			enable : enable,
			description : description,
			supplierId : [{
				supplierId : supplierId
			}]
		};
		ConfirmModalPDPA.setupModal("warning","ยืนยัน", "ข้อมูลส่วนบุคคลที่ท่านได้นำมาเพิ่มในระบบ ได้รับการยินยอมจากบุคคลดังกล่าวแล้วให้ท่านนำข้อมูลมาใช้");	
		ConfirmModalPDPA.confirmResult(function(isTrue){
			if (isTrue){
				if(objUser.userId == ""){
					//=============== INSERT ======================
					//$('#dialog_add_user').modal('toggle');
					/*ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการบันทึกข้อมูลนี้ใช่หรือไม่?");	
					ConfirmModal.confirmResult(function(isTrue){
						if(isTrue){
							ajaxSubmitUser(contextPath + "/api/user/insert_user",objUser);
						}else{
							$("#hd_user_id").val(objUser.userId);
							$("#employee_id_dialog").val(objUser.employeeId);
							$("#name_dialog").val(objUser.fullname);
							$("#userlan_dialog").val(objUser.username);
							$("#password_dialog").val(objUser.password);
							$("#email_dialog").val(objUser.email);
							$("#telephone_dialog").val(objUser.telephone);
							$("#description_dialog").val(objUser.description);
							$('#status_user_dialog').bootstrapSwitch('state',true);
							if(objUser.enable == 'N')
								$('#status_user_dialog').bootstrapSwitch('state',false);
							$('#sel_group_dialog').val(objUser.userGroupId.userGroupId);
					    	$('#sel_group_dialog').trigger('change');
					    	$(objUser.supplierId).each(function(i,v){
					    		$('#sel_supplier_company_dialog').val(v.supplierId);
						    	$('#sel_supplier_company_dialog').trigger('change');
					    	});
					    	
							$('#dialog_add_user').modal('toggle');
						}
					});*/
					
					let url = contextPath + "/api/user/insert_user";
					ajaxProcess.submit(url, 'POST', objUser, '#dialog_add_user', (data)=>{
						if(data.result){
							$('#table_user').DataTable().draw();
							$('#dialog_add_user').modal('toggle');
						}
					});
					
				}else{
					//=============== UPDATE ======================			
					//$('#dialog_add_user').modal('toggle');
					/*ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการแก้ไขข้อมูลนี้ใช่หรือไม่?");	
					ConfirmModal.confirmResult(function(isTrue){
						if(isTrue){
							ajaxSubmitUser(contextPath + "/api/user/update_user",objUser);
						}else{
							loadDataForm(objUser.userId);
							//$('#dialog_add_user').modal('toggle');
						}
					});*/
					
					let url = contextPath + "/api/user/update_user";
					ajaxProcess.submit(url, 'POST', objUser, '#dialog_add_user', (data)=>{
						if(data.result){
							$('#table_user').DataTable().draw();
							$('#dialog_add_user').modal('toggle');
						}
					});
					
				}
			}
		});
		
		
	}
});


function ajaxSubmitUser(path,objUser){
	$.ajax({
		url : path,
		type : 'POST',
		data : JSON.stringify(objUser),
		contentType : "application/json; charset=utf-8",
		beforeSend : function(arr, $form, options){
        	BlockUi.blockPage();
	    },
	    complete : function(){
	    	BlockUi.unBlockPage();
	    },
		success : function(data) {			
			if(data.result){
				customMessageDialog("success", "สถานะการดำเนินการ", data.message, null);
				//$('#dialog_add_user').modal('hide');
	    		$('#table_user').DataTable().draw();
			}else{
				customMessageDialog("error", "สถานะการดำเนินการ", data.message, null);
				
			}
		  },
		  error: function (jqXHR, textStatus, errorThrown) {
			  try{
				  customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseJSON.message, null);
			  }catch (e) {
				  customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
			  }
			  
		  },
	});
}



$(document).on('click','.editUser',function(){
	var data = $('#table_user').DataTable().row( $(this).parents('tr') ).data();
	var userId = data.userId.trim();
	$('.password_require').hide();
	loadDataForm(userId);
});





$(document).on('click','.removeUser',function(){
	var data = $('#table_user').DataTable().row( $(this).parents('tr') ).data();
	var userId = data.userId.trim();
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการลบข้อมูลนี้ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){
			$.ajax({
				url : contextPath + "/api/user/user_delete",
				type : 'DELETE',
				data : JSON.stringify({userId : userId}),
				contentType : "application/json; charset=utf-8",
				beforeSend : function(arr, $form, options){
					BlockUi.blockPage();
			    },
			    complete : function(){
			    	BlockUi.unBlockPage();
			    },
				success : function(data) {
					if(data.result){
						customMessageDialog("success", "สถานะการดำเนินการ", data.message, null);
						$('#table_user').DataTable().draw();
					}else{
						customMessageDialog("error", "สถานะการดำเนินการ", data.message, null);						
					}		    	 
			    	  
				},
				error: function (jqXHR, textStatus, errorThrown) {
					try{
						customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseJSON.message, null);
					}catch (e) {
						customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
					}
				},
			});
		}
	});			

});


$(document).on('click','.generatePassword',function(){
	let data = $('#table_user').DataTable().row( $(this).parents('tr') ).data();
	let userId = data.userId.trim();
	let email = data.email.trim();
	let userName = data.username.trim();
	
	if(data.userGroupId.userGroupId != '2'){
		
		ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการสร้างรหัสผ่านใหม่ให้ผู้ใช้งานนี้ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){
				let url = contextPath + "/api/user/user_change_password";
				let userObj = {
					//userId : userId,
					email : email,
					username : userName
				};
				
				ajaxProcess.submit(url, 'POST', userObj, null, null);
				/*$.ajax({
					url : contextPath + "/api/user/user_change_password",
					type : "POST",
					data : JSON.stringify({userId : userId,email : email,username : userName}),
					contentType : "application/json; charset=utf-8",
					beforeSend : function(arr, $form, options){
						BlockUi.blockPage();
				    },
				    complete : function(){
				    	BlockUi.unBlockPage();
				    },
				    success : function(data) {
						if(data.result){
							customMessageDialog("success", "สถานะการดำเนินการ", data.message, null);
							//$('#table_user').DataTable().draw();
						}else{
							customMessageDialog("error", "สถานะการดำเนินการ", data.message, null);						
						}		    	 
				    	  
					},
					error: function (jqXHR, textStatus, errorThrown) {
						try{
							customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseJSON.message, null);
						}catch (e) {
							customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseText, null);
						}
					}
				});*/
			}
			
		});
		
	}
	
	
});



$('#btn_search').on('click',function(){
	
	var userName = $('#username_textbox').val().trim();
	var fullName = $('#fullname_textbox').val().trim();
	var userGroup = $('#sel_group_search').val().trim();
	var status = $('#select_status').val().trim();
	
	$('#table_user').DataTable().columns(2).search(userName);
	$('#table_user').DataTable().columns(4).search(fullName);
	$('#table_user').DataTable().columns(5).search(userGroup);
	$('#table_user').DataTable().columns(7).search(status);
	$('#table_user').DataTable().draw();
});


$('#btn_clear').on('click',function(){
	$("#select_status").val($("#select_status option:first").val());
	$('#username_textbox').val('');
	$('#fullname_textbox').val('');
	$("#sel_group_search").val($("#sel_group_search option:first").val());
	$('#table_user').DataTable().columns(2).search('');
	$('#table_user').DataTable().columns(4).search('');
	$('#table_user').DataTable().columns(5).search('');
	$('#table_user').DataTable().columns(7).search('');
	$('#table_user').DataTable().draw();
	
});


$('#sel_group_dialog').on('change',function(){
	if (this.value == '3' || this.value == '4' || this.value == '8') {
		$("#hint-username").slideUp();
		$('.option-require').hide();
		$(".form-company-name").slideDown("slow");
		$('#employee_id_dialog').rules("remove");
		if (this.value == '4' || this.value == '8') {
			$(".form-description").slideDown("slow");
		} else {
			$(".form-description").slideUp();
			$("#description_dialog").val('');
		}
	} else {
		$('.option-require').show();
		$(".form-company-name").slideUp();
		$(".form-description").slideUp();
		$("#hint-username").slideUp();
		$('#employee_id_dialog').rules("add", {	
			required : true,
			maxlength : 10,
			messages:{
				required: 'กรุณาใส่รหัสพนักงาน',
        		maxlength : jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร")
			}
		});
	}
	
	if(this.value == '2' || this.value == '5' || this.value == '6' || this.value == '7'){
		$('#hint-username').slideDown("slow");
	}
	
});


$("#username_textbox").keyup(function(event) {
    if (event.keyCode === 13) {
        $("#btn_search").click();
    }
});
$("#fullname_textbox").keyup(function(event) {
    if (event.keyCode === 13) {
        $("#btn_search").click();
    }
});
$("#usergroup_textbox").keyup(function(event) {
    if (event.keyCode === 13) {
        $("#btn_search").click();
    }
});
/*$( "#select_status" ).change(function() {
	$("#btn_search").click();
});*/


