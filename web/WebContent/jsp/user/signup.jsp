<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:layout>
	<jsp:attribute name="head">
		Créer un compte
	</jsp:attribute>

	<jsp:body>
		<div class="container">
		
			<div class="page-header">
				<h1>Inscription</h1>
			</div>
			
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<form action="${pageContext.request.contextPath}/User/SignUp"
							method="post">
							
						<c:if test="${not empty loginError}">
							<div class="form-group has-error has-feedback">
							<p class="text-danger">${loginError}</p>
						</c:if>
						<c:if test="${empty loginError}">
							<div class="form-group">
						</c:if>
							<label for="login">Login :</label>
							<input id="login" class="form-control" type="text" name="login" value="${user.login}" />
						</div>
							
						<c:if test="${not empty passwordError}">
							<div class="form-group has-error has-feedback">
							<p class="text-danger">${passwordError}</p>
						</c:if>
						<c:if test="${empty passwordError}">
							<div class="form-group">
						</c:if>
							<label for="password">Mot de passe :</label>
							<input id="password" class="form-control" type="password"
										name="password" />
						</div>
						
						<c:if test="${not empty confirmationError}">
							<div class="form-group has-error has-feedback">
							<p class="text-danger">${confirmationError}</p>
						</c:if>
						<c:if test="${empty confirmationError}">
							<div class="form-group">
						</c:if>
							<label for="password-confirmation">Confirmation du mot de passe :</label>
							<input id="password-confirmation" class="form-control"
										type="password" name="password-confirmation" />
						</div>
						
						<div class="form-group">
							<label for="firstname">Prénom :</label>
							<input id="firstname" class="form-control" type="text"
										name="firstname" value="${user.firstName}" />
						</div>
						
						<div class="form-group">
							<label for="lastname">Nom :</label>
							<input id="lastname" class="form-control" type="text"
										name="lastname" value="${user.lastName}" />
						</div>
						
						<div class="form-group">
							<label for="email">Email :</label>
							<input id="email" class="form-control" type="email" name="email" value="${user.email}" />
						</div>
					
						<button class="btn btn-primary" type="submit">Créer un compte</button><br><br>
					</form>
				</div>
			</div>
		</div>
	</jsp:body>
</t:layout>