$(document).ready(function(){
	$("#menupedidos").click(function(){
		$("#contenido").load("fragments/pedidosc.jsp");
	});
	$("#menuhpedidos").click(function(){
		$("#contenido").load("fragments/hpedidosc.jsp");
	});
});