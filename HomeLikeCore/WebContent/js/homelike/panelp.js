$(document).ready(function(){
	$("#menupedidos").click(function(){
		$("#contenido").load("fragments/pedidosp.jsp");
	});
	$("#menuproductos").click(function(){
		$("#contenido").load("fragments/productosp.jsp");
	});
	$("#menuhorarios").click(function(){
		$("#contenido").load("fragments/horariosp.jsp");
	});
	$("#menuhpedidos").click(function(){
		$("#contenido").load("fragments/hpedidosp.jsp");
	});
	$("#menuhome").click(function(){
		$("#contenido").load("fragments/clientesp.jsp");
	});
	$("#menuestadisticas").click(function(){
		$("#contenido").load("fragments/estadisticasp.jsp");
	});
	$("#menucomentarios").click(function(){
		$("#contenido").load("fragments/comentariosp.jsp");
	});
});
