	/** google global namespace for Google projects. */
	var google = google || {};
	/** appengine namespace for Google Developer Relations projects. */
	google.appengine = google.appengine || {};
	/** homelike oferta proveedor namespace for this code. */
	google.appengine.homelike = google.appengine.homelike || {};
	google.appengine.homelike.proveedor = google.appengine.homelike.proveedor || {};
	google.appengine.homelike.proveedor.horarios = google.appengine.homelike.proveedor.horarios || {};

	google.appengine.homelike.WEB_CLIENT_ID='429890560769-odthisg69be4tj9q9k4jb3ordfjpj4kp.apps.googleusercontent.com';
	//google.appengine.homelike.WEB_CLIENT_ID='429890560769-1ahgmrm6v3o8o8diehah95j7locgshj3.apps.googleusercontent.com';
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
	    } , message: 'Espere un momento... cargando' });
		
		apisToLoad = 2; // must match number of calls to gapi.client.load()
		var ROOT = 'http://localhost:8888/_ah/api';
		//var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
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
			var tmpTime=$("#iLunes").val().split(":");
			var dateI=new Date();
			var dateF=new Date();
			dateI.setHours(tmpTime[0]);
			dateI.setMinutes(tmpTime[1]);
			
			tmpTime=$("#fLunes").val().split(":");
			dateF.setHours(tmpTime[0]);
			dateF.setMinutes(tmpTime[1]);
			google.appengine.homelike.proveedor.horarios.insert(
				dateI,
				dateF,
				document.querySelector('#idProveedor').value,
				1
			);
		});
		
		var btnSabado = document.querySelector('#btnSabado');
		btnSabado.addEventListener('click', function(e) {
			var tmpTime=$("#iSabado").val().split(":");
			var dateI=new Date();
			var dateF=new Date();
			dateI.setHours(tmpTime[0]);
			dateI.setMinutes(tmpTime[1]);
			
			tmpTime=$("#fSabado").val().split(":");
			dateF.setHours(tmpTime[0]);
			dateF.setMinutes(tmpTime[1]);
			google.appengine.homelike.proveedor.horarios.insert(
				dateI,
				dateF,
				document.querySelector('#idProveedor').value,
				2
			);
		});
		
		var btnDomingo = document.querySelector('#btnDomingo');
		btnDomingo.addEventListener('click', function(e) {
			var tmpTime=$("#iDomingo").val().split(":");
			var dateI=new Date();
			var dateF=new Date();
			dateI.setHours(tmpTime[0]);
			dateI.setMinutes(tmpTime[1]);
			
			tmpTime=$("#fDomingo").val().split(":");
			dateF.setHours(tmpTime[0]);
			dateF.setMinutes(tmpTime[1]);
			google.appengine.homelike.proveedor.horarios.insert(
				dateI,
				dateF,
				document.querySelector('#idProveedor').value,
				3
			);
		});
		
		$.unblockUI();
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
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}

