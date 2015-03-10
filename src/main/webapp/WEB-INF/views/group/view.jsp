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
			<h3 class="panel-title list">
				<span class="glyphicon glyphicon-paperclip" aria-hidden="true">&nbsp;</span>
				Degree Details
			</h3>
			<a class="btn list-btn btn-warning"
				href="<c:url value='/academicTerm/${academicId}/course/${courseId}/group/${groupId}/modify.htm'/>"> <span
				class="glyphicon glyphicon-edit" aria-hidden="true">&nbsp;</span>
				Edit
			</a>

		</div>

		<div class="panel-body">


			<div class="form-group">
				<div class="form-group view">
					<label>Name: </label>
					<p class="details">${model.group.name}</p>
				</div>
			</div>

		</div>
	</div>
	<div class="panel panel-primary group">
		<div class="panel-heading">
			<h3 class="panel-title list">
				<span class="glyphicon glyphicon-list" aria-hidden="true">&nbsp;</span>

				Professor List
			</h3>
			<a style="cursor:copy;" class="btn list-btn btn-warning2"
				href="<c:url value='/academicTerm/${academicId}/course/${courseId}/group/${groupId}/professor/add.htm'/>"> <span
				class="glyphicon glyphicon-plus" aria-hidden="true">&nbsp;</span>
				Add 
			</a>

		</div>
		<div class="panel-body">

			<table class="table table-striped table-bordered">
				<tr align="center">
					<td width="20%"><div class="td-label">LastName</div></td>
					<td width="50%"><div class="td-label">FirstName</div></td>
				</tr>
				<c:forEach items="${model.group.professors}" var="prof">
					<tr align="center">
						<td><div class="td-content">
								<c:out value="${prof.lastName}" />
							</div></td>
						<td><div class="td-content">
								<c:out value="${prof.firstName}" />
							</div></td>


						<td><a class="btn list-btn btn-success"
							href="<c:url value='/professor/${prof.id}.htm'/>">View</a>
							<a class="btn btn-danger"
							href="<c:url value='/professor/${prof.id}/disabled.htm'/>">
								Disabled
						</a></td>

					</tr>
				</c:forEach>


			</table>
		</div>
	</div>

	<div class="panel panel-primary group">
		<div class="panel-heading">
			<h3 class="panel-title list">			
			<span class="glyphicon glyphicon-list" aria-hidden="true">&nbsp;</span>
			Student List</h3>
			<a style="cursor:copy;" class="btn list-btn btn-warning2"
				href="<c:url value='/academicTerm/${academicId}/course/${courseId}/group/${groupId}/student/add.htm'/>"> 
				<span class="glyphicon glyphicon-plus" aria-hidden="true">&nbsp;</span>
				Add
			</a>

		</div>
		<div class="panel-body">

			<table class="table table-striped table-bordered">
				<tr align="center">
					<td width="20%"><div class="td-label">LastName</div></td>
					<td width="50%"><div class="td-label">FirstName</div></td>
				</tr>
				<c:forEach items="${model.group.students}" var="student">
					<tr align="center">
						<td><div class="td-content">
								<c:out value="${student.lastName}" />
							</div></td>
						<td><div class="td-content">
								<c:out value="${student.firstName}" />
							</div></td>


						<td><a class="btn list-btn btn-success"
							href="<c:url value='/professor/${student.id}.htm'/>">View</a>
							<a class="btn btn-danger"
							href="<c:url value='/professor/${student.id}/disabled.htm'/>">
								Disabled
						</a></td>

					</tr>
				</c:forEach>


			</table>
		</div>


	</div>


</body>

</html>
