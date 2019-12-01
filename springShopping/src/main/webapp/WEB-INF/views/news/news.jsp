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
	}
	
	#item_wrap .item_button {
		float: right;
		width: 70px;
		height: 200px;
		margin-left: 20px;
	}
	
	#item_wrap .item_button .btn {
		margin-top: 50px;
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
	
	#buttons {
		float: left;
		width: 1000px;
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
    
    <nav></nav>

    <article>
    <form>
	<h2>뉴스</h2>
	<div class="clear" style="width:1000px; border-top: 1px solid black"></div>
	
	<div id="item_wrap">
		
		<div>
		<c:forEach var="list" items="${items}">
			<c:set var="title" value="${list.title}"/>
			<c:set var="originallink" value="${list.originallink}"/>
			<c:set var="link" value="${list.link}"/>
			<c:set var="description" value="${list.description}"/>
			<div class="item_content">
				<b>${title}</b><br>
				 ${description}<br>
				<a href="${originallink}">${originallink}</a><br>
				<a href="${link}">${link}</a>
			<div class="clear" style="width: 1000px; margin-top: 10px; border-bottom: 1px solid black"></div>
			</div>
		</c:forEach>
		</div>

	</div>		
	
	<div class="clear" style="width: 1000px; margin-top: 100px; border-bottom: 1px solid black"></div>
	
	<div id="page_control">
	
	<div class="clear"></div>
	</div>
	
	</form>
    </article>

    <div class="clear" ></div>

    <!-- footer 영역 -->
	<jsp:include page="../include/footer.jsp" />
	
</div>

</body>
</html>