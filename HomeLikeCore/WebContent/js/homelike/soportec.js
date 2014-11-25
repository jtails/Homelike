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
		
		apisToLoad = 3; // must match number of calls to gapi.client.load()
		//var ROOT = 'http://localhost:8888/_ah/api';
		var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('sugerenciascendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('tsugerenciaendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('oauth2', 'v2', loadCallback);
	}
	
	function addEvents(){
		//alert('Endpoint API Cargada');
		var btnenviar = document.querySelector('#btnenviar');
		btnenviar.addEventListener('click', function(e) {
			google.appengine.homelike.soporte.insert(
	    		document.querySelector('#idCuenta').value,
	    		document.querySelector('#sugerencia').value,
	    		document.querySelector('#tsugerencia').value
	    	);
		});
	}
	
	
	google.appengine.homelike.soporte.insert = function(idCuenta,sugerencia,idTsugerencia){
		if(sugerencia!=null && sugerencia!=''){
			gapi.client.sugerenciascendpoint.insertSugerenciasc({'cuenta':{'idCuenta': idCuenta},'tsugerencia':{'idTsugerencia':idTsugerencia},'sugerencia': sugerencia}).execute(
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
	
	google.appengine.homelike.soporte.list = function(){
		$.blockUI({ css: { 
	        border: 'none', 
	        padding: '15px', 
	        backgroundColor: '#000', 
	        '-webkit-border-radius': '10px', 
	        '-moz-border-radius': '10px', 
	        opacity: .5, 
	        color: '#fff' 
	    } , message: 'Espere un momento... cargando tipos de sugerencias' });
		
		gapi.client.tsugerenciaendpoint.listTsugerencia().execute(
			function(output){
				//$("#tsugerencia").empty();
				for(var i=0;i<output.items.length;i++){
					var tsugerencia=output.items[i];
					$('#tsugerencia').append("<option value='"+tsugerencia.idTsugerencia+"'>"+tsugerencia.tipoSugerencia+"</option>");
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
		    		google.appengine.homelike.soporte.list();
		    		addEvents();
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}
