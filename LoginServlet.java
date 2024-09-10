import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        try {
            Class.forName("com.tmax.tibero.jdbc.TbDriver");
            Connection con = DriverManager.getConnection("jdbc:tibero:thin:@yourTiberoDBURL", "username", "password");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id=? AND password=?");
            ps.setString(1, id);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // 2차 인증: 이메일로 인증번호 전송 로직 추가 필요
                HttpSession session = request.getSession();
                String authCode = generateAuthCode(); // 인증번호 생성
                session.setAttribute("authCode", authCode);
                session.setAttribute("userID", id);

                // 이메일 전송 로직 (JavaMail API 사용)
                // sendEmail(rs.getString("email"), authCode);

                response.sendRedirect("auth.jsp");
            } else {
                response.getWriter().println("<script>alert('존재하지 않는 ID 또는 비밀번호입니다!');history.back();</script>");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<script>alert('로그인 중 오류가 발생했습니다.');history.back();</script>");
        }
    }

    private String generateAuthCode() {
        return "123456"; // 실제 난수 생성 로직 필요
    }
}
