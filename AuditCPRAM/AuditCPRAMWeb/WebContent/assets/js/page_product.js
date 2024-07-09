/**
 * 
 */
var action = getURLParameter("action");
var productId = getURLParameter("productId");
var validateAddDialog = null;
var dataTable = null;
if (action==''){
	$("#container_list").show();
}


$(function(){
	chSession()
	let objectValidate = {
			element : '#form-add-product-type',
			require : ['dal_txt_productTypeName'],
			message : ["กรุณาใส่ประเภทสินค้า"],
			maxLength : [200]
		};
	validateAddDialog = validate.init(objectValidate);
	dataTable = $('#table_product').DataTable({
	    "proccessing": true,
	    "serverSide": true,
	    "searching": true,
	    "ajax": {
	        "url": contextPath + "/api/product_type/datatable/product_type_list",
	        "type": 'POST',
	        beforeSend: function(){ BlockUi.blockPage(); },
	        contentType: 'application/json',
	        data: function ( d ) {
	            return JSON.stringify(d);
	        },
	        complete : function(){  	    	
	        	setTimeout(function(){ 
	  	    		BlockUi.unBlockPage();
				  }, 500)
	  	    }
	    },    
	    "language": {
	        "url": "assets/global/plugins/datatables/Thai.json"
	    },
	    "columns": [
	        { "data": null, "width": "30px" },
	        { "data": "productTypeId", name: "productId" },
	        { "data": "name", name: "name"},
	        {
	            "data": "enable",
	            "render": function (data, type, row) {
	            	
	            	var dpStatus = '<span class="label label-sm label-success" style="font-size : 12px;"> ใช้งาน </span>';
					if (data == 'N' ){
						dpStatus = '<span class="label label-sm label-default" style="font-size : 12px;"> ไม่ใช้งาน </span>';
					}
					
	                return dpStatus;
	            },
	            "width": "150px",
	            name: "enable"
	        }/*,
	        { "data": "createDate",
	            "render": function (data, type, row) {
	                return new Date(data.match(/\d+/)[0] * 1).toLocaleString("en-GB");
	            }
	        },        
	        {
	            "data": "updateDate",
	            "render": function (data, type, row) {
	                return new Date(data.match(/\d+/)[0] * 1).toLocaleString("en-GB");
	            }
	        }*/
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
	        	"targets": [1],
	            "visible": false,
	            "searchable": false
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [0,2,3,4]
	        },
	        {
	            "targets": 4,
	            "orderable": false,
	            "render": function (data, type, row) {
	            	var btnAction = '<div class="btn-group">';
	            	btnAction += '<button class="btn btn-xs blue dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false" style="font-size : 12px;"> เลือก <i class="fa fa-angle-down"></i></button>';
	    			btnAction += '<ul class="dropdown-menu pull-left" role="menu">';
	    			btnAction += 	'<li><a href="javascript:void(0);" class = "editProductType">';//ChangePage
	    			btnAction +=   		'<i class="icon-pencil"></i> แก้ไขข้อมูล </a>';
	    			btnAction +=	'</li>';
	    			btnAction +=	'<li><a href="javascript:void(0);" class = "removeProductType">';
	    			btnAction +=		'<i class="icon-trash"></i> ลบข้อมูล </a>';
	    			btnAction +=	'</li>';
	                btnAction += '</ul>';
	                btnAction +='</div>';
	                return btnAction;
	            },
	            "width": "200px" 
	        }
	    ],
	    "order": [[1, 'asc']],
	    "initComplete": function (settings, json) {
	    	$(".dataTables_filter").remove();
	    	
	    	$.fn.bootstrapSwitch.defaults.size = 'normal';
	    	$.fn.bootstrapSwitch.defaults.onColor = 'success';
	    	$("#dal_chb_activeSatatus").bootstrapSwitch();

	    }
	});
});

function chSession() {
	let url = contextPath +"/api/user/get_permission_menu";
	$.ajax({
		url : url,
		type : 'POST',
		contentType: "application/json",
		data : "product.jsp",
		success : function(data){
			if(!data.result){
				window.location = "/auditsupplier/home.jsp";
			}
		}
	});
}



$(document).on('click','.removeProductType',function(){
	let data = dataTable.row( $(this).parents('tr') ).data();
	let productTypeId = data.productTypeId.trim();
	let productTypeObj = {
		productTypeId : productTypeId	
	};
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการลบข้อมูลใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if (isTrue){ 
			let url = contextPath + "/api/product_type/delete_product_type";
			ajaxProcess.submit(url, 'DELETE', productTypeObj, null, (data)=>{
				if(data.result){
					$('#table_product').DataTable().draw();
				}
			});
		}
	});
});

//------------------------- Save Product
$("#dal_btn_saveProduct").click(function(event) {
	
	if($('#form-add-product-type').valid()){
		let productTypeId = $("#hd_productTypeId").val();
		let productTypeName = $("#dal_txt_productTypeName").val();	
		let statusId = 'N';

		if ($("#dal_chb_activeSatatus").is(':checked')){
			statusId = 'Y';
		}
		
		let objProducType = {
			productTypeId : productId,
			name : productTypeName,
			enable : statusId
		};		
		let url = contextPath + "/api/product_type/insert_product_type";
		ajaxProcess.submit(url, 'POST', objProducType, '#dialog_add_product', (data)=>{
			if(data.result){
				$('#dialog_add_product').modal('hide');
				$('#table_product').DataTable().draw();
			}
		});		
	}
	
	
	
});


$(document).on('click','.editProductType',function(){
	let data = dataTable.row( $(this).parents('tr') ).data();
	let productTypeId = data.productTypeId.trim();
	changePage(productTypeId);
});

//-------------Change Page---------------

function changePage(productionId)
{
	$("#hd_productTypeId").val(productionId);
	$('#productForm').submit();
}

//---------------------------------------
$('#dialog_add_product').on('hidden.bs.modal', function () {
	$('#dal_txt_productName').val('');
	$('#dal_chb_activeSatatus').bootstrapSwitch('state', true);	
	validateAddDialog.resetForm();
	$($(this).find('.has-error')).each(function(){
		$(this).removeClass('has-error');
	});
});

$('#btn_SearchSubmit').on('click',function(){		
	$('#table_product').DataTable().columns(2).search($('#txt_productName').val());
	$('#table_product').DataTable().columns(3).search($('#sel_Status').val());
	$('#table_product').DataTable().draw();
});

$('#btn_SearchClear').on('click',function(){
	$("#sel_Status").val($("#sel_Status option:first").val());
	$('#txt_productName').val('');
	
	$('#table_product').DataTable().columns(2).search('');
	$('#table_product').DataTable().columns(3).search('');
	$('#table_product').DataTable().draw();
});

function search() {
	dataTable.columns(2).search($('#txt_productName').val());
	dataTable.columns(3).search($('#sel_Status').val());
    dataTable.draw();    
}

function clearSearch() {    
	$('#txt_productName').val('');
	$('#sel_Status').prop('selectedIndex',0);
    dataTable.columns(2).search('');
    dataTable.columns(3).search('');
    dataTable.draw();
}



$("#txt_productName").keyup(function(event) {
    if (event.keyCode === 13) {
        $("#btn_SearchSubmit").click();
    }
});

/*$( "#sel_Status" ).change(function() {
	$("#btn_SearchSubmit").click();
});*/

