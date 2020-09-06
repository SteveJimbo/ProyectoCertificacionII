function selectCargos(){
	console.log("select");
	var selectBox = document.getElementById("area");
    var id = selectBox.options[selectBox.selectedIndex].value;
	$.ajax({
		url : "/cargo/search/"+id,
		method : 'GET',
		success : function(response){
			$("#cargo").empty();			
			var count = Object.keys(response).length;			
			if(count > 0){								
				$("#cargo").addClass('visible').removeClass('invisible');
				$.each( response, function(index, cargo ) {					
					$("#cargo").append("<option value='"+ cargo.idcargo +"'>" + cargo.nombre + " " + "</option>");					
				});
			}
			else{
				$("#cargo").addClass('invisible').removeClass('visible');
				console.log("No hay cargos disponibles");				
			}			
		},
		error : function(err){
			Swal.fire({
				title : '¡Error!',
				text : 'Error al recuperar los Cargos',
				icon : 'error',
				button : 'Aceptar'
			});
		}
	});
}