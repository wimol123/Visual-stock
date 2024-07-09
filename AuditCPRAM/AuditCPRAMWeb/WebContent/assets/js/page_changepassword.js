$(function(){	
	init.background();
	BlockUi.init();
	init.session();
	let objValidate = {
		element : '#frm_changepass',
		require : ['newpassword', 'confirmpassword'],
		message : ['กรุณาใส่ Password', 'กรุณาใส่ Password'],
		maxLength : [50, 50]
	};
	validate.init(objValidate)
	let obj = {
		element : '#txt_new_password',
		rules : [{pwcheck1n1c : true}],
		message : ["กรุณาตรวจสอบความถูกต้อง"],
	}
	validate.add(obj);
	
	$.validator.addMethod("checkPassword", function(value) {    
	        return $('#txt_new_password').val() == value;
	    },"ระบุให้ตรงกับ Password ใหม่ที่ตั้งไว้");
		
	$( "#txt_confirm_password" ).rules( "add", {
		checkPassword: true
	});
	
});

$('#txt_new_password').on("keyup", function(){
	var validatePassword = /^(?=.*\d)(?=.*[a-z]).{8}$/;
	if (validatePassword.test(this.value)) {
		$('#txt_new_password').valid();
    }
    else {
    	$('#txt_new_password').valid()                               
    }	
    checkConfirmPassword();
});

$('#txt_confirm_password').on("keyup", function(){
	checkConfirmPassword();
})

function checkConfirmPassword(){
	if($('#txt_confirm_password').val() == $('#txt_new_password').val()){
		$('#txt_confirm_password').valid();
	}else {
		$('#txt_confirm_password').valid();
	}
}

$('#btn_changepassword').on('click', function(){
	
	console.log($('#txt_new_password'));
	
	let msg ="";
	if ($('#txt_new_password').val().trim() =='' || !$('#txt_new_password').valid()){
		msg +="- New Password<br />";
	}
	if ($('#txt_confirm_password').val().trim() =='' || !$('#txt_confirm_password').valid()){
		msg +="- Confirm Password<br />";
	}
	if ($('#txt_new_password').val().trim() != $('#txt_confirm_password').val().trim()){
		msg +="- ระบุ Confirm Password ให้ตรงกับ Password ใหม่ที่ตั้งไว้<br />";
	}
	if (msg!=''){
		msg ="ตรวจสอบข้อมูล<br />"+msg;
		customMessageDialog("info", "ตรวจสอบข้อมูล", msg, null);		
		return false;
	}
	
	if(!$('#frm_changepass').valid()){
		alert("ssss");
		return false;
	}
	
	let objUser = {
		password : $('#txt_new_password').val().trim(),
		userId :userId
	};
	
	let url = contextPath + "/api/user/user_change_password_manual";
	
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
				customMessageDialog("success", "สถานะการดำเนินการ", data.message, null);
				
				setTimeout(function (){  
				  window.location.href = 'index.jsp';				            
				}, 3000);
				
				
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

let userId = "";

var init = function(){
	console.log($('#userId').val());
	if($('#userId').val() != ""){
		let stringUserId = URI.decode($('#userId').val());
		userId = JSON.parse(stringUserId);
		console.log(userId);
	} else {
		window.location.href = "index.jsp";
		return false;
	}
	
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