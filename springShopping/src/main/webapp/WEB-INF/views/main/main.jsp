<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>

	<title>Shopping Eldorado</title>
	
<style>
	
	@import url(https://fonts.googleapis.com/css?family=Varela+Round);

	.slides {
		padding: 0;
		width: 944px;
		height: 352px;
		display: block;
 		margin-left: 16px;
 		margin-top: 45px;
		position: relative;
		border: 2px solid #c0c0c0;
	}

	.slides * {
		user-select: none;
		-ms-user-select: none;
		-moz-user-select: none;
		-khtml-user-select: none;
		-webkit-user-select: none;
		-webkit-touch-callout: none;
	}

	.slides input { 
		display: none; 
	}

	.slide-container { 
		display: block; 
	}

	.slide {
		top: 0;
		opacity: 0;
		width: 944px;
		height: 352px;
		display: block;
		position: absolute;
		transform: scale(0);
		transition: all .7s ease-in-out;
	}

	.slide img {
		width: 100%;
		height: 100%;
	}

	.nav label {
		width: 200px;
		height: 100%;
		display: none;
		position: absolute;
		opacity: 0;
		z-index: 9;
		cursor: pointer;
		transition: opacity .2s;
		color: #FFF;
		font-size: 156pt;
		text-align: center;
		line-height: 380px;
		font-family: "Varela Round", sans-serif;
		background-color: rgba(255, 255, 255, .3);
		text-shadow: 0px 0px 15px rgb(119, 119, 119);
	}

	.slide:hover + .nav label { 
		opacity: 0.5; 
	}

	.nav label:hover {
		opacity: 1; 
	}

	.nav .next {
		right: 0; 
	}

	input:checked + .slide-container  .slide {
		opacity: 1;
		transform: scale(1);
		transition: opacity 1s ease-in-out;
	}

	input:checked + .slide-container .nav label { 
		display: block;
	}

	.nav-dots {
		width: 100%;
		bottom: 9px;
		height: 11px;
		display: block;
		position: absolute;
		text-align: center;
	}

	.nav-dots .nav-dot {
		top: -5px;
		width: 11px;
    	height: 11px;
    	margin: 0 4px;
    	position: relative;
    	border-radius: 100%;
    	display: inline-block;
    	background-color: rgba(0, 0, 0, 0.6);
	}

	.nav-dots .nav-dot:hover {
    	cursor: pointer;
    	background-color: rgba(0, 0, 0, 0.8);
	}

	input#img-1:checked ~ .nav-dots label#img-dot-1,
	input#img-2:checked ~ .nav-dots label#img-dot-2,
	input#img-3:checked ~ .nav-dots label#img-dot-3,
	input#img-4:checked ~ .nav-dots label#img-dot-4,
	input#img-5:checked ~ .nav-dots label#img-dot-5,
	input#img-6:checked ~ .nav-dots label#img-dot-6 {
    	background: rgba(0, 0, 0, 0.8);
	}
	
	#wrap {
        width: 1400px;
        min-height: 800px;
        margin-left: auto;
        margin-right: auto;
        text-align: left;
    }
	
	body a {
		text-decoration: none;
	}
	
	body a:link, a:visited, a:hover, a:active {
		color: #666666;
	}
	
    div .clear {
        height: 40px;
        clear: both;
    }

    article {
        float: left;
        width: 1200px;
    }

    #news, #auction {
        float: left;
        width: 480px;
        height: 400px;
        margin-left: 10px;
    }

    #news .news_content, #auction .auction_board {
        width: 460px;
        height: 350px;
        border: 2px solid #666666;
    }
    
    #news .news_content {
    	font-family: "맑은 고딕", sans-serif;
        font-size: 14px;
    }
    
    #news .news_content .news_title {
    	float: left;
		width: 410px;
		height: 25px;
		margin-top: 2px;
		overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

	#auction .auction_board .auction_board_img {
		float: left;
		width: 110px;
		height: 110px;
		margin-top: 10px;
		margin-left: 7px;
	}
	
	#auction .auction_board .auction_board_img img {
		width: 110px;
		height: 110px;
	}

	#auction .auction_board .auction_board_content {
		float: right;
		width: 280px;
		height: 110px;
		margin-top: 10px;
		margin-right: 7px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 13px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
	}

    #best {
        width: 1000px;
        height: 320px;
        margin-left: 10px;
    }

    #best .best_goods {
        float: left;
        width: 305px;
        height: 305px;
        margin-left: 20px;
        font-family: "맑은 고딕", sans-serif;
        font-size: 14px;
        text-align: center;
    }

	#best .best_goods b {
        font-family: "맑은 고딕", sans-serif;
        font-size: 20px;
        text-align: center;
    }

    #best .best_goods .best_goods_img {
    	float: left;
        width: 295px;
        height: 295px;
        border: none;
    }
    
    #best .best_goods .best_goods_img img {
        width: 295px;
        height: 295px;
        border: none;
    }

    #recent {
        width: 1000px;
        height: 250px;
        margin-left: 10px;
    }

    #recent .recent_goods {
        float: left;
        width: 184px;
        height: 170px;
        margin-left: 10px;
        font-family: "맑은 고딕", sans-serif;
        font-size: 13px;
        text-align: center;
    }

    #recent .recent_goods .recent_goods_img {
    	float: left;
        width: 175px;
        height: 150px;
        border: none;
    }

	#recent .recent_goods .recent_goods_img img {
        width: 175px;
        height: 150px;
        border: none;
    }
    
    .headline {
        float: left;
        width: 480px;
        height: 24px;
        padding-left: 13px;
        font-family: "맑은 고딕", sans-serif;
        font-size: 24px;
    }
    
    #board, #trade_board {
        float: left;
        width: 480px;
        height: 403px;
        margin-top: 17px;
        margin-left: 10px;
        border: 2px solid #666666;
    }

    #board .board_content, #trade_board .trade_board_content {
    	float: left;
        width: 470px;
        height: 22px;
        margin-top: 1px;
        padding-top: 3px;
        padding-left: 10px;
        border-bottom: 1px dotted #666666;
        font-family: "맑은 고딕", sans-serif;
        font-size: 13px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    footer {
        width: 1400px;
        height: 160px;
        margin-top: 50px;
        background-color: #c0c0c0;
    }

    footer .bottom_content {
        padding-left: 170px;
        font-family: "맑은 고딕", sans-serif;
        font-size: 12px;
    }

</style>

</head>

<body>
<div id="wrap">

    <!-- section 영역 -->
    <jsp:include page="../include/section.jsp" />
	
	<div class="clear"></div>

	 <!-- header 영역 -->
	<jsp:include page="../include/header.jsp" />

    <div class="clear"></div>

	<!-- nav 영역 -->
	<jsp:include page="../include/nav.jsp" />
	

    <article>
    
    <!-- nav_interest 영역 -->
	<jsp:include page="../include/nav_interest.jsp" />
	
	<div class="headline">
        <b>이벤트</b>
	</div>
	
	<ul class="slides">
    <input type="radio" name="radio-btn" id="img-1" checked />
    <li class="slide-container">
        <div class="slide">
            <a href="http://randing.pincrux.com/skbroadband/pc/pc.html?pinkey=d9cfb992242e04490168e8e41abf00122f014c42&pubkey=910007&appkey=105047"
            target="_blank"><img src="/resources/images/event/event1.jpg"/></a>
        </div>
        <div class="nav">
            <label for="img-6" class="prev">&#x2039;</label>
            <label for="img-2" class="next">&#x203a;</label>
        </div>
    </li>

    <input type="radio" name="radio-btn" id="img-2" />
    <li class="slide-container">
        <div class="slide">
        	<a href="http://www.cncad.kr/event/9/index.html?event_idx=9&media_idx=668&banner_idx=2642"
            target="_blank"><img src="/resources/images/event/event2.jpg" /></a>
        </div>
        <div class="nav">
            <label for="img-1" class="prev">&#x2039;</label>
            <label for="img-3" class="next">&#x203a;</label>
        </div>
    </li>

    <input type="radio" name="radio-btn" id="img-3" />
    <li class="slide-container">
        <div class="slide">
        	<a href="http://randing.pincrux.com/miraeasset/11st/pc.html?pinkey=11st19::43bf278d3aeaa928d621e29f14080a16e63021b6"
            target="_blank"><img src="/resources/images/event/event3.jpg" /></a>
        </div>
        <div class="nav">
            <label for="img-2" class="prev">&#x2039;</label>
            <label for="img-4" class="next">&#x203a;</label>
        </div>
    </li>

    <input type="radio" name="radio-btn" id="img-4" />
    <li class="slide-container">
        <div class="slide">
        	<a href="http://www.11st.co.kr/browsing/MallPlanDetail.tmall?method=getMallPlanDetail&planDisplayNumber=916273"
            target="_blank"><img src="/resources/images/event/event4.jpg" /></a>
        </div>
        <div class="nav">
            <label for="img-3" class="prev">&#x2039;</label>
            <label for="img-5" class="next">&#x203a;</label>
        </div>
    </li>

    <input type="radio" name="radio-btn" id="img-5" />
    <li class="slide-container">
        <div class="slide">
          <a href="http://www.11st.co.kr/browsing/MallPlanDetail.tmall?method=getMallPlanDetail&planDisplayNumber=2022592"
          target="_blank"><img src="/resources/images/event/event5.jpg" /></a>
        </div>
        <div class="nav">
            <label for="img-4" class="prev">&#x2039;</label>
            <label for="img-6" class="next">&#x203a;</label>
        </div>
    </li>

    <input type="radio" name="radio-btn" id="img-6" />
    <li class="slide-container">
        <div class="slide">
          <a href="http://rpp.auction.co.kr/?exhib=16045"
          target="_blank"><img src="/resources/images/event/event6.jpg" /></a>
        </div>
        <div class="nav">
            <label for="img-5" class="prev">&#x2039;</label>
            <label for="img-1" class="next">&#x203a;</label>
        </div>
    </li>

    <li class="nav-dots">
      <label for="img-1" class="nav-dot" id="img-dot-1"></label>
      <label for="img-2" class="nav-dot" id="img-dot-2"></label>
      <label for="img-3" class="nav-dot" id="img-dot-3"></label>
      <label for="img-4" class="nav-dot" id="img-dot-4"></label>
      <label for="img-5" class="nav-dot" id="img-dot-5"></label>
      <label for="img-6" class="nav-dot" id="img-dot-6"></label>
    </li>
    </ul>

	<div class="clear"></div>
	
        <div id="news">
            <h2>뉴스</h2>
            <div class="news_content">
            <c:forEach var="list" items="${items}" begin="0" end="12">
			<c:set var="title" value="${list.title}"/>
			<c:set var="originallink" value="${list.originallink}"/>
				<div class="news_title"><a href="${originallink}" target="_blank"><b>&nbsp;&nbsp;· ${title}</b></a></div>
			</c:forEach> 
            </div>
        </div>

        <div id="auction">
            <h2>경매</h2>
            <div>
            <c:if test="${aCount gt 0}">
            	<c:forEach var="sellVO" items="${aBoardList}">
            		<div class="auction_board">
            		<div class="auction_board_img">
            			<a href="auctionContent.do?num=${sellVO.num}&aPageNum=${aPageNum}">
            			<img src="/resources/upload/${sellVO.filename}"></a>            		
            		</div>
            		<div class="auction_board_content">
            			<b style="font-size: 14px;">${sellVO.name}</b><br>
						${sellVO.info}<br>
						총 수량: ${sellVO.quantity}<br>
						최저가: <fmt:formatNumber value="${sellVO.minPrice}" pattern="#,###" /><br>
						최대가: <fmt:formatNumber value="${sellVO.maxPrice}" pattern="#,###" /><br>
						등록일자: <fmt:formatDate value="${sellVO.regDate}" pattern="yyyy.MM.dd" />
            		</div>
            		</div>
            	</c:forEach>
            </c:if>
            </div>
        </div>

        <div class="clear"></div>

        <div id="best">
            <h2>베스트</h2>
            <c:if test="${merCount gt 0}">
            	<c:forEach var="totalVO" items="${bestBoardList}">
            		<div class="best_goods">
		                <div class="best_goods_img">
		                	<a href="merchandiseInfo.do?num=${totalVO.num}&gPageNum=${gPageNum}&majorc=${totalVO.majorc}
							&subc=${totalVO.subc}&name=${totalVO.name}"><img src="/resources/images_${totalVO.majorc}/${totalVO.path}"></a>
		            	</div><br>
		                <a href="merchandiseInfo.do?num=${totalVO.num}&gPageNum=${gPageNum}&majorc=${totalVO.majorc}
						&subc=${totalVO.subc}&name=${totalVO.name}">${totalVO.name}</a><br>
						<b><fmt:formatNumber value="${totalVO.price}" pattern="#,###" />원</b>
		            </div>
            	</c:forEach>
            </c:if>   
        </div>
    
        <div class="clear" style="height: 80px; clear:both;"></div>

        <div id="recent">
            <h2>최근 등록된 상품</h2>
            <c:if test="${merCount gt 0}">
            	<c:forEach var="mTotalVO" items="${merBoardList}">
            		<div class="recent_goods">
		            	<div class="recent_goods_img">
		            		<a href="merchandiseInfo.do?num=${mTotalVO.num}&gPageNum=${gPageNum}&majorc=${mTotalVO.majorc}
							&subc=${mTotalVO.subc}&name=${mTotalVO.name}"><img src="/resources/images_${mTotalVO.majorc}/${mTotalVO.path}"></a>
		            	</div><br>
	               		<b><a href="merchandiseInfo.do?num=${mTotalVO.num}&gPageNum=${gPageNum}&majorc=${mTotalVO.majorc}
						&subc=${mTotalVO.subc}&name=${mTotalVO.name}">${mTotalVO.name}</a></b>
	            	</div> 
            	</c:forEach>
            </c:if>
        </div>

        <div class="clear"></div>

		<div class="headline">
        	<b>자유게시판</b>
		</div>
		
		<div class="headline">
        	<b>중고매매</b>
		</div>
		
        <div id="board">
        	<c:if test="${fCount gt 0}">
        		<c:forEach var="fboardVO" items="${fBoardList}">
        			<div class="board_content">
         			<a href="communityFreeContent.do?num=${fboardVO.num}&pageNum=${pageNum}">· ${fboardVO.subject}</a><br>
         			</div>
        		</c:forEach>
        	</c:if>
        </div>

        
        <div id="trade_board">
        	<c:if test="${tCount gt 0}">
        		<c:forEach var="tboardVO" items="${tBoardList}">
        			<div class="trade_board_content">
         			<a href="tradeContent.do?num=${tboardVO.num}&pageNum=${pageNum}">· ${tboardVO.subject}</a><br>
         			</div>
        		</c:forEach>
        	</c:if>        
        </div>

    </article>
	
    <div class="clear"></div>
	
    <!-- footer 영역 -->
	<jsp:include page="../include/footer.jsp" />
	
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script>

</script>

</body>
</html>