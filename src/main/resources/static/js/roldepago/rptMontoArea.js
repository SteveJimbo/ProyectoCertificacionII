var barChartData;

function report2(){	
	var selectBox = document.getElementById("anio");
    var id = selectBox.options[selectBox.selectedIndex].value;
	var selectBox = document.getElementById("mes");
    var id2 = selectBox.options[selectBox.selectedIndex].value;
	var selectBox = document.getElementById("area");
    var id3 = selectBox.options[selectBox.selectedIndex].value;
	var color = Chart.helpers.color;
	$.ajax({
		url : "/roldepago/dataRptMontoArea/"+id+"/"+id2+"/"+id3,
		//url : "/roldepago/dataRptMontoArea/"+2020+"/"+1+"/"+1,
		method : 'GET',
		success : function(response){
			
			//console.log(response);
			var titulo = "Sumatoria";
			var data = [];
			var area = [];
			$.each(response, function(i, item){
				area.push(item.area);
				data.push(item.monto);
			});
									
			barChartData = {
				labels: ['Sumatoria'],
				datasets: [{
					label: area,
					backgroundColor: color(getRandomColor()).alpha(0.5).rgbString(),			
					borderWidth: 1,
					data: data
				}]
			};
			
			//console.log(barChartData);
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
	});
	
}