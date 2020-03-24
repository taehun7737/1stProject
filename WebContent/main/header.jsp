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
	<header>
		<div class="mid">
			<div class="logo">
				<a href="${conPath }/main/main.jsp"><img src="${conPath }/img/메인로고.png" width='150' /></a>
			</div>
			<c:if test="${empty member and empty admin}"> <%-- 로그인 전 화면 --%>
			<div class="gnb">
				<ul>
					<li><a href="${conPath }/member/join.jsp">회원가입</a></li>
					<li><a href="${conPath }/member/login.jsp">로그인</a></li>
					<li><a href="${conPath }/main/main.jsp">홈</a></li>
				</ul>
			</div>
		</c:if>
		<c:if test="${not empty member and empty admin}"> <%-- 사용자 모드 로그인 화면--%>
			<div class="gnb">
				<ul>
					<li><a href="${conPath }/ordersList.do?mId=${member.mId}">주문리스트</a></li>
					<li><a href="${conPath }/logout.do">로그아웃</a></li>
					<li><a href="${conPath }/modifyView.do">정보수정</a></li>
					<li><a href="${conPath }/main/main.jsp">홈</a></li>
					<li><a>${member.mName }님 &nbsp; ▶</a></li>	
				</ul>
			</div>
		</c:if>
		<c:if test="${empty member and not empty admin}"> <%-- 관리자 모드 로그인 화면--%>
			<div class="gnb">
				<ul>
					<li><a href="${conPath }/allView.do">회원리스트</a></li>
					<li><a href="${conPath }/logout.do">관리자모드나가기</a></li>
					<li><a href="${conPath }/main/main.jsp">홈</a></li>
					<li><a>${admin.aName }님 &nbsp; ▶</a></li>	
				</ul>
			</div>
		</c:if>
		</div>
		
		<div class="lnb">
			<ul>
				<li><a href="${conPath }/qnaList.do">QnA</a></li>
				<li><a href="${conPath }/reviewList.do">구매후기</a></li>
				<li><a href="${conPath }/productList.do">상품리스트</a></li>
			</ul>
		</div>
	</header>
</body>
</html>