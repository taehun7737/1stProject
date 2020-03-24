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
	<form action="${conPath}/productModifyView.do?pNo=${productContent.pNo}&pageNum=${param.pageNum }" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="pNo" value="${productContent.pNo}">
		<table>
				<tr>
					<th colspan="2" id="caption">
						<h1>${productContent.pNo }번 상품 상세보기</h1>
					</th>
				</tr>
				 <tr><td>제목</td>
				 		 <td>${productContent.pName }</td>
				 </tr>
				 <tr><td>첨부파일</td>
						 <td>
						 	<c:if test="${not empty productContent.pFileName }">
						 		<a href="${conPath }/productFileBoardUp/${productContent.pFileName}" target="_blank">${productContent.pFileName}</a>
						 	</c:if>
						 	<c:if test="${empty productContent.pFileName }">
						 		첨부파일없음
						 	</c:if>
						</td>
				 </tr>
				 <tr><td>상품설명</td>
				 	<td>${productContent.pDetail }</td>
				 </tr>
				 <tr><td>가격</td>
				 	<td>${productContent.pPrice }</td>
				 </tr>
				 <tr><td colspan="2">
				 		<c:if test="${not empty admin}">
				 			<input type="submit" value="수정" class="btn">
				 		</c:if>
				 			<input type="button" value="목록" class="btn" onclick="location='${conPath}/productList.do?pageNum=${param.pageNum }'">
				 		<c:if test="${not empty member }">
				 			<input type="button" value="주문" class="btn" onclick="location='${conPath}/ordersView.do?pageNum=${param.pageNum }&pNo=${productContent.pNo }'">
				 		</c:if>
		</table>
	</form>
</div>
<hr>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>

