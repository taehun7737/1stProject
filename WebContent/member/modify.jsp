<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/modify.css" rel="stylesheet">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
  $( function() {
	  $( "#datepicker" ).datepicker({
	    	dateFormat : 'yy-mm-dd',
	    	monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    	showMonthAfterYear : true,
	    	yearSuffix : '년',
	    	showOtherMonths : true,
	    	dayNamesMin:['일','월','화','수','목','금','토']
	    });
  });
  </script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/modify.do" method="post">
			<table>
				<tr>
					<th id="caption">
						<h1>회원정보수정</h1>
					</th>
				</tr>
				<tr>
					<td><input type="text" name="mId" placeholder="아이디 *" value="${member.mId }"
						readonly="readonly"></td>
				</tr>
				<tr>
					<td><input type="password" name="mPw" placeholder="비밀번호 *" value="${member.mPw }"
						required="required"></td>
				</tr>
				<tr>
					<td><input type="text" name="mName" placeholder="이름 *" value="${member.mName }"
						required="required"></td>
				</tr>
				<tr>
					<td><input type="email" name="mEmail" placeholder="이메일"
						value="${member.mEmail }"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="text" name="mBirth" id="datepicker" placeholder="생년월일"
						value="${member.mBirth }"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="text" name="mAddress" placeholder="주소"
						value="${member.mAddress }"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="tel" name="mPhone" placeholder="전화번호 "
						value="${member.mPhone }"></td>
				</tr>
				<tr>
					<td colspan="3">
						<input type="submit" value="정보수정" class="btn">
						<input type="reset" value="취소" class="btn">
						<input type="reset" value="이전"	class="btn" onclick="history.go(-1)"></td>
				</tr>
			</table>
		</form>
	</div>
	<hr>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>