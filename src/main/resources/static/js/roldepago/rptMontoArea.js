function report(){	
	$.ajax({
		url : "/roldepago/dataRptMontoArea",
		method : 'GET',
		success : function(response){
			console.log(response);
			
		},
		error : function(err){
			console.log(err);
		}		
	});	
}

$(document).ready(function(){
	
	report();

	
});