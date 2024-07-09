$(function() {
	chSession();	
	$('.form-input-datetime').datetimepicker({
		format: 'DD/MM/YYYY HH:mm',
		widgetPositioning: {
       		horizontal: "auto",
			vertical: "bottom"
		}
	});
	
	loadAutoSupplierList();
	loadAutoMaterialList();
	loadAutoPlantList();
	//loadDataTable();
	

	
});

 function generateTableHeaders1 (headers) {
            // Select the <thead> element
      var headerTime = document.getElementById('headerTime');
        // Create a new th element
      //  var newHeaderCell = document.createElement('th');

        // Set the content of the th element
    //    newHeaderCell.textContent = 'Deliver Date Time';

        // Append the th element to the header row
    //    headerRow.appendChild(newHeaderCell);
        
//          var table = document.getElementById('visual_stock_table');

            var thead = document.getElementById('visual_stock_table')

            // Create the main row
            var mainRow = document.createElement('tr');
            
            // Create the sub row
            var subRow = document.createElement('tr');

            // Define the main and sub th values
           // var mainThValues = ['07:00', '08:00'];
            var subThValues = ['Reservation/Requirement', 'Rounding', 'Sup.Confirm', 'หมายเหตุ', 'จัดซื้อตอบกลับ'];

            // Loop through the main th values to create main th elements
            headers.forEach(function(mainValue) {
				newmainValue=mainValue.replace(/:/g, '');
				//alert(newmainValue)
                var mainTh = document.createElement('th');
                mainTh.setAttribute('colspan', subThValues.length);
                mainTh.textContent = mainValue;
                mainRow.appendChild(mainTh);

                // Loop through the sub th values to create sub th elements
                subThValues.forEach(function(subValue) {
                    var subTh = document.createElement('th');
                    subTh.textContent = subValue;
                   // subTh.id = mainValue + subValue;
                     subTh.setAttribute('id', subValue+'/'+newmainValue);;
                     subTh.setAttribute('name', subValue);
                    subRow.appendChild(subTh);
                });
            });

            // Append the main row and sub row to the thead

    // Iterate through each th element and clear its content

                
            headerTime.appendChild(mainRow);
            headerTime.appendChild(subRow);
  //thead.querySelector('tr').appendChild(mainRow);
  //thead.querySelector('tr').appendChild(subRow);

        }


 function generateTableHeaders2 (headers) {
            // Select the header row by ID
      var headerRow = document.getElementById('header-row');
        // Create a new th element
        var newHeaderCell = document.createElement('th');

        // Set the content of the th element
        newHeaderCell.textContent = 'Deliver Date Time';

        // Append the th element to the header row
        headerRow.appendChild(newHeaderCell);
        
          var table = document.getElementById('visual_stock_table');

            // Check if the table element exists
            if (table) {
                // Create a new tr element
                var newRow = document.createElement('tr');


// Loop through the header values and create <th> elements
headers.forEach(value => {
  var th = document.createElement("th");  // Create a new <th> element
  th.textContent = value;  // Set the text content of the <th> element
  newRow.appendChild(th);  // Append the <th> element to the <tr>
  
  table.querySelector('thead').appendChild(newRow);
             
});


      //      generateTableHeaders('level-2-headers-time', headers);


                // Create and append new td elements to the tr
//                newCell1.textContent = 'New Row, Cell 1';
//                newRow.appendChild(newCell1);

                // Append the tr element to the table's tbody
            } else {
                console.error('Table with ID "myTable" not found.');
            }
        
        }

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
var data = [];
var collapsedGroups = {};
    

$('#btn_search').on('click', function() {
	searchTable();
});


$("#supplier_input").keyup(function(event) {
    if (event.keyCode === 13) {
        search();
    }
});
$("#meterial_input").keyup(function(event) {
    if (event.keyCode === 13) {
        search();
    }
});

$("#plant_input").keyup(function(event) {
    if (event.keyCode === 13) {
        search();
    }
});


$('#btn_clear').on('click', function() {
	clearSearch("ssas");
});

$('#btn_export').on('click', function() {
	exportExcel();
});


function clearSearch() {
	$('#supplier_input').val('');
	$('#meterial_input').val('');
	$('#plant_input').val('');
	$('#status_confirm').val('');
}

function exportExcel() {
	$('#export_supplier').val($('#supplier_input').val().trim());
	$('#export_meterial').val($('#meterial_input').val().trim());
	$('#export_plant').val($('#plant_input').val().trim());
	$('#export_status').val($('#status_confirm').val().trim());
	$("#export_form").submit();
}

function loadAutoSupplierList() {
ajaxProcess.submit(
		contextPath + "/api/visual_stock/get_auto_supplier_list", 
		'GET', 
		null, 
		'', 
		(data) => {
			if (data.result && data.message != null && data.message != "") {
				let listStatus = JSON.parse(data.message.trim());
				var availableTags =[]
				$(listStatus).each(function(i, v) {
					availableTags.push(v.Supplier);    
				});
				    $( "#supplier_input" ).autocomplete({
      source: availableTags
    });

			}
		}
	);
}

function loadAutoMaterialList() {
ajaxProcess.submit(
		contextPath + "/api/visual_stock/get_auto_material_list", 
		'GET', 
		null, 
		'', 
		(data) => {
			if (data.result && data.message != null && data.message != "") {
				let listStatus = JSON.parse(data.message.trim());
				var availableTags =[]
				$(listStatus).each(function(i, v) {
					availableTags.push(v.Material);    
				});
				    $( "#meterial_input" ).autocomplete({
      source: availableTags
    });

			}
		}
	);
}

function loadAutoPlantList() {
ajaxProcess.submit(
		contextPath + "/api/visual_stock/get_auto_plant_list", 
		'GET', 
		null, 
		'', 
		(data) => {
			if (data.result && data.message != null && data.message != "") {
				let listStatus = JSON.parse(data.message.trim());
				var availableTags =[]
				$(listStatus).each(function(i, v) {
					availableTags.push(v.Plant);    
				});
				    $( "#plant_input" ).autocomplete({
      source: availableTags
    });

			}
		}
	);
}

function loadSearchListWindowTime(supplierInput, meterialInput, plantInput) {
		var headers =[]
		var headers1 =['Po No.','Item No.','Material No.','Description','Plant','Total Stock','Unit']
		var headers2 =['Total','Confirm history']
		
ajaxProcess.submit(
		contextPath + "/api/visual_stock/get_list_window_time/" + supplierInput + "/" + meterialInput + "/"  + plantInput,
		'GET', 
		null, 
		'', 
		(data) => {
			if (data.result && data.message != null && data.message != "") {
				let listStatus = JSON.parse(data.message.trim());
			
				$(listStatus).each(function(i, v) {
					//alert(v.Time); 
					  headers.push(v.Time);
				});
				               // Generate the headers in the specified row
            generateTableHeaders1(headers);
            addHeaderRow(headers1,headers2,headers);

     //	alert(headers);
	
            // Initialize DataTable
         //   $('#visual_stock_table').DataTable();
     
			}
			//alert(headers);
			searchTable1(headers);
		//	return headers;
		}
	);

}

function searchTable() {
	    //    $('#visual_stock_table').DataTable().destroy();

//alert("aadc");
var supplierInput= $('#supplier_input').val();
var meterialInput= $('#meterial_input').val();
var plantInput= $('#plant_input').val();
//var statusConfirm= $('#status_confirm').val();
//alert(supplierInput);
//alert(meterialInput);
//alert(plantInput);
//alert(statusConfirm);

/*
ajaxProcess.submit(
		contextPath + "/api/visual_stock/get_search_table_list/" + supplierInput + "/" + meterialInput + "/"  + plantInput, 
		'GET', 
		null, 
		'', 
		(data) => {
			if (data.result && data.message != null && data.message != "") {
				let listStatus = JSON.parse(data.message.trim());
				var availableTags =[]
				// Step 1: Create an empty array
var data = [];

				$(listStatus).each(function(i, v) {
					var obj = {};
obj["PoNo"] = v.Po; 
obj["ItemNo"] = v.Item; 
obj["MaterialNo"] = v.Material; 
obj["Plant"] = v.Plant; 
obj["Description"] = v.Description; 
obj["Stock"] = v.Stock; 
obj["Unit"] = v.Unit; 
obj["Deliver Date Time"] = v.Po;
 
//alert(v.DateOfDelivery);
            // Step 3: Push the JSON object into the array
            
				data.push(obj); 
				});

var data1 = [
    {
      "PoNo": "40413",
    }]				
   
   var cd = [];
๕*/
loadSearchListWindowTime(supplierInput,meterialInput,plantInput) ;
 //  alert(cd);
    //	            var headers = ['Name', 'Position', 'Office', 'Age', 'Start date', 'Salary'];

            // Generate the headers in the specified row
      //      generateTableHeaders('level-2-headers-time', headers);

            // Initialize DataTable
         //   $('#visual_stock_table').DataTable();
    
    //alert("OK");
    
    /*
				$('#visual_stock_table').DataTable({
    data: data,
    columns: [
	    	{ "data": "PoNo",                               name: "Po No."},
	    	{ "data": "ItemNo",                               name: "Item No."},
	    	{ "data": "MaterialNo",                               name: "Material No."},
	    	{ "data": "Plant",                               name: "Plant"},
	    	{ "data": "Description",                               name: "Item No."},
	    	{ "data": "Stock",                               name: "Total Stock"},
	    	{ "data": "Unit",                               name: "Unit"},
	    	{ "data": "Deliver Date Time",                     name: "Deliver Date Time"},

	    ],
    columnDefs: [
        ],
	    "order": [],
	    "initComplete": function() {
	    	$('.dataTables_filter').remove();
	    }
    

  });
			}
			
		}
	);
	*/
}

function searchTable1(time) {
    alert(time);
    alert("a");
    
    $('#visual_stock_table').DataTable().destroy();

    var supplierInput = $('#supplier_input').val();
    var meterialInput = $('#meterial_input').val();
    var plantInput = $('#plant_input').val();

    ajaxProcess.submit(
        contextPath + "/api/visual_stock/get_search_table_list_with_time/" + supplierInput + "/" + meterialInput + "/"  + plantInput + "/"  + time, 
        'GET', 
        null, 
        '', 
        (data) => {
            if (data.result && data.message != null && data.message != "") {
                let listStatus = JSON.parse(data.message.trim());
                var data = [];

                $(listStatus).each(function(i, v) {
                    var obj = {};

                    // Copy properties from the main object
                    for (var key in v) {
                        if (v.hasOwnProperty(key) && key !== 'dynamicProperties') {
                            obj[key] = v[key];
                        }
                    }
                    
                    // Copy properties from dynamicProperties
                    if (v.dynamicProperties) {
                        for (var dynKey in v.dynamicProperties) {
                            if (v.dynamicProperties.hasOwnProperty(dynKey)) {
                                obj[dynKey] = v.dynamicProperties[dynKey];
                            }
                        }
                    }
                    
                    data.push(obj); 
                });

                // Get the column names from the first object in the data array
                var columns = [];
                if (data.length > 0) {
                    for (var key in data[0]) {
                        if (data[0].hasOwnProperty(key)) {
                            columns.push({ 
                                "data": key, 
                                "name": key,
                                "render": function(data, type, row, meta) {
									//alert(meta.settings.aoColumns[meta.col].name);
								 var inputId = 'input-' + meta.row + '-' + meta.col +'-'+ meta.settings.aoColumns[meta.col].name;
									if(!(meta.settings.aoColumns[meta.col].name.includes('SupConfirm'))){
								 return	'<p id="' + inputId + '">' + (data ? data : '') + '</p>';
									}
									
									if(meta.settings.aoColumns[meta.col].name.includes('SupConfirm')){
										return '<input type="text" id="' + inputId + '" value="' + (data ? data : '') + '">';
									}
                                    // Convert empty strings to textarea
                                    if (data === null || data.trim() === '') {
                                        return '<textarea></textarea>';
                                    }
                                    return data;
                                }
                            });
                        }
                    }
                }

                $('#myTable').DataTable({
                    data: data,
                    columns: columns,
                    columnDefs: [],
                    "order": [],
                    "initComplete": function() {
                        $('.dataTables_filter').remove();
                    }
                });
            }
        }
    );
}


function addHeaderRow(header1,headers2, headers) {
    // Get the table header element
    var tableHeader = document.querySelector('#myTable thead');

    // Create a new header row
    var mainRow = document.createElement('tr');
    mainRow.setAttribute('id', 'timeRow');


    header1.forEach(function(header1Value) {
        var header1Head = document.createElement('th');
        header1Head.textContent = header1Value;
        header1Head.setAttribute('id', header1Value);
        header1Head.setAttribute('name', header1Value);
                header1Head.setAttribute('rowspan', 3);
        mainRow.appendChild(header1Head);
    });

    // Create the sub row
    var subRow = document.createElement('tr');
    var subThValues = ['Reservation/Requirement', 'Rounding', 'Sup.Confirm', 'หมายเหตุ', 'จัดซื้อตอบกลับ'];

    var mainRowTime = document.createElement('tr');
        var header1HeadTime = document.createElement('th');
        header1HeadTime.textContent = 'Deliverytime';
            header1HeadTime.style.textAlign = 'center';
        header1HeadTime.setAttribute('colspan', headers.length*subThValues.length);

        mainRow.appendChild(header1HeadTime);

        var header2Head = document.createElement('th');
        header2Head.textContent = 'Total';
                header2Head.setAttribute('rowspan', 3);
        mainRow.appendChild(header2Head);


    // Loop through the headers to create main th elements with subheaders
    headers.forEach(function(mainValue) {
        var newMainValue = mainValue.replace(/:/g, '');
        var mainTh = document.createElement('th');
        mainTh.setAttribute('colspan', subThValues.length);
        mainTh.textContent = mainValue;
        mainTh.setAttribute('id', newMainValue);
        mainTh.setAttribute('name', newMainValue);
                    mainTh.style.textAlign = 'center';
        mainRowTime.appendChild(mainTh);

        // Loop through the sub th values to create sub th elements
        subThValues.forEach(function(subValue) {
            var subTh = document.createElement('th');
            subTh.textContent = subValue;
            subTh.setAttribute('id', subValue + '/' + newMainValue);
            subTh.setAttribute('name', subValue);
            subTh.style.textAlign = 'center';
            subRow.appendChild(subTh);
        });
    });



    // Append the new header rows to the table header
    tableHeader.appendChild(mainRow);
    tableHeader.appendChild(mainRowTime);
    tableHeader.appendChild(subRow);
}
    // Function to add a data row to the table
 var myButton = document.getElementById('input-0-8-0700/Rounding');

// Add a click event listener
myButton.addEventListener('click', function() {
    // Code to execute when the button is clicked
    alert("OK");
});