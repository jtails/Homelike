	/** google global namespace for Google projects. */
	var google = google || {};
	/** appengine namespace for Google Developer Relations projects. */
	google.appengine = google.appengine || {};
	/** homelike proveedores namespace for this code. */
	google.appengine.homelike = google.appengine.homelike || {};
	google.appengine.homelike.proveedores = google.appengine.homelike.proveedores || {};

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
		gapi.client.load('proveedorendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('oauth2', 'v2', loadCallback);
	}
	
	/**
	 * Prints a log.
	 * param {Object} to print.
	 */
	google.appengine.homelike.proveedores.setProveedor = function(output) {
		if(output.status!=-1){
		  $("#idProveedor").val(output.idProveedor);
		  $.ajax({type: 'post',
				url: 'proveedor',
				data: {usuario:output.usuario,opcion:'login'},
				success: function(){
					window.location.replace('panelp.jsp');
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
					google.appengine.homelike.proveedores.insert(
						document.querySelector('#idProveedor').value,	
						document.querySelector('#nombre').value,
						document.querySelector('#logo').value,
						document.querySelector('#slogan').value,
						document.querySelector('#rfc').value,
						document.querySelector('#razon').value,
						document.querySelector('#telefono').value,
						document.querySelector('#servicio').value,
						document.querySelector('#usuario').value,
						document.querySelector('#calle').value,
						document.querySelector('#numeroe').value,
						document.querySelector('#numeroi').value,
						document.querySelector('#colonia').value,
						document.querySelector('#delegacion').value,
						document.querySelector('#cp').value,
						document.querySelector('#estado').value,
						document.querySelector('#pais').value,
						document.querySelector('#calle1').value,
						document.querySelector('#calle2').value,
						document.querySelector('#referencia1').value,
						document.querySelector('#referencia2').value,
						document.querySelector('#latitud').value,
						document.querySelector('#longitud').value,
						document.querySelector('#nelatitud').value,
						document.querySelector('#nelongitud').value,
						document.querySelector('#swlatitud').value,
						document.querySelector('#swlongitud').value
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
	
	google.appengine.homelike.proveedores.insert = function(
			idProveedor,nombre,logo,slogan,rfc,razon,telefono,servicio,usuario,
			calle,numeroe,numeroi,colonia,delegacion,cp,estado,pais,
			calle1,calle2,referencia1,referencia2,latitud,longitud,nelatitud,nelongitud,swlatitud,swlongitud){
		gapi.client.proveedorendpoint.insertProveedor({'idProveedor': idProveedor,'nombre': nombre,'logo': logo,'slogan': slogan,'rfc': rfc,
														'razonSocial': razon,'telefono': telefono,'servicio': {'idServicio': servicio},
														'usuario': usuario,'calle': calle,'nexterior': numeroe,'ninterior': numeroi,
														'colonia': colonia,'delegacion': delegacion,
														'cp': cp,'estado': estado,'pais': pais,
														'calle1': calle1,'calle2': calle2,
														'referencia1': referencia1,'referencia2': referencia2,
														'latitud': latitud,'longitud': longitud,'nelatitud': nelatitud,'nelongitud': nelongitud,
														'swlatitud': swlatitud,'swlongitud': swlongitud}).execute(
		function(output) {
			    google.appengine.homelike.proveedores.setProveedor(output);
		});

	}

	
	
	
	//------------------------------------------------
	
	
	var rectangle;

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

		// [START region_rectangle]
			var nelat,nelng,swlat,swlng;
			
			if($("#nelatitud").val()!='')
				nelat=$("#nelatitud").val();
			else
				nelat=lat+.04;
			if($("#nelongitud").val()!='')
				nelng=$("#nelongitud").val();
			else
				nelng=lng+.04;
			if($("#swlatitud").val()!='')
				swlat=$("#swlatitud").val();
			else
				swlat=lat-.04;
			if($("#swlongitud").val()!='')
				swlng=$("#swlongitud").val();
			else
				swlng=lng-.04;

			setcoordinate(nelat,nelng,swlat,swlng);
			var bounds = new google.maps.LatLngBounds(
				new google.maps.LatLng(nelat,swlng),
				new google.maps.LatLng(swlat,nelng)
			);

			// Define a rectangle and set its editable property to true.
			rectangle = new google.maps.Rectangle({
				bounds: bounds,
				editable: true
			});

			rectangle.setMap(map);
		// [END region_rectangle]

		//Add an event listener on the rectangle.
			google.maps.event.addListener(rectangle, 'bounds_changed', showNewRect);
	}

	/** @this {google.maps.Rectangle} */
	function showNewRect(event) {
			var ne = rectangle.getBounds().getNorthEast();
			var sw = rectangle.getBounds().getSouthWest();
			setcoordinate(ne.lat(),ne.lng(),sw.lat(),sw.lng());
	}

	function setcoordinate(nelat,nelng,swlat,swlng){
		document.getElementById('nelatitud').value=nelat;
		document.getElementById('nelongitud').value=nelng;
		document.getElementById('swlatitud').value=swlat;
		document.getElementById('swlongitud').value=swlng;
	} 

	//google.maps.event.addDomListener(window, 'load', initialize);
	
	
	//--------------------------------------------------------------------
	
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
