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
    }

	#merchandise_info {
		float: left;
		width: 1000px;
		margin-left: 20px;
	}
	
	#merchandise_info .buttons button {
		background-color: lightgray;
		border: 1px solid #c0c0c0;
		border-radius: 5px;
		font-size: 12px;
        font-family: "맑은 고딕";
	}	
	
	#merchandise_info .merchandise_image {
		float: left;
		width: 460px;
        height: 460px;
        margin-left: 30px;
        border: none;
	}
	
	#merchandise_info .merchandise_image img {
		width: 460px;
        height: 460px;
        border: none;
	}
	
	#merchadise_info .merchandise_content {
		float: right;
		width: 460px;
		font-size: 12px;
		font-family: "맑은 고딕";
	}
	
	#merchandise_info .merchandise_price {
		float: right;
		width: 460px;
        height: 460px;
        margin-right: 30px;
        border: none;
	}
	
	#merchandise_info .merchandise_price b {
		font-size: 20px;
		font-family: "맑은 고딕";
	}

	#merchandise_info .merchandise_price .mall_list{
		width: 460px;
        height: 30px;
        margin-top: 10px;
	}
	
	#merchandise_info .merchandise_price .mall_list .mall_name {
		float: left;
		width: 80px;
        height: 40px;
        padding-top: 10px;
        font-size: 13px;
        font-family: "맑은 고딕";
	}
	
	#merchandise_info .merchandise_price .mall_list .mall_name span {
		float: left;
		width: 80px;
        height: 40px;
        padding-top: 10px;
        font-size: 13px;
        font-family: "맑은 고딕";
	}	
	
	#merchandise_info .merchandise_price .mall_list .mall_name img {
		width: 70px;
		height: 38px;
	}
	
	#merchandise_info .merchandise_price .mall_list .mall_price {
		float: left;
		width: 320px;
        height: 40px;
        padding-top: 20px;
        font-size: 13px;
        font-family: "맑은 고딕";
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
    <form name="frm" method="post" action="/category/interest">
    <input type="hidden" name="check" value="${totalVO.num}">
	<div id="merchandise_info">
		<div class="merchandise_image">
			<img src="/resources/images_${totalVO.majorc}/${totalVO.path}">
		</div>
		<div class="merchandise_price">
		<div class="merchandise_content">
		<span style="font-weight:bold; font-size:22px;">${totalVO.name}</span><br>
		${totalVO.info}
		</div>
		<div class="clear" style="width:434px; height:10px; margin-top:10px; border-top:2px solid #c0c0c0; clear:both;"></div>
		<b>최저가</b><br>
		<b><fmt:formatNumber value="${minimum.price}" pattern="#,###" />원</b>
		<div class="clear" style="height:10px; clear:both;"></div>
		<div class="buttons">
			<button type="submit">현재상품 관심항목 등록</button>
			<button type="button" onclick="window.open('${totalVO.link}')">구매하러 가기</button>
		</div>
		<div class="clear" style="height:10px; clear:both;"></div>
		<c:forEach var="lowTotalVO" items="${totalBoardList}">
			<div class="mall_list">
				<div class="mall_name">
				<c:set var="mallName" value="${lowTotalVO.mall}" scope="request"/>
				<a href="${lowTotalVO.link}">
				<c:choose>
					<c:when test="${mallName eq '11번가'}"><img src='/resources/images_logo/11st.gif'></c:when>
					<c:when test="${mallName eq 'AUCTION'}"><img src='/resources/images_logo/auction.gif'></c:when>
					<c:when test="${mallName eq 'CJ몰'}"><img src='/resources/images_logo/cjmall.gif'></c:when>
					<c:when test="${mallName eq '쿠팡'}"><img src='/resources/images_logo/COUPANG.gif'></c:when>
					<c:when test="${mallName eq '엘롯데'}"><img src='/resources/images_logo/ellote.gif'></c:when>
					<c:when test="${mallName eq '이마트몰'}"><img src='/resources/images_logo/emartmall.gif'></c:when>
					<c:when test="${mallName eq 'G마켓'}"><img src='/resources/images_logo/gmarket.gif'></c:when>
					<c:when test="${mallName eq 'GSSHOP'}"><img src='/resources/images_logo/gsshop.gif'></c:when>
					<c:when test="${mallName eq '홈플러스'}"><img src='/resources/images_logo/homeplus.gif'></c:when>
					<c:when test="${mallName eq '인터파크'}"><img src='/resources/images_logo/interpark.gif'></c:when>
					<c:when test="${mallName eq '롯데홈쇼핑'}"><img src='/resources/images_logo/lottehome.gif'></c:when>
					<c:when test="${mallName eq '올리브영'}"><img src='/resources/images_logo/oliveyoung.gif'></c:when>
					<c:when test="${mallName eq 'ssg'}"><img src='/resources/images_logo/ssg.gif'></c:when>
					<c:when test="${mallName eq '위메프'}"><img src='/resources/images_logo/we.gif'></c:when>
					<c:otherwise>${mallName}</c:otherwise>
				</c:choose>
				</a>
				</div>
				<div class="mall_price"><fmt:formatNumber value="${lowTotalVO.price}" pattern="#,###" />원</div>
			</div>
		</c:forEach>
		</div>
	</div>
	</form>
    </article>

    <div class="clear"></div>

    <!-- footer 영역 -->
	<jsp:include page="../include/footer.jsp" />
	
</div>

</body>
</html>