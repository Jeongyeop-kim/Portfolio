<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<title></title>

<style>

	div .clear {
        height: 40px;
        clear: both;
    }
	
	#address_table {
		width: 400px;
		height: 450px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 13px;
        text-align: center;
	}

	#address_table .head_zipcode {
		float: left;
		width: 80px;
		height: 30px;
		background-color: #666666;
	}
	
	#address_table .head_list {
		float: right;
		width: 320px;
		height: 30px;
		background-color: #666666;
	}
	
	#address_table .zipcode {
		float: left;
		width: 80px;
		height: 23px;
		background-color: lightgray;
		font-size: 12px;
	}
	
	#address_table .address_list {
		float: right;
		width: 320px;
		height: 23px;
		background-color: lightgray;
		font-size: 12px;
		cursor: pointer; 
		cursor: hand;
	}
	
	#page_control {
		float: left;
		width: 300px;
		height: 22px;
		margin-top: 10px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 12px;
        text-align: center;
	}
	
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<div id="address_table">
	<form action="/member/joinAddressCheck" method="get" name="wfrm">
	<b style="font-size: 14px;">주소찾기</b><br>
		<div class="head_zipcode"><b>우편번호</b></div>
		<div class="head_list"><b>주소</b></div>
		<br>
		<c:choose>
			<c:when test="${addrPageInfoMap.count gt 0}">
				<c:forEach var="cityVO" items="${addrBoardList}">
					<div class="zipcode">${cityVO.zipcode}</div>
					<div class="address_list" id="list${cityVO.seq}" onclick="useAdd();">${cityVO.sido} ${cityVO.gugun} ${cityVO.dong}</div>
					<script>
						$(document).on('click', 'div#list${cityVO.seq}', function() {
							$("input.address", parent.opener.document).val("${cityVO.sido} ${cityVO.gugun} ${cityVO.dong}");
							close();
						});
					</script>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="zipcode">X</div>
				<div class="address_list" id="list">검색 결과가 없습니다.</div>
			</c:otherwise>
		</c:choose>
	</form>	
	</div><br>
	
	<div id="page_control">
		<c:if test="${addrPageInfoMap.count gt 0}">
			<c:if test="${addrPageInfoMap.startPage gt addrPageInfoMap.pageBlock}">
				<a href="/member/joinAddressCheck?addrPageNum=${addrPageInfoMap.startPage - addrPageInfoMap.pageBlock}&inputAdd=${inputAdd}">[이전]</a>
			</c:if>
			
			<c:forEach var="i" begin="${addrPageInfoMap.startPage}" end="${addrPageInfoMap.endPage}" step="1">
				<a href="/member/joinAddressCheck?addrPageNum=${i}&inputAdd=${inputAdd}">
				<c:choose>
					<c:when test="${i eq pageNum}">
						<span style="font-weight: bold; color: #9999FF;">${i}</span>
					</c:when>
					<c:otherwise>
						<span>${i}</span>
					</c:otherwise>
				</c:choose>
				</a>
		    </c:forEach>
			
			<c:if test="${addrPageInfoMap.endPage lt addrPageInfoMap.pageCount}">
				<a href="/member/joinAddressCheck?addrPageNum=${addrPageInfoMap.startPage + addrPageInfoMap.pageBlock}&inputAdd=${inputAdd}">[다음]</a>
			</c:if>
		</c:if>
	</div>

</body>
</html>