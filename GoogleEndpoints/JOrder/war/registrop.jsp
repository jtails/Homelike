<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<meta charset="utf-8">
  	<!-- Title and other stuffs -->
  	<title>Registro - Proveedor</title>
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
	<script src="js/homelike/registrop.js"></script>
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
 
 <form class="form-horizontal" action="proveedor" method="POST" onsubmit="return false;" id="form">
	<input type="hidden" id="idProveedor" name="idProveedor" value="${sessionScope.proveedor.idProveedor}">
	<input type="hidden" id="nombre" name="nombre" value="${sessionScope.proveedor.nombre}">
	<input type="hidden" id="logo" name="logo" value="${sessionScope.proveedor.logo}">
	<input type="hidden" id="slogan" name="slogan" value="${sessionScope.proveedor.slogan}">
	<input type="hidden" id="rfc" name="rfc" value="${sessionScope.proveedor.rfc}">
	<input type="hidden" id="razon" name="razon" value="${sessionScope.proveedor.razonSocial}">
	<input type="hidden" id="telefono" name="telefono" value="${sessionScope.proveedor.telefono}">
	<input type="hidden" id="servicio" name="servicio" value="${sessionScope.proveedor.servicio.idServicio}">
	<input type="hidden" id="usuario" name="usuario" value="${sessionScope.proveedor.usuario}">

	<input type="hidden" id="calle" name="calle" value="${sessionScope.proveedor.calle}">
	<input type="hidden" id="numeroe" name="numeroe" value="${sessionScope.proveedor.nexterior}">
	<input type="hidden" id="numeroi" name="numeroi" value="${sessionScope.proveedor.ninterior}">
	<input type="hidden" id="colonia" name="colonia" value="${sessionScope.proveedor.colonia}">
	<input type="hidden" id="delegacion" name="delegacion" value="${sessionScope.proveedor.delegacion}">
	<input type="hidden" id="cp" name="cp" value="${sessionScope.proveedor.cp}">
	<input type="hidden" id="estado" name="estado" value="${sessionScope.proveedor.estado}">
	<input type="hidden" id="pais" name="pais" value="${sessionScope.proveedor.pais}">
	<input type="hidden" id="calle1" name="calle1" value="${sessionScope.proveedor.calle1}">
	<input type="hidden" id="calle2" name="calle2" value="${sessionScope.proveedor.calle2}">
	<input type="hidden" id="referencia1" name="referencia1" value="${sessionScope.proveedor.referencia1}">
	<input type="hidden" id="referencia2" name="referencia2" value="${sessionScope.proveedor.referencia2}">
	                
	<input type="hidden" id="latitud" name="latitud">
	<input type="hidden" id="longitud" name="longitud">
	<input type="hidden" id="nelatitud" name="nelatitud" value="${sessionScope.proveedor.nelatitud}">
	<input type="hidden" id="nelongitud" name="nelongitud" value="${sessionScope.proveedor.nelongitud}">
	<input type="hidden" id="swlatitud" name="swlatitud" value="${sessionScope.proveedor.swlatitud}">
	<input type="hidden" id="swlongitud" name="swlongitud" value="${sessionScope.proveedor.swlongitud}">
	<input type="hidden" id="opcion" name="opcion" value="login">               
</form>
 
 <jsp:include page="fragments/sessionrp.jsp"/>
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
        <div class="pull-left">Selecciona tu area de cobertura</div>
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
            <div class="checkbox">
            	<label>
                	<input type="checkbox"> Accept Terms &amp; Conditions
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
   
  </body>
</html>


