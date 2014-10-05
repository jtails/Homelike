	/** google global namespace for Google projects. */
	var google = google || {};
	/** appengine namespace for Google Developer Relations projects. */
	google.appengine = google.appengine || {};
	/** homelike namespace for this code. */
	google.appengine.homelike = google.appengine.homelike || {};
	google.appengine.homelike.cuenta = google.appengine.homelike.cuenta || {};

	//google.appengine.homelike.WEB_CLIENT_ID='429890560769-odthisg69be4tj9q9k4jb3ordfjpj4kp.apps.googleusercontent.com';
	google.appengine.homelike.WEB_CLIENT_ID='429890560769-1ahgmrm6v3o8o8diehah95j7locgshj3.apps.googleusercontent.com';
	google.appengine.homelike.EMAIL_SCOPE='https://www.googleapis.com/auth/userinfo.email';
	
	var localizacion=false;
	
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
		gapi.client.load('cuentaendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('oauth2', 'v2', loadCallback);
	}
	
	/**
	 * Prints a log.
	 * param {Object} to print.
	 */
	google.appengine.homelike.cuenta.setCuenta = function(output) {
		if(output.status!=-1){
			$("#idCuenta").val(output.idCuenta);
			$.ajax({type: 'post',
				url: 'cliente',
				data: {usuario:output.usuario,opcion:'login'},
				success: function(){
					window.location.replace('panelc.jsp');
				}
			});
		}else{
			$("#message").addClass("alert alert-warning"); 
			$("#message").text("Correo Electronico Existente");
		}
	};


	function addEvents(){
		//alert('Endpoint API Cargada');
		var btnenviar = document.querySelector('#btnenviar');
		btnenviar.addEventListener('click', function(e) {
			if(localizacion){
				if($("#contrato").is(':checked')){
					google.appengine.homelike.cuenta.insert(
						document.querySelector('#idCuenta').value,
						document.querySelector('#telefono').value,
						document.querySelector('#usuario').value,
						document.querySelector('#idDireccion').value,
						document.querySelector('#latitud').value,
						document.querySelector('#longitud').value,
						document.querySelector('#calle1').value,
						document.querySelector('#calle2').value,
						document.querySelector('#referencia1').value,
						document.querySelector('#referencia2').value,
						document.querySelector('#calle').value,
						document.querySelector('#numeroe').value,
						document.querySelector('#numeroi').value,
						document.querySelector('#colonia').value,
						document.querySelector('#delegacion').value,
						document.querySelector('#cp').value,
						document.querySelector('#estado').value,
						document.querySelector('#pais').value
					);
				}else{
					$("#message").addClass("alert alert-warning"); 
	  				$("#message").text("Debe aceptar los terminos y condiciones");
				}
			}else{
				$("#message").addClass("alert alert-warning"); 
  				$("#message").text("Ubicacion no encontrada, Por favor ingrese su direccion para poder continuar");
			}
		});
		$("#btnenviar").removeAttr("disabled");
	}
	
	google.appengine.homelike.cuenta.insert = function(
			idCuenta,telefono,usuario,idDireccion,latitud,longitud,calle1,calle2,referencia1,referencia2,
			calle,numeroe,numeroi,colonia,delegacion,cp,estado,pais){
		gapi.client.cuentaendpoint.insertCuenta({'idCuenta': idCuenta,'telefono': telefono,'usuario': usuario,
														'direcciones': [{'idDireccion':idDireccion,'calle': calle,'nexterior': numeroe,'ninterior': numeroi,
														'colonia': colonia,'delegacion': delegacion,
														'cp': cp,'estado': estado,'pais': pais,
														'calle1': calle1,'calle2': calle2,
														'referencia1': referencia1,'referencia2': referencia2,
														'latitud': latitud,'longitud': longitud}]}).execute(
		function(output) {
			    google.appengine.homelike.cuenta.setCuenta(output);
		});

	}

	
	//--------------------------------------------------
	
	function initialize() {
			var mapOptions = {
			center: new google.maps.LatLng(19.3054,-99.1797),
			zoom: 10
			};

			var map = new google.maps.Map(document.getElementById('map-canvas'),
      		mapOptions);
	}

	function locate(lat,lng){
		var myLatlng = new google.maps.LatLng(lat,lng);
		var mapOptions = {
			center: new google.maps.LatLng(lat,lng),
			zoom: 13
			};

			var map = new google.maps.Map(document.getElementById('map-canvas'),
  			mapOptions);
	
			var marker = new google.maps.Marker({
    		position: myLatlng,
    		map: map,
    		title: 'Mi ubicacion!'
		});
	}
	//google.maps.event.addDomListener(window, 'load', initialize);
	
	//----------------------------------------------
	
	function setMap(){
  		var calle=$('#calle').val();
  		var numeroe=$('#numeroe').val();
  		var colonia=$('#colonia').val();
  		var delegacion=$('#delegacion').val();
  		var cp=$('#cp').val();
  		var estado=$('#estado').val();
  		var pais=$('#pais').val();
  		$.getJSON( "https://maps.googleapis.com/maps/api/geocode/json?address="+calle+"+"+numeroe+"+"+colonia+"+"+delegacion+"+"+cp+"+"+estado+"+"+pais+"&sensor=false", function( json ) {
  			if(json.results[0]!=undefined){
  				lat=json.results[0].geometry.location.lat;
  				lng=json.results[0].geometry.location.lng;
  				$('#latitud').val(lat);
  				$('#lat').text(lat);
  				$('#longitud').val(lng);
  				$('#lng').text(lng);
  				locate(lat,lng);
  				localizacion=true;
  			}else{
  				$("#message").addClass("alert alert-warning"); 
  				$("#message").text("Ubicacion no encontrada");
  				localizacion=false;
  			}
  		});
  		
  		$("#back").click(function(){
  			parent.history.back();
  			return false;
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
		    		setMap();
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}
