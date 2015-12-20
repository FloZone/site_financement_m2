<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<t:administrationLayout>
	<div class="panel panel-info">
		<div class="panel-heading">
			 <h3 class="panel-title">Utilisateurs</h3>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-stripped table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>Login</th>
							<th class="hidden-xs">Prénom</th>
							<th class="hidden-xs">Nom</th>
							<th>Email</th>
							<th>Administrateur</th>
						</tr>
					</thead>
						
					<tbody>
						<c:forEach items="${users}" var="u">
							<tr>
								<td>${u.id}</td>
								<td>${u.login}</td>
								<td class="hidden-xs">${u.firstName}</td>
								<td class="hidden-xs">${u.lastName}</td>
								<td>${u.email}</td>
								<td>${u.administrator}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</t:administrationLayout>