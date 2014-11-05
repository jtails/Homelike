/** google global namespace for Google projects. */
var google = google || {};
/** appengine namespace for Google Developer Relations projects. */
google.appengine = google.appengine || {};
/** homelike oferta proveedor namespace for this code. */
google.appengine.homelike = google.appengine.homelike || {};
google.appengine.homelike.comentarios = google.appengine.homelike.comentarios || {};

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
    } , message: 'Espere un momento... cargando ultimos comentarios' });
	
	apisToLoad = 2; // must match number of calls to gapi.client.load()
	var ROOT = 'http://localhost:8888/_ah/api';
	//var ROOT = 'https://homelike-dot-steam-form-673.appspot.com/_ah/api';
	gapi.client.load('pedidoendpoint', 'v1',loadCallback, ROOT);
	gapi.client.load('oauth2', 'v2', loadCallback);
}

google.appengine.homelike.comentarios.list = function(idProveedor,fechaHoraUltimoPedido){
	gapi.client.pedidoendpoint.getComentariosPedidosByProveedor({'idProveedor': idProveedor}).execute(
	function(output){
		output.items = output.items || [];
		if(output.items.length==0){
			$("#message").addClass("alert alert-warning"); 
			$("#message").text("No tiene comentarios existentes");
			$("#message").show();
			$.unblockUI();
		}else{
			for(var i=0;i<output.items.length;i++){
				var pedido=output.items[i];
				if(pedido[0]!=null && pedido[0]!='')
					addComment(pedido);
			}
			$.unblockUI();
		}
	});
}

function addComment(pedido){
	$("#comentariosp").append(
		"<li class='by-me'>"+
			"<div class='avatar pull-left'>"+
				"<img src='img/logo_.png' alt='' style='width:50px;height:50px'>"+
			"</div>"+
			"<div class='chat-content'>"+
				"<div class='chat-meta'>"+
					"<span class='pull-right'>"+new Date(pedido[1]).toLocaleDateString()+" "+new Date(pedido[1]).toLocaleTimeString()+"</span>"+
					"<span class='pull-left'>"+pedido[0]+"</span>"+
				"</div>"+
				"<div class='clearfix'></div>"+
			"</div>"+
		"</li>"
	);
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
	    		google.appengine.homelike.comentarios.list(
	    				document.querySelector('#idProveedor').value
	    		);
	    	}
	    }else{
	    	alert('No Login');
	    	window.location.replace('index.jsp');
	    }
	});
}
