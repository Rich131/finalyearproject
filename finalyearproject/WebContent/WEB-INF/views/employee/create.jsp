<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FYP | Create Employee</title>


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="<c:url value='/resources/css/main.css'/>" type="text/css" >

</head>
<body>
	Under construction - create new employee


	<form class="formtable" method="post" action="${ pageContext.request.contextPath }/employee/created">
		<table>
			<tr>
				<td class="label">First Name</td>
				<td><input type="text" name="firstName"></td>
			</tr>
			<tr>
				<td class="label">Surname</td>
				<td><input type="text" name="surname"></td>
			</tr>
			<tr>
				<td class="label">Department</td>
				<td><select name="departmentId">
					<option value="0">Tech Support</option>
					<option value="1">Sales</option>
					<option value="2">Customer Service</option>
				</select></td>
			</tr>
			<tr>
				<td class="label">Start Date</td>
				<td><input type="date" name="startDate" /></td>
			</tr>
			<tr>
				<td colspan="2">
				Following traits must be between 1-99
				</td>
			</tr>
			<tr>
				<td class="label">Intelligence</td>
				<td><input type="number" name="intelligence" min="1" max="99" value="50"/></td>
			</tr>
			<tr>
				<td class="label">Patience</td>
				<td><input type="number" name="patience" min="1" max="99" value="50"/></td>
			</tr>
			<tr>
				<td class="label">Experience</td>
				<td><input type="number" name="experience" min="1" max="99" value="50"/></td>
			</tr>
			<tr>
				<td class="label">Motivation</td>
				<td><input type="number" name="motivation" min="1" max="99" value="50"/></td>
			</tr>
			<tr>
				<td colspan="2"><input class="control" type="submit" value="Create Employee"/></td>
			</tr>
		</table>
	</form>

</body>
</html>