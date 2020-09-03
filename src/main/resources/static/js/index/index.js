function create(){	
	$.ajax({
		url : "/mensajeContacto/create/",
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
	console.log(requestBody);			
	$.ajax({
		url : developURL + "mensajeContacto/save",
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
                        "<strong>Your message has been sent. </strong>"
                    );
                    $("#success > .alert-success").append("</div>");
                    //clear all fields
                    $("#contactForm").trigger("reset");
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
                            "Sorry, it seems that my mail server is not responding. Please try again later!"
                        )
                    );
                    $("#success > .alert-danger").append("</div>");
                    //clear all fields
                    $("#contactForm").trigger("reset");
		}		
	});
	
}



$(document).ready(function(){
	create();
	
	$("#btnSend").click(function(){
		save();		
	});
});