$(function(){
	//%3FchecklistPlanId%3D1
	$('#static').modal('show');
	let checklistPlanId = getURLParameterEncode('checklistPlanId');
	if( checklistPlanId != "" && checklistPlanId != null){
		let URL = contextPath+'/api/cryptography/decrypt_rsa';
		
		ajaxProcess.submit(URL, 'POST', {dataEncrypt : checklistPlanId}, '', (data)=>{		
			if(data.result){
				let objParam = {
					destination : "final_auditresult_form.jsp",
					value : {checklistPlanId : JSON.parse(data.message).decodeResult.trim()}
				};
				$('#pass_param').val(URI.encode(JSON.stringify(objParam)));
				
			}else{
				$('#pass_param').val("");
			}
			
			let url = contextPath+'/api/user/get_session_user';
			ajaxProcess.submit(url, 'GET', null, '', (dataSession)=>{
				let objData = JSON.parse(dataSession.message);
				if(objData != null){
					if(objData.employeeId != null && objData.fullname != null && objData.userId != null && objData.userMenu != null && objData.userGroupId.userGroupId != null){
		    			setTimeout(()=>{
		    				$('#pass_param').val(JSON.parse(data.message).decodeResult.trim());
		    				$('#pass_form_finalauditresult').attr('action', "final_auditresult_form.jsp");
							$('#pass_form_finalauditresult').submit();
						},1000);
		    		}else{
		    			$('#pass_form_finalauditresult').submit();
		    		}
				}else{
					$('#pass_form_finalauditresult').submit();
				}	    						
			});
			
			
		});		
		
	}else{
		window.location.href = "index.jsp";
	}	
	
});

function getURLParameterEncode(sParam)
{
	var sPageURL = URI.decode(window.location.search.substring(1));
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam)
        {
            return sParameterName[1];
        }
    }
	return "";
}