package servlet.before;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/before_userLogout")
public class UserLogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(UserLogoutServlet.class);

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.invalidate();
        response.sendRedirect("beforeUser/userLogin.jsp");
    }
}