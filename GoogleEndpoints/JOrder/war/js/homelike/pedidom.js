	/** google global namespace for Google projects. */
	var google = google || {};
	/** appengine namespace for Google Developer Relations projects. */
	google.appengine = google.appengine || {};
	/** homelike proveedores namespace for this code. */
	google.appengine.homelike = google.appengine.homelike || {};
	google.appengine.homelike.cantidadpago = google.appengine.homelike.cantidadpago || {};
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
		
		apisToLoad = 3; // must match number of calls to gapi.client.load()
		//var ROOT = 'http://localhost:8888/_ah/api';
		var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('cantidadpagoendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('pedidoendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('oauth2', 'v2', loadCallback);
	}
	
	function makePedido(){
		//alert('Endpoint API Cargada');
		var btnenviar = document.querySelector('#btnenviar');
		btnenviar.addEventListener('click', function(e) {
			var dpedidoJson=new Array();
			for(var i=0;i<dpedido.length;i++){
				 var dpedidoBean=dpedido[i];
				 if(dpedidoBean.cantidad>0){
					 dpedidoJson.push({'cantidad': dpedidoBean.cantidad,
					 'producto': {'idProducto': dpedidoBean.idProducto}});
				 }
			 }
			if(dpedidoJson.length>0){
			google.appengine.homelike.pedidos.insert(
					document.querySelector('#idProveedor').value,
					document.querySelector('#idDireccion').value,
					document.querySelector('#idCuenta').value,
					dpedidoJson,
					document.querySelector('#comentarioCliente').value,
					1
					//document.querySelector('#cantidadPago').value
			);
			}else{
				$("#message").addClass("alert alert-warning"); 
				$("#message").text("Debe seleccionar productos para su pedido");
				$("#message").show();
			}
			$('#modalBox').modal('hide');
		});
	}
	
	
	/*google.appengine.homelike.cantidadpago.list = function(){
		gapi.client.cantidadpagoendpoint.listCantidadPago().execute(
		
		function(output){
			output.items = output.items || [];
			for(var i=0;i<output.items.length;i++){
				addCantidad(output.items[i].idCantidadPago,
							output.items[i].cantidadTexto);
			}
		});
	}
	
	function addCantidad(idCantidadPago,cantidadTexto){
		 $('#cantidadPago').append("<option value='"+idCantidadPago+"'>"+cantidadTexto +"</option>");
	}*/
	
	
	google.appengine.homelike.pedidos.insert = function(
			idProveedor,idDireccion,idCuenta,dpedidoJson,comentarioCliente,cantidadPago){
		gapi.client.pedidoendpoint.insertPedido({'proveedor': {'idProveedor': idProveedor},'direccion': {'idDireccion': idDireccion},'cuenta': {'idCuenta': idCuenta},'detallePedido': dpedidoJson,'comentarioCliente': comentarioCliente,'cantidadPago': {'idCantidadPago': cantidadPago}}).execute(
		function(output) {
			//if(output.status!=-1){
			//	  $("#idProveedor").val(output.idProveedor);
				  $("#contenido").load("fragments/pedidosc.jsp");
			//  }else{
			//	  $("#message").addClass("alert alert-warning"); 
			//	  $("#message").text("Nombre de Usuario Existente");
			//  }
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
		    		//google.appengine.homelike.cantidadpago.list();
		    		makePedido();
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}

	
	