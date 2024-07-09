var PrivacyDocumentObj = null;
var Checkresult;
var linkPdf
var ObjUser;
var Fullname;
var PdpaObject = null;
var UrlPdpa;
var Login = function () {
    
    return {
        init: function () {
        	
		    $.backstretch([
		          "assets/pages/img/bg_cpram.jpg"
		        ], {
		          fade: 1000,
		          duration: 8000
		    	}
        	);
        }
    };

}();

var Session = function(){
	return {
		init : function(){
			let url = contextPath+'/api/user/get_session_user';
			$.ajax({				
			      url: url,
			      type: 'GET',
			      contentType: 'application/json',			      
			      success : function(data, textStatus, jqXHR) {
			    	  BlockUi.blockPage();
			    	  if(data!=null){
			    		  try{
				    		  let objData = JSON.parse(data.message);
							  var result = ValidateSesion(objData.userId);
							  var ValidateSesionObj = result.responseJSON;
				    		  if(objData.employeeId != null && objData.fullname != null && objData.userId != null && objData.userMenu != null && objData.userGroupId.userGroupId != null && ValidateSesionObj.returnCode == 00000){
				    			  if($('#receiverParam').val() == "" && objData.acceptPdpaStatus == 'Y'|| $('#receiverParam').val() == null && objData.acceptPdpaStatus == 'Y'){
					    			  setTimeout(function(){
					    				  window.location.href= 'home.jsp';
					    			  },500);	
				    			  }else{
				    				  
				    				  processParam.init(JSON.parse(URI.decode($('#receiverParam').val().trim())), objData);
				    				  
				    			  }			  
				    		  }	else{
				    			  BlockUi.unBlockPage();
				    		  }	
			    		  }catch (e) {
			    			  //customMessageDialog("error", "ตรวจสอบข้อมูล", e, null);
			    			  BlockUi.unBlockPage();
			    		  }
			    		 	    		 
			    	  }else{
			    		  BlockUi.unBlockPage();
			    	  }
				  },
				  error: function (jqXHR, textStatus, errorThrown) {					  
					  console.log("ajax error  => "+textStatus + " => "+ errorThrown + " => "+ jqXHR);
				  },
			});
		}
	}
}();



jQuery(document).ready(function() {	
	BlockUi.init();
    Login.init();   
    Session.init();
    ajaxProcess.submit(contextPath+'/api/privacy_document/get_url_pdpa', 'GET', null, '#section-standard-document', (data) => {
		if (data.result) {
			UrlPdpaObj = JSON.parse(data.message);
			UrlPdpa = UrlPdpaObj['system_value'];
			console.log(UrlPdpa);
		}
	}, { async: false });
    //validateInnerText.validByElement('#txt_username', null);
   
});
$('#txt_username').on("keypress", function(e){
	return validateInnerText.valid(/[a-zA-Z0-9 _]/g, e)
});
$("#txt_username").keyup(function(event) {
    if (event.keyCode === 13) {
        $("#bt_submit").click();
    }
});
$("#txt_password").keyup(function(event) {
    if (event.keyCode === 13) {
        $("#bt_submit").click();
    }
});

$("#txt_username").focus();

$('#frm_login').submit(function() { 
	BlockUi.blockPage();
	var username = $("#txt_username").val().trim();
	var password = $("#txt_password").val().trim();
	var msg ="";
	if (username==''){
		msg +="- Username<br />";
	}
	if (password==''){
		msg +="- Password<br />";
	}
	if (msg!=''){
		msg ="ตรวจสอบข้อมูล<br />"+msg;
		//customMessageDialog("info", "ตรวจสอบข้อมูล", msg, null);
		BlockUi.unBlockPage();
		return false;
	}
	else
	{ 
		let objUser = {
			username : username,
			password : password
		};
		let url = contextPath+'/api/user/authen';
		$.ajax({
		      url: url,
		      type: 'POST',
		      contentType: 'application/json',
		      data : JSON.stringify(objUser),
		      success : function(data, textStatus, jqXHR) {
		    	  	if (data!=null){	  	
		    	  		try{
		    	  			let objData = JSON.parse(data.message);
		    	  			console.log(objData);
		    	  			if (objData.returnCode=="0000"){
								ObjUser = objData.userId;
								Fullname = objData.fullname;
								// window.location.href= 'home.jsp';
								var result = ValidateUser(objData.userId);
								var ValidateObj = result.responseJSON;
								console.log(ValidateObj);
								if(ValidateObj.returnCode == 70019 ){
									loadPrivacyDocumentData();
									linkPdf = objData['pdpalink'];
		    	  				}else if(ValidateObj.returnCode != 00000){
									alert(
										"returnCode: "+ValidateObj.returnCode+"\n"+
										"returnDesc: "+ValidateObj.returnDesc+"\n"+
										"returnDescTH: "+ValidateObj.returnDescTH+"\n"
									);
									window.location.href = '${contextPath}/logout';
								}
								console.log(ValidateObj.data.activities[0].activityStatus);
		    	  				//if($('#receiverParam').val() == "" && objData.acceptPdpaStatus == 'Y' && ValidateObj.returnCode == 00000 || $('#receiverParam').val() == null && objData.acceptPdpaStatus == 'Y' && ValidateObj.returnCode == 00000 ){
		    	  				if($('#receiverParam').val() == "" && ValidateObj.data.activities[0].activityStatus == 1 && ValidateObj.returnCode == 00000 || $('#receiverParam').val() == null && ValidateObj.data.activities[0].activityStatus == 1 && ValidateObj.returnCode == 00000 ){
				      				//window.location.href= 'home.jsp';
		    	  					loadPrivacyDocumentData();
									//linkPdf = ValidateObj['data']['activities'][0]['systemList'][0]['term']['termInfo']
		    	  					linkPdf = objData['pdpalink'];
		    	  				}else{
		    	  					console.log(objData)
		    	  					processParam.init(JSON.parse(URI.decode($('#receiverParam').val().trim())), objData);
		    	  				}
	    	  				
			    	  		}else if (objData.returnCode=="8888"){
			      				//window.location.href= 'mobile/home.jsp'; 
			    	  		}else if (objData.returnCode=="0002"){
								$('#userId').val(URI.encode(JSON.stringify(objData.userId)));
								$('#userId').closest('form').submit();
								//window.location.href = 'change_password.jsp?userId='+objData.userId;
							}
			    	  		else
			    	  		{
			    	  			BlockUi.unBlockPage();
		    	  				customMessageDialog("error", "ตรวจสอบข้อมูล", objData.returnMessage, null);
			    	  		}
		    	  		}catch (e) {
		    	  			//customMessageDialog("error", "ตรวจสอบข้อมูล", e, null);
		    	  			BlockUi.unBlockPage();
						}
		    	  		
		    	  	}else{
		    	  		BlockUi.unBlockPage();
		    	  	}
			  },
			  error: function (jqXHR, textStatus, errorThrown) {
				  BlockUi.unBlockPage();
				  console.log("ajax error  => "+textStatus + " => "+ errorThrown + " => "+ jqXHR);
		  },
		});
		
		
		return false;
	}
});



var processParam = function(){
	return{
		init : function(objParam, sessionObj){
			switch (objParam.destination) {
			case "final_auditresult_form.jsp":
				let URL = contextPath + "/api/final_audit_result/check_access_final_audit_result/"+JSON.stringify({checklistPlanId : objParam.value.checklistPlanId, userId : sessionObj.userId});
				ajaxProcess.submit(URL, 'GET', null, '', (data)=>{
					if(data.result){
						let dataobj = JSON.parse(data.message);

						if(dataobj.length == 0 || dataobj == null){
							window.location.href = "home.jsp";
						}else{
							$('#pass_param').val(objParam.value.checklistPlanId);
							$('#form_control_redirect').prop('action', objParam.destination);
							$('#form_control_redirect').submit();
						}
					}else{
						window.location.href = "home.jsp";
					}
				});
				break;
			default:
				break;
			}
		}
	}
}();


function UpdatePdpa(){
	
	$("#pdpaModal").modal("hide");
	var result = updatePdpaStatus();
	var UpdateObj = result.responseJSON;
	
	if(UpdateObj.returnCode == 00000 ){
		
		let url = contextPath +'/api/user/updatePdpaStatus'
		$.ajax({
			url: url,
		    type: 'POST',
		    async: false,
		    contentType: 'application/json; charset=UTF-8',
			data : ObjUser,
		    success : function(data, msg, jqXHR) {
				loadTearmOfUseDocument();
	    		//window.location.href = "home.jsp";
		    },
			error: function(data, msg, jqXHR){    
	    		
			}
		});
		
	}else{
		alert(
			"returnCode: "+UpdateObj.returnCode+"\n"+
			"returnDesc: "+UpdateObj.returnDesc+"\n"+
			"returnDescTH: "+UpdateObj.returnDescTH+"\n"
		);
		window.location.href = '${contextPath}/logout';
	}
}

function UpdateCancelPdpa(){
	
	$("#pdpaModal").modal("hide");
	var result = updateCancelPdpaStatus();
	var UpdateObj = result.responseJSON;
	let url = contextPath +'/api/user/updateCancelPdpaStatus'
	$.ajax({
		url: url,
		type: 'POST',
		async: false,
		contentType: 'application/json; charset=UTF-8',
		data : ObjUser,
		success : function(data, msg, jqXHR) {
			window.location.href = '${contextPath}/logout';
		},
		error: function(data, msg, jqXHR){    
			window.location.href = '${contextPath}/logout';
		}
	});
		
}

function updateCancelPdpaStatus(){
	
	let url = UrlPdpa+'updateactivity';
	//let url = UrlPdpa+'term-consent/updateactivity';
	//let url = 'https://mra3hnmrre.execute-api.ap-southeast-1.amazonaws.com/dev/term-consent/updateactivity';
	//let url = 'https://krg980zpde.execute-api.ap-southeast-1.amazonaws.com/prod/term-consent/updateactivity';
	var date = new Date();
	var day = date.getDate();
	var month = date.getMonth()+1;
	var newday = date.getDate();
	var newmonth = date.getMonth()+1;
	
	if(day < 10){
		newday = "0"+day;
	}
	
	if(month < 10 ){
		newmonth = "0"+month;
	}
	var minute = date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
	var hours = date.getHours() <10 ? "0"+date.getHours() : date.getHours();
	var second = date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
	var timestamp = date.getFullYear()+
	  "-"+newmonth+
	  "-"+newday+
	  "T"+
	  ""+hours+
	  ":"+minute+
	  ":"+second+
	  "+07:00";
	
	let Updatedata = 
	{
    	sysDateTime: timestamp,
    	systemId: "05026001",
    	channelId: "CN03",
    	activityId: "WSS100001",
    	systemKey: {
        	id: ObjUser,
        	thaiId: "0"
    	},
	    term: {
	        version: 1,
	        statusId: 2
	    }
	}
		
	return $.ajax({
		url: url,
	    type: 'POST',
	    async: false,
	    headers: {'x-api-key':'dhyVh0mqjD2pH9K5vv7sHLle6CTyqhk3cjvZeCuc'},
	    contentType: 'application/json; charset=UTF-8',
		data : JSON.stringify(Updatedata),
	    success : function(data, msg, jqXHR) {
    		
	    },
		error: function(data, msg, jqXHR){    
    		
		}
	});

	
}

function updatePdpaStatus(){
	
	let url = UrlPdpa+'updateactivity';
	//let url = UrlPdpa+'term-consent/updateactivity';
	//let url = 'https://mra3hnmrre.execute-api.ap-southeast-1.amazonaws.com/dev/term-consent/updateactivity';
	//let url = 'https://krg980zpde.execute-api.ap-southeast-1.amazonaws.com/prod/term-consent/updateactivity';
	var date = new Date();
	var day = date.getDate();
	var month = date.getMonth()+1;
	var newday = date.getDate();
	var newmonth = date.getMonth()+1;
	
	if(day < 10){
		newday = "0"+day;
	}
	
	if(month < 10 ){
		newmonth = "0"+month;
	}
	var minute = date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
	var hours = date.getHours() <10 ? "0"+date.getHours() : date.getHours();
	var second = date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
	var timestamp = date.getFullYear()+
	  "-"+newmonth+
	  "-"+newday+
	  "T"+
	  ""+hours+
	  ":"+minute+
	  ":"+second+
	  "+07:00";
	
	let Updatedata = 
	{
    	sysDateTime: timestamp,
    	systemId: "05026001",
    	channelId: "CN03",
    	activityId: "WSS100001",
    	systemKey: {
        	id: ObjUser,
        	thaiId: "0"
    	},
	    term: {
	        version: 1,
	        statusId: 1
	    }
	}
		
	return $.ajax({
		url: url,
	    type: 'POST',
	    async: false,
	    headers: {'x-api-key':'dhyVh0mqjD2pH9K5vv7sHLle6CTyqhk3cjvZeCuc'},
	    contentType: 'application/json; charset=UTF-8',
		data : JSON.stringify(Updatedata),
	    success : function(data, msg, jqXHR) {
    		
	    },
		error: function(data, msg, jqXHR){    
    		
		}
	});

	
}

function ValidateUser(userID){
	var date = new Date();
	var day = date.getDate();
	var month = date.getMonth()+1;
	var newday = date.getDate();
	var newmonth = date.getMonth()+1;
	
	if(day < 10){
		newday = "0"+day;
	}
	
	if(month < 10 ){
		newmonth = "0"+month;
	}
			
	var minute = date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
	var hours = date.getHours() <10 ? "0"+date.getHours() : date.getHours();
	var second = date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
	var timestamp = date.getFullYear()+
	  "-"+newmonth+
	  "-"+newday+
	  "T"+
	  ""+hours+
	  ":"+minute+
	  ":"+second+
	  "+07:00";
	
	
	let Validatedata = {
		sysDateTime: timestamp ,
		channelId: "CN03",
		systemId: "05026001",
		activityId: "WSS100001",
		systemKey: {
		    id: userID
		}
	}
	
	var summitDate = newday+"/"+newmonth+"/"+date.getFullYear()
	document.getElementById("PdpaDetail").innerHTML = "ชื่อ: "+Fullname+" วันที่ยอมรับ: "+summitDate;
	
	return $.ajax({
		url: UrlPdpa+'validateactivity',
		//url: UrlPdpa+'term-consent/validateactivity',
		//url: 'https://mra3hnmrre.execute-api.ap-southeast-1.amazonaws.com/dev/term-consent/validateactivity',
	    type: 'POST',
	    async: false,
	    headers: {'x-api-key':'dhyVh0mqjD2pH9K5vv7sHLle6CTyqhk3cjvZeCuc'},
	    contentType: 'application/json; charset=UTF-8',
		data : JSON.stringify(Validatedata),
	    success : function(data, msg, jqXHR) {
    		console.log(data);
	    },
		error: function(data, msg, jqXHR){    
    		
		}
	});
	
}

function ValidateSesion(userID){
	
	var date = new Date();
	var day = date.getDate();
	var month = date.getMonth()+1;
	var newday = date.getDate();
	var newmonth = date.getMonth()+1;
	
	if(day < 10){
		newday = "0"+day;
	}
	
	if(month < 10 ){
		newmonth = "0"+month;
	}
			
	var timestamp = date.getFullYear()+
	  "-"+newmonth+
	  "-"+newday+
	  "T"+
	  ""+date.getHours()+
	  ":"+date.getMinutes()+
	  ":"+date.getSeconds()+
	  "+07:00";
	
	
	let Validatedata = {
		sysDateTime: timestamp ,
		channelId: "CN03",
		systemId: "05026001",
		activityId: "WSS100001",
		systemKey: {
		    id: userID
		}
	}

	
	return $.ajax({
		url: UrlPdpa+'validateactivity',
		//url: UrlPdpa+'term-consent/validateactivity',
		//url: 'https://mra3hnmrre.execute-api.ap-southeast-1.amazonaws.com/dev/term-consent/validateactivity',
	    type: 'POST',
	    headers: {'x-api-key':'dhyVh0mqjD2pH9K5vv7sHLle6CTyqhk3cjvZeCuc'},
	    //beforeSend: function(xhr){xhr.setRequestHeader('x-api-key', 'dhyVh0mqjD2pH9K5vv7sHLle6CTyqhk3cjvZeCuc');},
	    async: false,
	    contentType: 'application/json; charset=UTF-8',
		data : JSON.stringify(Validatedata),
	    success : function(data, msg, jqXHR) {
    		
	    },
		error: function(data, msg, jqXHR){    
    		
		}
	});
	
}


function CancelPdpa(){
	window.location.href = '${contextPath}/logout';
}


function loadPrivacyDocumentData() {
	let url = contextPath + '/api/privacy_document/privacy_document_list'
	ajaxProcess.submit(url, 'GET', null, '#section-standard-document', (data) => {
		if (data.result) {
			PrivacyDocumentObj = JSON.parse(data.message)
		}
	}, { async: false });
//	console.log("PrivacyDocumentObj: "+PrivacyDocumentObj[0]['system_value']);
	
	$('#privacyModal').find('#iframePrivacyLink').attr( 'src',PrivacyDocumentObj[0]['system_value']+"#toolbar=0" );
	
	$("#privacyModal").modal({ backdrop: 'static' } , "show");
}

function loadTearmOfUseDocument() {
	let url = contextPath + '/api/privacy_document/get_terms_document'
	let file ;
	ajaxProcess.submit(url, 'GET', null, '#section-standard-document', (data) => {
		if (data.result) {
			 file = data.file
		}
	}, { async: false });	
	
	
	$('#termsModal').find('#iframeTermsLink').attr( 'src',"data:application/pdf;base64, "+file+"#toolbar=0" );
	
	$("#termsModal").modal({ backdrop: 'static' } , "show");
}

function submitTerms(){
	window.location.href = "home.jsp";
}


function acceptPrivacy(){
	console.log(linkPdf);
	$("#privacyModal").modal("hide");
	$('#pdpaModal').find('#iframeLink').attr('src',linkPdf+"#toolbar=0");
	$("#pdpaModal").modal({ backdrop: 'static' } , "show");
	BlockUi.unBlockPage();
}

$("#show_hide_password a").on('click', function(event) {
    event.preventDefault();
    if ($('#show_hide_password input').attr("type") == "text") {
        $('#show_hide_password input').attr('type', 'password');
        $('#show_hide_password #i-eye').addClass("fa-eye-slash");
        $('#show_hide_password #i-eye').removeClass("fa-eye");
    } else if ($('#show_hide_password input').attr("type") == "password") {
        $('#show_hide_password input').attr('type', 'text');
        $('#show_hide_password #i-eye').removeClass("fa-eye-slash");
        $('#show_hide_password #i-eye').addClass("fa-eye");
    }
});


/*$("#txt_username").on("keypress", function(event) {
    return validateInnerText.valid(event, null);
});

$('#txt_username').on("paste",function(e){
    e.preventDefault();
});*/



/*$('#txt_username').on('keydown',function(e){
	 if (!/^[a-zA-Z0-9]+$/.test(this.value)) {
		 console.log(e);
	 }
});*/