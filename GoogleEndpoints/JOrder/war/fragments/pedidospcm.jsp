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
			<h4 class="modal-title">Confirmacion de Pedido</h4>
		</div>
		<div class="modal-body">
			<div class="admin-form">
				<div class="container">
					<div class="row">
						<div class="col-lg-12">
							<div class="widget wred">
								<div class="widget-content">
									<div class="padd">
										<form class="form-horizontal" role="form">
											<div class="form-group">
												<label class="col-lg-3 control-label">Comentarios</label>
												<div class="col-lg-9">
													<textarea id="comentario" class="form-control"
														rows="5" placeholder="Comentario entrega proveedor ..."></textarea>
												</div>
											</div>
										</form>
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
				aria-hidden="true">Cancelar</button>
			<input type="hidden" id="id"/>
			<button type="button" class="btn btn-primary" id="btnenviar">Confirmar
				Pedido</button>
		</div>
	</div>
</div>
