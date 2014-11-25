<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <!-- Title and other stuffs -->
  <title>Soporte - HomeLike</title>
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
  <script src="js/homelike/soportec.js"></script>
  <script src="https://apis.google.com/js/client.js?onload=init"></script>
  
  <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-54276262-1', 'auto');
  ga('send', 'pageview');
  </script>

  <!--[if lt IE 9]>
  <script src="js/html5shiv.js"></script>
  <![endif]-->

  <!-- Favicon -->
  <link rel="shortcut icon" href="img/favicon/favicon.ico">
</head>

<body>

 <jsp:include page="fragments/sessionrc.jsp"/>
 
<!-- Form area -->
<div class="admin-form">
  <div class="container">

    <div class="row">
      <div class="col-md-12">
        <!-- Widget starts -->
            <div class="widget worange">
              <!-- Widget head -->
              <div class="widget-head">
                <i class="fa fa-lock"></i> Contacto 
              </div>

              <div class="widget-content">
                <div class="padd">
                	<div id="message">
        			</div>
				 	<div class="support-contact">
				 		<form class="form-horizontal" method="POST" onsubmit="return false;">
				 			<input type="hidden" id="idCuenta" name="idCuenta" value="${sessionScope.cuenta.idCuenta}">
							<hr />
                        	<p><i class="fa fa-phone"></i>&nbsp; Telefono<strong>:</strong> +52-55-21-06-70-45</p>
                        	<hr />
                        	<p><i class="fa fa-envelope"></i>&nbsp; Email<strong>:</strong> soporte.homelike@jtails.mx</p>
                        	<hr />
                        	<p><i class="fa fa-home"></i>&nbsp; Direccion<strong>:</strong> Chicago 74,Napoles Benito Juarez,Mexico D.F </p>
							<hr />
							<p style="text-align:justify;">Si usted experimento algun tipo de problema en este sitio web, le agradecemos nos comente su experiencia y con gusto daremos atencion a su solicitud.</p>
							<div class="form-group">
								<div class="col-lg-5">
									<select class="form-control" id="tsugerencia">
                           			</select>
                           		</div>
                           	</div>
							<textarea class="form-control" id="sugerencia" name="sugerencia" rows="5" placeholder="Comentarios - Sugerencias - Mejoras - Nuevas funcionalidades"></textarea>
                        	<!-- Button -->
                        </form>
                   </div>
				</div>
           	</div>
            <div class="widget-foot">
            	<div style="text-align:right;">
               		<button type="submit" class="btn btn-danger" id="btnenviar" name="btnenviar">Enviar</button>
                </div>
         	</div>
      	</div>  
      </div>
    </div>
  </div> 
</div>

</body>
</html>