  <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-54276262-1', 'auto');
  ga('send', 'pageview');
  </script>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">x</button>
			<h4 class="modal-title">Comentarios</h4>
		</div>
		<div class="modal-body">
			<div class="admin-form">
				<div class="container">
					<div class="row">
						<div class="col-lg-12">
							<div class="widget wred">
								<div class="widget-content">
									<div class="pad">
										<div
											style="position: relative; overflow: hidden; width: auto; height: 350px;"
											class="slimScrollDiv">
											<div style="overflow: hidden; width: auto; height: 350px;"
												class="padd scroll-chat">
												<ul class="chats">
													<li class="by-me">
														<div class="avatar pull-left">
															<img src="img/logo_.png" alt="">
														</div>
														<div class="chat-content">
															<div class="chat-meta">
																Comentario cliente : <span class="pull-right" id="fechaHoraPedido"></span>
															</div>
															<div id="comentarioCliente"></div>
															<div class="clearfix"></div>
														</div>
													</li>
													<li class="by-other">
														<div class="avatar pull-right">
															<img src="${sessionScope.proveedor.logo}" alt="" style="width:50px;height:50px">
														</div>
														<div class="chat-content">
															<div class="chat-meta">
																Comentario entrega proveedor : <span class="pull-right" id="fechaHoraAceptacion"></span>
															</div>
															<div id="comentarioProveedor"></div>
															<div class="clearfix"></div>
														</div>
													</li>
													<li class="by-me">
														<div class="avatar pull-left">
															<img src="img/logo_.png" alt="">
														</div>
														<div class="chat-content">
															<div class="chat-meta">
																Comentario entrega cliente : <span class="pull-right" id="fechaHoraEntrega"></span>
															</div>
															<div id="comentarioEntregaCliente"></div>
															<div class="clearfix"></div>
														</div>
													</li>
													<li class="by-other">
														<div class="avatar pull-right">
															<img src="${sessionScope.proveedor.logo}" alt="" style="width:50px;height:50px">
														</div>
														<div class="chat-content">
															<div class="chat-meta">
																Comentario proveedor : <span class="pull-right"></span>
															</div>
															<div id="comentarioEntregaProveedor"></div>
															<div class="clearfix"></div>
														</div>
													</li>
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
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">Cerrar</button>
		</div>
	</div>
</div>
