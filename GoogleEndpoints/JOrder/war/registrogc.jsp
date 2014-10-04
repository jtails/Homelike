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
	
	<script src="js/jquery.js"></script>
   	<script src="js/respond.min.js"></script>
   	<script src="js/bootstrap.min.js"></script>
   	
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
 
 <jsp:include page="fragments/sessionrc.jsp"/>
 
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
                  
                  <form id="form" class="form-horizontal" action="rcuenta" method="POST">
                    <!-- Registration form starts -->
                                      <!-- Name -->
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Correo</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="usuario" name="usuario" value="${sessionScope.cuenta.usuario}">
                                            </div>
                                          </div> 
                                          <!-- Email -->
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">Telefono</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="telefono" name="telefono" value="${sessionScope.cuenta.telefono}">
                                            </div>
                                          </div>
                                          <!-- Username -->
                                          <div id="message">
        								  </div>
                                          <!-- Accept box and button s-->
                                          <div class="form-group">
                                            <div class="col-lg-9 col-lg-offset-3">
                                              <input type="hidden" id="opcion" name="opcion" value="registrodc"/>
                                              <button type="reset" class="btn btn-sm btn-default">Reset</button>
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


