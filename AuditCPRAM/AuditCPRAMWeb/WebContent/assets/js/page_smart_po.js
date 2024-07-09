const supplierGroup = ["3", "8"];
const purchasingGroup = "5";
var userGroupId = $('#userGroupId').val();
var isSupplier = false;
var poAcceptedList = [];
var countSupplierAccepted = 0;
var currentStatusId = 0;

const loadingOverlay = document.getElementById('loading-overlay');

$(function() {
	chSession();
	if (supplierGroup.includes(userGroupId)) {
		isSupplier = true;
		$('.supplier-hide').hide();
	}
	
	$('.form-input-datetime').datetimepicker({
		format: 'DD/MM/YYYY HH:mm',
		widgetPositioning: {
       		horizontal: "auto",
			vertical: "bottom"
		}
	});
	
	loadDataTable();
	loadStatusPO();
	loadPoAcceptedList();
	loadPoDetailTable();
	loadPoHistoryTable();
});

function chSession() {
	let url = contextPath + "/api/user/get_permission_menu";
	$.ajax({
		url: url,
		type: 'POST',
		contentType: "application/json",
		data: "smart_po.jsp",
		success: function(data) {
			if (!data.result) {
				window.location = "/auditsupplier/home.jsp";
			}
		}
	});
}

function loadStatusPO() {
	ajaxProcess.submit(
		contextPath + "/api/smart_po/get_po_status_list/" + userGroupId, 
		'GET', 
		null, 
		'', 
		(data) => {
			if (data.result && data.message != null && data.message != "") {
				let listStatus = JSON.parse(data.message.trim());
				$(listStatus).each(function(i, v) {
					$('#po_status').append(
						'<option value="' + v.poStatusId + '" ' + v.selected + '> ' + 
						v.poStatusName + ' </option>');
				});
				search();
			}
		}
	);
}

function loadPoAcceptedList() {
	ajaxProcess.submit(
		contextPath + "/api/smart_po/get_po_accepted_list", 
		'GET', 
		null, 
		'', 
		(data) => {
			if (data.result && data.message != null && data.message != "") {
				poAcceptedList = JSON.parse(data.message.trim());
			}
		}
	);
}

function loadDataTable() {
	$('#smart_po_table').DataTable({
		proccessing: true,
		serverSide: true,
		searching: true,
	    ajax: {
	        url: contextPath + "/api/smart_po/search_po",
	        type: 'POST',
	        contentType: "application/json",
	        beforeSend: function() {
		    	BlockUi.blockPage();
		    },
		    complete: function() {
		    	BlockUi.unBlockPage();
		    },
	        data: function(d) {
	        	return JSON.stringify(d);
	        }
	    },
		language: {
	        url: "assets/global/plugins/datatables/Thai.json"
	    },
	    createdRow: function (row, data, dataIndex) {
		    $(row).addClass('po-status-' + data.poAcceptedId);
		},
	    columns: [
	    	{ "data": "selectPo",               name: "selectPo"},
	    	{ "data": null },
	    	{ "data": "poNo", 					name: "poNo" },
	    	{ "data": "supplier", 				name: "supplier" },
	    	{ "data": "createDate",				name: "createDate" },
	    	{ "data": "poStatusName", 			name: "poStatusName" },
	    	{ "data": "isMailSent", 			name: "isMailSent" },
	    	{ "data": "isViewed", 				name: "isViewed" },
	    	{ "data": "isPrinted", 				name: "isPrinted" },
	    	{ "data": "poAcceptedId",			name: "poAcceptedId" },
	    	{ "data": null },
	    	{ "data": "poId", 					name: "poId" },
	    	{ "data": "poStatusId", 			name: "poStatusId" },
	    	{ "data": "poCreateBy", 			name: "poCreateBy" },
	    	{ "data": "createDateStart",		name: "createDateStart" },
	    	{ "data": "createDateEnd",			name: "createDateEnd" },
	    	{ "data": "countSupplierAccepted",	name: "countSupplierAccepted" }
	    ],
	    columnDefs: [
				       {
	            "targets": [0],
	            "render": function(data, type, row, meta) {
					var count = meta.settings._iDisplayStart + meta.row +1;
					console.log(count)

						var gen_html = '<input type= "checkbox" ';
						gen_html += 'id="selectPo' + '_' + row.poNo + '_' + row.poId + '" ';
						gen_html += 'class="selectPo"';
						gen_html += 'name = "selectPo"';
	                	return gen_html + '/> ';
	                		
	              }  ,
	              
	             // "visible": !isSupplier
	            },
	                
			{
				"className": "dt-right",
				"targets": [1],
				"searchable": false,
	            "orderable": false,
	            "render": function(data, type, full, meta) {
	                return meta.settings._iDisplayStart + meta.row + 1;
	            }
			},
			{
				"className": "dt-center",
	            "targets": [6,7,8],
	            "searchable": false,
	            "orderable": false,
	            "render": function(data, type, full, meta) {
					if (data == 'Y') {
						return '<i class="fa fa-check-circle fa-lg" style="color: #1bd30d;"></i>';
					} else {
	                	return '<i class="fa fa-times-circle fa-lg" style="color: #f70808;"></i>';
	                }
	            },
	           // "visible": !isSupplier
	        },
	        {
				"className": "dt-center",
	            "targets": [9],
	            "searchable": false,
	            "orderable": false,
	            "render": function(data, type, row, meta) {
					var gen_html = '-';
					$(poAcceptedList).each(function(i, v) {
                		if (data == v.poAcceptedId) {
                			gen_html = v.poAcceptedName;
                		}
                	});
                	return gen_html;
	            },
	            "visible": !isSupplier
	        },
	        {
	            "targets": 10,
	            "searchable": false,
	            "orderable": false,
	            "width": "45px",
	            "render": function(data, type, row, meta) {
					var btnAction = '' +
						'<div class="btn-group">' +
						'	<button class="btn btn-xs blue dropdown-toggle" type="button" ' +
						'		data-toggle="dropdown" aria-expanded="true" ' +
						'		style="font-size:12px;"> เลือก<i class="fa fa-angle-down"></i>' +
						'	</button>' +
						'	<ul class="dropdown-menu pull-left" role="menu">';
					if (isSupplier) {
						btnAction = btnAction +
							'	<li>' +
							'		<a href="javascript:void(0);" class="view_po_btn">' +
							'			<i class="icon-magnifier"></i> ดู ' +
							'		</a>' +
							'	</li>' +
							'	<li>' +
							'		<a href="javascript:void(0);" class="print_po_btn">' +
							'			<i class="fa fa-print"></i>พิมพ์ ' +
							'		</a>' +
							'	</li>';
					} else {
						btnAction = btnAction +
							'	<li>' +
							'		<a href="javascript:void(0);" class="view_po_btn">' +
							'			<i class="icon-magnifier"></i> ดู ' +
							'		</a>' +
							'	</li>' +
							'	<li>' +
							'		<a href="javascript:void(0);" class="send_mail_btn">' +
							'			<i class="fa fa-paper-plane"></i> ส่งเมล์' +
							'		</a>' +
							'	</li>';
					}
					
					btnAction = btnAction +
						'	</ul>' +
						'</div>';
	               
	                return btnAction;
	            }
	        },
	        {
				"className": "dt-center",
	            "targets": [0],
	            "searchable": false,
	            "orderable": false
	        },
	        {"className": "dt-center",
	            "targets": [2],
	            "searchable": true,
	            "orderable": false
	        },

	        {
	            "targets": [3],
	            "searchable": true,
	            "orderable": false,
	            "visible": !isSupplier
	        },
	        {
				"className": "dt-center",
	            "targets": [4,5],
	            "searchable": false,
	            "orderable": false
	        },
	        {
	            "targets": [11,12,13,14,15,16],
	            "searchable": true,
	            "orderable": false,
	            "visible": false
	        }
	    ],
	    "order": [],
	    "initComplete": function() {
	    	$('.dataTables_filter').remove();
	    }
	});
}




$('#btn_search').on('click', function() {
	search();
});
$('#btn_search').on('click', function(event) {
    if (event.keyCode === 13) {
        // Perform actions specific to Enter key press (e.g., additional filtering, validation)
        search(); // Call search function after Enter key actions
    } else {
        search(); // Call search function for regular click
    }
});

$("#supplier").keyup(function(event) {
    if (event.keyCode === 13) {
        search();
    }
});
$("#po_no").keyup(function(event) {
    if (event.keyCode === 13) {
        search();
    }
});

$("#po_create_by").keyup(function(event) {
    if (event.keyCode === 13) {
        search();
    }
});



$('#btn_clear').on('click', function() {
	clearSearch();
});

$('#btn_export').on('click', function() {
	exportExcel();
});

$(document).on('click', '.view_po_btn', function() {
	let data = $('#smart_po_table').DataTable().row($(this).parents('tr')).data();
	countSupplierAccepted = data.countSupplierAccepted;
	currentStatusId = data.poStatusId;
	switch(countSupplierAccepted) {
  	case 0:
  		$('#check_all_1').show();
	    $('#check_all_2').hide();
	    $('#check_all_3').hide();
  		if (!isSupplier || currentStatusId != 1) $('#all_accepted_1').prop("disabled", true);
  		else $('#all_accepted_1').prop("disabled", false);
	    break;
  	case 1:
  		$('#check_all_1').show();
	    $('#check_all_2').show();
	    $('#check_all_3').hide();
  		$('#all_accepted_1').prop("disabled", true);
  		if (!isSupplier || currentStatusId != 1) $('#all_accepted_2').prop("disabled", true);
  		else $('#all_accepted_2').prop("disabled", false);
	    break;
	case 2:
		$('#check_all_1').show();
	    $('#check_all_2').show();
	    $('#check_all_3').show();
	    $('#all_accepted_1').prop("disabled", true);
	    $('#all_accepted_2').prop("disabled", true);
	    if (!isSupplier || currentStatusId != 1) $('#all_accepted_3').prop("disabled", true);
	    else $('#all_accepted_3').prop("disabled", false);
	    break;
  	default:
  		$('#check_all_1').show();
	    $('#check_all_2').show();
	    $('#check_all_3').show();
  		$('#all_accepted_1').prop("disabled", true);
	    $('#all_accepted_2').prop("disabled", true);
	    $('#all_accepted_3').prop("disabled", true);
	}
	
	loadPO(data.poId);
	$('#collapseHistory').removeClass('in');
	$('#dialog_po_detail').modal('show');
});


$(document).on('click', '.view_po', function() {
	console.log(userGroupId)

	$('#dialog_po').modal('show');
});


$(document).on('click', '.print_po_btn', function() {
	let data = $('#smart_po_table').DataTable().row($(this).parents('tr')).data();
	$('#print_po_no').val(data.poNo);
	$("#print_po_form").submit();
	window.location.reload();
});

$('#print_all_po_btn').on('click',  function() {
	    var pos = [];
		$(".selectPo:checked").each(function(){
			var no = $(this).attr('id').split("_")[1];			
			pos.push(no);					
		});
		if(pos.length <= 0) {
			alert("ไม่มีข้อมูลที่จะดาวน์โหลด");
		}
		else if(pos.length == 1) {
			$('#print_po_no').val(pos[0]);
	        $("#print_po_form").submit();
		}
		else {
			console.log("f");
			var poString = pos.join(",");
		    $('#print_all_po_no').val(poString);
	        $("#print_all_po_form").submit();
	        window.location.reload();
		}
		//url = contextPath + '/api/smart_po/print_all_po';
		//ajaxProcess.submit(url, 'POST', pos);		
		console.log(pos);
		/*var poString = pos.join(",");
		console.log(poString);
		$('#print_all_po_no').val(poString);
	    $("#print_all_po_form").submit();
	    window.location.reload();*/
});






/*function downloadPdf(pdfBlob, name) {
  const url = window.URL.createObjectURL(pdfBlob);
  const link = document.createElement('a');
  link.href = url;
  link.download = name;
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
  window.URL.revokeObjectURL(url); // Clean up memory leak
}
async function getPdfBlob(url) {
  try {
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    const blob = await response.blob();
    return blob;
  } catch (error) {
    console.error('Error fetching PDF:', error);
    throw error; // Re-throw the error for handling at the call site
  }
}
async function multiDownload(urls, { rename = () => {} } = {}) {
	
  if (!urls || !Array.isArray(urls)) {
    throw new Error('`urls` must be a non-empty array');
  }

  for (const [index, url] of urls.entries()) {
    // Assuming `getPdfBlob(url)` returns a Promise that resolves to a PDF Blob;
    const pdfBlob = await getPdfBlob(url); 
    const filename = url;/* Logic to generate a filename based on `url` or index 
    downloadPdf(pdfBlob, filename);
  }
}
document.querySelector('#print_btn').addEventListener('click', async event => {
  var pos = [];
  $(".selectPo:checked").each(function(){
    var no = $(this).attr('id').split("_")[1];
    var po = "/download/"+no+"pdf";
    pos.push(po);
    console.log(po);
  });

  // Assuming `pos` now contains an array of URL patterns for PDFs
  await multiDownload(pos);
});*/

$(document).on('click', '.send_mail_btn', function() {
	let data = $('#smart_po_table').DataTable().row($(this).parents('tr')).data();
	url = contextPath + '/api/smart_po/send_mail';
	ajaxProcess.submit(url, 'POST', data, '', (data) => {
		if (data.result) {
			search();
		}
	});	
});

function search() {
	$('#smart_po_table').DataTable().columns(2).search($('#po_no').val().trim());
	$('#smart_po_table').DataTable().columns(3).search($('#supplier').val().trim());
	$('#smart_po_table').DataTable().columns(12).search($('#po_status').val().trim());
	$('#smart_po_table').DataTable().columns(13).search($('#po_create_by').val().trim());
	$('#smart_po_table').DataTable().columns(14).search($('#create_date_start').val().trim());
	$('#smart_po_table').DataTable().columns(15).search($('#create_date_end').val().trim());
	$('#smart_po_table').DataTable().draw();
	
}

function clearSearch() {
	$('#po_no').val('');
	$('#supplier').val('');
	$('#po_status').val('');
	$('#po_create_by').val('');
	$('#create_date_start').val('');
	$('#create_date_end').val('');
}

function exportExcel() {
	$('#export_supplier').val($('#supplier').val().trim());
	$('#export_po_no').val($('#po_no').val().trim());
	$('#export_po_status').val($('#po_status').val().trim());
	$('#export_po_create_by').val($('#po_create_by').val().trim());
	$('#export_create_date_start').val($('#create_date_start').val().trim());
	$('#export_create_date_end').val($('#create_date_end').val().trim());
	$("#export_po_form").submit();
}

function loadPoDetailTable() {
	$('#po_detail_table').DataTable({
		searching: false,
		paging: false,
		info: false,
		language: {
	        url: "assets/global/plugins/datatables/Thai.json"
	    },
	    columns: [
	    	{ "data": "item", 				name: "item" },
	    	{ "data": "materialCode",		name: "materialCode" },
	    	{ "data": "materialName", 		name: "materialName" },
	    	{ "data": "deliveryDate", 		name: "deliveryDate" },
	    	{ "data": "quantity", 			name: "quantity" },
	    	{ "data": "unit", 				name: "unit" },
	    	{ "data": "netPrice", 			name: "netPrice" },
	    	{ "data": "netValue",			name: "netValue" },
	    	{ "data": "suppAccepted1",		name: "suppAccepted1" },
	    	{ "data": "suppNote1",			name: "suppNote1" },
	    	{ "data": "purResponse1",		name: "purResponse1" },
	    	{ "data": "suppAccepted2",		name: "suppAccepted2" },
	    	{ "data": "suppNote2",			name: "suppNote2" },
	    	{ "data": "purResponse2",		name: "purResponse2" },
	    	{ "data": "suppAccepted3",		name: "suppAccepted3" },
	    	{ "data": "suppNote3",			name: "suppNote3" },
	    	{ "data": "poDetailId",			name: "poDetailId" },
	    	{ "data": "poId",				name: "poId" }
	    ],
	    columnDefs: [

			{
				"targets" :[0],
				"render" : function( set,type, row) {
					var stringItem = row.item

					return stringItem.slice(2)

				}
			},
			{
				"targets" :[4],
				"render" : function( set,type, row) {
					var stringQuantity = row.quantity

					return stringQuantity.slice(0,-1)

				}
			},
			{
				"className": "dt-right",
				"targets": [4,6,7],
			},
						{
				"targets" :[0],
				"render" : function(data, type, row) {
					console.log(row)
					console.log(data)
				}
			},
			{
				"className": "dt-center",
				"targets": [0,1,3,5,8,9,10,11,12,13,14,15]

			},
	        {
	            "targets": [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17],
	            "searchable": false,
	            "orderable": false
	        },
	        {
	            "targets": [16,17],
	            "visible": false
	        },
	        {
	            "targets": [8,11,14],
	            "render": function(data, type, row, meta) {
					var count = 0;
					switch(meta.col) {
						case 8 : count = 1; break;
						case 11 : count = 2; break;
						case 14 : count = 3; break;
						default : count = 0;
					}
					
					if (countSupplierAccepted >= (count - 1)) {
						var gen_html = '<input type="checkbox" ';
						gen_html += 'id="suppAccepted' + count + '_' + row.poDetailId + '_' + row.poId + '" ';
						gen_html += 'class="suppAccepted'+ count + '" ';
						gen_html += 'onclick="checkAll('+ count + ')" ';
						if (data == 'Y') gen_html += 'checked ';
						console.log(data)
						if (countSupplierAccepted != (count - 1) || !isSupplier || currentStatusId != 1) 
							gen_html += 'disabled ';
	                	return gen_html + '/> OK ';
	                } else return '';
	            }
	        },
	        {
	            "targets": [9,12,15],
	            "render": function(data, type, row, meta) {
					var count = 0;
					switch(meta.col) {
						case 9 : count = 1; break;
						case 12 : count = 2; break;
						case 15 : count = 3; break;
						default : count = 0;
					}
					
					if (countSupplierAccepted >= (count - 1)) {
						console.log(data)
						var gen_html = '<textarea rows="3" maxlength="1000" ';
						gen_html += 'id="suppNote' + count + '_' + row.poDetailId + '_' + row.poId + '" ';
						if (countSupplierAccepted != (count - 1) || !isSupplier || currentStatusId != 1) 
							gen_html += 'disabled ';
	                	return gen_html + '>' + ((data == null) ? '' : data) + '</textarea>';
	                } else return '';
	            }
	        },
	        {
	            "targets": [10,13],
	            "render": function(data, type, row, meta) {
					var count = 0;
					switch(meta.col) {
						case 10 : count = 1; break;
						case 13 : count = 2; break;
						default : count = 0;
					}
					
					if (countSupplierAccepted >= (count - 1)) {
						var gen_html = '<textarea rows="3" maxlength="1000" ';
						gen_html += 'id="purResponse' + count + '_' + row.poDetailId + '_' + row.poId + '" ';
						if (countSupplierAccepted != count || purchasingGroup != userGroupId || currentStatusId != 2) 
							gen_html += 'disabled ';
	                	return gen_html + '>' + ((data == null) ? '' : data) + '</textarea>';
	                } else return '';
	            }
	        }
	    ],
	    "order": [],
	    "initComplete": function() {
	    	$('.dataTables_filter').remove();
	    	$('[data-toggle="tooltip"]').each(function() {
				$(this).tooltip();
			});
	    	$('#po_detail_table').DataTable().on( 'draw', function () {
	    		$('[data-toggle="tooltip"]').each(function() {
					$(this).tooltip();
				});
	    	});
	    }
	});
}

function selectAll(count) {
	if ($("#all_accepted_" + count).is(":checked")) {
		$(".suppAccepted" + count).prop("checked", true);
	} else {
		$(".suppAccepted" + count).prop("checked", false);
	}
}

function checkAll(count) {
	if ($(".suppAccepted" + count).length == $(".suppAccepted" + count + ":checked").length) {
		$("#all_accepted_" + count).prop("checked", true);
	} else {
		$("#all_accepted_" + count).prop("checked", false);
	}
}
function selectAllPo() {
	if ($("#all_selected").is(":checked")) {
		$(".selectPo").prop("checked", true);

	}
	else {
		$(".selectPo").prop("checked", false);
	}

}

function loadPoHistoryTable() {
	$('#po_history_table').DataTable({
		searching: false,
		paging: false,
		info: false,
		language: {
	        url: "assets/global/plugins/datatables/Thai.json"
	    },
	    columns: [
	    	{ "data": null },
	    	{ "data": "description", 	name: "description" },
	    	{ "data": "createDate",		name: "createDate" },
	    	{ "data": "createByName", 	name: "createByName" },
	    	{ "data": "note", 			name: "note" }
	    ],
	    columnDefs: [
			{
				"className": "dt-right",
				"targets": [0]
			},
			{
				"className": "dt-center",
				"targets": [2,3]
			},
	        {
	            "targets": [0,1,2,3,4],
	            "searchable": false,
	            "orderable": false,
	        },
	        {
	            "targets": 0,
	            "searchable": false,
	            "orderable": false,
	            "render": function(data, type, full, meta) {
	                return meta.settings._iDisplayStart + meta.row + 1;
	            }
	        }
	    ],
	    "order": [],
	    "initComplete": function() {
	    	$('.dataTables_filter').remove();
	    	$('[data-toggle="tooltip"]').each(function() {
				$(this).tooltip();
			});
	    	$('#po_detail_table').DataTable().on( 'draw', function () {
	    		$('[data-toggle="tooltip"]').each(function() {
					$(this).tooltip();
				});
	    	});
	    }
	});
}

function loadPO(poId) {	
	ajaxProcess.submit(
		contextPath + "/api/smart_po/get_po_info/" + poId, 
		'GET', 
		null, 
		'', 
		(data) => {
			if (data.result && data.message != null && data.message != "") {
				let po = JSON.parse(data.message.trim());
				$('#poNo').text(po.poNo);
				$('#print_po_no').val(po.poNo);
				$('#statusName').text(po.poStatusName);
				$('#supplierName').text(po.supplier);
				$('#createDate').text(po.createDate);
				$('#poCreateDate').text(po.poCreateDate);
				$('#createBy').text(po.poCreateBy);
				loadPoDetail(poId);
				loadPoHistory(poId);
				
				if (po.poStatusId != 2 || purchasingGroup != userGroupId) $('#btn_response').hide();
				else $('#btn_response').show();
				
				if (po.poStatusId != 1 || !isSupplier) $('#btn_accepted').hide();
				else $('#btn_accepted').show();
			}
		}
	);
}

function loadPoDetail(poId) {
	ajaxProcess.submit(
		contextPath + "/api/smart_po/get_po_detail/" + poId, 
		'GET', 
		null, 
		'', 
		(data) => {
			if (data.result && data.message != null && data.message != "") {
				$('#po_detail_table').dataTable().fnClearTable();
				$('#po_detail_table').dataTable().fnAddData(JSON.parse(data.message.trim()));
				checkAll(1);
				checkAll(2);
				checkAll(3);
			}
		}
	);
}

function loadPoHistory(poId) {
	ajaxProcess.submit(
		contextPath + "/api/smart_po/get_po_history/" + poId, 
		'GET', 
		null, 
		'', 
		(data) => {
			if (data.result && data.message != null && data.message != "") {
				$('#po_history_table').dataTable().fnClearTable();
				$('#po_history_table').dataTable().fnAddData(JSON.parse(data.message.trim()));
			}
		}
	);
}

$('#btn_print').on('click', function() {
	$("#print_po_form").submit();
});

$('#btn_accepted').on('click', function() {
	var result = true;
	var count = countSupplierAccepted + 1;
	var poDetails = [];
	
	if (count == 3 && $(".suppAccepted" + count + ":checked").length != $(".suppAccepted" + count).length) {
		result = false;
		alert("à¸à¸²à¸£à¸•à¸­à¸šà¸£à¸±à¸šà¸„à¸£à¸±à¹‰à¸‡à¸—à¸µà¹ˆ 3 à¸•à¹‰à¸­à¸‡ OK à¸—à¸¸à¸à¸£à¸²à¸¢à¸à¸²à¸£ \nà¸«à¸²à¸à¸¡à¸µà¸›à¸£à¸°à¹€à¸”à¹‡à¸™à¹€à¸žà¸´à¹ˆà¸¡à¹€à¸•à¸´à¸¡à¸à¸£à¸¸à¸“à¸²à¸•à¸´à¸”à¸•à¹ˆà¸­à¸«à¸™à¹ˆà¸§à¸¢à¸‡à¸²à¸™à¸ˆà¸±à¸”à¸‹à¸·à¹‰à¸­ CPRAM");
	} else {
		$(".suppAccepted" + count).each(function() {
			var detailId = $(this).attr('id').split("_")[1];
			var id = $(this).attr('id').split("_")[2];
			if (!$(this).is(":checked") && $("#suppNote" + count + '_' + detailId + "_" + id).val().length <= 0) {
				result = false;
				alert("กรุณาระบุหมายเหตุทุกรายการที่ไม่ OK");
				$("#suppNote" + count + '_' + detailId + "_" + id).focus();
				return false;
			} else {
				var poDetail = { poId: id, poDetailId: detailId, count: count };
				poDetail["suppAccepted" + count] = $(this).is(":checked") ? "Y" : "N";
				poDetail["suppNote" + count] = $("#suppNote" + count + '_' + detailId + "_" + id).val();
				poDetails.push(poDetail);
			}
		});
	}
	

	
	if (result) {
		url = contextPath + '/api/smart_po/supplier_accepted';
		ajaxProcess.submit(url, 'POST', poDetails, '', (data) => {
			if (data.result) {
				$('#dialog_po_detail').modal('hide');
				search();
			}
		});	
	};
});
$('#btn_response').on('click', function() {
	var result = true;
	var count = countSupplierAccepted;
	var poDetails = [];
	$(".suppAccepted" + count).each(function() {
		var detailId = $(this).attr('id').split("_")[1];
		var id = $(this).attr('id').split("_")[2];
		if (!$(this).is(":checked") && $("#purResponse" + count + '_' + detailId + "_" + id).val().length <= 0) {
			result = false;
			alert("กรุณาระบุผลการตอบกลับทุกรายการที่ไม่ OK");
			$("#purResponse" + count + '_' + detailId + "_" + id).focus();
			return false;
		} else {
			var poDetail = { poId: id, poDetailId: detailId, count: count };
			poDetail["purResponse" + count] = $("#purResponse" + count + '_' + detailId + "_" + id).val();
			poDetails.push(poDetail);
		}
	});
	
	if (result) {
		url = contextPath + '/api/smart_po/purchasing_response';
		ajaxProcess.submit(url, 'POST', poDetails, '', (data) => {
			if (data.result) {
				$('#dialog_po_detail').modal('hide');
				search();
			}
		});	
	};
});
$('#btn_accepted_all').on('click', function() {
	var result = true;
	var pos = [];	
	if ($(".suppAccepted" + "1" + ":unchecked")) {
				$(".suppAccepted" + "1").prop("checked", true);
	}
	//console.log($(".selectPo:checked"));//
	if ($(".selectPo:checked")) {
		$(".selectPo:checked").each(function(){
			let checkPoStatus = $('#smart_po_table').DataTable().row($(this).parents('tr')).data().poStatusName;
			var id = $(this).attr('id').split("_")[2];
			console.log($('#smart_po_table').DataTable().row($(this).parents('tr')).data())
			var po = { poId: id};
				pos.push(po);
			if (checkPoStatus != "รอ Supplier ตอบรับ") {
				result = false
			}
				
		});
	}
	if (result) {
		console.log(pos)
		if(pos.length > 0) {
			url = contextPath + '/api/smart_po/supplier_accepted_po';
			/*ajaxProcess.submit(url, 'POST', pos, '', (data) => {
				if (data.result) {
					search();
				}
			})*/
			
		}
		
	}else {
		alert("ต้องมีสถานะ '"+"รอ Supplier ตอบกลับ"+"' ");
	}
});
