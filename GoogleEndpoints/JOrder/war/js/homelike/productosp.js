	/** google global namespace for Google projects. */
	var google = google || {};
	/** appengine namespace for Google Developer Relations projects. */
	google.appengine = google.appengine || {};
	/** homelike oferta proveedor namespace for this code. */
	google.appengine.homelike = google.appengine.homelike || {};
	google.appengine.homelike.ofertaproveedor = google.appengine.homelike.ofertaproveedor || {};

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
	    } , message: 'Espere un momento... cargando catalogo de productos' });
		
		apisToLoad = 3; // must match number of calls to gapi.client.load()
		//Cambiar a HTTPS
		//var ROOT = 'https://homelike-dot-valid-keep-552.appspot.com/_ah/api';
		//var ROOT = 'http://localhost:8888/_ah/api';
		var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('productoendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('cproductoendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('oauth2', 'v2', loadCallback);
	}
	
	function addEvents(){
		//alert('Endpoint API Cargada');
		var btnenviar = document.querySelector('#btnenviar');
		btnenviar.addEventListener('click', function(e) {
			google.appengine.homelike.ofertaproveedor.insert(
				document.querySelector('#idProveedor').value,
				document.querySelector('#precio').value,
				document.querySelector('#cproducto').value
		    );
		});
	}
	
	function addProducto(idProducto,descripcion,presentacion,costoUnitario){
		 $('#tblProductos').append("<tr id='tr"+idProducto+"'>"+
					"<td>"+descripcion+"-"+presentacion+"</td>"+
					"<td>"+costoUnitario+"</td>"+
					"<td><input type='button' class='btn btn-sm btn-info' id='btneliminar"+idProducto+"' name='btneliminar' value='Eliminar'/></td>"+
			   "</tr>");
		$("#btneliminar"+idProducto).click(function(){
			google.appengine.homelike.ofertaproveedor.del(
				idProducto
			);
		});
	}
	
	function addCatalogo(idCProducto,descripcion,presentacion){
		 $('#cproducto').append("<option value='"+idCProducto+"'>"+
				 					descripcion+" - "+ presentacion +
				 				"</option>");
	}
	
	google.appengine.homelike.ofertaproveedor.insert = function(proveedor,precio,cproducto){
		gapi.client.productoendpoint.insertProducto({'cproducto': {'idCProducto': cproducto},
													'proveedor': {'idProveedor': proveedor},
													'costoUnitario': precio}).execute(
		function(output) {
			 if(output.idProducto!=0){
				 //var output='Registro de Producto Exitoso :' + resp.idProducto;
				 $("#message").addClass("alert alert-warning"); 
				 $("#message").text("Producto Actualizado");
				 google.appengine.homelike.ofertaproveedor.list(
							document.querySelector('#idProveedor').value
				 );
			 }
		});

	}
	
	google.appengine.homelike.ofertaproveedor.del = function(producto){
		gapi.client.productoendpoint.removeProducto({'id': producto}).execute(
		
		function(output){
			$("#tr"+producto).remove();
		});
	}
	
	google.appengine.homelike.ofertaproveedor.list = function(idProveedor){
		gapi.client.productoendpoint.listProductosByProveedor({'idProveedor': idProveedor}).execute(
		
		function(output){
			$('#tblProductos').empty();
			output.items = output.items || [];
			for(var i=0;i<output.items.length;i++){
				addProducto(output.items[i].idProducto,
							output.items[i].cproducto.descripcion,
							output.items[i].cproducto.presentacion,
							output.items[i].costoUnitario);
			}
		});
	}
	
	google.appengine.homelike.ofertaproveedor.listcatalogo = function(idServicio){
		gapi.client.cproductoendpoint.listCProductoByServicio({'idServicio': idServicio}).execute(
		
		function(output){
			output.items = output.items || [];
			for(var i=0;i<output.items.length;i++){
				addCatalogo(output.items[i].idCProducto,
							output.items[i].descripcion,
							output.items[i].presentacion);
			}
			$.unblockUI();
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
		    		addEvents();
					google.appengine.homelike.ofertaproveedor.list(
						document.querySelector('#idProveedor').value
					);
					google.appengine.homelike.ofertaproveedor.listcatalogo(
						document.querySelector('#idServicio').value
					);
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}

