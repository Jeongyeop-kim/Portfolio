<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>

    nav {
        float: left;
        width: 120px;    
    }

    nav button {
        width: 120px;
        height: 50px;
        background-color: #FF9966;
        border: 1px solid white;
        cursor: pointer; 
		cursor: hand;
    }

</style>
<nav>
	<button type="button" onclick="location.href='/admin/notice';">공지사항</button>
	<button type="button" onclick="location.href='/admin/members';">회원관리</button>
	<button type="button" onclick="location.href='/admin/boards';">게시판관리</button>
</nav>