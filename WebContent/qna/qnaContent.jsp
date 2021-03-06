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
	<form action="${conPath}/qnaModifyView.do" method="post">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="bNo" value="${qnaContent.bNo }">
		<table>
				<tr>
					<th colspan="2" id="caption">
						<h1>${qnaContent.bNo }번 글 상세보기</h1>
					</th>
				</tr>
				<tr><td>작성자</td>
						 <td>${qnaContent.mId}님</td>
				</tr>
				<tr><td>제목</td>
						 <td>${qnaContent.bHead }</td>
				</tr>
				<tr><td>내용</td>
						 <td><pre>${qnaContent.bContent}</pre></td>
				</tr>
				<tr><td colspan="2">
							<c:if test="${member.mId eq qnaContent.mId or not empty member}">
								<input type="submit" value="수정" class="btn">
							</c:if>
							<c:if test="${member.mId eq qnaContent.mId or not empty admin}">
								<input type="button" value="삭제" class="btn" onclick="location='${conPath}/qnaDelete.do?bNo=${qnaContent.bNo }&pageNum=${param.pageNum }'">
							</c:if>
							<c:if test="${member.mId eq qnaContent.mId or not empty member}">
								<input type="button" value="답변" class="btn" onclick="location='${conPath}/qnaReplyView.do?bNo=${qnaContent.bNo}&pageNum=${param.pageNum}'">
							</c:if>
							<input type="button" value="목록" class="btn" onclick="location='${conPath}/qnaList.do?pageNum=${param.pageNum }'">
		</table>
	</form>
</div>
<hr>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>

