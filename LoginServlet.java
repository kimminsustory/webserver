package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String message = "";

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/tibero");
            Connection conn = ds.getConnection();

            String query = "SELECT COUNT(*) FROM users WHERE id = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                // 2차 인증 로직 추가 필요
                message = "등록하신 이메일로 인증번호를 보내드렸어요";
                response.sendRedirect("mypage.jsp");
                return;
            } else {
                message = "존재하지 않는 ID이거나 비밀번호가 틀렸습니다!";
            }

            conn.close();
        } catch (Exception e) {
            message = "에러 발생: " + e.getMessage();
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
