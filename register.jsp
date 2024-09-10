<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <jsp:include page="header.jsp" />
</head>
<body>
    <form action="register" method="post">
        ID: <input type="text" name="id"><br>
        PW: <input type="password" name="password"><br>
        Email: <input type="text" name="email"><br>
        <input type="submit" value="등록">
    </form>
    <p>${message}</p>
</body>
</html>
