function create(){	
	$.ajax({
		url : "/mensajes/create/",
		method : 'GET',
		success : function(response){
			$("#contentMensaje").html(response);
		},
		error : function(err){
			console.log(err);
		}		
	});
}

function save(){	
	var dataForm = objectifyForm($("#frmMensaje").serializeArray());	
	var requestBody = JSON.stringify(dataForm);			
	$.ajax({
		url : developURL + "mensajes/save",
		method : 'POST',
		contentType : "application/json",
		headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},		
		data : requestBody,
		success : function(response){
					// Success message
                    $("#success").html("<div class='alert alert-success'>");
                    $("#success > .alert-success")
                        .html(
                            "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;"
                        )
                        .append("</button>");
                    $("#success > .alert-success").append(
                        "<strong>Tu mensaje ha sido enviado.</strong>"
                    );
                    $("#success > .alert-success").append("</div>");
                    //clear all fields
                    $("#frmMensaje").trigger("reset");
		},
		error : function(err){
					// Fail message
                    $("#success").html("<div class='alert alert-danger'>");
                    $("#success > .alert-danger")
                        .html(
                            "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;"
                        )
                        .append("</button>");
                    $("#success > .alert-danger").append(
                        $("<strong>").text(
                            "Lo siento, parece que mi servidor de correo no responde. ¡Por favor, inténtelo de nuevo más tarde!"
                        )
                    );
                    $("#success > .alert-danger").append("</div>");
                    //clear all fields
                    $("#frmMensaje").trigger("reset");
		}		
	});
	
}



$(document).ready(function(){
	create();
	
	$("#btnSend").click(function(){
		save();		
	});
});