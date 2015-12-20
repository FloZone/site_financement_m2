<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- Page d'un projet -->
<t:layout>
	<jsp:attribute name="head">
		Projet - <c:out value="${project.title}"></c:out>
	</jsp:attribute>

	<jsp:attribute name="css">
			<link href='<c:url value="/css/style.css"/>' rel="stylesheet">
	</jsp:attribute>

	<jsp:body>
		<!-- now = date d'aujourd'hui pour afficher/masquer les champs de dons et commentaires -->
		<jsp:useBean id="now" class="java.util.Date"/>
		
		<div class="container bloc">
		
			<c:if test="${not empty userId }">
				<c:if test="${param.info == 'success'}">
					<div class="alert alert-success">
						<strong>${project.title}</strong> est à présent mis en avant.
					</div>
				</c:if>
				
				<c:if test="${param.info == 'error'}">
					<div class="alert alert-danger">
						<strong>Erreur</strong>, une erreur est survenue. Aucune modification n'a été enregistrée.
					</div>
				</c:if>
			
				<div class="row">
					<c:if test="${not empty admin }">
						<div class="col-xs-6">
							<a class="modal-toggle text-danger" href="#" data-toggle="modal" data-target="#myModal">
								<span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span>
							 	Mettre ce projet en avant
							</a>
						</div>
					</c:if>
				</div>
				
				<div id="myModal" class="modal fade">
				 <div class="modal-dialog">
				   <div class="modal-content">
				     <div class="modal-header">
				       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				       <h4 class="modal-title">Mettre un projet en avant</h4>
				     </div>
			    		
				     <div class="modal-body">
				       <p>
					       Vous souhaitez épingler le projet
					       <span class="text-primary">${project.title}</span>.
				       </p>

				     	<c:url value="/Administration/TopProject" var="addTopProjectUrl" />
	     		      	<form action="${addTopProjectUrl}" method="post">
	     		      		<input type="hidden" name="projectId" value="${param.id}" />
	     		      		
	     		      		<div class="form-group">
		     		      		<label for="topProjectDescriptionField">Description :</label>
		     		      		<textarea id="topProjectDescriptionField" class="form-control" name="topProjectDescription" rows="3"></textarea>
	     		      		</div>
	     		      		
	     		      		<div class="form-group text-right">
						       <button type="button" class="btn btn-primary" data-dismiss="modal">Annuler</button>
						       <button type="submit" class="btn btn-danger">Mettre en avant</button>
					       </div>
				       </form>
				     </div>
				   </div>
				</div>
				</div>
			</c:if>

			<div class="jumbotron blue">
				<h1 class=""><c:out value="${project.title}"></c:out></h1>
				<!-- Barre de progression du financement -->
				<div class="progress">
					<div class="progress-bar progress-bar-striped progress-bar-success active"
						role="progressbar" aria-valuenow="${project.totalDonations * 100 / project.requiredAmount}" aria-valuemin="0"
						aria-valuemax="100" style="width: ${project.totalDonations * 100 / project.requiredAmount}%;">
					    <span class="sr-only">${project.totalDonations * 100 / project.requiredAmount}% Complete</span>
					 </div>
				</div>	
			</div>
			<br/>
			<div class="row">
				<div class="col-lg-9">
				 	<c:if test="${param.msg == '1'}">
				 		<p class="bg-success">Votre don a été enregistré, merci pour votre participation :)</p>
				 	</c:if>
				 
				 	<c:if test="${param.msg == '2'}">
				 		<p class="bg-danger">Veuillez saisir un montant valide.</p>
				 	</c:if>
				 	
				 	<c:if test="${param.msg == '3'}">
				 		<p class="bg-danger">Attention ! Le montant saisi doit être positif et non nul.</p>
				 	</c:if>
				 	
				 	<c:if test="${param.msg == '4'}">
				 		<p class="bg-danger">Attention ! Le montant saisi est inférieur au montant de la compensation.</p>
				 	</c:if>
				 	
				 	<c:if test="${param.msg == '5'}">
				 		<p class="bg-danger">Veuillez vous connecter pour effectuer un don.</p>
				 	</c:if>
				</div>
			
				<div class="col-lg-9 col-md-8 col-sm-12 col-xs-12">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<img src="<c:url value="${project.image}" />" class="img-responsive" />
						</div>
						<!-- CATEGORIES -->
						<div>

						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<ul class="list-group">
								<li class="list-group-item">
									<c:forEach items="${project.categories}" var="category">
										<span class="label label-primary">${category.name}</span>
									</c:forEach>
								</li>
							  	<li class="list-group-item"><strong>${project.totalDonations} €</strong> collectés</li>
								<li class="list-group-item"><strong>${project.requiredAmount} €</strong> demandés</li>
								<li class="list-group-item">Jusqu'au <strong><fmt:formatDate value="${project.limitDate}" pattern="dd-MM-yyyy" /></strong>!</li>
							</ul>		
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<br/><br/>
							<div class="panel panel-success">
								<div class="panel-heading">
							    	<h2 class="panel-title">Description du projet: </h2>
							 	</div>
							  	<div class="panel-body">
							  		${project.description}
							  	</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="panel panel-info">
								<div class="panel-heading">
							    	<h2 class="panel-title">Que vais-je faire avec ce financement? </h2>
							 	</div>
							  	<div class="panel-body">
							  		${project.amountDescription}
							  	</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-4 col-sm-12 col-xs-12">
				
					<c:if test="${not empty userId }">
						<c:if test="${now lt project.limitDate}">
							<form action="${pageContext.request.contextPath}/Project/Donation" method="post">
								<div class="input-group">
							  	  <input id="project" type="hidden" name="project" value="${project.id}"/>
								
								  <span class="input-group-addon" id="basic-addon1">Votre Montant: </span>
								  <input type="number" class="form-control" aria-describedby="basic-addon1" name="amountDonation">
								  <span class="input-group-addon">€</span>
								</div>
								<br/>
								
								<button type="submit" class="btn btn-danger btn-lg">
				    				Financer!
				  				</button>
				  				<br/><br/>
				  		</c:if>
			  		</c:if>

						<div class="panel panel-warning">
								<div class="panel-heading">
							    	<h2 class="panel-title"> Compensations </h2>
							 	</div>
							  	<div class="panel-body">
								  	<table class="table">
								  		<!-- <label>
											<input type="radio" name="compensation" value="-1" checked>								  		
											Aucune
								  		</label> -->

								  		<c:forEach items="${compensations}" var="compensation">
											<tr>
												<th>
													<c:out value="${compensation.amount}"></c:out><br>
													<!-- Voir comment on stocke la compensation choisie
													(ou alors calculable <=> pas possible de choisir une compensation plus faible que le montant) -->
													<!-- <input type="radio" name="compensation" value="${compensation.id}"> -->
												</th>
												<td>
													<c:out value="${compensation.description}"></c:out>
												</td>
											</tr>
										</c:forEach>
								  	</table>
							    </div>
						</div>

					<c:if test="${not empty userId }">
						</form>
					</c:if>
				</div>
			</div>
			
			
			<!-- Bloc avec les messages -->
			<div class="panel panel-default">
				<div class="panel-heading">
			    	<h3 class="panel-title">Commentaires</h3>
			 	</div>
			 	
			  	<div class="panel-body">
			  		<ul class="list-group">
				    	<c:forEach items="${messages}" var="hierMessage">
							<li class="list-group-item">
								<form name="formGoodOrBad" action="${pageContext.request.contextPath}/GoodorBad" method="post">
				    			<h4>
				    				<span class="label label-default"><c:out value="${hierMessage.message.user.fullname}"></c:out></span>
					    				<a class="label label-success label-as-badge"
				    					   href="#" 
					    				   onClick="avis('message', ${hierMessage.message.id}, 1)">
					    					${hierMessage.message.good} <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
					    				</a> 
					    				<a class="label label-danger label-as-badge" 
					    					href="#"
					    					onClick="avis('message', ${hierMessage.message.id}, 0)">
					    					${hierMessage.message.bad} <span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
					    				</a>
					    				<input type="hidden" value="${project.id}" name="projectGoB"/>
					    				<input type="hidden" value="" name="goodorbad" id="rep"/>
					    				<input type="hidden" value="${hierMessage.message.id}" name="goodorbadId" id="id"/>
					    				<input type="hidden" value="" name="goodorbadType" id="type"/>
				    			</h4>
				    			<c:out value="${hierMessage.message.content}"></c:out><span class="badge">${fn:length(hierMessage.answers)}</span>
				    			<ul class="list-group">
				    				<c:forEach items="${hierMessage.answers}" var="answer">
							    		<li class="list-group-item">
							    			<h5>
							    				<span class="label label-default"><c:out value="${answer.user.fullname}"></c:out></span>
							    				<a class="label label-success label-as-badge" 
							    					href="#"
					    							onClick="avis('answer', ${answer.id}, 1)">
							    					${answer.good} <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
							    				</a> 
							    				<a class="label label-danger label-as-badge"
						    						href="#"
					    							onClick="avis('answer', ${answer.id}, 0)">
							    					${answer.bad} <span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
							    				</a>
							    			</h5>
							    			<c:out value="${answer.content}"></c:out>
							    		</li>
				    				</c:forEach>
				    			</ul>
					    		</form>
						    	<c:if test="${not empty userId }">
						    		<c:if test="${now lt project.limitDate}">
								    	<form action="${pageContext.request.contextPath}/Answer" method="post">
										  	<div class="form-group">
										  		<input id="mess" type="hidden" name="mess" value="<c:out value="${hierMessage.message.id}"></c:out>"/>
										    	<input id="answer" class="form-control" type="text" name="answer" placeholder="Répondre à un commentaire"/>
										  	</div>
									  		<button type="submit" class="btn btn-info">Répondre</button>
										</form>
									</c:if>
								</c:if>
				    		</li>
				    	</c:forEach>
			    	</ul>
			    	<c:if test="${not empty userId }">
				    	<c:if test="${now lt project.limitDate}">
					    	<form action="${pageContext.request.contextPath}/Message" method="post">
							  	<div class="form-group">
							  		<input id="project" type="hidden" name="project" value="<c:out value="${project.id}"></c:out>"/>
							    	<input id="message" class="form-control" type="text" name="message" placeholder="Un commentaires? Une Question?"/>
							  	</div>
						  		<button type="submit" class="btn btn-primary">Envoyer</button>
							</form>
						</c:if>
					</c:if>
			  	</div>
			</div>

		</div>
		
		<script>
			function avis(type, id, rep){
				<c:if test="${not empty userId }">
					document.getElementById("type").value = type;
					document.getElementById("id").value = id;
					document.getElementById("rep").value = rep;
				    document.forms["formGoodOrBad"].submit();
				</c:if>
			}
		</script>
	</jsp:body>
</t:layout>