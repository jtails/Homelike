	/** google global namespace for Google projects. */
	var google = google || {};
	/** appengine namespace for Google Developer Relations projects. */
	google.appengine = google.appengine || {};
	/** homelike proveedores namespace for this code. */
	google.appengine.homelike = google.appengine.homelike || {};
	
	//google.appengine.homelike.WEB_CLIENT_ID='429890560769-vgaolojo01ke78sbhut2sgvf3qcol21m.apps.googleusercontent.com';
	google.appengine.homelike.WEB_CLIENT_ID='429890560769-e15fqu56oc53v2cs1vfss4a4bla3vb7s.apps.googleusercontent.com';
	google.appengine.homelike.EMAIL_SCOPE='https://www.googleapis.com/auth/userinfo.email';
	
	//Inside your callback function, load your Endpoint:
	function init() {
		//var ROOT = 'http://localhost:56419/_ah/api';
		var ROOT = 'https://admin-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('oauth2', 'v2', addEvents);
	}
	
	var opcion;
	function addEvents(){
		var login = document.querySelector('#login');
		login.addEventListener('click', function(e) {
			$("#login").text("Ingresando ...");
			signin(false, userAuthed);
		});
		
		$("#login").removeAttr("disabled");
		$("#login").text("Soy un Admin");
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
		    		$.ajax({type: 'post',
		    			url: 'admin',
		    			data: {usuario:output.email,opcion:'login',logotipo:output.picture},
		    			success: function(){
		    				window.location.replace('panel.jsp');
		    			}
		    		});
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}

