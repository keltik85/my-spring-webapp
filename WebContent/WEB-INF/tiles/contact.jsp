<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<h2>Send Message</h2>

<sf:form method="post" commandName="message">

	<input type="hidden" name="flowExecutionKey"
		value="${flowExecutionKey}" />

	<table class="formtable">
		<tr>
			<td class="label">Your Name:</td>
			<td><sf:input class="control" path="name" type="text" /><br />
				<div class="error">
					<sf:errors path="name"></sf:errors>
				</div></td>
		</tr>

		<tr>
			<td class="label">Your EMail:</td>
			<td><sf:input class="control" path="email" type="text" /><br />
				<div class="error">
					<sf:errors path="email"></sf:errors>
				</div></td>
		</tr>

		<tr>
			<td class="label">Subject:</td>
			<td><sf:input class="control" path="subject" type="text" /><br />
				<div class="error">
					<sf:errors path="subject"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Your Message:</td>
			<td><sf:input class="control" path="content" name="content"
					type="text" /><br />
				<div class="error">
					<sf:errors path="content"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" value="Create account" type="submit" /></td>
		</tr>
	</table>

</sf:form>