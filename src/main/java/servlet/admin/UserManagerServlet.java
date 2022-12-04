package servlet.admin;

import service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/admin_userManager")
public class UserManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doPost(request, response);
//	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");//获得动作类型
		AdminService as = new AdminService();
		PrintWriter out = response.getWriter();
		List<Map<String, Object>> list = as.selectUsers();
		request.setAttribute("userList", list);
		RequestDispatcher rds = null;
		//查询
		if ("manager".equals(act)) {
			rds = request.getRequestDispatcher("admin/userManager.jsp");
			rds.forward(request, response);
		} else if("delete".equals(act)){
			String bid = request.getParameter("bid");
			String rs = as.deleteUser(bid);
			if (rs.equals("nodelete")) {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print("<script language='javascript'>" +
						"alert('删除成功！');" +
						"window.location.href='admin_userManager?act=manager';</script>')");
			} else {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print("<script language='javascript'>" +
						"alert('删除成功！');" +
						"window.location.href='admin_userManager?act=manager';</script>')");
			}

			return;
		}
	}

}
