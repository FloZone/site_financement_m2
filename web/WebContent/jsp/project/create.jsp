<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Création d'un projet -->
<t:layout>
	<jsp:attribute name="head">
		Création d'un projet
	</jsp:attribute>

	<jsp:attribute name="css">
	
	</jsp:attribute>

	<jsp:body>
		<div class="container">
			<div class="page-header">
				<h1>Créer un projet</h1>
			</div>
				
			<p class="bg-danger">${errorMessage}</p>
		
			<div class="row">				
				<div class="col-md-8 col-md-offset-2 col-xs-12">
				
					<c:url value="/Project/Create" var="actionUrl" />
					<form class="form-horizontal" action="${actionUrl}" method="post" id="form">
					
					<c:set var="labelCols" value="col-xs-12 col-sm-3 " />
					<c:set var="fieldCols" value="col-xs-12 col-sm-9 " />
					
				  	<div class="form-group">
					    <label for="title" class="${labelCols} control-label">Titre du projet :</label>
					    <div class="${fieldCols}">
						    <input id="title" class="form-control" type="text" name="title" />
							<p class="errorText bg-danger" style="display: none">Veuillez saisir un titre</p>
						</div>
				  	</div>
					  
				 	<div class="form-group">
					    <label for="description" class="${labelCols} control-label">Description :</label>
					    <div class="${fieldCols}">
						    <textarea class="form-control" id="description" placeholder="Quel est mon projet?" rows="8" name="description"></textarea>
			                <p class="errorText bg-danger" style="display: none">Veuillez saisir une decription</p>
						</div>
				  	</div>
					  
			  		<div class="form-group">
				    <label for="requiredAmount" class="${labelCols} control-label">Montant nécessaire :</label>
					    <div class="${fieldCols}">
						    <input id="requiredAmount" type="number" min="0" name="requiredAmount" class="form-control" />
						    <p class="errorText bg-danger" style="display: none">Veuillez saisir un montant</p>
						</div>
				  	</div>	
	
				  	<div class="form-group">
					    <label for="amountDescription" class="${labelCols} control-label">Utilisation du financement :</label>
					    <div class="${fieldCols}">
						    <textarea id="amountDescription" placeholder="Que vais-je faire avec ce financement?" class="form-control" name="amountDescription" rows="5"></textarea>
						</div>
				  	</div>				  
					  
				  	<div class="form-group">
						<label for="limitDate" class="${labelCols} control-label">Echéance :</label>
						<div class="${fieldCols}">
							<input id="limitDate" class="form-control" type="date" name="limitDate" />
						</div>
				  	</div>
					
				  	<div class="form-group">
						<label for="image" class="${labelCols} control-label">Image :</label>
						<div class="${fieldCols}">
							<input id="image" class="form-control" type="text" name="image"
									placeholder="url de l'image" />
						</div>
				  	</div>
	
				  	<!-- liste des catégories à cocher -->
				  	<div class="panel panel-default">
					  	<div class="panel-heading">
							<h4 class="panel-title">Catégories</h4>						  	
					  	</div>
					  	
					  	<!-- insert into category values (1, 'A'), (2,'B'),(3,'C'),(4,'D'),(5,'E'),(6,'F'),(7,'G') -->
					  	<div class="panel-body">
					  		<div class="row">
								<c:forEach items="${categories}" var="c" varStatus="status">
									<div class="col-xs-12 col-sm-4 col-md-2">
										<div class="checkbox">
											<label>
												<input type="checkbox" name="categories" value="${c.id}">
												${c.name}
											</label>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					  </div>
						
					  <!-- Compensations -->
					  <div>
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
								<input type="hidden" name="compensationNumber" id="compensationNumber" value="0">
								<div id="compensationContainer" class="row">								
								</div>
							</div>
						</div>
					  </div>
	
					  <button type="submit" class="btn btn-primary">Créer mon projet</button>
	
					</form>
					<br>
				</div>
			</div>
				
		</div>
			
		<!-- Template pour l'affichage d'une compensation -->
		<script type="text/template" class="template">
				<div class="col-md-4 {{ vars.extraClass }} col-xs-12" id="div{{ vars.compensationNumber }}">				
				  <div class="form-group">
				    <label for="compensationAmount{{ vars.compensationNumber }}">Montant :</label>
				    <input id="compensationAmount{{ vars.compensationNumber }}" class="col-sm-10 form-control" type="number" name="compensationAmount{{ vars.compensationNumber }}" />
				  </div>
				  
				  <div class="form-group">
				    <label for="compensationDescription{{ vars.compensationNumber }}">Description :</label>
				    <textarea class="col-sm-10 form-control" id="compensationDescription{{ vars.compensationNumber }}" rows="2" name="compensationDescription{{ vars.compensationNumber }}"></textarea>
				  </div>

					<a id="deleteCompensation{{ vars.compensationNumber }}" class="text-danger" href="#" onClick="removeCompensation(this.id)">
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
							compensationNumber: compensationNumber,
							extraClass: compensationNumber % 2 == 1 ? "col-md-offset-1" : "col-md-offset-2"
					};

					
					$("#compensationContainer").append(template(templateData));
				}
			}

			document.getElementById("form").addEventListener(
					"submit",
					function(e) {
						var prevent = false;

						var elements = document
								.querySelectorAll(".errorText");
						for (var i = 0; i < elements.length; ++i) {
							elements[i].style.display = "none";
						}

						// titre
						var title = document.getElementById("title");
						if (title.value.length == 0) {
							prevent = true;
							title.nextElementSibling.style.display = "";
						}
						// description
						var title = document.getElementById("description");
						if (title.value.length == 0) {
							prevent = true;
							title.nextElementSibling.style.display = "";
						}
						// montant
						var title = document
								.getElementById("requiredAmount");
						if (title.value.length == 0) {
							prevent = true;
							title.nextElementSibling.style.display = "";
						}

						if (prevent) {
							e.preventDefault();
						}
					}, false);
		</script>
	</jsp:body>
</t:layout>