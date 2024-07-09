var passwordUserEmail = null;
$(function(){
	chSession()
	bootstrapSwitch.init();
	let url = contextPath+'/api/system_configuration/system_configuration_by_key/'+JSON.stringify({	systemKey : ''});
	ajaxProcess.submit(url, 'GET', null, '#portlet-config-ldap,#portlet-config-system,#portlet-config-email,#portlet-config-emergancy', (data)=>{
		
		let ConfigurationObj = JSON.parse(data.message);
		
		$(ConfigurationObj).each(function(i,v){
			
			if(v.systemKey == "system.ldap.domain"){
				$('#domain_ldap').val(v.systemValue);
			}else if(v.systemKey == "system.ldap.password"){
				$('#password_ldap').val(v.systemValue);
			}else if(v.systemKey == "system.ldap.search_base"){
				$('#SearchBase_ldap').val(v.systemValue);
			}else if(v.systemKey == "system.ldap.username"){
				$('#username_ldap').val(v.systemValue);
			}else if(v.systemKey == "system.ldap.server"){
				$('#server_ldap').val(v.systemValue);
			}else if(v.systemKey == "system.secure.salted_string"){
				$('#SaltedString').val(v.systemValue);
			}else if(v.systemKey == "system.mail.enable_authen"){
				$('#status_enable_authentification').bootstrapSwitch('state', v.systemValue === 'true');
			}else if(v.systemKey == "system.mail.enable_start_TLS"){
				$('#status_enable_start_TLS').bootstrapSwitch('state', v.systemValue === 'true');
			}else if(v.systemKey == "system.mail.footer_admin_mail"){
				$('#mail_admin').val(v.systemValue);
			}else if(v.systemKey == "system.mail.footer_telephone"){
				$('#telephone').val(v.systemValue);
			}else if(v.systemKey == "system.mail.footer_url_web"){
				$('#url_system').val(v.systemValue);
			}else if(v.systemKey == "system.emer.footer_admin_mail"){
				$('#mail_emergency').val(v.systemValue);
			}else if(v.systemKey == "system.emer.footer_telephone"){
				$('#telephone_emergency').val(v.systemValue);
			}else if(v.systemKey == "system.emer.footer_url_web"){
				$('#url_system_emergency').val(v.systemValue);
			}else if(v.systemKey == "system.mail.path_logo_cpram"){
				$('#path_logo_cpram').val(v.systemValue);
			}else if(v.systemKey == "system.mail.port_SMTP"){
				$('#smtp_mail_port').val(v.systemValue);
			}else if(v.systemKey == "system.mail.sender_mail"){
				$('#smtp_mail_sender').val(v.systemValue);
			}else if(v.systemKey == 'system.mail.sender_password'){
				passwordUserEmail = v.systemValue;
				//$('#smtp_mail_password_sender').val(v.systemValue);
			}else if(v.systemKey == 'system.mail.server_SMTP'){
				$('#smtp_mail_server').val(v.systemValue);
			}else if(v.systemKey == 'system.path_file.path_supplier_logo'){
				$('#path_supplier_logo').val(v.systemValue);
			}else if(v.systemKey == 'system.path_file.path_temp'){
				$('#path_temp').val(v.systemValue);
			}else if(v.systemKey == 'system.datetime.supplier_standard_document_expire_day'){
				$('#supplier_standard_document_expire_day_default').val(v.systemValue);
			}
		});
		
	});
	
	
	let objectValidate = {
		element : '#config-ldap-form',
		require : ['domain_ldap','SearchBase_ldap','server_ldap','username_ldap','password_ldap'],
		message : ["กรุณาใส่โดเมน ldap","กรุณาใส่ Search Base ldap","กรุณาใส่ที่อยู่เซิร์ฟเวอร์ ldap","กรุณาใส่ Username ของ LDAP", "กรุณาใส่ Password ของ LDAP"],
		maxLength : [200, 200, 200, 200,]
	}
	validate.init(objectValidate);
	
	objectValidate = {
		element : '#config-system-form',
		require : ['path_temp','path_supplier_logo','path_logo_cpram', 'supplier_standard_document_expire_day_default'],
		message : ["กรุณาใส่ที่อยู่ของโฟลเดอร์ Temp","กรุณาใส่ ที่อยู่ของโฟลเดอร์โลโกซัพพลายเออร์","กรุณาใส่ที่อยู่ของโฟลเดอร์โลโกซีพีแรม", "กรุณาใส่ค่าเริ่มต้นจำนวนวันหมดอายุของเอกสาร"],
		maxLength : [200, 200, 200]
	}
	validate.init(objectValidate);
	
	objectValidate = {
		element : '#config-email-form',
		require : ['smtp_mail_server','smtp_mail_port','smtp_mail_sender','mail_admin','telephone','url_system'],
		message : ["กรุณาใส่ที่อยู่ของ Server SMTP","กรุณาใส่หมายเลขพอร์ต SMTP","กรุณาใส่อีเมล์ผู้ส่ง","กรุณาใส่อีเมล์ของผู้ดูแลระบบ","กรุณาใส่เบอร์โทร","กรุณาใส่ที่อยู่เว็บไซต์สำหรับการเข้าใช้งานระบบ"],
		maxLength : [200, 200, 200, 200, 200, 200]
	}
	validate.init(objectValidate);
	
	objectValidate = {
			element : '#config-emergency-form',
			require : ['mail_emergency','telephone_emergency','url_system_emergency'],
			message : ["กรุณาใส่อีเมล์ของผู้ดูแลระบบ","กรุณาใส่เบอร์โทร","กรุณาใส่ที่อยู่เว็บไซต์สำหรับการเข้าใช้งานระบบ"],
			maxLength : [200, 200, 200]
		}
		validate.init(objectValidate);
	
	objectValidate = {
		element : '#smtp_mail_port',
		rules : [{number : true}],
		message : ["กรุณาใส่จำนวนเต็มเท่านนั้น"]
	}
	validate.add(objectValidate)
	
	objectValidate = {
		element : '#supplier_standard_document_expire_day_default',
		rules : [{number : true}],
		message : ["กรุณาใส่จำนวนเต็มเท่านนั้น"]
	}
	validate.add(objectValidate)
	
	objectValidate = {
		element : '#smtp_mail_sender',
		rules : [{validateEmail : true}],
		message : [null]
	}
	validate.add(objectValidate)
	/*$( "#smtp_mail_sender" ).rules( "add", {
		validateEmail : true
	});*/
		
	
});

function chSession() {
	let url = contextPath +"/api/user/get_permission_menu";
	$.ajax({
		url : url,
		type : 'POST',
		contentType: "application/json",
		data : "setting.jsp",
		success : function(data){
			if(!data.result){
				window.location = "/auditsupplier/home.jsp";
			}
		}
	});
}

$('#smtp_mail_port').on("keypress", function(e){
	return validateInnerText.valid(/[0-9]/g, e)
});

$('#smtp_mail_sender').on("keyup", function(){
	var validateMail = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if (validateMail.test(this.value)) {
		$('#smtp_mail_sender').valid()
    }
    else {
    	$('#smtp_mail_sender').valid()                               
    }
	
});


$('#reload-config').on('click',function(){
	ConfirmModal.setupModal("warning","ยืนยัน", "ยืนยันการโหลดข้อมูลการตั้งค่าใหม่ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if(isTrue){ 
			let url = contextPath+'/api/system_configuration/reload_system_configuration'
			ajaxProcess.submit(url, 'POST', null, null, (data)=>{});
		}
	});
});


$('#btn_save_config_ldap').on('click', function(){
	if($('#config-ldap-form').valid()){
		
		ConfirmModal.setupModal("warning","ยืนยัน", "ยืนยันการบันทึกข้อมูลการตั้งค่านี้ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){ 
				let configurationValueObj = {
					ldapDomain : $('#domain_ldap').val().trim(),
					ldapSever : $('#server_ldap').val().trim(),
					ldapSearchBase : $('#SearchBase_ldap').val().trim(),
					ldapPassword : $('#password_ldap').val().trim(),
					ldapUsername : $('#username_ldap').val().trim()
				};
				executeUpdateSystemConfig(configurationValueObj);
			}
		});
	}
});

$('#btn_save_config_system').on('click', function(){
	if($('#config-system-form').valid()){
		ConfirmModal.setupModal("warning","ยืนยัน", "ยืนยันการบันทึกข้อมูลการตั้งค่านี้ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){ 
				let configurationValueObj = {
					pathTemp : $('#path_temp').val().trim(),
					pathSupplierLogo : $('#path_supplier_logo').val().trim(),
					pathLogoCPRAM : $('#path_logo_cpram').val().trim(),
					dateTimeSupplierStandardDocumentExpireDay : $('#supplier_standard_document_expire_day_default').val().trim()
				};
				executeUpdateSystemConfig(configurationValueObj);
			}
		});
	}
});

$('#btn_save_config_mail').on('click', function(){
	if($('#config-email-form').valid()){
		ConfirmModal.setupModal("warning","ยืนยัน", "ยืนยันการบันทึกข้อมูลการตั้งค่านี้ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){ 
				let password = "";
				if($('#smtp_mail_password_sender').val().trim() == ''){
					password = passwordUserEmail;
				}else{
					password = $('#smtp_mail_password_sender').val().trim();
				}
				
				let statusEnanbleAuthen = 'false';
				if($('#status_enable_authentification').prop('checked')){
					statusEnanbleAuthen = 'true';
				}
				let statusEnanbleStartTLS = 'false';
				if($('#status_enable_start_TLS').prop('checked')){
					statusEnanbleStartTLS = 'true';
				}
				let configurationValueObj = {
					serverSMTP : $('#smtp_mail_server').val().trim(),
					portSMTP : $('#smtp_mail_port').val().trim(),
					senderMail : $('#smtp_mail_sender').val().trim(),
					senderPassword : password,
					enanbleAuthen : statusEnanbleAuthen,
					enableStartTLS : statusEnanbleStartTLS,
					mailFooterAdminMail : $('#mail_admin').val().trim(),
					mailFooterTelephone : $('#telephone').val().trim(),
					mailFooterUrlWeb : $('#url_system').val().trim(),
				};
				executeUpdateSystemConfig(configurationValueObj);
			}
		});
	}
});

$('#btn_save_config_emergency').on('click', function(){
	if($('#config-emergency-form').valid()){
		ConfirmModal.setupModal("warning","ยืนยัน", "ยืนยันการบันทึกข้อมูลการตั้งค่านี้ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){ 
				let configurationValueObj = {
					emerFooterMail : $('#mail_emergency').val().trim(),
					emerFooterTelephone : $('#telephone_emergency').val().trim(),
					emerFooterUrlWeb : $('#url_system_emergency').val().trim(),
				};
				executeUpdateSystemConfig(configurationValueObj);
			}
		});
	}
});


function executeUpdateSystemConfig(objConfig){
	
	let url = contextPath +'/api/system_configuration/update_system_configuration';
	ajaxProcess.submit(url, 'POST', objConfig, null, (data)=>{});
}