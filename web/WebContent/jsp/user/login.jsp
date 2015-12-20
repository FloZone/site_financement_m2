<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:layout>
	<jsp:attribute name="head">
		Se connecter
	</jsp:attribute>

	<jsp:body>
		<div class="container">
		
			<div class="page-header">
				<h1>Connexion</h1>
			</div>
			
			<c:if test="${not empty message}">
				<div class="row alert alert-success">
					<p>${message}</p>
				</div>
			</c:if>
	
			<div class="row">
				<div class="col-md-4 jumbotron text-center">
					<p>Pas encore de compte ? Créez le aujourd'hui.</p>
					<a class="btn-lg btn-success center"
							href="<c:url value="/User/SignUp" />">Créer un compte</a>
				</div>
		
				<div class="col-md-4 col-md-offset-2">
					<c:if test="${not empty param.loginError}">
						<div class="alert alert-danger" role="alert">
							<strong>Erreur</strong>, le couple login/mot de passe est incorrect
						</div>
					</c:if>
				
					<form action="${pageContext.request.contextPath}/User/Login"
							method="post">
						<div class="form-group">
							<label for="login">Login :</label>
							<input id="login" class="form-control" type="text" name="login"
									value="${user.login}" />
						</div>
							
						<div class="form-group">
							<label for="password">Mot de passe :</label>
							<input id="password" class="form-control" type="password"
									name="password" />
						</div>
					
						<button class="btn btn-primary" type="submit">Se connecter</button><br><br>
					</form>
				</div>
			</div>
		</div>
	</jsp:body>
</t:layout>