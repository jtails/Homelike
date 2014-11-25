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
   	<script src="js/respond.min.js"></script>
   	<script src="js/bootstrap.min.js"></script>
   	<script src="js/homelike/registrodp.js"></script>
   	
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
                <i class="fa fa-lock"></i> Registro - Direccion
              </div>
              <div class="widget-content">
                <div class="padd">
                  
                  <form class="form-horizontal" action="rproveedor" method="POST">
                    <!-- Registration form starts -->
                                      <!-- Name -->
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Calle</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="calle" name="calle" value="${sessionScope.proveedor.calle}">
                                            </div>
                                          </div>   
                                          <!-- Email -->
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Numero Ext</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="numeroe" name="numeroe" value="${sessionScope.proveedor.nexterior}">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Numero Int</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="numeroi" name="numeroi" value="${sessionScope.proveedor.ninterior}">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Colonia</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="colonia" name="colonia" value="${sessionScope.proveedor.colonia}">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Delegacion</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="delegacion" name="delegacion" value="${sessionScope.proveedor.delegacion}">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">CP</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="cp" name="cp" value="${sessionScope.proveedor.cp}">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Estado</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="estado" name="estado" value="${sessionScope.proveedor.estado}">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Pais</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="pais" name="pais" value="${sessionScope.proveedor.pais}">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Entre Calle</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="calle1" name="calle1" value="${sessionScope.proveedor.calle1}">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Entre Calle</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="calle2" name="calle2" value="${sessionScope.proveedor.calle2}">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Referencia</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="referencia1" name="referencia1" value="${sessionScope.proveedor.referencia1}">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Referencia</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="referencia2" name="referencia2" value="${sessionScope.proveedor.referencia2}">
                                            </div>
                                          </div>
                                          <!-- Accept box and button s-->
                                          <div class="form-group">
                                            <div class="col-lg-9 col-lg-offset-3">
                                              <input type="hidden" id="opcion" name="opcion" value="registrop"/>
                                              <button type="button" id="back" name="back" class="btn btn-sm btn-default">Atras</button>
                                              <button type="submit" class="btn btn-sm btn-info" id="btnvalidar" name="btnvalidar">Siguiente</button>
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


