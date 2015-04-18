<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
<head>
<title><fmt:message key="common.title" /></title>

   

<script type="text/javascript" >
$(document).ready(function(){
 sendAjax();

});
 
function getDummyAjax() {
 
$.ajax({ 
    url: "${pageContext.request.contextPath}/rest/activity", 
    type: 'GET', 
    dataType: 'json', 
    //data: "{\"id_group\":\"1L\",\"id_course\":\"null\",\"name\":\"name\",\"description\":\"description\"}", 
    contentType: 'application/json',
    mimeType: 'application/json',
    success: function(data) { 
        alert( data.name);
    },
    error:function(data,status,er) { 
        alert("error: "+data+" status: "+status+" er:"+er);
    }
});
}

function postAjax() {
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");
	
	$.ajax({ 
	    url: "${pageContext.request.contextPath}/rest/activity", 
	    type: 'POST', 
	    dataType: 'json', 
	    data: "{\"id_group\":\"1L\",\"id_course\":\"null\",\"name\":\"name\",\"description\":\"description\"}", 
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    beforeSend: function(xhr){
	    	alert(header);
	    	alert(token);
	        xhr.setRequestHeader(header, token);
	    },
	    success: function(data) { 
	        alert( data.name);
	    },
	    error:function(data,status,er) { 
	        alert("error: "+data+" status: "+status+" er:"+er);
	    }
	});
	}
</script>




</head>
<body>
	<div class="list-group index">
		<a class="list-group-item "
			href="<c:url value='/academicTerm/page/0.htm?showAll=false'/>"> 
			<h4 class="list-group-item-heading">				
			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<fmt:message key="home.ats" /></h4>
			<p class="list-group-item-text">
			<fmt:message key="academicterm.header" />
			</p>
		</a> 
		<a class="list-group-item " 
			href="<c:url value='/degree/page/0.htm?showAll=false'/>">
			<h4 class="list-group-item-heading">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<fmt:message key="home.degs" />
			</h4>
			<p class="list-group-item-text">
			<fmt:message key="degree.header" />
			</p>
		</a>

<a class="list-group-item "
				href="<c:url value='/user/page/0.htm?showAll=false&typeOfUser=ROLE_PROFESSOR'/>">
				<h4 class="list-group-item-heading">
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<fmt:message key="home.profs" />
				</h4>
				<p class="list-group-item-text"><fmt:message key="header.profs" /></p>
			</a> 
			<a class="list-group-item "
				href="<c:url value='/user/page/0.htm?showAll=false&typeOfUser=ROLE_STUDENT'/>">
				<h4 class="list-group-item-heading">
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<fmt:message key="home.studs" />
				</h4>
				<p class="list-group-item-text"><fmt:message key="header.studs" /></p>
			</a>
	
		
		<br><br>
		
		Direct Access to delete
		<a  class="list-group-item " 
			href="<c:url value='/academicTerm/1/course/1.htm'/>">
			<h4 class="list-group-item-heading">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				Course 1  (Coordinator)</h4>
		</a>
		
		 <a  class="list-group-item " 
			href="<c:url value='/academicTerm/1/course/1/group/1.htm'/>">
			<h4 class="list-group-item-heading">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				Group 1 - C1 (Student, Professor)</h4>
		</a>
	</div>
</body>
</html>
