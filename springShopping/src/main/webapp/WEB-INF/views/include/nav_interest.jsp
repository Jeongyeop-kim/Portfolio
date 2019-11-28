<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	
	#interesting {
		float: right;
        width: 180px;
        height: 250px;  
    }
    
    #interesting a {
		text-decoration: none;
	}
	
    #interesting #interest_box {
    	width: 180px;
    	height: 250px;
    	border: 2px solid #c0c0c0;
    	overflow: scroll;
    	font-size: 12px;
    	color: #666666;
    	text-align: center;
    }

	#interesting #interest_box .headline {
    	width: 180px;
    	height: 20px;
    	border-bottom: 1px solid #c0c0c0;
    	font-size: 12px;
    	color: #666666;
    	text-align: center;
    }

	#interesting #interest_box .interest_image {
    	width: 150px;
    	height: 150px;
    	margin-top: 30px;
    	margin-left: 10px;
    }
    
    #interesting #interest_box .interest_image img {
    	float: left;
    	width: 140px;
    	height: 140px;
    }
</style>

<div id="interesting">
	<div id="interest_box">
		<div class="headline">관심항목 목록</div>
		<c:choose>
			<c:when test="${not empty interestList}">
				<c:forEach var="array" items="${interestList}">
				<c:set var="boardList" value="${totalDao.getInterestBoards(Integer.parseInt(array))}"/>
					<c:forEach var="totalVO" items="${boardList}">
						<div class="interest_image">
		    			<a href="/category/merchandiseInfo?num=${totalVO.num}&gPageNum=${gPageNum}&majorc=${totalVO.majorc}
						&subc=${totalVO.subc}&name=${totalVO.name}"><img src="/resources/images_${totalVO.majorc}/${totalVO.path}"></a>
						</div>
						<a href="/category/merchandiseInfo?num=${totalVO.num}&gPageNum=${gPageNum}&majorc=${totalVO.majorc}
						&subc=${totalVO.subc}&name=${totalVO.name}">${totalVO.name}</a>
					</c:forEach>
				</c:forEach>
			</c:when>
			<c:otherwise>
			등록된 관심상품이 없습니다.
			</c:otherwise>
		</c:choose>
	</div>
</div>
