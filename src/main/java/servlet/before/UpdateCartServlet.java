package servlet.before;

import dto.UserDTO;
import service.BeforeUserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/before_updateCart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shoppingnum = request.getParameter("shoppingnum");
		String gno = request.getParameter("gno");
		HttpSession session = request.getSession(true);
		int bid = ((UserDTO)session.getAttribute("user")).getId();
		//业务层
		BeforeUserService bs = new BeforeUserService();
		RequestDispatcher rds = null;
		if(bs.updateCart(shoppingnum,gno, bid)){
			rds = request.getRequestDispatcher("before_selectCart");
		}else{
			rds = request.getRequestDispatcher("beforeUser/operateerror.jsp");
		}
		rds.forward(request, response);
	}


}
