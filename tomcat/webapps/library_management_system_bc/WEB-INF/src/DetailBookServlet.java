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
            // ログイン確認
            LoginService loginService = new LoginService();
            if(!loginService.loginCheck(request, response)){
                return;
            }
            //本情報取得
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection();
            OperateBook operateBook = new OperateBook(connection);
            BookDao bookDao = new BookDao(connection);
            int bookId = Integer.parseInt(request.getParameter("book_id"));
            Book book = bookDao.selectById(bookId);
            // トラック情報取得
            TrackService trackService = new TrackService();
            ArrayList<Track> trackList = trackService.getTrackListByBookId(bookId);

            
            //カレンダーデータの取得
            LibraryCalendarDataFormatter libraryCalendarDataFormatter = new LibraryCalendarDataFormatter();
            ArrayList<Map<String, Object>> trackDataList = libraryCalendarDataFormatter.makeTrackDataList(trackList);
            
            // リクエストにセット
            request.setAttribute("trackList", trackList);
            request.setAttribute("book", book);
            request.setAttribute("trackDataList", trackDataList);
            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/detail_book.jsp");
            dispatch.forward(request, response);
        }
}
 