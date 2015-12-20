<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:layout>
	<jsp:attribute name="head">
		Administration
	</jsp:attribute>
	<jsp:attribute name="css">
  		<link href='<c:url value="/css/admin.css"/>' rel="stylesheet">
	</jsp:attribute>
	<jsp:body>
			<div class="container">	    
	    		<div class="row">
	    			<h2> Top 10 Projets </h2>
					<c:forEach items="${topProjects}" var="tp" varStatus="status">
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<!-- Rajouter un lien vers le projet -->
							<img src="<c:url value="${tp.project.image}" />"
									alt="${tp.project.title}"><br/>
							<!--  Suppression d'un projet en tant que top project -->
							<form action="${pageContext.request.contextPath}/Administration" method="post">
								<input type="hidden" name="topProject" value="${tp.id}"/>
								<c:out value="${tp.project.title}"></c:out>
								<button type="submit" class="btn btn-default btn-md" value="buttonDeleteTp" name="buttonAdmin">
								  <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								</button>
							</form>
						</div>
					</c:forEach>
				</div>
				<br/>
				<div class="row">
				 	<h2> Catégories </h2>
					<c:forEach items="${categories}" var="cat" varStatus="status">
			        	<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
			        		<!--  Rajouter un lien vers une liste de projets de cette catégorie -->
							<!--  Suppression d'une catégorie -->
							<form action="${pageContext.request.contextPath}/Administration" method="post">
								<input type="hidden" name="category" value="${cat.id}"/>
								<c:out value="${cat.name}"></c:out>
								<button type="submit" class="btn btn-default btn-md" value="buttonDeleteCat" name="buttonAdmin">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								</button>
							</form>
						</div>
					</c:forEach>
				</div>
				
				<div class="row">
					<h2> Nouvelle Catégorie </h2>
					<form action="${pageContext.request.contextPath}/Administration" method="post" id="before">
						<div class="form-group">
						    <label for="nameCat">Nom Catégorie :</label>
						    <input id="nameCat" class="form-control" type="text"
								class="form-control" name="nameCat" onblur="noName(this)" />
					  	</div>
					  	<button type="submit" class="btn btn-success" value="buttonAddCat" name="buttonAdmin">Valider</button>		  	
					</form>
				</div>
			</div>
			
			<script>
				function noName(name){
					if (name.value == "" || name.value == null){
						var element = $("#red");
						if(! element.length){
							$( "#before" ).before(
									'<div id="red" class="alert alert-danger" role="alert">Oups! Vous avez oublié de donner un nom à votre catégorie.</div>' 
								);
						}
					}
				}
			</script>
	</jsp:body>
</t:layout>