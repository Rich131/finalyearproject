<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<div class="row">
	<div class="col-md-8 col-md-offset-2">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>Create Employee</h4>
			</div>
			<div class="panel-body">
				<sf:form class="form-inline" method="post" commandName="employee"
					action="${ pageContext.request.contextPath }/employees/create">

					<sf:input class="form-control" type="text" path="firstName"
						name="firstName" placeholder="First Name" />
					<sf:errors path="firstName" cssClass="error"></sf:errors>

					<sf:input class="form-control" type="text" path="surname"
						name="surname" placeholder="Surname" />
					<sf:errors path="surname" cssClass="error"></sf:errors>


					<sf:select class="form-control" path="departmentId"
						name="departmentId">
						<sf:option value="-1">- Select a department -</sf:option>
						<sf:option value="0">Tech Support</sf:option>
						<sf:option value="1">Sales</sf:option>
						<sf:option value="2">Customer Service</sf:option>
					</sf:select>
					<sf:errors path="departmentId" cssClass="error"></sf:errors>

					<input class="control btn btn-default" type="submit"
						value="Create Employee" />
				</sf:form>
			</div>
		</div>



	</div>
</div>



<div class="row">
	<div class="col-12-md">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="scrollable emp-table">
					<table class="table table-condensed">
						<tr>
							<th>Department</th>
							<th>Full Name</th>
						</tr>
						<c:forEach var="emp" items="${employees}">
							<tr>
								<td><c:out value="${ emp.departmentId }"></c:out></td>
								<td><c:out value="${ emp.firstName } ${ emp.surname }"></c:out></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>