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
        min-height: 1600px;
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

	#board {
		float: left;
		width: 1000px;
		height: 40px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 14px;
	}
	
	#board input {
		width: 500px;
		height: 20px;
		margin-left: 20px;
	}
	
	#board_content {
		float: left;
		width: 1000px;
		height: 500px;
		overflow: scroll;
		border: 1px solid black;
		font-family: "맑은 고딕", sans-serif;
        font-size: 14px;
	}
	
	#file_attach {
		float: left;
		width: 1000px;
		height: 160px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
	}	
	
	#file_attach .btn {
		width: 100px;
		height: 20px;
		margin-left: 20px;
	}
	
	#file_attach .attach_box {
		width: 1000px;
		height: 130px;
		overflow: scroll;
		border: 2px solid lightgray;
	}
	
	#file_container {
		float: left;
		width: 200px;
		margin-left: 20px;
	}
	
	#file_container .scale {
		transform: scale(1);
		-webkit-transform: scale(1);
		-moz-transform: scale(1);
		-ms-transform: scale(1);
		-o-transform: scale(1);
		transition: all 0.3s ease-in-out;   /* 부드러운 모션을 위해 추가*/
	}
	
	#file_container .scale:hover {
		transform: scale(1.2);
		-webkit-transform: scale(1.2);
		-moz-transform: scale(1.2);
		-ms-transform: scale(1.2);
		-o-transform: scale(1.2);
		width: 400px;
		height: 400px;
	}
	
	#file_container .img {
		width: 100px; 
		height: 100px; 
		overflow: hidden;
	}

	#write_buttons {
		float: left;
		width: 1000px;
		margin-top: 20px;
		text-align: center;
	}

	#write_buttons .btn{
		width: 100px;
		height: 35px;
		cursor: pointer; 
		cursor: hand;
	}

	#reply {
		float: left;
		width: 1000px;
		height: 80px;
		border-top: 1px solid #666666;
		border-bottom: 1px solid #666666;
		font-family: "맑은 고딕", sans-serif;
        font-size: 12px;
	}
	
	#reply .reply_id span {
		cursor: pointer; 
		cursor: hand;
	}
	
	#reply textarea {
		resize: none;
		border: none;
	}

	#reply_content {
		float: left;
		width: 1000px;
		height: 90px;
		margin-top: 20px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 12px;
	}
	
	#reply_content .inputReply {
		resize: none;
	}
	
	#reply_content .btn {
		float: right;
		width: 93px;
		height: 93px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 12px;
	}
	
	#reply_pagecontrol {
		float: left;
		width: 1000px;
		height: 20px;
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

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
		<h2>중고매매</h2>
		<div class="clear" style="width:1000px; border-top: 1px solid black;"></div>
		
		<div id="board">
			제목 &nbsp; ${boardVO.subject}
			<span style="margin-left: 150px;"><fmt:formatDate value="${boardVO.regDate}" pattern="yyyy.MM.dd" /></span>
		</div>
		
		<div id="board">
			이름 &nbsp; ${boardVO.username}
		</div>
		
		<div class="clear" style="height: 10px;"></div>
		
		<div id="file_attach">
			첨부 파일
			<div class="attach_box">
				<div id="file_container">
					<c:forEach var="attachVO" items="${attachList}">
						<c:if test="${attachVO.filetype eq 'I'}">
						<div id="product">
						<div class="scale"><img src="/resources/upload/${attachVO.uploadpath}/${attachVO.uuid}_${attachVO.filename}"></div>
						<a href="/resources/upload/${attachVO.uploadpath}/${attachVO.uuid}_${attachVO.filename}" download>${attachVO.filename}</a>
						</div>
						</c:if>
					</c:forEach>
				
					<div class="clear"></div>
				</div>
			</div>
			
		</div>
		
		<div class="clear"></div>
		
		<div id="board_content">
			<pre>${boardVO.content}</pre>
		</div>
		
		<div id="board">
			조회수 &nbsp; ${boardVO.readcount}
		</div>
		<c:if test="${replyPageInfoMap.count gt 0}">
			<c:forEach var="replyVO" items="${treplyList}">
				<c:if test="${boardVO.num eq replyVO.parentnum}">
					<c:set var="parNum" value="${replyVO.parentnum}" scope="page" />
					<form name="reply${replyVO.num}" action="/trade/tradeReplyDelete" method="post">
					<script>
					// 댓글 수정
					$(document).one('click', 'span.u${replyVO.num}', function() {
						var $u = $('span.u${replyVO.num}').closest('form');
						var btn = '<span class="check${replyVO.num}"> | 완료</span>';
						$u.children('div#reply').attr('style', 'background-color:white; border:1px solid #c0c0c0;');
						$u.find('.ta${replyVO.num}').attr('style', 'background-color:white; border:1px solid #c0c0c0;').removeAttr("readonly");
						$('span.u${replyVO.num}').append(btn);
					});
					
					// 댓글 수정 전송
					$(document).on('click', 'span.check${replyVO.num}', function() {
						var pageNum = ${pageNum};
						var num = ${boardVO.num};
						var replyNum = ${replyVO.num};
						var uContent = $('textarea.ta${replyVO.num}').val();
						
						$.ajax({
							url: '/trade/tradeReplyUpdateJson',
							data: {pageNum: pageNum, num: num, replyNum: replyNum, uContent: uContent},
							method: 'POST',
							success: function (data) {
								$('textarea.ta${replyVO.num}').val(data);
							}
						});
						var $u = $('span.u${replyVO.num}').closest('form');
						$u.children('div#reply').attr('style', 'border-top:1px solid #666666; border-bottom:1px solid #666666');
						$u.find('.ta${replyVO.num}').attr('style', 'background-color:none; border:none;').attr('readonly', true);
						$('span.check${replyVO.num}').detach();
					});
					
					// 댓글 삭제
					$(document).on('click', 'span.d${replyVO.num}', function() {
						var pageNum = ${pageNum};
						var num = ${boardVO.num};
						var replyNum = ${replyVO.num};
						$.ajax({
							url: '/trade/tradeReplyDeleteJson',
							data: {pageNum: pageNum, num: num, replyNum: replyNum},
							method: 'POST',
							success: function (data) {
							}
						});
						var $d = $('span.d${replyVO.num}').closest('form');
						$d.children('div#reply').remove();
						alert('댓글이 삭제되었습니다.');
					});
					</script>
					<div id="reply">
						<div class="reply_id">${replyVO.username}
						<c:if test="${not empty loginMember and loginMember.id eq replyVO.username}">
							<span class="u${replyVO.num}">수정</span> | <span class="d${replyVO.num}">삭제</span><br>
						</c:if>
						</div>
						<pre><textarea name="uContent" class="ta${replyVO.num}" rows="2" cols="136" readonly>${replyVO.content}</textarea></pre>
					</div>
					<input type="hidden" name="replyNum" value="${replyVO.num}">
					<input type="hidden" name="num" value="${boardVO.num}">
					<input type="hidden" name="pageNum" value="${pageNum}">
					</form>
				</c:if>
			</c:forEach>
		</c:if>
		
		<div class="clear"></div>
		
		<div id="reply_pagecontrol">
			<c:if test="${replyPageInfoMap.count gt 0 and boardVO.num eq parNum}">
				<c:if test="${replyPageInfoMap.startPage gt replyPageInfoMap.pageBlock}">
					<a href="/trade/tradeContent?replyPageNum=${replyPageInfoMap.startPage - replyPageInfoMap.pageBlock}&num=${boardVO.num}&pageNum=${pageNum}">[이전]</a>
				</c:if>
				
				<c:forEach var="i" begin="${replyPageInfoMap.startPage}" end="${replyPageInfoMap.endPage}" step="1">
					<a href="/trade/tradeContent?replyPageNum=${i}&num=${boardVO.num}&pageNum=${pageNum}">
					<c:choose>
						<c:when test="${i eq replyPageNum}">
							<span style="color: #9999FF; font-weight: bold;">[${i}]</span>
						</c:when>
						<c:otherwise>
							<span>${i}</span>
						</c:otherwise>
					</c:choose>
					</a>
		    	</c:forEach>
				
				<c:if test="${replyPageInfoMap.endPage lt replyPageInfoMap.pageCount}">
					<a href="/trade/tradeContent?replyPageNum=${replyPageInfoMap.startPage + replyPageInfoMap.pageBlock}&num=${boardVO.num}&pageNum=${pageNum}">[다음]</a>
				</c:if>
			</c:if>
		</div>
		
		<div class="clear"></div>
		
		<form name="frm" action="/trade/tradeReply" method="post">
		<div id="reply_content">
			<textarea class="inputReply" name="content" rows="6" cols="123"></textarea>
			<input type="submit" value="댓글쓰기" class="btn">
			<input type="hidden" name="num" value="${boardVO.num}">
			<input type="hidden" name="pageNum" value="${pageNum}">
			<input type="hidden" name="parentnum" value="${boardVO.num}">
			<c:if test="${not empty loginMember}">
				<input type="hidden" name="username" value="${loginMember.id}">
			</c:if>
		</div>
		</form>
		<div class="clear"></div>
		
		<div id="write_buttons">
		<c:if test="${not empty loginMember}">
			<c:if test="${loginMember.id eq boardVO.username or loginMember.id eq 'admin'}">
				<input type="button" value="수정하기" class="btn" onclick="location.href='/trade/tradeUpdate?num=${boardVO.num}&pageNum=${pageNum}';">
				<input type="submit" value="삭제하기" class="btn" onclick="location.href='/trade/tradeDelete?num=${boardVO.num}&pageNum=${pageNum}';">
			</c:if>
			<input type="button" value="답글쓰기" class="btn" onclick="location.href='/trade/tradeReWrite?reRef=${boardVO.reRef}&reLev=${boardVO.reLev}&reSeq=${boardVO.reSeq}';">
		</c:if>
		<input type="button" value="목록보기" class="btn" onclick="location.href='/trade/trade?pageNum=${pageNum}';">
		</div>
    </article>

    <div class="clear"></div>

    <!-- footer 영역 -->
	<jsp:include page="../include/footer.jsp" />
	
</div>

</body>
</html>