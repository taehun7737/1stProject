<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/reviewWrite.css" rel="stylesheet">
<style>
	#content_form {
		width: 800px; height:700px;
		margin: 100px auto 0px;
	}
</style>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
<div id="content_form">
	<form action="${conPath }/productWrite.do" method="post" enctype="multipart/form-data">
		<table>
				<tr>
					<th id="caption">
						<h1>상품 등록</h1>
					</th>
				</tr>
			<tr><td><input type="text" name="pName" placeholder="상품 이름" required="required" size="30"></td></tr>
			<tr><td><input type="file" name="pPhoto"></td></tr>
			<tr><td><textarea name="pDetail" placeholder="상품 설명" rows="8" cols="32"></textarea></td></tr>
			<tr><td><input type="text" name="pPrice" placeholder="상품 가격" required="required" size="30"></td></tr>
			<tr><td colspan="2">
						<input type="submit" value="등록" class="btn">
						<input type="reset" value="취소" class="btn">
						<input type="button" value="목록" class="btn"
							onclick="location.href='${conPath}/productList.do'">
		</table>
	</form>
</div>
<hr>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>