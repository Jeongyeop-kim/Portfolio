<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

    <title></title>
    
<style>
    body {
        background-color: #666666;
    }

    #wrap {
        width: 800px;
        min-height: 1100px;
        margin: 0 auto;
        background-color: white;
    }

    #wrap h2 {
        padding-left: 10px;
    }

    #wrap h6 {
        padding-left: 60px;
    }

    div .clear {
        height: 40px;
        clear: both;
    }    
    
    #insert {
        float: left;
        width: 800px;
        height: 500px;
        margin-top: 50px;
        font-family: "맑은 고딕", sans-serif;
    }

    #insert table {
        width: 650px;
        margin-left: auto;
        margin-right: auto;
        font-size: 12px;
        border: none;
    }

    #insert table td {
        padding-top: 25px;
        padding-bottom: 25px;
        border-bottom: 1px dotted #666666;
        border-collapse: collapse;
    }

    #insert .passwd, .newpasswd {
        width: 180px;
    }

    #insert #passwd-message {
        margin-left: 3px;
    }

    #insert .buttons {
        text-align: center;
        border: none;
    }

	#insert .submit, .cancel {
		width: 150px;
		height: 50px;
		background-color: #c0c0c0;
	}

    footer {
        width: 800px;
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
	<form name="frm" action="/member/updatePasswd" method="post" onsubmit="return check();">
	<input type="hidden" name="id" value="${loginMember.id}">
    <div id="wrap">

        <div class="clear"></div>

        <div id="insert">
            <h2>회원정보수정</h2>
            <hr>
        
            <div class="clear"></div>
            
            <h6>현재 사용중인 비밀번호와 다른 비밀번호를 입력해주세요.</h6>
            <table>
                <tr>
                    <td><label>* 현재 비밀번호</label></td> <td><input name="passwd" type="password" class="passwd"></td>
                </tr><tr>
                    <td><label>* 새 비밀번호</label></td> <td><input name="newpasswd" type="password" class="newpasswd" id="newpasswd">
                    <span id="passwd-message"></span></td>
                </tr><tr>
                    <td><label>* 비밀번호 확인</label></td> <td><input name="newpasswdcheck" type="password" class="newpasswd"></td>
                </tr><tr>
                	<td colspan="3" class="buttons">
                	<input type="submit" value="비밀번호변경" class="submit">
                	<input type="button" value="뒤로가기" class="cancel" onclick="history.back();">
                	</td>
                </tr>
            </table>

            <div class="clear"></div>
            
        </div>

    </div>
	</form>
	<!-- footer 영역 -->
	<jsp:include page="../include/footer.jsp" />
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$('#newpasswd').keyup(function () {
	var id = '${loginMember.id}'
	var newpasswd = $(this).val();
	
	$.ajax({
		url: '/member/updatePasswdDupCheckJson',
		data: {id: id, newpasswd: newpasswd},
		success: function (data) {
			passwdDupMessage(data);
		}
	});
});

function passwdDupMessage(isPasswdDup) {
	if (isPasswdDup) { // 중복 true
		$('span#passwd-message').html('현재 사용중인 비밀번호와 같습니다.').css('color', 'red');
	} else { // 중복아님 false
		$('span#passwd-message').html('사용 가능한 비밀번호입니다.').css('color', 'green');
	}
}

function check() {
	if (frm.newpasswd.value == frm.passwd.value) {
		alert('현재 사용중인 비밀번호와 같습니다. \n다른 비밀번호를 입력해주세요.');
		frm.newpasswd.select();
		return false;
	}
	
	if (frm.newpasswd.value.length < 6 || frm.newpasswd.value.length > 16) {
		alert('비밀번호는 6자 이상 16자 이하로 사용 바랍니다.');
		frm.newpasswd.select();
		return false;
	}
	
	if (frm.newpasswd.value != frm.newpasswdcheck.value) {
		alert('비밀번호가 서로 일치하지 않습니다. 다시 확인하여 주십시오.');
		frm.newpasswd.select();
		return false;
	}
}
</script>
</body>
</html>