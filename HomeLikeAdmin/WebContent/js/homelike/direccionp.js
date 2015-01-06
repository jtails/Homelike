	/** google global namespace for Google projects. */
	var google = google || {};
	/** appengine namespace for Google Developer Relations projects. */
	google.appengine = google.appengine || {};
	/** homelike proveedores namespace for this code. */
	google.appengine.homelike = google.appengine.homelike || {};
	google.appengine.homelike.proveedor = google.appengine.homelike.proveedor || {};
	
	//google.appengine.homelike.WEB_CLIENT_ID='429890560769-vgaolojo01ke78sbhut2sgvf3qcol21m.apps.googleusercontent.com';
	google.appengine.homelike.WEB_CLIENT_ID='429890560769-e15fqu56oc53v2cs1vfss4a4bla3vb7s.apps.googleusercontent.com';
	google.appengine.homelike.EMAIL_SCOPE='https://www.googleapis.com/auth/userinfo.email';
	
	//Inside your callback function, load your Endpoint:
	function init() {	
		var apisToLoad;
		var loadCallback = function() {
		    if (--apisToLoad == 0) {
		      signin(true, userAuthed);
		    }
		};
		
		apisToLoad = 1; // must match number of calls to gapi.client.load()
		//var ROOT = 'http://localhost:57424/_ah/api';
		var ROOT = 'https://admin-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('oauth2', 'v2', loadCallback);
	}
	
	
	//--------------------------------------------------
	var map;
	
	function locate(lat,lng){
		var myLatlng = new google.maps.LatLng(lat,lng);
		var mapOptions = {
			center: new google.maps.LatLng(lat,lng),
			zoom: 12
		};

		 map = new google.maps.Map(document.getElementById('map-canvas'),mapOptions);
	
		var marker = new google.maps.Marker({
			position: myLatlng,
			map: map,
			title: currentProveedor.nombre
		});
	}
	
	//Se agrega funcionalidad de resize, ya que este mapa es mostrado en un ModalBox oculto y necesita refresh al ser mostrado
	function updateMap(){
		google.maps.event.trigger(map, "resize");
	}
	
	//google.maps.event.addDomListener(window, 'load', initialize);
	
	//----------------------------------------------
	
	function setMap(){
		var latitud=currentProveedor.latitud;
  		var longitud=currentProveedor.longitud;
  		$.getJSON( "https://maps.googleapis.com/maps/api/geocode/json?latlng="+latitud+","+longitud+"&sensor=false", function( json ) {
  			if(json.results[0]!=undefined){
  				lat=json.results[0].geometry.location.lat;
  				lng=json.results[0].geometry.location.lng;
  				locate(lat,lng);
  			}else{
  				$("#message").addClass("alert alert-warning"); 
  				$("#message").text("Ubicacion no encontrada");
  			}
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
		    		setMap();
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}
