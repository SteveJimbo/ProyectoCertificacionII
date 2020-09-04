function list(){	
	$.ajax({
		url : "/roldepago/detalles",
		method : 'GET',
		success : function(response){
			$("#adicionales").empty();
			$("#adicionales").html(response);
		},
		error : function(err){
			console.log(err);
		}		
	});	
}

function create(){		
	$.ajax({
		url : "/detalle/create",
		method : 'GET',
		success : function(response){
			$("#contentFormulario").empty();
			$("#contentFormulario").html(response);
		},
		error : function(err){
			console.log(err);
		}		
	});
}

function save(){	
	var dataForm = objectifyForm($("#frmDetalle").serializeArray());	
	var requestBody = JSON.stringify(dataForm);
	console.log(requestBody);			
	$.ajax({
		url : developURL + "roldepago/add",
		method : 'POST',
		contentType : "application/json",
		headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},		
		data : requestBody,
		success : function(response){		 
			list();
		},
		error : function(err){
			console.log(err);
		}		
	});
	
}

$(document).ready(function(){
	
	$("#btnAdd").click(function(){
		create();		
	});
	
	$("#btnSubmit").click(function(){
		save();		
	});
		
});