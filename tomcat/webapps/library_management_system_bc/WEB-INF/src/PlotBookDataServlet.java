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
    private static final int RECORDS_PER_PAGE = 10;
	public PlotBookDataServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String target = request.getRequestURI();
            LoginService loginService = new LoginService();
            if(!loginService.loginCheck(request, response)){
                return;
            }
            // Setting the book name
            int currentPage = Integer.parseInt(request.getParameter("page"));
            String searchQuery = request.getParameter("search");
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection();
            OperateBook operateBook = new OperateBook(connection);
            ArrayList<Book> bookList;
            if (searchQuery != null && !searchQuery.isEmpty()) {
                // If search query is present, get books that match the query
                bookList = operateBook.searchBook(searchQuery);
            } else {
                // Otherwise, get all books
                bookList = operateBook.searchAllBooks();
            }
            int start = (currentPage - 1) * RECORDS_PER_PAGE;
            int end = Math.min(start + RECORDS_PER_PAGE, bookList.size());
            ArrayList<Book> pageBooks = new ArrayList<Book>(bookList.subList(start, end));
            ArrayList<Integer> pageNumbers = new ArrayList<Integer>();
            // ページNo.は２の乗数の感覚で表示する
            int maxPage = (int)Math.pow(2, Math.ceil(Math.log(bookList.size()) / Math.log(2)));
            pageNumbers.add(currentPage);
            int pre_num = currentPage;
            int next_num = currentPage;
            int page_size = bookList.size()/RECORDS_PER_PAGE + 1;
            for (int i = 1; i <= maxPage; i *= 2) {
                // pageNumbersの先頭に追加する
                System.out.println("next_num + i: " + (next_num + i));
                System.out.println("page_size: " + page_size);
                if(next_num + i<= page_size){
                    pageNumbers.add( next_num + i);
                }
                // pageNumbersの末尾に追加する
                if(pre_num - i> 0){
                    pageNumbers.add(0,pre_num - i);
                }
                next_num = next_num + i;
                pre_num = pre_num - i;
            }
            request.setAttribute("pageNumbers", pageNumbers);
            request.setAttribute("bookList", pageBooks);
            request.setAttribute("currentPage", currentPage);
            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/plot_book_data.jsp");
            dispatch.forward(request, response);
        }
}