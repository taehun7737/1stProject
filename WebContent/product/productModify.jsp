<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
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
	<form action="${conPath }/productModify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pNo" value="${productModifyView.pNo }">
		<input type="hidden" name="dbFileName" value="${productModifyView.pFileName }">
		<table>
			<caption><h1>${productModifyView.pNo }번 상품 수정</h1></caption>
			<tr><th>상품이름</th>
					<td><input type="text" name="pName" required="required" size="30"
								value="${productModifyView.pName }"></td>
			</tr>
			<tr><th>첨부파일</th>
					<td><input type="file" name="pFileName" class=""> <br>첨부파일:
							<c:if test="${not empty productModifyView.pFileName }">
						 		<a href="${conPath }/productFileBoardUp/${productModifyView.pFileName}" target="_blank">${productModifyView.pFileName}</a>
						 	</c:if>
						 	<c:if test="${empty productModifyView.pFileName }">
						 		첨부파일없음
						 	</c:if>
					</td>
			</tr>
			<tr><th>상품설명</th>
					<td><textarea rows="5" cols="32" 
							name="pDetail">${productModifyView.pDetail }</textarea></td>
			</tr>
			<tr><th>가격</th>
				<td><input type="text" name="pPrice" required="required" size="30" value="${productModifyView.pPrice }"></td>
			</tr>
			<tr><td colspan="2">
						<input type="submit" value="수정" class="btn">
						<input type="button" value="목록"  class="btn"
							onclick="location='${conPath}/productList.do?pageNum=${param.pageNum }'">
						<input type="reset" value="취소" class="btn"
						  onclick="history.back()">
					</td>
			</tr>
		</table>
	</form>
	</div>
	<hr>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>