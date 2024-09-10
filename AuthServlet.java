import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AuthServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userAuthCode = request.getParameter("authCode");
        String sessionAuthCode = (String) session.getAttribute("authCode");

        if (sessionAuthCode != null && sessionAuthCode.equals(userAuthCode)) {
            response.getWriter().println("<script>alert('환영합니다!');location.href='mypage.jsp';</script>");
        } else {
            response.getWriter().println("<script>alert('인증번호를 확인해주세요!');history.back();</script>");
        }
    }
}
