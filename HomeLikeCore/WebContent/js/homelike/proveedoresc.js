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
        } , message: 'Espere un momento... cargando proveedores' });
		
		apisToLoad = 2; // must match number of calls to gapi.client.load()
		//var ROOT = 'http://localhost:8888/_ah/api';
		var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('proveedorendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('oauth2', 'v2', loadCallback);
	}
	
	google.appengine.homelike.proveedores.proveedoresinRage = function(latitud,longitud,idServicio){
		gapi.client.proveedorendpoint.listProveedoresinRange({'latitud': latitud,'longitud': longitud,'idServicio': idServicio}).execute(
		function(output){
			//clearProveedores();
			output.items = output.items || [];
			if(output.items.length>0){
				for(var i=0;i<output.items.length;i++){
					var proveedor=output.items[i];
					setMarker(proveedor.latitud,proveedor.longitud,proveedor.nombre);
					addProveedor(proveedor);
				}
				$.unblockUI();
			}else{
				 $("#message").addClass("alert alert-warning"); 
				 $("#message").text("No se encuentran proveedores cercanos a tu ubicacion");
				 $("#message").show();
				 $.unblockUI();
			}
		});
		
	}
	
	function addProveedor(proveedor){
		 var numstars=parseFloat(proveedor.calificacion);
		 var stars="";
		 for(var i=1;i<=5;i++){
			 if(i<=numstars)
				 stars+="<i class='fa fa-star' style='color:#FFCC00'/>";
			 else
				 stars+="<i class='fa fa-star'/>";
		 }
		 $('#proveedores').append("<li class='by-me'>"+
				 "<div class='avatar pull-left'><img src='"+proveedor.logo+"' alt='' style='width:50px;height:50px'></div>"+
				 "<div style='cursor: pointer' id='p"+proveedor.idProveedor+"' class='chat-content'>"+
				 	"<div class='chat-meta'>"+
				 	proveedor.nombre+
				 	" <span class='pull-right'>Comentarios : "+proveedor.numPedidos+"</span>"+
				 	"</div>"+
				 	"<span class='pull-left'>Calificacion : "+stars+"</span>"+
				 	"<span class='pull-left'>"+
				 		proveedor.calle+", "+proveedor.colonia+" "+proveedor.delegacion+", "+proveedor.cp+" "+proveedor.estado+
				 	"</span>"+
				 "<div class='clearfix'></div>"+
				 "</div></li>");
		 $("#p"+proveedor.idProveedor).click(function(){
			 $("#idProveedor").val(proveedor.idProveedor);
			 $("#contenido").load("fragments/productosc.jsp");
		 });
	}
	
	/*function clearProveedores(){
		locate($('#latitud').val(),$('#longitud').val());
		$("#proveedores").empty();
		$("#message").hide();
	}*/
	
	
	//--------------------------------------------------
	var map;
	
	function locate(lat,lng){
		var myLatlng = new google.maps.LatLng(lat,lng);
		var mapOptions = {
			center: new google.maps.LatLng(lat,lng),
			zoom: 11
			};

			map = new google.maps.Map(document.getElementById('map-canvas'),
  			mapOptions);
	
			var marker = new google.maps.Marker({
    		position: myLatlng,
    		map: map,
    		title: 'Mi ubicacion!'
		});
	}
	
	function setMarker(lat,lng,proveedor){
		var myLatlng = new google.maps.LatLng(lat,lng);
		var marker = new google.maps.Marker({
    		position: myLatlng,
    		map: map,
    		title: 'Proveedor : '+proveedor,
    		icon: 'http://google.com/mapfiles/ms/micons/man.png'
		});
	}
	//google.maps.event.addDomListener(window, 'load', initialize);
	
	//----------------------------------------------
	
	function setMap(){
		var latitud=$('#latitud').val();
  		var longitud=$('#longitud').val();
  		var idServicio=$('#idServicio').val();
  		$.getJSON( "https://maps.googleapis.com/maps/api/geocode/json?latlng="+latitud+","+longitud+"&sensor=false", function( json ) {
  			if(json.results[0]!=undefined){
  				lat=json.results[0].geometry.location.lat;
  				lng=json.results[0].geometry.location.lng;
  				locate(lat,lng);
  				if(idServicio!=0)
  		  			google.appengine.homelike.proveedores.proveedoresinRage(latitud,longitud,idServicio);
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
