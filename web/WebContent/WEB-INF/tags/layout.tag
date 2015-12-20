<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="css" fragment="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title><jsp:invoke fragment="head" /></title>
	<jsp:invoke fragment="css" />
	
	<c:url value="/css/bootstrap.min.css" var="bootstrapCssUrl" />
	<link href="${bootstrapCssUrl}" rel="stylesheet" />
</head>

<body>
	<c:url value="/js/jquery.min.js" var="jqueryUrl" />
	<script src="${jqueryUrl}"></script>
	
	<c:url value="/js/bootstrap.min.js" var="bootstrapJsUrl" />
	<script src="${bootstrapJsUrl}"></script>
	
	<c:url value="/js/underscore-min.js" var="underscoreUrl" />
	<script src="${underscoreUrl}"></script>
	
	<script>
		// On va utiliser les template de mustache.js, car ceux par défaut (ERB), sont incompatibles avec les jsp
		_.templateSettings = {
				  interpolate: /\{\{(.+?)\}\}/g,
				  variable: "vars"
		};
	</script>
	
	<div id="header">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					
					<c:url value="/Index" var="indexUrl" />
					<a class="navbar-brand" href="${indexUrl}">Financement</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li>
							<c:url value="/Project/ShowAll" var="showAllProjectUrl" />
							<a href="${showAllProjectUrl}">Tous les projets</a></li>

						<c:if test="${not empty userId}">
							<li>
								<c:url value="/Project/Create" var="createProjectUrl" />
								<a href="${createProjectUrl}">Créer un projet</a>
							</li>
						</c:if>
					</ul>

					<ul class="nav navbar-nav navbar-right">
						<c:if test="${empty userId}">
							<li>
								<c:url value="/User/Login" var="loginUrl" />
								<a href="${loginUrl}">Se connecter</a>
							</li>
						</c:if>

						<c:if test="${not empty userId}">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">${user.firstName} ${user.lastName}<span
									class="caret"></span></a>
								<ul class="dropdown-menu">
									<li>
										<c:url value="/User/Show" var="showCurrentUserUrl"></c:url>
										<a href="${showCurrentUserUrl}">Mon Compte</a>
									</li>
									<c:if test="${not empty admin }">
										<li>
											<c:url value="/Administration/TopProject" var="showCurrentUserUrl"></c:url>
											<a href="${showCurrentUserUrl}">Administration</a>
										</li>
									</c:if>
									
									<li role="separator" class="divider"></li>
									<li>
										<c:url value="/User/Logout" var="logoutUrl" />
										<form action="${logoutUrl}" method="post">
											<button class="btn btn-sm btn-danger btn-block" type="submit">Se déconnecter</button>
										</form>
									</li>
								</ul>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
	</div>

	<div id="body">
		<jsp:doBody />
	</div>

</body>
</html>