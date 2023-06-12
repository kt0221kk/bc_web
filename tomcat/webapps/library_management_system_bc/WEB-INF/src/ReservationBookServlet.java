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
            HttpSession session = request.getSession(false);
            String target = request.getRequestURI();
            if (session == null){
                /* まだ認証されていない */
                session = request.getSession(true);
                session.setAttribute("target", target);
                response.sendRedirect("/library_management_system_bc/login.jsp");
                return;
            }else{
                Object loginCheck = session.getAttribute("login");
                if (loginCheck == null){
                    /* まだ認証されていない */
                    session.setAttribute("target", target);
                    response.sendRedirect("/library_management_system_bc/login.jsp");
                    return;
                }
            }

            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection();
            BookDao bookDao = new BookDao(connection);
            int bookId = Integer.parseInt(request.getParameter("book_id"));
            Book book = bookDao.selectById(bookId);
            request.setAttribute("book", book);
            TrackDao trackDao = new TrackDao(connection);
            ArrayList<Track> trackList = trackDao.selectByBookId(bookId);
            request.setAttribute("trackList", trackList);
            ArrayList<Map<String, Object>> trackDataList = new ArrayList<>();
            //全てのイベントを含む日付のリスト
            List<String> occupiedDates = new ArrayList<>();
            List<String> disabledDateList = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");


            for(Track track : trackList){
                Map<String, Object> trackData = new HashMap<>();
                trackData.put("title", track.getTrackStatus());
                // trackData.put("start", track.getTrackTime());
                String color = "";
                String textColor = "";
                String url = "";
                java.util.Date start=null;
                java.util.Date end=null;
                Calendar startCal = Calendar.getInstance();
                Calendar endCal = Calendar.getInstance();

                switch (track.getTrackStatus()) {
                    case "書籍登録":
                        start = track.getTrackTime();
                        end = track.getTrackTime();
                        // グレー
                        color = "#808080";
                        textColor = "white";

                        break;
                    case "貸出":
                        if(book.getStatus().equals("貸出中")){
                            color = "red";
                            Due due = (Due)track;
                            start = due.getBorrowDate();
                            //一日追加
                            end= due.getReturnDueDate();
                            textColor = "white";
                            startCal.setTime(start);
                            endCal.setTime(end);
                            for (java.util.Date date = startCal.getTime(); !startCal.after(endCal); startCal.add(Calendar.DATE, 1), date = startCal.getTime()) {
                                occupiedDates.add(sdf.format(date));
                                disabledDateList.add(sdf2.format(date));
                            }
                            url = "/library_management_system_bc/DetailTrack?track_id=" + track.getTrackId()+"&book_id="+bookId;
                        }else{
                            // 次のfor文のループに入る
                            continue;
                        }
                        break;
                    case "予約":
                        Reservation reservation = (Reservation)track;
                        if(reservation.getIsActive()){
                            color = "yellow";
                            start = reservation.getReservationStartDate();
                            end=reservation.getReservationEndDate();
                            textColor = "black";
                            startCal.setTime(start);
                            endCal.setTime(end);
                            for (java.util.Date date = startCal.getTime(); !startCal.after(endCal); startCal.add(Calendar.DATE, 1), date = startCal.getTime()) {
                                occupiedDates.add(sdf.format(date));
                                disabledDateList.add(sdf2.format(date));

                            }
                            url = "/library_management_system_bc/DetailTrack?track_id=" + track.getTrackId()+"&book_id="+bookId;
                            break;
                        }else{
                            // 次のfor文のループに入る
                            continue;
                        }
                        
                }
                trackData.put("start", sdf.format(start));
                trackData.put("color", color);
                trackData.put("textColor", textColor);
                trackData.put("url", url);

                endCal.add(Calendar.DATE, 1);
                String formattedEndDate = sdf.format(endCal.getTime());
                trackData.put("end",formattedEndDate);
                            
                trackDataList.add(trackData);
            }
            // 今日から30日後までの日付について、それがoccupiedDatesに含まれていない場合は新しいイベントを作成
            TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo"));
            Calendar cal = Calendar.getInstance(tz);
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < 60; i++) {
                String date = sdf.format(cal.getTime());
                if (!occupiedDates.contains(date)) {
                    Map<String, Object> trackData = new HashMap<>();
                    if (i == 0) {
                        trackData.put("title", "貸出可能");
                        // trackData.put("title", date);
                        sdf = new SimpleDateFormat("yyyy-MM-dd");

                    } else {
                        trackData.put("title", "予約可能");
                    }
                    trackData.put("start", date);
                    trackData.put("end", date);
                    if (i == 0) {
                        trackData.put("color", "green");
                    } else {
                        //淡い緑色
                        trackData.put("color", "#98FB98");
                    }
                    if (i == 0) {
                        trackData.put("textColor", "white");
                    } else {
                        trackData.put("textColor", "black");
                    }
                    trackData.put("url", "/library_management_system_bc/ReservationBook");
                    trackDataList.add(trackData);
                }
                cal.add(Calendar.DATE, 1);
            }
            request.setAttribute("trackDataList", trackDataList);
            request.setAttribute("occupiedDates", occupiedDates);
            request.setAttribute("disabledDateList", disabledDateList);
            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/reservation_book.jsp");
            dispatch.forward(request, response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
            String target = request.getRequestURI();
            response.setContentType("text/html; charset=UTF-8");

            request.setCharacterEncoding("UTF-8");

            if (session == null){
                /* まだ認証されていない */
                session = request.getSession(true);
                session.setAttribute("target", target);
                response.sendRedirect("/library_management_system_bc/login.jsp");
                return;
            }else{
                Object loginCheck = session.getAttribute("login");
                if (loginCheck == null){
                    /* まだ認証されていない */
                    session.setAttribute("target", target);
                    response.sendRedirect("/library_management_system_bc/login.jsp");
                    return;
                }
            }
            Track track= new Track();

            String book_status = request.getParameter("book_status");
            int bookId = Integer.parseInt(request.getParameter("book_id"));
            int userId = Integer.parseInt(request.getParameter("user_id"));
            String reservationStartDate = request.getParameter("start");
            String reservationEndDate = request.getParameter("end");
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection();
            int insert_result =0;
            TrackDao trackDao = new TrackDao(connection);
            // 予約が完了したら、予約した本の詳細画面に遷移する
            BookDao bookDao = new BookDao(connection);
            Book book = bookDao.selectById(bookId);

            if(book_status.equals("貸出")){
                Due due = new Due();
                due.setTrackStatus("貸出");
                due.setBookId(bookId);
                due.setUserId(userId);
                try{
                    due.setBorrowDate(sdf.parse(reservationStartDate));
                    due.setReturnDueDate(sdf.parse(reservationEndDate));

                }catch(ParseException e){
                    e.printStackTrace();
                }
                insert_result = trackDao.insert(due);
                connectionManager.commit();
                book.setStatus("貸出中");
                
            }else if(book_status.equals("予約")){
                Reservation reservation = new Reservation();
                reservation.setTrackStatus("予約");
                reservation.setBookId(bookId);
                reservation.setUserId(userId);
                try{
                    reservation.setReservationStartDate(sdf.parse(reservationStartDate));
                reservation.setReservationEndDate(sdf.parse(reservationEndDate));
                }catch(ParseException e){
                    e.printStackTrace();
                }
                insert_result = trackDao.insert(reservation);
                connectionManager.commit();
            }
            // TrackDao trackDao = new TrackDao(connection);
            // int insert_result = trackDao.insert(track);
            connectionManager.commit();
            if(insert_result == 1){
                request.setAttribute("message", "予約が完了しました。");
            }else{
                request.setAttribute("message", "予約に失敗しました。");
            }

            
            bookDao.update(book);
            connectionManager.commit();
            UserDao userDao = new UserDao(connection);
            User user = userDao.find(userId);
            connectionManager.closeConnection();
            request.setAttribute("book", book);
            request.setAttribute("book_status", book_status);
            request.setAttribute("track", track);
            request.setAttribute("track", track);
            request.setAttribute("user_id", userId);
            request.setAttribute("insert_result", insert_result);
            request.setAttribute("start", request.getParameter("start"));
            request.setAttribute("end", request.getParameter("end"));
            request.setAttribute("user", user);
            // AccessLibraryDataにリダイレクト

            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/reservation_book_post.jsp");
            dispatch.forward(request, response);

    }

}