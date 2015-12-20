<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="css" fragment="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
	<jsp:attribute name="head">
		Administration
	</jsp:attribute>
	
	<jsp:attribute name="css">
		<c:url value="/css/style.css" var="administrationCssUrl" />
  		<link href="${administrationCssUrl}" rel="stylesheet">
	</jsp:attribute>
	
	<jsp:body>
		<div class="container bloc">
			<div class="page-header">
				<h1>Administration</h1>
			</div>		
		
			<div class="row">
				<div class="col-sm-3">
					<ul class="list-group">
						<li class="list-group-item">
							<c:url value="/Administration/TopProject" var="topProjectCategoryUrl" />
							<a href="${topProjectCategoryUrl}">Projets à l'affiche</a>
						</li>
						<li class="list-group-item">
							<c:url value="/Administration/Category" var="administrationCategoryUrl" />
							<a href="${administrationCategoryUrl}">Catégories</a>
						</li>
						<li class="list-group-item">
							<c:url value="/Administration/User" var="administrationUserUrl" />
							<a href="${administrationUserUrl}">Utilisateurs</a>
						</li>
					</ul>
				</div>
				
				<div class="col-sm-9">
					<jsp:doBody />
				</div>
			</div>
		</div>
	</jsp:body>
</t:layout>