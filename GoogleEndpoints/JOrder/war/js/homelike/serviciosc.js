	/** google global namespace for Google projects. */
	var google = google || {};
	/** appengine namespace for Google Developer Relations projects. */
	google.appengine = google.appengine || {};
	/** homelike proveedores namespace for this code. */
	google.appengine.homelike = google.appengine.homelike || {};
	google.appengine.homelike.cuenta = google.appengine.homelike.cuenta || {};
	
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
        } , message: 'Espere un momento... cargando servicios' });
		
		apisToLoad = 2; // must match number of calls to gapi.client.load()
		//var ROOT = 'http://localhost:8888/_ah/api';
		var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('servicioendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('oauth2', 'v2', loadCallback);
	}
	
	
	google.appengine.homelike.cuenta.listservicio = function(){
		gapi.client.servicioendpoint.listServicio().execute(
		
		function(output){
			output.items = output.items || [];
			for(var i=0;i<output.items.length;i++){
				addServicio(output.items[i].idServicio,
							output.items[i].nombre);
			}
			$.unblockUI();
		});
	}
	
	function addServicio(idServicio,nombre){
		 $('#menuservicios').append("<li><a id='servicio"+idServicio+"' href='#'>"+nombre+"</a></li>");
		 
		 $("#servicio"+idServicio).click(function(){
			$("#idServicio").val(idServicio);
			$("#idProveedor").val(0);
			$("#contenido").load("fragments/proveedoresc.jsp");
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
		    		google.appengine.homelike.cuenta.listservicio();
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}
