package library_management_class;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.text.*;
import java.util.*;

@WebServlet("/ReservationDetail")
public class ReservationDetailServlet extends HttpServlet{
    static final String contextPath = "/library_management_system_bc";
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        LoginService loginService = new LoginService();
        if (!loginService.loginCheck(request, response)){
            return;
        }
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        // 予約情報の取得
        TrackService trackService = new TrackService();
        int trackId = Integer.parseInt(request.getParameter("track_id"));
        Reservation my_track = (Reservation)trackService.getTrackByTrackId(trackId);
        // ユーザ情報の取得
        UserDao userDao = new UserDao(connection);
        User user = userDao.selectByUserId(my_track.getUserId());
        // 書籍情報の取得
        BookDao bookDao = new BookDao(connection);
        int bookId = my_track.getBookId();
        Book book = bookDao.selectById(bookId);
        // 今日が予約開始日から予約終了日以内かを判定
        java.util.Date today = new java.util.Date();
        if (today.compareTo(my_track.getReservationStartDate()) < 0 || today.compareTo(my_track.getReservationEndDate()) > 0){
            request.setAttribute("IsDue", false);
        }else{
            request.setAttribute("IsDue", true);
        }

        request.setAttribute("book", book);
        request.setAttribute("user", user);
        request.setAttribute("track", my_track);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/reservation_detail.jsp");
        dispatcher.forward(request, response);
    }
}