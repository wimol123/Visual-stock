// Type => success, info, warning, error

function customMessageDialog(type, title, message, callback){
	/*
	$("#gobalDialogTitle").html(title);
	$("#gobalDialogBody").html(message);
	$("#gobalDialog").modal('show');
	$('#gobalDialog').on('hidden.bs.modal', function () {
		if (callback!=null){
			  callback();
		}
	})
	*/
    toastr.options = {
	  "closeButton": true,
	  "debug": false,
	  "positionClass": "toast-top-right",
	  "onclick": null,
	  "showDuration": "1000",
	  "hideDuration": "1000",
	  "timeOut": "5000",
	  "extendedTimeOut": "1000",
	  "showEasing": "swing",
	  "hideEasing": "linear",
	  "showMethod": "fadeIn",
	  "hideMethod": "fadeOut"
	}
    if (type=='info'){
    	toastr.info(message);
    }else if (type=='success'){
    	toastr.success(message);
    }else if (type=='warning'){
    	toastr.warning(message);
    }else if (type=='error'){
    	toastr.error(message);
    }	
    
    if (callback!=null){
		  callback();
	}
}
function customCofirmDialog(type, title, message, callback){
	
	$("#gobalDialogConfirmTitle").html(title);
	$("#gobalDialogConfirmBody").html(message);
	$("#gobalDialogConfirm").modal('show');
	$('#gobalDialogConfirm').on('click', '#gobal_confim_confirm', function(e){
		if (callback!=null){
			return callback(true);
		}
	})
	$('#gobalDialogConfirm').on('click', '#gobal_confim_cancel', function(e){
		if (callback!=null){
			return callback(false);
		}
	})
}

var ConfirmModal = function(){
	return{
		setupModal: function(type, title, message) {
			$("#gobalDialogConfirmTitle").html(title);
			$("#gobalDialogConfirmBody").html(message);
			$("#gobalDialogConfirm").modal('show');
		},
		confirmResult: function(callback){
			$('#gobal_confim_confirm').on('click', function(e){
				//console.log("Confirm");
				if (callback!=null){
					$('#gobal_confim_confirm').unbind();
					$('#gobal_confim_cancel').unbind();
					return callback(true);
				}
			})
			$('#gobal_confim_cancel').on('click', function(e){
				//console.log("Cancel");
				if (callback!=null){
					$('#gobal_confim_confirm').unbind();
					$('#gobal_confim_cancel').unbind();
					return callback(false);
				}
			})
		}
		
	};
}();

var ConfirmModalPDPA = function(){
	return{
		setupModal: function(type, title, message) {
			$("#gobalDialogConfirmTitlePDPA").html(title);
			$("#gobalDialogConfirmBodyPDPA").html(message);
			$("#gobalDialogConfirmPDPA").modal('show');
		},
		confirmResult: function(callback){
			$('#gobal_confim_confirmPDPA').on('click', function(e){
				//console.log("Confirm");
				if (callback!=null){
					$('#gobal_confim_confirmPDPA').unbind();
					$('#gobal_confim_cancelPDPA').unbind();
					return callback(true);
				}
			})
			$('#gobal_confim_cancelPDPA').on('click', function(e){
				//console.log("Cancel");
				if (callback!=null){
					$('#gobal_confim_confirmPDPA').unbind();
					$('#gobal_confim_cancelPDPA').unbind();
					return callback(false);
				}
			})
		}
		
	};
}();
