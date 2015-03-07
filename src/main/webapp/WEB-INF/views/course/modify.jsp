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
			<h3 class="panel-title list">				
			<span class="glyphicon glyphicon-edit" aria-hidden="true">&nbsp;</span>
			Modify Course</h3>
		</div>
		<div class="panel-body">


			<form:form method="post" commandName="modifyCourse" role="form">
	
				
				<div class="form-group">
					<label>Academic Term:</label>
					<p>${academicTerm.term} &nbsp; ${academicTerm.degree.info.name}</p>
					
					<!--<form:select class="form-control 2" path="academicTerm" id="termSelect">
						<form:option value="">-- Select an option --</form:option>
						<c:forEach items="${academicTerms}" var="at">
							<c:choose>
								<c:when test="${at.id == idAcademicTerm}">
									<form:option value="${at.id}" selected='true'>${at.term}</form:option>
								</c:when>
								<c:otherwise>
									<form:option value="${at.id}">${at.term}</form:option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
					-->
				</div>
				
				<div class="form-group">
					<label>Subject:</label>
					<form:select class="form-control 2" path="subject" >
						<form:option value="">-- Select an option --</form:option>
						<c:forEach items="${subjects}" var="subject">
							<c:choose>
								<c:when test="${subject.id == idSubject}">
									<form:option value="${subject.id}" selected='true'>${subject.info.code} - ${subject.info.name}</form:option>
								</c:when>
								<c:otherwise>
									<form:option value="${subject.id}">${subject.info.code} - ${subject.info.name} </form:option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</div>
				
				
				<input type="submit" class="btn btn-success" value="Update">

			</form:form>
		</div>
	</div>
	<c:if test="${not empty errors}">
	<div align="center">
		<h3 class="panel-title list">	Errors: </h3>	
			<br/>
			<c:forEach items="${errors}" var="error">
				<c:out  value="${error}" /><br/>
			</c:forEach>
	</div>
	</c:if>
</body>
</html>
