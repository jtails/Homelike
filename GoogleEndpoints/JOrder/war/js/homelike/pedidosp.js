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
	//var ROOT = 'http://localhost:8888/_ah/api';
	var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
	gapi.client.load('pedidoendpoint','v1',loadCallback, ROOT);
	gapi.client.load('oauth2', 'v2', loadCallback);
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
				var dispositivo=pedido.dispositivo;
				var direccion=pedido.direccion;
				var proveedor=pedido.proveedor;
				addRow(pedido,detallePedido,direccion,dispositivo,proveedor);
			}
			$.unblockUI();
		}
	});
}

google.appengine.homelike.pedidos.update = function(idPedido,comentarioProveedor,status,date){
	gapi.client.pedidoendpoint.insertPedido({'idPedido': idPedido,'comentarioProveedor': comentarioProveedor,'fechaHoraAceptacion':date,'status': status}).execute(
		function(output){
			var pedido=output;
			//actualizamos el status del pedido
			setStatus(pedido);
			//actualizamos los comentarios
			//setComentarios(pedido);
			$("#pcontenido-"+pedido.idPedido).html("<div class='alert alert-success'>Pedido confirmado!!</div>");
	});
}
	
function addRow(pedido,detallePedido,direccion,dispositivo,proveedor){
	$("#pedidos").append(	
			"<div class='row'>"+
				"<div class='col-md-12'>"+
					"<div class='widget'>"+
						"<div class='widget-head'>"+
							"<div class='pull-left'>Pedido "+pedido.idPedido+" : "+
								new Date(pedido.fechaHoraPedido).toLocaleDateString()+" "+
								new Date(pedido.fechaHoraPedido).toLocaleTimeString()+" .:: "+
								parseInt((new Date()-new Date(pedido.fechaHoraPedido))/(1000*60))+" Minutos atras ::."+
							"</div>"+
							"<div class='widget-icons pull-right' id='status-"+pedido.idPedido+"'>"+
								"<a href='#' class='wminimize'><i class='fa fa-chevron-up'></i></a>"+
								"<a href='#' class='wclose'><i class='fa fa-times'></i></a>"+
							"</div>"+
							"<div class='clearfix'></div>"+
						"</div>"+

						"<div class='widget-content'>"+
							"<div class='pad' id='pcontenido-"+pedido.idPedido+"'>"+
								
							"</div>"+

							"<div class='widget-foot'>"+
								"<div class='pull-left'>"+
									"Total :<span id='total-"+pedido.idPedido+"'></span>"+
								"</div>"+
								"<ul class='pagination pagination-sm pull-right'>"+
									"<li><a id='gpedidoc-"+pedido.idPedido+"' href='#'>Productos</a></li>"+
									"<li><a id='dirpedidoc-"+pedido.idPedido+"' href='#'>Direccion envio</a></li>"+
									//"<li><a id='compedidoc-"+pedido.idPedido+"' href='#'>Comentarios</a></li>"+
									"<li><a id='spedidoc-"+pedido.idPedido+"' href='#'>Confirmar pedido</a></li>"+
								"</ul>"+
								"<div class='clearfix'></div>"+
							"</div>"+
						"</div>"+
					"</div>"+
				"</div>"+
			"</div>"
	);
	setStatus(pedido);
	setgPedido(pedido,detallePedido);
	
	$("#gpedidoc-"+pedido.idPedido).click(function(){
		setgPedido(pedido,detallePedido);
	});
			
	$("#dirpedidoc-"+pedido.idPedido).click(function(){
		$("#pcontenido-"+pedido.idPedido).html(
			"<div class='matter'>"+
				"<div class='container'>"+
					"<div class='row'>"+
			            "<div class='col-md-6'>"+
			            	"<div class='widget'>"+
			                	"<div class='widget-head'>"+
			                  		"<div class='pull-left'>Direccion de envio</div>"+
			                  		"<div class='widget-icons pull-right'>"+
			                    		"<a href='#' class='wminimize'><i class='fa fa-chevron-up'></i></a>"+ 
			                    		"<a href='#' class='wclose'><i class='fa fa-times'></i></a>"+
			                  		"</div>"+  
			                  		"<div class='clearfix'></div>"+
			                	"</div>"+
			                	"<div class='widget-content'>"+
			                  		"<ul class='activity' id='tblDireccion-"+pedido.idPedido+"'>"+
			                  		"</ul>"+
			                	"</div>"+
			              	"</div>"+
			            "</div>"+
			            "<div class='col-md-6'>"+
			            	"<div class='widget'>"+
			                	"<div class='widget-head'>"+
			                  		"<div class='pull-left'>Google Maps</div>"+
			                  		"<div class='widget-icons pull-right'>"+
			                    		"<a href='#' class='wminimize'><i class='fa fa-chevron-up'></i></a>"+ 
			                    		"<a href='#' class='wclose'><i class='fa fa-times'></i></a>"+
			                  		"</div>"+  
			                  		"<div class='clearfix'></div>"+
			                	"</div>"+
			                	"<div class='widget-content'>"+
			                  		"<div id='map-canvasc-"+pedido.idPedido+"'></div>"+
			                	"</div>"+
			              	"</div>"+ 
			            "</div>"+ 
			    	"</div>"+
				"</div>"+
			"</div>"		
		);

		$("#tblDireccion-"+pedido.idPedido).html(
			"<li>"+
				"<i class='fa fa-sign-in'></i>"+
				"<strong>Direccion : </strong>"+
				direccion.calle+" "+direccion.nexterior+" ,"+direccion.colonia+" "+direccion.delegacion+
				" "+direccion.estado+" "+direccion.pais+", "+direccion.cp+
			"</li>"+
			"<li>"+
				"<i class='fa fa-sign-in'></i>"+
				"<strong>Entre calles : </strong>"+
				direccion.calle1+" ,"+direccion.calle2+
			"</li>"+
			"<li>"+
				"<i class='fa fa-sign-in'></i>"+
				"<strong>Referencias : </strong>"+
				direccion.referencia1+" ,"+direccion.referencia2+
			"</li>"+
			"<li>"+
				"<i class='fa fa-sign-in'></i>"+
				"<strong>Alias : </strong>"+
				dispositivo.alias+
			"</li>"+
			"<li>"+
				"<i class='fa fa-sign-in'></i>"+
				"<strong>Comentario cliente: </strong>"+
				((pedido.comentarioCliente!=undefined)?pedido.comentarioCliente:"")+
			"</li>"
		);
		setMap(direccion.latitud,direccion.longitud,"map-canvasc-"+pedido.idPedido);
	});	
		
	/*$("#compedidoc-"+pedido.idPedido).click(function(){
		$("#pcontenido-"+pedido.idPedido).html(
			"<div class='matter'>"+
				"<div class='container'>"+
					"<div class='row'>"+
			            "<div class='col-md-6'>"+
			            	"<div class='widget'>"+
			                	"<div class='widget-head'>"+
			                  		"<div class='pull-left'>Comentarios</div>"+
			                  		"<div class='widget-icons pull-right'>"+
			                    		"<a href='#' class='wminimize'><i class='fa fa-chevron-up'></i></a>"+ 
			                    		"<a href='#' class='wclose'><i class='fa fa-times'></i></a>"+
			                  		"</div>"+  
			                  		"<div class='clearfix'></div>"+
			                	"</div>"+
			                	"<div class='widget-content'>"+
			                  		"<ul class='activity'>"+
			                  			"<li>"+
			                  				"<i class='fa fa-comment-o'></i>"+
			                  				"<strong>Comentario cliente </strong>"+
			                  				((pedido.comentarioCliente!=undefined)?pedido.comentarioCliente:"")+
			                  			"</li>"+
			                  			"<li>"+
		                  					"<i class='fa fa-comments-o'></i>"+
		                  					"<strong>Comentario entrega proveedor </strong>"+
		                  					((pedido.comentarioProveedor!=undefined)?pedido.comentarioProveedor:"")+
		                  				"</li>"+
		                  				"<li>"+
	                  						"<i class='fa fa-comment-o'></i>"+
	                  						"<strong>Comentario entrega cliente </strong>"+
	                  						((pedido.comentarioEntregaCliente!=undefined)?pedido.comentarioEntregaCliente:"")+
	                  					"</li>"+
	                  					"<li>"+
                  							"<i class='fa fa-comments-o'></i>"+
                  							"<strong>Comentario proveedor </strong>"+
                  							((pedido.comentarioEntregaProveedor!=undefined)?pedido.comentarioEntregaProveedor:"")+
                  						"</li>"+
			                  		"</ul>"+
			                	"</div>"+
			              	"</div>"+
			            "</div>"+ 
			    	"</div>"+
				"</div>"+
			"</div>"		
		);
	});*/
	
	
	/*$("#compedidoc-"+pedido.idPedido).click(function(){
		$("#pcontenido-"+pedido.idPedido).html(
			"<div style='position: relative; overflow: hidden; width: auto; height: 350px;' class='slimScrollDiv'>"+
				"<div style='overflow: hidden; width: auto; height: 350px;' class='padd scroll-chat'>"+
					"<ul class='chats' id='comentarios-"+pedido.idPedido+"'>"+
					"</ul>"+
				"</div>"+
				"<div style='background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.3); width: 5px; position: absolute; top: 0px; border-radius: 7px; z-index: 99; right: 1px; height: 272.829px; display: none; opacity: 0.4;' class='slimScrollBar'></div>"+
				"<div style='width: 5px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: none repeat scroll 0% 0% rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;' class='slimScrollRail'></div>"+
			"</div>"
		);
		setComentarios(pedido);
	});*/	
	
	
	$("#spedidoc-"+pedido.idPedido).click(function(){
		if(pedido.status<1){
			$("#pcontenido-"+pedido.idPedido).html(
			"<div class='matter'>"+
				"<div class='container'>"+
					"<div class='row'>"+
			            "<div class='col-md-6'>"+
			            	"<div class='widget'>"+
			                	"<div class='widget-head'>"+
			                  		"<div class='pull-left'>Confirmar pedido</div>"+
			                  		"<div class='widget-icons pull-right'>"+
			                    		"<a href='#' class='wminimize'><i class='fa fa-chevron-up'></i></a>"+ 
			                    		"<a href='#' class='wclose'><i class='fa fa-times'></i></a>"+
			                  		"</div>"+  
			                  		"<div class='clearfix'></div>"+
			                	"</div>"+
			                	"<div class='widget-content'>"+
			                  		"<div class='pad'>"+
										"<form class='form' action='pedido' method='POST' onsubmit='return false;'>"+
											"<textarea class='form-control' id='comentarioe-"+pedido.idPedido+"' placeholder='Comentario entrega proveedor ...' rows='5'></textarea>"+
											"<div class='clearfix'></div>"+
											"<div class='buttons pull-right'>"+
												"<button class='btn btn-sm btn-info' id='btnapedido-"+pedido.idPedido+"'>Confirmar pedido</button>"+
											"</div>"+
										"</form>"+
									"</div>"+
			                	"</div>"+
			              	"</div>"+
			            "</div>"+
					"</div>"+
				"</div>"+
			"</div>"
			);
			$("#btnapedido-"+pedido.idPedido).click(function(){
				google.appengine.homelike.pedidos.update(pedido.idPedido,$("#comentarioe-"+pedido.idPedido).val(),1,new Date());
			});
		}else{
			$("#pcontenido-"+pedido.idPedido).html("<div class='alert alert-success'>Pedido confirmado!!</div>");
		}
	});
}

/*function setComentarios(pedido){
	$("#comentarios-"+pedido.idPedido).html(
		"<li class='by-me'>"+
			"<div class='avatar pull-left'>"+
				"<img src='img/logo_.png' alt=''>"+
			"</div>"+
			"<div class='chat-content'>"+
				"<div class='chat-meta'>"+
					"Comentario cliente : <span class='pull-right'>"+
					((pedido.fechaHoraPedido!=undefined)?new Date(pedido.fechaHoraPedido).toLocaleDateString():"")+" "+
					((pedido.fechaHoraPedido!=undefined)?new Date(pedido.fechaHoraPedido).toLocaleTimeString():"")+
					"</span>"+
				"</div>"+
				((pedido.comentarioCliente!=undefined)?pedido.comentarioCliente:"")+
				"<div class='clearfix'></div>"+
			"</div>"+
		"</li>"+
		"<li class='by-other'>"+
			"<div class='avatar pull-right'>"+
				"<img src='img/logo_.png' alt=''>"+
			"</div>"+
			"<div class='chat-content'>"+
				"<div class='chat-meta'>"+
					"Comentario entrega proveedor : <span class='pull-right'>"+
					((pedido.fechaHoraAceptacion!=undefined)?new Date(pedido.fechaHoraAceptacion).toLocaleDateString():"")+" "+
					((pedido.fechaHoraAceptacion!=undefined)?new Date(pedido.fechaHoraAceptacion).toLocaleTimeString():"")+
					"</span>"+
				"</div>"+
				((pedido.comentarioProveedor!=undefined)?pedido.comentarioProveedor:"")+
				"<div class='clearfix'></div>"+
			"</div>"+
		"</li>"+
		"<li class='by-me'>"+
			"<div class='avatar pull-left'>"+
				"<img src='img/logo_.png' alt=''>"+
			"</div>"+
			"<div class='chat-content'>"+
				"<div class='chat-meta'>"+
					"Comentario entrega cliente : <span class='pull-right'>"+
					((pedido.fechaHoraEntrega!=undefined)?new Date(pedido.fechaHoraEntrega).toLocaleDateString():"")+" "+
					((pedido.fechaHoraEntrega!=undefined)?new Date(pedido.fechaHoraEntrega).toLocaleTimeString():"")+
					"</span>"+
				"</div>"+
				((pedido.comentarioEntregaCliente!=undefined)?pedido.comentarioEntregaCliente:"")+
				"<div class='clearfix'></div>"+
			"</div>"+
		"</li>"+
		"<li class='by-other'>"+
			"<div class='avatar pull-right'>"+
				"<img src='img/logo_.png' alt=''>"+
			"</div>"+
			"<div class='chat-content'>"+
				"<div class='chat-meta'>"+
					"Comentario proveedor : <span class='pull-right'></span>"+
				"</div>"+
				((pedido.comentarioEntregaProveedor!=undefined)?pedido.comentarioEntregaProveedor:"")+
				"<div class='clearfix'></div>"+
			"</div>"+
		"</li>"
	);
}*/


function setgPedido(pedido,detallePedido){
	$("#pcontenido-"+pedido.idPedido).html(
			"<form class='form-horizontal' action='pedidos' method='POST' onsubmit='return false;'>"+
				"<table class='table table-striped table-bordered table-hover'>"+
					"<thead>"+
						"<tr>"+
							"<th># Producto</th>"+
							"<th>Descripcion</th>"+
							"<th>Presentacion</th>"+
							"<th>Costo Unitario</th>"+
							"<th>Cantidad</th>"+
						"</tr>"+
					"</thead>"+
					"<tbody id='tblProductos-"+pedido.idPedido+"'>"+
					"</tbody>"+
				"</table>"+
			"</form>"
		);
		var total=0;
		for(var i=0;i<detallePedido.length;i++){
			$("#tblProductos-"+pedido.idPedido).append(
				"<tr>"+
					"<td>"+detallePedido[i].producto.idProducto+"</td>"+
					"<td>"+detallePedido[i].producto.cproducto.descripcion+"</td>"+
					"<td>"+detallePedido[i].producto.cproducto.presentacion+"</td>"+
					"<td>"+detallePedido[i].producto.costoUnitario+"</td>"+
					"<td>"+detallePedido[i].cantidad+"</td>"+
				"</tr>"
			);
			total+=detallePedido[i].producto.costoUnitario*detallePedido[i].cantidad;
			$("#total-"+pedido.idPedido).text(total);
		}
}

function setStatus(pedido){
	var statusString;
	if(pedido.status==0)
		statusString='Nuevo';
	if(pedido.status==1)
		statusString='Confirmado proveedor';
	if(pedido.status==3)
		statusString='Confirmado cliente'
	$("#status-"+pedido.idPedido).html(
		"<span>Status : </span>"+statusString+
		"<a href='#' class='wminimize'><i class='fa fa-chevron-up'></i></a>"+
		"<a href='#' class='wclose'><i class='fa fa-times'></i></a>"
	);
}

function setMap(latitud,longitud,component){
	$("#"+component).css({"height":"200px","margin":"0px","padding":"0px"});
	$.getJSON( "https://maps.googleapis.com/maps/api/geocode/json?latlng="+latitud+","+longitud+"&sensor=false", function( json ) {
		if(json.results[0]!=undefined){
			lat=json.results[0].geometry.location.lat;
			lng=json.results[0].geometry.location.lng;
			locate(lat,lng,component);
		}else{
			$("#message").addClass("alert alert-warning"); 
			$("#message").text("Ubicacion no encontrada");
		}
	});
}

function locate(lat,lng,component){
	var myLatlng = new google.maps.LatLng(lat,lng);
	var mapOptions = {
		center: new google.maps.LatLng(lat,lng),
		zoom: 13
	};

	var map = new google.maps.Map(document.getElementById(component),mapOptions);

	var marker = new google.maps.Marker({
		position: myLatlng,
		map: map,
		title: 'Mi ubicacion!'
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
	    	}
	    }else{
	    	alert('No Login');
	    	window.location.replace('loginp.jsp');
	    }
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
	    	}
	    }else{
	    	alert('No Login');
	    	window.location.replace('index.jsp');
	    }
	});
}

