<script src="js/homelike/clientesp.js"></script>
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
	height: 350px;
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
						<div class="pull-left">Mapa de clientes</div>
						<div class="widget-icons pull-right">
							<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
							<a href="#" class="wclose"><i class="fa fa-times"></i></a>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="widget-content">
						<div id="message"></div>
						
						<div id="map-canvas"></div>
						<div class="widget-foot">
							<div>
                    			<ul class="pagination">
                    			    <li><a href="#">Segmentacion</a></li>
                        			<li><a href="#" id="0">1</a></li>
                        			<li><a href="#" id="2">2</a></li>
                        			<li><a href="#" id="4">4</a></li>
                        			<li><a href="#" id="9">9</a></li>
                        			<li><a id='btnseg' name='btnseg' style='cursor: pointer'>Guardar</a></li>
                      			</ul>
							</div>
						</div>
					</div>
					<!-- Widget ends -->
					
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Matter ends -->


