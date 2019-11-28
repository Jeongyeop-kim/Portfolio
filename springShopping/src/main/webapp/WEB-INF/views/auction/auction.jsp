<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>

	<title></title>
	
<style>

    #wrap {
        width: 1400px;
        min-height: 800px;
        margin-left: auto;
        margin-right: auto;
        text-align: left;
    }

    div .clear {
        height: 40px;
        clear: both;
    }

    article {
        float: left;
        width: 1200px;
        margin-left: 160px;
    }

	#item_wrap {
		float: left;
		width: 1000px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 12px;
	}
	
	#item_wrap .item_image {
		float: left;
		width: 200px;
		height: 200px;
		border: 1px solid black;
	}
	
	#item_wrap .item_image img {
		width: 200px;
		height: 200px;
	}
	
	#item_wrap .item_content {
		float: left;
		width: 680px;
		height: 200px;
		margin-left: 20px;
	}
	
	#item_wrap .item_button {
		float: right;
		width: 70px;
		height: 200px;
		margin-left: 20px;
	}
	
	#item_wrap .item_button .btn {
		margin-top: 50px;
	}
	
	#page_control {
		float: left;
		width: 1000px;
		height: 80px;
		margin-top: 40px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 12px;
        text-align: center;
	}
	
	#page_control .write_button {
		float: left;
		width: 1000px;
		height: 80px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 12px;
        text-align: right;
	}
	
	#buttons {
		float: left;
		width: 1000px;
		text-align: center;
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
    
    <nav></nav>

    <article>
    <form action="/auction/auction" method="get">
	<h2>경매</h2>
	<div class="clear" style="width:1000px; border-top: 1px solid black"></div>
	
	<div id="item_wrap">
	<c:choose>
		<c:when test="${pageInfoMap.count gt 0}">
			<c:forEach var="sellVO" items="${aBoardList}">
				<div>
					<div class="item_image">
						<img src="/resources/upload/${sellVO.filename}">
					</div>
					
					<div class="item_content">
						<b>${sellVO.name}</b><br>
						${sellVO.info}<br>
						수량: ${sellVO.quantity}<br>
						최저가: <fmt:formatNumber value="${sellVO.minPrice}" pattern="#,###" /><br>
						최대가: <fmt:formatNumber value="${sellVO.maxPrice}" pattern="#,###" /><br>
						등록일자: <fmt:formatDate value="${sellVO.regDate}" pattern="yyyy.MM.dd" />
					</div>
					
					<div class="item_button">
						<input type="button" value="참여하기" class="btn" onclick="location.href='auctionContent.do?num=${sellVO.num}&aPageNum=${aPageNum}';">
					</div>
					<div class="clear" style="width: 1000px; margin-top: 10px; border-bottom: 1px solid black"></div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			작성된 내용이 없습니다.
		</c:otherwise>
	</c:choose>
	</div>		
	
	<div class="clear" style="width: 1000px; margin-top: 100px; border-bottom: 1px solid black"></div>
	
	<div id="page_control">
		<c:if test="${pageInfoMap.count gt 0}">
			<c:if test="${pageInfoMap.startPage gt pageInfoMap.pageBlock}">
				<a href="/auction/auction?aPageNum=${pageInfoMap.startPage - pageInfoMap.pageBlock}&aSearch=${aSearch}">[이전]</a>
			</c:if>
			
			<c:forEach var="i" begin="${pageInfoMap.startPage}" end="${pageInfoMap.endPage}" step="1">
				<a href="/auction/auction?aPageNum=${i}&aSearch=${aSearch}">
				<c:choose>
					<c:when test="${i eq aPageNum}">
						<span style="color: #9999FF; font-weight: bold;">[${i}]</span>
					</c:when>
					<c:otherwise>
						<span>${i}</span>
					</c:otherwise>
				</c:choose>
				</a>
	    	</c:forEach>
			
			<c:if test="${pageInfoMap.endPage lt pageInfoMap.pageCount}">
				<a href="/auction/auction?aPageNum=${pageInfoMap.startPage + pageInfoMap.pageBlock}&aSearch=${aSearch}">[다음]</a>
			</c:if>
		</c:if>
	<div class="clear"></div>
	
		<div class="write_button">
			<input type="button" value="등록하기" onclick="">
		</div>
	</div>
	
	<div class="clear"></div>
	
	<div id="buttons">
		<input type="text" name="aSearch" value="${empty aSearch ? '' : aSearch}">
		<input type="button" value="검색">
	</div>
	</form>
    </article>

    <div class="clear" ></div>

    <!-- footer 영역 -->
	<jsp:include page="../include/footer.jsp" />
	
</div>

</body>
</html>