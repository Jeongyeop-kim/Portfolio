<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<title></title>
	
<style>
	#wrap {
		width: 500px;
		height: 500px;
	}

</style>

</head>
<body>

<div id="wrap">
	<form name="frm" action="/member/memberFindPasswd" method="post">
		사용자 확인을 위해 아이디와 이메일 주소를 입력하세요.<br>
		아이디: <input type="text" name="id" class="id"><br>
		이메일: <input type="text" name="email" class="email">
	<input type="submit" value="확인하기">
	</form>
</div>

</body>
</html>