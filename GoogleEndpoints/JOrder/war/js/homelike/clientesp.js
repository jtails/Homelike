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
	
	var coordenadas=[];
	var rectangles=[];
	
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
		
		apisToLoad = 3; // must match number of calls to gapi.client.load()
		//var ROOT = 'http://localhost:8888/_ah/api';
		var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('cuentaendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('regionesendpoint', 'v1',loadCallback, ROOT);
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
			console.log(output);
		});
	}
	
	//--------------------------------------------------
	var map;
	var rectangle;
	
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
	
	function setRectangle(nelat,nelng,swlat,swlng){
		// [START region_rectangle]
		var bounds = new google.maps.LatLngBounds(
			new google.maps.LatLng(swlat,swlng),//swlat,swlng
			new google.maps.LatLng(nelat,nelng)//nelat,nelng
		);
		// Define a rectangle and set its editable property to true.
		rectangle = new google.maps.Rectangle({
			bounds: bounds,
			editable: false
		});

		rectangle.setMap(map);
		// [END region_rectangle]
		
	}
	
	//google.maps.event.addDomListener(window, 'load', initialize);
	
	//----------------------------------------------

	
	function clearRegions(){
		for(var i=0;i<rectangles.length;i++)
			rectangles[i].setMap(null);
		coordenadas=[];
		rectangles=[];
	}
	
	function setRegions(c,c1){
		var nelat=$('#nelatitud').val();
  		var nelng=$('#nelongitud').val();
  		var swlat=$('#swlatitud').val();
  		var swlng=$('#swlongitud').val();
  		
  		
  		var dltlat=(parseFloat(nelat)-parseFloat(swlat))/c;
  		var dltlng=(parseFloat(nelng)-parseFloat(swlng))/c1;
		
		//coordenadas=new Array(c*c1);
		//rectangles=new Array(c*c1);
		
		var colors=["#99FF66","#CC0099","#6633FF","#CCFF33","#FFCC33","#FF33FF","#99FF66","#CC0099","#6633FF","#CCFF33","#FFCC33","#FF33FF","#99FF66","#CC0099","#6633FF","#CCFF33","#FFCC33","#FF33FF","#99FF66","#CC0099","#6633FF","#CCFF33","#FFCC33","#FF33FF","#CCFF33"];
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
					coordenadas.push(bound);
					
					var boundsx = new google.maps.LatLngBounds(
						new google.maps.LatLng(swlatitud,swlongitud),
						new google.maps.LatLng(nelatitud,nelongitud)
					);
					rectanglex = new google.maps.Rectangle({
						bounds: boundsx,
						editable: true,
						draggable: true,
						fillColor: colors[4]
					});
					rectanglex.setMap(map);
					rectangles.push(rectanglex);
				}
			}
	}
	
	
	function setMap(){
		var latitud=$('#latitud').val();
  		var longitud=$('#longitud').val();
		var nelatitud=$('#nelatitud').val();
  		var nelongitud=$('#nelongitud').val();
  		var swlatitud=$('#swlatitud').val();
  		var swlongitud=$('#swlongitud').val();
  		$.getJSON( "https://maps.googleapis.com/maps/api/geocode/json?latlng="+latitud+","+longitud+"&sensor=false", function( json ) {
  			if(json.results[0]!=undefined){
  				lat=json.results[0].geometry.location.lat;
  				lng=json.results[0].geometry.location.lng;
  				locate(lat,lng);
  				setRectangle(nelatitud,nelongitud,swlatitud,swlongitud);
  		  		google.appengine.homelike.clientes.clientesinRage(document.querySelector('#idProveedor').value,parseFloat(nelatitud)+.04,parseFloat(nelongitud)+.04,parseFloat(swlatitud)-.04,parseFloat(swlongitud)-.04);
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
		    		for(var i=0;i<=9;i++){
		    			$("#"+i).click(function(){
							regions=parseInt($(this).attr('id'));
							clearRegions();
							switch(regions){
								case 0: 
									setRegions(0,0);
									break;
								case 2: 
									setRegions(2,1);
									break;
								case 4: 
									setRegions(2,2);
									break;
								case 9: 
									setRegions(3,3);
									break;
							}	
						});
		    		}
		    		$("#btnseg").click(function(){
		    			for(var i=0;i<coordenadas.length;i++){
		    				var coordenada=coordenadas[i];
		    				var nelat=coordenada.nelatitud;
		    		  		var nelng=coordenada.nelongitud;
		    		  		var swlat=coordenada.swlatitud;
		    		  		var swlng=coordenada.swlongitud;
		    				google.appengine.homelike.regiones.insert(
		    						document.querySelector('#idProveedor').value,
		    						nelat,nelng,swlat,swlng
		    				); 
		    			}
		    		});
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}
