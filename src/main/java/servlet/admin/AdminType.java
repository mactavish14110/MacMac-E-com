package servlet.admin;

import service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
@WebServlet("/admin_Type")
public class AdminType extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		AdminService as = new AdminService();
		if("add".equals(act)) {
			String typename = request.getParameter("typename");
			as.addType(typename);
			List<Map<String, Object>>  list = as.getGoodsType();
			HttpSession session = request.getSession(true);
			session.setAttribute("goodsType", list);
			response.sendRedirect("admin_Type?act=manager");
		}else if(act.equals("manager")) {
			List<Map<String, Object>> allType = as.getGoodsType();
			request.setAttribute("allType", allType);
			RequestDispatcher rds = request.getRequestDispatcher("admin/typeManager.jsp");
			rds.forward(request, response);
		}else if("delete".equals(act)) {
			String id = request.getParameter("id");
			String s = as.deleteType(id);
			List<Map<String, Object>>  list = as.getGoodsType();
			HttpSession session = request.getSession(true);
			session.setAttribute("goodsType", list);
			PrintWriter out = response.getWriter();

			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script language='javascript'>" +
					"alert('"+s+"');" +
					"window.location.href='admin_Type?act=manager';</script>')");

		}
	}


}
