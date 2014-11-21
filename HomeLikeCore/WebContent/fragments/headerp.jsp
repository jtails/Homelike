<script src="js/homelike/headerp.js"></script>
<script src="https://apis.google.com/js/client.js?onload=init"></script>
  <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-54276262-1', 'auto');
  ga('send', 'pageview');
  </script>

<div class="row">

	<!-- Logo section -->
	<div class="col-md-4">
		<!-- Logo. -->
		<div class="logo">
			<h1>
				<a href="#">Home<span class="bold">Like</span></a>
			</h1>
			<div class='rateit' data-rateit-value='${sessionScope.proveedor.calificacion}' data-rateit-resetable='false' data-rateit-readonly='true' data-rateit-step='1' id='calificacions'></div>
		</div>
		<!-- Logo ends -->
	</div>

	<div class="col-md-4">
		<div id="messageg"></div>
	</div>
	<!-- Button section -->
	<div class="col-md-4">
		<!-- Buttons -->
		<ul class="nav nav-pills pull-right" id="detailsp">

			<!-- Comment button with number of latest comments count -->
			<li class="dropdown dropdown-big" id="pedidosph"><a class="dropdown-toggle"
				href="#" data-toggle="dropdown"> <i class="fa fa-truck"></i>
					Activos <span class="label label-info" id="pedidosp">0</span>
			</a></li>

			<!-- Message button with number of latest messages count-->
			<li class="dropdown dropdown-big" id="pedidosth"><a class="dropdown-toggle"
				href="#" data-toggle="dropdown"> <i class="fa fa-truck"></i>
					Totales <span class="label label-primary" id="pedidost">0</span>
			</a></li>

			<!-- Members button with number of latest members count -->
			<li class="dropdown dropdown-big" id="clientesph"><a class="dropdown-toggle"
				href="#" data-toggle="dropdown"> <i class="fa fa-user"></i>
					Clientes <span class="label label-success" id="clientesp">0</span>
			</a></li>

		</ul>

	</div>

	<!-- Data section -->
</div>