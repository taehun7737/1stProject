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
<link href="${conPath }/css/login.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/adminLogin.do" method="post">
			<table>
				<tr>
					<th id="caption">
						<h1>관리자 모드 로그인</h1>
					</th>
				</tr>
				<tr>
					<td>
						<input type="text" name="aId" value="${aId }" placeholder="아이디" required="required"><br><br>
						<input type="password" name="aPw" placeholder="비밀번호" required="required"></td>
					<td>
						<input type="submit" value="로그인" class="btn">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<hr>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>