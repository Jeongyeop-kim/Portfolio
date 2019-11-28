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
        width: 1000px;
        margin-left: 20px;
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
	
	#board_content .good {
		width: 100px;
		height: 80px;
		margin-top: 320px;
		margin-left: auto;
		margin-right: auto;
		border: 1px solid #c0c0c0;
		color: blue;
		font-family: "맑은 고딕", sans-serif;
        font-size: 14px;
        text-align: center;
        cursor: pointer; 
		cursor: hand;
	}
	
	#board_content .good .good_count {
		float: left;
		width: 98px;
		height: 50px;
		border: 1px solid #c0c0c0;
		color: blue;
		font-family: "맑은 고딕", sans-serif;
        font-size: 14px;
        text-align: center;
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

	<!-- nav 영역 -->
	<jsp:include page="../include/nav_community.jsp" />

    <article>
		<h2>자유게시판</h2>
		<div class="clear" style="width:1000px; border-top: 1px solid black;"></div>
		
		<div id="board">
			제목 &nbsp; ${fboardVO.subject}
			<span style="margin-left: 150px;"><fmt:formatDate value="${fboardVO.regDate}" pattern="yyyy.MM.dd" /></span>
		</div>
		
		<div id="board">
			이름 &nbsp; ${fboardVO.usernick}
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
			<pre>${fboardVO.content}</pre>
			
			<div class="clear"></div>
			
			<div class="good">
				<span id="suggest">추천!</span>
				<input type="text" class="good_count" value="${fboardVO.good}" readonly>
			</div>
		</div>
		
		<div id="board">
			조회수 &nbsp; ${fboardVO.readcount}
		</div>
		<c:if test="${replyPageInfoMap.count gt 0}">
			<c:forEach var="replyVO" items="${freplyList}">
				<c:if test="${fboardVO.num eq replyVO.parentnum}">
					<c:set var="parNum" value="${replyVO.parentnum}" scope="page" />
					<form name="reply${replyVO.num}" action="/community/freeReplyDelete" method="post">
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
						var num = ${fboardVO.num};
						var replyNum = ${replyVO.num};
						var uContent = $('textarea.ta${replyVO.num}').val();
						
						$.ajax({
							url: '/community/freeReplyUpdateJson',
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
						var num = ${fboardVO.num};
						var replyNum = ${replyVO.num};
						$.ajax({
							url: '/community/freeReplyDeleteJson',
							data: {pageNum: pageNum, num: num, replyNum: replyNum},
							method: 'POST',
							success: function (data) {
							}
						});
						var $d = $('span.d${replyVO.num}').closest('form');
						$d.children('div#reply').remove();
						alert('댓글이 삭제되었습니다.');
					});
// 					$(document).on('click', 'span.d${replyVO.num}', function() {
// 						var $d = $('span.d${replyVO.num}').closest('form');
// 						$d.children('div#reply').remove();
// 						$('form[name=reply${replyVO.num}]').submit();
// 					});
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
					<input type="hidden" name="num" value="${fboardVO.num}">
					<input type="hidden" name="pageNum" value="${pageNum}">
					</form>
				</c:if>
			</c:forEach>
		</c:if>
		
		<div class="clear"></div>
		
		<div id="reply_pagecontrol">
			<c:if test="${replyPageInfoMap.count gt 0 and fboardVO.num eq parNum}">
				<c:if test="${replyPageInfoMap.startPage gt replyPageInfoMap.pageBlock}">
					<a href="/community/freeContent?replyPageNum=${replyPageInfoMap.startPage - replyPageInfoMap.pageBlock}&num=${fboardVO.num}&pageNum=${pageNum}">[이전]</a>
				</c:if>
				
				<c:forEach var="i" begin="${replyPageInfoMap.startPage}" end="${replyPageInfoMap.endPage}" step="1">
					<a href="/community/freeContent?replyPageNum=${i}&num=${fboardVO.num}&pageNum=${pageNum}">
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
					<a href="/community/freeContent?replyPageNum=${replyPageInfoMap.startPage + replyPageInfoMap.pageBlock}&num=${fboardVO.num}&pageNum=${pageNum}">[다음]</a>
				</c:if>
			</c:if>
		</div>
		
		<div class="clear"></div>
		
		<form name="frm" action="/community/freeReply" method="post">
		<div id="reply_content">
			<textarea class="inputReply" name="content" rows="6" cols="123"></textarea>
			<input type="submit" value="댓글쓰기" class="btn">
			<input type="hidden" name="num" value="${fboardVO.num}">
			<input type="hidden" name="pageNum" value="${pageNum}">
			<input type="hidden" name="parentnum" value="${fboardVO.num}">
			<c:if test="${not empty loginMember}">
				<input type="hidden" name="username" value="${loginMember.id}">
			</c:if>
		</div>
		</form>
		<div class="clear"></div>
		
		<div id="write_buttons">
		<c:if test="${not empty loginMember}">
			<c:if test="${loginMember.id eq fboardVO.username or loginMember.id eq 'admin'}">
				<input type="button" value="수정하기" class="btn" onclick="location.href='/community/freeUpdate?num=${fboardVO.num}&pageNum=${pageNum}';">
				<input type="submit" value="삭제하기" class="btn" onclick="location.href='/community/freeDelete?num=${fboardVO.num}&pageNum=${pageNum}';">
			</c:if>
			<input type="button" value="답글쓰기" class="btn" onclick="location.href='/community/freeReWrite?reRef=${fboardVO.reRef}&reLev=${fboardVO.reLev}&reSeq=${fboardVO.reSeq}';">
		</c:if>
		<input type="button" value="목록보기" class="btn" onclick="location.href='/community/free?pageNum=${pageNum}';">
		</div>
    </article>

    <div class="clear"></div>

    <!-- footer 영역 -->
	<jsp:include page="../include/footer.jsp" />
	
</div>

<script>
$('#suggest').on('click', function() {
	var username = "${loginMember.id}";
//	var pageNum = ${pageNum};
	var num = ${fboardVO.num};
//	var good = ${fboardVO.good};	
	
	if (username == null || username == "") {
		alert('로그인이 필요한 서비스입니다.');
		return false;
	}
	
	$.ajax({
		url: '/community/goodJson',
		data: {username: username, num: num},
		method: 'GET',
		success: function (data) {
			isUserRecommended(data);
		}
	});
});

function isUserRecommended(IsUserRec) {
	var sum = ${fboardVO.good};
	if (IsUserRec == true) { // 중복 true
		alert('추천은 한번만 가능합니다.');
	} else { // 중복아님 false
		sum += 1;
		$('input.good_count').val(sum);
		alert('이 글을 추천하였습니다.');
	}
}
</script>
</body>
</html>