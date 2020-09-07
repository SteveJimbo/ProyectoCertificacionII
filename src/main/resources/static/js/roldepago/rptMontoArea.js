function report2(){	
	var selectBox = document.getElementById("anio");
    var id = selectBox.options[selectBox.selectedIndex].value;
	var selectBox = document.getElementById("mes");
    var id2 = selectBox.options[selectBox.selectedIndex].value;
	var selectBox = document.getElementById("area");
    var id3 = selectBox.options[selectBox.selectedIndex].value;
	var color = Chart.helpers.color;
	$.ajax({
		//url : "/dataRptMontoArea/"+id+"/"+id2+"/"+id3,
		url : "/roldepago/dataRptMontoArea/"+2020+"/"+1+"/"+1,
		method : 'GET',
		success : function(response){
			
			console.log(response);
			var titulo = "Sumatoria";
			var data = [];
			var area = [];
			$.each(response, function(i, item){
				area.push(item.area);
				data.push(item.monto);
			});
									
			var barChartData = {
				labels: titulo,
				datasets: [{
					label: area,
					backgroundColor: color(getRandomColor()).alpha(0.5).rgbString(),			
					borderWidth: 1,
					data: data
				}]
			};
			
			console.log(barChartData);
			
			window.onload = function() {
				console.log("llega");
				var ctx = document.getElementById('canvas').getContext('2d');
				window.myBar = new Chart(ctx, {
					type: 'bar',
					data: barChartData,
					options: {
						responsive: true,
						legend: {
							position: 'top',
						},
						title: {
							display: true,
							text: 'Sumatoria por área'
						},
						scales: {
					        yAxes: [{
					            ticks:{
									beginAtZero: true
								}
					        }]
					    }
						
					}
				});
		
			};	
			
		},
		error : function(err){
			console.log(err);
		}		
	});	
}

function report3(){
	
	var color = Chart.helpers.color;
	var barChartData = {
		labels: ['Certificación I', 'Ingeniería Web', 'Métodos numéricos', 'Estructura de datos', 'Metodología'],
		datasets: [{
			label: 'diegoismael',
			backgroundColor: color(getRandomColor()).alpha(0.5).rgbString(),			
			borderWidth: 1,
			data: [
				10,
				2,
				5,
				9,
				8				
			]
		}, {
			label: 'dsanchez',
			backgroundColor: color(getRandomColor()).alpha(0.5).rgbString(),			
			borderWidth: 1,
			data: [
				16,
				12,
				4,
				0,
				9
			]
		}]

	};

	window.onload = function() {
		var ctx = document.getElementById('canvas').getContext('2d');
		window.myBar = new Chart(ctx, {
			type: 'bar',
			data: barChartData,
			options: {
				responsive: true,
				legend: {
					position: 'top',
				},
				title: {
					display: true,
					text: 'Matrículas por materia y por usuario'
				}
			}
		});

	};		
}

$(document).ready(function(){
	
	report2();		

});