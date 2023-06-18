package library_management_class;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import library_management_class.*;
import java.util.*;
import java.text.*;
import library_management_class.*;



@WebServlet("/ReservationBook")
public class ReservationBookServlet extends HttpServlet {
    public ReservationBookServlet(){
        super();
    }   

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
            LoginService loginService = new LoginService();
            if(!loginService.loginCheck(request, response)){
                return;
            }

            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection();
            BookDao bookDao = new BookDao(connection);
            int bookId = Integer.parseInt(request.getParameter("book_id"));
            Book book = bookDao.selectById(bookId);
            TrackDao trackDao = new TrackDao(connection);
            TrackService trackService = new TrackService();
            ArrayList<Track> trackList = trackService.getTrackListByBookId(bookId);
            //全てのイベントを含む日付のリスト
            LibraryCalendarDataFormatter libraryCalendarDataFormatter = new LibraryCalendarDataFormatter();
            ArrayList<String> occupiedDates = libraryCalendarDataFormatter.makeOccupiedDatesList(trackList);
            ArrayList<String> disabledDateList = libraryCalendarDataFormatter.makeDisabledDateList(trackList);
            ArrayList<Map<String, Object>> trackDataList = libraryCalendarDataFormatter.makeTrackDataList(trackList);
            
            request.setAttribute("book", book);
            request.setAttribute("trackList", trackList);
            request.setAttribute("trackDataList", trackDataList);
            request.setAttribute("occupiedDates", occupiedDates);
            request.setAttribute("disabledDateList", disabledDateList);
            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/reservation_book.jsp");
            dispatch.forward(request, response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            response.setContentType("text/html; charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            LoginService loginService = new LoginService();
            loginService.loginCheck(request, response);
            TrackService trackService = new TrackService();
            int track_id=-1;
            try{
                track_id = trackService.registerReservationTrack(request, response);
            }catch(ParseException e){
                // IDのint変換に失敗しました
                e.printStackTrace();
            }
            if(track_id >0){
                request.setAttribute("message", "予約が完了しました。");
            }else{
                request.setAttribute("message", "予約に失敗しました。");
            }

            
            // bookDao.update(book);
            // connectionManager.commit()
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection();
            UserDao userDao = new UserDao(connection);
            BookDao bookDao = new BookDao(connection);
            int bookId = Integer.parseInt(request.getParameter("book_id"));
            Book book = bookDao.selectById(bookId);
            int userId = Integer.parseInt(request.getParameter("user_id"));
            User user = userDao.selectByUserId(userId);
            Track track = trackService.getTrackByTrackId(track_id);
            connectionManager.closeConnection();
            request.setAttribute("track", track);
            request.setAttribute("start", request.getParameter("start"));
            request.setAttribute("end", request.getParameter("end"));
            request.setAttribute("user", user);
            request.setAttribute("book", book);
            // AccessLibraryDataにリダイレクト

            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/reservation_book_post.jsp");
            dispatch.forward(request, response);

    }

}