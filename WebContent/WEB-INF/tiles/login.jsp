<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<script type="text/javascript">
	$(document).ready(function() {
		document.f.j_username.focus();
	})
</script>
<h3>Login with Username asdasd and Password</h3>

<c:if test="${param.failure == true}">

	<p class="error">Login failed. Check that your username and
		password are correct.</p>

</c:if>


<form:form name='f' action='${pageContext.request.contextPath}/login'
	method='POST'>
	<table class="formtable">
		<tr>
			<td>User:</td>
			<td><input type='text' name='j_username' value=''></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type='password' name='j_password' /></td>
		</tr>
		<tr>
				<td>Remember me:</td>
				<td><input type='checkbox' name='_spring_security_remember_me' checked="checked"/></td>
			</tr>
		<tr>
			<td colspan='2'><input name="submit" type="submit" value="Login" /></td>
		</tr>
	</table>
</form:form>

<p>
	<a href="<c:url value="/newaccount"/>">Create new account</a>
</p>

