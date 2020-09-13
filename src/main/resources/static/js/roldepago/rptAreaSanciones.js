var barChartData;

function report2(){	
	var selectBox = document.getElementById("anio");
    var id = selectBox.options[selectBox.selectedIndex].value;
	var selectBox = document.getElementById("mes");
    var id2 = selectBox.options[selectBox.selectedIndex].value;
	var color = Chart.helpers.color;
	$.ajax({
		url : "/roldepago/dataRptAreaSanciones/"+id+"/"+id2,
		//url : "/roldepago/dataRptMontoArea/"+2020+"/"+1+"/"+1,
		method : 'GET',
		success : function(response){
			
			console.log(response);
			
			var titulo = "Sumatoria";
			var data = [];
			var area = [];
			
			var toLabels = [];
			var toData = [];
			var toSanc = [];
			
			$.each(response, function(i, item){
				toLabels.push(item.area);
				$.each(item.sanciones, function(x,elemento){
					if(!toSanc.includes(elemento.sancion)){
						toSanc.push(elemento.sancion);
					}
				});
			});
			
			$.each(toSanc, function(i,item){
				var valores = new Array(toLabels.lenght).fill(0);
				$.each(response,function(x,elemento){
					$.each(elemento.sanciones,function(y,valor){
						if(valor.sancion == item){
							valores[toLabels.indexOf(elemento.area)] = valor.monto;
						}
					});
				});
				var dat = {
					label: item,
					backgroundColor: color(getRandomColor()).alpha(0.5).rgbString(),			
					borderWidth: 1,
					data: valores,
				}
				toData.push(dat);
			});
									
			barChartData = {
				labels: toLabels,
				datasets: toData
			};
			
			console.log(barChartData);
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
							text: 'Descuentos por Área y Mes'
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