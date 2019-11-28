<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        min-height: 1450px;
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

    #agreement {
        float: left;
        width: 800px;
        height: 400px;
    }

    #agreement .agreement_content {
        width: 700px;
        height: 100px;
        overflow: scroll;
        margin-left: auto;
        margin-right: auto;
        border: 1px solid black;
        font-family: "맑은 고딕", sans-serif;
        font-size: 12px;
    }

    #agreement fieldset {
        border: none;
    }

    #agreement fieldset legend {
        padding-left: 34px;
        font-family: "맑은 고딕", sans-serif;
        font-size: 20px;
    }

    #agreement table {
        width: 700px;
        margin-left: auto;
        margin-right: auto;
        font-family: "맑은 고딕", sans-serif;
    }

    #agreement table span {
        font-size: 14px;
    }

    #agreement table .agree_check {
        text-align: right;
        font-size: 12px;
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
        font-family: "맑은 고딕", sans-serif;
        font-size: 12px;
    }
3
    footer .bottom_content {
        padding-left: 100px;
        font-family: "맑은 고딕", sans-serif;
        font-size: 12px;
    }
</style>

</head>

<body>
	<form name="frm" action="/member/join" method="post" onsubmit="return check();">
    <div id="wrap">
        <div id="agreement">
        <h2>회원가입</h2>
        <hr>

        <div class="clear"></div>
        
        <fieldset>
            <legend>약관동의</legend>
            <div class="agreement_content">
                이용약관 제1조 목적
            </div>
            <table><tr>
            <td><span>회원 이용약관에 동의합니다.(필수)</span></td>
            <td class="agree_check"><input type="checkbox" name="agree" value="동의">동의</td>
            </tr></table>

            <div class="clear"></div>

            <div class="agreement_content">
                1.수집하는 개인정보의 항목 및 수집방법
            </div>
            <table><tr>
            <td><span>개인정보 수집 및 이용에 동의합니다.(필수)</span></td>
            <td class="agree_check"><input type="checkbox" name="agree" value="동의">동의</td>
            </tr></table>

            <div class="clear"></div>
        </fieldset>
        </div>

        <div class="clear"></div>

        <div id="insert">
            <h2>정보입력</h2>
            <hr>
        
            <div class="clear"></div>
            
            <h6>*는 필수입력 사항입니다.</h6>
            <table>
                <tr>
                    <td><label>* 아이디</label></td> <td><input name="id" type="text" class="id" id="id">
                        <input type="button" value="중복확인" class="dup" onclick="winOpen();"><br>
                        <span id="id-message"></span></td>
                </tr><tr>
                    <td><label>* 비밀번호</label></td> <td><input name="passwd" type="password" class="passwd"></td>
                </tr><tr>
                    <td><label>* 비밀번호 확인</label></td> <td><input name="passwdcheck" type="password" class="passwd"></td>
                </tr><tr>
                    <td><label>* 이름</label></td> <td><input name="name" type="text" class="name"></td>
                </tr><tr>
                    <td><label>* 닉네임</label></td> <td><input name="nickname" type="text" class="nickname"></td>
                </tr><tr>
                    <td><label>* 주소</label></td> <td><input name="address" type="text" class="address">
                    	<input type="button" value="주소찾기" class="search_add" onclick="winOpenA();"><br></td>
                </tr><tr>
                    <td><label>* 이메일</label></td> <td><input name="email" type="email" class="email" ></td>
                </tr><tr>
                    <td><label>전화번호</label></td> <td><input name="tel" type="tel" class="phone"></td>
                </tr><tr>
                    <td><label>휴대폰번호</label></td> <td><input name="mtel" type="tel" class="mobile"></td>
                </tr><tr>
                	<td colspan="2" class="buttons">
                	<input type="submit" value="회원가입" class="submit">
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
$('#id').keyup(function () {
	var id = $(this).val();
	console.log(id);
	
	$.ajax({
		url: '/member/joinIdDupCheckJson',
		data: {id: id},
		success: function (data) {
			console.log(typeof data);
			console.log(data);
			
			idDupMessage(data);
		}
	});
});

function idDupMessage(isIdDup) {
	if (isIdDup) { // 중복 true
		$('span#id-message').html('중복된 아이디입니다.').css('color', 'red');
	} else { // 중복아님 false
		$('span#id-message').html('사용가능한 아이디입니다.').css('color', 'green');
	}
}
</script>

<script>
function check() {
	if (frm.id.value.length == 0) {
		alert('아이디는 필수입력 사항입니다.');
		frm.id.select();
		return false;
	}
	
	if (frm.passwd.value.length == 0) {
		alert('비밀번호는 필수입력 사항입니다.');
		frm.passwd.select();
		return false;
	}
	
	if (frm.name.value.length == 0) {
		alert('이름은 필수입력 사항입니다.');
		frm.name.select();
		return false;
	}
	
	if (frm.address.value.length == 0) {
		alert('주소는 필수입력 사항입니다.');
		frm.address.select();
		return false;
	}
	
	if (frm.email.value.length == 0) {
		alert('이메일은 필수입력 사항입니다.');
		frm.email.select();
		return false;
	}
	
	if (frm.passwd.value.length < 6 || frm.passwd.value.length > 16) {
		alert('비밀번호는 6자 이상 16자 이하로 사용 바랍니다.');
		frm.passwd.select();
		return false;
	}
	
	if (frm.passwd.value != frm.passwdcheck.value) {
		alert('비밀번호가 서로 일치하지 않습니다. 다시 확인하여 주십시오.');
		frm.passwd.select();
		return false;
	}
	
	if (frm.agree[0].checked == false) {
		alert('이용약관에 동의하지 않으셨습니다.');
		frm.agree[0].focus();
		return false;
	}
	
	if (frm.agree[1].checked == false) {
		alert('개인정보 수집 및 이용약관에 동의하지 않으셨습니다.');
		frm.agree[1].focus();
		return false;
	}
}

function winOpen() {
	var inputId = document.frm.id.value;
	if (inputId == '') {
		alert('아이디를 입력하세요.');
		document.frm.id.focus();
		return;
	} else if (inputId.length < 4 || inputId.length > 12) {
		alert('아이디는 4자 이상 12자 이하로만 사용 가능합니다.');
		document.frm.id.focus();
		return;
	}
	var childWindow = window.open('/member/joinIdDupCheck?id=' + inputId, '', 'width=400, height=300');
}

function winOpenA() {
	var inputAdd = document.frm.address.value;
	if (inputAdd == '') {
		alert('주소를 입력하세요.');
		document.frm.address.focus();
		return;
	} 
	var childWindow = window.open('/member/joinAddressCheck?inputAdd=' + inputAdd, '', 'width=500, height=500');
}
</script>
</body>
</html>