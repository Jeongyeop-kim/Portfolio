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
	
	nav .bk {
		background-color: #9999FF;
	}
	
    article {
        float: left;
        width: 1200px;
    }
    
	article hr {
        float: left;
        width: 900px;
        margin-left: 60px;
    }

    #classify {
    	float: left;
    	width: 950px;
    	height: 60px;
        margin-left: 50px;
        padding-left: 10px;
		font-size: 12px;
        font-family: "맑은 고딕";
    }
    
	#classify .classify_box {
    	float: left;
    	width: 178px;
    	height: 30px;
    	padding-top: 12px;
        text-align: center;
        border: 1px solid lightgray;
        cursor: pointer; 
		cursor: hand;
    }
    
    #classify .classify_box:hover {
        border: 1px solid #9999FF;
    }
    
    #classify .classify_box img {
    	float: left;
    	width: 20px;
    	height: 20px;
    	padding-left: 5px;
    }     
    
    #interest_view {
		float: left;
		width: 960px;
		height: 60px;
		margin-top: 40px;
		text-align: right;
	}
	
	#interest_view button {
		background-color: lightgray;
		border: 1px solid #c0c0c0;
		border-radius: 5px;
		font-size: 12px;
        font-family: "맑은 고딕";
	}
	
	#interest_view .align {
		padding-top: 20px;
		font-size: 12px;
        font-family: "맑은 고딕";
	}
	
	#interest_view .align a:link, a:visited, a:hover, a:active {
		color: #666666;
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
        margin-top: 50px;
        padding-left: 20px;
        font-size: 12px;
        font-family: "맑은 고딕";
	}
	
	#merchandise a:link, a:visited, a:hover, a:active {
		color: #666666;
	}	
	
	#merchandise .merchandise_image {
		width: 160px;
        height: 160px;
        border: none;
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
    
    <!-- nav_interest 영역 -->
	<jsp:include page="../include/nav_interest.jsp" />
	
    <div id="classify">
    	<div class="classify_box" onclick="location.href='/category/book?subc=hot';"><img src="/resources/images_icon/hot.png">베스트셀러</div>
    	<div class="classify_box" onclick="location.href='/category/book?subc=stu';"><img src="/resources/images_icon/stu.png">수험서</div>
    	<div class="classify_box" onclick="location.href='/category/book?subc=dev';"><img src="/resources/images_icon/dev.png">자기개발서</div> 
    	<div class="classify_box" onclick="location.href='/category/book?subc=nov';"><img src="/resources/images_icon/nov.png">소설</div>
    	<div class="classify_box"></div>
    	<div class="classify_box"></div>
    	<div class="classify_box"></div>
    	<div class="classify_box"></div> 
    	<div class="classify_box"></div>
    	<div class="classify_box"></div>
	</div>
	
	<form name="frm" method="post" action="/category/interest" >
		<div id="interest_view">
		    <button type="submit">선택상품 관심항목 등록</button>
		    <button type="button" onclick="location.href='/category/interest';">관심항목 이동</button>
			    <div class="align">
				    <a href="/category/book?subc=${subc}&sw=readcount">조회수순</a> 
				    | <a href="/category/book?subc=${subc}&sw=lowprice">낮은가격순</a>
				    | <a href="/category/book?subc=${subc}&sw=highprice">높은가격순</a> 
				    | <a href="/category/book?subc=${subc}&sw=date">신상품순</a>
				</div>
		</div>
		<hr>
	
	<div id="merchandise_wrap">
		<c:choose>
			<c:when test="${pageInfoMap.count gt 0}">
				<c:forEach var="totalVO" items="${bkBoardList}">
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
		<c:if test="${pageInfoMap.count gt 0}">
			<c:if test="${pageInfoMap.startPage gt pageInfoMap.pageBlock}">
				<a href="/category/book?subc=${subc}&sw=${sw}&gPageNum=${pageInfoMap.startPage - pageInfoMap.pageBlock}&gSearch=${gSearch}">[이전]</a>
			</c:if>
			
			<c:forEach var="i" begin="${pageInfoMap.startPage}" end="${pageInfoMap.endPage}" step="1">
				<a href="/category/book?subc=${subc}&sw=${sw}&gPageNum=${i}&gSearch=${gSearch}">
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
				<a href="/category/book?subc=${subc}&sw=${sw}&gPageNum=${pageInfoMap.startPage + pageInfoMap.pageBlock}&gSearch=${gSearch}">[다음]</a>
			</c:if>
		</c:if>
	<div class="clear"></div>
	
	<div class="write_button">
		<c:if test="${not empty loginMember and loginMember.id eq 'admin'}">
			<input type="button" value="글쓰기" onclick="location.href='categoryRegister.do';">
		</c:if>
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