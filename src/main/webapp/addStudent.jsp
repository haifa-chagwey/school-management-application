<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>FooBar University</title>
		 <style type="text/css"><%@include file="css/style.css" %></style>
		 <style type="text/css"><%@include file="css/add-student-style.css" %></style>
		 
	</head>

	<body>

		<div id="wrapper">
			<div id="header">
				<h2>FooBar University</h2>
			</div>
		</div>

		<h2>Add Student</h2>

		<form action="/web_student_tracker_IW/" method="post">
		
			<input type="hidden" name="command" value="add">
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><input type="text" name="firstName" /></td>
					</tr>
					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lastName" /></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
			
		</form>

		<a href="/web_student_tracker_IW/">back to students list</a>

	</body>

	</html>