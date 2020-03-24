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
	<form action="${conPath}/ordersDo.do" method="post">
		<input type="hidden" name="pNo" value="${product.pNo}">
		<input type="hidden" name="pPrice" value="${product.pPrice }">
		<input type="hidden" name="mId" value="${member.mId }">
		<table>
				<tr>
					<th colspan="4" id="caption">
						<h1>${product.pNo }번 상품 주문하기</h1>
					</th>
				</tr>
				<tr><td>상품명</td>
				 	<td>${product.pName }</td>
				</tr>
				<tr><td>상품설명</td>
					<td>${product.pDetail }</td>
				</tr>
				<tr><td>가격</td>
					<td>${product.pPrice }</td>
				</tr>
				<tr><td>배송지</td>
					<td><input type="text" name="oAddress" required="required" size="30"></td>
				</tr>
				<tr><td>수량</td>
					<td><input type="number" name="oQty" required="required" size="30"></td>
				</tr>
				<tr><td>전화번호</td>
					<td><input type="tel" name="oPhone" required="required" size="30"></td>
				</tr>
				<tr><td colspan="2">
					<input type="submit" value="주문" class="btn">
					<input type="button" value="목록" class="btn" onclick="location='${conPath}/productList.do?pageNum=${param.pageNum }'">
				</td></tr>
		</table>
	</form>
</div>
<hr>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>

