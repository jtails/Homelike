	/** google global namespace for Google projects. */
	var google = google || {};
	/** appengine namespace for Google Developer Relations projects. */
	google.appengine = google.appengine || {};
	/** homelike proveedores namespace for this code. */
	google.appengine.homelike = google.appengine.homelike || {};
	google.appengine.homelike.estadisticas = google.appengine.homelike.estadisticas || {};
	
	//google.appengine.homelike.WEB_CLIENT_ID='429890560769-odthisg69be4tj9q9k4jb3ordfjpj4kp.apps.googleusercontent.com';
	google.appengine.homelike.WEB_CLIENT_ID='429890560769-1ahgmrm6v3o8o8diehah95j7locgshj3.apps.googleusercontent.com';
	google.appengine.homelike.EMAIL_SCOPE='https://www.googleapis.com/auth/userinfo.email';
	
	//Inside your callback function, load your Endpoint:
	function init() {
		//var ROOT = 'http://localhost:8888/_ah/api';
		var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('estadisticasendpoint', 'v1', google.appengine.homelike.estadisticas.list,ROOT);
	}
	
	google.appengine.homelike.estadisticas.list = function(){
		gapi.client.estadisticasendpoint.getEstadisticas().execute(
		function(output) {
			output.items = output.items || [];
			$("#numCuentas").html("HomeLike ahora tiene : "+output.items[0].value+" Clientes");
			$("#numProveedores").html("HomeLike ahora tiene : "+output.items[1].value+" Proveedores");
		});
	}
	

