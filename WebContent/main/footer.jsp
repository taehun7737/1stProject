<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/main.css" rel="stylesheet">
</head>
<body>
	<div id="footer">
		<ul>
			<li><img src="${conPath }/img/로고.png" alt="로고" /></li>
			<li><img src="${conPath }/img/address.JPG" alt="회사주소" /></li>
			<li><b><a href="${conPath }/adminloginView.do">관리자 모드</a></b></li>
		</ul>
	</div>
</body>
</html>