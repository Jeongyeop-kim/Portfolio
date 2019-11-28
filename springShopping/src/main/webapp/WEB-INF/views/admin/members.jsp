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

	#member_list_wrap {
		float: left;
		width: 1000px;
	}
	
	#member_list_wrap .buttons {
		float: left;
		width: 930px;
		height: 22px;
		text-align: right;
	}

	#member_list_wrap .buttons .mSearch {
		width: 260px;
		height: 20px;
	}

	#member_list_wrap .delete_button {
		float: right;
		width: 50px;
		height: 22px;
		margin-right: 15px;
	}
		
	#member_list_wrap .member_list {
		float: left;
		width: 1000px;
		height: 586px;
		border: 2px solid #666666;
		overflow: scroll;
		overflow-y: hidden;
	}

	#member_list_wrap .member_list .member_list_headline {
		width: 1370px;
		height: 20px;
		background-color: #c0c0c0;
        text-align: center;
        border-bottom: 1px solid black;
	}
	
	#member_list_wrap .member_list .member_list_content {
		width: 1370px;
		height: 16px;
		background-color: lightgray;
        text-align: center;
        border-bottom: 1px dotted black;
	}
	
	#member_list_wrap .member_list .member_list_headline .member_list_checkbox {
		float: left;
		width: 29px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        font-weight: bold;
        text-align: center;
        border-right: 1px solid white;
	}

	#member_list_wrap .member_list .member_list_headline .member_list_id {
		float: left;
		width: 109px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        font-weight: bold;
        text-align: center;
        border-right: 1px solid white;
	}
	
	#member_list_wrap .member_list .member_list_headline .member_list_passwd, .member_list_headline .member_list_tel, .member_list_headline .member_list_mtel {
		float: left;
		width: 119px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        font-weight: bold;
        text-align: center;
        border-right: 1px solid white;
	}
	
	#member_list_wrap .member_list .member_list_headline .member_list_email, .member_list_headline .member_list_reg_date, .member_list_headline .member_list_address {
		float: left;
		width: 184px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        font-weight: bold;
        text-align: center;
	}
	
	#member_list_wrap .member_list .member_list_headline .member_list_address {
		float: left;
		width: 284px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        font-weight: bold;
        text-align: center;
	}
	
	#member_list_wrap .member_list .member_list_headline .member_list_name, .member_list_headline .member_list_nickname {
		float: left;
		width: 99px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        font-weight: bold;
        text-align: center;
        border-right: 1px solid white;
	}


	#member_list_wrap .member_list .member_list_content .member_list_checkbox {
		float: left;
		width: 30px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        text-align: center;
	}

	#member_list_wrap .member_list .member_list_content .member_list_id {
		float: left;
		width: 110px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        text-align: center;
	}
	
	#member_list_wrap .member_list .member_list_content .member_list_passwd, .member_list_content .member_list_tel, .member_list_content .member_list_mtel {
		float: left;
		width: 120px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        text-align: center;
	}
	
	#member_list_wrap .member_list_passwd input {
		width: 120px;
		height: 13px;
		border-left: none;
		border-right: none;
		border-top: none;
		border-bottom: none;
		background-color: lightgray;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
	}
	
	#member_list_wrap .member_list .member_list_content .member_list_email, .member_list_content .member_list_reg_date, .member_list_content .member_list_address {
		float: left;
		width: 185px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        text-align: center;
	}
	
	#member_list_wrap .member_list .member_list_content .member_list_address {
		float: left;
		width: 285px;
		height: 16px;
		font-family: "맑은 고딕", sans-serif;
        font-size: 11px;
        text-align: center;
	}
	
	#member_list_wrap .member_list .member_list_content .member_list_name, .member_list_content .member_list_nickname {
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

	<h2>회원 관리</h2>
	<div class="clear" style="width: 1000px; border-top: 1px solid black"></div>
	
	<div id="mychart" style="width: 1000px; height: 500px;"></div>
	
	<div class="clear" style="margin-top:40px; border-top:1px solid black"></div>

	
	<div id="member_list_wrap">
	<h2>회원 목록</h2>
	<div class="clear" style="width: 1000px; border-top: 1px solid black"></div>
		<div class="buttons">
			<form name="frm" action="/admin/members" method="get">
				<input type="text" name="mSearch" value="${empty mSearch ? '' : mSearch}" class="mSearch">
				<input type="submit" value="검색">
				<input type="button" value="변경 활성화" id="removeReadonlyPasswd">
				<input type="button" value="비밀번호 변경" id="updatePasswd">
			</form>
		</div>
		<form name="dfrm" action="/admin/deleteMembers" method="post">
			<div class="delete_button">
				<input type="submit" value="강제탈퇴" class="delete">
			</div>
		<div class="clear" style="height: 10px;"></div>
		
		<div class="member_list">
			<div class="member_list_headline">
				<div class="member_list_checkbox">선택</div>
				<div class="member_list_id">아이디</div>
				<div class="member_list_passwd">비밀번호</div>
				<div class="member_list_name">이름</div>
				<div class="member_list_nickname">닉네임</div>
				<div class="member_list_email" style="border-right: 1px solid white;">이메일</div>
				<div class="member_list_address" style="border-right: 1px solid white;">주소</div>
				<div class="member_list_tel">전화번호</div>
				<div class="member_list_mtel">휴대폰번호</div>
				<div class="member_list_reg_date" style="width: 185px;">가입일자</div>
			</div>
			
			<c:if test="${membersPageInfoMap.count gt 0}">
				<c:forEach var="memberVO" items="${mBoardList}">
					<div class="member_list_content">
						<div class="member_list_checkbox"><input type="checkbox" name="check" value="${memberVO.id}" id="check"></div>
						<div class="member_list_id">${memberVO.id}</div>
						<div class="member_list_passwd"><input type="text" name="newpasswd" value="${memberVO.passwd}" id="newpasswd" readonly></div>
						<div class="member_list_name">${memberVO.name}</div>
						<div class="member_list_nickname">${memberVO.nickname}</div>
						<div class="member_list_email">${memberVO.email}</div>
						<div class="member_list_address">${memberVO.address}</div>
						<div class="member_list_tel">${memberVO.tel}</div>
						<div class="member_list_mtel">${memberVO.mtel}</div>
						<div class="member_list_reg_date"><fmt:formatDate value="${memberVO.regDate}" pattern="yyyy.MM.dd" /></div><br>
					</div>
				</c:forEach>
			</c:if>
		</div>
		</form>
	</div>
	
	<div id="page_control">
		<c:if test="${membersPageInfoMap.count gt 0}">
			<c:if test="${membersPageInfoMap.startPage gt membersPageInfoMap.pageBlock}">
				<a href="/admin/members?mPageNum=${membersPageInfoMap.startPage - membersPageInfoMap.pageBlock}&mSearch=${mSearch}">[이전]</a>
			</c:if>
			
			<c:forEach var="i" begin="${membersPageInfoMap.startPage}" end="${membersPageInfoMap.endPage}" step="1">
				<a href="/admin/members?mPageNum=${i}&mSearch=${mSearch}">
				<c:choose>
					<c:when test="${i eq mPageNum}">
						<span style="color: #9999FF; font-weight: bold;">[${i}]</span>
					</c:when>
					<c:otherwise>
						<span>${i}</span>
					</c:otherwise>
				</c:choose>
				</a>
	    	</c:forEach>
			
			<c:if test="${membersPageInfoMap.endPage lt membersPageInfoMap.pageCount}">
				<a href="/admin/members?mPageNum=${membersPageInfoMap.startPage + membersPageInfoMap.pageBlock}&mSearch=${mSearch}">[다음]</a>
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
			title: '지역별 회원수',
			hAxis: {
				title: '지역',
				titleTextStyle: {
					color: 'red'
				}
			}
		};

		var objDiv = document.getElementById('mychart');
		var chart = new google.visualization.ColumnChart(objDiv);
		chart.draw(dataTable, options);
	}
	
	$('#removeReadonlyPasswd').on('click', function() {
		$('input#newpasswd').attr('style', 'background-color:white; border-bottom:1px solid white;').removeAttr("readonly");
	});
	
	// 회원 비밀번호 강제 변경
	$(document).on('click', '#updatePasswd', function() {
		var idValues = new Array();
	    $('input#check').each( function(index, item) {
	    	console.log(index);
	    	console.log($(item).val());
	    	idValues.push($(item).val());
	    });
	    
	    var passwdValues = new Array();
	    $('input#newpasswd').each( function(index, item) {
	    	passwdValues.push($(item).val());
	    });  
	    
	    var mPageNum = ${mPageNum};
	    jQuery.ajaxSettings.traditional = true;
		$.ajax({
			method: 'POST',
			url: '/admin/updateMembers?mPageNum=${mPageNum}',
			data: {idValues: idValues, passwdValues: passwdValues},
			success: function (data) {
			}
		});
		alert('회원의 비밀번호를 변경하였습니다.');
	});
	
</script>

</body>
</html>