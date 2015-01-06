/** google global namespace for Google projects. */
var google = google || {};
/** appengine namespace for Google Developer Relations projects. */
google.appengine = google.appengine || {};
/** homelike oferta proveedor namespace for this code. */
google.appengine.homelike = google.appengine.homelike || {};
google.appengine.homelike.proveedor = google.appengine.homelike.proveedor || {};

//google.appengine.homelike.WEB_CLIENT_ID='429890560769-vgaolojo01ke78sbhut2sgvf3qcol21m.apps.googleusercontent.com';
google.appengine.homelike.WEB_CLIENT_ID='429890560769-e15fqu56oc53v2cs1vfss4a4bla3vb7s.apps.googleusercontent.com';
google.appengine.homelike.EMAIL_SCOPE='https://www.googleapis.com/auth/userinfo.email';

//Inside your callback function, load your Endpoint:
function init() {
	var apisToLoad;
	var loadCallback = function() {
	    if (--apisToLoad == 0) {
	      signin(true, userAuthed);
	    }
	};
	
	apisToLoad = 1; // must match number of calls to gapi.client.load()
	//var ROOT = 'http://localhost:57424/_ah/api';
	var ROOT = 'https://admin-dot-steam-form-673.appspot.com/_ah/api';
	gapi.client.load('oauth2', 'v2', loadCallback);
}


google.appengine.homelike.proveedor.load = function(){
	$("#calificacions").attr('data-rateit-value',currentProveedor.calificacion);
	$(".rateit").rateit();
	$('#nombrep').text(currentProveedor.nombre+" :: "+currentProveedor.slogan+" :: ");
	$("#direccion").append(
	"<li>"+
		"<i class='fa fa-building-o'></i>"+
		"<strong>Facturacion : </strong>"+
		currentProveedor.razonSocial+" , "+currentProveedor.rfc+
	"</li>"+
	"<li>"+
		"<i class='fa fa-phone'></i>"+
	 	"<strong>Telefono : </strong>"+
	 	currentProveedor.telefono+
	"</li>"+
	"<li>"+
		"<i class='fa fa-sign-in'></i>"+
		"<strong>Direccion : </strong>"+
		currentProveedor.calle+" "+currentProveedor.nexterior+" ,"+currentProveedor.colonia+" "+currentProveedor.delegacion+
		" "+currentProveedor.estado+" "+currentProveedor.pais+", "+currentProveedor.cp+
	"</li>"+
	"<li>"+
		"<a href='#modalBox' data-toggle='modal'> [ Ver Mapa ] </a>"+
	"</li>");
	
	var productos=currentProveedor.productos;
	if(productos!=undefined){
		for(var i=0;i<productos.length;i++){
			$("#tblProductos").append(
				"<tr>" +
					"<td>"+(productos[i].cproducto!=undefined?productos[i].cproducto.descripcion:productos[i].descripcion)+"</td>" +
					"<td>"+(productos[i].cproducto!=undefined?productos[i].cproducto.presentacion:productos[i].presentacion)+"</td>" +
					"<td>"+(productos[i].costoUnitario)+"</td>" +
					"<td>"+(productos[i].status==0?'ACTIVO':'INACTIVO')+"</td>" +
				"</tr>"
			);
		}
	}else{
		$("#message").addClass("alert alert-warning"); 
		$("#message").text("El proveedor no cuenta con Productos");
	}
	
	var pedidos=currentProveedor.pedidos;
	if(pedidos!=undefined){
		for(var i=0;i<pedidos.length;i++){
			var pedido=pedidos[i];
			var detallePedido=pedido.detallePedido;
			var direccion=pedido.direccion;
			var proveedor=currentProveedor;
			var style='odd';
			if(i % 2 == 0)
				style='odd';
			else
				style='even';
			addPedido(style,pedido,detallePedido,direccion,proveedor);
		}
	}else{
		$("#message2").addClass("alert alert-warning"); 
		$("#message2").text("El proveedor no cuenta con Pedidos");
	}
	
}

function addmodalBox(){
	$("#modalBox").load("fragments/direccionp.jsp");
	$("#modalBox2").load("fragments/comentariosp.jsp");
}



function addPedido(style,pedido,detallePedido,direccion,proveedor){
	var total=0;
	var dpedido="";
	var status;
	switch(pedido.status){
		case 0: 
			status="Nuevo"
			break;
		case 1: 
			status="Confirmado"
			break;
		case 2: 
			status="Terminado"
			break;
	}
	for(var i=0;i<detallePedido.length;i++){
		total+=detallePedido[i].producto.costoUnitario*detallePedido[i].cantidad;
		if(detallePedido[i].producto.cproducto!=undefined)
			dpedido+="<strong>"+detallePedido[i].cantidad+"</strong> "+detallePedido[i].producto.cproducto.descripcion+" "+detallePedido[i].producto.cproducto.presentacion+",";
		else
			dpedido+="<strong>"+detallePedido[i].cantidad+"</strong> "+detallePedido[i].producto.descripcion+" "+detallePedido[i].producto.presentacion+",";
	}
	$("#tblPedidos").append(
		"<tr class='"+style+"'>"+
			"<td class='  sorting_1'>"+pedido.idPedido+"</td>"+
			"<td class=' '>"+direccion.calle+" "+direccion.nexterior+", "+direccion.ninterior+" "+direccion.colonia+" "+direccion.delegacion+" "+direccion.cp+" "+direccion.estado+"</td>"+
			"<td class=' '>$ "+total+"</td>"+
			"<td class=' '>"+dpedido+"</td>"+
			"<td class=' '>"+
				"<a href='#modalBox2' class='fa fa-comments-o' data-toggle='modal' id='comentarios-"+pedido.idPedido+"' title='Comentarios' style='cursor: pointer'>"+
					" Comentarios"+
				"</a>"+
			"</td>"+
			"<td>"+
				new Date(pedido.fechaHoraPedido).toLocaleDateString()+" "+
				new Date(pedido.fechaHoraPedido).toLocaleTimeString()+
			"</td>"+
			"<td>"+status+"</td>"+
		"</tr>"
	);
	$("#comentarios-"+pedido.idPedido).click(function(){
		//Primero limpiamos los comentarios ya que el Modal Box es Generico
		$("#comentarioCliente").text((pedido.comentarioCliente!=undefined)?pedido.comentarioCliente:"");
		$("#comentarioProveedor").text((pedido.comentarioProveedor!=undefined)?pedido.comentarioProveedor:"");
		$("#comentarioEntregaCliente").text((pedido.comentarioEntregaCliente!=undefined)?pedido.comentarioEntregaCliente:"");
		$("#comentarioEntregaProveedor").text((pedido.comentarioEntregaProveedor!=undefined)?pedido.comentarioEntregaProveedor:"");
		$("#fechaHoraPedido").text((pedido.fechaHoraPedido!=undefined)?new Date(pedido.fechaHoraPedido).toLocaleDateString()+" "+new Date(pedido.fechaHoraPedido).toLocaleTimeString():"");
		$("#fechaHoraAceptacion").text((pedido.fechaHoraAceptacion!=undefined)?new Date(pedido.fechaHoraAceptacion).toLocaleDateString()+" "+new Date(pedido.fechaHoraAceptacion).toLocaleTimeString():"");
		$("#fechaHoraEntrega").text((pedido.fechaHoraEntrega!=undefined)?new Date(pedido.fechaHoraEntrega).toLocaleDateString()+" "+new Date(pedido.fechaHoraEntrega).toLocaleTimeString():"");
		$("#comentarioep").attr('src',proveedor.logo);
		$("#comentariop").attr('src',proveedor.logo);
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
	    		google.appengine.homelike.proveedor.load();
	    		addmodalBox();
	    	}
	    }else{
	    	alert('No Login');
	    	window.location.replace('index.jsp');
	    }
	});
}
