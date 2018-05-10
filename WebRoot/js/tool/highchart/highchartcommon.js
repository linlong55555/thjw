/**
 * highchart 调用后台封装
 * @param options
 */
function hchartloaddata(options){
	$.ajax({
		type:"post",
		url:options.url,
		data:options.param,
		dataType:"json",
		success:function(){
			highchartinit(options);
		}
	});
}

function highchartinit(options){
	var hchart = new Highcharts.Chart({
	        chart: {
	            renderTo: options.id,
	            type: options.highcharttype,
	            margin: 75,
	            options3d: {
	                enabled: true,
	                alpha: 15,
	                beta: 15,
	                depth: 50,
	                viewDistance: 25
	            }
	        },
	        title: {
	            text: options.title
	        },
	        subtitle: {
	            text: options.subtitle
	        },
	        plotOptions:options.plot,
	        series:options.series
	    });
}