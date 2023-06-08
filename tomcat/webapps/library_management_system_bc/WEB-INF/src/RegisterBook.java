package library_management_class;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import library_management_class.Track;
import library_management_class.TrackDao;
import library_management_class.Book;
import library_management_class.BookDao;
import library_management_class.ConnectionManager;

@WebServlet("/RegisterBook")
public class RegisterBook extends HttpServlet {
    public RegisterBook(){
        super();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
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

        ArrayList<Book> bookList = bookDao.selectByTitle(book.getTitle());
        request.setAttribute("insert_status", insert_status);
        response.sendRedirect("/library_management_system_bc/");
        Date latestCreatedAt = null;
        Book latestBook = null;

        for (Book b : bookList) {
            Date createdAt = new Date(b.getCreatedAt().getTime());
            if (latestCreatedAt == null || createdAt.after(latestCreatedAt)) {
                latestCreatedAt = createdAt;
                latestBook = b;
            }
        }
        String user_name =(String) session.getAttribute("user");
        Track track = new Track();
        track.setBookId(latestBook.getBookId());
        int userId = (int) session.getAttribute("user_id");
        track.setUserId(userId);
        track.setTrackStatus("書籍登録");
        TrackDao trackDao = new TrackDao(connection);
        trackDao.insert(track);
        connectionManager.commit();
        connectionManager.closeConnection();

    }
}