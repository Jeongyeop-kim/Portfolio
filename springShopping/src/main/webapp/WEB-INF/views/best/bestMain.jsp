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
	
	body a {
		text-decoration: none;
	}
	
    div .clear {
        height: 40px;
        clear: both;
    }
	
    article {
        float: left;
        width: 1000px;
    }
    
    #interest_view {
		float: left;
		width: 960px;
		height: 60px;
		text-align: right;
	}
	
	#interest_view .align {
		padding-top: 20px;
	}
	
	#merchandise_wrap {
		float: left;
		width: 1000px;
		margin-left: 50px;
	}
	
	#merchandise {
		float: left;
		width: 460px;
        height: 500px;
        margin-top: 60px;
        padding-left: 20px;
        font-size: 18px;
        font-family: "맑은 고딕";
	}
	
	#merchandise .merchandise_image {
		width: 400px;
        height: 400px;
        border: none;
	}
	
	#merchandise .merchandise_image img {
		width: 400px;
        height: 400px;
        border: none;
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
	
	<div class="clear"></div>
	
	<form name="frm" method="post" action="/category/interest" >
		<div id="interest_view">
		    <button type="submit">선택상품 관심항목 등록</button>
		    <button type="button" onclick="location.href='/category/interest';">관심항목 이동</button>
		</div>
	
	<div class="clear"></div>
	
	<div id="merchandise_wrap">
		<c:choose>
			<c:when test="${pageInfoMap.count gt 0}">
				<c:forEach var="totalVO" items="${bestBoardList}">
					<div id="merchandise">
				    <div class="merchandise_image">
				    	<img src="/resources/images_${totalVO.majorc}/${totalVO.path}">
				    </div><br>
				    	<input type="checkbox" name="check" value="${totalVO.num}">
				    	<input type="hidden" name="num" value="${totalVO.num}">
				    	<input type="hidden" name="subc" value="${totalVO.subc}">
				    	<input type="hidden" name="majorc" value="${totalVO.majorc}">
						<a href="/category/merchandiseInfo?num=${totalVO.num}&gPageNum=${gPageNum}&majorc=${totalVO.majorc}&subc=${totalVO.subc}&name=${totalVO.name}">
						<b>${totalVO.name}</b></a><br>
						<fmt:formatNumber value="${totalVO.price}" pattern="#,###" />원<br>
				    	${totalVO.info}<br>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="merchandise_image">
		    	</div><br>
		    	작성된 내용이 없습니다.
			</c:otherwise>
		</c:choose>
	</div>
	
	<div class="clear"></div>
	
	<div id="page_control">
	
	<div class="clear"></div>
	
	</div>
	</form>
    </article>

    <div class="clear"></div>

    <!-- footer 영역 -->
	<jsp:include page="../include/footer.jsp" />
	
</div>

</body>
</html>