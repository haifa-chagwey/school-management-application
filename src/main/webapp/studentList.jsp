<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="ISO-8859-1">
			<title>FooBar University</title>
			 <style type="text/css"><%@include file="css/style.css" %></style>
		</head>

		<body>

			<div id="wrapper">
				<div id="header">
					<h2>FooBar University</h2>
				</div>
			</div>
			<input type="button" value="Add Student" onclick="window.location.href='addStudent.jsp'; return false;"
				class="add-student-button" />

			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>

				</tr>
				<c:forEach var="tempStudent" items="${student_list}">
					<!-- set up a link for each student-->
					<c:url var="tempLink" value="/">
						<c:param name="command" value="load" />
						<c:param name="studentId" value="${tempStudent.id}" />
					</c:url>

					<c:url var="deleteLink" value="/">
						<c:param name="command" value="delete" />
						<c:param name="studentId" value="${tempStudent.id}" />
					</c:url>

					<tr>
						<td>${tempStudent.firstName}</td>
						<td>${tempStudent.lastName}</td>
						<td>${tempStudent.email}</td>
						<td>
							<a href="${tempLink}">Update</a> |
							<a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>

		</body>

		</html>