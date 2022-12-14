package servlet.before;

import service.BeforeUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "before_pay", urlPatterns = { "/before_pay" })
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ordersn = request.getParameter("ordersn");
		//业务层
		BeforeUserService bs = new BeforeUserService();
		PrintWriter out = response.getWriter();
		if (bs.pay(ordersn)) {
			out.println("ok");
			return;
		} else {
			out.println("no");
			return;
		}
	}
}
