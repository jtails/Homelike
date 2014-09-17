<script src="js/homelike/productosc.js"></script>
<script src="https://apis.google.com/js/client.js?onload=init"></script>
  <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-54276262-1', 'auto');
  ga('send', 'pageview');
  </script>

<!-- Page heading -->
<div class="page-head">
	<h2 class="pull-left">
		<i class="fa fa-home"></i> Pedido
	</h2>

	<!-- Breadcrumb -->
	<div class="bread-crumb pull-right">
		<a href="index.html"><i class="fa fa-home"></i> Home</a>
		<!-- Divider -->
		<span class="divider">/</span> <a href="#" class="bread-current">Dashboard</a>
	</div>

	<div class="clearfix"></div>

</div>
<!-- Page heading ends -->



<!-- Matter -->

<div class="matter">
	<div class="container">
		<div id="message"></div>

		<div class="row">
			<div class="col-md-12">
				<div class="widget">

					<div class="widget-head">
						<div class="pull-left">Detalle de Pedido</div>
						<div class="widget-icons pull-right">
							<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
							<a href="#" class="wclose"><i class="fa fa-times"></i></a>
						</div>
						<div class="clearfix"></div>
					</div>

					<div class="widget-content">
						<div class="pad">
						<form class="form-horizontal" action="pedidos" method="POST" onsubmit="return false;">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>Descripcion</th>
									<th>Presentacion</th>
									<th>Costo Unitario</th>
									<th>Cantidad</th>
								</tr>
							</thead>
							<tbody id="tblProductos">
							</tbody>
						</table>
						</form>
						</div>

						<div class="widget-foot">
							<div class="pull-left">
								Total :<span id="total"></span>
							</div>
							<div class="pull-right">
								<a href="#" class="btn btn-default btn-sm">Cancelar Pedido</a>
								<a href="#modalBox" class="btn btn-danger btn-sm" data-toggle="modal">Finalizar Pedido</a>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Matter ends -->
