<script src="js/homelike/headerc.js"></script>
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
			<p class="meta">Maxima comodidad</p>
		</div>
		<!-- Logo ends -->
	</div>

	<!-- Button section -->
	<div class="col-md-4">
		<!-- Campos de control  -->
		<input type="hidden" id="idServicio" name="idServicio" value="0">
		<input type="hidden" id="idProveedor" name="idProveedor" value="0">
		<input type="hidden" id="idDireccion" name="idDireccion" value="${sessionScope.direccion.idDireccion}"> 
		<input type="hidden" id="idCuenta" name="idCuenta" value="${sessionScope.cuenta.idCuenta}">  
		<input type="hidden" id="latitud" name="latitud" value="${sessionScope.direccion.latitud}"> 
		<input type="hidden" id="longitud" name="longitud" value="${sessionScope.direccion.longitud}">
	</div>

	<!-- Data section -->

	<!-- Button section -->
	<div class="col-md-8">

		<!-- Buttons -->
		<ul class="nav nav-pills pull-right" id="tpedidosh">
		</ul>

	</div>
	
</div>