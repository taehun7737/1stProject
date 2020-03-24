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
	height: 500px;
	margin: 50px auto 300px;
}
</style>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
	
		<br>
		<header>
			<h1>상품 주문 리스트</h1>
		</header>
		<table>
			<tr>
				<th>주문번호</th>
				<th>주문일자</th>
				<th>주문수량</th>
				<th>고객전화번호</th>
				<th>상품명</th>
				<th>상품가격</th>
			</tr>
			<c:if test="${list.size() eq 0 }">
				<tr>
					<td colspan="6">해당페이지의 글이 없습니다</td>
				</tr>
			</c:if>
			<c:if test="${list.size() != 0 }">
				<c:forEach items="${orderList }" var="dto">
					<tr>
						<td>${dto.oNo }</td>
						<td>${dto.oRdate }</td>
						<td>${dto.oQty }</td>
						<td>${dto.oPhone }</td>
						<td>${dto.pName }</td>
						<td>${dto.pPrice }</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<br><br><br>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
				[ <a href="${conPath }/ordersList.do?pageNum=${startPage-1}"> 이전</a> ]
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i == pageNum }">
						<b> [ ${i } ] </b>
					</c:if>
				<c:if test="${i != pageNum }">
					[ <a href="${conPath }/ordersList.do?pageNum=${i}&mId=${member.mId}">${i } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
				[ <a href="${conPath }/ordersList.do?pageNum=${endPage+1}"> 다음</a> ]
			</c:if>
		</div>
	</div>
	<hr>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>