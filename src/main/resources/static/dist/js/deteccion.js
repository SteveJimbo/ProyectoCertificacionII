jQuery(document).ready(function($){
	$(document).ready(function(){
		var pathname = window.location.pathname;
		var real = pathname.split("/");
		
		if(real[1] == "index.html" || real[1] == "")
			$('#apag').addClass('active');
		if(real[1] == "trabajador")
			$('#atbr').addClass('active');	
		if(real[1] == "roldepago")
			$('#ardp').addClass('active');
		if(real[1] == "sancion")
			$('#asan').addClass('active');
		if(real[1] == "penalidad")
			$('#apen').addClass('active');
		if(real[1] == "cargo")
			$('#acar').addClass('active');
			
	});
});