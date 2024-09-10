<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
</head>
<body>
    <h2>로그인</h2>
    <form action="LoginServlet" method="post">
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" required><br>
        <label for="password">PW:</label>
        <input type="password" id="password" name="password" required><br>
        <input type="submit" value="로그인">
    </form>
    <div id="weather">
        <!-- 실시간 날씨 정보 표시 -->
    </div>
</body>
</html>
