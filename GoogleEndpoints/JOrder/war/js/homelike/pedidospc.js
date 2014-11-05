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

//Inside your callback function, load your Endpoint:
function init() {
	var apisToLoad;
	var loadCallback = function() {
	    if (--apisToLoad == 0) {
	      signin(true, userAuthed);
	    }
	};
	
	$.blockUI({ css: { 
        border: 'none', 
        padding: '15px', 
        backgroundColor: '#000', 
        '-webkit-border-radius': '10px', 
        '-moz-border-radius': '10px', 
        opacity: .5, 
        color: '#fff' 
    } , message: 'Espere un momento... cargando pedidos' });
	
	apisToLoad = 2; // must match number of calls to gapi.client.load()
	var ROOT = 'http://localhost:8888/_ah/api';
	//var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
	gapi.client.load('pedidoendpoint', 'v1',loadCallback, ROOT);
	gapi.client.load('oauth2', 'v2', loadCallback);
}


google.appengine.homelike.pedidos.update = function(idPedido,comentario,status,date){
	gapi.client.pedidoendpoint.insertPedido({'idPedido': idPedido,'comentarioProveedor': comentario,'fechaHoraAceptacion':date,'status': status}).execute(
		function(output){
			var pedido=output;
			//actualizamos el status del pedido
			//setStatus(pedido);
			//actualizamos los comentarios
			//setComentarios(pedido);
			$("#btnapedido-"+pedido.idPedido).attr('disabled',true);
			$('#modalBox2').modal('hide');
	});
}

function addEvents(){
	$("#vclasica").click(function(){
		$("#contenido").load("fragments/pedidosp.jsp");
	});
	
	$("#modalBox").load("fragments/comentarioshp.jsp");
	$("#modalBox2").load("fragments/pedidospcm.jsp",function() {
		//Evento lanzado cuando se confirma un pedido, recordar que el ModalBox es Generico y que cada cliente en cada navegador Web solo tendra una instancia de este
		var btnenviar = document.querySelector('#btnenviar');
		btnenviar.addEventListener('click', function(e) {
			var idPedido=$("#id").val();
			google.appengine.homelike.pedidos.update(idPedido,$("#comentario").val(),1,new Date());
		});
	});
}


google.appengine.homelike.pedidos.list = function(idProveedor){
	gapi.client.pedidoendpoint.listPedidosByProveedor({'idProveedor': idProveedor}).execute(
		function(output){
			output.items = output.items || [];
			if(output.items.length==0){
				$("#message").addClass("alert alert-warning"); 
				$("#message").text("No tiene pedidos activos");
				$("#message").show();
				$.unblockUI();
			}else{
				for(var i=0;i<output.items.length;i++){
					var pedido=output.items[i];
					var detallePedido=pedido.detallePedido;
					var direccion=pedido.direccion;
					var proveedor=pedido.proveedor;
					var style='odd';
					if(i % 2 == 0)
						style='odd';
					else
						style='even';
					addRow(style,pedido,detallePedido,direccion,proveedor);
				}
				$.unblockUI();
			}
	});
}

function addRow(style,pedido,detallePedido,direccion,proveedor){
	var total=0;
	var dpedido="";
	var status;
	if(pedido.status>=1){
		status="disabled";
	}
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
				"<a href='#modalBox' class='fa fa-comments-o' data-toggle='modal' id='comentarios-"+pedido.idPedido+"' title='Comentarios' style='cursor: pointer'>"+
					" Comentarios"+
				"</a>"+
			"</td>"+
			"<td>"+
				"<a href='#modalBox2' "+status+" class='btn btn-sm btn-info' id='btnapedido-"+pedido.idPedido+"' data-toggle='modal'>Confirmar pedido</a>"+
			"</td>"+
		"</tr>"
	);
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
	$("#btnapedido-"+pedido.idPedido).click(function(){
		$("#comentario").val("");
		$("#id").val(pedido.idPedido);
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
	    			document.querySelector('#idProveedor').value
	    		);
	    		addEvents();
	    	}
	    }else{
	    	alert('No Login');
	    	window.location.replace('index.jsp');
	    }
	});
}
