/** google global namespace for Google projects. */
var google = google || {};
/** appengine namespace for Google Developer Relations projects. */
google.appengine = google.appengine || {};
/** homelike oferta proveedor namespace for this code. */
google.appengine.homelike = google.appengine.homelike || {};
google.appengine.homelike.pedidos = google.appengine.homelike.pedidos || {};

google.appengine.homelike.WEB_CLIENT_ID='429890560769-odthisg69be4tj9q9k4jb3ordfjpj4kp.apps.googleusercontent.com';
//google.appengine.homelike.WEB_CLIENT_ID='429890560769-1ahgmrm6v3o8o8diehah95j7locgshj3.apps.googleusercontent.com';
google.appengine.homelike.EMAIL_SCOPE='https://www.googleapis.com/auth/userinfo.email';

var datos=new Array();
var labels=new Array();
var datos2=new Array();
var labels2=new Array();
var datos3=new Array();
var labels3=new Array();


//Inside your callback function, load your Endpoint:
function init() {
	var apisToLoad;
	var loadCallback = function() {
	    if (--apisToLoad == 0) {
	      signin(true, userAuthed);
	    }
	};
	
	apisToLoad = 2; // must match number of calls to gapi.client.load()
	var ROOT = 'http://localhost:8888/_ah/api';
	//var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
	gapi.client.load('pedidoendpoint', 'v1',loadCallback, ROOT);
	gapi.client.load('oauth2', 'v2', loadCallback);
}


google.appengine.homelike.pedidos.list = function(idProveedor){
	$.blockUI({ css: { 
        border: 'none', 
        padding: '15px', 
        backgroundColor: '#000', 
        '-webkit-border-radius': '10px', 
        '-moz-border-radius': '10px', 
        opacity: .5, 
        color: '#fff' 
    } , message: 'Espere un momento... cargando estadisticas' });

	gapi.client.pedidoendpoint.getHistoricoPedidosByDayProveedor({'idProveedor': idProveedor}).execute(
		function(output){
			output.items = output.items || [];
			for(var i=0;i<output.items.length;i++){
				var dato=output.items[i];
				datos.push([i,dato.value]);
				labels.push([i,dato.label]);
			}
			
			$.plot($("#bar-chart"), [ datos ], {
		        series: {
		            stack: 0,
		            lines: { show: false, fill: true, steps: false },
		            bars: { show: true, barWidth: 0.4 }
		        },
		        grid: {
		            borderWidth: 0, hoverable: true, color: "#777"
		        },
		        colors: ["#ff6c24"],
		        bars: {
		              show: true,
		              lineWidth: 0,
		              fill: true,
		              fillColor: { colors: [ { opacity: 0.9 }, { opacity: 0.8 } ] }
		        },
		        xaxis:{
		        	ticks:labels
		        }
		    });
		});
    
    gapi.client.pedidoendpoint.getHistoricoPedidosByMesProveedor({'idProveedor': idProveedor}).execute(
    	function(output){
    		output.items = output.items || [];
    		for(var i=0;i<output.items.length;i++){
    			var dato=output.items[i];
    			datos2.push([i,dato.value]);
    			labels2.push([i,dato.label]);
    		}
    			
    		$.plot($("#bar-chart2"), [ datos2 ], {
    	        series: {
    	            stack: 0,
    	            lines: { show: false, fill: true, steps: false },
    	            bars: { show: true, barWidth: 0.4 }
    	        },
    	        grid: {
    	            borderWidth: 0, hoverable: true, color: "#777"
    	        },
    	        colors: ["#ff6c24"],
    	        bars: {
    	              show: true,
    	              lineWidth: 0,
    	              fill: true,
    	              fillColor: { colors: [ { opacity: 0.9 }, { opacity: 0.8 } ] }
    	        },
    	        xaxis:{
    	        	ticks:labels2
    	        }
    	    });
    	});
    
    gapi.client.pedidoendpoint.getHistoricoGananciaByMesProveedor({'idProveedor': idProveedor}).execute(
        function(output){
        	output.items = output.items || [];
        	for(var i=0;i<output.items.length;i++){
        		var dato=output.items[i];
        		datos3.push([i,dato.value]);
        		labels3.push([i,dato.label]);
        	}
        		
        	$.plot($("#bar-chart3"), [ datos3 ], {
                series: {
                    stack: 0,
                    lines: { show: false, fill: true, steps: false },
                    bars: { show: true, barWidth: 0.4 }
                },
                grid: {
                    borderWidth: 0, hoverable: true, color: "#777"
                },
                colors: ["#009933"],
                bars: {
                	show: true,
                    lineWidth: 0,
                    fill: true,
        	        fillColor: { colors: [ { opacity: 0.9 }, { opacity: 0.8 } ] }
                },
        	    xaxis:{
        	        ticks:labels3
        	    }
        	});
        });
	$.unblockUI();
}


//---------------------OAuth 2.0-----------------------------

function signin(mode, authorizeCallback) {
	gapi.auth.authorize({client_id: google.appengine.homelike.WEB_CLIENT_ID,
		scope: google.appengine.homelike.EMAIL_SCOPE, immediate: mode},
	    authorizeCallback);
}


function userAuthed() {
	var request =
		gapi.client.oauth2.userinfo.get().execute(function(output) {
	    if(output!=undefined && output.verified_email!=undefined){
	    	if(output.verified_email){
	    		google.appengine.homelike.pedidos.list(
	    				document.querySelector('#idProveedor').value
	    		);
	    	}
	    }else{
	    	alert('No Login');
	    	window.location.replace('index.jsp');
	    }
	});
}

