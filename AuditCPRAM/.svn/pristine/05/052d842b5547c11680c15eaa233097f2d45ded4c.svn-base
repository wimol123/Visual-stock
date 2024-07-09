/**
 * 
 */

//var productId = $("#txt_productTypeId").val();
//console.log("productId : "+productId);

//---------- DataTable ----------
var tableproductmappingsupplier = null;
var table_choose_supplier_product_mapping = null;
var validateProductType = null;

$(function(){
	
	$.fn.bootstrapSwitch.defaults.size = 'normal';
	$.fn.bootstrapSwitch.defaults.onColor = 'success';
	$("#chb_activeSatatus").bootstrapSwitch();
	
	let objectValidate = {
			element : '#product-type-form',
			require : ['txt_productTypeName'],
			message : ["กรุณาใส่ประเภทสินค้า"],
			maxLength : [200]
		};
	validateProductType = validate.init(objectValidate);
	
	if($("#txt_productTypeId").val() == "")
	{
		$(".option-product").addClass("disabledelements");	
	}
	else
	{
		loadProductTypeById();
		loadChecklistMappingProductType();
		loadSupplierMappingProductType();
	}
	
	$('#dialog_choose_supplier').on('hidden.bs.modal', function (e){
		//tableproductmappingsupplier.draw();
	});
});

function loadProductTypeById()
{
	let url = contextPath + "/api/product_type/product_type_by_id/"+JSON.stringify({productTypeId : $("#txt_productTypeId").val().trim()});
	ajaxProcess.submit(url, 'GET', null, '#product-deatil-portlet', (data)=>{
		if(data.result){
			let objProductType = JSON.parse(data.message);
			if(objProductType.length > 0){
				$.each(objProductType,function(i,value){
					$('#txt_productTypeName').val(value.name);
					if(value.enable == 'N'){
						$('#chb_activeSatatus').bootstrapSwitch('state', false);	
					}
				});
			}
		}
	});
}

$('#btnSaveProduct').on('click',function(){
	saveProduct();	
});

function saveProduct()
{
	
	if($('#product-type-form').valid()){
		
		let productTypeId = $("#txt_productTypeId").val();
		let productName = $("#txt_productTypeName").val();	
		let statusId = 'N';
		if ($("#chb_activeSatatus").is(':checked')){
			statusId = 'Y';
		}
		
		let objProductType = {
			productTypeId : productTypeId,
			name : productName,
			enable : statusId
		};
		
		if(objProductType.productTypeId == ''){
			
			ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการบันทึกข้อมูลนี้ใช่หรือไม่?");	
			ConfirmModal.confirmResult(function(isTrue){
				if (isTrue){
					let url = contextPath + "/api/product_type/insert_product_type";
					ajaxProcess.submit(url, 'POST', objProductType, '#product-deatil-portlet', (data)=>{
						if(data.result){
							BlockUi.blockPage('warning');
							setTimeout(()=>{
								window.location.href = 'product.jsp';
							},300);
						}
					});
				}
			});
			
		}else{
			ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการบันทึกการแก้ข้อมูลนี้ใช่หรือไม่?");	
			ConfirmModal.confirmResult(function(isTrue){
				if (isTrue){
					let url = contextPath + "/api/product_type/update_product_type";;
					ajaxProcess.submit(url, 'POST', objProductType, '#product-deatil-portlet', (data)=>{
						
					});
				}
			});
		}
		
	}	

}


$(document).on('click', '.removeChecklist', function(){
	let data = $('#table_checklist').DataTable().row( $(this).parents('tr') ).data();
	
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการลบข้อมูลนี้ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if (isTrue){	
			let checklistId = data.checklistId.trim();
			let url = contextPath + "/api/checklist/delete_checklist";
			let objChecklist = {checklistId : checklistId};
			
			ajaxProcess.submit(url, 'DELETE', objChecklist, '.portlet-checklist', (data)=>{
				if(data.result){
					$('#table_checklist').DataTable().draw();
				}
			});
			
		}
	});
	
});


$('#btn_SearchChecklist').on('clcik',function(){
	search();
});

$('#btn_ClearSearchChecklist').on('click',function(){
	clearSearch();
});
$("#txt_ChecklistTitle").keyup(function(event) {
    if (event.keyCode === 13) {
    	search();
    }
});

function search() {
	$('#table_checklist').DataTable().columns(1).search($('#txt_ChecklistTitle').val().trim());
	$('#table_checklist').DataTable().draw();    
}

function clearSearch() {    
	$('#txt_ChecklistTitle').val('');
	$('#table_checklist').DataTable().columns(1).search('');
	$('#table_checklist').DataTable().draw();
}

function loadChecklistMappingProductType(){
	$('#table_checklist').DataTable({
		"proccessing": true,
	    "serverSide": true,
	    "searching": true,
	    "ajax": {
	        "url": contextPath + "/api/checklist/datatable/checklist_by_product_type",
	        "type": 'POST',
	        beforeSend: function(){ BlockUi.blockPosition('.portlet-checklist'); },
	        contentType: 'application/json',
	        data: function ( d ) {
	        	let data =  $.extend( {}, d, {
	                "optionString": URI.decode($('#txt_productTypeId').val().trim())
	            });	        	
	        	return JSON.stringify(data);
	        },
	        complete : function(){  	    	
	        	setTimeout(function(){ 
	  	    		BlockUi.unBlockPosition('.portlet-checklist');
				  }, 500)
	  	    }
	    },    
	    "language": {
	        "url": "assets/global/plugins/datatables/Thai.json"
	    },
	    "columns": [
	        { "data": "checklistId", name: "checklistId" },
	        { "data": "checklistTitle", name: "checklistTitle"},	        
	    ],
	    "columnDefs": [	        
	        {
	        	"targets": [0],
	            "visible": false,
	            "searchable": false
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [1]
	        },
	        {
	            "targets": 2,
	            "orderable": false,
	            "render": function (data, type, row) {
	            	var btnAction = '<div class="btn-group">';
	            	btnAction += '<button class="btn btn-xs blue dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false" style="font-size : 12px;"> เลือก <i class="fa fa-angle-down"></i></button>';
	    			btnAction += '<ul class="dropdown-menu pull-left" role="menu">';	    			
	    			btnAction +=	'<li><a href="javascript:void(0);" class = "removeChecklist">';
	    			btnAction +=		'<i class="icon-trash"></i> ลบข้อมูล </a>';
	    			btnAction +=	'</li>';
	                btnAction += '</ul>';
	                btnAction +='</div>';
	                return btnAction;
	            }
	        }
	    ],
	    "order": [[0, 'asc']],
	    "initComplete": function (settings, json) {
	    	$(".dataTables_filter").remove();    	
	    }
	});
}


$('#btn_SearchSupplier').on('click',function(){
	searchSupplier();
});

$('#btn_ClearSearchSupplier').on('click',function(){
	clearSearchSupplier();
});
$("#txt_SupplierCompany").keyup(function(event) {
    if (event.keyCode === 13) {
    	searchSupplier();
    }
});
$("#txt_location").keyup(function(event) {
    if (event.keyCode === 13) {
    	searchSupplier();
    }
});

function searchSupplier() {
	$('#table_supplier_mapping_product').DataTable().columns(2).search($('#txt_SupplierCompany').val().trim());
	$('#table_supplier_mapping_product').DataTable().columns(3).search($('#txt_location').val().trim());
	$('#table_supplier_mapping_product').DataTable().draw();    
}

function clearSearchSupplier() {    
	$('#txt_SupplierCompany').val('');
	$('#txt_location').val('');
	$('#table_supplier_mapping_product').DataTable().columns(2).search('');
	$('#table_supplier_mapping_product').DataTable().columns(3).search('');
	$('#table_supplier_mapping_product').DataTable().draw();
}

$(document).on('click', '.removeLocationProduce', function(){
	let data = $('#table_supplier_mapping_product').DataTable().row( $(this).parents('tr') ).data();
	
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการลบข้อมูลนี้ใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if (isTrue){
			let url = contextPath + "/api/supplier_product_type_address_mapping/delete_supplier_location";
			let objSupplierProductAddressMapping = {
				id : data.id,
				supplierId : {supplierId : data.supplierId.supplierId.trim()},
				addressId : {addressId : data.addressId.addressId}
			};
			ajaxProcess.submit(url, 'DELETE', objSupplierProductAddressMapping, '#portlet_supplier_mapping_product', (data)=>{
				if(data.result){
					$('#table_supplier_mapping_product').DataTable().draw();
				}
			});
		}
	});
});

function loadSupplierMappingProductType(){
	$('#table_supplier_mapping_product').DataTable({
		"proccessing": true,
	    "serverSide": true,
	    "searching": true,
	    "ajax": {
	        "url": contextPath + "/api/supplier_product_type_address_mapping/datatable/supplier_list_mapping_by_product",
	        "type": 'POST',
	        beforeSend: function(){ BlockUi.blockPosition('#portlet_supplier_mapping_product'); },
	        contentType: 'application/json',
	        data: function ( d ) {
	        	let data =  $.extend( {}, d, {
	                "optionString": URI.decode($('#txt_productTypeId').val().trim())
	            });	        	
	        	return JSON.stringify(data);
	        },
	        complete : function(){  	    	
	        	setTimeout(function(){ 
	  	    		BlockUi.unBlockPosition('#portlet_supplier_mapping_product');
				  }, 500)
	  	    }
	    },    
	    "language": {
	        "url": "assets/global/plugins/datatables/Thai.json"
	    },
	    "columns": [
	    	{ "data": null, "width": "30px" },
	    	{ "data": "supplierId.supplierId" ,name:"supplierId"},
	    	{ "data": "supplierId.supplierCompany" ,name:"supplierCompany"},	    	
	    	{ "data": "locationName" ,name:"locationName"}
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
	            "searchable": false,
	            "orderable": false,
	            "targets": [0,1]
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [2,3]
	        },
	        {
	            "targets": 4,
	            "orderable": false,
	            "render": function (data, type, row) {
	            	
	            	
	            	var btnAction = '<div class="btn-group">';
	            	btnAction += '<button class="btn btn-xs blue dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false" style="font-size : 12px;"> เลือก <i class="fa fa-angle-down"></i></button>';
	    			btnAction += '<ul class="dropdown-menu pull-left" role="menu">';	    			
	    			btnAction +=	'<li><a href="javascript:void(0);" class = "removeLocationProduce">';
	    			btnAction +=		'<i class="icon-trash"></i> ลบข้อมูล </a>';
	    			btnAction +=	'</li>';
	                btnAction += '</ul>';
	                btnAction +='</div>';
	               
	                return btnAction;
	            },
	            "width": "70px" 
	        }
	    ],
	    "order": [['1', 'asc']],
	    "initComplete": function (settings, json) {	    	
	    	$('.dataTables_filter').remove();
	    	
	     } 
	});
}



/*$('#dialog_choose_supplier').on('shown.bs.modal', function (e){
	if(table_choose_supplier_product_mapping != null)
	{
		table_choose_supplier_product_mapping.draw();
	}
	else
	{
		table_choose_supplier_product_mapping = $('#table_choose_supplier_product_mapping').DataTable({
			"proccessing": true,
			"serverSide": true,
			"searching": true,
			"ajax": {
		        "url": contextPath + "/api/supplier_product_type_mapping/datatable/supplier_list_not_in_product/"+JSON.stringify({productTypeId : $("#txt_productTypeId").val().trim()}),
		        "type": 'POST',
		        beforeSend: function(){ BlockUi.blockPosition('#dialog_choose_supplier'); },
		        contentType: "application/json",
			    complete : function(){
			    	BlockUi.unBlockPosition('#dialog_choose_supplier');
			    },
		        data: function (d) {
		        	return JSON.stringify(d);
		        }
		    },
		    "language": {
		        "url": "assets/global/plugins/datatables/Thai.json"
		    },
		    "columns": [
		    	{ "data": null, "width": "50" },
		    	{ "data": null, name:"product_id"},
		    	{ "data": "supplierId", name:"supplierId"},
		    	{ "data": "supplierCompany", name: "supplierCompany"}	    		    	
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
		        	"targets": [1,2],
		            "visible": false,
		            "searchable": true
		        },
		        {
		        	"orderable": false,
		            "targets": [0, 1, 2, 3, 4]
		        },
		        {
		            "targets": 4,
		            className: 'dt-center',
		            "orderable": false,
		            "render": function (data, type, row) {
		            	
		            	var btnAction = '<a href="javascript:void(0);" class="btn btn-xs blue btn-choose-supplier btn-remove-product-supplier" style="font-size:12px;">';
		            	btnAction += 		'<i class="fa fa-check"></i>';
		    			btnAction += 		'เลือก';
		    			btnAction += 	'</a>';	
		               
		                return btnAction;
		            },
		            "width":"100px"
		        }
		    ],
		    "order": [[2, 'asc']],
		    "initComplete": function (settings, json) {    	
		    	$('.dataTables_filter').remove();
		    }
		});
	}	
});

$(document).on('click','.btn-choose-supplier',function(){
	let data = table_choose_supplier_product_mapping.row( $(this).parents('tr')).data();
	let supplierId = data.supplierId.trim();
	let productTypeId = $("#txt_productTypeId").val().trim();
	chooseSupplier(supplierId,productTypeId);
});

function chooseSupplier(supplierId, productTypeId) {
	
	var objMapping = {
		productTypeId : productTypeId,
		supplierId : [{
			supplierId : supplierId
		}],
		statusId : '1'
	};
	$.ajax({
		url : contextPath + '/api/supplier_product_type_mapping/insert_supplier_product_to_mapping/'+JSON.stringify(objMapping),
		type : 'POST',
		beforeSend: function(){ 
			BlockUi.blockPosition('#dialog_choose_supplier');
			BlockUi.blockPosition('#portlet_supplier_mapping_product'); 
		},
		success : function(data, msg, jqXHR) {
			if(data.result){
				customMessageDialog("success", "ข้อความ", data.message, null);
				$('#dialog_choose_supplier').modal('hide');
			}
			else
			{
				customMessageDialog("error", "ข้อความ", data.message, null);
			}	    	
		},
		error : function(jqXHR, textStatus, errorThrown) {			
			var data = JSON.parse(jqXHR.responseText);
			customMessageDialog("error", "ข้อความ", data.message, null);
		},
		  complete: function(){ 
			  setTimeout(function(){ 
				  BlockUi.unBlockPosition('#dialog_choose_supplier');
				  BlockUi.unBlockPosition('#portlet_supplier_mapping_product');				  
				  table_choose_supplier_product_mapping.draw();
			  }, 500)
		  },
	});  
}

$(document).on('click','.btn-remove-supplier-mapping',function(){
	let data = tableproductmappingsupplier.row( $(this).parents('tr')).data();
	let supplierId = data.supplierId.trim();
	let productTypeId = $("#txt_productTypeId").val().trim();
	unChooseSuplier(supplierId,productTypeId);
});

function unChooseSuplier(supplierId, productTypeId)
{
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการลบข้อมูลซัพพลายเออร์จากสินค้าใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if (isTrue){			
			var objMapping = {
				productTypeId : productTypeId,
				supplierId : [{
					supplierId : supplierId
				}]
			};
			
			$.ajax({
				url : contextPath + '/api/supplier_product_type_mapping/delete_supplier_product_to_mapping/'+JSON.stringify(objMapping),
				type : 'DELETE',
				beforeSend: function(){ BlockUi.blockPage(); },
				success : function(data, msg, jqXHR) {
					if(data.result){
						customMessageDialog("success", "ข้อความ", data.message, null);
					}
					else
					{
						customMessageDialog("error", "ข้อความ", data.message, null);
					}
			    	
				},
				error : function(jqXHR, textStatus, errorThrown) {
					var data = JSON.parse(jqXHR.responseText);
					customMessageDialog("error", "ข้อความ", data.message, null);
				},
				  complete: function(){ 
					  setTimeout(function(){ 
						  BlockUi.unBlockPage();
						  tableproductmappingsupplier.draw();
					  }, 500)
				  },
			});
		}
	});	
}

function loadSupplierMappingProduct(){
	tableproductmappingsupplier = $('#table_supplier_mapping_product').DataTable({
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": contextPath + "/api/supplier_product_type_mapping/datatable/supplier_list_mapping_by_product/"+JSON.stringify({productTypeId : $("#txt_productTypeId").val().trim()}),
	        "type": 'POST',
	        beforeSend: function(){ BlockUi.blockPosition('#portlet_supplier_mapping_product'); },
	        contentType: "application/json",
		    complete : function(){
		    	BlockUi.unBlockPosition('#portlet_supplier_mapping_product');
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
	    	{ "data": null, "width": "2px" },
	    	{ "data": "supplierId","width": "1px"},
	    	{ "data": "supplierCompany", name: "supplierCompany" },
	    	{ "data": "statusId",
	            "render": function (data, type, row) {
	            	
	            	var dpStatus = '<span class="label label-sm label-success" style="font-size:12px;"> ใช้งาน </span>';
					if (data == '0' ){
						dpStatus = '<span class="label label-sm label-default" style="font-size:12px;"> ไม่ใช้งาน </span>';
					}
					
	                return dpStatus;
	            },
	            "width": "20px" 
	    			
	    	}	    		    	
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
	        	"targets": [1,2],
	            "visible": false
	        },
	        {
	        	"orderable": false,
	            "targets": [0, 1, 2, 3, 4, 5]
	        },
	        {
	            "searchable": true,
	            "orderable": false,
	            "targets": [1],
	            "name":"p.product_Id|="
	        },
	        {
	            "targets": 5,	            
	            "orderable": false,
	            "render": function (data, type, row) {
	            	
	            	var btnAction = '<a href="javascript:void(0);" class="btn btn-xs blue btn-remove-product-supplier btn-remove-supplier-mapping" style="font-size:12px;">';
	            	btnAction += 		'<i class="icon-trash "></i>';
	    			btnAction += 		'ลบข้อมูล';
	    			btnAction += 	'</a>';	    			
	               
	                return btnAction;
	            },
	            "width":"80px"
	        }
	    ],	    
	    "order": [[2, 'asc']],
	    "initComplete": function (settings, json) {
	    	
	    	$('.dataTables_filter').remove();
	    }
	});
}

function searchSupplier() {
	tableproductmappingsupplier.columns(3).search($('#txt_SupplierCompany').val());
	tableproductmappingsupplier.draw();    
}

function searchChooseSupplier() {
	table_choose_supplier_product_mapping.columns(3).search($('#txt_Choose_Supplier_SupplierCompany').val());
	table_choose_supplier_product_mapping.draw();    
}

function clearSupplier() {
    
	$('#txt_SupplierCompany').val('');
	tableproductmappingsupplier.columns(3).search('');
	tableproductmappingsupplier.draw();
}

function clearChooseSupplier() {
    
	$('#txt_Choose_Supplier_SupplierCompany').val('');
	table_choose_supplier_product_mapping.columns(3).search('');
	table_choose_supplier_product_mapping.draw();
}



function deleteSupplier(supplierId)
{
	ConfirmModal.setupModal("warning","ยืนยัน", "ต้องการลบข้อมูลใช่หรือไม่?");	
	ConfirmModal.confirmResult(function(isTrue){
		if (isTrue){			
			$.ajax({
			  url : contextPath + "/api/product/delete_supplier/" +$("#txt_productTypeId").val()+ "/"+ supplierId,
	  		      type: 'DELETE',
	  		      beforeSend: function(){ BlockUi.blockPage(); },
	  		      success : function(data, textStatus, jqXHR) {
	  		    	if(data.result){
						customMessageDialog("success", "ข้อความ", data.message, null);
					}
					else
					{
						customMessageDialog("error", "ข้อความ", data.message, null);
					}
	  			  },
	  			  error: function (jqXHR, textStatus, errorThrown) {
	  				var data = JSON.parse(jqXHR.responseText);
					customMessageDialog("error", "ข้อความ", data.message, null);
	  			  },
				  complete: function(){ 
					  setTimeout(function(){ 
						  BlockUi.unBlockPage();
						  tableproductmappingsupplier.draw();
					  }, 1000)
				  },
	  			});			
		}
	});	
}







$('#txt_SupplierCompany').keyup(function(event) {
    if (event.keyCode === 13) {
    	searchSupplier();
    }
});

$('#txt_Choose_Supplier_SupplierCompany').keyup(function(event) {
    if (event.keyCode === 13) {
    	searchChooseSupplier();
    }
});
*/