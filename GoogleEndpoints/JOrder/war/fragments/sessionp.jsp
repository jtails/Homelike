  <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-54276262-1', 'auto');
  ga('send', 'pageview');
  </script>
  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:if test="${not empty sessionScope.isLoginI}">
	<script>window.location.replace("/registrogp.jsp");</script>
 </c:if>
 <c:if test="${empty sessionScope.isLoginP}">
	<script>window.location.replace("/loginp.jsp");</script>
 </c:if>
 
<input type="hidden" name="idProveedor" id="idProveedor" value="${sessionScope.proveedor.idProveedor}"/>
<input type="hidden" name="idServicio" id="idServicio" value="${sessionScope.proveedor.servicio.idServicio}"/>
<input type="hidden" name="latitud" id="latitud" value="${sessionScope.proveedor.latitud}"/>
<input type="hidden" name="longitud" id="longitud" value="${sessionScope.proveedor.longitud}"/>
<input type="hidden" name="nelatitud" id="nelatitud" value="${sessionScope.proveedor.nelatitud}"/>
<input type="hidden" name="nelongitud" id="nelongitud" value="${sessionScope.proveedor.nelongitud}"/>
<input type="hidden" name="swlatitud" id="swlatitud" value="${sessionScope.proveedor.swlatitud}"/>
<input type="hidden" name="swlongitud" id="swlongitud" value="${sessionScope.proveedor.swlongitud}"/>

<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">

	<div class="conjtainer">
		<!-- Menu button for smallar screens -->
		<div class="navbar-header">
			<button class="navbar-toggle btn-navbar" type="button"
				data-toggle="collapse" data-target=".bs-navbar-collapse">
				<span>Menu</span>
			</button>
			<!-- Site name for smallar screens -->
			<a href="index.html" class="navbar-brand hidden-lg">HomeLike</a>
		</div>



		<!-- Navigation starts -->
		<nav class="collapse navbar-collapse bs-navbar-collapse"
			role="navigation">
			
			<!-- Links -->
			<ul class="nav navbar-nav pull-right">
				<li class="dropdown dropdown-big pull-left"><a href="soportep.jsp">
					<i class="fa fa-briefcase"></i>
						Soporte</a> <!-- Dropdown -->
				</li>
			
				<li class="dropdown pull-right"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#">
						<img id="profile" class="img-circle" style="width:20px;height:20px"/>
						<c:out value="${sessionScope.proveedor.usuario}"/> <b class="caret"></b>
						
				</a> <!-- Dropdown menu -->
				
					<ul class="dropdown-menu">
						<li><a href="/proveedor?opcion=logout"><i class="fa fa-sign-out"></i>
								Salir</a></li>
					</ul>
				</li>

			</ul>
		</nav>

	</div>
</div>