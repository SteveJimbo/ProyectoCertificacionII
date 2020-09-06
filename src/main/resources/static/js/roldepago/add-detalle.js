function list(){	
	$.ajax({
		url : "/roldepago/detalles",
		method : 'GET',
		success : function(response){
			$("#adicionales").empty();
			$("#adicionales").html(response);
			total();
		},
		error : function(err){
			Swal.fire({
				title : '¡Error!',
				text : 'Error al recuperar los Datos',
				icon : 'error',
				button : 'Aceptar'
			});
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
			Swal.fire({
				title : '¡Error!',
				text : 'Error al recuperar los Datos',
				icon : 'error',
				button : 'Aceptar'
			});
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
			Swal.fire({
				title : '¡Error!',
				text : 'Error al recuperar los Datos',
				icon : 'error',
				button : 'Aceptar'
			});
		}		
	});
	
}

function cargarOb(){
	var selectBox = document.getElementById("trabajador.idtrabajador");
    var id = selectBox.options[selectBox.selectedIndex].value;
	$.ajax({
		url : "/roldepago/obligaciones/"+id,
		method : 'GET',
		success : function(response){
			list();		
		},
		error : function(err){
			Swal.fire({
				title : '¡Error!',
				text : 'Error al recuperar los Datos',
				icon : 'error',
				button : 'Aceptar'
			});
		}
	});
}

function total(){
	$.ajax({
		url : "/roldepago/total",
		method : 'GET',
		success : function(response){
			console.log(response)
			document.getElementById("total").value = ""+response;			
		},
		error : function(err){
			Swal.fire({
				title : '¡Error!',
				text : 'Error al recuperar los Datos',
				icon : 'error',
				button : 'Aceptar'
			});
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