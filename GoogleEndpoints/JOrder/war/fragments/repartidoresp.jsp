<script src="js/homelike/repartidoresp.js"></script>
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
		<i class="fa fa-truck"></i> Repartidores
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

<!-- Matter -->

<div class="matter">
	<div class="container" id="productos">
		<div class="row">
			<div class="col-md-12">
				<!-- Widget starts -->
				<div class="widget">
					<div class="widget-head">
						<div class="pull-left">Repartidores</div>
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
													<div class="pull-left">Nuevo Repartidor</div>
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
																<label class="control-label col-lg-4" for="name">Nombres</label>
																<div class="col-lg-8">
																	<input type="text" name="nombres" id="nombres"
																		class="form-control" />
																</div>
															</div>
															<div class="form-group">
																<label class="control-label col-lg-4" for="name">Apellido paterno</label>
																<div class="col-lg-8">
																	<input type="text" name="apaterno" id="apaterno"
																		class="form-control" />
																</div>
															</div>
															<div class="form-group">
																<label class="control-label col-lg-4" for="name">Apellido materno</label>
																<div class="col-lg-8">
																	<input type="text" name="amaterno" id="amaterno"
																		class="form-control" />
																</div>
															</div>
															<div class="form-group">
																<label class="control-label col-lg-4" for="name">Correo electronico</label>
																<div class="col-lg-8">
																	<input type="text" name="usuario" id="usuario"
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
													<div class="pull-left">Repartidores</div>
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
																<th>Nombres</th>
																<th>A.Paterno</th>
																<th>A.Materno</th>
																<th>Correo</th>
															</tr>
														</thead>
														<tbody id="tblRepartidores">
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

