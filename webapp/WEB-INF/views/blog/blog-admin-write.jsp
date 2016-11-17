<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script
	src="${pageContext.request.contextPath}/assets/se2/js/HuskyEZCreator.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/se2/init.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	$(function() {
		$("textarea.smarteditor2").each(function() {
			var textareaId = $(this).attr("id");
			se2_init(textareaId);
		});

		$("[type=submit]").click(function() {
			se2_syncData();
		});

	});
</script>

</head>

<body>
	<div id="container">
		<div id="header">
			<h1>
				<a href="${pageContext.request.contextPath}/${id}">${blog.title }
				</a>
			</h1>
			<ul class="menu">

				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<li><a
					href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">블로그
						관리</a></li>
			</ul>
		</div>

		<div id="wrapper">
			<div id="smartcontent">
				<ul class="admin-menu">
					<li><a
						href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
					<li><a
						href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
					<li class="selected">글작성</li>

				</ul>
				<form:form modelAttribute="post"
					action="${pageContext.request.contextPath}/${id}/admin/write"
					method="post" enctype="multipart/form-data">
					<div>
						<span>제목:</span>
						<form:input path="title" name="title" />
						<form:select path="categoryNo">
							<c:forEach items="${list}" var="vo">
								<form:option value="${vo.no }" label="${vo.name }">
								</form:option>
							</c:forEach>
						</form:select>
					</div>
					<textarea id="content" name="content" class="smarteditor2"></textarea>
					<input type="submit" value="포스트하기">
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blogfooter.jsp"></c:import>
	</div>
</body>
</html>