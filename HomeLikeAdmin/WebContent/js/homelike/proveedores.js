/** google global namespace for Google projects. */
var google = google || {};
/** appengine namespace for Google Developer Relations projects. */
google.appengine = google.appengine || {};
/** homelike oferta proveedor namespace for this code. */
google.appengine.homelike = google.appengine.homelike || {};
google.appengine.homelike.proveedores = google.appengine.homelike.proveedores || {};

//google.appengine.homelike.WEB_CLIENT_ID='429890560769-vgaolojo01ke78sbhut2sgvf3qcol21m.apps.googleusercontent.com';
google.appengine.homelike.WEB_CLIENT_ID='429890560769-e15fqu56oc53v2cs1vfss4a4bla3vb7s.apps.googleusercontent.com';
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
	//var ROOT = 'http://localhost:56419/_ah/api';
	var ROOT = 'https://admin-dot-steam-form-673.appspot.com/_ah/api';
	gapi.client.load('proveedoradminendpoint', 'v1',loadCallback, ROOT);
	gapi.client.load('oauth2', 'v2', loadCallback);
}

//Variable para llevar el control del Proveedor actual
var currentProveedor;


google.appengine.homelike.proveedores.list = function(){
	gapi.client.proveedoradminendpoint.listProveedor().execute(
		function(output){
			output.items = output.items || [];
			if(output.items.length==0){
				$("#message").addClass("alert alert-warning"); 
				$("#message").text("No existen proveedores");
				$("#message").show();
				$.unblockUI();
			}else{
				for(var i=0;i<output.items.length;i++){
					var proveedor=output.items[i];
					if(i % 2 == 0)
						style='odd';
					else
						style='even';
					addRow(style,proveedor);
				}
				$.unblockUI();
			}
	});
}


function addRow(style,proveedor){
	var deshabilitar="<button class='btn btn-danger' id='btndeshabilitar"+proveedor.idProveedor+"' name='btndeshabilitar'>Deshabilitar</button>"; 
	var habilitar="<button class='btn btn-info' id='btnhabilitar"+proveedor.idProveedor+"' name='btnhabilitar'>Habilitar</button>";
	var status;
	if(proveedor.status==0)
		status=habilitar;
	if(proveedor.status==1)
		status=deshabilitar;
	$("#tblProveedores").append(
		"<tr class='"+style+"'>"+
			"<td class='  sorting_1'>"+proveedor.idProveedor+"</td>"+
			"<td class='  sorting_1' id='p"+proveedor.idProveedor+"' style='cursor: pointer'><img src='"+proveedor.logo+"' class='img-circle' style='width:35px;height:35px'/></td>"+
			"<td class='  sorting_1'>"+proveedor.nombre+"</td>"+
			"<td class='  sorting_1'>"+proveedor.usuario+"</td>"+
			"<td class='  sorting_1'>"+proveedor.telefono+"</td>"+	
			"<td class='  sorting_1'>"+
				new Date(proveedor.fechaHoraCreacion).toLocaleDateString()+" "+
				new Date(proveedor.fechaHoraCreacion).toLocaleTimeString()+
			"</td>"+	
			"<td>" +
				"<button class='btn btn-info' id='btnpagos"+proveedor.idProveedor+"' name='btnpagos'>Ver Pagos</button>"+
			"</td>"+
			"<td>"+status+"</td>"+
		"</tr>"
	);	
	$("#p"+proveedor.idProveedor).click(function(){
		currentProveedor=proveedor;
		$("#contenido").load("fragments/proveedor.jsp");
	});
	
	$("#btnpagos"+proveedor.idProveedor).click(function(){
		currentProveedor=proveedor;
		$("#contenido").load("fragments/pagosp.jsp");
	});
	
	$("#btndeshabilitar"+proveedor.idProveedor).click(function(){
		google.appengine.homelike.proveedores.deshabilitar(
			proveedor.idProveedor
		);
	});
	
	$("#btnhabilitar"+proveedor.idProveedor).click(function(){
		google.appengine.homelike.proveedores.habilitar(
			proveedor.idProveedor
		);
	});
}


google.appengine.homelike.proveedores.deshabilitar = function(idProveedor){
	gapi.client.proveedoradminendpoint.updateStatus({'idProveedor': idProveedor,'status':'0'}).execute(
	function(output){
		$("#contenido").load("fragments/proveedores.jsp");
	});
}

google.appengine.homelike.proveedores.habilitar = function(idProveedor){
	gapi.client.proveedoradminendpoint.updateStatus({'idProveedor': idProveedor,'status':'1'}).execute(
	function(output){
		$("#contenido").load("fragments/proveedores.jsp");
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
	    		google.appengine.homelike.proveedores.list();
	    	}
	    }else{
	    	alert('No Login');
	    	window.location.replace('index.jsp');
	    }
	});
}
