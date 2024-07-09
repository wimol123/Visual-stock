
const supplierGroup = ["3", "8"];
const purchasingGroup = "5";
var isSupplier = false;
var titleText;
var day = [];
//var month = [0,'Jan.','Feb.','Mar','Apr.','May','Jun.','Jul.','Aug.','Sept.','Oct.','Nov.','Dec.']
var months = [];
const daysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
let materialData = [];
var option;
var count;
const dateStartElement = document.getElementById("create_date_start");
const dateEndElement = document.getElementById("create_date_end");
$('#graph_y_material').select2();
$('#graph_y_supplier').select2();
var selectList = [];
var periodOfTime;

function sortAndRemoveDuplicates(dateStrings) {
	const uniqueDates = new Set(dateStrings);		
	return [...uniqueDates].sort();
}


function getDatesBetween(startDate, endDate) {
  const dates = [];
  let currentDate = startDate;
  while (currentDate <= endDate) {
    const yyyy = currentDate.getFullYear().toString().padStart(4, '0'); // Adjust padding for full year (YYYY)
    const mm = (currentDate.getMonth() + 1).toString().padStart(2, '0');
    const dd = currentDate.getDate().toString().padStart(2, '0');
    dates.push(`${dd}/${mm}/${yyyy}`);
    currentDate.setDate(currentDate.getDate() + 1);
  }
  return dates;
}
$(document).ready(function() {
	
	chSession();
	$('.input-group.datepicker-start').datepicker({
		language: "th",
		format: 'dd/mm/yyyy',
		orientation: "bottom auto",
		autoclose: true
	});
	$('.input-group.datepicker-end').datepicker({
		language: "th",
		format: 'dd/mm/yyyy',
		orientation: "bottom auto",
		autoclose: true
	});
	loadSupplier();
	loadMaterial();
	

});

function chSession() {
	let url = contextPath + "/api/user/get_permission_menu";
	$.ajax({
		url: url,
		type: 'POST',
		contentType: "application/json",
		data: "graph_data.jsp",
		success: function(data) {
			if (!data.result) {
				window.location = "/auditsupplier/home.jsp";
			}
		}
	});
}

 function loadSupplier() {
	/*let url = contextPath + "/api/supplier/supplier_list";
	$.ajax({
		url: url,
		type: 'GET',
		contentType: "application/json",
		beforeSend : function(arr, $form, options){
		    	BlockUi.blockPage();
		    },
		    complete : function(){
		    	setTimeout(function(){
		    		BlockUi.unBlockPage();
		    	},500);
		    },
		success: function(data) {
			if (data.result && data.message != null && data.message != "") {
				let listStatus = JSON.parse(data.message.trim());
				$(listStatus).each(function(i, v) {
					$('#graph_y_supplier').append(
						'<option value="' + v.supplierCode + '" ' + '> ' + 
						v.supplierCompany + ' </option>');
				});
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
	    // Handle any errors during the request
	    console.error("Error:", textStatus, errorThrown);
  }
	},{async : false});*/
	let url = contextPath+'/api/supplier/supplier_list';
	ajaxProcess.submit(url, 'GET', null, '', (data)=>{
		if (data.result && data.message != null && data.message != "") {
				let listStatus = JSON.parse(data.message.trim());
				$(listStatus).each(function(i, v) {
					$('#graph_y_supplier').append(
						'<option value="' + v.supplierCode + '" ' + '> ' + 
						v.supplierCompany + ' </option>');
				});
			}		
	},{async : false});
}

function loadQuestionaireDocumentDataList(){

	
	
}


function loadMaterial() {
	ajaxProcess.submit(
		contextPath + "/api/material/get_material_list",
		'GET', 
		null, 
		'', 
		(data) => {
			if (data.result && data.message != null && data.message != "") {
				let listStatus = JSON.parse(data.message.trim());
				$(listStatus).each(function(i, v) {
					$('#graph_y_material').append(
						'<option value="' + v.materialCode + '" ' + '> ' + 
						v.materialName + ' </option>');
				});
			}
		}
	);
}

function loadDataTable() {
	$("#supplier_table").DataTable({
		data:data,
	    language: {
	        url: "assets/global/plugins/datatables/Thai.json"
	    },
	    columns: [
			{"data": null},
			{"data": "supplier",   name: "supplier"},
		
			
		],
		columnDefs: [
			{
				"className": "dt-left",
				"targets": [0],
				"searchable": false,
	            "orderable": false,
	            "render": function(data, type, full, meta) {
					var btnAction = '';
					count = meta.settings._iDisplayStart + meta.row + 1;
					console.log(count);
	                return meta.settings._iDisplayStart + meta.row + 1;
	                
	            }
			},
			{
				"className": "supplier-select-btn" + count,
				"targets": [1],
				"searchable": false,
	            "orderable": false,

			},
        ],
	    "order": [],
	    "initComplete": function() {
	    	$('.dataTables_filter').remove();
	    }
    
	})
}

$('#btn_data_search').on('click', function() {
  titleText = $('#graph_y').val().trim();
  selectList = [];
  day = [];
  const dateStartString = dateStartElement.value.split(" ")[0];
  const dateEndString = dateEndElement.value.split(" ")[0];
  const dateStartParts = dateStartString.split("/");
  const dateEndParts = dateEndString.split("/");
  console.log(dateStartString)
  console.log(dateStartParts)
  const dateStart = new Date(
      dateStartParts[2], // Year
      parseInt(dateStartParts[1]) - 1,
      dateStartParts[0] // Month (0-indexed)
  );
  const dateEnd = new Date(
     dateEndParts[2], // Year
     parseInt(dateEndParts[1]) - 1 ,
     dateEndParts[0] // Month (0-indexed)
     // Day
  );
  console.log(dateStart)
  if (dateStartString == ''||dateEndString == '' ) {
	alert('กรุณาระบุช่วงเวลา');
  }
  else {
		var group_by =	$('#graph_y').val().trim()
		var selectmat = $("#graph_y_material").select2("data");
        var selectsup = $("#graph_y_supplier").select2("data");
		
		selectList = [];
		for (var i = 0; i < (selectmat.length +selectsup.length) ; i++) {
			if (selectmat[i] == undefined && selectsup[i] == undefined) {
				break
			}
			if (selectmat[i] == undefined) {
				selectmat[i] = null
				matid = selectmat[i];
			}
			else {
				matid = selectmat[i].id;
			}
			if (selectsup[i] == undefined) {
				selectsup[i] = null
				console.log(selectsup[i])
				supid = selectsup[i];
			}
			else {
				supid = selectsup[i].id;
			}
			if (dateStartString == undefined) {
				dateStartString = ''
				
			}
			if (dateEndString == undefined) {
				dateEndString = ''
				
			}
			var dataPost = { materialNo: matid , supplierNo : supid , groupBy: group_by ,
			                 startDeliverDate: dateStartString, endDeliverDate: dateEndString};
			selectList.push(dataPost);
		}
		console.log(selectList)

		if (selectList.length == 0) {
			alert("โปรดใส่ข้อมูลที่ต้องการค้นหา")
		}
		else if (group_by == "supplier" && selectList[0].supplierNo == null ){
		    alert("โปรดใส่ข้อมูล supplier")
		
		}
		else if (group_by == "material" && selectList[0].materialNo == null ){
			alert("โปรดใส่ข้อมูล material")
		
		}	
			
		else {
		    ajaxProcess.submit(
			contextPath + "/api/material/get_material_data_list" ,
			'POST', 
			selectList,
			"",
			(data) => {
					materialData = JSON.parse(data.message.trim());
					if (materialData.length <= 0|| materialData.length == null) {
						alert("ไม้พบข้อมูล");
					}
					
					else if ($('#graph_x').val().trim() === 'day'){
						console.log(materialData)
						day = [];
						for (let i = 0; i< materialData.length; i ++) {				
							day.push(materialData[i].deliverDate)			
						}
				          day = getDatesBetween(dateStart, dateEnd);
				          console.log(day);
				          console.log(materialData)
				          createChart(titleText,day, materialData);
				    }
				    else if ($('#graph_x').val().trim() === 'month') {
						console.log(materialData)
				
				      const groupedData = materialData.reduce((acc, item) => {
					 //const month = new Date(item.deliverDate).getMonth() + 1;
					  const month = item.deliverDate.split("/")[1]; 	 	
					  const year = item.deliverDate.split("/")[2];  
					  const key = `${item.materialDesc}-${month}`;
					  if (!acc[key]) {
					    acc[key] = { materialDesc: item.materialDesc, supplierName: item.supplierName, totalRoundingValue: 0 };
					  }
					  acc[key].month = `${year}-${month}`
					  acc[key].totalRoundingValue += parseInt(item.roundingValue);
					  return acc;
					  },[]);	
					  console.log(groupedData)
					  materialData = [];
					  for (const key in groupedData) {	
					  const item = groupedData[key];
					  materialData.push(item);
					  }
					console.log(materialData)
					months = [];
					year = [];
					for (let i = 0; i< materialData.length; i ++) {				
						months.push(materialData[i].month)			
					}
					months = sortAndRemoveDuplicates(months)
					createChart(titleText,months ,materialData);
					} 
					
					});
			}
					  
	}
    
});




/*$('#btn_data_search').on('click', function() {

	
	titleText = $('#graph_y').val().trim();
	selectList = [];
	var group_by =	$('#graph_y').val().trim()
	var selectmat = $("#graph_y_material").select2("data");
    var selectsup = $("#graph_y_supplier").select2("data");
	for (var i = 0; i < (selectmat.length +selectsup.length) ; i++) {
		if (selectmat[i] == undefined && selectsup[i] == undefined) {
			break
		}
		if (selectmat[i] == undefined) {
			selectmat[i] = null
			matid = selectmat[i];
		}
		else {
			matid = selectmat[i].id;
		}
		if (selectsup[i] == undefined) {
			selectsup[i] = null
			console.log(selectsup[i])
			supid = selectsup[i];
		}
		else {
			supid = selectsup[i].id;
		}
		var dataPost = { materialNo: matid , supplierNo : supid , groupBy: group_by};
		selectList.push(dataPost);
	}	
	if (selectList.length == 0) {
		alert("โปรดใส่ข้อมูล")
	}
	else if (group_by == "supplier" && selectList[0].supplierNo == null ){
		alert("โปรดใส่ข้อมูล supplier")
		
	}
	else if (group_by == "material" && selectList[0].materialNo == null ){
		alert("โปรดใส่ข้อมูล material")
		
	}
	else {
		console.log(selectList)
        //const queryString = `materialIds=${materialData.join(",")}`;
	    ajaxProcess.submit(
		contextPath + "/api/material/get_material_data_list" ,
		'POST', 
		selectList,
		"",
		(data) => {
			if (selectList.length == 0) {
		       alert("ไม่พบข้อมุล")
	        }
	        else {
				
			
			materialData = JSON.parse(data.message.trim());
			if ($('#graph_x').val().trim() === 'day') {
			day = [];
			
			console.log(materialData)
			for (let i = 0; i< materialData.length; i ++) {				
				day.push(materialData[i].deliverDate)			
			}
			console.log(day);

			console.log(materialData)		
			createChart(titleText,sortAndRemoveDuplicates(day), materialData);

			}
			else if ($('#graph_x').val().trim() === 'month') {
		          const groupedData = materialData.reduce((acc, item) => {
					  const month = new Date(item.deliverDate).getMonth() + 1; 	
					  const year = new Date(item.deliverDate).getFullYear();  
					  const key = `${item.materialDesc}-${month}`;
					  if (!acc[key]) {
					    acc[key] = { materialDesc: item.materialDesc, totalRoundingValue: 0 };
					  }
					  acc[key].month = `${year}-${month}`
					  acc[key].totalRoundingValue += parseInt(item.roundingValue);
					  return acc;
					  },[]);	
					  console.log(groupedData)
					  materialData = [];
					  for (const key in groupedData) {	
					  const item = groupedData[key];
					  materialData.push(item);
					  }
					console.log(materialData)
					months = [];
					year = [];
					for (let i = 0; i< materialData.length; i ++) {				
						months.push(materialData[i].month)			
					}
					months = sortAndRemoveDuplicates(months)
					createChart(titleText,months ,materialData);
			}
		}
		}
	);
	}
})*/


function createChart(titleText, periodOfTime, data) {
  const dom = document.getElementById('container');
  const myChart = echarts.init(dom, null, {
    renderer: 'canvas',
    useDirtyRect: false
  });
  let seriesData = [];
  console.log(data)
  if ($('#graph_x').val().trim() === 'day'){
	  seriesData = data.reduce((acc, item) => {
	  const name = item.materialDesc || item.supplierName;
	  let existingEntry = acc.find(entry => entry.name === name);
	  if (!existingEntry) {
	    existingEntry = { name, type: 'line', data: [] };
	    acc.push(existingEntry);
	  }
	  existingEntry.data.push([item.deliverDate, item.roundingValue]);
	  existingEntry.data.sort((a, b) => {
		  const [dayA, monthA, yearA] = a[0].split('/');
		  const [dayB, monthB, yearB] = b[0].split('/');
		  const dateA = new Date(yearA, monthA - 1, dayA);
		  const dateB = new Date(yearB, monthB - 1, dayB);
		  return dateA - dateB;
		});
	  return acc;
	}, []);
  console.log(seriesData)
 }
	else if ($('#graph_x').val().trim() === 'month'){
		  console.log(data)
		  seriesData = data.reduce((acc, item) => {
		  const name = item.materialDesc || item.supplierName;
		  let existingEntry = acc.find(entry => entry.name === name);
		  if (!existingEntry) {
		    existingEntry = { name, type: 'line', data: [] };
		    acc.push(existingEntry);
		  }
		  existingEntry.data.push([item.month, item.totalRoundingValue]);
		  existingEntry.data.sort((a, b) => new Date(a[0]) - new Date(b[0]));
		  existingEntry.data.item.month == month[item.month.split("-")[0]];
		  return acc;
		  
		}, []);	  
		console.log(seriesData)
}


  const options = {
    title: {
	    text: titleText
	  },
	  tooltip: {
		trigger: 'axis'
	  },
	  legend: {
	    data: seriesData.name
	  },
	  grid: {
	    left: '3%',
	    right: '4%',
	    bottom: '3%',
	    containLabel: true
	  },
	  toolbox: {
        right: 10,
        feature: {
          saveAsImage: {}
        }
      },
      dataZoom: [
        {
          startValue: '2014-06-01'
        },
        {
          type: 'inside'
        }
      ],
	  xAxis: {
	    type: 'category',
	    boundaryGap: false,
	    data: periodOfTime
	  },
	  yAxis: {
	    type: 'value'
	  },
    series: 
     seriesData 
  };
  options&&myChart.setOption(options);
}