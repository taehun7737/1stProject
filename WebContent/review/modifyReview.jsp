<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath }/css/style.css" rel="stylesheet">
	<style>
		#content_form {
			width: 800px; height:400px;
			margin: 100px auto 0px;
		}
</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<form action="${conPath }/modifyReview.do" method="post">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="eNo" value="${modifyReview.eNo }">
		<table>
			<caption>${modifyReview.eNo }번 글 수정(page:${param.pageNum })</caption>
			<tr><th>작성자</th>
					<td><input type="text" required="required" size="30"
								value="${modifyReview.mName }(${modifyReview.mId })" readonly="readonly"></td>
			</tr>
			<tr><th>제목</th>
					<td><input type="text" name="eHead" required="required" size="30"
								value="${modifyReview.eHead }"></td>
			</tr>
			<tr><th>본문</th>
					<td><textarea rows="5" cols="32" 
							name="eContent">${modifyReview.eContent }</textarea></td>
			</tr>
			<tr><td colspan="2">
						<input type="submit" value="수정" class="btn">
						<input type="button" value="목록"  class="btn"
							onclick="location='${conPath}/reviewList.do.do?pageNum=${param.pageNum }'">
						<input type="reset" value="취소" class="btn"
						  onclick="history.back()">
					</td>
			</tr>
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>