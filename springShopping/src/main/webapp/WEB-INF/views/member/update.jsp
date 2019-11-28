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

    #insert .id, .name, .nickname {
        width: 120px;
    }

    #insert .address, .email {
        width: 300px;
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
	<form name="frm" action="/member/update" method="post" onsubmit="return check();">
    <div id="wrap">

        <div class="clear"></div>

        <div id="insert">
            <h2>회원정보수정</h2>
            <hr>
        
            <div class="clear"></div>
            
            <h6>비밀번호가 일치하면 회원정보를 수정합니다.</h6>
            <table>
                <tr>
                    <td><label>* 아이디</label></td>
                    <td><input name="id" type="text" class="id" value="${loginMember.id}" readonly></td>
                </tr><tr>
                    <td><label>* 비밀번호</label></td> <td><input name="passwd" type="password" class="passwd"></td>
                </tr><tr>
                    <td><label>* 비밀번호 확인</label></td> <td><input name="passwdcheck" type="password" class="passwd"></td>
                </tr><tr>
                    <td><label>* 이름</label></td>
                    <td><input name="name" type="text" class="name" value="${loginMember.name}"readonly></td>
                </tr><tr>
                    <td><label>* 닉네임</label></td>
                    <td><input name="nickname" type="text" class="nickname" value="${loginMember.nickname}"></td>
                </tr><tr>
                    <td><label>* 주소</label></td>
                    <td><input name="address" type="text" class="address" value="${loginMember.address}"></td>
                </tr><tr>
                    <td><label>이메일</label></td>
                    <td><input name="email" type="email" class="email" value="${loginMember.email}"></td>
                </tr><tr>
                    <td><label>전화번호</label></td>
                    <td><input name="tel" type="tel" class="phone" value="${loginMember.tel}"></td>
                </tr><tr>
                    <td><label>휴대폰번호</label></td>
                    <td><input name="mtel" type="tel" class="mobile" value="${loginMember.mtel}"></td>
                </tr><tr>
                	<td colspan="3" class="buttons">
                	<input type="submit" value="회원정보수정" class="submit">
                	<input type="button" value="비밀번호변경" class="submit" onclick="location.href='/member/updatePasswd';">
                	<input type="button" value="회원탈퇴" class="cancel" onclick="deleteCheck();">
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
	
<script>
function check() {
	if (frm.passwd.value != frm.passwdcheck.value) {
		alert('비밀번호가 서로 일치하지 않습니다. 다시 확인하여 주십시오.');
		frm.passwd.select();
		return false;
	}
	return true;
}

function deleteCheck() {
	var result = confirm('정말로 탈퇴하시겠습니까?');
	if (result == true) {
		location.href = '/member/delete';
	}
}
</script>
</body>
</html>