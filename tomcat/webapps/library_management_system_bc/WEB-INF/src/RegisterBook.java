package library_management_class;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

@WebServlet("/RegisterBook")
public class RegisterBook extends HttpServlet {
    public RegisterBook(){
        super();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String book_name = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String isbn = request.getParameter("ISBN");
        String genre = request.getParameter("genre");
        String status = request.getParameter("status");
        String publication_year = request.getParameter("publication_year");

        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        BookDao bookDao = new BookDao(connection);
        Book book = new Book();
        book.setTitle(book_name);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setStatus(status);
        book.setPublicationYear(Integer.parseInt(publication_year));
        book.setIsbn(isbn);
        book.setPublisher(publisher);

        int insert_status = bookDao.insert(book);
        connectionManager.commit();
        request.setAttribute("insert_status", insert_status);
        response.sendRedirect("/library_management_system_bc/");



    }
}