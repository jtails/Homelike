<script src="js/custom.js"></script><!-- Necesario para expendir y contraer controles -->
<script src="js/homelike/proveedor.js"></script>
<script src="https://apis.google.com/js/client.js?onload=init"></script>

  <!--<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-54276262-1', 'auto');
  ga('send', 'pageview');
  </script>-->

<!-- Page heading -->
<div class="page-head">
	<h2 class="pull-left">
		<i class="fa fa-male"></i> Proveedor
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
	<div class="container" id="proveedor">
		<div class="row">
			<div class="col-md-12">
				<!-- Widget starts -->
				<div class="widget">
					<div class="widget-head">
						<div class="pull-left" id='nombrep'></div>
						<div class='rateit' data-rateit-value='0' data-rateit-resetable='false' data-rateit-readonly='true' data-rateit-step='1' id='calificacions'></div>
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
													<div class="pull-left">Datos Generales</div>
													<div class="widget-icons pull-right">
														<a href="#" class="wminimize"><i
															class="fa fa-chevron-down"></i></a> 
														<a href="#" class="wclose"><i
															class="fa fa-times"></i></a>
													</div>
													<div class="clearfix"></div>
												</div>
												<div class="widget-content" style="display: none;">
													<ul class='activity' id='direccion'>
														
			                  						</ul>
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
															class="fa fa-chevron-down"></i></a> <a href="#" class="wclose"><i
															class="fa fa-times"></i></a>
													</div>
													<div class="clearfix"></div>
												</div>
												<div class="widget-content referrer" style="display: none;">
													<table
														class="table table-striped table-bordered table-hover">
														<thead>
															<tr>
																<th>Descripcion</th>
																<th>Presentacion</th>
																<th>Precio Unitario</th>
																<th>Status</th>
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
		
		
		
		<div class="row">
			<div class="col-md-12">
				<div class="widget">
					<div class="widget-head">
						<div class="pull-left">Pedidos</div>
						<div class="widget-icons pull-right">
							<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
							<a href="#" class="wclose"><i class="fa fa-times"></i></a>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="widget-content">
						<div class="padd">
							<div id="message2"></div>
							<div class="page-tables">
								<!-- Table -->
								<div class="table-responsive">
									<div id="data-table_wrapper" class="dataTables_wrapper"
										role="grid">
										<table style="width: 100%;" aria-describedby="data-table_info"
											class="dataTable" id="data-table" cellpadding="0"
											cellspacing="0" border="0" width="100%">
											<thead>
												<tr role="row">
													<th
														aria-label="Rendering engine: activate to sort column descending"
														aria-sort="ascending" style="width: 50px;" colspan="1"
														rowspan="1" aria-controls="data-table" tabindex="0"
														role="columnheader" class="sorting_asc">#
													</th>
													<th aria-label="Browser: activate to sort column ascending"
														style="width: 268px;" colspan="1" rowspan="1"
														aria-controls="data-table" tabindex="0"
														role="columnheader" class="sorting">Direccion de envio
													</th>
													<th
														aria-label="Platform(s): activate to sort column ascending"
														style="width: 50px;" colspan="1" rowspan="1"
														aria-controls="data-table" tabindex="0"
														role="columnheader" class="sorting">Total
													</th>
													<th
														aria-label="Engine version: activate to sort column ascending"
														style="width: 151px;" colspan="1" rowspan="1"
														aria-controls="data-table" tabindex="0"
														role="columnheader" class="sorting">Productos
													</th>
													<th
														aria-label="CSS grade: activate to sort column ascending"
														style="width: 114px;" colspan="1" rowspan="1"
														aria-controls="data-table" tabindex="0"
														role="columnheader" class="sorting">Comentarios
													</th>
													<th
														aria-label="CSS grade: activate to sort column ascending"
														style="width: 114px;" colspan="1" rowspan="1"
														aria-controls="data-table" tabindex="0"
														role="columnheader" class="sorting">Fecha Pedido
													</th>
													<th
														aria-label="CSS grade: activate to sort column ascending"
														style="width: 114px;" colspan="1" rowspan="1"
														aria-controls="data-table" tabindex="0"
														role="columnheader" class="sorting">Status
													</th>
												</tr>
											</thead>
											<tbody id="tblPedidos" aria-relevant="all" aria-live="polite" role="alert">
											</tbody>
										</table>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="widget-foot">
						<!-- Footer goes here -->
					</div>
				</div>
			</div>
		</div>
		
		
		
	</div>
	
</div>

<!-- Matter ends -->

