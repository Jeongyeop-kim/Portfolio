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
		width: 460px;
		height: 460px;
		border: 1px solid black;
	}
	
	#item_wrap .item_image img {
		width: 460px;
		height: 460px;
	}
	
	#item_wrap .item_content {
		float: left;
		width: 460px;
		height: 460px;
		margin-left: 20px;
	}
	
	#item_wrap .item_button {
		width: 460px;
		height: 30px;
	}
	
	#item_wrap .item_button .price {
		float: left;
		width: 400px;
		height: 30px;
	}
	
	#item_wrap .item_button .btn {
		float: right;
		width: 55px;
		height: 30px;
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
    <form name="frm" action="/auction/auctionProcess?num=${sellVO.num}&aPageNum=${aPageNum}" method="post" onsubmit="return check();">
    <c:if test="${not empty loginMember}">
    	<input type="hidden" class="username" name="username" value="${loginMember.id}">
		<input type="hidden" class="address" name="address" value="${loginMember.address}">
    </c:if>
	<h2>경매</h2>
	<div class="clear" style="width:1000px; border-top: 1px solid black"></div>
	
	<div id="item_wrap">
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
			<div class="item_button">
				<input type="number" name="price" class="price" 
				value="${price gt sellVO.maxPrice ? sellVO.maxPrice : price}">
				<input type="submit" class="btn" value="입력">
			</div>
		</div>
	</div>		
	
	<div class="clear" style="width:1000px; margin-top: 100px; border-bottom: 1px solid black"></div>
	
	<div class="clear"></div>
	</form>
    </article>

    <div class="clear" ></div>

    <!-- footer 영역 -->
	<jsp:include page="../include/footer.jsp" />
	
</div>

<script>
function check() {
	if (frm.price.value.length == 0) {
		alert('입찰 가격을 입력해주세요.');
		frm.price.select();
		return false;
	}
	
	if (frm.price.value < ${sellVO.minPrice}) {
		alert('입력하신 금액이 최저가보다 가격이 낮습니다. 다시 입력해주세요.');
		frm.price.select();
		return false;
	}
	
	if (frm.price.value > ${sellVO.maxPrice}) {
		alert('입력하신 금액이 상한가보다 가격이 높습니다. 최대가격으로 재조정됩니다.');
		frm.price.value = ${sellVO.maxPrice};
		return false;
	}
	
	if (frm.address.value == null) {
		alert('등록된 주소가 없습니다. 계속 진행하시려면 먼저 주소를 등록해주세요.');
		location.href = '/member/update';
		return false;
	}
}
</script>

</body>
</html>