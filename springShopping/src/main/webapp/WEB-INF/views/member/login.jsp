<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>

    <title></title>
    
<style>
    body {
        background-color: #666666;
    }
	
	body a {
		text-decoration: none;
	}

    #wrap {
        width: 700px;
        height: 700px;
        margin: 0 auto;
        text-align: center;
        background-color: white;
    }
	
	div .clear {
        height: 40px;
        clear: both;
    }
    
    div .logo {
        width: 300px;
        height: 280px;
        margin-top: 50px;
        margin-left: auto;
        margin-right: auto;
    }

    div .logo img {
        width: 300px;
        height: 280px;
    }

    table {
        margin-left: auto;
        margin-right: auto;
    }

    table .input_text {
        width: 320px;
        height: 40px;
    }

    td .loginbutton {
        text-align: center;
    }

    #wrap .missing {
        color: #666666;
        cursor: pointer; 
		cursor: hand;
    }

    button {
        width: 150px;
        height: 40px;
    }

    footer {
        width: 700px;
        height: 120px;
        margin-left: auto;
        margin-right: auto;
        background-color: #c0c0c0;
    }
    
    footer .bottom_content {
        padding-left: 100px;
        font-family: "맑은 고딕", sans-serif;
        font-size: 12px;
    }
</style>
</head>

<body>
<div id="wrap">
    
    <div class="clear"></div>
    
    <div class="logo">
    	<a href="/"><img src="/resources/images/logo.gif"></a>
    </div>
    
    <div class="clear" style="height: 10px;"></div>
    
    <form action="/member/loginProcess" method="post">
    <table>
        <tr>
        	<td><input type="text" name="id" placeholder="아이디를 입력해주세요." class="input_text"></td>
        </tr><tr>
        	<td><input type="password" name="passwd" placeholder="비밀번호를 입력해주세요." class="input_text"></td>
        </tr><tr>
        	<td style="text-align: left"><input type="checkbox" name="rememberMe" value="true">로그인 유지
        	</td>
        </tr><tr>
        <td class="loginbutton">
        	<button type="submit" style="margin-right:18px">로그인</button>
            <button type="button" onclick="location.href='/member/join';">회원가입</button></td>
        </tr>
    </table>    
    </form>
    
	<div class="clear"></div>
	
    <div class="missing" onclick="winOpen();">아이디 혹은 비밀번호를 잊어버리셨나요?</div>
    
	<div class="clear"></div>

</div>

	<!-- footer 영역 -->
	<jsp:include page="../include/footer.jsp" />
	
<script>
function winOpen() {
	var childWindow = window.open('/member/missingAcc', '', 'width=500, height=500');
}
</script>
</body>
</html>