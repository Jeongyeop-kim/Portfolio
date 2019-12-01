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

	#board_list_wrap {
		float: left;
		width: 1000px;
	}
	
	#board_list_wrap .buttons {
		float: left;
		width: 944px;
		height: 22px;
		text-align: right;
	}

	#board_list_wrap .buttons .tSearch, .buttons .fSearch {
		width: 260px;
		height: 20px;
	}

	#board_list_wrap .delete_button {
		float: right;
		width: 50px;
		height: 22px;
		margin-right: 1px;
	}
	
	#board_list_wrap .board_list {
		float: left;
		width: 1000px;
		height: 293px;
		border: 2px solid #666666;
		overflow: scroll;
		overflow-y: hidden;
	}

	#board_list_wrap .board_list .board_list_headline {
		width: 1230px;
		height: 20px;
		background-color: #c0c0c0;
        text-align: center;
        border-bottom: 1px solid black;
	}
	
	#board_list_wrap .board_list .board_list_content {
		width: 1230px;
		height: 16px;
		background-color: lightgray;
        text-align: center;
        border-bottom: 1px dotted black;
	}
	
	#board_list_wrap .board_list .board_list_headline .board_list_checkbox {
		float: left;
		width: 29px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        font-weight: bold;
        text-align: center;
        border-right: 1px solid white;
	}

	#board_list_wrap .board_list .board_list_headline .board_list_username {
		float: left;
		width: 109px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        font-weight: bold;
        text-align: center;
        border-right: 1px solid white;
	}
	
	#board_list_wrap .board_list .board_list_headline .board_list_ip {
		float: left;
		width: 119px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        font-weight: bold;
        text-align: center;
        border-right: 1px solid white;
	}
	
	#board_list_wrap .board_list .board_list_headline .board_list_subject {
		float: left;
		width: 184px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        font-weight: bold;
        text-align: center;
        border-right: 1px solid white;
	}
	
	#board_list_wrap .board_list .board_list_headline .board_list_reg_date {
		float: left;
		width: 185px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        font-weight: bold;
        text-align: center;
	}
	
	#board_list_wrap .board_list .board_list_headline .board_list_con {
		float: left;
		width: 399px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        font-weight: bold;
        text-align: center;
        border-right: 1px solid white;
        text-overflow: ellipsis;
	}
	
	#board_list_wrap .board_list .board_list_headline .board_list_readcount, .board_list_headline .board_list_num {
		float: left;
		width: 99px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        font-weight: bold;
        text-align: center;
        border-right: 1px solid white;
	}


	#board_list_wrap .board_list .board_list_content .board_list_checkbox {
		float: left;
		width: 30px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        text-align: center;
	}

	#board_list_wrap .board_list .board_list_content .board_list_username {
		float: left;
		width: 110px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        text-align: center;
	}
	
	#board_list_wrap .board_list .board_list_content .board_list_ip {
		float: left;
		width: 120px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        text-align: center;
	}
	
	#board_list_wrap .board_list .board_list_content .board_list_subject, .board_list_content .board_list_reg_date {
		float: left;
		width: 185px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        text-align: center;
	}
	
	#board_list_wrap .board_list .board_list_content .board_list_con {
		float: left;
		width: 400px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        text-align: center;
        text-overflow: ellipsis;
	}
	
	#board_list_wrap .board_list .board_list_content .board_list_readcount, .board_list_content .board_list_num {
		float: left;
		width: 100px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        text-align: center;
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
	<jsp:include page="../include/nav_admin.jsp" />
	

    <article>

	<h2>게시판 관리</h2>
	<div class="clear" style="width: 1000px; border-top: 1px solid black"></div>
	
	<div id="mychart" style="width: 1000px; height: 500px;"></div>
	
	<div class="clear" style="margin-top:40px; border-top:1px solid black"></div>
	
	<!-- 중고거래 게시판 -->
	<div id="board_list_wrap">
	<h2>중고매매 게시판</h2>
	<div class="clear" style="width: 1000px; border-top: 1px solid black"></div>
		<div class="buttons">
			<form name="tfrm" action="/admin/boards" method="get">
				<input type="text" name="tSearch" value="${empty tSearch ? '' : tSearch}" class="tSearch">
				<input type="submit" value="검색">
			</form>
		</div>
		<form name="deleteTboard_frm" action="/admin/deleteBoards" method="post">
			<div class="delete_button">
				<input type="submit" value="글삭제" class="delete">
			</div>
		<div class="clear" style="height: 10px;"></div>
		
		<div class="board_list">
			<div class="board_list_headline">
				<div class="board_list_checkbox">선택</div>
				<div class="board_list_num">글번호</div>
				<div class="board_list_username">작성자</div>
				<div class="board_list_subject">제목</div>
				<div class="board_list_con">내용</div>
				<div class="board_list_readcount">조회수</div>
				<div class="board_list_ip">작성자 IP</div>
				<div class="board_list_reg_date" >작성일자</div>
			</div>
			
			<c:if test="${tPageInfoMap.tCount gt 0}">
				<c:forEach var="tBoardVO" items="${tBoardList}">
					<div class="board_list_content">
						<div class="board_list_checkbox"><input type="checkbox" name="tCheck" value="${tBoardVO.num}"></div>
						<div class="board_list_num">${tBoardVO.num}</div>
						<div class="board_list_username">${tBoardVO.username}</div>
						<div class="board_list_subject">${tBoardVO.subject}</div>
						<div class="board_list_con">${tBoardVO.content}</div>
						<div class="board_list_readcount">${tBoardVO.readcount}</div>
						<div class="board_list_ip">${tBoardVO.ip}</div>
						<div class="board_list_reg_date"><fmt:formatDate value="${tBoardVO.regDate}" pattern="yyyy.MM.dd" /></div><br>
					</div>
				</c:forEach>
			</c:if>
		</div>
		</form>
	</div>
	
	<div id="page_control">
		<c:if test="${tPageInfoMap.tCount gt 0}">
			<c:if test="${tPageInfoMap.tStartPage gt tPageInfoMap.tPageBlock}">
				<a href="/admin/boards?tPageNum=${tPageInfoMap.tStartPage - tPageInfoMap.tPageBlock}&tSearch=${tSearch}">[이전]</a>
			</c:if>
			
			<c:forEach var="i" begin="${tPageInfoMap.tStartPage}" end="${tPageInfoMap.tEndPage}" step="1">
				<a href="/admin/boards?tPageNum=${i}&tSearch=${tSearch}">
				<c:choose>
					<c:when test="${i eq tPageNum}">
						<span style="color: #9999FF; font-weight: bold;">[${i}]</span>
					</c:when>
					<c:otherwise>
						<span>${i}</span>
					</c:otherwise>
				</c:choose>
				</a>
	    	</c:forEach>
			
			<c:if test="${tPageInfoMap.tEndPage lt tPageInfoMap.tPageCount}">
				<a href="/admin/boards?tPageNum=${tPageInfoMap.tStartPage + tPageInfoMap.tPageBlock}&tSearch=${tSearch}">[다음]</a>
			</c:if>
		</c:if>
	</div>
	
	<div class="clear" style="border-top: 1px solid black"></div>
	
	
	<!-- 자유 게시판 -->
	<div id="board_list_wrap">
	<h2>자유 게시판</h2>
	<div class="clear" style="width: 1000px; border-top: 1px solid black"></div>
		<div class="buttons">
			<form name="ffrm" action="/admin/boards" method="get">
				<input type="text" name="fSearch" value="${empty fSearch ? '' : fSearch}" class="fSearch">
				<input type="submit" value="검색">
			</form>
		</div>
		<form name="deleteFboard_frm" action="/admin/deleteBoards" method="post">
			<div class="delete_button">
				<input type="submit" value="글삭제" class="delete">
			</div>
		<div class="clear" style="height: 10px;"></div>
		
		<div class="board_list">
			<div class="board_list_headline">
				<div class="board_list_checkbox">선택</div>
				<div class="board_list_num">글번호</div>
				<div class="board_list_username">작성자</div>
				<div class="board_list_subject">제목</div>
				<div class="board_list_con">내용</div>
				<div class="board_list_readcount">조회수</div>
				<div class="board_list_ip">작성자 IP</div>
				<div class="board_list_reg_date">작성일자</div>
			</div>
			
			<c:if test="${fPageInfoMap.fCount gt 0}">
				<c:forEach var="fBoardVO" items="${fBoardList}">
					<div class="board_list_content">
						<div class="board_list_checkbox"><input type="checkbox" name="fCheck" value="${fBoardVO.num}"></div>
						<div class="board_list_num">${fBoardVO.num}</div>
						<div class="board_list_username">${fBoardVO.username}</div>
						<div class="board_list_subject">${fBoardVO.subject}</div>
						<div class="board_list_con">${fBoardVO.content}</div>
						<div class="board_list_readcount">${fBoardVO.readcount}</div>
						<div class="board_list_ip">${fBoardVO.ip}</div>
						<div class="board_list_reg_date"><fmt:formatDate value="${fBoardVO.regDate}" pattern="yyyy.MM.dd" /></div><br>
					</div>
				</c:forEach>
			</c:if>
		</div>
		</form>
	</div>
	
	<div id="page_control">
		<c:if test="${fPageInfoMap.fCount gt 0}">
			<c:if test="${fPageInfoMap.fStartPage gt fPageInfoMap.fPageBlock}">
				<a href="/admin/boards?fPageNum=${fPageInfoMap.fStartPage - fPageInfoMap.fPageBlock}&fSearch=${fSearch}">[이전]</a>
			</c:if>
			
			<c:forEach var="i" begin="${fPageInfoMap.fStartPage}" end="${fPageInfoMap.fEndPage}" step="1">
				<a href="/admin/boards?fPageNum=${i}&fSearch=${fSearch}">
				<c:choose>
					<c:when test="${i eq fPageNum}">
						<span style="color: #9999FF; font-weight: bold;">[${i}]</span>
					</c:when>
					<c:otherwise>
						<span>${i}</span>
					</c:otherwise>
				</c:choose>
				</a>
	    	</c:forEach>
			
			<c:if test="${fPageInfoMap.fEndPage lt fPageInfoMap.fPageCount}">
				<a href="/admin/boards?fPageNum=${fPageInfoMap.fStartPage + fPageInfoMap.fPageBlock}&fSearch=${fSearch}">[다음]</a>
			</c:if>
		</c:if>
	</div>
	
	<div class="clear" style="width: 1000px; margin-top: 100px; border-bottom: 1px solid black"></div>
	
    </article>

    <div class="clear" ></div>

    <!-- footer 영역 -->
	<jsp:include page="../include/footer.jsp" />
	
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://www.gstatic.com/charts/loader.js"></script>
<script>
	google.charts.load('current', {packages: ['corechart']});
	google.charts.setOnLoadCallback(drawChart);
		
	function drawChart(array) {
		var array = ${jsonArray}
		var dataTable = google.visualization.arrayToDataTable(array);
		var options = {
			title: '테이블별 작성글 개수',
			hAxis: {
				title: '테이블명',
				titleTextStyle: {
					color: 'red'
				}
			}
		};

	var objDiv = document.getElementById('mychart');
	var chart = new google.visualization.ColumnChart(objDiv);
	chart.draw(dataTable, options);
	}
</script>

</body>
</html>