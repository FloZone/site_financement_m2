<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Liste de tous les projets -->
<t:layout>
	<jsp:attribute name="head">
		Rechercher des projets
	</jsp:attribute>
	
	<jsp:attribute name="css">
		<link href='<c:url value="/css/style.css"/>' rel="stylesheet">
	</jsp:attribute>
		
	<jsp:body>
		<fmt:setLocale value="fr_FR" />
		<div class="container bloc">
			<div class="row">
			<jsp:useBean id="now" class="java.util.Date" />
			<!-- Filters & research -->
				<div class="col-lg-3 col-md-4 col-sm-12 col-xs-12">
						<form action="${pageContext.request.contextPath}/Project/ShowAll"
								method="post">
								
						<!-- Search field -->
						<div class="panel panel-info">
							<div class="panel-heading">
						    	<h4 class="panel-title">Recherche</h4>
						  	</div>
						  	<div class="panel-body">
								<input id="search" class="form-control" type="text"
										placeholder="Entrez un mot-clef" name="search">
							</div>
						</div>
							
						<!-- Catégories -->
						<c:if test="${fn:length(categories) > 0}">
							<div class="panel panel-info">
								<div class="panel-heading">
							    	<h4 class="panel-title">Catégories</h4>
							  	</div>
							  	<ul class="list-group">
									<c:forEach items="${categories}" var="c">
    									<li class="list-group-item">
    										<input type="checkbox" name="<c:out value="${c.id}"></c:out>">
											<c:out value="${c.name}"></c:out>
										</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
						

						<c:if test="${fn:length(categories) == 0}">
							<h4>Aucune catégorie</h4>
						</c:if>
							
						<!-- Bouton -->
						<div class="form-group">
							<button type="submit" class="btn btn-primary">Rechercher</button>
						</div>
						</form>
				</div>
				<!-- projects viewer -->
				<div class="col-lg-9 col-md-8 col-sm-12 col-xs-12">
					<div class="row">
						<c:if test="${fn:length(projects) > 0}">
							<c:forEach items="${projects}" var="p">
								<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
									<a href="${pageContext.request.contextPath}/Project/Show?id=${p.id}" class="noDeco">
										<div class="panel panel-primary taille">
											<div class="panel-heading">
										    	<h2 class="panel-title"><c:out value="${p.title}"></c:out></h2>
										  	</div>
										  	<div class="panel-body">
										  		<div class="row">
										  			<div class="col-lg-6 col-md-6 coœl-sm-6 col-xs-12">
														<img alt="<c:out value="${p.title}"></c:out>"
																		src="<c:url value="${p.image}" />" class="img-responsive">
													</div>
													<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 center">
														<h2>
															<span class="label label-success">
																<fmt:formatNumber value="${p.totalDonations * 100 / p.requiredAmount}" maxFractionDigits="2"/> %
															</span>
														</h2>
														<br>
														A financer avant le
														<h5><fmt:formatDate value="${p.limitDate}" dateStyle="full" />!</h5>
													</div>
													<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					                              		<p>Créé le 
					                              			<fmt:formatDate value="${p.creationDate}" dateStyle="full" />
														</p>
													</div>
										  		</div>
										  	</div>
										</div>
									</a>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${fn:length(projects) == 0}">
							<h4>Aucun projet trouvé...</h4>
						</c:if>
					</div>
				</div>
			</div>
		</div>	
	</jsp:body>
</t:layout>