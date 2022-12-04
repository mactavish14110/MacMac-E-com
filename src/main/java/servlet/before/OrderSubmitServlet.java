package servlet.before;

import dto.UserDTO;
import service.BeforeUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/before_ordersubmit")
public class OrderSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String amount = request.getParameter("amount");
		HttpSession session = request.getSession(true);
		int bid = ((UserDTO)session.getAttribute("user")).getId();
		//业务层
		BeforeUserService bs = new BeforeUserService();
		//订单提交
		int id = bs.orderSubmit(bid, amount);
		if(id > 0){
			request.setAttribute("ordersn", id);
			request.getRequestDispatcher("beforeUser/orderdone.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("beforeUser/operateerror.jsp").forward(request, response);
		}
	}

}
