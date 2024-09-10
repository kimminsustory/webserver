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
            PreparedStatement ps = con.prepareStatement("SELECT email FROM users WHERE id=? AND password=?");
            ps.setString(1, id);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String email = rs.getString("email");
                // Generate and send OTP (or use email link as a second factor)
                String otpCode = generateOtpCode(); // OTP 코드 생성
                sendOtpEmail(email, otpCode); // OTP 코드 이메일로 전송

                // Store OTP in session
                HttpSession session = request.getSession();
                session.setAttribute("otpCode", otpCode);
                session.setAttribute("userID", id);

                response.sendRedirect("otp.jsp"); // OTP 입력 페이지로 리다이렉트
            } else {
                response.getWriter().println("<script>alert('잘못된 ID 또는 비밀번호입니다.');history.back();</script>");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<script>alert('로그인 중 오류가 발생했습니다.');history.back();</script>");
        }
    }

    private String generateOtpCode() {
        return String.format("%06d", new java.util.Random().nextInt(999999));
    }

    private void sendOtpEmail(String email, String otpCode) {
        // 이메일 전송 로직 (JavaMail API 사용)
        // 구현 방법은 아래에서 추가 설명
    }
}
