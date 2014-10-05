<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<meta charset="utf-8">
  	<!-- Title and other stuffs -->
  	<title>Registro - Cliente</title>
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<meta name="description" content="">
  	<meta name="keywords" content="">
  	<meta name="author" content="">
    
    <!-- Stylesheets -->
  	<link href="css/bootstrap.min.css" rel="stylesheet">
  	<link rel="stylesheet" href="css/font-awesome.min.css">
  	<link href="css/style.css" rel="stylesheet">
    <style>
      #map-canvas {
        height: 350px;
        margin: 0px;
        padding: 0px
      }
    </style>
	
	<script src="js/jquery.js"></script>
	<script src="js/respond.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/homelike/registroc.js"></script>
   	<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
   	<script src="https://apis.google.com/js/client.js?onload=init"></script>
   	
  <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-54276262-1', 'auto');
  ga('send', 'pageview');
  </script>
   	
   	
   	<!-- Favicon -->
  	<link rel="shortcut icon" href="img/favicon/favicon.ico">
  </head>
  
 <body>
 
 <form class="form-horizontal" action="cliente" method="POST" onsubmit="return false;" id="form">
	<input type="hidden" id="idCuenta" name="idCuenta" value="${sessionScope.cuenta.idCuenta}">
	<input type="hidden" id="usuario" name="usuario" value="${sessionScope.cuenta.usuario}">
	<input type="hidden" id="telefono" name="telefono" value="${sessionScope.cuenta.telefono}">

	<input type="hidden" id="idDireccion" name="idDireccion" value="${sessionScope.direccion.idDireccion}">
	<input type="hidden" id="calle" name="calle" value="${sessionScope.direccion.calle}">
	<input type="hidden" id="numeroe" name="numeroe" value="${sessionScope.direccion.nexterior}">
	<input type="hidden" id="numeroi" name="numeroi" value="${sessionScope.direccion.ninterior}">
	<input type="hidden" id="colonia" name="colonia" value="${sessionScope.direccion.colonia}">
	<input type="hidden" id="delegacion" name="delegacion" value="${sessionScope.direccion.delegacion}">
	<input type="hidden" id="cp" name="cp" value="${sessionScope.direccion.cp}">
	<input type="hidden" id="estado" name="estado" value="${sessionScope.direccion.estado}">
	<input type="hidden" id="pais" name="pais" value="${sessionScope.direccion.pais}">
	<input type="hidden" id="calle1" name="calle1" value="${sessionScope.direccion.calle1}">
	<input type="hidden" id="calle2" name="calle2" value="${sessionScope.direccion.calle2}">
	<input type="hidden" id="referencia1" name="referencia1" value="${sessionScope.direccion.referencia1}">
	<input type="hidden" id="referencia2" name="referencia2" value="${sessionScope.cuenta.direcciones[0].referencia2}">
	                
	<input type="hidden" id="latitud" name="latitud">
	<input type="hidden" id="longitud" name="longitud">
	<input type="hidden" id="nelatitud" name="nelatitud">
	<input type="hidden" id="nelongitud" name="nelongitud">
	<input type="hidden" id="swlatitud" name="swlatitud">
	<input type="hidden" id="swlongitud" name="swlongitud">
	<input type="hidden" id="opcion" name="opcion" value="login">
	                
</form>
 
 <jsp:include page="fragments/sessionrc.jsp"/>
 <header>
 </header>
 
 <div class="metter">
 <div class="container">
  <div class="row">
    <div class="col-md-12">

    <!-- Widget -->
    <div class="widget">
      <!-- Widget head -->
      <div class="widget-head">
        <div class="pull-left">Selecciona tu ubicacion</div>
        <div class="widget-icons pull-right">
           <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
           <a href="#" class="wclose"><i class="fa fa-times"></i></a>
        </div>  
        <div class="clearfix"></div>
      </div>              

      <!-- Widget content -->
      <div class="widget-content">
        <div class="padd">
		<div id="message">
        </div>
        <!-- Curve chart (Blue color). jQuery Flot plugin used. -->
          <div id="map-canvas"></div>
            <hr />
            <!-- Hover location -->
            <div id="hoverdata">
               Latitud - Longitud  (<span id="lat">0</span>,<span id="lng">0</span>). <span id="clickdata"></span>
            </div>  
            <a href='#modalBox' data-toggle='modal' style='cursor: pointer'>Aviso de Privacidad</a>
            <div class="checkbox">
            	<label>
                	<input type="checkbox" id="contrato"> Acepto Terminos y Condiciones
                </label>
			</div>
            <div style="text-align:right;">
            	<button type="button" id="back" name="back" class="btn btn-sm btn-default">Atras</button>
            	<button type="submit" class="btn btn-sm btn-info" id="btnenviar" name="btnenviar" disabled>Finalizar</button>
            </div>       
        </div>
      </div>
      <!-- Widget ends -->

    </div>
    </div>
  </div>
</div>
</div>


<div id="modalBox" class="modal fade" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">x</button>
			<h4 class="modal-title">Aviso de Privacidad</h4>
		</div>
		<div class="modal-body">
			<embed src="http://homelike.com.mx/docs/AvisodePrivacidad.pdf" width="550" height="750"></embed>
		</div>
	</div>
</div>
</div>
   
  </body>
</html>


