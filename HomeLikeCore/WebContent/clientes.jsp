<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Rectangle Events</title>
    <style>
      html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
    </style>
	
	<script>
		</script>
   	<script src="https://apis.google.com/js/client.js?onload=init"></script>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>	

  <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-54276262-1', 'auto');
  ga('send', 'pageview');
  </script>

  </head>
  <body>
  
  	<output id="outputLog">texto</output>
  	<form action="clientes" method="POST" onsubmit="return false;">
  		<table>
  			<tr>
  				<td>
  					<table>
  						<tr><td>Correo : </td><td><input type="text" name="correo" id="correo"/></td></tr>
  						<tr><td>Telefono : </td><td><input type="text" name="telefono" id="telefono"/></td></tr>
  					
  						<tr><td>Calle :</td><td><input type="text" name="calle" id="calle"/></td></tr>
  						<tr><td>Numero Ext :</td><td><input type="text" name="numeroe" id="numeroe"/></td></tr>
  						<tr><td>Numero Int :</td><td><input type="text" name="numeroi" id="numeroi"/></td></tr>
  						<tr><td>Colonia :</td><td><input type="text" name="colonia" id="colonia"/></td></tr>
  						<tr><td>Delegacion </td><td><input type="text" name="delegacion" id="delegacion"/></td></tr>
  						<tr><td>CP :</td><td><input type="text" name="cp" id="cp"/></td></tr>
  						<tr><td>Estado :</td><td><input type="text" name="estado" id="estado"/></td></tr>
  						<tr><td>Pais : </td><td><input type="text" name="pais" id="pais"/></td></tr>
  						<tr><td>Entre Calle : </td><td><input type="text" name="calle1" id="calle1"/></td></tr>
  						<tr><td>Entre Calle : </td><td><input type="text" name="calle2" id="calle2"/></td></tr>
  						<tr><td>Referencia : </td><td><input type="text" name="referencia1" id="referencia1"/></td></tr>
  						<tr><td>Referencia : </td><td><input type="text" name="referencia2" id="referencia2"/></td></tr>
  					</table>
  				</td>
  				<td>
  					<table>					
  						<tr><td>Alias :</td><td><input type="text" name="alias" id="alias"/></td></tr>
  						<tr><td>Plataforma (Android,iOS,Windows)</td><td><input type="text" name="plataforma" id="plataforma"/></td></tr>
  						<tr><td>Gcm ID Dispositivo :</td><td><input type="text" name="gcmid" id="gcmid"/></td></tr>
  						<tr><td>IMEI Dispositivo :</td><td><input type="text" name="imei" id="imei"/></td></tr>
  						<tr><td>Modelo Dispositivo :</td><td><input type="text" name="modelo" id="modelo"/></td></tr>
  						<tr><td>Tipo Dispositivo :</td><td><input type="text" name="tipo" id="tipo"/></td></tr>
  						<tr><td colspan="2" align="right"><input type="button" id="btnvalidar" name="btnvalidar" value="Consultar"/></td></tr>
  					</table>
  				</td>
  				<td>
  					<table>
  						<tr><td>latitud</td><td><input type="text" name="latitud" id="latitud"/></td></tr>
  						<tr><td>longitud</td><td><input type="text" name="longitud" id="longitud"/></td></tr>
  						<tr>
  							<td colspan="2" align="right">
  								<input type="hidden" name="opcion" value="agregar"/>
  								<input type="submit" id="btnenviar" name="btnenviar" value="Terminar"/>
  							</td>
  						</tr>
  					</table>
  				</td>
  			</tr>
  		</table>
  	</form>
  
    <div id="map-canvas"></div>
    
    <script>
  	
  	</script>
  	
  </body>
</html>


