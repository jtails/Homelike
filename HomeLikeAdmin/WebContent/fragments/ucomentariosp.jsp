<script src="js/homelike/ucomentariosp.js"></script>
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
		<i class="fa fa-comments-o"></i> Comentarios
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
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<!-- Widget starts -->
				<div class="widget">
					<div class="widget-head">
						<div class="pull-left">Ultimos 20 comentarios</div>
						<div class="widget-icons pull-right">
							<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
							<a href="#" class="wclose"><i class="fa fa-times"></i></a>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="widget-content">
						<div class="pad">
						<div id="message"></div>
							<div
								style="position: relative; overflow: hidden; width: auto; height: 350px;"
								class="slimScrollDiv">
								<div style="overflow: hidden; width: auto; height: 350px;"
									class="padd scroll-chat">
									<ul class="chats" id="comentariosp">
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
						<div class="widget-foot"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>