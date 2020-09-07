var barChartData;

function report2(){	
	var selectBox = document.getElementById("anio");
    var id = selectBox.options[selectBox.selectedIndex].value;
	var color = Chart.helpers.color;
	$.ajax({
		//url : "/roldepago/dataRptCantidadMensual/"+2020,
		url : "/roldepago/dataRptCantidadMensual/"+id,
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
				toSum.push(item.sumatoria);
			});
			console.log(toLabels);
			console.log(toCant);
			console.log(toSum);
				var dat = {
					label: "Cantidad",
					backgroundColor: color(getRandomColor()).alpha(0.5).rgbString(),			
					borderWidth: 1,
					data: toCant,
				}
				console.log(dat);
				toData.push(dat);
				var dat2 = {
					label: "Sumatoria",
					backgroundColor: color(getRandomColor()).alpha(0.5).rgbString(),			
					borderWidth: 1,
					data: toSum,
				}
				console.log(dat2);
				toData.push(dat2);
									
			barChartData = {
				labels: toLabels,
				datasets: toData
			};
			
			cargar();	
			
		},
		error : function(err){
			console.log(err);
		}		
	});	
}

function cargar(){
	$(document).ready(function(){
	
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
	});
}