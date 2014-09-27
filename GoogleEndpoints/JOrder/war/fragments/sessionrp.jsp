  <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-54276262-1', 'auto');
  ga('send', 'pageview');
  </script>
  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">

<input type="hidden" name="idServicio" id="idServicio" value="${sessionScope.proveedor.servicio.idServicio}"/>

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

			<!-- Search form -->
			<!-- Links -->
			<ul class="nav navbar-nav pull-right">
				<li class="dropdown pull-right"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#">
						<img id="profile" src="${sessionScope.proveedor.logo}" class="img-circle" style="width:20px;height:20px"/>
						<c:out value="${sessionScope.proveedor.usuario}"/> <b class="caret"></b>
				</a> <!-- Dropdown menu -->
					<ul class="dropdown-menu">
						<li><a href="/proveedor?opcion=logout"><i class="fa fa-sign-out"></i>
								Salir</a></li>
					</ul>
				</li>
				
				<li class="dropdown pull-right">
					<a href="panelp.jsp">
						<span class="fa fa-home">
						</span>
						Inicio
					</a>
				</li>

			</ul>
		</nav>

	</div>
</div>