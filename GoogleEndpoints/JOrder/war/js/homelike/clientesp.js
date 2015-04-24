	/** google global namespace for Google projects. */
	var google = google || {};
	/** appengine namespace for Google Developer Relations projects. */
	google.appengine = google.appengine || {};
	/** homelike proveedores namespace for this code. */
	google.appengine.homelike = google.appengine.homelike || {};
	google.appengine.homelike.clientes = google.appengine.homelike.clientes || {};
	google.appengine.homelike.regiones = google.appengine.homelike.regiones || {};
	
	//google.appengine.homelike.WEB_CLIENT_ID='429890560769-odthisg69be4tj9q9k4jb3ordfjpj4kp.apps.googleusercontent.com';
	google.appengine.homelike.WEB_CLIENT_ID='429890560769-1ahgmrm6v3o8o8diehah95j7locgshj3.apps.googleusercontent.com';
	google.appengine.homelike.EMAIL_SCOPE='https://www.googleapis.com/auth/userinfo.email';
	
	
	//Contiene la lista de regiones agregadas al mapa y sus correspondientes coordenadas
	//No se agrega la region principal a la lista
	var coordinates=[];
	var regions=[];
	
	//Coordenadas del proveedor y su area de cobertura
	var lat;
	var lng;
	var nelat;
	var nelng;
	var swlat;
	var swlng;
	
	//Referencia al mapa
	var map;
	
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
	    } , message: 'Espere un momento... cargando clientes' });
		
		apisToLoad = 4; // must match number of calls to gapi.client.load()
		//var ROOT = 'http://localhost:8888/_ah/api';
		var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('cuentaendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('regionesendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('proveedorendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('oauth2', 'v2', loadCallback);
	}
	
	
	google.appengine.homelike.clientes.clientesinRage = function(idProveedor,nelatitud,nelongitud,swlatitud,swlongitud){
		gapi.client.cuentaendpoint.listClientesinRange({'idProveedor': idProveedor,'nelatitud': nelatitud,'nelongitud': nelongitud,
											'swlatitud': swlatitud,'swlongitud': swlongitud}).execute(
		function(output){
			output.items = output.items || [];
			if(output.items.length>0){
				for(var i=0;i<output.items.length;i++){
					var cuenta=output.items[i];
					var direccion=cuenta.direcciones[0];
					setMarker(direccion.latitud,direccion.longitud,direccion.alias,cuenta.numPedidos);
				}
				$.unblockUI();
			}else{
				 $("#message").addClass("alert alert-warning"); 
				 $("#message").text("No se encuentran clientes cercanos a su ubicacion");
				 $("#message").show();
				 $.unblockUI();
			}
		});
	}
	
	google.appengine.homelike.regiones.insert = function(idProveedor,nelatitud,nelongitud,swlatitud,swlongitud){
		gapi.client.regionesendpoint.insertRegion({'proveedor':{'idProveedor': idProveedor},'nelatitud': nelatitud,'nelongitud': nelongitud,
											'swlatitud': swlatitud,'swlongitud': swlongitud}).execute(
		function(output){
			//console.log(output);
		});
	}
	
	google.appengine.homelike.regiones.delete = function(idProveedor){
		gapi.client.regionesendpoint.deleteRegiones({'idProveedor': idProveedor}).execute(
		function(output){
			//console.log(output);
		});
	}
	
	
	
	//--------------------------------------------------
	
	function locate(lat,lng){
		var myLatlng = new google.maps.LatLng(lat,lng);
		var mapOptions = {
			center: new google.maps.LatLng(lat,lng),
			zoom: 10
			};

			map = new google.maps.Map(document.getElementById('map-canvas'),
  			mapOptions);
	
			var marker = new google.maps.Marker({
				position: myLatlng,
				map: map,
				title: 'Mi ubicacion!'
			});
	}
	
	function setMarker(lat,lng,alias,numPedidos){
		var icono;
		if(numPedidos>0)
			icono='http://google.com/mapfiles/ms/micons/man.png';
		else
			icono='http://maps.google.com/mapfiles/kml/pal3/icon45.png';
		var myLatlng = new google.maps.LatLng(lat,lng);
		alias=numPedidos+' Pedido[s]';
		var marker = new google.maps.Marker({
    		position: myLatlng,
    		map: map,
    		title: alias,
    		icon: icono
		});
	}
	
	function addRegion(nelat,nelng,swlat,swlng){
		// [START region_rectangle]
		var bounds = new google.maps.LatLngBounds(
			new google.maps.LatLng(swlat,swlng),//swlat,swlng
			new google.maps.LatLng(nelat,nelng)//nelat,nelng
		);
		// Define a rectangle.
		var rectangle = new google.maps.Rectangle({
			bounds: bounds,
			//editable: true,
			//draggable: true,
			fillColor: '#FFCC33'
		});
		rectangle.setMap(map);
		// [END region_rectangle]
		return rectangle;
	}
	
	//google.maps.event.addDomListener(window, 'load', initialize);
	
	//----------------------------------------------

	function clearRegions(){
		for(var i=0;i<regions.length;i++)
			regions[i].setMap(null);
		coordinates=[];
		regions=[];
	}
	
	
	function setNewRegions(c,c1){
  		var dltlat=(parseFloat(nelat)-parseFloat(swlat))/c;
  		var dltlng=(parseFloat(nelng)-parseFloat(swlng))/c1;
		var x=0;
			for(var i=1;i<=c;i++){
				for(var j=1;j<=c1;j++){
					var swlatitud=parseFloat(swlat)+(parseFloat(dltlat)*(i-1));
					var swlongitud=parseFloat(swlng)+(parseFloat(dltlng)*(j-1));
					var nelatitud=parseFloat(swlat)+(parseFloat(dltlat)*(i));
					var nelongitud=parseFloat(swlng)+(parseFloat(dltlng)*(j));
					
					var bound={
						"swlatitud":swlatitud,
						"swlongitud":swlongitud,
						"nelatitud":nelatitud,
						"nelongitud":nelongitud
					};
					coordinates.push(bound);
					var currentRegion=addRegion(nelatitud,nelongitud,swlatitud,swlongitud);
					regions.push(currentRegion);
				}
			}
	}
	
	
	function setInitialMap(){
		lat=$('#latitud').val();
  		lng=$('#longitud').val();
		nelat=$('#nelatitud').val();
  		nelng=$('#nelongitud').val();
  		swlat=$('#swlatitud').val();
  		swlng=$('#swlongitud').val();
  		
  		$.getJSON( "https://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lng+"&sensor=false", function( json ) {
  			if(json.results[0]!=undefined){
  				lat=json.results[0].geometry.location.lat;
  				lng=json.results[0].geometry.location.lng;
  				locate(lat,lng);
  				//Establecemos la region del area de cobertura
  				addRegion(nelat,nelng,swlat,swlng);
  		  		google.appengine.homelike.clientes.clientesinRage(document.querySelector('#idProveedor').value,parseFloat(nelat)+.04,parseFloat(nelng)+.04,parseFloat(swlat)-.04,parseFloat(swlng)-.04);
  			}else{
  				$("#message").addClass("alert alert-warning"); 
  				$("#message").text("Ubicacion no encontrada");
  			}
  		});
  	}
	
	function loadCurrentRegions(){
		idProveedor=document.querySelector('#idProveedor').value;
		gapi.client.proveedorendpoint.getProveedor({'idProveedor': idProveedor}).execute(
			function(output){
				currentRegions = output.regiones || [];
				for(var i=0;i<currentRegions.length;i++){
					var currentRegion=addRegion(currentRegions[i].nelatitud,
							currentRegions[i].nelongitud,
							currentRegions[i].swlatitud,
							currentRegions[i].swlongitud
					);
					regions.push(currentRegion);
				}
		});
	}
	
	
	function addRegionsEvents(){
		for(var i=0;i<=9;i++){
			$("#"+i).click(function(){
				numRegions=parseInt($(this).attr('id'));
				clearRegions();
				switch(numRegions){
					case 0: 
						setNewRegions(0,0);
						break;
					case 2: 
						setNewRegions(2,1);
						break;
					case 4: 
						setNewRegions(2,2);
						break;
					case 9: 
						setNewRegions(3,3);
						break;
				}	
			});
		}
		$("#btnseg").click(function(){
			//Eliminamos las regiones del proveedor
	  		google.appengine.homelike.regiones.delete(
	  			document.querySelector('#idProveedor').value
	  		);
	  		
			for(var i=0;i<coordinates.length;i++){		
				var coordinate=coordinates[i];
				var nelatitud=coordinate.nelatitud;
		  		var nelongitud=coordinate.nelongitud;
		  		var swlatitud=coordinate.swlatitud;
		  		var swlongitud=coordinate.swlongitud;
		 
		  		//Agregamos las nuevas regiones del proveedor
				google.appengine.homelike.regiones.insert(
						document.querySelector('#idProveedor').value,
						nelatitud,nelongitud,swlatitud,swlongitud
				); 
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
		    		setInitialMap();
		    		loadCurrentRegions();
		    		addRegionsEvents();
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}
