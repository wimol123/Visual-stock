var filesNameNew = "";
var filesArray = [];
var validateForm = null;
var validateLocationProduce = null;
$(function(){
	$('.group-detail-address').hide();
	
	let objectValidate = {
			element : '#supplier_form',
			require : ['supplier_company','contact_name','address','sub_district','district','province','postcode','userlan_dialog','password_dialog','supplier_telephone', 'auditRound'],
			message : ["กรุณาใส่ชื่อบริษัท","กรุณาใส่ชื่อผู้ติดต่อ","กรุณาใส่ที่อยู่","กรุณาใส่ตำบล/แขวง","กรุณาใส่อำเภอ/เขต","กรุณาใส่จังหวัด","กรุณาใส่รหัสไปรษณีย์","กรุณาใส่ชื่อเข้าใช้งาน","กรุณาใส่รหัสผ่าน","กรุณาใส่เบอร์โทรศัพท์", "กรุณาใส่รอบเข้าตรวจ"],
			maxLength : [100, 100, 200, 200, 200, 200, null, 50, 100, 30, 11]
		};
	validateForm = validate.init(objectValidate);
	
	objectValidate = {
			element : '#location_produce_form',
			require : ['product_type_selection','location_name_produce','address_produce','sub_district_produce','district_produce','province_produce','postcode_produce'],
			message : ["กรุณาเลือกประเภทสินค้า","กรุณาใส่ชื่อสถานที่ผลิต","กรุณาใส่ที่อยู่","กรุณาใส่ตำบล/แขวง","กรุณาใส่อำเภอ/เขต","กรุณาใส่จังหวัด","กรุณาใส่รหัสไปรษณีย์"],
			maxLength : [null, 100, 200, 200, 200, 200]
		};
	validateLocationProduce = validate.init(objectValidate);
	
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
	
	$('#auditRound').rules("add", {
		number: true,
		messages: {
			number : "กรุณาใส่ข้อมูลเป็นตัวเลขเท่านั้น"
		}
	});

	let url = contextPath +"/api/user/get_session_user";	
	$.ajax({
		url : url,
		type : 'GET',
		contentType: "application/json",
		success : function(data){
			let userObj = JSON.parse(data.message);
			if (userObj.userGroupId.userGroupId == '3'
					|| userObj.userGroupId.userGroupId == '4'
					|| userObj.userGroupId.userGroupId == '8') {
				loadDataSupplier();
			} else {
				window.location.href = 'supplier.jsp';
			}
		}
	});
	
	loadLocation.processFunction(function(data){
		locationRawList = data;
		loadLocation.callBack(data);
	});
	initialSelectionProductType();
	
	generateFileInput();
	
	
});

$('#userlan_dialog').on("keypress", function(e){
	return validateInnerText.valid(/[a-zA-Z0-9 ]/g, e)
});

$('#btn_submitsupplier').on('click', function(){
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
		
		ConfirmModal.setupModal("warning","ยืนยัน", "ยืนยันการแก้ไขข้อมูลนี้ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){ 
				let url = contextPath+'/api/supplier/supplier_update';
				ajaxProcess.submit(url,'POST',objSupplier,'#portlet-supplierDetail',null);
			}
		});
			
			
		
		
		
	}else{
		return false;
	}
});

function loadDataSupplier(){
	$("#sub_district").typeahead();
	$("#district").typeahead();
	$("#province").typeahead();
	$("#postcode").typeahead();
	bootstrapSwitch.init();	
	let url = contextPath+'/api/supplier/supplier_detail/'+JSON.stringify({supplierId : ''});			
	ajaxProcess.submit(url, 'GET', null, "#portlet-supplierDetail", function(data){		
		let supplierDetailObj = JSON.parse(data.message)[0];
		if(!($.isEmptyObject(supplierDetailObj.addressId))){
			
			let jsonString = JSON.stringify({supplierId : supplierDetailObj.supplierId});
    		let url = contextPath+'/api/download/logo_supplier/'+jsonString;
    		$.ajax({
    			url : url,
    			method : 'GET',
    			success : function(data){
    				if(data.result){
    					let urlImg = data.message;
    					$('#logo_supplier').attr("src",urlImg); 
    				}else{
    					$('#logo_supplier').removeAttr( "style" )
    					$('#logo_supplier').attr("src",contextPath+'/assets/images/picture-attachment.png'); 
    				}			
    				
    			}
    		});
			
			$("#supplierId").val(supplierDetailObj.supplierId);
			$("#supplier_Code").val(supplierDetailObj.supplierCode);
			$("#supplier_company").val(supplierDetailObj.supplierCompany);
			$("#address").val(supplierDetailObj.addressId.address);
			$("#sub_district").typeahead('val',supplierDetailObj.addressId.subDistrictId.name);
			$("#district").typeahead('val',supplierDetailObj.addressId.districtId.name);
			$("#province").typeahead('val',supplierDetailObj.addressId.provinceId.name);
			$("#postcode").typeahead('val',supplierDetailObj.addressId.postcode);
			
			$("#address_id").val(supplierDetailObj.addressId.addressId);
			$("#sub_district_id").val(supplierDetailObj.addressId.subDistrictId.subDistrictId);
			$("#district_id").val(supplierDetailObj.addressId.districtId.districtId);
			$("#province_id").val(supplierDetailObj.addressId.provinceId.provinceId);
			
			if(supplierDetailObj.approval == "N")
				$('#status_avl').bootstrapSwitch('state',false);
			if(supplierDetailObj.isGreenCard == "N"){
				$('#status_greenCard').bootstrapSwitch('state',false);
				$('.expire-greencard').hide();
				supplierDetailObj.greenCardExpireDate = "";
			}
				
			if(supplierDetailObj.enable == "N")
				$('#status_supplier').bootstrapSwitch('state',false);
			
			$('#supplier_form').find("#contact_name").parent().parent().addClass('hide');
			$('#supplier_form').find("#userlan_dialog").parent().addClass('hide');
			$('#supplier_form').find("#supplier_email").parent().addClass('hide');
			$('#supplier_form').find("#supplier_telephone").parent().addClass('hide');
			$("#logoPath").val(supplierDetailObj.logo);
			let logoString = URI.encode(supplierDetailObj.logo).replace(/%5C/g, '%5C%5C');
			let objRequest = {
				logo : logoString
			};
			//$( "#logo" ).rules( "remove" );
			//functionLoadLogo(objRequest);
			
			$('#auditRound').val(supplierDetailObj.auditRound);
			if(supplierDetailObj.greenCardExpireDate != ""){
				$('#greenCardExpire').val(supplierDetailObj.greenCardExpireDate.split(" ")[0].trim());								
				$('#greenCardExpireHistory').val(supplierDetailObj.greenCardExpireDate.split(" ")[0].trim());
			}
			
		}
		$('#status_greenCard').bootstrapSwitch('readonly',true);
		$('#status_supplier').bootstrapSwitch('readonly',true);
		$('#status_avl').bootstrapSwitch('readonly',true);
		$('#auditRound').prop('readonly',true);
		$('#supplier_Code').prop('readonly',true);
		$('#supplier_company').prop('readonly',true);
		getLocationProduce();
	});	
	
}


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
//	    removeLabel: "ยกเลิกการเลือกรูปภาพนี้",
	    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
//	    removeTitle: "ยกเลิกการเลือกรูปภาพนี้",
	    elErrorContainer: '#error-choose-file',
	    msgErrorClass: 'alert alert-block alert-danger',
	    //defaultPreviewContent: '<img src="'+url+'" alt="Your Logo"><h6 class="text-muted">คลิกเพื่อเลือกรูปภาพ</h6>',
	    //defaultPreviewContent: '<img src="'+contextPath+'/assets/images/picture-attachment.png" alt="Your Logo"><h6 class="text-muted">คลิกเพื่อเลือกรูปภาพ</h6>',
	    defaultPreviewContent: '<img id="logo_supplier" style="width:100%" alt="Your Logo"><h6 class="text-muted">คลิกเพื่อเปลี่ยนรูปภาพ</h6>',
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
		    	if($('#supplierId').val() != ''){
		    		let jsonString = JSON.stringify({supplierId : $('#supplierId').val()});
		    		let url = contextPath+'/api/download/logo_supplier/'+jsonString;
		    		$.ajax({
		    			url : url,
		    			method : 'GET',
		    			success : function(data){
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
	        	        	
	        	return JSON.stringify(d);
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
	    "order": [],
	    "initComplete": function (settings, json) {	    	
	    	$('.dataTables_filter').remove();  	
	    	
	    }
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

$('#user_group_selection').on('change',function() {
	if (this.value == '4' || this.value == '8') {
		$(".form-description").slideDown("slow");
	} else {
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

