<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Main</title>
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

	#item_wrap {
		float: left;
		width: 1000px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 12px;
	}
	
	#item_wrap .item_image {
		float: left;
		width: 200px;
		height: 200px;
		border: 1px solid black;
	}
	
	#item_wrap .item_image img {
		width: 200px;
		height: 200px;
	}
	
	#item_wrap .item_content {
		float: left;
		width: 680px;
		height: 200px;
		margin-left: 20px;
	}
	
	#item_wrap .item_button {
		float: right;
		width: 70px;
		height: 200px;
		margin-left: 20px;
	}
	
	#item_wrap .item_button button {
		width: 70px;
		height: 200px;
		margin-top: 50px;
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
    <form>
	<h2>경매</h2>
	<div class="clear" style="width:1000px; border-top: 1px solid black"></div>
	
	<div id="item_wrap">
	
		<div class="item_image">
			
		</div>
		<div class="item_content">
		<div id="file_attach">
			파일 첨부
			<input type="button" value="파일추가" class="btn" onclick="addFileElement();">
			<div class="clear"></div>
			
			<div class="attach_box">
				<div id="file_container">
					<input type="file" name="filename1">
				</div>
			</div>
		</div>	
		</div>
		
		

	</div>		
	
	<div class="clear" style="width:1000px; margin-top: 100px; border-bottom: 1px solid black"></div>
	

	<div class="clear"></div>
	
		<div class="write_button">
			<input type="button" value="등록하기" onclick="location.href='auctionRegister.jsp';">
		</div>

	
	<div class="clear"></div>
	
	</form>
    </article>

    <div class="clear" ></div>

    <!-- footer 영역 -->
	<jsp:include page="../include/footer.jsp" />
	
</div>


</body>
</html>