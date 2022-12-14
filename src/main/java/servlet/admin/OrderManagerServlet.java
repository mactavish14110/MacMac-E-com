package servlet.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import service.AdminService;
import util.SendEmail;

import javax.mail.MessagingException;
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

@WebServlet("/admin_orderManager")
public class OrderManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(OrderManagerServlet.class);

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");//获得动作类型
		AdminService as = new AdminService();
		PrintWriter out = response.getWriter();
		RequestDispatcher rds = null;
		//查询
		if ("manager".equals(act)) {
			List<Map<String, Object>> list = as.selectOrders();
			request.setAttribute("orderList", list);
			rds = request.getRequestDispatcher("admin/orderManager.jsp");
			rds.forward(request, response);
		} else if("delete".equals(act)) {//删除
			List<Map<String, Object>> list = as.selectOrders();
			request.setAttribute("orderList", list);
			String ordersn = request.getParameter("ordersn");
			if(as.deleteOrder(ordersn)){
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print("<script language='javascript'>" +
						"alert('删除成功！');" +
						"window.location.href='admin_orderManager?act=manager';</script>')");
			}else{

				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print("<script language='javascript'>" +
						"alert('删除成功！');" +
						"window.location.href='admin_orderManager?act=manager';</script>')");
			}

			return;
		} else if (act.equals("notice")) { // 通知发货
			log.info("notice email");
			String ordersn = request.getParameter("ordersn");
			String to = request.getParameter("email");
			try {
				SendEmail.send(to, "您在MacMac购买的商品已经发货啦，订单号为：" + ordersn + "。");
				out.println("ok");
				out.flush();
				return ;
			} catch (MessagingException e) {
				log.error("send email error!");
				out.println("no");
				out.flush();
				e.printStackTrace();
				return ;
			}
		} else if (act.equals("report")) { // 销售报表
			List<Map<String, Object>> list = as.selectReport();
			request.setAttribute("orderReport", list);
			rds = request.getRequestDispatcher("admin/orderReport.jsp");
			rds.forward(request, response);
		}
	}
}
