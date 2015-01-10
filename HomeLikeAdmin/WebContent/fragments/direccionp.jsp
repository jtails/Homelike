<style>
#map-canvas {
	height: 250px;
	margin: 0px;
	padding: 0px
}
</style>
<script src="js/homelike/direccionp.js"></script>
<script src="https://apis.google.com/js/client.js?onload=init"></script>

  <!--<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-54276262-1', 'auto');
  ga('send', 'pageview');
  </script>-->

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">x</button>
			<h4 class="modal-title">Direccion</h4>
		</div>
		<div class="modal-body">
			<div class="admin-form">
				<div class="container">
					<div id="message"></div>
					<div class="row">
						<div class="col-lg-12">
							<div class="widget wred">
								<div class="widget-content">
									<a href="#" onclick='updateMap();'>Actualizar</a>
									<div id="map-canvas"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">Cerrar
			</button>
		</div>
	</div>
</div>
