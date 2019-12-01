<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<c:choose>
	<c:when test="${isIdDup eq true}">
		사용중인 아이디입니다.<br>
	</c:when>
	<c:otherwise>
		사용가능한 아이디입니다.<br>
		<button type="button" onclick="useId();">사용</button>
	</c:otherwise>
</c:choose> 
<form action="/member/joinIdDupCheck" method="get" name="wfrm">
	<input type="text" name="id" value="${userid}"> 
	<button type="submit">중복확인</button>
</form>

<script>
function useId() {
	opener.document.frm.id.value = wfrm.id.value;
	close();
}
</script>
</body>
</html>