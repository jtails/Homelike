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
        } , message: 'Espere un momento... cargando productos' });
		
		apisToLoad = 2; // must match number of calls to gapi.client.load()
		//var ROOT = 'http://localhost:8888/_ah/api';
		var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('productoendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('oauth2', 'v2', loadCallback);
	}
	
	function addmodalBox(){
		$("#modalBox").load("fragments/pedidom.jsp",function() {
			google.appengine.homelike.ofertaproveedor.list(
					document.querySelector('#idProveedor').value
			);
		});
	}
	
	//variable para llevar el control del pedido entre paginas
	var dpedido;
	
	function addProducto(index,idProducto,descripcion,presentacion,costoUnitario){
		 $('#tblProductos').append("<tr>"+
				    "<td>"+idProducto+"</td>"+
					"<td>"+descripcion+"</td>"+
					"<td>"+presentacion+"</td>"+
					"<td><input type='hidden' value='"+costoUnitario+"'/>"+costoUnitario+"</td>"+
					"<td><select class='form-control' id='c"+idProducto+"'>" +
							"<option value='0'>0</opcion>" +
							"<option value='1' selected>1</opcion>" +
							"<option value='2'>2</opcion>" +
							"<option value='3'>3</opcion>" +
							"<option value='4'>4</opcion>" +
							"<option value='5'>5</opcion>" +
					"</select></td>"+
			   "</tr>");
		 dpedido[index]={'idProducto': idProducto,'costo': costoUnitario,'cantidad': 1};

		 setTotal();
		 
		 $("#c"+idProducto).change(function(){
			 var dpedidoBean=dpedido[index];
			 dpedidoBean.cantidad=$("#c"+idProducto).val();
			 setTotal();
		 });
	}
	
	function setTotal(){
		var total=0;
		for(var i=0;i<dpedido.length;i++){
			 var dpedidoBean=dpedido[i];
			 if(dpedidoBean!=null)
			 	total=total+dpedidoBean.cantidad*dpedidoBean.costo;
		 }
		 $("#total").text(total);
		 $("#ctotal").text('Total : $ '+total);
	}
		
	google.appengine.homelike.ofertaproveedor.list = function(idProveedor){
		gapi.client.productoendpoint.listProductosByProveedor({'idProveedor': idProveedor}).execute(
		
		function(output){
			output.items = output.items || [];
			dpedido=new Array(output.items.length);
			for(var i=0;i<output.items.length;i++){
				if(output.items[i].cproducto!=undefined){
					addProducto(i,output.items[i].idProducto,
						output.items[i].cproducto.descripcion,
						output.items[i].cproducto.presentacion,
						output.items[i].costoUnitario
					);
				}else{
					addProducto(i,output.items[i].idProducto,
						output.items[i].descripcion,
						output.items[i].presentacion,
						output.items[i].costoUnitario
					);
				}
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
		    		addmodalBox();
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}
