import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        try {
            Class.forName("com.tmax.tibero.jdbc.TbDriver");
            Connection con = DriverManager.getConnection("jdbc:tibero:thin:@yourTiberoDBURL", "username", "password");
            PreparedStatement ps = con.prepareStatement("SELECT id FROM users WHERE id=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                response.getWriter().println("<script>alert('등록된 ID입니다!');history.back();</script>");
            } else {
                ps = con.prepareStatement("INSERT INTO users (id, password, email) VALUES (?, ?, ?)");
                ps.setString(1, id);
                ps.setString(2, password);
                ps.setString(3, email);
                ps.executeUpdate();
                response.getWriter().println("<script>alert('회원가입완료!');location.href='login.jsp';</script>");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<script>alert('회원가입 중 오류가 발생했습니다.');history.back();</script>");
        }
    }
}
