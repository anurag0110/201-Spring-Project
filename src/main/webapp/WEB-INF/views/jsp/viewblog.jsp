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
	<div style="width: 100%">
		<div style="position: relative;">
			<a class="prev" id="prev">&#10094;</a>
			<div style="text-align: right; width: 100%; color: black;">


				<h2>
					Welcome ${guestUserName}
					<form action="/appLogout" method="POST">
						<input type="submit" value="LOGOUT" /> <input type="hidden"
							name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
				</h2>
			</div>

			<div style="text-align: center;">
				<div style="text-align: center;">
					<c:choose>
						<c:when test="${not empty blogFo}">
							<h3 style="color: black;">Title:${blogFo.title}</h3>
							<h3 style="color: black;">
								Description: ${blogFo.description}<br />
							</h3>
							<h3 style="color: black;">
								<b>Image:</b>
							</h3>
							<img src="data:image/jpg;base64,${blogFo.imageSrc}"
								class="mySlides" alt="No image" width="300" height="300">
							<br />
							<br />
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
				<c:if test="${not empty blogFo}">
					<div style="text-align: center:" id="userComment"></div>
					<div style="text-align: center;">
						<form:form action="/saveBlogComments"
							modelAttribute="blogCommentsFo" method="post" id="comments-form">

							<table align="center">
								<tr>
									<td style="color: black">Comments:</td>
									<td><form:textarea id="comment" path="comment" /> <input
										type="hidden" value="${guestUserName}" /></td>
								</tr>
								<tr>
									<td colspan="2" style="text-align: center;"><input
										type="submit" value="SUBMIT" /></td>
								</tr>
							</table>
						</form:form>
					</div>
				</c:if>
			</div>


		</div>
		<c:if test="${not empty blogFo}">
			<div style="display: inline-block; overflow: hidden;">
				<div style="text-align: center; color: black; font-size: xx-large;">Tweets
					- About ${blogFo.title}</div>
				<div style="display: inline-block; overflow: hidden;"></div>


			</div>
		</c:if>
	</div>

</body>
</html>
<script>
	jQuery(document)
			.ready(
					function($) {
						refreshData(event);
						var slideIndex = '${blogId}';
						var chk = '${blogFo}'
						if ($.isEmptyObject(chk)) {
							$("#leftcolumn").width(1500);
						}
						;
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
											.replace("/viewBlog?guestUserName="
													+ '${guestUserName}'
													+ "&blogId=" + blogId);

								});
						$("#prev").click(
								function() {
									var blogId = parseInt('${blogId}') - 1;
									window.location
											.replace("/viewBlog?guestUserName="
													+ '${guestUserName}'
													+ "&blogId=" + blogId);

								});
						$
								.ajax({
									type : "GET",
									url : '/twitter/home',
									dataType : 'json',
									data : {
										'title' : '${blogFo.title}'
									},
									success : function(json) {
										var $el = $("#tweets");
										var htmlContent = '<div class="row"  style="display: inline-block;overflow: hidden;">';
										$el.empty(); // remove old options 
										$
												.each(
														json,
														function(value, key) {
															htmlContent += '<marquee  scrolldelay="500" direction="up">'
																	+ key.fromUser
																	+ '</br>';
															htmlContent += key.text
																	+ '</marquee>'

														});
										htmlContent += '</div>';
										$el.html(htmlContent);
									}
								});

						$("#comments-form").submit(function(event) {
							refreshData(event);
						});
						function refreshData(event) {
							event.preventDefault();
							var data = {
								"comment" : $("#comment").val(),
								"userName" : '${guestUserName}',
								"blogId" : '${blogId}'
							}
							$
									.ajax({
										type : "POST",
										contentType : "application/json",
										url : "/saveBlogComments",
										data : JSON.stringify(data),
										dataType : 'json',
										success : function(data) {
											console.log("SUCCESS: ", data);
											var htmlCnt = "<div style='text-align: center;'><h2 style='color: black;'>User Comments</h2>";
											$
													.each(
															data,
															function(index,
																	element) {
																htmlCnt += "<b style='font-size: large;color: black'>"
																		+ element.userName
																		+ ":</b>";
																htmlCnt += element.comment
																		+ "<br /></br></div>";
															});
											$("#userComment").html(htmlCnt);
											$("#comment").val("");
										},
										error : function(e) {
											console.log("ERROR: ", e);
											//	display(e);
										},
										done : function(e) {
											//	console.log("DONE");
										}
									});
						}

					})
</script>