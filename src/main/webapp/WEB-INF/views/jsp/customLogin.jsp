<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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


		<div>
			<br /> <br /> <br /> <br /> <br /> <br /> <br />
			<div id="msg" style="text-align: center;">
				<font color="red"> ${SPRING_SECURITY_LAST_EXCEPTION.message}
				</font>
			</div>
			</br>
			<form action="/appLogin" method="POST" style="text-align: center;">
				<div id="adminview">
					UserName : <input type="text" name="app_username" /><br /></br>
					Password &nbsp;: &nbsp;<input type="password" name="app_password" />

				</div>
				<div id="formsubmit">
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
						type="submit" value="LOGIN" id="submit" /> <input type="hidden"
						name="${_csrf.parameterName}" value="${_csrf.token}" />
				</div>

			</form>
		</div>
	</div>
</body>
</html>