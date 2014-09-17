	/** google global namespace for Google projects. */
	var google = google || {};
	/** appengine namespace for Google Developer Relations projects. */
	google.appengine = google.appengine || {};
	/** homelike proveedores namespace for this code. */
	google.appengine.homelike = google.appengine.homelike || {};
	google.appengine.homelike.tpedido = google.appengine.homelike.tpedido || {};
	
	//google.appengine.homelike.WEB_CLIENT_ID='429890560769-odthisg69be4tj9q9k4jb3ordfjpj4kp.apps.googleusercontent.com';
	google.appengine.homelike.WEB_CLIENT_ID='429890560769-1ahgmrm6v3o8o8diehah95j7locgshj3.apps.googleusercontent.com';
	google.appengine.homelike.EMAIL_SCOPE='https://www.googleapis.com/auth/userinfo.email';
	
	//Inside your callback function, load your Endpoint:
	function init() {
		//Debido a que los headers siempre estan presentes se les cambia el nombre a las funciones de OAuth
		var apisToLoad;
		var loadCallback_ = function() {
		    if (--apisToLoad == 0) {
		      signin_(true, userAuthed_);
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
        } , message: 'Espere un momento... cargando estadisticas de pedidos' });
		
		apisToLoad = 2; // must match number of calls to gapi.client.load()
		//Cambiar a HTTPS
		//var ROOT = 'https://homelike-dot-valid-keep-552.appspot.com/_ah/api';
		//var ROOT = 'http://localhost:8888/_ah/api';
		var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('tpedidoendpoint', 'v1',loadCallback_, ROOT);
		gapi.client.load('oauth2', 'v2', loadCallback_);
	}
	
	
	google.appengine.homelike.tpedido.listTpedido = function(idCuenta){
		gapi.client.tpedidoendpoint.listTpedidosByCuenta({'idCuenta': idCuenta}).execute(
		function(output){
			output.items = output.items || [];
			for(var i=0;i<output.items.length;i++){
				var tpedido=output.items[i];
				console.log(tpedido);
				addTpedidosh(tpedido);
			}
			$.unblockUI();
		});
	}
	
	function addTpedidosh(tpedido){
		$("#tpedidosh").append(
			"<li class='dropdown dropdown-big'><a class='dropdown-toggle' "+
					"href='#' data-toggle='dropdown'> <i class='fa fa-truck'></i>"+
					tpedido.key.servicio.nombre+" <span class='label label-primary'>"+tpedido.total+"</span>"+
				"</a>"+
			"</li>"
		);
	}
	
	//---------------------OAuth 2.0-----------------------------
	
	function signin_(mode, authorizeCallback) {
		gapi.auth.authorize({client_id: google.appengine.homelike.WEB_CLIENT_ID,
			scope: google.appengine.homelike.EMAIL_SCOPE, immediate: mode},
		    authorizeCallback);
	}

	
	function userAuthed_() {
		var request =
			gapi.client.oauth2.userinfo.get().execute(function(output) {
		    if(output!=undefined && output.verified_email!=undefined){
		    	if(output.verified_email){
		    		google.appengine.homelike.tpedido.listTpedido(
		    			document.querySelector('#idCuenta').value
		    		);
		    		$("#profile").attr('src',output.picture);
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}
