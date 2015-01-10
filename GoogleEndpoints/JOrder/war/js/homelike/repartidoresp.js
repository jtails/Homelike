	/** google global namespace for Google projects. */
	var google = google || {};
	/** appengine namespace for Google Developer Relations projects. */
	google.appengine = google.appengine || {};
	/** homelike oferta proveedor namespace for this code. */
	google.appengine.homelike = google.appengine.homelike || {};
	google.appengine.homelike.repartidor = google.appengine.homelike.repartidor || {};

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
	    } , message: 'Espere un momento... cargando repartidores' });
		
		apisToLoad = 2; // must match number of calls to gapi.client.load()
		//var ROOT = 'http://localhost:8888/_ah/api';
		var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('repartidorendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('oauth2', 'v2', loadCallback);
	}
	
	function addEvents(){
		var btnenviar = document.querySelector('#btnenviar');
		btnenviar.addEventListener('click', function(e) {
		google.appengine.homelike.repartidor.insertRepartidor(
			document.querySelector('#idProveedor').value,
			document.querySelector('#nombres').value,
			document.querySelector('#apaterno').value,
			document.querySelector('#amaterno').value,
			document.querySelector('#usuario').value,0
		);				
		});
	}
	
	function addRepartidor(idRepartidor,nombres,apaterno,amaterno,usuario,status){
		var deshabilitar="<button class='btn btn-danger' id='btndeshabilitar"+idRepartidor+"' name='btndeshabilitar'>Deshabilitar</button>"; 
		var habilitar="<button class='btn btn-info' id='btnhabilitar"+idRepartidor+"' name='btnhabilitar'>Habilitar</button>";
		var editar="<button class='btn btn-default' id='btneditar"+idRepartidor+"' name='btneditar'><i class='fa fa-pencil'></i></button>";
		var save="<button class='btn btn-default' style='display:none' id='btnguardar"+idRepartidor+"' name='btnguardar'><i class='fa fa-save'></i></button>";
		var button;
		//Producto deshabilitado
		if(status==-3)
			button=habilitar;
		else{
			button=editar;
			button+=save;
			button+=deshabilitar;
		}
		$('#tblRepartidores').append("<tr id='tr"+idRepartidor+"'>"+
					"<td><input type='text' disabled value='"+nombres+"' id='nombres"+idRepartidor+"'/></td>"+
					"<td><input type='text' disabled value='"+apaterno+"' id='apaterno"+idRepartidor+"'/></td>"+
					"<td><input type='text' disabled value='"+amaterno+"' id='amaterno"+idRepartidor+"'/></td>"+
					"<td><input type='text' disabled value='"+usuario+"' id='usuario"+idRepartidor+"'/></td>"+
					"<td>"+
						button+
					"</td>"+
			   "</tr>");
		$("#btndeshabilitar"+idRepartidor).click(function(){
			google.appengine.homelike.repartidor.deshabilitar(
				idRepartidor
			);
		});
		
		$("#btnhabilitar"+idRepartidor).click(function(){
			google.appengine.homelike.repartidor.habilitar(
				idRepartidor
			);
		});
		$("#btneditar"+idRepartidor).click(function(){
			$("#nombres"+idRepartidor).removeAttr('disabled');
			$("#apaterno"+idRepartidor).removeAttr('disabled');
			$("#amaterno"+idRepartidor).removeAttr('disabled');
			$("#usuario"+idRepartidor).removeAttr('disabled');
			$("#btnguardar"+idRepartidor).show();
			$("#btneditar"+idRepartidor).hide();
		});
		
		$("#btnguardar"+idRepartidor).click(function(){
			$("#nombres"+idRepartidor).attr('disabled','disabled');
			$("#apaterno"+idRepartidor).attr('disabled','disabled');
			$("#amaterno"+idRepartidor).attr('disabled','disabled');
			$("#usuario"+idRepartidor).attr('disabled','disabled');
			$("#btnguardar"+idRepartidor).hide();
			$("#btneditar"+idRepartidor).show();
			
			google.appengine.homelike.repartidor.insertRepartidor(
				document.querySelector('#idProveedor').value,
				document.querySelector('#nombres'+idRepartidor).value,
				document.querySelector('#apaterno'+idRepartidor).value,
				document.querySelector('#amaterno'+idRepartidor).value,
				document.querySelector('#usuario'+idRepartidor).value,
				idRepartidor
			);	
			
		});
	}

	google.appengine.homelike.repartidor.insertRepartidor = function(proveedor,nombres,apaterno,amaterno,usuario,repartidor){
		gapi.client.repartidorendpoint.insertRepartidor({'idRepartidor': repartidor,'proveedor': {'idProveedor': proveedor},
													'nombres': nombres,'apaterno': apaterno,'amaterno': amaterno,
													'usuario': usuario}).execute(
		function(output) {
			 if(output.idRepartidor!=0){
				 $("#message").addClass("alert alert-warning"); 
				 $("#message").text("Repartidor Actualizado");
				 google.appengine.homelike.repartidor.list(
							document.querySelector('#idProveedor').value
				 );
			 }
		});

	}
	
	google.appengine.homelike.repartidor.deshabilitar = function(repartidor){
		gapi.client.repartidorendpoint.deshabilitarRepartidor({'id': repartidor}).execute(
		function(output){
			google.appengine.homelike.repartidor.list(
					document.querySelector('#idProveedor').value
			);
		});
	}
	
	google.appengine.homelike.repartidor.habilitar = function(repartidor){
		gapi.client.repartidorendpoint.habilitarRepartidor({'id': repartidor}).execute(
		function(output){
			google.appengine.homelike.repartidor.list(
					document.querySelector('#idProveedor').value
			);
		});
	}
	
	google.appengine.homelike.repartidor.list = function(idProveedor){
		gapi.client.repartidorendpoint.listAllRepartidoresByProveedor({'idProveedor': idProveedor}).execute(
		
		function(output){
			$('#tblRepartidores').empty();
			output.items = output.items || [];
			for(var i=0;i<output.items.length;i++){
				addRepartidor(output.items[i].idRepartidor,
					output.items[i].nombres,
					output.items[i].apaterno,
					output.items[i].amaterno,
					output.items[i].usuario,
					output.items[i].status
				);
			}
		});
		$.unblockUI();
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
					google.appengine.homelike.repartidor.list(
						document.querySelector('#idProveedor').value
					);
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}

