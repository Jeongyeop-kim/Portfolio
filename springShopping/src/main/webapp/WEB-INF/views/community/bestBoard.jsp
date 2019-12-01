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
        width: 1000px;
        margin-left: 20px;
    }

	#board {
		float: left;
		width: 1000px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 12px;
        text-align: center;
        border-collapse: collapse;
	}
	
	#board th {
		height: 28px;
		background-color: #666666;
		color: white;
		border: 1px solid black;
	}
		
	#board .board_list {
		text-overflow: ellipsis;
		cursor: pointer; 
		cursor: hand;
	}

	#board .board_list td {
		height: 20px;
		border: 1px solid #666666;		
	}
		
	#page_control {
		float: left;
		width: 1000px;
		height: 80px;
		margin-top: 40px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 15px;
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
	}
	
	#buttons .opt {
		float: left;
		width: 80px;
		height: 25px;
		margin-left: 300px;
		text-align: center;
	}
	
	#buttons .search {
		float: left;
		width: 250px;
		height: 19px;
	}
	
	#buttons .submit {
		float: left;
		width: 70px;
		height: 25px;
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
	<jsp:include page="../include/nav_community.jsp" />
	

    <article>
    <form>
	<h2>인기 게시판</h2>
	<div class="clear" style="width:1000px; border-top: 1px solid black"></div>
	
	<table id="board">
		<tr>
			<th class="tno" style="width: 50px;">번호</th>
			<th class="ttitle" style="width: 600px;">제목</th>
			<th class="twrite" style="width: 150px;">작성자</th>
			<th class="tdate" style="width: 100px;">등록일</th>
			<th class="tread" style="width: 100px;">조회수</th>
		</tr>
	<c:choose>
		<c:when test="${freePageInfoMap.count gt 0}">
			<c:forEach var="boardVO" items="${fBoardList}">
				<c:set var="writtenReply" value="${freplyDao.writtenReplyCount(boardVO.num)}" scope="page" />
				<tr class="board_list" onclick="location.href='/community/freeContent?num=${boardVO.num}&pageNum=${pageNum}';">
					<td>${boardVO.num}</td>
					<td style="padding-left: 15px; text-align: left;">
				<c:if test="${boardVO.reLev gt 0}">
					<c:set var="level" value="${boardVO.reLev*10}" scope="page" />
					<img src="/resources/images/level.gif" width="${level}" height="13">
		  			<img src="/resources/images/icon_re.gif">
				</c:if>
					${boardVO.subject}
				<c:if test="${writtenReply gt 0}">
					[<span style="color:#FF9966; font-weight: bold;">${writtenReply}</span>]
				</c:if>
					</td>
					<td>${boardVO.usernick}</td>
				  	<td><fmt:formatDate value="${boardVO.regDate}" pattern="yyyy.MM.dd" /></td>
				  	<td>${boardVO.readcount}</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
				<tr>
					<td colspan="5">게시판에 작성된 글이 없습니다.</td>
				</tr>
		</c:otherwise>
	</c:choose>
	</table>
	
	<div class="clear" style="width:1000px; margin-top: 100px; border-bottom: 1px solid black"></div>
	
	<div id="page_control">
		<c:if test="${freePageInfoMap.count gt 0}">
			<c:if test="${freePageInfoMap.startPage gt freePageInfoMap.pageBlock}">
				<a href="/community/best?pageNum=${freePageInfoMap.startPage - freePageInfoMap.pageBlock}&searchType=${searchType}&search=${search}">[이전]</a>
			</c:if>
			
			<c:forEach var="i" begin="${freePageInfoMap.startPage}" end="${freePageInfoMap.endPage}" step="1">
				<a href="/community/best?pageNum=${i}&searchType=${searchType}&search=${search}">
				<c:choose>
					<c:when test="${i eq pageNum}">
						<span style="color: #9999FF; font-weight: bold;">[${i}]</span>
					</c:when>
					<c:otherwise>
						<span>${i}</span>
					</c:otherwise>
				</c:choose>
				</a>
	    	</c:forEach>
			
			<c:if test="${freePageInfoMap.endPage lt freePageInfoMap.pageCount}">
				<a href="/community/best?pageNum=${freePageInfoMap.startPage + freePageInfoMap.pageBlock}&searchType=${searchType}&search=${search}">[다음]</a>
			</c:if>
		</c:if>
	<div class="clear"></div>
	</div>
	<div class="clear" style="height: 10px;"></div>
	
	<div id="buttons">
		<select name="searchType" class="opt">
			<option value="subject">제목</option>
			<option value="username">작성자</option>
			<option value="content">내용</option>
		</select>
		<input type="text" name="search" value="${empty search ? '' : search}" class="search">
		<input type="submit" value="검색" class="submit">
	</div>
	</form>
    </article>

    <div class="clear" ></div>

    <!-- footer 영역 -->
	<jsp:include page="../include/footer.jsp" />
	
</div>

</body>
</html>