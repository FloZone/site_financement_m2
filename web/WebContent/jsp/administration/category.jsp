<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<t:administrationLayout>
	<jsp:body>
		<div class="panel panel-info">
			<div class="panel-heading">
			  <h3 class="panel-title">Ajouter une catégorie</h3>
			</div>
			<div class="panel-body">
				<c:if test="${param.creationError == 'notUnique'}">
					<div class="alert alert-danger">
						<strong>Erreur</strong>, ce nom de catégorie existe déjà.
					</div>
				</c:if>
				<c:url value="/Administration/Category" var="administrationCategoryUrl" />
				<form class="form-inline" action="${administrationCategoryUrl}"
					method="post">
					<div class="form-group">
						<label for="catNameField">Nom :</label>
						<input id="catNameField" class="form-control" type="text"
							name="name" />
						<button type="submit" class="btn btn-primary">Ajouter</button>
					</div>
				</form>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">
			  <h3 class="panel-title">Catégories</h3>
			</div>
			<div class="panel-body">
				<ul class="list-group">
					<c:forEach items="${categories}" var="c">
						<li class="list-group-item col-sm-6 col-md-4">
							${c.name}
							<a class="modal-toggle text-danger" href="#" data-categoryid="${c.id}"
									data-categoryname="${c.name}" data-toggle="modal"
									data-target="#myModal">
								<span class="glyphicon glyphicon-trash" aria-hidden="true">
								</span>
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
		        <h4 class="modal-title" id="myModalLabel">Confirmer la suppression</h4>
		      </div>
		      <div class="modal-body">
		     	Êtes-vous sûr de vouloir supprimer la catégorie <span
							id="categoryPlaceHolder" class="text-primary"></span> ?
		      </div>
		      
		      <div class="modal-footer text-right">
		      	<form class="form-inline" action="${administrationCategoryUrl}"
							method="post">
		      		<input id="categoryToDeleteField" type="hidden"
								name="categoryId" />
		      		<button class="btn btn-primary" data-dismiss="modal">Annuler</button>
		      		<button class="btn btn-danger" type="submit">Supprimer</button>
		      	</form>
		      </div>
		    </div>
		  </div>
		</div>
	
	<script>
		$(function() {
			$(".modal-toggle").on("click", function() {
				var categoryId = $(this).data("categoryid");
				var categoryName = $(this).data("categoryname");

				$("#categoryPlaceHolder").text(categoryName);
				$("#categoryToDeleteField").val(categoryId);
			});
		});
	</script>
	</jsp:body>
</t:administrationLayout>