package calltest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("/CallTest")
public class CallTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CallTest() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 呼び出し元Jspからデータ受け取り
		request.setCharacterEncoding("UTF8");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String status = request.getParameter("status");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");


		// 呼び出し先Jspに渡すデータセット
		request.setAttribute("title", title);
		request.setAttribute("author", author);
		request.setAttribute("status", status);
		request.setAttribute("start_date", start_date);
		request.setAttribute("end_date", end_date);

		// result.jsp にページ遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("result.jsp");
		dispatch.forward(request, response);
	}
}