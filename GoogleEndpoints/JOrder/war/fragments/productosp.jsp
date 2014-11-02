<script src="js/homelike/productosp.js"></script>
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
		<i class="fa fa-tags"></i> Productos
	</h2>

	<!-- Breadcrumb -->
	<div class="bread-crumb pull-right">
		<a href="#"><i class="fa fa-home"></i> Home</a>
		<!-- Divider -->
		<span class="divider">/</span> <a href="#" class="bread-current">Dashboard</a>
	</div>

	<div class="clearfix"></div>

</div>
<!-- Page heading ends -->

<input type="hidden" id="idServicio" value="${sessionScope.proveedor.servicio.idServicio}">

<!-- Matter -->

<div class="matter">
	<div class="container" id="productos">
		<div class="row">
			<div class="col-md-12">
				<!-- Widget starts -->
				<div class="widget">
					<div class="widget-head">
						<div class="pull-left">Productos</div>
						<div class="widget-icons pull-right">
							<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
							<a href="#" class="wclose"><i class="fa fa-times"></i></a>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="widget-content">
						<div class="pad">
							<div class="matter">
								<div class="container">
									<div id="message"></div>
									<div class="row">
										<div class="col-md-4">
											<!-- Widget starts -->
											<div class="widget">
												<div class="widget-head">
													<div class="pull-left">Catalogo Productos</div>
													<div class="widget-icons pull-right">
														<a href="#" class="wminimize"><i
															class="fa fa-chevron-up"></i></a> <a href="#" class="wclose"><i
															class="fa fa-times"></i></a>
													</div>
													<div class="clearfix"></div>
												</div>
												<div class="widget-content">
													<div class="padd">

														<form class="form-horizontal" action="proveedor"
															method="POST" onsubmit="return false;">
															<div class="form-group">
																<label class="control-label col-lg-4" for="name">Producto</label>
																<div class="col-lg-8">
																	<select name="cproducto" id="cproducto"
																		class="form-control">
																	</select>
																</div>
															</div>
															<div class="form-group" id="ddescripcion" style="display:none">
																<label class="control-label col-lg-4" for="name">Descripcion</label>
																<div class="col-lg-8">
																	<input type="text" name="descripcion" id="descripcion"
																		class="form-control"/>
																</div>
															</div>
															<div class="form-group" id="dpresentacion" style="display:none">
																<label class="control-label col-lg-4" for="name">Presentacion</label>
																<div class="col-lg-8">
																	<input type="text" name="presentacion" id="presentacion"
																		class="form-control"/>
																</div>
															</div>
															<div class="form-group">
																<label class="control-label col-lg-4" for="name">Precio</label>
																<div class="col-lg-8">
																	<input type="text" name="precio" id="precio"
																		class="form-control" />
																</div>
															</div>
															<!-- Accept box and button s-->
															<div class="form-group">
																<div class="col-lg-9 col-lg-offset-3"
																	style="text-align: right;">
																	<button type="submit" class="btn btn-sm btn-info"
																		id="btnenviar" name="btnenviar">Agregar</button>
																</div>
															</div>
														</form>

													</div>
												</div>
											</div>
										</div>
										<div class="col-md-8">
											<!-- Widget starts -->
											<div class="widget">
												<div class="widget-head">
													<div class="pull-left">Productos</div>
													<div class="widget-icons pull-right">
														<a href="#" class="wminimize"><i
															class="fa fa-chevron-up"></i></a> <a href="#" class="wclose"><i
															class="fa fa-times"></i></a>
													</div>
													<div class="clearfix"></div>
												</div>
												<div class="widget-content referrer">
													<table
														class="table table-striped table-bordered table-hover">
														<thead>
															<tr>
																<th>Descripcion</th>
																<th>Presentacion</th>
																<th>Precio Unitario</th>
																<th>Controles</th>
															</tr>
														</thead>
														<tbody id="tblProductos">
														</tbody>
													</table>
													<div class="widget-foot"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="widget-foot"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>

<!-- Matter ends -->
