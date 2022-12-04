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
@WebServlet(name = "before_deleteCart", urlPatterns = { "/before_deleteCart" })
public class DeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		HttpSession session = request.getSession(true);
		int bid = ((UserDTO)session.getAttribute("user")).getId();
		//业务层
		BeforeUserService bs = new BeforeUserService();
		//清空购物车
		if("all".equals(act)){
			bs.deleteCart(bid, null);
		}else{//删除一个商品
			String gno = request.getParameter("gno");
			bs.deleteCart(bid, gno);
		}
		request.getRequestDispatcher("before_selectCart").forward(request, response);;
	}
}
