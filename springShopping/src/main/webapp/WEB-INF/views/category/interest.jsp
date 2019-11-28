<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	
	nav .app {
		background-color: #9999FF;
	}
	
    article {
        float: left;
        width: 1000px;
    }

    #classify {
    	float: left;
    	width: 950px;
    	height: 60px;
        margin-left: 50px;
        padding-left: 10px;
        border: 1px solid black;
    }
    
    #interest_view {
		float: left;
		width: 1000px;
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
		width: 160px;
        height: 200px;
        padding-left: 20px;
        font-size: 12px;
        font-family: "맑은 고딕";
	}
	
	#merchandise .merchandise_image {
		width: 160px;
        height: 160px;
        border: 1px solid black;
	}
	
	#merchandise .merchandise_image img {
		width: 160px;
        height: 160px;
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
    <form name="frm" method="post" action="/category/interestDelete" >
	<div id="interest_view">
		<button type="submit" id="cancel">선택상품 관심항목 해제</button>
	</div>
	
	<div class="clear"></div>
	
	<div id="merchandise_wrap">
		<c:choose>
			<c:when test="${not empty interestList}">
				<c:forEach var="array" items="${interestList}">
				<c:set var="boardList" value="${totalDao.getInterestBoards(Integer.parseInt(array))}"/>
					<c:forEach var="totalVO" items="${boardList}">
						<div id="merchandise">
						    <div class="merchandise_image">
						    	<img src="/resources/images_${totalVO.majorc}/${totalVO.path}">
						    </div><br>
					    	<input type="checkbox" name="check" value="${totalVO.num}">
							<a href="/category/merchandiseInfo?num=${totalVO.num}&gPageNum=${gPageNum}&majorc=${totalVO.majorc}&subc=${totalVO.subc}&name=${totalVO.name}">
							${totalVO.name}</a><br>
							<fmt:formatNumber value="${totalVO.price}" pattern="#,###" />원<br>
					    	${totalVO.info}
						</div>
					</c:forEach>
				</c:forEach>
			</c:when>
			<c:otherwise>
			등록된 관심상품이 없습니다.
			</c:otherwise>
		</c:choose>
	</div>
	
	<div class="clear"></div>
	
	<div id="page_control">
		<c:if test="${pageInfoMap.count gt 0}">
			<c:if test="${pageInfoMap.startPage gt pageInfoMap.pageBlock}">
				<a href="/category/interest?gPageNum=${pageInfoMap.startPage - pageInfoMap.pageBlock}&gSearch=${gSearch}">[이전]</a>
			</c:if>
			
			<c:forEach var="i" begin="${pageInfoMap.startPage}" end="${pageInfoMap.endPage}" step="1">
				<a href="/category/interest?gPageNum=${i}&gSearch=${gSearch}">
				<c:choose>
					<c:when test="${i eq gPageNum}">
						<span style="color: #9999FF; font-weight: bold;">[${i}]</span>
					</c:when>
					<c:otherwise>
						<span>${i}</span>
					</c:otherwise>
				</c:choose>
				</a>
	    	</c:forEach>
			
			<c:if test="${pageInfoMap.endPage lt pageInfoMap.pageCount}">
				<a href="/category/interest?gPageNum=${pageInfoMap.startPage + pageInfoMap.pageBlock}&gSearch=${gSearch}">[다음]</a>
			</c:if>
		</c:if>
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