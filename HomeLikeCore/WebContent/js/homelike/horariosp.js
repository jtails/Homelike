	/** google global namespace for Google projects. */
	var google = google || {};
	/** appengine namespace for Google Developer Relations projects. */
	google.appengine = google.appengine || {};
	/** homelike oferta proveedor namespace for this code. */
	google.appengine.homelike = google.appengine.homelike || {};
	google.appengine.homelike.proveedor = google.appengine.homelike.proveedor || {};
	google.appengine.homelike.proveedor.horarios = google.appengine.homelike.proveedor.horarios || {};

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
	    } , message: 'Espere un momento... cargando horarios' });
		
		apisToLoad = 2; // must match number of calls to gapi.client.load()
		//var ROOT = 'http://localhost:8888/_ah/api';
		var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('horariosproveedorendpoint', 'v1',loadCallback, ROOT);
		gapi.client.load('oauth2', 'v2', loadCallback);
	}
	
	function addEvents(){
		$('#datetimepicker1').datetimepicker({
			pickDate: false
		});
		$('#datetimepicker2').datetimepicker({
			pickDate: false
		});
		$('#datetimepicker3').datetimepicker({
			pickDate: false
		});
		$('#datetimepicker4').datetimepicker({
			pickDate: false
		});
		$('#datetimepicker5').datetimepicker({
			pickDate: false
		});
		$('#datetimepicker6').datetimepicker({
			pickDate: false
		});
		$("#btnLunes").removeAttr("disabled");
		$("#btnSabado").removeAttr("disabled");
		$("#btnDomingo").removeAttr("disabled");
		
		var btnLunes = document.querySelector('#btnLunes');
		btnLunes.addEventListener('click', function(e) {
			
			var tmpTimei=$("#iLunes").val().split(":");
			var tmpTimef=$("#fLunes").val().split(":");
			if(tmpTimei.length>1 && tmpTimef.length>1){
				var dateI=new Date();
				var dateF=new Date();
				dateI.setHours(tmpTimei[0]);
				dateI.setMinutes(tmpTimei[1]);
			
				dateF.setHours(tmpTimef[0]);
				dateF.setMinutes(tmpTimef[1]);
				google.appengine.homelike.proveedor.horarios.insert(
					dateI,
					dateF,
					document.querySelector('#idProveedor').value,
					1
				);
			}else{
				$("#message").addClass("alert alert-warning"); 
				$("#message").text("Debe seleccionar un horario");
			}
		});
		
		var btnSabado = document.querySelector('#btnSabado');
		btnSabado.addEventListener('click', function(e) {
			
			var tmpTimei=$("#iSabado").val().split(":");
			var tmpTimef=$("#fSabado").val().split(":");
			if(tmpTimei.length>1 && tmpTimef.length>1){
				var dateI=new Date();
				var dateF=new Date();
				dateI.setHours(tmpTimei[0]);
				dateI.setMinutes(tmpTimei[1]);
			
				dateF.setHours(tmpTimef[0]);
				dateF.setMinutes(tmpTimef[1]);
				google.appengine.homelike.proveedor.horarios.insert(
					dateI,
					dateF,
					document.querySelector('#idProveedor').value,
					2
				);
			}else{
				$("#message").addClass("alert alert-warning"); 
				$("#message").text("Debe seleccionar un horario");
			}
		});
		
		var btnDomingo = document.querySelector('#btnDomingo');
		btnDomingo.addEventListener('click', function(e) {
			
			var tmpTimei=$("#iDomingo").val().split(":");
			var tmpTimef=$("#fDomingo").val().split(":");
			if(tmpTimei.length>1 && tmpTimef.length>1){
				var dateI=new Date();
				var dateF=new Date();
				dateI.setHours(tmpTimei[0]);
				dateI.setMinutes(tmpTimei[1]);
			
				dateF.setHours(tmpTimef[0]);
				dateF.setMinutes(tmpTimef[1]);
				google.appengine.homelike.proveedor.horarios.insert(
					dateI,
					dateF,
					document.querySelector('#idProveedor').value,
					3
				);
			}else{
				$("#message").addClass("alert alert-warning"); 
				$("#message").text("Debe seleccionar un horario");
			}
		});
		
	}
	
	
	
	google.appengine.homelike.proveedor.horarios.insert = function(abrimos,cerramos,proveedor,tipo_horario){
		gapi.client.horariosproveedorendpoint.insertHorariosProveedor({'abrimos': abrimos,'cerramos': cerramos,
													'proveedor': {'idProveedor': proveedor},
													'tipoHorario': tipo_horario}).execute(
		function(output) {
			 if(output.idHorario!=0){
				 $("#message").addClass("alert alert-warning"); 
				 $("#message").text("Horario Actualizado");
			 }
		});

	}
	
	google.appengine.homelike.proveedor.horarios.del = function(proveedor,tipo_horario){
		gapi.client.horariosproveedorendpoint.deleteHorariosProveedor({'proveedor': {'idProveedor': proveedor},
													'tipoHorario': tipo_horario}).execute(
		function(output) {
			 if(output.idHorario!=0){
				 $("#message").addClass("alert alert-warning"); 
				 $("#message").text("Horario Actualizado");
			 }
		});

	}
	
	google.appengine.homelike.proveedor.horarios.list = function(proveedor){
		gapi.client.horariosproveedorendpoint.getHorariosProveedor({'proveedor': {'idProveedor': proveedor}}).execute(
		function(output) {
			output.items = output.items || [];
			for(var i=0;i<output.items.length;i++){
				var horarioproveedor=output.items[i];
				var abrimos=new Date(horarioproveedor.abrimos);
				var cerramos=new Date(horarioproveedor.cerramos);
				//Lunes-Viernes
				if(horarioproveedor.tipoHorario==1){
					$("#iLunes").val(abrimos.toLocaleTimeString());
					$("#fLunes").val(cerramos.toLocaleTimeString());
					$("#controles1").append(
						"<button class='btn btn-danger' id='btneliminar1' name='btneliminar1'><i class='fa fa-times'></i></button>"
					);
					$("#btneliminar1").click(function(){
						google.appengine.homelike.proveedor.horarios.del(
							document.querySelector('#idProveedor').value,
							1
						);
						$("#iLunes").val("");
						$("#fLunes").val("");
						$("#message").addClass("alert alert-warning"); 
						$("#message").text("Horario Eliminado");
					});
				}
				if(horarioproveedor.tipoHorario==2){
					$("#iSabado").val(abrimos.toLocaleTimeString());
					$("#fSabado").val(cerramos.toLocaleTimeString());
					$("#controles2").append(
						"<button class='btn btn-danger' id='btneliminar2' name='btneliminar2'><i class='fa fa-times'></i></button>"
					);
					$("#btneliminar2").click(function(){
						google.appengine.homelike.proveedor.horarios.del(
							document.querySelector('#idProveedor').value,
							2
						);
						$("#iSabado").val("");
						$("#fSabado").val("");
						$("#message").addClass("alert alert-warning"); 
						$("#message").text("Horario Eliminado");
					});
				}
				if(horarioproveedor.tipoHorario==3){
					$("#iDomingo").val(abrimos.toLocaleTimeString());
					$("#fDomingo").val(cerramos.toLocaleTimeString());
					$("#controles3").append(
						"<button class='btn btn-danger' id='btneliminar3' name='btneliminar3'><i class='fa fa-times'></i></button>"
					);
					$("#btneliminar3").click(function(){
						google.appengine.homelike.proveedor.horarios.del(
							document.querySelector('#idProveedor').value,
							3
						);
						$("#iDomingo").val("");
						$("#fDomingo").val("");
						$("#message").addClass("alert alert-warning"); 
						$("#message").text("Horario Eliminado");
					});
				}
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
		    		addEvents();
		    		google.appengine.homelike.proveedor.horarios.list(
		    			document.querySelector('#idProveedor').value
		    		);
		    		$.unblockUI();
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}

