<script src="js/homelike/hpedidosc.js"></script>
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
		<i class="fa fa-home"></i> Historico de Pedidos
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
	<div class="container" id="productos">
		<div class="row">
			<div class="col-md-12">
				<div class="widget">
					<div class="widget-head">
						<div class="pull-left">Data Tables</div>
						<div class="widget-icons pull-right">
							<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
							<a href="#" class="wclose"><i class="fa fa-times"></i></a>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="widget-content">
						<div class="padd">
							<div class="page-tables">
								<!-- Table -->
								<div class="table-responsive">
									<div id="data-table_wrapper" class="dataTables_wrapper"
										role="grid">
										<div class="dataTables_length" id="data-table_length">
											<label>Show <select aria-controls="data-table"
												size="1" name="data-table_length">
													<option selected="selected" value="10">10</option>
													<option value="25">25</option>
													<option value="50">50</option>
													<option value="100">100</option>
											</select> entries
											</label>
										</div>
										<div id="data-table_filter" class="dataTables_filter">
											<label>Search: <input aria-controls="data-table"
												type="text">
											</label>
										</div>
										<table style="width: 100%;" aria-describedby="data-table_info"
											class="dataTable" id="data-table" cellpadding="0"
											cellspacing="0" border="0" width="100%">
											<thead>
												<tr role="row">
													<th
														aria-label="Rendering engine: activate to sort column descending"
														aria-sort="ascending" style="width: 179px;" colspan="1"
														rowspan="1" aria-controls="data-table" tabindex="0"
														role="columnheader" class="sorting_asc">#
													</th>
													<th aria-label="Browser: activate to sort column ascending"
														style="width: 268px;" colspan="1" rowspan="1"
														aria-controls="data-table" tabindex="0"
														role="columnheader" class="sorting">Direccion
													</th>
													<th
														aria-label="Platform(s): activate to sort column ascending"
														style="width: 243px;" colspan="1" rowspan="1"
														aria-controls="data-table" tabindex="0"
														role="columnheader" class="sorting">Proveedor
													</th>
													<th
														aria-label="Engine version: activate to sort column ascending"
														style="width: 151px;" colspan="1" rowspan="1"
														aria-controls="data-table" tabindex="0"
														role="columnheader" class="sorting">Total
													</th>
													<th
														aria-label="CSS grade: activate to sort column ascending"
														style="width: 114px;" colspan="1" rowspan="1"
														aria-controls="data-table" tabindex="0"
														role="columnheader" class="sorting">Fecha
													</th>
												</tr>
											</thead>
											<tbody id="tblPedidos" aria-relevant="all" aria-live="polite" role="alert">
											</tbody>
										</table>
										<div id="data-table_info" class="dataTables_info">Showing
											1 to 10 of 57 entries</div>
										<div id="data-table_paginate"
											class="dataTables_paginate paging_full_numbers">
											<a id="data-table_first" tabindex="0"
												class="first paginate_button paginate_button_disabled">First</a>
											<a id="data-table_previous" tabindex="0"
												class="previous paginate_button paginate_button_disabled">Previous</a>
											<span> <a tabindex="0" class="paginate_active">1</a> <a
												tabindex="0" class="paginate_button">2</a> <a tabindex="0"
												class="paginate_button">3</a> <a tabindex="0"
												class="paginate_button">4</a> <a tabindex="0"
												class="paginate_button">5</a>
											</span> <a id="data-table_next" tabindex="0"
												class="next paginate_button">Next</a> <a
												id="data-table_last" tabindex="0"
												class="last paginate_button">Last</a>
										</div>
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