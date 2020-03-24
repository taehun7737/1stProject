<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
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
			<form action="${conPath }/login.do" method="post">
				<table>
				<tr>
				<th id="caption">
					<h1>로그인</h1>
					<h4>로그인을 하시면 편리하게 사이트를 이용하실 수 있습니다.</h4><br><br><br>
				</th>
				</tr>
					<tr>
						<td>
							<input type="text" name="mId" placeholder="아이디" required="required"><br><br>
							<input type="password" name="mPw" placeholder="비밀번호" required="required">
						</td>
						<td>
							<input type="submit" value="로그인" class="btn">
						</td>
					</tr>
					<tr>
						<td id=join>
							<a href="${conPath}/member/join.jsp"><u>회원가입하러가기 ☞</u></a>
						</td>
					</tr>
				</table>
				<br><br><br>
			</form>
		</div>
    	<hr>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>