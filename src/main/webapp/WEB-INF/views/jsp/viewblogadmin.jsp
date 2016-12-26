<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.11.1.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
		<div style="position: relative;">
			<a class="prev" id="prev">&#10094;</a>
			<div style="text-align: center;">
				<c:choose>
					<c:when test="${not empty blogFo}">
						<h3 style="color: black;">Title:${blogFo.title}</h3>
						<h3 style="color: black">
							Description: ${blogFo.description}<br />
						</h3>
						<h3 style="color: black;">
							<b>Image:</b>
						</h3>
						<img src="data:image/jpg;base64,${blogFo.imageSrc}"
							class="mySlides" alt="No image" width="250" height="250">
					</c:when>
					<c:otherwise>
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<h1 style="color: red">NO Record Found</h1>
					</c:otherwise>
				</c:choose>
			</div>
			<a class="next" id="next">&#10095;</a>
			<div style="text-align: center;">
				<c:if test="${not empty blogFo.blogCommentsFo}">
					<h3>User Comments</h3>
					<c:forEach items="${blogFo.blogCommentsFo}" var="blogComment">
						<div style="text-align: center;">
							<b style="font-size: large; color: black;">${blogComment.userName}:</b>
							${blogComment.comment} <br /></br>
						</div>
					</c:forEach>
				</c:if>
			</div>

		</div>
	</div>

</body>
</html>
<script>
	jQuery(document).ready(
			function($) {
				var slideIndex = '${blogId}';
				if (slideIndex == 1) {
					$("#prev").hide();
				}
				if ('${blogFo.count}' <= 1) {
					$("#prev").hide();
					$("#next").hide();
				}
				if ('${blogFo.count}' == slideIndex) {
					$("#next").hide();
				}
				$("#next").click(
						function() {
							var blogId = parseInt('${blogId}') + 1;
							window.location
									.replace("/viewBlogForAdmin?guestUserName="
											+ '${guestUserName}' + "&blogId="
											+ blogId);

						});
				$("#prev").click(
						function() {
							var blogId = parseInt('${blogId}') - 1;
							window.location
									.replace("/viewBlogForAdmin?guestUserName="
											+ '${guestUserName}' + "&blogId="
											+ blogId);

						});
			});
</script>