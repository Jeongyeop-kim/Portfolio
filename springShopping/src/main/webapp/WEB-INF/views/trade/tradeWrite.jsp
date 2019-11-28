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

	#write_subject {
		float: left;
		width: 1000px;
		height: 40px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 14px;
	}
	
	#write_subject input {
		width: 500px;
		height: 20px;
		margin-left: 20px;
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
		width: 995px;
		height: 130px;
		overflow: scroll;
		border: 2px solid lightgray;
	}
	
	#file_container {
		width: 995px;
		height: 22px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
	}
	
	#file_container input {
		height: 20px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
	}
	
	#write_content {
		float: left;
		width: 1000px;
		height: 200px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 12px;
	}

	#write_content textarea {
		resize: none;
		overflow: scroll;
		overflow-x: hidden;
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
	    <form name="frm" action="/trade/tradeWrite" method="post" enctype="multipart/form-data">
		<h2>중고매매</h2>
		<div class="clear" style="width:1000px; border-top: 1px solid black;"></div>
		<c:if test="${not empty loginMember}">
			<input type="hidden" name="username" value="${loginMember.id}">
		</c:if>
		
		<div id="write_subject">
			제목<input type="text" name="subject" placeholder="게시글 제목을 입력해주세요.">
		</div>
		
		<div class="clear" style="height: 10px;"></div>
		
		<div id="file_attach">
			첨부 파일
			<input type="button" value="파일추가" class="btn" onclick="addFileElement();">
			<div class="clear"></div>
			
			<div class="attach_box">
				<div id="file_container">
					<input type="file" name="files">
				</div>
			</div>
		</div>
		
		<div class="clear"></div>
		
		<div id="write_content">
			<textarea name="content" rows="15" cols="136"></textarea>
		</div>
		
		<div class="clear"></div>
		
		<div id="write_buttons">
			<input type="button" value="임시저장" class="btn">
			<input type="submit" value="확인" class="btn">
			<input type="button" value="목록보기" class="btn" onclick="location.href='/trade/trade';">
		</div>
		</form>
    </article>

    <div class="clear"></div>

    <!-- footer 영역 -->
	<jsp:include page="../include/footer.jsp" />
	
</div>

<script>
var num = 2; // 초기값
function addFileElement() {
	var input = '<br><input type="file" name="files">';
	num++;
	var fileContainer = document.getElementById('file_container');
	fileContainer.innerHTML += input;
}

</script>

</body>
</html>