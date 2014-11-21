<script src="js/homelike/proveedoresc.js"></script>
<script src="https://apis.google.com/js/client.js?onload=init"></script>
  <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-54276262-1', 'auto');
  ga('send', 'pageview');
  </script>

<style>
#map-canvas {
	height: 250px;
	margin: 0px;
	padding: 0px
}
</style>


<!-- Matter -->

<div class="matter">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<!-- Widget -->
				<div class="widget">
					<!-- Widget title -->
					<div class="widget-head">
						<div class="pull-left">Nuevo pedido </div>
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
											<!-- Widget -->
											<div class="widget">
												<!-- Widget title -->
												<div class="widget-head">
													<div class="pull-left">Seleccione proveedor</div>
													<div class="widget-icons pull-right">
														<a href="#" class="wminimize"><i
															class="fa fa-chevron-up"></i></a> <a href="#" class="wclose"><i
															class="fa fa-times"></i></a>
													</div>
													<div class="clearfix"></div>
												</div>

												<div class="widget-content">
													<!-- Widget content -->

													<!-- Below class "scroll-chat" will add nice scroll bar. It uses Slim Scroll jQuery plugin. Check custom.js for the code -->
													<div
														style="position: relative; overflow: hidden; width: auto; height: 250px;"
														class="slimScrollDiv">
														<div style="overflow: hidden; width: auto; height: 250px;"
															class="padd scroll-chat">
															<ul class="chats" id="proveedores">

																<!-- Chat by us. Use the class "by-me". -->
															</ul>

														</div>
														<div
															style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.3); width: 5px; position: absolute; top: 0px; border-radius: 7px; z-index: 99; right: 1px; height: 272.829px; display: none; opacity: 0.4;"
															class="slimScrollBar"></div>
														<div
															style="width: 5px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: none repeat scroll 0% 0% rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"
															class="slimScrollRail"></div>
													</div>
												</div>
											</div>
										</div>

										<div class="col-md-8">

											<!-- Widget -->
											<div class="widget">
												<!-- Widget head -->
												<div class="widget-head">
													<div class="pull-left">Localizacion de Proveedores</div>
													<div class="widget-icons pull-right">
														<a href="#" class="wminimize"><i
															class="fa fa-chevron-up"></i></a> <a href="#" class="wclose"><i
															class="fa fa-times"></i></a>
													</div>
													<div class="clearfix"></div>
												</div>

												<!-- Widget content -->
												<div class="widget-content">
														<div id="map-canvas"></div>
												</div>
												<!-- Widget ends -->

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


