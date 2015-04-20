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
	
	apisToLoad = 3; // must match number of calls to gapi.client.load()
	//var ROOT = 'http://localhost:57424/_ah/api';
	var ROOT = 'https://admin-dot-steam-form-673.appspot.com/_ah/api';
	gapi.client.load('corteendpoint','v1',loadCallback, ROOT);
	gapi.client.load('pagoendpoint','v1',loadCallback, ROOT);
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
			if(corte.status==-1 || corte.status==-2){
				$("#message").addClass("alert alert-warning");
				$("#message").text(corte.descripcion);
			}else{
				$("#message").addClass("alert alert-warning");
				$("#message").text(corte.descripcion);
			}
		});
}


google.appengine.homelike.pagos.insertPago = function(idCorte,cantidad){
	gapi.client.pagoendpoint.insertPago({'corte':{'idCorte': idCorte},'cantidad':cantidad}).execute(
		function(output){
			pago=output;
			//if(corte.status==-1 || corte.status==-2){
			//	$("#message").addClass("alert alert-warning");
			//	$("#message").text(corte.descripcion);
			//}else{
			//	$("#message").addClass("alert alert-warning");
			//	$("#message").text("Corte Realizado: "+corte.descripcion);
			//}
			$("#message").text("Pago Realizado");
			$("#contenido").load("fragments/pagosp.jsp");
		});
}

google.appengine.homelike.pagos.listCortes = function(idProveedor){
	gapi.client.corteendpoint.listCortes({'proveedor':{'idProveedor': idProveedor}}).execute(
		function(output){
			output.items = output.items || [];
			for(var i=0;i<output.items.length;i++){
				var corte=output.items[i];
				
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
												"<th>#</th>"+
												"<th>Descripcion</th>"+
												"<th>No.Pedidos</th>"+
												"<th>Saldo</th>"+
												"<th>Status</th>"+
											"</tr>"+
										"</thead>"+
										"<tbody id='tblCortes"+corte.idCorte+"'>"+
										"</tbody>"+
									"</table>"+
									"<div class='widget-foot'></div>"+
									"<table "+
										"class='table table-striped table-bordered table-hover'>"+
										"<thead>"+
											"<tr>"+
												"<th>#</th>"+
												"<th>Fecha Pago</th>"+
												"<th>Monto</th>"+
												"<th>Balance</th>"+
												"<th>Controles</th>"+
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
				
				var status;
				if(corte.status==0)
					status='Con adeudo';
				if(corte.status==1)
					status='Pagado';
				
				$("#tblCortes"+corte.idCorte).append("<tr id='tr"+corte.idCorte+"'>"+
					"<td>"+corte.idCorte+"</td>"+
					"<td>"+corte.descripcion+"</td>"+
					"<td>"+corte.nopedidos+"</td>"+
					"<td>"+corte.adeudo+"</td>"+
					"<td>"+status+"</td>"+
				"</tr>");
				
				var pagos = corte.pagos || [];
				var balance=corte.adeudo;
				var btnPago="<button class='btn btn-info' id='btnpago"+corte.idCorte+"' name='"+corte.idCorte+"'>Registrar un Pago</button>";
				for(var j=0;j<pagos.length;j++){
					var pago=pagos[j];
					balance=balance-pago.cantidad;
					$("#tblPagos"+corte.idCorte).append("<tr id='tr"+pago.idPago+"'>"+
						"<td>"+pago.idPago+"</td>"+
						"<td>"+pago.fechaHoraPago+"</td>"+
						"<td>"+pago.cantidad+"</td>"+
						"<td>"+balance+"</td>"+
						"<td></td>"+
					"</tr>");

				}
				if(corte.status==0){
					$("#tblPagos"+corte.idCorte).append("<tr>"+
						"<td>"+btnPago+"</td>"+
						"<td>"+new Date()+"</td>"+
						"<td><input type='text' id='newp"+corte.idCorte+"' disabled></td>"+
						"<td></td>"+
						"<td><button class='btn btn-default' id='btnguardar"+corte.idCorte+"' name='"+corte.idCorte+"'><i class='fa fa-save'></i></button></td>"+
					"</tr>");
					$("#btnpago"+corte.idCorte).click(function(){
						idCorte=$(this).attr('name');
						$("#newp"+idCorte).removeAttr('disabled');
					});
					$("#btnguardar"+corte.idCorte).click(function(){
						idCorte=$(this).attr('name');
						google.appengine.homelike.pagos.insertPago(
							idCorte,
							document.querySelector("#newp"+idCorte).value
						);
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
	    		google.appengine.homelike.proveedor.load();
	    	}
	    }else{
	    	alert('No Login');
	    	window.location.replace('index.jsp');
	    }
	});
}
