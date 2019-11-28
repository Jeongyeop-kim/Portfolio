<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>

	header {
        width: 1400px;
        height: 60px;
    }
    
    header a {
    	text-decoration: none;
    }
    
    body a:link, a:visited, a:hover, a:active {
		color: black;
	}
	
	header ul, li{
        list-style-type: none;
    }
	
	header #top_menu {
        float: left;
        width: 130px;
        height: 30px;
        padding-top: 12px;
        color: white;
        border: 1px solid white;
        background-color: #666666;
        font-family: "맑은 고딕", sans-serif;
        font-size: 14px;
        text-align: center;
        cursor: pointer; 
		cursor: hand;
    }
    
    header #top_menu_blank, #top_menu_category {
    	float: left;
    	width: 200px;
        height: 42px;
        background-color: white;
    }
    
	header #top_menu_category {
		background-color: black;
		border: 1px solid white;
    	font-family: "맑은 고딕", sans-serif;
        font-size: 14px;
        color: white;
        text-align: center;
    }
    
    header #top_menu_category .menu {
        cursor: pointer; 
		cursor: hand;
    }
    
    header #top_menu_category .menu span {
        margin-right: 35px;
    }   
     
    header #top_menu_category .menu .hide {
		display: none;
		margin-top: 10px;
    }
    
    header #top_menu_category .menu .hide .list {
    	position: relative;
		width: 119px;
        height: 30px;
        padding-top: 12px;
        border: 1px solid white;
		background-color: #c0c0c0;
        font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        text-align: center;
        z-index: 1;
    }
</style>

<header>	
	
	<div id="top_menu_category">
		<ul>
        <li class="menu">
            <span>카테고리 전체보기</span>
            <ul class="hide">
                <li><div class="list"><a href="/category/appliances">가전</a></div></li>
                <li><div class="list"><a href="/category/computer">컴퓨터 · 노트북</a></div></li>
                <li><div class="list"><a href="/category/fashion">패션 · 신발</a></div></li>
                <li><div class="list"><a href="/category/general">잡화 · 화장품</a></div></li>
                <li><div class="list"><a href="/category/sports">스포츠</a></div></li>
                <li><div class="list"><a href="/category/furniture">가구 · 생활 · 취미</a></div></li>
                <li><div class="list"><a href="/category/kids">아동 · 유아</a></div></li>
                <li><div class="list"><a href="/category/book">도서</a></div></li>
            </ul>
        </li>
    	</ul>
	</div>
	
	<div id="top_menu" class="b" onclick="location.href='bestMain.do';">베스트</div>
	<div id="top_menu" class="c" onclick="location.href='auction.do';">경매</div>
	<div id="top_menu" class="d" onclick="location.href='/trade/trade';">중고매매</div>
	<div id="top_menu" class="e" onclick="location.href='/community/free';">커뮤니티</div>
	<div id="top_menu" class="f" onclick="location.href='event.do';">이벤트</div>
	<div id="top_menu" class="g" onclick="location.href='/news/news';">뉴스</div>
	<div id="top_menu" class="h" onclick="location.href='event.do';">쇼핑검색</div>
	<div id="top_menu_blank"></div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	
	$(document).ready(function() {
		 $(".menu > span").click(function() {
            var submenu = $(this).next("ul");
 
            if (submenu.is(":visible")) {
                submenu.slideUp();
            } else {
                submenu.slideDown();
            }
        }).mouseover(function() {
            $(this).next("ul").slideDown();
        });
	});
	
	$('.list').mouseover(function() {
		$(this).stop().animate({
			opacity: '0.8'
		}, 500);
	});
	$('.list').mouseout(function() {
		$(this).stop().animate({
			opacity: '1'
		}, 500);
	});
	
	// b
	$('.b').mouseover(function() {
		$(this).stop().animate({
			opacity: '0.6'
		}, 500);
	});
	$('.b').mouseout(function() {
		$(this).stop().animate({
			opacity: '1'
		}, 500);
	});

	// c
	$('.c').mouseover(function() {
		$(this).stop().animate({
			opacity: '0.6'
		}, 500);
	});
	$('.c').mouseout(function() {
		$(this).stop().animate({
			opacity: '1'
		}, 500);
	});
	
	// d
	$('.d').mouseover(function() {
		$(this).stop().animate({
			opacity: '0.6'
		}, 500);
	});
	$('.d').mouseout(function() {
		$(this).stop().animate({
			opacity: '1'
		}, 500);
	});
	
	// e
	$('.e').mouseover(function() {
		$(this).stop().animate({
			opacity: '0.6'
		}, 500);
	});
	$('.e').mouseout(function() {
		$(this).stop().animate({
			opacity: '1'
		}, 500);
	});
	
	// f
	$('.f').mouseover(function() {
		$(this).stop().animate({
			opacity: '0.6'
		}, 500);
	});
	$('.f').mouseout(function() {
		$(this).stop().animate({
			opacity: '1'
		}, 500);
	});
	
	// g
	$('.g').mouseover(function() {
		$(this).stop().animate({
			opacity: '0.6'
		}, 500);
	});
	$('.g').mouseout(function() {
		$(this).stop().animate({
			opacity: '1'
		}, 500);
	});
	
	// h
	$('.h').mouseover(function() {
		$(this).stop().animate({
			opacity: '0.6'
		}, 500);
	});
	$('.h').mouseout(function() {
		$(this).stop().animate({
			opacity: '1'
		}, 500);
	});
</script>
</header>