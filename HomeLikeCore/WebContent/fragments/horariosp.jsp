<script src="js/homelike/horariosp.js"></script>
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
		<i class="fa fa-clock-o"></i> Horarios
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
	<div class="container" id="horarios">
		<div class="row">
			<div class="col-md-12">
				<!-- Widget starts -->
				<div class="widget">
					<div class="widget-head">
						<div class="pull-left">Horarios</div>
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
										<div class="col-md-8">
											<!-- Widget starts -->
											<div class="widget">
												<div class="widget-head">
													<div class="pull-left">Seleccione horarios</div>
													<div class="widget-icons pull-right">
														<a href="#" class="wminimize"><i
															class="fa fa-chevron-up"></i></a> <a href="#" class="wclose"><i
															class="fa fa-times"></i></a>
													</div>
													<div class="clearfix"></div>
												</div>
												<div class="widget-content">
													<table
														class="table table-striped table-bordered table-hover">
														<thead>
															<tr>
																<th>Dia</th>
																<th>Abrimos</th>
																<th>Cerramos</th>
																<th>Controles</th>
															</tr>
														</thead>
														<tbody id="tblHorarios">
															<tr>
																<td>
																	Lunes - Viernes
																</td>
																<td>
																	<div id="datetimepicker1" class="input-append input-group dtpicker">
																		<input data-format="hh:mm" class="form-control" type="text" id="iLunes" placeholder="">
																		<span class="input-group-addon add-on">
																			<i class="fa fa-clock-o" data-time-icon="fa fa-clok-o" data-date-icon="fa fa-clock-o"></i>
																		</span>
																	</div>
																</td>
																<td>
																	<div id="datetimepicker2" class="input-append input-group dtpicker">
																		<input data-format="hh:mm" class="form-control" type="text" id="fLunes" placeholder="">
																		<span class="input-group-addon add-on">
																			<i class="fa fa-clock-o" data-time-icon="fa fa-clock-o" data-date-icon="fa fa-clock-o"></i>
																		</span>
																	</div>
																</td>
																<td id="controles1">
																	<button type="submit" class="btn btn-sm btn-info" name="btnLunes" id="btnLunes" disabled>Guardar</button>
																</td>
															</tr>
															<tr>
																<td>
																	Sabado
																</td>
																<td>
																	<div id="datetimepicker3" class="input-append input-group dtpicker">
																		<input data-format="hh:mm" class="form-control" type="text" id="iSabado" placeholder="">
																		<span class="input-group-addon add-on">
																			<i class="fa fa-clock-o" data-time-icon="fa fa-clok-o" data-date-icon="fa fa-clock-o"></i>
																		</span>
																	</div>
																</td>
																<td>
																	<div id="datetimepicker4" class="input-append input-group dtpicker">
																		<input data-format="hh:mm" class="form-control" type="text" id="fSabado" placeholder="">
																		<span class="input-group-addon add-on">
																			<i class="fa fa-clock-o" data-time-icon="fa fa-clock-o" data-date-icon="fa fa-clock-o"></i>
																		</span>
																	</div>
																</td>
																<td id="controles2">
																	<button type="submit" class="btn btn-sm btn-info" name="btnSabado" id="btnSabado" disabled>Guardar</button>
																</td>
															</tr>
															<tr>
																<td>
																	Domingo
																</td>
																<td>
																	<div id="datetimepicker5" class="input-append input-group dtpicker">
																		<input data-format="hh:mm" class="form-control" type="text" id="iDomingo" placeholder="">
																		<span class="input-group-addon add-on">
																			<i class="fa fa-clock-o" data-time-icon="fa fa-clok-o" data-date-icon="fa fa-clock-o"></i>
																		</span>
																	</div>
																</td>
																<td>
																	<div id="datetimepicker6" class="input-append input-group dtpicker">
																		<input data-format="hh:mm" class="form-control" type="text" id="fDomingo" placeholder="">
																		<span class="input-group-addon add-on">
																			<i class="fa fa-clock-o" data-time-icon="fa fa-clock-o" data-date-icon="fa fa-clock-o"></i>
																		</span>
																	</div>
																</td>
																<td id="controles3">
																	<button type="submit" class="btn btn-sm btn-info" name="btnDomingo" id="btnDomingo" disabled>Guardar</button>
																</td>
															</tr>
														</tbody>
													</table>
													<div class="widget-foot">
													</div>
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