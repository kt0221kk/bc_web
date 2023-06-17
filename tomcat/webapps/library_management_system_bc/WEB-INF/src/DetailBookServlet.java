package library_management_class;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import library_management_class.*;
import java.util.*;
import java.text.SimpleDateFormat;

@WebServlet("/DetailBook")
public class DetailBookServlet extends HttpServlet {
    public DetailBookServlet(){
        super();
    }   

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
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
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection();
            OperateBook operateBook = new OperateBook(connection);
            BookDao bookDao = new BookDao(connection);
            int bookId = Integer.parseInt(request.getParameter("book_id"));
            Book book = bookDao.selectById(bookId);
            TrackService trackService = new TrackService();
            ArrayList<Track> trackList = trackService.getTrackListByBookId(bookId);
            request.setAttribute("trackList", trackList);
            request.setAttribute("book", book);
            LibraryCalendarDataFormatter libraryCalendarDataFormatter = new LibraryCalendarDataFormatter();
            List<String> occupiedDates = libraryCalendarDataFormatter.makeOccupiedDatesList(trackList);
            List<String> disabledDateList = libraryCalendarDataFormatter.makeDisabledDateList(trackList);
            ArrayList<Map<String, Object>> trackDataList = libraryCalendarDataFormatter.makeTrackDataList(trackList);
            request.setAttribute("trackDataList", trackDataList);
            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/detail_book.jsp");
            dispatch.forward(request, response);
        }
}
 