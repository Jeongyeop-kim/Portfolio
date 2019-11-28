<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>

    nav {
        float: left;
        width: 160px;    
    }

    nav button {
        width: 160px;
        height: 50px;
        background-color: #FF9966;
        border: 1px solid white;
        cursor: pointer; 
		cursor: hand;
    }
    
	nav button img {
		float: left;
        width: 20px;
        height: 20px;
    }

</style>
<nav>
	<button type="button" class="app" onclick="location.href='/category/appliances';"><img src="/resources/images_icon/app.png">가전</button>
	<button type="button" class="com" onclick="location.href='/category/computer';"><img src="/resources/images_icon/com.png">컴퓨터 · 노트북</button>
	<button type="button" class="fas" onclick="location.href='/category/fashion';"><img src="/resources/images_icon/fas.png">패션 · 신발</button>
	<button type="button" class="gen" onclick="location.href='/category/general';"><img src="/resources/images_icon/gen.png">잡화 · 화장품</button>
	<button type="button" class="spo" onclick="location.href='/category/sports';"><img src="/resources/images_icon/spo.png">스포츠</button>
	<button type="button" class="fur" onclick="location.href='/category/furniture';"><img src="/resources/images_icon/fur.png">가구 · 생활 · 취미</button>
	<button type="button" class="kid" onclick="location.href='/category/kids';"><img src="/resources/images_icon/kid.png">아동 · 유아</button>
	<button type="button" class="bk" onclick="location.href='/category/book';"><img src="/resources/images_icon/bk.png">도서</button>
</nav>