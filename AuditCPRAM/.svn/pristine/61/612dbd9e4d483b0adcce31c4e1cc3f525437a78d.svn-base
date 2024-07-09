$(function(){
	init.background();
	BlockUi.init();
	init.session();
	let objValidate = {
		element : '#frm_forgetpass',
		require : ['email', 'username'],
		message : ['กรุณาใส่อีเมล์', 'กรุณาใส่ชื่อเข้าใช้งาน'],
		maxLength : [50, 50]
	};
	validate.init(objValidate)
	let obj = {
		element : '#txt_email',
		rules : [{validateEmail : true}],
		message : [null],
	}
	validate.add(obj);
});

$('#txt_username').on("keypress", function(e){
	return validateInnerText.valid(/[a-zA-Z0-9 ]/g, e)
});

$('#txt_email').on("keyup", function(){
	var validateMail = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if (validateMail.test(this.value)) {
		$('#txt_email').valid();		
    }
    else {
    	$('#txt_email').valid()                               
    }	
});

keyUp.enter('#txt_email', ()=>{
	$("#btn_regenpassword").click();
});
keyUp.enter('#txt_username', ()=>{
	$("#btn_regenpassword").click();
});

$('#btn_regenpassword').on('click', function(){
	let msg ="";
	if ($('#txt_email').val().trim() ==''){
		msg +="- Email<br />";
	}
	if ($('#txt_username').val().trim() ==''){
		msg +="- Username<br />";
	}
	if (msg!=''){
		msg ="ตรวจสอบข้อมูล<br />"+msg;
		customMessageDialog("info", "ตรวจสอบข้อมูล", msg, null);
		
		return false;
	}else{
		if(!isValidEmail($('#txt_email').val().trim())){
			customMessageDialog("info", "ตรวจสอบข้อมูล", "ตรวจสอบข้อมูล<br /> - กรุณาใส่ Email ให้ถูกต้อง", null);
			return false;
		}
	}
	
	if(!$('#frm_forgetpass').valid()){
		return false;
	}
	
	let objUser = {
		email : $('#txt_email').val().trim(),
		username : $('#txt_username').val().trim()
	};
	
	let url = contextPath + "/api/user/user_change_password";
	$.ajax({
		url : url,
		type : 'POST',
		data : JSON.stringify(objUser),
		contentType: 'application/json',
		beforeSend : function(arr, $form, options){
			BlockUi.blockPage();
		},
		complete : function(){
			setTimeout(function(){
				BlockUi.unBlockPage();
			},500)
		},
		success : function(data) {
			if(data.result){
				$('#dialog_success_genPassword').modal('show');
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
	});
	
});

var init = function(){
	return{
		background : function(){
			 $.backstretch([
                 "assets/pages/img/bg_cpram.jpg"
		      ], {
		        fade: 1000,
		        duration: 8000
		  	}
			);
		},
		session : function(){
			let url = contextPath+'/api/user/get_session_user';
			ajaxProcess.submit(url, 'GET', null, '', (data)=>{
				BlockUi.blockPage();
				if(data.result){
					try{
						
						let userObj = JSON.parse(data.message);
						if(userObj.employeeId != null && userObj.fullname != null && userObj.userId != null && userObj.userMenu != null && userObj.userGroupId.userGroupId != null){
			    			 setTimeout(function(){
			    				 window.location.href= 'index.jsp';
			    			 },500);			    			  
						}else{
			    			BlockUi.unBlockPage();
			    		}	
						
					}catch(e){
						BlockUi.unBlockPage();
					}
				}else{
					BlockUi.unBlockPage();
				}
			});
		}
	}
}();