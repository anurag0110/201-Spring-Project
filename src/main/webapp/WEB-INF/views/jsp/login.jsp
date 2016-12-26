<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery-1.11.1.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>CONTENT MANAGEMENT SYSTEM</title>
</head>
<body>
	<h1 align="center">Content Management System</h1>
	<div>
		<div>
			<br /> <br /> <br /> <br /> <br /> <br /> <br />
			<div style="text-align: center;">
				<form:form action="/userLogin" method="get">
					<input type="submit" value="GUEST" id="guest" />
				</form:form>
				<form:form action="/customLogin" method="get">
					<input type="submit" value="ADMIN" id="admin" />
				</form:form>
			</div>
		</div>

	</div>
</body>
</html>