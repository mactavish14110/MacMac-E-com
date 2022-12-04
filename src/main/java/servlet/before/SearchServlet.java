package servlet.before;

import service.BeforeUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@WebServlet("/before_search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mykey = request.getParameter("mykey");
		BeforeUserService bs = new BeforeUserService();
		List<Map<String, Object>> list = bs.search(mykey);
		request.setAttribute("searchlist", list);
		request.getRequestDispatcher("beforeUser/searchResult.jsp").forward(request, response);
	}
}
