<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>

    section {
        width: 1400px;
        height: 150px;
    }

	section a {
		text-decoration: none;
	}

    section .blank{
        float: left;
        width: 180px;
        height: 60px;
    }

    section .logo {
        float: left;
        margin-left: 50px;
        width: 200px;
        height: 150px;
        border: none;
    }
    
	section .logo img {
        width: 200px;
        height: 150px;
        border: none;
    }
    
    section .search_box {
    	float: left;
    	margin-top: 60px;
    	margin-left: 100px;
        width: 395px;
        height: 40px;
    }
    
    section .search_box input {
        width: 350px;
        height: 30px;
        border: 2px solid lightgray;
    }
    
    section .search_box button {
    	float: right;
        width: 40px;
        height: 36px;
        border: 2px solid lightgray;
    }

	section .search_box img {
        width: 20px;
        height: 20px;
    }

	section #login_menu {
        float: right;
        width: 180px;
        height: 70px;
        font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        color: #c0c0c0;
        text-align: center;
        cursor: pointer; 
		cursor: hand;
    }
    
    section #login_menu .icon {
        float: left;
        width: 52px;
        height: 70px;
        margin-left: 7px;
    }
    
	section #login_menu .icon img {
        float: left;
        width: 45px;
        height: 40px;
    }
</style>
<section>
	<div class="blank"></div>
	
	<div class="logo">
		<a href="/"><img src="/resources/images/logo.gif"></a>
	</div>
	
	<form action="/category/searchResult" method="get">
	<div class="search_box">
		<input type="text" name="gSearch" value="${empty gSearch ? '' : gSearch}" class="input_box">
		<button type="submit"><img src="/resources/images_icon/search.png"></button>
	</div>
	</form>
	
	<div id="login_menu">
		<c:if test="${empty loginMember}">
			<div class="icon"><a href="/member/login"><img src="/resources/images_icon/login.png">로그인</a></div>
			<div class="icon"><a href="/member/join"><img src="/resources/images_icon/join.png">회원가입</a></div>
			<div class="icon"><a href=""><img src="/resources/images_icon/service.png">고객센터</a></div>
		</c:if>
		<c:if test="${not empty loginMember}">
			<div class="icon"><a href="/member/logout" onclick="logoutCheck();"><img src="/resources/images_icon/logout.png">로그아웃</a></div>
				<div class="icon"><a href="/member/update"><img src="/resources/images_icon/update.png">정보수정</a></div>
				<div class="icon"><a href=""><img src="/resources/images_icon/service.png">고객센터</a></div>
				<br>
			<c:if test="${not empty loginMember.nickname}">
				${loginMember.nickname}님 환영합니다.
			</c:if>
			<c:if test="${empty loginMember.name}">
				${loginMember.name}님 환영합니다.
			</c:if>
			<c:if test="${loginMember.id eq 'admin'}">
				<br><a href="/admin/adminPage">관리자페이지 이동</a>
			</c:if>
		</c:if>
	</div>
	
<script>
function logoutCheck() {
	var result = confirm('로그아웃 하시겠습니까?');
	if (result == true) {
		return;
	} else {
		return false;
	}
}
</script>
</section>