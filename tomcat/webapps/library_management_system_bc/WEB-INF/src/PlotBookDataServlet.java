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
            if (session == null){
                /* まだ認証されていない */
                session = request.getSession(true);
                session.setAttribute("target", target);
                response.sendRedirect("/library_management_system_bc/login");
                return;
            }else{
                Object loginCheck = session.getAttribute("login");
                if (loginCheck == null){
                    /* まだ認証されていない */
                    session.setAttribute("target", target);
                    response.sendRedirect("/library_management_system_bc/login");
                    return;
                }
            }
            // Setting the book name
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection();
            OperateBook operateBook = new OperateBook(connection);
            ArrayList<Book> bookList = operateBook.searchAllBooks();
            request.setAttribute("bookList", bookList);
            // Forwarding the request to "plot_book_data.jsp"
            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/plot_book_data.jsp");
            dispatch.forward(request, response);
            System.out.println("AccessLibraryData.java: doGet()");
        }
}