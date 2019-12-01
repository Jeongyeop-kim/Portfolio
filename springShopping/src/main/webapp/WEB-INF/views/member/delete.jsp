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
        min-height: 800px;
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
	<form name="frm" action="/member/delete" method="post" onsubmit="return check();">
    <div id="wrap">

        <div class="clear"></div>

        <div id="insert">
            <h2>회원탈퇴</h2>
            <hr>
        
            <div class="clear"></div>
            
            <h6>회원탈퇴를 위한 비밀번호 최종 확인입니다.</h6>
            <table>
                <tr>
                    <td><label>* 비밀번호</label></td> <td><input name="passwd" type="password" class="passwd"></td>
                </tr><tr>
                    <td><label>* 비밀번호 확인</label></td> <td><input name="passwdcheck" type="password" class="passwd"></td>
                </tr><tr>
                	<td colspan="2" class="buttons">
                	<input type="submit" value="회원탈퇴" class="submit">
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
</script>	
</body>
</html>