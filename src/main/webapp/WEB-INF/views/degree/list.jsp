<%@page import="org.apache.taglibs.standard.tag.common.xml.IfTag"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
<head>
<title><fmt:message key="title" /></title>
</head>
<body>



	<div class="table-responsive list">
		<div class="panel-heading list">
			<h4>Degrees</h4>
			<a class="btn btn-add2" href="<c:url value='/degree/add.htm'/>">
				Add Degree </a>
		</div>
		<table class="table table-striped table-bordered">
			<tr align="center">
				<td>Code</td>
				<td>Name</td>
				<td width="50%">Description</td>
				<td>Actions</td>
			</tr>
			<c:forEach items="${model.degrees}" var="degree">
				<tr align="center">
					<td><c:out value="${degree.code}" /></td>
					<td><c:out value="${degree.name}" /></td>
					<td><c:out value="${degree.description}" /></td>

					<td><a class="btn list-btn btn-success"
						href="<c:url value='view/${degree.id}.htm'/>">View</a> <!-- <a href="modify.html"  class="btn list-btn btn-warning">Modify</a>-->
						<a class="btn list-btn btn-danger"
						href="<c:url value='delete/${degree.id}.htm'/>">Delete</a></td>

				</tr>
			</c:forEach>


		</table>
	</div>
	<div class="home-button">
		<a class="btn home" href="<c:url value="/home.htm"/>">Home</a>
	</div>




</body>
</html>
