<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title><fmt:message key="title" /></title>
<style>
.error {
	color: red;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<div class="panel panel-primary group">
		<div class="panel-heading">
			<span class="glyphicon glyphicon-paperclip" aria-hidden="true">&nbsp;</span>
			<h3 class="panel-title list">
			Subject Details</h3>
			<sec:authorize access="hasRole('ROLE_ADMIN')"><a class="btn list-btn btn-warning"
				href="<c:url value='/degree/${degreeId}/module/${model.subject.topic.module.id}/topic/${model.subject.topic.id}/subject/${subjectId}/modify.htm'/>">				
				<span class="glyphicon glyphicon-edit" aria-hidden="true">&nbsp;</span>
				Edit</a></sec:authorize>

		</div>

		<div class="panel-body">


			<div class="form-group">
				<div class="form-group view">
					<label>Code: </label> 
					<p class="details">${model.subject.info.code}</p>
				</div>
				<div class="form-group view">
					<label>Name: </label> 
					<p class="details">${model.subject.info.name}</p>
				</div>
				<div class="form-group view">
					<label>Description: </label> 
					<p class="details">${model.subject.info.description}</p>
				</div>
				<div class="form-group view">
					<label>Topic: </label> 
					<p class="details">${model.subject.topic.info.name}</p>
				</div>
			</div>

		</div>
	</div>

	<div class="panel panel-primary group">
		<div class="panel-heading">
			<h3 class="panel-title list">
			<span class="glyphicon glyphicon-list" aria-hidden="true">&nbsp;</span>
			Competence List </h3>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a style="cursor:copy;" class="btn list-btn btn-warning2"
				href="<c:url value='/degree/${degreeId}/module/${model.subject.topic.module.id}/topic/${model.subject.topic.id}/subject/${subjectId}/addCompetences.htm'/>">
								<span class="glyphicon glyphicon-plus" aria-hidden="true">&nbsp;</span>
				Add</a></sec:authorize>

		</div>
		<div class="panel-body">
			<table class="table table-striped table-bordered">
				<tr align="center">
					<td width="20%"><div class="td-label">Name</div></td>
					<td width="50%"><div class="td-label">Description</div></td>
			
				</tr>
				<c:forEach items="${model.competences}" var="competence">
					<tr align="center">
						<td><div class="td-content">
								<c:out value="${competence.info.name}" />
							</div></td>
						<td>
							<div class="td-content">
								<c:out value="${competence.info.description}" />
							</div>
						</td>

						<td> 
						<a class="btn list-btn btn-success"
						href="<c:url value='/degree/${degreeId}/competence/${competence.id}.htm'/>">View</a>
							<sec:authorize access="hasRole('ROLE_ADMIN')"><a class="btn btn-danger"
							href="<c:url value='/degree/${degreeId}/module/${model.module}/topic/${model.topic}/subject/${subjectId}/competence/${competence.id}/delete.htm'/>">
							Delete
							</a></sec:authorize>
							</td>

					</tr>
				</c:forEach>


			</table>
		</div>

	</div>

</body>

</html>
