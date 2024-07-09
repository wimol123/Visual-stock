

var objSearch = {	checklistName : '', statusId : ''	};
loadChecklistToTable(objSearch);

function loadChecklistToTable(optionObj){
	$('#table_checklist').DataTable({		
		"proccessing": true,
	    "serverSide": true,
	    "searching": false,
	    "ajax": {
	        "url": contextPath + "/api/checklist/getChecklistList/"+JSON.stringify(optionObj),
	        "type": 'GET'
	    },
	    "language": {
	        "url": "assets/global/plugins/datatables/Thai.json"
	    },
	    "columns": [
	    	{ "data": null, "width": "30px" },
	    	{ "data": "checklistName" },
	    	{ "data": "checklistId"},
	    	{
	            "data": "statusId",
	            "render": function (data, type, row) {
	            	
	            	var dpStatus = '<span class="label label-sm label-success"> Active </span>';
					if (data == '0' ){
						dpStatus = '<span class="label label-sm label-default"> Inactive </span>';
					}
					
	                return dpStatus;
	            },
	            "width": "150px" 
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
	        	"targets": [2],
	            "visible": false,
	            "searchable": false
	        },
	        {
	        	"targets": [0,1,2,3,4],
	            "searchable": false,
	            "orderable": false	            
	        },
	        {
	            "targets": 4,
	            "orderable": false,
	            "render": function (data, type, row) {
	            	
	            	
	            	var btnAction = '<div class="btn-group">';
	            	btnAction += '<button class="btn btn-xs green dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false"> Actions <i class="fa fa-angle-down"></i></button>';
	    			btnAction += '<ul class="dropdown-menu pull-left" role="menu">';
	    			btnAction += 	'<li><a href="javascript:void(0);" class = "editChecklist">';
	    			btnAction +=   		'<i class="icon-pencil"></i> Edit </a>';
	    			btnAction +=	'</li>';
	    			btnAction +=	'<li><a href="javascript:void(0);" class = "removeChecklist">';
	    			btnAction +=		'<i class="icon-trash"></i> Remove </a>';
	    			btnAction +=	'</li>';
	                btnAction +=	'<li><a href="javascript:void(0);" class = "messageChecklist">';
	                btnAction +=		'<i class="icon-envelope "></i> Message</a>';
	                btnAction +=	'</li>';
	                btnAction += '</ul>';
	                btnAction +='</div>';
	               
	                return btnAction;
	            },
	            "width": "200px" 
	        }
	    ],
	    "order": [[1, 'asc']],
	    "initComplete": function (settings, json) {} 
	    
	    
	});
}