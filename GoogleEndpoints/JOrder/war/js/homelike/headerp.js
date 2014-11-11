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
		//Debido a que los headers siempre estan presentes se les cambia el nombre a las funciones de OAuth
		var apisToLoad;
		var loadCallback_ = function() {
		    if (--apisToLoad == 0) {
		      signin_(true, userAuthed_);
		    }
		};
		
		apisToLoad = 3; // must match number of calls to gapi.client.load()
		//var ROOT = 'http://localhost:8888/_ah/api';
		var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
		gapi.client.load('pedidoendpoint', 'v1',loadCallback_, ROOT);
		gapi.client.load('cuentaendpoint', 'v1',loadCallback_, ROOT);
		gapi.client.load('oauth2', 'v2', loadCallback_);
	}
	
	function showPedidos(){
		var idProveedor=document.querySelector('#idProveedor').value;
		$(document).powerTimer({interval: 180000,func: function(){
			gapi.client.pedidoendpoint.countActivePedidosByProveedor({'idProveedor': idProveedor}).execute(
				function(output){
					noty({text: "<h3 style='color:#FFFFFF;'><strong>"+output.numPedidos+"</strong> Pedidos Activos <i class='fa fa-star' style='color:#FFCC00'/></h3>",
						layout:'topRight',type:'information',timeout:60000,
						callback: {
							onClose: function(){
								//$("#contenido").load("fragments/pedidosp.jsp");
							}
						}});
					$("#pedidosp").text(output.numPedidos);
			});
			
			gapi.client.pedidoendpoint.countTotalPedidosByProveedor({'idProveedor': idProveedor}).execute(
					function(output){					
						$("#pedidost").text(output.numPedidos);
			});
			
		},});
	}
	
	
	function showClientes(){
		var idProveedor=document.querySelector('#idProveedor').value;
		gapi.client.cuentaendpoint.getClienteswithPedidoByProveedor({'idProveedor': idProveedor}).execute(
			function(output){					
				$("#clientesp").text(output.numClientes);
		});
	}
	
	function addEvents(){
		$("#pedidosph").click(function(){
			$("#contenido").load("fragments/pedidosp.jsp");
		});
		$("#pedidosth").click(function(){
			$("#contenido").load("fragments/hpedidosp.jsp");
		});
		
		var status=$("#status").val();
		if(status==0){
			$("#messageg").addClass("alert alert-warning"); 
			$("#messageg").text("Usted debe ponerse en contacto con nuestro equipo de administracion para que su cuenta sea activada!!");
		}
	}
	
	//---------------------OAuth 2.0-----------------------------
	
	function signin_(mode, authorizeCallback) {
		gapi.auth.authorize({client_id: google.appengine.homelike.WEB_CLIENT_ID,
			scope: google.appengine.homelike.EMAIL_SCOPE, immediate: mode},
		    authorizeCallback);
	}

	
	function userAuthed_() {
		var request =
			gapi.client.oauth2.userinfo.get().execute(function(output) {
		    if(output!=undefined && output.verified_email!=undefined){
		    	if(output.verified_email){
		    		addEvents();
		    		showPedidos();
		    		showClientes();
		    		$("#profile").attr('src',output.picture);
		    	}
		    }else{
		    	alert('No Login');
		    	window.location.replace('index.jsp');
		    }
		});
	}
