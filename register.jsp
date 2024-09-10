<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
    <h2>회원가입</h2>
    <form action="RegisterServlet" method="post">
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" required><br>
        <label for="password">PW:</label>
        <input type="password" id="password" name="password" required><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>
        <input type="submit" value="등록">
    </form>
    <div id="weather">
        <!-- 실시간 날씨 정보 표시 -->
    </div>
</body>
</html>
