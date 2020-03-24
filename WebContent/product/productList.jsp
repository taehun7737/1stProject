<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
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
<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<header>
			<h1>상품리스트</h1>
		</header>
		<table>
		<tr><th nowrap width='70'>상품번호</th>
			<th>상품이름</th>
			<th nowrap width='150'>가격</th>
		</tr>
		<c:if test="${list.size() eq 0 }">
		<tr><td colspan="3">해당페이지의 글이 없습니다</td></tr>
		</c:if>
		<c:if test="${list.size() != 0 }">
			<c:forEach items="${productList }" var="dto">
				<tr><td>${dto.pNo }</td>
					<td class="left">
						<a href="${conPath }/productContent.do?pNo=${dto.pNo}&pageNum=${pageNum}">${dto.pName }</a>
					</td>
					<td>${dto.pPrice }</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<div id="bottom">
			<c:if test="${not empty admin }"><a href="${conPath }/productWriteView.do">상품등록</a></c:if>
	</div>
			<br><br><br>
	<div class="paging">
		<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/productList.do?pageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == pageNum }">
				<b> [ ${i } ] </b>
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath }/productList.do?pageNum=${i}"> ${i } </a> ]
			</c:if>
		</c:forEach>
		<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/productList.do?pageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
	</div>
	</div>
	<hr>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>