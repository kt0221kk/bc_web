package library_management_class;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/DueDetail")
public class DueDetailServlet extends HttpServlet {
    public DueDetailServlet(){
        super();
    }   

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
            // ログイン確認
            LoginService loginService = new LoginService();
            if(!loginService.loginCheck(request, response)){
                return;
            }
            // トラック情報取得
            int track_id = Integer.parseInt(request.getParameter("track_id"));
            TrackService trackService = new TrackService();
            Track track = trackService.getTrackByTrackId(track_id);
            //本情報取得
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection();
            OperateBook operateBook = new OperateBook(connection);
            BookDao bookDao = new BookDao(connection);
            int bookId = track.getBookId();
            Book book = bookDao.selectById(bookId);
            
            // ユーザ情報の取得
            UserDao userDao = new UserDao(connection);
            User user = userDao.selectByUserId(track.getUserId());
            connectionManager.closeConnection();
            // リクエストにセット
            request.setAttribute("due", (Due)track);
            request.setAttribute("book", book);
            request.setAttribute("user", user);
            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/due_detail.jsp");
            dispatch.forward(request, response);
        }
}