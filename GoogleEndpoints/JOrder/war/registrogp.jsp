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
	
	<script src="js/jquery.js"></script>
	<script src="js/jQuery/jquery.blockUI.js"></script>
   	<script src="js/respond.min.js"></script>
   	<script src="js/bootstrap.min.js"></script>
   	<script src="js/homelike/registrogp.js"></script>
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
 
 <jsp:include page="fragments/sessionrp.jsp"/>
 
 <div class="admin-form">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <!-- Widget starts -->
            <div class="widget wred">
              <div class="widget-head">
                <i class="fa fa-lock"></i> Registro - Datos Generales
              </div>
              <div class="widget-content">
                <div class="padd">
                  
                  <form id="form" class="form-horizontal" action="rproveedor" method="POST">
                    <!-- Registration form starts -->
                                      <!-- Name -->
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Nombre</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="nombre" name="nombre" value="${sessionScope.proveedor.nombre}">
                                            </div>
                                          </div>   
                                          <input type="hidden" class="form-control" id="logo" name="logo" value="${sessionScope.proveedor.logo}">

                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Slogan</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="slogan" name="slogan" value="${sessionScope.proveedor.slogan}">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Rfc</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="rfc" name="rfc" value="${sessionScope.proveedor.rfc}">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Razon Social</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="razon" name="razon" value="${sessionScope.proveedor.razonSocial}">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Telefono</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="telefono" name="telefono" value="${sessionScope.proveedor.telefono}">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Correo</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="usuario" name="usuario" value="${sessionScope.proveedor.usuario}">
                                            </div>
                                          </div>
                                          <!-- Select box -->
                                          <div class="form-group">
                                            <label class="control-label col-lg-3">Servicio</label>
                                            <div class="col-lg-4">
                                            	<select class="form-control" name="servicio" id="servicio">
  												</select>                               
                                            </div>
                                          </div>                                           
                                          <!-- Username -->
                                          <div id="message">
        								  </div>
                                          <!-- Accept box and button s-->
                                          <div class="form-group">
                                            <div class="col-lg-9 col-lg-offset-3">
                                              <input type="hidden" id="opcion" name="opcion" value="registrodp"/>
                                              <input type="hidden" id="idProveedor" name="idProveedor" value="${sessionScope.proveedor.idProveedor}"/>
                                              <button type="reset" class="btn btn-sm btn-default">Cancelar</button>
                                              <button type="submit" class="btn btn-sm btn-info" name="btnenviar" id="btnenviar">Siguiente</button>
                                            </div>
                                          </div>
                  </form>

                </div>
              </div>
            </div>  
      </div>
    </div>    
  </div> 
</div>
 
  </body>
</html>


