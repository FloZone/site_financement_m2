<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Création d'un projet -->
<t:layout>
	<jsp:attribute name="head">
		Modification d'un projet
	</jsp:attribute>

	<jsp:attribute name="css"></jsp:attribute>

	<jsp:body>
		<div class="container">
		
			<div class="page-header">
				<h1>Modifier un projet</h1>
			</div>
			
			<div class="row">
				<c:if test="${param.message == '1'}">
					<p class="bg-success">Modification du projet effectuée.</p>
				</c:if>
			 	<c:if test="${param.message == '2'}">
			 		<p class="bg-danger">Erreur lors de la modification du projet.</p>
			 	</c:if>
			</div>	

			<div class="row">				
				<div class="col-md-8 col-md-offset-2 col-xs-12">
				
					<c:url value="/Project/Edit" var="actionUrl" />
					<form class="form-horizontal" action="${actionUrl}" method="post" id="form">
					
						<c:set var="labelCols" value="col-xs-12 col-sm-3 " />
						<c:set var="offsetCols" value="col-xs-12 col-sm-offset-3 " />
						<c:set var="fieldCols" value="col-xs-12 col-sm-9 " />
						
						<!-- ID -->
						<input type="hidden" name="id" value="${project.id}" />
						
						<!-- TITRE -->
						<div class="form-group">
							<label for="title" class="${labelCols} control-label">Titre :</label>
							<div class="${fieldCols}">
							    <input id="title" class="form-control" type="text" name="title" value="${project.title}" disabled/>
						  	</div>
					  	</div>
					  	
					  	<!-- MONTANT -->
						<div class="form-group">
							<label for="requiredAmount" class="${labelCols} control-label">Montant requis :</label>
							<div class="${fieldCols}">
							    <input id="requiredAmount" type="text" name="requiredAmount" class="form-control" value="${project.requiredAmount} € (${project.totalDonations * 100 / project.requiredAmount} %)" disabled/>
						  	</div>
					  	</div>
					  	
					  	<!-- ECHEANCE -->
						<div class="form-group">
						  	<label for="limitDate" class="${labelCols} control-label">Echéance :</label>
							<div class="${fieldCols}">
								<fmt:setLocale value="fr_FR" />
								<input id="limitDate" class="form-control" type="text" name="limitDate" value="<fmt:formatDate value='${project.limitDate}' dateStyle='full' />" disabled />
						  	</div>
					  	</div>
					  	
					  	<!-- DESCRIPTION -->
					  	<div class="form-group">
							<label for="description" class="${labelCols} control-label">Description :</label>
							<div class="${fieldCols}">
							    <p>${project.description}</p>
						  	</div>
						  	<div class="${offsetCols} ${fieldCols}">
								<textarea class="form-control" id="description" rows="8" name="description"></textarea>
							</div>
					  	</div>
					  	
					  	<!-- OBJECTIF -->
					  	<div class="form-group">
						  	<label for="amountDescription" class="${labelCols} control-label">Objectifs :</label>
							<div class="${fieldCols}">
							    <p>${project.amountDescription}</p>
						  	</div>
						  	<div class="${offsetCols} ${fieldCols}">
								<textarea id="amountDescription" class="form-control" rows="5" name="amountDescription"></textarea>
							</div>
					  	</div>
					  	
					  	<!-- IMAGE -->
					  	<div class="form-group">
						  	<label for="image" class="${labelCols} control-label">Image :</label>
							<div class="col-xs-6">
								<img  src="<c:url value="${project.image}" />" class="img-responsive" />
						  	</div>
						  	<div class="${offsetCols} ${fieldCols}">
								<br>
								<input id="image" class="form-control" type="text" name="image" placeholder="url de l'image" value="${project.image}" />
							</div>
						</div>
					  	
					  	<!-- CATEGORIES -->
					  	<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">Catégories</h4>						  	
						  	</div>
						  	
						  	<div class="panel-body">
						  		<c:forEach items="${project.categories}" var="category" varStatus="status">
						  			<span class="label label-primary">${category.name}</span>
						  		</c:forEach>
						  		<br>
								<c:forEach items="${categories}" var="category" varStatus="status">
									<div class="col-xs-12 col-sm-4 col-md-2">
										<div class="checkbox">
											<label>
												<input type="checkbox" name="categories" value="${category.id}">${category.name}
											</label>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>

						<!-- COMPENSATIONS -->
						<div class="panel panel-default">
						  	<div class="panel-heading">
						  		<div class="row">
							  		<h4 class="panel-title col-sm-8 col-md-6 col-lg-8">Compensations</h4>
							  			
							  		<div class="col-sm-4 col-md-6 col-lg-4 text-right">
										<a onClick="addCompensation(this.id)" id="addCompensation">
											<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
											Ajouter une compensation
										</a>
									</div>
								</div>
					  		</div>
					  			
						  	<div class="panel-body">																			
								<div class="row">
									<c:forEach items="${project.compensations}" var="compensation" varStatus="status">
										<div class="col-md-4 col-md-offset-1 col-xs-6">				
											<div class="form-group">
												<label for="ca${compensation.id}">Montant :</label>
											    <input id="compensationAmount" class="col-sm-10 form-control" type="text" name="ca${compensation.id}" value="${compensation.amount} €" disabled/>
											</div>
												  
											<div class="form-group">
												<label for="cd${compensation.id}">Description :</label>
											    <textarea class="form-control" rows="2" name="cd${compensation.id}" disabled>${compensation.description}</textarea>
											</div>
											<hr />
										</div>
									</c:forEach>
								</div>
									
								<input type="hidden" name="compensationNumber" id="compensationNumber" value="0">
								<div id="compensationContainer" class="row"></div>
							</div>
						</div>
					  	
						<button type="submit" class="btn btn-primary">Modifier le projet</button>
						<br><br>
					</form>
					
				</div>
			</div>
		</div>
		
		
		<script text/javascript>
			// cocher les catégories du projet
			
		</script>
		
		<!-- Template pour l'affichage d'une compensation -->
		<script type="text/template" class="template">
			<div class="col-md-4 col-md-offset-1 col-xs-12" id="div{{ vars.compensationNumber }}">				
				<div class="form-group">
					<label for="compensationAmount{{ vars.compensationNumber }}">Montant :</label>
				    <input id="compensationAmount{{ vars.compensationNumber }}" class="col-sm-10 form-control" type="number" name="compensationAmount{{ vars.compensationNumber }}" />
				</div>
				  
				<div class="form-group">
					<label for="compensationDescription{{ vars.compensationNumber }}">Description :</label>
				    <textarea class="col-sm-10 form-control" id="compensationDescription{{ vars.compensationNumber }}" rows="2" name="compensationDescription{{ vars.compensationNumber }}"></textarea>
				</div>

				<a id="deleteCompensation{{ vars.compensationNumber }}" class="text-danger" onClick="removeCompensation(this.id)">
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
					Supprimer la compensation
				</a>
				<hr />
			</div>
		</script>
		
		<script type="text/javascript">
			var compensationNumber = 0;
			var template = _.template($("script.template").html());

			function removeCompensation(id) {
				$("#div" + id.replace("deleteCompensation", "")).remove();
			}

			function addCompensation(id) {
				if (id == "addCompensation") {
					// incrémenter le nombre de compensation
					++compensationNumber;
					$("#compensationNumber").val(compensationNumber);
					
					var templateData = {
						compensationNumber: compensationNumber
					};

					$("#compensationContainer").append(template(templateData));
				}
			}
		</script>
		
	</jsp:body>
</t:layout>