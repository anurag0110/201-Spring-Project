<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery-1.11.1.js"></script>
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

	<div>
		<br /> <br /> <br /> <br /> <br /> <br /> <br />

		<form:form action="/viewBlog" method="GET" id="user-form"
			commandName="loginFo" style='text-align: center'>
			<h3 style="color: red;">
				<form:errors path="guestUserName" cssClass="error" />
			</h3>
			<div id="userview">
				UserName:
				<form:input id="guestUserName" path="guestUserName" />

				<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; <input
					type="submit" value="SUBMIT" id="submit" />
			</div>

		</form:form>
	</div>
</body>
</html>