<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:layout>
	<jsp:attribute name="head">
		Accueil
	</jsp:attribute>
	
	<jsp:attribute name="css">
		<link href='<c:url value="/css/style.css"/>' rel="stylesheet">
	    <link href='<c:url value="/css/styleSlide.css"/>' rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:body>
		<div>
			<!-- Main View -->
			<div class="boite">
				<section class="restricted-width news">
			      <section id="news-demo">
			      	<!--  Article "page d'accueil" -->
			        <article>
			          <div class="text-content">
			            <h3>Bienvenue!</h3>
			            <p>Vous voulez participer à des projets innovants? Aider à entretenir un peu de magie? Vous êtes au bon endroit.<br/>
			            	Voici les dix projets que nous voulons vous présenter...</p>
			          </div>
			          <div class="image-content"><img src='<c:url value="/images/project.jpg"/>' alt="demo1_1"></div>
			        </article>
			        <!-- Les Top Projects -->
			       	<c:forEach items="${topProjects}" var="tp" varStatus="status">
				        <article>
				          <div class="text-content">
				            <h3><c:out value="${tp.project.title}"></c:out></h3>
				            <p><c:out value="${tp.description}"></c:out></p> 
				            <c:url value="/Project/Show" var="currentProjectUrl"><c:param name="id" value="${tp.project.id}" /></c:url>
				            <a href="${currentProjectUrl}" class="button-link read-more">Voir le Projet</a>
				          </div>
				          <div class="image-content"><img src="<c:url value="${tp.project.image}" />" alt="demo1_1"></div>
				        </article>
				     </c:forEach>
			      </section>
			    </section>
			</div>
		</div>
    	<script src='<c:url value="/js/scriptsSlide.js"/>' type="text/javascript"></script>
	</jsp:body>
</t:layout>