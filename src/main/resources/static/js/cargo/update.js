$(document).ready(function(){
	
	$(".btnUpdateCargo").click(function(){
		updateCargo($(this).val());
		
	});
});
function updateCargo(id){		
	console.log(id);
	var idArea = $("#idarea").val();
	console.log(idArea);
	$.ajax({
		url : "/cargo/update/" + id ,
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
