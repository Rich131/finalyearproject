<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FYP | Create Employee</title>


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
	Under construction - create new employee

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<sf:form class="form" method="post" commandName="employee"
					action="${ pageContext.request.contextPath }/employees/create">
					<h4>Add an Employee</h4>
					<c:if test="${ param.created != null }">
						<p class="added">Employee created!</p>
					</c:if>
					<table class="table">
						<tr>
							<td class="">First Name</td>
							<td><sf:input type="text" path="firstName" name="firstName" /><br />
								<sf:errors path="firstName" cssClass="error"></sf:errors></td>
						</tr>
						<tr>
							<td class="">Surname</td>
							<td><sf:input type="text" path="surname" name="surname" /><br />
								<sf:errors path="surname" cssClass="error"></sf:errors></td>
						</tr>
						<tr>
							<td class="">Department</td>
							<td><sf:select path="departmentId" name="departmentId">
									<sf:option value="-1">- Select a department -</sf:option>
									<sf:option value="0">Tech Support</sf:option>
									<sf:option value="1">Sales</sf:option>
									<sf:option value="2">Customer Service</sf:option>
								</sf:select><br /> <sf:errors path="departmentId" cssClass="error"></sf:errors></td>
						</tr>
						<tr>
							<td class="">Start Date</td>
							<td><sf:input type="date" path="startDate" name="startDate" /></td>
						</tr>
						<tr>
							<td colspan="2">Following traits must be between 1-99</td>
						</tr>
						<tr>
							<td class="">Intelligence</td>
							<td><sf:input type="number" path="intelligence"
									name="intelligence" min="1" max="99" value="50" /></td>
						</tr>
						<tr>
							<td class="">Patience</td>
							<td><sf:input type="number" path="patience" name="patience"
									min="1" max="99" value="50" /></td>
						</tr>
						<tr>
							<td class="">Empathy</td>
							<td><sf:input type="number" path="empathy" name="empathy"
									min="1" max="99" value="50" /></td>
						</tr>
						<tr>
							<td class="">Experience</td>
							<td><sf:input type="number" path="experience"
									name="experience" min="1" max="99" value="50" /></td>
						</tr>
						<tr>
							<td class="">Motivation</td>
							<td><sf:input type="number" path="motivation"
									name="motivation" min="1" max="99" value="50" /></td>
						</tr>
						<tr>
							<td colspan="2"><input class="control" type="submit"
								value="Create Employee" /></td>
						</tr>
					</table>
				</sf:form>
			</div>
		</div>
	</div>

</body>
</html>