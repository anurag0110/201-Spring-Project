<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery-1.11.1.js"></script>
<title>Content Management System</title>
</head>
<body>
	<h1 align="center">Content Management System</h1>
	<div>
		<div>
			<br /> <br /> <br /> <br /> <br /> <br /> <br />
			<div style="text-align: center;">
				<a href="/secure/home"> <input type="button" value="ADD BLOG"
					id="guest" />
				</a></br>
				</br> <a href="/viewBlogForAdmin?guestUserName=admin&blogId=1"> <input
					type="button" value="VIEW BLOG" id="admin" />
				</a></br>
				</br>
				<form action="/appLogout" method="POST">
					<input type="submit" value="LOGOUT" /> <input type="hidden"
						name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		</div>
		<div>
			<div style='text-align: center'>
				<form:form commandName="blogFo" action="saveBlog" id="add-form"
					style='text-align: center' method="POST"
					enctype="multipart/form-data">

					<h1 style="color: black">
						<c:if test="${msg!=null}">
                  ${msg}
                 </c:if>
					</h1>
					<div style='text-align: center'>
						<table>
							<tr>
								<td><label for="title">TITLE: </label></td>
								<td><form:input id="title" path="title" /></td>
								<td style="color: red;"><form:errors path="title"
										cssClass="error" /></td>
							</tr>
							<tr>
								<td><label for="description">DESCRIPTION: </label></td>
								<td><form:textarea rows="10" cols="40" id="description"
										path="description" /></td>
								<td style="color: red;"><form:errors path="description"
										cssClass="error" /></td>
							</tr>
							<tr>
								<td><label for="image">BLOG: </label></td>
								<td><input type="file" name="file" id="photo" />(Upload
									JPEG/PNG File)</td>
								<td style="color: red;"><c:if test="${nofile!=null}">
                 					 ${nofile}
                 					</c:if></td>
							</tr>
							<tr>
								<td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
									id="reset" type="reset" tabindex="4"> <input
									id="submit" type="submit" tabindex="5" value="ADD">
								</td>
							</tr>
						</table>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>