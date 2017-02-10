<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<a class="title" href="<c:url value='/'/>">Offers</a>

<sec:authorize access="!isAuthenticated()">
	<p>
		<a class="login" href="<c:url value='/login'/>">Log in</a>
	</p>
</sec:authorize>

<!-- <div style="margin: 0 auto" align=right> -->
<%-- 	<sec:authorize access="isAuthenticated()"> --%>
<%-- 		<%-- <p><a href="<c:url value='/j_spring_security_logout'/>">Log out</a></p> --%>

<%-- 		<form:form --%>
<%-- 			action="${pageContext.request.contextPath}/j_spring_security_logout" --%>
<%-- 			method="POST"> --%>

<!-- 			<input type="submit" value="Logout" /> -->

<%-- 		</form:form> --%>
<%-- 	</sec:authorize> --%>
<!-- </div> -->

<sec:authorize access="isAuthenticated()">
	<p>
		<a class="login" href="<c:url value='/j_spring_security_logout'/>">Log out</a>
	</p>
</sec:authorize>