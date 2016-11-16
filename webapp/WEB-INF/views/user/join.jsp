<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<title>JBlog</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#join-form").submit(function() {
			if ($("#name").val() == "") {
				$("#dialog p").text("이름은 필수 입력 항목입니다.");
				$("#dialog").dialog();
				$("#name").focus();
				return false;
			}

			if ($("#id").val() == "") {
				$("#dialog p").text("ID는 필수 입력 항목입니다.");
				$("#dialog").dialog();
				$("#id").focus();
				return false;
			}

			if ($("#chkid").is(":visible") == false) {
				$("#dialog p").text("ID 중복체크 하세요.");
				$("#dialog").dialog();
				return false;
			}
			if ($("input[type='password']").val() == "") {
				$("#dialog p").text("비밀번호는 필수 입력 항목입니다.");
				$("#dialog").dialog();
				$("input[type='password']").focus();
				return false;
			}
			if ($("#agree-prov").is(":checked") == false) {
				$("#dialog p").text("약관에 동의하세요.");
				$("#dialog").dialog();
				return false;
			}
		});

		$("#id").change(function() {
			$("#chkid").hide();
			$("#btn-checkid").show();
		});
		$("#btn-checkid")
				.click(
						function() {
							var id = $("#id").val();
							if (id == "") {
								return;
							}

							$
									.ajax({
										url : "${pageContext.request.contextPath }/user/api/chkid?id="
												+ id,
										type : "get",
										dataType : "json",
										data : "",
										contentType : "application/json",
										success : function(response) {
											if (response.result != "success") {
												console.log(response.message);
												return;
											} else if (response.data == "exist") {
												$("#dialog p").text(
														"이미존재하는  ID입니다.");
												$("#dialog").dialog();
												$("#id").val("").focus();
												return;
											} else if (response.data == "not exist") {
												$("#chkid").show();
												$("#btn-checkid").hide();
												return;
											}
										},
										error : function(jqXHR, status, e) {
											console.log(status + ":" + e);
										}
									});
						});
	});
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<form:form modelAttribute="jusers" class="join-form" id="join-form" method="post">
		<label class="block-label" for="name">이름</label>
		<form:input path="name"/>
		<p style="text-align: left; color: red">
			<form:errors path="name" />
		</p>
		<label class="block-label" for="id">ID</label>
		<form:input path="id"/>
		<input id="btn-checkid" type="button" value="id 중복체크"> <img
				id="img-checkemail" style="display: none;"
				src="${pageContext.request.contextPath}/assets/images/check.png">
				<span id="chkid" style="display: none;">사용가능</span>
		<p style="text-align: left; color: red">
			<form:errors path="id" />
		</p>
		<label class="block-label" for="password">PASSWORD</label>
		<form:password path="password"/>
		<p style="text-align: left; color: red">
			<form:errors path="password" />
		</p>
		<fieldset>
			<legend>약관동의</legend>
			<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
			<label class="l-float">서비스 약관에 동의합니다.</label>
		</fieldset>
		<input type="submit" value="가입하기">
		</form:form>
	</div>
	<div id="dialog" title="회원 가입 알림" style="display: none">
		<p></p>
	</div>
</body>
</html>
