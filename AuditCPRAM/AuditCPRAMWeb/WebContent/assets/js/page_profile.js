var objUserDetail = null;
$(function(){
	init.session();
	let objectValidate = {
		element : '#user-form',
		require : ['username', 'fullname', 'email', 'telephone'],
		message : ["กรุณาใส่ชื่อเข้าใช้งาน","กรุณาใส่ชื่อ","กรุณาใส่อีเมล์","กรุณาใส่เบอร์โทรศัพท์"],
		maxLength : [50, 100, 50, 30]
	};
	validate.init(objectValidate);
	let objAddRule = {
		element : '#password',
		rules : [{maxlength : 100}],
		message : [jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร")]
	}
	validate.add(objAddRule);
	$( "#email" ).rules( "add", {
		validateEmail : true		
	});
	
	/*$( "#password" ).rules( "add", {
		maxlength : 100,
		messages: {
			minlength: jQuery.validator.format("สามารถใส่ข้อมูลได้มากสุด {0} ตัวอักษร")
		}

	});*/
});

$('#username').on("keypress", function(e){
	return validateInnerText.valid(/[A-Za-z0-9 ]/g, e)
});

$('#fullname').on("keyup", function(e){
	var validateName = /[A-Za-z0-9 ]/g;
	if (validateName.test(this.value)) {
		$('#fullname').valid()
    }
    else {
    	$('#fullname').valid()                               
    }
});

$('#telephone').on("keypress", function(e){
	return validateInnerText.valid(/[0-9 ,]/g, e)
});

$('#email').on("keyup", function(){
	var validateMail = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if (validateMail.test(this.value)) {
		$('#email').valid()
    }
    else {
    	$('#email').valid()                               
    }
	
});

$('#btn_save_update').on('click',function(){
	if($('#user-form').valid()){
		
		let objUser = {				
			userId : objUserDetail[0].userId,
			employeeId : objUserDetail[0].employeeId,
			userGroupId : {userGroupId : objUserDetail[0].userGroupId.userGroupId},
			username : $('#username').val().trim(),
			password : $('#password').val().trim(),
			fullname : $('#fullname').val().trim(),
			email : $('#email').val().trim(),
			telephone : $('#telephone').val().trim(),
			enable : 'Y',
			description : ''
		};
		ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการแก้ไขข้อมูลนี้ใช่หรือไม่?");	
		ConfirmModal.confirmResult(function(isTrue){
			if(isTrue){
				let url = contextPath + "/api/user/update_user";
				ajaxProcess.submit(url, 'POST', objUser, '#portlet-profile', (data)=>{
					if(data.result){
						init.loadUserDetail(objUser.userId.trim())
						$('#password').val('');
					}else{
						init.loadUserDetail(objUser.userId.trim())
					}
				});
			}
		});
	}
});

var init = function(){
	return{
		session : function(){
			let url = contextPath+"/api/user/get_session_user";
			ajaxProcess.submit(url, 'GET', null, '', (data)=>{
				if(data.result){
					let objUser = JSON.parse(data.message);
					$('#user_group_name').text(objUser.userGroupId.userGroupName.trim());
					$('#fullname').val(objUser.fullname.trim());
					$('#pdpaModal').find('#iframeLink').attr( 'src',objUser.pdpalink+"#toolbar=0" );
					document.getElementById("PdpaDetail").innerHTML = "ชื่อ: "+objUser.fullname+" วันที่ยอมรับ: "+objUser.acceptPdpaDate;
	
					if(objUser.userGroupId.userGroupId == '1' || objUser.userGroupId.userGroupId == '2' || objUser.userGroupId.userGroupId == '5'){
						$('#password').closest('tr').hide();
					}
					init.loadUserDetail(objUser.userId.trim())
				}
			});
		},
		loadUserDetail : function(userId){
			//#portlet-profile
			let url = contextPath+'/api/user/user_deatil_by_id/'+JSON.stringify({
				userId : userId
			});
			ajaxProcess.submit(url, 'GET', null, '', (data)=>{
				if(data.result){
					objUserDetail = JSON.parse(data.message);
					$('#username').val(objUserDetail[0].username.trim());
					$('#email').val(objUserDetail[0].email.trim());
					$('#telephone').val(objUserDetail[0].telephone.trim());					
				}
			});
		}
	}
}();