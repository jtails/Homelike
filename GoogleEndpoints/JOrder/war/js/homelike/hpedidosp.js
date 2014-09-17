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


function addEvents(){
	$("#modalBox").load("fragments/comentarioshp.jsp");
	$('#datetimepicker1').datetimepicker({
		pickTime: false
	}).on('changeDate',function(ev){
		$("#tblPedidos").empty();
		$('#datetimepicker1').datetimepicker("hide");
		var split=$("#fecha").val().split("-");
		//months from 0 to 11 JavaScript
		var date=new Date(split[0],split[1]-1,split[2],0,0,0,0);
		google.appengine.homelike.pedidos.list(
			document.querySelector('#idProveedor').value,
			date
		)
	});
	/*if($("#fecha").val()==''){
		$("#message").addClass("alert alert-warning"); 
		$("#message").text("Seleccione fecha");
		$("#message").show();
	}*/
}

google.appengine.homelike.pedidos.list = function(idProveedor,fechaHoraUltimoPedido){
	$.blockUI({ css: { 
        border: 'none', 
        padding: '15px', 
        backgroundColor: '#000', 
        '-webkit-border-radius': '10px', 
        '-moz-border-radius': '10px', 
        opacity: .5, 
        color: '#fff' 
    } , message: 'Espere un momento... cargando historico de pedidos' });
	
	gapi.client.pedidoendpoint.listHistoricoPedidosByProveedor({'idProveedor': idProveedor,'fechaHoraUltimoPedido': fechaHoraUltimoPedido}).execute(
	function(output){
		output.items = output.items || [];
		for(var i=0;i<output.items.length;i++){
			var pedido=output.items[i];
			var detallePedido=pedido.detallePedido;
			var direccion=pedido.direccion;
			//Pedidos del Historico
			var style='odd';
			if(i % 2 == 0)
				style='odd';
			else
				style='even';
			addRow(style,pedido,detallePedido,direccion);
		}
		$.unblockUI();
	});	
}

function addRow(style,pedido,detallePedido,direccion){
	var total=0;
	var dpedido="";
	for(var i=0;i<detallePedido.length;i++){
		total+=detallePedido[i].producto.costoUnitario*detallePedido[i].cantidad;
		dpedido+="<strong>"+detallePedido[i].cantidad+"</strong> "+detallePedido[i].producto.cproducto.descripcion+" "+detallePedido[i].producto.cproducto.presentacion+",";
	}
	$("#tblPedidos").append(
		"<tr class='"+style+"'>"+
			"<td class='  sorting_1'>"+pedido.idPedido+"</td>"+
			"<td class=' '>"+direccion.calle+" "+direccion.nexterior+", "+direccion.ninterior+" "+direccion.colonia+" "+direccion.delegacion+" "+direccion.cp+" "+direccion.estado+"</td>"+
			"<td class=' '>$ "+total+"</td>"+
			"<td class=' '>"+dpedido+"</td>"+
			"<td class=' '>"+
				"<a href='#modalBox' data-toggle='modal' id='comentarios-"+pedido.idPedido+"' title='Comentarios' style='cursor: pointer'>"+
					"<div class='rateit' data-rateit-value='"+pedido.calificacion+"' data-rateit-resetable='false' data-rateit-readonly='true' data-rateit-step='1'></div>"+
				"</a>"+
			"</td>"+
		"</tr>"
	);
	$(".rateit").rateit();
	$("#comentarios-"+pedido.idPedido).click(function(){
		//Primero limpiamos los comentarios ya que el Modal Box es Generico
		$("#comentarioCliente").text((pedido.comentarioCliente!=undefined)?pedido.comentarioCliente:"");
		$("#comentarioProveedor").text((pedido.comentarioProveedor!=undefined)?pedido.comentarioProveedor:"");
		$("#comentarioEntregaCliente").text((pedido.comentarioEntregaCliente!=undefined)?pedido.comentarioEntregaCliente:"");
		$("#comentarioEntregaProveedor").text((pedido.comentarioEntregaProveedor!=undefined)?pedido.comentarioEntregaProveedor:"");
		if(pedido.fechaHoraPedido!=undefined && pedido.fechaHoraPedido!='')
			$("#fechaHoraPedido").text(new Date(pedido.fechaHoraPedido).toLocaleDateString()+" "+new Date(pedido.fechaHoraPedido).toLocaleTimeString());
		if(pedido.fechaHoraAceptacion!=undefined && pedido.fechaHoraAceptacion!='')
			$("#fechaHoraAceptacion").text(new Date(pedido.fechaHoraAceptacion).toLocaleDateString()+" "+new Date(pedido.fechaHoraAceptacion).toLocaleTimeString());
		if(pedido.fechaHoraEntrega!=undefined && pedido.fechaHoraEntrega!='')
			$("#fechaHoraEntrega").text(new Date(pedido.fechaHoraEntrega).toLocaleDateString()+" "+new Date(pedido.fechaHoraEntrega).toLocaleTimeString());
	});
	
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
	    			document.querySelector('#idProveedor').value,
	    			new Date()
	    		);
	    		addEvents();
	    	}
	    }else{
	    	alert('No Login');
	    	window.location.replace('index.jsp');
	    }
	});
}
