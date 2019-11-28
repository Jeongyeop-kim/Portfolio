<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>

	<title></title>
	
<link href='/resources/packages/core/main.css' rel='stylesheet' />
<link href='/resources/packages/daygrid/main.css' rel='stylesheet' />
<link href='/resources/packages/list/main.css' rel='stylesheet' />
<script src='/resources/packages/core/main.js'></script>
<script src='/resources/packages/interaction/main.js'></script>
<script src='/resources/packages/daygrid/main.js'></script>
<script src='/resources/packages/list/main.js'></script>
<script src='/resources/packages/google-calendar/main.js'></script>
<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {

      plugins: [ 'interaction', 'dayGrid', 'list', 'googleCalendar' ],

      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,listYear'
      },

      displayEventTime: false, // don't show the time column in list view

      // THIS KEY WON'T WORK IN PRODUCTION!!!
      // To make your own Google API key, follow the directions here:
      // http://fullcalendar.io/docs/google_calendar/
      googleCalendarApiKey: 'AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE',

      // US Holidays
      events: 'en.usa#holiday@group.v.calendar.google.com',

      eventClick: function(arg) {
        // opens events in a popup window
        window.open(arg.event.url, 'google-calendar-event', 'width=700,height=600');

        arg.jsEvent.preventDefault() // don't navigate in main tab
      },

      loading: function(bool) {
        document.getElementById('loading').style.display =
          bool ? 'block' : 'none';
      }

    });

    calendar.render();
  });

</script>	
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
    
	#loading {
    	display: none;
    	position: absolute;
    	top: 10px;
    	right: 10px;
    }
    
	#calendar {
    	max-width: 900px;
    	margin: 0 auto;
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

	<h2>관리자 페이지</h2>
	<div class="clear" style="width: 1000px; border-top: 1px solid black"></div>
	
	<div id='loading'>loading...</div>
	<div id="calendar"></div>
	
	<div class="clear" style="width: 1000px; margin-top: 50px; border-bottom: 1px solid black"></div>
	
    </article>

    <div class="clear" ></div>

    <!-- footer 영역 -->
	<jsp:include page="../include/footer.jsp" />
	
</div>


</body>
</html>