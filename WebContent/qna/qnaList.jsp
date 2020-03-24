<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath}/css/review.css" rel="stylesheet">
<style>
#content_form {
	height: 400px;
	margin: 50px auto 300px;
}
</style>
</head>
<body>
	<c:if test="${not empty resultMsg }">
		<script>
			alert('${resultMsg}');
		</script>
	</c:if>
	<c:if test="${not empty loginErrorMsg}">
		<script>
			alert('${loginErrorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty errorMsg}">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<br>
		<header>
			<h1>QnA</h1>
		</header>
		<table>
			<tr>
				<th nowrap width='60'>글번호</th>
				<th nowrap width='70'>작성자</th>
				<th nowrap width='200'>제목</th>
				<th nowrap width='80'>작성일</th>
				<th nowrap width='40'>조회수</th>
			</tr>
			<c:if test="${list.size() eq 0 }">
				<tr>
					<td colspan="5">해당페이지의 글이 없습니다</td>
				</tr>
			</c:if>
			<c:if test="${list.size() != 0 }">
				<c:forEach items="${qnaList }" var="dto">
					<tr>
						<td>${dto.bNo }</td>
						<td><c:if test="${empty dto.aId }">
					${dto.mId }
				</c:if> <c:if test="${empty dto.mId }">
					${dto.aId }
				</c:if> <c:if test="${not empty dto.mId && not empty dto.aId }">
					${dto.mId }
				</c:if></td>
						<td class="left"><a
							href="${conPath }/qnaContent.do?bNo=${dto.bNo}&pageNum=${pageNum}">
								<c:forEach var="i" begin="1" end="${dto.bIndent }">
									<c:if test="">&nbsp; &nbsp; &nbsp;</c:if>
									<c:if test="${i == dto.bIndent }">&nbsp; &nbsp; &nbsp;└</c:if>
								</c:forEach> ${dto.bHead }
						</a></td>
						<td><fmt:formatDate value="${dto.bRdate }"
								pattern="yyyy년 MM월 dd일 (E)" /></td>
						<td>${dto.bHit }</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div id="bottom">
			<c:if test="${not empty member }">
				<a href="${conPath }/qnaWriteView.do">글쓰기</a>
			</c:if>
		</div>
		<br><br><br>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/qnaList.do?pageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b> [ ${i } ] </b>
				</c:if>
				<c:if test="${i != pageNum }">
				[ <a href="${conPath }/qnaList.do?pageNum=${i}"> ${i } </a> ]
			</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/qnaList.do?pageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
		</div>
	</div>
	<hr>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>