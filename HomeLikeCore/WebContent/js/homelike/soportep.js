	/** google global namespace for Google projects. */
	var google = google || {};
	/** appengine namespace for Google Developer Relations projects. */
	google.appengine = google.appengine || {};
	/** homelike proveedores namespace for this code. */
	google.appengine.homelike = google.appengine.homelike || {};
	google.appengine.homelike.soporte = google.appengine.homelike.soporte || {};
	
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
		//var ROOT = 'http://localhost:8888/_ah/api';
		var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('sugerenciaspendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('oauth2', 'v2', loadCallback);
	}
	
	function addEvents(){
		//alert('Endpoint API Cargada');
		var btnenviar = document.querySelector('#btnenviar');
		btnenviar.addEventListener('click', function(e) {
			google.appengine.homelike.soporte.insert(
	    		document.querySelector('#idProveedor').value,
	    		document.querySelector('#sugerencia').value
	    	);
		});
	}
	
	
	google.appengine.homelike.soporte.insert = function(idProveedor,sugerencia){
		if(sugerencia!=null && sugerencia!=''){
			gapi.client.sugerenciaspendpoint.insertSugerenciasp({'proveedor':{'idProveedor': idProveedor},'sugerencia': sugerencia}).execute(
			function(output){
				if(output!=undefined && output.idSugerencia!=0){
					$("#sugerencia").val('');
					$("#message").addClass("alert alert-warning"); 
					$("#message").text("Su comentario ha sido enviado, le agradecemos la atencion brindada");
				}
			});
		}else{
			$("#message").addClass("alert alert-warning"); 
			$("#message").text("Debe ingresar un comentario");
		}
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
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}
