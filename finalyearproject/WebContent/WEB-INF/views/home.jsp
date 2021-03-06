<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Final Year Project | Rich Murphy</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="<c:url value='/resources/css/main.css'/>"
	type="text/css">

</head>
<body>

	<p>
		<a href="${ pageContext.request.contextPath }/employees">View
			Employees</a>
	</p>
	<p>
		<a href="${ pageContext.request.contextPath }/employees/create">Create
			New Employee</a>
	</p>
	<p>
		<a href="${ pageContext.request.contextPath }/simulator">Simulator</a>
	</p>
</body>
</html>