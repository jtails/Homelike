/** google global namespace for Google projects. */
var google = google || {};
/** appengine namespace for Google Developer Relations projects. */
google.appengine = google.appengine || {};
/** homelike oferta proveedor namespace for this code. */
google.appengine.homelike = google.appengine.homelike || {};
google.appengine.homelike.proveedor = google.appengine.homelike.proveedor || {};
google.appengine.homelike.pagos = google.appengine.homelike.pagos || {};

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
	
	apisToLoad = 2; // must match number of calls to gapi.client.load()
	//var ROOT = 'http://localhost:57424/_ah/api';
	var ROOT = 'https://admin-dot-steam-form-673.appspot.com/_ah/api';
	gapi.client.load('corteendpoint','v1',loadCallback, ROOT);
	gapi.client.load('oauth2', 'v2', loadCallback);
}


google.appengine.homelike.proveedor.load = function(){
	$("#calificacions").attr('data-rateit-value',currentProveedor.calificacion);
	$(".rateit").rateit();
	$('#nombrep').text(currentProveedor.nombre+" :: "+currentProveedor.slogan+" :: ");
	
	$('#btncorte').click(function(){
		google.appengine.homelike.pagos.corte(currentProveedor.idProveedor);
	});
	
	google.appengine.homelike.pagos.listCortes(currentProveedor.idProveedor);
}

google.appengine.homelike.pagos.corte = function(idProveedor){
	gapi.client.corteendpoint.insertCorte({'proveedor':{'idProveedor': idProveedor}}).execute(
		function(output){
			corte=output;
			if(corte.status=-1){
				$("#message").addClass("alert alert-warning");
				$("#message").text(corte.descripcion);
			}else{
				$("#message").addClass("alert alert-warning");
				$("#message").text("Corte Realizado: "+corte.descripcion);
			}
		});
}


google.appengine.homelike.pagos.listCortes = function(idProveedor){
	gapi.client.corteendpoint.listCortes({'proveedor':{'idProveedor': idProveedor}}).execute(
		function(output){
			output.items = output.items || [];
			for(var i=0;i<output.items.length;i++){
				var corte=output.items[i];
				console.log(corte);
				
				$('#container').append(
						"<div class='row'>"+
						"<div class='col-md-12'>"+
							"<div class='widget'>"+
								"<div class='widget-head'>"+
									"<div class='pull-left'>Corte</div>"+
									"<div class='widget-icons pull-right'>"+
										"<a href='#' class='wminimize'><i"+
											"class='fa fa-chevron-down'></i></a> <a href='#' class='wclose'><i"+
											"class='fa fa-times'></i></a>"+
									"</div>"+
									"<div class='clearfix'></div>"+
								"</div>"+
								"<div class='widget-content referrer'>"+
									"<table "+
										"class='table table-striped table-bordered table-hover'>"+
										"<thead>"+
											"<tr>"+
												"<th style='width:10px'>#</th>"+
												"<th style='width:200px'>Descripcion</th>"+
												"<th style='width:50px'>No.Pedidos</th>"+
												"<th style='width:50px'>Adeudo</th>"+
												"<th style='width:80px'>Status</th>"+
											"</tr>"+
										"</thead>"+
										"<tbody id='tblCortes"+corte.idCorte+"'>"+
										"</tbody>"+
									"</table>"+
									"<table "+
										"class='table table-striped table-bordered table-hover'>"+
										"<thead>"+
											"<tr>"+
												"<th style='width:10px'>#</th>"+
												"<th style='width:200px'>Fecha Pago</th>"+
												"<th style='width:50px'>Monto</th>"+
												"<th style='width:50px'>Adeudo</th>"+
												"<th style='width:80px'>Realizar un Pago</th>"+
											"</tr>"+
										"</thead>"+
										"<tbody id='tblPagos"+corte.idCorte+"'>"+
										"</tbody>"+
									"</table>"+
									"<div class='widget-foot'></div>"+
								"</div>"+
							"</div>"+
						"</div>"+
					"</div>");
				
				$("#tblCortes"+corte.idCorte).append("<tr id='tr"+corte.idCorte+"'>"+
					"<td>"+corte.idCorte+"</td>"+
					"<td>"+corte.descripcion+"</td>"+
					"<td>"+corte.nopedidos+"</td>"+
					"<td>"+corte.adeudo+"</td>"+
					"<td>"+corte.status+"</td>"+
				"</tr>");
				
				var pagos = corte.pagos || [];
				for(var i=0;i<pagos.length;i++){
					var pago=pagos[i];
					$("#tblPagos"+corte.idCorte).append("<tr id='tr"+pago.idPago+"'>"+
						"<td>"+pago.idPago+"</td>"+
						"<td>"+pago.fechaHoraPago+"</td>"+
						"<td>"+pago.cantidad+"</td>"+
						"<td></td>"+
						"<td><button class='btn btn-info' id='btnpago"+corte.idCorte+"' name='btnpago'>Realizar Pago</button></td>"+
					"</tr>");

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
	    		google.appengine.homelike.proveedor.load();
	    	}
	    }else{
	    	alert('No Login');
	    	window.location.replace('index.jsp');
	    }
	});
}
