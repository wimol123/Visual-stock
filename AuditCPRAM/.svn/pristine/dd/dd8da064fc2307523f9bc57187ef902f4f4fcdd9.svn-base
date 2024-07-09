
$(function(){
	if($('#information_supplier').val() != "" && $('#information_supplier').val() != undefined ){
		let stringInformationSupplier = URI.decode($('#information_supplier').val());
		console.log(stringInformationSupplier);
		objInformationDetail = JSON.parse(stringInformationDetail);
		if(objInformationDetail.acceptStatus == "Y"){
			customMessageDialog('success', 'การบันทึกข้อมูล', 'บันทึกข้อมูลสำเร็จ', null);
		}
	}
	loadInformationToTable();
	$('.input-group.datepicker-effective').datepicker({
		language: "th",
		format: 'dd/mm/yyyy',
		orientation: "bottom auto",
		autoclose: true
	});
});

function loadInformationToTable(){
	var informationTable = $('#table_information').DataTable({
		"proccessing": true,
		"serverSide": true,
		"searching": true,
		"ajax": {
	        "url": contextPath + "/api/information/datatable/information_list_by_supplier",
	        "type": 'POST',
	        contentType: "application/json",
	        beforeSend : function(arr, $form, options){
		    	BlockUi.blockPage();
		    },
		    complete : function(){
		    	setTimeout(function(){
		    		BlockUi.unBlockPage();
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
	    	{ "data": null, "width": "30px" },
	    	{ "data": "informationId.informationTitle", "name": "informationTitle"},
	    	{ "data" : "informationId.sendDate", "name" : "sendDate"},
	    	{
	            "data": "acceptStatus",
	            "name": "acceptStatus",
	            "render": function (data, type, row) {

					dpStatus = '<span class="label label-sm label-success" style="font-size:12px;"> รับทราบแล้ว </span>';
					if (data != 'Y' ){
						dpStatus = '<span class="label label-sm label-default" style="font-size:12px;"> ยังไม่รับทราบ </span>';
					}
					
	                return dpStatus;
	            },
	            "width": "120px" 
	    	},
	    	{ "data" : "acceptDate"}
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
	            "searchable": true,
	            "orderable": false,
	        	"targets": [1,2,3,4],
	        	"defaultContent":""
	        },
	        {
	            "targets": 5,
	            "orderable": false,
	            "render": function (data, type, row) {
//	            	var btnAction = '<div class="btn-group">';
//	            	btnAction += '<button class="btn btn-xs blue dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false" style="font-size:12px;"> เลือก <i class="fa fa-angle-down"></i></button>';
//	    			btnAction += '<ul class="dropdown-menu pull-left" role="menu">';
//	    			btnAction += 	'<li><a href="javascript:void(0);" class = "editChecklist">';
//	    			btnAction +=   		'<i class="icon-pencil"></i> ดูรายละเอียด </a>';
//	    			btnAction +=	'</li>';
//	    			/*btnAction +=	'<li><a href="javascript:void(0);" class = "removeChecklist">';
//	    			btnAction +=		'<i class="icon-trash"></i> ลบข้อมูล </a>';
//	    			btnAction +=	'</li>';*/
//	                btnAction += '</ul>';
//	                btnAction +='</div>';
	            	let btnAction = '<button type="button" class="btn btn-xs blue btn-select-information"> รายละเอียด</button>';
	               
	                return btnAction;
	            },
	            "width": "100px" 
	        }
	    ],
	    "order": false,
	    "initComplete": function (settings, json) {	    	
	    	$('.dataTables_filter').remove();
//	    	$('[data-toggle="tooltip"]').each(function(i,v){
//				$(this).tooltip();
//			});
//	    	$('#table_information').DataTable().on( 'draw', function () {
//	    		$('[data-toggle="tooltip"]').each(function(i,v){
//					$(this).tooltip();
//				});
//	    	});
	    	
	    } 
	    
	    
	});
	
}

$(document).on('click','.btn-select-information',function(e){
//	let data = $('#table_search_location_produce').DataTable().row( $(this).parents('tr') ).data();	
//	$('#supplierLocation_Dialog').typeahead('val',data.locationName.trim());
//	$('#LocationID_dialog').val(data.id);
//	$('#dialog_search_location').modal('hide');
	let data = $('#table_information').DataTable().row( $(this).parents('tr') ).data();	
	console.log("data",data);
	let json = {
		informationId : data.informationId.informationId,
		typeDetail : "supplier",
		acceptStatus : data.acceptStatus,
		acceptDate : data.acceptDate,
		acceptBy : data.acceptBy.username
	}
	$('#informationDetail').val(URI.encode(JSON.stringify(json)));
	$('#informationDetail').closest('form').submit();
});

$('#btn_clear').on('click',function(){
	$('#information_name').val('');
	$("#select_status").val($("#select_status option:first").val());
	$('#sendDate_textbox').val('');
	search();
});


$('#btn_search').on('click',function(){
	search();
});

$("#information_name").keyup(function(event) {
    if (event.keyCode === 13) {
    	search();
    }
});

$("#select_type").on('change',function(){
	console.log("test",$("#select_type").val());
	if($("#select_type").val() == "S"){
		$(".div-text-supplier").show();
	}else{
		$(".div-text-supplier").hide();
	}
});

function search(){
	let informationName = $('#information_name').val().trim();
	let acceptStatus = $('#select_status').val().trim();
	let sendDate = $('#sendDate_textbox').val().trim();
	
	$('#table_information').DataTable().columns(1).search(informationName);
	$('#table_information').DataTable().columns(2).search(sendDate);
	$('#table_information').DataTable().columns(3).search(acceptStatus);
	$('#table_information').DataTable().draw();
}

