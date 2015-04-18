<script src="js/custom.js"></script><!-- Necesario para expendir y contraer controles -->
<script src="js/homelike/pagosp.js"></script>
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
								<div class="container" id="container">
									<div id="message"></div>
									<div>
										<button class='btn btn-danger' id='btncorte' name='btncorte'>Realizar Corte</button>
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

