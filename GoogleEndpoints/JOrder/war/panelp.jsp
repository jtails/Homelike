<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<!-- Title and other stuffs -->
<title>HomeLike</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">


<!-- Stylesheets -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Font awesome icon -->
<link rel="stylesheet" href="css/font-awesome.min.css">
<!-- jQuery UI -->
<link rel="stylesheet" href="css/jquery-ui.css">
<!-- Calendar -->
<link rel="stylesheet" href="css/fullcalendar.css">
<!-- prettyPhoto -->
<link rel="stylesheet" href="css/prettyPhoto.css">
<!-- Star rating -->
<link rel="stylesheet" href="css/rateit.css">
<!-- Date picker -->
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<!-- CLEditor -->
<link rel="stylesheet" href="css/jquery.cleditor.css">
<!-- Data tables -->
<link rel="stylesheet" href="css/jquery.dataTables.css">
<!-- Bootstrap toggle -->
<link rel="stylesheet" href="css/jquery.onoff.css">
<!-- Main stylesheet -->
<link href="css/style.css" rel="stylesheet">
<!-- Widgets stylesheet -->
<link href="css/widgets.css" rel="stylesheet">

<script src="js/respond.min.js"></script>
<!--[if lt IE 9]>
  <script src="js/html5shiv.js"></script>
  <![endif]-->

<script src="js/jquery.js"></script>
<script src="js/jQuery/jquery.blockUI.js"></script>
<script src="js/jquery.powertimer.js"></script>
<script src="js/homelike/panelp.js"></script>
<script src="https://apis.google.com/js/client.js?onload=init"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>

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

 <jsp:include page="fragments/sessionp.jsp"/>
 
	<!-- Header starts -->
	<header>
		<div class="container">
			<jsp:include page="fragments/headerp.jsp"/>
		</div>
	</header>

	<!-- Header ends -->

	<!-- Main content starts -->

	<div class="content">

		<!-- Sidebar -->
		<div class="sidebar">
			<div class="sidebar-dropdown">
				<a href="#">Navigation</a>
			</div>

			<!--- Sidebar navigation -->
			<!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->
			<ul id="nav">
				<!-- Main menu with font awesome icon -->
				<li class="open"><a href="#" id="menuhome"><i
						class="fa fa-home"></i> Inicio</a> <!-- Sub menu markup 
            <ul>
              <li><a href="#">Submenu #1</a></li>
              <li><a href="#">Submenu #2</a></li>
              <li><a href="#">Submenu #3</a></li>
            </ul>--></li>
            	<li ><a href="registrogp.jsp"><i
						class="fa fa-pencil"></i> Datos Generales</a>
				</li>
            	<li class="has_sub"><a href="#" id="menupedidos"><i
						class="fa fa-shopping-cart"></i> Pedidos activos</a>
				</li>
				<li class="has_sub"><a href="#" id="menuhpedidos"><i
						class="fa fa-search"></i> Historico Pedidos</a>
				</li>
				<li class="has_sub"><a href="#" id="menuproductos"><i
						class="fa fa-tags"></i> Mis productos</a>
				</li>
				<li class="has_sub"><a href="#"><i class="fa fa-truck"></i>
						Repartidores<span class="pull-right"><i
							class="fa fa-chevron-right"></i></span></a>
					<ul>
						<li><a id='menunrepartidos' href='#'>Nuevo repartidor</a></li>
						<li><a id='menucrepartidos' href='#'>Consultar repartidores</a></li>
					</ul>
				</li>
				<li class="has_sub"><a href="#" id="menuestadisticas"><i 
						class="fa fa-bar-chart-o"></i>Estadisticas</a>
				</li>
				<li class="has_sub"><a href="#" id="menucomentarios"><i 
						class="fa fa-comments-o"></i>Comentarios</a>
				</li>
			</ul>
		</div>

		<!-- Sidebar ends -->

		<!-- Main bar -->
		<div class="mainbar" id="contenido">
			<jsp:include page="fragments/clientesp.jsp"/>
		</div>
		
		<div id="modalBox" class="modal fade" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
		</div>
		<div id="modalBox2" class="modal fade" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
		</div>

		<!-- Mainbar ends -->
		<div class="clearfix"></div>

	</div>
	<!-- Content ends -->

	<!-- Footer starts -->
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<!-- Copyright info -->
					<p class="copy">
						Copyright &copy; 2014 | <a href="http://jtails.mx">Jtails S.A de C.V</a>
					</p>
				</div>
			</div>
		</div>
	</footer>

	<!-- Footer ends -->

	<!-- Scroll to top -->
	<span class="totop"><a href="#"><i class="fa fa-chevron-up"></i></a></span>

	<!-- JS -->
	<!-- jQuery -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Bootstrap -->
	<script src="js/jquery-ui.min.js"></script>
	<!-- jQuery UI -->
	<script src="js/fullcalendar.min.js"></script>
	<!-- Full Google Calendar - Calendar -->
	<script src="js/jquery.rateit.min.js"></script>
	<!-- RateIt - Star rating -->
	<script src="js/jquery.prettyPhoto.js"></script>
	<!-- prettyPhoto -->
	<script src="js/jquery.slimscroll.min.js"></script>
	<!-- jQuery Slim Scroll -->
	<script src="js/jquery.dataTables.min.js"></script>
	<!-- Data tables -->

	<!-- jQuery Flot -->
	<script src="js/excanvas.min.js"></script>
	<script src="js/jquery.flot.js"></script>
	<script src="js/jquery.flot.resize.js"></script>
	<script src="js/jquery.flot.pie.js"></script>
	<script src="js/jquery.flot.stack.js"></script>

	<!-- jQuery Notification - Noty -->
	<script src="js/jquery.noty.js"></script>
	<!-- jQuery Notify -->
	<script src="js/themes/default.js"></script>
	<!-- jQuery Notify -->
	<script src="js/layouts/bottom.js"></script>
	<!-- jQuery Notify -->
	<script src="js/layouts/topRight.js"></script>
	<!-- jQuery Notify -->
	<script src="js/layouts/top.js"></script>
	<!-- jQuery Notify -->
	<!-- jQuery Notification ends -->

	<script src="js/sparklines.js"></script>
	<!-- Sparklines -->
	<script src="js/jquery.cleditor.min.js"></script>
	<!-- CLEditor -->
	<script src="js/bootstrap-datetimepicker.min.js"></script>
	<!-- Date picker -->
	<script src="js/jquery.onoff.min.js"></script>
	<!-- Bootstrap Toggle -->
	<script src="js/filter.js"></script>
	<!-- Filter for support page -->
	<script src="js/custom.js"></script>
	<!-- Custom codes -->
	<script src="js/charts.js"></script>
	<!-- Charts & Graphs -->

	

</body>
</html>