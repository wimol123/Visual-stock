/**
 * 
 */
var checkInsertOrUpdate = '';
var checkLoadproduct = false;
var filesArray = [];
var uniqueIdLogo = null;
var locationRawList = [];
var filesNameNew = "";
var validateForm = null;
var validateContractForm = null;
var validateLocationProduce = null;
var userObjSession = null;

$(function(){	
	generateDatePicker();
	//$('#status_greenCard').bootstrapSwitch('state',true);
	//$('#status_supplier').bootstrapSwitch('state',true);
	$('.form-description').hide();
	let objectValidate = {
			element : '#supplier_form',
			require : ['supplier_company','contact_name','address','sub_district','district','province','postcode','userlan_dialog','password_dialog','supplier_telephone', 'auditRound'],
			message : ["กรุณาใส่ชื่อบริษัท","กรุณาใส่ชื่อผู้ติดต่อ","กรุณาใส่ที่อยู่","กรุณาใส่ตำบล/แขวง","กรุณาใส่อำเภอ/เขต","กรุณาใส่จังหวัด","กรุณาใส่รหัสไปรษณีย์","กรุณาใส่ชื่อเข้าใช้งาน","กรุณาใส่รหัสผ่าน","กรุณาใส่เบอร์โทรศัพท์", "กรุณาใส่รอบเข้าตรวจ"],
			maxLength : [100, 100, 200, 200, 200, 200, null, 50, null, 30, 11]
		};
	validateForm = validate.init(objectValidate);
	
	objectValidate = {
		element : '#location_produce_form',
		require : ['product_type_selection','location_name_produce','address_produce','sub_district_produce','district_produce','province_produce','postcode_produce'],
		message : ["กรุณาเลือกประเภทสินค้า","กรุณาใส่ชื่อสถานที่ผลิต","กรุณาใส่ที่อยู่","กรุณาใส่ตำบล/แขวง","กรุณาใส่อำเภอ/เขต","กรุณาใส่จังหวัด","กรุณาใส่รหัสไปรษณีย์"],
		maxLength : [null, 100, 200, 200, 200, 200, null]
	};
	validateLocationProduce = validate.init(objectValidate);
	
	objectValidate = {
		element : '#supplier_Code',
		rules : [{maxlength : 50}],
		message : [null]
	}
	validate.add(objectValidate);
	
	$( "#postcode" ).rules( "add", {
		number : true,		
		minlength: 5,
		maxlength : 5,
		messages: {
			number: "กรุณาใส่เป็นตัวเลขเท่านั้น",
		    minlength: jQuery.validator.format("กรุณาใส่รหัสไปรษณีย์ให้ครบ  {0} หลัก"),
		    maxlength: jQuery.validator.format("กรุณาใส่รหัสไปรษณีย์ไม่เกิน  {0} หลัก")
		}
	});
	
	$( "#postcode_produce" ).rules( "add", {
		number : true,		
		minlength: 5,
		maxlength : 5,
		messages: {
			number: "กรุณาใส่เป็นตัวเลขเท่านั้น",
		    minlength: jQuery.validator.format("กรุณาใส่รหัสไปรษณีย์ให้ครบ  {0} หลัก"),
		    maxlength: jQuery.validator.format("กรุณาใส่รหัสไปรษณีย์ไม่เกิน  {0} หลัก")
		}
	});	
	
	$( "#supplier_email" ).rules( "add", {
		required : true,
		validateEmail : true,
		messages: {
			required : "กรุณากรอกอีเมล์"
		}
	});
	
	$('#auditRound').rules("add", {
		number: true,
		messages: {
			number : "กรุณาใส่ข้อมูลเป็นตัวเลขเท่านั้น"
		}
	});
	
	/*$.validator.addMethod("validatelogo", function() {		
		if(filesArray.length <= 0){
			return false;
		}
		return true;
	  }, 
	  	"กรุณาเลือกรูปภาพโลโก้บริษัท");*/
	
	/*$("#logo").rules("add",{
		validatelogo : true
	});*/
	
	objValidateContractForm = {
		element : '#supplier_contract_form',
		require : ['user_group_selection','contact_name_contract_modal','userlan_contract_modal','contact_tel_contract_modal','description_contract_modal'],
		message : ["กรุณาเลือกกลุ่มผู้ใช้","กรุณาใส่ชื่อผู้ติดต่อ","กรุณาใส่ชื่อเข้าใช้งาน","กรุณาใส่เบอร์โทรศัพท์","กรุณาระบุรายละเอียดของผู้ใช้นี้"],
		maxLength : [null, 100, 50, 30, 100]
	};
	validateContractForm = validate.init(objValidateContractForm);
	$( "#contact_email_contract_modal" ).rules( "add", {
		required : true,
		validateEmail : true,
		messages: {
			required : "กรุณากรอกอีเมล์"
		}
	});
	$( "#employee_id_contract_modal" ).rules( "add", {
		required : false,
		maxlength : 10,
		messages : {
			maxlength : jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร")
		}
	});
	/*$('#employee_id_contract_modal').rules("add",{
		required : false,
		maxlength : 10,
		message : {
			maxlength : jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร")
		}
	});*/
	
	if($('#supplier_id').val() == ""){		
		checkInsertOrUpdate = 'Insert';
		$(".user-input").slideDown("slow");
		$(".password-input").slideDown("slow");
		$(".option-supplier").addClass("disabledelements").fadeOut('slow');		
	
		if($("#portlet-supplierDetail").parent().hasClass('col-md-6')){			
			$("#portlet-supplierDetail").parent().removeClass('col-md-6').addClass('col-md-12');		
			$('#supplier_form').find("#logo").parent().parent().removeClass('col-md-6').addClass('col-md-4');
			$('#supplier_form').find("#logo").parent().parent().next().removeClass('col-md-6').addClass('col-md-8');
		}
		loadSession();
		//BlockUi.blockPageWithTimeOutAndRedirect('warning',null,'supplier.jsp','500');
	}
	else{
		loadSession();
		checkInsertOrUpdate = 'Update';
		$(".user-input").hide();
		$(".password-input").hide();
		$(".group-detail-address").hide();
		let jsonString = URI.decode($('#supplier_id').val());
		$("#sub_district").typeahead();
		$("#district").typeahead();
		$("#province").typeahead();
		$("#postcode").typeahead();
		BlockUi.blockPosition('#portlet-location-produce');
		BlockUi.blockPosition('#portlet-contract');
		
		let url = contextPath+'/api/supplier/supplier_detail/'+jsonString;
		
		ajaxProcess.submit(url, 'GET', null, "#portlet-supplierDetail", function(dataObj){	
			let supplierObj = {};
			try{
				supplierObj = JSON.parse(dataObj.message);
				if(supplierObj != []){
					$(supplierObj).each(function(i,v){
						if(!($.isEmptyObject(v.addressId))){	
							
							$("#supplierId").val(v.supplierId);
							$("#supplier_Code").val(v.supplierCode);
							$("#supplier_company").val(v.supplierCompany);
							$("#address").val(v.addressId.address);
							$("#sub_district").typeahead('val',v.addressId.subDistrictId.name);
							$("#district").typeahead('val',v.addressId.districtId.name);
							$("#province").typeahead('val',v.addressId.provinceId.name);
							$("#postcode").typeahead('val',v.addressId.postcode);
							
							$("#address_id").val(v.addressId.addressId);
							$("#sub_district_id").val(v.addressId.subDistrictId.subDistrictId);
							$("#district_id").val(v.addressId.districtId.districtId);
							$("#province_id").val(v.addressId.provinceId.provinceId);
							
							if(v.approval == "N")
								$('#status_avl').bootstrapSwitch('state',false);
							if(v.isGreenCard == "N"){
								$('#status_greenCard').bootstrapSwitch('state',false);
								$('.expire-greencard').hide();
								v.greenCardExpireDate = "";
							}
								
							if(v.enable == "N")
								$('#status_supplier').bootstrapSwitch('state',false);
							
							$('#supplier_form').find("#contact_name").parent().parent().addClass('hide');
							$('#supplier_form').find("#userlan_dialog").parent().addClass('hide');
							$('#supplier_form').find("#supplier_email").parent().addClass('hide');
							$('#supplier_form').find("#supplier_telephone").parent().addClass('hide');
							$("#logoPath").val(v.logo);
							let logoString = URI.encode(v.logo).replace(/%5C/g, '%5C%5C');
							let objRequest = {
								logo : logoString
							};
							functionLoadLogo(objRequest);
							
							$('#auditRound').val(v.auditRound);
							if(v.greenCardExpireDate != ""){
								$('.datepicker-dialog').datepicker('setDate',v.greenCardExpireDate.split(" ")[0].trim());
								$('#greenCardExpire').val(v.greenCardExpireDate.split(" ")[0].trim());								
								$('#greenCardExpireHistory').val(v.greenCardExpireDate.split(" ")[0].trim());
							}
							
							
							/*ajaxProcess.submit(contextPath+'/api/download/logo_supplier/'+JSON,stringify(objRequest), 'GET', "", "#portlet-supplierDetail", function(dataObj){
								//console.log(dataObj);
							});*/
						}
					});
					
					getLocationProduce();
					getContact();
					laodGroup();
					initialSelectionProductType();
					loadAppoint(supplierObj[0].supplierId.trim());
					loadSupplierStandardDocument.init(supplierObj[0].supplierId.trim());
					loadSupplierQuestionaireDocument.init(supplierObj[0].supplierId.trim());
				}
			}catch(e){
				if($("#portlet-supplierDetail").parent().hasClass('col-md-6')){			
					$("#portlet-supplierDetail").parent().removeClass('col-md-6').addClass('col-md-12');		
					$('#supplier_form').find("#logo").parent().parent().removeClass('col-md-6').addClass('col-md-4');
					$('#supplier_form').find("#logo").parent().parent().next().removeClass('col-md-6').addClass('col-md-8');
				}
				$('#supplier_id').val("");
			}
			
			
		});
		
	}	
	loadLocation.processFunction(function(data){
		locationRawList = data;
		loadLocation.callBack(data);
	});
	
	
	bootstrapSwitch.init();	
	generateFileInput();
	/*validateInnerText.validByElement('#userlan_dialog', null);
	validateInnerText.validByElement('#userlan_contract_modal', null);
	validateInnerText.validByElement('#auditRound', /[0-9]/g);*/
});

$('#userlan_dialog').on("keypress", function(e){
	return validateInnerText.valid(/[a-zA-Z0-9 _]/g, e)
});
$('#userlan_contract_modal').on("keypress", function(e){
	return validateInnerText.valid(/[a-zA-Z0-9 _]/g, e)
});
$('#auditRound').on("keypress", function(e){
	return validateInnerText.valid(/[0-9]/g, e)
});

$('#supplier_email').on("keyup", function(){
	var validateMail = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if (validateMail.test(this.value)) {
		$('#supplier_email').valid();
    }
    else {
    	$('#supplier_email').valid();                               
    }	
});

$('#supplier_email').on("keyup", function(){
	var validateMail = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if (validateMail.test(this.value)) {
		$('#supplier_email').valid();
    }
    else {
    	$('#supplier_email').valid();                              
    }	
});

$('#contact_email_contract_modal').on("keyup", function(){
	var validateMail = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if (validateMail.test(this.value)) {
		$('#contact_email_contract_modal').valid();
    }
    else {
    	$('#contact_email_contract_modal').valid();                               
    }	
});

function functionLoadLogo(obj){
	
	if(userObjSession.userGroupId.userGroupId == '5'){
		//let parentColumn = $('#logo').fileinput().closest('.col-md-6');
		//parentColumn.find('.files-input-section').remove();
		  		
	}
	//console.log(contextPath+'/api/download/logo_supplier/'+JSON.stringify(obj));
	//$('#shown-logo').attr("src",contextPath+'/api/download/logo_supplier/'+JSON.stringify(obj));  
	/*if(userObjSession.userGroupId.userGroupId == '5'){
		let parentColumn = $('#logo').fileinput().closest('.col-md-6');
		parentColumn.find('.files-input-section').remove();
		$('#shown-logo').attr("src",contextPath+'/api/download/logo_supplier/'+JSON.stringify(obj));    		
	}*/
	//console.log($('.file-default-preview').find('img').attr("src",contextPath+'/api/download/logo_supplier/'+JSON.stringify(obj)));
	//generateFileInput(contextPath+'/api/download/logo_supplier/'+JSON.stringify(obj));
	/*$.ajax({
		url: contextPath+'/api/download/logo_supplier/'+JSON.stringify(obj),
	    type: 'GET',
	    success : function(data, textStatus, jqXHR) {
	    	
	    	
		}
	});*/
	
}

function loadSession() {
	let url = contextPath +"/api/user/get_session_user";	
	$.ajax({
		url : url,
		type : 'GET',
		contentType: "application/json",
		success : function(data){
			userObjSession = JSON.parse(data.message);	
			$('#portlet-location-produce .btn-group button[href="#dialog_add_location_produce"]').removeClass('hide');
			$('#portlet-contract .btn-group button[href="#form_add_contact"]').removeClass('hide');
			//$('#apoint_supplier .btn-group a[href="appoint.jsp"]').removeClass('hide');
			/*if(userObjSession.userGroupId.userGroupId == '5'){
				
				if($('#supplier_id').val() == ""){
					window.location.href = 'supplier.jsp';
				}else{
					$('#supplier_Code').prop('readonly',true);
					$('#supplier_company').prop('readonly',true);
					$('#address').prop('readonly',true);					
					$('#sub_district').prop('disabled', true);
					$('#district').prop('disabled', true);
					$('#province').prop('disabled', true);
					$('#postcode').prop('disabled', true);
					$("#auditRound").prop('readonly',true);
					$('#btn_submitsupplier').hide();
					$('#status_avl').bootstrapSwitch('readonly',true);
					$('#status_greenCard').bootstrapSwitch('readonly',true);
					$('#status_supplier').bootstrapSwitch('readonly',true);					
					$('#employee_id_contract_modal').prop('readonly',true);
					$('#contact_name_contract_modal').prop('readonly',true);
					$("#user_group_selection").prop("disabled", true);
					$('#description_contract_modal').prop('readonly',true);
					$('#contact_tel_contract_modal').prop('readonly',true);
					$('#contact_email_contract_modal').prop('readonly',true);
					$('#btn_dialog_contact').remove();			
					$("#product_type_selection").prop("disabled", true);
					$('#location_name_produce').prop('readonly',true);
					$('.checkbox').prop('disabled',true);
					$('#address_produce').prop('readonly',true);
					$('#sub_district_produce').prop('disabled', true);
					$('#district_produce').prop('disabled', true);
					$('#province_produce').prop('disabled', true);
					$('#postcode_produce').prop('disabled', true);
					$('#btn_submit_location_produce').remove();
				}		
				
			}else{
				
				$('#portlet-location-produce .btn-group button[href="#dialog_add_location_produce"]').removeClass('hide');
				$('#portlet-contract .btn-group button[href="#form_add_contact"]').removeClass('hide');
				$('#apoint_supplier .btn-group a[href="appoint.jsp"]').removeClass('hide');
			}*/
			
		}
	});
}


$('#btn_change_logo').on('click',function(){
	let parentBtn = $(this).parent();
	$(this).parent().slideUp('slow',function(e){
		parentBtn.prev().slideDown('slow');
	});
	
});

function generateFileInput(){	
	
	$("#logo").fileinput({
		previewFileType: "image",
		language: "th",
		overwriteInitial: true,
		initialPreview : true,
		initialPreviewAsData: true, // identify if you are sending preview data only and not the markup
		maxFileSize: 300,
		maxFileCount: 1,		
		showClose: false,
	    showCaption: false,
	    showBrowse: false,
		showUpload: true, // hide upload button
	    showRemove: false, // hide remove button
	    browseOnZoneClick: true,
	    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
	    elErrorContainer: '#error-choose-file',
	    msgErrorClass: 'alert alert-block alert-danger',
	    defaultPreviewContent: '<img id="logo_supplier" style="width:100%" alt="Your Logo"><h6 class="text-muted">คลิกเพื่อเปลี่ยนรูปภาพ</h6>',
	    //defaultPreviewContent: '<img src="'+contextPath+'/assets/images/picture-attachment.png" alt="Your Logo"><h6 class="text-muted">คลิกเพื่อเลือกรูปภาพ</h6>',
	    layoutTemplates: {main2: '{preview} {remove} {browse}'},
	    allowedFileExtensions: ["jpg", "png", "gif"],
	    uploadUrl: contextPath+'/api/supplier/upload_logo',
        uploadAsync: false,
        fileActionSettings: {
        	showUpload: true,
        }
	}).on('filebatchpreupload', function(event, data) {
		uniqueIdLogo = generate.randomByDate();
		let filesName = data.files[0].name;
		let filesType = data.files[0].type.split('/')[1];
		filesNameNew = (filesName.split('.'+filesType)[0]).concat('_').concat(uniqueIdLogo).concat('.').concat(filesType);
		data.form.append('logo_supplier', data.files[0],filesNameNew); 		
	}).on('filebatchselected', function(event, files) {
		
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
	        	BlockUi.blockPosition("#portlet-supplierDetail");
		    },
		    complete : function(){
		    	if($('#supplier_id').val() != ''){
		    		let jsonString = URI.decode($('#supplier_id').val());
		    		let url = contextPath+'/api/download/logo_supplier/'+jsonString;
		    		$.ajax({
		    			url : url,
		    			method : 'GET',
		    			success : function(data){
		    				console.log(data)
		    				if(data.result){
		    					let urlImg = data.message;
		    					$('#logo_supplier').attr("src",urlImg); 
		    				}else{
		    					$('#logo_supplier').removeAttr( "style" )
		    					$('#logo_supplier').attr("src",contextPath+'/assets/images/picture-attachment.png'); 
		    				}			
		    				
		    			}
		    		});
		    	}
		    	setTimeout(function(){
		    		BlockUi.unBlockPosition("#portlet-supplierDetail");
		    	},500);		    	
		    }
		});
		filesArray = [];
	}).on('filebatchselected', function(event) {
	    var files = $('#logo').fileinput('getFileStack');
	    filesArray = files;
	});
	if($('#supplier_id').val() != ''){
		let jsonString = URI.decode($('#supplier_id').val());
		let url = contextPath+'/api/download/logo_supplier/'+jsonString;
		$.ajax({
			url : url,
			method : 'GET',
			success : function(data){
				if(data.result){
					let urlImg = data.message;
					if(urlImg.trim() == ""){
						$('#logo_supplier').removeAttr( "style" )
						$('#logo_supplier').attr("src",contextPath+'/assets/images/picture-attachment.png'); 
					}else{
						$('#logo_supplier').attr("src",urlImg); 
					}					
				}else{
					$('#logo_supplier').removeAttr( "style" )
					$('#logo_supplier').attr("src",contextPath+'/assets/images/picture-attachment.png'); 
				}			
				
			}
		});
		
		
	}else{
		$('#logo_supplier').removeAttr( "style" )
		$('#logo_supplier').attr("src",contextPath+'/assets/images/picture-attachment.png'); 
	}
	

}


$('#btn_submitsupplier').on('click',function(){
	
	if($('#supplier_form').valid()){
		
		let resultValidateLocal = validateInputAddress({
			subDistrictName : $('#sub_district').val().trim(),
			districtName : $('#district').val().trim(),
			province : $('#province').val().trim(),
			postCode : $('#postcode').val().trim()
		});
		
		if(!resultValidateLocal){
			customMessageDialog("error", "เกิดข้อผิดพลาด", "กรุณาใส่ข้อมูลที่อยู่ให้ถูกต้องก่อนการบันทึกข้อมูล", null);
			 $('#sub_district').typeahead('val','');
			 $('#district').typeahead('val','');
			 $('#province').typeahead('val','');
			 $('#postcode').typeahead('val','');
			 
			 $('#sub_district_id').val('');
			 $('#district_id').val('');
			 $('#province_id').val('');
			return false;
		}
		
		
		let statusApproval = 'N';
		if($('#status_avl').prop('checked')){
			statusApproval = 'Y';
		}
		let supplierId = $('#supplierId').val().trim();
		let supplierCode = $('#supplier_Code').val().trim();
		let supplierCompany = $('#supplier_company').val().trim();
		let supplierEmail = $('#supplier_email').val().trim();
		let supplierTelephone = $('#supplier_telephone').val().trim();
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
		let supplierAddress = $('#address').val().trim();
		let supplierSubDistrictId = $('#sub_district_id').val().trim();
		let supplierSubDistrictName = $('#sub_district').val().trim();
		let supplierDistrictName = $('#district').val().trim();
		let supplierDistrictId = $('#district_id').val().trim();
		let supplierProvince = $('#province').val().trim();
		let supplierProvinceId = $('#province_id').val().trim();
		let supplierPostCode = $('#postcode').val().trim();
		let supplierContractName = $('#contact_name').val().trim();
		let addressId = $("#address_id").val().trim();
		let logoFileName = '';
		$(filesArray).each(function(i,file){
			let filesName = file.name;
			let filesType = file.type.split('/')[1];
			logoFileName = (filesName.split('.'+filesType)[0]).concat('_').concat(uniqueIdLogo).concat('.').concat(filesType);		
		});
		
		let objSupplier = {
			supplierId : supplierId,
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
				email : supplierEmail,
				telephone : supplierTelephone,
				fullname : supplierContractName,
				enable : 'Y'
			}],
			addressId : {
				addressId : addressId,
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
		
		if($('#supplier_id').val() == ""){
			let url = contextPath+'/api/supplier/supplier_insert';
			ajaxProcess.submit(url,'POST',objSupplier,'#portlet-supplierDetail',function(data){
				if(data.result){
					BlockUi.blockPositionWithTimeOutAndRedirect('#portlet-supplierDetail', 'warning', null, 'supplier.jsp', 500);
				}
			});
		}else{
			ConfirmModal.setupModal("warning","ยืนยัน", "ยืนยันการแก้ไขข้อมูลนี้ใช่หรือไม่?");	
			ConfirmModal.confirmResult(function(isTrue){
				if(isTrue){ 			
					let url = contextPath+'/api/supplier/supplier_update';
					ajaxProcess.submit(url,'POST',objSupplier,'#portlet-supplierDetail',null);
				}
			});
			
			
			
		}
		
		
	}else{
		return false;
	}
	
});


$('#btn_dialog_contact').on('click',function(){
	
	if($("#supplier_contract_form").valid()){
		if($('#supplierId').val() == ""){
			customMessageDialog("error", "เกิดข้อผิดพลาด", "กรุณาสร้างข้อมูลของ Supplier ก่อน", null);
			return;
		}
		
		let enableStatus = 'Y';
		let userObj = {
			employeeId : $('#employee_id_contract_modal').val().trim(),
			userId : $('#contact_id_contract_modal').val().trim(),
			userGroupId : {
				userGroupId : $('#user_group_selection').val().trim()
			},
			username : $('#userlan_contract_modal').val().trim(),
			fullname : $('#contact_name_contract_modal').val().trim(),
			description : $('#description_contract_modal').val().trim(),
			email : $('#contact_email_contract_modal').val().trim(),
			telephone : $('#contact_tel_contract_modal').val().trim(),
			enable : enableStatus,
			supplierId : [{
				supplierId : $('#supplierId').val()
			}]
		};
		
		if(userObj.userId == ''){
			let url = contextPath +'/api/user/insert_user';
			ajaxProcess.submit(url,'POST',userObj,'#form_add_contact',function(data){
				if(data.result){
					$('#table_contact').DataTable().draw();
					$('#form_add_contact').modal('hide');
				}
			});
		}else{
			let url = contextPath +'/api/user/update_user';
			ajaxProcess.submit(url,'POST',userObj,'#form_add_contact',function(data){
				if(data.result){
					$('#table_contact').DataTable().draw();
					$('#form_add_contact').modal('hide');
				}
			});
		}
	}
	
	
});

$('#form_add_contact').on('hidden.bs.modal', function () {
	
	$('#contact_name_contract_modal').val("");
	$('#contact_tel_contract_modal').val("");
	$('#contact_email_contract_modal').val("");
	$('#contact_id_contract_modal').val("");
	$('#employee_id_contract_modal').val('');
	$('#userlan_contract_modal').val('');
	$('#password_contract_modal').val('');
	$('#description_contract_modal').val('');
	$('#userlan_contract_modal').prop('readonly', false);
	$('#user_group_selection').empty();
	//$('.password_require').show();
	//validator.resetForm();
	validateContractForm.resetForm();
	$($('#supplier_contract_form').find('.form-group')).each(function(i,e){		
		if($(this).hasClass('has-error')){
			$(this).removeClass('has-error');
		}
	});
	laodGroup();
});


$(document).on('click','a.editContact',function(){
	var data = $('#table_contact').DataTable().row( $(this).parents('tr') ).data();
	
	$('#contact_name_contract_modal').val(data.fullname);
	$('#contact_tel_contract_modal').val(data.telephone);
	$('#contact_email_contract_modal').val(data.email);
	$('#description_contract_modal').val(data.description);
	$('#contact_id_contract_modal').val(data.userId);
	$('#userlan_contract_modal').val(data.username);
	$('#userlan_contract_modal').prop('readonly', true);
	$('#employee_id_contract_modal').val(data.employeeId);
	$('#user_group_selection').val(data.userGroupId.userGroupId);
	$('#user_group_selection').trigger('change');
	/*if($('#contact_id_contract_modal').val()==''){
		$('.password_require').show();
	}else{
		$('.password_require').hide();
	}*/
	
	$('#form_add_contact').modal('show');
});

$(document).on('click','a.removeContact',function(){
	let data = $('#table_contact').DataTable().row( $(this).parents('tr') ).data();
	var userId = data.userId;
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการลบข้อมูลนี้ใช่หรือไม่?");
	ConfirmModal.confirmResult(function(isTrue){
		if (isTrue){ 
			let userObj = {
				userId : data.userId
			};
			let url = contextPath +'/api/user/user_delete';
			ajaxProcess.submit(url,'DELETE',userObj,'#portlet-contract',function(data){
				if(data.result){
					$('#table_contact').DataTable().draw();
				}
			});			
			
		}
	});
	
	return false;
	
});

$('#table_contact').on('show.bs.dropdown', function () {
	setTimeout(() => {
		$( ".table-scrollable" ).scrollLeft($('.table-scrollable').offset().left);
	}, 1);
	
});

function getContact(){
	var url = contextPath+'/api/supplier_user_mapping/datatable/contract_supplier';
	
	var supplierTable = $('#table_contact').DataTable({
		
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": url,
	        "type": 'POST',
	        contentType: "application/json",
	        beforeSend : function(arr, $form, options){
		    	BlockUi.blockPosition('#portlet-contract');
		    },
		    complete : function(){
		    	setTimeout(function(){
		    		BlockUi.unBlockPosition('#portlet-contract');
		    	},500);
		    	
		    },
	        data: function (d) {		        	
	        	let data =  $.extend( {}, d, {
	                "optionString": URI.decode($('#supplier_id').val().trim())
	            });
	        	
	        	return JSON.stringify(data);
	        }
	    },
	    "language": {
	        "url": "assets/global/plugins/datatables/Thai.json"
	    },
	    "columns": [
	    	{ "data": null, "width": "2px" },
	    	{ "data": "userId" },
	    	{ "data": "fullname","width": "100px"},
	    	{ "data": "description","width": "350px"},
	    	{ "data": "telephone", "width": "100px"},
	    	{ "data": "email","width": "100px"},
	    		    	
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
	        	"targets": [1,0],
	            "visible": false,
	            "searchable": false
	        },	        
	        {
	            "searchable": false,
	            "orderable": false,
	            "targets": [0,6]
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [2,3,4,5]
	        },
	        {
	            "targets": 6,
	            "orderable": false,
	            "render": function (data, type, row) {	            	
	            	let btnAction = "";
	            	btnAction += '<div class="row">';
	            	btnAction += '<div class="col-md-2">';
	            	btnAction += '<div class="btn-group">';
	            	btnAction += '<button class="btn btn-xs blue dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false" style="font-size : 12px;"> เลือก <i class="fa fa-angle-down"></i></button>';
	    			btnAction += '<ul class="dropdown-menu pull-left" role="menu" style="position: relative;">';
	    			/*if(userObjSession.userGroupId.userGroupId == '5'){
	    				btnAction += 	'<li><a href="javascript:void(0);" class = "editContact">';
		    			btnAction +=   		'<i class="icon-magnifier"></i> ดูรายละเอียด </a>';
		    			btnAction +=	'</li>';
	    			}else{
	    				btnAction += 	'<li><a href="javascript:void(0);" class = "editContact">';
		    			btnAction +=   		'<i class="icon-pencil"></i> แก้ไขข้อมูล </a>';
		    			btnAction +=	'</li>';
		    			btnAction +=	'<li><a href="javascript:void(0);" class = "removeContact">';
		    			btnAction +=		'<i class="icon-trash"></i> ลบข้อมูล </a>';
		    			btnAction +=	'</li>';
	    			}*/
	    			btnAction += 	'<li><a href="javascript:void(0);" class = "editContact">';
	    			btnAction +=   		'<i class="icon-pencil"></i> แก้ไขข้อมูล </a>';
	    			btnAction +=	'</li>';
	    			btnAction +=	'<li><a href="javascript:void(0);" class = "removeContact">';
	    			btnAction +=		'<i class="icon-trash"></i> ลบข้อมูล </a>';
	    			btnAction +=	'</li>';
	    			
	                btnAction += '</ul>';
	                btnAction +='</div>';
	                btnAction +='</div>';
	                btnAction +='</div>';
	               
	                return btnAction;
	            },
	            "width":"20px"
	        }
	    ],
	    "order": [],
	    "initComplete": function (settings, json) {	    	
	    	$('.dataTables_filter').remove();  	
	    	
	    }
	});
	
}



$('#btn_submit_location_produce').on('click',function(){
	
	if($('#location_produce_form').valid()){	
				
		let addressObj = null;
		if($('#checkbox_same_mainlocation').prop('checked')){
			let resultValidateLocal = validateInputAddress({
				subDistrictName : $('#sub_district').val().trim(),
				districtName : $('#district').val().trim(),
				province : $('#province').val().trim(),
				postCode : $('#postcode').val().trim()
			});
			
			if(!resultValidateLocal){
				customMessageDialog("error", "เกิดข้อผิดพลาด", "กรุณาใส่ข้อมูลที่อยู่ให้ถูกต้องก่อนการบันทึกข้อมูล", null);
				 $('#sub_district').typeahead('val','');
				 $('#district').typeahead('val','');
				 $('#province').typeahead('val','');
				 $('#postcode').typeahead('val','');
				 
				 $('#sub_district_id').val('');
				 $('#district_id').val('');
				 $('#province_id').val('');
				return false;
			}
			addressObj = {
				addressId : $('#address_id').val().trim(),
				postcode : $('#postcode').val().trim(),
				subDistrictId : {
					subDistrictId : $('#sub_district_id').val().trim()
				},
				districtId : {
					districtId : $('#district_id').val().trim()
				},
				provinceId : {
					provinceId : $('#province_id').val().trim()
				}
			};
		}else{
			let resultValidateLocal = validateInputAddress({
				subDistrictName : $('#sub_district_produce').val().trim(),
				districtName : $('#district_produce').val().trim(),
				province : $('#province_produce').val().trim(),
				postCode : $('#postcode_produce').val().trim()
			});
			
			if(!resultValidateLocal){
				customMessageDialog("error", "เกิดข้อผิดพลาด", "กรุณาใส่ข้อมูลที่อยู่ให้ถูกต้องก่อนการบันทึกข้อมูล", null);
				 $('#sub_district_produce').typeahead('val','');
				 $('#district_produce').typeahead('val','');
				 $('#province_produce').typeahead('val','');
				 $('#postcode_produce').typeahead('val','');
				 
				 $('#sub_district_id_produce').val('');
				 $('#district_id_produce').val('');
				 $('#province_id_produce').val('');
				return false;
			}
			addressObj = {
				addressId : $('#address_id_produce').val().trim(),
				postcode : $('#postcode_produce').val().trim(),
				address: $('#address_produce').val().trim(),
				subDistrictId : {
					subDistrictId : $('#sub_district_id_produce').val().trim(),
					name : $('#sub_district_produce').val().trim()
				},
				districtId : {
					districtId : $('#district_id_produce').val().trim(),
					name : $('#district_produce').val().trim()
				},
				provinceId : {
					provinceId : $('#province_id_produce').val().trim(),
					name : $('#province_produce').val().trim()
				}
			};
		}
		
		
		let supplierProductAddressMappingObj = {
			id : $('#location_Id_produce').val().trim(),
			supplierId : {
				supplierId : $('#supplierId').val().trim()
			},
			productTypeId : [
				{productTypeId : $('#product_type_selection').val().trim()}
			],
			addressId : addressObj,
			locationName : $('#location_name_produce').val().trim(),
			enable : 'Y'
		}
		console.log(supplierProductAddressMappingObj);
		if(supplierProductAddressMappingObj.id == ""){
			let url = contextPath+'/api/supplier_product_type_address_mapping/insert_address_prouduct_type_by_supplier';
			ajaxProcess.submit(url, 'POST', supplierProductAddressMappingObj, '#dialog_add_location_produce', (data)=>{
				if(data.result){					
					$('#dialog_add_location_produce').modal('hide');
					$('#table_product_mapping_supplier').DataTable().draw();
				}
			});
		}else{
			let url = contextPath+'/api/supplier_product_type_address_mapping/update_address_prouduct_type_by_supplier';
			ajaxProcess.submit(url, 'POST', supplierProductAddressMappingObj, '#dialog_add_location_produce', (data)=>{
				if(data.result){					
					$('#dialog_add_location_produce').modal('hide');
					$('#table_product_mapping_supplier').DataTable().draw();
				}
			});
		}
		
	}
});

$(document).on('click','.editLocationProduce',function(){
	
	let data = $('#table_product_mapping_supplier').DataTable().row( $(this).parents('tr') ).data();
	
	$(data.productTypeId).each(function(i,objProductType){
		$('#product_type_selection').val(objProductType.productTypeId).trigger('change');
	});
	$('#location_name_produce').val(data.locationName);
	$('#location_Id_produce').val(data.id);
	
	$('#address_produce').val(data.addressId.address);
	$('#address_id_produce').val(data.addressId.addressId);
	$('#sub_district_id_produce').val(data.addressId.subDistrictId.subDistrictId);
	$('#district_id_produce').val(data.addressId.districtId.districtId);
	$('#province_id_produce').val(data.addressId.provinceId.provinceId);
	
	$('#sub_district_produce').typeahead('val',data.addressId.subDistrictId.name);
	$('#district_produce').typeahead('val',data.addressId.districtId.name);
	$('#province_produce').typeahead('val',data.addressId.provinceId.name);
	$('#postcode_produce').typeahead('val',data.addressId.postcode);
	
	if(data.addressId.addressId != $('#address_id').val().trim()){
		$('#checkbox_same_mainlocation').prop('checked', false);
		$(".group-detail-address").show();
	}	
	$('#dialog_add_location_produce').modal('show');	
	
	
});

$(document).on('click','.removeLocationProduce',function(){
	let data = $('#table_product_mapping_supplier').DataTable().row( $(this).parents('tr') ).data();
	let addressId = data.addressId.addressId;
	let id = data.id;
	let supplierId = data.supplierId.supplierId;
	
	let supplierProductAddressMappingObj = {
		id : id,
		supplierId : {
			supplierId : supplierId
		},
		addressId : {addressId : addressId}
	};
	
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการลบข้อมูลนี้ใช่หรือไม่?");
	ConfirmModal.confirmResult(function(isTrue){
		if (isTrue){ 
			let url = contextPath+'/api/supplier_product_type_address_mapping/delete_supplier_location';
			ajaxProcess.submit(url, 'DELETE', supplierProductAddressMappingObj, '#portlet-location-produce', (data)=>{
				if(data.result){				
					$('#table_product_mapping_supplier').DataTable().draw();
				}
			});
		}
	});	
	
	
});

$('#dialog_add_location_produce').on('hidden.bs.modal', function(){
	
	$('#location_name_produce').val('');
	$('#checkbox_same_mainlocation').prop('checked', true);
	$('.group-detail-address').hide();	
	$('#product_type_selection').select2('val', $('#product_type_selection option:first').val());
	validateLocationProduce.resetForm();
	$($('#location_produce_form').find('.form-group')).each(function(i,e){		
		if($(this).hasClass('has-error')){
			$(this).removeClass('has-error');
		}
	});
	
});

function getLocationProduce(){
	
	let url = contextPath+'/api/supplier_product_type_address_mapping/datatable/supplier_produce_mapping_product_type_by_supplier';
	let tableproductmappingsupplier = $('#table_product_mapping_supplier').DataTable({
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": url,
	        "type": 'POST',
	        contentType: "application/json",
	        beforeSend : function(arr, $form, options){
		    	BlockUi.blockPosition('#portlet-location-produce');
	        	//BlockUi.blockPage();
		    },
		    complete : function(){
		    	setTimeout(function(){
		    		BlockUi.unBlockPosition('#portlet-location-produce');
		    	},500);
		    },
	        data: function (d) {
	        	
	        	let data =  $.extend( {}, d, {
	                "optionString": URI.decode($('#supplier_id').val().trim())
	            });
	        	
	        	return JSON.stringify(data);
	        }
	    },
	    "language": {
	        "url": "assets/global/plugins/datatables/Thai.json"
	    },
	    "columns": [
	    	{ "data": null, "width": "2px" },
	    	{ "data": "productTypeId", "name": "productTypeName",
	    		"render" : function(data, type, row){
	    			let nameProductType = "";
	    			$(data).each(function(i,v){
	    				nameProductType = v.name;
	    			});
	    			return nameProductType;
	    		} 
	    	},
	    	{ "data": "locationName","name":"locationName","width": "100px"}    		    	
	    ],
	    "columnDefs": [
	    	{
	            "searchable": false,
	            "orderable": false,
	            "targets": 0,
	            "render": function (data, type, full, meta) {
	                return meta.settings._iDisplayStart + meta.row + 1;
	            }
	        },{
	        	"targets": [1,2,3],
	            "searchable": false,
	            "orderable": false
	        },{
	            "targets": 3,
	            "orderable": false,
	            "render": function (data, type, row) {
	            	
	            	
	            	var btnAction = '<div class="btn-group">';
	            	btnAction += '<button class="btn btn-xs blue dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false" style="font-size:12px;"> เลือก <i class="fa fa-angle-down"></i></button>';
	    			btnAction += '<ul class="dropdown-menu pull-left" role="menu">';
	    			/*if(userObjSession.userGroupId.userGroupId == '5'){
	    				btnAction += 	'<li><a href="javascript:void(0);" class = "editLocationProduce">';
		    			btnAction +=   		'<i class="icon-magnifier"></i> ดูรายละเอียด </a>';
		    			btnAction +=	'</li>';
	    			}else{
	    				btnAction += 	'<li><a href="javascript:void(0);" class = "editLocationProduce">';
		    			btnAction +=   		'<i class="icon-pencil"></i> แก้ไขข้อมูล </a>';
		    			btnAction +=	'</li>';
		    			btnAction +=	'<li><a href="javascript:void(0);" class = "removeLocationProduce">';
		    			btnAction +=		'<i class="icon-trash"></i> ลบข้อมูล </a>';
		    			btnAction +=	'</li>';
	    			}	*/
	    			btnAction += 	'<li><a href="javascript:void(0);" class = "editLocationProduce">';
	    			btnAction +=   		'<i class="icon-pencil"></i> แก้ไขข้อมูล </a>';
	    			btnAction +=	'</li>';
	    			btnAction +=	'<li><a href="javascript:void(0);" class = "removeLocationProduce">';
	    			btnAction +=		'<i class="icon-trash"></i> ลบข้อมูล </a>';
	    			btnAction +=	'</li>';
	                btnAction += '</ul>';
	                btnAction +='</div>';
	               
	                return btnAction;
	            }
	        }
	    ],
	    "order": []
	});
}



$('#portlet-location-produce').on('click','#btn_search_produce',function(){
	searchLocation();
});


$('#portlet-location-produce').on('click','#btn_clear_produce',function(){
	$('#produce_product_type').val('');
	$('#produce_location').val('');
	$('#table_product_mapping_supplier').DataTable().columns(1).search('');
	$('#table_product_mapping_supplier').DataTable().columns(2).search('');
	$('#table_product_mapping_supplier').DataTable().draw();
});



$("#produce_product_type").keyup(function(event) {
    if (event.keyCode === 13) {
    	searchLocation();
    }
});

$("#produce_location").keyup(function(event) {
    if (event.keyCode === 13) {
    	searchLocation();
    }
});

function searchLocation(){
	
	$('#table_product_mapping_supplier').DataTable().columns(1).search($('#produce_product_type').val().trim());
	$('#table_product_mapping_supplier').DataTable().columns(2).search($('#produce_location').val().trim());
	$('#table_product_mapping_supplier').DataTable().draw();
}

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

}

$('#user_group_selection').on('change',function(){
	if(this.value == '4'){
		$(".form-description").slideDown("slow");
	}else{
		$(".form-description").slideUp();
		$("#description_dialog").val('');
	}
});

$('#checkbox_same_mainlocation').on('change', function(){
	if($(this).is(':checked')){
		$(".group-detail-address").slideUp();
	}else{
		$(".group-detail-address").slideDown("slow");
	}
	
});


function initialSelectionProductType(){
	let url = contextPath+'/api/product_type/product_type_list';	
	ajaxProcess.submit(url, 'GET', null, '#dialog_add_location_produce', (data)=>{
		if(data.result){
			let dataList = [];
			if(data.message != ""){
				try{
					let objJson = JSON.parse(data.message);
					$(objJson).each(function(i,val){
	    	    		dataList.push({
		    	    		id : val.productTypeId,
		    	    		text : val.name
		    	    	});   		
	    	    	});
					
				}catch (e) {
					customMessageDialog("error", "ตรวจสอบข้อมูล", e, null);
				}
			}
			
			$("#product_type_selection").select2({
	    		dropdownParent: $('#dialog_add_location_produce'),
	    		placeholder: "เลือกประเภทสินค้า",
	    		theme: "bootstrap",
	    		data: dataList
	    	});
			
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
	    	    	$(objJson).each(function(i,val){
	    	    		if(val.userGroupId == '3' || val.userGroupId == '4'){
	    	    			dataList.push({
		    	    			id : val.userGroupId,
		    	    			text : val.userGroupName
		    	    		});
	    	    		}	    	    		
	    	    	});
	    			
	    		}catch (e) {
	    			customMessageDialog("error", "ตรวจสอบข้อมูล", e, null);
				}
	    	}
	    	
	    	 
	    	
	    	$("#user_group_selection").select2({
	    		dropdownParent: $('#form_add_contact'),
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

$(document).on('click','.see-more-appoint',function(){
	let appointId = $(this).closest('tr').find('.appointId').text();
	let userIdCreateBy = $(this).closest('tr').find('.appointCreateBy-userId').text();
	let fullnameCreateBy = $(this).closest('tr').find('.appointCreateBy-fullname').text();
	$('#detailAppoint').val(URI.encode(JSON.stringify({
		appointId : appointId,
		appointCreateBy : {
			userId : userIdCreateBy,
			fullname : fullnameCreateBy
		}
	})));
	$('#passDataToForm').submit();
});


$('#status_greenCard').on('switchChange.bootstrapSwitch', function (e, state) {
	if(state){
		$(".expire-greencard").slideDown("slow");
	}else{
		$(".expire-greencard").slideUp("slow");
	}
}); 

function generateDatePicker(){
	dateTime.currentDateTime('yyyy/MM/dd HH:mm:ss',(currentTimeDate)=>{
		let currentDate = new Date();
		currentDate.setDate(currentTimeDate.getDate()+1);
		
		let expireDefault = new Date();
		expireDefault.setDate(currentTimeDate.getDate()+365)
		
		$('.datepicker-dialog').datepicker({
			language: "th",
			format: 'dd/mm/yyyy',
			orientation: "top auto",
			autoclose: true
		}).on('hide', function(e) {
			if($('#greenCardExpireHistory').val() != ""){
				if($('#greenCardExpire').val() == ""){
		        	$('#greenCardExpire').val($('#greenCardExpireHistory').val());
		        }
			}
	    });			
		$('.datepicker-dialog').datepicker('setDate',expireDefault);
		$('.datepicker-dialog').datepicker('setStartDate',currentDate);
	});
}

function loadAppoint(suuplierId){
	let url = contextPath+"/api/appoint/get_appoint_detail/"+JSON.stringify({
		supplierId : {supplierId : suuplierId}
	});
	ajaxProcess.submit(url, 'GET', null, '#apoint_supplier', (data)=>{
		if(data.result){
			
			let objAppoint = JSON.parse(data.message);
			$('#table_show_appoint').find('tbody').empty();
			$(objAppoint).each(function(i,value){
				
				$('#table_show_appoint').find('tbody')
					.append($('<tr>')
						.append($('<td style="width:35%">')
								.append(value.appointDate)
								)
						.append($('<td style="width:50%">')
								.append(value.title)
								)
						.append($('<td style="width:15%">')
								.append($('<a class="btn btn-xs default see-more-appoint"><i class="icon-magnifier"></i> ดูรายละเอียด </a>'))
								)
						.append($('<td style="width:10%" class="hide appointId">')
								.append(value.appointId)
								)
						.append($('<td style="width:10%" class="hide appointCreateBy-userId">')
								.append(value.appointCreateBy.userId)
								)
						.append($('<td style="width:10%" class="hide appointCreateBy-fullname">')
								.append(value.appointCreateBy.fullname)
								)
					);
			});
		}
	});
}

var loadSupplierStandardDocument = function(){
	return{
		init : function(supplierId){
			let supplierStandardDocumentObj = null, standardDoucumentObj = null;
			let url = contextPath+'/api/supplier_standard_document/supplier_standard_document_list/'+JSON.stringify({
				supplierId : {supplierId : supplierId}
			});
			ajaxProcess.submit(url, 'GET', null, '#supplierStandardDocument', (data)=>{
				if(data.result){
					supplierStandardDocumentObj = JSON.parse(data.message);
				}
			},{async : false});
			
			url = contextPath+'/api/standard_document/standard_documet_list';
			ajaxProcess.submit(url, 'GET', null, '#supplierStandardDocument', (data)=>{
				if(data.result){
					standardDoucumentObj = JSON.parse(data.message)
				}		
			},{async : false});
			loadSupplierStandardDocument.generateElement(supplierStandardDocumentObj, standardDoucumentObj);
		},
		generateElement : function(supplierStandardDocumentObj, standardDoucumentObj){
			if(standardDoucumentObj == null){
				return false;
			}else{
				
				$(standardDoucumentObj).each(function(i,v){
					let supplierStandardDocumentByStandardDocumentId = $.grep(supplierStandardDocumentObj, function(o){
						return o.standardDocumentId.standardDocumentId == v.standardDocumentId;
					});
					
					let optionPreviewObj = loadSupplierStandardDocument.getOptionPreview(supplierStandardDocumentByStandardDocumentId);
					
					let html = '<div class="form-group">';
						html += '<div class="row">';
							html += '<div class="col-md-12">';
								html += '<div class="caption font-green-sharp">';
									html += '<span class="bold" style="overflow-wrap: break-word;">'+v.standardDocumentName+'</span>'
								html += '</div>';
							html += '</div>';
						html += '</div>';
					html+= '</div>';
					
					html += '<div class="form-group">';
						html += '<div class="row">';
							html += '<div class="col-md-12 section-fileinput">';
								html += '<input class="standard-document-input file" type="file" name="standard-document-input[]" multiple data-preview-file-type="files/*" accept=".doc, .pdf, .docx">';
								html += '<input class="form-control optionPreview hide" type="text" name="optionPreview" value="'+URI.encode(JSON.stringify(optionPreviewObj))+'">';
							html += '</div>';
						html += '</div>';
					html += '</div>';
					$('#supplierStandardDocument').find('.portlet-body').append(html);
				});
				
				loadSupplierStandardDocument.initialFileInputExtension('.standard-document-input');
			}
		},
		getOptionPreview : function(supplierStandardDocumentByStandardDocumentId){
			let initialPreviewData = [];
			let initialPreviewConfigData = [];
			
			$(supplierStandardDocumentByStandardDocumentId).each(function(i,v){
				let typeFile = (v.supplierStandardDocumentLocation.split('/')[v.supplierStandardDocumentLocation.split('/').length-1]).trim().split('.')[(v.supplierStandardDocumentLocation.split('/')[v.supplierStandardDocumentLocation.split('/').length-1]).trim().split('.').length-1];
				let fileName = (v.supplierStandardDocumentLocation.split('/')[v.supplierStandardDocumentLocation.split('/').length-1]).trim()
				initialPreviewData.push(v.supplierStandardDocumentLocation);
				initialPreviewConfigData.push({
					type : typeFile.trim(),
					caption : fileName + "<br/><br/>วันหมดอายุ:"+v.supplierStandardDocumentExpireDate.split(" ")[0],
    				filename : fileName,					
					downloadUrl : v.supplierStandardDocumentLocation.trim(),
					key : v.supplierStandardDocumentId.trim(),
					frameAttr : {
						expireDate : v.supplierStandardDocumentExpireDate,
						supplierStandardDocumentId : v.supplierStandardDocumentId.trim()
					}
				})
			});
			
			let optionPreviewObj = {
				initialPreview : initialPreviewData,
				initialPreviewConfig : initialPreviewConfigData
			};
			return optionPreviewObj;
		},
		initialFileInputExtension : function(fileinputSelector){
			$(fileinputSelector).each(function(i,v){
				let optionPreviewData = $(this).closest('.section-fileinput').find('.optionPreview').val().trim();
				let optionFileInput = {
					theme: "explorer",
				    language: "th",
				    showClose: false,
				    showCaption: false,
				    showBrowse: false,
					showUpload: false,
					showRemove: false,
					autoOrientImage :true,
					maxFileSize: 3000,
				    minFileCount: 1,
				    maxFileCount: 100,
				    overwriteInitial: false,
				    previewFileIcon: '<i class="fa fa-file-o"></i>',
				    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',	
				    defaultPreviewContent: '<h5 class="text-muted">ไม่พบไฟล์เอกสารรับรองมาตรฐาน</h5>',
				    layoutTemplates: {main2: '{preview}'},
				    allowedFileExtensions: ["doc", "pdf", "docx"],
				    initialPreviewAsData: true,
				    preferIconicPreview: true, // this will force thumbnails to display icons for following file extensions
				    purifyHtml: true, // this by default purifies HTML data for preview	    
				    uploadUrl: contextPath+'/api/upload/file',
			        uploadAsync: false,
			        fileActionSettings: {
			        	showUpload: false,
			        	showDownload : true,
			        	showZoom : false,
			        	showDrag : false,
			        	showRemove : false		
			        },
				    previewFileIconSettings: { // configure your icon file extensions
				        'doc': '<i class="fa fa-file-word-o text-primary"></i>',
				        'pdf': '<i class="fa fa-file-pdf-o text-danger"></i>' 
				    },
				    previewFileExtSettings: { // configure the logic for determining icon file extensions
				        'doc': function(ext) {
				            return ext.match(/(doc|docx)$/i);
				        },
				        'pdf': function(ext) {
				            return ext.match(/(pdf)$/i);
				        }
				    }
				};
				if(optionPreviewData != "" && optionPreviewData != null){
					optionFileInput = $.extend(optionFileInput,JSON.parse(URI.decode(optionPreviewData)))
				}
				$(this).fileinput(optionFileInput);
			});
		}
	}
	
}();

var loadSupplierQuestionaireDocument = function(){

	return {
		init : function(supplierId){
			let supplierQuestionaireDocumentObj = null, questionaireDoucumentObj = null;
			
			let url = contextPath+'/api/questionaire_document/questionaire_document_list';
			ajaxProcess.submit(url, 'GET', null, '#supplierQuestionaireDocument', (data)=>{
				if(data.result){
					questionaireDoucumentObj = JSON.parse(data.message)
				}		
			},{async : false});
			
			url = contextPath+'/api/supplier_questionaire_document/supplier_questionaire_document_list/'+JSON.stringify({
				supplierId : {supplierId : supplierId}
			});
			ajaxProcess.submit(url, 'GET', null, '#supplierQuestionaireDocument', (data)=>{
				if(data.result){
					supplierQuestionaireDocumentObj = JSON.parse(data.message)
				}		
			},{async : false});
			
			loadSupplierQuestionaireDocument.generateElement(supplierQuestionaireDocumentObj, questionaireDoucumentObj);
		},
		generateElement : function(supplierQuestionaireDocumentObj, questionaireDoucumentObj){
			if(questionaireDoucumentObj == null){
				return false;
			}else{
				$(questionaireDoucumentObj).each(function(i,v){
					
					let supplierQuestionaireDocumentByQuestionaireDocumentId = $.grep(supplierQuestionaireDocumentObj, function(o){
						return o.questionaireDocumentId.questionaireDocumentId == v.questionaireDocumentId;
					});
					
					let optionPreviewObj = loadSupplierQuestionaireDocument.getOptionPreview(supplierQuestionaireDocumentByQuestionaireDocumentId);
					
					let html = '<div class="form-group">';
						html += '<div class="row">';
							html += '<div class="col-md-12">';
								html += '<div class="caption font-green-sharp">';
									html += '<span class="bold" style="overflow-wrap: break-word;">'+v.questionaireDocumentName+'</span>'
								html += '</div>';
							html += '</div>';
						html += '</div>';
					html+= '</div>';
					
					html += '<div class="form-group">';
						html += '<div class="row">';
							html += '<div class="col-md-12 section-supplier-questionaire-document-fileinput">';
								html += '<input class="supplier-questionaire-document-input file" type="file" name="supplier-questionaire-document-input[]" multiple data-preview-file-type="files/*" accept=".doc, .pdf, .docx">';
								html += '<input class="form-control optionPreview hide" type="text" name="optionPreview" value="'+URI.encode(JSON.stringify(optionPreviewObj))+'">';
							html += '</div>';
						html += '</div>';
					html += '</div>';
					$('#supplierQuestionaireDocument').find('.portlet-body').append(html);
				});
				
				loadSupplierQuestionaireDocument.initialFileInputExtension('.supplier-questionaire-document-input');
			}
		},
		getOptionPreview : function(supplierQuestionaireDocumentByQuestionaireDocumentId){
			let initialPreviewData = [];
			let initialPreviewConfigData = [];
			
			$(supplierQuestionaireDocumentByQuestionaireDocumentId).each(function(i,v){
				let typeFile = (v.supplierQuestionaireDocumentLocation.split('/')[v.supplierQuestionaireDocumentLocation.split('/').length-1]).trim().split('.')[(v.supplierQuestionaireDocumentLocation.split('/')[v.supplierQuestionaireDocumentLocation.split('/').length-1]).trim().split('.').length-1];
				let fileName = (v.supplierQuestionaireDocumentLocation.split('/')[v.supplierQuestionaireDocumentLocation.split('/').length-1]).trim()
				initialPreviewData.push(v.supplierQuestionaireDocumentLocation);
				initialPreviewConfigData.push({
					type : typeFile.trim(),
					caption : fileName,
    				filename : fileName,					
					downloadUrl : v.supplierQuestionaireDocumentLocation.trim(),
					key : v.supplierQuestionaireDocumentId.trim()
				})
			});
			
			let optionPreviewObj = {
				initialPreview : initialPreviewData,
				initialPreviewConfig : initialPreviewConfigData
			};
			return optionPreviewObj;
		},
		initialFileInputExtension : function(fileinputSelector){
			$(fileinputSelector).each(function(i,v){
				let optionPreviewData = $(this).closest('.section-supplier-questionaire-document-fileinput').find('.optionPreview').val().trim();
				let optionFileInput = {
					theme: "explorer",
				    language: "th",
				    showClose: false,
				    showCaption: false,
				    showBrowse: false,
					showUpload: false,
					showRemove: false,
					autoOrientImage :true,
					maxFileSize: 3000,
				    minFileCount: 1,
				    maxFileCount: 100,
				    overwriteInitial: false,
				    previewFileIcon: '<i class="fa fa-file-o"></i>',
				    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',	
				    defaultPreviewContent: '<h5 class="text-muted">ไม่พบไฟล์แบบสอบถาม</h5>',
				    layoutTemplates: {main2: '{preview}'},
				    allowedFileExtensions: ["doc", "pdf", "docx"],
				    initialPreviewAsData: true,
				    preferIconicPreview: true, // this will force thumbnails to display icons for following file extensions
				    purifyHtml: true, // this by default purifies HTML data for preview	    
				    uploadUrl: contextPath+'/api/upload/file',
			        uploadAsync: false,
			        fileActionSettings: {
			        	showUpload: false,
			        	showDownload : true,
			        	showZoom : false,
			        	showDrag : false,
			        	showRemove : false		
			        },
				    previewFileIconSettings: { // configure your icon file extensions
				        'doc': '<i class="fa fa-file-word-o text-primary"></i>',
				        'pdf': '<i class="fa fa-file-pdf-o text-danger"></i>' 
				    },
				    previewFileExtSettings: { // configure the logic for determining icon file extensions
				        'doc': function(ext) {
				            return ext.match(/(doc|docx)$/i);
				        },
				        'pdf': function(ext) {
				            return ext.match(/(pdf)$/i);
				        }
				    }
				};
				if(optionPreviewData != "" && optionPreviewData != null){
					optionFileInput = $.extend(optionFileInput,JSON.parse(URI.decode(optionPreviewData)))
				}
				$(this).fileinput(optionFileInput);
				
			});
		}
	}
}();

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
					BlockUi.blockPosition('#portlet-supplierDetail');
			    },
			    complete : function(){
			    	setTimeout(function() {
			    		BlockUi.unBlockPosition('#portlet-supplierDetail');  
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
			
			$("#sub_district").typeahead({
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
		    	
		    	$("#district").typeahead('val',obj.districtName);
		    	$("#province").typeahead('val',obj.provinceName);
		    	$("#postcode").typeahead('val',obj.postcode);		    	
		    	
		    	$('#sub_district_id').val(obj.subDistrictId);
		    	$('#district_id').val(obj.districtId);
		    	$('#province_id').val(obj.provinceId);
		    });
			
			$("#district").typeahead({
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
		    	$("#sub_district").typeahead('val',obj.subDistrictName);
		    	$("#province").typeahead('val',obj.provinceName);
		    	$("#postcode").typeahead('val',obj.postcode);
		    	
		    	$('#sub_district_id').val(obj.subDistrictId);
		    	$('#district_id').val(obj.districtId);
		    	$('#province_id').val(obj.provinceId);
		    });
			
			$("#province").typeahead({
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
		    	$("#sub_district").typeahead('val',obj.subDistrictName);
		    	$("#district").typeahead('val',obj.districtName);
		    	$("#postcode").typeahead('val',obj.postcode);
		    	
		    	$('#sub_district_id').val(obj.subDistrictId);
		    	$('#district_id').val(obj.districtId);
		    	$('#province_id').val(obj.provinceId);
		    });
			
			$("#postcode").typeahead({
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
		    	$("#sub_district").typeahead('val',obj.subDistrictName);
		    	$("#district").typeahead('val',obj.districtName);
		    	$("#province").typeahead('val',obj.provinceName);
		    	
		    	$('#sub_district_id').val(obj.subDistrictId);
		    	$('#district_id').val(obj.districtId);
		    	$('#province_id').val(obj.provinceId);
		    });
			
			
			$("#sub_district_produce").typeahead({
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
		    	
		    	$("#district_produce").typeahead('val',obj.districtName);
		    	$("#province_produce").typeahead('val',obj.provinceName);
		    	$("#postcode_produce").typeahead('val',obj.postcode);		    	
		    	
		    	$('#sub_district_id_produce').val(obj.subDistrictId);
		    	$('#district_id_produce').val(obj.districtId);
		    	$('#province_id_produce').val(obj.provinceId);
		    });
			
			$("#district_produce").typeahead({
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
		    	$("#sub_district_produce").typeahead('val',obj.subDistrictName);
		    	$("#province_produce").typeahead('val',obj.provinceName);
		    	$("#postcode_produce").typeahead('val',obj.postcode);
		    	
		    	$('#sub_district_id_produce').val(obj.subDistrictId);
		    	$('#district_id_produce').val(obj.districtId);
		    	$('#province_id_produce').val(obj.provinceId);
		    });
			
			$("#province_produce").typeahead({
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
		    	$("#sub_district_produce").typeahead('val',obj.subDistrictName);
		    	$("#district_produce").typeahead('val',obj.districtName);
		    	$("#postcode_produce").typeahead('val',obj.postcode);
		    	
		    	$('#sub_district_id_produce').val(obj.subDistrictId);
		    	$('#district_id_produce').val(obj.districtId);
		    	$('#province_id_produce').val(obj.provinceId);
		    });
			
			$("#postcode_produce").typeahead({
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
		    	$("#sub_district_produce").typeahead('val',obj.subDistrictName);
		    	$("#district_produce").typeahead('val',obj.districtName);
		    	$("#province_produce").typeahead('val',obj.provinceName);
		    	
		    	$('#sub_district_id_produce').val(obj.subDistrictId);
		    	$('#district_id_produce').val(obj.districtId);
		    	$('#province_id_produce').val(obj.provinceId);
		    });
		}
	}
}();

