package library_management_class;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import library_management_class.Book;
import library_management_class.OperateBook;
import library_management_class.ConnectionManager;
import java.util.ArrayList;

@WebServlet("/PlotBookData")
public class PlotBookDataServlet extends HttpServlet {

	public PlotBookDataServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            HttpSession session = request.getSession(false);
            String target = request.getRequestURI();
            LoginService loginService = new LoginService();
            if(!loginService.loginCheck(request, response)){
                return;
            }

            // Setting the book name
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection();
            OperateBook operateBook = new OperateBook(connection);
            ArrayList<Book> bookList = operateBook.searchAllBooks();
            request.setAttribute("bookList", bookList);
            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/plot_book_data.jsp");
            dispatch.forward(request, response);
        }
}