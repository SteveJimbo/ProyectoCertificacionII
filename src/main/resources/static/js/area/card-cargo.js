function save(){	
	var dataForm = objectifyForm($("#frmCargo").serializeArray());	
	var requestBody = JSON.stringify(dataForm);		
	$.ajax({
		url : developURL + "cargo/save",
		method : 'POST',
		contentType : "application/json",
		headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},		
		data : requestBody,
		success : function(response){
			console.log(response);
		},
		error : function(err){
			console.log(err);
		}		
	});
	
}

function create(){
	var id = $("#idarea").val();	
	
	$.ajax({
		url : "/cargo/create/" + id,
		method : 'GET',
		success : function(response){		

			$("#contentCargo").empty();
			$("#contentCargo").html(response);
		},
		error : function(err){
			console.log(err);
		}		
	});
}

function list(){
	var id = $("#idarea").val();
	$.ajax({
		url : "/cargo/list/" + id,
		method : 'GET',
		success : function(response){
			console.log(response);
			$("#listCargo").empty();
			$("#listCargo").html(response);
		},
		error : function(err){
			console.log(err);
		}		
	});	
}

$(document).ready(function(){
	
	console.log("Página cargada...");
	
	$("#btnAdd").click(function(){
		create();		
	});
	
	$("#btnSubmit").click(function(){
		save();		
	});
	
	$("#tab--2").click(function(){
		list();		
	});
	
});
