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
			//Producto sin catalogo
			if($("#cproducto").val()==0){
				google.appengine.homelike.ofertaproveedor.insertWithoutCatalogo(
					document.querySelector('#idProveedor').value,
					document.querySelector('#descripcion').value,
					document.querySelector('#presentacion').value,
					document.querySelector('#precio').value,0
				);				
			}else{
				//Producto con catalogo
				google.appengine.homelike.ofertaproveedor.insertWithCatalogo(
					document.querySelector('#idProveedor').value,
					document.querySelector('#precio').value,
					document.querySelector('#cproducto').value,0
				);
			}
		});
		
		$("#cproducto").change(function(){
			if($("#cproducto").val()==0){
				$("#ddescripcion").show();
				$("#dpresentacion").show();
			}
			else{
				$("#ddescripcion").hide();
				$("#dpresentacion").hide();
			}
		});
	}
	
	function addProducto(idProducto,descripcion,presentacion,costoUnitario,status,idCProducto){
		var deshabilitar="<button class='btn btn-danger' id='btndeshabilitar"+idProducto+"' name='btndeshabilitar'>Deshabilitar</button>"; 
		var habilitar="<button class='btn btn-info' id='btnhabilitar"+idProducto+"' name='btnhabilitar'>Habilitar</button>";
		var editar="<button class='btn btn-default' id='btneditar"+idProducto+"' name='btneditar'><i class='fa fa-pencil'></i></button>";
		var save="<button class='btn btn-default' style='display:none' id='btnguardar"+idProducto+"' name='btnguardar'><i class='fa fa-save'></i></button>";
		var button;
		//Producto deshabilitado
		if(status==-3)
			button=habilitar;
		else{
			button=editar;
			button+=save;
			button+=deshabilitar;
		}
		$('#tblProductos').append("<tr id='tr"+idProducto+"'>"+
					"<td><input type='text' disabled value='"+descripcion+"' id='edescripcion"+idProducto+"'/></td>"+
					"<td><input type='text' disabled value='"+presentacion+"' id='epresentacion"+idProducto+"'/></td>"+
					"<td><input type='text' disabled value='"+costoUnitario+"' id='ecosto"+idProducto+"'/></td>"+
					"<td>"+
						button+
					"</td>"+
			   "</tr>");
		$("#btndeshabilitar"+idProducto).click(function(){
			google.appengine.homelike.ofertaproveedor.deshabilitar(
				idProducto
			);
		});
		
		$("#btnhabilitar"+idProducto).click(function(){
			google.appengine.homelike.ofertaproveedor.habilitar(
				idProducto
			);
		});
		$("#btneditar"+idProducto).click(function(){
			//El producto no tiene Catalogo y puede ser editada su descripcion y su presentacion 
			if(idCProducto==0){
				$("#edescripcion"+idProducto).removeAttr('disabled');
				$("#epresentacion"+idProducto).removeAttr('disabled');
			}
			$("#ecosto"+idProducto).removeAttr('disabled');
			$("#btnguardar"+idProducto).show();
			$("#btneditar"+idProducto).hide();
		});
		
		$("#btnguardar"+idProducto).click(function(){
			$("#edescripcion"+idProducto).attr('disabled','disabled');
			$("#epresentacion"+idProducto).attr('disabled','disabled');
			$("#ecosto"+idProducto).attr('disabled','disabled');
			$("#btnguardar"+idProducto).hide();
			$("#btneditar"+idProducto).show();
			
			//El producto tiene catalogo
			if(idCProducto!=0){
				google.appengine.homelike.ofertaproveedor.insertWithCatalogo(
					document.querySelector('#idProveedor').value,
					$("#ecosto"+idProducto).val(),
					idCProducto,
					idProducto
				);	
			}else{//El producto no tiene catalogo
				google.appengine.homelike.ofertaproveedor.insertWithoutCatalogo(
					document.querySelector('#idProveedor').value,
					$("#edescripcion"+idProducto).val(),
					$("#epresentacion"+idProducto).val(),
					$("#ecosto"+idProducto).val(),
					idProducto
				);	
			}
			
		});
	}
	
	function addCatalogo(idCProducto,descripcion,presentacion){
		 $('#cproducto').append("<option value='"+idCProducto+"'>"+
				 					descripcion+" - "+ presentacion +
				 				"</option>");
	}
	
	google.appengine.homelike.ofertaproveedor.insertWithCatalogo = function(proveedor,precio,cproducto,idProducto){
		gapi.client.productoendpoint.insertProducto({'idProducto':idProducto,'cproducto': {'idCProducto': cproducto},
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
	
	google.appengine.homelike.ofertaproveedor.insertWithoutCatalogo = function(proveedor,descripcion,presentacion,precio,idProducto){
		gapi.client.productoendpoint.insertProducto({'idProducto':idProducto,'descripcion':descripcion,'presentacion':presentacion,
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
	
	google.appengine.homelike.ofertaproveedor.deshabilitar = function(producto){
		gapi.client.productoendpoint.deshabilitarProducto({'id': producto}).execute(
		function(output){
			google.appengine.homelike.ofertaproveedor.list(
					document.querySelector('#idProveedor').value
			);
		});
	}
	
	google.appengine.homelike.ofertaproveedor.habilitar = function(producto){
		gapi.client.productoendpoint.habilitarProducto({'id': producto}).execute(
		function(output){
			google.appengine.homelike.ofertaproveedor.list(
					document.querySelector('#idProveedor').value
			);
		});
	}
	
	google.appengine.homelike.ofertaproveedor.list = function(idProveedor){
		gapi.client.productoendpoint.listAllProductosByProveedor({'idProveedor': idProveedor}).execute(
		
		function(output){
			$('#tblProductos').empty();
			output.items = output.items || [];
			for(var i=0;i<output.items.length;i++){
				if(output.items[i].cproducto!=undefined){
					addProducto(output.items[i].idProducto,
						output.items[i].cproducto.descripcion,
						output.items[i].cproducto.presentacion,
						output.items[i].costoUnitario,
						output.items[i].status,
						output.items[i].cproducto.idCProducto
					);
				}else{
					addProducto(output.items[i].idProducto,
						output.items[i].descripcion,
						output.items[i].presentacion,
						output.items[i].costoUnitario,
						output.items[i].status,
						0
					);
				}
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
			addCatalogo(0,'Otro','Especifique');
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

