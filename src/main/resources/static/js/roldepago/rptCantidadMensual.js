function report2(){	
	var selectBox = document.getElementById("anio");
    var id = selectBox.options[selectBox.selectedIndex].value;
	var color = Chart.helpers.color;
	$.ajax({
		url : "/roldepago/dataRptCantidadMensual/"+2020,
		method : 'GET',
		success : function(response){
			
			console.log(response);
			
			var toData = [];
			var toLabels = [];
			var toCant = [];
			var toSum = [];
			var toTipos = [];
			
			toTipos.push("Cantidad");
			toTipos.push("Sumatoria");
			
			$.each(response, function(i, item){
				toLabels.push(item.mes);
				toCant.push(item.cantidad);
				toSum.push(0);
			});
			console.log(toLabels);
			console.log(toCant);
			console.log(toSum);
				var dat = {
					label: "Cantidad",
					backgroundColor: color(getRandomColor()).alpha(0.5).rgbString(),			
					borderWidth: 1,
					data: [1,2,3,4,5,6,7,8,9,10,11,12],
				}
				console.log(dat);
				toData.push(dat);
				var dat2 = {
					label: "Sumatoria",
					backgroundColor: color(getRandomColor()).alpha(0.5).rgbString(),			
					borderWidth: 1,
					data: [1,2,3,4,5,6,7,8,9,10,11,12],
				}
				console.log(dat2);
				toData.push(dat2);
									
			var barChartData = {
				labels: toLabels,
				datasets: toData
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
							text: 'Cantidad de Roles por Mes'
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