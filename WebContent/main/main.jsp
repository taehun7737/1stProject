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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <script>
    $(document).ready(function(){
      $('.slider').bxSlider();
    });
    </script>
    <link href="${conPath }/css/main.css" rel="stylesheet">
</head>
	<c:if test="${not empty modifyResult}">
		<script>
			alert('${modifyResult}');
		</script>
	</c:if>
<body>
<jsp:include page="../main/header.jsp"/>

        <div class="slider_main">
            <div class="slider">
                <div class="img"><img src="${conPath }/img/슬라이드사진.jpg" alt="1" /></div>
                <div class="img"><img src="${conPath }/img/슬라이드사진2.jpg" alt="2" /></div>
                <div class="img"><img src="${conPath }/img/슬라이드사진3.jpg" alt="3" /></div>
            </div>
        </div>
        <div id="center">
            <div class="menu">
                <ul>
                    <li><h2>베스트 상품</h2></li>
                </ul>
            </div>
        </div>
        <hr>
        <div id="test">
            <div class="zoom">
                <a href="#" class="menu1"><img src="${conPath }/img/불닭맛.jpg" alt="불닭맛" /></a>
                <a href="#" class="menu2"><img src="${conPath }/img/훈제.jpg" alt="훈제" /></a>
                <a href="#" class="menu3"><img src="${conPath }/img/청양고추맛.jpg" alt="청양고추맛" /></a>
            </div>
            <div class="zoom_text">
                <a href="#"><h3>[프렌닭] 화끈한불맛 닭가슴살</h3>30팩 35,000원</a>
                <a href="#"><h3>[프렌닭] 부드러운 훈제 닭가슴살</h3>30팩 32,000원</a>
                <a href="#"><h3>[프렌닭] 매콤 청양고추맛 닭가슴살</h3>30팩 34,000원</a>
            </div>
        </div>
        <div id="side_banner">
            <h1>고객문의</h1>
            <h2>02-1234-1234</h2>
            <h4>이메일 문의</h4>
            <h4>taehun7737@naver.com</h4>
            <h4>평일 : 오전 09:00 ~ 오후 06:00</h4>
            <h4>점심 : 오전 12:00 ~ 오후 01:00</h4>
            <h4>주말 및 공휴일은 휴무입니다.</h4>
        </div>
    <hr>
    <jsp:include page="../main/footer.jsp"/>
</body>
</html>