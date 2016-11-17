<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
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
<h2>게시판</h2>
<hr />
<form method="post">
	<div>
		<span>제목:</span> <input type="text" name="title" />
	</div>
	<textarea id="body" name="body" class="smarteditor2"></textarea>
	<div>
		<button type="submit" class="btn btn-primary">
			<i class="icon-ok icon-white"></i> 저장하기
		</button>
		<a href="list.do?${ pagination.queryString }" class="btn"> <i
			class="icon-ban-circle"></i> 취소
		</a>
	</div>
</form>