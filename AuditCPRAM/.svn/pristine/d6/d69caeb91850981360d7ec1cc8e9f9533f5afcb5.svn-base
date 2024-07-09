var filesArray = [];
var uniqueIdLogo = null;
var locationRawList = [];
var filesNameNew = "";
var validateForm = null;
var expireDefault = null;
var userObj = null;
$(function(){
	chSession();
	
	loadSession();
	BlockUi.blockPage();
	uniqueIdLogo = generate.randomByDate();
	
	//$('#status_greenCard').bootstrapSwitch('state',true);
	//$('#status_supplier').bootstrapSwitch('state',true);
	let objectValidate = {
		element : '#supplier_form',
		require : ['supplier_company_modal','contact_name_modal','address_modal','sub_district_modal','district_modal','province_modal','postcode_modal','userlan_dialog','password_dialog','supplier_telephone_modal', 'auditRound'],
		message : ["กรุณาใส่ชื่อบริษัท","กรุณาใส่ชื่อผู้ติดต่อ","กรุณาใส่ที่อยู่","กรุณาใส่ตำบล/แขวง","กรุณาใส่อำเภอ/เขต","กรุณาใส่จังหวัด","กรุณาใส่รหัสไปรษณีย์","กรุณาใส่ชื่อเข้าใช้งาน","กรุณาใส่รหัสผ่าน","กรุณาใส่เบอร์โทรศัพท์", "กรุณาใส่รอบเข้าตรวจ"],
		maxLength : [100, 100, 200, 200, 200, 200, null, 50, null, 30, 11]
	}
	validateForm = validate.init(objectValidate);
	
	objectValidate = {
		element : '#password_dialog',
		rules : [{minlength: 8},{maxlength : 12},{pwcheckconsists : true},{pwchecklowercase : true},{pwcheckuppercase : true},{pwcheckdigit : true},{pwcheckspacialcharactor : true}],
		message : [jQuery.validator.format("กรุณาใส่รหัสผ่านมากกว่า  {0} ตัวอักษร"),jQuery.validator.format("กรุณาใส่รหัสผ่านไม่เกิน  {0} ตัวอักษร"),"กรุณาใส่รหัสผ่านให้ถูกต้องตามเงื่อนไขที่กำหนดไว้","รหัสผ่านจะต้องประกอบด้วยตัวอักษรตัวเล็กอย่างน้อย 1 ตัวอักษร","รหัสผ่านจะต้องประกอบด้วยตัวอักษรตัวใหญ่อย่างน้อย 1 ตัวอักษร","รหัสผ่านจะต้องประกอบด้วยตัวเลขอย่างน้อย 1 ตัวเลข","รหัสผ่านจะต้องประกอบด้วยอักขระพิเศษอย่างน้อย 1 ตัวอักษร"]
	}
	validate.add(objectValidate);
	
	objectValidate = {
		element : '#postcode_modal',
		rules : [{number : true},{minlength: 5},{maxlength : 5}],
		message : ["กรุณาใส่เป็นตัวเลขเท่านั้น", jQuery.validator.format("กรุณาใส่รหัสไปรษณีย์ให้ครบ  {0} หลัก"), jQuery.validator.format("กรุณาใส่รหัสไปรษณีย์ไม่เกิน  {0} หลัก")]
	}
	validate.add(objectValidate);
	
	objectValidate = {
		element : '#supplier_Code_modal',
		rules : [{maxlength : 50}],
		message : [null]
	}
	validate.add(objectValidate);
	
	/*$('#password_dialog').rules("add", {
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
	});*/
	/*$( "#postcode_modal" ).rules( "add", {
		number : true,		
		minlength: 5,
		maxlength : 5,
		messages: {
			number: "กรุณาใส่เป็นตัวเลขเท่านั้น",
		    minlength: jQuery.validator.format("กรุณาใส่รหัสไปรษณีย์ให้ครบ  {0} หลัก"),
		    maxlength: jQuery.validator.format("กรุณาใส่รหัสไปรษณีย์ให้ครบ  {0} หลัก")
		}
	});*/
	$( "#supplier_email_modal" ).rules( "add", {
		required : true,
		validateEmail : true,
		messages: {
			required : "กรุณากรอกอีเมล์"
		}
	});
	$('#supplier_company').on("keypress", function(e){
		return validateInnerText.valid(/^[ก-๏ A-Za-z \s]+$/g, e)
	});
	
	$('#auditRound').rules("add", {
		number: true,
		messages: {
			number : "กรุณาใส่ข้อมูลเป็นตัวเลขเท่านั้น"
		}
	});
	
	/*$("#logo").rules("add",{
		validatelogo : true
	});*/
    
	
	$("#logo").fileinput({		
		previewFileType: "image",
		language: "th",
		overwriteInitial: true,
		initialPreviewAsData: true, // identify if you are sending preview data only and not the markup
		//maxFileSize: 300,
		maxFileCount: 1,		
		showClose: false,
	    showCaption: false,
	    showBrowse: false,
		showUpload: true, // hide upload button
	    showRemove: false, // hide remove button
	    browseOnZoneClick: true,
//	    removeLabel: "ยกเลิกการเลือกรูปภาพนี้",
	    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
//	    removeTitle: "ยกเลิกการเลือกรูปภาพนี้",
	    elErrorContainer: '#error-choose-file',
	    msgErrorClass: 'alert alert-block alert-danger',
	    defaultPreviewContent: '<img src="'+contextPath+'/assets/images/picture-attachment.png" alt="Your Logo"><h6 class="text-muted">คลิกเพื่อเลือกรูปภาพ</h6>',
	    layoutTemplates: {main2: '{preview} {remove} {browse}'},
	    allowedFileExtensions: ["jpg", "jpeg", "png"],
	    uploadUrl: contextPath+'/api/supplier/upload_logo',
        uploadAsync: false,
        fileActionSettings: {
        	showUpload: true,
        }
	})
	.on('filebatchpreupload', function(event, data) {
		uniqueIdLogo = generate.randomByDate();
		let filesName = data.files[0].name;
		let filesType = data.files[0].type.split('/')[1];
		if(filesType == "jpeg" || filesType == "jpg"){
			filesName = filesName.split('.'+filesType)[0];
			filesName = filesName.split('.jpg')[0];
		}
		filesNameNew = (filesName.split('.'+filesType)[0]).concat('_').concat(uniqueIdLogo).concat('.').concat(filesType);
		data.form.append('logo_supplier', data.files[0],filesNameNew);
	})
	.on('filebatchselected', function(event, files) {
		
		if(files.length > 0){				
			$("#logo").fileinput("upload");
			$('#input-id').fileinput('addToStack', files); 
		}
			
	}).on('filesuccessremove', function(event, id) {
		$.ajax({
			url : contextPath+"/api/supplier/delete_logo",
			type : "DELETE",
			data : filesNameNew,
			contentType: "application/json",
	        beforeSend : function(arr, $form, options){
	        	BlockUi.blockPosition("#dialog_add_supplier");
		    },
		    complete : function(){
		    	setTimeout(function(){
		    		BlockUi.unBlockPosition("#dialog_add_supplier");
		    	},500);		    	
		    }
		});
		filesArray = [];
	});
	
	/*$.validator.addMethod("validatelogo", function() {		
		if(filesArray.length <= 0){
			return false;
		}
		return true;
	  }, 
	  	"กรุณาเลือกรูปภาพโลโก้บริษัท");*/
	
	//loadLocation.init();
	loadLocation.processFunction(function(data){
		locationRawList = data;
		loadLocation.callBack(data);
	});
	
	
	generateDatePicker();
	
	
	
	/*validateInnerText.validByElement('#userlan_dialog', null);
	validateInnerText.validByElement('#auditRound', /[0-9]/g);*/
	//
	
});

$('#userlan_dialog').on("keypress", function(e){
	return validateInnerText.valid(/[A-Za-z0-9 _]/g, e)
});

$('#auditRound').on("keypress", function(e){
	return validateInnerText.valid(/[0-9]/g, e)
});

$('#postcode_modal').on("keypress", function(e){
	return validateInnerText.valid(/[0-9]/g, e)
});

$('#supplier_telephone_modal').on("keypress", function(e){
	return validateInnerText.valid(/[0-9 ,]/g, e)
});

$('#supplier_email_modal').on("keyup", function(){
	var validateMail = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if (validateMail.test(this.value)) {
		$('#supplier_email_modal').valid()
    }
    else {
    	$('#supplier_email_modal').valid()                               
    }	
});

$('#status_greenCard').on('switchChange.bootstrapSwitch', function (e, state) {
	if(state){
		$(".expire-greencard").slideDown("slow");
	}else{
		$(".expire-greencard").slideUp("slow");
	}
}); 

$( document ).on(
	    'DOMMouseScroll mousewheel scroll',
	    '#dialog_add_supplier', 
	    function(){     
	    	var t;
	        window.clearTimeout( t );
	        var t = window.setTimeout( function(){            
	            $('.input-group.datepicker-dialog').datepicker('place')
	        },100 );        
	    }
	);

function loadSession(){
	let url = contextPath +"/api/user/get_session_user";
	
	$.ajax({
		url : url,
		type : 'GET',
		contentType: "application/json",
		success : function(data){
			userObj = JSON.parse(data.message);						
			$('a[href="#dialog_add_supplier"]').removeClass('hide')
			loadSupplierToTable(userObj);
		}
	});
}

function chSession() {
	let url = contextPath +"/api/user/get_permission_menu";
	$.ajax({
		url : url,
		type : 'POST',
		contentType: "application/json",
		data : "supplier.jsp",
		success : function(data){
			if(!data.result){
				window.location = "/auditsupplier/home.jsp";
			}
		}
	});
}

function loadSupplierToTable(userObj){

	var supplierTable = $('#table_supplier').DataTable({
		/*"aLengthMenu": [[5, 10, 20, -1], [5, 10, 20, "All"]],
        "iDisplayLength": 5,*/
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": contextPath + "/api/supplier/datatable/get_supplier_list",
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
	    	{ "data": "supplierCompany" ,name:"supplierCompany"},
	    	{ "data": "supplierCode" ,name:"supplierCode"},
	    	{
	    		"data" : "isGreenCard",
	    		"name" : "isGreenCard",
	    		"render" : function(data, type, row){
	    			
	    			let greenCard = '<span class="label label-sm bg-green-meadow" style="font-size : 12px;"> ได้รับ </span>';
					if (data == 'N' ){
						greenCard = '<span class="label label-sm label-default" style="font-size : 12px;"> ไม่ได้รับ </span>';
					}
					
	                return greenCard;
	    		},
	            "width": "70px"
	    	},
	    	{
	            "data": "enable",
	            "name" : "enable",
	            "render": function (data, type, row) {
	            	
	            	var dpStatus = '<span class="label label-sm label-success" style="font-size : 12px;"> ใช้งาน </span>';
					if (data == 'N' ){
						dpStatus = '<span class="label label-sm label-default" style="font-size : 12px;"> ไม่ใช้งาน </span>';
					}
					
	                return dpStatus;
	            },
	            "width": "70px" 
	        },
	        { 	"data": "supplierId",
	        	"name": "supplierId"
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
	        	"targets": [5],
	            "visible": false,
	            "searchable": false
	        },	        
	        {
	            "searchable": false,
	            "orderable": false,
	            "targets": [0,5]
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [1,2,3,4]
	        },
	        {
	            "targets": 6,
	            "orderable": false,
	            "render": function (data, type, row) {
	            	
	            	
	            	var btnAction = '<div class="btn-group">';
	            	btnAction += '<button class="btn btn-xs blue dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false" style="font-size : 12px;"> เลือก <i class="fa fa-angle-down"></i></button>';
	    			btnAction += '<ul class="dropdown-menu pull-left" role="menu">';
    				btnAction += 	'<li><a href="javascript:void(0);" class = "editSupplier">';
	    			btnAction +=   		'<i class="icon-pencil"></i> แก้ไขข้อมูล </a>';
	    			btnAction +=	'</li>';
	    			btnAction +=	'<li><a href="javascript:void(0);" class = "removeSupplier">';
	    			btnAction +=		'<i class="icon-trash"></i> ลบข้อมูล </a>';
	    			btnAction +=	'</li>';
	                btnAction += '</ul>';
	                btnAction +='</div>';
	               
	                return btnAction;
	            },
	            "width": "70px" 
	        }
	    ],
	    "order": [['5', 'asc']],
	    "initComplete": function (settings, json) {
	    	
	    	$('.dataTables_filter').remove();
	    	
	    	bootstrapSwitch.init();
	    	/*$('.checkbox-status').each(function(){
	    		$.fn.bootstrapSwitch.defaults.size = 'normal';
		    	$.fn.bootstrapSwitch.defaults.onColor = 'success';
		    	$(this).bootstrapSwitch();
	    	});*/
	    	
	    	
	    	
	    } 
	});
}


function generateDatePicker(){
	dateTime.currentDateTime('yyyy/MM/dd HH:mm:ss',(currentTimeDate)=>{
		let currentDate = new Date();
		currentDate.setDate(currentTimeDate.getDate()+1);
		
		expireDefault = new Date();
		expireDefault.setDate(currentTimeDate.getDate()+365)
		
		$('.datepicker-dialog').datepicker({
			language: "th",
			format: 'dd/mm/yyyy',
			orientation: "top auto",
			autoclose: true
		}).on('hide', function(e) {
	       /* if($('#appoint_date_pickers').val() == ""){
	        	$('#appoint_date_pickers').val($('#appoint_date').val());
	        }*/
	    });			
		$('.datepicker-dialog').datepicker('setDate',expireDefault);
		$('.datepicker-dialog').datepicker('setStartDate',currentDate);
	});
}

$(document).on('click','a.editSupplier',function(){
	var data = $('#table_supplier').DataTable().row( $(this).parents('tr') ).data();
	var supplierId = data.supplierId.trim();
	$("#input_supplierId").val(URI.encode(JSON.stringify({
		supplierId : supplierId
	})));	
	$('#submithiddenform').submit();
});

$(document).on('click','a.removeSupplier',function(){
	let data = $('#table_supplier').DataTable().row( $(this).parents('tr') ).data();
	let supplierId = data.supplierId.trim();	
	
	let url = contextPath+'/api/supplier/supplier_delete';
	let objSupplier = {supplierId : supplierId};
	ConfirmModal.setupModal("warning","ยืนยัน", "ยืนยันการลบใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){ 			
			ajaxProcess.submit(url, 'DELETE', objSupplier, null, (data)=>{
				if(data.result){
					$('#table_supplier').DataTable().draw();
				}
			});					
		}
	});
	
});


$('#logo').on('filebatchselected', function(event) {
    var files = $('#logo').fileinput('getFileStack');
    filesArray = files;
});

$('#btn_submitsupplier_modal').on('click',function(){
	
	if($('#supplier_form').valid()){
		
		let resultValidateLocal = validateInputAddress({
			subDistrictName : $('#sub_district_modal').val().trim(),
			districtName : $('#district_modal').val().trim(),
			province : $('#province_modal').val().trim(),
			postCode : $('#postcode_modal').val().trim()
		});
		
		if(!resultValidateLocal){
			 $('#sub_district_modal').typeahead('val','');
			 $('#district_modal').typeahead('val','');
			 $('#province_modal').typeahead('val','');
			 $('#postcode_modal').typeahead('val','');
			 
			 $('#sub_district_id_modal').val('');
			 $('#district_id_modal').val('');
			 $('#province_id_modal').val('');
			return false;
		}
		
		/*if(filesArray.length <= 0){
			customMessageDialog("warning","คำเตือน","กรุณาเลือกรูปภาพ",null);
			return false;
		}*/
		
		
		let statusApproval = 'N';
		if($('#status_avl').prop('checked')){
			statusApproval = 'Y';
		}
		let supplierCode = $('#supplier_Code_modal').val().trim();
		let supplierCompany = $('#supplier_company_modal').val().trim();
		let supplierEmail = $('#supplier_email_modal').val().trim();
		let supplierTelephone = $('#supplier_telephone_modal').val().trim();
		let auditRound = $('#auditRound').val().trim();
		let expireDateGreenCard = '';
		let supplierGreenCard = 'N';
		if($('#status_greenCard').prop('checked')){
			supplierGreenCard = 'Y';
			expireDateGreenCard = $('#greenCardExpire').val().trim();
		}
		let supplierStatus = 'N';
		if($('#status_supplier').prop('checked')){
			supplierStatus = 'Y';
		}
		let supplierUserLan = $('#userlan_dialog').val().trim();
		let supplierPassword = $('#password_dialog').val().trim();
		let supplierAddress = $('#address_modal').val().trim();
		let supplierSubDistrictId = $('#sub_district_id_modal').val().trim();
		let supplierSubDistrictName = $('#sub_district_modal').val().trim();
		let supplierDistrictName = $('#district_modal').val().trim();
		let supplierDistrictId = $('#district_id_modal').val().trim();
		let supplierProvince = $('#province_modal').val().trim();
		let supplierProvinceId = $('#province_id_modal').val().trim();
		let supplierPostCode = $('#postcode_modal').val().trim();
		let supplierContractName = $('#contact_name_modal').val().trim();
		let logoFileName = '';
		$(filesArray).each(function(i,file){
			let filesName = file.name;
			let filesType = file.type.split('/')[1];
			if(filesType == "jpeg" || filesType == "jpg"){
				filesName = filesName.split('.'+filesType)[0];
				filesName = filesName.split('.jpg')[0];
			}
			logoFileName = (filesName.split('.'+filesType)[0]).concat('_').concat(uniqueIdLogo).concat('.').concat(filesType);	
			
		});
		
		let objSupplier = {
			supplierId : '',
			supplierCode : supplierCode,
			supplierCompany : supplierCompany,	
			approval : statusApproval,
			isGreenCard : supplierGreenCard,
			enable : supplierStatus,
			logo : logoFileName,
			greenCardExpireDate : expireDateGreenCard,
			auditRound : auditRound,
			userId : [{	
				userGroupId : {
					userGroupId : '3'
				},
				username : supplierUserLan,
				password : supplierPassword,
				email : supplierEmail,
				telephone : supplierTelephone,
				fullname : supplierContractName,
				enable : 'Y'
			}],
			addressId : {
				address : supplierAddress,
				provinceId : {
					provinceId : supplierProvinceId,
					name : supplierProvince
				},
				districtId : {
					districtId : supplierDistrictId,
					name : supplierDistrictName
				},
				subDistrictId : {
					subDistrictId : supplierSubDistrictId,
					name : supplierSubDistrictName
				},
				postcode : supplierPostCode,
				enable : 'Y'
			}
		};
		
		
		let url = contextPath+'/api/supplier/supplier_insert';
		ajaxProcess.submit(url, 'POST', objSupplier, '#dialog_add_supplier', (data)=>{
			if(data.result){
				$('#table_supplier').DataTable().draw();
		    	$('#dialog_add_supplier').modal('toggle');
			}
		});
		/*let resultFromajax = false;
		let url = contextPath+'/api/supplier/supplier_insert';
		$.ajax({
			url : url,
			type: 'POST',
			contentType: 'application/json',
			data : JSON.stringify(objSupplier),
			beforeSend : function(arr, $form, options){
				BlockUi.blockPosition('#dialog_add_supplier');
		    },
		    complete : function(){
		    	setTimeout(function() {
		    		if(resultFromajax){
		    			$('#table_supplier').DataTable().draw();
				    	$('#dialog_add_supplier').modal('toggle');
		    		}
		    		BlockUi.unBlockPosition('#dialog_add_supplier');  
	            }, 1000);
		    },
		    success: function(data, textStatus, jqXHR){		    	
		    	if(data.result == true){
		    		customMessageDialog("success", "สถานะการดำเนินการ", data.message, null);	 
		    		resultFromajax = true;		    	
		    	}else{
		    		customMessageDialog("error", "สถานะการดำเนินการ", data.message, null);
		    	}
		    	
		    },
		    error: function (jqXHR, exception){
		    	customMessageDialog("error", "เกิดข้อผิดพลาด", jqXHR.responseJSON.message, null);
		    }
		});*/
	}
	
	
});


$('#dialog_add_supplier').on('hidden.bs.modal', function () {
	validateForm.resetForm();
	$("#supplier_form").find('.has-error').removeClass('has-error');
	$('#supplier_Code_modal').val('');
	$('#supplier_company_modal').val('');
	$('#supplier_email_modal').val('');
	$('#supplier_telephone_modal').val('');
	$('#contact_name_modal').val('');
	$('#address_modal').val('');
	$('#sub_district_modal').typeahead('val','');
	$('#district_modal').typeahead('val','');
	$('#province_modal').typeahead('val','');
	$('#postcode_modal').typeahead('val','');	 
	$('#sub_district_id_modal').val('');
	$('#district_id_modal').val('');
	$('#province_id_modal').val('');
	$('#userlan_dialog').val('');
	$('#password_dialog').val('');
	$('#status_greenCard').bootstrapSwitch('state',false);
	$('#status_supplier').bootstrapSwitch('state',true);
	$('#status_avl').bootstrapSwitch('state',false);
	$('#auditRound').val('');
	$('.datepicker-dialog').datepicker('setDate',expireDefault);
	$('.expire-greencard').hide();
	filesArray = [];
	$('#logo').fileinput('clear');
});



/*$('body').on('click', '.portlet > .portlet-title', function (e) {
	e.preventDefault();
	$(this).find('.tools > .collapseOption, .tools > .expandOption').each(function() {
		
		var el = $(this).closest(".portlet").children(".portlet-body");
		
		if ($(this).hasClass("collapseOption")) {
            $(this).removeClass("collapseOption").addClass("expandOption");
            $(this).find('.fa').removeClass('fa-angle-up').addClass('fa-angle-down');
            el.slideUp(200);
        } else {
            $(this).removeClass("expandOption").addClass("collapseOption");
            $(this).find('.fa').removeClass('fa-angle-down').addClass('fa-angle-up');
            el.slideDown(200);
        }
		
	});
});*/


function validateInputAddress(inputAddress){
	
	let filterSubDistrict = locationRawList.filter(local => local.subDistrictName === inputAddress.subDistrictName);
	if(filterSubDistrict.length > 0){
		let filterDistrict = filterSubDistrict.filter(local => local.districtName === inputAddress.districtName);		
		if(filterDistrict.length > 0){
			
			let filterProvince = filterDistrict.filter(local => local.provinceName === inputAddress.province);
			if(filterProvince.length > 0){
				
				let filterPostcode = filterProvince.filter(local => local.postcode === inputAddress.postCode);
				if(filterPostcode.length > 0){
					return true;
					
				}else{
					return false;
				}				
			}else{
				return false;
			}			
		}else{
			return false;
		}		
	}else{
		return false;
	}
	//console.log(locationRawList.filter(x => x.subDistrictName == "หนองปากโลง").map(x => x.districtName));
}

var loadLocation = function(){
	return{
		init : function(){			
		    loadLocation.processFunction(loadLocation.callBack);
		},
		processFunction : function(callback){
			$.ajax({
				//url : contextPath+'/assets/global/plugins/thailand/raw_database.json',		
				url : contextPath+'/api/address/get_address_list',	
				contentType: 'application/json',
				beforeSend : function(arr, $form, options){
					BlockUi.blockPage();
			    },
			    complete : function(){
			    	setTimeout(function() {
			    		BlockUi.unBlockPage();  
		            }, 300);
			    },
				success: function(data, textStatus, jqXHR){
					let locatoionRawData = [];
					if(data.result){
						let objJSON = JSON.parse(data.message);
						$(objJSON).each(function(i,v){
							locatoionRawData.push({
								subDistrictId : v.subDistrictId.subDistrictId,
								subDistrictName : v.subDistrictId.name,
								districtId : v.districtId.districtId,
								districtName : v.districtId.name,
								provinceId : v.provinceId.provinceId,
								provinceName : v.provinceId.name,
								postcode : v.postcode,
								regionId : v.provinceId.regionId
							});
						});
					}else{
						customMessageDialog("error", "สถานะการดำเนินการ", data.message, null);
					}										
					return callback(locatoionRawData);
				}
			});
		},
		callBack : function(_d){			
			let tempalate = {
				empty: " ",
				suggestion: function(e) {			
					return e.postcode && (e.postcode = " » " + e.postcode), "<div>" + e.subDistrictName + " » " + e.districtName + " » " + e.provinceName + e.postcode + "</div>"
				}
			};
			let autoComplete_size = 12;
			let location = new Bloodhound({
				queryTokenizer: Bloodhound.tokenizers.whitespace,
				datumTokenizer: Bloodhound.tokenizers.obj.whitespace('subDistrictName','districtName','provinceName','postcode'),
				local : _d			    			    
			});
			location.initialize();
			
			$("#sub_district_modal").typeahead({
				hint: true,
				highlight: true,
				minLength: 1
		    },
		    {
		    	limit : autoComplete_size,
		    	display: function(item){ return item.subDistrictName},
		    	source: location.ttAdapter(),
		    	templates : tempalate
		    }).on('typeahead:selected', function(event, obj) {
		    	$("#district_modal").typeahead('val',obj.districtName);
		    	$("#province_modal").typeahead('val',obj.provinceName);
		    	$("#postcode_modal").typeahead('val',obj.postcode);		    	
		    	
		    	$('#sub_district_id_modal').val(obj.subDistrictId);
		    	$('#district_id_modal').val(obj.districtId);
		    	$('#province_id_modal').val(obj.provinceId);
		    });
			
			$("#district_modal").typeahead({
				hint: true,
				highlight: true,
				minLength: 1
		    },
		    {
		    	limit : autoComplete_size,
		    	display: function(item){ return item.districtName},
		    	source: location.ttAdapter(),
		    	templates : tempalate
		    }).on('typeahead:selected', function(event, obj) {		    	
		    	$("#sub_district_modal").typeahead('val',obj.subDistrictName);
		    	$("#province_modal").typeahead('val',obj.provinceName);
		    	$("#postcode_modal").typeahead('val',obj.postcode);
		    	
		    	$('#sub_district_id_modal').val(obj.subDistrictId);
		    	$('#district_id_modal').val(obj.districtId);
		    	$('#province_id_modal').val(obj.provinceId);
		    });
			
			$("#province_modal").typeahead({
				hint: true,
				highlight: true,
				minLength: 1
		    },
		    {
		    	limit : autoComplete_size,
		    	display: function(item){ return item.provinceName},
		    	source: location.ttAdapter(),
		    	templates : tempalate
		    }).on('typeahead:selected', function(event, obj) {		    	
		    	$("#sub_district_modal").typeahead('val',obj.subDistrictName);
		    	$("#district_modal").typeahead('val',obj.districtName);
		    	$("#postcode_modal").typeahead('val',obj.postcode);
		    	
		    	$('#sub_district_id_modal').val(obj.subDistrictId);
		    	$('#district_id_modal').val(obj.districtId);
		    	$('#province_id_modal').val(obj.provinceId);
		    });
			
			$("#postcode_modal").typeahead({
				hint: true,
				highlight: true,
				minLength: 1
		    },
		    {
		    	limit : autoComplete_size,
		    	display: function(item){ return item.postcode},
		    	source: location.ttAdapter(),
		    	templates : tempalate
		    }).on('typeahead:selected', function(event, obj) {		    	
		    	$("#sub_district_modal").typeahead('val',obj.subDistrictName);
		    	$("#district_modal").typeahead('val',obj.districtName);
		    	$("#province_modal").typeahead('val',obj.provinceName);
		    	
		    	$('#sub_district_id_modal').val(obj.subDistrictId);
		    	$('#district_id_modal').val(obj.districtId);
		    	$('#province_id_modal').val(obj.provinceId);
		    });
		}
	}
}();






$('#btn_clear').on('click',function(){
	$("#select_status").val($("#select_status option:first").val());
	$("#select_green_card").val($("#select_green_card option:first").val());
	$('#company_textbox').val('');
	$('#supplier_code_textbox').val('');
	
	$('#table_supplier').DataTable().columns(1).search('');
	$('#table_supplier').DataTable().columns(2).search('');
	$('#table_supplier').DataTable().columns(3).search('');
	$('#table_supplier').DataTable().columns(4).search('');
	$('#table_supplier').DataTable().draw();
});

$('#btn_search').on('click',function(){
	searchTable();
});

$("#company_textbox").keyup(function(event) {
    if (event.keyCode === 13) {
    	searchTable();
    }
});
$("#supplier_code_textbox").keyup(function(event) {
    if (event.keyCode === 13) {
    	searchTable();
    }
});
/*$( "#select_green_card" ).change(function() {
	searchTable();
});
$( "#select_status" ).change(function() {
	searchTable();
});*/

function searchTable(){
	
	let company = $('#company_textbox').val().trim();
	let supplierCode = $('#supplier_code_textbox').val().trim();
	let greenCard = $('#select_green_card').val().trim();
	let status = $('#select_status').val().trim();	
	
	$('#table_supplier').DataTable().columns(1).search(company);
	$('#table_supplier').DataTable().columns(2).search(supplierCode);
	$('#table_supplier').DataTable().columns(3).search(greenCard);
	$('#table_supplier').DataTable().columns(4).search(status);
	$('#table_supplier').DataTable().draw();
}


