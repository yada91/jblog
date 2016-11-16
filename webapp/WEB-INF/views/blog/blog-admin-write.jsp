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
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blogheader.jsp"></c:import>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-menu.jsp">
					<c:param name="menu" value="write" />
				</c:import>

				<form:form modelAttribute="post"
					action="${pageContext.request.contextPath}/${id}/admin/write"
					method="post">
					<table class="admin-cat-write">
						<tr>
							<td class="t">제목</td>
							<td><form:input path="title" size="60" /> <form:select
									path="categoryNo">
									<c:forEach items="${list}" var="vo">
										<form:option value="${vo.no }" label="${vo.name }">
										</form:option>
									</c:forEach>
								</form:select></td>
						</tr>
						<tr>
							<td class="t">내용</td>
							<td><textarea name="content"></textarea></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td class="s"><input type="submit" value="포스트하기"></td>
						</tr>
					</table>
				</form:form>

			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blogfooter.jsp"></c:import>
	</div>
</body>
</html>