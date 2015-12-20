<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:layout>
	<jsp:attribute name="head">
		${user.firstName} ${user.lastName}
	</jsp:attribute>

	<jsp:attribute name="css">
		<link href='<c:url value="/css/style.css"/>' rel="stylesheet">
	</jsp:attribute>

	<jsp:body>
		<div class="bloc">
			<div class="container">
				<h2> Mon compte </h2>
				
					<div class="row">
						<ul class="nav nav-tabs">
						  <li role="presentation" class="active lili" id="info"><a
								onclick="change('info')">Mes Infos</a></li>
						  <li role="presentation" class="lili" id="projet"><a
								onclick="change('projet')">Mes Projets</a></li>
						  <li role="presentation" class="lili" id="finance"><a
								onclick="change('finance')">Mes Dons</a></li>
						</ul>
						
						<div id="infos" class="onglet">
							<div class="page-header">
								<h1>
							  		${user.login} 
								  	<small> <span class="glyphicon glyphicon-user"
												aria-hidden="true"></span> ${user.firstName} ${user.lastName}</small>
								</h1>
							</div>
							<div class="col-lg-10 col-md-10 col-sm-10 col-xs-12">
								<form action="${pageContext.request.contextPath}/User/Change"
									method="post" class="">
									<input type="hidden" name="id" value="${user.id}" />
									
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
										<label for="email">
											<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> :</label>
											<input id="email" class="form-control" type="email" name="email"
													value="${user.email}" />
									</div>
								
									<button class="btn btn-info btn-lg" type="submit">Modifier</button>
								</form>
							</div>
						</div>
						
						<div id="projets" class="onglet" style="display: none">
							<div class="panel panel-info">
							  <div class="panel-heading">Mes projets</div>
						  		<table class="table">
						  			<fmt:setLocale value="fr_FR" />
						  			<thead>
							  			<tr>
							  				<th>Titre</th>
							  				<th class = "hidden-xs">Montant demandé</th>
							  				<th>Financement</th>
							  				<th class = "hidden-xs">Echéance</th>
							  			</tr>
							  		</thead>
						  			
								  	<c:forEach items="${user.projects}" var="p">
									    <tr>
									    	<c:url value="/Project/Show" var="currentProjectUrl">
												<c:param name="id" value="${p.id}" />
											</c:url>
									    	<th><a href="${currentProjectUrl}">${p.title}</a></th>
									    	<td class = "hidden-xs">${p.requiredAmount} €</td>
									    	<td><fmt:formatNumber value="${p.totalDonations * 100 / p.requiredAmount}" maxFractionDigits="1"/> %</td>
									    	<td class = "hidden-xs">
												<fmt:formatDate value="${p.limitDate}" dateStyle="full" />
											</td>
									    	<td>
									    		<c:url value="/Project/Edit" var="urlProject">
									    			<c:param name="id">${p.id}</c:param>
									    		</c:url>
									    		<a href="${urlProject}">
									    			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									    		</a>
									    	</td>
									    </tr>
								    </c:forEach>
								 </table>
							</div>							
						</div>
						
						<div id="finances" class="onglet" style="display: none">
							<div class="panel panel-info">
							  <div class="panel-heading">Mes Financements</div>
						  		<table class="table">
								  	<c:forEach items="${donationsAndCompensations}" var="dc">
									    <tr>
									    	<c:url value="/Project/Show" var="currentProjectUrl">
											<c:param name="id" value="${dc.key.project.id}" />
										</c:url>
									    	<th><a href="${currentProjectUrl}">${dc.key.project.title}</a></th>
									    	<td>${dc.key.amount}€</td>
									    	<td class = "hidden-xs"><fmt:formatDate value="${dc.key.date}" dateStyle="full" /></td>
									    	<td class = "hidden-xs">
									    		<c:if test="${empty dc.value}">
									    			Pas de compensation pour les pinces
									    		</c:if>
 											   	<c:if test="${not empty dc.value}">
									    			${dc.value.description}
									    		</c:if>
									    	</td>
									    </tr>
								    </c:forEach>
								 </table>
							</div>
						</div>
				</div>
				
			</div>
		</div>
	
		<script>
			function change(li) {
				var element = document.getElementById(li + 's');
				for (k = 0; k < document.getElementsByClassName("onglet").length; k++) {
					document.getElementsByClassName("onglet")[k].style.display = 'none';
				}
				element.style.display = 'block';
				for (i = 0; i < document.getElementsByClassName("lili").length; i++) {
					if (document.getElementsByClassName("lili")[i].id == li) {
						document.getElementsByClassName("lili")[i].className = "active lili";
					} else {
						document.getElementsByClassName("lili")[i].className = "lili";
					}
				}
			}
		</script>
	</jsp:body>
</t:layout>