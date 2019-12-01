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
	<form name="frm" action="/member/memberFindId" method="post">
		사용자 확인을 위해 이름과 이메일 주소를 입력하세요.<br>
		이름: <input type="text" name="name" class="name"><br>
		이메일: <input type="text" name="email" class="email">
	<input type="submit" value="확인하기">
	</form>
</div>

</body>
</html>