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
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	var count = 1;
	var dataId, dialog;
	var render = function(vo) {
		if (vo.num == 0) {
			var htmls = "<tr id="+vo.no+">"
					+ "<td>"
					+ count
					+ "</td>"
					+ "<td>"
					+ vo.name
					+ "</td>"
					+ "<td>"
					+ vo.num
					+ "</td>"
					+ "<td>"
					+ vo.description
					+ "</td>"
					+ "<td>"
					+ "<img src='${pageContext.request.contextPath}/assets/images/delete.jpg' data-id="+vo.no+">"
					+ "</td>" + "</tr>";
		} else {
			var htmls = "<tr id="+vo.no+">"
					+ "<td>"
					+ count
					+ "</td>"
					+ "<td>"
					+ vo.name
					+ "</td>"
					+ "<td>"
					+ vo.num
					+ "</td>"
					+ "<td>"
					+ vo.description
					+ "</td>"
					+ "<td>"
					+ "&nbsp"
					+ "</td>" + "</tr>";
		}
		count++;
		$("#tabletop").prepend(htmls);
	}
	var fetchList = function() {
		var no = $("#tabletop").data("id");
		$.ajax({
			url : "${pageContext.request.contextPath }/blog/api/list?no=" + no,
			type : "get",
			dataType : "json",
			data : "",
			success : function(response) {
				if (response.result != "success") {
					console.error(response.message);
					isEnd = true;
					return;
				}
				if (response.data != null) {
					//redering
					$(response.data).each(function(index, vo) {
						render(vo);
					});
				}

			},
			error : function(jqXHR, status, e) {
				console.error(status + ":" + e);
			}

		});
	}
	$(function() {
		$("#cate-button")
				.click(
						function(event) {
							event.preventDefault();

							var name = $("input[name='name']").val();
							var description = $("input[name='description']")
									.val();
							var param = "name=" + name + "&description="
									+ description;
							//ajax insert 
							$
									.ajax({
										url : "${pageContext.request.contextPath }/blog/api/insert?"
												+ param,
										type : "get",
										dataType : "json",
										data : "",
										contentType : "application/json",
										success : function(response) {
											if (response.result != "success") {
												console.error(response.message);
												return;
											}
											render(response.data);
										},
										error : function(jqXHR, status, e) {
											console.log(status + ":" + e);
										}
									});
						});

		fetchList();

	});

	$(function() {
		dialog = $("#dialog-confirm")
				.dialog(
						{
							autoOpen : false,
							resizable : false,
							height : "auto",
							width : 400,
							modal : true,
							buttons : {
								"삭제" : function() {
									$
											.ajax({
												url : "${pageContext.request.contextPath }/blog/api/delete?no="
														+ dataId,
												type : "get",
												dataType : "json",
												data : "",
												contentType : "application/json",
												success : function(response) {
													if (response.result != "success") {
														console
																.error(response.message);
														return;
													}
													$("#" +response.data).remove();
												},
												error : function(jqXHR, status,
														e) {
													console.log(status + ":"
															+ e);
												}
											});
									$(this).dialog("close");
								},
								"취소" : function() {
									$(this).dialog("close");
								}
							}
						});
		$(document).on("click", "#tabletop td img", function(event) {
			event.preventDefault();
			dataId = $(this).data("id");
			dialog.dialog("open");
		});

	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blogheader.jsp"></c:import>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-menu.jsp">
					<c:param name="menu" value="category" />
				</c:import>
				<table class="admin-cat">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
					<tbody id="tabletop" data-id="${no}">
					</tbody>
				</table>
				<h4 class="n-c">새로운 카테고리 추가</h4>
				<table id="admin-cat-add">
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" name="name"></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" name="description"></td>
					</tr>
					<tr>
						<td class="s">&nbsp;</td>
						<td><button id="cate-button">카테고리 추가</button></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="dialog-confirm" title="카테고리 삭제" style="display: none">
			<p>
				<span class="ui-icon ui-icon-alert"
					style="float: left; margin: 12px 12px 20px 0;"></span>삭제하시겠습니까?
			</p>
		</div>
		<c:import url="/WEB-INF/views/includes/blogfooter.jsp"></c:import>
	</div>

</body>
</html>