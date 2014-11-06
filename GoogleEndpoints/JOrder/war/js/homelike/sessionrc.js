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
		var apisToLoad;
		var loadCallback_ = function() {
		    if (--apisToLoad == 0) {
		      signin_(true, userAuthed_);
		    }
		};
		
		apisToLoad = 1; // must match number of calls to gapi.client.load()
		//var ROOT = 'http://localhost:8888/_ah/api';
		var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('oauth2', 'v2', loadCallback_);
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
		    		$("#profile").attr('src',output.picture);
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}
