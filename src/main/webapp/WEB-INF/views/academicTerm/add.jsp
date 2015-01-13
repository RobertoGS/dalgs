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
			<h3 class="panel-title list">Add Academic Term</h3>
		</div>
		<div class="panel-body">

			<form:form method="post" commandName="addAcademicTerm" role="form">
				<div class="form-group">
					<label>Term: </label>
					<form:input path="term" class="form-control" placeholder="Code of the academicTerm" required="true"/>
				</div>
	
				
				<input type="submit" class="btn btn-success" value="Add" />

			</form:form>
		</div>
	</div>
</body>
</html>
<div class="home-button"><a class="btn home" href="<c:url value="/home.htm"/>">Home</a></div></body>
</body>
</html>