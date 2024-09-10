<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>마이페이지</title>
</head>
<body>
    <h2>My Calendar</h2>
    <div id="calendar">
        <!-- 캘린더 서비스 구현 -->
    </div>
    <form action="LogoutServlet" method="post">
        <input type="submit" value="로그아웃">
    </form>
    <div id="weather">
        <!-- 실시간 날씨 정보 표시 -->
    </div>
</body>
</html>
