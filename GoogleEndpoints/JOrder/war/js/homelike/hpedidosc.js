/** google global namespace for Google projects. */
var google = google || {};
/** appengine namespace for Google Developer Relations projects. */
google.appengine = google.appengine || {};
/** homelike oferta proveedor namespace for this code. */
google.appengine.homelike = google.appengine.homelike || {};
google.appengine.homelike.pedidos = google.appengine.homelike.pedidos || {};

//google.appengine.homelike.WEB_CLIENT_ID='429890560769-odthisg69be4tj9q9k4jb3ordfjpj4kp.apps.googleusercontent.com';
google.appengine.homelike.WEB_CLIENT_ID='429890560769-1ahgmrm6v3o8o8diehah95j7locgshj3.apps.googleusercontent.com';
google.appengine.homelike.EMAIL_SCOPE='https://www.googleapis.com/auth/userinfo.email';

//Inside your callback function, load your Endpoint:
function init() {
	var apisToLoad;
	var loadCallback = function() {
	    if (--apisToLoad == 0) {
	      signin(true, userAuthed);
	    }
	};
	
	apisToLoad = 2; // must match number of calls to gapi.client.load()
	//Cambiar a HTTPS
	//var ROOT = 'https://homelike-dot-valid-keep-552.appspot.com/_ah/api';
	//var ROOT = 'http://localhost:8888/_ah/api';
	var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
	gapi.client.load('pedidoendpoint', 'v1',loadCallback, ROOT);
	gapi.client.load('oauth2', 'v2', loadCallback);
}

google.appengine.homelike.pedidos.list = function(idCuenta){
	$.blockUI({ css: { 
        border: 'none', 
        padding: '15px', 
        backgroundColor: '#000', 
        '-webkit-border-radius': '10px', 
        '-moz-border-radius': '10px', 
        opacity: .5, 
        color: '#fff' 
    } , message: 'Espere un momento... cargando historico de pedidos' });
	
	gapi.client.pedidoendpoint.listHistoricoPedidosByCuenta({'idCuenta': idCuenta}).execute(
	function(output){
		output.items = output.items || [];
		for(var i=0;i<output.items.length;i++){
			var pedido=output.items[i];
			var detallePedido=pedido.detallePedido;
			var direccion=pedido.direccion;
			var proveedor=pedido.proveedor;
			//Pedidos del Historico
			var style='odd';
			if(i % 2 == 0)
				style='odd';
			else
				style='even';
			addRow(style,pedido,detallePedido,direccion,proveedor);
		}
		$.unblockUI();
	});
	
	
}

function addRow(style,pedido,detallePedido,direccion,proveedor){
	var total=0;
	for(var i=0;i<detallePedido.length;i++){
		total+=detallePedido[i].producto.costoUnitario*detallePedido[i].cantidad;
	}
	$("#tblPedidos").append(
		"<tr class='"+style+"'>"+
			"<td class='  sorting_1'>"+pedido.idPedido+"</td>"+
			"<td class=' '>"+direccion.calle+" "+direccion.colonia+" "+direccion.delegacion+"</td>"+
			"<td class=' '>"+proveedor.nombre+"</td>"+
			"<td class=' '>"+total+"</td>"+
			"<td class=' '>"+pedido.fechaHoraPedido+"</td>"+
		"</tr>"
	);
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
	    			document.querySelector('#idCuenta').value
	    		);
	    	}
	    }else{
	    	alert('No Login');
	    	window.location.replace('index.jsp');
	    }
	});
}
