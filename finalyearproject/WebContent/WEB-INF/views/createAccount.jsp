<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FYP | Create Account</title>


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
	<div class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<table class="table">
					<tr>
						<td>Username</td>
						<td><sf:input type="text" path="username" name="username" /></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><sf:input type="text" path="email" name="email" /></td>
					</tr>
					<tr>
						<td>Agent ID</td>
						<td><sf:select path="agentId" name="agentId">
								<c:forEach var="emp" items="${employees}">
									<sf:option value="${ emp.agentID }"></sf:option>
								</c:forEach>
							</sf:select></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><sf:input type="password" path="password" name="password" /></td>
					</tr>
					<tr>
						<td>Retype Password</td>
						<td><input type="password" name="repassword" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Login" /></td>
					</tr>
				</table>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
</body>
</html>