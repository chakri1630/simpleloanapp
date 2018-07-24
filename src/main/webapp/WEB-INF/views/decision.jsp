<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

<div class="container">

	<jsp:include page="header.jsp"/>
	
	<jsp:include page="navbar.jsp"/>

	Application : ${application_status}

</div>

</body>

<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>

</html>
