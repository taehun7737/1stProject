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
<link href="${conPath }/css/join.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('#mId').keyup(function(){
			var mId = $('#mId').val();
			$.ajax({
				url : '${conPath}/idConfirm.do',
				type : 'get',
				dataType : 'html',
				data : "mId="+mId,
				success : function(data){
					$('#idConfirmResult').html(data).css('color','red');
				}
			});//ajax
		});
	});
</script>
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
		<form action="${conPath }/join.do" method="post" >
			<table>
				<tr>
					<th id="caption">
						<h1>회원가입</h1>
					</th>
				</tr>
				<tr>
					<td><input type="text" name="mId" id="mId" placeholder="아이디 *" required="required">
					<div id="idConfirmResult"> &nbsp; &nbsp; &nbsp; </div></td>
				</tr>
				<tr>
					<td><input type="password" name="mPw" placeholder="비밀번호 *" required="required"></td>
				</tr>
				<tr>
					<td><input type="text" name="mName" placeholder="이름 *" required="required"></td>
				</tr>
				<tr>
					<td><input type="email" name="mEmail" placeholder="이메일" ></td>
				</tr>
				<tr>
					<td><input type="text" name="mBirth" id="datepicker" placeholder="생년월일" ></td>
				</tr>
				<tr>
					<td><input type="text" name="mAddress" placeholder="주소"></td>
				</tr>
				<tr>
					<td><input type="tel" name="mPhone" placeholder="전화번호 *" required="required"></td>
				</tr>
				<tr><td colspan="2">
						<input type="submit" value="회원가입" class="btn">
						<input type="button" value="로그인" class="btn" onclick="location.href='${conPath}/member/login.jsp'">
			</table>
		</form>
	</div>
	<hr>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>
