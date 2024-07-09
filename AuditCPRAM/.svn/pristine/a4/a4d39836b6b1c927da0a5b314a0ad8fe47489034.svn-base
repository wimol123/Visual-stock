var dataColumn;
var status='Y';
var editNum = -1 ;
$(function(){
	chSession();
	getDocumentlist();
	loadPage();
});

function loadPage(){
	$('#Document-table').find('#Table-CRUD').empty();
	let htmlPortlet = DynamicTable();
	$('#Document-table').find('#Table-CRUD').append(htmlPortlet);
}

function chSession() {
	let url = contextPath +"/api/user/get_permission_menu";
	$.ajax({
		url : url,
		type : 'POST',
		contentType: "application/json",
		data : "document.jsp",
		success : function(data){
			if(!data.result){
				window.location = "/auditsupplier/home.jsp";
			}
		}
	});
}

function DynamicTable(){
	let Table = ''
	
	for(var i = 0 ; i<dataColumn.length ; i++){
		
		Table += '<tr> <th scope="row">'+ (i+1) +'</th><td>'+dataColumn[i]['DocumentName']+' <button type="button" style="Margin:5px; width: 30px; height: 30px; padding: 6px 0px;border-radius: 15px;text-align: center;font-size: 12px;line-height: 1.42857;" class="btn green" data-toggle="modal" data-target="#editModal" value= '+i+' onclick="edit(this)" ><i class="icon-note"></i></button> </td>  <td>  <button type="button" onclick="Test(this)" class="btn btn-success" value='+i+' >'+ dataColumn[i]['enable'] +'</button></td>'
		
		if(i != 0 && i != dataColumn.length-1){
			Table += '<td><button type="button" class="btn btn-outline-primary" value= '+i+' onclick="up(this)"> <span class="glyphicon glyphicon-arrow-up"></span> </button> <button type="button" class="btn btn-outline-primary" value= '+i+' onclick="down(this)" > <span class="glyphicon glyphicon-arrow-down"></span> </button><td/>'
		}else if(i == 0){
			Table += '<td><button type="button" class="btn btn-outline-primary" value= '+i+' onclick="up(this)" disabled> <span class="glyphicon glyphicon-arrow-up"></span> </button> <button type="button" class="btn btn-outline-primary" value= '+i+' onclick="down(this)" > <span class="glyphicon glyphicon-arrow-down"></span> </button><td/>'
		}else if(i == dataColumn.length-1){
			Table += '<td><button type="button" class="btn btn-outline-primary" value= '+i+' onclick="up(this)"> <span class="glyphicon glyphicon-arrow-up"></span> </button> <button type="button" class="btn btn-outline-primary" value= '+i+' onclick="down(this)" disabled> <span class="glyphicon glyphicon-arrow-down"></span> </button><td/>'
		}
		
		Table += '</tr>'         
	}
	
	return Table;
}


$(document).on('click', '#btn_save_update', function(){
	console.log("Save");
	updateDocumentlist();
});

function Test(e){
	console.log(e);
	if(dataColumn[e.value]['enable'] == "Y"){
		dataColumn[e.value]['enable'] = "N";
	}else{
		dataColumn[e.value]['enable'] = "Y";
	}
	loadPage();
}



function edit(e){
	console.log(e.value);
	document.getElementById("Edit_Document_name").value = dataColumn[e.value]['DocumentName'];
	editNum = e.value;
}


$(document).on('click', '#flexRadioDefault1', function(){
	status = "Y";
	console.log(status)
});


$(document).on('click', '#flexRadioDefault2', function(){
	status = "N";
	console.log(status)
});


function editname(){
	var name = document.getElementById("Edit_Document_name").value;	
	if(document.getElementById("Edit_Document_name").value == null || document.getElementById("Edit_Document_name").value == ""){
		alert("กรุณากรอกข้อมูลให้ครบถ้วน");
	}else{
		if(editNum != -1 ){
			dataColumn[editNum]['DocumentName'] = name;
			editNum = -1;
			alert("เเก้ไขข้อมูลสำเร็จ");
		}else{
			alert("เเก้ไขข้อมูลไม่สำเร็จ โปรดลองใหม่ออกครั้ง");
		}
	}
	loadPage();
}



function Add(){
	var name = document.getElementById("Document_name").value;
	
	if(document.getElementById("Document_name").value == null || document.getElementById("Document_name").value == ""){
		alert("กรุณากรอกข้อมูลให้ครบถ้วน");
	}else{
		dataColumn.push( {DocumentName: name , enable: status} );

		let Insertdata = {
			DocumentName: name,
			enable: status,
			Seq: dataColumn.length
		}
		
		let url = contextPath+'/api/document/InsertDocument';
		ajaxProcess.submit(url,'POST', Insertdata ,null , (data)=>{

		});

		loadPage();
	}
}

function up(e){

	var tmp = dataColumn[e.value];
	dataColumn[e.value] = dataColumn[e.value-1]
	dataColumn[e.value-1] = tmp
	loadPage()
	
}

function down(e){

	var tmp = dataColumn[e.value];
	dataColumn[e.value] = dataColumn[parseInt(e.value)+1];
	dataColumn[parseInt(e.value)+1] = tmp ;
	loadPage()
	
}



function getDocumentlist(){

	let url = contextPath+'/api/document/document_list';
	ajaxProcess.submit(url, 'GET', null, null, (data)=>{
		if(data.result){
			dataColumn = JSON.parse(data.message);
		}		
	},{async : false});
	
}

function updateDocumentlist(){
	console.log("hello")
	let url = contextPath+'/api/document/updateDocument';
	ajaxProcess.submit(url,'POST', dataColumn ,null , (data)=>{
		if(data.result){
			console.log("OK")
		}else{
			console.log("Oh No")
		}
	});
}
