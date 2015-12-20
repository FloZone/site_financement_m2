<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<t:administrationLayout>
	<jsp:body>
		<div class="row">
			<c:forEach items="${topProjects}" var="tp">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<a href="${pageContext.request.contextPath}/Project/Show?id=${tp.project.id}" class="noDeco">
						<div class="panel panel-primary taille">
							<div class="panel-heading">
								<h2 class="panel-title"><c:out value="${tp.project.title}"></c:out></h2>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-lg-6 col-md-6 coœl-sm-6 col-xs-12">
										<img alt="<c:out value="${tp.project.title}"></c:out>"
												src="<c:url value="${tp.project.image}" />" class="img-responsive">
									</div>
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 center">
						            	<c:out value="${tp.description}"></c:out><br>
						            	<a href="#" class="text-danger modal-toggle removeTopProjectLink" data-topprojectid="${tp.id}" data-projectname="${tp.project.title}" data-toggle="modal" data-target="#myModal">
											<span class="glyphicon glyphicon-remove"></span>
											Arrêter de mettre ce projet en avant
										</a> 
									</div>
								</div>
							</div>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
		
		<div id="myModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Supprimer la mise en avant d'un projet</h4>
					</div>
					<div class="modal-body">
						<p>
							Vous souhaitez désépingler le projet
							<span id="projectNamePlaceholder" class="text-primary"></span>.
						</p>
					</div>
					
					<div class="modal-footer">
					    	<c:url value="/Administration/TopProject" var="removeTopProjectUrl" />
						<form class="form-inline" action="${removeTopProjectUrl}" method="post">
							<input id="topProjectIdField" type="hidden" name="topProjectId" value="" />
							
							<div class="form-group text-right">
								<button type="button" class="btn btn-primary" data-dismiss="modal">Annuler</button>
								<button type="submit" class="btn btn-danger">Désépingler</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<script>
			$(function() {
				$(".removeTopProjectLink").on("click", function() {
					var projetName = $(this).data("projectname");
					var topProjectId = $(this).data("topprojectid");
					
					$("#projectNamePlaceholder").text(projetName);
					$("#topProjectIdField").val(topProjectId);
				});
			});
		</script>
	</jsp:body>
</t:administrationLayout>