<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/content.css" rel="stylesheet">
<style>
	#content_form {
		width: 800px; height:500px;
		margin: 100px auto 0px;
	}
</style>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
<div id="content_form">
	<form action="${conPath }/qnaReply.do" method="post">
		<input type="hidden" name="bGroup" value="${QnaReplyView.bGroup }">
		<input type="hidden" name="bStep" value="${QnaReplyView.bStep }">
		<input type="hidden" name="bIndent" value="${QnaReplyView.bIndent }">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="aId" value="${admin.aId }">
		<input type="hidden" name="mId" value="${member.mId}">
		<table>
			<caption><h1>${QnaReplyView.bNo }번글의 답변</h1></caption>
			<tr><th>작성자</th>
					<td><input type="text" required="required" size="30"
								value="${member.mId }" readonly="readonly"></td>
			</tr>
			<tr><th>제목</th>
				<td><input type="text" name="bHead" required="required" size="30"
							value="[답]${QnaReplyView.bHead }"></td>
			</tr>
			<tr><th>내용</th>
				<td><textarea name="bContent" rows="3" 
							required="required" cols="32"></textarea></td>
			</tr>
			<tr><td colspan="2">
						<input type="submit" value="답변쓰기" class="btn">
						<input type="button" value="목록" class="btn" onclick="location.href='${conPath}/qnaList.do'">
						<input type="reset" value="취소" class="btn" onclick="history.back()">
		</table>
	</form>
</div>
<hr>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>