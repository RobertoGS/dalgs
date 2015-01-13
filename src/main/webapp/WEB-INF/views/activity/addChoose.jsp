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

	<div class="panel panel-primary group category">
		<div class="panel-heading">
			<h3 class="panel-title list">Add Activity</h3>
		</div>
		<div class="panel-body">
			<%-- 	<form:form  method="post" modelAttribute="modifyProduct" > (ResquestParam)  --%>

			<form:form method="post" commandName="addactivity">
				<div class="form-group">
					<label>Code: </label>
					<form:input path="code" class="form-control"
						placeholder="Code of the activity" required="true" />
				</div>
				<div class="form-group">
					<label>Name: </label>
					<form:input path="name" class="form-control" id="name"
						required="true" />

				</div>
				<div class="form-group">
					<label>Description: </label>
					<form:input class="form-control" path="description"
						id="description" required="true" />
				</div>

		
				<div class="form-group">
					<label>Course:</label>
					<form:select class="form-control 2" path="course" id="courseSelect">
						<form:option value=""> --Select an option-- </form:option>
						<c:forEach items="${courses}" var="c">
							<form:option value="${c.id}">${c.name}</form:option>
						</c:forEach>
					</form:select>
				</div>

				<div class="panel-body">
					<label>Competence Status List:</label>
				
					<table class="table table-condensed">
						<tr align="center">
							<td width="20%"><div class="td-label">Competence</div></td>
							<td width="50%"><div class="td-label">Percentage</div></td>
						</tr>
						
						<c:forEach items="${competenceStatus}" var="compStatus" >					
							<tr align="center">
								<td><div class="td-content">
										<c:out value="${competences[compStatus.id_competence-1].name}" />
									</div></td>
								<td>
									<div class="td-content">
										<c:out value="${compStatus.percentage}" />
									</div>
								</td>
	
								<%-- 
								<td><a class="btn list-btn btn-warning"
									href="<c:url value='/subject/activity/modify/${subjectId}/${activity.id}.htm'/>">Modify</a>
									<a class="btn list-btn btn-danger"
									href="<c:url value='/subject/activity/delete/${subjectId}/${activity.id}.htm'/>">Delete</a></td>
 --%>
							</tr>
						</c:forEach>


					</table>
				</div>
				<input type="submit" class="btn btn-primary btn-lg addActivity" value="Add Activity" />

			</form:form >
			<div class="addCOmeptenceStatus">
			<form:form method="post" commandName="addcompetencestatus">
					<h4> New Competence Status</h4>
					<label>Competence:</label>
					<form:select class="form-control 2" path="id_competence"
						id="competence">
						<form:option value="0"> --Select an option-- </form:option>
						<c:forEach items="${competences}" var="comp">
							<form:option value="${comp.id}">${comp.name}</form:option>
						</c:forEach>
					</form:select>
					<label>Competence Percentage:</label>
					<form:input class="form-control" path="percentage" id="percentage"
						required="true" />

					<input type="submit" class="btn btn-success CompSta"
						value="Add Competence Status" />

			</form:form>
			</div>
		</div>
	</div>
</body>
</html>
<div class="home-button">
	<a class="btn home" href="<c:url value="/home.htm"/>">Home</a>
</div>
</body>
</body>
</html>