function onDelete(id,button){
	swal.fire({
		  title: "¿Está seguro?",
		  text: "Una vez borrado el registro no podrá recuperarlo",
		  icon: "warning",
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  preConfirm: ()=>{
			  $.ajax({
					url : "/descuento/delete/" + id,
					method : 'GET',
					success : function(response){

						button.parents("tr").remove();
						swal.fire("Realizado", "El registro ha sido eliminado con éxito", "success");
						
						
					},
					error: function () {
		                swal.fire("¡Error al borrar!", "Por favor inténtelo de nuevo", "error");
		            }
					
				});
		  }
		});
		
}

function list(){
	$.ajax({
		url : "/club/list/",
		method : 'GET',
		success : function(response){
			$("#listClub").empty();
			$("#listClub").html(response);
		},
		error : function(err){
			console.log(err);
		}		
	});	
}

$(document).ready(function(){
	
	$(".btnDelete").click(function(){
		onDelete($(this).val(),$(this));		
	});
});