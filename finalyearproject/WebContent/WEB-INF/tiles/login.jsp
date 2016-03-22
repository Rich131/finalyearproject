<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	
	<script>
	$(document).ready(function() {
		document.f.username.focus();
	});
	</script>

	<h3>Login with Username and Password</h3>

	<c:if test="${ param.error != null }">
		<p class="error">Login failed, check that your username and
			password are correct</p>
	</c:if>

	<form name='f' action='${ pageContext.request.contextPath }/login'
		method='POST'>
		<table class="table">
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Login" /></td>
			</tr>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</table>
	</form>
